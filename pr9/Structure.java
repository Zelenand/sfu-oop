package pr3;

import java.util.Objects;

/**
 * Класс сооружения
 */
public class Structure {
  /**
   * Цена сооружения
   */
  private int price;
  /**
   * Адрес сооружения
   */
  private String address;

  /**
   * конструктор пустого объекта
   */
  public Structure(){
    address = "Undefined";
    price = 0;
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   */
  public Structure(String address,  int price){
    this.address = address;
    this.price = price;
  }

  /**
   * Установить адрес сооружения
   * @param address адрес сооружения
   */
  public void setAddress(String address){
    this.address = address;
  }

  /**
   * Установить цену сооружения
   * @param price цена сооружения
   */
  public void setPrice(int price){
    if (price > 0) {
      this.price = price;
    }
  }

  /**
   * Получить адрес сооружения
   * @return адрес сооружения
   */
  public String getAddress(){
    return address;
  }

  /**
   * Получить цену сооружения
   * @return цена сооружения
   */
  public int getPrice(){
    return price;
  }

  /**
   * Сравнивает два объекта
   * @param o сравниваемый объект
   * @return равны ли объекты
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Structure structure = (Structure) o;
    return price == structure.price && Objects.equals(address, structure.address);
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(address, price);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString(){
    return String.format(" Сооружение, Хэш: %d, адрес: %s, цена: %d", hashCode(), address, price);
  }
}
