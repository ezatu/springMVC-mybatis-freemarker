package ltd.cracks.web.action;

import ltd.cracks.service.front.user.User;
import ltd.cracks.service.front.user.UserDao;
import ltd.cracks.service.front.user.UserService;
import ltd.cracks.util.*;
import ltd.cracks.service.mongo.mongoService;
import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.Documented;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by macos on 2017/4/14.
 */
@Controller
public class test extends HttpServlet {

    @Autowired
    private mongoService mongoService;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(test.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws Exception {
        logger.info("start request");
        System.out.print(logger.isDebugEnabled());
        logger.debug("dsfjaslfkasdkfjdasfjdasjfslkajfdasl");
        ArrayList<Document> list = mongoService.findDocuments("test");
        String code = request.getParameter("code");
        System.out.println(code);
        map.put("list",list);
        return "index";
    }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView view = new ModelAndView("hello");
        ArrayList<Document> list = mongoService.findDocuments("test");
        view.addObject("data",list);
        User user = new User();
        user.setUserName("ceshi123");
        user.setAge("123");
        user.setId(10);
        user.setInsertTime(new Timestamp(System.currentTimeMillis()));
        userService.insert(user);
        User user1 = userService.findById(1);
        user1.setAge("qqq");
        userService.update(user1);
        userService.delete(user1.getId());
        List<User> users = userService.findAll();
        return view;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
        byte[] body = uploadUitl.readBody(request);
        String textBody = new String(body, "ISO-8859-1");
        String filename = uploadUitl.getFilename(textBody);
        uploadUitl.Position p = uploadUitl.getFilePosition(request, textBody);
        uploadUitl.writeTo(filename, body, p);

        /**
         * 设置返回类型
         */
        response.setContentType("text/html");
        /**
         * 设置字符集 避免乱码 和页面字符集保持一致
         */
        response.setCharacterEncoding("UTF-8");

        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<10;i ++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",200);
            jsonObject.put("result",i);
            jsonObject.put("status",i+"success");
            jsonArray.put(jsonObject);
        }

        // 返回结果
        response.getWriter().write(jsonArray.toString());
        response.getWriter().flush();
        response.getWriter().close();
    }

    // 测试用例
    public static void main(String[] args) {
                 Logger logger = LoggerFactory.getLogger(test.class);
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");             }

}
