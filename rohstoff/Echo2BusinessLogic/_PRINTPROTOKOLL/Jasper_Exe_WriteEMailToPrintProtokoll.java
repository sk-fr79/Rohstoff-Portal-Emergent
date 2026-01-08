package rohstoff.Echo2BusinessLogic._PRINTPROTOKOLL;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_CONST.EXECUTER_JUMPPOINTS;
import panter.gmbh.Echo2.MAIL_AND_REPORT.EXECUTER.Jasper_Exe_ROOT;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailAdress4MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_NEW;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorString;

public class Jasper_Exe_WriteEMailToPrintProtokoll extends Jasper_Exe_ROOT {
	
	private String c_NAME_ID_PrintProtokollTable = null;
	private String c_TableName_PrintProtokollTable = null;
	private String c_TableNameProtokollTableEmail = null;
	private String c_FieldNameSendAdress = null;
	private String c_FieldNameReceiveAdress = null;

	/**
	 *  
	 * @param cTableName_PrintProtokollTable
	 * @param cTableNameProtokollTableEmail
	 * @param cName_ID_PrintProtokollTable
	 * @param cFieldNameSendAdress
	 * @param cFieldNameReceiveAdresse
	 */
	public Jasper_Exe_WriteEMailToPrintProtokoll(	String cTableName_PrintProtokollTable,
													String cTableNameProtokollTableEmail, 
													String cName_ID_PrintProtokollTable, 
													String cFieldNameSendAdress, 
													String cFieldNameReceiveAdresse) {
		super();
		this.c_TableName_PrintProtokollTable = cTableName_PrintProtokollTable;
		this.c_TableNameProtokollTableEmail = cTableNameProtokollTableEmail;
		this.c_NAME_ID_PrintProtokollTable = cName_ID_PrintProtokollTable;
		this.c_FieldNameSendAdress = cFieldNameSendAdress;
		this.c_FieldNameReceiveAdress = cFieldNameReceiveAdresse;
	}

	@Override
	public void EXECUTE(E2_JasperHASH oJasperHash, Object objZusatz, MyE2_MessageVector oMV, Object oObjectRueckgabe) throws myException {
		
		//zusatzobjekt ist hier ein MailBlock - Objekt,
		//rueckgabe-object ist VectorString
		
		if (objZusatz instanceof MailBlock && oObjectRueckgabe instanceof VectorString) {
			MailBlock  		oMailBlock = 	(MailBlock)objZusatz;
			VectorString    vSQL = 			(VectorString)oObjectRueckgabe;
			
			if (oMailBlock.get_bWasSendEvenOneTimes() && oMailBlock.get_v_MailAdress4MailBlock()!=null) {
				for (MailAdress4MailBlock oMailAdress: oMailBlock.get_v_MailAdress4MailBlock()) {
					if (oMailAdress.get_bLastSendingWasSuccessfull()) {
						VectorString  vIDsDruckTable = oJasperHash.get_HASH_ID_DRUCKTABLE();
						
						for (String cID_DruckTable: vIDsDruckTable) {
							
							MyRECORD_NEW recNewMail = new MyRECORD_NEW(c_TableNameProtokollTableEmail);
							recNewMail.set_NewValueForDatabase(c_FieldNameReceiveAdress, oMailAdress.get_eMailAdresseZiel());
							recNewMail.set_NewValueForDatabase(c_FieldNameSendAdress, S.NN(oMailAdress.get_cLastSendAdresse(),"<sender unknown>"));
							recNewMail.set_NewValueForDatabase(c_NAME_ID_PrintProtokollTable, cID_DruckTable);
							
							vSQL.add(recNewMail.get__StatementBuilder(true, true).get_CompleteInsertString(c_TableNameProtokollTableEmail, bibE2.cTO()));
							
							//jetzt auf jeden fall den eMailSchalter in der Protokolltabelle noch auf Y switchen (heiﬂt in allen drucktabellen MAIL)
							//dieser wird nicht in allen variante bereits gesetzt
							vSQL.add("UPDATE "+bibE2.cTO()+"."+this.c_TableName_PrintProtokollTable+" SET MAIL='Y' WHERE "+c_NAME_ID_PrintProtokollTable+"="+cID_DruckTable);
							
						}
					}
				}
			}
		}
	}

	@Override
	public EXECUTER_JUMPPOINTS get_JUMPMarker() {
		return Jasper_Exe_CONST.EXECUTER_JUMPPOINTS.JUMPPOINT_AFTER_PRECESSING_MAILBLOCK_IN_MASSMAILER_SEND_METHOD;
	}
	
	
}
