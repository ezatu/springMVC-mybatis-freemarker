package ltd.cracks.demo;

import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.spi.LoggerFactoryBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by macos on 2017/4/14.
 */
@Controller
public class test extends HttpServlet {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger logger = LoggerFactory.getLogger(test.class);
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "/Users/macos/Desktop/";

    class Position {
        int begin;
        int end;
        Position(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }

    private void writeTo(String filename, byte[] body, Position p)
            throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream =
                new FileOutputStream(UPLOAD_DIRECTORY + filename);
        fileOutputStream.write(body, p.begin, (p.end - p.begin));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    private Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {

        String contentType = request.getContentType();
        String boundaryText = contentType.substring(
                contentType.lastIndexOf("=") + 1, contentType.length());
        int pos = textBody.indexOf("filename=\"");
        pos = textBody.indexOf("\n", pos) + 1;
        pos = textBody.indexOf("\n", pos) + 1;
        pos = textBody.indexOf("\n", pos) + 1;
        int boundaryLoc = textBody.indexOf(boundaryText, pos) -4;
        int begin = ((textBody.substring(0,
                pos)).getBytes("ISO-8859-1")).length;
        int end = ((textBody.substring(0,
                boundaryLoc)).getBytes("ISO-8859-1")).length;

        return new Position(begin, end);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap map) {
        logger.info("start request");
        String code = request.getParameter("code");
        System.out.println(code);
        Set<String> collections = mongoTemplate.getCollectionNames();
        for (String name:collections
             ) {
            System.out.println(name);
        }
        map.put("name","myname");
        return "index";
    }


    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void upload(HttpServletRequest request, HttpServletResponse response, ModelMap map) throws IOException {
        byte[] body = readBody(request);
        String textBody = new String(body, "ISO-8859-1");
        String filename = getFilename(textBody);
        Position p = getFilePosition(request, textBody);
        writeTo(filename, body, p);


        /**
         * 设置返回类型
         */
        response.setContentType("text/html");
        /**
         * 设置字符集 避免乱码 和页面字符集保持一致
         */
        Gson gson = new Gson();

        JSONArray jsonArray = new JSONArray();
        for (int i=0;i<10;i ++) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code",200);
            jsonObject.put("result",i);
            jsonObject.put("status",i+"success");
            jsonArray.put(jsonObject);
        }

        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonArray.toString());
        response.getWriter().flush();
        response.getWriter().close();
    }

    private byte[] readBody(HttpServletRequest request)
            throws IOException{
        int formDataLength = request.getContentLength();
        DataInputStream dataStream = new DataInputStream(request.getInputStream());
        byte body[] = new byte[formDataLength];
        int totalBytes = 0;
        while (totalBytes < formDataLength) {
            int bytes = dataStream.read(body, totalBytes, formDataLength);
            totalBytes += bytes;
        }
        return body;
    }


    private String getFilename(String reqBody) {
        String filename = reqBody.substring(
                reqBody.indexOf("filename=\"") + 10);
        filename = filename.substring(0, filename.indexOf("\n"));
        filename = filename.substring(
                filename.lastIndexOf("\\") + 1, filename.indexOf("\""));
        return filename;
    }


}
