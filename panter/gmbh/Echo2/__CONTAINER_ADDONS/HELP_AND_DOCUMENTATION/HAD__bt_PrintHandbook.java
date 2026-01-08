package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.MAIL_AND_REPORT.BUTTON_MAIL_AND_REPORT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowAll;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class HAD__bt_PrintHandbook extends BUTTON_MAIL_AND_REPORT {

	private HAD__GridWithData dataGridThisBelongsTo = null;
	
	public HAD__bt_PrintHandbook(HAD__GridWithData p_dataGridThisBelongsTo) {
		super("printer.png",new MyE2_String("Handbuch-Druck"),null,null,null);
		
		this.dataGridThisBelongsTo=p_dataGridThisBelongsTo;
		
		this.add_GlobalValidator(new ownValidatorValidNoEmptySelection());
		
		this.setToolTipText(new MyE2_String("Drucken der momentan gewählten Hilfetexte").CTrans());
	}

	@Override
	public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException {
		V_JasperHASH  vRueck = new V_JasperHASH();
		vRueck.add(new ownJASPER_HASH());
		return vRueck;
	}

	private Vector<String> get_vIDs_Hilfetext() throws myException {
		Vector<String> v_rueck = new Vector<String>();
		Vector<String> v_alle = new Vector<String>();
		for (HAD__CheckBox cb: this.dataGridThisBelongsTo.get_v_cbs()) {
			v_alle.add(cb.get_RecHilfeText().get_ID_HILFETEXT_cUF());
			if (cb.isSelected()) {
				v_rueck.add(cb.get_RecHilfeText().get_ID_HILFETEXT_cUF());
			}
		}
		if (v_rueck.size()==0) {   //wenn nichts gewaehlt wurde, dann alles
			v_rueck.addAll(v_alle);
		}
		return v_rueck;
	}
	
	
	@Override
	public E2_MassMailer get_MassMailer() throws myException {
		return new E2_MassMailer_STD(	HAD___CONST.MAIL_KLEMMBRETT_BETREFF_HANDBUCHDRUCK,
										HAD___CONST.MAIL_KLEMMBRETT_MAILTEXT_HANDBUCHDRUCK,
										"handbook");
	}

	
	
	
	private class ownValidatorValidNoEmptySelection extends XX_ActionValidator {

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (HAD__bt_PrintHandbook.this.get_vIDs_Hilfetext().size()==0) {
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es gibt nichts zu Drucken !")));
			}
			
			return oMV;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			return null;
		}
		
	}

	
	
	
	private class ownJASPER_HASH extends E2_JasperHASH {

		public ownJASPER_HASH() throws myException {
			super("handbuch.jasper", new JasperFileDef_PDF());
			
			//jetzt die spezifischen felder fuer den report uebergeben
			String cWhere = HILFETEXT.id_hilfetext.fn()+" IN ("+
			bibALL.Concatenate(HAD__bt_PrintHandbook.this.get_vIDs_Hilfetext(), ",","-1")+")";
			
			this.put(HAD___CONST.parameter_handbuchdruck.WHEREBLOCK.name(), cWhere);
			this.put(HAD___CONST.parameter_handbuchdruck.HASH_TYP.name(), HAD___CONST.get_hm_ticketTyp());
			this.put(HAD___CONST.parameter_handbuchdruck.HASH_STATUS.name(), HAD___CONST.get_hm_status());
			this.put(HAD___CONST.parameter_handbuchdruck.HASH_MODULE.name(), E2_MODULNAME_ENUM.get_hm_MODULNAMES());
			this.put(HAD___CONST.parameter_handbuchdruck.ORDERBLOCK.name(), 
																HAD__bt_PrintHandbook.this.dataGridThisBelongsTo
																					.get_GridBedienPanel()
																					.get_selSorter()
																					.get_SortString());
		}
		
		
		@Override
		protected MailBlock create_MailBlock() throws myException {
			return new ownMailBlock();
		}

		@Override
		public boolean get_bIsDesignedForMail() throws myException {
			return true;
		}

		@Override
		public void doActionAfterCreatedFile() throws myException {
		}
		
		
		private class ownMailBlock extends MailBlock 	{
			public ownMailBlock() throws myException {
				super(new MailSecurityPolicyAllowAll());
				this.ADD_NewTargetAdress("");
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException {
				return new ownMailAdress4Mailblock(mailAdresse,OWN_MailBlock,new MyE2_String("Frei eingegebene MailAdresse"));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock("",OWN_MailBlock,new MyE2_String("Frei eingegebene MailAdresse"));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock(mailAdress,OWN_MailBlock,new MyE2_String("Mitarbeiter-MailAdresse"));
			}

			@Override
			protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
			{
				return new ownMailAdress4Mailblock(mailAdress,OWN_MailBlock,new MyE2_String("Firmen-MailAdresse"));
			}

			@Override
			public Component get_ComponentForMailerList() throws myException
			{
				return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
			}

			@Override
			public MyString get_ComponentTextForProtokoll() throws myException
			{
				return new MyE2_String("EU-Vertrag-Anschreiben");
			}

			@Override
			public String get_cBelegTyp() throws myException
			{
				return "EU-VERTRAG";
			}

			@Override
			public MyString get_cBelegTyp4User() throws myException
			{
				return new MyE2_String("EU-VERTRAG");
			}

			@Override
			public MyString get_cKommentar() throws myException
			{
				return new MyE2_String("Mail mit EU-VERTRAG");
			}

			@Override
			public String get_cModulInfo() throws myException
			{
				return E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST;
			}
		}
		
		
		private class ownMailAdress4Mailblock extends MailAdress4MailBlock_STD {
			public ownMailAdress4Mailblock(String mailAdress, MailBlock MailBlockThisBelongsTo, MyString Info) throws myException {
				super(mailAdress, MailBlockThisBelongsTo, Info);
			}
		}
		
	}
	
	
	


}
