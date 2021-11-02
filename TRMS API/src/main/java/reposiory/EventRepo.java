package reposiory;

import models.Event;
import models.User;

import java.util.List;

public interface EventRepo {

    public Event addEvent(Event input);

    public Event getEvent(int id);
    public List<Event> getAllEventsByStatus(int code);
    public List<Event> getAllEventsByUser(User user);
    public List<Event> getAllEvents();

    public Event updateEvent(Event change);

    public Event deleteEvent(Event input);

    public long timeupdate();
}
