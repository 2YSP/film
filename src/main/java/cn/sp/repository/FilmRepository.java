package cn.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sp.entity.Film;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * 电影Repository接口
 * @author 2YSP
 *
 */
public interface FilmRepository extends JpaRepository<Film, Integer>,JpaSpecificationExecutor<Film>{

	
}
