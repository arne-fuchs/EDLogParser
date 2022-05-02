package de.paesserver.journalLog;

public class DatabaseSingleton {
    private static Database database;

    public static Database getInstance(){
        if(database == null)
            database = new Database();
        return database;
    }
}
