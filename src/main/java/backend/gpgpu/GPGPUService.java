package backend.gpgpu;

import backend.job.Prototype;

public class GPGPUService {
	
	public String stateCheck()
	{
		Prototype ptt = new Prototype();
		ptt.run();
		
		return "all upp and running";
	}

}
