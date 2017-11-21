package cn.sp.controller;

import cn.sp.entity.Film;
import cn.sp.entity.WebSite;
import cn.sp.service.WebSiteInfoService;
import cn.sp.service.WebSiteService;
import cn.sp.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 2YSP on 2017/11/21.
 * 收录电影网站
 */
@Controller
@RequestMapping("webSite")
public class WebSiteController {

    @Resource
    private WebSiteService webSiteService;

    /**
     * 分页查询电影网站
     * @param page
     * @return
     */
    @RequestMapping("/list/{page}")
    public ModelAndView list(@PathVariable(value = "page",required = false)Integer page){
        ModelAndView mav = new ModelAndView();
        List<WebSite> webSiteList = webSiteService.list(page-1, 20, null);
        long total = webSiteService.getCount(null);
        mav.addObject("webSiteList",webSiteList);
        mav.addObject("mainPage","webSite/list");
        mav.addObject("mainPageKey","#f");
        mav.addObject("title","收录电影网站");
        mav.addObject("pageCode", PageUtil.genPagination("/webSite/list",Integer.valueOf(total+""),page,20));
        mav.setViewName("index");
        return mav;
    }
}
