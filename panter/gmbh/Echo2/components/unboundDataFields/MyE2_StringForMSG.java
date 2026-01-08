package panter.gmbh.Echo2.components.unboundDataFields;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;

public class MyE2_StringForMSG extends MyE2_String
{

	public MyE2_StringForMSG(String untranslated, IF_UB_Fields oField)
	{
		super(untranslated);
		
		if (S.isFull(oField.get_cDBFieldName()))
		{
			this.addUnTranslated("  ("+oField.get_cDBFieldName()+")");
		}
		
	}
	
}
