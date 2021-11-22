package ru.skillbox;

public class Book {

    private final String name;
    private final String Author;
    private final int pageCount;
    private final long ISBNNumber;

    public Book(String name, String author, int pageCount, long ISBNNumber) {
        this.name = name;
        Author = author;
        this.pageCount = pageCount;
        this.ISBNNumber = ISBNNumber;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return Author;
    }

    public int getPageCount() {
        return pageCount;
    }

    public long getISBNNumber() {
        return ISBNNumber;
    }
}
