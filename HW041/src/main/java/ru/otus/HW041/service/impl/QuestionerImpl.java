package ru.otus.HW041.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.HW041.data.Quiz;
import ru.otus.HW041.service.Questioner;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

@Service
public class QuestionerImpl implements Questioner {
    public void ask(InputStream in, Quiz quiz) {
        System.out.println("QUIZ:");
        Scanner scan = new Scanner(in);
        List<String[]> quizList = quiz.getQuiz();
        boolean[] result = new boolean[quizList.size()];

        for(int i = 0; i < quizList.size(); i++) {
            result[i] = false;
            System.out.println(quizList.get(i)[0] + " : " +
                    quizList.get(i)[1]);
            String ans = scan.nextLine();
            result[i] = ans.equals(quizList.get(i)[2]);
        }
        quiz.setResult(result);
    }
}
