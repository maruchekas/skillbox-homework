package org.example.mongo;

public class Book {

  private String title;
  private String author;
  private int release;

  public Book(String title, String author, int release) {
    this.title = title;
    this.author = author;
    this.release = release;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getRelease() {
    return release;
  }

  public void setRelease(int release) {
    this.release = release;
  }

  @Override
  public String toString() {
    return "{title: \"" + title + '\"' + ", author: \"" + author + '\"' + ", release: " + release + '}';
  }
}
