package com.service.impl;

import com.dao.PaperDao;
import com.pojo.Paper;
import com.service.PaperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-11-08 14:05
 **/
@Service
public class PaperServiceImpl implements PaperService {

    @Autowired
    private PaperDao paperDao;


    @Override
    @CachePut(value = "paperCache",key = "'PaperServiceImpl'+#paper.getPaperId()")
    public Paper addPaper(Paper paper) {
        paperDao.addPaper(paper);
        return paper;
    }

    @Override
    @CacheEvict(value = "paperCache",key = "'PaperServiceImpl'+#id")
    public long deletePaperById(long id) {
        paperDao.deletePaperById(id);
        return id;
    }

    @Override
    @CachePut(value = "paperCache",key = "'PaperServiceImpl'+#paper.getPaperId()")
    public Paper updatePaper(Paper paper) {
        paperDao.updatePaper(paper);
        return paper;
    }

    @Override
    @Cacheable(value = "paperCache",key = "'PaperServiceImpl'+#id")
    public Paper queryById(long id) {
        return paperDao.queryById(id);
    }

    @Override
    public List<Paper> queryAllPaper() {
        return paperDao.queryAllPaper();
    }
}
