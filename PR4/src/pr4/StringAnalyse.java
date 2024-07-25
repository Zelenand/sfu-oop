package pr4;

/**
 * Интерфейс для классов работающих со строкой
 */
@FunctionalInterface
public interface StringAnalyse {

  /**
   * Метод для анализа
   * @param str строка для анализа
   * @return целое число полученное после анализа
   */
  int analyse(String str);
}
