package rohstoff.Echo2BusinessLogic.BEWEGUNG.ABNAHMEANGEBOTELISTE;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowNothing;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VKOPF_STD_extend;
import rohstoff.utils.MAILCOLLECTORS.MAILFillerAbnahmeAngebot;

public class BSAAL_ButtonBauePDFs_JasperHASH extends E2_JasperHASH
{

	private RECORD_VKOPF_STD_extend REC_VKOPF_STD = null;
	
	public BSAAL_ButtonBauePDFs_JasperHASH(RECORD_VKOPF_STD_extend  recVKOPF_STD, String cMonat, String cJahr) throws myException
	{
		super("angebot", new JasperFileDef_PDF());
		this.REC_VKOPF_STD = recVKOPF_STD;
		
		this.put("id_vkopf_std", this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF_NN(""));
		this.put("mit_summenblock", "N");
		this.get_vID_ADRESSE_FOR_MailLoad().add(this.REC_VKOPF_STD.get_ID_ADRESSE_cUF_NN(""));
		
		
		Vector<String>  vSQL_Before = new Vector<String>();
		
		vSQL_Before.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_STD SET DRUCKDATUM=SYSDATE WHERE DRUCKDATUM IS NULL AND ID_VKOPF_STD="+this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF());
		vSQL_Before.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_STD SET BUCHUNGSNUMMER=TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+myCONST.VORGANGSART_ABNAHMEANGEBOT+".NEXTVAL) WHERE BUCHUNGSNUMMER IS NULL AND ID_VKOPF_STD="+this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF());
		
		 // info in druckprotokoll
		String cAnzahl = bibDB.EinzelAbfrage("select count(*) from "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VKOPF_STD="+this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF());
		String cGesamtpreis = bibDB.EinzelAbfrage("SELECT SUM(  NVL(GESAMTPREIS,0)) FROM  "+bibE2.cTO()+".JT_VPOS_STD WHERE ID_VKOPF_STD="+this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF());
			
		vSQL_Before.add("INSERT INTO "+bibE2.cTO()+".JT_VKOPF_STD_DRUCK (ID_VKOPF_STD_DRUCK,ID_VKOPF_STD,POSITIONEN,GESAMT_NETTO,DRUCKDATUM)" +
																"  VALUES (SEQ_VKOPF_STD_DRUCK.NEXTVAL,"+this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF()+"," +cAnzahl+","+cGesamtpreis+",SYSDATE)");

		this.set_vSQL_STATEMENTS_BEFORE_REPORT(vSQL_Before);
		this.set_vSQL_STATEMENTS_AFTER_REPORT(bibALL.get_Vector("UPDATE "+bibE2.cTO()+".JT_VKOPF_STD SET ABGESCHLOSSEN='Y' WHERE NVL(ABGESCHLOSSEN,'N')='N' AND ID_VKOPF_STD="+this.REC_VKOPF_STD.get_ID_VKOPF_STD_cUF()));
		
		this.set_cDownloadAndSendeNameStaticPart("ABNAHMEANGEBOT_"+cMonat+"_"+cJahr+"_ADRESS_"+this.REC_VKOPF_STD.get_ID_ADRESSE_cUF());
	}
	
	
	public RECORD_VKOPF_STD_extend get_REC_VKOPF_STD()
	{
		return REC_VKOPF_STD;
	}
	


	@Override
	protected MailBlock create_MailBlock() throws myException
	{
		ownMailBlock  oMailBlock = new ownMailBlock();
		new MAILFillerAbnahmeAngebot().fill_MAILAdresses_to_MailBLOCK(oMailBlock, this, true);
		
		return oMailBlock;
	}

	@Override
	public void doActionAfterCreatedFile() throws myException
	{
	}

	@Override
	public boolean get_bIsDesignedForMail() throws myException
	{
		return true;
	}

	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() throws myException
		{
			super(new MailSecurityPolicyAllowNothing());
			
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException {return null;}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException 	{return null;}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException	{return null;}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException	{return null;}

		@Override
		public Component get_ComponentForMailerList() throws myException
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll());
		}

		@Override
		public MyString get_ComponentTextForProtokoll() throws myException
		{
			return new MyE2_String("Abnahmeangebot");
		}

		@Override
		public String get_cBelegTyp() throws myException
		{
			return myCONST.VORGANGSART_ABNAHMEANGEBOT;
		}

		@Override
		public MyString get_cBelegTyp4User() throws myException
		{
			return new MyE2_String(myCONST.VORGANGSART_ABNAHMEANGEBOT_FOR_USER);
		}

		@Override
		public MyString get_cKommentar() throws myException
		{
			return new MyE2_String("<kein Kommentar>");
		}

		@Override
		public String get_cModulInfo() throws myException
		{
			return "AUTOMATISCHER ABNAHMEANGEBOTSVERSAND";
		}
	}


}
