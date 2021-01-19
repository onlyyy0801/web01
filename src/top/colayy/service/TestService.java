package top.colayy.service;

import top.colayy.dao.ClassifyDao;
import top.colayy.dao.TestDao;
import top.colayy.pojo.Test;

import java.util.ArrayList;

public class TestService {
    TestDao td = new TestDao();
    ClassifyDao cd = new ClassifyDao();
    public ArrayList<Test> getAllTest(){
        return td.getAllTest();
    }


    public boolean insertSingle(String uuid,String tTopic,String choiceA,String choiceB,String choiceC,String choiceD,String tAnswer,int tScore,int tType,String tClassify){
        boolean mark = false;
        String classifyID = cd.getClassifyID(tClassify);

        int testRow = td.insertTest(uuid, tTopic, tAnswer, tScore, tType, classifyID);
        if(testRow != 0){
            int optionRow = td.insertOption(uuid, choiceA, choiceB, choiceC, choiceD);
            mark = optionRow != 0;
        }
        return mark;
    }

    public boolean insertQuest(String uuid,String tTopic,String tAnswer,int tScore,int tType,String tClassify){
        boolean mark = false;
        String classifyID = cd.getClassifyID(tClassify);

        int testRow = td.insertTest(uuid, tTopic, tAnswer, tScore, tType, classifyID);
        mark = testRow != 0;
        return mark;
    }


    public Test getTestAllCon(String tTopic){

        Test test = td.getTestByTTopic(tTopic);

        String classifyID = td.getClassifyID(tTopic);
        String classifyName = cd.getClassifyName(classifyID);

        test.settClassify(classifyName);

        return test;
    }


    public Test getOptionAllCon(String tTopic){
        String testID = td.getTestID(tTopic);
        return td.getOptionByTID(testID);
    }


    public boolean updateSingle(String tTopic,String choiceA,String choiceB,String choiceC,String choiceD,String tAnswer,int tScore,String tClassify){
        boolean mark = false;
        String classifyID = cd.getClassifyID(tClassify);
        String testID = td.getTestID(tTopic);
        int testRow = td.updateTest(testID, tTopic, tAnswer, tScore, classifyID);
        if(testRow != 0){
            int optionRow = td.updateOption(testID, choiceA, choiceB, choiceC, choiceD);
            mark = optionRow != 0;
        }
        return mark;
    }

    public boolean updateQuest(String tTopic,String tAnswer,int tScore,String tClassify){
        boolean mark = false;
        String classifyID = cd.getClassifyID(tClassify);
        String testID = td.getTestID(tTopic);
        int testRow = td.updateTest(testID, tTopic, tAnswer, tScore, classifyID);
        mark = testRow != 0;
        return mark;
    }
}
