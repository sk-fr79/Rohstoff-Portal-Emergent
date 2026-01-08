package panter.gmbh.Echo2.UserSettings;

import java.util.Vector;

import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.E2_SortButtonsInList;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * ser saver legt eine String ab, der wie folgt aussieht: nummerspalte:UP  oder nummerspalte:DOWN
 */
public class E2_UserSetting_SaveSortButton_IN_SearchResultWindow extends XXX_UserSetting 
{
	private static String 			SESSION_HASH_SAVE_MULTI_SORTSTATUS = 	"@@____SAVE_SaveSortButton_IN_SearchResultWindow###";
	
	private String                  cMODULE_IDENTIFIER = null;
	

	private E2_SortButtonsInList    oSortButton = null;
	
	private boolean  				bCanSaveStatus = false;
	
	

	/**
	 * 
	 * @param SortButton   !!!!! wird beim laden des status null
	 * @param oSearchField
	 */
	public E2_UserSetting_SaveSortButton_IN_SearchResultWindow(E2_SortButtonsInList SortButton, Object oSearchField) 
	{
		super();
		this.oSortButton = SortButton;

		if (oSearchField != null )
		{
			this.bCanSaveStatus = true;
			this.cMODULE_IDENTIFIER = this.Extract_ModuleKenner_From_Classname(oSearchField);
		}
	}

	
	public boolean get_bCanSaveStatus()
	{
		return bCanSaveStatus;
	}

	
	@Override
	public String get_SessionHash() 
	{
		return  E2_UserSetting_SaveSortButton_IN_SearchResultWindow.SESSION_HASH_SAVE_MULTI_SORTSTATUS;
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
		
		String cRueck = ""+this.oSortButton.get_oSpalteDef().get_iNummerDerSpalteInPopupListe()+":";
		cRueck += this.oSortButton.get_bIsSortedUP()?"UP":"DOWN";
		
		return cRueck;
	}

	
	public String[] get_Status_aus_Database() throws myException
	{
		String cWerte = S.NN((String)this.get_Settings(this.cMODULE_IDENTIFIER));
		
		String[] cRueck = new String[2];
		
		Vector<String> vWerte = bibALL.TrenneZeile(cWerte, ":");
		
		if (vWerte.size()==2 && bibALL.isLong(vWerte.get(0)) && (vWerte.get(1).equals("UP") ||vWerte.get(1).equals("DOWN")))
		{
			cRueck[0]=vWerte.get(0);
			cRueck[1]=vWerte.get(1);
			return cRueck;
		}
		 
		return null;
		
	}
	
	
	public void saveSelectedStatus() throws myException
	{
		this.STORE(this.cMODULE_IDENTIFIER, this.get_Status_der_Sortierung_toSave());
	}

}
