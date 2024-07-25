/**
 * Вариант 17: Сооружение, дом, гараж, квартира
 */

package pr3;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
  /**
   * Выбор функций программы
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    int func;
    ArrayList<Structure> structures = new ArrayList<>();
    while (work) {
      System.out.println("""
              Функции программы:
              1 - Добавление сооружения
              2 - Удалить сооружение из списка
              3 - Вывод информации обо всех сооружениях
              4 - Сравнение сооружений на равенство
              5 - Выход
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
          try {
            structures.add(Input(scanner, structures.size() + 1));
          } catch (IllegalArgumentException e) {
            scanner.nextLine();
            System.out.println(e.getMessage());
          }
        }
        case 2 -> {
          if (structures.size() != 0){
            try {
              Delete(scanner, structures);
            } catch (IllegalArgumentException e) {
              scanner.nextLine();
              System.out.println(e.getMessage());
            }
          } else {
            System.out.println("Сначала добавьте сооружения");
          }
        }
        case 3 -> {
          if (structures.size() != 0){
            try {
              Output(structures);
            } catch (IllegalArgumentException e) {
              scanner.nextLine();
              System.out.println(e.getMessage());
            }
          } else {
            System.out.println("Сначала добавьте сооружения");
          }
        }
        case 4 -> {
          if (structures.size() != 0){
            try {
              EqualCheck(scanner, structures);
            } catch (IllegalArgumentException e) {
              scanner.nextLine();
              System.out.println(e.getMessage());
            }
          } else {
            System.out.println("Сначала добавьте сооружения");
          }
        }
        case 5 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  /**
   * Ввод данных
   * @param scanner сканер значений с консоли
   * @return новый объект
   */
  public static Structure Input(Scanner scanner, int index) {
    int structType;
    System.out.println("""
            Выберите тип сооружения:
            1 - Сооружения
            2 - Дом
            3 - Гараж
            4 - Квартира
            """);
    scanner.nextLine();
    if (scanner.hasNextInt()) {
      structType = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (structType > 4 || structType < 1) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    String tempAddress;
    int tempPrice;
    System.out.println("Введите адрес сооружения");
    scanner.nextLine();
    if (scanner.hasNextLine()) {
      tempAddress = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите цену сооружения");
    if (scanner.hasNextInt()) {
      tempPrice = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (tempPrice <= 0) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    Structure struct = new Structure();
    switch (structType) {
      case 1 -> {
        struct = new Structure(index, tempAddress, tempPrice);
      }
      case 2 -> {
        struct = InputHouse(scanner, index, tempAddress, tempPrice);
      }
      case 3 -> {
        struct = InputGarage(scanner, index, tempAddress, tempPrice);
      }
      case 4 -> {
        struct = InputFlat(scanner, index, tempAddress,tempPrice);
      }
    }
    return struct;
  }

  /**
   * Создание дома
   * @param scanner сканер значений с консоли
   * @param tempAddress адресс сооружения
   * @param tempPrice цена сооружения
   * @return дом
   */
  public static House InputHouse(Scanner scanner, int index, String tempAddress, int tempPrice) {
    int tempFloorsNum;
    System.out.println("Введите количество этажей");
    if (scanner.hasNextInt()) {
      tempFloorsNum = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (tempFloorsNum <= 0) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    String tempWallType;
    System.out.println("Введите тип стен");
    scanner.nextLine();
    if (scanner.hasNextLine()) {
      tempWallType = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    return new House(index, tempAddress, tempPrice, tempFloorsNum, tempWallType);
  }

  /**
   * Создание гаража
   * @param scanner сканер значений с консоли
   * @param tempAddress адресс сооружения
   * @param tempPrice цена сооружения
   * @return гараж
   */
  public static Garage InputGarage(Scanner scanner, int index, String tempAddress, int tempPrice) {
    String tempWallType;
    System.out.println("Введите тип стен");
    scanner.nextLine();
    if (scanner.hasNextLine()) {
      tempWallType = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    int tempCarNum;
    System.out.println("Введите количество машин");
    if (scanner.hasNextInt()) {
      tempCarNum = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (tempCarNum <= 0) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    return new Garage(index, tempAddress, tempPrice, tempWallType, tempCarNum);
  }

  /**
   * Создание квартиры
   * @param scanner сканер значений с консоли
   * @param tempAddress адресс сооружения
   * @param tempPrice цена сооружения
   * @return квартира
   */
  public static Flat InputFlat(Scanner scanner, int index, String tempAddress, int tempPrice) {
    String tempWallType;
    System.out.println("Введите тип стен");
    scanner.nextLine();
    if (scanner.hasNextLine()) {
      tempWallType = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    int tempRoomsNum;
    System.out.println("Введите количество комнат");
    if (scanner.hasNextInt()) {
      tempRoomsNum = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (tempRoomsNum <= 0) {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    return new Flat(index, tempAddress, tempPrice, tempRoomsNum, tempWallType);
  }

  /**
   * Вывод данных
   * @param structures список сооружений
   */
  public static void Output(ArrayList<Structure> structures){
    int i = 0;
    for(Structure drink : structures) {
      i++;
      System.out.println(" " + i + drink.toString());
    }
  }

  /**
   * Удалить сооружение из списка
   * @param scanner сканер значений с консоли
   * @param structures список сооружений
   */
  public static void Delete(Scanner scanner, ArrayList<Structure> structures){
    System.out.println("Введите номер сооружения:");
    int structNumber = 0;
    if (scanner.hasNextInt()) {
      structNumber = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (structNumber > structures.size() || structNumber < 1){
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    structNumber--;
    structures.remove(structNumber);
  }

  /**
   * Проверка на равенство объектов
   * @param scanner сканер значений с консоли
   * @param structures список сооружений
   */
  public static void EqualCheck(Scanner scanner, ArrayList<Structure> structures){
    int structNumber1;
    int structNumber2;
    System.out.println("Введите номер первого сооружения:");
    if (scanner.hasNextInt()) {
      structNumber1 = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (structNumber1 > structures.size() || structNumber1 < 1){
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    structNumber1--;
    System.out.println("Введите номер второго сооружения:");
    if (scanner.hasNextInt()) {
      structNumber2 = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (structNumber2 > structures.size() || structNumber2 < 1){
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    structNumber2--;
    System.out.println("Результат: " + structures.get(structNumber1).equals(structures.get(structNumber2)));
  }
}