package org.example;

import org.example.DatabaseConfig;
import org.example.DatabaseInitializer;
import org.example.FilterBlankProcessor;
import org.example.StatisticsProcessor;
import org.example.TrimProcessor;
import org.example.UpperCaseProcessor;
import org.example.DataManager;

public class Main {
    public static void main(String[] args) {
        DatabaseConfig databaseConfig = DatabaseConfig.fromSystemProperties();
        DatabaseInitializer initializer = new DatabaseInitializer(databaseConfig);
        initializer.init();
        initializer.seedSourceDataIfEmpty();

        DataManager dataManager = new DataManager(databaseConfig);
        dataManager.registerDataProcessor(new TrimProcessor());
        dataManager.registerDataProcessor(new FilterBlankProcessor());
        dataManager.registerDataProcessor(new UpperCaseProcessor());
        dataManager.registerDataProcessor(new StatisticsProcessor());

        dataManager.loadData("source_data");
        dataManager.processData();
        dataManager.saveData("processed_data");

        System.out.println("Обработка завершена.");
        System.out.println("Результат сохранён в таблицу processed_data.");
    }
}

