package panter.gmbh.Echo2.UserSettings;

import java.util.HashMap;
import java.util.Iterator;

import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.TextArea;
import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_ComponentMap extends  XXX_UserSetting 
{
	private String 				cSESSION_HASH = null;
	
	private HashMap<String, MyE2IF__Component>  hmComponents = new HashMap<String, MyE2IF__Component>();
	
	
	/**
	 * 
	 * @param SESSION_HASH ist der kenner fuer das abgespeicherte modul
	 * @param Keyset  ist Vector der vorkommenden keys
	 */
	public E2_UserSetting_ComponentMap(String SESSION_HASH) 
	{
		super();
		this.cSESSION_HASH = SESSION_HASH;
	}

	@Override
	public String get_SessionHash() 
	{
		return this.cSESSION_HASH;
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{
		HashMap<String, String>  hmTagsUndVals = (HashMap<String, String>)oSetting;
		
		String cString4Database = "";
		
		Iterator<String> oIter = this.hmComponents.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cKeyNext = oIter.next();
			cString4Database += "<"+cKeyNext+S.NN(hmTagsUndVals.get(cKeyNext))+cKeyNext+">";
		}
		
		return cString4Database;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
	{
		HashMap<String, String>  hmTagsUndVals = new HashMap<String, String>();
		
		StringBuffer  strbDatabaseWert = new StringBuffer(cDatabaseSetting);
		
		Iterator<String> oIter = this.hmComponents.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cKeyNext = oIter.next();
			String cValNext = S.NN(S.get_cWertInStringCode(strbDatabaseWert, cKeyNext));
			hmTagsUndVals.put(cKeyNext,cValNext);
		}
		return hmTagsUndVals;
	}

	
	public void put(String cKey, MyE2IF__Component  oComp) throws myException
	{
		//zuerst den typ der erlaubten komponenten durchgehen
		boolean bErlaubt = false;
		
		if (oComp instanceof CheckBox) 			{ bErlaubt=true; }
		if (oComp instanceof TextField) 		{ bErlaubt=true; }
		if (oComp instanceof TextArea) 			{ bErlaubt=true; }
		if (oComp instanceof MyE2_SelectField) 	{ bErlaubt=true; }
		
		
		if (!bErlaubt)
		{
			throw new myException(this,oComp.getClass().getCanonicalName()+" is not a saveable component !");
		}
		
		oComp.EXT().set_cHashKey4UserSetting(cKey);
		
		this.hmComponents.put(cKey, oComp);
	}
	
	
	
	
	private String get_SaveText( MyE2IF__Component  oComp) throws myException
	{
		String cRueck = "";
		
		if (oComp instanceof CheckBox) 		
		{ 
			cRueck = ((CheckBox)oComp).isSelected()?"Y":"N"; 
		}
		
		if (oComp instanceof TextField) 	
		{ 
			cRueck = S.NN(((TextField)oComp).getText()); 
		}
		
		if (oComp instanceof TextArea) 		
		{ 
			cRueck = S.NN(((TextField)oComp).getText()); 
		}
		
		if (oComp instanceof MyE2_SelectField) 	
		{ 
			cRueck = S.NN(((MyE2_SelectField)oComp).get_ActualWert());
		}
		
		return cRueck;
	}
	

	private void restore_Voreinstellung( MyE2IF__Component  oComp, String cWert)
	{
		if (oComp instanceof CheckBox) 		
		{ 
			((CheckBox)oComp).setSelected(S.NN(cWert).equals("Y")); 
		}
		
		if (oComp instanceof TextField) 	
		{ 
			((TextField)oComp).setText(S.NN(cWert)) ;
		}
		
		if (oComp instanceof TextArea) 		
		{ 
			((TextArea)oComp).setText(S.NN(cWert)) ;
		}
		
		if (oComp instanceof MyE2_SelectField) 	
		{ 
			((MyE2_SelectField)oComp).set_ActiveValue_OR_FirstValue(S.NN(cWert));
		}

	}
	
	
	
	public void SAVE_WERTE() throws myException
	{
		Iterator<String> oIter = this.hmComponents.keySet().iterator();
		
		HashMap<String, String> hmWerte = new HashMap<String, String>();
		
		while (oIter.hasNext())
		{
			String cKey = oIter.next();
			hmWerte.put(this.hmComponents.get(cKey).EXT().get_cHashKey4UserSetting(), this.get_SaveText(this.hmComponents.get(cKey)));
		}
		
		this.STORE(this.cSESSION_HASH,hmWerte);
	}
	
	
	public void RESTORE_WERTE() throws myException
	{
		HashMap<String, String> hmWerte = (HashMap<String, String>)this.get_Settings(this.cSESSION_HASH);
		
		if (hmWerte!=null)
		{
			Iterator<String> oIter = this.hmComponents.keySet().iterator();
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				this.restore_Voreinstellung(this.hmComponents.get(cKey),hmWerte.get(cKey));
			}
		}
	}
	
}
