/**
 * Вариант 17: Данные о напитках
 */

package pr7;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;


public class Main {

  /**
   * Выбор функций программы
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    int func;
    ArrayList<Drink> drinks = new ArrayList<>();
    Drink drink = new Drink("яблочный сок", "сок", 90, 50);
    drinks.add(drink);
    drinks.add(drink);
    drinks.add(new Drink("апельсиновый сок", "сок", 100, 40));
    drinks.add(new Drink("лимонад", "газировка", 110, 100));
    while (work) {
      System.out.println("""
            Функции программы:
            1 - Добавление напитка с данными, заполненными пользователем
            2 - Создание потока из списка напитков и вывод их на экран.
            3 - Фильтрация напитков по цене
            4 - Изъятие из списка дубликатов
            5 - Вывести сумму цен всех напитков
            6 - Группировка напитков по типу
            7 - SummaryStatistics цены напитков
            8 - Выход
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
            drinks.add(Input(scanner));
          } catch (IllegalArgumentException e){
            scanner.next();
            System.out.println(e.getMessage());
          }
        }
        case 2 -> drinks.stream().forEach(System.out::println);
        case 3 -> {
          try {
            FiltrateByPrice(drinks, scanner);
          } catch (IllegalArgumentException e){
            scanner.next();
            System.out.println(e.getMessage());
          }
        }
        case 4 -> {
          drinks = drinks.stream().distinct().collect(Collectors.toCollection(ArrayList::new));
        }
        case 5 -> {
          PriceSummarize(drinks);
        }
        case 6 -> {
          GroupedByType(drinks);
        }
        case 7 -> {
          System.out.println(drinks.stream().collect(Collectors.summarizingDouble(Drink::GetPrice)));
        }
        case 8 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  /**
   * Ввод данных
   * @param scanner сканер значений с консоли
   * @return новый объект-напиток
   */
  public static Drink Input(Scanner scanner) {
    String tempName;
    String tempType;
    int tempVolume;
    float tempPrice;
    System.out.println("Введите название нового напитка");
    scanner.nextLine();
    if (scanner.hasNextLine()){
      tempName = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите тип нового напитка");
    if (scanner.hasNextLine()){
      tempType = scanner.nextLine();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите цену нового напитка");
    if (scanner.hasNextFloat()){
      tempPrice = scanner.nextFloat();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (tempPrice < 0){
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    System.out.println("Введите объём в мл нового напитка");
    if (scanner.hasNextInt()){
      tempVolume = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    if (tempVolume < 0){
      throw new IllegalArgumentException("Неккоректный ввод");
    }

    return new Drink(tempName, tempType, tempPrice, tempVolume);
  }

  /**
   * Фильтрация напитков по цене
   * @param drinks ArrayList напитков
   * @param scanner сканер значений с консоли
   */
  public static void FiltrateByPrice(ArrayList<Drink> drinks, Scanner scanner) {
    System.out.println("Введите цену, меньше которой вывести напитки");
    int filterPrice;
    if (scanner.hasNextInt()){
      filterPrice = scanner.nextInt();
    } else {
      throw new IllegalArgumentException("Неккоректный ввод");
    }
    drinks.stream().filter(d -> d.GetPrice() <= filterPrice).forEach(System.out::println);
  }

  /**
   * Группировка напитков по типу
   * @param drinks ArrayList напитков
   */
  public static void GroupedByType(ArrayList<Drink> drinks) {
    Stream<Drink> drinksStream = drinks.stream();
    Map<String, List<Drink>> drinksByType = drinksStream.collect(Collectors.groupingBy(Drink::GetType));
    for(Map.Entry<String, List<Drink>> item : drinksByType.entrySet()){
      System.out.println(item.getKey());
      System.out.println(item.getValue().size());
      for(Drink eachDrink : item.getValue()){
        System.out.println(eachDrink.toString());
      }
      System.out.println();
      }
  }

  /**
   * Вывести сумму цен всех напитков
   * @param drinks ArrayList напитков
   */
  public static void PriceSummarize(ArrayList<Drink> drinks) {
    Stream<Float> prices = drinks.stream().map(Drink::GetPrice);
    Optional<Float> fullPrice = prices.reduce((x,y) -> x + y);
    System.out.println(fullPrice.orElse(0.0F));
  }

}
