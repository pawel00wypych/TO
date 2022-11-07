package exercise1;

public interface IShippableTask<E> {
    E execute() throws Exception;
}