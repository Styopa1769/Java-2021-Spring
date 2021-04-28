package chat.server;

import chat.support.BaseMessageClient;

import java.net.Socket;

/**
 * Сервер выполняет некоторые клиентские функции
 */
public class ServerClient extends BaseMessageClient {

	private final Server server;
	public ServerClient(Socket client, Server server) {
		super(client);
		this.server = server;
	}

	@Override
	public void onMessage(String message) {
		System.out.println(message);
		server.onMessage(message, this);
	}

}
