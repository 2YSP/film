package cn.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sp.entity.Link;
/**
 * 友情链接repository
 * @author 2YSP
 *
 */
public interface LinkRepository extends JpaRepository<Link,Integer>{

	
}
