package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_ListWithSelector_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextArea_WithSelectorEasy;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextFieldForNumbers;
import panter.gmbh.Echo2.components.unboundDataFields.UB_TextField_With_DatePOPUP_OWN;
import panter.gmbh.Echo2.components.unboundDataFields.VECTOR_UB_FIELDS;
import panter.gmbh.basics4project.myCONST_ENUM.FAXNUMMER_DEF;
import panter.gmbh.basics4project.myCONST_ENUM.MAILDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_SACHBEARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_FIBU_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_SACHBEARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.RECORD_FIBU_ext;

public class FIBU_Container4Mahnung_NG extends E2_BasicModuleContainer 
{

	public static String SESSION_KEY_FOR_STORING_SETTINGS = "FIBU_CONTAINER4MAHNUNG_SPEICHERWERTE";


	/*
	 * reclist mit den zu mahnenden FIBU-Positionen. Ist diese null,
	 * dann eine Mahnung bearbeitet, ist sie nicht null, dann neu erfasst
	 */
	private RECLIST_FIBU   				recListFibu 					= 	null;
	private RECLIST_FIBU_SACHBEARBEITER recListFibuSachbearbeiter 		= null;

	private int            		iMahnstufe = -1;

	private RECORD_MAHNUNG      recMahnung = null;

	private boolean             bNeu = false;

	//noetige felder fuer die mahnung
	private UB_TextFieldForNumbers     			ubMahngebuehrProz = 		new UB_TextFieldForNumbers("MAHNGEBUEHR_PROZ",1, true, "");
	private UB_TextFieldForNumbers      		ubMahngebuehrBetrag = 		new UB_TextFieldForNumbers("MAHNGEBUEHR_BETRAG",2, true, "");
	private UB_TextFieldForNumbers      		ubFristInTagen = 			new UB_TextFieldForNumbers("FRIST_IN_TAGEN",0, false, "");

	private UB_TextArea_WithSelectorEasy        ubEinleitung = 				new UB_TextArea_WithSelectorEasy("MAHNTEXT_EINLEITUNG", false, "","MAHNUNG_EINLEITUNGSTEXT",700,7);
	private UB_TextArea_WithSelectorEasy        ubAbschluss = 				new UB_TextArea_WithSelectorEasy("MAHNTEXT_AUSLEITUNG", false, "","MAHNUNG_ABSCHLUSSTEXT",700,7);

	private UB_TextField_With_DatePOPUP_OWN     ubDatumMahnung = 			new UB_TextField_With_DatePOPUP_OWN("DATUM_MAHNUNG", false,true, myDateHelper.get_cCalendarActual(0),100);
	private UB_TextField_With_DatePOPUP_OWN     ubDatumZahlungenGebucht = 	new UB_TextField_With_DatePOPUP_OWN("DATUM_ZAHLUNGEN_GEBUCHT", false, true, myDateHelper.get_cCalendarActual(-1),100);

	private MyE2_Label                			ubSachbearbeiter1 =         new MyE2_Label();
	private MyE2_Label                			ubSachbearbeiter2 =         new MyE2_Label();
	private MyE2_Label                			ubSachbearbeiter3 =         new MyE2_Label();

	private VECTOR_UB_FIELDS                    vUB_Vector = new VECTOR_UB_FIELDS();

	private MyE2_Button                         btMahnungSpeichern 			= new MyE2_Button(new MyE2_String("Speichern"));
	private FIBU_MASKE_ButtonPrintMahnung_NG    btMahnungSpeichernDrucken 	= null;
	private FIBU_MASKE_ButtonPrintMahnung_NG	btMahnungSpeichernMail		= null;
	private MyE2_Button                         btMahnungLoeschen 			= new MyE2_Button(new MyE2_String("Löschen"));

	private E2_NavigationList                   oNaviList = null;

	private ContactComponents					g_contactGrid = null;
	private SE_ListWithSelector_Component		c_mailSelectionList = null;
	private SE_ListWithSelector_Component		c_faxSelectionList = null;

	//2012-02-13:  weitere, gespeicherte checkbox, die definiert, ob die pdf-datei archiv-rechnungen mitanhaenge soll
	private MyE2_CheckBox  						oCB_AddArchivRechnungen = 	new MyE2_CheckBox(new MyE2_String("Rechnungsarchiv anhängen"),new MyE2_String("Auswählen, wenn die archivierten Rechnungen an die Mahnung angehängt werden soll ..."));

	/*
	 * hashkeys der zu speichernden Werte (benutzerspezifischer Speicher)
	 */
	private Vector<String>		  				vValuesToSave = new Vector<String>();


	private String mahnungID;


	//	private String smallestFibuId;

	private ownActionCloseWindow closeAction;


	private MyE2_MessageVector oMV_Rueck  = new MyE2_MessageVector();

	//neu erfassung
	@SuppressWarnings("unchecked")
	public FIBU_Container4Mahnung_NG(RECLIST_FIBU rec_ListFibuMahnbar,int MahnstufeVorhanden, E2_NavigationList NaviList)  throws myException 
	{
		super();
		this.recListFibu = 	rec_ListFibuMahnbar;
		this.iMahnstufe = 	MahnstufeVorhanden+1;
		this.bNeu = true;
		this.oNaviList = NaviList;

		this.btMahnungSpeichernMail 	= new FIBU_MASKE_ButtonPrintMahnung_NG(this, processtype.MAIL);
		this.btMahnungSpeichernDrucken 	= new FIBU_MASKE_ButtonPrintMahnung_NG(this, processtype.PRINT);

		this.c_mailSelectionList = new SE_ListWithSelector_Component(NaviList, btMahnungSpeichernDrucken, btMahnungSpeichernMail, null, processtype.MAIL);
		this.c_mailSelectionList.preloadContactList(NaviList.get_vSelectedIDs_Unformated(), MAILDEF.EMAIL_MAHNUNG);
		this.c_mailSelectionList.setOnly_five_contact(false);
		this.c_mailSelectionList.setFixedSize(350,100);

		this.c_faxSelectionList = new SE_ListWithSelector_Component(NaviList,  null, btMahnungSpeichernMail,  null, processtype.PRINT);
		this.c_faxSelectionList.setWidth(new Extent(350));
		
		this.g_contactGrid = new ContactComponents(c_faxSelectionList, c_mailSelectionList);

		this.build_Maske();

		//den sachbearbeiter voreinstellen

		this.define_values_to_save();

		HashMap<String, String> hmGespeicherteWerte = (HashMap<String, String>)new E2_UserSetting_HashMap(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).get_Settings(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS);

		if (hmGespeicherteWerte!=null)
		{
			if (iMahnstufe==1)
			{
				if (S.isFull(hmGespeicherteWerte.get("MAHNGEBUEHR_PROZ_STUFE1"))) {this.ubMahngebuehrProz.set_StartValue(hmGespeicherteWerte.get("MAHNGEBUEHR_PROZ_STUFE1"));   }
				if (S.isFull(hmGespeicherteWerte.get("MAHNGEBUEHR_BETRAG_STUFE1"))) {this.ubMahngebuehrBetrag.set_StartValue(hmGespeicherteWerte.get("MAHNGEBUEHR_BETRAG_STUFE1"));   }
				if (S.isFull(hmGespeicherteWerte.get("FRIST_IN_TAGEN_STUFE1"))) {this.ubFristInTagen.set_StartValue(hmGespeicherteWerte.get("FRIST_IN_TAGEN_STUFE1"));   }
			}
			else if (iMahnstufe==2)
			{
				if (S.isFull(hmGespeicherteWerte.get("MAHNGEBUEHR_PROZ_STUFE2"))) {this.ubMahngebuehrProz.set_StartValue(hmGespeicherteWerte.get("MAHNGEBUEHR_PROZ_STUFE2"));   }
				if (S.isFull(hmGespeicherteWerte.get("MAHNGEBUEHR_BETRAG_STUFE2"))) {this.ubMahngebuehrBetrag.set_StartValue(hmGespeicherteWerte.get("MAHNGEBUEHR_BETRAG_STUFE2"));   }
				if (S.isFull(hmGespeicherteWerte.get("FRIST_IN_TAGEN_STUFE2"))) {this.ubFristInTagen.set_StartValue(hmGespeicherteWerte.get("FRIST_IN_TAGEN_STUFE2"));   }
			}
			else if (iMahnstufe==3)
			{
				if (S.isFull(hmGespeicherteWerte.get("MAHNGEBUEHR_PROZ_STUFE3"))) {this.ubMahngebuehrProz.set_StartValue(hmGespeicherteWerte.get("MAHNGEBUEHR_PROZ_STUFE3"));   }
				if (S.isFull(hmGespeicherteWerte.get("MAHNGEBUEHR_BETRAG_STUFE3"))) {this.ubMahngebuehrBetrag.set_StartValue(hmGespeicherteWerte.get("MAHNGEBUEHR_BETRAG_STUFE3"));   }
				if (S.isFull(hmGespeicherteWerte.get("FRIST_IN_TAGEN_STUFE3"))) {this.ubFristInTagen.set_StartValue(hmGespeicherteWerte.get("FRIST_IN_TAGEN_STUFE3"));   }
			} 

			if (S.isFull(hmGespeicherteWerte.get("DRUCKE_MAHNUNGEN_MIT_ANHANG_RECHNUNGEN")))
			{
				this.oCB_AddArchivRechnungen.setSelected(hmGespeicherteWerte.get("DRUCKE_MAHNUNGEN_MIT_ANHANG_RECHNUNGEN").equals("Y"));
			}

		}
		closeAction  = new ownActionCloseWindow(this);

		this.add_CloseActions(closeAction);
	}


	//bearbeiten einer vorhandenen mahnung
	@SuppressWarnings("unchecked")
	public FIBU_Container4Mahnung_NG(RECORD_MAHNUNG  rec_Mahnung, E2_NavigationList NaviList) throws myException 
	{
		super();
		this.mahnungID =rec_Mahnung.get_ID_MAHNUNG_cUF();
		this.recMahnung = new RECORD_MAHNUNG(getMahnungID());
		this.oNaviList = NaviList;

		this.btMahnungSpeichernMail 	= new FIBU_MASKE_ButtonPrintMahnung_NG(this, processtype.MAIL);
		this.btMahnungSpeichernDrucken 	= new FIBU_MASKE_ButtonPrintMahnung_NG(this, processtype.PRINT);


		this.c_mailSelectionList = new SE_ListWithSelector_Component(NaviList, btMahnungSpeichernDrucken, btMahnungSpeichernMail, null, processtype.MAIL);
		this.c_mailSelectionList.setOnly_five_contact(false);
		this.c_mailSelectionList.setFixedSize(350,100);

		this.c_faxSelectionList = new SE_ListWithSelector_Component(NaviList,  null, btMahnungSpeichernMail,  null, processtype.PRINT);
		this.c_faxSelectionList.setWidth(new Extent(350));
		this.g_contactGrid = new ContactComponents(c_faxSelectionList, c_mailSelectionList);

		this.recListFibu = 	new RECLIST_FIBU();

		for (int i=0;i<this.recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().get_vKeyValues().size();i++)
		{
			this.recListFibu.ADD(this.recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().get(i).get_UP_RECORD_FIBU_id_fibu(),false);
		}

		this.iMahnstufe = 	this.recMahnung.get_MAHNSTUFE_bdValue(new BigDecimal(-1)).intValue();
		this.bNeu = 		false;

		this.build_Maske();

		this.ubMahngebuehrProz.set_StartValue(this.recMahnung.get_MAHNGEBUEHR_PROZ_cF_NN(""));
		this.ubMahngebuehrBetrag.set_StartValue(this.recMahnung.get_MAHNGEBUEHR_BETRAG_cF_NN(""));
		this.ubFristInTagen.set_StartValue(this.recMahnung.get_FRIST_IN_TAGEN_cF_NN(""));
		this.ubEinleitung.set_StartValue(this.recMahnung.get_MAHNTEXT_EINLEITUNG_cF_NN(""));
		this.ubAbschluss.set_StartValue(this.recMahnung.get_MAHNTEXT_AUSLEITUNG_cF_NN(""));
		this.ubDatumMahnung.set_StartValue(this.recMahnung.get_DATUM_MAHNUNG_cF_NN(""));
		this.ubDatumZahlungenGebucht.set_StartValue(this.recMahnung.get_DATUM_ZAHLUNGEN_GEBUCHT_cF_NN(""));

		this.define_values_to_save();

		HashMap<String, String> hmGespeicherteWerte = (HashMap<String, String>)new E2_UserSetting_HashMap(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).get_Settings(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS);

		if (hmGespeicherteWerte!=null && S.isFull(hmGespeicherteWerte.get("DRUCKE_MAHNUNGEN_MIT_ANHANG_RECHNUNGEN")))
		{
			this.oCB_AddArchivRechnungen.setSelected(hmGespeicherteWerte.get("DRUCKE_MAHNUNGEN_MIT_ANHANG_RECHNUNGEN").equals("Y"));
		}

		closeAction  = new ownActionCloseWindow(this);

		this.add_CloseActions(closeAction);

	}

	//den namensvector fuer die usersettings-hashmap definieren
	private void define_values_to_save()
	{
		this.vValuesToSave.add("FRIST_IN_TAGEN_STUFE1");
		this.vValuesToSave.add("MAHNGEBUEHR_PROZ_STUFE1");
		this.vValuesToSave.add("MAHNGEBUEHR_BETRAG_STUFE1");

		this.vValuesToSave.add("FRIST_IN_TAGEN_STUFE2");
		this.vValuesToSave.add("MAHNGEBUEHR_PROZ_STUFE2");
		this.vValuesToSave.add("MAHNGEBUEHR_BETRAG_STUFE2");

		this.vValuesToSave.add("FRIST_IN_TAGEN_STUFE3");
		this.vValuesToSave.add("MAHNGEBUEHR_PROZ_STUFE3");
		this.vValuesToSave.add("MAHNGEBUEHR_BETRAG_STUFE3");

		this.vValuesToSave.add("DRUCKE_MAHNUNGEN_MIT_ANHANG_RECHNUNGEN");

		//2012-10-18: die einstellten benutzer muessen auch gesichert werden
		this.vValuesToSave.add(RECORD_MAHNUNG.FIELD__ID_USER_SACHBEARBEITER_1);
		this.vValuesToSave.add(RECORD_MAHNUNG.FIELD__ID_USER_SACHBEARBEITER_2);
		this.vValuesToSave.add(RECORD_MAHNUNG.FIELD__ID_USER_SACHBEARBEITER_3);

	}


	private void build_Maske() throws myException
	{
		this.setSachbearbeiter();

		
		
		//		this.btMahnungSpeichernDrucken.set_bEnabled_For_Edit(false);
		this.btMahnungSpeichernMail.set_bEnabled_For_Edit(false);

		this.btMahnungSpeichern.setToolTipText(new MyE2_String("Mahnung speichern und Maske schliessen").CTrans());
		this.btMahnungLoeschen.setToolTipText(new MyE2_String("Mahnung löschen und Maske schliessen").CTrans());

		this.vUB_Vector.add(ubMahngebuehrProz);
		this.vUB_Vector.add(ubMahngebuehrBetrag);
		this.vUB_Vector.add(ubFristInTagen);
		this.vUB_Vector.add(ubEinleitung);
		this.vUB_Vector.add(ubAbschluss);
		this.vUB_Vector.add(ubDatumMahnung);
		this.vUB_Vector.add(ubDatumZahlungenGebucht);

		//zuerst die "innen-Liste" der Belege erfassen
		MyE2_Grid  oGridBelege = new MyE2_Grid(6,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		GridLayoutData GL_Titel_Inlay = LayoutDataFactory.get_GridLayout(new Insets(1,1,5,1),  new E2_ColorDDark(),  new Alignment(Alignment.LEFT, Alignment.TOP),1);
		GridLayoutData GL_Text_Inlay = LayoutDataFactory.get_GridLayout( new Insets(1,1,5,1),  new E2_ColorBase() ,  new Alignment(Alignment.LEFT, Alignment.TOP),1);
		GridLayoutData GL_Titel_InlayRight = LayoutDataFactory.get_GridLayout(new Insets(1,1,5,1),  new E2_ColorDDark(),  new Alignment(Alignment.RIGHT, Alignment.TOP),1);
		GridLayoutData GL_Text_InlayRight = LayoutDataFactory.get_GridLayout( new Insets(1,1,5,1),  new E2_ColorBase() ,  new Alignment(Alignment.RIGHT, Alignment.TOP),1);


		oGridBelege.add_raw(new MyE2_Label(new MyE2_String("Buchungsnummer"), 	MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Titel_Inlay);
		oGridBelege.add_raw(new MyE2_Label(new MyE2_String("Datum"), 			MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Titel_Inlay);
		oGridBelege.add_raw(new MyE2_Label(new MyE2_String("Fälligkeit"), 		MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Titel_Inlay);
		oGridBelege.add_raw(new MyE2_Label(new MyE2_String("Verzug (Tage)"), 	MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Titel_InlayRight);
		oGridBelege.add_raw(new MyE2_Label(new MyE2_String("Betrag"), 			MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Titel_InlayRight);
		oGridBelege.add_raw(new MyE2_Label(new MyE2_String("Offen"), 			MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Titel_InlayRight);

		Vector<RECORD_FIBU> vRecFibu = recListFibu.GET_SORTED_VECTOR(bibALL.get_Vector(RECORD_FIBU.FIELD__ZAHLUNGSZIEL), true);

		for (int i=0;i<vRecFibu.size();i++)
		{

			RECORD_FIBU_ext  recFibu = new RECORD_FIBU_ext(vRecFibu.get(i));
			GregorianCalendar calJetzt = new GregorianCalendar();
			GregorianCalendar calPos =   new MyDate(recFibu.get_ZAHLUNGSZIEL_cF()).get_Calendar();

			long iDiff = 999999;

			if (calPos!=null)
			{
				iDiff = myDateHelper.get_DayDifference_Date2_MINUS_Date1(calPos, calJetzt);
			}


			String cBuchungsnummer="-";
			if (recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null)
			{
				cBuchungsnummer = recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().get_BUCHUNGSNUMMER_cF();
			}

			oGridBelege.add_raw(new MyE2_Label(cBuchungsnummer, 																				MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Text_Inlay);
			oGridBelege.add_raw(new MyE2_Label(recFibu.get_BUCHUNGSDATUM_cF(), 																	MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Text_Inlay);
			oGridBelege.add_raw(new MyE2_Label(recFibu.get_ZAHLUNGSZIEL_cF(), 																	MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Text_Inlay);
			oGridBelege.add_raw(new MyE2_Label(""+iDiff,																						MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Text_InlayRight);

			oGridBelege.add_raw(new MyE2_Label(MyNumberFormater.formatDez(recFibu.get_ZahlBetrag_FW_Abhaengig_von_SkontoSchalter(),2,true),		MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Text_InlayRight);
			oGridBelege.add_raw(new MyE2_Label(MyNumberFormater.formatDez(recFibu.get_bdMahnbetrag(),2,true),									MyE2_Label.STYLE_SMALL_PLAIN()),	GL_Text_InlayRight);
		}


		MyE2_Grid  oGrid = new MyE2_Grid(5,MyE2_Grid.STYLE_GRID_DDARK_BORDER());

		GridLayoutData GL_Titel = 				LayoutDataFactory.get_GridLayout(E2_INSETS.I_5_5_5_5, new E2_ColorDDark(), new Alignment(Alignment.LEADING, Alignment.TOP),oGrid.getSize()-2);
		GridLayoutData GL_Titel2 = 				LayoutDataFactory.get_GridLayout(E2_INSETS.I_5_5_5_5, new E2_ColorDDark(), new Alignment(Alignment.LEADING, Alignment.TOP),2);

		GridLayoutData GL_Warnung = 			LayoutDataFactory.get_GridLayout(E2_INSETS.I_5_5_5_5, new E2_ColorAlarm(), new Alignment(Alignment.CENTER, Alignment.CENTER),oGrid.getSize());

		GridLayoutData GL_BeschriftungLeft = 	LayoutDataFactory.get_GridLayout(E2_INSETS.I_5_5_5_5, new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),1);
		GridLayoutData GL_OneLine = 			LayoutDataFactory.get_GridLayout(E2_INSETS.I_5_5_5_5, new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),oGrid.getSize()-1);


		GridLayoutData GL_OneLineInGroup = 		LayoutDataFactory.get_GridLayout(new Insets(0,0, 5, 0), new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),1);

		//2012-01-18: pruefung auf keine_mahnung felder im rechungskopf oder firmenstamm
		boolean bWarnungKeineMahnung = false;

		if (this.recListFibu!=null)
		{
			for (int i=0;i<this.recListFibu.get_vKeyValues().size();i++)
			{
				RECORD_FIBU  recFibu = this.recListFibu.get(i);
				if (recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && recFibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_KEINE_MAHNUNGEN_YES())
				{
					bWarnungKeineMahnung = true;
					break;
				}

				if (	recFibu.get_UP_RECORD_ADRESSE_id_adresse()!=null &&
						recFibu.get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get_vKeyValues().size()==1 &&
						recFibu.get_UP_RECORD_ADRESSE_id_adresse().get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).is_KEINE_MAHNUNGEN_YES())
				{
					bWarnungKeineMahnung = true;
					break;
				}
			}
		}

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Mahnung -- Stufe: ",true,""+this.iMahnstufe,false),MyE2_Label.STYLE_TITEL_NORMAL()),  GL_Titel);
		MyE2_Label oLabelID = new MyE2_Label(new MyE2_String("<Neuerfassung>"), MyE2_Label.STYLE_SMALL_PLAIN());
		if (!this.bNeu)
		{
			oLabelID = new MyE2_Label(new MyE2_String("ID: ",true,this.recMahnung.get_ID_MAHNUNG_cF_NN(""),false), MyE2_Label.STYLE_SMALL_PLAIN());

			Vector<String> idFibu = bibALL.get_Vector(this.recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().get(0).get_ID_FIBU_cUF()); 	

			Vector<String> v_fax = new Vector<String>();
			v_fax.add(this.recMahnung.get_FAXNUMMER_MAHNUNG_cF());

			Vector<String> v_mail = new Vector<>();
			v_mail.add(this.recMahnung.get_EMAIL1_MAHNUNG_cUF());
			v_mail.add(this.recMahnung.get_EMAIL2_MAHNUNG_cUF());
			v_mail.add(this.recMahnung.get_EMAIL3_MAHNUNG_cUF());
			v_mail.add(this.recMahnung.get_EMAIL4_MAHNUNG_cUF());
			v_mail.add(this.recMahnung.get_EMAIL5_MAHNUNG_cUF());

			c_faxSelectionList.preloadContactList(idFibu, FAXNUMMER_DEF.MAHNUNG, false, false);
			c_faxSelectionList.updateCheckedContact(v_fax);
			
			this.c_mailSelectionList.preloadContactList(idFibu, MAILDEF.EMAIL_MAHNUNG);
			if(c_mailSelectionList.updateCheckedContact(v_mail)){
				btMahnungSpeichernMail.set_bEnabled_For_Edit(true);
			}

		}else{
			this.c_faxSelectionList.preloadContactList(oNaviList.get_vSelectedIDs_Unformated(), FAXNUMMER_DEF.MAHNUNG, false, false);
			this.c_mailSelectionList.preloadContactList(oNaviList.get_vSelectedIDs_Unformated(), MAILDEF.EMAIL_MAHNUNG);
		}
		oGrid.add_raw(oLabelID,  GL_Titel2);

		//2012-01-18: pruefung auf keine_mahnung felder im rechungskopf oder firmenstamm
		if (bWarnungKeineMahnung)
		{
			oGrid.add(new MyE2_Label(new MyE2_String("Achtung! Der Mahnungsadressat oder einer der Belege trägt das Merkmal: <Keine Mahnung>"), new E2_FontBold(2)).get_InBorderGrid(null, null, E2_INSETS.I_5_5_5_5),GL_Warnung);
		}

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Datum der Mahnung:")),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubDatumMahnung,  GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Zahlungen erfasst bis:")),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubDatumZahlungenGebucht,  GL_OneLine);

		int lab[] = {200,200,200};
		oGrid.add_raw(new MyE2_Label(new MyE2_String("Sachbearbeiter:")),  GL_BeschriftungLeft);
		oGrid.add_raw(new E2_ComponentGroupHorizontal_NG(this.ubSachbearbeiter1,this.ubSachbearbeiter2,this.ubSachbearbeiter3,  lab),GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Kontakte:")), GL_BeschriftungLeft	); 
		oGrid.add_raw(g_contactGrid,GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Einleitung:")),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubEinleitung,  GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Liste:")),  GL_BeschriftungLeft);
		oGrid.add_raw(oGridBelege, GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Abschlusstext:")),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubAbschluss,  GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Frist (Tage):")),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubFristInTagen,  GL_OneLine);

		oGrid.add_raw(new MyE2_Label(new MyE2_String("Mahngebühr:")),  GL_BeschriftungLeft);
		oGrid.add_raw(new E2_ComponentGroupHorizontal_NG(this.ubMahngebuehrProz, new MyE2_Label(new MyE2_String("(%)")), this.ubMahngebuehrBetrag, new MyE2_Label(new MyE2_String("("+this.recListFibu.get(0).get_WAEHRUNG_FREMD_cUF_NN("??")+")")), GL_OneLineInGroup),
				GL_OneLine);

		E2_ComponentGroupHorizontal_NG a = new E2_ComponentGroupHorizontal_NG(
				this.btMahnungSpeichern,
				this.btMahnungSpeichernDrucken,
				this.btMahnungSpeichernMail,
				this.oCB_AddArchivRechnungen,
				this.btMahnungLoeschen, 
				LayoutDataFactory.get_GridLayout(new Insets(0,0,10,0), new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),1));

		oGrid.add( a,5, 1, new Insets(5,20, 0, 0), new Alignment(Alignment.CENTER, Alignment.TOP));


		this.add(oGrid,E2_INSETS.I_5_5_5_5);

		this.btMahnungSpeichern.add_oActionAgent(new actionSave());
		this.btMahnungLoeschen.add_oActionAgent(new actionDeleteMahnung());

		if (this.bNeu)
		{
			this.btMahnungLoeschen.set_bEnabled_For_Edit(false);
		}

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(930), new Extent(680), new MyE2_String(this.bNeu?"Mahnung erfassen":"Mahnung bearbeiten"));

	}


	private void setSachbearbeiter() throws myException {

		this.recListFibuSachbearbeiter =new RECLIST_FIBU_SACHBEARBEITER(RECORD_FIBU_SACHBEARBEITER.FIELD__ID_MANDANT + "=" + bibALL.get_ID_MANDANT(), RECORD_FIBU_SACHBEARBEITER.FIELD__ID_USER);

		if(recListFibuSachbearbeiter.size()==0){
			bibMSG.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Es ist kein FIBU Sachbearbeiter eingetragen.")));
		}
		else{
			RECORD_FIBU_SACHBEARBEITER[] listOfSachbearbeiter = {null,null,null};

			int i=0;
			for(RECORD_FIBU_SACHBEARBEITER rec: recListFibuSachbearbeiter){
				i++;
				if(i>3){
					break;
				}else{
					listOfSachbearbeiter[i-1] = rec;//.get_UP_RECORD_USER_id_user().get_VORNAME_cUF() + " " +rec.get_UP_RECORD_USER_id_user().get_NAME1_cF();
				}
			}

			if(! (listOfSachbearbeiter[0] == null)){
				ubSachbearbeiter1.setText(
						listOfSachbearbeiter[0].get_UP_RECORD_USER_id_user().get_VORNAME_cF_NN("")+" "+
								listOfSachbearbeiter[0].get_UP_RECORD_USER_id_user().get_NAME1_cF_NN("")
						);
				ubSachbearbeiter1.EXT().set_C_MERKMAL(listOfSachbearbeiter[0].get_ID_USER_cF_NN(""));
			}
			if(! (listOfSachbearbeiter[1] == null)){
				ubSachbearbeiter2.setText(
						listOfSachbearbeiter[1].get_UP_RECORD_USER_id_user().get_VORNAME_cF_NN("")+" "+
								listOfSachbearbeiter[1].get_UP_RECORD_USER_id_user().get_NAME1_cF_NN("")
						);
				ubSachbearbeiter2.EXT().set_C_MERKMAL(listOfSachbearbeiter[1].get_ID_USER_cF_NN(""));
			}
			if(! (listOfSachbearbeiter[2] == null)){
				ubSachbearbeiter3.setText(
						listOfSachbearbeiter[2].get_UP_RECORD_USER_id_user().get_VORNAME_cF_NN("")+" "+
								listOfSachbearbeiter[2].get_UP_RECORD_USER_id_user().get_NAME1_cF_NN("")
						);
				ubSachbearbeiter3.EXT().set_C_MERKMAL(listOfSachbearbeiter[2].get_ID_USER_cF_NN(""));
			}
		}
	}


	public RECORD_MAHNUNG get_recMahnung() 
	{
		return recMahnung;
	}


	public MyE2_MessageVector save_mask() throws myException
	{
		this.oMV_Rueck = new MyE2_MessageVector();

		MyE2_MessageVector  oMV_Check = this.vUB_Vector.get_MV_AllFieldsAreOK_ShowErrorInput();

		if (oMV_Check.get_bIsOK())
		{
			if (this.bNeu)
			{
				RECORDNEW_MAHNUNG recNew_MAHNUNG = new RECORDNEW_MAHNUNG();

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_MAHNSTUFE(this.iMahnstufe));

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_DATUM_FRIST(""));                      
				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_DATUM_MAHNUNG(this.ubDatumMahnung.get_cText()));

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_MAHNTEXT_EINLEITUNG(this.ubEinleitung.get_cText()));
				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(this.ubAbschluss.get_cText()));

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_FRIST_IN_TAGEN(this.ubFristInTagen.get_cText()));
				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(this.ubDatumZahlungenGebucht.get_cText()));

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_MAHNGEBUEHR_PROZ(this.ubMahngebuehrProz.get_cText()));
				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_MAHNGEBUEHR_BETRAG(this.ubMahngebuehrBetrag.get_cText()));

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_FAXNUMMER_MAHNUNG(this.c_faxSelectionList.getFaxNummer()));

				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(this.ubSachbearbeiter1.EXT().get_C_MERKMAL()));
				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(this.ubSachbearbeiter2.EXT().get_C_MERKMAL()));
				oMV_Check.add_MESSAGE(recNew_MAHNUNG.set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(this.ubSachbearbeiter3.EXT().get_C_MERKMAL()));

				if(! (S.isEmpty(c_faxSelectionList.getFaxNummer()) )){
					recNew_MAHNUNG.set_NEW_VALUE_FAXNUMMER_MAHNUNG(c_faxSelectionList.getFaxNummer());
				}

				if(c_mailSelectionList.getSelectedKontakt().size()>0){

					if(c_mailSelectionList.getSelectedKontakt().size()>5){
						g_contactGrid.show_InputStatus(false);
						oMV_Check.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es können maximal 5 eMail-Adressen ausgewählt werden !")));
					}
					else{

						boolean[] sizeTab = {false, false, false, false, false};
						for(int i= 0; i<c_mailSelectionList.getSelectedKontakt().size();i++){
							sizeTab[i]=true;
						}

						if(sizeTab[0]){
							recNew_MAHNUNG.set_NEW_VALUE_EMAIL1_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(0));
						}
						if(sizeTab[1]){
							recNew_MAHNUNG.set_NEW_VALUE_EMAIL2_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(1));
						}
						if(sizeTab[2]){
							recNew_MAHNUNG.set_NEW_VALUE_EMAIL3_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(2));
						}
						if( sizeTab[3]){
							recNew_MAHNUNG.set_NEW_VALUE_EMAIL4_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(3));
						}else{

						}
						if( sizeTab[4]){
							recNew_MAHNUNG.set_NEW_VALUE_EMAIL5_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(4));
						}
					}

				}

				if (oMV_Check.get_bIsOK())
				{
					Vector<String> vSQL = new Vector<String>();
					vSQL.add(recNew_MAHNUNG.get_InsertSQLStatementWith_Id_Field(false,true));
					for (int i=0;i<this.recListFibu.get_vKeyValues().size();i++)
					{
						RECORDNEW_FIBU_MAHNUNG  recNew = new RECORDNEW_FIBU_MAHNUNG();
						recNew.set_NEW_VALUE_ID_FIBU(this.recListFibu.get(i).get_ID_FIBU_cUF());
						recNew.set_NEW_VALUE_ID_MAHNUNG(recNew_MAHNUNG.get_cLastSEQ_NUMBER());
						vSQL.add(recNew.get_InsertSQLStatementWith_Id_Field(false,true));
					}

					oMV_Rueck.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL, true));

					if (oMV_Rueck.get_bIsOK())
					{
						this.mahnungID = recNew_MAHNUNG.get_cLastSEQ_NUMBER();
						this.recMahnung = new RECORD_MAHNUNG(this.mahnungID);
					}


				}
				else
				{
					oMV_Rueck.add_MESSAGE(oMV_Check);
				}

			}
			else
			{
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_DATUM_FRIST(""));                       //wird erstmal nicht benutzt
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_DATUM_MAHNUNG(this.ubDatumMahnung.get_cText()));

				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_MAHNTEXT_EINLEITUNG(this.ubEinleitung.get_cText()));
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_MAHNTEXT_AUSLEITUNG(this.ubAbschluss.get_cText()));

				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_FRIST_IN_TAGEN(this.ubFristInTagen.get_cText()));
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_DATUM_ZAHLUNGEN_GEBUCHT(this.ubDatumZahlungenGebucht.get_cText()));

				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_MAHNGEBUEHR_PROZ(this.ubMahngebuehrProz.get_cText()));
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_MAHNGEBUEHR_BETRAG(this.ubMahngebuehrBetrag.get_cText()));

				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_ID_USER_SACHBEARBEITER_1(this.ubSachbearbeiter1.EXT().get_C_MERKMAL()));
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_ID_USER_SACHBEARBEITER_2(this.ubSachbearbeiter2.EXT().get_C_MERKMAL()));
				oMV_Check.add_MESSAGE(this.recMahnung.set_NEW_VALUE_ID_USER_SACHBEARBEITER_3(this.ubSachbearbeiter3.EXT().get_C_MERKMAL()));

				recMahnung.set_NEW_VALUE_FAXNUMMER_MAHNUNG(c_faxSelectionList.getFaxNummer());

				boolean[] sizeTab = {false, false, false, false, false};
				ArrayList<String> selectedContactList = c_mailSelectionList.getSelectedKontakt();
				if(selectedContactList.size()>5){
					g_contactGrid.show_InputStatus(false);
					oMV_Check.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es können maximal 5 eMail-Adressen ausgewählt werden !")));
				}else{
					for(int i= 0; i<selectedContactList.size();i++){

						sizeTab[i]=true;
					}

					if(sizeTab[0]){
						recMahnung.set_NEW_VALUE_EMAIL1_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(0));
					}else{
						recMahnung.set_NEW_VALUE_EMAIL1_MAHNUNG(null);
					}
					if(sizeTab[1]){
						recMahnung.set_NEW_VALUE_EMAIL2_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(1));
					}else{
						recMahnung.set_NEW_VALUE_EMAIL2_MAHNUNG(null);
					}
					if(sizeTab[2]){
						recMahnung.set_NEW_VALUE_EMAIL3_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(2));
					}else{
						recMahnung.set_NEW_VALUE_EMAIL3_MAHNUNG(null);
					}
					if( sizeTab[3]){
						recMahnung.set_NEW_VALUE_EMAIL4_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(3));
					}else{
						recMahnung.set_NEW_VALUE_EMAIL4_MAHNUNG(null);
					}
					if( sizeTab[4]){
						recMahnung.set_NEW_VALUE_EMAIL5_MAHNUNG(c_mailSelectionList.getSelectedKontakt().get(4));
					}else{
						recMahnung.set_NEW_VALUE_EMAIL5_MAHNUNG(null);
					}

				}

				if (oMV_Check.get_bIsOK())
				{
					oMV_Rueck.add_MESSAGE(this.recMahnung.UPDATE(null, true));
				}
				else
				{
					oMV_Rueck.add_MESSAGE(oMV_Check);
				}

			}

			//jetzt die spezifischen werte speichern


			//jetzt die zu speichernden werte festlegen
			@SuppressWarnings("unchecked")
			HashMap<String, String> hmGespeicherteWerte = (HashMap<String, String>)new E2_UserSetting_HashMap(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).get_Settings(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS);
			if (hmGespeicherteWerte==null)
			{
				//leere huelle vorbereiten
				hmGespeicherteWerte = new HashMap<String, String>();
				for (int i=0;i<this.vValuesToSave.size();i++)
				{
					hmGespeicherteWerte.put(this.vValuesToSave.get(i), "");
				}
				new E2_UserSetting_HashMap(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).STORE(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, hmGespeicherteWerte);
			}

			hmGespeicherteWerte.put("FRIST_IN_TAGEN_STUFE"+this.iMahnstufe, this.ubFristInTagen.get_cText());
			hmGespeicherteWerte.put("MAHNGEBUEHR_PROZ_STUFE"+this.iMahnstufe, this.ubMahngebuehrProz.get_cText());
			hmGespeicherteWerte.put("MAHNGEBUEHR_BETRAG_STUFE"+this.iMahnstufe, this.ubMahngebuehrBetrag.get_cText());

			hmGespeicherteWerte.put("DRUCKE_MAHNUNGEN_MIT_ANHANG_RECHNUNGEN", this.oCB_AddArchivRechnungen.isSelected()?"Y":"N");

			//2012-10-18: die sachbearbeiter muessen auch gespeichert werden
			if(!(ubSachbearbeiter1.EXT().get_C_MERKMAL() == null)){
				hmGespeicherteWerte.put(RECORD_MAHNUNG.FIELD__ID_USER_SACHBEARBEITER_1, this.ubSachbearbeiter1.EXT().get_C_MERKMAL());
			}
			if(!(ubSachbearbeiter2.EXT().get_C_MERKMAL() == null)){
				hmGespeicherteWerte.put(RECORD_MAHNUNG.FIELD__ID_USER_SACHBEARBEITER_2, this.ubSachbearbeiter2.EXT().get_C_MERKMAL());
			}
			if(!(ubSachbearbeiter3.EXT().get_C_MERKMAL() == null)){
				hmGespeicherteWerte.put(RECORD_MAHNUNG.FIELD__ID_USER_SACHBEARBEITER_3, this.ubSachbearbeiter3.EXT().get_C_MERKMAL());
			}

			new E2_UserSetting_HashMap(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).STORE(FIBU_Container4Mahnung_NG.SESSION_KEY_FOR_STORING_SETTINGS, hmGespeicherteWerte);

		}
		else
		{
			oMV_Rueck.add_MESSAGE(oMV_Check);
		}

		return oMV_Rueck;

	}


	private class actionSave extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			FIBU_Container4Mahnung_NG oThis = FIBU_Container4Mahnung_NG.this;
			
			MyE2_MessageVector  oMV_Check = oThis.save_mask();

			if (oMV_Check.get_bIsOK())
			{
				oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			}
			else
			{
				bibMSG.add_MESSAGE(oMV_Check);
			}

		}
	}



	private class actionDeleteMahnung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FIBU_Container4Mahnung_NG  oThis = FIBU_Container4Mahnung_NG.this;
			if (oThis.bNeu==false)
			{
				//zuerst pruefen, ob die mahnstufe dieser mahnung bei allen beteiligten fibu-saetzen die neueste ist 
				for (int i=0;i<oThis.recListFibu.get_vKeyValues().size();i++)
				{
					RECORD_FIBU_ext recFibu = new RECORD_FIBU_ext(oThis.recListFibu.get(i));

					if (recFibu.get_MAXIMALE_MAHNSTUFE()!=oThis.iMahnstufe)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es kann nur eine Mahnung gelöscht werden, wenn diese Mahnung bei allen die neueste ist !!")));
					}
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bei Neuerfassung kann nicht gelöscht werden !!")));
			}

			if (bibMSG.get_bIsOK())
			{
				new ownYesNoMessage();   //fragt nochmal
			}
		}

		private class ownYesNoMessage extends E2_MessageBoxYesNo
		{
			public ownYesNoMessage() throws myException
			{
				super(	new MyE2_String("Mahnung LÖSCHEN ??"), 
						new MyE2_String("JA"), new MyE2_String("NEIN"), true, true, new MyE2_Label(new MyE2_String("SICHER ??"),MyE2_Label.STYLE_TITEL_BIG()),
						null, new Extent(300), new Extent(150));

				this.set_ActionAgent4Yes(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO oExecInfo) throws myException
					{
						bibMSG.add_MESSAGE(new RECORD_MAHNUNG(FIBU_Container4Mahnung_NG.this.recMahnung.get_ID_MAHNUNG_cUF()).DELETE());

						if (bibMSG.get_bIsOK())
						{
							ownYesNoMessage.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							FIBU_Container4Mahnung_NG.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Mahnung wurde gelöscht !")));
						}
					}
				},false);
			}

		}



	}

	private class ownActionCloseWindow extends XX_ActionAgentWhenCloseWindow
	{
		public ownActionCloseWindow(E2_BasicModuleContainer container)
		{
			super(container);
		}
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FIBU_Container4Mahnung_NG oThis = FIBU_Container4Mahnung_NG.this;
			for (int i=0;i<oThis.recListFibu.get_vKeyValues().size();i++)
			{
				E2_ComponentMAP  oMapFibu = oThis.oNaviList.get_ComponentMAP(oThis.recListFibu.get(i).get_ID_FIBU_cUF());
				if (oMapFibu!=null)
				{
					oMapFibu._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);

				}
				oThis.oNaviList.refresh_pageinfo_in_navigator(oThis.oNaviList.get_iActualPage());
			}
		}
	}

	private class ContactComponents extends MyE2_Grid{

		private MyE2_Grid fax_grid, mail_grid = null;

		public ContactComponents(MyE2_Grid c_faxGrid, MyE2_Grid c_mailGrid) {
			super(2);
			this.mail_grid = c_mailGrid;
			this.fax_grid = c_faxGrid;

			
			int[] breite = {400,400};
			this.setRowHeight(1, new Extent(100));
			this.set_Spalten(breite);
			this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
			this.setWidth(new Extent(400));
			this.add(new MyE2_Label(new MyE2_String("Fax Nummer"),new E2_FontBold()));
			this.add(new MyE2_Label(new MyE2_String("Email"),new E2_FontBold()));
			this.add(fax_grid);
			this.add(mail_grid);
		}
		
		@Override
		public void show_InputStatus(boolean bInputIsOK) {
			if(bInputIsOK){
				MutableStyle oStyle = new MutableStyle();
				oStyle.setProperty(Grid.PROPERTY_BORDER,new Border(1,new E2_ColorAlarm(),Border.STYLE_SOLID));
				oStyle.setProperty( Grid.PROPERTY_INSETS, E2_INSETS.I_0_0_0_0);
				oStyle.setProperty(Grid.PROPERTY_WIDTH, new Extent(99,Extent.PERCENT));
				this.setStyle(oStyle);
			}else this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
		}
	}

	public ownActionCloseWindow getCloseAction() {
		return closeAction;
	}

	public E2_NavigationList get_oNaviList() 
	{
		return oNaviList;
	}


	public boolean isbNeu() {
		return bNeu;
	}


	public void setbNeu(boolean bNeu) {
		this.bNeu = bNeu;
	}


	public VECTOR_UB_FIELDS getvUB_Vector() {
		return vUB_Vector;
	}


	public void setvUB_Vector(VECTOR_UB_FIELDS vUB_Vector) {
		this.vUB_Vector = vUB_Vector;
	}


	public RECLIST_FIBU get_RecListFibu()
	{
		return this.recListFibu;
	}

	public ArrayList<String> getSelectedEmails(){
		return c_mailSelectionList.getSelectedKontakt();
	}

	public String getSelectedFaxNummer(){
		String faxN = c_faxSelectionList.getFaxNummer();
		if(faxN.contains(" (")){
			faxN = faxN.substring(0, faxN.indexOf(" ("));
		}
		return faxN;
	}

	public String getMahnungID() {
		return mahnungID;
	}

	public boolean get_bPrintMahnungWithRechnungsArchivAnhang()
	{
		return this.oCB_AddArchivRechnungen.isSelected();
	}


	public MyE2_MessageVector getoMV_Rueck() {
		return oMV_Rueck;
	}


	public void setoMV_Rueck(MyE2_MessageVector oMV_Rueck) {
		this.oMV_Rueck = oMV_Rueck;
	}


	public MyE2_MessageVector checkFields() throws myException {
		this.oMV_Rueck = new MyE2_MessageVector();
		this.oMV_Rueck.addAll(getvUB_Vector().get_MV_AllFieldsAreOK_ShowErrorInput());
		if(this.c_mailSelectionList.getSelectedKontakt().size()>5){
			g_contactGrid.show_InputStatus(false);
			oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es können maximal 5 eMail-Adressen ausgewählt werden !")));
		}
		return oMV_Rueck;
	}
}
