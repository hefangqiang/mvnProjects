package com.dao;

import com.pojo.Message;
import com.pojo.Paper;

import java.util.List;


public interface PaperDao {
    /**
     * 新增论文信息
     * @param paper
     * @return Result<int>
     */
    int addPaper(Paper paper);


    int deletePaperById(long id);


    int updatePaper(Paper paper);


    Paper queryById(long id);


    List<Paper> queryAllPaper();

    int addSms(Message message);
}
