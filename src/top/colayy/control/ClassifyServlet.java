package top.colayy.control;

import com.alibaba.fastjson.JSON;
import top.colayy.service.ClassifyService;
import top.colayy.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(value = "/classify")
public class ClassifyServlet extends HttpServlet {
    ClassifyService cs = new ClassifyService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HashMap<String, Object> hm = new HashMap<>();

        String s = JSONUtil.readJSONString(request);
        HashMap hashMap = JSON.parseObject(s, HashMap.class);
        String mark = hashMap.get("mark").toString();
        String data = hashMap.get("data").toString();

        if (mark.equals("showAllClassify")){
            ArrayList<String> allClassify = cs.getAllClassify();
            hm.put("result",allClassify);
        }else if(mark.equals("insertClassify")){
            boolean result = cs.insertClassify(data);
            hm.put("result",result);
        }else if(mark.equals("deleteClassify")){
            boolean result = cs.deleteClassify(data);
            hm.put("result",result);
        }else if(mark.equals("changeClassify")){
            HashMap hashMap1 = JSON.parseObject(data, HashMap.class);
            String cNameOld = hashMap1.get("cNameOld").toString();
            String cNameNew = hashMap1.get("cNameNew").toString();
            boolean result = cs.changeClassify(cNameOld, cNameNew);
            hm.put("result",result);
        }
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(JSON.toJSONString(hm));
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
