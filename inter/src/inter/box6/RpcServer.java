package inter.box6;

import java.io.*;
import java.lang.reflect.Method;
import java.net.*;

public class RpcServer {

	public void run() throws IOException {

		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress(6666));
		while (true) {
			try (Socket socket = server.accept();
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
					ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream())) {
				String methodName = input.readUTF();
				Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
				Object[] arguments = (Object[]) input.readObject();
				Method method = HelloImpl.class.getMethod(methodName, parameterTypes);
				Object result = method.invoke(HelloImpl.class.newInstance(), arguments);
				output.writeObject(result);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("start server");
		new RpcServer().run();
	}

}