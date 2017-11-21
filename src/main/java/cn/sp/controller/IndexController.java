package cn.sp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by 2YSP on 2017/10/25.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(ModelMap model){
        model.addAttribute("title","首页");
        model.addAttribute("mainPage","film/indexFilm");
        model.addAttribute("mainPageKey","#f");
        return "index";
    }
    
    @RequestMapping("/login")
    public String login(){
    	return "/login";
    }
    
    @RequestMapping("/admin")
    public String toAdmin(){
    	return "/admin/main";
    }

    /**
     * 关于本站
     * @return
     */
    @RequestMapping("/aboutMe")
    public ModelAndView aboutMe(){
        ModelAndView mav=new ModelAndView();
        mav.addObject("title", "关于本站");
        mav.addObject("mainPage", "common/aboutMe");
        mav.addObject("mainPageKey", "#a");
        mav.setViewName("index");
        return mav;
    }
    
}
