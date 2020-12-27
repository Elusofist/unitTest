import org.junit.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestNewStack {
    static NewStack newStack = new NewStack();
    static StackAssertions stackAssertions = new StackAssertions();
    final static String SUCCESSFULLY = "passed successfully.";
    final static String HOORAY = "Hooooray! ";

    @BeforeClass
    public void test1() {
        boolean res1 = stackAssertions.assertEquals(newStack.size(), 0);
        boolean res2 = stackAssertions.assertTrue(newStack.isEmpty());
        try {
            newStack.peek();
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }
        try {
            newStack.pop();
            stackAssertions.fail();
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }
        if (res1 && res2) {
            System.out.println(HOORAY + "Test 1 " + SUCCESSFULLY);
        }
    }

    @Before
    public void test2() {
        newStack.push(0);
        boolean res1 = stackAssertions.assertTrue(!newStack.isEmpty());
        boolean res2 = stackAssertions.assertNotNull(newStack.getStack());
        boolean res3 = stackAssertions.assertEquals(newStack.size(), 1);
        boolean res4 = stackAssertions.assertEquals(newStack.size(), 0);
        boolean res5 = false;
        try {
            res5 = stackAssertions.assertEquals(newStack.peek(), 0);
        } catch (EmptyStackException e) {
            System.out.println(e.getMessage());
        }

        if (res1 && res2 && res3 && !res4 && res5) {
            System.out.println(HOORAY + "Test 2 " + SUCCESSFULLY);
        }
    }

    @Test
    public void test3() {
        int i = 1;
        boolean res1 = true;
        while (i <= 5) {
            newStack.push(i);
            try {
                res1 &= stackAssertions.assertEquals(newStack.peek(), i);
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
            i++;
        }

        if (res1) {
            System.out.println(HOORAY + "Test 3 " + SUCCESSFULLY);
        }
    }

    @Test
    public void test4() {
        int i = 1;
        boolean res1 = true;
        while (i <= 5) {
            newStack.push(i);
            try {
                res1 &= stackAssertions.assertEquals(newStack.pop(), i);
            } catch (EmptyStackException e) {
                e.printStackTrace();
            }
            i++;
        }
        boolean res2 = stackAssertions.assertTrue(newStack.isEmpty());

        if (res1 && res2) {
            System.out.println(HOORAY + "Test 4 " + SUCCESSFULLY);
        }
    }


    @After
    public void test5() {
        Integer i = 0;
        String s = "salam";
        boolean res = stackAssertions.assertSameTypes(i, s);
        if (!res) {
            System.out.println(HOORAY + "Test 5 " + SUCCESSFULLY);
        }
    }

    @AfterClass
    public void test6() {
        boolean res1 = stackAssertions.assertNotNull(newStack.getStack());
        boolean res2 = stackAssertions.assertEquals(newStack.size(), 0);
        if (res1 && res2) {
            System.out.println(HOORAY + "Test 6 " + SUCCESSFULLY);
        }
    }

    @Ignore
    public void test7() {
        boolean res = stackAssertions.assertNull(newStack.getStack());
        if (!res) {
            System.out.println(HOORAY + "Test 7 " + SUCCESSFULLY);
        }
    }
}
