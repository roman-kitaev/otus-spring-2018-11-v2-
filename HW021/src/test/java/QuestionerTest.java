import org.junit.Assert;
import org.junit.Test;
import ru.otus.main.questioner.Questioner;
import ru.otus.main.questioner.QuestionerImpl;
import ru.otus.main.quiz.Quiz;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class QuestionerTest {
    @Test
    public void askMakesCorrectResultsTest() {
        List<String[]> quizList = new ArrayList<String[]>();
        quizList.add(new String[]{"1", "Who was the first president of the USA?", "George Washington"});
        quizList.add(new String[]{"2", "How many women did Henry VIII have?", "6"});
        quizList.add(new String[]{"3", "Which city became capital of West-Germany in 1949?", "Bonn"});
        quizList.add(new String[]{"4", "On which island was Napoleon born?", "Corsica"});
        quizList.add(new String[]{"5", "Which French king was called the Sun King?", "Louis XIV"});
        Quiz quiz = new Quiz(quizList);

        Questioner questioner =
                new QuestionerImpl();

        ByteArrayInputStream in = new ByteArrayInputStream(
                ("George Washington\n" +
                        "6\n" +
                        "Bonn\n" +
                        "Corsica___\n" +
                        "Louis XIV___\n").getBytes());
        boolean[] expectedResult = {true, true, true, false, false};
        boolean actualResult[] = questioner.ask(in, quiz);
        Assert.assertArrayEquals(actualResult, expectedResult);
    }
}
