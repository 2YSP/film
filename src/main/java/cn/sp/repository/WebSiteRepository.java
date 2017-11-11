package cn.sp.repository;

import cn.sp.entity.WebSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 收录电影管理repository,
 * 多条件查询要继承JpaSpecificationExecutor接口
 * @author 2YSP
 *
 */
public interface WebSiteRepository extends JpaRepository<WebSite,Integer>,JpaSpecificationExecutor<WebSite>{

	
}
