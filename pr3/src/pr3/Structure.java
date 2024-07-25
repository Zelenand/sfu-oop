package pr3;

import java.util.Objects;

/**
 * Класс сооружения
 */
public class Structure {
  /**
   * Индекс сооружения
   */
  final private int index;
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
    index = 0;
    address = "Undefined";
    price = 0;
  }

  /**
   * Конструктор объекта
   *
   * @param index Индекс сооружения
   * @param address адрес сооружения
   * @param price   цена сооружения
   */
  public Structure(int index, String address, int price){
    if (index >= 0) {
      this.index = index;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (price >= 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    this.address = address;
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
    if (price >= 0) {
      this.price = price;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
  }

  /**
   * Получить индекс сооружения
   * @return индекс сооружения
   */
  public int getIndex(){
    return index;
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
    return index == structure.index;
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(index, address, price);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString(){
    return String.format(" Сооружение, индекс: %d, Хэш: %d, адрес: %s, цена: %d", getIndex(), hashCode(), address, price);
  }
}
