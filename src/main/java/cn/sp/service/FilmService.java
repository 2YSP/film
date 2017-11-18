package cn.sp.service;

import cn.sp.entity.Film;

import java.util.List;

public interface FilmService {
	/**
	 * 添加或修改
	 * @param film
	 */
	 void save(Film film);

	/**
	 * 分页查询
	 * @param film
	 * @param page
	 * @param size
	 * @return
	 */
	 List<Film> list(Film film, Integer page , Integer size);

	 Long getCount(Film film);

	 void delete(Integer id);

	 Film findById(Integer id);

	/**
	 * 上一个电影
	 * @param id
	 * @return
	 */
	Film getLast(Integer id);

	/**
	 * 获取下一个电影
	 * @param id
	 * @return
	 */
	Film getNext(Integer id);
}
