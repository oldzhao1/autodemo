package com.tester.cases;

import com.tester.config.TestConfig;
import com.tester.model.InterfaceEnum;
import com.tester.model.LoginCase;
import com.tester.utils.ConfigFile;
import com.tester.utils.DatabaseUtils;
import com.tester.utils.GetCookies;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.session.SqlSession;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;

public class LoginTest {

    @BeforeTest(groups = "login", description = "登录功能测试")
    public void beforeTest() throws IOException {
        TestConfig.getUserInfoUrl = ConfigFile.getUrl(InterfaceEnum.GETUSERINFO);
        TestConfig.updateUserUrl = ConfigFile.getUrl(InterfaceEnum.UPDATEUSER);
        TestConfig.addUserUrl = ConfigFile.getUrl(InterfaceEnum.ADDUSER);
        TestConfig.loginUrl = ConfigFile.getUrl(InterfaceEnum.LOGIN);
        TestConfig.addTaskUrl = ConfigFile.getUrl(InterfaceEnum.ADDTASK);
        TestConfig.updateTaskUrl = ConfigFile.getUrl(InterfaceEnum.UPDATETASK);
        TestConfig.getTaskUrl = ConfigFile.getUrl(InterfaceEnum.GETTASK);
        GetCookies.getCookiesAndToken();
    }

    @Test(groups = "login", description = "登录成功")
    public static void TestAddUserTrue() throws IOException {
        //sql执行对象
        SqlSession session = DatabaseUtils.getSqlsession();
        //获取loginCase表里id=1的数据，执行请求
        LoginCase loginCase = session.selectOne("loginCase", 1);
        System.out.println(loginCase.toString());
        System.out.println(TestConfig.loginUrl);
        String result = getResult(loginCase);
        Assert.assertEquals(result, loginCase.getExpected());
    }

    @Test(groups = "login", description = "登录失败")
    public static void TestAddUserFalse() throws IOException {
        //创建sql执行对象
        SqlSession sqlSession = DatabaseUtils.getSqlsession();
        //获取loginCase中id=2的数据
        LoginCase loginCase = sqlSession.selectOne("loginCase", 2);
        //使用id=2的数据进行请求
        String result = getResult(loginCase);
        Assert.assertEquals(result, loginCase.getExpected());

    }


    private static String getResult(LoginCase logincase) throws IOException {
        //创建post请求
        HttpPost post = new HttpPost(TestConfig.loginUrl);
        //设置入参
        JSONObject param = new JSONObject();
        param.put("name", logincase.getName());
        param.put("passwd", logincase.getPasswd());
        //设置请求头信息
        post.setHeader("content-type", "application/json");
        //往请求中塞入参数
        StringEntity entity = new StringEntity(param.toString(), Charset.defaultCharset());
        post.setEntity(entity);
        //执行post请求
        HttpResponse response = TestConfig.httpclient.execute(post);
        //获取响应结果
        String result;
        result = EntityUtils.toString(response.getEntity(), "utf-8");
        System.out.println(result);
        return result;
    }
}