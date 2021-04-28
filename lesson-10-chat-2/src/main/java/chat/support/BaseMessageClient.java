package chat.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Socket;

/**
 * Базовый клиент
 */
public abstract class BaseMessageClient implements MessageClient, Disconnectable {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final Socket socket;
	private final MessageReader reader;
	private final MessageWriter writer;

	public BaseMessageClient(String host, int port) throws Exception {
		this(new Socket(host, port));
	}

	public BaseMessageClient(Socket socket) {
		try {
			this.socket = socket;

			logger.info("Creating reader");
			logger.info("Socket - {}", socket);
			logger.info("Input - {}", this.socket.getInputStream());
			logger.info("Output - {}", this.socket.getOutputStream());

			reader = new MessageReader(this, socket.getInputStream());

			logger.info("Creating writer");

			writer = new MessageWriter(this, socket.getOutputStream());

			new Thread(reader).start();
			new Thread(writer).start();

			logger.info("Started threads");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	
	@Override
	public void errorOnWrite( Exception e ) {
		logger.warn("An error happened while writing");
	}

	@Override
	public void errorOnRead(Exception e) {
		logger.warn("An error happened while reading");
	}
	
	public void writeMessage(String message) {
		writer.writeMessage(message);
	}

	@Override
	public void disconnect() {
		reader.disconnect();
		writer.disconnect();
		try {
			Thread.sleep(1000);
			socket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
