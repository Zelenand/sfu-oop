package pr3;

import java.util.Objects;

/**
 * Класс дома
 */
public class House extends Structure{
  /**
   * количество комнат
   */

  private int floorsNum;
  /**
   * наличие  чердака
   */
  private boolean atticExist;

  /**
   * Конструктор пустого объекта
   */
  public House(){
    super();
    floorsNum = 0;
    atticExist = false;
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   * @param floorsNum количество этажей дома
   * @param atticExist наличие чердака
   */
  public House(String address,  int price, int floorsNum, boolean atticExist){
    super(address, price);
    this.floorsNum = floorsNum;
    this.atticExist = atticExist;
  }

  /**
   * Установить количество этажей дома
   * @param floorsNum количество этажей дома
   */
  public void setFloorsNum(int floorsNum) {
    if (floorsNum > 0){
      this.floorsNum = floorsNum;
    }
  }

  /**
   * Установить наличие чердака
   * @param atticExist наличие чердака
   */
  public void setAtticExist(boolean atticExist) {
    this.atticExist = atticExist;
  }

  /**
   * Получить количество этажей дома
   * @return количество этажей дома
   */
  public int getFloorsNum() {
    return floorsNum;
  }

  /**
   * Получить наличие чердака
   * @return наличие чердака
   */
  public boolean getAtticExist(){
    return atticExist;
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
    return floorsNum == house.floorsNum && atticExist == house.atticExist;
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), floorsNum, atticExist);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString() {
    return String.format(" Дом, Хэш: %d, адрес: %s, цена: %d, количество этажей: %d, наличее чердака: %b", hashCode(),
            getAddress(), getPrice(), floorsNum, atticExist);
  }
}
