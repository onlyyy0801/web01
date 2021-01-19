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

@WebServlet(value = "/login")
public class LoginServlet extends HttpServlet {
    ManagerService ms = new ManagerService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HashMap<String, Object> parmas = new HashMap<String, Object>();
        String paramString = JSONUtil.readJSONString(request);
        Manager manager = JSON.parseObject(paramString, Manager.class);
        String mAcc = manager.getmAcc();
        String mPwd = manager.getmPwd();
        boolean mark = ms.login(mAcc, mPwd);
        parmas.put("mark",mark);
        String result = JSON.toJSONString(parmas);
        response.setContentType("application/json");
        PrintWriter pw = response.getWriter();
        pw.write(result);
        pw.flush();
        pw.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }


}
