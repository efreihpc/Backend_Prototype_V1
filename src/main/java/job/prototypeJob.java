package job;

import jcuda.*;
import jcuda.runtime.*;

public class prototypeJob {
	
	public void run()
	{
        Pointer pointer = new Pointer();
        JCuda.cudaMalloc(pointer, 4);
        System.out.println("Pointer: "+pointer);
        JCuda.cudaFree(pointer);
	}

}
