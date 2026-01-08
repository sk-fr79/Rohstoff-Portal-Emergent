package panter.gmbh.Echo2.components.SortGrid;

import java.util.Vector;

import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class E2_SortGrid_USERSETTING_LastSort extends XXX_UserSetting
{
	private String 					SESSION_HASH_SAVE_MULTI_SORTSTATUS = 	"@@____SAVE_SaveSortInSortList";
	private String                  cMODULE_IDENTIFIER = "<ALL>";
	

	private E2_SortButtonInList    	oSortButton = null;
	private E2_SortGrid    			oSortGrid = null;
	
	
	/**
	 * 
	 * @param SortButton 
	 * @param oSearchField
	 */
	public E2_SortGrid_USERSETTING_LastSort(E2_SortButtonInList SortButton, E2_SortGrid SortGrid) 
	{
		super();
		this.oSortButton = 	SortButton;
		this.oSortGrid = 	SortGrid;
	}

	
	public boolean get_bCanSaveStatus()
	{
		return S.isFull(this.oSortButton.get_ownSortGrid().get_SaveKeyForUserSettings());
	}

	
	@Override
	public String get_SessionHash()
	{
		return  this.SESSION_HASH_SAVE_MULTI_SORTSTATUS+"_"+this.oSortGrid.get_SaveKeyForUserSettings();
	}

	@Override
	protected String get_OBJECT_TO_STRING(Object oSetting) throws myException 
	{
		return (String)oSetting;
	}

	@Override
	protected Object get_STRING_TO_OBJECT(String cDatabaseSetting)	throws myException 
	{
		return cDatabaseSetting;
	}


	private String get_Status_der_Sortierung_toSave() throws myException
	{
		
		String cRueck = ""+this.oSortButton.get_iColumnNumber()+":";
		cRueck += this.oSortButton.get_bIsSortedUP()?E2_SortGrid.SORTED_UP:E2_SortGrid.SORTED_DOWN;
		
		return cRueck;
	}

	
	/**
	 * 
	 * @return s array mit {"<spalte>","UP"}
	 * 
	 * @throws myException
	 */
	public String[] get_Status_aus_Database(String cModuleIdentifier) throws myException
	{
		String cWerte = S.NN((String)this.get_Settings(cModuleIdentifier));
		
		String[] cRueck = new String[2];
		
		Vector<String> vWerte = bibALL.TrenneZeile(cWerte, ":");
		
		if (vWerte.size()==2 && bibALL.isLong(vWerte.get(0)) && (vWerte.get(1).equals(E2_SortGrid.SORTED_UP) ||vWerte.get(1).equals(E2_SortGrid.SORTED_DOWN)))
		{
			cRueck[0]=vWerte.get(0);
			cRueck[1]=vWerte.get(1);
			return cRueck;
		}
		 
		return null;
		
	}
	
	
	public void saveSelectedStatus(String cModuleIdentifier) throws myException
	{
		this.STORE(cModuleIdentifier, this.get_Status_der_Sortierung_toSave());
	}

}
