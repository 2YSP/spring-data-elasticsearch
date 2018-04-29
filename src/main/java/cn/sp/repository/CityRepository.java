package cn.sp.repository;

import cn.sp.domain.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * ES操作类
 * Created by 2YSP on 2017/12/30.
 */
public interface CityRepository extends ElasticsearchRepository<City,Long> {
    /**
     * AND 语句查询
     * @param description
     * @param score
     * @return
     */
    List<City> findByDescriptionAndScore(String description, Integer score);

    /**
     * OR 语句查询
     * @param description
     * @param score
     * @return
     */
    List<City> findByDescriptionOrScore(String description,Integer score);

    /**
     * 根据描述分页查询
     * @param description
     * @param page
     * @return
     */
    Page<City> findByDescription(String description, Pageable page);

    /**
     * NOT 语句查询
     * @param description
     * @param page
     * @return
     */
    Page<City> findByDescriptionNot(String description, Pageable page);

    /**
     * LIKE 语句查询
     * @param description
     * @param page
     * @return
     */
    Page<City> findByDescriptionLike(String description, Pageable page);

    /**
     * 根据范围id查询
     * @param ids
     * @return
     */
    List<City> findByIdIn(List<Long> ids);

    /**
     * 根据自定义DSL语句查询
     * @param description
     * @param score
     * @return
     */
    @Query("{\"query\":{\"bool\":{\"must\":{\"match\":{\"description\":\"?0\"}},\"filter\":{\"range\":{\"score\":{\"gt\":?1}}}}}}")
    List<City> searchByDescriptionAndScore(String description,int score);
}
