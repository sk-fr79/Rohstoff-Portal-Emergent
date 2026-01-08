package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.BUCHUNG;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRecordCompareRule;
import panter.gmbh.indep.dataTools.MyRecordComparer;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


/*
 * wird eine Fuhre, die geschlossen ist, wieder geoeffnet, dann wird im falle, dass die position bereits einem nicht gedruckten rechnungskopf zugeordnet wurde,
 * diese position aus dem kopf geloescht und dann neu in die freien positionen geschoben. Dieser agent prueft die gleichheit der position und ihres geloeschten
 * vorgaengers und ordnet bei gleichheit die position einem evtl. vorhandenen vorgangskopf wieder zu
 */
public class FUB_ZUSATZ_ACTIONAGENT_Pruefe_Ob_Neue_Pos_InKopfsatz_muss extends 	XX_ActionAgent 
{

	
	private MyE2_MessageVector 		vInfos = 		new MyE2_MessageVector();
	private boolean 				bUseInList = 	false;
	private E2_NavigationList  		oNaviList = 	null;
	private FUB___LIST_BT_Buchung  	oButtonBuchung = null;

	
	public FUB_ZUSATZ_ACTIONAGENT_Pruefe_Ob_Neue_Pos_InKopfsatz_muss(FUB___LIST_BT_Buchung  ButtonBuchung, boolean UseInList, E2_NavigationList NaviList) 
	{
		super();
		this.bUseInList = UseInList;
		this.oNaviList = NaviList;
		this.oButtonBuchung = ButtonBuchung;
	}



	@Override
	public void executeAgentCode(ExecINFO execInfo) throws myException
	{

		this.vInfos.removeAllElements();
		
		Vector<String> vID_Fuhren = new Vector<String>();
		if (this.bUseInList)
		{
			vID_Fuhren.add( this.oButtonBuchung.EXT().get_C_MERKMAL());
		}
		else
		{
			vID_Fuhren.addAll(this.oNaviList.get_vSelectedIDs_Unformated());
		}

		
		Vector<String> vSQL_Stack = new Vector<String>();
		
		//zuerst alle recordlists sammeln, die untersucht werden muessen
		Vector<RECLIST_VPOS_RG> vRecList_VPOS_RG = new Vector<RECLIST_VPOS_RG>();
		
		for (int i=0;i<vID_Fuhren.size();i++)
		{
			RECORD_VPOS_TPA_FUHRE recFuhre = new RECORD_VPOS_TPA_FUHRE(vID_Fuhren.get(i));
			
			/*
			 * untersucht wird die letzte vorige position in der liste
			 */
			
			//zuerst die haupt-fuhre bearbeiten (EK-Seite)
			vRecList_VPOS_RG.add(recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
					" ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL AND LAGER_VORZEICHEN=1", "ID_VPOS_RG DESC", true));
			
			
			//dann die haupt-fuhre bearbeiten (VK-Seite)
			vRecList_VPOS_RG.add(recFuhre.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_zugeord(
					" ID_VPOS_TPA_FUHRE_ORT_ZUGEORD IS NULL AND LAGER_VORZEICHEN=-1", "ID_VPOS_RG DESC", true));
			
			
			RECLIST_VPOS_TPA_FUHRE_ORT reclistORT = recFuhre.get_DOWN_RECORD_LIST_VPOS_TPA_FUHRE_ORT_id_vpos_tpa_fuhre("NVL(DELETED,'N')='N'", null, true);
			for (int k=0;k<reclistORT.get_vKeyValues().size();k++)
			{
				RECORD_VPOS_TPA_FUHRE_ORT recORT = reclistORT.get(k);
				vRecList_VPOS_RG.add(recORT.get_DOWN_RECORD_LIST_VPOS_RG_id_vpos_tpa_fuhre_ort_zugeord("", "ID_VPOS_RG DESC", true));
			}
		}
		
		for (int i=0;i<vRecList_VPOS_RG.size();i++)
		{
			vSQL_Stack.addAll(this.get_vSQLS(vRecList_VPOS_RG.get(i)));
		}
		
		if (vSQL_Stack.size()>0)
		{
			this.vInfos.add_MESSAGE(bibDB.ExecMultiSQLVector(vSQL_Stack, true));
		}

		bibMSG.add_MESSAGE(this.vInfos);
		
	}

	
	private Vector<String>  get_vSQLS(RECLIST_VPOS_RG recListToCheck) throws myException
	{
		Vector<String> vSQL = new Vector<String>();
		
		//pruefung 1: hat die Reclist mindestens 2 records:
		if (recListToCheck.get_vKeyValues().size()>=2)
		{
			RECORD_VPOS_RG recNeu = recListToCheck.get(0);
			RECORD_VPOS_RG recVorher = recListToCheck.get(1);
			
			//pruefung 2: nur sinnvoll, wenn die erste nicht und die zweite geloescht wurde
			if (recNeu.is_DELETED_NO() && recVorher.is_DELETED_YES())
			{
				//bedingung 3: die positionen sind beide gleich (bis auf die kopfnummer)
				if (new ownRecordComparerComparePOS().IsEqual(recNeu, recVorher))
				{

					//2012-02-28: fehler aufgrund Rechnungsdrucks vor neuverbuchung der fuhren
					//            damit wurden die positionen einer bereits end-gedruckten rechnung zugewiesen und sind damit
					//            erst nach neuem druck sichtbar: Korrektur: die neueinordnung darf nur erfolgen, wenn eine Rechnung
					//            noch nicht endgueltig gedruckt wurde
					
					//bedingung 3: die geloeschte position liegt in einem ungeloeschten kopf
					if (recVorher.get_UP_RECORD_VKOPF_RG_id_vkopf_rg()!=null && recNeu.get_ID_VKOPF_RG_cUF_NN("").equals(""))
					{
						
						//version vor 2012-02-28:
//						if (recVorher.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_DELETED_NO())
//						{
//							//dann wird die neue position wieder in den kopf eingruppiert
//							recNeu.set_NEW_VALUE_POSITIONSNUMMER(recVorher.get_POSITIONSNUMMER_cF_NN("-1"));
//							recNeu.set_NEW_VALUE_ID_VKOPF_RG(recVorher.get_ID_VKOPF_RG_cUF());
//							
//							vSQL.add(recNeu.get_SQL_UPDATE_STATEMENT(null, true));
//						}
						
						//version nach 2012-02-28: dar nur in nicht geschlossene kopfsaetze zurueck !!!!
						if (recVorher.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_DELETED_NO() && recVorher.get_UP_RECORD_VKOPF_RG_id_vkopf_rg().is_ABGESCHLOSSEN_NO())
						{
							//dann wird die neue position wieder in den kopf eingruppiert
							recNeu.set_NEW_VALUE_POSITIONSNUMMER(recVorher.get_POSITIONSNUMMER_cF_NN("-1"));
							recNeu.set_NEW_VALUE_ID_VKOPF_RG(recVorher.get_ID_VKOPF_RG_cUF());
							
							vSQL.add(recNeu.get_SQL_UPDATE_STATEMENT(null, true));
						}
						
						
					}
				}
			}
		}
		return vSQL;
	}
	
	
	
	
	private class ownRecordComparerComparePOS extends MyRecordComparer
	{
		
		public ownRecordComparerComparePOS() throws myException
		{
			super();
			this.add_CompareRule("ID_MANDANT");
			this.add_CompareRule("POSITION_TYP");
			this.add_CompareRule("ID_ARTIKEL");
			this.add_CompareRule("ARTBEZ1");
			this.add_CompareRule("ARTBEZ2");
			this.add_CompareRule("EINHEITKURZ");
			this.add_CompareRule("ANR1");
			this.add_CompareRule("ANR2");
			this.add_CompareRule("ZOLLTARIFNR");
//			this.add_CompareRule("ANZAHL");
//			this.add_CompareRule("ANZAHL_ABZUG");
//			this.add_CompareRule("GESAMTPREIS");
//			this.add_CompareRule("GESAMTPREIS_FW");
//			this.add_CompareRule("GESAMTPREIS_ABZUG");
//			this.add_CompareRule("GESAMTPREIS_ABZUG_FW");
//			this.add_CompareRule("EINZELPREIS");
//			this.add_CompareRule("EINZELPREIS_FW");
//			this.add_CompareRule("EINZELPREIS_ABZUG_FW");
//			this.add_CompareRule("EINZELPREIS_RESULT_FW");
//			this.add_CompareRule("EINZELPREIS_ABZUG");
//			this.add_CompareRule("EINZELPREIS_RESULT");
			this.add_CompareRule("STEUERSATZ");
//			this.add_CompareRule("ID_VKOPF_RG");    hier muss einer einem kopf zugeordnet sein, der andere nicht
			this.add_CompareRule("EINHEIT_PREIS_KURZ");
			this.add_CompareRule("MENGENDIVISOR");
			this.add_CompareRule("ID_ADRESSE");
			this.add_CompareRule("BESTELLNUMMER");
			this.add_CompareRule("VORGANG_TYP");
			this.add_CompareRule("POSITIONSKLASSE");
			this.add_CompareRule("LAGER_VORZEICHEN");
			this.add_CompareRule("EUNOTIZ");
			this.add_CompareRule("EUCODE");
			this.add_CompareRule("ID_STRECKEN_DEF");
			this.add_CompareRule("AUSFUEHRUNGSDATUM");
			this.add_CompareRule("ID_ARTIKEL_BEZ");
			this.add_CompareRule("LIEFERBEDINGUNGEN");
			this.add_CompareRule("WIEGEKARTENKENNER");
			this.add_CompareRule("ID_VPOS_KON_ZUGEORD");
			this.add_CompareRule("ID_VPOS_TPA_FUHRE_ZUGEORD");
			this.add_CompareRule("BEMERKUNG_INTERN");
			this.add_CompareRule("ID_VPOS_X_NACHFOLGER");
			this.add_CompareRule("ID_VPOS_PREISKLAMMER");
			this.add_CompareRule("WAEHRUNGSKURS");
			this.add_CompareRule("ID_WAEHRUNG_FREMD");
			this.add_CompareRule("ZAHLUNGSBEDINGUNGEN");
			this.add_CompareRule("ID_ZAHLUNGSBEDINGUNGEN");
			this.add_CompareRule("ZAHLTAGE");
			this.add_CompareRule("FIXMONAT");
			this.add_CompareRule("FIXTAG");
			this.add_CompareRule("SKONTO_PROZENT");
			this.add_CompareRule("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD");
			this.add_CompareRule("EU_STEUER_VERMERK");
			

			this.add_CompareRule("OHNE_STEUER");
			this.add_CompareRule("IST_SONDERPOSITION");

			this.add_CompareRule(new ownCompareRuleSingleString("GEBUCHT"));
			
			
		}

		
		
		private class ownCompareRuleSingleString extends MyRecordCompareRule
		{
			public ownCompareRuleSingleString(String cfieldname)
			{
				super(cfieldname);
			}

			@Override
			public boolean IsEqual(MyRECORD rec1, MyRECORD rec2)	throws myException
			{
				String cText1 = rec1.get_UnFormatedValue(this.get_cFIELDNAME(), "N");
				String cText2 = rec2.get_UnFormatedValue(this.get_cFIELDNAME(), "N");
				
				return (cText1.equals(cText2));
			}
		}

		
	}

}
