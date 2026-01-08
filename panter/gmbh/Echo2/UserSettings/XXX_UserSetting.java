package panter.gmbh.Echo2.UserSettings;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.ServerPush.E2_ServerPushMessageContainer;
import panter.gmbh.Echo2.components.E2_Selection_Row_With_Multi_Cols;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECLIST_USERSETTINGS;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USERSETTINGS;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;



/*
 * fuer alle einstellungen wird eine 2-stufige hash-struktur gebildet
 * 1. wird in die session eine HashMap oHM geschrieben mit dem key SESSION_HASH, der das themengebiet definiert
 * 2. die einstellung des Moduls wird innerhalb von oHM mit dem key des MODULE_IDENIFIER geschrieben
 * 3. die Umsetzung des objekts in die datenbank ist implementierungsabhaengig   
 */
public abstract class XXX_UserSetting {
	
	public abstract String    		get_SessionHash();
	
	protected abstract String      	get_OBJECT_TO_STRING(Object oSetting) throws myException;
	protected abstract Object      	get_STRING_TO_OBJECT(String cDatabaseSetting) throws myException;

	//aufzaehlung aller basisklassen, die nicht gespeichert werden koennen
	private Vector<String>  vClassNamesNotStorable = new Vector<String>(); 
	

	public XXX_UserSetting()
	{
		super();
		
		//basisklassen E2_BasicModuleContainer
		this.vClassNamesNotStorable.add(E2_BasicModuleContainer.class.getName().substring(E2_BasicModuleContainer.class.getName().lastIndexOf(".")+1));
		this.vClassNamesNotStorable.add(E2_BasicModuleContainer_MASK.class.getName().substring(E2_BasicModuleContainer_MASK.class.getName().lastIndexOf(".")+1));
		this.vClassNamesNotStorable.add(E2_ServerPushMessageContainer.class.getName().substring(E2_ServerPushMessageContainer.class.getName().lastIndexOf(".")+1));
		
		//basisklasse Selektor
		this.vClassNamesNotStorable.add(E2_Selection_Row_With_Multi_Cols.class.getName().substring(E2_Selection_Row_With_Multi_Cols.class.getName().lastIndexOf(".")+1));
		
		//TabbedPane
		this.vClassNamesNotStorable.add(MyE2_TabbedPane.class.getName().substring(MyE2_TabbedPane.class.getName().lastIndexOf(".")+1));
	}

	
	
	
	/**
	 * 
	 * @param ElementToCustomize
	 * @return s den letzen bezeichner im klassenpfad
	 */
	public String Extract_ModuleKenner_From_Classname(Object ElementToCustomize)
	{
		return ElementToCustomize.getClass().getName().substring(ElementToCustomize.getClass().getName().lastIndexOf(".")+1);
	}
	
	
	public boolean Check_if_storable(Object oBject)
	{
		String cRefName = oBject.getClass().getName().substring(oBject.getClass().getName().lastIndexOf(".")+1);
		
		//sonderfall: E2_ServerPushMessageContainer werden nicht gespeichert
		if (oBject instanceof E2_ServerPushMessageContainer)
		{
			return false;
		}
		
		if (this.vClassNamesNotStorable.contains(cRefName))
		{
			return false;
		}
		return true;
	}
	
	
	@SuppressWarnings("unchecked")
	public Object get_Settings(String MODULE_IDENIFIER) throws myException
	{
		HashMap<String,String> oHashSettings = null;
		if (bibALL.getSessionValue(this.get_SessionHash())!=null)
		{
			oHashSettings = (HashMap<String,String>)bibALL.getSessionValue(this.get_SessionHash());
		}
		else
		{
			this.READ_DB_INTO_SESSION();
			oHashSettings = (HashMap<String,String>)bibALL.getSessionValue(this.get_SessionHash());
		}
		
		if (oHashSettings.get(MODULE_IDENIFIER)==null)
		{
			return null;
		}
		
		return this.get_STRING_TO_OBJECT(oHashSettings.get(MODULE_IDENIFIER));
	}
	
	
	
	/**
	 * 
	 * @param MODULE_IDENIFIER  ist der kenner, der den "ort", der gespeichert werden soll, repraesentiert
	 * @param oModuleSettings ist der String, den die einstellungswerte enthaelt
	 * @throws myException
	 */
	@SuppressWarnings("unchecked")
	public int STORE(String MODULE_IDENIFIER,Object oModuleSettings) throws myException
	{
		int iCountStoredObjects = 0;
		
		try
		{
			String cModulSettings = this.get_OBJECT_TO_STRING(oModuleSettings);
			
			
			HashMap<String,String> oHashSettingsFromSession = null;
			if (bibALL.getSessionValue(this.get_SessionHash())==null)
			{
				this.READ_DB_INTO_SESSION();
			}
			oHashSettingsFromSession = (HashMap<String,String>)bibALL.getSessionValue(this.get_SessionHash());
			
			//neuen wert eintragen und alles speichern
			oHashSettingsFromSession.put(MODULE_IDENIFIER, cModulSettings);
			
			
//			String cQuery = "SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE " +
//			"HASHVALUE_SESSION="+bibALL.MakeSql(this.get_SessionHash())+" AND ID_USER="+bibALL.get_ID_USER();
//			
//			System.out.println(cQuery);
			
			//dazu zuerst datenbank laden
			BASIC_RECLIST_USERSETTINGS reclistUserSettings = new BASIC_RECLIST_USERSETTINGS("SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE " +
					"HASHVALUE_SESSION="+bibALL.MakeSql(this.get_SessionHash())+" AND ID_USER="+bibALL.get_ID_USER());
			
			/*
			 * jetzt durch die sessionwerte durchgehen, und nachsehen, ob der jeweilige wert in der RECLIST ist.
			 * Wenn ja update, sonst insert
			 */
			for (Map.Entry<String, String> oEntry_from_Session: oHashSettingsFromSession.entrySet())
			{
				String cModuleKennerFromSession = 		oEntry_from_Session.getKey();
				String cModuleSettingFromSession = 		oEntry_from_Session.getValue();
				
				
				///fehlersuche
				//System.out.println("Fehler Tabreihenfolge: "+cModuleKennerFromSession+" -> "+cModuleSettingFromSession);
				///fehlersuche
				
				
				//diese sub-recliste enthaelt NUR 1 RECORD_USERSETTINGS, den mit dem Modulkenner 
				BASIC_RECLIST_USERSETTINGS TeilListe = reclistUserSettings.get_SUBLIST(new pruefModulKennerGleich(cModuleKennerFromSession));
				
				if (TeilListe.get_vKeyValues().size()>1)
				{
					throw new myException(this,"Multiple Saveing of one Modulekenner is not possible !!");
				}
				else if (TeilListe.get_vKeyValues().size()==1)
				{
					//2011-06-24: nullwert ausschliessen
					if (!TeilListe.get_(0).get_MODULSETTINGS_cUF_NN("").equals(cModuleSettingFromSession))
					{
						TeilListe.get_(0).set_NEW_VALUE_MODULSETTINGS(cModulSettings);
						bibMSG.add_MESSAGE(TeilListe.get_(0).UPDATE());
						if (bibMSG.get_bIsOK())
						{
							iCountStoredObjects++;
						}
					}
				}
				else
				{
					//neueingabe
				    MySqlStatementBuilder  oZuordnung = new MySqlStatementBuilder();

				    oZuordnung.addSQL_Paar("ID_USER", 			bibALL.get_ID_USER(), false);
				    oZuordnung.addSQL_Paar("HASHVALUE_SESSION", this.get_SessionHash(), true);
				    oZuordnung.addSQL_Paar("MODUL_KENNER", 		cModuleKennerFromSession, true);
				    oZuordnung.addSQL_Paar("MODULSETTINGS", 	cModuleSettingFromSession, true);
				    oZuordnung.addSQL_Paar("ID_USERSETTINGS", "SEQ_USERSETTINGS.NEXTVAL", false);
				    
				    bibMSG.add_MESSAGE(bibDB.ExecMultiSQLVector(bibALL.get_Vector(oZuordnung.get_CompleteInsertString("JD_USERSETTINGS", bibE2.cTO())), true));

				    if (bibMSG.get_bIsOK())
					{
						iCountStoredObjects++;
					}
				    
				}
			}
			
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
			throw new myException("E2_UserSetting:store_toSession;Error ..."+ex.getLocalizedMessage());
		}
		//DEBUG.System_println("Gepeicherte Objekte: "+iCountStoredObjects);
		return iCountStoredObjects;
	}
	
	
	
	private class pruefModulKennerGleich extends BASIC_RECLIST_USERSETTINGS.Validation
	{
		private String ModulKenner = null;

		public pruefModulKennerGleich(String cModulKenner)
		{
			super();
			ModulKenner = cModulKenner;
		}

		@Override
		public boolean isValid(BASIC_RECORD_USERSETTINGS orecord) throws myException
		{
			return orecord.get_MODUL_KENNER_cUF().equals(this.ModulKenner);
		}
	}
	
	
	
	/**
	 * werte aus der datenbank lesen und in die session laden
	 */
	private void READ_DB_INTO_SESSION() throws myException
	{
		//dazu zuerst datenbank laden
		BASIC_RECLIST_USERSETTINGS reclistUserSettings = new BASIC_RECLIST_USERSETTINGS("SELECT * FROM "+bibE2.cTO()+".JD_USERSETTINGS WHERE " +
				"HASHVALUE_SESSION="+bibALL.MakeSql(this.get_SessionHash())+" AND ID_USER="+bibALL.get_ID_USER());

		HashMap<String,String> oHashSettings = new HashMap<String, String>();
		bibALL.setSessionValue(this.get_SessionHash(),oHashSettings);

		//den RECORD mit dem besagten modulkenner suchen und updaten
		for (Map.Entry<String, BASIC_RECORD_USERSETTINGS> oEntrySettings: reclistUserSettings.entrySet())
		{
			oHashSettings.put(oEntrySettings.getValue().get_MODUL_KENNER_cUF(),oEntrySettings.getValue().get_MODULSETTINGS_cUF());
		}
	}
	
	
}
