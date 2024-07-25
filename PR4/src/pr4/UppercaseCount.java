package pr4;

/**
 * Класс для подсчёта количества заглавных латинских букв
 */
public class UppercaseCount implements StringAnalyse{

  /**
   * Метод для подсчёта количества заглавных латинских букв
   * @param inputStr обрабатываемая строка
   * @return количество заглавных латинских букв
   */
  @Override
  public int analyse(String inputStr) {
    StringAnalyse stringAnalyse = (str) -> {
      int UppercaseCounter = 0;
      for (char i:str.toCharArray()) {
        if (i >= 65 & i <= 90) UppercaseCounter += 1;
        else if (i < 97 || i > 122) {
          throw new IllegalArgumentException("Неккоректный ввод");
        }
      }
      return UppercaseCounter;
    };
    return stringAnalyse.analyse(inputStr);
  }
}
