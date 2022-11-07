package exercise1;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpServer {
    public static final int PORT = 8205;
    private static final int POOL_SIZE = 10;
    private ServerSocket ss;
    private ExecutorService executor;
    private static volatile TcpServer instance;
    
    private TcpServer() throws IOException {
	    ss = new ServerSocket(PORT);
	    executor = Executors.newFixedThreadPool(POOL_SIZE);
    }

    public static TcpServer getInstance() {
        TcpServer result = instance;
        if (result != null) {
            return result;
        }
        synchronized(TcpServer.class) {
            if (instance == null) {
                try {
                    instance = new TcpServer();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return instance;
        }
    }

    public void listen() throws IOException {
	System.out.println(this + " up and running");
	while ( true ) {
	    executor.execute(new ServerTask(ss.accept()));
	}
    }  

    public static final void main(String[] args) throws IOException {
        TcpServer server = TcpServer.getInstance();
        server.listen();
    }

    @Override
    public String toString() {
	return "TcpServer [port=" + ss.getLocalPort() + "]";
    }
    
}
