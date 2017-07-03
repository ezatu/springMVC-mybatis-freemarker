package ltd.cracks.web.action.index;

import ltd.cracks.service.front.account.AccountServiceImpl;
import ltd.cracks.service.front.article.Article;
import ltd.cracks.service.front.article.ArticleServiceImpl;
import ltd.cracks.service.front.product.Product;
import ltd.cracks.service.front.product.ProductServiceImpl;
import ltd.cracks.service.front.user.User;
import ltd.cracks.service.front.user.UserServiceImpl;
import net.sf.json.JSONArray;
import org.bson.Document;
import net.sf.json.JSONObject;
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
import java.io.*;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by macos on 2017/4/14.
 */
@Controller
public class IndexAction {

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private AccountServiceImpl accountService;

    // 注入userService实例
    @Autowired
    private UserServiceImpl userService;
    // 注入productService实例
    @Autowired
    private ProductServiceImpl productService;
    // 日志实例
    private static final Logger logger = LoggerFactory.getLogger(IndexAction.class);

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public void index(HttpServletRequest request, HttpServletResponse response) {
        int page = Integer.valueOf(request.getParameter("page"));
        Document result = new Document();
        ArrayList<Document> data = new ArrayList<Document>();
        List<Article> articles = null;
        try {
            Article tempArt = new Article();
            tempArt.setPage(page);
            articles = articleService.selectListByPage(tempArt);
            for (Article art : articles) {
                Document temp = new Document();
                temp.put("comment",art.getComment());
                temp.put("fav",art.getFav());
                temp.put("artId",art.getId());
                temp.put("title",art.getTitle());
//                temp.put("content",art.getContent());
                temp.put("insertTime",art.getInsertTime().toString());
                temp.put("userId",art.getUserId());
                temp.put("userName", accountService.findById(art.getUserId()).getUsername());
                if (art.getImgs() != null) {
                    String[] imgs = art.getImgs().split("@");
                    ArrayList<String> imgurls = new ArrayList<String>();
                    for (String url : imgs) {
                        imgurls.add(url);
                    }
                    temp.put("imgs",imgurls);
                }
                data.add(temp);
            }
            result.put("code",200);
            result.put("data", data);
            result.put("status","success");
            result.put("detail","刷新成功");
        } catch (Exception e) {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","刷新失败");
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result.toJson());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            writer.close();
        }

    }

    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public void status(HttpServletRequest request, HttpServletResponse response) {
            ModelAndView view = new ModelAndView("status");
            Document document = new Document();
            document.put("beian","success");
            document.put("nadi","failure");
            view.addObject("data",document);
        }

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public void hello(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView view = new ModelAndView("hello");
        // 测试userservice
        User user = new User();
        user.setUserName("ceshi123");
        user.setAge("123");
        user.setInsertTime(new Timestamp(System.currentTimeMillis()));
        for (int i = 0; i < 5; i ++) {
            userService.insert(user);
            logger.debug("userId =====" + String.valueOf(user.getId()) + "\n");
        }
        Product product  = new Product();
        product.setInsertTime(new Timestamp(System.currentTimeMillis()));
        product.setMessage("message");
        product.setOther("other");
        product.setownerId(1);
        product.setTitle("productId");
        productService.insert(product);
        List<Product> products = productService.findByOwnerId(1);
        userService.findAll();
//        User user1 = userService.findById(35);
//        userService.delete(35);
        List<User> list1 = userService.findAll();
        view.addObject("users",list1);
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
//        byte[] body = uploadUtil.readBody(request);
        logger.debug(file.getContentType()+"\n"+file.getOriginalFilename()+"\n"+file.getSize()+"\n"+file.getClass().toString());
//        String textBody = new String(body, "ISO-8859-1");
//        String filename = uploadUtil.getFilename(textBody);
//        uploadUtil.Position p = uploadUtil.getFilePosition(request, textBody);
//        uploadUtil.writeTo(filename, body, p);
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
