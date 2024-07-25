package com.example.pr13_3;

import java.util.Date;

/**
 * Интерфейс для классов с крайним сроком
 */
public interface Expiring {

  /**
   * Получить дату крайнего срока
   */
  public Date getExpiryDate();

  /**
   * Проверка на истечение срока
   */
  public boolean expiryCheck();
}
