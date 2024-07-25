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
   * тип стен
   */
  private String wallType;

  /**
   * конструктор пустого объекта
   */
  public Flat(){
    super();
    roomsNum = 1;
    wallType = "Undefined";
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   * @param roomsNum количество комнат
   * @param wallType тип стен
   */
  public Flat(int index, String address,  int price, int roomsNum, String wallType){
    super(index, address, price);
    if (roomsNum >= 1) {
      this.roomsNum = roomsNum;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    this.wallType = wallType;
  }

  /**
   * Установить количество комнат
   * @param roomsNum количество комнат
   */
  public void setRoomsNum(int roomsNum) {
    if (roomsNum >= 1) {
      this.roomsNum = roomsNum;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
  }

  /**
   * Установить тип стен
   * @param wallType тип стен
   */
  public void setWallType(String wallType) {
    this.wallType = wallType;
  }

  /**
   * Получить тип стен
   * @return тип стен
   */
  public String getWallType(){
    return wallType;
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
    return getIndex() == flat.getIndex();
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), roomsNum, wallType);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString() {
    return String.format(" Квартира, индекс: %d, Хэш: %d, адрес: %s, цена: %d, количество комнат: %d, тип стен: %s",
            getIndex(), hashCode(), getAddress(), getPrice(), roomsNum, wallType);
  }
}
