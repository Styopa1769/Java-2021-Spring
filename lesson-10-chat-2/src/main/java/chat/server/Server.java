package chat.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ServerSocketFactory;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Сервер
 */
public class Server {
	private final static Logger LOGGER = LoggerFactory.getLogger(Server.class);

	private volatile boolean connected = true;
	private final List<ServerClient> clients = Collections.synchronizedList(new ArrayList<>());
	private final int port;

	public Server(int port) {
		this.port = port;
	}

	public boolean isConnected() {
		return connected;
	}

	public void setConnected(boolean connected) {
		this.connected = connected;
		if (!this.connected) {
			for (ServerClient client : this.clients) {
				client.disconnect();
			}
		}
	}

	public void onMessage(String message, ServerClient source) {
		for (ServerClient client : this.clients) {
			if (!client.equals(source)) {
				client.writeMessage(message);
			}
		}
	}

	public static void main(String[] args) {
		new Thread(() -> {
			try {
				new Server(8080).listen();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}).start();

		LOGGER.info("Server running...");
	}

	private void listen() throws IOException {
		ServerSocket serverSocket = ServerSocketFactory.getDefault()
				.createServerSocket(port);
		while (serverSocket.isBound() && isConnected()) {
			Socket client = serverSocket.accept();
			LOGGER.info("Client connected: {}:{}", client.getInetAddress(), client.getPort());
			ServerClient serverClient = new ServerClient(client, this);
			clients.add(serverClient);
		}
	}

}
