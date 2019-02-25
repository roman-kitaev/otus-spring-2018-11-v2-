import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import ru.otus.main.namerequester.NameRequester;
import ru.otus.main.namerequester.NameRequesterImpl;
import ru.otus.main.questioner.Questioner;
import ru.otus.main.questioner.QuestionerImpl;
import ru.otus.main.quiz.Quizer;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class NameRequesterTest {
    NameRequester requester;

    @Before
    public void setUp() {
        requester = new NameRequesterImpl();
    }

    @Test
    public void goodNameInputTest() {
        ByteArrayInputStream in = new ByteArrayInputStream(
                "Ivan Ivanov".getBytes());
        String name = requester.askName(in);
        Assert.assertNotNull(name);
    }
}
