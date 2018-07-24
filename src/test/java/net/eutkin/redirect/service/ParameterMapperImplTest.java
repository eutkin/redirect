package net.eutkin.redirect.service;

import lombok.SneakyThrows;
import lombok.val;
import net.eutkin.redirect.entity.Destination;
import net.eutkin.redirect.entity.Parameter;
import net.eutkin.redirect.service.model.UrlMapping;
import net.eutkin.redirect.service.util.GuidValueGenerator;
import net.eutkin.redirect.service.util.ParameterMapper;
import net.eutkin.redirect.service.util.ParameterMapperImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.net.URL;
import java.util.Collection;

import static java.util.Arrays.asList;
import static net.eutkin.redirect.entity.ParameterName.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * <p>
 * Создан 07.07.2016
 * <p>
 *
 * @author Евгений Уткин (evgeny.utkin@mediascope.net)
 */
@RunWith(Parameterized.class)
public class ParameterMapperImplTest {

    private final ParameterMapper parameterMapper;
    private final UrlMapping expected;
    private final URL destUrl;
    private final Destination destination;


    public ParameterMapperImplTest(UrlMapping expected, URL url, Destination destination) {
        val mockGuidGenerator = mock(GuidValueGenerator.class);
        when(mockGuidGenerator.generate()).thenReturn("abc");
        this.parameterMapper = new ParameterMapperImpl(mockGuidGenerator);
        this.expected = expected;
        this.destination = destination;
        this.destUrl = url;
    }

    @Parameterized.Parameters
    @SneakyThrows
    public static Collection<Object[]> data() {
        return asList(new Object[][]{
                {
                        new UrlMapping(new URL("http://dist.ru?s1=1&s2=2&s3=3&s4=4&s5=5&s6=6&s7=7")),
                        new URL("http://t.me?utm_source=1&utm_medium=2&utm_campaign=3&utm_term=4&utm_keyword=5&utm_content=6&url=7"),
                        new Destination()
                                .setUrl(new URL("http://dist.ru"))
                                .setParameters(asList(
                                new Parameter().setDestName("s1").setSrcName(utm_source),
                                new Parameter().setDestName("s2").setSrcName(utm_medium),
                                new Parameter().setDestName("s3").setSrcName(utm_campaign),
                                new Parameter().setDestName("s4").setSrcName(utm_term),
                                new Parameter().setDestName("s5").setSrcName(utm_keyword),
                                new Parameter().setDestName("s6").setSrcName(utm_content),
                                new Parameter().setDestName("s7").setSrcName(url)
                        ))
                },
                {
                        new UrlMapping(new URL("http://dist.ru/1/6?s2=2&s3=3&s4=4&s5=5&s7=7")),
                        new URL("http://t.me?utm_source=1&utm_medium=2&utm_campaign=3&utm_term=4&utm_keyword=5&utm_content=6&url=7"),
                        new Destination()
                                .setUrl(new URL("http://dist.ru"))
                                .setParameters(asList(
                                new Parameter().setSrcName(utm_source),
                                new Parameter().setDestName("s2").setSrcName(utm_medium),
                                new Parameter().setDestName("s3").setSrcName(utm_campaign),
                                new Parameter().setDestName("s4").setSrcName(utm_term),
                                new Parameter().setDestName("s5").setSrcName(utm_keyword),
                                new Parameter().setSrcName(utm_content),
                                new Parameter().setDestName("s7").setSrcName(url)
                        ))
                },
                {
                        new UrlMapping(new URL("http://dist.ru?s1=1&s2=2&s3=abc&s4=4&s5=5&s6=6&s7=7"), "abc"),
                        new URL("http://t.me?utm_source=1&utm_medium=2&utm_campaign=guid&utm_term=4&utm_keyword=5&utm_content=6&url=7"),
                        new Destination()
                                .setUrl(new URL("http://dist.ru"))
                                .setParameters(asList(
                                new Parameter().setDestName("s1").setSrcName(utm_source),
                                new Parameter().setDestName("s2").setSrcName(utm_medium),
                                new Parameter().setDestName("s3").setSrcName(utm_campaign),
                                new Parameter().setDestName("s4").setSrcName(utm_term),
                                new Parameter().setDestName("s5").setSrcName(utm_keyword),
                                new Parameter().setDestName("s6").setSrcName(utm_content),
                                new Parameter().setDestName("s7").setSrcName(url)
                        ))
                }
        });
    }

    @Test
    public void map() {
        assertEquals(expected, this.parameterMapper.map(destUrl, destination));
    }

}