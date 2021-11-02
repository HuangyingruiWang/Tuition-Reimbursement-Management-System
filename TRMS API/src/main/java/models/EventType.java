package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "eventtype")
public class EventType {
    @Id
    @Column(name = "t_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int type_id;

    @Column(name = "type")
    private String eventtype;

    @Column(columnDefinition = "numeric(4,2)")
    private double percetage;

    public EventType() {
    }

    public EventType(int type_id) {
        this.type_id = type_id;
    }

    public EventType(String eventtype, double percetage) {
        this.eventtype = eventtype;
        this.percetage = percetage;
    }

    public EventType(int type_id, String eventtpye, double percetage) {
        this.type_id = type_id;
        this.eventtype = eventtpye;
        this.percetage = percetage;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getEventtype() {
        return eventtype;
    }

    public void setEventtype(String eventtpye) {
        this.eventtype = eventtpye;
    }

    public double getPercetage() {
        return percetage;
    }

    public void setPercetage(double percetage) {
        this.percetage = percetage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventType eventType = (EventType) o;
        return getType_id() == eventType.getType_id() && Double.compare(eventType.getPercetage(), getPercetage()) == 0 && Objects.equals(getEventtype(), eventType.getEventtype());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getType_id(), getEventtype(), getPercetage());
    }

    @Override
    public String toString() {
        return "EventType{" +
                "type_id=" + type_id +
                ", eventtpye='" + eventtype + '\'' +
                ", percetage=" + percetage +
                '}';
    }
}
