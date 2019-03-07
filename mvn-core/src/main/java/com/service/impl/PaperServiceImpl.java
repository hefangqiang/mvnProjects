package com.service.impl;

import com.dao.PaperDao;
import com.pojo.Message;
import com.pojo.Paper;
import com.service.PaperService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
//    @CachePut(value = "paperCache",key = "'PaperServiceImpl'+#paper.getPaperId()")
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public Paper addPaper(Paper paper) {
        paperDao.addPaper(paper);
        //调用自身方法实现事务
        ((PaperService) AopContext.currentProxy()).addSms(paper);
        return paper;
    }

    @Override
//    @CacheEvict(value = "paperCache",key = "'PaperServiceImpl'+#id")
    public long deletePaperById(long id) {
        paperDao.deletePaperById(id);
        return id;
    }

    @Override
//    @CachePut(value = "paperCache",key = "'PaperServiceImpl'+#paper.getPaperId()")
    public Paper updatePaper(Paper paper) {
        paperDao.updatePaper(paper);
        return paper;
    }

    @Override
//    @Cacheable(value = "paperCache",key = "'PaperServiceImpl'+#id")
    public Paper queryById(long id) {
        return paperDao.queryById(id);
    }

    @Override
//    @Cacheable(value = "paperCache",key = "'PaperServiceImplAllPaper'")
    public List<Paper> queryAllPaper() {
        return paperDao.queryAllPaper();
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void addSms(Paper paper) {
            Message message = new Message();
            message.setMessageName(paper.getPaperName());
            message.setMessageNumber(paper.getPaperNum());
            message.setMessageInformation("短信发送成功");
            paperDao.addSms(message);


    }
}
