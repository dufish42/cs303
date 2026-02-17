import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    private static int[] array;
    private static int size = 0;

    public static void main(String[] args) {

        // read data from file
        readFromFile("A1input.txt");

        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1) Search for an integer");
            System.out.println("2) Modify an integer by index");
            System.out.println("3) Add a new integer");
            System.out.println("4) Remove an integer by index");
            System.out.println("5) Display array");
            System.out.println("0) Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
            case 1:
                System.out.print("Enter number to search: ");
                int number = scanner.nextInt();
                int index = search(number);
                if (index != -1)
                    System.out.println("Number found at index: " + index);
                else
                    System.out.println("Number not found.");
                break;

            case 2:
                try {
                    System.out.print("Enter index to modify: ");
                    int modIndex = scanner.nextInt();
                    System.out.print("Enter new value: ");
                    int newValue = scanner.nextInt();
                    modify(modIndex, newValue);
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid index! Please try again.");
                }
                catch (Exception e) {
                    System.out.println("Invalid input! Please enter integers only.");
                    scanner.nextLine(); // clear buffer
                }
                break;

            case 3:
                try {
                    System.out.print("Enter value to add: ");
                    int valueToAdd = scanner.nextInt();
                    add(valueToAdd);
                    System.out.println("Value added successfully.");
                }
                catch (Exception e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    scanner.nextLine(); // clear buffer
                }
                break;

            case 4:
                System.out.print("Enter index to remove: ");
                int removeIndex = scanner.nextInt();
                remove(removeIndex);
                break;

            case 5:
                display();
                break;

            case 0:
                System.out.println("Exiting program.");
                break;

            default:
                System.out.println("Invalid choice.");
            }

        } while (choice != 0);

        scanner.close();
    }

    // function to read from file
    public static void readFromFile(String filename) {
        try {
            Scanner fileScanner = new Scanner(new File(filename));

            array = new int[150]; //  maximum capacity of 150

            while (fileScanner.hasNextInt()) {
                array[size] = fileScanner.nextInt();
                size++;
            }

            fileScanner.close();
            System.out.println("Data loaded successfully.");

        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    // 1) search function
    public static int search(int target) {
        for (int i = 0; i < size; i++) {
            if (array[i] == target)
                return i;
        }
        return -1;
    }

    // 2) modify function
    public static void modify(int index, int newValue) {
        int oldValue = array[index];
        array[index] = newValue;
        System.out.println("Old value: " + oldValue);
        System.out.println("New value: " + newValue);
    }

    // 3) add function
    public static void add(int value) {
        if (size < array.length) {
            array[size] = value;
            size++;
        }
        else {
            System.out.println("Array is full. Cannot add more elements.");
        }
    }

    // 4) remove function
    public static void remove(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index.");
            return;
        }

        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        size--;
        System.out.println("Element removed successfully.");
    }

    // display function
    public static void display() {
        System.out.print("Array: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}