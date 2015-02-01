package backend.model.job;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;

import org.springframework.core.task.TaskExecutor;

import com.fasterxml.jackson.annotation.JsonTypeName;

import backend.model.Job;

@Entity
@JsonTypeName("Chain")
public class Chain extends Job {
	
	@OneToOne
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
	Job lastJob;

	public Chain()
	{
		this.setName("Chain");
	}
	
	public Chain(TaskExecutor executor)
	{
		super(executor);
		this.setName("Chain");
	}
	
	protected void execute() {

	}
	
	public void add(Job nextJob)
	{
		if(lastJob == null)
		{
			lastJob = nextJob;
			addSecondaryJob(nextJob);
		}
		else
		{
			lastJob.addSecondaryJob(nextJob);
			lastJob = nextJob;
		}
	}

}
