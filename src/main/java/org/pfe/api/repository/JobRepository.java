package org.pfe.api.repository;

import org.pfe.api.bean.Job;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JobRepository {


    private final JdbcTemplate jdbcTemplate;

    public JobRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Job job) {
        String sql = "INSERT INTO jobs (title, company, location, salary, type, remote, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                job.getTitle(),
                job.getCompany(),
                job.getLocation(),
                job.getSalary(),
                job.getType(),
                job.getRemote(),
                job.getDescription()
        );
    }

    public List<Job> findAll() {
        String sql = "SELECT * FROM jobs";
        return jdbcTemplate.query(sql, jobRowMapper);
    }

    public Job findById(int id) {
        String sql = "SELECT * FROM jobs WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, jobRowMapper, id);
    }

    public int update(Job job) {
        String sql = "UPDATE jobs SET title = ?, company = ?, location = ?, salary = ?, type = ?, remote = ?, description = ? WHERE id = ?";
        return jdbcTemplate.update(sql,
                job.getTitle(),
                job.getCompany(),
                job.getLocation(),
                job.getSalary(),
                job.getType(),
                job.getRemote(),
                job.getDescription(),
                job.getId()
        );
    }

    public int delete(int id) {
        String sql = "DELETE FROM jobs WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    private final RowMapper<Job> jobRowMapper = (rs, rowNum) -> new Job(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getString("company"),
            rs.getString("location"),
            rs.getString("salary"),
            rs.getString("type"),
            rs.getString("remote"),
            rs.getString("description")
    );

    public List<Job> getAllJobs() {
        return findAll();
    }

    public void addJob(Job job) {
        save(job);
    }

    public List<Job> searchByTitleOrDescription(String keyword) {

        String sql = "SELECT * FROM jobs WHERE LOWER(title) LIKE ? OR LOWER(description) LIKE ?";
        String pattern = "%" + keyword.toLowerCase() + "%";
        return jdbcTemplate.query(sql, jobRowMapper, pattern, pattern);
    }
}
