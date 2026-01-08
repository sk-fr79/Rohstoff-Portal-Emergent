package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class E2_UserSettingTabReihenfolge extends XXX_UserSetting {


	
	
	public boolean prepareTabbedPane(E2_BasicModuleContainer oContainer) throws myException
	{
		E2_RecursiveSearch_Component oSearchComp = new E2_RecursiveSearch_Component(
																	oContainer,
																	bibALL.get_Vector(MyE2_TabbedPane.class.getName()),
																	null);
		
		boolean bRueck = false;
		
		if (oSearchComp.get_vAllComponents().size()==1)
		{
			bRueck = this.prepareTabbedPane((MyE2_TabbedPane)oSearchComp.get_vAllComponents().get(0));
		}

		return bRueck;
	}
	
	
	@SuppressWarnings("unchecked")
	public boolean prepareTabbedPane(MyE2_TabbedPane oTabbedPane) throws myException
	{
		boolean bRueck = false;
		
		if (this.Check_if_storable(oTabbedPane) && oTabbedPane.get_bAllowSaveTabReihenfolge())
		{

			Vector<String> vSettings = (Vector<String>)this.get_Settings(this.Extract_ModuleKenner_From_Classname(oTabbedPane));
			if (vSettings != null && vSettings.size()>0)
			{
				oTabbedPane.RebuildTabPane(vSettings);
			}
			
			bRueck = true;
		}

		return bRueck;
	}


	
	public int storeTabbedPane(MyE2_TabbedPane oTabbedPane) throws myException
	{
		int iRueck = 0;
		
		if (this.Check_if_storable(oTabbedPane))
		{
			iRueck = this.STORE(this.Extract_ModuleKenner_From_Classname(oTabbedPane), oTabbedPane.get_SortVector());
		}
		
		return iRueck;
	}



	public String get_SessionHash() {
		return ENUM_USER_SAVEKEY.SESSION_HASH_USER_TABPANE_ORDERS.name();
	}



	@SuppressWarnings("unchecked")
	public String get_OBJECT_TO_STRING(Object vSetting) throws myException 
	{
		String cRueck = "";
		
		if (vSetting != null)
		{
			try
			{
				Vector<String> vTabReihenfolge = (Vector<String>)vSetting;
				
				if (vTabReihenfolge.size()>1)    //sonst macht eine sortierung keinen sinn
				{
					cRueck = bibALL.Concatenate(vTabReihenfolge, "|", "");
				}
			}
			catch (Exception ex)
			{
				throw new myException(this.getClass().getName()+": get_DatabaseValueForSettings:"+ex.getLocalizedMessage());
			}
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
			throw new myException(this.getClass().getName()+": get_DatabaseValueForSettings:"+ex.getLocalizedMessage());
		}
		
		return vTeile;
	}


}
