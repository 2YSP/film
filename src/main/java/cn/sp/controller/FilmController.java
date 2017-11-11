package cn.sp.controller;

import cn.sp.entity.Film;
import cn.sp.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by 2YSP on 2017/11/11.
 */
@Controller
@RequestMapping("/film")
public class FilmController {

    @Resource
    private FilmService filmService;
    /**
     * 电影名称模糊查询
     * @param s_film
     * @param bindingResult
     * @return
     * @throws Exception
     */
    @RequestMapping("/search")
    public ModelAndView search(@Valid Film s_film, BindingResult bindingResult)throws  Exception{
        ModelAndView mav = new ModelAndView();
        //表单验证
        if (bindingResult.hasErrors()){
            mav.addObject("error",bindingResult.getFieldError().getDefaultMessage());
            mav.addObject("mainPage","film/indexFilm");
            mav.addObject("mainPageKey","#f");
            mav.addObject("title","首页");

        }else {
            List<Film> filmList = filmService.list(s_film, 0, 32);
            mav.addObject("filmList",filmList);
            mav.addObject("total",filmList.size());
            mav.addObject("mainPage","film/result");
            mav.addObject("mainPageKey","#f");
            mav.addObject("title",s_film.getName());
            //数据回显
            mav.addObject("s_film",s_film);
        }
        mav.setViewName("index");
        return mav;
    }
}
