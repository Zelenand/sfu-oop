package com.example.pr13_3;

/**
 * Объект записи о хранении
 */
public class Record {

  /**
   * Идентификатор записи
   */
  private int id;

  /**
   * Количество хранимого предмета
   */
  private final int quantity;

  /**
   * Название хранимого предмета
   */
  private final String item;

  /**
   * Владелец хранимого предмета
   */
  private final String owner;

  /**
   * Тип владельца хранимого предмета
   */
  private final String ownerType;

  /**
   * Контакты владельца хранимого предмета
   */
  private final String contact;

  /**
   * Конструктор записи о хранении
   * @param item Название хранимого предмета
   * @param quantity Количество хранимого предмета
   * @param owner Владелец хранимого предмета
   * @param ownerType Тип владельца хранимого предмета
   * @param contact Контакты владельца хранимого предмета
   */
  public Record(String item, int quantity, String owner, String ownerType, String contact){
    this.item = item;
    this.owner = owner;
    this.ownerType = ownerType;
    this.contact = contact;
    this.quantity = Math.max(quantity, 1);
  }

  /**
   * Конструктор записи о хранении из базы данных
   * @param id Идентификатор хранимого предмета
   * @param item Название хранимого предмета
   * @param quantity Количество хранимого предмета
   * @param owner Владелец хранимого предмета
   * @param ownerType Тип владельца хранимого предмета
   * @param contact Контакты владельца хранимого предмета
   */
  public Record(int id, String item, int quantity, String owner, String ownerType, String contact){
    this.id = id;
    this.item = item;
    this.owner = owner;
    this.ownerType = ownerType;
    this.contact = contact;
    this.quantity = Math.max(quantity, 1);
  }

  /**
   * Получить идентификатор хранимого предмета
   * @return Идентификатор хранимого предмета
   */
  public int getId() {
    return id;
  }

  /**
   * Получить Количество хранимого предмет
   * @return Количество хранимого предмета
   */
  public int getQuantity() {
    return quantity;
  }

  /**
   * Получить Название хранимого предмета
   * @return Название хранимого предмета
   */
  public String getItem() {
    return item;
  }

  /**
   * Получить Владелец хранимого предмета
   * @return Владелец хранимого предмета
   */
  public String getOwner() {
    return owner;
  }

  /**
   * Получить Тип владельца хранимого предмета
   * @return Тип владельца хранимого предмета
   */
  public String getOwnerType() {
    return ownerType;
  }

  /**
   * Получить Контакты владельца хранимого предмета
   * @return Контакты владельца хранимого предмета
   */
  public String getContact() {
    return contact;
  }

  /**
   * Получить строку срока хранения
   * @return строка срока хранения
   */
  public String getExpiryString() {
    return "Бессрочно";
  }
}
