package top.colayy.dao;

import Util.JDBCUtil;
import top.colayy.pojo.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class ManagerDao {

    /**
     * 功能描述: 通过账号获取密码，返回管理员对象
     *
     * @Param: [mAcc]
     * @Return: top.colayy.pojo.Manager
     * @Author: colayy
     * @Date: 2020/12/9 0009 13:55
     */
    public Manager getManager(String mAcc){
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        Manager m = new Manager();
        try {
            String m_acc =  null;
            String m_pwd = null;
            String sql = "select * from e_manager where m_acc = ? and is_delete = 0";
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1,mAcc);
            rs = pres.executeQuery();
            while (rs.next()){
                m_acc = rs.getString(2);
                m_pwd = rs.getString(3);
            }
            m.setmAcc(m_acc);
            m.setmPwd(m_pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return m;
    }

    /**
     * 功能描述: 添加管理员账号
     *
     * @Param: [m]
     * @Return: boolean
     * @Author: colayy
     * @Date: 2020/12/9 0009 13:57
     */
    public boolean addManager(Manager m){
        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;
        boolean mark = false;
        try {
            String sql = "insert into e_manager values (?,?,?,?,?,?,?)";
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            pres.setString(1, UUID.randomUUID().toString());
            pres.setString(2,m.getmAcc());
            pres.setString(3,m.getmPwd());
            pres.setString(4,m.getmPhone());
            pres.setString(5,m.getmEmail());
            pres.setString(6,new SimpleDateFormat("yyyy-MM-dd hh-mm-ss").format(new Date()));
            pres.setInt(7,0);
            mark = pres.executeUpdate() > 0;
            JDBCUtil.release(conn,pres,null);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }
        return mark;
    }


}
