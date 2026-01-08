package rohstoff.utils.SQL_DAEMONS;

import java.math.BigDecimal;
import java.util.Vector;
import java.util.Map.Entry;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_VPOS_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG_DRUCK;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox_LOG_INFO;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MyRECORD_LIST;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VectorSingle;

public class FIBU_Daemon extends XX_SQL_STACK_DAEMON {

	
	
	/*
	 * ein DBToolBox-validator, der kontrakte ueberwacht, wenn in einem speichervorgang ein SQL-statement (INSERT oder UPDATE)
	 * vorkommt, in dem ein speichervorgang einer tabelle aus den folgenden vorkommt: 
	 * JT_VPOS_RG
	 * Er macht eine Endpreiskalkulation und eine abzugskalkulation 
	 */

	
	public FIBU_Daemon() throws myException 
	{
		super();
	}


	
	// wird unmittelbar nach jedem einzelnen sql-statement ausgefuehrt
	public MyE2_MessageVector collect_LOG_INFOs(Vector<MyDBToolBox_LOG_INFO> vLogInfos, MyConnection oConn) throws myException 
	{
		//die betroffenen tabellen-LogInfos sammeln
		for (MyDBToolBox_LOG_INFO test: vLogInfos)
		{
			
			if (    (test.get_cTABLENAME().equals("JT_VKOPF_RG") ||
					test.get_cTABLENAME().equals("JT_VKOPF_RG_DRUCK"))
					&& !test.get_IS_DELETE())
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

		VectorSingle   vID_VKOPF_RG_Single = new VectorSingle();   //jeden beleg nur 1x verarbeiten, sonst koennten mehrere fibu-eintraege in der gleichen transaktion gesammelt werden 
		
		
		for (MyDBToolBox_LOG_INFO test: this.get_vLogInfos())
		{
			RECORD_VKOPF_RG recVKOPF_RG = null;
			if (test.get_cTABLENAME().toUpperCase().equals("JT_VKOPF_RG"))
			{
				recVKOPF_RG = new RECORD_VKOPF_RG(test.get_cID_TABLE());
			}
			else if(test.get_cTABLENAME().toUpperCase().equals("JT_VKOPF_RG_DRUCK"))
			{
				recVKOPF_RG = new RECORD_VKOPF_RG_DRUCK(test.get_cID_TABLE()).get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
			}

			if (recVKOPF_RG==null)
			{
				throw new myException("Error in FIBU_Deamon !! ");   //duerfte nie passieren
			}

			if (vID_VKOPF_RG_Single.contains(recVKOPF_RG.get_ID_VKOPF_RG_cUF()))
			{
				continue;
			}
			vID_VKOPF_RG_Single.add(recVKOPF_RG.get_ID_VKOPF_RG_cUF());
			
			RECORD_FIBU  recFibu = null;
			if (recVKOPF_RG.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get_vKeyValues().size()==1)
			{
				recFibu = recVKOPF_RG.get_DOWN_RECORD_LIST_FIBU_id_vkopf_rg().get(0);
			}
			
			PreisRechner  PR = new PreisRechner(recVKOPF_RG,oConn);
			
			if (!PR.bWirdGebuchtStichdatumOK)
			{
				continue;
			}
			
			
			if (S.isFull(recVKOPF_RG.get_BUCHUNGSNUMMER_cUF_NN("")))   //dann wird ein satz geschrieben oder veraendert
			{
						
				if (recFibu != null)  //UPDATE erzeugen
				{
					
					Vector<String> vSQL_Help = new Vector<String>();
					
					if (recFibu.get_VORLAEUFIG_cUF_NN("Y").equals(recVKOPF_RG.get_ABGESCHLOSSEN_cUF_NN("N")))  // vorlaeufig YES heist abgeschlossen NO
					{
						String cNewValueVorlaeufig = "N";
						if (recVKOPF_RG.get_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
						{
							cNewValueVorlaeufig = "Y";
						}
						recFibu.set_NEW_VALUE_VORLAEUFIG(cNewValueVorlaeufig);
					}

					if (recFibu.get_NETTOSUMME_BASIS_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdGesamtBetrag_Netto)!=0)
						{	recFibu.set_NEW_VALUE_NETTOSUMME_BASIS_WAEHRUNG(		this.form(PR.bdGesamtBetrag_Netto));}
					
					if (recFibu.get_STEUERSUMME_BASIS_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdMWST)!=0)
						{	recFibu.set_NEW_VALUE_STEUERSUMME_BASIS_WAEHRUNG(		this.form(PR.bdMWST));	}
					
					if (recFibu.get_ENDBETRAG_BASIS_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdGesamtBetrag_Brutto)!=0)
						{	recFibu.set_NEW_VALUE_ENDBETRAG_BASIS_WAEHRUNG(			this.form(PR.bdGesamtBetrag_Brutto));	}
					
					if (recFibu.get_SKONTOBETRAG_BASIS_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdSkontoBetrag)!=0)
						{	recFibu.set_NEW_VALUE_SKONTOBETRAG_BASIS_WAEHRUNG(		this.form(PR.bdSkontoBetrag));	}
					
					if (recFibu.get_ZAHLUNGSBETRAG_BASIS_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdGesamtBetrag_Brutto_inclSkonto)!=0)
						{recFibu.set_NEW_VALUE_ZAHLUNGSBETRAG_BASIS_WAEHRUNG(	this.form(PR.bdGesamtBetrag_Brutto_inclSkonto));}
					
					if (recFibu.get_NETTOSUMME_FREMD_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdGesamtBetrag_Netto_FW)!=0)
						{recFibu.set_NEW_VALUE_NETTOSUMME_FREMD_WAEHRUNG(		this.form(PR.bdGesamtBetrag_Netto_FW));	}

					if (recFibu.get_STEUERSUMME_FREMD_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdMWST_FW)!=0)
						{recFibu.set_NEW_VALUE_STEUERSUMME_FREMD_WAEHRUNG(		this.form(PR.bdMWST_FW));}
					
					if (recFibu.get_ENDBETRAG_FREMD_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdGesamtBetrag_Brutto_FW)!=0)
						{recFibu.set_NEW_VALUE_ENDBETRAG_FREMD_WAEHRUNG(			this.form(PR.bdGesamtBetrag_Brutto_FW));}
					
					if (recFibu.get_SKONTOBETRAG_FREMD_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdSkontoBetrag_FW)!=0)
						{recFibu.set_NEW_VALUE_SKONTOBETRAG_FREMD_WAEHRUNG(		this.form(PR.bdSkontoBetrag_FW));}
					
					if (recFibu.get_ZAHLUNGSBETRAG_FREMD_WAEHRUNG_bdValue(new BigDecimal(0)).compareTo(PR.bdGesamtBetrag_Brutto_inclSkonto_FW)!=0)
						{recFibu.set_NEW_VALUE_ZAHLUNGSBETRAG_FREMD_WAEHRUNG(	this.form(PR.bdGesamtBetrag_Brutto_inclSkonto_FW));	}
					
					if (recFibu.get_StatementBuilderFilledWithActualValues_ONLY_CHANGED_FIELDS().size()>0)
					{
						recFibu.set_NEW_VALUE_DATUMVERAENDERUNG(bibALL.get_cDateNOW());
						vSQL_Help.add(recFibu.get_SQL_UPDATE_STATEMENT(null, true));
					}
					
	
					if (vSQL_Help.size()>0)  //hat sich in diesem fibu-satz was geaendert ?
					{
						/*
						 * dann hat sich was geaendert, d.h. es muss eine evtl. klammer aufgehoben werden
						 */
						if (recFibu.is_BUCHUNG_GESCHLOSSEN_YES() && (! recFibu.get_BUCHUNGSBLOCK_NR_cUF_NN("-").equals("-"))) 
						{
							//dann wird das abgeschlossen aufgehoben und der buchungsblock entfernt
							vSQL_Help.add("UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNG_GESCHLOSSEN='N',RESTBETRAG_FREMD_WAEHRUNG=ZAHLUNGSBETRAG_FREMD_WAEHRUNG*FAKTOR_BUCHUNG_PLUS_MINUS  WHERE BUCHUNGSBLOCK_NR="+recFibu.get_BUCHUNGSBLOCK_NR_cUF_NN("-"));
						}
					}
					
					vSQL_Rueck.addAll(vSQL_Help);
	
				}
				else    // insert erzeugen
				{
					MySqlStatementBuilder stmtFIBU = new MySqlStatementBuilder();
					

					stmtFIBU.addSQL_Paar("ID_FIBU",			"SEQ_FIBU.NEXTVAL",false);
					stmtFIBU.addSQL_Paar("ID_VKOPF_RG",		recVKOPF_RG.get_ID_VKOPF_RG_VALUE_FOR_SQLSTATEMENT(),false);
					stmtFIBU.addSQL_Paar("BUCHUNGSBLOCK_NR","SEQ_BUCHUNGSBLOCK_FIBU.NEXTVAL",false);  //alles bekommt erstmal einen Buchungsblock
					stmtFIBU.addSQL_Paar("NETTOSUMME_BASIS_WAEHRUNG",this.form4Sql(PR.bdGesamtBetrag_Netto));
					stmtFIBU.addSQL_Paar("STEUERSUMME_BASIS_WAEHRUNG",this.form4Sql(PR.bdMWST));
					stmtFIBU.addSQL_Paar("ENDBETRAG_BASIS_WAEHRUNG",this.form4Sql(PR.bdGesamtBetrag_Brutto));
					stmtFIBU.addSQL_Paar("SKONTOBETRAG_BASIS_WAEHRUNG",this.form4Sql(PR.bdSkontoBetrag));
					stmtFIBU.addSQL_Paar("ZAHLUNGSBETRAG_BASIS_WAEHRUNG",this.form4Sql(PR.bdGesamtBetrag_Brutto_inclSkonto));
					stmtFIBU.addSQL_Paar("NETTOSUMME_FREMD_WAEHRUNG",this.form4Sql(PR.bdGesamtBetrag_Netto_FW));
					stmtFIBU.addSQL_Paar("STEUERSUMME_FREMD_WAEHRUNG",this.form4Sql(PR.bdMWST_FW));
					stmtFIBU.addSQL_Paar("ENDBETRAG_FREMD_WAEHRUNG",this.form4Sql(PR.bdGesamtBetrag_Brutto_FW));
					stmtFIBU.addSQL_Paar("SKONTOBETRAG_FREMD_WAEHRUNG",this.form4Sql(PR.bdSkontoBetrag_FW));
					stmtFIBU.addSQL_Paar("ZAHLUNGSBETRAG_FREMD_WAEHRUNG",this.form4Sql(PR.bdGesamtBetrag_Brutto_inclSkonto_FW));
					
					//2011-03-17: neues Feld fuer mahnung von teilrechnungsbetraegen (wird nur geschrieben, wenn geld rein kommt)
					if (PR.bdFaktorGeldkonto.multiply(PR.bdGesamtBetrag_Brutto_inclSkonto_FW).compareTo(BigDecimal.ZERO)>0)
					{
						stmtFIBU.addSQL_Paar(RECORD_FIBU.FIELD__RESTBETRAG_FREMD_WAEHRUNG,this.form4Sql(PR.bdFaktorGeldkonto.multiply(PR.bdGesamtBetrag_Brutto_inclSkonto_FW)));
					}
	
					stmtFIBU.addSQL_Paar("FAKTOR_BUCHUNG_PLUS_MINUS",this.form4Sql(PR.bdFaktorGeldkonto));
					stmtFIBU.addSQL_Paar("WAEHRUNG_FREMD",recVKOPF_RG.get_WAEHRUNG_FREMD_VALUE_FOR_SQLSTATEMENT());
					stmtFIBU.addSQL_Paar("ID_ADRESSE",recVKOPF_RG.get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT());
					stmtFIBU.addSQL_Paar("ID_ADRESSE",recVKOPF_RG.get_ID_ADRESSE_VALUE_FOR_SQLSTATEMENT());
					stmtFIBU.addSQL_Paar("BEARBEITERKUERZEL",bibALL.MakeSql(bibALL.get_KUERZEL()));
					stmtFIBU.addSQL_Paar("BUCHUNGSTYP",bibALL.MakeSql(PR.BUCHUNGS_TYP));
					stmtFIBU.addSQL_Paar("BUCHUNGSDATUM",bibALL.MakeSql(bibALL.get_cDateNOWInverse("-")));
					stmtFIBU.addSQL_Paar("DATUMVERAENDERUNG",bibALL.MakeSql(bibALL.get_cDateNOWInverse("-")));
					stmtFIBU.addSQL_Paar("KORREKTURBUCHUNG",bibALL.MakeSql("N"));
					
					stmtFIBU.addSQL_Paar("ZAHLUNGSZIEL",bibALL.MakeSql(PR.ZahlungsDatumBerechnet));

					
					String cNewValueVorlaeufig = "N";
					if (recVKOPF_RG.get_ABGESCHLOSSEN_cUF_NN("N").equals("N"))
					{
						cNewValueVorlaeufig = "Y";
					}
					stmtFIBU.addSQL_Paar("VORLAEUFIG",		cNewValueVorlaeufig,true);

					String cInfo = "";
					if (recVKOPF_RG.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG))
					{
						cInfo="Rechnung: "+recVKOPF_RG.get_BUCHUNGSNUMMER_cUF_NN("-")+" vom "+recVKOPF_RG.get_DRUCKDATUM_cF_NN("-");
					}
					else
					{
						cInfo="Gutschrift: "+recVKOPF_RG.get_BUCHUNGSNUMMER_cUF_NN("-")+" vom "+recVKOPF_RG.get_DRUCKDATUM_cF_NN("-");
					}
					stmtFIBU.addSQL_Paar("BUCHUNGSINFO",bibALL.MakeSql(cInfo));
					
					vSQL_Rueck.add(stmtFIBU.get_CompleteInsertString("JT_FIBU", bibE2.cTO()));
				}
			}
			else   //dann kann nur eine reaktion erfolgen, wenn der fibu-beleg bereits vorhanden ist (duefte nicht vorkommen)
			{
				if (recFibu != null)
				{
					recFibu.set_NEW_VALUE_VORLAEUFIG("Y");
					recFibu.set_NEW_VALUE_BUCHUNG_GESCHLOSSEN("N");
					recFibu.set_NEW_VALUE_BUCHUNGSBLOCK_NR(bibALL.get_SEQUENCE_VECT_VALUES("SEQ_BUCHUNGSBLOCK_FIBU", 1).get(0));
					vSQL_Rueck.add(recFibu.get_SQL_UPDATE_STATEMENT(null, true));
					//alle buchungen zu diesem buchungsblock ebenfall nicht geschlossen
					//2015-02-13: KORREKTUR-FIBU-BUG: mandanten-id zur bedingung
					vSQL_Rueck.add("UPDATE "+bibE2.cTO()+".JT_FIBU SET BUCHUNG_GESCHLOSSEN='N'  WHERE  ID_MANDANT="+bibALL.get_ID_MANDANT()+" AND  BUCHUNGSBLOCK_NR="+recFibu.get_BUCHUNGSBLOCK_NR_cUF_NN("-"));
				}
				
			}
		}
		return vSQL_Rueck;
	}

	
	private String form(BigDecimal bdWert)
	{
		return MyNumberFormater.formatDez(bdWert, 2, false);
	}
	
	private String form4Sql(BigDecimal bdWert)
	{
		String cHelp= MyNumberFormater.formatDez(bdWert,2,false,'.','@',true);
		return bibALL.ReplaceTeilString(cHelp, "@", "");
	}
	
	
	
	
	/*
	 * WICHTIG: diese klasse muss exakt so rechnen wie der report des Rechnungs-/Gutschrift-Belegs !!!
	 *          
	 * AUSSERDEM: der preisrechner bestimmt, ob alle positionen in der rechnung ein leistungdatum > dem mandanten-fibu-stichdatum haben          
	 */
	private class PreisRechner 
	{
		public BigDecimal bdGesamtBetrag_Netto = new BigDecimal(0);
		public BigDecimal bdGesamtBetrag_Netto_FW = new BigDecimal(0);
		public BigDecimal bdMWST = new BigDecimal(0);
		public BigDecimal bdMWST_FW = new BigDecimal(0);

		public BigDecimal bdGesamtBetrag_Brutto = new BigDecimal(0);
		public BigDecimal bdGesamtBetrag_Brutto_FW = new BigDecimal(0);
		
		public BigDecimal bdSkontoBetrag = new BigDecimal(0);
		public BigDecimal bdSkontoBetrag_FW = new BigDecimal(0);

		public BigDecimal bdGesamtBetrag_Brutto_inclSkonto = 		new BigDecimal(0);
		public BigDecimal bdGesamtBetrag_Brutto_inclSkonto_FW = 	new BigDecimal(0);
		
		public BigDecimal bdSkontoProzent = BigDecimal.ZERO;
		public BigDecimal bdFaktorWarenkonto = null; 
		public BigDecimal bdFaktorGeldkonto = null;
		
		public String     BUCHUNGS_TYP = null;
		
		public String     ZahlungsDatumBerechnet = null;
		
		
		public boolean    bWirdGebuchtStichdatumOK = true;
		
		private RECORD_VKOPF_RG recVKOPF = null;

		public PreisRechner(RECORD_VKOPF_RG recVKOPF, MyConnection oConn) throws myException
		{
			super();
			this.recVKOPF = recVKOPF;
			
			this.BUCHUNGS_TYP = this.recVKOPF.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG)?myCONST.BT_DRUCK_RECHNUNG:myCONST.BT_DRUCK_GUTSCHRIFT;
			
			bdFaktorWarenkonto = 	this.recVKOPF.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG)?new BigDecimal(-1):new BigDecimal(1);
			bdFaktorGeldkonto = 	this.recVKOPF.get_VORGANG_TYP_cUF().equals(myCONST.VORGANGSART_RECHNUNG)?new BigDecimal(1):new BigDecimal(-1);
			
			//zahlungsdatum steht in den positionen
			//ebenfalls die skonto-prozent-werte
			RECLIST_VPOS_RG reclistVpos = this.recVKOPF.get_DOWN_RECORD_LIST_VPOS_RG_id_vkopf_rg("NVL(DELETED,'N')='N'", null, true);
			if (reclistVpos.get_vKeyValues().size()>0)
			{

				this.bdSkontoProzent = 			reclistVpos.get(0).get_SKONTO_PROZENT_bdValue(BigDecimal.ZERO);
				this.ZahlungsDatumBerechnet = reclistVpos.get(0).get_ZAHLUNGSBED_CALC_DATUM_cF_NN("01.01.1990");
				
				this.ZahlungsDatumBerechnet = 	this.ZahlungsDatumBerechnet.substring(6,10)+"-"+
												this.ZahlungsDatumBerechnet.substring(3,5)+"-"+
												this.ZahlungsDatumBerechnet.substring(0,2)+"-";
												
				
			}
			
			
			String cDateStichtag = new MyDate(bibALL.get_RECORD_MANDANT().get_STICHTAG_START_FIBU_cF(),"","").get_cDBFormatErgebnis();
			for (int i=0;i<reclistVpos.get_vKeyValues().size();i++)
			{
				String cVergleich = new MyDate(reclistVpos.get(i).get_AUSFUEHRUNGSDATUM_cF(),"","").get_cDBFormatErgebnis();
				if (cDateStichtag.compareTo(cVergleich)>0)
				{
					this.bWirdGebuchtStichdatumOK = false;
				}
			}
			
			
			String cSQL = "SELECT"+ 
								" NVL(TO_CHAR(JT_VPOS_RG.STEUERSATZ),'-') AS CSTEUERSATZ, " +
								" SUM(JT_VPOS_RG.GESAMTPREIS_FW*JT_VPOS_RG.LAGER_VORZEICHEN-"+
								"    NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG_FW,0)*JT_VPOS_RG.LAGER_VORZEICHEN) AS GESAMTPREIS_FW,"+
								" SUM(JT_VPOS_RG.GESAMTPREIS*JT_VPOS_RG.LAGER_VORZEICHEN-"+
								"    NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG,0)*JT_VPOS_RG.LAGER_VORZEICHEN) AS GESAMTPREIS,"+
								" ROUND(SUM((JT_VPOS_RG.GESAMTPREIS_FW*JT_VPOS_RG.LAGER_VORZEICHEN-"+
								"           NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG_FW*JT_VPOS_RG.LAGER_VORZEICHEN,0))*NVL(JT_VPOS_RG.STEUERSATZ,0)/100),2) AS MWST_FW ,"+
								" ROUND(SUM((JT_VPOS_RG.GESAMTPREIS*JT_VPOS_RG.LAGER_VORZEICHEN-"+
								"           NVL(JT_VPOS_RG.GESAMTPREIS_ABZUG*JT_VPOS_RG.LAGER_VORZEICHEN,0))*NVL(JT_VPOS_RG.STEUERSATZ,0)/100),2) AS MWST ,"+
								" NVL(JT_VPOS_RG.STEUERSATZ,0) AS STEUERSATZ"+
								" FROM"+ 
								" JT_VKOPF_RG, JT_VPOS_RG"+
								" WHERE"+ 
								" JT_VKOPF_RG.ID_VKOPF_RG = JT_VPOS_RG.ID_VKOPF_RG"+
								" AND"+
								" JT_VKOPF_RG.ID_VKOPF_RG ="+this.recVKOPF.get_ID_VKOPF_RG_cUF()+
								" AND"+
								" JT_VPOS_RG.POSITION_TYP = 'ARTIKEL'"+
								" AND"+
								" NVL(JT_VPOS_RG.DELETED,'N')='N'"+
								" GROUP BY"+ 
								" JT_VPOS_RG.STEUERSATZ"+
								" ORDER BY"+ 
								" NVL(JT_VPOS_RG.STEUERSATZ,0)";
			
			MyRECORD_LIST recListTeilSummen = new MyRECORD_LIST(cSQL,"CSTEUERSATZ",oConn);
			
			
			
			
			for (Entry<String, MyRECORD> oEntry: recListTeilSummen.entrySet())
			{
				bdGesamtBetrag_Netto = 		bdGesamtBetrag_Netto.add(oEntry.getValue().get_bdValue("GESAMTPREIS", BigDecimal.ZERO, 2));
				bdGesamtBetrag_Netto_FW = 	bdGesamtBetrag_Netto_FW.add(oEntry.getValue().get_bdValue("GESAMTPREIS_FW", BigDecimal.ZERO, 2));
				bdMWST = 					bdMWST.add(oEntry.getValue().get_bdValue("MWST", BigDecimal.ZERO, 2));
				bdMWST_FW = 				bdMWST_FW.add(oEntry.getValue().get_bdValue("MWST_FW", BigDecimal.ZERO, 2));
				bdGesamtBetrag_Brutto =     bdGesamtBetrag_Brutto.add(oEntry.getValue().get_bdValue("GESAMTPREIS", BigDecimal.ZERO, 2)).add(oEntry.getValue().get_bdValue("MWST", BigDecimal.ZERO, 2));
				bdGesamtBetrag_Brutto_FW =  bdGesamtBetrag_Brutto_FW.add(oEntry.getValue().get_bdValue("GESAMTPREIS_FW", BigDecimal.ZERO, 2)).add(oEntry.getValue().get_bdValue("MWST_FW", BigDecimal.ZERO, 2));
			}

			bdGesamtBetrag_Brutto_inclSkonto = bdGesamtBetrag_Brutto.multiply(
				    new java.math.BigDecimal(1).subtract(bdSkontoProzent.divide(new java.math.BigDecimal(100) ))).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

			bdSkontoBetrag = bdGesamtBetrag_Brutto.subtract(bdGesamtBetrag_Brutto_inclSkonto);

			bdGesamtBetrag_Brutto_inclSkonto_FW = bdGesamtBetrag_Brutto_FW.multiply(
				    new java.math.BigDecimal(1).subtract(bdSkontoProzent.divide(new java.math.BigDecimal(100) ))).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

			bdSkontoBetrag_FW = bdGesamtBetrag_Brutto_FW.subtract(bdGesamtBetrag_Brutto_inclSkonto_FW);
			
			
			//jetzt den faktor (-1 fuer rechnung und +1 fuer gutschrift mitrechnen, damit im normalen alles positiv dasteht)
			bdGesamtBetrag_Netto = 					bdGesamtBetrag_Netto.multiply(bdFaktorWarenkonto);
			bdGesamtBetrag_Netto_FW = 				bdGesamtBetrag_Netto_FW.multiply(bdFaktorWarenkonto);
			bdMWST = 								bdMWST.multiply(bdFaktorWarenkonto);
			bdMWST_FW = 							bdMWST_FW.multiply(bdFaktorWarenkonto);
			bdGesamtBetrag_Brutto = 				bdGesamtBetrag_Brutto.multiply(bdFaktorWarenkonto);
			bdGesamtBetrag_Brutto_FW = 				bdGesamtBetrag_Brutto_FW.multiply(bdFaktorWarenkonto);
			
			bdSkontoBetrag = 						bdSkontoBetrag.multiply(bdFaktorWarenkonto);
			bdSkontoBetrag_FW = 					bdSkontoBetrag_FW.multiply(bdFaktorWarenkonto);

			bdGesamtBetrag_Brutto_inclSkonto = 		bdGesamtBetrag_Brutto_inclSkonto.multiply(bdFaktorWarenkonto);
			bdGesamtBetrag_Brutto_inclSkonto_FW = 	bdGesamtBetrag_Brutto_inclSkonto_FW.multiply(bdFaktorWarenkonto);
		}
	}
	
}
