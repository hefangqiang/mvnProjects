package com.service;

import com.pojo.Paper;

import java.util.List;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-11-08 14:05
 **/
public interface PaperService {

    Paper addPaper(Paper paper);

    long deletePaperById(long id);

    Paper updatePaper(Paper paper);

    Paper queryById(long id);

    List<Paper> queryAllPaper();

    void addSms(Paper paper);

}
