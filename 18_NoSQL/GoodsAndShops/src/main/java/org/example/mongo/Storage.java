package org.example.mongo;

import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import java.util.ArrayList;
import java.util.Arrays;
import org.bson.Document;

public class Storage {

  private static final MongoClient mongoClient;
  private static final MongoDatabase mongoDatabase;
  private static final MongoCollection<Document> shops;
  private static final MongoCollection<Document> goods;

  static {
    mongoClient = new MongoClient();
    mongoDatabase = mongoClient.getDatabase("marketPlace");
    shops = mongoDatabase.getCollection("shops");
    goods = mongoDatabase.getCollection("goods");
  }

  static void addShop(String shopName) {
    if (!shopName.isEmpty()) {
      Document shop = new Document("name", shopName);
      shop.append("goods", new ArrayList<String>());
      if (getShop(shopName) == null) {
        shops.insertOne(shop);
        System.out.println("Магазин " + shopName + " добавлен!");
      } else {
        System.out.println("Такой магазин уже есть");
      }
    } else {
      System.out.println("Не задано название магазина!");
    }
  }

  static void addProduct(String input) {
    try {
      String[] item = split(input);
      String productName = item[0];
      Document product = new Document("name", productName);
      product.append("price", Integer.parseInt(item[1]));

      if (getItem(productName) == null) {
        goods.insertOne(product);
        System.out.println("Товар " + productName + " добавлен!");
      } else {
        System.out.println("Такой товар уже есть");
      }
    } catch (Exception e) {
      System.out.println("Неверный формат команды");
    }
  }

  static void addProductToShop(String input) {
    try {
      String[] item = split(input);
      String shopName = item[1];
      String productName = item[0];

      shops.updateOne(getShop(shopName), new Document("$addToSet",
          new Document("goods", getItem(productName).get("name"))));
      System.out.println("Товар " + productName + " выставлен на полку магазина " + shopName);
    } catch (Exception e) {
      System.out.println("Магазин или товар не найден! ");
    }
  }

  private static AggregateIterable<Document> getStatistics() {
    return goods.aggregate(Arrays.asList(
        Aggregates.lookup("shops", "name", "goods", "shops_list"),
        Aggregates.unwind("$shops_list"),
        Aggregates.group("$shops_list.name",
            Accumulators.sum("goodscount", 1),
            Accumulators.avg("avgprice", "$price"),
            Accumulators.max("maxprice", "$price"),
            Accumulators.min("minprice", "$price")
        )
    ));
  }

  private static void printStatistics(AggregateIterable<Document> aggregates) {
    for (Document document : aggregates
    ) {
      String shopName = document.getString("_id");

      System.out.println("Магазин " + shopName);
      System.out.println("\tКоличество товаров: " + document.get("goodscount"));
      System.out.println("\tСредняя цена товара: " + document.get("avgprice"));
      System.out.println("\tСамый дорогой товар: " + document.get("maxprice"));
      System.out.println("\tСамый недорогой товар: " + document.get("minprice"));
      System.out
          .println(
              "\tКоличество товаров, дешевле 100 рублей: " + countGoodsLtHundred(shopName) + "\n");

    }
  }

  static void showStatistics(){
    printStatistics(getStatistics());
  }

  static long countGoodsLtHundred(String shopName) {
    Document shop = getShop(shopName);
    ArrayList<String> goods = (ArrayList<String>) shop.get("goods");
    return goods.stream().filter(i -> (int) getItem(i).get("price") < 100).count();
  }

  private static Document getShop(String name) {
    return shops.find(new Document("name", name)).first();
  }

  private static Document getItem(String name) {
    return goods.find(new Document("name", name)).first();
  }

  static String[] split(String command){
    return command.split(" ", 2);
  }

  static void shutdown() {
    mongoDatabase.drop();
    mongoClient.close();
    System.out.println("До свидания!");
  }

}
