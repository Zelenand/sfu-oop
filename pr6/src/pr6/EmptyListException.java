package pr6;

/**
 * ошибка пустого списка
 */
public class EmptyListException extends Exception {
  /**
   * конструктор пустого объекта
   */
  public EmptyListException(){}

  /**
   * конструктор объекта
   * @param message сообщение об ошибке
   */
  public EmptyListException(String message){
    super(message);
  }
}
