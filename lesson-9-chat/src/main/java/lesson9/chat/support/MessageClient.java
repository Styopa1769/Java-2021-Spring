package lesson9.chat.support;

/**
 * Интерфейс для взаимодействия с клиентом
 */
public interface MessageClient {

	/**
	 * Вызывается при возниконвении ошибки записи
	 * @param e ошибка
	 */
	void errorOnWrite(Exception e);

	/**
	 * Вызывается при возникновении ошибки чтения
	 * @param e ошибка
	 */
	void errorOnRead(Exception e);

	/**
	 * Вызывается при получении сообщения
	 * @param message сообщение
	 */
	void onMessage(String message);
	
}
