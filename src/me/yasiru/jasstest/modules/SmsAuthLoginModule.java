package me.yasiru.jasstest.modules;

import me.yasiru.jasstest.callbacks.SMSCallBack;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

/**
 * Created by wik2kassa on 12/17/15.
 */
public class SmsAuthLoginModule implements LoginModule {
    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map sharedState;
    private Map options;
    private boolean succeeded;
    @Override
    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState, Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
        this.succeeded = false;
    }

    @Override
    public boolean login() throws LoginException {

        //get and verify the code..

        Callback[] callbacks = new Callback[1];
        String username = (String) sharedState.get("USERNAME");

        callbacks[0] = new SMSCallBack(username);

        try {
            callbackHandler.handle(callbacks);

            SMSCallBack mySmsCallBack = (SMSCallBack)callbacks[0];
            if(mySmsCallBack.verifyConfirmationCode(mySmsCallBack.getUserInputCode()) == false) {
                succeeded = false;
                return false;
            }
            else {
                succeeded = true;
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedCallbackException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean commit() throws LoginException {
        return (succeeded);
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
