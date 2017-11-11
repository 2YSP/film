package cn.sp.entity;

import cn.sp.util.CustomDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 2YSP on 2017/10/25.
 * 电影实体类
 */
@Entity
@Table(name = "t_film")
public class Film {

    @Id
    @GeneratedValue
    private Integer id;//编号

    @Column(length = 200)
    private String name;//电影名称

    @Column(length = 500)
    private String title;//帖子名称

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;//帖子内容
    @Column(length = 300)
    private String imageName;//电影图片

    private Integer hot;//是否热门电影 1：是 0：非热门

    private Date publishDate;//发布日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    @JsonSerialize(using = CustomDateTimeSerializer.class)
    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }
}
