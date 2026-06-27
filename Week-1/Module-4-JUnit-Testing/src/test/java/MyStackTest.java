// MyStackTest.java
// Exercise 4: AAA Pattern + @Before + @After

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;

public class MyStackTest {

    private MyStack stack;

    // ARRANGE — runs before EVERY test
    @Before
    public void setUp() {
        stack = new MyStack();  // fresh stack for each test
        System.out.println("setUp: New stack created");
    }

    // TEARDOWN — runs after EVERY test
    @After
    public void tearDown() {
        stack = null;
        System.out.println("tearDown: Stack cleared\n");
    }

    // AAA Pattern Test 1
    @Test
    public void testPush() {
        // ARRANGE (already done in setUp)

        // ACT
        stack.push(10);
        stack.push(20);

        // ASSERT
        assertEquals(2, stack.size());
        System.out.println("testPush passed! Size=" + stack.size());
    }

    // AAA Pattern Test 2
    @Test
    public void testPop() {
        // ARRANGE
        stack.push(10);
        stack.push(20);
        stack.push(30);

        // ACT
        int popped = stack.pop();

        // ASSERT
        assertEquals(30, popped);
        assertEquals(2, stack.size());
        System.out.println("testPop passed! Popped=" + popped);
    }

    // AAA Pattern Test 3
    @Test
    public void testPeek() {
        // ARRANGE
        stack.push(5);
        stack.push(15);

        // ACT
        int top = stack.peek();

        // ASSERT
        assertEquals(15, top);
        assertEquals(2, stack.size()); // size unchanged after peek
        System.out.println("testPeek passed! Top=" + top);
    }

    // AAA Pattern Test 4
    @Test
    public void testIsEmpty() {
        // ARRANGE — stack is empty from setUp

        // ACT & ASSERT
        assertTrue(stack.isEmpty());
        stack.push(1);
        assertFalse(stack.isEmpty());
        System.out.println("testIsEmpty passed!");
    }

    // AAA Pattern Test 5 — Exception test
    @Test(expected = RuntimeException.class)
    public void testPopEmptyStack() {
        // ARRANGE — empty stack

        // ACT — should throw exception
        stack.pop();
    }
}