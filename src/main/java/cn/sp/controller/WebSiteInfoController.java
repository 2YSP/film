package cn.sp.controller;

import cn.sp.entity.WebSite;
import cn.sp.entity.WebSiteInfo;
import cn.sp.service.WebSiteInfoService;
import cn.sp.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by 2YSP on 2017/11/21.
 */
@Controller
@RequestMapping("webSiteInfo")
public class WebSiteInfoController {

    @Resource
    private WebSiteInfoService webSiteInfoService;

    /**
     * 分页查询电影网站动态信息
     * @param page
     * @return
     */
    @RequestMapping("/list/{page}")
    public ModelAndView list(@PathVariable(value = "page",required = false)Integer page){
        ModelAndView mav = new ModelAndView();
        List<WebSiteInfo> webSiteInfoList = webSiteInfoService.list(page-1, 20, null);
        long total = webSiteInfoService.getCount(null);
        mav.addObject("webSiteInfoList",webSiteInfoList);
        mav.addObject("mainPage","webSiteInfo/list");
        mav.addObject("mainPageKey","#f");
        mav.addObject("title","电影网站信息");
        mav.addObject("pageCode", PageUtil.genPagination("/webSiteInfo/list",Integer.valueOf(total+""),page,20));
        mav.setViewName("index");
        return mav;
    }
}
