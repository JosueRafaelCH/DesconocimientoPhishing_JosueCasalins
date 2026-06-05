package com.example.SimulatorApp.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SchemaFixer {

    private static final Logger log = LoggerFactory.getLogger(SchemaFixer.class);

    private final JdbcTemplate jdbc;

    public SchemaFixer(DataSource dataSource) {
        this.jdbc = new JdbcTemplate(dataSource);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void fixSchema() {
        try {
            String tableName = getActualTableName();
            String fkName = findForeignKeyConstraint(tableName);

            if (fkName != null) {
                jdbc.execute("ALTER TABLE " + tableName + " DROP FOREIGN KEY " + fkName);
            }
            jdbc.execute("ALTER TABLE " + tableName + " MODIFY COLUMN id_test INT NULL");
            log.info("SchemaFixer: columna id_test ahora es nullable en {}", tableName);
        } catch (Exception e) {
            log.warn("SchemaFixer: {}", e.getMessage());
        }
    }

    private String getActualTableName() {
        try {
            return jdbc.queryForObject(
                "SELECT TABLE_NAME FROM information_schema.TABLES " +
                "WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND TABLE_NAME LIKE '%evento%simulacion%'",
                String.class
            );
        } catch (Exception e) {
            return "eventos_simulacion";
        }
    }

    private String findForeignKeyConstraint(String tableName) {
        try {
            return jdbc.queryForObject(
                "SELECT CONSTRAINT_NAME FROM information_schema.KEY_COLUMN_USAGE " +
                "WHERE TABLE_SCHEMA = (SELECT DATABASE()) AND TABLE_NAME = ? " +
                "AND COLUMN_NAME = 'id_test' AND REFERENCED_TABLE_NAME IS NOT NULL",
                String.class,
                tableName
            );
        } catch (Exception e) {
            return null;
        }
    }
}
