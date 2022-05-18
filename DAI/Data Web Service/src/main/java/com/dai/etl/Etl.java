package com.dai.etl;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;

@Profile("prod")
@Component
public class Etl {

    @Value("${database.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String dbUsername;

    @Value("${spring.datasource.password}")
    private String dbPassword;

    @Scheduled(cron = "0 38 14 ? * *")
    @Async
    public void performEtl() {
        System.out.println("ETL Triggered");
        try {
            Connection c = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            if (c == null)
                return;

            Reader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("Etl.sql")));
            ScriptRunner sr = new ScriptRunner(c);
            sr.setSendFullScript(true);
            sr.runScript(reader);
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
