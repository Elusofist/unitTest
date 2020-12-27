import java.util.ArrayList;

public class StackAssertions<T, K> {
    public boolean assertArrayEquals(ArrayList<T> arr, ArrayList<K> expected) {
        if (assertNotNull(arr)) {
            if (assertSameTypes(arr.get(0), expected.get(0))) {
                if (assertSameLength(arr.size(), expected.size())) {
                    for (int i = 0; i < arr.size(); i++) {
                        if (!assertEquals(arr.get(i), expected.get(i))) {
                            System.out.println("arr and expected are different.");
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean assertEquals(T a, K expected) {
        boolean res = false;
        if (assertSameTypes(a, expected)) {
            res = a.equals(expected);
            if (res) System.out.println("Entered and expected are the same.");
            else System.out.println("Entered and expected aren't the same.");
        }
        return res;
    }

    public boolean assertTrue(boolean expression) {
        if (!expression) System.out.println("It's not true.");
        else System.out.println("The expression is true.");
        return expression;
    }

    public void fail() {
        System.out.println("Exception wasn't caught.");
    }

    public boolean assertNull(ArrayList<T> arr) {
        boolean res = arr == null;
        if (!res) System.out.println("arr is not null.");
        return res;
    }

    public boolean assertNotNull(ArrayList<T> arr) {
        return !assertNull(arr);
    }

    public boolean assertSameTypes(T a, K b) {
        if (!a.getClass().equals(b.getClass())) {
            System.out.println("Inconsistent types.");
            return false;
        }
        return true;
    }

    public boolean assertSameLength(int i, int j) {
        boolean res = i == j;
        if (!res) System.out.println("The numbers aren't equal.");
        return res;
    }
}