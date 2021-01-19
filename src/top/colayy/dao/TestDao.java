package top.colayy.dao;

import Util.JDBCUtil;
import top.colayy.pojo.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TestDao {
    public ArrayList<Test> getAllTest(){
        ArrayList<Test> al = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select * from e_test left join e_classify on e_test.c_id = e_classify.c_id and e_test.is_delete = 0 and e_classify.is_delete = 0 order by e_test.create_time desc";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                Test t = new Test();
                t.settClassify(rs.getString(10));
                t.settType(rs.getInt(5));
                t.settTopic(rs.getString(2));
                t.setCreateTime(new SimpleDateFormat("yyyy:MM:dd HH:mm:ss").format(rs.getTimestamp(7)));
                al.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return al;
    }

    public int insertTest(String uuid,String tTopic,String tAnswer,int tScore,int tType,String classifyID){
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        int row = 0;
        String sql = "insert into e_test values(?,?,?,?,?,?,now(),0)";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,uuid);
            pres.setString(2,tTopic);
            pres.setString(3,tAnswer);
            pres.setInt(4,tScore);
            pres.setInt(5,tType);
            pres.setString(6,classifyID);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return row;
    }

    public int insertOption(String uuid,String choiceA,String choiceB,String choiceC,String choiceD){
        int row = 0;
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "insert into e_option values(?,?,?,?,?)";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,uuid);
            pres.setString(2,choiceA);
            pres.setString(3,choiceB);
            pres.setString(4,choiceC);
            pres.setString(5,choiceD);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return row;
    }

    public Test getTestByTTopic(String tTopic){
        Test test = new Test();

        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select * from e_test where t_topic = ? and is_delete = 0";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,tTopic);
            rs = pres.executeQuery();
            while (rs.next()){
                test.settType(rs.getInt(5));
                test.settTopic(rs.getString(2));
                test.settAnswer(rs.getString(3));
                test.settScore(rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return test;
    }

    public String getClassifyID(String tTopic){
        String classifyID = "";
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select c_id from e_test where t_topic = ? and is_delete = 0";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,tTopic);
            rs = pres.executeQuery();
            while (rs.next()){
                classifyID = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return classifyID;
    }

    /**
     * 功能描述:
     *
     * @Param: [tTopic]
     * @Return: java.lang.String
     * @Author: colayy
     * @Date: 2020/12/11 0011 13:30
     */
    public String getTestID(String tTopic){
        String testID = "";
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select t_id from e_test where t_topic = ? and is_delete = 0";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,tTopic);
            rs = pres.executeQuery();
            while (rs.next()){
                testID = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return testID;
    }

    public Test getOptionByTID(String tID){
        Test test = new Test();

        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select * from e_option where o_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,tID);
            rs = pres.executeQuery();
            while (rs.next()){
                test.setoA(rs.getString(2));
                test.setoB(rs.getString(3));
                test.setoC(rs.getString(4));
                test.setoD(rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }

        return test;
    }

    public int updateTest(String uuid,String tTopic,String tAnswer,int tScore,String classifyID){
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        int row = 0;
        String sql = "update e_test set t_topic = ?, t_answer = ?, t_score = ?, c_id = ?, create_time = now() where t_id = ? and is_delete = 0";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(5,uuid);
            pres.setString(1,tTopic);
            pres.setString(2,tAnswer);
            pres.setInt(3,tScore);
            pres.setString(4,classifyID);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return row;
    }

    public int updateOption(String uuid,String choiceA,String choiceB,String choiceC,String choiceD){
        int row = 0;
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "update e_option set o_a = ?, o_b = ?, o_c = ?, o_d = ? where o_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(5,uuid);
            pres.setString(1,choiceA);
            pres.setString(2,choiceB);
            pres.setString(3,choiceC);
            pres.setString(4,choiceD);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return row;
    }
}