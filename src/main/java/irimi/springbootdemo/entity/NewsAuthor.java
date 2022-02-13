package irimi.springbootdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import irimi.springbootdemo.Helper;

@Entity
@Table(name = "news_author")
public class NewsAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "praenomen")
    private String praenomen;

    @Column(name = "nomen")
    private String nomen;

    @Column(name = "cognomen")
    private String cognomen;

    @Column(name = "agnomen")
    private String agnomen;

    @Column(name = "extra_name")
    private String extraName;

    public NewsAuthor() {
    }

    public NewsAuthor(
            String praenomen,
            String nomen,
            String cognomen,
            String agnomen,
            String extraName) {
        setPraenomen(praenomen);
        setNomen(nomen);
        setCognomen(cognomen);
        setAgnomen(agnomen);
        setExtraName(extraName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPraenomen() {
        return praenomen;
    }

    public void setPraenomen(String praenomen) {
        this.praenomen = Helper.normalize(praenomen);
    }

    public String getNomen() {
        return nomen;
    }

    public void setNomen(String nomen) {
        this.nomen = Helper.normalize(nomen);
    }

    public String getCognomen() {
        return cognomen;
    }

    public void setCognomen(String cognomen) {
        this.cognomen = Helper.normalize(cognomen);
    }

    public String getAgnomen() {
        return agnomen;
    }

    public void setAgnomen(String agnomen) {
        this.agnomen = Helper.normalize(agnomen);
    }

    public String getExtraName() {
        return extraName;
    }

    public void setExtraName(String extraName) {
        this.extraName = Helper.normalize(extraName);
    }

    public String getName() {
        StringBuilder buildingName = new StringBuilder();
        if (agnomen == null) {
            if (
                    (praenomen == null) &&
                    (nomen != null) &&
                    (cognomen != null)) {
                buildingName.append(cognomen);
                buildingName.append(' ');
                buildingName.append(nomen);
            } else {
                String[] triaNomina = {
                        praenomen,
                        nomen,
                        cognomen
                };
                for (String triaNominaPart : triaNomina)
                    if (triaNominaPart != null) {
                        if (!buildingName.isEmpty())
                            buildingName.append(' ');
                        buildingName.append(triaNominaPart);
                    }
            }
        } else {
            if (
                    (praenomen != null) &&
                    (nomen == null) &&
                    (cognomen == null)) {
                buildingName.append(praenomen);
                buildingName.append(' ');
            }
            buildingName.append(agnomen);
        }
        if (extraName != null) {
            if (!buildingName.isEmpty())
                buildingName.append(' ');
            buildingName.append(extraName);
        }
        if (buildingName.isEmpty())
            return null;
        String name = buildingName.toString();
        return name;
    }
}
