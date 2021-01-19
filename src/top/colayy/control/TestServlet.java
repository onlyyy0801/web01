package top.colayy.control;

import com.alibaba.fastjson.JSON;
import top.colayy.pojo.Test;
import top.colayy.service.TestService;
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
import java.util.UUID;

@WebServlet(value = "/test")
public class TestServlet extends HttpServlet {
    TestService ts = new TestService();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        HashMap<String, Object> hm = new HashMap<>();
        String s = JSONUtil.readJSONString(request);
        HashMap hashMap = JSON.parseObject(s, HashMap.class);

        String mark = hashMap.get("mark").toString();
        String data = hashMap.get("data").toString();

        if(mark.equals("showAllTest")){
            ArrayList<Test> allTest = ts.getAllTest();
            hm.put("result",allTest);
        }else if(mark.equals("insertSingle")){
            HashMap singleHm = JSON.parseObject(data, HashMap.class);
            String uuid = UUID.randomUUID().toString();
            String tTopic = singleHm.get("tTopic").toString();
            int tType = Integer.parseInt(singleHm.get("tType").toString());
            String choiceA = singleHm.get("choiceA").toString();
            String choiceB = singleHm.get("choiceB").toString();
            String choiceC = singleHm.get("choiceC").toString();
            String choiceD = singleHm.get("choiceD").toString();
            String tAnswer = singleHm.get("tAnswer").toString();
            int tScore = Integer.parseInt(singleHm.get("tScore").toString());
            String tClassify = singleHm.get("tClassify").toString();

            boolean result = ts.insertSingle(uuid, tTopic, choiceA, choiceB, choiceC, choiceD, tAnswer, tScore, tType, tClassify);
            hm.put("result",result);

        }else if(mark.equals("insertQuest")){
            HashMap questHm = JSON.parseObject(data, HashMap.class);
            String uuid = UUID.randomUUID().toString();
            String tTopic = questHm.get("tTopic").toString();
            int tType = Integer.parseInt(questHm.get("tType").toString());
            String tAnswer = questHm.get("tAnswer").toString();
            int tScore = Integer.parseInt(questHm.get("tScore").toString());
            String tClassify = questHm.get("tClassify").toString();

            boolean result = ts.insertQuest(uuid, tTopic, tAnswer, tScore, tType, tClassify);
            hm.put("result",result);
        }else if(mark.equals("deleteTest")){

        }else if(mark.equals("changeTest")){
            HashMap dataHashMap = JSON.parseObject(data,HashMap.class);
            if(dataHashMap.get("judge").toString().equals("单选题")){
                Test testAllCon = ts.getTestAllCon(dataHashMap.get("tTopic").toString());
                Test optionAllCon = ts.getOptionAllCon(dataHashMap.get("tTopic").toString());
                hm.put("testAllCon",testAllCon);
                hm.put("optionAllCon",optionAllCon);
            }else if(dataHashMap.get("judge").toString().equals("简答题")){
                Test testAllCon = ts.getTestAllCon(dataHashMap.get("tTopic").toString());
                hm.put("testAllCon",testAllCon);
            }
        }else if(mark.equals("updateSingle")){
            HashMap singleHm = JSON.parseObject(data, HashMap.class);
            String tTopic = singleHm.get("tTopic").toString();
            String choiceA = singleHm.get("choiceA").toString();
            String choiceB = singleHm.get("choiceB").toString();
            String choiceC = singleHm.get("choiceC").toString();
            String choiceD = singleHm.get("choiceD").toString();
            String tAnswer = singleHm.get("tAnswer").toString();
            int tScore = Integer.parseInt(singleHm.get("tScore").toString());
            String tClassify = singleHm.get("tClassify").toString();

            boolean result = ts.updateSingle( tTopic, choiceA, choiceB, choiceC, choiceD, tAnswer, tScore, tClassify);
            hm.put("result",result);

        }else if(mark.equals("updateQuest")){
            HashMap questHm = JSON.parseObject(data, HashMap.class);
            String tTopic = questHm.get("tTopic").toString();
            String tAnswer = questHm.get("tAnswer").toString();
            int tScore = Integer.parseInt(questHm.get("tScore").toString());
            String tClassify = questHm.get("tClassify").toString();

            boolean result = ts.updateQuest( tTopic, tAnswer, tScore, tClassify);
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
