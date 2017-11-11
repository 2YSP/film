package cn.sp.controller.admin;

import cn.sp.entity.WebSite;
import cn.sp.entity.WebSiteInfo;
import cn.sp.run.StartupRunner;
import cn.sp.service.WebSiteInfoService;
import cn.sp.service.WebSiteService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影动态信息
 * @author 2YSP
 *
 */
@RestController
@RequestMapping("/admin/webSiteInfo")
public class WebSiteInfoAdminController {

	@Resource
	private WebSiteInfoService webSiteInfoService;

	@Resource
	private StartupRunner startupRunner;

	/**
	 * 
	 * @param page 当前页号
	 * @param rows 每页数量
	 * @return
	 */
	@RequestMapping("list")
	public Map<String, Object> list(@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows,
									WebSiteInfo webSiteInfo){
		Map<String, Object> resultMap = new HashMap<>();
		List<WebSiteInfo> list = webSiteInfoService.list(page-1, rows,webSiteInfo);
		Long total = webSiteInfoService.getCount(webSiteInfo);
		resultMap.put("rows", list);
		resultMap.put("total", total);
		return resultMap;
	}

	/**
	 * 添加电影动态信息
	 * @param webSiteInfo
	 * @return
	 */
	@RequestMapping("save")
	public Map<String,Object> save(WebSiteInfo webSiteInfo){
		Map<String, Object> resultMap = new HashMap<>();
		webSiteInfo.setPublishDate(new Date());
		webSiteInfoService.save(webSiteInfo);
		resultMap.put("success",true);
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}

	@RequestMapping("delete")
	public Map<String,Object> del(String ids){
		Map<String, Object> resultMap = new HashMap<>();
		String[] idsStr = ids.split(",");
		for(String id : idsStr){
			webSiteInfoService.delete(Integer.parseInt(id));
		}
		resultMap.put("success",true);
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}
}
