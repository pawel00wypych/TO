package exercise1;

public class ClientTaskFactory implements ITaskFactory<ClientTask, Integer>{


    @Override
    public ClientTask createTask(Integer state) {
        return new ClientTask(state);
    }
}
