package backend;

import org.springframework.core.task.TaskExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import backend.model.Job;
import backend.model.JobRepository;
import backend.model.job.Chain;
import backend.model.job.Persist;
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
		Prototype job = new Prototype(m_taskExecutor);
		Persist persist = new Persist(m_taskExecutor, m_jobRepository, job);
		job.addSecondaryJob(persist);
		
		Prototype job2 = new Prototype(m_taskExecutor);
		Persist persist2 = new Persist(m_taskExecutor, m_jobRepository, job);
		job2.addSecondaryJob(persist2);
		
		Chain chainJob = new Chain(m_taskExecutor);
		chainJob.add(job);
		chainJob.add(job2);
		
		addJob(chainJob);
		
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
