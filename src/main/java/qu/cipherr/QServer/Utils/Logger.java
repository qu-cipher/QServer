package qu.cipherr.QServer.Utils;

public class Logger {
    public static void in(String data){
        System.out.println("[INFO] " + data);
    }

    public static void err(String data){
        System.out.println("[ERROR] " + data);
    }

    public static void debug (String data){
        System.out.println("[DEBUG] " + data);
    }

    public static void warn (String data){
        System.out.println("[WARNING] " + data);
    }
}