package top.colayy.util;

import java.io.InputStream;
import java.util.Properties;

public class PATHUtil {
    private static String path = null;

    static {
        try {
            Properties props = new Properties();
            InputStream ins = PATHUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            props.load(ins);
            path = props.getProperty("pathOl");
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public PATHUtil() {
    }

    public static String getPathOl(){
        return path;
    }

}
