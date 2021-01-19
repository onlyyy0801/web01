package top.colayy.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

public class JSONUtil {
    public static String readJSONString(HttpServletRequest request){
        StringBuffer json = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null ){
                json.append(line);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return json.toString();
    }
}
