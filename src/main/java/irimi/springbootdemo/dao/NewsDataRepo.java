package irimi.springbootdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import irimi.springbootdemo.entity.News;

@RepositoryRestResource(path = "news")
public interface NewsDataRepo
        extends JpaRepository<News, Integer> {

}
