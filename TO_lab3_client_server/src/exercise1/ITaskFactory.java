package exercise1;

public interface ITaskFactory<E, V> {
    E createTask(V o);
}
