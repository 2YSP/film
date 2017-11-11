package cn.sp.entity;

import javax.persistence.*;

/**
 * Created by 2YSP on 2017/10/25.
 * 电影网站实体类
 */
@Entity
@Table(name = "t_webSite")
public class WebSite {

    @Id
    @GeneratedValue
    private Integer id;//编号

    @Column(length = 300)
    private String url;//网址

    @Column(length = 100)
    private String name;//网站名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
