package org.example.csvtomongo;

import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Projections.include;

import com.mongodb.client.MongoCollection;
import java.util.Scanner;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;

public class Main {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);
    System.out.println("Введите путь к CSV файлу");
    String pathCsv = scanner.nextLine();
    MongoCollection<Document> collection = DBFiller.increaseMongo();

    //Вставим документы из коллекции
    DBFiller.fillCollection(pathCsv, collection);

    System.out.println("Общее количество студентов в базе - " + collection.countDocuments());
    System.out.println("Количество студентов старше 40 лет - " + collection
        .countDocuments(BsonDocument.parse("{age: {$gt: 40}}")));
    collection.find().sort(BsonDocument.parse("{age: 1}")).limit(1)
        .projection(fields(include("name"), excludeId()))
        .forEach((Consumer<Document>) System.out::println);
    collection.find().sort(BsonDocument.parse("{age: -1}")).limit(1)
        .projection(fields(include("courses"), excludeId()))
        .forEach((Consumer<Document>) System.out::println);

  }
}
