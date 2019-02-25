package ru.otus.HW031;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.HW031.questioner.Questioner;
import ru.otus.HW031.quiz.Quiz;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class QuestionerTest {
    @Autowired
    Questioner questioner;

    @Test
    public void askMakesCorrectResultsTest() {
        List<String[]> quizList = new ArrayList<String[]>();
        quizList.add(new String[]{"1", "Who was the first president of the USA?", "George Washington"});
        quizList.add(new String[]{"2", "How many women did Henry VIII have?", "6"});
        quizList.add(new String[]{"3", "Which city became capital of West-Germany in 1949?", "Bonn"});
        quizList.add(new String[]{"4", "On which island was Napoleon born?", "Corsica"});
        quizList.add(new String[]{"5", "Which French king was called the Sun King?", "Louis XIV"});
        Quiz quiz = new Quiz("Test testov", quizList);

        ByteArrayInputStream in = new ByteArrayInputStream(
                ("George Washington\n" +
                        "6\n" +
                        "Bonn\n" +
                        "Corsica___\n" +
                        "Louis XIV___\n").getBytes());
        boolean[] expectedResult = {true, true, true, false, false};
        questioner.ask(in, quiz);
        Assert.assertArrayEquals(quiz.getResult(), expectedResult);
    }
}
