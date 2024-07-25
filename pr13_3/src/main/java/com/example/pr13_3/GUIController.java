package com.example.pr13_3;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.time.ZoneId;
import java.util.*;
import java.net.URL;
import java.sql.SQLException;

/**
 * Контроллер логики работы окна программы
 */
public class GUIController implements Initializable {

  /**
   * Список индексов записей
   */
  @FXML
  private ListView<Integer> recordList;

  /**
   * Label составления отчёта
   */
  @FXML
  private Label reportLabel;

  /**
   * Label указания контакта
   */
  @FXML
  private Label contactLabel;

  /**
   * Label указания даты
   */
  @FXML
  private Label dateLabel;

  /**
   * Label ошибки ввода данных
   */
  @FXML
  private Label addErrorLabel;

  /**
   * Поле ввода данных для отчёта
   */
  @FXML
  private TextField reportField;

  /**
   * Поле ввода названия
   */
  @FXML
  private TextField itemField;

  /**
   * Поле ввода количества
   */
  @FXML
  private TextField quantityField;

  /**
   * Поле ввода владельца
   */
  @FXML
  private TextField ownerField;

  /**
   * Поле ввода контактов
   */
  @FXML
  private TextField contactField;

  /**
   * Поле выбора типа хранения
   */
  @FXML
  private ComboBox<String> expiryTypeBox;

  /**
   * Поле выбора типа отчёта
   */
  @FXML
  private ComboBox<String> reportBox;

  /**
   * Поле выбора типа владельца
   */
  @FXML
  private ComboBox<String> ownerTypeBox;

  /**
   * Поле выбора даты
   */
  @FXML
  private DatePicker datePicker;

  /**
   * Таблица для отображения данных
   */
  @FXML
  private TableView<Record> reportTable;

  /**
   * Столбец названия предмета таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, String> itemCol;

  /**
   * Столбец владельца таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, String> ownerCol;

  /**
   * Столбец типа владельца таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, String> ownerTypeCol;

  /**
   * Столбец колчества таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, Integer> quantityCol;

  /**
   * Столбец контактов таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, String> contactCol;

  /**
   * Столбец срока хранения таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, String> expiryCol;

  /**
   * Столбец id таблицы для отображения данных
   */
  @FXML
  private TableColumn<Record, Integer> idCol;

  /**
   * Массив типов владельцев
   */
  private static final String[] OWNER_TYPES = {"Юридическое лицо", "Физическое лицо"};
  private static final String[] REPORT_TYPES = {"Отчёт по владельцу", "Отчёт по названию предмета", "Отчёт по истекшим срокам хранения"};
  private static final String[] EXPIRY_TYPES = {"Хранение до срока", "Бессрочное хранение"};

  /**
   * Инициализация логики работы окна программы
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Database database = null;
    try {
      database = Database.getInstance();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    ObservableList<Integer> recordsId = FXCollections.observableArrayList(database.getAllRecordsId());
    recordList.setItems(recordsId);
    ObservableList<String> reportTypes = FXCollections.observableArrayList(REPORT_TYPES);
    reportBox.setItems(reportTypes);
    ObservableList<String> ownerTypes = FXCollections.observableArrayList(OWNER_TYPES);
    ownerTypeBox.setItems(ownerTypes);
    ObservableList<String> expiryTypes = FXCollections.observableArrayList(EXPIRY_TYPES);
    expiryTypeBox.setItems(expiryTypes);
    quantityField.textProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> observable, String oldValue,
                          String newValue) {
        if (!newValue.matches("\\d*")) {
          quantityField.setText(newValue.replaceAll("[^\\d]", ""));
        }
      }
    });
  }

  /**
   * Удаление записи
   * @throws SQLException ошибка работы с базой данных
   */
  @FXML
  protected void onDeleteButtonClick() throws SQLException {
    if (recordList.getSelectionModel().getSelectedItem() != null){
      Database database = Database.getInstance();
      database.deleteRecord(recordList.getSelectionModel().getSelectedItem());
      ObservableList<Integer> recordsId = FXCollections.observableArrayList(database.getAllRecordsId());
      recordList.setItems(recordsId);
    }
  }

  /**
   * Добавление записи
   * @throws SQLException ошибка работы с базой данных
   */
  @FXML
  protected void onAddButtonClick() throws SQLException {
    Database database = Database.getInstance();
    RecordMaker maker = new RecordMaker();
    database.addRecord(maker.makeRecord());
    ObservableList<Integer> recordsId = FXCollections.observableArrayList(database.getAllRecordsId());
    recordList.setItems(recordsId);
  }

  /**
   * Отображение данных выбранной записи
   * @throws SQLException ошибка работы с базой данных
   */
  @FXML
  protected void onShowButtonClick() throws SQLException {
    if (recordList.getSelectionModel().getSelectedItem() != null){
      Database database = Database.getInstance();
      Record record = database.getRecordById(recordList.getSelectionModel().getSelectedItem());
      List<Record> records = new ArrayList<Record>();
      records.add(record);
      showTableReport(records);
    }
  }

  /**
   * Отображение данных всех записей записи
   * @throws SQLException ошибка работы с базой данных
   */
  @FXML
  protected void onShowAllButtonClick() throws SQLException {
    Database database = Database.getInstance();
    showTableReport(database.getAllRecords());
  }

  /**
   * Отображение выбранного отчёта
   * @throws SQLException ошибка работы с базой данных
   */
  @FXML
  protected void onReportButtonClick() throws SQLException {
    Database database = Database.getInstance();
    String selectedReport = reportBox.getSelectionModel().getSelectedItem();
    if (Objects.equals(selectedReport, "Отчёт по владельцу")){
      if (reportField.getText() != null & !Objects.equals(reportField.getText(), "")){
        showTableReport(database.getRecordsWithOwner(reportField.getText()));
      }
    } else if (Objects.equals(selectedReport, "Отчёт по названию предмета")) {
      if (reportField.getText() != null & !Objects.equals(reportField.getText(), "")){
        showTableReport(database.getRecordsWithItem(reportField.getText()));
      }
    } else if (Objects.equals(selectedReport, "Отчёт по истекшим срокам хранения")){
      showTableReport(database.getRecordsExpired());
    } else return;
  }

  /**
   * Отображение/Скрытие поля выбора даты при смене типа хранения
   */
  @FXML
  protected void onExpiryTypeBoxAction() {
    String selectedExpiryType = expiryTypeBox.getSelectionModel().getSelectedItem();
    if (Objects.equals(selectedExpiryType, "Хранение до срока")){
      dateLabel.setDisable(false);
      datePicker.setDisable(false);
    } else {
      dateLabel.setDisable(true);
      datePicker.setDisable(true);
    }
  }

  /**
   * Отображение/Изменение поля запроса отчёта при смене типа отчёта
   */
  @FXML
  protected void onReportBoxAction() {
    String selectedReport = reportBox.getSelectionModel().getSelectedItem();
    if (Objects.equals(selectedReport, "Отчёт по владельцу")){
      reportLabel.setText("Имя/название владельца");
      reportField.setDisable(false);
    } else if (Objects.equals(selectedReport, "Отчёт по названию предмета")) {
      reportLabel.setText("Название предмета");
      reportField.setDisable(false);
    } else {
      reportLabel.setText("");
      reportField.setDisable(true);
    }
  }

  /**
   * Отображение/Изменение поля контакта владельца при смене типа владельца
   */
  @FXML
  protected void onOwnerTypeBoxAction() {
    String selectedOwnerType = ownerTypeBox.getSelectionModel().getSelectedItem();
    if (Objects.equals(selectedOwnerType, "Юридическое лицо")){
      contactLabel.setText("Адрес владельца");
      contactField.setDisable(false);
    } else if (Objects.equals(selectedOwnerType, "Физическое лицо")) {
      contactLabel.setText("Телефон владельца");
      contactField.setDisable(false);
    } else {
      contactLabel.setText("");
      contactField.setDisable(true);
    }
  }

  /**
   * Заполение таблицы данными записей
   * @param records List записей
   */
  private void showTableReport(List<Record> records) {
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
    itemCol.setCellValueFactory(new PropertyValueFactory<>("item"));
    ownerCol.setCellValueFactory(new PropertyValueFactory<>("owner"));
    ownerTypeCol.setCellValueFactory(new PropertyValueFactory<>("ownerType"));
    quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    contactCol.setCellValueFactory(new PropertyValueFactory<>("contact"));
    expiryCol.setCellValueFactory(new PropertyValueFactory<>("expiryString"));

    ObservableList<Record> observableRecords = FXCollections.observableArrayList(records);
    reportTable.setItems(observableRecords);
  }

  /**
   * Простая фабрика (Factory) Record
   */
  private class RecordMaker
  {
    /**
     * Получить Record с параметрами введёнными в окне программы
     * @return Record с параметрами введёнными в окне программы
     */
    public Record makeRecord(){
      String item;
      String owner;
      int quantity;
      String contact;
      String ownerType;
      if (itemField.getText() != null & !Objects.equals(itemField.getText(), "")){
        item = itemField.getText();
      } else {
        addErrorLabel.setText("Неккоректный ввод");
        return null;
      }

      if (ownerField.getText() != null & !Objects.equals(ownerField.getText(), "")){
        owner = ownerField.getText();
      } else {
        addErrorLabel.setText("Неккоректный ввод");
        return null;
      }

      if (quantityField.getText() != null & !Objects.equals(quantityField.getText(), "")){
        quantity = Integer.parseInt(quantityField.getText());
      } else {
        addErrorLabel.setText("Неккоректный ввод");
        return null;
      }

      String selectedOwnerType = ownerTypeBox.getSelectionModel().getSelectedItem();
      if (List.of(OWNER_TYPES).contains(selectedOwnerType)){
        ownerType = selectedOwnerType;
        if (contactField.getText() != null & !Objects.equals(contactField.getText(), "")){
          contact = contactField.getText();
        } else {
          addErrorLabel.setText("Неккоректный ввод");
          return null;
        }
      } else {
        addErrorLabel.setText("Неккоректный ввод");
        return null;
      }

      String selectedExpiryType = expiryTypeBox.getSelectionModel().getSelectedItem();
      if (Objects.equals(selectedExpiryType, "Хранение до срока")) {
        if (datePicker.getValue() != null) {
          Date date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
          return new ExpiringRecord(item, quantity, owner, ownerType, contact, date);
        } else {
          addErrorLabel.setText("Неккоректный ввод");
        }
      } else if (List.of(EXPIRY_TYPES).contains(selectedExpiryType)) {
        return new  Record(item, quantity, owner, ownerType, contact);
      } else {
        addErrorLabel.setText("Неккоректный ввод");
      }
      return null;
    }
  }
}
