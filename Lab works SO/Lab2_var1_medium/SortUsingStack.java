import java.util.Stack;

public class SortUsingStack {
    public static void main(String[] args) {
        int[] inputSequence = {1, 8, 9, 6, 7, 4, 2, 3, 5, 4};

        int n = inputSequence.length;

        Stack<Integer> stack = new Stack<>();
        Stack<Integer> tempStack = new Stack<>();

        stack.push(inputSequence[0]);

        for (int i = 1; i < n; i++) {
            int currentElement = inputSequence[i];

            while (!stack.isEmpty() && currentElement > stack.peek()) {
                tempStack.push(stack.pop());
            }

            stack.push(currentElement);

            while (!tempStack.isEmpty()) {
                stack.push(tempStack.pop());
            }
        }

        System.out.println("Sorted result:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
    }
}