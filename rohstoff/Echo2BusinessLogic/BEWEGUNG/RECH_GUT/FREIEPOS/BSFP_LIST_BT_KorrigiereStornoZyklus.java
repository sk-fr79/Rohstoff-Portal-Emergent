package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.FREIEPOS;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_MessageBoxYesNo;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRecordCompareRule;
import panter.gmbh.indep.dataTools.MyRecordComparer;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class BSFP_LIST_BT_KorrigiereStornoZyklus extends MyE2_Button 
{

	private E2_NavigationList   	oNaviList = null;
	
	
	private RECORD_VPOS_RG  		recVPOS_Storno2 = null;
	private RECORD_VPOS_RG  		recNeuePosNachStorno = null;
	private RECORD_VPOS_RG  		recOriginal_Storno1 = null;
	
	
	public BSFP_LIST_BT_KorrigiereStornoZyklus(E2_NavigationList  NaviList) 
	{
		super(E2_ResourceIcon.get_RI("storno_weg.png"));
		
		this.oNaviList = NaviList;
		
		this.add_GlobalAUTHValidator_AUTO("STORNO_POSITIONEN_REVIDIEREN");

		this.setToolTipText(new MyE2_String("Entfernen von Stornopaaren, die zu 0 aufgehen ...").CTrans());
		
		this.add_oActionAgent(new ownActionPopup());
		
	}

	

	private class ownActionPopup extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSFP_LIST_BT_KorrigiereStornoZyklus oThis = BSFP_LIST_BT_KorrigiereStornoZyklus.this;
			
			Vector<String> vID_pos = oThis.oNaviList.get_vSelectedIDs_Unformated();
			
			//validierungskaskade
			
			if (vID_pos.size()!=1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau EINE Position vom Typ Storno 2 selektieren ..."));
				return;
			}
			
			
			oThis.recVPOS_Storno2 = new RECORD_VPOS_RG(vID_pos.get(0));
			
			
			if (recVPOS_Storno2.get_ID_VKOPF_RG_lValue(new Long(-1))>0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bereits einem Kopf zugehörende Positionen können nicht revidiert werden !"));
				return;
			}
			
			
			if (recVPOS_Storno2.get_ID_VPOS_RG_STORNO_VORGAENGER_lValue(new Long(-1))<0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Revidierung kann nur von einer Position vom Typ Storno 2 (S2) ausgehen !"));
				return;
			}
			
			
//			//jetzt die nachfolgende position suchen
//			RECLIST_VPOS_RG  reclistRG = new RECLIST_VPOS_RG("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_RG>"+recVPOS_Storno2.get_ID_VPOS_RG_cUF()+
//															 " AND NVL(ID_VPOS_TPA_FUHRE_ZUGEORD,0)="+recVPOS_Storno2.get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN("0")+
//															 " AND NVL(ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,0)="+recVPOS_Storno2.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF_NN("0")+
//															 " AND NVL(LAGER_VORZEICHEN,0)="+recVPOS_Storno2.get_LAGER_VORZEICHEN_cUF_NN("0"));
			
			//2011-06-08: geloeschte werden ignoriert
			//jetzt die nachfolgende position suchen
			RECLIST_VPOS_RG  reclistRG = new RECLIST_VPOS_RG("SELECT * FROM "+bibE2.cTO()+".JT_VPOS_RG WHERE ID_VPOS_RG>"+recVPOS_Storno2.get_ID_VPOS_RG_cUF()+
															 " AND NVL(ID_VPOS_TPA_FUHRE_ZUGEORD,0)="+recVPOS_Storno2.get_ID_VPOS_TPA_FUHRE_ZUGEORD_cUF_NN("0")+
															 " AND NVL(ID_VPOS_TPA_FUHRE_ORT_ZUGEORD,0)="+recVPOS_Storno2.get_ID_VPOS_TPA_FUHRE_ORT_ZUGEORD_cUF_NN("0")+
															 " AND NVL(LAGER_VORZEICHEN,0)="+recVPOS_Storno2.get_LAGER_VORZEICHEN_cUF_NN("0")+
															 " AND NVL(DELETED,'N')='N'");

			
			//es duerfte nur eine nachfolgende position geben
			if (reclistRG.get_vKeyValues().size()>1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Es wurden mehrere Folgende Positionen zu dieser Fuhre gefunden - REVISION unmöglich !"));
				return;
			}
			
			
			oThis.recNeuePosNachStorno = reclistRG.get(0);
			
			if (recNeuePosNachStorno.get_ID_VKOPF_RG_lValue(new Long(-1))>0)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bereits einem Kopf zugehörende Positionen können nicht revidiert werden !"));
				return;
			}
			
			
			
			oThis.recOriginal_Storno1 = new RECORD_VPOS_RG(recVPOS_Storno2.get_ID_VPOS_RG_STORNO_VORGAENGER_cUF());

			
			//noch eine pruefung:
			if (recOriginal_Storno1.is_DELETED_YES() || recVPOS_Storno2.is_DELETED_YES() || recNeuePosNachStorno.is_DELETED_YES())
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Eine Position im Storno-Zyklus ist bereits gelöscht !!! Bitte informieren Sie den Systemverwalter !!!"));
				return;
			}
			
			new ownPopupYesNo();
		}
	}
	
	
	private class ownPopupYesNo extends E2_MessageBoxYesNo
	{

		public ownPopupYesNo() throws myException 
		{
			super(	new	MyE2_String("Revidieren eines Stornos"), 
					new MyE2_String("OK-Revidieren"),  
					new MyE2_String("Abbruch"), 
					S.VT("Soll von dieser Position aus der ","Storno-Vorgang revidiert werden ?"),
					new ownActionAgent());
		}
		
	}
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			BSFP_LIST_BT_KorrigiereStornoZyklus oThis = BSFP_LIST_BT_KorrigiereStornoZyklus.this;
			
			Vector<String>  vSQL = new Vector<String>();
			
			// wenn jetzt der Vergleich zwischen recNeuePosNachStorno und recVPOS_Storno2 gleich ergibt, dann koennen diese beiden geloescht werden
			// und  das storno 1 - merkmal im original entfernt werden
			
			if (new ownRecordComparerComparePOS().IsEqual(oThis.recNeuePosNachStorno, oThis.recVPOS_Storno2))
			{
				//3. Bedingung (muesste eigentlich immer so sein)
				if (recOriginal_Storno1.get_ID_VPOS_RG_STORNO_NACHFOLGER_lValue(new Long(-1)).longValue()==recVPOS_Storno2.get_ID_VPOS_RG_lValue(new Long(-2)))
				{
					//dann alles ausfuehren
					
					oThis.recNeuePosNachStorno.set_NEW_VALUE_DELETED("Y");
					oThis.recNeuePosNachStorno.set_NEW_VALUE_DEL_GRUND("STORNO-ENTFERNUNG-MANUELL");
					oThis.recNeuePosNachStorno.set_NEW_VALUE_DEL_DATE(bibALL.get_cDateNOW());
					
					oThis.recVPOS_Storno2.set_NEW_VALUE_DELETED("Y");
					oThis.recVPOS_Storno2.set_NEW_VALUE_DEL_GRUND("STORNO-ENTFERNUNG-MANUELL");
					oThis.recVPOS_Storno2.set_NEW_VALUE_DEL_DATE(bibALL.get_cDateNOW());
					oThis.recVPOS_Storno2.set_NEW_VALUE_ID_VPOS_RG_STORNO_VORGAENGER(null);
					
					oThis.recOriginal_Storno1.set_NEW_VALUE_ID_VPOS_RG_STORNO_NACHFOLGER(null);
					
					vSQL.add(oThis.recNeuePosNachStorno.get_SQL_UPDATE_STATEMENT(null, true));
					vSQL.add(oThis.recVPOS_Storno2.get_SQL_UPDATE_STATEMENT(null, true));
					vSQL.add(oThis.recOriginal_Storno1.get_SQL_UPDATE_STATEMENT(null, true));
					
					
					MyE2_MessageVector  oMV = bibDB.ExecMultiSQLVector(vSQL, true);
					
					if (oMV.get_bIsOK())
					{
						bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Storno-Zyklus ist revidiert !"));
						oThis.oNaviList._REBUILD_ACTUAL_SITE("");
					}
					else
					{
						bibMSG.add_MESSAGE(oMV);
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Storno-Positionen gehören nicht zusammen !!!"));
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Positionen sind nicht hinreichend gleich !!!"));
			}
		}
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
			
			//2011-02-11: Artbez2 wird nicht mehr verglichen
			//this.add_CompareRule("ARTBEZ2");
			
			this.add_CompareRule("EINHEITKURZ");
			this.add_CompareRule("ANR1");
			this.add_CompareRule("ANR2");
			//2010-12-21: ZOLLTARIFNR nicht relevant fuer storno
			//this.add_CompareRule("ZOLLTARIFNR");
			this.add_CompareRule("EINZELPREIS");
			this.add_CompareRule("STEUERSATZ");
			this.add_CompareRule("ID_VKOPF_RG");
			this.add_CompareRule("EINHEIT_PREIS_KURZ");
			this.add_CompareRule("MENGENDIVISOR");
			this.add_CompareRule("ID_ADRESSE");
			this.add_CompareRule("VORGANG_TYP");
			this.add_CompareRule("EINZELPREIS_ABZUG");
			this.add_CompareRule("EINZELPREIS_RESULT");
			this.add_CompareRule("POSITIONSKLASSE");
			this.add_CompareRule("LAGER_VORZEICHEN");
			this.add_CompareRule("ID_ARTIKEL_BEZ");
			//2010-12-21: LIEFERBEDINGUNGEN nicht relevant fuer storno
			//this.add_CompareRule("LIEFERBEDINGUNGEN");
			//2010-12-21: wiegekartenkenner nicht relevant fuer storno
			//this.add_CompareRule("WIEGEKARTENKENNER");
			this.add_CompareRule("ID_VPOS_KON_ZUGEORD");
			this.add_CompareRule("ID_VPOS_TPA_FUHRE_ZUGEORD");
			this.add_CompareRule("BEMERKUNG_INTERN");
			this.add_CompareRule("ID_VPOS_X_NACHFOLGER");
			this.add_CompareRule("ID_VPOS_PREISKLAMMER");
			this.add_CompareRule("EINZELPREIS_FW");
			this.add_CompareRule("EINZELPREIS_ABZUG_FW");
			this.add_CompareRule("EINZELPREIS_RESULT_FW");
			this.add_CompareRule("WAEHRUNGSKURS");
			this.add_CompareRule("ID_WAEHRUNG_FREMD");
			this.add_CompareRule("ID_VPOS_TPA_FUHRE_ORT_ZUGEORD");
			
			this.add_CompareRule(new ownCompareRuleNegativ("ANZAHL"));
			this.add_CompareRule(new ownCompareRuleNegativ("ANZAHL_ABZUG"));
			this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS"));
			this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS_FW"));
			this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS_ABZUG"));
			this.add_CompareRule(new ownCompareRuleNegativ("GESAMTPREIS_ABZUG_FW"));

			this.add_CompareRule(new ownCompareRuleSingleString("DELETED"));
			this.add_CompareRule(new ownCompareRuleSingleString("IST_SONDERPOSITION"));

			this.add_CompareRule(new ownCompareRuleSingleString("GEBUCHT"));
			

			//2011-01-28: weitere gleichheitskriterien abschalten
//			this.add_CompareRule(new ownCompareRuleSingleString("OHNE_STEUER"));
//			this.add_CompareRule("EU_STEUER_VERMERK");
//			this.add_CompareRule("AUSFUEHRUNGSDATUM");
//			this.add_CompareRule("BESTELLNUMMER");
//			this.add_CompareRule("EUNOTIZ");
//			this.add_CompareRule("EUCODE");
//			this.add_CompareRule("ID_STRECKEN_DEF");
//			// hier wird der Skonto NULL = 0 gleich behandelt
//			this.add_CompareRule(new ownCompareRuleSingle_NULL_IST_GLEICH_0("SKONTO_PROZENT"));
		
			//2011-06-09: aenderungen an den zusaetzlichen abzugesfeldern muessen auch verglichen werden
			this.add_CompareRule(new ownCompareRuleNegativ("ANZAHL_ABZUG_LAGER"));
			this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_MGE"));
			this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_MGE_FW"));
			this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_AUF_NETTOMGE"));
			this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_AUF_NETTOMGE_FW"));
			this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_VORAUSZAHLUNG"));
			this.add_CompareRule(new ownCompareRuleNegativ("GPREIS_ABZ_VORAUSZAHLUNG_FW"));
			
			this.add_CompareRule("EPREIS_RESULT_NETTO_MGE");
			this.add_CompareRule("EPREIS_RESULT_NETTO_MGE_FW");
			//---2011-06-09

			
			
		}

		
		private class ownCompareRuleNegativ extends MyRecordCompareRule
		{
			public ownCompareRuleNegativ(String cfieldname)
			{
				super(cfieldname);
			}

			@Override
			public boolean IsEqual(MyRECORD rec1, MyRECORD rec2)	throws myException
			{
				BigDecimal  bd1 = rec1.get_bdValue(this.get_cFIELDNAME(), new BigDecimal(0), 6);
				BigDecimal  bd2 = rec2.get_bdValue(this.get_cFIELDNAME(), new BigDecimal(0), 6);

				bd2 = bd2.multiply(new BigDecimal(-1));
				
				return (bd1.compareTo(bd2)==0);
			}
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


		private class ownCompareRuleSingle_NULL_IST_GLEICH_0 extends MyRecordCompareRule
		{
			public ownCompareRuleSingle_NULL_IST_GLEICH_0(String cfieldname)
			{
				super(cfieldname);
			}

			@Override
			public boolean IsEqual(MyRECORD rec1, MyRECORD rec2)	throws myException
			{
				BigDecimal  bd1 = rec1.get_bdValue(this.get_cFIELDNAME(), new BigDecimal(0), 3);
				BigDecimal  bd2 = rec2.get_bdValue(this.get_cFIELDNAME(), new BigDecimal(0), 3);
				return ((bd1.compareTo(bd2)==0));
			}
		}

	
	
	}
	
	
	
}
