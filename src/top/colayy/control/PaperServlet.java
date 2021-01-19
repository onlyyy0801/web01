package top.colayy.control;

import com.alibaba.fastjson.JSON;
import top.colayy.pojo.Paper;
import top.colayy.service.PaperService;
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

@WebServlet(value = "/paper")
public class PaperServlet extends HttpServlet {
    PaperService ps = new PaperService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String jsonString = JSONUtil.readJSONString(request);

        HashMap<String, Object> hm = new HashMap<>();

        HashMap hashMap = JSON.parseObject(jsonString, HashMap.class);

        String mark = hashMap.get("mark").toString();

        if("showAllPaper".equals(mark)){
            ArrayList<Paper> allPaper = ps.getALlPaper();
            hm.put("result",allPaper);

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
