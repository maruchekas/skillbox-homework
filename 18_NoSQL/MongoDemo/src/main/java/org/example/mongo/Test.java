package org.example.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.bson.BsonDocument;
import org.bson.Document;

public class Test {

  public static void main(String[] args) {
    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

    MongoDatabase database = mongoClient.getDatabase("test");

    // Создаем коллекцию
    MongoCollection<Document> collection = database.getCollection("bookshelf");

    // Удалим из нее все документы
    collection.drop();

    // Создадим первый документ
    Document firstDocument = new Document()
        .append("title", "Хроники Амбера")
        .append("author", "Роджер Желязны")
        .append("release", 1978);

    //Создадим объекты книг
    List<Book> books = new ArrayList<>();
    books.add(new Book("Мастер и Маргарита", "Михаил Булгаков", 1967));
    books.add(new Book("Незнайка на Луне", "Николай Носов", 1965));
    books.add(new Book("Город пустых", "Ренсом Риггз", 2015));
    books.add(new Book("Дом странных детей", "Ренсом Риггз", 2012));
    books.add(new Book("Мастер сновидений", "Роджер Желязны", 1967));

    //Создадим второй документ из объекта
    Document secondDocument = new Document()
        .append("title", books.get(0).getTitle())
        .append("author", books.get(0).getAuthor())
        .append("release", books.get(0).getRelease());

    // Вставляем документы в коллекцию
    collection.insertOne(firstDocument);
    collection.insertOne(secondDocument);

    //Удалим документ(дубль), созданный из объекта
    collection.deleteOne(BsonDocument.parse("{title: \"Мастер и Маргарита\"}"));

    //Вставим документы из коллекции
    books.forEach(b -> {
      Document document = Document.parse(b.toString());
      collection.insertOne(document);
    });

    // Используем JSON-синтаксис для создания объекта из строки
    Document nextDocument = Document.parse(
        "{title: \"Холакратия\", author:\"Брайан Дж. Робертсон\", release: 2017}"
    );
    collection.insertOne(nextDocument);

    //Снова удалим документ дубль
    collection.deleteOne(BsonDocument.parse("{title: \"Мастер и Маргарита\"}"));

    //Аналогично примеру выше, но парсим строку из объекта
    Document oneMore = Document.parse(books.get(0).toString());
    collection.insertOne(oneMore);

    System.out.println("Выводим список всех книг");
    collection.find().forEach((Consumer<Document>) System.out::println);

    // Используем JSON-синтаксис для написания запроса
    BsonDocument query = BsonDocument.parse("{author: \"Ренсом Риггз\"}");
    System.out.println("Книги автора Ренсом Риггз");
    collection.find(query).forEach((Consumer<Document>) System.out::println);

    System.out.println("Книга с самым ранним годом издания");
    query = BsonDocument.parse("{release: 1}");
    collection.find().sort(query).limit(1).forEach((Consumer<Document>) System.out::println);
  }
}
