package ltd.cracks.web.action.index;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.BucketInfo;
import ltd.cracks.core.util.uploadUitl;
import ltd.cracks.service.front.product.Product;
import ltd.cracks.service.front.product.ProductService;
import ltd.cracks.service.front.user.User;
import ltd.cracks.service.front.user.UserService;
import ltd.cracks.service.front.user.UserServiceImpl;
import ltd.cracks.service.mongo.mongoService;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.TransactionManager;
import java.io.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by macos on 2017/4/14.
 */
@Controller
public class IndexAction {

    // 注入阿里云OSSClient管理实例
//    @Autowired
//    private OSSClient ossClient;
    // 注入mongoService实例
    @Autowired
    private mongoService mongoService;
    // 注入userService实例
    @Autowired
    private UserServiceImpl userService;
    // 注入productService实例
    @Autowired
    private ProductService productService;
    // 日志实例
    private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map) throws Exception {
        logger.info("start request");
        ArrayList<Document> list = mongoService.findDocuments("test");
        String code = request.getParameter("code");
        System.out.println(code);
        map.put("list",list);

//        BucketInfo bucketInfo = ossClient.getBucketInfo("cracks");
//        ossClient.putObject("cracks","filename.docx",new File("/Users/macos/Desktop/icity_icp.docx"));

        return "index";
    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public ModelAndView status(HttpServletRequest request, HttpServletResponse response) {
            ModelAndView view = new ModelAndView("status");
            Document document = new Document();
            document.put("beian","success");
            document.put("nadi","failure");
            view.addObject("data",document);
            return view;
        }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public ModelAndView hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView view = new ModelAndView("hello");
        ArrayList<Document> list = mongoService.findDocuments("test");
        view.addObject("data",list);
        // 测试userservice
        User user = new User();
        user.setUserName("ceshi123");
        user.setAge("123");
        user.setId(10);
        user.setInsertTime(new Timestamp(System.currentTimeMillis()));
        for (int i = 0; i < 5; i ++) {
            userService.insert(user);
        }
        userService.findAll();
        User user1 = userService.findById(35);
        List<User> list1 = userService.findAll();
//        List<User> users = userService.findAll();
//        ArrayList<Document> documentArrayList = new ArrayList<Document>();
//        for (User user1:users) {
//            Document document = new Document();
//            document.put("id",user1.getId());
//            document.put("age",user1.getAge());
//            document.put("name",user1.getUserName());
//            document.put("insertTime",user1.getInsertTime());
//            documentArrayList.add(document);
//        }
//        logger.info(documentArrayList.toString());
//        //测试productservice
//        Product product = new Product();
//        product.setInsertTime(new Timestamp(System.currentTimeMillis()));
//        product.setMessage("message");
//        product.setOwner(String.valueOf(user.getId()));
//        product.setOther("other");
//        product.setTitle("title");
//        productService.save(product);
//        List<Product> products = productService.findAll();
//        logger.info(products.toString());
        return view;
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
        byte[] body = uploadUitl.readBody(request);
        logger.debug(file.getContentType()+"\n"+file.getOriginalFilename()+"\n"+file.getSize()+"\n"+file.getClass().toString());
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
                 Logger logger = LoggerFactory.getLogger(IndexAction.class);
        // 记录debug级别的信息
        logger.debug("This is debug message.");
        // 记录info级别的信息
        logger.info("This is info message.");
        // 记录error级别的信息
        logger.error("This is error message.");
    }

}
