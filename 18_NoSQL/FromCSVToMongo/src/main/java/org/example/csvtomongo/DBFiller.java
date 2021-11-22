package org.example.csvtomongo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DBFiller {

  //Установливает соединение, инициализирует БД, создает пустую коллекцию
  public static MongoCollection<Document> increaseMongo(){
    MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);

    MongoDatabase database = mongoClient.getDatabase("local");

    MongoCollection<Document> collection = database.getCollection("students");

    collection.drop();
    return collection;
  }

  //Заполняет коллекцию документами, полученными из строк файла csv
  public static void fillCollection(String pathCsv, MongoCollection<Document> collection){
    Parser.getStudents(pathCsv).forEach(s -> {
      Document document = Document.parse(s.toString());
      collection.insertOne(document);
    });
  }
}
