package exercise1;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTask implements IShippableTask<Void>, Runnable {
    private final int state;
    
    public ClientTask(int state) {
	this.state = state;
    }

	@Override
	public void run() {
		try {
			ShippableTaskFactory factory = new ShippableTaskFactory();
			Socket socket = new Socket("localhost", TcpServer.PORT);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(factory.createTask(state));
			out.flush();
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			Integer result = (Integer) in.readObject();
			System.out.println(result);
			socket.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Void execute() throws InterruptedException{
		run();
		return null;
    }

}
