package chat.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Класс для записи сообщения
 */
public class MessageWriter implements Runnable, Disconnectable {
	private final static Logger LOGGER = LoggerFactory.getLogger(MessageWriter.class);

	private final MessageClient client;
	private final DataOutputStream stream;
	private final BlockingQueue<String> queue = new LinkedBlockingQueue<String>();
	private volatile boolean connected = true;
	
	public MessageWriter( MessageClient client, OutputStream stream ) {
		this.client = client;
		this.stream = new DataOutputStream( stream );
		LOGGER.info("Writer is ready");
	}

	@Override
	public void disconnect() {
		this.connected = false;
	}
	
	public void writeMessage( String message ) {
		this.queue.offer(message);
	}
	
	@Override
	public void run() {
		while (connected) {
			try {
				String message = this.queue.poll( 1 , TimeUnit.MILLISECONDS);
				if ( message != null ) {
					byte[] bytes = message.getBytes();
					this.stream.writeInt( bytes.length );
					this.stream.write( bytes );
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch ( IOException e ) {
				this.client.errorOnWrite(e);
			}
		}
		
	}


}
