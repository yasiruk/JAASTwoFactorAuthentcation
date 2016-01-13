package me.yasiru.jasstest.config;

import me.yasiru.jasstest.modules.BasicAuthLoginModule;
import me.yasiru.jasstest.modules.SmsAuthLoginModule;

import javax.security.auth.login.AppConfigurationEntry;
import javax.security.auth.login.Configuration;
import java.util.HashMap;

/**
 * Created by wik2kassa on 1/11/2016.
 */
public class LoginConfiguration extends Configuration {
    @Override
    public AppConfigurationEntry[] getAppConfigurationEntry(String name) {
        AppConfigurationEntry[] configs = new AppConfigurationEntry[2];
        configs[0] = new AppConfigurationEntry(BasicAuthLoginModule.class.getName(), AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, new HashMap<String, Object>());
        configs[1] = new AppConfigurationEntry(SmsAuthLoginModule.class.getName(), AppConfigurationEntry.LoginModuleControlFlag.REQUIRED, new HashMap<String, Object>());
        return configs;
    }
}
