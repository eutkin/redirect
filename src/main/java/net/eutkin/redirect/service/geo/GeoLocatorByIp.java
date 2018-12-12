package net.eutkin.redirect.service.geo;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.eutkin.redirect.service.blacklist.BlackListChecker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

@Service
@ConditionalOnProperty("geo.location.country-iso-code.white-list")
@Slf4j
public class GeoLocatorByIp implements BlackListChecker {

    private final Set<String> whiteListIsoCodes;

    private final DatabaseReader databaseReader;

    public GeoLocatorByIp(
            @Value("${geo.location.country-iso-code.white-list}") String whiteListIsoCodes
    ) {
        this(Stream.of(whiteListIsoCodes.split(",")));
    }

    @SneakyThrows(IOException.class)
    public GeoLocatorByIp(
            Stream<String> whiteListIsoCodes
    ) {
        this.whiteListIsoCodes = (whiteListIsoCodes == null ? Stream.<String>empty() : whiteListIsoCodes)
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(toSet());
        Resource resource = new ClassPathResource("GeoLite2-Country.mmdb");
        File file = resource.getFile();
        this.databaseReader = new DatabaseReader.Builder(file).build();
    }


    @Override
    public boolean isOnBlackList(String ip, List<String> blackList) {
        try {
            InetAddress ipAddress = InetAddress.getByName(ip);
            CountryResponse countryResponse = databaseReader.country(ipAddress);
            Country country = countryResponse.getCountry();

            String countryIsoCode = country.getIsoCode().toUpperCase();
            return !whiteListIsoCodes.contains(countryIsoCode);
        } catch (IOException | GeoIp2Exception e) {
            log.error(e.getMessage(), e);
            return true;
        }

    }
}
