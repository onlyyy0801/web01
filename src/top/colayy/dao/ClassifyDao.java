package top.colayy.dao;

import Util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClassifyDao {

    /**
     * 功能描述: 从数据库获取所有未删除的的分类名
     *
     * @Param: []
     * @Return: java.util.ArrayList<java.lang.String>
     * @Author: colayy
     * @Date: 2020/12/9 0009 13:59
     */
    public ArrayList<String> getAllClassify(){
        ArrayList<String> al = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select c_name from e_classify where is_delete = 0 order by create_time desc ";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                al.add(rs.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return al;
    }

    /**
     * 功能描述: 向数据库分类表中添加分类名
     *
     * @Param: [cName] 要添加的分类名
     * @Return: int 收影响行数，初始为0
     * @Author: colayy
     * @Date: 2020/12/9 0009 14:01
     */
    public int insertClassify(String uuid,String cName){
        int row = 0;
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "insert into e_classify values (?,?,now(),0)";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, uuid);
            pres.setString(2,cName);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }

        return row;
    }

    /**
     * 功能描述: 从数据库中删除指定的分类名
     * 
     * @Param: [cName] 指定的分类名
     * @Return: int 收影响行数，初始为0
     * @Author: colayy
     * @Date: 2020/12/9 0009 14:03
     */
    public int deleteClassify(String cName){
        int row = 0;
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "update e_classify set is_delete = 1 where c_name = ?";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, cName);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return row;
    }

    public String getClassifyName(String classifyID){
        String classifyName = "";
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select c_name from e_classify where c_id = ? and is_delete = 0";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, classifyID);
            rs = pres.executeQuery();
            while (rs.next()){
                classifyName = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }

        return classifyName;
    }


    public String getClassifyID(String cName){
        String cID = "";
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "select c_id from e_classify where c_name = ? and is_delete = 0";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, cName);
            rs = pres.executeQuery();
            while (rs.next()){
                cID = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return cID;
    }


    /**
     * 功能描述: 根据ID修改类名
     *
     * @Param: [cID, cName]
     * @Return: int
     * @Author: colayy
     * @Date: 2020/12/9 0009 14:15
     */
    public int changeClassify(String cID, String cName){
        int row = 0;
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        String sql = "update e_classify set c_name = ? where c_id = ?";
        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, cName);
            pres.setString(2, cID);
            row = pres.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return row;
    }

}












































