package part2_collection;

import java.util.Arrays;

// Part 2.1 Collection class
public class BookCollection {
    // Part 2.1.1 Items count
    private int count;

    // Part 2.1.2 Items storage
    private Book[] books = new Book[4];

    public int getCount() {
        return count;
    }

    // Part 2.1.3 Add item
    public void add(Book newBook) {
        // Grow when full
        if (count == books.length) {
            books = Arrays.copyOf(books, books.length * 2);
        }
        books[count++] = newBook;
    }

    // Part 2.1.4 Print one item
    public void printOne(int i) {
        if (i < 0 || i >= count) {
            System.out.println("No book #" + (i + 1));
            return;
        }
        System.out.println("#" + (i + 1) + " " + books[i].description());
    }

    // Part 2.1.4 Print all items
    public void print() {
        if (count == 0) {
            System.out.println("Collection is empty");
            return;
        }
        System.out.printf("    %-24s | %-18s | %4s | %5s | %7s%n", "Title", "Author", "Year", "Pages", "Price");
        for (int i = 0; i < count; i++) {
            System.out.printf("%2d. %s%n", i + 1, books[i]);
        }
    }

    // Part 2.1.5 Find by title
    public void find(String s) {
        String query = s.toLowerCase();
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getTitle().toLowerCase().contains(query)) {
                printOne(i);
                found++;
            }
        }
        printFoundTotal(found);
    }

    // Part 2.1.6 Bubble sort by year
    public void sort() {
        for (int i = 0; i < count - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < count - 1 - i; j++) {
                if (books[j].getYear() > books[j + 1].getYear()) {
                    Book temp = books[j];
                    books[j] = books[j + 1];
                    books[j + 1] = temp;
                    swapped = true;
                }
            }
            // Already sorted
            if (!swapped) {
                break;
            }
        }
    }

    // Part 2.1.7 Find by year
    public void findByYear(int year) {
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getYear() == year) {
                printOne(i);
                found++;
            }
        }
        printFoundTotal(found);
    }

    // Part 2.1.7 Find by author and year
    public void findByAuthorAndYear(String author, int year) {
        String query = author.toLowerCase();
        int found = 0;
        for (int i = 0; i < count; i++) {
            if (books[i].getAuthor().toLowerCase().contains(query) && books[i].getYear() == year) {
                printOne(i);
                found++;
            }
        }
        printFoundTotal(found);
    }

    // Search summary
    private void printFoundTotal(int found) {
        System.out.println(found == 0 ? "Nothing found" : "Found: " + found);
    }
}
