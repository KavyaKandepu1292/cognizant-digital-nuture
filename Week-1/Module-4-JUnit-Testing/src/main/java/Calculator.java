// Calculator.java
// This is the class we want to TEST using JUnit

public class Calculator {

    // Add two numbers
    public int add(int a, int b) {
        return a + b;
    }

    // Subtract
    public int subtract(int a, int b) {
        return a - b;
    }

    // Multiply
    public int multiply(int a, int b) {
        return a * b;
    }

    // Divide
    public double divide(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return a / b;
    }

    // Check if number is even
    public boolean isEven(int n) {
        return n % 2 == 0;
    }

    // Find max of two numbers
    public int max(int a, int b) {
        return a > b ? a : b;
    }
}