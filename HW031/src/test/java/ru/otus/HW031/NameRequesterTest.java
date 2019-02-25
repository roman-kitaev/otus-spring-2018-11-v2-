package ru.otus.HW031;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW031.localisationservice.LocalisationService;
import ru.otus.HW031.localisationservice.LocalisationServiceConfig;
import ru.otus.HW031.namerequester.NameRequester;
import ru.otus.HW031.namerequester.NameRequesterImpl;
import ru.otus.HW031.properties.QuizerProperties;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NameRequesterTest {
    NameRequester requester;

    @Autowired
    QuizerProperties properties;

    @Before
    public void setUp() throws IOException {
        LocalisationService localisationService =
                LocalisationServiceConfig.getLocalisationService(
                        properties);
        requester = new NameRequesterImpl(localisationService);
    }

    @Test
    public void goodNameInputTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "Ivan Ivanov".getBytes());
        String name = requester.askName(in);
        Assert.assertNotNull(name);
    }
}
