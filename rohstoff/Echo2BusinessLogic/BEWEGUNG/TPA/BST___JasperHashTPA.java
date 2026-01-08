package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock_STD_MitEditPolicy;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef_PDF;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL.Jasper_Exe_WriteEMailToPrintProtokoll;
import rohstoff.utils.MAILCOLLECTORS.MAILFillerTPA;

public class BST___JasperHashTPA extends E2_JasperHASH
{
	private String cModulName = null;
	

	public BST___JasperHashTPA(String ModulName, String cID_VKOPF_TPA, boolean bMail_Is_Yes_In_PrintProtokoll) 	throws myException
	{
		super("transportauftrag", new JasperFileDef_PDF());
		
		this.cModulName = ModulName;
		
		
		RECORD_VKOPF_TPA recVKOPF = new RECORD_VKOPF_TPA(cID_VKOPF_TPA);
		
		this.get_vID_ADRESSE_FOR_MailLoad().removeAllElements();
		this.get_vID_ADRESSE_FOR_MailLoad().add(recVKOPF.get_ID_ADRESSE_cUF());

		
		Vector<String>  vSQLBefore = new Vector<String>();
		Vector<String>  vSQLAfterError = new Vector<String>();
		
		vSQLBefore.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA SET DRUCKDATUM=SYSDATE WHERE DRUCKDATUM IS NULL AND ID_VKOPF_TPA="+cID_VKOPF_TPA);
		vSQLBefore.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA SET BUCHUNGSNUMMER=TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_TRANSPORT.NEXTVAL) " +
												" WHERE BUCHUNGSNUMMER IS NULL AND ID_VKOPF_TPA="+cID_VKOPF_TPA);
		//dummystatement fuer alle positionen einfuegen, damit die abzuege nochmal durchgerechnet werden
		vSQLBefore.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+"UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA SET LETZTE_AENDERUNG=LETZTE_AENDERUNG WHERE ID_VKOPF_TPA="+cID_VKOPF_TPA);

		
		String cAnzahl = bibDB.EinzelAbfrage("select count(*) from "+bibE2.cTO()+".JT_VPOS_TPA WHERE NVL(DELETED,'N')='N' AND ID_VKOPF_TPA="+cID_VKOPF_TPA);
		String cGesamtpreis = bibDB.EinzelAbfrage("SELECT SUM(  NVL(GESAMTPREIS,0)) FROM  "+bibE2.cTO()+".JT_VPOS_TPA " +
													" WHERE  NVL(DELETED,'N')='N' AND " +
													" POSITION_TYP="+bibALL.MakeSql(myCONST.VG_POSITION_TYP_ARTIKEL)+" AND " +
													" ID_VKOPF_TPA ="+cID_VKOPF_TPA);
					
		
		//2010-09-23: fuer die archivierungsfunktion werden archivmedien - eintraege geschrieben (falls gewuenscht)
		Vector<String> vSeqs = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VKOPF_TPA_DRUCK", 1);
		
//		//2010-11-17: die archiveintraege werden vor dem druck geschrieben, damit eine archivierung moeglich ist,
//		//            und der benutzer wird ins archiv geschrieben
//		vSQL_Pre.add("INSERT INTO "+bibE2.cTO()+"."+cKTable+"_DRUCK ("+cKID+"_DRUCK,"+cKID+",POSITIONEN,GESAMT_NETTO,DRUCKDATUM,KUERZEL)" +
//																"  VALUES (" +vSeqs.get(0)+ ","+ID_KOPF+"," +cAnzahl+","+cGesamtpreis+
//																",SYSDATE,"+bibALL.get_RECORD_USER().get_KUERZEL_VALUE_FOR_SQLSTATEMENT()+")");

		
		vSQLBefore.add("INSERT INTO "+bibE2.cTO()+".JT_VKOPF_TPA_DRUCK (ID_VKOPF_TPA_DRUCK,ID_VKOPF_TPA,POSITIONEN,GESAMT_NETTO,DRUCKDATUM,KUERZEL)" +
																		"  VALUES ("+vSeqs.get(0)+","+cID_VKOPF_TPA+"," +cAnzahl+","+cGesamtpreis+
																		",SYSDATE,"+bibALL.get_RECORD_USER().get_KUERZEL_VALUE_FOR_SQLSTATEMENT()+")");
				
		this.add_HASH_ID_DRUCKTABLE(vSeqs.get(0));
		
		vSQLBefore.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA SET ABGESCHLOSSEN='Y' WHERE ID_VKOPF_TPA="+cID_VKOPF_TPA);

		
		this.set_vSQL_STATEMENTS_BEFORE_REPORT(vSQLBefore);

		
		
		if (recVKOPF.is_ABGESCHLOSSEN_NO())
		{
			vSQLAfterError.add("UPDATE "+bibE2.cTO()+".JT_VKOPF_TPA SET ABGESCHLOSSEN='N' " +
											" WHERE ID_VKOPF_TPA="+cID_VKOPF_TPA);
		}
		
		this.set_vSQL_STATEMENTS_AFTER_REPORT_ERROR(vSQLAfterError);

		this.set_cDownloadAndSendeNameStaticPart("transportauftrag");
		this.set_cSQL_QUERY_FOR_DOWNLOAD_AND_SENDENAME_DYNAMIC_PART("SELECT NVL(BUCHUNGSNUMMER,'-') FROM "+bibE2.cTO()+".JT_VKOPF_TPA WHERE ID_VKOPF_TPA="+cID_VKOPF_TPA);
		
		this.put("belegtyp", myCONST.VORGANGSART_TRANSPORT);
		this.put("id_vkopf_tpa",cID_VKOPF_TPA);
		
		
		//einen executer fuer das schreiben der mailadressen zu den printlog-dateien
		this.get_vExecuters().add(new Jasper_Exe_WriteEMailToPrintProtokoll(_DB.VKOPF_TPA_DRUCK,
																			_DB.VKOPF_TPA_DRUCK_EM,
																			_DB.VKOPF_TPA_DRUCK_EM$ID_VKOPF_TPA_DRUCK,
																			_DB.VKOPF_TPA_DRUCK_EM$EMAIL_SEND,
																			_DB.VKOPF_TPA_DRUCK_EM$EMAIL_RECEIVE));

	}

	

	
	@Override
	public MailBlock create_MailBlock() throws myException
	{
		return new ownMailBlock();
	}
	
	
	private class ownMailBlock extends MailBlock
	{

		public ownMailBlock() throws myException
		{
			//super(new MailSecurityPolicyAllowNothing_but_EditAdress());
			// 2013-06-24: neue policy: freie adresseingabe
			super(new MailSecurityPolicyAllowSearchMitarbeiter_and_Insert_free_Adress_WithEditAllowed());
			new MAILFillerTPA().fill_MAILAdresses_to_MailBLOCK(this, BST___JasperHashTPA.this, true);
			
		}

		@Override
		public MailAdress4MailBlock build_MailAdress4MailBlock(String mailAdresse, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
//		{
//			return null;
//		}
//
//		@Override
//		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
//		{
//			return null;
//		}
//
		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_SearchedMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return null;
		}
		

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_EmptyMail(MailBlock OWN_MailBlock) throws myException
		{
			return new MailAdress4MailBlock_STD_MitEditPolicy("",OWN_MailBlock,new MyE2_String("Freie eMail"));
		}

		@Override
		protected MailAdress4MailBlock build_MailAdress4MailBlock4Added_MitarbeiterMail(String mailAdress, MailBlock OWN_MailBlock) throws myException
		{
			return new MailAdress4MailBlock_STD_MitEditPolicy(mailAdress,OWN_MailBlock,new MyE2_String("Mitarb.-eMail"));
		}


		

		@Override
		public Component get_ComponentForMailerList()
		{
			return new MyE2_Label(this.get_ComponentTextForProtokoll(),MyE2_Label.STYLE_SMALL_BOLD());
		}

		@Override
		public MyString get_ComponentTextForProtokoll()
		{
			return new MyE2_String("Transportauftrag");
		}

		@Override
		public String get_cBelegTyp()
		{
			return "TRANSPORTAUFTRAG";
		}

		@Override
		public MyString get_cBelegTyp4User()
		{
			return new MyE2_String("Transportauftrag");
		}

		@Override
		public MyString get_cKommentar()
		{
			return new MyE2_String("Mailversendung eines Transportauftrag");
		}

		@Override
		public String get_cModulInfo()
		{
			return BST___JasperHashTPA.this.cModulName;
		}
		
	}
	
	


	@Override
	public boolean get_bIsDesignedForMail()
	{
		return true;
	}
	
	
	@Override
	public void doActionAfterCreatedFile() throws myException
	{
	}
	
	

}
