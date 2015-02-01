package backend.model.job;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.core.task.TaskExecutor;

import com.fasterxml.jackson.annotation.JsonTypeName;

import backend.model.Job;
import backend.model.JobRepository;

@Entity
@JsonTypeName("Chain")
public class Persist extends Job implements Runnable {
	
	@Transient
	JobRepository repository;
	
	@OneToOne
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
	Job jobToPersist;
	
	public Persist()
	{
		this.setName("Persist");
	}

	public Persist(TaskExecutor executor, JobRepository repository, Job toPersist)
	{
		super(executor);
		this.setName("Persist");
		this.repository = repository;
		this.jobToPersist = toPersist;
	}
	
	public Persist(JobRepository repository, Job toPersist)
	{
		this.setName("Persist");
		this.repository = repository;
		this.jobToPersist = toPersist;
	}
	
	@Override
	public void execute() {
		System.out.println("Persisting");
		this.repository.save(this.jobToPersist);
	}
	
	public void setJobToPersist(Job job)
	{
		jobToPersist = job;
	}
	
	public JobRepository getRepository()
	{
		return repository;
	}

}
