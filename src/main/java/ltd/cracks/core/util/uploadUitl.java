package ltd.cracks.core.util;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by macos on 2017/6/8.
 */
public class uploadUitl {
    // 上传文件存储目录
    private static final String UPLOAD_DIRECTORY = "/Users/macos/Desktop/";

    // 定义文件起始位置类
    public static class Position {
        int begin;
        int end;
        Position(int begin, int end) {
            this.begin = begin;
            this.end = end;
        }
    }
    // 获取上传文件并写入本地
    public static void writeTo(String filename, byte[] body, Position p)
            throws FileNotFoundException, IOException {
        FileOutputStream fileOutputStream =
                new FileOutputStream(UPLOAD_DIRECTORY + filename);
        fileOutputStream.write(body, p.begin, (p.end - p.begin));
        fileOutputStream.flush();
        fileOutputStream.close();
    }

    // 获取上传文件起始位置
    public static Position getFilePosition(HttpServletRequest request, String textBody) throws IOException {

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

    // 读取request请求数据
    public static byte[] readBody(HttpServletRequest request)
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

    // 读取上传文件名
    public static String getFilename(String reqBody) {
        String filename = reqBody.substring(
                reqBody.indexOf("filename=\"") + 10);
        filename = filename.substring(0, filename.indexOf("\n"));
        filename = filename.substring(
                filename.lastIndexOf("\\") + 1, filename.indexOf("\""));
        return filename;
    }
}
