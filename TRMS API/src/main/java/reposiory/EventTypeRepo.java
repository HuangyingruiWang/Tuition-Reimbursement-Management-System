package reposiory;

import models.EventType;

import java.util.List;

public interface EventTypeRepo {
    public EventType addEventType(EventType input);
    public List<EventType> getAllEventTypes();
    public EventType getEventType(int id);
    public EventType updateEventType(EventType change);
    public EventType deleteEventType(EventType input);
}
