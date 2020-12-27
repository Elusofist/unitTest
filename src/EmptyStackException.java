public class EmptyStackException extends Exception{
    EmptyStackException() {
        super("The stack hasn't been initialized yet.");
    }
}
