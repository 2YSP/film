package cn.sp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.sp.entity.Film;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 电影Repository接口
 * @author 2YSP
 *
 */
public interface FilmRepository extends JpaRepository<Film, Integer>,JpaSpecificationExecutor<Film>{
    @Query(value = "SELECT * FROM t_film WHERE id < ?1 ORDER BY id DESC LIMIT 1",nativeQuery = true)
    Film getLast(Integer id);

    @Query(value = "SELECT * FROM t_film WHERE id > ?1 ORDER BY id  LIMIT 1",nativeQuery = true)
    Film getNext(Integer id);
}
