package me.yasiru.jasstest.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * Created by wik2kassa on 12/18/15.
 */
public class SMSSimulator {
    private static final int PORT= 8000;
    private static final String HOST = "localhost";
    private static HashMap<String, Integer> map;

    public SMSSimulator() {
        if(map == null)
            map = new HashMap<>();
    }

    public void sendConfirmationCode(String username) throws IOException {
        Socket outputSocket = new Socket(HOST, PORT);
        PrintWriter socketWriter= new PrintWriter(outputSocket.getOutputStream());
        Integer randomCode = map.get(username) ;
        if(randomCode== null){
            randomCode = getRandomCode();
        }

        map.put(username, randomCode);
        socketWriter.println("Code for " + username + " is " + randomCode);
        socketWriter.flush();
    }
    public int getConfirmationCode(String username) {
        return map.get(username);
    }
    private int getRandomCode(){
        return (int)(Math.random() * 1000);
    }
}
