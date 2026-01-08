package panter.gmbh.indep.batch;

import java.util.HashMap;
import java.util.Vector;

public interface ICallableTask {

	public boolean runTask();
	public void setTaskParameters(HashMap<String, String> hmParameters);
	public Vector<String> getTaskMessages() ;
	
}
