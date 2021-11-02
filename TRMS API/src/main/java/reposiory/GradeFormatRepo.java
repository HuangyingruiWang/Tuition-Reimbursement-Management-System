package reposiory;

import models.GradeFormat;

import java.util.List;

public interface GradeFormatRepo {
    public GradeFormat addGradeFormat(GradeFormat input);
    public List<GradeFormat> getAllGradeFormats();
    public GradeFormat getGradeFormat(int id);
    public GradeFormat updateGradeFormat(GradeFormat change);
    public GradeFormat deleteGradeFormat(GradeFormat input);
}
