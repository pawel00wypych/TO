package exercise1;

public class ShippableTaskFactory implements ITaskFactory<ShippableTask, Integer>{

    @Override
    public ShippableTask createTask(Integer state) {
        return new ShippableTask(state);
    }
}
