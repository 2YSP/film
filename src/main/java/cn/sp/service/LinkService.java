package cn.sp.service;

import java.util.List;

import cn.sp.entity.Link;

public interface LinkService {

	 List<Link> list(Integer page,Integer pageSize);

	 Long getCount();
	/**
	 * 添加或修改
	 * @param link
	 */
	 void save(Link link);
	
	 void delete(Integer id);

	 List<Link> listAll();
}
