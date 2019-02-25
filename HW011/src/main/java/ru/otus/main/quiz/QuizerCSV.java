package ru.otus.main.quiz;

import com.opencsv.CSVReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class QuizerCSV implements Quizer {
    String fileName;

    public QuizerCSV(String fileName) {
        this.fileName = fileName;
    }

    public Quiz makeQuiz() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(fileName);
        CSVReader csvReader = new CSVReader(new InputStreamReader(in));
        List<String[]> allRows = null;
        try {
            allRows = csvReader.readAll();
        } catch (IOException e) {
        }
        return new Quiz(allRows);
    }
}
