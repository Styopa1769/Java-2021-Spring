package chat.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.InputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Класс для чтения сообщения
 */
public class MessageReader implements Runnable, Disconnectable {
	private final static Logger LOGGER = LoggerFactory.getLogger(MessageReader.class);

	private final Executor pool = Executors.newSingleThreadExecutor();

	private final MessageClient client;
	private final DataInputStream stream;
	private volatile boolean connected = true;

	public MessageReader(MessageClient client, InputStream stream) {
		this.client = client;
		this.stream = new DataInputStream(stream);
		LOGGER.info("Reader is ready");
	}

	@Override
	public void disconnect() {
		this.connected = false;
	}

	@Override
	public void run() {

		try {
			while (connected) {
				int size = this.stream.readInt();
				byte[] message = new byte[size];
				for (int x = 0; x < size; x++) {
					message[x] = (byte) this.stream.read();
				}
				final String realMessage = new String(message);

				Runnable runnable = () -> client.onMessage(realMessage);
				pool.execute(runnable);
			}
		} catch (Exception e) {
			this.client.errorOnRead(e);
			throw new RuntimeException(e);
		}

	}

}
