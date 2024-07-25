package com.example.pr13_3;

import org.sqlite.JDBC;
import java.sql.*;
import java.util.*;

/**
 * Подключение и взаимодействие с базой данных. Singlton
 */
public class Database {

  /**
   * Единственный экземпляр класса (Singlton)
   */
  private static Database INSTANCE;

  /**
   * Строка параметров подключения к базе данных
   */
  private static final String CON_STR = "jdbc:sqlite:database.s3db";

  /**
   * Связь с базой данных
   */
  private Connection connection;

  /**
   * Конструктор. Инициализация подключения
   * @throws SQLException ошибка подключения к базе данных
   */
  private Database() throws SQLException {
    DriverManager.registerDriver(new JDBC());
    this.connection = DriverManager.getConnection(CON_STR);
  }

  /**
   * Получение/создание единственного экземпляра класса (Singlton)
   * @return Единственный экземпляр класса
   * @throws SQLException ошибка подключения к базе данных
   */
  public static synchronized Database getInstance() throws SQLException {
    if (INSTANCE == null) {
      INSTANCE = new Database();
    }
    return INSTANCE;
  }

  /**
   * Получить все записи
   * @return List всех записей
   */
  public List<Record> getAllRecords() {
    try (Statement statement = this.connection.createStatement()) {
      List<Record> records = new ArrayList<Record>();
      ResultSet resultSet = statement.executeQuery("SELECT id, item, owner, num, expiry_date, owner_type, contact FROM storage");
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String item = resultSet.getString("item");
        String owner = resultSet.getString("owner");
        int num = resultSet.getInt("num");
        String dateString = resultSet.getString("expiry_date");
        String ownerType = resultSet.getString("owner_type");
        String contact = resultSet.getString("contact");

        if (Objects.equals(dateString, "Бессрочно")){
          records.add(new Record(id, item, num, owner, ownerType, contact ));
        } else {
          records.add(new ExpiringRecord(id, item, num, owner, ownerType, contact, dateString));
        }
      }
      return records;
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }


  /**
   * Получить запись по id
   * @param id id записи
   * @return запись с id
   */
  public Record getRecordById(int id) {
    try (PreparedStatement statement = this.connection.prepareStatement("SELECT item, owner, num, expiry_date, owner_type, contact FROM storage WHERE id = ?")) {
      statement.setObject(1, id);
      ResultSet resultSet = statement.executeQuery();
      String item = resultSet.getString("item");
      String owner = resultSet.getString("owner");
      int num = resultSet.getInt("num");
      String dateString = resultSet.getString("expiry_date");
      String ownerType = resultSet.getString("owner_type");
      String contact = resultSet.getString("contact");

      Record record;
      if (Objects.equals(dateString, "Бессрочно")) {
        record = new Record(id, item, num, owner, ownerType, contact);
      } else {
        record = new ExpiringRecord(id, item, num, owner, ownerType, contact, dateString);
      }
      return record;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Получить List записей с истекшим сроком хранения
   * @return List записей с истекшим сроком хранения
   */
  public List<Record> getRecordsExpired() {
    List<Record> records = getAllRecords();
    List<Record> ExpiredRecords = new ArrayList<Record>();
    for (Record record : records) {
      if (record.getExpiryString() != "Бессрочно"){
        ExpiringRecord expiringRecord = (ExpiringRecord) record;
        if (expiringRecord.expiryCheck()) ExpiredRecords.add(expiringRecord);
      }
    }
    return ExpiredRecords;
  }

  /**
   * Получить List записей с совпадающим названием предмета хранения
   * @param item название предмета хранения
   * @return List записей с совпадающим названием предмета хранения
   */
  public List<Record> getRecordsWithItem(String item) {
    List<Record> records = new ArrayList<Record>();
    try (PreparedStatement statement = this.connection.prepareStatement("SELECT id, owner, num, expiry_date, owner_type, contact FROM storage WHERE item = ?")) {
      statement.setObject(1, item);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String owner = resultSet.getString("owner");
        int num = resultSet.getInt("num");
        String dateString = resultSet.getString("expiry_date");
        String ownerType = resultSet.getString("owner_type");
        String contact = resultSet.getString("contact");

        if (Objects.equals(dateString, "Бессрочно")) {
          records.add(new Record(id, item, num, owner, ownerType, contact));
        } else {
          records.add(new ExpiringRecord(id, item, num, owner, ownerType, contact, dateString));
        }
      }
      return records;
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  /**
   * Получить List записей с совпадающим владельцем
   * @param owner название/имя владельца
   * @return List записей с совпадающим владельцем
   */
  public List<Record> getRecordsWithOwner(String owner) {
    List<Record> records = new ArrayList<Record>();
    try (PreparedStatement statement = this.connection.prepareStatement("SELECT id, item, num, expiry_date, owner_type, contact FROM storage WHERE owner = ?")) {
      statement.setObject(1, owner);
      ResultSet resultSet = statement.executeQuery();
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        String item = resultSet.getString("item");
        int num = resultSet.getInt("num");
        String dateString = resultSet.getString("expiry_date");
        String ownerType = resultSet.getString("owner_type");
        String contact = resultSet.getString("contact");

        if (Objects.equals(dateString, "Бессрочно")) {
          records.add(new Record(id, item, num, owner, ownerType, contact));
        } else {
          records.add(new ExpiringRecord(id, item, num, owner, ownerType, contact, dateString));
        }
      }
      return records;
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  /**
   * Получить List id всех записей
   * @return List id всех записей
   */
  public List<Integer> getAllRecordsId() {
    try (Statement statement = this.connection.createStatement()) {
      List<Integer> recordsId = new ArrayList<Integer>();
      ResultSet resultSet = statement.executeQuery("SELECT id FROM storage");
      while (resultSet.next()) {
        int id = resultSet.getInt("id");
        recordsId.add(id);
      }
      return recordsId;
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

  /**
   * Добавить данные записи в базу данных
   * @param record объект записи
   */
  public void addRecord(Record record) {
    try (PreparedStatement statement = this.connection.prepareStatement(
            "INSERT INTO storage('item', 'owner', 'num', 'expiry_date', 'owner_type', 'contact') " +
                    "VALUES(?, ?, ?, ?, ?, ?)")) {
      statement.setObject(1, record.getItem());
      statement.setObject(2, record.getOwner());
      statement.setObject(3, record.getQuantity());
      statement.setObject(4, record.getExpiryString());
      statement.setObject(5, record.getOwnerType());
      statement.setObject(6, record.getContact());
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Удалить запись из базы данных по id
   * @param id id, удаляемой записи
   */
  public void deleteRecord(int id) {
    try (PreparedStatement statement = this.connection.prepareStatement(
            "DELETE FROM storage WHERE id = ?")) {
      statement.setObject(1, id);
      statement.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
