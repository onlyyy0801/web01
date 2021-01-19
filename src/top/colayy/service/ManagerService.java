package top.colayy.service;


import top.colayy.dao.ManagerDao;
import top.colayy.pojo.Manager;

public class ManagerService {
    ManagerDao md = new ManagerDao();

    public boolean login(String mAcc,String mPwd){
        Manager m = md.getManager(mAcc);
        if(m!=null){
            String m_pwd = m.getmPwd();
            return m_pwd.equals(mPwd);
        }else return false;
    }

    public boolean checkManager(String mAcc){
        return md.getManager(mAcc).getmAcc() != null;
    }

    public boolean register(Manager m){
        return md.addManager(m);
    }
}
