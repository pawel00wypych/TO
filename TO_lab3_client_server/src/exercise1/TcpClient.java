package exercise1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpClient implements IShippableTask<Void> {
    private static final int POOL_SIZE = 10;
    private static final int RUNS = 100;
   
    void fire() {
	ExecutorService executor = Executors.newFixedThreadPool(POOL_SIZE);
	
	for ( int i = 0; i < RUNS; i++ ) {
	    executor.execute(new ClientTask(i));
	}
	
	executor.shutdown();
	
    }

    @Override
    public Void execute() throws InterruptedException {
        fire();
        return null;
    }
    
    public static final void main(String[] args) throws InterruptedException {
	    new TcpClient().execute();
    }


}
