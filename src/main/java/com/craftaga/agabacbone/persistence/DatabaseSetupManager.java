package com.craftaga.agabacbone.persistence;

import com.jolbox.bonecp.BoneCPDataSource;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * description
 *
 * @author Jonathan
 * @since 13/07/2014
 */
public class DatabaseSetupManager implements IDatabaseSetupManager {
    private BoneCPDataSource dataSource;
    private FileConfiguration configuration;
    private JavaPlugin plugin;
    private static final String SQL_EXTENSION = ".sql";
    private static final String STATEMENT_DIRECTORY = "/mysql/statements/";
    private static final String SETUP_SCRIPT_V1 = "Setup.1";

    public DatabaseSetupManager(FileConfiguration config, BoneCPDataSource dataSource, JavaPlugin plugin) {
        this.configuration = config;
        this.dataSource = dataSource;
        this.plugin = plugin;
    }

    @Override
    public boolean CheckAndSetup() {
        if(configuration.getConfigurationSection("database").getInt("version") < 1) {
            if (this.getClass().getResource(STATEMENT_DIRECTORY + SETUP_SCRIPT_V1 + SQL_EXTENSION) != null) {
                Connection connection = null;
                Statement statement = null;
                try {
                    connection = dataSource.getConnection();
                    ScriptRunner scriptRunner = new ScriptRunner(connection);
                    scriptRunner.setLogWriter(null);
                    scriptRunner.setErrorLogWriter(null);
                    scriptRunner.setAutoCommit(true);
                    scriptRunner.runScript(new InputStreamReader(this.getClass().getResourceAsStream(STATEMENT_DIRECTORY + SETUP_SCRIPT_V1 + SQL_EXTENSION)));
                    configuration.set("database.version", "1");
                    plugin.saveConfig();
                } catch (SQLException e) {
                    plugin.getLogger().info("Error in Database Setup SQL in " + this.getClass().getCanonicalName());
                    plugin.getLogger().info(ExceptionUtils.getStackTrace(e));
                    return false;
                }
            } else {
                plugin.getLogger().info("Unable to find database setup script in " + this.getClass().getCanonicalName());
                return false;
            }
        }
        return true;
    }
}
