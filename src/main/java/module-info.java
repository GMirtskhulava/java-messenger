module org.example.javamessenger {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;


    opens org.example.javamessenger to javafx.fxml;
    exports org.example.javamessenger;
}
