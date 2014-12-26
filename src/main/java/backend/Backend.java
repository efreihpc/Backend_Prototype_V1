package backend;

import backend.gpgpu.GPGPUService;

public class Backend {
	
	private GPGPUService m_gpuProcessor = new GPGPUService();
	
	public String stateCheck()
	{
		return m_gpuProcessor.stateCheck();
	}

}
