package rohstoff.Echo2BusinessLogic.FIBU;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.MAIL_AND_REPORT.ACTIONAGENT_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdressSearcher;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIBU;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class FIBU_LIST_BT_PrintMail_Buchungsblock extends MyE2_Button 
{

	private E2_NavigationList  oNaviList = null;

	
	public FIBU_LIST_BT_PrintMail_Buchungsblock(E2_NavigationList NaviList) 
	{
		super(new MyE2_String("Druck/Mail Buchungsblatt"));
		this.oNaviList =NaviList;
		
		this.setToolTipText(new MyE2_String("Drucken oder Mailen Buchungsblatt des gewählten Buchungsblocks ...").CTrans());
		
		this.add_oActionAgent(new ownActionAgent(new MyE2_String("Drucke Buchungsblatt"), this.oNaviList));
		
	}
	
	
	
	private  class ownActionAgent extends ACTIONAGENT_MAIL_AND_REPORT
	{

		private Vector<String>    			vIDs = new Vector<String>();

		public ownActionAgent(	MyE2_String 		fensterTitel, 
								E2_NavigationList 	navList)
		{
			super(fensterTitel, "PRINT_BUCHUNGSBLATT_BLATT", "PRINT_BUCHUNGSBLATT_BLATT",null,false);
		}

		
		
		@Override
		public E2_MassMailer get_MassMailer() throws myException
		{
			E2_MassMailer oMassMailer = new E2_MassMailer_STD(	"FIBU_BUCHUNGSBLATT_MAIL_BETREFF",
																"FIBU_BUCHUNGSBLATT_MAIL_TEXT",
																"FIBU_BUCHUNGSBLATT");
			
			return oMassMailer;
		}


		@Override
		public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
		{
			
			V_JasperHASH vRueck = new V_JasperHASH();
			
			this.vIDs = new Vector<String>();
			this.vIDs.addAll(FIBU_LIST_BT_PrintMail_Buchungsblock.this.oNaviList.get_vSelectedIDs_Unformated());

			//jetzt nachsehen, ob es eine selektion innerhalb eines buchungsblock ist
			RECLIST_FIBU  reclistTest = new RECLIST_FIBU(vIDs);
			VectorSingle vTest = new VectorSingle();
			
			vTest.addAll(reclistTest.get_BUCHUNGSBLOCK_NR_hmString_UnFormated("").values());
			
			if (vTest.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Zeile  und nur Zeilen aus genau einem Buchungsblock auswählen !")));
				return vRueck;
			}
			
			vRueck.add(new ownJasperHASH(reclistTest.get(0).get_ID_ADRESSE_cUF(), vTest.get(0)));
			
			return vRueck;
		}


		
		private class ownJasperHASH extends E2_JasperHASH
		{

			private String cID_ADRESSE = null;
			
			public ownJasperHASH(String ID_ADRESSE, String ID_BUCHUNGSBLOCK)	throws myException
			{
				super("BUCHUNGS_BLATT", new JasperFileDef_PDF());
				this.cID_ADRESSE = ID_ADRESSE;
				this.put("buchungsblock_nr", ID_BUCHUNGSBLOCK);

				this.set_bVorschauDruck(false);
				this.set_cDownloadAndSendeNameStaticPart("BUCHUNGS_BLATT"+"_"+cID_ADRESSE);
			}

			@Override
			protected MailBlock create_MailBlock() throws myException
			{
				ownMailBlock oMailBlock = new ownMailBlock();
				
				oMailBlock.add_VMailAdress4MailBlock(
						new MailAdressSearcher(	this.cID_ADRESSE,
												true,
												true,
												true,
												myCONST.EMAIL_TYPE_VALUE_FIBU,
												new MyE2_String(myCONST.EMAIL_TYPE_TEXT_FIBU),
												null, 
												true, 
												new Boolean(false), 
												new Boolean(true))
						);
				
				return oMailBlock;
			}

			@Override
			public boolean get_bIsDesignedForMail() throws myException
			{
				return true;           //wenn preview, dann keine mail
			}
			
			
			@Override
			public void doActionAfterCreatedFile() throws myException
			{
			}
			
		}

		
		
		private class ownMailBlock extends MailBlock
		{

			public ownMailBlock() throws myException
			{
				super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4MailBlock(mailAdresse,OWN_MailBlock,new MyE2_String("Vorhandene eMail"),new Boolean(false));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4MailBlock("",OWN_MailBlock,new MyE2_String("Freie eMail"),new Boolean(true));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"),new Boolean(false));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4MailBlock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-eMail"),new Boolean(false));
			}

			@Override
			public Component get_ComponentForMailerList() throws myException
			{
				return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
			}

			@Override
			public MyString get_ComponentTextForProtokoll() throws myException
			{
				return new MyE2_String("FIBU_KONTOBLATT");
			}

			@Override
			public String get_cBelegTyp() throws myException
			{
				return "FIBU_KONTOBLATT";
			}

			@Override
			public MyString get_cBelegTyp4User() throws myException
			{
				return new MyE2_String("FIBU_KONTOBLATT");
			}

			@Override
			public MyString get_cKommentar() throws myException
			{
				return new MyE2_String("Mail aus FIBU-Kontoblatt");
			}

			@Override
			public String get_cModulInfo() throws myException
			{
				return FIBU_LIST_BT_PrintMail_Buchungsblock.this.oNaviList.get_oContainer_NaviList_BelongsTo().get_MODUL_IDENTIFIER();
			}
		}

		
		
		private  class ownMailAdress4MailBlock extends MailAdress4MailBlock
		{
			private MyE2_String cStringForComponent = null;
			
			public ownMailAdress4MailBlock(String mailAdress,MailBlock MailBlockThisBelongsTo,MyE2_String StringForComponent, Boolean bAllowEdit) throws myException
			{
				super(mailAdress, MailBlockThisBelongsTo, bAllowEdit);
				this.cStringForComponent = StringForComponent; 
			}

			@Override
			public Component get_ComponentForMailerList() throws myException
			{
				return new MyE2_Label(this.cStringForComponent,MyE2_Label.STYLE_SMALL_ITALIC());
			}

			@Override
			public String get_cAdressInfo() throws myException
			{
				return "<keine Adressinfo>";
			}

			@Override
			public MyString get_cComponentText() throws myException
			{
				return this.cStringForComponent;
			}

			@Override
			public String get_cID_ADRESSE_EMPFAENGER() throws myException
			{
				return null;
			}
			
		}

	
	
	
	}
	
}
