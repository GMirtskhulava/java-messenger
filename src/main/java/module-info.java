module org.example.javamessenger {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.javamessenger to javafx.fxml;
    exports org.example.javamessenger;
}