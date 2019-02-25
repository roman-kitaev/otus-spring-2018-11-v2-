package ru.otus.HW041;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW041.service.LocalisationService;
import ru.otus.HW041.service.impl.LocalisationServiceImpl;
import ru.otus.HW041.service.NameRequester;
import ru.otus.HW041.service.impl.NameRequesterImpl;
import ru.otus.HW041.properties.QuizerProperties;

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
                new LocalisationServiceImpl();
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
