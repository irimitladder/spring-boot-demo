package irimi.springbootdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import irimi.springbootdemo.entity.NewsTheme;

@Repository
public class NewsThemeJpaDao
implements DataAccessObject<NewsTheme> {

    private EntityManager manager;

    @Autowired
    public NewsThemeJpaDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public NewsTheme getById(int id) {
        NewsTheme theme = manager.find(NewsTheme.class, id);
        return theme;
    }

    @Override
    public List<NewsTheme> getAll() {
        Query dbQuery = manager.createQuery("FROM NewsTheme");
        List<NewsTheme> themes = dbQuery.getResultList();
        return themes;
    }

    @Override
    public void save(NewsTheme theme) {
        NewsTheme existing = manager.merge(theme);
        int existingId = existing.getId();
        theme.setId(existingId);
    }

    @Override
    public void deleteById(int id) {
        Query dbQuery = manager.createQuery("DELETE FROM NewsTheme WHERE id=:id");
        dbQuery.setParameter("id", id);
        dbQuery.executeUpdate();
    }

}
