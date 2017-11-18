package cn.sp.service.impl;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import cn.sp.entity.WebSite;
import cn.sp.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import cn.sp.entity.Film;
import cn.sp.repository.FilmRepository;
import cn.sp.service.FilmService;

import java.util.List;

@Service("filmService")
public class FilmServiceImpl implements FilmService{

	@Resource
	private FilmRepository filmRepository;
	
	@Override
	public void save(Film film) {
		filmRepository.save(film);
	}

	@Override
	public List<Film> list(Film film, Integer page, Integer size) {
		Pageable pageable = new PageRequest(page,size, Sort.Direction.DESC,"publishDate");
		Page<Film> page2 = filmRepository.findAll((Root<Film> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) ->{
			Predicate predicate = criteriaBuilder.conjunction();
			if (film != null){
				if (StringUtil.isNotEmpty(film.getName())){
					predicate.getExpressions().add(criteriaBuilder.like(root.get("name"),"%"+film.getName().trim()+"%"));
				}

				if (film.getHot() != null && film.getHot() == 1){
					predicate.getExpressions().add(criteriaBuilder.equal(root.get("hot"),film.getHot()));
				}
			}

			return predicate;
		},pageable);
		return page2.getContent();
	}

	@Override
	public Long getCount(Film film) {
		long count =  filmRepository.count(
				(Root<Film> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) -> {
					Predicate predicate = criteriaBuilder.conjunction();
					if (film != null){
						if (StringUtil.isNotEmpty(film.getName())) {
							predicate.getExpressions().add(criteriaBuilder.like(root.get("name"), "%" + film.getName().trim() + "%"));
						}
						if (film.getHot() != null && film.getHot() == 1){
							predicate.getExpressions().add(criteriaBuilder.equal(root.get("hot"),film.getHot()));
						}
					}

					return predicate;
				}
		);
		return count;
	}

	@Override
	public void delete(Integer id) {
		filmRepository.delete(id);
	}

	@Override
	public Film findById(Integer id) {
		return filmRepository.findOne(id);
	}

	@Override
	public Film getLast(Integer id) {
		return filmRepository.getLast(id);
	}

	@Override
	public Film getNext(Integer id) {
		return filmRepository.getNext(id);
	}

}
