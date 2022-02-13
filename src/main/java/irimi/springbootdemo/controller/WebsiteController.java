package irimi.springbootdemo.controller;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import irimi.springbootdemo.Helper;
import irimi.springbootdemo.entity.News;
import irimi.springbootdemo.service.NewsService;

@Controller
public class WebsiteController {

    private static final Comparator<News> NEWS_COMPARATOR = Comparator.comparingInt(unsortedNews -> unsortedNews.getAppearance().getHorizontalPosition());

    @Autowired
    private NewsService newsService;

    @Value("${website.title}")
    private String title;

    @GetMapping("/")
    public String showHomePage(Model model) {
        setModelDefaultAttributes(model);
        List<News> news = newsService.getAll();
        List<Set<News>> sortedNews = sortNews(news);
        model.addAttribute("news", sortedNews);
        return "home";
    }

    @GetMapping("/{id}")
    public String showNewsPage(
            Model model,
            @PathVariable(value = "id") int id) {
        setModelDefaultAttributes(model);
        News news = newsService.get(id);
        if (news == null)
            return "redirect:/";
        model.addAttribute("news", news);
        return "news";
    }

    @GetMapping("/updateNews")
    public String showNewsUpdatingPage(
            Model model,
            @RequestParam("id") int id) {
        setModelDefaultAttributes(model);
        News news = newsService.get(id);
        if (news == null)
            return "redirect:/";
        model.addAttribute("news", news);
        return "add-news";
    }

    @GetMapping("/addNews")
    public String showNewsAddingPage(Model model) {
        setModelDefaultAttributes(model);
        News news = new News();
        model.addAttribute("news", news);
        return "add-news";
    }

    @PostMapping("/saveNews")
    public String saveNews(@ModelAttribute("news") News news) {
        newsService.saveWithCompletion(news);
        return "redirect:/";
    }

    @GetMapping("/deleteNews")
    public String deleteNews(
            Model model,
            @RequestParam("id") int id) {
        newsService.delete(id);
        return "redirect:/";
    }

    private void setModelDefaultAttributes(Model model) {
        model.addAttribute("title", title);
        ZonedDateTime currentRomanDateAndTime = ZonedDateTime.now(Helper.ROMAN_TIME_ZONE_ID);
        String currentRomanDateAndTimeString = currentRomanDateAndTime.format(Helper.DATE_AND_TIME_STRING_FORMATTER);
        model.addAttribute("currentRomanDateAndTime", currentRomanDateAndTimeString);
    }

    private static List<Set<News>> sortNews(List<News> news) {
        NavigableMap<Integer, Set<News>> sortedNewsMap = new TreeMap<>();
        for (News unsortedNews : news) {
            int newsVerticalPosition = unsortedNews.getAppearance().getVerticalPosition();
            Set<News> horizontallySortedNews = sortedNewsMap.get(newsVerticalPosition);
            if (horizontallySortedNews == null) {
                horizontallySortedNews = new TreeSet<>(NEWS_COMPARATOR);
                horizontallySortedNews.add(unsortedNews);
                sortedNewsMap.put(newsVerticalPosition, horizontallySortedNews);
            } else
                horizontallySortedNews.add(unsortedNews);
        }
        Collection<Set<News>> sortedNewsCollection = sortedNewsMap.values();
        List<Set<News>> sortedNews = new ArrayList<Set<News>>(sortedNewsCollection);
        return sortedNews;
    }
}
