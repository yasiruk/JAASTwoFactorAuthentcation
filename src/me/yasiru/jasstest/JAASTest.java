package me.yasiru.jasstest;




import me.yasiru.jasstest.util.TwoFactorCallBackHandler;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import java.security.Principal;
import java.util.Iterator;

public class JAASTest {
    public static void main(String[] args) {
        LoginContext lc = null;

        try {
            lc = new LoginContext("TwoFactorLogin", new TwoFactorCallBackHandler());

        } catch (LoginException e) {
            e.printStackTrace();
        }

        try {
            lc.login();
            System.out.println("Successfully Logged in");

            Subject mySubject = lc.getSubject();


        } catch (LoginException e) {
            System.out.println("Login Failed.");
            e.printStackTrace();
        }
    }
}
