package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ActionAgent_RadioFunction_CheckBoxList;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.V_MailBlock_FromAdresse;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import echopointng.Separator;

public class FS_BT_MASS_MAILER extends MyE2_Button
{

	private E2_NavigationList	oNaviListAdresses = null;
	
	public FS_BT_MASS_MAILER(E2_NavigationList 	onavigationList)
	{
		super(E2_ResourceIcon.get_RI("emailwhite.png"), true);
		
		this.oNaviListAdresses = onavigationList;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"SENDE_MASSENMAIL"));

		
	}


	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			FS_BT_MASS_MAILER oThis = FS_BT_MASS_MAILER.this;
			new FS_WindowPaneSelect_MAIL_RANGE(oThis.oNaviListAdresses);
		}
		
	}
	
	
	
	/*
	 * popup-fenster, das eine auswahl erlaubt, welche adressen in eine 
	 * e-Mail-Aktion einbezogen werden sollen
	 */
	public class FS_WindowPaneSelect_MAIL_RANGE extends E2_BasicModuleContainer
	{
		private E2_NavigationList	oNaviListAdresses = null;
		
		private MyE2_Grid			oGridBasic = new MyE2_Grid(3,0);

		private MyE2_CheckBox		oCBALL_AdressesInList = 				new MyE2_CheckBox();
		private MyE2_CheckBox		oCBALL_AdressesVisiblePage = 			new MyE2_CheckBox();
		private MyE2_CheckBox		oCBALL_AdressesSelected = 				new MyE2_CheckBox();
		
		
		private MyE2_CheckBox		oCBSearchCompanyMails	=			new MyE2_CheckBox(new MyE2_String("Firmen-eMails"));
		private MyE2_CheckBox		oCBSearchCompanyEmplyoeMails	=	new MyE2_CheckBox(new MyE2_String("Mitarbeiter-eMails"));
		private MyE2_CheckBox		oCBSearchAddonAdressesMails		=	new MyE2_CheckBox(new MyE2_String("Lieferadressen-eMails"));
		
		private MyE2_Button			buttonStartReadingAdresses = new MyE2_Button(new MyE2_String("Lese Adressen ein"));
		private MyE2_Button			buttonCancel = new MyE2_Button(new MyE2_String("Abbrechen"));
		
		
		//2013-11-20: mail-typen selektor (wird als selektionswerkzeug benutzt)
		private MyE2_CheckBox 					oCB_BenutzeQualifizier = new MyE2_CheckBox(new MyE2_String("Weitere Auswahl ..."), MyE2_CheckBox.STYLE_BIG_BOLD(), false, false);
		private MyE2_Grid 						oGridContainer4Qualifier = new MyE2_Grid(1, 0);
		private FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS   	oQualifierMatrix = null;


		/**
		 * @param oContentPaneCallingUnit
		 * @param messageagent
		 * @param onavigationList
		 */
		public FS_WindowPaneSelect_MAIL_RANGE(		E2_NavigationList 	onavigationList) throws myException
		{
			super();
			this.oNaviListAdresses = onavigationList;

			//qualifier-grid mit dummy-sqlfield ausstatten (sql-field wird nur eingefuehrt, damit eines da ist, benutzt wird ein passives abfragefeld)
			SQLField oHelpField = onavigationList.get_oComponentMAP__REF().get_oSQLFieldMAP().get("UST_LKZ_ID");
			oQualifierMatrix = new FS__QUALIFIER_GRID_4_EMAIL_MAIN_ADRESS(oHelpField, _DB.ADRESSE);
			oQualifierMatrix.prepare_ContentForNew(false);
			oQualifierMatrix.SET_ALL_CheckBoxes(true);
			
			this.oCB_BenutzeQualifizier.add_oActionAgent(new ToogleQualifierGrid());
			
			/*
			 * styles-vorrat fuer die labels
			 */
			MutableStyle oStyleSmallLabel = new MutableStyle();
			oStyleSmallLabel.setProperty(Label.PROPERTY_FONT,new E2_FontPlain(0));
			
			MutableStyle oStyleLabelTitel = new MutableStyle();
			oStyleLabelTitel.setProperty(Label.PROPERTY_FONT,new E2_FontBold(2));;
			
			this.buttonCancel.add_oActionAgent(new ownActionCancel());
			this.buttonStartReadingAdresses.add_oActionAgent(new ownActionStartReading());
			
			ActionAgent_RadioFunction_CheckBoxList oRadio1 = new ActionAgent_RadioFunction_CheckBoxList(false);
			oRadio1.add_CheckBox(oCBALL_AdressesInList);
			oRadio1.add_CheckBox(oCBALL_AdressesVisiblePage);
			oRadio1.add_CheckBox(oCBALL_AdressesSelected);
			
			if (this.oNaviListAdresses.get_vSelectedIDs_Unformated().size()>0)
				oCBALL_AdressesSelected.setSelected(true);
			else
				oCBALL_AdressesVisiblePage.setSelected(true);
			

			oCBSearchCompanyMails.setSelected(true);
			oCBSearchCompanyEmplyoeMails.setSelected(true);
			oCBSearchAddonAdressesMails.setSelected(true);
			
			
			Insets	oIN = 		new Insets(6,6,6,6);
			Insets	oINHigh = 	new Insets(6,6,6,20);
			
			oGridBasic.add(new MyE2_Label(new MyE2_String("Bitten wählen Sie den Bereich aus, aus dem Mailadressen geholt werden"),oStyleLabelTitel),3,oINHigh);
			
			oGridBasic.add(new MyE2_Label(new MyE2_String("Alle Adressen in der momentanen Liste"),oStyleSmallLabel),1,oIN);
			oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.oNaviListAdresses.get_vectorSegmentation().size(),false),oStyleSmallLabel),1,oIN);
			oGridBasic.add(oCBALL_AdressesInList,1,oIN);
			
			oGridBasic.add(new MyE2_Label(new MyE2_String("Alle Adressen auf der momentanen Seite"),oStyleSmallLabel),1,oIN);
			oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.oNaviListAdresses.get_vActualID_Segment().size(),false),oStyleSmallLabel),1,oIN);
			oGridBasic.add(oCBALL_AdressesVisiblePage,1,oIN);

			oGridBasic.add(new MyE2_Label(new MyE2_String("Alle ausgewählten Adressen"),oStyleSmallLabel),1,oIN);
			oGridBasic.add(new MyE2_Label(new MyE2_String(""+this.oNaviListAdresses.get_vSelectedIDs_Unformated().size(),false),oStyleSmallLabel),1,oIN);
			oGridBasic.add(oCBALL_AdressesSelected,1,oIN);

			oGridBasic.add(new Separator(),3,oINHigh);

			oGridBasic.add(new MyE2_Label(new MyE2_String("Welche Mail-Adressen sollen berücksichtigt werden ?"),oStyleLabelTitel),3,oINHigh);
			
			oGridBasic.add(oCBSearchCompanyMails,1,oIN);
			oGridBasic.add(oCBSearchCompanyEmplyoeMails,1,oIN);
			oGridBasic.add(oCBSearchAddonAdressesMails,1,oIN);

			oGridBasic.add(new Separator(),3,oIN);
			
			//2013-11-20: neue komponente, um weitere verfeinerung zu haben
			oGridBasic.add(this.oCB_BenutzeQualifizier,3,oIN);
			oGridBasic.add(this.oGridContainer4Qualifier,3,oIN);
			
			oGridBasic.add(new Separator(),3,oIN);
			
			oGridBasic.add(new E2_ComponentGroupHorizontal(0,this.buttonStartReadingAdresses,this.buttonCancel,new Insets(2,2,20,2)),3,oIN);
			
			this.add(oGridBasic, E2_INSETS.I_2_2_2_2);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(450),new MyE2_String("Adressen-Mailer ..."));
			
		}

	
		private class ToogleQualifierGrid extends XX_ActionAgent {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FS_WindowPaneSelect_MAIL_RANGE oThis = FS_WindowPaneSelect_MAIL_RANGE.this;

				oThis.oGridContainer4Qualifier.removeAll();
				if (oThis.oCB_BenutzeQualifizier.isSelected() && oThis.oQualifierMatrix != null) {
					oThis.oGridContainer4Qualifier.add(oThis.oQualifierMatrix);
					oThis.oQualifierMatrix.SET_ALL_CheckBoxes(true);
				}
			}
		}
		
		
		private class ownActionCancel extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException
			{
				FS_WindowPaneSelect_MAIL_RANGE.this.CLOSE_AND_DESTROY_POPUPWINDOW(false);
			}
		}

		
		private class ownActionStartReading extends XX_ActionAgent
		{

			public void executeAgentCode(ExecINFO oExecInfo)
			{
				FS_WindowPaneSelect_MAIL_RANGE oThis = FS_WindowPaneSelect_MAIL_RANGE.this;
				
				Vector<String> vID_ADRESSES = new Vector<String>();
				
				if 		(oThis.oCBALL_AdressesInList.isSelected())
					vID_ADRESSES.addAll(oThis.oNaviListAdresses.get_vectorSegmentation());
				else if (oThis.oCBALL_AdressesVisiblePage.isSelected())
					vID_ADRESSES.addAll(oThis.oNaviListAdresses.get_vActualID_Segment());
				else if (oThis.oCBALL_AdressesSelected.isSelected())
					vID_ADRESSES.addAll(oThis.oNaviListAdresses.get_vSelectedIDs_Unformated());
				
				if (vID_ADRESSES.size()==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Sie haben keine Adresse ausgewählt !!"));
					return;
				}
					
				try
				{
				
					//jetzt den vector fuer den sammler zusammenstellen
					Vector<String> vZuSammelndeTypen = null;
					
					if (oThis.oCB_BenutzeQualifizier.isSelected()) {
						vZuSammelndeTypen = new Vector<String>(); 
						
						Vector<String> vKeys = new Vector<String>();
						vKeys.addAll(oThis.oQualifierMatrix.get_hmQ_DEF_CheckBoxen().keySet());
						
	 					
						for (String cMailTyp: vKeys) {
							if (oThis.oQualifierMatrix.get_hmQ_DEF_CheckBoxen().get(cMailTyp)!=null && 
								oThis.oQualifierMatrix.get_hmQ_DEF_CheckBoxen().get(cMailTyp).isSelected()) {
								vZuSammelndeTypen.add(cMailTyp);
							}
						}
					}
					
					MailBlock_Vector vMailAdresses = new V_MailBlock_FromAdresse(	vID_ADRESSES,
																				oThis.oCBSearchCompanyMails.isSelected(),
																				oThis.oCBSearchCompanyEmplyoeMails.isSelected(),
																				oThis.oCBSearchAddonAdressesMails.isSelected(), 
																				vZuSammelndeTypen,
																				false,
																				"ADRESS_MODULE"
																				);
					
					if (vMailAdresses.size()==0)
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurden keine Mail-Adressen gefunden !!!"));
						return;
					}
					
					
					ownMassMailer oMailContainer = new ownMassMailer();   
					
					//2011-02-24: vordefinition der wiedervorlage im massenmailer weg
					oMailContainer.baue_MailMaske(vMailAdresses,
													bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,true,false,30,null);
					
					oMailContainer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Mailversand an (einzelne/mehrere) Adressen ..."));
					oThis.CLOSE_AND_DESTROY_POPUPWINDOW(false);
					
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Aufbereiten der Adressen ..."));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
				}
			}
		}

		
		
		private class ownMassMailer extends E2_MassMailer
		{

			public ownMassMailer() throws myException
			{
				//super("ADRESS_MAIL_BETREFF", "ADRESS_MAIL_TEXTBLOCK","ADRESSENMAILER", new MailSecurityPolicyAllowNothing());
				//2013-01-15: mailerweiteurng mit eingenen adressen erlauben
				super("ADRESS_MAIL_BETREFF", "ADRESS_MAIL_TEXTBLOCK","ADRESSENMAILER", new MailSecurityPolicyAllowAll());
			}

//			@Override
//			public MailBlock build_MailBlock4Added_EmptyMails() throws myException
//			{
//				return null;   //wird nicht benoetigt, dies nicht erlaubt ist
//			}
	//
//			@Override
//			public MailBlock build_MailBlock4Added_MitarbeiterMails() throws myException
//			{
//				return null;   //wird nicht benoetigt, dies nicht erlaubt ist
//			}
	//
//			@Override
//			public MailBlock build_MailBlock4Added_SearchedMails()	throws myException
//			{
//				return null;   //wird nicht benoetigt, dies nicht erlaubt ist
//			}

			
			@Override
			public MailBlock build_MailBlock4Added_EmptyMails() throws myException
			{
				return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Freie Maileingabe>"));
			}

			@Override
			public MailBlock build_MailBlock4Added_MitarbeiterMails()	throws myException
			{
				return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Mitarbeiteradresse>"));
			}

			@Override
			public MailBlock build_MailBlock4Added_SearchedMails() throws myException
			{
				return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Firmenadresse aus Suche>"));
			}
		}
		

	//	
//		private class ownMailBlock extends MailBlock
//		{
//			private MyE2_String 	cStringForLabel = null;
//			private MyE2_String 	cStringForSubLabel = null;
//			
//			public ownMailBlock(MyE2_String  StringForLabel, MyE2_String StringForSubLabel)throws myException
//			{
//				super( new MailSecurityPolicyAllowNothing_but_EditAdress());
//				this.cStringForLabel = StringForLabel;
//				this.cStringForSubLabel = StringForSubLabel;
//			}
	//
//			@Override
//			protected MailAdress4MailBlock build_MailAdress4MailBlock(	String mailAdresse, MailBlock OWN_MailBlock) throws myException
//			{
//				return new ownMailAdress4MailBlock(mailAdresse,this,this.cStringForSubLabel);
//			}
	//
//			@Override
//			public Component get_ComponentForMailerList()
//			{
//				return new MyE2_Label(this.cStringForLabel,MyE2_Label.STYLE_SMALL_PLAIN());
//			}
	//
//			@Override
//			public MyString get_ComponentTextForProtokoll()
//			{
//				return this.cStringForLabel;
//			}
	//
//			@Override
//			public String get_cBelegTyp()
//			{
//				return new MyE2_String("<kein Beleg>").CTrans();
//			}
	//
//			@Override
//			public MyString get_cBelegTyp4User()
//			{
//				return new MyE2_String("<kein Beleg>");
//			}
	//
//			@Override
//			public MyString get_cKommentar()
//			{
//				return new MyE2_String("<Kein Kommentar>");
//			}
	//
//			@Override
//			public String get_cModulInfo()
//			{
//				return "MASSMAILER";
//			}
	//
//			@Override
//			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
//			{
//				return new ownMailAdress4MailBlock("",this,new MyE2_String("<Leere Adresse>"));
//			}
	//
//			@Override
//			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
//			{
//				return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("<Mitarbeiteradresse>"));
//			}
	//
//			@Override
//			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
//			{
//				return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("<Adresse aus Firmenstamm>"));
//			}
//			
//		}
	//	
	//	
	//
//		private class ownMailAdress4MailBlock extends MailAdress4MailBlock
//		{
//			private MyString cInfo = null;
	//
//			public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyString Info) throws myException
//			{
//				super(mailAdress, MailBlockThisBelongsTo, null);
//				this.cInfo = Info;
//			}
	//
//			@Override
//			public Component get_ComponentForMailerList()
//			{
//				return new MyE2_Label(this.cInfo,MyE2_Label.STYLE_SMALL_ITALIC());
//			}
//			
//			@Override
//			public String get_cAdressInfo()
//			{
//				return new MyE2_String("<Keine Adresse>").CTrans();
//			}
	//
//			@Override
//			public MyString get_cComponentText()
//			{
//				return this.cInfo;
//			}
	//
//			@Override
//			public String get_cID_ADRESSE_EMPFAENGER()
//			{
//				return "<>";
//			}
//		}
		
		
		
	}
	
	
	
}
