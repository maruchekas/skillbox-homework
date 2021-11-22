package org.example.csvtomongo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Parser {

  //Читает файл csv
  static List<String> readAllLines(String pathMovementsCsv) {
    List<String> allLines = new ArrayList<>();
    try {
      allLines = Files.readAllLines(Path.of(pathMovementsCsv));
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    return allLines;
  }

  //В строке разделяет студентов и курсы
  private static List<String[]> parseCSV(String pathCsv) {
    List<String[]> lines = new ArrayList<>();
    readAllLines(pathCsv).forEach(l -> lines.add(l.split("\"")));

    return lines;
  }

  //Формирует список студентов
  public static List<Student> getStudents(String pathCsv){
    List<Student> students = new ArrayList<>();

    for (String[] array : parseCSV(pathCsv)
    ) {
      students.add(new Student(array[0].split(",")[0],
          Integer.parseInt(array[0].split(",")[1]),
          parseCourses(array[1].split(","))));
    }
    return students;
  }

  //Разделяет курсы из массива
  private static String[] parseCourses(String[] courses){
    for (int i = 0; i < courses.length; i++) {
      courses[i] = "\"" + courses[i] + "\"";
    }
    return courses;
  }
}
