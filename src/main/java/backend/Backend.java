package backend;

import org.springframework.core.task.TaskExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import backend.model.Job;
import backend.model.JobRepository;
import backend.model.job.Prototype;

public class Backend {
	
	private TaskExecutor m_taskExecutor;
	private ApplicationContext m_context;
	JobRepository m_jobRepository;
	
	public Backend()
	{
		m_context = new ClassPathXmlApplicationContext("Spring-Config.xml");
	    m_taskExecutor = (ThreadPoolTaskExecutor) m_context.getBean("taskExecutor");
	    m_jobRepository = m_context.getBean(JobRepository.class);
	}
	
	public void stateCheck()
	{
		addJob(new Prototype());
	}
	
	public void addJob(Job job)
	{
		m_jobRepository.save(job);
		m_taskExecutor.execute(job);
	}
	
	public Iterable<Job> getAllJobs()
	{
		return m_jobRepository.findAll();
	}

}
