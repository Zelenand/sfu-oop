package pr5;

/**
 * Класс напитка
 */
public class Drink{
  //название напитка
  private String name;
  //тип напитка
  private String type;
  //цена напитка
  private float price;
  //объём напитка
  private int volume;

  /**
   * конструктор пустого объекта
   */
  public Drink(){
    this.name = "Undefined";
    this.type = "Undefined";
    this.price = 0;
    this.volume = 0;
  }

  /**
   * Конструктор объекта
   * @param name название напитка
   * @param type тип напитка
   * @param price цена напитка
   * @param volume объём напитка
   */
  public Drink(String name, String type, float price, int volume) throws IncorrectValueException {
    if (price < 0) {
      throw new IncorrectValueException("Price is lower 0");
    }
    if (volume < 0) {
      throw new IncorrectValueException("Volume is lower 0");
    }
    this.name = name;
    this.type = type;
    this.price = price;
    this.volume = volume;
  }

  /**
   * Установить название напитка
   * @param name название напитка
   */
  public void SetName(String name){

    this.name = name;
  }

  /**
   * Установить тип напитка
   * @param type тип напитка
   */
  public void SetType(String type){
    this.type = type;
  }

  /**
   * Установить цену напитка
   * @param price цена напитка
   */
  public void SetPrice(float price) throws IncorrectValueException {
    if (price < 0){
      this.price = 0;
      throw new IncorrectValueException("Price is lower 0");
    }
    this.price = price;
  }

  /**
   * Установить объём напитка
   * @param volume объём напитка
   */
  public void SetVolume(int volume) throws IncorrectValueException {
    if (volume < 0) {
      this.volume = 0;
      throw new IncorrectValueException("Volume is lower 0");
    }
    this.volume = volume;
  }

  /**
   * Получить цену за миллилитр напитка
   * @return Цена за миллилитр напитка
   */
  public float GetPricePerMl(){
    return (volume / price);
  }

  /**
   * Получить название напитка
   * @return название напитка
   */
  public String GetName(){
    return name;
  }

  /**
   * Получить тип напитка
   * @return тип напитка
   */
  public String GetType(){
    return type;
  }

  /**
   * Получить цену напитка
   * @return цена напитка
   */
  public float GetPrice(){
    return price;
  }

  /**
   * Получить объём напитка
   * @return объём напитка
   */
  public int GetVolume(){
    return volume;
  }

  /**
   * Возвращает все параметры напитка
   * @return строка со всеми параметрами напитка
   */
  @Override
  public String toString(){
    return String.format("%s %s %f %d %f", name, type, price, volume, GetPricePerMl());
  }
}

