package pr8;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Параллельное программирование Вариант 17(7) Железнодорожная станция
 */
public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    final int railwayNumber;
    final int trainNumber = 100;
    final int maxWait = 10;
    final int minWait = 1;
    final int arrivalTime = 5;
    try {
      System.out.println("Введите количество путей");
      if (scanner.hasNextInt()) {
        railwayNumber = scanner.nextInt();
      } else {
        throw new IllegalArgumentException("Неккоректный ввод");
      }
      if (railwayNumber < 1) throw new IllegalArgumentException("Неккоректный ввод");
      station(railwayNumber, trainNumber, maxWait, minWait, arrivalTime);
    } catch (IllegalArgumentException | InterruptedException e ){
      System.out.println(e.getMessage());
    }
  }

  /**
   * Запуск поездов к станции
   * @param railwayNumber количество путей
   * @param trainNumber количество поездов
   * @param maxWait максимальное время на станции
   * @param minWait минимальное время на станции
   * @param arrivalTime время прибытия
   * @throws InterruptedException ошибка с временем
   */
  public static void station(int railwayNumber, int trainNumber, int maxWait, int minWait, int arrivalTime) throws InterruptedException {
    Executor executor = Executors.newCachedThreadPool();
    Executor fixedExecutor = Executors.newCachedThreadPool();
    BlockingQueue<Boolean> railways = new LinkedBlockingQueue<>(railwayNumber);
    for (int i = 0; i < railwayNumber; i++) {
      railways.add(true);
    }
    for (int i = 1; i < trainNumber + 1; i++) {
      TimeUnit.SECONDS.sleep(arrivalTime);
      Train train = new Train(i, (long) ((Math.random() * ((maxWait - minWait) + 1)) + minWait), railways, fixedExecutor);
      executor.execute(train);
    }
  }
}