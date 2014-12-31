package backend.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Inheritance
public abstract class Job implements Runnable {
	
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    private String name;
    
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
    
	public abstract void run();

}
