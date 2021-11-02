package reposiory;

import models.Supervisor;

import java.util.List;

public interface SupervisorRepo {
    public Supervisor addSupervisor(Supervisor input);
    public List<Supervisor> getAllUsersBySupervisor(int supervisor_id);
    public Supervisor getSupervisor(int id);
    public Supervisor updateSupervisor(Supervisor change);
    public Supervisor deleteSupervisor(Supervisor input);
}
