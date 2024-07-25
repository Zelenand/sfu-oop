/**
 * Вариант 7(17): Заглавные и строчные латинские буквы в строке
 */
package pr4;

import java.util.Scanner;

public class Main {
  /**
   * Выбор функций программы
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    int func;
    UppercaseCount uppercaseCount = new UppercaseCount();
    LowercaseCount lowercaseCount = new LowercaseCount();
    while (work) {
      System.out.println("""
              Функции программы:
              1 - Посчитать заглавные и строчные латинские буквы в строке
              2 - Выход
              Введите номер функции:""");
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
            System.out.println(InputAndAnalysing(scanner, uppercaseCount, lowercaseCount));
          } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
          }

        }
        case 2 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  /**
   * Ввод и получение строки с количеством заглавных и строчных латинских букв
   * @param scanner сканер значений с консоли
   * @param uppercaseCount объект класса подсчёта количества заглавных латинских букв
   * @param lowercaseCount объект класса подсчёта количества строчных латинских букв
   * @return строка с количеством заглавных и строчных латинских букв
   */
  public static String InputAndAnalysing(Scanner scanner, UppercaseCount uppercaseCount, LowercaseCount lowercaseCount){
    String inputString;
    System.out.println("Введите строку состоящую только из букв латинского алфавита:");
    scanner.nextLine();
    if (scanner.hasNextLine()) {
      inputString = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    for (char i:inputString.toCharArray()) {
      if ((i < 65 || i > 90) & (i < 97 || i > 122)) {
        throw new IllegalArgumentException("Неккоректный ввод");
      }
    }
    return String.format("Количество заглавных: %d, количество строчных: %d", uppercaseCount.analyse(inputString),
            lowercaseCount.analyse(inputString));
  }
}