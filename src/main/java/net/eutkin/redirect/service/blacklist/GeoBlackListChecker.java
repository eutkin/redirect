package net.eutkin.redirect.service.blacklist;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CountryResponse;
import com.maxmind.geoip2.record.Country;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Service
@ConditionalOnProperty("geo.location.country-iso-code.white-list")
@Slf4j
public class GeoBlackListChecker implements BlackListChecker {

    private final Set<String> whiteListIsoCodes;

    private final DatabaseReader databaseReader;

    @SneakyThrows(IOException.class)
    public GeoBlackListChecker(
            @Value("${geo.location.country-iso-code.white-list}") List<String> whiteListIsoCodes
    ) {
        this.whiteListIsoCodes = whiteListIsoCodes
                .stream()
                .map(String::trim)
                .map(String::toUpperCase)
                .collect(toSet());
        Resource resource = new ClassPathResource("GeoLite2-Country.mmdb");
        try(InputStream inputStream = resource.getInputStream()) {
            this.databaseReader = new DatabaseReader.Builder(inputStream).build();
        }
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
