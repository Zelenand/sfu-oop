package pr3;

import java.util.Objects;

/**
 * Класс квартиры
 */
public class Flat extends Structure{
  /**
   * количество комнат
   */

  private int roomsNum;
  /**
   * наличие балкона
   */
  private boolean balconyExist;

  /**
   * конструктор пустого объекта
   */
  public Flat(){
    super();
    roomsNum = 0;
    balconyExist = false;
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   * @param roomsNum количество комнат
   * @param balconyExist наличие балкона
   */
  public Flat(String address,  int price, int roomsNum, boolean balconyExist){
    super(address, price);
    this.roomsNum = roomsNum;
    this.balconyExist = balconyExist;
  }

  /**
   * Установить количество комнат
   * @param roomsNum количество комнат
   */
  public void setRoomsNum(int roomsNum) {
    if (roomsNum > 0){
      this.roomsNum = roomsNum;
    }
  }

  /**
   * Установить наличие чердака
   * @param balconyExist наличие чердака
   */
  public void setBalconyExist(boolean balconyExist) {this.balconyExist = balconyExist;}

  /**
   * Получить наличие чердака
   * @return наличие чердака
   */
  public boolean getBalconyExist(){
    return balconyExist;
  }

  /**
   * Получить количество комнат
   * @return количество комнат
   */
  public int getRoomsNum() {
    return roomsNum;
  }

  /**
   * Сравнивает два объекта
   * @param o сравниваемый объект
   * @return равны ли объекты
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    Flat flat = (Flat) o;
    return (roomsNum == flat.roomsNum && balconyExist == flat.balconyExist);
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), roomsNum, balconyExist);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString() {
    return String.format(" Квартира, Хэш: %d, адрес: %s, цена: %d, количество комнат: %d, наличие чердака: %b",
            hashCode(), getAddress(), getPrice(), roomsNum, balconyExist);
  }
}
