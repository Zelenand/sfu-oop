package pr3;

import java.util.Objects;

/**
 * Класс дома
 */
public class House extends Structure{
  /**
   * количество этажей
   */
  private int floorsNum;
  /**
   * тип стен
   */
  private String wallType;

  /**
   * Конструктор пустого объекта
   */
  public House(){
    super();
    floorsNum = 1;
    wallType = "Undefined";
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   * @param floorsNum количество этажей дома
   * @param wallType тип стен
   */
  public House(int index, String address,  int price, int floorsNum, String wallType){
    super(index, address, price);
    if (floorsNum >= 1) {
      this.floorsNum = floorsNum;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    this.wallType = wallType;
  }

  /**
   * Установить количество этажей дома
   * @param floorsNum количество этажей дома
   */
  public void setFloorsNum(int floorsNum) {
    if (floorsNum >= 1) {
      this.floorsNum = floorsNum;
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
   * Получить количество этажей дома
   * @return количество этажей дома
   */
  public int getFloorsNum() {
    return floorsNum;
  }

  /**
   * Получить тип стен
   * @return тип стен
   */
  public String getWallType(){
    return wallType;
  }

  /**
   * Сравнивает два объекта
   * @param o сравниваемый объект
   * @return равны ли объекты
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    House house = (House) o;
    return getIndex() == house.getIndex();
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), floorsNum, wallType);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString() {
    return String.format(" Дом, индекс: %d, Хэш: %d, адрес: %s, цена: %d, количество этажей: %d, тип стен: %s",
            getIndex(), hashCode(), getAddress(), getPrice(), floorsNum, wallType);
  }
}
