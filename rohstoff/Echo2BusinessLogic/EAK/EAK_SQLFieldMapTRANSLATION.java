package rohstoff.Echo2BusinessLogic.EAK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;

public class EAK_SQLFieldMapTRANSLATION extends Project_SQLFieldMAP
{

	private String BASENAME = null; 
	
	/**
	 * @param cBASE_NAME (Kann sein: BRANCHE / GRUPPE / CODE)
	 * @throws myException
	 */
	public EAK_SQLFieldMapTRANSLATION(String cBASE_NAME, String cID_MOTHERTABLE_Unformated) throws myException
	{
		super("JT_EAK_"+cBASE_NAME+"_SP", ":ID_EAK_"+cBASE_NAME+":", false);
		
		this.BASENAME = cBASE_NAME;
		
		this.add_SQLField(new SQLFieldForRestrictTableRange("JT_EAK_"+this.BASENAME+"_SP",
															"ID_EAK_"+this.BASENAME,
															"ID_EAK_"+this.BASENAME,
															new MyE2_String("ID (Ref)"),
															cID_MOTHERTABLE_Unformated,
															bibE2.get_CurrSession()), false);
		
		
		this.initFields();
		
	}

}
