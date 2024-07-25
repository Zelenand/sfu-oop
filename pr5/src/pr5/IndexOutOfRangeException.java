package pr5;

/**
 * ошибка выбора индекса за пределами списка
 */
public class IndexOutOfRangeException extends RuntimeException{
  /**
   * конструктор пустого объекта
   */
  public IndexOutOfRangeException(){}

  /**
   * конструктор объекта
   * @param message сообщение об ошибке
   */
  public IndexOutOfRangeException(String message){
    super(message);
  }
}
