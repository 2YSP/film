package cn.sp.controller.admin;

import cn.sp.entity.WebSite;
import cn.sp.entity.WebSiteInfo;
import cn.sp.run.StartupRunner;
import cn.sp.service.WebSiteInfoService;
import cn.sp.service.WebSiteService;
import cn.sp.util.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电影收录管理控制层
 * @author 2YSP
 *
 */
@RestController
@RequestMapping("/admin/webSite")
public class WebSiteAdminController {

	@Resource
	private WebSiteService webSiteService;

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
		WebSite webSite){
		Map<String, Object> resultMap = new HashMap<>();
		List<WebSite> list = webSiteService.list(page-1, rows,webSite);
		Long total = webSiteService.getCount(webSite);
		resultMap.put("rows", list);
		resultMap.put("total", total);
		return resultMap;
	}

	/**
	 * 添加或修改网站信息
	 * @param webSite
	 * @return
	 */
	@RequestMapping("save")
	public Map<String,Object> save(WebSite webSite){
		Map<String, Object> resultMap = new HashMap<>();
		webSiteService.save(webSite);
		resultMap.put("success", true);
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public Map<String,Object> delWebSites(String ids)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		String[] idsStr = ids.split(",");
		boolean flag = true;//是否存在外键关联
		for(String idStr : idsStr){
			int webSiteId = Integer.parseInt(idStr);
			List<WebSiteInfo> webSiteInfos = webSiteInfoService.getByWebSiteId(webSiteId);
			if (webSiteInfos.size() > 0){
				flag = false;
				continue;
			}
			webSiteService.delete(webSiteId);
		}

		if (flag){
			resultMap.put("success", true);
		}else {
			resultMap.put("success", false);
			resultMap.put("errMsg","电影动态信息中存在网址信息，不能删除！");
		}
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}

	/**
	 * 下拉框模糊查询
	 * @param q
	 * @return
	 */
	@RequestMapping("comboList")
	public List<WebSite> comboList(String q){
		if (StringUtil.isEmpty(q)){
			return null;
		}
		WebSite webSite = new WebSite();
		webSite.setUrl(q);
		return  webSiteService.list(0,30,webSite);
	}

}
