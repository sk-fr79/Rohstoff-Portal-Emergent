package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MaskSearchField.XX_SearchBlock;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;


/*
 * popup-fenster zum suchen und selektieren von adress-Mails 
 */
public class Popup_EMailAdress_Searcher extends E2_BasicModuleContainer 
{
	private 	MyE2_Column				oBasicColumn = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER());
	
	private 	MyE2_Grid 				oGridSearchErgebnis = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER());
	
	private 	MyE2_TextField			oTFSearch = new MyE2_TextField();
	private 	MyE2_Button				oBT_StartSearch = new MyE2_Button(E2_ResourceIcon.get_RI("suche.png"));

	private 	MyE2_Button				oBT_UebernehmeMails = new MyE2_Button(new MyE2_String("Gewählte Mailadressen übernehmen"));
	
	private 	E2_ResourceIcon			iconPerson = 	E2_ResourceIcon.get_RI("person.png");
	private 	E2_ResourceIcon			iconFirma = 	E2_ResourceIcon.get_RI("firma.png");
	private 	E2_ResourceIcon			iconLager = 	E2_ResourceIcon.get_RI("firma_lager.png");
	
	private 	E2_MassMailer 			oMassMailer = null;                //je nach verwendung ist einer der beiden parameter leer
	private  	MailBlock           	oMailBlock = null;
	
	private 	RECLIST_EMAIL  			reclistEmail = null;
	
	private   	Vector<MyE2_CheckBox>   vCheckBox = new Vector<MyE2_CheckBox>();

	/**
	 * 
	 * @param MassMailer    (Variante 1)
	 * @param oMailBlock 	(Variante 2)    genau eines von beiden muss null sein
	 * @throws myException
	 */
	public Popup_EMailAdress_Searcher(	E2_MassMailer 	MassMailer, 
										MailBlock 		mailBlock) throws myException
	{
		super();

		this.oMassMailer = MassMailer;
		this.oMailBlock = mailBlock;
		
		if ((this.oMassMailer==null && this.oMailBlock==null) || (this.oMassMailer != null && this.oMailBlock != null))
		{
			throw new myException(this,"Error: only one parameter MUST be NULL !!");
		}
		
		this.oBT_StartSearch.add_oActionAgent(new ownActionAgentStartSearch());
		this.oBT_UebernehmeMails.add_oActionAgent(new ownActionAgentSaveAndClosePOPUP());

		this.add(this.oBasicColumn);
		
		// suchelemente einblenden
		this.oBasicColumn.add(new E2_ComponentGroupHorizontal(0,this.oTFSearch,this.oBT_StartSearch,E2_INSETS.I_0_0_10_0),E2_INSETS.I_10_10_10_10);
		this.oBasicColumn.add(new Separator());
		this.oBasicColumn.add(this.oGridSearchErgebnis,E2_INSETS.I_10_10_10_10);
	
		this.set_Component_To_ButtonPane(new E2_ComponentGroupHorizontal(0,this.oBT_UebernehmeMails,E2_INSETS.I_10_10_10_2));
		
	}

	
	
	
	private void build_Liste() throws myException
	{
		this.oGridSearchErgebnis.removeAll();
		this.vCheckBox.removeAllElements();
		
		for (int i=0;i<this.reclistEmail.get_vKeyValues().size();i++)
		{
			RECORD_EMAIL recEmail = this.reclistEmail.get(i);
			MyE2_CheckBox oCB = new MyE2_CheckBox();
			oCB.EXT().set_O_PLACE_FOR_EVERYTHING(recEmail);
			this.vCheckBox.add(oCB);
			
			this.oGridSearchErgebnis.add(oCB,E2_INSETS.I_0_2_10_2);

			MyE2_String cInfo = this.get_MailAdressInfoText(recEmail);
			
			if (recEmail.get_UP_RECORD_ADRESSE_id_adresse().get_ADRESSTYP_lValue(new Long(-1)).longValue() == myCONST.ADRESSTYP_MITARBEITER )
			{
				this.oGridSearchErgebnis.add(new MyE2_Label(this.iconPerson),E2_INSETS.I_0_2_10_2);
				this.oGridSearchErgebnis.add(new MyE2_Label(recEmail.get_EMAIL_cUF_NN("")),E2_INSETS.I_0_2_10_2);
				this.oGridSearchErgebnis.add(new MyE2_Label(cInfo, MyE2_Label.STYLE_SMALL_ITALIC()),E2_INSETS.I_0_2_10_2);	
			}
			else if (recEmail.get_UP_RECORD_ADRESSE_id_adresse().get_ADRESSTYP_lValue(new Long(-1)).longValue() == myCONST.ADRESSTYP_LIEFERADRESSE)
			{
				this.oGridSearchErgebnis.add(new MyE2_Label(this.iconLager),E2_INSETS.I_0_2_10_2);
				this.oGridSearchErgebnis.add(new MyE2_Label(recEmail.get_EMAIL_cUF_NN("")),E2_INSETS.I_0_2_10_2);
				this.oGridSearchErgebnis.add(new MyE2_Label(cInfo, MyE2_Label.STYLE_SMALL_ITALIC()),E2_INSETS.I_0_2_10_2);	
			}
			else
			{
				this.oGridSearchErgebnis.add(new MyE2_Label(this.iconFirma),E2_INSETS.I_0_2_10_2);
				this.oGridSearchErgebnis.add(new MyE2_Label(recEmail.get_EMAIL_cUF_NN("")),E2_INSETS.I_0_2_10_2);
				this.oGridSearchErgebnis.add(new MyE2_Label(cInfo, MyE2_Label.STYLE_SMALL_ITALIC()),E2_INSETS.I_0_2_10_2);	
			}
		}
	}
	
	
	
	
	private class ownActionAgentStartSearch extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			Popup_EMailAdress_Searcher oThis = Popup_EMailAdress_Searcher.this;
			
			String cSearchText = oThis.oTFSearch.getText();
			if (bibALL.isEmpty(cSearchText))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben Sie einen Suchtext an ..."));
				return;
			}
			
			try
			{
				oThis.reclistEmail = new SearchBlockAdressenUndMitarbeiter().get_Results(cSearchText);
				
				if (oThis.reclistEmail.get_vKeyValues().size()==0)
				{
					if (oThis.reclistEmail.get_Overflow())
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurden zu viele Ergebnisse gefunden. Bitte genauere Suchangaben !")));
						return;
					}
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es konnte keine Mailadresse gefunden werden !")));
					return;
				}

				oThis.build_Liste();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
		}
	}
	

	
	
	
	/*
	 * geht die ergebnissliste durch, und baut fuer die selektierten mailadressen
	 * ein E2_MailEintragFuerMassMailer - objekt und haengt dieses in der Vektor des rufenden 
	 * E2_ModuleContainer_PopUP_MassMailer -  Objektes
	 */
	private class ownActionAgentSaveAndClosePOPUP extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			Popup_EMailAdress_Searcher oThis = Popup_EMailAdress_Searcher.this;
			String cInfo = "";
			
			try
			{
				/*
				 * alle angekreutzten zusatzmailadressen anhaengen
				 */
				for (MyE2_CheckBox oCB: oThis.vCheckBox)
				{
					if (oCB.isSelected())
					{
						RECORD_EMAIL recEmail = (RECORD_EMAIL)oCB.EXT().get_O_PLACE_FOR_EVERYTHING();
						
						cInfo+=recEmail.get_EMAIL_cUF_NN("")+" / ";
						
						if (oThis.oMassMailer!=null)                 //dann wurde der button in der ebene des mailers gestartet
						{
							MailBlock	mailBlock =  oThis.oMassMailer.build_MailBlock4Added_SearchedMails();
							mailBlock.ADD_MailAdress4MailBlock4Added_SearchedMail(recEmail.get_EMAIL_cUF_NN(""));
							
							oThis.oMassMailer.get_MailBlockVector().add(mailBlock);
							oThis.oMassMailer.paint_MailMask();  					 // NEUAUFBAU
						}
						if (oThis.oMailBlock != null)      //dann wurde der button in der ebene des maileintrags gestartet
						{
							oThis.oMailBlock.ADD_NewTargetAdress(recEmail.get_EMAIL_cUF_NN(""));
						}
					}
				}
				
				if (oThis.oMassMailer!=null)                 //dann wurde der button in der ebene des mailers gestartet
				{
					oThis.oMassMailer.paint_MailMask();
				}
				if (oThis.oMailBlock != null)      //dann wurde der button in der ebene des maileintrags gestartet
				{
					oThis.oMailBlock.build_MailSendGrid();
				}

			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
			oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurde folgende Mailadresse(n) an die Liste angehaengt: ",true,cInfo,false)));
		}
		
	}
	
	
	
	
	private MyE2_String get_MailAdressInfoText(RECORD_EMAIL recEmail) throws myException
	{
		MyE2_String cRueck = new MyE2_String("");
		
		/*
		 * alle angekreutzten zusatzmailadressen anhaengen
		 */
		MyE2_String oStringFirma = 			new MyE2_String("<Firma> ");
		MyE2_String oStringMitarbeiter = 	new MyE2_String("<Firmen> <Mitarbeiter> ");
		MyE2_String oStringLieferadresse = 	new MyE2_String("<Firmen> <Lieferadresse> ");

		
		if (recEmail.get_UP_RECORD_ADRESSE_id_adresse().get_ADRESSTYP_lValue(new Long(-1)).longValue() == myCONST.ADRESSTYP_MITARBEITER)
		{
			//hauptadresse suchen
			RECORD_ADRESSE oAdrHaupt = recEmail.get_UP_RECORD_ADRESSE_id_adresse().
										get_DOWN_RECORD_LIST_MITARBEITER_id_adresse_mitarbeiter().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
			String cInfo = oAdrHaupt.get_NAME1_cUF_NN("")+" - "+oAdrHaupt.get_ORT_cUF_NN("");
			
			cRueck.addString(oStringMitarbeiter);
			cRueck.addUnTranslated(cInfo);
		}
		else if (recEmail.get_UP_RECORD_ADRESSE_id_adresse().get_ADRESSTYP_lValue(new Long(-1)).longValue() == myCONST.ADRESSTYP_LIEFERADRESSE)
		{
			//hauptadresse suchen
			RECORD_ADRESSE oAdrHaupt = recEmail.get_UP_RECORD_ADRESSE_id_adresse().
												get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
			String cInfo = oAdrHaupt.get_NAME1_cUF_NN("")+" - "+oAdrHaupt.get_ORT_cUF_NN("");
			
			cRueck.addString(oStringLieferadresse);
			cRueck.addUnTranslated(cInfo);
		}
		else
		{
			String cInfo = recEmail.get_UP_RECORD_ADRESSE_id_adresse().get_NAME1_cUF_NN("")+" - "+recEmail.get_UP_RECORD_ADRESSE_id_adresse().get_ORT_cUF_NN("");

			cRueck.addString(oStringFirma);
			cRueck.addUnTranslated(cInfo);
		}

		
		return cRueck;
	}
	
	
	
	
	private class SearchBlockAdressenUndMitarbeiter extends XX_SearchBlock
	{
		private int    iMaxResults = 100;
		

		public SearchBlockAdressenUndMitarbeiter()
		{
			super();
		}

		
		public RECLIST_EMAIL get_Results(String cSearchText) throws myException
		{
			RECLIST_EMAIL rec_listEmail = new RECLIST_EMAIL(	bibALL.ReplaceTeilString("SELECT JT_EMAIL.* "+ 
					" FROM "+bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_EMAIL WHERE"+ 
					" JT_ADRESSE.ID_ADRESSE=JT_EMAIL.ID_ADRESSE AND "+ 
					" (UPPER(NAME1) LIKE UPPER('%#WERT#%') OR UPPER(NAME2) LIKE UPPER('%#WERT#%') OR UPPER(ORT) LIKE UPPER('%#WERT#%') OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') OR UPPER(PLZ) LIKE UPPER('%#WERT#%') OR UPPER(EMAIL) LIKE UPPER('%#WERT#%') OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#')"+ 
					" ORDER BY EMAIL ","#WERT#",cSearchText),this.iMaxResults);
			
			return rec_listEmail;
		}

		
//		@Override
//		public Component get_ContainerWithFoundButtons()
//		{
//			return null;
//		}

		@Override
		public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
		{
			return null;
		}
		
		
		@Override
		public E2_BasicModuleContainer get_ContainerForShowResults()
		{
			return new ownBasicModuleContainer();
		}
		
		private class ownBasicModuleContainer extends E2_BasicModuleContainer
		{
		}

		
	}
	
	
	
}
