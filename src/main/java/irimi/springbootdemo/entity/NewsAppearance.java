package irimi.springbootdemo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "news_appearance")
public class NewsAppearance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(
            name = "horizontal_position",
            nullable = false)
    private int horizontalPosition;

    @Column(
            name = "vertical_position",
            nullable = false)
    private int verticalPosition;

    @Column(
            name = "highlighted",
            nullable = false)
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean highlighted;

    public NewsAppearance() {
    }

    public NewsAppearance(
            int horizontalPosition,
            int verticalPosition,
            boolean highlighted) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
        this.highlighted = highlighted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHorizontalPosition() {
        return horizontalPosition;
    }

    public void setHorizontalPosition(int horizontalPosition) {
        this.horizontalPosition = horizontalPosition;
    }

    public int getVerticalPosition() {
        return verticalPosition;
    }

    public void setVerticalPosition(int verticalPosition) {
        this.verticalPosition = verticalPosition;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlighted = highlighted;
    }
}
