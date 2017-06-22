package ltd.cracks.core.util;


/**
 * Created by macos on 2017/6/6.
 */
public class timeUtil {
    public static Long coverLocalTime(Long times) {
        return times+8*60*60*1000;
    }
}
