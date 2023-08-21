package com.tester.utils;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceEnum;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import static com.tester.config.TestConfig.cookieStore;

public class GetCookies {

    public static void getCookiesAndToken() throws IOException {
        //获取请求地址
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceEnum.LOGIN);
        //创建登录请求
        HttpPost loginPost = new HttpPost(TestConfig.loginUrl);
        JSONObject param = new JSONObject();
        param.put("name", "zhangsan");
        param.put("passwd", "111111");
        //设置请求头
        loginPost.setHeader("Content-Type", "application/json");
        //添加参数
        StringEntity entity = new StringEntity(param.toString(), Charset.defaultCharset());
        loginPost.setEntity(entity);
        //执行请求,并传入一个存放cookies的context
        HttpResponse response = TestConfig.httpclient.execute(loginPost);
        String result = EntityUtils.toString(response.getEntity());
        //获取cookies
        List<Cookie> cookies = cookieStore.getCookies();
        //把cookies存储至配置类中的cookie中，方便后续接口直接取值
        TestConfig.cookies = cookies;

        //获取token
//        ObjectMapper objectMapper = new ObjectMapper();
//        AuthenticationResponse authResponse = objectMapper.readValue(result, AuthenticationResponse.class);
//        String token = authResponse.getToken();


    }


}

