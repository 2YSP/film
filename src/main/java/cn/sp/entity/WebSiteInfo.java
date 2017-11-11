package cn.sp.entity;

import cn.sp.util.CustomDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 2YSP on 2017/10/25.
 * 网站动态更新电影信息实体
 */
@Entity
@Table(name = "t_info")
public class WebSiteInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "filmId")
    private Film film;// 电影

    @ManyToOne
    @JoinColumn(name="webSiteId")
    private WebSite website;// 网站

    @Column(length = 1000)
    private String info;// 信息

    @Column(length = 500)
    private String url;// 具体网址

    private Date publishDate;// 发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public WebSite getWebSite() {
        return website;
    }

    public void setWebSite(WebSite website) {
        this.website = website;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @JsonSerialize(using = CustomDateSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
