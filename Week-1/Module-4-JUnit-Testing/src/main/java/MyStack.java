// MyStack.java
// A simple Stack implementation to test

import java.util.ArrayList;
import java.util.List;

public class MyStack {

    private List<Integer> stack = new ArrayList<>();

    // Push item onto stack
    public void push(int item) {
        stack.add(item);
    }

    // Pop item from stack
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return stack.remove(stack.size() - 1);
    }

    // Peek top item without removing
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is empty!");
        }
        return stack.get(stack.size() - 1);
    }

    // Check if empty
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    // Get size
    public int size() {
        return stack.size();
    }
}