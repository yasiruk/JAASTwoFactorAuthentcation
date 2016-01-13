package me.yasiru.jasstest.util;

import me.yasiru.jasstest.callbacks.SMSCallBack;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PushbackInputStream;
import java.util.Arrays;

/**
 * Created by wik2kassa on 12/18/15.
 */
public class TwoFactorCallBackHandler implements CallbackHandler {
    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        for(Callback callback : callbacks) {
            if(callback instanceof NameCallback ) {
                System.out.print(((NameCallback) callback).getPrompt());
                ((NameCallback) callback).setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            }
            if(callback instanceof PasswordCallback) {
                System.out.print(((PasswordCallback) callback).getPrompt());
                ((PasswordCallback) callback).setPassword(readPassword(System.in));
            }
            if(callback instanceof SMSCallBack) {
                ((SMSCallBack) callback).sendConfirmatinonCode();
                System.out.println(((SMSCallBack) callback).getPromt());
                int code = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
                ((SMSCallBack) callback).setUserInputCode(code);
            }
        }
    }
    private char[] readPassword(InputStream in) throws IOException {

        char[] lineBuffer;
        char[] buf;
        int i;

        buf = lineBuffer = new char[128];

        int room = buf.length;
        int offset = 0;
        int c;

        loop:	while (true) {
            switch (c = in.read()) {
                case -1:
                case '\n':
                    break loop;

                case '\r':
                    int c2 = in.read();
                    if ((c2 != '\n') && (c2 != -1)) {
                        if (!(in instanceof PushbackInputStream)) {
                            in = new PushbackInputStream(in);
                        }
                        ((PushbackInputStream)in).unread(c2);
                    } else
                        break loop;

                default:
                    if (--room < 0) {
                        buf = new char[offset + 128];
                        room = buf.length - offset - 1;
                        System.arraycopy(lineBuffer, 0, buf, 0, offset);
                        Arrays.fill(lineBuffer, ' ');
                        lineBuffer = buf;
                    }
                    buf[offset++] = (char) c;
                    break;
            }
        }

        if (offset == 0) {
            return null;
        }

        char[] ret = new char[offset];
        System.arraycopy(buf, 0, ret, 0, offset);
        Arrays.fill(buf, ' ');

        return ret;
    }
}
