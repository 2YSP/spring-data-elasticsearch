package cn.sp.service.impl;

import cn.sp.domain.City;
import cn.sp.repository.CityRepository;
import cn.sp.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES操作业务实现类
 * Created by 2YSP on 2017/12/30.
 */
@Service
public class CityESServiceImpl implements CityService {

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer pageNumber = 0;
    private static final Integer pageSize = 10;
    Pageable pageable = new PageRequest(pageNumber, pageSize);

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Long saveCity(City city) {
        City c = cityRepository.save(city);
        return c.getId();
    }

    @Override
    public List<City> findByDescriptionAndScore(String description, Integer score) {
        return cityRepository.findByDescriptionAndScore(description, score);
    }

    @Override
    public List<City> findByDescriptionOrScore(String description, Integer score) {

        return cityRepository.findByDescriptionOrScore(description,score);
    }

    @Override
    public List<City> findByDescription(String description) {
        Page<City> page = cityRepository.findByDescription(description, pageable);
        return page.getContent();
    }

    @Override
    public List<City> findByDescriptionNot(String description) {
        return cityRepository.findByDescriptionNot(description,pageable).getContent();
    }

    @Override
    public List<City> findByDescriptionLike(String description) {
        return cityRepository.findByDescriptionLike(description,pageable).getContent();
    }
}
