package cn.sp.service.impl;

import cn.sp.entity.WebSite;
import cn.sp.entity.WebSiteInfo;
import cn.sp.repository.WebSiteInfoRepository;
import cn.sp.service.WebSiteInfoService;
import cn.sp.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by 2YSP on 2017/11/5.
 */
@Service("webSiteInfoService")
public class WebSiteInfoServiceImpl implements WebSiteInfoService {

    @Resource
    private WebSiteInfoRepository webSiteInfoRepository;

    @Override
    public List<WebSiteInfo> list(Integer page, Integer pageSize, WebSiteInfo webSiteInfo) {
        Pageable pageable = new PageRequest(page,pageSize, Sort.Direction.DESC,"publishDate");//

        Page<WebSiteInfo> page2 = webSiteInfoRepository.findAll((Root<WebSiteInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->{
            Predicate predicate = criteriaBuilder.conjunction();
            if (webSiteInfo != null && StringUtil.isNotEmpty(webSiteInfo.getInfo())){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("info"),"%"+webSiteInfo.getInfo().trim()+"%"));
            }

            return predicate;
        },pageable);
        return page2.getContent();
    }

    @Override
    public Long getCount(WebSiteInfo webSiteInfo) {
        long count =  webSiteInfoRepository.count(
                (Root<WebSiteInfo> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
                    Predicate predicate = criteriaBuilder.conjunction();
                    if (webSiteInfo != null &&StringUtil.isNotEmpty(webSiteInfo.getInfo())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("info"), "%" + webSiteInfo.getInfo().trim() + "%"));
                    }

                    return predicate;
                }
        );
        return count;
    }

    @Override
    public List<WebSiteInfo> getByFilmId(Integer filmId) {
        return webSiteInfoRepository.getByFilmId(filmId);
    }

    @Override
    public List<WebSiteInfo> getByWebSiteId(Integer webSiteId) {
        return webSiteInfoRepository.getByWebSiteId(webSiteId);
    }

    @Override
    public void save(WebSiteInfo webSiteInfo) {
        webSiteInfoRepository.save(webSiteInfo);
    }

    @Override
    public void delete(Integer id) {
        webSiteInfoRepository.delete(id);
    }
}
