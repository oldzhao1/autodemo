package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.GetUserInfoCase;
import com.tester.model.InterfaceEnum;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtils;
import com.tester.utils.GetCookies;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class GetUserInfo {

    @BeforeTest
    public static void beforeTest() throws IOException {
        GetCookies.getCookiesAndToken();
    }

    @Test
    public static void getUserInfoTest() throws IOException {
        SqlSession session = DatabaseUtils.getSqlsession();
        GetUserInfoCase getUserInfoCase = session.selectOne("getUserInfo", 7);
        String result = getResult(getUserInfoCase);
        Assert.assertEquals(result, getUserInfoCase.getExpected());

    }


    public static String getResult(GetUserInfoCase getUserInfoCase) throws IOException {

        //获取接口地址
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceEnum.GETUSERINFO);
        //创建请求
        HttpPost post = new HttpPost(TestConfig.getUserInfoUrl);
        //设置请求头
        post.setHeader("Content-Type", "application/json");
        //设置cookies
        List<Cookie> cookies = TestConfig.cookies;
        for (Cookie cookie : TestConfig.cookies) {
            post.setHeader("Cookie", cookie.getName() + "=" + cookie.getValue());
        }
        //添加请求参数
        JSONObject param = new JSONObject();
        param.put("id", getUserInfoCase.getId());
        StringEntity entity = new StringEntity(param.toString(), Charset.defaultCharset());
        post.setEntity(entity);
        //执行请求
        HttpResponse response = TestConfig.httpclient.execute(post);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }


}
