package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_SearchDefinition;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class E2_UserSetting_SearchFields extends XXX_UserSetting
{

	public void store_ColumnList(E2_DataSearch  oDataSearch) throws myException 
	{
		String cMODUL_IDENTIFIER_of_ContainingModule = oDataSearch.get_cMODULE_KENNER();
		if (S.isFull(cMODUL_IDENTIFIER_of_ContainingModule))
		{
			this.STORE(cMODUL_IDENTIFIER_of_ContainingModule, oDataSearch);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Suchfelder wurden für den nächsten Aufruf gespeichert !")));
		}
	}
	
	public void restore_SearchList(E2_DataSearch  oDataSearch, String cMODUL_IDENTIFIER)  throws myException
	{
		if (S.isFull(cMODUL_IDENTIFIER))
		{
			String 			cActiveSearchers = 			(String)this.get_Settings(cMODUL_IDENTIFIER);
			//cActiveSearchers enthaelt fuer jedes searchfield entweder ein + oder - 
			
			if (S.isFull(cActiveSearchers))
			{
				Vector<E2_SearchDefinition> 	vSearchDefs = 	oDataSearch.get_vSearchDefinitions();
				
				for (int i=0;i<vSearchDefs.size();i++)
				{
					if (vSearchDefs.size()>i)
					{
						vSearchDefs.get(i).get_oCheckIsEnabled().setSelected(false);
						if (cActiveSearchers.length()>i)
						{
							if (cActiveSearchers.substring(i,i+1).equals("+"))
							{
							     vSearchDefs.get(i).get_oCheckIsEnabled().setSelected(true);
							}
						}
					}
				}
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected String get_OBJECT_TO_STRING(Object setting) throws myException
	{
		//alle componenten (=spalten)-keys einlesen, die sichtbar sind
		
		Vector<E2_SearchDefinition> 	vSearchDefinitions = (Vector<E2_SearchDefinition>)setting;
		
		String cMemory = "";
		
		for (int i=0;i<vSearchDefinitions.size();i++)
		{
			if (vSearchDefinitions.get(i).get_oCheckIsEnabled().isSelected())
			{
				cMemory += "+";
			}
			else
			{
				cMemory += "-";
			}
		}
		return cMemory;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String databaseSetting)  throws myException
	{
		return databaseSetting;
	}

	@Override
	public String get_SessionHash()
	{
		return ENUM_USER_SAVEKEY.SESSION_HASH_USER_SEARCH_KOMBINATION.name();
	}

}
