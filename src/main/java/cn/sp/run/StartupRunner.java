package cn.sp.run;

import cn.sp.entity.Film;
import cn.sp.service.FilmService;
import cn.sp.service.LinkService;
import cn.sp.service.WebSiteInfoService;
import cn.sp.service.WebSiteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by 2YSP on 2017/11/11.
 */
@Component("startupRunner")
public class StartupRunner implements ServletContextListener,CommandLineRunner {

    private ServletContext application;


    @Resource
    private FilmService filmService;

    @Resource
    private WebSiteInfoService webSiteInfoService;

    @Resource
    private LinkService linkService;

    @Resource
    private WebSiteService webSiteService;


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        application = sce.getServletContext();

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void run(String... strings) throws Exception {
        this.loadDota();
    }

    /**
     * 加载数据到application缓存中
     */
    public void loadDota(){
        //最新10条电影动态
        application.setAttribute("newestInfoList",webSiteInfoService.list(0,10,null));
        //获取最新10条热门电影
        Film film = new Film();
        film.setHot(1);
        application.setAttribute("newestHotFilmList",filmService.list(film,0,10));
        //// 获取最新32条热门电影 首页显示用到
        application.setAttribute("newestIndexHotFilmList",filmService.list(film,0,32));
        //获取最新10条电影网站收录
        application.setAttribute("newestWebSiteList",webSiteService.newestList(0,10));
        // 获取最新10条电影信息
        application.setAttribute("newestFilmList",filmService.list(null,0,10));
        // 查询所有友情链接
        application.setAttribute("linkList",linkService.listAll());

    }
}
