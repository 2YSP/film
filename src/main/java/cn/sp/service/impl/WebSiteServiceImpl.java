package cn.sp.service.impl;

import cn.sp.entity.WebSite;
import cn.sp.repository.WebSiteRepository;
import cn.sp.service.WebSiteService;
import cn.sp.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * 收录电影管理service
 * Created by 2YSP on 2017/11/4.
 */
@Service("webSiteService")
public class WebSiteServiceImpl implements WebSiteService {

    @Resource
    private WebSiteRepository webSiteRepository;

    /**
     * 根据条件分页查询
     * @param page
     * @param pageSize
     * @param website
     * @return
     */
    @Override
    public List<WebSite> list(Integer page, Integer pageSize, WebSite website) {
        Pageable pageable = new PageRequest(page,pageSize, Sort.Direction.ASC,"id");//根据id升序排列

//        webSiteRepository.findAll(new Specification<WebSite>() {
//            @Override
//            public Predicate toPredicate(Root<WebSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
//                return null;
//            }
//        },pageable);
        Page<WebSite> webSitePage = webSiteRepository.findAll((Root<WebSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->{
            Predicate predicate = criteriaBuilder.conjunction();
            if (StringUtil.isNotEmpty(website.getName())){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("name"),"%"+website.getName().trim()+"%"));
            }
            if (StringUtil.isNotEmpty(website.getUrl())){
                predicate.getExpressions().add(criteriaBuilder.like(root.get("url"),"%"+website.getUrl().trim()+"%"));
            }
            return predicate;
        },pageable);
        return webSitePage.getContent();
    }


    @Override
    public Long getCount(WebSite website) {
       long count =  webSiteRepository.count(
                (Root<WebSite> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
                    Predicate predicate = criteriaBuilder.conjunction();
                    if (StringUtil.isNotEmpty(website.getName())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + website.getName().trim() + "%"));
                    }
                    if (StringUtil.isNotEmpty(website.getUrl())) {
                        predicate.getExpressions().add(criteriaBuilder.like(root.get("url"), "%" + website.getUrl().trim() + "%"));
                    }
                    return predicate;
                }
        );
        return count;
    }

    @Override
    public void save(WebSite link) {
        webSiteRepository.save(link);
    }

    @Override
    public void delete(int id) {
        webSiteRepository.delete(id);
    }

    @Override
    public List<WebSite> newestList(Integer page, Integer pageSize) {
        Pageable pageable = new PageRequest(page,pageSize, Sort.Direction.DESC,"id");

        return webSiteRepository.findAll(pageable).getContent();
    }
}
