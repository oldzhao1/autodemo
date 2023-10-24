package com.tester.config;


import org.apache.http.client.CookieStore;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import java.util.List;

/**
 * 测试配置
 *
 * @author zz
 * @date 2023/09/24
 */
public class TestConfig {
    //登录接口&获取cookies接口
    public static String loginUrl;
    //更新用户接口
    public static String updateUserUrl;
    //获取用户信息接口
    public static String getUserInfoUrl;
    //添加用户接口
    public static String addUserUrl;
    //新增任务接口
    public static String addTaskUrl;
    //更新任务接口
    public static String updateTaskUrl;
    //获取任务详情接口
    public static String getTaskUrl;
    public static CookieStore store;
    //创建一个cookies存放对象
    public static CookieStore cookieStore = new BasicCookieStore();
    //创建一个执行客户端对象
    public static CloseableHttpClient httpclient = HttpClients.custom()
            .setDefaultCookieStore(cookieStore)
            .build();

    public static List<Cookie> cookies;


}
