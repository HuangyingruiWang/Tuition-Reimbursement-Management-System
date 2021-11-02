package service;

import models.Event;

import java.util.List;

public interface EventService {
    public Event getEvent(int event_id);

    public List<Event> getAllEventsByUserId(int user_id);

    public List<Event> getAllEvents();

    public Event addEvent(Event m);

    public Event updateEvent(Event change, int u_id, int e_id);

    public Event deleteEvent(int u_id, int e_id);
}
