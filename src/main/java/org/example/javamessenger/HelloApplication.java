package org.example.javamessenger;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class HelloApplication extends Application {
    private boolean mysqlLoaded = false;

    @Override
    public void init() {
        try {
            MySQL.initialize(MySQLConfig.load());
            mysqlLoaded = true;
        } catch (SQLException e) {
            System.out.println("[MySQL Init/ERR]\t" + e.getMessage());
        } catch (IOException e) {
            System.out.println("[MySQL Init/-IO]\t" + e.getMessage());
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        if(!mysqlLoaded) {
            System.out.println("[Application/STOP] MySQL was not loaded. Application stopped.");
            Platform.exit();
            return;
        }

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        MySQL.closeConnection();
    }
}
