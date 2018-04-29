package cn.sp.service;

import cn.sp.domain.City;

import java.util.List;

/**
 * 城市 ES业务接口类
 * Created by 2YSP on 2017/12/30.
 */
public interface CityService {
    /**
     * 新增
     * @param city
     * @return
     */
    Long saveCity(City city);

    /**
     * AND 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<City> findByDescriptionAndScore(String description, Integer score);

    /**
     * OR 语句查询
     *
     * @param description
     * @param score
     * @return
     */
    List<City> findByDescriptionOrScore(String description, Integer score);

    /**
     * 查询城市描述
     *
     * @param description
     * @return
     */
    List<City> findByDescription(String description);

    /**
     * NOT 语句查询
     *
     * @param description
     * @return
     */
    List<City> findByDescriptionNot(String description);

    /**
     * LIKE 语句查询
     *
     * @param description
     * @return
     */
    List<City> findByDescriptionLike(String description);


    List<City> searchCity(Integer pageNumber,Integer pageSize,String searchContent);

    List<City> findByIdIn(List<Long> ids);

    boolean deleteById(Long id);

    List<City> searchByDescriptionAndScore(String description,int score);

}
