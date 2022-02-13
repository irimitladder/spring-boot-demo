package irimi.springbootdemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import irimi.springbootdemo.entity.NewsAuthor;

@Repository
public class NewsAuthorHibernateDao
        implements DataAccessObject<NewsAuthor> {

    private EntityManager manager;

    @Autowired
    public NewsAuthorHibernateDao(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public NewsAuthor getById(int id) {
        Session dbSession = manager.unwrap(Session.class);
        NewsAuthor author = dbSession.get(NewsAuthor.class, id);
        return author;
    }

    @Override
    public List<NewsAuthor> getAll() {
        Session dbSession = manager.unwrap(Session.class);
        Query<NewsAuthor> dbSessionQuery = dbSession.createQuery("FROM NewsAuthor", NewsAuthor.class);
        List<NewsAuthor> authors = dbSessionQuery.getResultList();
        return authors;
    }

    @Override
    public void save(NewsAuthor author) {
        Session dbSession = manager.unwrap(Session.class);
        dbSession.saveOrUpdate(author);
    }

    @Override
    public void deleteById(int id) {
        Session dbSession = manager.unwrap(Session.class);
        Query<?> dbSessionQuery = dbSession.createQuery("DELETE FROM NewsAuthor WHERE id=:id");
        dbSessionQuery.setParameter("id", id);
        dbSessionQuery.executeUpdate();
    }

}
