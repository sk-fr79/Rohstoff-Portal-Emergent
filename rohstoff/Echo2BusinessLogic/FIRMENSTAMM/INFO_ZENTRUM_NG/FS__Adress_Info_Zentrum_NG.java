package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;


import nextapp.echo2.app.Extent;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.UserSettings.XXX_UserSetting;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


//informationen zu Adressen mit sprungmethoden
public class FS__Adress_Info_Zentrum_NG extends MyE2_TabbedPane 
{
	
	public static String STORE_MODUL_KENNER = "STORE_SETTINGS_IN_ADRESS_INFO_CENTER";; 
	
	public static int    INT_MAX_RECORDS_IN_LIST = 200;
	
	private RECORD_ADRESSE 					recADRESSE = 					null;
	private E2_BasicModuleContainer  		oContainerToCloseAfterJump = 	null;
	private INFO_BLOCK_Fuhren_NG          	oInfoBlockFuhren =   			null;
	private INFO_BLOCK_Kontrakte_NG        	oInfoBlockKontrakte =   		null;
	private INFO_BLOCK_PREISE_NG	        oInfoBlockPreiseEK =   			null;
	private INFO_BLOCK_PREISE_NG	        oInfoBlockPreiseVK =   			null;
	
	private INFO_BLOCK_ADRESS_UEBERSICHT_NT_NG  oInfoAdressUebersicht = null;
	
	//28.11.2018: sebastien - ubersicht fur zusatdateien		
	private INFO_MELDUNG_ADRESSE_UEBERSICHT_NG oInfoMeldungUbersicht = null;
	//28.11.2018: sebastien - ubersicht fur zusatdateien		
	private INFO_ZUSATZ_DATEI_UBERSICHT_NG oZusatzDateiUbersicht;
	//23.07.2019: sebastien - ubersicht fur wiegekarte
	private INFO_WIEGEKARTE_UBERSICHT_NG oWiegekarteUbersicht;
	
	//2011-10-20: 2 weitere Info-Bloecke: sorten EK- und VK
	private INFO_BLOCK_SORTEN_NG           oInfoBlockSortenEK = null;
	private INFO_BLOCK_SORTEN_NG           oInfoBlockSortenVK = null;
	

	//eine sammlung von action-agents, die die jeweilige liste aufbauen
	private XX_ActionAgent              oAgentBaueFuhre = null;
	private XX_ActionAgent              oAgentBaueKontrakte = null;
	private XX_ActionAgent              oAgentBauePreiseEK = null;
	private XX_ActionAgent              oAgentBauePreiseVK = null;
	private XX_ActionAgent              oAgentBaueAdressUebersicht = null;
	private XX_ActionAgent              oAgentBaueSortenEK = null;
	private XX_ActionAgent              oAgentBaueSortenVK = null;
	private XX_ActionAgent				oAgentInfoMeldungUbersicht = null;
	
	private XX_ActionAgent 				oAgentZusatzInfoUbersicht;

	
	private GridLayoutData              GL_Titel_Left = 		new FSI___GridLayoutList_NG(	0, 	new E2_ColorDDark());
	private GridLayoutData              GL_Titel_Center = 		new FSI___GridLayoutList_NG(	1, 	new E2_ColorDDark());
	private GridLayoutData              GL_Titel_Right = 		new FSI___GridLayoutList_NG(	2, 	new E2_ColorDDark());
	private GridLayoutData              GL_Normal_Left = 		new FSI___GridLayoutList_NG(	0, 	new E2_ColorBase());
	private GridLayoutData              GL_Normal_Center = 		new FSI___GridLayoutList_NG(	1, 	new E2_ColorBase());
	private GridLayoutData              GL_Normal_Right = 		new FSI___GridLayoutList_NG(	2, 	new E2_ColorBase());
	private GridLayoutData              GL_Highlight_Left = 	new FSI___GridLayoutList_NG(	0, 	new E2_ColorLLight());
	private GridLayoutData              GL_Highlight_Center = 	new FSI___GridLayoutList_NG(	1, 	new E2_ColorLLight());
	private GridLayoutData              GL_Highlight_Right = 	new FSI___GridLayoutList_NG(	2, 	new E2_ColorLLight());
	
	
	//2015-12-14: spruenge abschaltbar machen
	private boolean   					b_jump_is_active = true;           //standard

	private actionWiegekarteNeu oAgentWiegekarteUbersicht;

	


	
	
	public FS__Adress_Info_Zentrum_NG(E2_BasicModuleContainer  ContainerToCloseAfterJump) throws myException 
	{
		super(400);
		this.set_bAutoHeight(true);

		this.set_bAllowSaveTabReihenfolge(false);    //tabb-reihenfolge speichern wird gesperrt
		
		//info bloecke
		this.oInfoBlockFuhren = 	new INFO_BLOCK_Fuhren_NG(this, false);
		this.oInfoBlockKontrakte = 	new INFO_BLOCK_Kontrakte_NG(this);
		this.oInfoBlockPreiseEK = 	new INFO_BLOCK_PREISE_NG(this, false,"EK");
		this.oInfoBlockPreiseVK = 	new INFO_BLOCK_PREISE_NG(this, false,"VK");
		
		this.oInfoAdressUebersicht = new INFO_BLOCK_ADRESS_UEBERSICHT_NT_NG();
		
		this.oInfoBlockSortenEK = new INFO_BLOCK_SORTEN_NG(this, false, "EK");
		this.oInfoBlockSortenVK = new INFO_BLOCK_SORTEN_NG(this, false, "VK");
		
		//27.11.2018: sebastien - ubersicht fur meldung
		this.oInfoMeldungUbersicht = new INFO_MELDUNG_ADRESSE_UEBERSICHT_NG();
		
		//28.11.2018: sebastien - ubersicht fur zusatdateien
		this.oZusatzDateiUbersicht = new INFO_ZUSATZ_DATEI_UBERSICHT_NG();
		
		//23.07.2019: sebastien - ubersicht fur wiegekarte
		this.oWiegekarteUbersicht = new INFO_WIEGEKARTE_UBERSICHT_NG();
		
		//action agent
		this.oAgentBaueFuhre = 				new actionBaueFuhrenNeu(this.oInfoBlockFuhren);
		this.oAgentBaueKontrakte = 			new actionBaueKontrakteNeu(this.oInfoBlockKontrakte);
		this.oAgentBauePreiseEK = 			new actionBauePreiseNeuEK(this.oInfoBlockPreiseEK);
		this.oAgentBauePreiseVK = 			new actionBauePreiseNeuVK(this.oInfoBlockPreiseVK);
		this.oAgentBaueAdressUebersicht = 	new actionBaueAdressInfosNeu(this.oInfoAdressUebersicht);
		
		this.oAgentBaueSortenEK = 			new actionBaueSortenNeuEK(this.oInfoBlockSortenEK);
		this.oAgentBaueSortenVK = 			new actionBaueSortenNeuVK(this.oInfoBlockSortenVK);
		
		//27.11.2018: sebastien - ubersicht fur meldung
		this.oAgentInfoMeldungUbersicht = new actionBaueAdressMeldungNeu(this.oInfoMeldungUbersicht);
		
		//28.11.2018: sebastien - ubersicht fur zusatdateien		
		this.oAgentZusatzInfoUbersicht = new actionBaueZusatzInfoNeu(this.oZusatzDateiUbersicht);
		
		//23.07.2019: sebastien - ubersicht fur wiegekarte
		this.oAgentWiegekarteUbersicht = new actionWiegekarteNeu(this.oWiegekarteUbersicht);
		
		this.oContainerToCloseAfterJump = ContainerToCloseAfterJump;
	}

	
	
	public void init_INFO(String ID_ADRESSE_Unformated) throws myException
	{
		this.recADRESSE = new RECORD_ADRESSE(ID_ADRESSE_Unformated);
		
		this.add_Tabb(new MyE2_String("Fuhren"),			this.oInfoBlockFuhren.get_ContainerGridMitBedienZeile(), 			this.oAgentBaueFuhre);
		this.add_Tabb(new MyE2_String("Kontraktpositionen"),this.oInfoBlockKontrakte.get_ContainerGridMitBedienZeile(), 		this.oAgentBaueKontrakte);
		this.add_Tabb(new MyE2_String("EK-Angebotspreise"),			this.oInfoBlockPreiseEK.get_ContainerGridMitBedienZeile(), 	this.oAgentBauePreiseEK);
		this.add_Tabb(new MyE2_String("VK-Angebotspreise"),			this.oInfoBlockPreiseVK.get_ContainerGridMitBedienZeile(), 	this.oAgentBauePreiseVK);
		
		this.add_Tabb(new MyE2_String("EK-Sorten"),			this.oInfoBlockSortenEK.get_ContainerGridMitBedienZeile(), 			this.oAgentBaueSortenEK);
		this.add_Tabb(new MyE2_String("VK-Sorten"),			this.oInfoBlockSortenVK.get_ContainerGridMitBedienZeile(), 			this.oAgentBaueSortenVK);
		
		this.add_Tabb(new MyE2_String("Zusatzinfos"),		this.oInfoAdressUebersicht.get_ContainerGridMitBedienZeile(), 		this.oAgentBaueAdressUebersicht);
		
		this.add_Tabb(S.ms("Meldungen"), 		this.oInfoMeldungUbersicht.get_ContainerGridMitBedienZeile(), 					this.oAgentInfoMeldungUbersicht);
		this.add_Tabb(S.ms("Zusatz Dateien"), 	this.oZusatzDateiUbersicht.get_ContainerGridMitBedienZeile(),					this.oAgentZusatzInfoUbersicht);
		
		//23.07.2019: sebastien - ubersicht fur wiegekarte
		this.add_Tabb(S.ms("Wiegekarte"), 	this.oWiegekarteUbersicht.get_ContainerGridMitBedienZeile(),					this.oAgentWiegekarteUbersicht);

		
		//dam adress-info-button noch den adress-record uebergeben
		this.oInfoAdressUebersicht.set_recAdresse(this.recADRESSE);
		this.oInfoMeldungUbersicht.set_recAdresse(this.recADRESSE);
		this.oZusatzDateiUbersicht.set_recAdresse(this.recADRESSE);
		this.oWiegekarteUbersicht.set_recAdresse(this.recADRESSE);
		
		this.set_bAutoHeight(true);
		
		String cLastIndex = (String)(new E2_UserSettings_StoreActiveTab().get_Settings(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER));
		MyInteger  iActualIndex = new MyInteger(cLastIndex);
		if (iActualIndex.get_oInteger()!=null)
		{
			this.setSelectedIndex(iActualIndex.get_iValue());
			//jetzt die erste tab fuellen
			if (iActualIndex.get_iValue()==0) {	this.oAgentBaueFuhre.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==1) {	this.oAgentBaueKontrakte.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==2) {	this.oAgentBauePreiseEK.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==3) {	this.oAgentBauePreiseVK.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==4) {	this.oAgentBaueSortenEK.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==5) {	this.oAgentBaueSortenVK.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==6) {	this.oAgentBaueAdressUebersicht.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==7) {	this.oAgentInfoMeldungUbersicht.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==8) {	this.oAgentZusatzInfoUbersicht.executeAgentCode(null);}
			if (iActualIndex.get_iValue()==9) {	this.oAgentWiegekarteUbersicht.executeAgentCode(null);}
			
		}
	}
	
	
	private class actionBaueFuhrenNeu extends XX_ActionAgent
	{
		private INFO_BLOCK_Fuhren_NG  oInfoBlockFuhren = null;

		public actionBaueFuhrenNeu(INFO_BLOCK_Fuhren_NG  InfoBlockFuhren) throws myException 
		{
			super();
			this.oInfoBlockFuhren = InfoBlockFuhren;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockFuhren.fuhren_dateien_laden_v3();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "0");
		}
	}
	
	
	private class actionBaueKontrakteNeu extends XX_ActionAgent
	{
		private INFO_BLOCK_Kontrakte_NG  oInfoBlockKontrakte = null;

		public actionBaueKontrakteNeu(INFO_BLOCK_Kontrakte_NG  InfoBlockKontrakte) 
		{
			super();
			this.oInfoBlockKontrakte = InfoBlockKontrakte;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockKontrakte.__aufbau1();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "1");
		}
	}

	

	private class actionBauePreiseNeuEK extends XX_ActionAgent
	{
		private INFO_BLOCK_PREISE_NG  oInfoBlockPreiseEK = null;

		public actionBauePreiseNeuEK(INFO_BLOCK_PREISE_NG  InfoBlockPreiseEK) 
		{
			super();
			this.oInfoBlockPreiseEK = InfoBlockPreiseEK;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockPreiseEK.__aufbau();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "2");
		}
	}



	private class actionBauePreiseNeuVK extends XX_ActionAgent
	{
		private INFO_BLOCK_PREISE_NG  oInfoBlockPreiseVK = null;

		public actionBauePreiseNeuVK(INFO_BLOCK_PREISE_NG  InfoBlockPreiseVK) 
		{
			super();
			this.oInfoBlockPreiseVK = InfoBlockPreiseVK;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockPreiseVK.__aufbau();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "3");
		}
	}


	
	private class actionBaueSortenNeuEK extends XX_ActionAgent
	{
		private INFO_BLOCK_SORTEN_NG  	oInfoBlockSorten = null;
		
		public actionBaueSortenNeuEK(INFO_BLOCK_SORTEN_NG  InfoBlockSorte) 
		{
			super();
			this.oInfoBlockSorten = InfoBlockSorte;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockSorten.__query_und_baue_auf();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "4");
		}
	}

	private class actionBaueSortenNeuVK extends XX_ActionAgent
	{
		private INFO_BLOCK_SORTEN_NG  	oInfoBlockSorten = null;
				
		public actionBaueSortenNeuVK(INFO_BLOCK_SORTEN_NG  InfoBlockSorte) 
		{
			super();
			this.oInfoBlockSorten = InfoBlockSorte;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockSorten.__query_und_baue_auf();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "5");
		}
	}

	private class actionBaueAdressInfosNeu extends XX_ActionAgent
	{
		private INFO_BLOCK_ADRESS_UEBERSICHT_NT_NG  oInfoBlockAdresse = null;

		public actionBaueAdressInfosNeu(INFO_BLOCK_ADRESS_UEBERSICHT_NT_NG  InfoBlockAdresse) 
		{
			super();
			this.oInfoBlockAdresse = InfoBlockAdresse;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoBlockAdresse.fill_Report();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "6");
		}
	}

	/**
	 * 
	 * @author sebastien
	 * @date 27.11.2018
	 *
	 */
	private class actionBaueAdressMeldungNeu extends XX_ActionAgent
	{
		private INFO_MELDUNG_ADRESSE_UEBERSICHT_NG  oInfoMeldungAdresse = null;

		public actionBaueAdressMeldungNeu(INFO_MELDUNG_ADRESSE_UEBERSICHT_NG  InfoBlockAdresse) 
		{
			super();
			this.oInfoMeldungAdresse = InfoBlockAdresse;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoMeldungAdresse.fill_Report();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "7");
		}
	}

	/**
	 * 
	 * @author sebastien
	 * @date 28.11.2018
	 *
	 */
	private class actionBaueZusatzInfoNeu extends XX_ActionAgent{
		
		private INFO_ZUSATZ_DATEI_UBERSICHT_NG oInfoMeldungAdresse = null;

		public actionBaueZusatzInfoNeu(INFO_ZUSATZ_DATEI_UBERSICHT_NG  InfoBlockAdresse) 
		{
			super();
			this.oInfoMeldungAdresse = InfoBlockAdresse;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oInfoMeldungAdresse.fill_Report();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "8");
		}
		
	}
	/**
	 * @author sebastien
	 * @date 23.07.2019
	 *
	 */
	private class actionWiegekarteNeu extends XX_ActionAgent{
		private INFO_WIEGEKARTE_UBERSICHT_NG oWiegekarteAdresse = null;

		public actionWiegekarteNeu(INFO_WIEGEKARTE_UBERSICHT_NG  InfoBlockAdresse) 
		{
			super();
			this.oWiegekarteAdresse = InfoBlockAdresse;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oWiegekarteAdresse.fill_Report();
			new E2_UserSettings_StoreActiveTab().STORE(FS__Adress_Info_Zentrum_NG.STORE_MODUL_KENNER, "9");
		}
	}
	
	public E2_BasicModuleContainer get_oContainerToCloseAfterJump() 
	{
		return oContainerToCloseAfterJump;
	}


	
	public RECORD_ADRESSE get_recADRESSE() 
	{
		return recADRESSE;
	}

	
	public void setHeight(Extent iHeight)
	{
		super.setHeight(iHeight);
		
		if (iHeight.getUnits()==Extent.PX && this.oInfoBlockFuhren!=null)
		{
			this.oInfoBlockFuhren.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-35));
			this.oInfoBlockKontrakte.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-35));
			this.oInfoBlockPreiseEK.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-35));
			this.oInfoBlockPreiseVK.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-35));
			this.oInfoBlockSortenEK.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-35));
			this.oInfoBlockSortenVK.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-35));
			this.oInfoAdressUebersicht.get_oContainerEx().setHeight(new Extent(iHeight.getValue()-70));
		}
	}

		
	
	
	
	//ein user-setting-objekt
	private class E2_UserSettings_StoreActiveTab extends XXX_UserSetting 
	{

		private String cSessionHash = "SESSION_HASH_FIRMENSTAMM_JUMPBOX_SPEICHERE_ACTIVE_TAB";
		
		public E2_UserSettings_StoreActiveTab() 
		{
			super();
		}

		@Override
		public String get_SessionHash() 
		{
			return this.cSessionHash;
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
		
		
	}




	public GridLayoutData get_GL_Titel_Left()
	{
		return GL_Titel_Left;
	}



	public GridLayoutData get_GL_Titel_Center()
	{
		return GL_Titel_Center;
	}



	public GridLayoutData get_GL_Titel_Right()
	{
		return GL_Titel_Right;
	}



	public GridLayoutData get_GL_Normal_Left()
	{
		return GL_Normal_Left;
	}



	public GridLayoutData get_GL_Normal_Center()
	{
		return GL_Normal_Center;
	}



	public GridLayoutData get_GL_Normal_Right()
	{
		return GL_Normal_Right;
	}



	public GridLayoutData get_GL_Highlight_Left()
	{
		return GL_Highlight_Left;
	}



	public GridLayoutData get_GL_Highlight_Center()
	{
		return GL_Highlight_Center;
	}



	public GridLayoutData get_GL_Highlight_Right()
	{
		return GL_Highlight_Right;
	}


	//2015-12-14: spruenge abschaltbar machen
	public boolean is_jump_is_active() {
		return b_jump_is_active;
	}


	//2015-12-14: spruenge abschaltbar machen
	public void set_jump_is_active(boolean b_jump_is_active) {
		this.b_jump_is_active = b_jump_is_active;
	}

	
	
	
}
