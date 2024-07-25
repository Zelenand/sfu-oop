/**
 * Нахождение минимального и максимального отрезков в массиве точек, вводимых или генерируемых случайно
 * Scanner - нужен для ввода данных из консоли
 */

import java.util.Scanner;

public class Main {
  /**
   * Выбор функций программы
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    boolean pointsExist = false;
    final int MAX = 100;
    final int MIN = 0;
    int func;
    int[][] points = new int[0][0];
    while (work) {
      System.out.println("""
              Функции программы:
              1 - Ввести координаты точек
              2 - Сгенерировать координаты точек
              3 - Найти наименьший и наибольший отрезок
              4 - Выход
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
          points = Input(scanner, points);
          if (points[0][0] == 0) {
            scanner.next();
            System.out.println("Неккоректный ввод");
            continue;
          }
          pointsExist = true;
        }
        case 2 -> {
          points = RandomPoints(scanner, points, MIN, MAX);
          if (points[0][0] == 0) {
            scanner.next();
            System.out.println("Неккоректный ввод");
            continue;
          }
          pointsExist = true;
        }
        case 3 -> {
          if (pointsExist) {
            Range(points, MIN, MAX);
          } else {
            System.out.println("Точки не сгенерированы");
            continue;
          }
        }
        case 4 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  /**
   * Нахождение пар точек, образующих наименьший и наибольший отрезок
   * @param points массив точек
   * @param MIN минимальное значение координаты точки
   * @param MAX максимальное значение координаты точки
   */
  public static void Range(int[][] points, int MIN, int MAX) {
    final int CORDINATES = 2;
    int[][] minRangePair = new int[2][2];
    int[][] maxRangePair = new int[2][2];
    double minRange = 2 * Math.sqrt(Math.pow(MAX - MIN, 2) + Math.pow(MAX - MIN, 2));
    double maxRange = 0;
    double range;
    for (int i = 0; i < points.length; i++) {
      for (int j = i + 1; j < points.length; j++) {
        range = Math.sqrt(Math.pow(points[j][0] - points[i][0], 2) +
                Math.pow(points[j][1] - points[i][1], 2));
        if (range < minRange) {
          minRange = range;
          minRangePair[0] = points[i];
          minRangePair[1] = points[j];
        } else if (range > maxRange) {
          maxRange = range;
          maxRangePair[0] = points[i];
          maxRangePair[1] = points[j];
        }
      }
    }
    Output(minRangePair, maxRangePair, minRange, maxRange);
  }

  /**
   * Вывод результата
   * @param minRangePair пара точек, образующих минимальный отрезок
   * @param maxRangePair пара точек, образующих максимальный отрезок
   * @param minRange минимальный отрезок
   * @param maxRange максимальный отрезок
   */
  public static void Output(int[][] minRangePair, int[][] maxRangePair, double minRange, double maxRange){
    System.out.println("Наименьший отрезок:");
    System.out.println("Точка А: " + minRangePair[0][0] + ", " + minRangePair[0][1]);
    System.out.println("Точка Б: " + minRangePair[1][0] + ", " + minRangePair[1][1]);
    System.out.println("Длина: " + minRange);
    System.out.println("Наибольший отрезок:");
    System.out.println("Точка А: " + maxRangePair[0][0] + ", " + maxRangePair[0][1]);
    System.out.println("Точка Б: " + maxRangePair[1][0] + ", " + maxRangePair[1][1]);
    System.out.println("Длина: " + maxRange);
  }

  /**
   * Случайная генерация точек
   * @param scanner сканер значений с консоли
   * @param points массив точек
   * @param MIN минимальное значение координаты точки
   * @param MAX максимальное значение координаты точки
   * @return points массив точек
   */
  public static int[][] RandomPoints(Scanner scanner, int[][] points, int MIN, int MAX) {
    int pointNum;
    int i;
    points = new int[1][1];
    System.out.println("Введите количество точек:");
    if (scanner.hasNextInt()) {
      pointNum = scanner.nextInt();
    } else {
      return points;
    }
    if (pointNum < 2) {
      return points;
    }
    points = new int[pointNum][2];
    for (i = 0; i < pointNum; i++) {
      points[i][0] = (int) (Math.random() * ((MAX - MIN) + 1)) + MIN;
      points[i][1] = (int) (Math.random() * ((MAX - MIN) + 1)) + MIN;
    }
    return points;
  }

  /**
   * Ввод точек пользователем
   * @param scanner сканер значений с консоли
   * @param points массив точек
   * @return points массив точек
   */
  public static int[][] Input(Scanner scanner, int[][] points) {
    int pointNum = 0;
    points = new int[1][1];
    System.out.println("Введите количество точек:");
    if (scanner.hasNextInt()) {
      pointNum = scanner.nextInt();
    } else {
      return points;
    }
    if (pointNum < 2) {
      return points;
    }
    points = new int[pointNum][2];
      for (int i = 0; i < pointNum; i++) {
        System.out.println("Введите координаты точки " + (i + 1) + ":");
        if (scanner.hasNextInt()) {
          points[i][0] = scanner.nextInt();
        } else {
          points = new int[1][1];
          return points;
        }
        if (scanner.hasNextInt()) {
          points[i][1] = scanner.nextInt();
        } else {
          points = new int[1][1];
          return points;
        }
      }
      return points;
  }
}