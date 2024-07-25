package pr4;

/**
 * Класс для подсчёта количества строчных латинских букв
 */
public class LowercaseCount implements StringAnalyse{

  /**
   * Метод для подсчёта количества строчных латинских букв
   * @param inputStr обрабатываемая строка
   * @return количество строчных латинских букв
   */
  @Override
  public int analyse(String inputStr) {
    StringAnalyse stringAnalyse = (str) -> {
      int LowercaseCounter = 0;
      for (char i:str.toCharArray()) {
        if (i >= 97 & i <= 122) LowercaseCounter += 1;
        else if (i < 65 || i > 90) {
          throw new IllegalArgumentException("Неккоректный ввод");
        }
      }
      return LowercaseCounter;
    };
    return stringAnalyse.analyse(inputStr);
  }
}
