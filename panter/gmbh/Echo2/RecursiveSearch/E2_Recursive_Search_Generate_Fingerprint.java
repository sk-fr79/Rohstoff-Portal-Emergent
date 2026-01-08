package panter.gmbh.Echo2.RecursiveSearch;

import java.util.HashMap;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.components.MyE2IF__CanGetStampInfo;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;

public class E2_Recursive_Search_Generate_Fingerprint extends E2_RecursiveSearch_AB_Basis {

	public E2_Recursive_Search_Generate_Fingerprint() {
		super(MyE2IF__CanGetStampInfo.class.getName());
	}

	public HashMap<String, String> get_hmFingerPrint() throws myException {
		
		HashMap<String, String> hmRueck = new HashMap<String, String>();
		
		for (Component oComp: this.get_vAllComponents()) {
			
			if (oComp instanceof MyE2IF__CanGetStampInfo && oComp instanceof MyE2IF__Component) {
				
				MyE2IF__CanGetStampInfo oCS = (MyE2IF__CanGetStampInfo)oComp;
				MyE2IF__Component oMy = (MyE2IF__Component)oComp;
				
				hmRueck.put(oMy.EXT().get_UUID().toString(), oCS.get_STAMP_INFO());
			}
		}
		
		return hmRueck;
	}
	
}
