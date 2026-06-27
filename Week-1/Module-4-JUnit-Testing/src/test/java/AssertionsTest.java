// AssertionsTest.java
// Exercise 3: All JUnit Assertions

import org.junit.Test;
import static org.junit.Assert.*;

public class AssertionsTest {

    @Test
    public void testAssertions() {

        // Assert equals — checks if two values are equal
        assertEquals(5, 2 + 3);
        System.out.println("assertEquals passed!");

        // Assert true — checks if condition is true
        assertTrue(5 > 3);
        System.out.println("assertTrue passed!");

        // Assert false — checks if condition is false
        assertFalse(5 < 3);
        System.out.println("assertFalse passed!");

        // Assert null — checks if value is null
        assertNull(null);
        System.out.println("assertNull passed!");

        // Assert not null — checks if value is NOT null
        assertNotNull(new Object());
        System.out.println("assertNotNull passed!");

        // Assert array equals
        int[] expected = {1, 2, 3};
        int[] actual   = {1, 2, 3};
        assertArrayEquals(expected, actual);
        System.out.println("assertArrayEquals passed!");

        // Assert same — checks if same object reference
        String s = "hello";
        assertSame(s, s);
        System.out.println("assertSame passed!");

        System.out.println(" All assertions passed!");
    }

    @Test
    public void testStringAssertions() {
        String name = "Kavya";

        assertNotNull(name);
        assertEquals("Kavya", name);
        assertTrue(name.startsWith("K"));
        assertFalse(name.isEmpty());

        System.out.println(" All String assertions passed!");
    }

    @Test
    public void testNumberAssertions() {
        double pi = 3.14159;

        // assertEquals with delta for floating point
        assertEquals(3.14, pi, 0.01);  // within 0.01 tolerance
        assertTrue(pi > 3.0);
        assertFalse(pi > 4.0);

        System.out.println(" All Number assertions passed!");
    }
}