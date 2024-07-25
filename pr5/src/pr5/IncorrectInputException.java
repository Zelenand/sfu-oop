package pr5;

/**
 * ошибка неккоректного ввода
 */
public class IncorrectInputException extends Exception {
  /**
   * конструктор пустого объекта
   */
  public IncorrectInputException(){}

  /**
   * конструктор объекта
   * @param message сообщение об ошибке
   */
  public IncorrectInputException(String message){
    super(message);
  }
}
