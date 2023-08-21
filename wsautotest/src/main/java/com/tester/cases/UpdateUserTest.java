package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceEnum;
import com.tester.model.UpdateUser;
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

public class UpdateUserTest {

    @BeforeTest
    public static void beforeTest() throws IOException {
        GetCookies.getCookiesAndToken();
    }


    @Test(description = "添加用户测试")
    public static void updateUserTest() throws IOException {
        SqlSession session = DatabaseUtils.getSqlsession();
        UpdateUser updateUser = session.selectOne("updateUserCase", 12);
        System.out.println(updateUser.toString());
        String result = getResult(updateUser);
        Assert.assertEquals(result, updateUser.getExpected());

    }

    public static String getResult(UpdateUser updateUser) throws IOException {
        //获取更新请求地址
        TestConfig.updateUserUrl = ConfigFile.getUrl(InterfaceEnum.UPDATEUSER);
        //创建请求
        HttpPost post = new HttpPost(TestConfig.updateUserUrl);
        //post添加请求头
        post.setHeader("Content-Type", "application/json");
        //如果有token，通过登录获取token，并将token存储到测试配置类中的token，再此进行设置
        //post.setHeader("token","TestConfig.token");
        //设置请求的cookies
        List<Cookie> cookies = TestConfig.cookies;
        for (Cookie cookie : TestConfig.cookies) {
            post.setHeader("Cookie", cookie.getName() + "=" + cookie.getValue());
        }
        //设置请求的入参
        JSONObject param = new JSONObject();
        param.put("name", updateUser.getName());
        param.put("passwd", updateUser.getPasswd());
        param.put("id", updateUser.getId());
        //创建请求体
        StringEntity entity = new StringEntity(param.toString(), Charset.defaultCharset());
        post.setEntity(entity);
        //执行请求
        HttpResponse response = TestConfig.httpclient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }
}
