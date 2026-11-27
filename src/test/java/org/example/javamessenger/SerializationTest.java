package org.example.javamessenger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SerializationTest {
    private static final String TEST_FILE = "mysql-config-test.ser";

    @AfterEach
    void removeTestFile() {
        File file = new File("data", TEST_FILE);
        file.delete();
    }

    @Test
    void serializesAndDeserializesConfig() throws Exception {
        MySQLConfig expected = new MySQLConfig("3307", "messenger", "secret");

        Serialization.serializeObject(expected, TEST_FILE);
        MySQLConfig actual = (MySQLConfig) Serialization.deserializeObject(TEST_FILE);

        assertEquals(expected.getPort(), actual.getPort());
        assertEquals(expected.getUsername(), actual.getUsername());
        assertEquals(expected.getPassword(), actual.getPassword());
    }
}
