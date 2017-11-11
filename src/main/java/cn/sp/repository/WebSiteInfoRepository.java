package cn.sp.repository;

import cn.sp.entity.WebSiteInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * 电影动态信息管理repository,
 * 多条件查询要继承JpaSpecificationExecutor接口
 * @author 2YSP
 *
 */
public interface WebSiteInfoRepository extends JpaRepository<WebSiteInfo,Integer>,JpaSpecificationExecutor<WebSiteInfo>{

    @Query(value = "SELECT * FROM t_info WHERE film_id = ?1",nativeQuery = true)
    List<WebSiteInfo> getByFilmId(Integer filmId);

    @Query(value = "SELECT * FROM t_info WHERE web_site_id = ?1",nativeQuery = true)
    List<WebSiteInfo> getByWebSiteId(Integer webSiteId);
}
