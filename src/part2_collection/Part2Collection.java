package part2_collection;

import java.util.Scanner;

// Part 2.2 Main. Option 1: user dialogue
public class Part2Collection {
    // Form fields
    private static final String[] FIELDS = {"Title", "Author", "Year", "Pages", "Price"};
    private static final int TITLE = 0;
    private static final int AUTHOR = 1;
    private static final int YEAR = 2;
    private static final int PAGES = 3;
    private static final int PRICE = 4;

    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        BookCollection books = new BookCollection();

        // Starter data
        books.add(new Book("Dune", "Frank Herbert", 1965, 688, 15.50));
        books.add(new Book("1984", "George Orwell", 1949, 328, 9.99));
        books.add(new Book("The Hobbit", "J.R.R. Tolkien", 1937, 310, 12.00));

        System.out.println("Welcome to the book database!");

        // Main menu
        while (true) {
            System.out.println();
            System.out.println("Choose an option to proceed:");
            System.out.println("1 - add books to collection");
            System.out.println("2 - print all books");
            System.out.println("3 - print one book");
            System.out.println("4 - sort books by year");
            System.out.println("5 - search books");
            System.out.println("0 - exit the program");

            switch (readInt("Your choice: ")) {
                case 1 -> inputItems(books);
                case 2 -> books.print();
                case 3 -> books.printOne(readInt("Book number: ") - 1);
                case 4 -> sort(books);
                case 5 -> search(books);
                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Wrong option");
            }
        }
    }

    // Part 2.2 Input dialogue
    public static void inputItems(BookCollection books) {
        System.out.println("Empty title = stop, \"<\" = previous field");
        while (true) {
            Book book = inputBook();
            // Stop input
            if (book == null) {
                return;
            }
            books.add(book);
            System.out.println(progressBar(FIELDS.length) + " Added! Total books: " + books.getCount());
        }
    }

    // Part 2.2 Search dialogue
    public static void search(BookCollection books) {
        System.out.println("1 - by title");
        System.out.println("2 - by year");
        System.out.println("3 - by author and year");

        switch (readInt("Your choice: ")) {
            case 1 -> books.find(readLine("Title: "));
            case 2 -> books.findByYear(readInt("Year: "));
            case 3 -> {
                String author = readLine("Author: ");
                books.findByAuthorAndYear(author, readInt("Year: "));
            }
            default -> System.out.println("Wrong option");
        }
    }

    // Part 2.2 Sort dialogue
    public static void sort(BookCollection books) {
        books.sort();
        System.out.println("Sorted by year:");
        books.print();
    }

    // Field-by-field form, null = stop
    private static Book inputBook() {
        String[] values = new String[FIELDS.length];
        int step = 0;

        while (step < FIELDS.length) {
            String input = readLine(progressBar(step) + " " + FIELDS[step] + ": ");

            // Step back
            if (input.equals("<")) {
                step = Math.max(0, step - 1);
            // Stop on empty title
            } else if (step == TITLE && input.isEmpty()) {
                return null;
            // Step forward
            } else if (isValid(step, input)) {
                values[step++] = input;
            } else {
                System.out.println("Wrong value, try again");
            }
        }

        return new Book(values[TITLE], values[AUTHOR], Integer.parseInt(values[YEAR]),
                Integer.parseInt(values[PAGES]), parsePrice(values[PRICE]));
    }

    // Progress scale: [##---] 2/5
    private static String progressBar(int done) {
        return "[" + "#".repeat(done) + "-".repeat(FIELDS.length - done) + "] " + done + "/" + FIELDS.length;
    }

    // Field check
    private static boolean isValid(int step, String input) {
        try {
            return switch (step) {
                case YEAR, PAGES -> Integer.parseInt(input) > 0;
                case PRICE -> parsePrice(input) >= 0;
                default -> !input.isEmpty();
            };
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Accepts 9,99 and 9.99
    private static double parsePrice(String input) {
        return Double.parseDouble(input.replace(',', '.'));
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return SCANNER.nextLine().trim();
    }

    // Repeats until valid
    private static int readInt(String prompt) {
        while (true) {
            try {
                return Integer.parseInt(readLine(prompt));
            } catch (NumberFormatException e) {
                System.out.println("Enter a whole number");
            }
        }
    }
}
