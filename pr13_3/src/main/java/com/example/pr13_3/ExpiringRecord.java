package com.example.pr13_3;
import java.util.Date;
import java.text.*;

/**
 * Запись о хранении с крайним сроком хранения
 */
public class ExpiringRecord extends Record implements Expiring{

  /**
   * Дата крайнего срока хранения
   */
  private final Date expiryDate;

  /**
   * Конструктор записи о хранении с крайним сроком хранения
   * @param item Название хранимого предмета
   * @param quantity Количество хранимого предмета
   * @param owner Владелец хранимого предмета
   * @param ownerType Тип владельца хранимого предмета
   * @param contact Контакты владельца хранимого предмета
   * @param date Дата крайнего срока хранения
   */
  public ExpiringRecord(String item, int quantity, String owner, String ownerType, String contact, Date date) {
    super(item, quantity, owner, ownerType, contact);
    expiryDate = date;
  }

  /**
   * Конструктор записи о хранении с крайним сроком хранения из базы данных
   * @param id Идентификатор записи
   * @param item Название хранимого предмета
   * @param quantity Количество хранимого предмета
   * @param owner Владелец хранимого предмета
   * @param ownerType Тип владельца хранимого предмета
   * @param contact Контакты владельца хранимого предмета
   * @param dateString Строка даты крайнего срока хранения
   */
  public ExpiringRecord(int id, String item, int quantity, String owner, String ownerType, String contact, String dateString) {
    super(id, item, quantity, owner, ownerType, contact);
    Date date;
    try {
      DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      date = df.parse(dateString);
    } catch (ParseException e) {
      date = new Date();
    }
    expiryDate = date;
  }

  /**
   * Получить дату срока хранения
   * @return дата срока хранения
   */
  public Date getExpiryDate() {
    return expiryDate;
  }

  /**
   * Получить строку даты срока хранения
   * @return строка даты срока хранения
   */
  @Override
  public String getExpiryString() {
    SimpleDateFormat formatForDate = new SimpleDateFormat("dd/MM/yyyy");
    return formatForDate.format(expiryDate);
  }

  /**
   * Проверить истёк ли срок хранения
   * @return true, если срок хранения истёк; false, если срок хранения не истёк
   */
  public boolean expiryCheck() {
    return expiryDate.before(new Date());
  }
}
