package panter.gmbh.Echo2.UserSettings;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_HashMap extends XXX_UserSetting 
{
	private String 				cSESSION_HASH = null;
	private Vector<String> 	 	vKeys = null;
	
	/**
	 * 
	 * @param SESSION_HASH ist der kenner fuer das abgespeicherte modul
	 * @param Keyset  ist Vector der vorkommenden keys
	 */
	public E2_UserSetting_HashMap(String SESSION_HASH, Vector<String> Keyset) 
	{
		super();
		this.cSESSION_HASH = SESSION_HASH;
		this.vKeys = Keyset;
	}

	
	
	/**
	 * 
	 * @param SESSION_HASH ist der kenner fuer das abgespeicherte modul
	 * @param Keyset  ist Vector der vorkommenden keys
	 */
	public E2_UserSetting_HashMap(String SESSION_HASH) 
	{
		super();
		this.cSESSION_HASH = SESSION_HASH;
	}

	/**
	 * 2018-08-13: neue konstruktoren mit enum 
	 * @param SESSION_HASH ist der kenner fuer das abgespeicherte modul
	 * @param Keyset  ist Vector der vorkommenden keys
	 */
	public E2_UserSetting_HashMap(ENUM_USER_SAVEKEY SESSION_HASH, Vector<String> Keyset) 
	{
		super();
		this.cSESSION_HASH = SESSION_HASH.toString();
		this.vKeys = Keyset;
	}

	
	
	/**
	 * 2018-08-13: neue konstruktoren mit enum 
	 * @param SESSION_HASH ist der kenner fuer das abgespeicherte modul
	 * @param Keyset  ist Vector der vorkommenden keys
	 */
	public E2_UserSetting_HashMap(ENUM_USER_SAVEKEY SESSION_HASH) 
	{
		super();
		this.cSESSION_HASH = SESSION_HASH.toString();
	}



	public void set_KeySet(Vector<String> Keyset)
	{
		this.vKeys = Keyset;
	}
	
	
	@Override
	public String get_SessionHash() 
	{
		return this.cSESSION_HASH;
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{
		@SuppressWarnings("unchecked")
		HashMap<String, String>  hmTagsUndVals = (HashMap<String, String>)oSetting;
		
		String cString4Database = "";
		
		for (int i=0;i<this.vKeys.size();i++)
		{
			String cKeyNext = this.vKeys.get(i);
			cString4Database += "<"+cKeyNext+S.NN(hmTagsUndVals.get(cKeyNext))+cKeyNext+">";
		}
		
		return cString4Database;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
	{
		HashMap<String, String>  hmTagsUndVals = new HashMap<String, String>();
		
		StringBuffer  strbDatabaseWert = new StringBuffer(cDatabaseSetting);
		
		for (int i=0;i<this.vKeys.size();i++)
		{
			String cKeyNext = this.vKeys.get(i);
			String cValNext = S.NN(S.get_cWertInStringCode(strbDatabaseWert, cKeyNext));
			hmTagsUndVals.put(cKeyNext,cValNext);
		}
		return hmTagsUndVals;
	}

	
	//einfachere handhabung
	@SuppressWarnings("unchecked")
	public HashMap<String, String> READ(String MODULE_IDENIFIER) throws myException {
		Object auslesen = this.get_Settings(MODULE_IDENIFIER);
		try {
			return (HashMap<String, String>)auslesen;
		} catch (Exception e) {
			throw new myException(this,"Error converting stored value into String !");
		}
	}
	
	
	public int SAVE(HashMap<String, String> hm, String MODULE_IDENIFIER) throws myException {
		return this.STORE(MODULE_IDENIFIER, hm);
	}
}
