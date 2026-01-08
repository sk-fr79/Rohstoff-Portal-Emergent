package rohstoff.utils.SQL_DAEMONS;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG_ABZUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.dataTools.MyDataRecordHashList;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ABZUEGE.BL_AbzugsKalkulator;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PreisCalculator;

public class ENDPREIS_CALC_Daemon extends XX_SQL_STACK_DAEMON {

	
	
	/*
	 * ein DBToolBox-validator, der kontrakte ueberwacht, wenn in einem speichervorgang ein SQL-statement (INSERT oder UPDATE)
	 * vorkommt, in dem ein speichervorgang einer tabelle aus den folgenden vorkommt: 
	 * JT_VPOS_RG
	 * Er macht eine Endpreiskalkulation und eine abzugskalkulation 
	 */

	
	public ENDPREIS_CALC_Daemon() throws myException 
	{
		super();
	}


	
	// wird unmittelbar nach jedem einzelnen sql-statement ausgefuehrt
	public MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> vLogInfos, MyConnection oConn) throws myException 
	{
		//die betroffenen tabellen-LogInfos sammeln
		for (MyDBToolBox_LOG_INFO test: vLogInfos)
		{
			
			if (    (test.get_cTABLENAME().equals("JT_VPOS_RG") ||
					test.get_cTABLENAME().equals("JT_VPOS_RG_ABZUG") ||
					test.get_cTABLENAME().equals("JT_VPOS_KON") || 
					test.get_cTABLENAME().equals("JT_VPOS_TPA") ||
					test.get_cTABLENAME().equals("JT_VPOS_STD"))
					&& !test.get_IS_DELETE()) //nur insert und update werden beruecksichtigt !
			{
				this.get_vLogInfos().add(test);
			}
		}
		return new MyE2_MessageVector();
	}


	
	public MyE2_MessageVector doValidate(MyConnection oConn) throws myException 
	{
		MyE2_MessageVector vErrorRueck = new MyE2_MessageVector();
		return vErrorRueck;
	}
	
	
	

	public Vector<String> getTriggerStatementsAfterSQL(MyConnection oConn, MyE2_MessageVector oMV) throws myException 
	{

		Vector<String> vSQL_Rueck 		= new Vector<String>();

		
		
		for (MyDBToolBox_LOG_INFO test: this.get_vLogInfos())
		{
			String cVPOS_TYP = "";
			if (test.get_cTABLENAME().toUpperCase().equals("JT_VPOS_STD"))  cVPOS_TYP = "STD";
			if (test.get_cTABLENAME().toUpperCase().equals("JT_VPOS_RG")) 	cVPOS_TYP = "RG";
			if (test.get_cTABLENAME().toUpperCase().equals("JT_VPOS_TPA"))  cVPOS_TYP = "TPA";
			if (test.get_cTABLENAME().toUpperCase().equals("JT_VPOS_KON"))  cVPOS_TYP = "KON";
			
			//ausser beim abzug wird immer nach der table-id gesucht
			String cID_TABLE = test.get_cID_TABLE();
			
			// wenn es ein abzug ist, dann muss der id_vpos_rg rausgesucht werden und mit in die id-liste aufgenommen werden
			if (test.get_cTABLENAME().equals("JT_VPOS_RG_ABZUG"))
			{
				RECORD_VPOS_RG_ABZUG oMA = new RECORD_VPOS_RG_ABZUG(test.get_cID_TABLE());
				cID_TABLE = oMA.get_ID_VPOS_RG_cUF();
				cVPOS_TYP = "RG";
			}
			
			MyDataRecordHashMap oHMPosition =  new MyDataRecordHashMap("SELECT 	 " +
																		"NVL(ANZAHL,0) AS ANZAHL, " +
																		"NVL(EINZELPREIS,0) AS EINZELPREIS, " +
																		"NVL(MENGENDIVISOR,1) AS MENGENDIVISOR," +
																		"NVL(WAEHRUNGSKURS,1) AS WAEHRUNGSKURS, " +
																		"NVL(EINHEITKURZ,'-') AS EINHEITKURZ,"+
																		"NVL(EINHEIT_PREIS_KURZ,EINHEITKURZ) AS EINHEIT_PREIS_KURZ,"+
																		"ID_WAEHRUNG_FREMD "+
																		" FROM "+bibE2.cTO()+".JT_VPOS_"+cVPOS_TYP +
																				" WHERE ID_VPOS_"+cVPOS_TYP+"="+cID_TABLE);
			
			//zuerst den gesamtpreis undfremdwaehrungen kalkulieren 
			BS_PreisCalculator oBSC = new BS_PreisCalculator(   oHMPosition.get_FormatedValue("ANZAHL"),
																oHMPosition.get_FormatedValue("EINZELPREIS"),
																oHMPosition.get_FormatedValue("MENGENDIVISOR"),
																oHMPosition.get_FormatedValue("WAEHRUNGSKURS"),
																true);
			
			vSQL_Rueck.add("UPDATE JT_VPOS_"+cVPOS_TYP+" SET " +
											" GESAMTPREIS="+MyNumberFormater.formatDezForDATABASE(oBSC.get_dGesamtPreis().doubleValue(), 2)+","+
											" GESAMTPREIS_FW="+MyNumberFormater.formatDezForDATABASE(oBSC.get_dGesamtPreis_FW().doubleValue(), 2)+","+
											" WAEHRUNGSKURS="+MyNumberFormater.formatDezForDATABASE(oBSC.get_dWaehrungskurs().doubleValue(), 4)+","+
											" EINZELPREIS_FW="+MyNumberFormater.formatDezForDATABASE(oBSC.get_dEinzelPreis_FW().doubleValue(), 2)+
																	" WHERE ID_VPOS_"+cVPOS_TYP+"="+cID_TABLE);

			// fuer rechnungs/gutschrift- positionen werden abzuege kalkuliert
			if (cVPOS_TYP.equals("RG"))
			{
			
				
					Vector<String> vFelder = new Vector<String>();
					vFelder.add("ID_VPOS_RG_ABZUG");
					vFelder.add("ABZUGTYP");
					vFelder.add("MENGE_VOR_ABZUG");
					vFelder.add("EPREIS_VOR_ABZUG");
					vFelder.add("EPREIS_VOR_ABZUG_FW");
					vFelder.add("ABZUG");
					vFelder.add("ABZUG2");	
					vFelder.add("ABZUG_BELEGTEXT_SCHABLONE");
					
					MyDataRecordHashList listAbzuege = new MyDataRecordHashList(vFelder,
																				bibE2.cTO()+".JT_VPOS_RG_ABZUG",
																				"ID_VPOS_RG="+cID_TABLE,
																				"ID_VPOS_RG_ABZUG");
					
					
					RECORD_WAEHRUNG oWaehrung = new RECORD_WAEHRUNG(oHMPosition.get_UnFormatedValue("ID_WAEHRUNG_FREMD"));
					
					
					
					if (listAbzuege.get_vVectorWithHashKeys().size()>0)
					{
						BL_AbzugsKalkulator oBLA = new BL_AbzugsKalkulator( oHMPosition.get_FormatedValue("ANZAHL"),
																			oHMPosition.get_FormatedValue("EINZELPREIS"),
																			MyNumberFormater.formatDez(oBSC.get_dEinzelPreis_FW().doubleValue(),2,true),
																			""+oBSC.get_lMengenDivisor().longValue(),
																			MyNumberFormater.formatDez(oBSC.get_dWaehrungskurs().doubleValue(),4,true),
																			bibE2.get_cBASISWAEHRUNG_SYMBOL(),
																			oWaehrung.get_WAEHRUNGSSYMBOL_cUF(),
																			oHMPosition.get_FormatedValue("EINHEITKURZ"),
																			oHMPosition.get_FormatedValue("EINHEIT_PREIS_KURZ"));
						
						for (int i=0;i<listAbzuege.get_size();i++)
						{
							if (i==0)
							{
								oBLA.add_AbzugsKalkulationsZeile(listAbzuege.get_FormatedValue("ABZUGTYP", i),
													 oHMPosition.get_FormatedValue("ANZAHL"),
													 oHMPosition.get_FormatedValue("EINZELPREIS"),
													 MyNumberFormater.formatDez(oBSC.get_dEinzelPreis_FW().doubleValue(),2,true),
													 listAbzuege.get_FormatedValue("ABZUG", i),
													 listAbzuege.get_FormatedValue("ABZUG2", i),
													 listAbzuege.get_FormatedValue("ID_VPOS_RG_ABZUG", i),
													 listAbzuege.get_FormatedValue("ABZUG_BELEGTEXT_SCHABLONE", i));
							}
							else
							{
								oBLA.add_AbzugsKalkulationsZeile(listAbzuege.get_FormatedValue("ABZUGTYP", i),
													 MyNumberFormater.formatDez(oBLA.get_dMengeNachAbzugLetzteAbzugsZeile(),3,false), 
													 MyNumberFormater.formatDez(oBLA.get_dEPreisNachAbzugLetzteAbzugsZeile(),2,false), 
													 MyNumberFormater.formatDez(oBLA.get_dEPreisNachAbzugLetzteAbzugsZeile_FW(),2,false),
													 listAbzuege.get_FormatedValue("ABZUG", i),
													 listAbzuege.get_FormatedValue("ABZUG2", i),
													 listAbzuege.get_FormatedValue("ID_VPOS_RG_ABZUG", i),
													 listAbzuege.get_FormatedValue("ABZUG_BELEGTEXT_SCHABLONE", i));
							}
						}
						//die positionen der abzugsliste nochmal aufschreiben
						vSQL_Rueck.addAll(oBLA.get_vSQL_CompleteUpdateStack_Fuer_RechnungGutschrift_ABZUEGE("JT_VPOS_"+cVPOS_TYP+"_ABZUG"));
						
						//jetzt noch die abzugszeile um die resultierenden werte in der position zu schreiben
						MySqlStatementBuilder oSQL = new MySqlStatementBuilder();
						oSQL.addSQL_Paar("ANZAHL_ABZUG", MyNumberFormater.formatDezForDATABASE(oBLA.get_GESAMTER_MENGENABZUG(),3), false);
						oSQL.addSQL_Paar("EINZELPREIS_ABZUG", MyNumberFormater.formatDezForDATABASE(oBLA.get_GESAMT_EPREIS_ABZUG(),2), false);
						oSQL.addSQL_Paar("EINZELPREIS_ABZUG_FW", MyNumberFormater.formatDezForDATABASE(oBLA.get_GESAMT_EPREIS_ABZUG_FW(),2), false);
						oSQL.addSQL_Paar("EINZELPREIS_RESULT", MyNumberFormater.formatDezForDATABASE(oBLA.get_RESULTIERENDER_EINZELPREIS(),2), false);
						oSQL.addSQL_Paar("EINZELPREIS_RESULT_FW", MyNumberFormater.formatDezForDATABASE(oBLA.get_RESULTIERENDER_EINZELPREIS_FW(),2), false);
						oSQL.addSQL_Paar("GESAMTPREIS_ABZUG", MyNumberFormater.formatDezForDATABASE(oBLA.get_ABZUG_VOM_GESAMTPREIS(),2), false);
						oSQL.addSQL_Paar("GESAMTPREIS_ABZUG_FW", MyNumberFormater.formatDezForDATABASE(oBLA.get_ABZUG_VOM_GESAMTPREIS_FW(),2), false);

						//2011-04-18: neue abzugsfelder
//						ANZAHL_ABZUG_LAGER
//						GPREIS_ABZ_MGE
//						GPREIS_ABZ_MGE_FW
//						GPREIS_ABZ_AUF_NETTOMGE
//						GPREIS_ABZ_AUF_NETTOMGE_FW
//						GPREIS_ABZ_VORAUSZAHLUNG
//						GPREIS_ABZ_VORAUSZAHLUNG_FW
//						EPREIS_RESULT_NETTO_MGE
//						EPREIS_RESULT_NETTO_MGE_FW						
						oSQL.addSQL_Paar("ANZAHL_ABZUG_LAGER", 			MyNumberFormater.formatDezForDATABASE(oBLA.get_GESAMTER_MENGENABZUG_Lager() ,3), false);
						oSQL.addSQL_Paar("GPREIS_ABZ_MGE", 				MyNumberFormater.formatDezForDATABASE(oBLA.get_dSumme_Geldabzug_wegen_realem_MengenAbzug(),2), false);
						oSQL.addSQL_Paar("GPREIS_ABZ_MGE_FW", 			MyNumberFormater.formatDezForDATABASE(oBLA.get_dSumme_Geldabzug_wegen_realem_MengenAbzug_FW(),2), false);
						oSQL.addSQL_Paar("GPREIS_ABZ_AUF_NETTOMGE", 	MyNumberFormater.formatDezForDATABASE(oBLA.get_dSumme_Geldabzug_auf_NettoMenge(),2), false);
						oSQL.addSQL_Paar("GPREIS_ABZ_AUF_NETTOMGE_FW", 	MyNumberFormater.formatDezForDATABASE(oBLA.get_dSumme_Geldabzug_auf_NettoMenge_FW(),2), false);
						oSQL.addSQL_Paar("GPREIS_ABZ_VORAUSZAHLUNG", 	MyNumberFormater.formatDezForDATABASE(oBLA.get_dSumme_Geldabzug_Vorauszahlung(),2), false);
						oSQL.addSQL_Paar("GPREIS_ABZ_VORAUSZAHLUNG_FW", MyNumberFormater.formatDezForDATABASE(oBLA.get_dSumme_Geldabzug_Vorauszahlung_FW(),2), false);
						oSQL.addSQL_Paar("EPREIS_RESULT_NETTO_MGE",		MyNumberFormater.formatDezForDATABASE(oBLA.get_RESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE(),2), false);
						oSQL.addSQL_Paar("EPREIS_RESULT_NETTO_MGE_FW", 	MyNumberFormater.formatDezForDATABASE(oBLA.get_RESULTIERENDER_EINZELPREIS_AUF_LAGERMENGE_FW(),2), false);
					
						vSQL_Rueck.add(oSQL.get_CompleteUPDATEString("JT_VPOS_"+cVPOS_TYP+"", bibE2.cTO(), "ID_VPOS_"+cVPOS_TYP+"="+cID_TABLE, null));
					}
					else
					{
						//die felder resetten
						MySqlStatementBuilder oSQL = new MySqlStatementBuilder();
						oSQL.addSQL_Paar("ANZAHL_ABZUG", 				"NULL", false);
						oSQL.addSQL_Paar("EINZELPREIS_ABZUG", 			"NULL", false);
						oSQL.addSQL_Paar("EINZELPREIS_ABZUG_FW", 		"NULL", false);
						oSQL.addSQL_Paar("EINZELPREIS_RESULT", 			"EINZELPREIS", false);
						oSQL.addSQL_Paar("EINZELPREIS_RESULT_FW",	 	"EINZELPREIS_FW", false);
						oSQL.addSQL_Paar("GESAMTPREIS_ABZUG", 			"NULL", false);
						oSQL.addSQL_Paar("GESAMTPREIS_ABZUG_FW", 		"NULL", false);
					
						//2011-04-18: neue abzugsfelder
						oSQL.addSQL_Paar("ANZAHL_ABZUG_LAGER", 			"NULL", 			false);
						oSQL.addSQL_Paar("GPREIS_ABZ_MGE", 				"NULL", 			false);
						oSQL.addSQL_Paar("GPREIS_ABZ_MGE_FW", 			"NULL", 			false);
						oSQL.addSQL_Paar("GPREIS_ABZ_AUF_NETTOMGE", 	"NULL", 			false);
						oSQL.addSQL_Paar("GPREIS_ABZ_AUF_NETTOMGE_FW", 	"NULL", 			false);
						oSQL.addSQL_Paar("GPREIS_ABZ_VORAUSZAHLUNG", 	"NULL", 			false);
						oSQL.addSQL_Paar("GPREIS_ABZ_VORAUSZAHLUNG_FW", "NULL", 			false);
						oSQL.addSQL_Paar("EPREIS_RESULT_NETTO_MGE", 	"EINZELPREIS", 		false);
						oSQL.addSQL_Paar("EPREIS_RESULT_NETTO_MGE_FW", 	"EINZELPREIS_FW", 	false);
						
						
						
						
						
						vSQL_Rueck.add(oSQL.get_CompleteUPDATEString("JT_VPOS_"+cVPOS_TYP+"", bibE2.cTO(), "ID_VPOS_"+cVPOS_TYP+"="+cID_TABLE, null));
					}
			}
			
		}
		return vSQL_Rueck;
	}

}
