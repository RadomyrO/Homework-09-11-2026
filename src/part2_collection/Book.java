package part2_collection;

import java.util.Locale;

// Part 2.1 Custom class (improved: added year)
public class Book {
    private String title;
    private String author;
    private int year;
    private int pages;
    private int pagesRead;
    private double price;

    public Book(String title, String author, int year, int pages, double price) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.pages = pages;
        this.pagesRead = 0;
        this.price = price;
    }

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    public int getPagesRead() { return pagesRead; }
    public void setPagesRead(int pagesRead) {
        // Max is book size
        this.pagesRead = Math.min(pagesRead, pages);
    }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // Read percent
    public double progress() {
        return pages == 0 ? 0 : pagesRead * 100.0 / pages;
    }

    // Pages to read
    public int pagesLeft() {
        return pages - pagesRead;
    }

    // Cost of one page
    public double pricePerPage() {
        return pages == 0 ? 0 : price / pages;
    }

    // Full info
    public String description() {
        return String.format(Locale.US,
                "\"%s\" by %s, %d | %d pages, read %d (%.1f%%), left %d | price %.2f (%.3f per page)",
                title, author, year, pages, pagesRead, progress(), pagesLeft(), price, pricePerPage());
    }

    // Table row
    @Override
    public String toString() {
        return String.format(Locale.US, "%-24s | %-18s | %4d | %5d | %7.2f",
                title, author, year, pages, price);
    }
}
