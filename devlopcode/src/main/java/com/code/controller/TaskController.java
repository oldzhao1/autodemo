package com.code.controller;

import com.code.model.Task;
import com.code.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Objects;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@RestController
@Api(value = "v1", tags = "taskapi")
public class TaskController {

    @Autowired
    private SqlSessionTemplate template;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录接口", httpMethod = "POST")
    public Boolean login(HttpServletResponse response, @RequestBody User user) {
        int i = template.selectOne("login", user);
        Cookie cookie = new Cookie("login", "true");
        response.addCookie(cookie);
        LOGGER.info("查询到的结果是" + i);
        if (i == 1) {
            LOGGER.info("登录的用户是：" + user.getName());
            return true;
        }
        return false;
    }

    @ApiOperation(value = "添加用户", httpMethod = "POST")
    @RequestMapping(value = "/add/user", method = RequestMethod.POST)
    public Boolean addUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = vifeCookies(request);
        int result = 0;
        if (x) {
            result = template.insert("addUser", user);
        }
        if (result > 0) {
            LOGGER.info("添加的用户" + request);
            return true;
        }
        return false;
    }

    private Boolean vifeCookies(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (Objects.isNull(cookies)) {
            return false;
        }
        for (Cookie cookie :
                cookies) {
            if (cookie.getName().equals("login") && cookie.getValue().equals("true")) {
                LOGGER.info("验证通过");
                return true;
            }
        }
        LOGGER.info("验证失败");
        return false;
    }

    @ApiOperation(value = "获取用户列表", httpMethod = "POST")
    @RequestMapping(value = "get/user/info", method = RequestMethod.POST)
    public List<User> getUserInfo(HttpServletRequest request, @RequestBody User user) {
        Boolean x = vifeCookies(request);
        if (x) {
            List<User> users = template.selectList("getUserInfo", user);
            LOGGER.info("获取到的用户是：" + users);
            return users;
        } else {
            return null;
        }
    }


    @ApiOperation(value = "更新用户", httpMethod = "POST")
    @RequestMapping(value = "/update/user", method = RequestMethod.POST)
    public int updateUser(HttpServletRequest request, @RequestBody User user) {
        Boolean x = vifeCookies(request);
        int i = 0;
        if (x) {
            i = template.update("updateUser", user);
        }
        LOGGER.info("更新用户数的条目为：" + i);
        return i;
    }


    @ApiOperation(value = "增加任务计划", httpMethod = "POST")
    @RequestMapping(value = "add/task", method = RequestMethod.POST)
    public int addTask(HttpServletRequest request, @RequestBody Task task) {
        Boolean x = vifeCookies(request);
        int i = 0;
        if (x) {
            i = template.insert("addTask", task);
        }
        LOGGER.info("任务计划添加成功：" + i);
        return i;
    }


    @ApiOperation(value = "更新任务计划", httpMethod = "POST")
    @RequestMapping(value = "/update/task", method = RequestMethod.POST)
    public int updateTask(HttpServletRequest request, @RequestBody Task task) {
        Boolean x = vifeCookies(request);
        int i = 0;
        if (x) {
            i = template.update("updateTask", task);
        }
        LOGGER.info("更新的记录条数为：" + i);
        return i;
    }

    @ApiOperation(value = "获取task列表", httpMethod = "POST")
    @RequestMapping(value = "/get/task", method = RequestMethod.POST)
    public List<Task> getTaskInfo(HttpServletRequest request, @RequestBody Task task) {
        Boolean x = vifeCookies(request);
        if (x) {
            List<Task> tasks = template.selectList("getTaskInfo", task);
            LOGGER.info("获取到的task列表为：" + tasks);
            return tasks;
        } else {
            return null;
        }

    }

}
