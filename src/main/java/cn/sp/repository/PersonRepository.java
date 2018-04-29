package cn.sp.repository;

import cn.sp.domain.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by 2YSP on 2018/4/29.
 */
public interface PersonRepository extends ElasticsearchRepository<Person,Integer> {
}
