package top.colayy.service;

import top.colayy.dao.ClassifyDao;

import java.util.ArrayList;
import java.util.UUID;

public class ClassifyService {
    ClassifyDao cd = new ClassifyDao();

    /**
     * 功能描述: 获取所有的分类名
     *
     * @Param: []
     * @Return: java.util.ArrayList<java.lang.String>
     * @Author: colayy
     * @Date: 2020/12/9 0009 14:28
     */
    public ArrayList<String> getAllClassify(){
        return cd.getAllClassify();
    }


    /**
     * 功能描述: 添加分类名
     *
     * @Param: [sName]
     * @Return: boolean
     * @Author: colayy
     * @Date: 2020/12/9 0009 14:31
     */
    public boolean insertClassify(String cName){
        return cd.insertClassify(UUID.randomUUID().toString(),cName) != 0;
    }


    /**
     * 功能描述: 删除指定分类名
     *
     * @Param: [sName]
     * @Return: boolean
     * @Author: colayy
     * @Date: 2020/12/9 0009 14:32
     */
    public boolean deleteClassify(String cName){
        return cd.deleteClassify(cName) != 0;
    }

    public boolean changeClassify(String cNameOld, String cNameNew){
        String cID = cd.getClassifyID(cNameOld);
        return cd.changeClassify(cID,cNameNew) != 0;
    }
}
