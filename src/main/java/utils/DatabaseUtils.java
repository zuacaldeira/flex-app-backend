/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author zua
 */
public class DatabaseUtils {

    private static DatabaseUtils instance;
    
    private DatabaseUtils() {
    }

    public static DatabaseUtils getInstance() {
        if (instance == null) {
            instance = new DatabaseUtils();
        }
        return instance;
    }

    public String wrapUp(String string) {
        if (string == null) {
            return null;
        }

        if (string.isEmpty()) {
            return "";
        }
        
        if(string.contains("\'")) {
            string = string.replace("\'", "`");
        }
        return "'" + string + "'";
    }

}
