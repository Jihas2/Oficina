package com.oficina.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Path;

public class DBUtil {
    private static final String URL = "jdbc:h2:./data/oficina_db;AUTO_SERVER=TRUE";
    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try {
            initDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private static void initDatabase() throws Exception {
        Path p = Path.of("schema.sql");
        if (!Files.exists(p)) return;
        String schema = new String(Files.readAllBytes(p));
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            for (String stmt : schema.split(";")) {
                if (stmt.trim().isEmpty()) continue;
                st.execute(stmt);
            }
        }
    }
}
