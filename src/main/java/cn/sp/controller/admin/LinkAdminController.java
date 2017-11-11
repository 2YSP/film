package cn.sp.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.sp.run.StartupRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.sp.entity.Link;
import cn.sp.service.LinkService;

/**
 * 友情链接控制层
 * @author 2YSP
 *
 */
@RestController
@RequestMapping("/admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;

	@Resource
	private StartupRunner startupRunner;
	/**
	 * 
	 * @param page 当前页号
	 * @param rows 每页数量
	 * @return
	 */
	@RequestMapping("list")
	public Map<String, Object> list(@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows){
		Map<String, Object> resultMap = new HashMap<>();
		List<Link> list = linkService.list(page-1, rows);
		Long total = linkService.getCount();
		resultMap.put("rows", list);
		resultMap.put("total", total);
		return resultMap;
	}
	/**
	 * 添加或修改友情链接
	 * @param link
	 * @return
	 */
	@RequestMapping("save")
	public Map<String,Object> save(Link link){
		Map<String, Object> resultMap = new HashMap<>();
		linkService.save(link);
		resultMap.put("success", true);
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}
	
	@RequestMapping("delete")
	public Map<String,Object> delLinks(String ids)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		String[] idsStr = ids.split(",");
		for(String idStr : idsStr){
			linkService.delete(Integer.parseInt(idStr));
		}
		resultMap.put("success", true);
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}
}
