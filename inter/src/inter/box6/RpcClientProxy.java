package inter.box6;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;

public class RpcClientProxy<T> implements InvocationHandler {

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		Socket socket = null;
		ObjectOutputStream output = null;
		ObjectInputStream input = null;
		try {
			socket = new Socket();
			socket.connect(new InetSocketAddress("localhost", 6666));

			// 将远程服务调用所需的接口类、方法名、参数列表等编码后发送给服务提供者
			output = new ObjectOutputStream(socket.getOutputStream());
			output.writeUTF(method.getName());
			output.writeObject(method.getParameterTypes());
			output.writeObject(args);
			// 同步阻塞等待服务器返回应答，获取应答后返回
			input = new ObjectInputStream(socket.getInputStream());
			return input.readObject();
		} finally {
			if (socket != null)
				socket.close();
			if (output != null)
				output.close();
			if (input != null)
				input.close();
		}
	}

	public static void main(String[] args) {
		RpcClientProxy<IHello> handler = new RpcClientProxy<>();
		IHello hello = (IHello) Proxy.newProxyInstance(IHello.class.getClassLoader(), new Class<?>[] { IHello.class },
				handler);

		System.out.println(hello.sayHello("socket rpc"));
	}
}