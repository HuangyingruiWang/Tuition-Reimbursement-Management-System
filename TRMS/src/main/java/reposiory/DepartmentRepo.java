package reposiory;

import models.Department;

import java.util.List;

public interface DepartmentRepo {
    public Department addDepartment(Department input);

    public Department getDepartment(int id);

    public List<Department> getAllDepartments();

    public Department updateDepartment(Department change);

    public Department deleteDepartment(Department input);
}
