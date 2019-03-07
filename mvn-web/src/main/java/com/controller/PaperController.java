package com.controller;


import com.pojo.Paper;
import com.service.PaperService;
import com.utils.LogsUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


/**
 * @description： TODO
 * @author： Mr.He
 * @date： 2018-11-08 14:31
 **/

@Controller
@RequestMapping("/paper")
public class PaperController {

    @Autowired
    private PaperService paperService;

    private static final Logger paperLog= LogsUtil.getInstance();

    private static final Logger log= LoggerFactory.getLogger(PaperController.class);

    @RequestMapping("/allPaper")
    public String list(Model model) {
        List<Paper> list = paperService.queryAllPaper();
        paperLog.info("进入allPaper,list:{}", list);
        model.addAttribute("list", list);
        return "allPaper";
    }

    @RequestMapping("/queryPaperById")
    public String queryPaperById(Model model,long paperId){
        Paper paper = paperService.queryById(paperId);
        paperLog.info("进入queryPaperById,paperId:{}", paperId);
        List<Paper> list = new ArrayList<>();
        list.add(paper);
        model.addAttribute("list", list);
        return "allPaper";
    }

    @RequestMapping("/toAddPaper")
    public String toAddPaper() {
        return "addPaper";
    }

    @RequestMapping("/addPaper")
    public String addPaper(Paper paper) {
        paperService.addPaper(paper);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("/del/{paperId}")
    public String deletePaper(@PathVariable("paperId") long id) {
        paperService.deletePaperById(id);
        return "redirect:/paper/allPaper";
    }

    @RequestMapping("/toUpdatePaper")
    public  String toUpdatePaper(Model model,long id) {
        model.addAttribute("paper", paperService.queryById(id));
        return "updatePaper";
    }

    @RequestMapping("/updatePaper")
    public String updatePaper(Model model, Paper paper) {
        paperService.updatePaper(paper);
        paper=paperService.queryById(paper.getPaperId());
        model.addAttribute("paper",paper);
        return "redirect:/paper/allPaper";
    }

}
