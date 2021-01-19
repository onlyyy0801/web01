package top.colayy.control;

import com.alibaba.fastjson.JSON;
import top.colayy.pojo.Manager;
import top.colayy.service.ManagerService;
import top.colayy.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(value = "/signup")
public class SignupServlet extends HttpServlet {
    ManagerService ms = new ManagerService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> hs = new HashMap<>();
        boolean mark = false;
        String s = JSONUtil.readJSONString(request);
        Map map = (Map) JSON.parse(s);
        String key = (String) map.get("k");
        if ("checkAcc".equals(key)) {
            mark = ms.checkManager((String) map.get("v"));
        } else if ("insertManager".equals(key)) {
            Manager m = JSON.parseObject(map.get("v").toString(), Manager.class);
            mark = ms.register(m);
        }
        hs.put("mark",mark);
        String jsonString = JSON.toJSONString(hs);
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(jsonString);
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
