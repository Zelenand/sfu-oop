package com.example.pr13_3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Основной класс программы
 */
public class StorageApplication extends Application {

  /**
   * Отображение окна программы
   * @param stage окно программы
   * @throws IOException ошибка загрузки интерфейса
   */
  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(StorageApplication.class.getResource("gui.fxml"));
    Scene scene = new Scene(fxmlLoader.load(), 700, 640);
    stage.setTitle("StorageApplication");
    stage.setScene(scene);
    stage.show();
  }

  /**
   * Основная функция запускающая программу
   */
  public static void main(String[] args) {
    launch();
  }
}
