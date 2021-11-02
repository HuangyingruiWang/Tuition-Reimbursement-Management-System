package reposiory;

import models.Head;
import models.User;

import java.util.List;

public interface HeadRepo {
    public Head addHead(Head input);
    public List<Head> getAllHeads();
    public User getHeadByDepartmentId(int id);
    public Head updateHead(Head change);
    public Head deleteHead(Head input);
}
