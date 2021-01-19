package top.colayy.service;

import top.colayy.dao.PaperDao;
import top.colayy.pojo.Paper;

import java.util.ArrayList;

public class PaperService {
    PaperDao pd = new PaperDao();

    public ArrayList<Paper> getALlPaper(){
        return pd.getAllPaper();
    }
}
