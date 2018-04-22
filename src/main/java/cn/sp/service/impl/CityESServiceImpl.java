package cn.sp.service.impl;

import cn.sp.domain.City;
import cn.sp.repository.CityRepository;
import cn.sp.service.CityService;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 城市 ES操作业务实现类
 * Created by 2YSP on 2017/12/30.
 */
@Service
public class CityESServiceImpl implements CityService {

    // 分页参数 -> TODO 代码可迁移到具体项目的公共 common 模块
    private static final Integer PAGE_NUMBER = 0;
    private static final Integer PAGE_SIZE = 10;
    Pageable pageable = new PageRequest(PAGE_NUMBER, PAGE_SIZE);

    Logger log = LoggerFactory.getLogger(CityESServiceImpl.class);

    /**搜索模式**/
    String SCORE_MODE_SUM = "sum";//权重分求和模式
    float MIN_SCORE = 10.0f;//由于无相关性的分值默认为1，设置权重分值最小值为10


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

    @Override
    public List<City> searchCity(Integer pageNumber, Integer pageSize, String searchContent) {
        //校验分页参数
        if (pageSize <= 0 || pageSize == null){
            pageSize = PAGE_SIZE;
        }
        if (pageNumber == null || pageNumber < PAGE_NUMBER){
            pageNumber = PAGE_NUMBER;
        }
        log.info("\n searchCity: searchContent ["+searchContent+"] \n");
        SearchQuery searchQuery = getCitySearchQuery(pageNumber,pageSize,searchContent);

        log.info("\n searchCity: searchContent ["+searchContent+"] \n DSL = \n "+searchQuery.getQuery().toString());
        Page<City> page = cityRepository.search(searchQuery);
        return page.getContent();
    }

    /**
     * 根据搜索词构造搜索查询语句
     *
     *
     * @param pageNumber
     * @param pageSize
     * @param searchContent
     * @return
     */
    private SearchQuery getCitySearchQuery(Integer pageNumber, Integer pageSize, String searchContent) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
                .add(QueryBuilders.matchPhraseQuery("name", searchContent), ScoreFunctionBuilders.weightFactorFunction(1000))
                .add(QueryBuilders.matchPhraseQuery("description", searchContent), ScoreFunctionBuilders.weightFactorFunction(500))
                .scoreMode(SCORE_MODE_SUM).setMinScore(MIN_SCORE);

        Pageable pageable = new PageRequest(pageNumber,pageSize);


        return new NativeSearchQueryBuilder()
                .withPageable(pageable)
                .withQuery(functionScoreQueryBuilder).build();

    }
}
