package reposiory;

import models.Status;

import java.util.List;

public interface StatusRepo {
    public Status addStatus(Status input);
    public List<Status> getAllStatuss();
    public Status getStatus(int id);
    public Status updateStatus(Status change);
    public Status deleteStatus(Status input);
}
