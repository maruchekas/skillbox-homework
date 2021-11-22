package org.example.mongo;

import static org.example.mongo.Storage.*;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import java.util.Scanner;
import org.slf4j.LoggerFactory;

public class Demo {

  public static void runDemo() {
    ((LoggerContext) LoggerFactory.getILoggerFactory()).getLogger("org.mongodb.driver").setLevel(
        Level.ERROR);

    Scanner scanner = new Scanner(System.in);
    String commandExamples = "\nПримеры команд: "
        + "\n\tДОБАВИТЬ_МАГАЗИН Шестерочка"
        + "\n\tДОБАВИТЬ_ТОВАР Хлеб 42"
        + "\n\tВЫСТАВИТЬ_ТОВАР Хлеб Шестерочка"
        + "\n\tСТАТИСТИКА_ТОВАРОВ"
        + "\n\tВЫХОД\n";

    System.out.println("Для начала работы введите команду. " + commandExamples);

    for (; ; ) {
      System.out.println("Введите команду");
      String[] command = split(scanner.nextLine().trim());
      String action = command[0];
      String item = command.length > 1 ? command[1] : "";

      switch (action) {
        case ("ДОБАВИТЬ_МАГАЗИН"):
          addShop(item);
          break;

        case ("ДОБАВИТЬ_ТОВАР"):
          addProduct(item);
          break;

        case ("ВЫСТАВИТЬ_ТОВАР"):
          addProductToShop(item);
          break;

        case ("СТАТИСТИКА_ТОВАРОВ"):
          showStatistics();

          break;

        case ("ВЫХОД"):
          shutdown();
          return;
        default:
          System.out.println("Неверный формат команды");
          System.out.println(commandExamples);
      }
    }
  }

}
