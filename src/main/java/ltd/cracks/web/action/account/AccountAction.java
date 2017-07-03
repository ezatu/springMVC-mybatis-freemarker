package ltd.cracks.web.action.account;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import ltd.cracks.core.util.base64Util;
import ltd.cracks.core.util.requestUtil;
import ltd.cracks.service.front.accinfo.AccInfo;
import ltd.cracks.service.front.accinfo.AccInfoServiceImpl;
import ltd.cracks.service.front.account.Account;
import ltd.cracks.service.front.account.AccountServiceImpl;
import ltd.cracks.service.front.article.Article;
import ltd.cracks.service.front.article.ArticleServiceImpl;
import ltd.cracks.service.mongo.mongoService;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by macos on 2017/6/23.
 */
@Controller
@RequestMapping("/account")
public class AccountAction {

    @Autowired
    private OSSClient ossClient;

    @Autowired
    private AccountServiceImpl accountService;

    @Autowired
    private ArticleServiceImpl articleService;

    @Autowired
    private AccInfoServiceImpl accInfoService;

    private Logger logger = LoggerFactory.getLogger(AccountAction.class);

    private static final String baseUrl = "http://cracks.oss-cn-qingdao.aliyuncs.com/";

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ModelAndView account(HttpServletResponse response, HttpServletRequest request) {

        ModelAndView view = new ModelAndView("create");
        return view;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void create(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        Document result = new Document();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String phoneNumber = request.getParameter("phoneNumber");
        Account account = new Account();
        account.setId(phoneNumber);
        account.setUsername(username);
        account.setPassword(DigestUtils.md5Hex(password));
        account.setInsertTime(new Timestamp(System.currentTimeMillis()));
        account.setPhoneNumber(phoneNumber);
        Account temp = accountService.selectOne(account);
        if (temp != null) {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","用户名已存在");
        } else {
            accountService.insert(account);
            result.put("code",200);
            result.put("status","success");
            result.put("detail","注册成功");
        }
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(result.toJson());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            response.setStatus(400);
        }
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public void login(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        Document result = new Document();
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        Account account = new Account();
        account.setId(phoneNumber);
        Account temp = accountService.selectOne(account);
        if (temp != null && temp.getPassword().equalsIgnoreCase(DigestUtils.md5Hex(password))) {
            result.put("code",200);
            result.put("status","success");
            result.put("detail","登录成功");
            result.put("token", DigestUtils.md5Hex(phoneNumber));
        } else {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","用户名密码错误或不存在");
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

    @RequestMapping(value = "/userInfo",method = RequestMethod.GET)
    public void userInfo(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        Document result = new Document();
        String token = request.getParameter("token");
        String userId = request.getParameter("userId");
        Account temp = accountService.findById(userId);
        String md5hex = DigestUtils.md5Hex("swy111");
        if (temp != null && DigestUtils.md5Hex(temp.getPhoneNumber()).equalsIgnoreCase(token)) {
            AccInfo accInfo = accInfoService.findById(userId);
            result.put("username",temp.getUsername());
            if (accInfo != null) {
                result.put("sex",accInfo.getSex());
                result.put("brithday",accInfo.getBrithday().toString());
                result.put("icon",accInfo.getIcon());
                result.put("sign",accInfo.getSign());
                result.put("email",accInfo.getEmail());
            }
        } else {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","用户名密码错误或不存在");
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

    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    public void reset(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        Document result = new Document();
        String userId = request.getParameter("userId");
        String newPwd = request.getParameter("newpwd");
        Account temp = accountService.findById(userId);
        if (temp != null) {
            temp.setPassword(DigestUtils.md5Hex(newPwd));
            accountService.update(temp);
            result.put("code",200);
            result.put("status","success");
            result.put("detail","重置密码成功");
        } else {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","未找到相关用户");
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

    @RequestMapping(value = "/editUserInfo",method = RequestMethod.POST)
    public void editUserInfo(@RequestParam("usericon") MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        String imgurl = null;
        Document result = new Document();
        logger.debug(file.getContentType()+"\n"+file.getOriginalFilename()+"\n"+file.getSize()+"\n"+file.getClass().toString());
        String userId = request.getParameter("userId");
        String token = request.getParameter("token");
        String username = request.getParameter("username");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String email = request.getParameter("email");
        String sign = request.getParameter("sign");

        try {
            ossClient.putObject("cracks","usericon_"+userId+".png",file.getInputStream());
            imgurl = baseUrl+"usericon_"+userId+".png";
        } catch (IOException e) {
            e.printStackTrace();
        }
        Account temp = accountService.findById(userId);
        if (temp != null && DigestUtils.md5Hex(temp.getPhoneNumber()).equalsIgnoreCase(token)) {
            temp.setUsername(username);
            accountService.update(temp);
            AccInfo accInfo = accInfoService.findById(userId);
            if (accInfo == null) { // 用户资料为空
                accInfo = new AccInfo();
                accInfo.setId(userId);
                accInfo.setBrithday(birthday);
                accInfo.setEmail(email);
                accInfo.setIcon(imgurl);
                if (sex.equalsIgnoreCase("男")) {
                    accInfo.setSex(1);
                } else {
                    accInfo.setSex(0);
                }
                accInfo.setSign(sign);
                accInfoService.insert(accInfo);
                result.put("code",200);
                result.put("status","success");
                result.put("detail","用户资料更新成功");
                Document data = new Document();
                data.put("username",temp.getUsername());
                data.put("sex",accInfo.getSex());
                data.put("brithday",accInfo.getBrithday().toString());
                data.put("icon",accInfo.getIcon());
                data.put("sign",accInfo.getSign());
                data.put("email",accInfo.getEmail());
                result.put("data", data);
            } else { // 用户资料不为空
                accInfo.setBrithday(birthday);
                accInfo.setEmail(email);
                accInfo.setIcon(imgurl);
                if (sex.equalsIgnoreCase("男")) {
                    accInfo.setSex(1);
                } else {
                    accInfo.setSex(0);
                }
                accInfo.setSign(sign);
                accInfoService.update(accInfo);
                result.put("code",200);
                result.put("status","success");
                result.put("detail","用户资料更新成功");
                Document data = new Document();
                data.put("username",temp.getUsername());
                data.put("sex",accInfo.getSex());
                data.put("brithday",accInfo.getBrithday().toString());
                data.put("icon",accInfo.getIcon());
                data.put("sign",accInfo.getSign());
                data.put("email",accInfo.getEmail());
                result.put("data", data);
            }
            accInfo.setBrithday(birthday);
            accInfo.setEmail(email);
            accInfo.setIcon(imgurl);
            if (sex.equalsIgnoreCase("男")) {
                accInfo.setSex(1);
            } else {
                accInfo.setSex(0);
            }
            accInfo.setSign(sign);
            accInfoService.update(accInfo);
            result.put("code",200);
            result.put("status","success");
            result.put("detail","用户资料更新成功");
            Document data = new Document();
            data.put("username",temp.getUsername());
            data.put("sex",accInfo.getSex());
            data.put("brithday",accInfo.getBrithday().toString());
            data.put("icon",accInfo.getIcon());
            data.put("sign",accInfo.getSign());
            data.put("email",accInfo.getEmail());
            result.put("data", data);
        } else {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","未找到相关用户");
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

    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    public void uploadImg(@RequestParam("artImg") MultipartFile file, HttpServletResponse response, HttpServletRequest request) {
        Document document = new Document();
        response.setContentType("text/html;charset=UTF-8");
        String imgurl = null;
        logger.debug(file.getContentType()+"\n"+file.getOriginalFilename()+"\n"+file.getSize()+"\n"+file.getClass().toString());
        ObjectId objectId = new ObjectId();
        String imgId = objectId.toString();
        try {
            ossClient.putObject("cracks",imgId+".png",file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imgurl = baseUrl+imgId+".png";
        document.put("imgurl",imgurl);
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.write(document.toJson());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            writer.close();
        }
    }

    @RequestMapping(value = "/postart",method = RequestMethod.POST)
    public void postart(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("text/html;charset=UTF-8");
        Document result = new Document();
        String userId = request.getParameter("userId");
        String token = request.getParameter("token");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String imgs = request.getParameter("imgs");
        String[] imgArray = imgs.split("@");
        Account temp = accountService.findById(userId);
        Article article = new Article();
        if (DigestUtils.md5Hex(temp.getPhoneNumber()).equalsIgnoreCase(token)) {
            logger.debug("用户token校验一致");
            article.setInsertTime(new Timestamp(System.currentTimeMillis()));
            article.setTitle(title);
            article.setUserId(userId);
            article.setContent(content);
            if (imgs.length() != 0) {
                article.setImgs(imgs);
            }
            try {
                articleService.insert(article);
                result.put("code",200);
                result.put("status","success");
                result.put("detail","发布成功");
            } catch (Exception e) {
                result = new Document();
                result.put("code",404);
                result.put("status","failure");
                result.put("detail","发布失败");
            }

        } else {
            result.put("code",400);
            result.put("status","failure");
            result.put("detail","用户鉴权失败");
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

    @RequestMapping(value = "/myarticle",method = RequestMethod.GET)
    public void myarticle(HttpServletResponse response, HttpServletRequest request) {
        Document result = new Document();
        ArrayList<Document> data = new ArrayList<Document>();
        response.setContentType("text/html;charset=UTF-8");
        String userId = request.getParameter("userId");
        Article temp = new Article();
        List<Article> list = null;
        temp.setUserId(userId);
        try {
            list = articleService.selectList(temp);
            if (list.size() != 0) {
                for (Article article : list) {
                    Document document = new Document();
                    document.put("artId", article.getId());
                    document.put("title", article.getTitle());
                    document.put("insertTime", article.getInsertTime().toString());
                    document.put("comment", article.getComment());
                    document.put("fav", article.getFav());
                    data.add(document);
                }
                result.put("data", data);
                result.put("code", 200);
                result.put("status", "success");
                result.put("detail", "请求成功");
            } else {
                result.put("code", 201);
                result.put("status", "nomoredata");
                result.put("detail", "暂未发布笔记");
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 400);
            result.put("status", "failure");
            result.put("detail", "请求失败");
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

    @RequestMapping(value = "/delmyart",method = RequestMethod.POST)
    public void delmyart(HttpServletResponse response, HttpServletRequest request) {
        Document result = new Document();
        response.setContentType("text/html;charset=UTF-8");
        String artId = request.getParameter("artId");
        try {
            articleService.delete(Integer.valueOf(artId));
            result.put("code", 200);
            result.put("status", "success");
            result.put("detail", "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            result.put("code", 400);
            result.put("status", "failure");
            result.put("detail", "删除失败");
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

    public static void main(String[] args) throws Exception {
        requestUtil.sendPost("https://localhost:8081/account/create","username=123&password=qwe");
    }



}
