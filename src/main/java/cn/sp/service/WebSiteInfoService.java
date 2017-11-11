package cn.sp.service;

import cn.sp.entity.WebSiteInfo;

import java.util.List;

public interface WebSiteInfoService {

	 List<WebSiteInfo> list(Integer page, Integer pageSize, WebSiteInfo webSiteInfo);
	
	 Long getCount(WebSiteInfo webSiteInfo);

	List<WebSiteInfo> getByFilmId(Integer filmId);

	List<WebSiteInfo> getByWebSiteId(Integer webSiteId);

	void save(WebSiteInfo webSiteInfo);

	void delete(Integer id);
}
