// CalculatorTest.java
// Exercise 1: Setting Up JUnit
// Exercise 2: Writing Basic JUnit Tests

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class CalculatorTest {

    private Calculator calculator;

    // Runs BEFORE every test
    @Before
    public void setUp() {
        calculator = new Calculator();
        System.out.println("--- Test Starting ---");
    }

    // Runs AFTER every test
    @After
    public void tearDown() {
        System.out.println("--- Test Finished ---\n");
    }

    // Test add()
    @Test
    public void testAdd() {
        int result = calculator.add(2, 3);
        assertEquals(5, result);
        System.out.println("testAdd passed! 2+3=" + result);
    }

    // Test subtract()
    @Test
    public void testSubtract() {
        int result = calculator.subtract(10, 4);
        assertEquals(6, result);
        System.out.println("testSubtract passed! 10-4=" + result);
    }

    // Test multiply()
    @Test
    public void testMultiply() {
        int result = calculator.multiply(3, 4);
        assertEquals(12, result);
        System.out.println("testMultiply passed! 3*4=" + result);
    }

    // Test divide()
    @Test
    public void testDivide() {
        double result = calculator.divide(10, 2);
        assertEquals(5.0, result, 0.001);
        System.out.println("testDivide passed! 10/2=" + result);
    }

    // Test divide by zero - expects exception
    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        calculator.divide(10, 0);
        System.out.println("testDivideByZero passed!");
    }

    // Test isEven()
    @Test
    public void testIsEven() {
        assertTrue(calculator.isEven(4));
        assertFalse(calculator.isEven(5));
        System.out.println("testIsEven passed!");
    }

    // Test max()
    @Test
    public void testMax() {
        assertEquals(10, calculator.max(5, 10));
        assertEquals(7,  calculator.max(7, 3));
        System.out.println("testMax passed!");
    }
}