package backend.gpgpu;

import backend.job.Prototype;

public class GPGPUService {
	
	public String stateCheck()
	{
		Prototype ptt = new Prototype();
		boolean passed = ptt.run();
		
		return "Test "+(passed?"PASSED":"FAILED");
	}

}
