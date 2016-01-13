package me.yasiru.jasstest.modules;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by wik2kassa on 12/21/15.
 */
public class BasicAuthLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;
    private static String USERNAME = "root";
    private static char[] PASSWORD = {'j', 'a', 'a', 's'};
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    @Override
    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("Enter username: ");
        PasswordCallback passwordCallback = new PasswordCallback("Enter Password: ", true);

        Callback[] callbacks = new Callback[2];

        callbacks[0] = nameCallback;
        callbacks[1] = passwordCallback;

        try {
            callbackHandler.handle(callbacks);
            if(USERNAME.equals(nameCallback.getName()) && Arrays.equals(PASSWORD, passwordCallback.getPassword())) {
                sharedState.put("USERNAME", nameCallback.getName());
                return true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean commit() throws LoginException {
        return true;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
