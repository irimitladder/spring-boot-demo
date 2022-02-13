package irimi.springbootdemo.entity;

import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import irimi.springbootdemo.Helper;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinTable(
            name = "news_news_author",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "news_author_id"))
    private List<NewsAuthor> authors = new ArrayList<>();

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH
    })
    @JoinColumn(
            name = "theme_id",
            referencedColumnName = "id")
    private NewsTheme theme;

    @Column(
            name = "title",
            unique = true)
    private String title;

    @Column(
            name = "text",
            columnDefinition = "TEXT")
    private String text;

    @Column(name = "publication_date")
    private OffsetDateTime publicationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "appearance_id",
            referencedColumnName = "id")
    private NewsAppearance appearance;

    public News() {
        setPublicationDate((OffsetDateTime) null);
    }

    public News(
            String title,
            String text,
            OffsetDateTime publicationDate) {
        setTitle(title);
        setText(text);
        setPublicationDate(publicationDate);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<NewsAuthor> getAuthors() {
        Comparator<NewsAuthor> authorComparator = Comparator.comparing(NewsAuthor::getName);
        Set<NewsAuthor> authors = new TreeSet<>(authorComparator);
        for (NewsAuthor author : this.authors)
            authors.add(author);
        return authors;
    }

    public void setAuthors(List<NewsAuthor> authors) {
        this.authors = authors == null ?
                new ArrayList<>() :
                authors;
    }

    public NewsTheme getTheme() {
        return theme;
    }

    public void setTheme(NewsTheme theme) {
        this.theme = theme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = Helper.normalize(title);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = Helper.normalize(text);
    }

    public OffsetDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(OffsetDateTime publicationDate) {
        if (publicationDate == null) {
            ZonedDateTime publicationZonedDate = null;
            setPublicationDate(publicationZonedDate);
            return;
        }
        this.publicationDate = publicationDate;
    }

    public void setPublicationDate(ZonedDateTime publicationDate) {
        if (publicationDate == null)
            publicationDate = ZonedDateTime.now(Helper.ROMAN_TIME_ZONE_ID);
        OffsetDateTime publicationNonzonedDateAndTime = publicationDate.toOffsetDateTime();
        setPublicationDate(publicationNonzonedDateAndTime);
    }

    public NewsAppearance getAppearance() {
        return appearance;
    }

    public void setAppearance(NewsAppearance appearance) {
        this.appearance = appearance;
    }

    public String getPublicationRomanDateAndTime() {
        ZonedDateTime publicationRomanDateAndTime = publicationDate.atZoneSimilarLocal(Helper.ROMAN_TIME_ZONE_ID);
        String publicationRomanDateAndTimeString = publicationRomanDateAndTime.format(Helper.DATE_AND_TIME_STRING_FORMATTER);
        return publicationRomanDateAndTimeString;
    }
}
