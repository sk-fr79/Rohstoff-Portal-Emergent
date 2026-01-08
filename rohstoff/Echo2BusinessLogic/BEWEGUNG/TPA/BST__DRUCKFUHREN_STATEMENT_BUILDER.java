package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.E2_JasperHASH;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class BST__DRUCKFUHREN_STATEMENT_BUILDER 
{
	
	
	/**
	 * @param cID_VPOS_TPA_FUHRE
	 * @param cBeleg
	 * @return
	 * @throws myException
	 */
	public static String CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE(String cID_VPOS_TPA_FUHRE, String cBeleg, boolean bWasMail, E2_JasperHASH oJasperHash) throws myException
	{
		String cRueck = "";
		String[][] cInfo = bibDB.EinzelAbfrageInArray("SELECT NVL(ZEITSTEMPEL,'--') FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE);
		
		if (cInfo==null || cInfo.length!=1)
			throw new myException("Error in CREATE_FUHRE_DRUCKTABLE_ROW");
		
		//aenderung 2010-11-17: erstellen des HASH-Werts fuer die Druckarchiv-Tabelle
		//aenderung 2010-11-17: fuer die archivierungsfunktion werden archivmedien - eintraege geschrieben (falls gewuenscht)
		Vector<String> vSeqs = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VPOS_TPA_FUHRE_DRUCK", 1);
		oJasperHash.add_HASH_ID_DRUCKTABLE(vSeqs.get(0));

		
		
		MySqlStatementBuilder oInsert = new MySqlStatementBuilder();
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE_DRUCK", vSeqs.get(0), false);
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE",		cID_VPOS_TPA_FUHRE, false);
		oInsert.addSQL_Paar("KUERZEL", bibALL.get_KUERZEL(), true);
		oInsert.addSQL_Paar("ZEITSTEMPEL", cInfo[0][0], true);
		oInsert.addSQL_Paar("DRUCKDATUM", "SYSDATE", false);
		oInsert.addSQL_Paar("BELEG", cBeleg, true);
		oInsert.addSQL_Paar("MAIL", (bWasMail?"Y":"N"), true);

		cRueck = oInsert.get_CompleteInsertString("JT_VPOS_TPA_FUHRE_DRUCK", bibE2.cTO());
		
		
		return cRueck;
		
	}
	

	
	/**
	 * @param cID_VPOS_TPA_FUHRE
	 * @param cBeleg
	 * @return
	 * @throws myException
	 */
	public static String CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE(String cID_VPOS_TPA_FUHRE, String cBeleg, String BelegZusatz, boolean bWasMail, E2_JasperHASH oJasperHash) throws myException
	{
		String cRueck = "";
		String[][] cInfo = bibDB.EinzelAbfrageInArray("SELECT NVL(ZEITSTEMPEL,'--') FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE);
		
		if (cInfo==null || cInfo.length!=1)
			throw new myException("Error in CREATE_FUHRE_DRUCKTABLE_ROW");
		
		//aenderung 2010-11-17: erstellen des HASH-Werts fuer die Druckarchiv-Tabelle
		//aenderung 2010-11-17: fuer die archivierungsfunktion werden archivmedien - eintraege geschrieben (falls gewuenscht)
		Vector<String> vSeqs = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VPOS_TPA_FUHRE_DRUCK", 1);
		oJasperHash.add_HASH_ID_DRUCKTABLE(vSeqs.get(0));

		
		
		MySqlStatementBuilder oInsert = new MySqlStatementBuilder();
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE_DRUCK", vSeqs.get(0), false);
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE",		cID_VPOS_TPA_FUHRE, false);
		oInsert.addSQL_Paar("KUERZEL", bibALL.get_KUERZEL(), true);
		oInsert.addSQL_Paar("ZEITSTEMPEL", cInfo[0][0], true);
		oInsert.addSQL_Paar("DRUCKDATUM", "SYSDATE", false);
		oInsert.addSQL_Paar("BELEG", cBeleg, true);
		oInsert.addSQL_Paar(_DB.VPOS_TPA_FUHRE_DRUCK$BELEGINFO, BelegZusatz, true);
		oInsert.addSQL_Paar("MAIL", (bWasMail?"Y":"N"), true);

		cRueck = oInsert.get_CompleteInsertString("JT_VPOS_TPA_FUHRE_DRUCK", bibE2.cTO());
		
		
		return cRueck;
		
	}
	


	
	
	
	
	/**
	 * @param cID_VPOS_TPA_FUHRE
	 * @param cBeleg
	 * @return
	 * @throws myException
	 */
	public static String CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE_ORT(String cID_VPOS_TPA_FUHRE, String cID_VPOS_TPA_FUHRE_ORT, String cBeleg, boolean bWasMail, E2_JasperHASH oJasperHash) throws myException
	{
		String cRueck = "";
		String[][] cInfo = bibDB.EinzelAbfrageInArray("SELECT NVL(ZEITSTEMPEL,'') FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE);
		
		if (cInfo==null || cInfo.length!=1)
			throw new myException("Error in CREATE_FUHRE_DRUCKTABLE_ROW");
		
		//aenderung 2010-11-17: erstellen des HASH-Werts fuer die Druckarchiv-Tabelle
		//aenderung 2010-11-17: fuer die archivierungsfunktion werden archivmedien - eintraege geschrieben (falls gewuenscht)
		Vector<String> vSeqs = bibALL.get_SEQUENCE_VECT_VALUES("SEQ_VPOS_TPA_FUHRE_DRUCK", 1);
		oJasperHash.add_HASH_ID_DRUCKTABLE(vSeqs.get(0));

		
		
		MySqlStatementBuilder oInsert = new MySqlStatementBuilder();
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE_DRUCK", 	vSeqs.get(0), false);
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE",		cID_VPOS_TPA_FUHRE, false);
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE_ORT",	cID_VPOS_TPA_FUHRE_ORT, false);
		oInsert.addSQL_Paar("KUERZEL", bibALL.get_KUERZEL(), true);
		oInsert.addSQL_Paar("ZEITSTEMPEL", cInfo[0][0], true);
		oInsert.addSQL_Paar("DRUCKDATUM", "SYSDATE", false);
		oInsert.addSQL_Paar("BELEG", cBeleg, true);
		oInsert.addSQL_Paar("MAIL", (bWasMail?"Y":"N"), true);

		cRueck = oInsert.get_CompleteInsertString("JT_VPOS_TPA_FUHRE_DRUCK", bibE2.cTO());
		
		
		return cRueck;
		
	}
	


	
	
	
	/**
	 * @param cID_VPOS_TPA
	 * @param cBeleg
	 * @return
	 * @throws myException
	 */
	public static String CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA(String cID_VPOS_TPA, String cBeleg, boolean bWasMail) throws myException
	{
		String cRueck = "";
		String[][] cInfo = bibDB.EinzelAbfrageInArray("SELECT NVL(ZEITSTEMPEL,''), ID_VPOS_TPA_FUHRE FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE WHERE ID_VPOS_TPA="+cID_VPOS_TPA);
		
		if (cInfo==null || cInfo.length!=1)
			throw new myException("Error in CREATE_FUHRE_DRUCKTABLE_ROW");
		
		MySqlStatementBuilder oInsert = new MySqlStatementBuilder();
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE_DRUCK", "SEQ_VPOS_TPA_FUHRE_DRUCK.NEXTVAL", false);
		oInsert.addSQL_Paar("ID_VPOS_TPA_FUHRE",		cInfo[0][1], false);
		oInsert.addSQL_Paar("KUERZEL", bibALL.get_KUERZEL(), true);
		oInsert.addSQL_Paar("ZEITSTEMPEL", cInfo[0][0], true);
		oInsert.addSQL_Paar("DRUCKDATUM", "SYSDATE", false);
		oInsert.addSQL_Paar("BELEG", cBeleg, true);
		oInsert.addSQL_Paar("MAIL", (bWasMail?"Y":"N"), true);

		cRueck = oInsert.get_CompleteInsertString("JT_VPOS_TPA_FUHRE_DRUCK", bibE2.cTO());
		
		
		return cRueck;
		
	}

	

	/**
	 * @param cID_VPOS_TPA
	 * @return
	 */
	public static String CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA(String cID_VPOS_TPA)
	{
		String cRueck = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET BUCHUNGSNR_FUHRE=TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+
						myCONST.VORGANGSART_TRANSPORT_ZUSATZ_FUHRE+".NEXTVAL) WHERE BUCHUNGSNR_FUHRE IS NULL AND ID_VPOS_TPA="+cID_VPOS_TPA;
		
		
		return cRueck;
		
	}

	
	/**
	 * @param cID_VPOS_TPA_FUHRE
	 * @return
	 */
	public static String CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA_FUHRE(String cID_VPOS_TPA_FUHRE)
	{
		String cRueck = "UPDATE "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE SET BUCHUNGSNR_FUHRE=TO_CHAR(SEQ_"+bibALL.get_ID_MANDANT()+"_"+
						myCONST.VORGANGSART_TRANSPORT_ZUSATZ_FUHRE+".NEXTVAL) WHERE BUCHUNGSNR_FUHRE IS NULL AND ID_VPOS_TPA_FUHRE="+cID_VPOS_TPA_FUHRE;
		
		
		return cRueck;
		
	}

	
	
	/**
	 * @param cID_VPOS_TPA_FUHRE
	 * @return  
	 * zaehlt innerhalb der gleichen  Fuhre bei jedem ort um eines weiter (begin bei 1)
	 */
	public static String CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA_FUHRE_ORT(String cID_VPOS_TPA_FUHRE_ORT)
	{
		String cRueck = "UPDATE JT_VPOS_TPA_FUHRE_ORT SET "+ 
							"BUCHUNGSNUMMER_ZUSATZ= "+
							"(SELECT NVL(MAX(NVL(BUCHUNGSNUMMER_ZUSATZ,0)),0)+1 FROM JT_VPOS_TPA_FUHRE_ORT B WHERE B.ID_VPOS_TPA_FUHRE= JT_VPOS_TPA_FUHRE_ORT.ID_VPOS_TPA_FUHRE) "+
							"WHERE NVL(BUCHUNGSNUMMER_ZUSATZ,'-')='-' AND ID_VPOS_TPA_FUHRE_ORT= "+cID_VPOS_TPA_FUHRE_ORT;
		
		
		return cRueck;
		
	}

	
//	/**
//	 * 
//	 * @param cID_VPOS_TPA_FUHRE
//	 * @param cBeleg
//	 * @param bWasMail
//	 * @return
//	 * @throws myException
//	 */
//	public static Vector<String> CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE(	String cID_VPOS_TPA_FUHRE, 
//																						String cBeleg, 
//																						boolean bWasMail, 
//																						E2_JasperHASH  oJasperHash) throws myException
//	{
//		Vector<String> vRueck = new Vector<String>();
//		vRueck.add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE));
//		vRueck.add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE, cBeleg, bWasMail, oJasperHash));
//		
//		return vRueck;
//	}
// 
	
	
	
	
	/**
	 * ab 2014-09-23: void-methode, die direkt in die E2_JasperHASH schreibt
	 * @param cID_VPOS_TPA_FUHRE
	 * @param cBeleg
	 * @param bWasMail
	 * @return
	 * @throws myException
	 */
	public static void CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE(	String cID_VPOS_TPA_FUHRE, 
																				String cBeleg, 
																				boolean bWasMail, 
																				E2_JasperHASH  oJasperHash) throws myException	{
		oJasperHash.get_vSQL_STATEMENTS_BEFORE_REPORT().add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE));
		oJasperHash.get_vSQL_STATEMENTS_AFTER_REPORT().add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE(cID_VPOS_TPA_FUHRE, cBeleg, bWasMail, oJasperHash));
	}
 
	

//	/**
//	 * 
//	 * @param cID_VPOS_TPA_FUHRE
//	 * @param cID_VPOS_TPA_FUHRE_ORT
//	 * @param cBeleg
//	 * @param bWasMail
//	 * @return
//	 * @throws myException
//	 */
//	public static Vector<String> CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE_ORT(	String cID_VPOS_TPA_FUHRE, 
//																							String cID_VPOS_TPA_FUHRE_ORT, 
//																							String cBeleg, 
//																							boolean bWasMail, 
//																							E2_JasperHASH  oJasperHash) throws myException
//	{
//		Vector<String> vRueck = new Vector<String>();
//		vRueck.add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT));
//		vRueck.add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE,cID_VPOS_TPA_FUHRE_ORT, cBeleg, bWasMail, oJasperHash));
//		
//		return vRueck;
//	}
// 
	
	/**
	 *  ab 2014-09-23: void-methode, die direkt in die E2_JasperHASH schreibt
	 * @param cID_VPOS_TPA_FUHRE
	 * @param cID_VPOS_TPA_FUHRE_ORT
	 * @param cBeleg
	 * @param bWasMail
	 * @return
	 * @throws myException
	 */
	public static void CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA_FUHRE_ORT(	String cID_VPOS_TPA_FUHRE, 
																					String cID_VPOS_TPA_FUHRE_ORT, 
																					String cBeleg, 
																					boolean bWasMail, 
																					E2_JasperHASH  oJasperHash) throws myException 	{
		oJasperHash.get_vSQL_STATEMENTS_BEFORE_REPORT().add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE_ORT));
		oJasperHash.get_vSQL_STATEMENTS_BEFORE_REPORT().add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA_FUHRE_ORT(cID_VPOS_TPA_FUHRE,cID_VPOS_TPA_FUHRE_ORT, cBeleg, bWasMail, oJasperHash));
	}
 
	
	
	
	
//	public static Vector<String> CREATE_BEFORE_PRINT_STATEMENTS_FROM_ID_VPOS_TPA(String cID_VPOS_TPA, String cBeleg, boolean bWasMail) throws myException
//	{
//		Vector<String> vRueck = new Vector<String>();
//		vRueck.add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_BUCHNUNGSNR_STATEMENT_FROM_ID_VPOS_TPA(cID_VPOS_TPA));
//		vRueck.add(BST__DRUCKFUHREN_STATEMENT_BUILDER.CREATE_FUHRE_DRUCKTABLE_ROW_FROM_ID_VPOS_TPA(cID_VPOS_TPA, cBeleg,bWasMail));
//		
//		return vRueck;
//	}
// 

	
}
