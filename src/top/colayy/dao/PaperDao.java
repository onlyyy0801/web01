package top.colayy.dao;

import Util.JDBCUtil;
import top.colayy.pojo.Paper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PaperDao {
    public ArrayList<Paper> getAllPaper(){
        ArrayList<Paper> al = new ArrayList<>();

        Connection conn = null;
        PreparedStatement pres = null;
        ResultSet rs = null;

        String sql = "select * from e_paper where is_delete = 0 order by create_time desc";

        try {
            conn = JDBCUtil.getConnection();
            pres = conn.prepareStatement(sql);
            rs = pres.executeQuery();
            while (rs.next()){
                Paper p = new Paper();
                p.setpId(rs.getString(1));
                p.setpName(rs.getString(2));
                p.setpScore(rs.getInt(3));
                p.setCreateTime(new SimpleDateFormat("yy:MM:dd HH:mm:ss").format(rs.getTimestamp(4)));
                al.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            JDBCUtil.release(conn,pres,rs);
        }

        return al;
    }
}
