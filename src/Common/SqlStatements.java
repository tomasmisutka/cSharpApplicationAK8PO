package Common;

public interface SqlStatements {
    String CONNECTION = "jdbc:mysql://localhost:3306/secretary";
    String SELECT_GLOBAL_CONFIGS_STATEMENT = "SELECT * FROM global_configs";
    String NEW_EMPLOYEE_STATEMENT = "INSERT INTO employees (first_name, last_name, full_name, private_email, job_email," +
            " work_points, work_points_en, is_doctoral, work_load, work_label_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String NEW_SUBJECT_STATEMENT = "INSERT INTO subjects (abbreviation, weeks_count, lectures_count, practises_count, " +
            "seminars_count, classification, teach_language, class_size, study_group_id)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String NEW_STUDY_GROUP_STATEMENT = "INSERT INTO study_groups (abbreviation, study_year, term, students_count, " +
            "study_form, study_type, study_language) VALUES (?, ?, ?, ?, ?, ?, ?)";
}