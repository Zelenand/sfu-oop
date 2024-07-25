package pr3;

import java.util.Objects;

/**
 * Класс гаража
 */
public class Garage extends Structure{
  /**
   * тип стен
   */
  private String wallType;
  /**
   * количество машин
   */
  private int carNum;

  /**
   * конструктор пустого объекта
   */
  public Garage(){
    super();
    carNum = 1;
    wallType = "Undefined";
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   * @param wallType тип стен
   * @param carNum количество машин
   */
  public Garage(int index, String address,  int price, String wallType, int carNum){
    super(index, address, price);
    if (carNum >= 1) {
      this.carNum = carNum;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    this.wallType = wallType;
  }

  /**
   * Установить тип стен
   * @param wallType тип стен
   */
  public void setWallType(String wallType) {
    this.wallType = wallType;
  }

  /**
   * Установить количество машин
   * @param carNum количество машин
   */
  public void setCarNum(int carNum) {
    if (carNum >= 1) {
      this.carNum = carNum;
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
  }

  /**
   * Получить тип стен
   * @return тип стен
   */
  public String getWallType(){
    return wallType;
  }

  /**
   * Получить количество машин
   * @return количество машин
   */
  public int getCarNum(){
    return carNum;
  }

  /**
   * Сравнивает два объекта
   * @param o сравниваемый объект
   * @return равны ли объекты
   */
  @Override
  public boolean equals(Object o) {
    if (!super.equals(o)) return false;
    Garage garage = (Garage) o;
    return getIndex() == garage.getIndex();
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), carNum, wallType);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString() {
    return String.format(" Гараж, индекс: %d, Хэш: %d, адрес: %s, цена: %d, количество машин: %d, тип стен: %s",
            getIndex(), hashCode(), getAddress(), getPrice(), carNum, wallType);
  }
}
