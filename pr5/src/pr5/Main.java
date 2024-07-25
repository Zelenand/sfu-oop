//Основано на работе 2 - Вариант 17: Данные о напитках


package pr5;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Comparator;


public class Main {

  /**
   * Выбор функций программы
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    boolean work = true;
    boolean drinksExist = false;
    int func;
    ArrayList<Drink> drinks = new ArrayList<>();
    while (work) {
      System.out.println("""
            Функции программы:
            1 - Добавление пустого напитка к списку
            2 - Добавление напитка с данными, заполненными пользователем
            3 - Редактирование поля напитка
            4 - Вывод информации обо всех напитках
            5 - Сортировка списка напитков по любому из полей
            6 - Выход
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
            drinks.add(new Drink());
            drinksExist = true;
        }
        case 2 -> {
          try {
            drinks.add(Input(scanner, func));
            drinksExist = true;
          } catch (IncorrectInputException e){
            scanner.next();
            System.out.println(e.getMessage());
          } catch (IncorrectValueException e){
            drinks.add(new Drink());
            drinksExist = true;
            System.out.println(e.getMessage());
            if (e.getCause() != null){
              System.out.println(e.getCause());
            }
          }
        }
        case 3 -> {
          if (drinksExist) {
            try {
              FieldEdit(scanner, drinks);
            } catch (IncorrectInputException e) {
              scanner.next();
              System.out.println(e.getMessage());
            } catch (IncorrectValueException e){
              System.out.println(e.getMessage());
              if (e.getCause() != null) {
                System.out.println(e.getCause());
              }
            } catch (IndexOutOfRangeException ignored){}
          } else {
            System.out.println("Сначала добавьте напитки");
          }
        }
        case 4 -> Output(drinks);
        case 5 -> {
          if (drinksExist) {
            try {
              DrinksSort(scanner, drinks);
            } catch (IncorrectInputException e) {
              scanner.next();
              System.out.println(e.getMessage());
            }
          } else {
            System.out.println("Сначала добавьте напитки");
          }
        }
        case 6 -> work = false;
        default -> System.out.println("Неккоректный ввод");
      }
    }
  }

  /**
   * Ввод данных
   * @param scanner сканер значений с консоли
   * @param func номер функции
   * @return новый объект-напиток
   */
  public static Drink Input(Scanner scanner, int func) throws IncorrectInputException, IncorrectValueException {
    if (func == 1){
      return new Drink();
    } else {
      String tempName;
      String tempType;
      int tempVolume;
      float tempPrice;
      System.out.println("Введите название нового напитка");
      scanner.nextLine();
      if (scanner.hasNextLine()){
        tempName = scanner.nextLine();
      } else {
        throw new IncorrectInputException("Неккоректный ввод");
      }
      System.out.println("Введите тип нового напитка");
      if (scanner.hasNextLine()){
        tempType = scanner.nextLine();
      } else {
        throw new IncorrectInputException("Неккоректный ввод");
      }
      System.out.println("Введите цену нового напитка");
      if (scanner.hasNextFloat()){
        tempPrice = scanner.nextFloat();
      } else {
        throw new IncorrectInputException("Неккоректный ввод");
      }
      System.out.println("Введите объём в мл нового напитка");
      if (scanner.hasNextInt()){
        tempVolume = scanner.nextInt();
      } else {
        throw new IncorrectInputException("Неккоректный ввод");
      }
      Drink newDrink;
      try {
        newDrink = new Drink(tempName, tempType, tempPrice, tempVolume);
        return newDrink;
      } catch(IncorrectValueException e){
        IncorrectValueException ex = new IncorrectValueException("Неккоректное значение, создан объект по умолчанию");
        ex.initCause(e);
        throw ex;
      }
    }
  }

  /**
   * Вывод данных
   * @param drinks список напитков
   */
  public static void Output(ArrayList<Drink> drinks){
    int i = 0;
    for(Drink drink : drinks) {
      i++;
      System.out.println(" " + i + drink.toString());
    }
  }

  /**
   * Редактирование полей объекта
   * @param scanner сканер значений с консоли
   * @param drinks список напитков
   */
  public static void FieldEdit(Scanner scanner, ArrayList<Drink> drinks) throws IncorrectValueException, IndexOutOfRangeException, IncorrectInputException {
    System.out.println("Введите номер напитка:");
    int drinkNumber = 0;
    if (scanner.hasNextInt()) {
      drinkNumber = scanner.nextInt();
    } else {
      throw new IncorrectInputException("Неккоректный ввод");
    }
    if (drinkNumber > drinks.size() || drinkNumber < 1){
      throw new IndexOutOfRangeException("Номер напитка больше чем их количество");
    }
    drinkNumber--;
    System.out.println("""
            Какое поле изменить?
            1 - Название
            2 - Тип
            3 - Цена
            4 - Объём""");
    int fieldNumber = 0;
    if (scanner.hasNextInt()) {
      fieldNumber = scanner.nextInt();
    } else {
      throw new IncorrectInputException("Неккоректный ввод");
    }

    switch (fieldNumber){
      case 1 -> {
        String inputName;
        System.out.println("Введите новое название напитка");
        scanner.nextLine();
        inputName = scanner.nextLine();
        Drink editedDrink = drinks.get(drinkNumber);
        editedDrink.SetName(inputName);
        drinks.set(drinkNumber, editedDrink);
      }
      case 2 -> {
        String inputType;
        System.out.println("Введите новый тип напитка");
        scanner.nextLine();
        inputType = scanner.nextLine();
        Drink editedDrink = drinks.get(drinkNumber);
        editedDrink.SetType(inputType);
        drinks.set(drinkNumber, editedDrink);

      }
      case 3 -> {
        float inputPrice;
        System.out.println("Введите новую цену напитка");
        if (scanner.hasNextFloat()){
          inputPrice = scanner.nextFloat();
        } else {
          throw new IncorrectInputException("Неккоректный ввод");
        }
        assert inputPrice > 0: inputPrice;
        Drink editedDrink = drinks.get(drinkNumber);
        editedDrink.SetPrice(inputPrice);
        drinks.set(drinkNumber, editedDrink);
      }
      case 4 -> {
        int inputVolume;
        System.out.println("Введите новый объём напитка");
        if (scanner.hasNextInt()){
          inputVolume = scanner.nextInt();
        } else {
          throw new IncorrectInputException("Неккоректный ввод");
        }
        Drink editedDrink = drinks.get(drinkNumber);
        try {
          editedDrink.SetVolume(inputVolume);
        } catch(IncorrectValueException e){
          scanner.next();
          IncorrectValueException ex = new IncorrectValueException("Неккоректное значение");
          ex.initCause(e);
          throw ex;
        }
        drinks.set(drinkNumber, editedDrink);
      }
    }
  }

  /**
   * Сортировка напитков
   * @param scanner сканер значений с консоли
   * @param drinks список напитков
   */
  public static void DrinksSort(Scanner scanner, ArrayList<Drink> drinks) throws IncorrectInputException {
    System.out.println("""
            По какому полю сортировать напитки?
            1 - По названию
            2 - По типу
            3 - По цене
            4 - По объёму
            5 - По цене за миллилитр""");
    int sortingField = 0;
    if (scanner.hasNextInt()) {
      sortingField = scanner.nextInt();
    } else {
      throw new IncorrectInputException("Неккоректный ввод");
    }
    ArrayList<Drink> sortedDrinks = new ArrayList<>(drinks);

    switch (sortingField){
      case 1 -> sortedDrinks.sort(Comparator.comparing(Drink::GetName));
      case 2 -> sortedDrinks.sort(Comparator.comparing(Drink::GetType));
      case 3 -> sortedDrinks.sort(Comparator.comparing(Drink::GetPrice));
      case 4 -> sortedDrinks.sort(Comparator.comparing(Drink::GetVolume));
      case 5 -> sortedDrinks.sort(Comparator.comparing(Drink::GetPricePerMl));
      default -> throw new IncorrectInputException("Неккоректный ввод");
    }
    Output(sortedDrinks);
  }
}
