package panter.gmbh.Echo2;

import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU_LIST_ModulContainer;

public class E2_complex_object_safe
{
	
	
	public static FU_LIST_ModulContainer get_FU_LIST_ModulContainer(String cModuleName, Vector<String> vNew) throws myException
	{
		FU_LIST_ModulContainer oComplexObject= (FU_LIST_ModulContainer) bibALL.getSessionValue("@@@@STORE_MODULES@@@@_"+cModuleName);
		if (oComplexObject==null)
		{
			oComplexObject =  new FU_LIST_ModulContainer(cModuleName);
			bibALL.setSessionValue("@@@@STORE_MODULES@@@@_"+cModuleName,oComplexObject);
			vNew.add("1");
		}
		
		return oComplexObject;
	}
	

}
