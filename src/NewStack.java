import java.util.ArrayList;

public class NewStack<T> {
    private ArrayList<T> stack;

    NewStack() {
        this.setStack(new ArrayList<>());
    }

    public ArrayList<T> getStack() {
        return stack;
    }

    public void setStack(ArrayList<T> stack) {
        this.stack = stack;
    }

    public int size() {
        return getStack().size();
    }

    public boolean isEmpty() {
        return getStack().size() == 0;
    }

    public void push(T element) {
        if (element == null) throw new NullPointerException();
        getStack().add(element);
    }

    public T pop() throws EmptyStackException {
        T popped = peek();
        getStack().remove(popped);
        return popped;

    }

    public T peek() throws EmptyStackException {
        if (!isEmpty()) {
            T peeked = getStack().remove(size() - 1);
            return peeked;
        } else throw new EmptyStackException();
    }
}
