package models;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @Column(name = "e_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int event_id;

    @Column(nullable = false)
    private long date;

    @Column(nullable = false)
    private long last_update;

    @Column(columnDefinition = "numeric(12, 2)")
    private double cost;

    @Column(columnDefinition = "boolean")
    private boolean work_related_justification = true;
    @Column(columnDefinition = "boolean")
    private boolean exceeding_available;

    private String email_address;
    private String location;
    private String description;
    private String actual_grade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "format", referencedColumnName = "g_id")
    private GradeFormat format;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type", referencedColumnName = "t_id")
    private EventType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id", referencedColumnName = "s_id")
    private Status status;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_fk", referencedColumnName = "u_id")
    private User user_fk;

    public Event() {
    }

    public Event(double cost, Status status, User user_fk) {
        this.cost = cost;
        this.status = status;
        this.user_fk = user_fk;
    }

    public Event(long date, long last_update, double cost, boolean work_related_justification, boolean exceeding_available, String email_address, String location, String description, String actual_grade, GradeFormat format, EventType type, Status status, User user_fk) {
        this.date = date;
        this.last_update = last_update;
        this.cost = cost;
        this.work_related_justification = work_related_justification;
        this.exceeding_available = exceeding_available;
        this.email_address = email_address;
        this.location = location;
        this.description = description;
        this.actual_grade = actual_grade;
        this.format = format;
        this.type = type;
        this.status = status;
        this.user_fk = user_fk;
    }

    public Event(int event_id, long date, long last_update, double cost, boolean work_related_justification, boolean exceeding_available, String email_address, String location, String description, String actual_grade, GradeFormat format, EventType type, Status status, User user_fk) {
        this.event_id = event_id;
        this.date = date;
        this.last_update = last_update;
        this.cost = cost;
        this.work_related_justification = work_related_justification;
        this.exceeding_available = exceeding_available;
        this.email_address = email_address;
        this.location = location;
        this.description = description;
        this.actual_grade = actual_grade;
        this.format = format;
        this.type = type;
        this.status = status;
        this.user_fk = user_fk;
    }

    public int getEvent_id() {
        return event_id;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public boolean isExceeding_available() {
        return exceeding_available;
    }

    public void setExceeding_available(boolean exceeding_available) {
        this.exceeding_available = exceeding_available;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getLast_update() {
        return last_update;
    }

    public void setLast_update(long last_update) {
        this.last_update = last_update;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isWork_related_justification() {
        return work_related_justification;
    }

    public void setWork_related_justification(boolean work_related_justification) {
        this.work_related_justification = work_related_justification;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActual_grade() {
        return actual_grade;
    }

    public void setActual_grade(String actual_grade) {
        this.actual_grade = actual_grade;
    }

    public GradeFormat getFormat() {
        return format;
    }

    public void setFormat(GradeFormat format) {
        this.format = format;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getUser_fk() {
        return user_fk;
    }

    public void setUser_fk(User user_fk) {
        this.user_fk = user_fk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return getEvent_id() == event.getEvent_id() && getDate() == event.getDate() && getLast_update() == event.getLast_update() && Double.compare(event.getCost(), getCost()) == 0 && isWork_related_justification() == event.isWork_related_justification() && isExceeding_available() == event.isExceeding_available() && Objects.equals(getEmail_address(), event.getEmail_address()) && Objects.equals(getLocation(), event.getLocation()) && Objects.equals(getDescription(), event.getDescription()) && Objects.equals(getActual_grade(), event.getActual_grade()) && Objects.equals(getFormat(), event.getFormat()) && Objects.equals(getType(), event.getType()) && Objects.equals(getStatus(), event.getStatus()) && Objects.equals(getUser_fk(), event.getUser_fk());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEvent_id(), getDate(), getLast_update(), getCost(), isWork_related_justification(), isExceeding_available(), getEmail_address(), getLocation(), getDescription(), getActual_grade(), getFormat(), getType(), getStatus(), getUser_fk());
    }

    @Override
    public String toString() {
        return "Event{" +
                "event_id=" + event_id +
                ", date=" + date +
                ", last_update=" + last_update +
                ", cost=" + cost +
                ", work_related_justification=" + work_related_justification +
                ", exceeding_available=" + exceeding_available +
                ", email_address='" + email_address + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", actual_grade='" + actual_grade + '\'' +
                ", format=" + format +
                ", type=" + type +
                ", status=" + status +
                ", user_fk=" + user_fk +
                '}';
    }
}
