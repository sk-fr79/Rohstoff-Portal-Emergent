package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM;

import java.util.Vector;

import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class FSI__SortKontrakteButtonEK_SaveStatus extends XXX_UserSetting
{


	private String 						SESSION_HASH_SAVE_SORT_IN_INFO = 	"@@____SAVE_SaveSortButtonKontrakt_EK____IN_INFO###";
	private String                  	cMODULE_IDENTIFIER = "ALL";
	
	//private FSI__SortKontrakteButtonEK    oSortButton = null;

	/**
	 * 
	 * @param SortButton  
	 * @param oSearchField
	 */
	public FSI__SortKontrakteButtonEK_SaveStatus() 
	{
		super();
	}

	
	public boolean get_bCanSaveStatus()
	{
		return true;
	}

	
	@Override
	public String get_SessionHash() 
	{
		return  this.SESSION_HASH_SAVE_SORT_IN_INFO;
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


	private String get_Status_der_Sortierung_toSave(FSI__SortKontrakteButtonEK oButton) throws myException
	{
		
		String cRueck = ""+oButton.get_cSortKenner()+":";
		cRueck += oButton.get_bIsSortedUP()?"UP":"DOWN";
		
		return cRueck;
	}

	
	public String[] get_Status_aus_Database() throws myException
	{
		String cWerte = S.NN((String)this.get_Settings(this.cMODULE_IDENTIFIER));
		
		String[] cRueck = new String[2];
		
		Vector<String> vWerte = bibALL.TrenneZeile(cWerte, ":");
		
		if (vWerte.size()==2 && (vWerte.get(1).equals("UP") ||vWerte.get(1).equals("DOWN")))
		{
			cRueck[0]=vWerte.get(0);
			cRueck[1]=vWerte.get(1);
			return cRueck;
		}
		 
		return null;
		
	}
	
	
	public void saveSelectedStatus(FSI__SortKontrakteButtonEK oButton) throws myException
	{
		this.STORE(this.cMODULE_IDENTIFIER, this.get_Status_der_Sortierung_toSave(oButton));
	}

}
