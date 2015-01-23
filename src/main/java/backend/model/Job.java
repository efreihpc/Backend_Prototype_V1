package backend.model;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.*;

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
    protected HashMap<String, String> results = new HashMap<String, String>();
    
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
