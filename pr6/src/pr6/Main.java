//Вариант 7 - кольцевой однонаправленный список
package pr6;

import java.util.Scanner;

public class Main {

  /**
   * Выбор типа списка
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean workCheck = true;
    int func = 0;
    while (workCheck) {
      System.out.println("""
                Введите тип данных:
                1 - int
                2 - double
                3 - String""");
      if (scanner.hasNextInt()) {
        func = scanner.nextInt();
      } else {
        System.out.println("Неккоректный ввод");
        scanner.next();
        continue;
      }
      switch (func) {
        case 1 -> {
          MyLinkedList<Integer> list = new MyLinkedList<Integer>(Integer.class);
          workCycle(list, scanner);
        }
        case 2 -> {
          MyLinkedList<Double> list = new MyLinkedList<Double>(Double.class);
          workCycle(list, scanner);
        }
        case 3 -> {
          MyLinkedList<String> list = new MyLinkedList<String>(String.class);
          workCycle(list, scanner);
        }
        default -> {
          System.out.println("Неккоректный ввод");
          continue;
        }
      }
    }
  }

  public static void workCycle(MyLinkedList list, Scanner scanner){
    boolean workCheck = true;
    int func = 0;
    while (workCheck) {
      System.out.println("""
              Функции программы:
              1 - проверить, список пуст/не пуст;
              2 - установить указатель в начало списка;
              3 - добавить элемент за указателем;
              4 - удалить элемент за указателем;
              5 - просмотреть элемент за указателем;
              6 - переместить указатель вправо;
              7 - обменять значения конца списка и элемента за указателем;
              8 - обменять значения начала списка и элемента за указателем;
              9 - вывод списка на экран.
              0 - выход
              Введите номер функции:""");
      func = 0;
      if (scanner.hasNextInt()) {
        func = scanner.nextInt();
      } else {
        System.out.println("Неккоректный ввод");
        scanner.next();
        continue;
      }
      switch (func) {
        case 1 -> {
          if (list.isEmpty()){
            System.out.println("Список пуст");
          } else{
            System.out.println("Размер списка:");
            System.out.println(list.getSize());
          }
        }
        case 2 -> list.setPointerToZero();
        case 3 -> {
          try{
            addElement(list, scanner);
          } catch (EmptyListException e){
            System.out.println(e.getMessage());
          }
        }
        case 4 -> {
          try {
            list.removeNext();
          } catch (EmptyListException e){
          System.out.println(e.getMessage());
        }
        }
        case 5 -> {
          try {
            System.out.println(list.getNext());
          } catch (EmptyListException e){
            System.out.println(e.getMessage());
          }
        }
        case 6 -> {
          System.out.println("Позиция указателя:");
          System.out.println(list.movePointer());
        }
        case 7 -> {
          try {
            list.switchWithLast();
          } catch (EmptyListException e){
            System.out.println(e.getMessage());
          }
        }
        case 8 -> {
          try {
            list.switchWithFirst();
          } catch (EmptyListException e){
            System.out.println(e.getMessage());
          }
        }
        case 9 -> {
          try {
            Output(list);
          } catch (EmptyListException e){
            System.out.println(e.getMessage());
          }
        }
        case 0 -> workCheck = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  /**
   * Ввод и добавление элемента в связный список
   * @param list связный список
   * @param scanner сканнер данных с консоли
   * @throws EmptyListException ошибка пустого списка
   */
  public static void addElement(MyLinkedList list, Scanner scanner) throws EmptyListException {
    Class<?> type = list.getType();
    System.out.println("Введите значение");
    if (type == Integer.class) {
      int newElement;
      if (scanner.hasNextInt()) {
        newElement = scanner.nextInt();
      } else {
        throw new IllegalArgumentException("Неккоректный ввод");
      }
      list.addNext(newElement);
    } else if (type == Double.class) {
      double newElement;
      if (scanner.hasNextDouble()) {
        newElement = scanner.nextDouble();
      } else {
        throw new IllegalArgumentException("Неккоректный ввод");
      }
      list.addNext(newElement);
    } else if (type == String.class) {
      String newElement;
      if (scanner.hasNext()) {
        newElement = scanner.next();
      } else {
        throw new IllegalArgumentException("Неккоректный ввод");
      }
      list.addNext(newElement);
    }
  }

  /**
   * Вывод связного списка
   * @param list связный список
   * @throws EmptyListException ошибка пустого списка
   */
  public static void Output(MyLinkedList list) throws EmptyListException {
    String string = "";
    try {
      list.setPointerToZero();
      for (int i = 0; i != list.getSize(); i++) {
        string = (String) (string + list.getNext());
        string += ", ";
        list.movePointer();
      }
    } catch (Exception e){
      throw e;
    }
    System.out.println(string);
  }
}