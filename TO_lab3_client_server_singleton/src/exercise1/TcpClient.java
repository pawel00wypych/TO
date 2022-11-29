package exercise1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpClient {
    private static final int POOL_SIZE = 10;
    private static final int RUNS = 100;
    private static volatile TcpClient instance;


    private TcpClient() {
    }

    public static TcpClient getInstance() {
        TcpClient result = instance;
        if (result != null) {
            return result;
        }

        synchronized(TcpClient.class) {
            if (instance == null) {
                instance = new TcpClient();
            }
            return instance;
        }
    }

    void fire() {
	ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
	
	for ( int i = 0; i < RUNS; i++ ) {
	    executor.execute(new ClientTask(i));
	}
	
	executor.shutdown();
	
    }
    
    public static final void main(String[] args)  {
        TcpClient client = TcpClient.getInstance();
        client.fire();
    }
    
}
