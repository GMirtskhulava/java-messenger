package org.example.javamessenger;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Scanner;

public class MySQLConfig implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final String CONFIG_FILE = "mysql.ser";

    private String port;
    private String username;
    private String password;


    public MySQLConfig(String username, String password) {
        this("3306", username, password);
    }
    public MySQLConfig(String port, String username, String password) {
        setPort(port);
        setUsername(username);
        setPassword(password);
    }


    public static MySQLConfig load() throws IOException {
        if(Serialization.fileExists(CONFIG_FILE)) {
            try {
                MySQLConfig config = (MySQLConfig) Serialization.deserializeObject(CONFIG_FILE);
                if(isValidPort(config.getPort()) && config.getUsername() != null && !config.getUsername().isEmpty() && config.getPassword() != null) return config;
                System.out.println("[MySQLConfig/Error]Saved MySQL configuration is invalid.");
            } catch (ClassNotFoundException | ClassCastException exception) {
                System.out.println("[MySQLConfig/Error] Could not read MySQL configuration: " + exception.getMessage());
            }
        } else {
            System.out.println("[MySQLConfig/Error] MySQL configuration was not found.");
        }

        Scanner scanner = new Scanner(System.in);

        System.out.print("[MySQLConfig/Setup] MySQL port [3306]: ");
        String port = scanner.nextLine();
        if(port.isEmpty()) port = "3306";

        while(!isValidPort(port)) {
            System.out.print("[MySQLConfig/Setup] Enter a valid port (1-65535): ");
            port = scanner.nextLine().trim();
        }

        System.out.print("[MySQLConfig/Setup] MySQL username: ");
        String username = scanner.nextLine().trim();
        while(username.isEmpty()) {
            System.out.print("[MySQLConfig/Setup] MySQL username cannot be empty: ");
            username = scanner.nextLine().trim();
        }

        System.out.print("[MySQLConfig/Setup] MySQL password: ");
        String password = scanner.nextLine();

        MySQLConfig config = new MySQLConfig(port, username, password);
        Serialization.serializeObject(config, CONFIG_FILE);

        System.out.println("[MySQLConfig/Setup] MySQL configuration saved in data/" + CONFIG_FILE);
        return config;
    }


    private static boolean isValidPort(String port) {
        if(port == null || port.isEmpty()) return false;

        try {
            int portNumber = Integer.parseInt(port);
            return portNumber >= 1 && portNumber <= 65535;
        }
        catch(NumberFormatException exception) {
            return false;
        }
    }

    public String getPort() {
        return port == null || port.isEmpty() ? "3306" : port;
    }
    public void setPort(String port) {
        this.port = port == null || port.isEmpty() ? "3306" : port;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password == null ? "" : password;
    }
}
