/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

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
import panter.gmbh.Echo2.FontsAndColors.E2_ColorMaskHighlight;
import panter.gmbh.Echo2.FontsAndColors.E2_Font;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.Mask.MaskComponentsFAB;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_USER_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatz;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea_WithSelektorEASY;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.MyE2_DBC_CrossConnection;
import panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR.XX_SelectAgentForCheckboxesVisible;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class BAMB_MASK_ComponentMAP extends E2_ComponentMAP
{
	
	
	public BAMB_MASK_ComponentMAP(SQLFieldMAP sqlfieldMAP, BAMB_MASK_ModulContainer oMaskContainer, String cModulKenner, E2_NavigationList oNaviList) throws myException
	{
		super(sqlfieldMAP);
		
		String cTO = bibALL.get_TABLEOWNER();
		String cQueryComboBox1 = "SELECT BAM_FESTSTELLUNG FROM "+cTO+".JT_FBAM_FESTSTELLUNG ORDER BY BAM_FESTSTELLUNG";
		String cQueryComboBox2 = "SELECT BAM_BETREFF FROM "+cTO+".JT_FBAM_BETREFF ORDER BY BAM_BETREFF";
		String cQueryComboBox3 = "SELECT BAM_GRUND FROM "+cTO+".JT_FBAM_GRUND ORDER BY BAM_GRUND";
		
		
		/*
		 * textarea mit popup: weigermeldung uebernahme-vorschlag/Behebungs-vorschlag/Behebungsvermerk
		 */
		MyE2_DB_TextArea_WithSelektorEASY oTF_BehebungVorschlag = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("BEHEB_VORSCHLAG"),"BAM_BEHEB_VORSCHLAG");
		oTF_BehebungVorschlag.get_oTextArea().set_iWidthPixel(500);
		oTF_BehebungVorschlag.get_oTextArea().set_iRows(8);

		MyE2_DB_TextArea_WithSelektorEASY oTF_BehebungVermerk = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("BEHEB_VERMERK"),"BAM_BEHEB_VERMERK");
		oTF_BehebungVermerk.get_oTextArea().set_iWidthPixel(500);
		oTF_BehebungVermerk.get_oTextArea().set_iRows(8);
		
		MyE2_DB_TextArea_WithSelektorEASY oTF_Auswirkungen = new MyE2_DB_TextArea_WithSelektorEASY((SQLField)sqlfieldMAP.get("AUSWIRKUNGEN"),"BAM_AUSWIRKUNGEN");
		oTF_Auswirkungen.get_oTextArea().set_iWidthPixel(500);
		oTF_Auswirkungen.get_oTextArea().set_iRows(8);
		
		
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("ID_FBAM"), false, false, 0, false),new MyE2_String("ID"));
		this.add_Component(new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("BAM_GRUND"),cQueryComboBox3, false),new MyE2_String("Grund der Beanstandung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("BAM_DATUM"), false, false, 0, false),new MyE2_String("Datum der Beanstandung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("BAM_ORT"), false, false, 0, false),new MyE2_String("Ort der Beanstandung"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("FEHLERURSACHE"), false, false, 0, false),new MyE2_String("Fehlerursache"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("FEHLERBESCHREIBUNG"), false, false, 0, false),new MyE2_String("Fehlerbeschreibung"));
		this.add_Component(new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("FESTSTELLUNG_BAM"),cQueryComboBox1, false),new MyE2_String("Fehler festgestellt bei"));

		this.add_Component(oTF_Auswirkungen,new MyE2_String("Auswirkungen"));
		this.add_Component(oTF_BehebungVorschlag,new MyE2_String("Behebungsvorschlag"));
		this.add_Component(oTF_BehebungVermerk,new MyE2_String("Behebungsvermerk"));
		
		this.add_Component( new  DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_AUSSTELLUNG")),new MyE2_String("Ausgestellt von"));
		this.add_Component( new  DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_BEHEBUNG")),new MyE2_String("Behoben von"));
		this.add_Component( new  DB_Component_USER_DROPDOWN((SQLField)sqlfieldMAP.get("ID_USER_KONTROLLE")),new MyE2_String("Kontrolliert von"));
		
		this.add_Component(new MyE2_DB_ComboBoxErsatz((SQLField)sqlfieldMAP.get("BETREFF_BAM"),cQueryComboBox2, false),new MyE2_String("Fehler betrifft: "));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("DATUM_AUSSTELLUNG"), false, false, 0, false),new MyE2_String("Ausstellungsdatum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("DATUM_BEHEBUNG"), false, false, 0, false),new MyE2_String("Behebungsdatum"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("DATUM_KONTROLLE"), false, false, 0, false),new MyE2_String("Kontrolldatum"));

		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("ABGESCHLOSSEN_BEHEBUNG"), false, false, 0, false),new MyE2_String("Behebung abgeschlossen"));
		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("ABGESCHLOSSEN_KONTROLLE"), false, false, 0, false),new MyE2_String("Kontrolle durchgeführt"));

		this.add_Component(MaskComponentsFAB.createStandardComponent((SQLField)sqlfieldMAP.get("BUCHUNGSNUMMER"), false, false, 0, false),new MyE2_String("Buchungsnummer"));
		
		
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
																				" NVL(NAME1,'-')|| ' ' || NVL(VORNAME,'-') ",
																				4,
																				new E2_FontPlain(), new MyE2_String("Bitte mindestens einen Verteiler ankreuzen !"), MyE2_DBC_CrossConnection.CROSSTYP_MUST_ONE);
		
		oCC_BamUser.add_AddOnComponentsInFront(new ownCheckBoxToggleInaktivUsers(oCC_BamUser));
		
		
		this.add_Component(BAMB_MASK_ModulContainer.FIELDNAME_CROSSREFERENCE_BAM_USER,oCC_BamUser,new MyE2_String("Zuordnung zu Benutzern"));
		// ------------------------------------------
		
		/*
		 * tochtertabelle fuer das druckprotokoll
		 */
		BAMB_Daughter_PrintProtokoll oDaughterPrintProt = new BAMB_Daughter_PrintProtokoll(sqlfieldMAP,this);
		this.add_Component(BAMB_MASK_ModulContainer.FIELDNAME_DAUGHTERTABLE_PRINTPROTOKOLL,oDaughterPrintProt,new MyE2_String("Druckprotokoll"));
		
		// ------------------------------------------
		
		
		/*
		 * tochtertabelle fuer die infobloecke
		 */
		BAMB_Daughter_Info 	oDaughterInfos = new BAMB_Daughter_Info(sqlfieldMAP,this);
		this.add_Component(BAMB_MASK_ModulContainer.FIELDNAME_DAUGHTERTABLE_INFOS,oDaughterInfos,new MyE2_String("Zusatzinfos zur Beanstandung"));
		

		/*
		 * button zum sdender der info-mails
		 */
		/*
		 * button zum entsperren der BAM-Weigermeldung
		 */
		MyE2_Button oButtonInfoMail = new MyE2_Button(E2_ResourceIcon.get_RI("sendmail.png"),E2_ResourceIcon.get_RI("leer.png"));
		oButtonInfoMail.add_GlobalValidator(new E2_ButtonAUTHValidator(cModulKenner,"SENDE_INFO_MAIL"));
		oButtonInfoMail.add_oActionAgent(new AA_MASK_BAMB_SendMailToUsers(oMaskContainer,oNaviList));
		oButtonInfoMail.setToolTipText(new MyE2_String("Infomail an betroffene Teilnehmer ...").CTrans());
		this.add_Component(BAMB_MASK_ModulContainer.FIELDNAME_FBAM_SENDE_MAILS_TO_VERTEILER,oButtonInfoMail , new MyE2_String("Infomails senden"));
		
		
		
		/*
		 * jetzt ein paar spezielle einstellungen der Maske
		 */
		/*
		 * die checkboxen der Felder ABGESCHLOSSEN_BEHEBUNG
		 * die checkboxen der Felder ABGESCHLOSSEN_KONTROLLE
		 */
		MyE2_DB_CheckBox oCheckBEHEBUNG =  (MyE2_DB_CheckBox)this.get("ABGESCHLOSSEN_BEHEBUNG");
		MyE2_DB_CheckBox oCheckKONTROLLE =  (MyE2_DB_CheckBox)this.get("ABGESCHLOSSEN_KONTROLLE");
		oCheckBEHEBUNG.add_oActionAgent(new AA_MASK_BAMB_CheckBox_BEHEBUNG(this));
		oCheckKONTROLLE.add_oActionAgent(new AA_MASK_BAMB_CheckBox_KONTROLLE(this));
		
		MyE2_DB_ComboBoxErsatz oCB_FestellungBAM = (MyE2_DB_ComboBoxErsatz)this.get("FESTSTELLUNG_BAM");
		MyE2_DB_ComboBoxErsatz oCB_BetreffBAM = (MyE2_DB_ComboBoxErsatz)this.get("BETREFF_BAM");
		MyE2_DB_ComboBoxErsatz oCB_GrundBAM = (MyE2_DB_ComboBoxErsatz)this.get("BAM_GRUND");
		oCB_FestellungBAM.getTextField().setWidth(new Extent(300));
		oCB_BetreffBAM.getTextField().setWidth(new Extent(300));
		oCB_GrundBAM.getTextField().setWidth(new Extent(300));
		
		((MyE2_DB_TextField) this.get("BUCHUNGSNUMMER")).setWidth(new Extent(200));
		
		((MyE2IF__Component) this.get("BUCHUNGSNUMMER")).EXT().set_bDisabledFromBasic(true);
		
		this.set_oMAPSettingAgent(new MAPSETTER_BAMB_MASK());
		
		this.get_V_ADDON_SQL_STATEMENT_BUILDER().add(new ownAddonStatementBuilder());

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

	
	
	private class ownAddonStatementBuilder implements builder_AddOnSQL_STATEMENTS
	{

		@Override
		public Vector<String> get_vSQL_ADDON_INSERT_STATEMENTS(E2_ComponentMAP componentMAP, SQLMaskInputMAP inputMAP,MyE2_MessageVector omv) throws myException
		{
			Vector<String> vRueck = new Vector<String>();
						
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
			return new Vector<String>();
		}
		
	}



	
}