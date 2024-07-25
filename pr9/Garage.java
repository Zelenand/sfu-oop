package pr3;

import java.util.Objects;

/**
 * Класс гаража
 */
public class Garage extends Structure{
  /**
   * наличие смотровой ямы
   */

  private boolean pitExist;
  /**
   * наличие подвала
   */
  private boolean basementExist;

  /**
   * конструктор пустого объекта
   */
  public Garage(){
    super();
    pitExist = false;
    basementExist = false;
  }

  /**
   * Конструктор объекта
   * @param address адрес сооружения
   * @param price цена сооружения
   * @param pitExist наличие смотровой ямы
   * @param basementExist наличие подвала
   */
  public Garage(String address,  int price, boolean pitExist, boolean basementExist){
    super(address, price);
    this.pitExist = pitExist;
    this.basementExist = basementExist;
  }

  /**
   * Установить наличие смотровой ямы
   * @param pitExist наличие смотровой ямы
   */
  public void setPitExist(boolean pitExist) {
    this.pitExist = pitExist;
  }

  /**
   * Установить наличие подвала
   * @param basementExist наличие подвала
   */
  public void setBasementExist(boolean basementExist) {this.basementExist = basementExist;}

  /**
   * Получить наличие смотровой ямы
   * @return наличие смотровой ямы
   */
  public boolean getPitExist(){
    return pitExist;
  }

  /**
   * Получить наличие подвала
   * @return наличие подвала
   */
  public boolean getbasementExist(){
    return basementExist;
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
    return (pitExist == garage.pitExist && basementExist == garage.basementExist);
  }

  /**
   * Возвращает хэш объекта
   * @return хэш
   */
  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), pitExist, basementExist);
  }

  /**
   * Возвращает строку со всеми параметрами объекта
   * @return строка со всеми параметрами объекта
   */
  @Override
  public String toString() {
    return String.format(" Гараж, Хэш: %d, адрес: %s, цена: %d, наличие смотровой ямы: %b, наличие подвала: %b",
            hashCode(), getAddress(), getPrice(), pitExist, basementExist);
  }
}
