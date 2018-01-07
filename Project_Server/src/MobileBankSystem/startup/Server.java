package MobileBankSystem.startup;



import MobileBankSystem.net.MultiThread;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
	public static void main(String[] args) throws Exception{

		@SuppressWarnings("resource")
		ServerSocket server = new ServerSocket(9999);
		System.out.println("Bank server is running");
		while(true){
			Socket socket=server.accept();

			String hostname=socket.getInetAddress().getHostAddress();
			System.out.println(hostname+" is connected to server");
			new MultiThread(socket).start();
		}
	}
}
