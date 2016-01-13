package me.yasiru.jasstest.callbacks;

import me.yasiru.jasstest.util.SMSSimulator;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.NameCallback;
import java.io.IOException;

/**
 * Created by wik2kassa on 12/17/15.
 */
public class SMSCallBack implements Callback {
    private String username;
    SMSSimulator smsSimulator;
    private int userInputCode;

    public SMSCallBack(String username) {
        this.username = username;
    }

    public void sendConfirmatinonCode() throws IOException {
        smsSimulator = new SMSSimulator();
        smsSimulator.sendConfirmationCode(username);
    }

    public boolean verifyConfirmationCode(int code) {
        if (smsSimulator.getConfirmationCode(username) == code) {
            return true;
        } else
            return false;
    }

    public String getPromt() {
        return "Enter the confirmation code for user " + username + ": ";
    }

    public int getUserInputCode() {
        return userInputCode;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserInputCode(int userInputCode) {
        this.userInputCode = userInputCode;
    }
}
