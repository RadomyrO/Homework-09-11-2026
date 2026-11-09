package part1_references;

// Part 1.1 Class for assignments
class Player {
    String name;
    int score;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', score=" + score + "}";
    }
}

// Part 1.2 Class for arguments
class Laptop {
    String brand;
    int ramGb;

    Laptop(String brand, int ramGb) {
        this.brand = brand;
        this.ramGb = ramGb;
    }

    @Override
    public String toString() {
        return "Laptop{brand='" + brand + "', ramGb=" + ramGb + "}";
    }
}

// Part 1. References: assignments and arguments
public class Part1References {

    public static void main(String[] args) {
        demoAssignments();
        System.out.println();
        demoArguments();
    }

    // Part 1.1 Assignments
    private static void demoAssignments() {
        System.out.println("=== Part 1.1 Assignments ===");

        // One object in heap
        Player first = new Player("Alex", 10);

        // Copies ref, not object
        Player second = first;

        // Same values
        System.out.println("Start:");
        printPlayers(first, second);

        // Change via first
        first.score = 50;
        System.out.println("After first.score = 50:");
        printPlayers(first, second);

        // Change via second
        second.name = "Maria";
        System.out.println("After second.name = \"Maria\":");
        printPlayers(first, second);

        // Same address
        System.out.println("first == second: " + (first == second));
    }

    // Prints both refs
    private static void printPlayers(Player first, Player second) {
        System.out.println("  first  -> " + first);
        System.out.println("  second -> " + second);
    }

    // Part 1.2 Arguments
    private static void demoArguments() {
        System.out.println("=== Part 1.2 Arguments ===");

        // Original object
        Laptop laptop = new Laptop("Lenovo", 8);
        System.out.println("Before method: " + laptop);

        // Passes copy of ref
        upgradeRam(laptop);

        // Change is visible outside
        System.out.println("After method:  " + laptop);
    }

    // Param points to same object
    private static void upgradeRam(Laptop target) {
        // Changes original object
        target.ramGb = 32;
        System.out.println("Inside method: " + target);
    }
}
