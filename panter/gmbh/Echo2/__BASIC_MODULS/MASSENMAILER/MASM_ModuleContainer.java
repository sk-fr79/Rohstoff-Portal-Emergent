package panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER;

import java.util.StringTokenizer;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing_but_EditAdress;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * massenmailer 
 */
public class MASM_ModuleContainer extends E2_BasicModuleContainer 
{

	private MyE2_Button			oButtonLeseAdressenEin	= 	new MyE2_Button(new MyE2_String("Lese Adressen ein"));
	private MyE2_TextArea		oTextMitEmails = 			new MyE2_TextArea("",700,1000,30);
	
	private MyE2_Grid			oGridBasic = new MyE2_Grid(6, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
	 
	private SelektionEmailGroup	oSelGroup = new SelektionEmailGroup();
	
	private MailSecurityPolicy  oSecPolicyGlobale = new MailSecurityPolicyAllowAll();
	private MailSecurityPolicy  oSecPolicyInner = new MailSecurityPolicyAllowNothing_but_EditAdress();
	
	
	public MASM_ModuleContainer()  throws myException
	{
		super();
		this.set_MODUL_IDENTIFIER(E2_CONSTANTS_AND_NAMES.NAME_MODUL_MASSMAILER);

		this.oButtonLeseAdressenEin.add_oActionAgent(new ActionLeseEin());
		
		this.add(this.oGridBasic, E2_INSETS.I_10_10_10_10);
		
		// zeile 1
		this.oGridBasic.add(new MyE2_Label(new MyE2_String("Massenmailer"),MyE2_Label.STYLE_TITEL_BIG()),6, E2_INSETS.I_10_10_10_10);

		// zeile 2
		this.oGridBasic.add(new MyE2_Label(new MyE2_String("Bitte geben sie die Mailadressen in das folgende Feld ein:"),MyE2_Label.STYLE_TITEL_NORMAL()),6, E2_INSETS.I_10_10_10_10);
		
		// zeile 3
		this.oGridBasic.add(new MyE2_Label("Vordefinierte Gruppe laden: "),3, E2_INSETS.I_10_10_10_10);
		this.oGridBasic.add(this.oSelGroup,3, E2_INSETS.I_10_10_10_10);
		
		// zeile 4
		this.oGridBasic.add(this.oTextMitEmails,6, E2_INSETS.I_10_10_10_10);
		
		
		// zeile 5
		this.oGridBasic.add(new E2_ComponentGroupHorizontal(0,this.oButtonLeseAdressenEin,new Insets(0)),1, E2_INSETS.I_10_10_10_10);
	}

	
	
	
	
	/*
	 * laesst eine email-gruppe zuladen
	 */
    private class SelektionEmailGroup extends MyE2_SelectField 
    {
		public SelektionEmailGroup() throws myException
		{
			super("SELECT TITEL,ID_EMAIL_BLOCK FROM "+bibALL.get_TABLEOWNER()+".JT_EMAIL_BLOCK ORDER BY TITEL ",false,true,false,false);
			this.add_oActionAgent(new ActionAgentSelectMailBloecke());
		}
    }

	
	private class ActionAgentSelectMailBloecke extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			MyE2_SelectField 		oSel = (MyE2_SelectField)bibE2.get_LAST_ACTIONEVENT().getSource();
			MASM_ModuleContainer 	oThis = MASM_ModuleContainer.this;
			
			try
			{
				String cID_EMAIL_BLOCK = oSel.get_ActualWert();
	
				if (!cID_EMAIL_BLOCK.trim().equals(""))            // der leer-eintrag
				{
					
					String cEmail_List = bibDB.EinzelAbfrage("SELECT EMAIL_LISTE FROM "+
										bibE2.cTO()+
										".JT_EMAIL_BLOCK WHERE ID_EMAIL_BLOCK="+cID_EMAIL_BLOCK);
					
					oThis.oTextMitEmails.setText(cEmail_List);
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Auswahl der Mailblöcke !!"));
			}
		}
		
	}
	
	
	
	/*
	 * action-agent fuer den einlese-button
	 */
	private class ActionLeseEin extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			MASM_ModuleContainer 	oThis = MASM_ModuleContainer.this;
			String 					cText = oThis.oTextMitEmails.getText();
			
			
			if (cText == null || cText.trim().equals(""))
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte geben sie e-Mail-Adressen an !!!"));
				return;
			}
			
			try
			{
				// jetzt die zieladressen in einen adress-vector legen
				StringTokenizer textTrenner = new StringTokenizer(" \n\r" + (cText + " \n\r"), "\n\r");
				String		    cZeile = null;
				
				MailBlock_Vector vMailEintraege = new MailBlock_Vector();
	
				while (textTrenner.hasMoreElements())
				{
					cZeile = textTrenner.nextToken().trim();
					if (bibALL.isEmpty(cZeile))
						continue;
					
					/*
					 * jetzt die zeile noch trennen nach leerzeichen/kommas/semikolons
					 */
					cZeile = bibALL.ReplaceTeilString(cZeile," ",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,";",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
					cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
					
					Vector<String> vSeps = bibALL.TrenneZeile(cZeile,",");
					
					for (int i=0;i<vSeps.size();i++)
					{
						String cMailAdresse = ((String)vSeps.get(i)).trim();
						
						if (!bibALL.isEmpty(cMailAdresse))
						{
							
							//ownMailBlock oMailBlock = new ownMailBlock(new MyE2_String("Massenmail"),new MyE2_String("<aus Mailgruppe>"));
							MailBlock_STD oMailBlock = new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<aus Mailgruppe>"), 
															MASM_ModuleContainer.this.oSecPolicyInner);
							oMailBlock.ADD_NewTargetAdress(cMailAdresse);
							
							vMailEintraege.add(oMailBlock);
							
						}
					}
				}
	
				if (vMailEintraege.size() == 0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler! Keine Zieladressen vorhanden !"));
				}
				else
				{

					ownMassMailer oMassmailer =   
						new  ownMassMailer(	 "MASS_MAIL_BETREFF",
											 "MASS_MAIL_TEXTBLOCK",
											 "MASSENMAILER",
											 oThis.oSecPolicyGlobale);
					
					oMassmailer.baue_MailMaske(vMailEintraege,
													bibALL.get_RECORD_USER().get_EMAIL_cUF_NN(""),true,false,false,0,null);
					
					oMassmailer.CREATE_AND_SHOW_POPUPWINDOW(null,null,new MyE2_String("Mailversand an (einzelne/mehrere) Adressen ..."));
				}
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Einlesen der Mails !"));
			}
		}
	}
	
	
	
//	
//	private class ownMailBlock extends MailBlock
//	{
//		private MyE2_String 	cStringForLabel = null;
//		private MyE2_String 	cStringForSubLabel = null;
//		
//		public ownMailBlock(MyE2_String  StringForLabel, MyE2_String StringForSubLabel)throws myException
//		{
//			super(MASM_ModuleContainer.this.oSecPolicyInner);
//			this.cStringForLabel = StringForLabel;
//			this.cStringForSubLabel = StringForSubLabel;
//		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock(	String mailAdresse, MailBlock OWN_MailBlock) throws myException
//		{
//			return new ownMailAdress4MailBlock(mailAdresse,this,this.cStringForSubLabel);
//		}
//
//		@Override
//		public Component get_ComponentForMailerList()
//		{
//			return new MyE2_Label(this.cStringForLabel,MyE2_Label.STYLE_SMALL_PLAIN());
//		}
//
//		@Override
//		public MyString get_ComponentTextForProtokoll()
//		{
//			return this.cStringForLabel;
//		}
//
//		@Override
//		public String get_cBelegTyp()
//		{
//			return new MyE2_String("<kein Beleg>").CTrans();
//		}
//
//		@Override
//		public MyString get_cBelegTyp4User()
//		{
//			return new MyE2_String("<kein Beleg>");
//		}
//
//		@Override
//		public MyString get_cKommentar()
//		{
//			return new MyE2_String("<Kein Kommentar>");
//		}
//
//		@Override
//		public String get_cModulInfo()
//		{
//			return "MASSMAILER";
//		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
//		{
//			return new ownMailAdress4MailBlock("",this,new MyE2_String("<Leere Adresse>"));
//		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
//		{
//			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("<Mitarbeiteradresse>"));
//		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
//		{
//			return new ownMailAdress4MailBlock(mailAdress,this,new MyE2_String("<Adresse aus Firmenstamm>"));
//		}
//		
//	}
//	
//	
//
//	private class ownMailAdress4MailBlock extends MailAdress4MailBlock
//	{
//		private MyString cInfo = null;
//
//		public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyString Info) throws myException
//		{
//			super(mailAdress, MailBlockThisBelongsTo, null);
//			this.cInfo = Info;
//		}
//
//		@Override
//		public Component get_ComponentForMailerList()
//		{
//			return new MyE2_Label(this.cInfo,MyE2_Label.STYLE_SMALL_ITALIC());
//		}
//		
//		@Override
//		public String get_cAdressInfo()
//		{
//			return new MyE2_String("<Keine Adresse>").CTrans();
//		}
//
//		@Override
//		public MyString get_cComponentText()
//		{
//			return this.cInfo;
//		}
//
//		@Override
//		public String get_cID_ADRESSE_EMPFAENGER()
//		{
//			return "<>";
//		}
//	}
	
	
	
	
	private class ownMassMailer extends E2_MassMailer
	{

		public ownMassMailer(String mailerTextSelectorKENNER_BETREFF,String mailerTextSelectorKENNER_MAILBLOCK,String cnamensanteil_fuer_archiv,MailSecurityPolicy mailSecurityPolicy) throws myException
		{
			super(mailerTextSelectorKENNER_BETREFF, mailerTextSelectorKENNER_MAILBLOCK,cnamensanteil_fuer_archiv, mailSecurityPolicy);
		}

		@Override
		public MailBlock build_MailBlock4Added_EmptyMails() throws myException
		{
			return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Freie Maileingabe>"),MASM_ModuleContainer.this.oSecPolicyInner);
		}

		@Override
		public MailBlock build_MailBlock4Added_MitarbeiterMails()	throws myException
		{
			return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Mitarbeiteradresse>"),MASM_ModuleContainer.this.oSecPolicyInner);
		}

		@Override
		public MailBlock build_MailBlock4Added_SearchedMails() throws myException
		{
			return new MailBlock_STD(new MyE2_String("Massenmail"),new MyE2_String("<Firmenadresse aus Suche>"),MASM_ModuleContainer.this.oSecPolicyInner);
		}

		
	}
	
	
}
