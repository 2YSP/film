package cn.sp.service;

import cn.sp.entity.WebSite;

import java.util.List;

public interface WebSiteService {

	 List<WebSite> list(Integer page, Integer pageSize, WebSite website);
	
	 Long getCount(WebSite website);

    void save(WebSite link);

	void delete(int id);

	/**
	 * 获取最新电影网址
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<WebSite> newestList(Integer page, Integer pageSize);
}
