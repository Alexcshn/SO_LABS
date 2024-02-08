import java.util.Scanner;

public class TextManipulationApp {
    public static void main(String[] args) {
        TextManipulator manipulator = new TextManipulator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Text Manipulation App!");
        System.out.println("Choose an operation:");
        System.out.println("1: Remove Spaces");
        System.out.println("2: Remove New Lines");
        System.out.println("3: Mirror Text");
        System.out.println("4: Change Word");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        switch (choice) {
            case 1:
                System.out.println("Enter text to remove spaces:");
                System.out.println(manipulator.removeSpaces(scanner.nextLine()));
                break;
            case 2:
                System.out.println("Enter text to remove new lines:");
                System.out.println(manipulator.removeNewLines(scanner.nextLine()));
                break;
            case 3:
                System.out.println("Enter text to mirror:");
                System.out.println(manipulator.mirrorText(scanner.nextLine()));
                break;
            case 4:
                System.out.println("Enter text:");
                String text = scanner.nextLine();
                System.out.println("Enter word to replace:");
                String oldWord = scanner.nextLine();
                System.out.println("Enter new word:");
                String newWord = scanner.nextLine();
                System.out.println(manipulator.changeWord(text, oldWord, newWord));
                break;
            default:
                System.out.println("Invalid choice. Please restart the program.");
                break;
        }

        scanner.close();
    }
}