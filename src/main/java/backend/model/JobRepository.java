package backend.model;

import java.util.List;
import backend.model.Job;

import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<Job, Long> {

    List<Job> findByName(String name);
}