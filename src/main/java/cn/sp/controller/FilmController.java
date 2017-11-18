package cn.sp.controller;

import cn.sp.entity.Film;
import cn.sp.service.FilmService;
import cn.sp.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
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

    /**
     * 分页查询电影
     * @param page
     * @return
     */
    @RequestMapping("/list/{page}")
    public ModelAndView list(@PathVariable(value = "page",required = false)Integer page){
        ModelAndView mav = new ModelAndView();
        List<Film> filmList = filmService.list(null, page-1, 20);
        long total = filmService.getCount(null);
        mav.addObject("filmList",filmList);
        mav.addObject("mainPage","film/list");
        mav.addObject("mainPageKey","#f");
        mav.addObject("title","电影收录");
        mav.addObject("pageCode", PageUtil.genPagination("/film/list",Integer.valueOf(total+""),page,20));
        mav.setViewName("index");
        return mav;
    }

    /**
     * 电影详细信息
     * @param id
     * @return
     */
    @RequestMapping("{id}")
    public ModelAndView detail(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView();
        Film film = filmService.findById(id);
        mav.addObject("film",film);
        mav.addObject("mainPage","film/view");
        mav.addObject("mainPageKey","#f");
        mav.addObject("title",film.getTitle());
        mav.addObject("pageCode", getUpAndDownCode(filmService.getLast(id),filmService.getNext(id)));
        mav.setViewName("index");
        return mav;
    }

    /**
     * 获取上一个电影和下一个电影html
     * @param lastFilm
     * @param nextFilm
     * @return
     */
    private String getUpAndDownCode(Film lastFilm,Film nextFilm){
        StringBuffer pageCode=new StringBuffer();
        if(lastFilm==null || lastFilm.getId()==null){
            pageCode.append("<p>上一篇：没有了</p>");
        }else{
            pageCode.append("<p>上一篇：<a href='/film/"+lastFilm.getId()+"'>"+lastFilm.getTitle()+"</a></p>");
        }
        if(nextFilm==null || nextFilm.getId()==null){
            pageCode.append("<p>下一篇：没有了</p>");
        }else{
            pageCode.append("<p>下一篇：<a href='/film/"+nextFilm.getId()+"'>"+nextFilm.getTitle()+"</a></p>");
        }
        return pageCode.toString();
    }
}
