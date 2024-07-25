package pr8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;


/**
 * Класс поезда
 */
public class Train implements Runnable{

  /**
   * Номер поезда
   */
  private final int id;

  /**
   * Очередь путей
   */
  private final BlockingQueue<Boolean> railways;

  /**
   * Executor для запуска на станции
   */
  private final Executor executor;

  /**
   * Runnable task для запуска в потоках
   */
  private final Runnable task;

  /**
   * Конструктор поезда
   * @param id Номер поезда
   * @param waitTime Время (число) ожидания на станции
   * @param railways Очередь путей
   * @param executor Executor для запуска на станции
   */
  public Train(int id, long waitTime, BlockingQueue<Boolean> railways, Executor executor){
    this.id = id;
    this.railways = railways;
    this.executor = executor;
    task = () -> {
      System.out.println("Поезд " + id + " заехал на путь станции");
      try {
        TimeUnit.SECONDS.sleep(waitTime);
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
      railways.add(true);
      System.out.println("Поезд " + id + " уезжает с пути");
    };

  }

  /**
   * Метод для потока исполнения
   */
  @Override
  public void run() {
    System.out.println("Поезд " + id + " подъехал к станции");
    try {
      if (railways.take()){
        executor.execute(task);
      }
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
