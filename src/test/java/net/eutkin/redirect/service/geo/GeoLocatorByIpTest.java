package net.eutkin.redirect.service.geo;

import net.eutkin.redirect.service.blacklist.BlackListChecker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GeoLocatorByIpTest {

    @Autowired
    private BlackListChecker blackListChecker;


    @Test
    public void locate()  {
        boolean onBlackList = blackListChecker.isOnBlackList("88.212.238.76", Collections.emptyList());
        assertFalse(onBlackList);
    }

    @SpringBootConfiguration
    static class Config {

        @Bean
        public BlackListChecker blackListChecker( @Value("${geo.location.country-iso-code.white-list:}") String whiteList) {
            return new GeoLocatorByIp(whiteList);
        }
    }
}