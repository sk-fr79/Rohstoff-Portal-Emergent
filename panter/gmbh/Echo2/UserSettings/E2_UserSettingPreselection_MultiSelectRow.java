package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class E2_UserSettingPreselection_MultiSelectRow extends XXX_UserSetting {


	@SuppressWarnings("unchecked")
	public boolean prepareSelector(E2_Selection_Row_With_Multi_Cols oSelector) throws myException
	{
		boolean bRueck = false;
		
		if (this.Check_if_storable(oSelector))
		{

			Vector<String> vSettings = (Vector<String>)this.get_Settings(this.Extract_ModuleKenner_From_Classname(oSelector));
			if (vSettings != null && vSettings.size()>0)
			{
				oSelector.START(vSettings);
				bRueck = true;
			}
			
		}

		return bRueck;
	}

	
	public int storeSelector(E2_Selection_Row_With_Multi_Cols oSelector, Vector<String> vPreSelects) throws myException
	{
		int iRueck = 0;
		
		if (this.Check_if_storable(oSelector))
		{
			iRueck = this.STORE(this.Extract_ModuleKenner_From_Classname(oSelector), vPreSelects);
		}
		
		return iRueck;
	}

	
	

	public String get_SessionHash() 
	{
		return ENUM_USER_SAVEKEY.SESSION_HASH_USER_MULTI_ROW_PRESELECTION.name();
	}


	@SuppressWarnings("unchecked")
	public String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{

		String cRueck = "|";
		try
		{
			Vector<String> oVectorStoredSelections = (Vector<String>)oSetting;
			
			
			for (int i=0;i<oVectorStoredSelections.size();i++)
			{
				cRueck += oVectorStoredSelections.get(i)+"|";
			}
			
		}
		catch (Exception ex)
		{
			throw new myException(this,": get_DatabaseValueForSettings:"+ex.getLocalizedMessage());
		}
		return cRueck;
	}



	public Object get_STRING_TO_OBJECT(String cDatabaseSetting) throws myException 
	{
		Vector<String> vTeile = null;
		
		try
		{
			vTeile = bibALL.TrenneZeile(cDatabaseSetting, "|");
		}
		catch (Exception ex)
		{
			throw new myException(this,": get_DatabaseValueForSettings:"+ex.getLocalizedMessage());
		}
		
		return vTeile;
	}


}
