package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSettingRowsInNavigationList extends XXX_UserSetting
{

	public void store_ColumnList(E2_NavigationList  oNaviList) throws myException 
	{
		String cMODUL_IDENTIFIER_of_ContainingModule = oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
		if (S.isFull(cMODUL_IDENTIFIER_of_ContainingModule))
		{
			this.STORE(cMODUL_IDENTIFIER_of_ContainingModule, oNaviList);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Zustand wurde für den nächsten Aufruf gespeichert !")));
		}
	}
	
	public void restore_ColumnList(E2_NavigationList  oNaviList, String cMODUL_IDENTIFIER_of_ContainingModule)  throws myException
	{
		if (S.isFull(cMODUL_IDENTIFIER_of_ContainingModule))
		{
			String 			cVisibleCols = 			(String)this.get_Settings(cMODUL_IDENTIFIER_of_ContainingModule);
			//cVisibleCols enthaelt die sichtbaren Spalten getrennt durch | 
			
			if (S.isFull(cVisibleCols))
			{
				Vector<String> 	vVisibleCols = 			bibALL.TrenneZeile(cVisibleCols, "|");
				Vector<String> 	vComponentHashKeys = 	oNaviList.get_oComponentMAP__REF().get_vComponentHashKeys();
				
				for (int i=0;i<vComponentHashKeys.size();i++)
				{
					MyE2IF__Component oComponent = (MyE2IF__Component)oNaviList.get_oComponentMAP__REF().get(vComponentHashKeys.get(i)) ;
					
					// alle komponenten ausser den steuerungskomponenten selektieren
					if (!(oComponent instanceof E2_ButtonListMarker || oComponent instanceof E2_CheckBoxForList))
					{
						oComponent.EXT().set_bIsVisibleInList(vVisibleCols.contains(vComponentHashKeys.get(i)));
					}
				}
			}
		}
	}
	
	
	@Override
	protected String get_OBJECT_TO_STRING(Object setting) throws myException
	{
		//alle componenten (=spalten)-keys einlesen, die sichtbar sind
		
		E2_NavigationList  oNaviList = (E2_NavigationList)setting;
		
		String cRueck = "|";

		Vector<String> 		vComponentHashKeys = 	oNaviList.get_oComponentMAP__REF().get_vComponentHashKeys();
		
		for (int i=0;i<vComponentHashKeys.size();i++)
		{
			MyE2IF__Component oComponent = (MyE2IF__Component)oNaviList.get_oComponentMAP__REF().get(vComponentHashKeys.get(i)) ;
			
			// alle komponenten ausser den steuerungskomponenten selektieren
			if (!(oComponent instanceof E2_ButtonListMarker || oComponent instanceof E2_CheckBoxForList))
			{
				if (oComponent.EXT().get_bIsVisibleInList())
				{
					cRueck+=(vComponentHashKeys.get(i)+"|");
				}
			}
		}
		return cRueck;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String databaseSetting)  throws myException
	{
		return databaseSetting;
	}

	@Override
	public String get_SessionHash()
	{
		return ENUM_USER_SAVEKEY.SESSION_HASH_USER_LIST_COLUMNS_VISIBLE.name();
	}

}
