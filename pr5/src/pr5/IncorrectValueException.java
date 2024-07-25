package pr5;

/**
 * ошибка неккоректного значения
 */
public class IncorrectValueException extends RuntimeException{
  /**
   * конструктор пустого объекта
   */
  public IncorrectValueException(){}

  /**
   * конструктор объекта
   * @param message сообщение об ошибке
   */
  public IncorrectValueException(String message){
    super(message);
  }
}
