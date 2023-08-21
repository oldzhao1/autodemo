package com.tester.utils;

import com.tester.model.InterfaceEnum;

import java.util.Locale;
import java.util.ResourceBundle;

public class ConfigFile {

    private static ResourceBundle bundle = ResourceBundle.getBundle("application", Locale.CHINA);

    public static String getUrl(InterfaceEnum interfaceEnum) {
        String ad = bundle.getString("test.url");
        String uri = "";
        String testUrl;
        if (InterfaceEnum.LOGIN == interfaceEnum) {
            uri = bundle.getString("login.url");
        }
        if (InterfaceEnum.ADDUSER == interfaceEnum) {
            uri = bundle.getString("addUser.url");
        }
        if (InterfaceEnum.GETUSERINFO == interfaceEnum) {
            uri = bundle.getString("getUser.url");
        }
        if (InterfaceEnum.UPDATEUSER == interfaceEnum) {
            uri = bundle.getString("updateUser.url");
        }
        if (InterfaceEnum.ADDTASK == interfaceEnum) {
            uri = bundle.getString("addTask.url");
        }
        if (InterfaceEnum.UPDATETASK == interfaceEnum) {
            uri = bundle.getString("updateTask.url");
        }
        if (InterfaceEnum.GETTASK == interfaceEnum) {
            uri = bundle.getString("getTask.url");
        }
        testUrl = ad + uri;
        return testUrl;
    }

}
