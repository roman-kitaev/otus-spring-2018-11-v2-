import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.otus.main.localisationservice.LocalisationService;
import ru.otus.main.localisationservice.LocalisationServiceConfig;
import ru.otus.main.namerequester.NameRequester;
import ru.otus.main.namerequester.NameRequesterImpl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class NameRequesterTest {
    NameRequester requester;

    @Before
    public void setUp() throws IOException {
        String filePropsName = "testprops.properties";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(filePropsName);
        Properties property = new Properties();
        property.load(in);

        LocalisationService localisationService =
                LocalisationServiceConfig.getLocalisationService(
                        property.getProperty("data.locale"));
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
