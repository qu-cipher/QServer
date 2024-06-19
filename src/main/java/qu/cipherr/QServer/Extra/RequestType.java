package qu.cipherr.QServer.Extra;

import java.io.*;
import java.net.*;

import qu.cipherr.QServer.Utils.Logger;

public class RequestType {
    public static String getType(Socket client){
        String type = "None";
        try {
            BufferedReader in = new BufferedReader( new InputStreamReader(client.getInputStream()));
            
            String requestLine = in.readLine();
            if (requestLine != null && requestLine.startsWith("GET")) {
                type = "GET";
            } else if (requestLine != null && requestLine.startsWith("POST")) {
                type = "POST";
            } else if (requestLine != null && requestLine.startsWith("PUT")) {
                type = "PUT";
            } else if (requestLine != null && requestLine.startsWith("DELETE")) {
                type = "DELETE";
            } else {
                type = "Unknown";
            }
            
        } catch (IOException e) {
            Logger.err(e.getMessage() + " : " + e.getCause()); // Error message and cause
        }

        return type;
    }
}
