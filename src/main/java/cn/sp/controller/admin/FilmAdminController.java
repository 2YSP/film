package cn.sp.controller.admin;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import cn.sp.entity.WebSiteInfo;
import cn.sp.run.StartupRunner;
import cn.sp.service.WebSiteInfoService;
import cn.sp.util.StringUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import cn.sp.entity.Film;
import cn.sp.service.FilmService;
import cn.sp.util.DateUtil;

/**
 * 电影后台controller
 * @author 2YSP
 *
 */
@RestController
@RequestMapping("/admin/film")
public class FilmAdminController {
	
	@Value("${imageFilePath}")
	private String imageFilePath;
	
	@Resource
	private FilmService filmService;

	@Resource
	private WebSiteInfoService webSiteInfoService;

	@Resource
	private StartupRunner startupRunner;
	
	@RequestMapping("save")
	public Map<String,Object> saveFilm(Film film,@RequestParam("imageFile")MultipartFile file)throws Exception{
		Map<String,Object> resultMap = new HashMap<>();
		if(!file.isEmpty()){
			String fileName = file.getOriginalFilename();//获取原始文件名
			//获取后缀名
			String suffixName = fileName.substring(fileName.lastIndexOf("."));
			String newFileName = DateUtil.getCurrentDateStr() + suffixName;
			//重点
			FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));
			film.setImageName(newFileName);
		}
		film.setPublishDate(new Date());
		filmService.save(film);
		//更新缓存
		startupRunner.loadDota();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping("ckeditorUpload")
	public String ckeditorUpload(@RequestParam("upload")MultipartFile file,String CKEditorFuncNum)throws Exception{
		String fileName = file.getOriginalFilename();//获取原始文件名
		//获取后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		String newFileName = DateUtil.getCurrentDateStr() + suffixName;
		//重点
		FileUtils.copyInputStreamToFile(file.getInputStream(), new File(imageFilePath+newFileName));
//		System.out.println(imageFilePath+newFileName);
		StringBuffer sb=new StringBuffer();
	    sb.append("<script type=\"text/javascript\">");
	    sb.append("window.parent.CKEDITOR.tools.callFunction("+ CKEditorFuncNum + ",'" +"/static/filmImage/"+ newFileName + "','')");
	    sb.append("</script>");
	     
	    return sb.toString();
	}

	@RequestMapping("list")
	public Map<String,Object> list(@RequestParam(value="page",required=false)Integer page,@RequestParam(value="rows",required=false)Integer rows,Film film){
		List<Film> list = filmService.list(film, page - 1, rows);
		Long total = filmService.getCount(film);
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("rows",list);
		resultMap.put("total",total);
		return resultMap;
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("delete")
	public Map<String,Object> delFilms(String ids)throws Exception{
		Map<String, Object> resultMap = new HashMap<>();
		String[] idsStr = ids.split(",");
		boolean flag = true;//是否存在外键关联
		for(String idStr : idsStr){
			int filmId = Integer.parseInt(idStr);
			List<WebSiteInfo> list = webSiteInfoService.getByFilmId(filmId);
			if (list.size() > 0){
				flag = false;
				continue;
			}
			filmService.delete(filmId);
		}
		if (flag){
			resultMap.put("success", true);
		}else {
			resultMap.put("success", false);
			resultMap.put("errMsg","电影动态信息中存在电影信息，不能删除！");
		}
		//更新缓存
		startupRunner.loadDota();
		return resultMap;
	}

	@RequestMapping(value = "findById",method = RequestMethod.POST)
	public Film findById(Integer id){
		return filmService.findById(id);
	}

	/**
	 * 下拉框模糊查询
	 * @param q
	 * @return
	 */
	@RequestMapping("comboList")
	public List<Film> comboList(String q){
		System.out.println(q+"===============");
		if (StringUtil.isEmpty(q)){
			return null;
		}
		Film film = new Film();
		film.setName(q);
		return  filmService.list(film,0,30);
	}

}
