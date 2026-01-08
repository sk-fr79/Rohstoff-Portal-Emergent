package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

/**
 * speichert den sort-vector aus einer sql-field-map
 * @author martin
 *
 */
public class E2_Usersetting_Vector_of_Strings extends XXX_UserSetting
{

	private String cMODULE_IDENTIFIER = null;
	private String cSESSION_HASH = null;
	private String cSTRING_SEPARATOR = null;
	
	public E2_Usersetting_Vector_of_Strings(String sessionHash, String stringSeparator,  String identifier)
	{
		super();
		this.cMODULE_IDENTIFIER = 			identifier;
		this.cSESSION_HASH = 		sessionHash;
		this.cSTRING_SEPARATOR = 	stringSeparator;
	}

	

	
	public int STORE_Vector(Vector<String> vVectorToStore) throws myException
	{
		return this.STORE(this.cMODULE_IDENTIFIER, vVectorToStore);
		
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Vector<String> READ_Vector() throws myException
	{
		Vector<String> vRueck = new Vector<String>();
		
		if (S.isFull(this.cMODULE_IDENTIFIER))
		{
			Vector<String> vHelp = (Vector<String>)this.get_Settings(this.cMODULE_IDENTIFIER);
			if (vHelp != null) {
				vRueck.addAll(vHelp);
			}
		}
		return vRueck;
		
	}




	@Override
	public String get_SessionHash() {
		return this.cSESSION_HASH;
	}




	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException {
		
		String cStringAusVector="";

		if (oSetting != null && oSetting instanceof Vector<?> && ((Vector<?>)oSetting).size()>0)  {
			@SuppressWarnings("unchecked")
			Vector<String>  vValuesToSave = (Vector<String>)oSetting;
			cStringAusVector+=this.cSTRING_SEPARATOR;
			
			for (int i=0;i<vValuesToSave.size();i++) {
				cStringAusVector+=vValuesToSave.get(i)+this.cSTRING_SEPARATOR;
			}
			
		}
		
		return cStringAusVector;
	}




	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting) throws myException {
		Vector<String> vRueck = new Vector<String>();
		vRueck.addAll(bibALL.TrenneZeile(S.NN(cDatabaseSetting), this.cSTRING_SEPARATOR));
		return vRueck;
	}
	
	
}
