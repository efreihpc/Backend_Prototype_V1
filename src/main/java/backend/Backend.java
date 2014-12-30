package backend;

import org.springframework.core.task.TaskExecutor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import backend.job.Prototype;

public class Backend {
	
	private TaskExecutor m_taskExecutor;
	private ApplicationContext m_context;
	
	public Backend()
	{
		m_context = new ClassPathXmlApplicationContext("Spring-Config.xml");
	    m_taskExecutor = (ThreadPoolTaskExecutor) m_context.getBean("taskExecutor");
	}
	
	public void stateCheck()
	{
		m_taskExecutor.execute(new Prototype());
	}

}
