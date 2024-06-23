package qu.cipherr.QServer.Utils;

import qu.cipherr.QServer.Extra.ColorLog;
import qu.cipherr.QServer.Extra.TimeStamp;

public class Logger {
    public static void in(String data){
        System.out.println(ColorLog.GREEN + TimeStamp.getTimestamp() + " [INFO] " + data + ColorLog.RESET);
    }

    public static void err(String data){
        System.out.println(ColorLog.RED + TimeStamp.getTimestamp() +  " [ERROR] " + data + ColorLog.RESET);
    }

    public static void debug (String data){
        System.out.println(ColorLog.PURPLE + TimeStamp.getTimestamp() +  " [DEBUG] " + data + ColorLog.RESET);
    }

    public static void warn (String data){
        System.out.println(ColorLog.YELLOW + TimeStamp.getTimestamp() +  " [WARNING] " + data + ColorLog.RESET);
    }
}