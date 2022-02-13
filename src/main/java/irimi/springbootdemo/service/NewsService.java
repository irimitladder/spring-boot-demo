package irimi.springbootdemo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import irimi.springbootdemo.dao.NewsAuthorHibernateDao;
import irimi.springbootdemo.dao.NewsDataRepo;
import irimi.springbootdemo.dao.NewsThemeJpaDao;
import irimi.springbootdemo.entity.News;
import irimi.springbootdemo.entity.NewsAuthor;
import irimi.springbootdemo.entity.NewsTheme;

@Service
public class NewsService
        implements irimi.springbootdemo.service.Service<News> {

    private NewsDataRepo dataRepo;
    private NewsAuthorHibernateDao authorDao;
    private NewsThemeJpaDao themeDao;

    @Autowired
    public NewsService(
            NewsDataRepo dataRepo,
            NewsAuthorHibernateDao authorDao,
            NewsThemeJpaDao themeDao) {
        this.dataRepo = dataRepo;
        this.authorDao = authorDao;
        this.themeDao = themeDao;
    }

    public News get(int id) {
        Optional<News> packedNews = dataRepo.findById(id);
        News news = packedNews.orElse(null);
        return news;
    }

    public List<News> getAll() {
        List<News> news = dataRepo.findAll();
        // TODO Maybe null checking not needed, then fix later
        if (news == null)
            news = new ArrayList<>();
        return news;
    }

    public void save(News news) {
        if (news == null)
            return;
        dataRepo.save(news);
    }

    public void saveWithCompletion(News news) {
        if (news == null)
            return;

        // Set random authors (but not too many)
        List<NewsAuthor> authors = authorDao.getAll();
        int authorCount = (int) (Math.random() * 3) + 1;
        while (authors.size() > authorCount) {
            int deletingAuthorNumber = (int) (Math.random() * authors.size());
            authors.remove(deletingAuthorNumber);
        }
        news.setAuthors(authors);

        // Set random theme
        List<NewsTheme> themes = themeDao.getAll();
        int randomThemeNumber = (int) (Math.random() * themes.size());
        NewsTheme theme = themes.get(randomThemeNumber);
        news.setTheme(theme);

        save(news);
    }

    public void delete(int id) {
     // TODO Maybe if exists checking not needed, then fix later
        if (dataRepo.existsById(id))
            dataRepo.deleteById(id);
    }
}
