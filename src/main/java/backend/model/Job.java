package backend.model;

import java.util.HashMap;
import java.util.Vector;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.core.task.TaskExecutor;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import backend.model.job.Prototype;

@Entity
@Inheritance
@JsonTypeInfo(  
	    use = JsonTypeInfo.Id.NAME,  
	    include = JsonTypeInfo.As.PROPERTY,  
	    property = "type")  
@JsonSubTypes({  
    @Type(value = Prototype.class, name = "Prototype") })  
public abstract class Job implements Runnable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;
    private String name;
    private HashMap<String, String> results = new HashMap<String, String>();

	@OneToMany
	@org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Vector<Job> secondaryJobs = new Vector<Job>();
    @Transient
    private TaskExecutor taskExecutor;
    
    public Job()
    {
    	
    }
        
    public Job(TaskExecutor executor)
    {
    	this.taskExecutor = executor;
    }
    
    public long getId()
    {
    	return this.id;
    }
    
    public void setName(String name)
    {
    	this.name = name;
    }
    
    public String getName()
    {
    	return this.name;
    }
    
    public HashMap<String, String> getResults()
    {
    	return this.results;
    }
    
    void setResults(HashMap<String, String> results)
    {
    	this.results = results;
    }
    
    protected void addResult(String key, String value)
    {
    	this.results.put(key, value);
    }
    
    public void addSecondaryJob(Job job)
    {
    	secondaryJobs.add(job);
    }
    
    public void setTaskExecutor(TaskExecutor executor)
    {
    	this.taskExecutor = executor;
    }
    
    public TaskExecutor getTaskExecutor()
    {
    	return this.taskExecutor;
    }
    
    private void runSecondaryJobs()
    {
    	if(this.taskExecutor != null)
	    	for(Job job : this.secondaryJobs)
	    	{
	    		this.taskExecutor.execute(job);
	    	}
    }
    
	public final void run()
	{
		execute();
		runSecondaryJobs();
	}
	
	protected abstract void execute();

}
