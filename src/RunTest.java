import org.junit.*;
import org.junit.jupiter.api.Test;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.Annotation;
import java.util.HashSet;
import java.util.Set;

public class RunTest {
    static TestNewStack testNewStack;
    static Class stackTest;
    static Set<Method> testMethods;
    static Set<Method> afterMethods;
    static Set<Method> beforeMethods;
    static Set<Method> ignoreMethods;
    static Set<Method> beforeClassMethods;
    static Set<Method> afterClassMethods;

    static {
        testNewStack = new TestNewStack();
        stackTest = testNewStack.getClass();
        testMethods = new HashSet<>();
        afterClassMethods = new HashSet<>();
        afterMethods = new HashSet<>();
        beforeClassMethods = new HashSet<>();
        beforeMethods = new HashSet<>();
        ignoreMethods = new HashSet<>();

        setBeforeClasses();
        setBefore();
        setTests();
        setIgnores();
        setAfters();
        setAfterClasses();
    }

    public static void setBefore() {
        for (Method method : stackTest.getDeclaredMethods()) {
            if (method.getAnnotation(Before.class) != null) {
                beforeMethods.add(method);
            }
        }
    }

    public static void setIgnores() {
        for (Method method : stackTest.getDeclaredMethods()) {
            if (method.getAnnotation(Ignore.class) != null) {
                ignoreMethods.add(method);
            }
        }
    }

    public static void setBeforeClasses() {
        for (Method method : stackTest.getDeclaredMethods()) {
            if (method.getAnnotation(BeforeClass.class) != null) {
                beforeClassMethods.add(method);
            }
        }
    }

    public static void setAfterClasses() {
        for (Method method : stackTest.getDeclaredMethods()) {
            if (method.getAnnotation(AfterClass.class) != null) {
                afterClassMethods.add(method);
            }
        }
    }

    public static void setTests(){
        for (Method method : stackTest.getDeclaredMethods()) {
            if (method.getAnnotation(Test.class) != null) {
                testMethods.add(method);
            }
        }
    }

    public static void setAfters() {
        for (Method method : stackTest.getDeclaredMethods()) {
            if (method.getAnnotation(After.class) != null) {
                afterMethods.add(method);
            }
        }
    }

    public static void print() {
        System.out.println("@TEST:");
        for (Method method : testMethods) {
            System.out.println(method.getName());
        }
        System.out.println("@AFTER");
        for (Method method : afterMethods) {
            System.out.println(method.getName());
        }
        System.out.println("@AFTER_CLASS");
        for (Method method : afterClassMethods) {
            System.out.println(method.getName());
        }
        System.out.println("@BEFORE");
        for (Method method : beforeMethods) {
            System.out.println(method.getName());
        }
        System.out.println("@BEFORE_CLASS");
        for (Method method : beforeClassMethods) {
            System.out.println(method.getName());
        }
        System.out.println("@IGNORE");
        for (Method method : ignoreMethods) {
            System.out.println(method.getName());
        }
    }

    private static void testAntRun() throws InvocationTargetException, IllegalAccessException {
        if (testMethods != null && testMethods.size() != 0) {
            int i = 0;
            for (Method method : testMethods) {
                if (i != 0) {
                    beforeAntRun();
                }
                method.invoke(testNewStack);
                if (i != testMethods.size() - 1) {
                    afterAntRun();
                }
                i++;
            }
        }
    }

    private static void beforeClassAntRun() throws InvocationTargetException, IllegalAccessException {
        if (beforeClassMethods != null && beforeClassMethods.size() != 0) {
            for (Method method : beforeClassMethods) {
                method.invoke(testNewStack);
            }
        }
    }

    private static void beforeAntRun() throws InvocationTargetException, IllegalAccessException {
        if (beforeMethods != null && beforeMethods.size() != 0) {
            for (Method method1 : beforeMethods) {
                method1.invoke(testNewStack);
            }
        }
    }

    private static void afterAntRun() throws InvocationTargetException, IllegalAccessException {
        if (afterMethods != null && afterMethods.size() != 0) {
            for (Method method1 : afterMethods) {
                method1.invoke(testNewStack);
            }
        }
    }

    private static void afterClassAntRun() throws InvocationTargetException, IllegalAccessException {
        if (afterClassMethods != null && afterClassMethods.size() != 0) {
            for (Method method : afterClassMethods) {
                method.invoke(testNewStack);
            }
        }
    }

    private static void ignoreAntRun() throws InvocationTargetException, IllegalAccessException {
        if (ignoreMethods != null && ignoreMethods.size() != 0) {
            for (Method method : ignoreMethods) {
                method.invoke(testNewStack);
            }
        }
    }

    public static void runTest() throws InvocationTargetException, IllegalAccessException {
        beforeClassAntRun();
        beforeAntRun();
        testAntRun();
        afterAntRun();
        afterClassAntRun();
        ignoreAntRun();
    }

    public static void main(String[] args) {
//        print();
        try {
            runTest();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}