package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN._VALID_DAEMON;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_UMA_KONTRAKT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FU___ERMITTLE_PREIS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG.BUCH_StatementBuilder;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.PRUEF_RECORD_VPOS_TPA_FUHRE_ORT;

/*
 * Daemon der die maskenvalidierung der fuhre ueber die V<MANDANT>_FUHRE durchfuehrt, damit eine
 * fuhre und die kombination fuhre/fuhrenort in einem zug validiert werden kann
 */
public class FUHREN_SQL_DAEMON_4_VALIDATION extends XX_SQL_STACK_DAEMON {

	
	private Vector<String>  vTablesToCheck = new Vector<String>();
	
	
	public FUHREN_SQL_DAEMON_4_VALIDATION() throws myException 
	{
		super();
		this.vTablesToCheck.add("JT_VPOS_TPA_FUHRE");
		this.vTablesToCheck.add("JT_VPOS_TPA_FUHRE_ORT");
		this.vTablesToCheck.add("JT_VPOS_RG");
		
		//2012-01-04: auch uma-kontrakte ueberwachen, damit die sortenzuordnung uma-fuhre immer gewahrt bleibt
		this.vTablesToCheck.add("JT_UMA_KONTRAKT");
	}

	private MyDBToolBox 	oDB = 					null;

	
	protected void finalize()
	{
		if (oDB != null)
			bibALL.destroy_myDBToolBox(oDB);
	}

	
	
	// wird unmittelbar nach jedem einzelnen sql-statement ausgefuehrt
	public MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> vLogInfos, MyConnection oConn) throws myException 
	{
		
		//die betroffenen tabellen-LogInfos sammeln
		for (int i=0;i<vLogInfos.size();i++)
		{
			if (this.vTablesToCheck.contains(vLogInfos.get(i).get_cTABLENAME()))
			{
				if (!vLogInfos.get(i).get_IS_DELETE())           //nur insert und update werden beruecksichtigt !
				{
					this.get_vLogInfos().add(vLogInfos.get(i));
				}
			}
		}
		
		return new MyE2_MessageVector();
	}


	
	public MyE2_MessageVector doValidate(MyConnection oConn) throws myException 
	{
		
		
		MyE2_MessageVector oMV = new MyE2_MessageVector();
		
		if (this.oDB==null)
			this.oDB =bibALL.get_myDBToolBox(oConn);
		
		
		//zuerst die id-Vectoren fuer die fuhren und orte sammeln sammeln
		VectorSingle  vIDsFuhren = 	new VectorSingle();
		VectorSingle  vIDsOrt = 	new VectorSingle();
		
		for (int i=0;i<this.get_vLogInfos().size();i++)
		{
			String cTableName = this.get_vLogInfos().get(i).get_cTABLENAME();
			String cTableID   = this.get_vLogInfos().get(i).get_cID_TABLE();
			
			if (cTableName.toUpperCase().equals("JT_VPOS_TPA_FUHRE"))
			{
				vIDsFuhren.add(cTableID);
			}
			else if (cTableName.toUpperCase().equals("JT_VPOS_TPA_FUHRE_ORT"))
			{
				vIDsOrt.add(cTableID);
			}
			//2012-01-04: uma-kontrakte: alle fuhren dazu ueberwachen
			else if (cTableName.toUpperCase().equals("JT_UMA_KONTRAKT"))
			{
				RECORD_UMA_KONTRAKT recUma = new RECORD_UMA_KONTRAKT(cTableID,oConn);
				vIDsFuhren.addAll(recUma.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_id_uma_kontrakt().get_vKeyValues());
			}
		}		
		
		
		
		//dann eine reclist mit allen betreffenden view-eintraegen ziehen
		String vIDs_FuhrenBlock = 		"("+bibALL.Concatenate(vIDsFuhren, ",", "-1000")+")";
		String vIDs_FuhrenOrtBlock = 	"("+bibALL.Concatenate(vIDsOrt, ",", "-1000")+")";
		
		String cViewName = "V"+bibALL.get_ID_MANDANT()+"_FUHREN";
		
		RECLIST_VPOS_TPA_FUHRE recLIST_Alle_ViewEintraege  = new RECLIST_VPOS_TPA_FUHRE("SELECT TO_CHAR(ID_VPOS_TPA_FUHRE)||'-'||TO_CHAR(NVL(ID_VPOS_TPA_FUHRE_ORT,0)) AS HELPID," +
				cViewName+".* FROM "+cViewName+" WHERE  NVL(DELETED,'N')='N' AND ID_VPOS_TPA_FUHRE IN "+vIDs_FuhrenBlock+" OR ID_VPOS_TPA_FUHRE_ORT IN "+vIDs_FuhrenOrtBlock,oConn,0,"HELPID");
		
		
		//es koennen mehrere fuhren in dem block vorhanden sein, deshalb muessen sub-reclists fuer die einzelnen fuhren gebaut werden, um die validierungen machen zu koennen
		VectorSingle vIDs_NurFuhrenIDs = new VectorSingle();
		for (int i=0;i<recLIST_Alle_ViewEintraege.get_vKeyValues().size();i++)
		{
			//schnellerfassungsfuhren aussen vor-lassen
			if (recLIST_Alle_ViewEintraege.get(i).is_FUHRE_KOMPLETT_YES())
			{
				vIDs_NurFuhrenIDs.add(recLIST_Alle_ViewEintraege.get(i).get_ID_VPOS_TPA_FUHRE_cUF());
			}
		}
		
		//jetzt fuer jede vorkommende id_Fuhre eine eigene RECLIST sammeln
		for (int i=0;i<vIDs_NurFuhrenIDs.size();i++)
		{
			String cID_FuhreToCheck = vIDs_NurFuhrenIDs.get(i);
			RECLIST_VPOS_TPA_FUHRE  recListTemp = new RECLIST_VPOS_TPA_FUHRE(oConn);
			recListTemp.set_KEYFIELD("HELPID");
			
			for (int k=0;k<recLIST_Alle_ViewEintraege.get_vKeyValues().size();k++)
			{
				if (recLIST_Alle_ViewEintraege.get(k).get_ID_VPOS_TPA_FUHRE_cUF().equals(cID_FuhreToCheck))
				{
					recListTemp.ADD(recLIST_Alle_ViewEintraege.get(k), false);
				}
			}
			
			for (int ii=0;ii<recListTemp.size();ii++)
			{
				if (recListTemp.get(ii).is_DELETED_NO())
				{
					//	hier die verschiedenen pruefklassen durchlaufen
					oMV.add_MESSAGE(new FUHREN_PRUEFUNG_AVV_STRUCTURE(recListTemp.get(ii)).mache_Pruefung());
					oMV.add_MESSAGE(new FUHREN_PRUEFUNG_WARENAUSGANG_NUR_MIT_VK_KON(recListTemp.get(ii)).mache_Pruefung());
					oMV.add_MESSAGE(new FUHREN_PRUEFE_ADRESSE_LAGER_KONTRAKT(recListTemp.get(ii)).mache_Pruefung());
					/*
					 * geaendert am: 19.01.2010 von: martin
					 */
					//aenderung: 2010-12-30: eindeutigkeit auf beiden seiten wieder weg
					//oMV.add_MESSAGE(new FUHREN_PRUEFE_EINDEUTIGKEIT_LAND_BEIDESEITEN(recListTemp.get(ii)).mache_Pruefung());
					
					
					//2011-10-04: neue plausibilitaet fuer sortengleichheit auf den fuhrenseiten
					//            um evtl. fehlbedienungen abzufangen
					oMV.add_MESSAGE(new FUHREN_PRUEFE_FUHRE_SORTEN_Plausibilitaet(recListTemp.get(ii)).mache_Pruefung());

					//2012-03-27: fehlender handelsvertrag beim VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG = Y im daemon ausfuehren
					
					// inaktiv am 2016-02-26: wegen neuer pruefung via decision beim drucken/buchen 
					// oMV.add_MESSAGE(new FUHREN_PRUEFUNG_FEHLENDER_HANDELSVERTRAG(recListTemp).mache_Pruefung());
					//
				}
			}
			
			oMV.add_MESSAGE(new FUHREN_PRUEFUNG_SORTEN_ABRECHSTATUS(recListTemp).mache_Pruefung());
			oMV.add_MESSAGE(new FUHREN_PRUEFUNG_KONTRAKT_ZUORDNUNG(recListTemp).mache_Pruefung());
			oMV.add_MESSAGE(new FUHREN_PRUEFUNG_LAGERZUORDNUNG(recListTemp).mache_Pruefung());
			
			//2012-01-05: plausibilitaet der UMA-kontrakte
			oMV.add_MESSAGE(new FUHREN_PRUEFUNG_UMA_KONTRAKT(recListTemp).mache_Pruefung());
		
			
		}
		
			

		if (oMV.get_bIsOK())
		{
			// fuer die weitere pruefung einen Vector mit PRUEF_RECORD_VPOS_TPA_FUHRE aufbauen, der spaeter verwendet werden kann
			Vector<PRUEF_RECORD_VPOS_TPA_FUHRE>  		vRecsFuhrenPruefung = 		new Vector<PRUEF_RECORD_VPOS_TPA_FUHRE>();
			
			//und einen weiteren fuer die orte (die neu aufgebaut werden muessen)
			Vector<PRUEF_RECORD_VPOS_TPA_FUHRE_ORT>  	vRecsFuhrenOrtPruefung = 	new Vector<PRUEF_RECORD_VPOS_TPA_FUHRE_ORT>();
			
			for (int i=0;i<recLIST_Alle_ViewEintraege.get_vKeyValues().size();i++)
			{
				RECORD_VPOS_TPA_FUHRE  recTest = recLIST_Alle_ViewEintraege.get(i);              //enthaelt auch alle felder des V1_FUHREN - spezial-views 
				
				if (S.isEmpty(recTest.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT")))
				{
					//dann ist es eine fuhre
					vRecsFuhrenPruefung.add(new PRUEF_RECORD_VPOS_TPA_FUHRE(recTest,false));
				}
				else
				{
					vRecsFuhrenOrtPruefung.add(new PRUEF_RECORD_VPOS_TPA_FUHRE_ORT(recTest.get_UnFormatedValue("ID_VPOS_TPA_FUHRE_ORT"),oConn,false));
				}
			}


			
			Vector<String>  		vSQLPreisFindung = 			new Vector<String>();
			
			//jetzt die zwei listen nacheinander abfahren fuer die preis-findungsautomatik
			for (int i=0;i<vRecsFuhrenPruefung.size();i++)
			{
				PRUEF_RECORD_VPOS_TPA_FUHRE  recTestFuhre = vRecsFuhrenPruefung.get(i);
				boolean bWasZuTun = false;

				BUCH_StatementBuilder oTestBuildEK = new BUCH_StatementBuilder(recTestFuhre,true);     //EK
				if (!oTestBuildEK.get_bIstBereitsGebucht())
				{
					FU___ERMITTLE_PREIS  fu_LoaderEK = new FU___ERMITTLE_PREIS(recTestFuhre,oMV,true,oConn);
					recTestFuhre.set_NEW_VALUE_ID_VPOS_STD_EK(S.NN(fu_LoaderEK.get_ID_VPOS_STD_Rueck_UF()));
					recTestFuhre.set_NEW_VALUE_EINZELPREIS_EK(fu_LoaderEK.get_bdNeuerPreis_Rueck()==null?null:MyNumberFormater.formatDezWithRound(fu_LoaderEK.get_bdNeuerPreis_Rueck(), 2));
					recTestFuhre.set_NEW_VALUE_MANUELL_PREIS_EK(S.NN(fu_LoaderEK.get_cPreisManuell_Y_N_Rueck()));
					bWasZuTun = true;
				}

				BUCH_StatementBuilder oTestBuildVK = new BUCH_StatementBuilder(recTestFuhre,false);     //VK
				if (!oTestBuildVK.get_bIstBereitsGebucht())
				{
					FU___ERMITTLE_PREIS  fu_LoaderVK = new FU___ERMITTLE_PREIS(recTestFuhre,oMV,false,oConn);
					recTestFuhre.set_NEW_VALUE_ID_VPOS_STD_VK(S.NN(fu_LoaderVK.get_ID_VPOS_STD_Rueck_UF()));
					recTestFuhre.set_NEW_VALUE_EINZELPREIS_VK(fu_LoaderVK.get_bdNeuerPreis_Rueck()==null?null:MyNumberFormater.formatDezWithRound(fu_LoaderVK.get_bdNeuerPreis_Rueck(), 2));
					recTestFuhre.set_NEW_VALUE_MANUELL_PREIS_VK(S.NN(fu_LoaderVK.get_cPreisManuell_Y_N_Rueck()));
					bWasZuTun = true;
				}
				if (bWasZuTun)
				{
					vSQLPreisFindung.add(recTestFuhre.get_SQL_UPDATE_STATEMENT(null, true));
				}
			}
			
			//jetzt die zwei listen nacheinander abfahren
			for (int i=0;i<vRecsFuhrenOrtPruefung.size();i++)
			{
				PRUEF_RECORD_VPOS_TPA_FUHRE_ORT  recTestFuhreOrt = vRecsFuhrenOrtPruefung.get(i);

				BUCH_StatementBuilder oTestBuildOrt = new BUCH_StatementBuilder(recTestFuhreOrt);     //EK
				
				if (!oTestBuildOrt.get_bIstBereitsGebucht())
				{
					FU___ERMITTLE_PREIS  		fu_Loader = new FU___ERMITTLE_PREIS(recTestFuhreOrt,oMV,oConn);

					recTestFuhreOrt.set_NEW_VALUE_ID_VPOS_STD(S.NN(fu_Loader.get_ID_VPOS_STD_Rueck_UF()));
					recTestFuhreOrt.set_NEW_VALUE_EINZELPREIS(fu_Loader.get_bdNeuerPreis_Rueck()==null?null:MyNumberFormater.formatDezWithRound(fu_Loader.get_bdNeuerPreis_Rueck(), 2));
					recTestFuhreOrt.set_NEW_VALUE_MANUELL_PREIS(S.NN(fu_Loader.get_cPreisManuell_Y_N_Rueck()));

					vSQLPreisFindung.add(recTestFuhreOrt.get_SQL_UPDATE_STATEMENT(null, true));
				}
			}
			
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox(oConn);
			if (vSQLPreisFindung.size()>0)
			{
				if (!oDB.ExecSQL(vSQLPreisFindung, false))
				{
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Die Preisfindung konnte nicht durchgefuehrt werden (SQL-Fehler) !!"));
				}
			}
			
			
			//AB HIER MUESSEM DIE RECORDS NEU GEHOLT WERDEN, DA VORHER EVTL RELEVANTE EINTRAEGE GESETZT WURDEN

			//jetzt noch die Mengenverteilung und die buchungs-status eintragung machen
			if (oMV.get_bIsOK())
			{
				for (int i=0;i<this.get_vLogInfos().size();i++)
				{
					
					if (this.get_vLogInfos().get(i).get_cTABLENAME().equals("JT_VPOS_TPA_FUHRE"))
					{
						oMV.add_MESSAGE(new PRUEF_RECORD_VPOS_TPA_FUHRE(this.get_vLogInfos().get(i).get_cID_TABLE(),oConn,true).__writeSQLStatemtents_MengenSituation_undFuhrenStatus(false));
					}
					if (this.get_vLogInfos().get(i).get_cTABLENAME().equals("JT_VPOS_RG"))
					{
						RECORD_VPOS_RG recTest = new RECORD_VPOS_RG(this.get_vLogInfos().get(i).get_cID_TABLE(),oConn);
						if (recTest.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord()!=null)
						{
							oMV.add_MESSAGE(new PRUEF_RECORD_VPOS_TPA_FUHRE(recTest.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre_zugeord(),true).__writeSQLStatemtents_MengenSituation_undFuhrenStatus(false));
						}
					}
				}
			}
		}
		
		
		return oMV;
	}

	
	

	public Vector<String> getTriggerStatementsAfterSQL( MyConnection oConn, MyE2_MessageVector oMV) throws myException 
	{
		return new Vector<String>();
	}
	
	
}
