package backend.model.job;

import org.springframework.core.task.TaskExecutor;

import backend.model.Job;
import backend.model.JobRepository;

public class Persist extends Job implements Runnable {
	
	JobRepository repository;
	Job jobToPersist;
	
	public Persist()
	{
		
	}

	public Persist(TaskExecutor executor, JobRepository repository, Job toPersist)
	{
		super(executor);
		this.repository = repository;
		this.jobToPersist = toPersist;
	}
	
	@Override
	public void execute() {
		System.out.println("Persisting");
		this.repository.save(this.jobToPersist);
	}

}
