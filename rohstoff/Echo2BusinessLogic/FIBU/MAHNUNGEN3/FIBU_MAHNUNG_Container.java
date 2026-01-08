package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.LayoutDataFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.UserSettings.E2_UserSetting_HashMap;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_Const.processtype;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL.SE_ListWithSelector_Component;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal_NG;
import panter.gmbh.Echo2.components.E2_Grid;
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
import panter.gmbh.basics4project.DB_ENUMS.MAHNUNG_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU_SACHBEARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MAHNUNG_POS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_SACHBEARBEITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAHNUNG_POS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIBU.RECORD_FIBU_ext;

public class FIBU_MAHNUNG_Container extends E2_BasicModuleContainer 
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

	private UB_TextArea_WithSelectorEasy        ubEinleitung = 				new UB_TextArea_WithSelectorEasy("MAHNTEXT_EINLEITUNG", false, "","MAHNUNG_EINLEITUNGSTEXT",800,4);
	private UB_TextArea_WithSelectorEasy        ubAbschluss = 				new UB_TextArea_WithSelectorEasy("MAHNTEXT_AUSLEITUNG", false, "","MAHNUNG_ABSCHLUSSTEXT",800,4);

	private UB_TextField_With_DatePOPUP_OWN     ubDatumMahnung = 			new UB_TextField_With_DatePOPUP_OWN("DATUM_MAHNUNG", false,true, myDateHelper.get_cCalendarActual(0),100);
	private UB_TextField_With_DatePOPUP_OWN     ubDatumZahlungenGebucht = 	new UB_TextField_With_DatePOPUP_OWN("DATUM_ZAHLUNGEN_GEBUCHT", false, true, myDateHelper.get_cCalendarActual(-1),100);

	private MyE2_Label                			ubSachbearbeiter1 =         new MyE2_Label();
	private MyE2_Label                			ubSachbearbeiter2 =         new MyE2_Label();
	private MyE2_Label                			ubSachbearbeiter3 =         new MyE2_Label();

	private VECTOR_UB_FIELDS                    vUB_Vector = new VECTOR_UB_FIELDS();

	private MyE2_Button                         btMahnungSpeichern 			= new MyE2_Button(new MyE2_String("Speichern"));
	private FIBU_MAHNUNG_Print_Mail_Save_Button   	btMahnungSpeichernDrucken 	= null;
	private FIBU_MAHNUNG_Print_Mail_Save_Button		btMahnungSpeichernMail		= null;
	private MyE2_Button                         btMahnungLoeschen 			= new MyE2_Button(new MyE2_String("Löschen"));

	private E2_NavigationList                   oNaviList = null;

	private ContactComponents					g_contactGrid = null;
	private SE_ListWithSelector_Component		c_mailSelectionList = null;
	private SE_ListWithSelector_Component		c_faxSelectionList = null;

	private FIBU_MAHNUNG_Beleg_ListComponent		c_belegSelectionList = null;

	//2012-02-13:  weitere, gespeicherte checkbox, die definiert, ob die pdf-datei archiv-rechnungen mitanhaenge soll
	private MyE2_CheckBox  						oCB_AddArchivRechnungen = 	new MyE2_CheckBox(new MyE2_String("Rechnungsarchiv anhängen"),new MyE2_String("Auswählen, wenn die archivierten Rechnungen an die Mahnung angehängt werden soll ..."));

	/*
	 * hashkeys der zu speichernden Werte (benutzerspezifischer Speicher)
	 */
	private Vector<String>		  				vValuesToSave = new Vector<String>();

	private boolean 							overwriteMahnung = false;
	private Vector<String>						idMahnungPos_to_overwrite = new Vector<>();
	
	private String mahnungID;


	//	private String smallestFibuId;

	private ownActionCloseWindow closeAction;


	private MyE2_MessageVector oMV_Rueck  = new MyE2_MessageVector();


	//neu erfassung
	@SuppressWarnings("unchecked")
	public FIBU_MAHNUNG_Container(RECLIST_FIBU rec_ListFibuMahnbar,int MahnstufeVorhanden, E2_NavigationList NaviList,Vector<String> first_beleg_liste)  throws myException 
	{
		super();
		this.recListFibu = 	rec_ListFibuMahnbar;
		this.iMahnstufe = 	MahnstufeVorhanden;
		this.bNeu = true;
		this.oNaviList = NaviList;

		this.btMahnungSpeichernMail 	= new FIBU_MAHNUNG_Print_Mail_Save_Button(this, processtype.MAIL);
		this.btMahnungSpeichernDrucken 	= new FIBU_MAHNUNG_Print_Mail_Save_Button(this, processtype.PRINT);

		this.c_mailSelectionList = new SE_ListWithSelector_Component(NaviList, btMahnungSpeichernDrucken, btMahnungSpeichernMail, null, processtype.MAIL);
		this.c_mailSelectionList.preloadContactList(NaviList.get_vSelectedIDs_Unformated(), MAILDEF.EMAIL_MAHNUNG);
		this.c_mailSelectionList.setOnly_five_contact(false);
		this.c_mailSelectionList.setWidth(new Extent(350));

		this.c_faxSelectionList = new SE_ListWithSelector_Component(NaviList,  null, btMahnungSpeichernMail,  null, processtype.PRINT);
		this.c_faxSelectionList.setWidth(new Extent(350));

		this.g_contactGrid = new ContactComponents(c_faxSelectionList, c_mailSelectionList);
	
		this.c_belegSelectionList = new FIBU_MAHNUNG_Beleg_ListComponent(first_beleg_liste, true);

		this.checkMahnstuffe();

		this.build_Maske();

		//den sachbearbeiter voreinstellen

		this.define_values_to_save();

		HashMap<String, String> hmGespeicherteWerte = (HashMap<String, String>)new E2_UserSetting_HashMap(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).get_Settings(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS);

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


	private void checkMahnstuffe() throws myException {
		for(String id : this.oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only()){
			if(new RECORD_FIBU(id).get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu().size()>0){
				for(RECORD_MAHNUNG_POS mahnung: new RECORD_FIBU(id).get_DOWN_RECORD_LIST_MAHNUNG_POS_id_fibu()){
					if(mahnung.get_UP_RECORD_MAHNUNG_id_mahnung().get_MAHNSTUFE_bdValue(new BigDecimal(0.0)).intValue()==iMahnstufe){
						bibMSG.add_MESSAGE(new MyE2_Warning_Message("Eine Beleg in der Auswahl wurde bereits mit dieser Mahnstufe gemahnt: "+ iMahnstufe));
						overwriteMahnung = true;
						idMahnungPos_to_overwrite.add(mahnung.get_ID_MAHNUNG_POS_cUF_NN(""));
					}
				}
			}
		}
	}


	//bearbeiten einer vorhandenen mahnung
	@SuppressWarnings("unchecked")
	public FIBU_MAHNUNG_Container(RECORD_MAHNUNG  rec_Mahnung, E2_NavigationList NaviList) throws myException 
	{
		super();
		this.mahnungID =rec_Mahnung.get_ID_MAHNUNG_cUF();
		this.recMahnung = new RECORD_MAHNUNG(getMahnungID());
		this.oNaviList = NaviList;

		this.btMahnungSpeichernMail 	= new FIBU_MAHNUNG_Print_Mail_Save_Button(this, processtype.MAIL);
		this.btMahnungSpeichernDrucken 	= new FIBU_MAHNUNG_Print_Mail_Save_Button(this, processtype.PRINT);


		this.c_mailSelectionList = new SE_ListWithSelector_Component(NaviList, btMahnungSpeichernDrucken, btMahnungSpeichernMail, null, processtype.MAIL);
		this.c_mailSelectionList.preloadContactList(NaviList.get_vSelectedIDs_Unformated(), MAILDEF.EMAIL_MAHNUNG);
		this.c_mailSelectionList.setOnly_five_contact(false);
		this.c_mailSelectionList.setFixedSize(350,100);

		this.c_faxSelectionList = new SE_ListWithSelector_Component(NaviList,  null, btMahnungSpeichernMail,  null, processtype.PRINT);
		this.c_faxSelectionList.setWidth(new Extent(350));
		this.g_contactGrid = new ContactComponents(c_faxSelectionList, c_mailSelectionList);

		this.recListFibu = 	new RECLIST_FIBU();

		//		this.c_belegSelectionList = new FIBU_MAHNUNG_Beleg_ListComponent(rec_Mahnung, true, true);

		if(this.recMahnung.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_mahnung().size()>0){
			for (int i=0;i<this.recMahnung.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_mahnung().get_vKeyValues().size();i++)
			{
				this.recListFibu.ADD(this.recMahnung.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_mahnung().get(i).get_UP_RECORD_FIBU_id_fibu(),false);
			}
			this.c_belegSelectionList = new FIBU_MAHNUNG_Beleg_ListComponent(rec_Mahnung, true, true);
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

		HashMap<String, String> hmGespeicherteWerte = (HashMap<String, String>)new E2_UserSetting_HashMap(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).get_Settings(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS);

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

		MyE2_Grid  oGrid = new MyE2_Grid(5, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		GridLayoutData GL_Warnung = 			LayoutDataFactory.get_GridLayout(E2_INSETS.I(5,10,5,10), new E2_ColorAlarm(), new Alignment(Alignment.CENTER, Alignment.CENTER),oGrid.getSize());

		GridLayoutData GL_BeschriftungLeft = 	LayoutDataFactory.get_GridLayout(E2_INSETS.I(5,10,5,10), new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),1);
		GridLayoutData GL_OneLine = 			LayoutDataFactory.get_GridLayout(E2_INSETS.I(5,10,5,10), new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),oGrid.getSize()-1);


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

		oGrid.add_raw(new RB_lab(new MyE2_String("Mahnung -- Stufe: ",true,""+this.iMahnstufe,false))._b(), new RB_gld()._col(new E2_ColorDDDark())._left_top()._ins(5,5,5,5));
		RB_lab oLabelID = new RB_lab(new MyE2_String("<Neuerfassung>"))._f(new E2_FontPlain(-2));
		if (!this.bNeu)
		{
			oLabelID = new RB_lab(new MyE2_String("ID: ",true,this.recMahnung.get_ID_MAHNUNG_cF_NN(""),false))._f(new E2_FontPlain(-2));

			Vector<String> idFibu = null;

			if(this.recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().size()>0){
				idFibu = bibALL.get_Vector(this.recMahnung.get_DOWN_RECORD_LIST_FIBU_MAHNUNG_id_mahnung().get(0).get_ID_FIBU_cUF()); 	
			}else{
				idFibu = bibALL.get_Vector(this.recMahnung.get_DOWN_RECORD_LIST_MAHNUNG_POS_id_mahnung().get(0).get_ID_FIBU_cUF());
			}

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

		oGrid.add_raw(oLabelID, new RB_gld()._col(new E2_ColorDDDark())._left_mid()._ins(5,5,5,5)._span(4));

		//2012-01-18: pruefung auf keine_mahnung felder im rechungskopf oder firmenstamm
		if (bWarnungKeineMahnung)
		{
			oGrid.add(new MyE2_Label(new MyE2_String("Achtung! Der Mahnungsadressat oder einer der Belege trägt das Merkmal: <Keine Mahnung>"), new E2_FontBold(2)).get_InBorderGrid(null, null, E2_INSETS.I_5_5_5_5),GL_Warnung);
		}

		oGrid.add_raw(new RB_lab("Datum der Mahnung:"),  new RB_gld()._left_mid()._ins(5,10,5,5));
		oGrid.add_raw(this.ubDatumMahnung, new RB_gld()._left_mid()._ins(5,10,5,5));

		oGrid.add_raw(new RB_lab("Zahlungen erfasst bis:"),  new RB_gld()._right_mid()._ins(5,2,5,2));
		oGrid.add_raw(this.ubDatumZahlungenGebucht, new RB_gld()._left_mid()._span(2)._ins(5,2,5,2));

		int lab[] = {200,200,200};
		oGrid.add_raw(new RB_lab("Sachbearbeiter:"),  GL_BeschriftungLeft);
		oGrid.add_raw(new E2_ComponentGroupHorizontal_NG(this.ubSachbearbeiter1,this.ubSachbearbeiter2,this.ubSachbearbeiter3,  lab),GL_OneLine);

		oGrid.add_raw(new RB_lab("Kontakte:"), GL_BeschriftungLeft	); 
		oGrid.add_raw(g_contactGrid,GL_OneLine);

		oGrid.add_raw(new RB_lab("Einleitung:"),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubEinleitung,  GL_OneLine);
		c_belegSelectionList.show_InputStatus(true);

		oGrid.add_raw(new RB_lab("Liste:"),  GL_BeschriftungLeft);
		if(this.oNaviList.get_vSelectedIDs_Unformated_Select_the_one_and_only().size()>5){
			MyE2_ContainerEx belegSelectionContainer = new MyE2_ContainerEx(this.c_belegSelectionList);
			belegSelectionContainer.setHeight(new Extent(300));
			oGrid.add_raw(belegSelectionContainer, GL_OneLine);
		}else{
			oGrid.add_raw(this.c_belegSelectionList, GL_OneLine);
		}

		oGrid.add_raw(new RB_lab("Abschlusstext:"),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubAbschluss,  GL_OneLine);

		oGrid.add_raw(new RB_lab("Frist (Tage):"),  GL_BeschriftungLeft);
		oGrid.add_raw(this.ubFristInTagen,  new RB_gld()._left_mid()._ins(5,2,5,2));

		oGrid.add_raw(new RB_lab("Mahngebühr:"),  new RB_gld()._right_mid()._ins(5,2,5,2));
		oGrid.add_raw(new E2_ComponentGroupHorizontal_NG(this.ubMahngebuehrProz, new MyE2_Label(new MyE2_String("(%)")), this.ubMahngebuehrBetrag, new MyE2_Label(new MyE2_String("("+this.recListFibu.get(0).get_WAEHRUNG_FREMD_cUF_NN("??")+")")), GL_OneLineInGroup),
				new RB_gld()._left_mid()._ins(5,2,5,2)._span(2));

		E2_ComponentGroupHorizontal_NG a = new E2_ComponentGroupHorizontal_NG(
				this.btMahnungSpeichern,
				this.btMahnungSpeichernDrucken,
				this.btMahnungSpeichernMail,
				this.oCB_AddArchivRechnungen,
				this.btMahnungLoeschen, 
				LayoutDataFactory.get_GridLayout(new Insets(0,0,10,0), new E2_ColorBase(), new Alignment(Alignment.LEADING, Alignment.TOP),1));
		oGrid.add(new RB_lab(""));
		oGrid.add( a,4, 1, new Insets(5,20, 0, 0), new Alignment(Alignment.LEFT, Alignment.TOP));


		this.add(oGrid,E2_INSETS.I_5_5_5_5);

		this.btMahnungSpeichern.add_oActionAgent(new actionSave());
		this.btMahnungLoeschen.add_oActionAgent(new actionDeleteMahnung());

		if (this.bNeu)
		{
			this.btMahnungLoeschen.set_bEnabled_For_Edit(false);
		}

		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1200), new Extent(900), new MyE2_String(this.bNeu?"Mahnung erfassen":"Mahnung bearbeiten"));

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

	public MyE2_MessageVector test_save_mask() throws myException{
		this.oMV_Rueck = new MyE2_MessageVector();

		this.c_belegSelectionList.getSelectedId_geordnet();

		this.oMV_Rueck.add_MESSAGE(new MyE2_Alarm_Message("only a test"));

		return oMV_Rueck;
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

				if(this.c_belegSelectionList.getSelectedId_geordnet().size()==0){
					oMV_Check.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Belegauswahl ist leer.")));
					this.c_belegSelectionList.show_InputStatus(false);
				}

				if (oMV_Check.get_bIsOK())
				{
					Vector<String> vSQL = new Vector<String>();
					vSQL.add(recNew_MAHNUNG.get_InsertSQLStatementWith_Id_Field(false,true));

					Vector<FIBU_MAHNUNG_Beleg_Model> beleg_geordnet_id = this.c_belegSelectionList.getSelectedId_geordnet();

					if(overwriteMahnung){
						for(String id: idMahnungPos_to_overwrite){
							RECORD_MAHNUNG_POS rec = new RECORD_MAHNUNG_POS(id);
							vSQL.add(rec.get_DELETE_STATEMENT());
						}
					}
					
					for (int i=0;i<beleg_geordnet_id.size();i++)
					{
						RECORDNEW_MAHNUNG_POS  recNew = new RECORDNEW_MAHNUNG_POS();
						recNew.set_NEW_VALUE_ID_MAHNUNG(recNew_MAHNUNG.get_cLastSEQ_NUMBER());
						recNew.set_NEW_VALUE_ID_FIBU(beleg_geordnet_id.get(i).getFibuId());
						recNew.set_NEW_VALUE_TEXT(beleg_geordnet_id.get(i).getText());
						recNew.set_NEW_VALUE_MANHNUNG_POS(beleg_geordnet_id.get(i).getPosition());

						vSQL.add(recNew.get_InsertSQLStatementWith_Id_Field(false,true));
					}

					bibDB.ExecSQL(vSQL, true);

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

				if(c_belegSelectionList.getSelectedId_geordnet().size()==0){
					oMV_Check.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Die Belegauswahl ist leer.")));
					this.c_belegSelectionList.show_InputStatus(false);
				}

				if (oMV_Check.get_bIsOK())
				{
					
					Vector<String> sqlStatements = new Vector<>();
					
					sqlStatements.add(recMahnung.get_SQL_UPDATE_STATEMENT(null, true));

					for(FIBU_MAHNUNG_Beleg_Model infoToUpdate : c_belegSelectionList.getSelectedId_geordnet()){

						RECORD_MAHNUNG_POS recMahnungPos =  new RECORD_MAHNUNG_POS(
								MAHNUNG_POS.id_mahnung.fieldName()+"="+recMahnung.get_ID_MAHNUNG_cUF_NN("") +" AND "+ MAHNUNG_POS.id_fibu + "=" + infoToUpdate.getFibuId());

						recMahnungPos.set_NEW_VALUE_MANHNUNG_POS(infoToUpdate.getPosition().toString());
						recMahnungPos.set_NEW_VALUE_TEXT(infoToUpdate.getText());

						sqlStatements.add(recMahnungPos.get_SQL_UPDATE_STATEMENT(null,true));
					}

					for(String fibuIdToDelete : this.c_belegSelectionList.getUnselectedId()){
						RECORD_MAHNUNG_POS recPos = new RECORD_MAHNUNG_POS(MAHNUNG_POS.id_mahnung.fieldName()+"="+recMahnung.get_ID_MAHNUNG_cUF_NN("") +" AND "+ MAHNUNG_POS.id_fibu + "=" + fibuIdToDelete);
						sqlStatements.add(recPos.get_DELETE_STATEMENT());
					}
					oMV_Rueck.add_MESSAGE(bibDB.ExecMultiSQLVector(sqlStatements, true));
					
				}
				else
				{
					oMV_Rueck.add_MESSAGE(oMV_Check);
				}
			}

			//jetzt die spezifischen werte speichern


			//jetzt die zu speichernden werte festlegen
			@SuppressWarnings("unchecked")
			HashMap<String, String> hmGespeicherteWerte = (HashMap<String, String>)new E2_UserSetting_HashMap(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).get_Settings(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS);
			if (hmGespeicherteWerte==null)
			{
				//leere huelle vorbereiten
				hmGespeicherteWerte = new HashMap<String, String>();
				for (int i=0;i<this.vValuesToSave.size();i++)
				{
					hmGespeicherteWerte.put(this.vValuesToSave.get(i), "");
				}
				new E2_UserSetting_HashMap(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).STORE(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, hmGespeicherteWerte);
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

			new E2_UserSetting_HashMap(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, this.vValuesToSave).STORE(FIBU_MAHNUNG_Container.SESSION_KEY_FOR_STORING_SETTINGS, hmGespeicherteWerte);

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
			FIBU_MAHNUNG_Container oThis = FIBU_MAHNUNG_Container.this;

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
			FIBU_MAHNUNG_Container  oThis = FIBU_MAHNUNG_Container.this;
			if (oThis.bNeu==false)
			{
				//zuerst pruefen, ob die mahnstufe dieser mahnung bei allen beteiligten fibu-saetzen die neueste ist 
				for (int i=0;i<oThis.recListFibu.get_vKeyValues().size();i++)
				{
					RECORD_FIBU_ext recFibu = new RECORD_FIBU_ext(oThis.recListFibu.get(i));

					if (recFibu.get_MAXIMALE_POS_MAHNSTUFE()!=oThis.iMahnstufe)
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
						bibMSG.add_MESSAGE(new RECORD_MAHNUNG(FIBU_MAHNUNG_Container.this.recMahnung.get_ID_MAHNUNG_cUF()).DELETE());

						if (bibMSG.get_bIsOK())
						{
							ownYesNoMessage.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
							FIBU_MAHNUNG_Container.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
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
			FIBU_MAHNUNG_Container oThis = FIBU_MAHNUNG_Container.this;
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

	private class ContactComponents extends E2_Grid{

		private MyE2_Grid fax_grid, mail_grid = null;

		public ContactComponents(MyE2_Grid c_faxGrid, MyE2_Grid c_mailGrid) {
			super();
			this.mail_grid = c_mailGrid;
			this.fax_grid = c_faxGrid;

			int[] breite = {400,400};
			this
			._gld(new RB_gld()._ins(5, 2, 5, 2))
			._bo_dd()
			._a_lm(new RB_lab("Fax Nummer")._b())
			._a_lm(new RB_lab("Email")._b())
			._a_lt(fax_grid)
			._a_lt(mail_grid)
			._setSize(breite);
			

			
//			this.setRowHeight(1, new Extent(100));
//			this.set_Spalten(breite);
//			this.setStyle(MyE2_Grid.STYLE_GRID_DDARK_BORDER());
//			this.setWidth(new Extent(400));
//			this.add(new MyE2_Label(new MyE2_String("Fax Nummer"),new E2_FontBold()), new RB_gld()._ins(2, 0, 2, 0));
//			this.add(new MyE2_Label(new MyE2_String("Email"),new E2_FontBold()), new RB_gld()._ins(2, 0, 2, 0));
//			this.add(fax_grid, new RB_gld()._ins(2, 0, 2, 0));
//			this.add(mail_grid, new RB_gld()._ins(2, 0, 2, 0));
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

	public Vector<String> getSelectedBeleg(){
		Vector<String> geordnetBelegVector =  new Vector<>();
		for (FIBU_MAHNUNG_Beleg_Model beleg: c_belegSelectionList.getSelectedId_geordnet()){
			geordnetBelegVector.add(beleg.getFibuId());
		}
		return geordnetBelegVector;
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
		if(this.c_belegSelectionList.getSelectedId_geordnet().size()==0){
			c_belegSelectionList.show_InputStatus(false);
			oMV_Rueck.add(new MyE2_Alarm_Message(new MyE2_String("Sie haben alle Belege deaktiviert !")));
		}
		return oMV_Rueck;
	}
}
