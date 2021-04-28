
package chat.client;

import chat.support.BaseMessageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * Клиент
 */
public class Client extends BaseMessageClient {

	private final static Logger LOGGER = LoggerFactory.getLogger(Client.class);

	public Client(String host, int port) throws Exception {
		super(host, port);
	}

	@Override
	public void onMessage(String message) {
		System.out.printf( "Message read is -> %s%n", message );
	}

	public static void main(String[] args) throws Exception {

		LOGGER.info("Prepare client");

		Client client = new Client("localhost", 8080);

		LOGGER.info("Client is ready");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Name: ");
		String name = scanner.nextLine();
		LOGGER.info("You are : " + name);
		System.out.println("Write messages: ");

		while (true) {
			String message = scanner.nextLine();
			if (message.equals("exit!"))
				break;
			client.writeMessage(String.format("%s: %s", name, message));
		}
	}
	
}
