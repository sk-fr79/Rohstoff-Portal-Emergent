/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_FormatCheckBox;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_SelectAgentForCheckboxesVisible;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ABZUG_EK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ABZUG_VK;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BAMF_MASK_ComponentMAP extends E2_ComponentMAP {
	private String  			idVPOS_TPA_FUHRE = null;
	private String  			idVPOS_TPA_FUHRE_ORT = null;

	
	public BAMF_MASK_ComponentMAP(	BAMF_SQLFieldMAP			sqlfieldMAP, 
									BAMF_MASK_ModulContainer 	oMaskContainer, 
									String 						cModulKenner) throws myException	{
		super(sqlfieldMAP);
		
		this.idVPOS_TPA_FUHRE=		sqlfieldMAP.getIdVPOS_TPA_FUHRE();
		this.idVPOS_TPA_FUHRE_ORT=	sqlfieldMAP.getIdVPOS_TPA_FUHRE_ORT();

		
		
		String cTO = bibALL.get_TABLEOWNER();
		String cQueryComboBox1 = "SELECT BAM_FESTSTELLUNG FROM "+cTO+".JT_FBAM_FESTSTELLUNG ORDER BY BAM_FESTSTELLUNG";
		String cQueryComboBox2 = "SELECT BAM_BETREFF FROM "+cTO+".JT_FBAM_BETREFF ORDER BY BAM_BETREFF";
		String cQueryComboBox3 = "SELECT BAM_GRUND FROM "+cTO+".JT_FBAM_GRUND ORDER BY BAM_GRUND";
		//String cQueryComboBox4 = "SELECT   NVL(VORNAME,'-')||' '||  NVL(NAME1,'-') FROM "+cTO+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT() +"AND NOT VORNAME IS NULL ORDER BY VORNAME";
		
		//2012-10-10: sortierung den anderen Mitarbeiter-sortierungen angepasst
		String cQueryComboBox4 = "SELECT NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')' AS ANZEIGE,  NVL(VORNAME,'-')||' '||  NVL(NAME1,'-') AS WERT FROM "+cTO+".JD_USER WHERE ID_MANDANT="+bibALL.get_ID_MANDANT() +"AND NOT VORNAME IS NULL ORDER BY 1";
//		"NVL(NAME1,NVL(NAME,'-'))||', '||NVL(VORNAME,'')||' ('|| NVL(KUERZEL,'-')||')'"
		
		/*
		 * textarea mit popup: weigermeldung uebernahme-vorschlag/Behebungs-vorschlag/Behebungsvermerk
		 */
		MyE2_DB_TextArea_WithSelektorEASY oTF_UebernahmeVorschlag = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("WM_UEBERNAHMEVORSCHLAG"),"WM_UEBERNAHME_VORSCHLAG");
		oTF_UebernahmeVorschlag.get_oTextArea().set_iWidthPixel(650);
		oTF_UebernahmeVorschlag.get_oTextArea().set_iRows(8);

		MyE2_DB_TextArea_WithSelektorEASY oTF_BehebungVorschlag = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("BEHEB_VORSCHLAG"),"BAM_BEHEB_VORSCHLAG");
		oTF_BehebungVorschlag.get_oTextArea().set_iWidthPixel(650);
		oTF_BehebungVorschlag.get_oTextArea().set_iRows(8);

		MyE2_DB_TextArea_WithSelektorEASY oTF_BehebungVermerk = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("BEHEB_VERMERK"),"BAM_BEHEB_VERMERK");
		oTF_BehebungVermerk.get_oTextArea().set_iWidthPixel(650);
		oTF_BehebungVermerk.get_oTextArea().set_iRows(8);
		
		MyE2_DB_TextArea_WithSelektorEASY oTF_Auswirkungen = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("AUSWIRKUNGEN"),"BAM_AUSWIRKUNGEN");
		oTF_Auswirkungen.get_oTextArea().set_iWidthPixel(650);
		oTF_Auswirkungen.get_oTextArea().set_iRows(6);

		//2014-10-13: fehlerinfo extern fuer weigermeldung
		MyE2_DB_TextArea_WithSelektorEASY oTF_Fehler4Extern = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get(_DB.FBAM$FEHLERINFO_EXTERN),"BAM_FEHLERINFO_EXTERN");
		oTF_Fehler4Extern.get_oTextArea().set_iWidthPixel(650);
		oTF_Fehler4Extern.get_oTextArea().set_iRows(6);

		
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("ID_FBAM"), false, false, 200, false),new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("BAM_GRUND"),cQueryComboBox3, false),new MyE2_String("Grund der Beanstandung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("BAM_DATUM"), false, false, 0, false),new MyE2_String("Datum der Beanstandung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("BAM_ORT"), false, false, 0, false),new MyE2_String("Ort der Beanstandung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("FEHLERURSACHE"), false, false, 650, false),new MyE2_String("Fehlerursache"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("FEHLERBESCHREIBUNG"), false, false, 0, false),new MyE2_String("Fehlerbeschreibung"));
		this.add_Component(new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("FESTSTELLUNG_BAM"),cQueryComboBox1, false),new MyE2_String("Fehler festgestellt bei"));

		this.add_Component(oTF_Auswirkungen,new MyE2_String("Auswirkungen"));
		this.add_Component(oTF_BehebungVorschlag,new MyE2_String("Behebungsvorschlag"));
		this.add_Component(oTF_BehebungVermerk,new MyE2_String("Behebungsvermerk"));
		this.add_Component(oTF_Fehler4Extern,new MyE2_String("Fehlerbeschreibung / extern"));

		this.add_Component( new  DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_AUSSTELLUNG")),new MyE2_String("Ausgestellt von"));
		this.add_Component( new  DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_BEHEBUNG")),new MyE2_String("Behoben von"));
		this.add_Component( new  DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_KONTROLLE")),new MyE2_String("Kontrolliert von"));
		
		this.add_Component(new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("BETREFF_BAM"),cQueryComboBox2, false),new MyE2_String("Fehler betrifft: "));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("DATUM_AUSSTELLUNG"), false, false, 0, false),new MyE2_String("Ausstellungsdatum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("DATUM_BEHEBUNG"), false, false, 0, false),new MyE2_String("Behebungsdatum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("DATUM_KONTROLLE"), false, false, 0, false),new MyE2_String("Kontrolldatum"));

		this.add_Component(oTF_UebernahmeVorschlag,new MyE2_String("Übernahmevorschlag"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ORT"), false, false, 0, false),new MyE2_String("Ort"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_DATUM"), false, false, 0, false),new MyE2_String("Datum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_UHRZEIT"), false, false, 0, false),new MyE2_String("Uhrzeit"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ANLIEFERDATUM"), false, false, 0, false),new MyE2_String("Anlieferdatum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ABHOLDATUM"), false, false, 0, false),new MyE2_String("Abholdatum"));

		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_LETZTERDRUCK_DATUM"), false, false, 0, false),new MyE2_String("Letzter Druck Datum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_LETZTERDRUCK_UHRZEIT"), false, false, 0, false),new MyE2_String("Letzter Druck Uhrzeit"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_GESPERRT"), false, false, 0, false),new MyE2_String("Weigermeldung gesperrt"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ZAEHLER_ENTSPERRUNG"), false, false, 0, false),new MyE2_String("Anzahl Entsperrungen Weigermeldung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ARTBEZ1"), false, false, 0, false),new MyE2_String("Weigermeldung gesperrt"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_MENGE_ABLADEN"), false, false, 0, false),new MyE2_String("Anzahl Entsperrungen Weigermeldung"));
		
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("ABGESCHLOSSEN_BEHEBUNG"), false, false, 0, false),new MyE2_String("Behebung abgeschlossen"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("ABGESCHLOSSEN_KONTROLLE"), false, false, 0, false),new MyE2_String("Kontrolle durchgeführt"));

		MyE2_DB_ComboBoxErsatz oComboAnsprechpartnerIntern =	new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("WM_ANSPRECHPARTNER_INHOUSE"),cQueryComboBox4, false);
		oComboAnsprechpartnerIntern.set_WidthAndHeightOfDropDown(new Extent(350), new Extent(300), -375, 350);
		
		this.add_Component(oComboAnsprechpartnerIntern,new MyE2_String("Unser Ansprechpartner"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ANSPRECHPARTNER_LIEFERANT"), false, false, 0, false),new MyE2_String("Ansprechpartner Lieferant"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_ABNEHMER"), false, false, 0, false),new MyE2_String("Abnehmer"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("WM_FAXNUMMER"), false, false, 0, false),new MyE2_String("Faxnummer"));

		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("BUCHUNGSNUMMER"), false, false, 120, false),new MyE2_String("Buchungsnummer"));

		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE1,new BAMF_MaskInfoGrid(),new MyE2_String("Infozeile"));
		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE2,new BAMF_MaskInfoGrid(),new MyE2_String("Infozeile"));
		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE3,new BAMF_MaskInfoGrid(),new MyE2_String("Infozeile"));
		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE4,new BAMF_MaskInfoGrid(),new MyE2_String("Infozeile"));
		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE5,new BAMF_MaskInfoGrid(),new MyE2_String("Infozeile"));
		
		//2019-12-27: neue felder bearbeitet von verantwortlichem
		this.add_Component(new  DB_Component_USER_DROPDOWN(sqlfieldMAP.get(FBAM.id_user_bearbeitung.fn())),new MyE2_String("Bearbeitet von Verantwortl."));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get(FBAM.bearbeitung_datum.fn()), false, false, 0, false),new MyE2_String("Datum (Bearb. von Verantw.)"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get(FBAM.bearbeitung.fn()), false, false, 0, false),new MyE2_String("Bearbeitet von Verantwortl."));
		MyE2_DB_CheckBox checkBearbeitet = (MyE2_DB_CheckBox) this.get__Comp(FBAM.bearbeitung);
		checkBearbeitet._aaa(new BAMF_MASK_CheckBox_BEARBEITUNG_VERANTWORTL_ActionAgent(this));

		
		
		/*
		 * kreuztabelle fuer die benutzerzuordnung
		 */
		Vector<String> vWhere = new Vector<String>();
		vWhere.add("JD_USER.ID_MANDANT="+bibALL.get_ID_MANDANT());
		MyE2_DBC_CrossConnection oCC_BamUser = new MyE2_DBC_CrossConnection( (SQLFieldForPrimaryKey)sqlfieldMAP.get("ID_FBAM"),
																				"JT_FBAM_USER",
																				"JD_USER",
																				"ID_FBAM_USER",
																				"ID_FBAM",
																				"ID_USER",
																				"ID_USER",
//																				"  NVL(NAME,'-')||' ('||  NVL(VORNAME,'-')||')'",
																				"  NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') || ' ('||  NVL(KUERZEL,'?')||')'",
																				" NVL(JD_USER.AKTIV,'N') ", 
																				null,
																				vWhere,
//																				null,
																				" NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') ",
																				5,
																				new E2_FontPlain(), new MyE2_String("Bitte mindestens einen Verteiler ankreuzen !"), MyE2_DBC_CrossConnection.CROSSTYP_MUST_ONE);
		
		oCC_BamUser.add_AddOnComponentsInFront(new ownCheckBoxToggleInaktivUsers(oCC_BamUser));

		//2013-04-09: formatierer fuer die angeklickten felder
		oCC_BamUser.set_oFormatCheckBoxes(new ownFormatterCheckbox());
		oCC_BamUser.add_ActionAgentToCheckboxes(new ownAction4CheckboxesFormatter(oCC_BamUser));
		
		this.add_Component(BAMF__CONST.FIELDNAME_CROSSREFERENCE_BAM_USER,oCC_BamUser,new MyE2_String("Zuordnung zu Benutzern"));
		// ------------------------------------------
		
		/*
		 * tochtertabelle fuer das druckprotokoll
		 */
		BAMF_Daughter_PrintProtokoll oDaughterPrintProt = new BAMF_Daughter_PrintProtokoll(sqlfieldMAP,this);
		this.add_Component(BAMF__CONST.FIELDNAME_DAUGHTERTABLE_PRINTPROTOKOLL,oDaughterPrintProt,new MyE2_String("Druckprotokoll"));
		
		// ------------------------------------------

		
		/*
		 * button zum senden der info-mails
		 */
		MyE2_Button oButtonInfoMail = new MyE2_Button(E2_ResourceIcon.get_RI("sendmail.png"),E2_ResourceIcon.get_RI("leer.png"));
		oButtonInfoMail.add_GlobalValidator(new E2_ButtonAUTHValidator(cModulKenner,"SENDE_INFO_MAIL"));
		oButtonInfoMail.add_oActionAgent(new BAMF_MASK_SendMailToUsers(oMaskContainer));
		oButtonInfoMail.setToolTipText(new MyE2_String("Infomail an betroffene Teilnehmer ...").CTrans());
		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_SENDE_MAILS_TO_VERTEILER,oButtonInfoMail , new MyE2_String("Infomails senden"));
		
		/*
		 * button zum aufbau der Fehlerbeschreibung
		 */
		MyE2_Button oButtonFehlerBeschreibung = new MyE2_Button(E2_ResourceIcon.get_RI("wizard.png"),E2_ResourceIcon.get_RI("leer.png"));
		oButtonFehlerBeschreibung.add_oActionAgent(new actionFillFehlerBeschreibung());
		oButtonFehlerBeschreibung.setToolTipText(new MyE2_String("Text aus den Abzugslisten aufbauen ...").CTrans());
		this.add_Component(BAMF__CONST.FIELDNAME_BUTTON_COMPOSE_FEHLERBESCHREIBUNG,oButtonFehlerBeschreibung , new MyE2_String("Fehlerbeschreibung bauen"));

		
		
		/*
		 * button zum resetten des weigermeldungszaehlers
		 */
		this.add_Component(BAMF__CONST.FIELDNAME_FBAM_RESET_WEIGERMELDUNGSZAEHLER,new BT_MASK_RESET_PRINTCOUNTER(oMaskContainer) , new MyE2_String("Reset Weigermeldungszähler"));
		
		
		/*
		 * jetzt ein paar spezielle einstellungen der Maske
		 */
		/*
		 * die checkboxen der Felder ABGESCHLOSSEN_BEHEBUNG
		 * die checkboxen der Felder ABGESCHLOSSEN_KONTROLLE
		 */
		MyE2_DB_CheckBox oCheckBEHEBUNG =  (MyE2_DB_CheckBox)this.get("ABGESCHLOSSEN_BEHEBUNG");
		MyE2_DB_CheckBox oCheckKONTROLLE =  (MyE2_DB_CheckBox)this.get("ABGESCHLOSSEN_KONTROLLE");
		oCheckBEHEBUNG.add_oActionAgent(new BAMF_MASK_CheckBox_BEHEBUNG(this));
		oCheckKONTROLLE.add_oActionAgent(new BAMF_MASK_CheckBox_KONTROLLE(this));
		
		MyE2_DB_ComboBoxErsatz oCB_FestellungBAM = (MyE2_DB_ComboBoxErsatz)this.get("FESTSTELLUNG_BAM");
		MyE2_DB_ComboBoxErsatz oCB_BetreffBAM = (MyE2_DB_ComboBoxErsatz)this.get("BETREFF_BAM");
		MyE2_DB_ComboBoxErsatz oCB_GrundBAM = (MyE2_DB_ComboBoxErsatz)this.get("BAM_GRUND");
		oCB_FestellungBAM.getTextField().setWidth(new Extent(300));
		oCB_BetreffBAM.getTextField().setWidth(new Extent(300));
		oCB_GrundBAM.getTextField().setWidth(new Extent(300));
		
		((MyE2_DB_TextArea) this.get(_DB.FBAM$FEHLERBESCHREIBUNG)).set_iWidthPixel(650);
		((MyE2_DB_TextArea) this.get(_DB.FBAM$FEHLERBESCHREIBUNG)).set_iRows(7);
		((MyE2_DB_TextArea) this.get(_DB.FBAM$FEHLERBESCHREIBUNG)).setFont(new E2_FontPlain(-2));
		
		((MyE2_DB_TextField) this.get(_DB.FBAM$FEHLERURSACHE)).set_iWidthPixel(650);
		
		((MyE2_DB_TextField) this.get(_DB.FBAM$ID_FBAM)).set_iWidthPixel(195);
		((MyE2_DB_TextField) this.get(_DB.FBAM$BAM_ORT)).set_iWidthPixel(545);
	
		((MyE2IF__Component) this.get("BUCHUNGSNUMMER")).EXT().set_bDisabledFromBasic(true);
		((MyE2IF__Component) this.get("WM_ZAEHLER_ENTSPERRUNG")).EXT().set_bDisabledFromBasic(true);
		((MyE2IF__Component) this.get("WM_LETZTERDRUCK_DATUM")).EXT().set_bDisabledFromBasic(true);
		((MyE2IF__Component) this.get("WM_LETZTERDRUCK_UHRZEIT")).EXT().set_bDisabledFromBasic(true);
		((MyE2IF__Component) this.get("WM_GESPERRT")).EXT().set_bDisabledFromBasic(true);
		
		this.set_oMAPSettingAgent(new MAPSETTER_BAMF_MASK());
		
		this.set_oSubQueryAgent(new ownFormatingAgent_4_AnzeigeFuhrenInfos());
		/*
		 * dummy-statement einbauen, damit der LAGER-Daemon anspringt
		 */
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new builder_AddOnSQL_STATEMENTS()
		{

			@Override
			public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(	E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,	MyE2_MessageVector omv) throws myException
			{
				Vector<String> vRueck = new Vector<String>();
				vRueck.addAll(get_vAddons(componentMAP.get_oSQLFieldMAP()));
				
				//auch nocht gleich eine buchungsnummer vergeben
				String cID_BAM = ""+inputMAP.get_LActualValue("ID_FBAM", true, true, new Long(0)).longValue();
				if (cID_BAM.trim().equals("0"))
				{
					throw new myException("Error finding the current ID_FBAM - Value !");
				}
												
				vRueck.add("UPDATE "+bibE2.cTO()+".JT_FBAM SET BUCHUNGSNUMMER = "+
														"TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_FUHREN_BAM_NUMMER.NEXTVAL) "+
														" WHERE ID_FBAM="+cID_BAM+" AND BUCHUNGSNUMMER IS NULL");

				
				
				return vRueck;
			}

			@Override
			public Vector<String> get_vSQL_ADDON_UPDATE_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,MyE2_MessageVector omv) throws myException
			{
				return get_vAddons(componentMAP.get_oSQLFieldMAP());
			}
			
			
			/*
			 * liefert dummy, damit die fuhre in den daemonen beruecksichtigt wird
			 */
			private Vector<String> get_vAddons(SQLFieldMAP oSQLFieldMAP) throws myException
			{
				Vector<String>  vRueck = new Vector<String>();
				
				
				BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW oRecFuhreAusView = BAMF_MASK_ComponentMAP.this.get_recFuhreAusView();
				
				if (S.isFull(oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_cUF_NN("")))
				{
					vRueck.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET L_NAME1=L_NAME1 WHERE ID_VPOS_TPA_FUHRE="+oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_cUF());
				}
				if (S.isFull(oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("")))
				{
					vRueck.add("UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_ORT SET NAME1=NAME1 WHERE ID_VPOS_TPA_FUHRE_ORT="+oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(""));
				}

				return vRueck;
			}
			
		});
		
	}

	
	
	//checkboxen fuer ein/ausblenden der inaktiven benutzer
	private class ownCheckBoxToggleInaktivUsers extends MyE2_CheckBox
	{
		MyE2_DBC_CrossConnection oCCTeilnehmer = null;
		
		public ownCheckBoxToggleInaktivUsers(MyE2_DBC_CrossConnection CCTeilnehmer) throws myException
		{
			super(new MyE2_String("Inaktive"));
			this.oCCTeilnehmer = CCTeilnehmer;
			this.add_oActionAgent(new ownActionToogleInaktivUsers());
			GridLayoutData oGL = new GridLayoutData();
			oGL.setBackground(new E2_ColorMaskHighlight());
			this.oCCTeilnehmer.set_oAgentToSelektAnzeige(new SelectAgent(this));
			this.setLayoutData(oGL);
		}

		//schalter fuer das ein/ausschalten von inaktiven benutzern
		private class ownActionToogleInaktivUsers extends XX_ActionAgent
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				ownCheckBoxToggleInaktivUsers.this.oCCTeilnehmer.build_selectorGrid();
			}
		}
		
		private class SelectAgent extends XX_SelectAgentForCheckboxesVisible
		{
			private ownCheckBoxToggleInaktivUsers cbToggleUsers = null;
			
			public SelectAgent(ownCheckBoxToggleInaktivUsers cbToggleUsers)
			{
				super();
				this.cbToggleUsers = cbToggleUsers;
			}

			@Override
			public boolean get_Visible(MyE2_CheckBox ocb)
			{
				//inaktive hier auch markieren
				ocb.setFont(new E2_Font(Font.PLAIN,-2));
				if (ocb.EXT().get_C_MERKMAL2().equals("N"))
				{
					ocb.setFont(new E2_Font(Font.LINE_THROUGH,-2));
				}
				
				if (this.cbToggleUsers.isSelected())
				{
					return true;
				}
				return ocb.EXT().get_C_MERKMAL2().equals("Y");
			}

			@Override
			public Object get_Copy(Object objHelp) throws myExceptionCopy
			{
				return null;
			}
		
		}


	} 

	
	
	private class actionFillFehlerBeschreibung extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			BAMF_MASK_ComponentMAP oThis = BAMF_MASK_ComponentMAP.this;
			

			BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW recFuhreAusView = oThis.get_recFuhreAusView();
			

			String cText = "";
			
			if (S.isFull(recFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("")))
			{
				RECORD_VPOS_TPA_FUHRE_ORT 			recFuhreORT = new RECORD_VPOS_TPA_FUHRE_ORT(recFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(""));
				RECLIST_VPOS_TPA_FUHRE_ORT_ABZUG  	recListAbzug = recFuhreORT.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_ABZUG_id_vpos_tpa_fuhre_ort();

				for (int i=0;i<recListAbzug.size();i++)
				{
					if (i==0)
					{
						if (recFuhreORT.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
						{
							cText += "Abzüge Lieferant:\n";
						}
						else
						{
							cText += "Abzüge Abnehmer:\n";
						}
					}
					
					cText += ("\n"+recListAbzug.get(i).get_ABZUG_BELEGTEXT_cF_NN("")+ "--->" +recListAbzug.get(i).get_KURZTEXT_cF_NN(""));
					if (S.isFull(recListAbzug.get(i).get_LANGTEXT_cF_NN("")))
					{
						cText+=("\n"+recListAbzug.get(i).get_LANGTEXT_cF_NN(""));
					}
				}
				
			}
			else if (S.isFull(recFuhreAusView.get_ID_VPOS_TPA_FUHRE_cUF_NN("")))
			{
				RECORD_VPOS_TPA_FUHRE 	recFuhre = new RECORD_VPOS_TPA_FUHRE(recFuhreAusView.get_ID_VPOS_TPA_FUHRE_cUF_NN(""));
				RECLIST_VPOS_TPA_FUHRE_ABZUG_EK  	recListAbzugEK = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_EK_id_vpos_tpa_fuhre();
				RECLIST_VPOS_TPA_FUHRE_ABZUG_VK  	recListAbzugVK = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ABZUG_VK_id_vpos_tpa_fuhre();
			
				for (int i=0;i<recListAbzugEK.size();i++)
				{
					if (i==0)
					{
						cText += "Abzüge Lieferant:";
					}
					
					cText += ("\n"+recListAbzugEK.get(i).get_ABZUG_BELEGTEXT_cF_NN("")+ " ---> " +recListAbzugEK.get(i).get_KURZTEXT_cF_NN(""));
					if (S.isFull(recListAbzugEK.get(i).get_LANGTEXT_cF_NN("")))
					{
						cText+=("\n"+recListAbzugEK.get(i).get_LANGTEXT_cF_NN(""));
					}
				}

				for (int i=0;i<recListAbzugVK.size();i++)
				{
					if (i==0)
					{
						cText += "\n\n";
						cText += "Abzüge Abnehmer:";
					}
					
					cText+= ("\n"+recListAbzugVK.get(i).get_ABZUG_BELEGTEXT_cF_NN("")+ " ---> " +recListAbzugVK.get(i).get_KURZTEXT_cF_NN(""));
					if (S.isFull(recListAbzugVK.get(i).get_LANGTEXT_cF_NN("")))
					{
						cText+=("\n"+recListAbzugVK.get(i).get_LANGTEXT_cF_NN(""));
					}
				}
			}
			
			((MyE2_DB_TextArea)oThis.get__Comp("FEHLERBESCHREIBUNG")).setText(cText);
			
		}
	}
	
	
	/**
	 * 
	 * @return
	 * @throws myException
	 */
	public BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW get_recFuhreAusView() throws myException
	{
		BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW recFuhreAusView = null;
		
		/*
		 * jetzt rauskriegen, welche fuhren-id oder fuhren-ort-id vorliegt.
		 * bei Aufruf aus der maske existieren restriction-fields 
		 */
		SQLFieldMAP oSQLFields = this.get_oSQLFieldMAP();
		
		if (oSQLFields.get_("ID_VPOS_TPA_FUHRE_ORT") instanceof SQLFieldForRestrictTableRange)  	// aufruf aus der fuhren-ort-liste-ausklappliste
		{
			String cID_VPOS_TPA_FUHRE_ORT = ((SQLFieldForRestrictTableRange)oSQLFields.get_("ID_VPOS_TPA_FUHRE_ORT")).get_cRestictionValue_IN_DB_FORMAT();
			
			RECORD_VPOS_TPA_FUHRE_ORT  recORT = new RECORD_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT);
			
			recFuhreAusView = new BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW(recORT.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF()); 

		}
		else if ((oSQLFields.get_("ID_VPOS_TPA_FUHRE") instanceof SQLFieldForRestrictTableRange))   // aufruf aus der Fuhren-Liste
		{
			String cID_VPOS_TPA_FUHRE = ((SQLFieldForRestrictTableRange)oSQLFields.get_("ID_VPOS_TPA_FUHRE")).get_cRestictionValue_IN_DB_FORMAT();
			
			recFuhreAusView = new BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW(cID_VPOS_TPA_FUHRE,null); 
			
		}
		else     //aufruf als nur EDIT aus der liste der Fuhren-BAM (eigenes modul) und keine beschraenkungsfelder, dafuer aber infos in der  SQLResultMAP
		{
			
			SQLResultMAP oResultMAP = this.get_oInternalSQLResultMAP();
			
			if (oResultMAP==null)
			{
				throw new myException(this,"Error: Resultmap must be not null !!");
			}
			
			if (oResultMAP.get_LActualDBValue("ID_VPOS_TPA_FUHRE_ORT", true)>0)   //dann wird eine fuhren-ort-BAM bearbeitet
			{
				RECORD_VPOS_TPA_FUHRE_ORT  recORT = new RECORD_VPOS_TPA_FUHRE_ORT(""+oResultMAP.get_LActualDBValue("ID_VPOS_TPA_FUHRE_ORT", true));
				
				recFuhreAusView = new BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW(recORT.get_ID_VPOS_TPA_FUHRE_cUF(),recORT.get_ID_VPOS_TPA_FUHRE_ORT_cUF()); 
			}
			else    //dann wird eine fuhren-BAM bearbeitet
			{
				recFuhreAusView = new BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW(""+oResultMAP.get_LActualDBValue("ID_VPOS_TPA_FUHRE", true),null); 
			}
		}
				
		return recFuhreAusView;
	}

	
	
	
	
	/*
	 * anzeige fuer die aktuelle fuhre/fuhrenort ausfüllen
	 */
	private class ownFormatingAgent_4_AnzeigeFuhrenInfos extends XX_ComponentMAP_SubqueryAGENT
	{

		@Override
		public void PrepareComponents_For_NEW(E2_ComponentMAP omap) 	throws myException
		{
				
			this.__Fill();
		}

		@Override
		public void fillComponents(E2_ComponentMAP omap,SQLResultMAP usedResultMAP) throws myException
		{
			this.__Fill();
		}
		
		
		private void __Fill() throws myException
		{
			BAMF_MASK_ComponentMAP oThis = BAMF_MASK_ComponentMAP.this;
			
			Vector<BAMF_MaskInfoGrid>   vCols = new Vector<BAMF_MaskInfoGrid>();
			vCols.add((BAMF_MaskInfoGrid)oThis.get__Comp(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE1));
			vCols.add((BAMF_MaskInfoGrid)oThis.get__Comp(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE2));
			vCols.add((BAMF_MaskInfoGrid)oThis.get__Comp(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE3));
			vCols.add((BAMF_MaskInfoGrid)oThis.get__Comp(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE4));
			vCols.add((BAMF_MaskInfoGrid)oThis.get__Comp(BAMF__CONST.FIELDNAME_FBAM_MASK_INFO_LINE5));

			
			BAMF_RECORD_VPOS_TPA_FUHRE_AUS_VIEW oRecFuhreAusView = oThis.get_recFuhreAusView();

			for (BAMF_MaskInfoGrid grid: vCols) {
				grid._render(BAMF_MASK_ComponentMAP.this);
			}
			
			
//			if (S.isFull(oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN("")))
//			{
//				
//				RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt = new RECORD_VPOS_TPA_FUHRE_ORT(oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_ORT_cUF_NN(""));
//				Insets  In1 = new Insets(0,1,10,1);
//				
//				
//				for (int i=0;i<5;i++)
//				{
//					
//					MyE2_Grid oGridHelp = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
//					oGridHelp.setSize(6);
//
//					if (recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
//					{
//						oGridHelp.add(new MyE2_Label(new MyE2_String("BAM zum Zusatzladeort"),MyE2_Label.STYLE_SMALL_PLAIN()),In1);
//					}
//					else
//					{
//						oGridHelp.add(new MyE2_Label(new MyE2_String("BAM zum Zusatzabladeort"),MyE2_Label.STYLE_SMALL_PLAIN()),In1);
//					}
//					oGridHelp.add(new MyE2_Label("Fuhre-ID: "+oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_cF_NN("???")+" -- ",MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					oGridHelp.add(new MyE2_Label(recFuhreOrt.get_ANR1_cUF_NN("")+"-"+recFuhreOrt.get_ANR2_cUF_NN("")+" "+recFuhreOrt.get_ARTBEZ1_cUF_NN(""),MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					if (recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF().equals("EK"))
//					{
//						oGridHelp.add(new MyE2_Label(new MyE2_String("geladen bei"),MyE2_Label.STYLE_SMALL_PLAIN()),In1);
//					}
//					else
//					{
//						oGridHelp.add(new MyE2_Label(new MyE2_String("abgeladen bei"),MyE2_Label.STYLE_SMALL_PLAIN()),In1);
//					}
//
//					oGridHelp.add(new MyE2_Label(recFuhreOrt.get_NAME1_cUF_NN("")+" "+recFuhreOrt.get_ORT_cUF_NN(""),MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					
//					vCols.get(i).addAfterRemoveAll(oGridHelp, E2_INSETS.I_2_2_2_2);
//				}
//				
//			}
//			else if (S.isFull(oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_cUF_NN("")))
//			{
//				
//				RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(oRecFuhreAusView.get_ID_VPOS_TPA_FUHRE_cUF_NN(""));
//			    Insets  In1 = new Insets(0,1,10,1);
//			    
//			    for (int i=0;i<5;i++)
//			    {
//				    MyE2_Grid oGridHelp = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
//				    oGridHelp.setSize(6);
//					
//					oGridHelp.add(new MyE2_Label(new MyE2_String("BAM zur Fuhre:"),MyE2_Label.STYLE_SMALL_PLAIN()),In1);
//					//2012-10-16: id-fuhre wird auch angezeigt
//					oGridHelp.add(new MyE2_Label("Fuhre-ID: "+recFuhre.get_ID_VPOS_TPA_FUHRE_cF_NN("???")+" -- ",MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					oGridHelp.add(new MyE2_Label(recFuhre.get_ANR1_EK_cUF_NN("")+"-"+recFuhre.get_ANR2_EK_cUF_NN("")+" "+recFuhre.get_ARTBEZ1_EK_cUF_NN(""),MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					oGridHelp.add(new MyE2_Label(recFuhre.get_L_NAME1_cUF_NN("")+" "+recFuhre.get_L_ORT_cUF_NN(""),MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					oGridHelp.add(new MyE2_Label(new MyE2_String("nach"),MyE2_Label.STYLE_SMALL_PLAIN()),In1);
//					oGridHelp.add(new MyE2_Label(recFuhre.get_A_NAME1_cUF_NN("")+" "+recFuhre.get_A_ORT_cUF_NN(""),MyE2_Label.STYLE_SMALL_BOLD()),In1);
//					
//					vCols.get(i).addAfterRemoveAll(oGridHelp, E2_INSETS.I_2_2_2_2);
//			    }
//			}
//			else
//			{
//				throw new myException(this,"Error: BAM must be at Fuhre or Fuhreort !!!");
//			}

		}
		
	}
	
	
	//2013-04-09: formatierung der Grid-eintraege der angekreutzen mitarbeiter
	private class ownFormatterCheckbox extends XX_FormatCheckBox
	{
		@Override
		public void doFormat(MyE2_CheckBox oCB) throws myException 
		{
			if (oCB.isSelected())
			{
				oCB.setBackground(new E2_ColorLLLight());
			}
			else
			{
				oCB.setBackground(null);
			}
		}
	}
	
	
	private class ownAction4CheckboxesFormatter extends XX_ActionAgent
	{
		private MyE2_DBC_CrossConnection oCC_Field = null;
		
		public ownAction4CheckboxesFormatter(MyE2_DBC_CrossConnection CC_Field) {
			super();
			this.oCC_Field = CC_Field;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oCC_Field.MakeFormatting();
		}
	}


	public String getIdVPOS_TPA_FUHRE() {
		return idVPOS_TPA_FUHRE;
	}


	public String getIdVPOS_TPA_FUHRE_ORT() {
		return idVPOS_TPA_FUHRE_ORT;
	}
}