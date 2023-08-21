package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.AddUserCase;
import com.tester.model.InterfaceEnum;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtils;
import com.tester.utils.GetCookies;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class AddUserTest {

    @BeforeTest
    public static void beforTest() throws IOException {
        GetCookies.getCookiesAndToken();
    }

    @Test
    public static void addUserTest() throws IOException {

        SqlSession session = DatabaseUtils.getSqlsession();
        //获取测试case
        AddUserCase addUserCase = session.selectOne("addUserCase", 1);
        System.out.println(addUserCase.toString());
        //执行测试case
        String result = getResult(addUserCase);
        Assert.assertEquals(result, addUserCase.getExpected());

    }


    public static String getResult(AddUserCase addUserCase) throws IOException {
        //获取添加用户地址
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceEnum.ADDUSER);
        //创建post请求
        HttpPost post = new HttpPost(TestConfig.addUserUrl);
        //添加请求参数
        JSONObject param = new JSONObject();
        param.put("name", addUserCase.getName());
        param.put("passwd", addUserCase.getPasswd());
        //设置请求头信息
        post.setHeader("Content-Type", "application/json");
        //设置cooKies信息
        List<Cookie> cookies = TestConfig.cookies;
        for (Cookie cookie : TestConfig.cookies) {
            post.setHeader("Cookie", cookie.getName() + "=" + cookie.getValue());
        }
        //请求参数装入请求体
        StringEntity entity = new StringEntity(param.toString(), Charset.defaultCharset());
        post.setEntity(entity);
        //执行请求
        HttpResponse response = TestConfig.httpclient.execute(post);
        String result = EntityUtils.toString(response.getEntity());

        return result;
    }
}
