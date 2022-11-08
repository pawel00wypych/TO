package exercise1;

import java.io.IOException;
import java.net.Socket;

public class ServerTaskFactory implements ITaskFactory<ServerTask, Socket>{

    @Override
    public ServerTask createTask(Socket socket) {
        try {
            return new ServerTask(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
