package pr6;

/**
 * Однонаправленный кольцевой список
 * @param <T> тип хранимых данных
 */
public class MyLinkedList<T> {

  /**
   * Первый элемент
   */
  private Node<T> first;

  /**
   * Последний элемент
   */
  private Node<T> last;

  /**
   * Размер списка
   */
  private int size;

  /**
   * указатель
   */
  private int pointer;

  /**
   * тип хранимых данных
   */
  private final Class<T> type;

  /**
   * Класс элемента списка
   * @param <T> тип хранимого элемента
   */
  private static class Node<T> {
    /**
     * хранимые данные
     */
    private T data;

    /**
     * следующий элемент
     */
    private Node<T> next;

    /**
     * Конструктор
     * @param data хранимые данные
     */
    Node(T data){
      this.data = data;
    }

    /**
     * Установить данные
     * @param data данные
     */
    public void setData(T data) {
      this.data = data;
    }

    /**
     * Получить данные
     * @param data данные
     */
    public T getData() {
      return data;
    }

    /**
     * Установить следующий элемент
     * @param next следующий элемент
     */
    public void setNext(Node<T> next) {
      this.next = next;
    }

    /**
     * Получить следующий элемент
     * @param next следующий элемент
     */
    public Node<T> getNext() {
      return next;
    }
  }

  /**
   * Конструктор
   * @param type хранимый тип
   */
  public MyLinkedList(Class<T> type){
    this.type = type;
    this.first = null;
    this.last = null;
    this.size = 0;
    this.pointer = 0;
  }

  /**
   * Проверить пуст ли список
   * @return пуст ли список
   */
  public boolean isEmpty(){
    if (this.size == 0) {
      return true;
    } else{
      return false;
    }
  }

  /**
   * Получить размер списка
   * @return размер списка
   */
  public int getSize(){
    return this.size;
  }

  /**
   * Установить значение указателя на ноль
   */
  public void setPointerToZero(){
    this.pointer = 0;
  }

  /**
   * Сдвинуть указатель вправо
   * @return новое значение указателя
   */
  public int movePointer(){
    if (this.pointer + 1 > this.size){
      this.setPointerToZero();
    } else{
      this.pointer = this.pointer + 1;
    }
    return this.pointer;
  }

  /**
   * Добавить элемент за указателем
   * @param data данные нового элемента
   */
  public void addNext(T data){
    Node<T> node = new Node<T>(data);
    if (this.size == 0){
      this.first = node;
      this.first.setNext(this.first);
      this.last = this.first;
    } else {
      Node<T> tempNode;
      if (this.pointer == 0) {
        tempNode = this.first;
        this.first = node;
        this.first.setNext(tempNode);
      } else {
        Node<T> nowNode = this.first;
        for (int i = 1; i != this.pointer; i++) {
          nowNode = nowNode.getNext();
        }
        tempNode = nowNode.getNext();
        nowNode.setNext(node);
        nowNode.getNext().setNext(tempNode);
        if (nowNode == this.last) {
          this.last = nowNode.getNext();
        }
      }
    }
    this.size = this.size + 1;
  }

  /**
   * Удалить элемент за указателем
   * @throws EmptyListException Ошибка пустого списка
   */
  public void removeNext() throws EmptyListException {
    switch (this.size){
      case 0 -> throw new EmptyListException("Список пуст");
      case 1 ->{
        this.first = null;
        this.last = null;
        this.size = 0;
      }
      default -> {
        if (this.pointer == 0){
          this.first = this.first.getNext();
          this.last.setNext(this.first);
        } else {
          Node<T> nowNode = this.first;
          for (int i = 1; i != this.pointer; i++) {
            nowNode = nowNode.getNext();
          }
          if (nowNode.getNext() == this.last) {
            nowNode.setNext(this.first);
            this.last = nowNode;
          } else if (nowNode.getNext() != this.first) {
            nowNode.setNext(nowNode.getNext().getNext());
          }
        }
        this.size = this.size - 1;
      }
    }
  }

  /**
   * получить элемент за указателем
   * @return данные элемента за указателем
   * @throws EmptyListException Ошибка пустого списка
   */
  public T getNext() throws EmptyListException {
    if (this.size == 0) throw new EmptyListException("Список пуст");
    Node<T> nowNode = this.first;
    for (int i = 0; i != this.pointer; i++) {
      nowNode = nowNode.getNext();
    }
    return nowNode.getData();
  }

  /**
   * Поменять значения элемента за указателем и в начале списка
   * @throws EmptyListException Ошибка пустого списка
   */
  public void switchWithFirst() throws EmptyListException {
    if (this.size > 0) {
      Node<T> nowNode = this.first;
      for (int i = 0; i != this.pointer; i++) {
        nowNode = nowNode.getNext();
      }
      T tempData = this.first.getData();
      this.first.setData(nowNode.getData());
      nowNode.setData(tempData);
    } else{
      throw new EmptyListException("Список пуст");
    }
  }

  /**
   * Поменять значения элемента за указателем и в конце списка
   * @throws EmptyListException Ошибка пустого списка
   */
  public void switchWithLast() throws EmptyListException {
    if (this.size > 0) {
      Node<T> nowNode = this.first;
      for (int i = 0; i != this.pointer; i++) {
        nowNode = nowNode.getNext();
      }
      T tempData = this.last.getData();
      this.last.setData(nowNode.getData());
      nowNode.setData(tempData);
    } else{
      throw new EmptyListException("Список пуст");
    }
  }

  /**
   * Получить тип списка
   * @return тип списка
   */
  public Class<?> getType() {
    return this.type;
  }
}
