module com.example.pr13_3 {
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.web;

  requires org.controlsfx.controls;
  requires com.dlsc.formsfx;
  //requires validatorfx;
  requires org.kordamp.ikonli.javafx;
  requires org.kordamp.bootstrapfx.core;
  requires eu.hansolo.tilesfx;
  requires com.almasb.fxgl.all;
  requires org.xerial.sqlitejdbc;

  opens com.example.pr13_3 to javafx.fxml;
  exports com.example.pr13_3;
}