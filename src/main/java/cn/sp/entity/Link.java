package cn.sp.entity;


import javax.persistence.*;

/**
 * Created by 2YSP on 2017/10/25.
 * 友情链接实体类
 */
@Entity
@Table(name = "t_link")
public class Link {

    @Id
    @GeneratedValue
    private Integer id;//编号，自增

    @Column(length = 500)
    private String name;//名称

    @Column(length = 500)
    private String url;//地址

    private Integer sort;//排序


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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
