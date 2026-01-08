package rohstoff.Echo2BusinessLogic._KORRE.LIEF_BED;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_PROTOKOLLE_BATCH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_ORT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public abstract class XX_KorrRule
{
	private long   lOwnZaehler = 0;
	
	public static String KORR_RULE_WE_EIGENER_LKW_FCA=			"KORR_RULE_WE_EIGENER_LKW_FCA";
	public static String KORR_RULE_WA_EIGENER_LKW_CPT=			"KORR_RULE_WA_EIGENER_LKW_CPT";
	public static String KORR_RULE_WE_FREMD_LKW_AUSWEIS_DDP=	"KORR_RULE_WE_FREMD_LKW_AUSWEIS_DDP";
	public static String KORR_RULE_WE_FREMD_LKW_TPA_FCA=		"KORR_RULE_WE_FREMD_LKW_TPA_FCA";
	
	public abstract String 		get_UebersichtsText();
	public abstract String  	get_Kennung4ProtokollTabelle();
	public abstract String  	get_Info4ProtokollTabelle();
	public abstract boolean 	ist_Fuer_WE_Seite();
	public abstract String  	__suchePassende_LiefBedingungen(	String 			cID_ADRESSE_START, 
																	String 			cID_ADRESSE_ZIEL, 
																	String 			cLKWNR, 
																	String          cID_VPOS_TPA,
																	KorrCounter 	oCount
																	) throws myException;
	
	
	/*
	 * prueft kombination aus ID_ADRESSEN und der pruefsituation WA / WE
	 */
	public boolean bPruefe_RegelIstAnwendbar(String cID_ADRESSE_START, String cID_ADRESSE_ZIEL) {
		if (S.isFull(cID_ADRESSE_START) && S.isFull(cID_ADRESSE_ZIEL)) {
			if (this.ist_Fuer_WE_Seite()) {
				if (!cID_ADRESSE_START.equals(bibALL.get_ID_ADRESS_MANDANT())) {
					if (cID_ADRESSE_ZIEL.equals(bibALL.get_ID_ADRESS_MANDANT())) {
						return true;
					}
				}
			} else {
				if (cID_ADRESSE_START.equals(bibALL.get_ID_ADRESS_MANDANT())) {
					if (!cID_ADRESSE_ZIEL.equals(bibALL.get_ID_ADRESS_MANDANT())) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	/**
	 * variante fuer fuhren
	 * @param recFuhre
	 * @param oCount
	 * @param bUpdate
	 * @return
	 * @throws myException
	 */
	public String PRUEFE_LieferBedingung(RECORD_VPOS_TPA_FUHRE recFuhre, KorrCounter oCount, boolean bUpdate) throws myException {
		String cLiefBedRueck= this.__suchePassende_LiefBedingungen(	recFuhre.get_ID_ADRESSE_START_cUF_NN("-"), 
																	recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-"), 
																	recFuhre.get_TRANSPORTKENNZEICHEN_cUF_NN(""),
																	recFuhre.get_ID_VPOS_TPA_cUF_NN(""),
																	oCount);
		
		//nur wenn __suchePassende_LiefBedingungen fuer diesen fall zutrifft, dann wird etwas zureckgeliefert,  
		if (S.isFull(cLiefBedRueck)) {
			if (this.ist_Fuer_WE_Seite()) {
				this.increase_OwnCounter();
				if (S.isFull(recFuhre.get_LIEFERBED_ALTERNATIV_EK_cUF_NN(""))) {
					oCount.iZaehlerSachverhaltGefundenUndBereitsVorhanden++;
				} else {
					if (bUpdate) {
						recFuhre.set_NEW_VALUE_LIEFERBED_ALTERNATIV_EK(cLiefBedRueck);
						if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(recFuhre.get_SQL_UPDATE_STD(), true)) {
							oCount.iZaehlerSachverhaltGefundenUndUpdateFehler++;
							DEBUG.System_println("Fehler: "+recFuhre.get_SQL_UPDATE_STD());
						} else {
							oCount.iZaehlerSachverhaltGefundenUndAktualisiert++;
							DEBUG.System_println("Aktualisierung: "+recFuhre.get_SQL_UPDATE_STD());
							
							this.schreibe_ProtokollEintrag(recFuhre);

						}
					}
				}
			} else {
				this.increase_OwnCounter();
				if (S.isFull(recFuhre.get_LIEFERBED_ALTERNATIV_VK_cUF_NN(""))) {
					oCount.iZaehlerSachverhaltGefundenUndBereitsVorhanden++;
				} else {
					if (bUpdate) {
						recFuhre.set_NEW_VALUE_LIEFERBED_ALTERNATIV_VK(cLiefBedRueck);
						if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(recFuhre.get_SQL_UPDATE_STD(), true)) {
							oCount.iZaehlerSachverhaltGefundenUndUpdateFehler++;
							DEBUG.System_println("Fehler: "+recFuhre.get_SQL_UPDATE_STD());
						} else {
							oCount.iZaehlerSachverhaltGefundenUndAktualisiert++;
							DEBUG.System_println("Aktualisierung: "+recFuhre.get_SQL_UPDATE_STD());
							
							this.schreibe_ProtokollEintrag(recFuhre);

						}

					}
				}
			}
		}
		
		return cLiefBedRueck;
	}


	/**
	 * variante fuer fuhrenorte, hier wird die fuhre mitgeliefert, um zeit zu sparen
	 * @param recFuhreOrt
	 * @param recFuhre
	 * @param oCount
	 * @param bUpdate
	 * @return
	 * @throws myException
	 */
	public String PRUEFE_LieferBedingung(RECORD_VPOS_TPA_FUHRE_ORT recFuhreOrt,RECORD_VPOS_TPA_FUHRE recFuhre, KorrCounter oCount,boolean bUpdate) throws myException {
		
		String cLiefBedRueck = null;
		
		//hier pruefen, ob die Regel zur Seite des ortes korreliert, sonst wird eine lieferbedingung eingetragen, obwohl der ord auf der falschen seite ist
		if (recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK") && !this.ist_Fuer_WE_Seite()) {
			return null;
		}
		
		if (!recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK") && this.ist_Fuer_WE_Seite()) {
			return null;
		}
		
		
		if (recFuhreOrt.get_DEF_QUELLE_ZIEL_cUF_NN("").equals("EK")) { 
			cLiefBedRueck=  this.__suchePassende_LiefBedingungen(	recFuhreOrt.get_ID_ADRESSE_cUF_NN("-"), 
																	recFuhre.get_ID_ADRESSE_ZIEL_cUF_NN("-"), 
																	recFuhre.get_TRANSPORTKENNZEICHEN_cUF_NN(""),
																	recFuhre.get_ID_VPOS_TPA_cUF_NN(""),
																	oCount);
			
		} else {
			cLiefBedRueck=  this.__suchePassende_LiefBedingungen(	recFuhre.get_ID_ADRESSE_START_cUF_NN("-"), 
																	recFuhreOrt.get_ID_ADRESSE_cUF_NN("-"), 
																	recFuhre.get_TRANSPORTKENNZEICHEN_cUF_NN(""),
																	recFuhre.get_ID_VPOS_TPA_cUF_NN(""),		
																	oCount);
		}
		if (S.isFull(cLiefBedRueck)) {
			this.increase_OwnCounter();
			if (S.isFull(recFuhreOrt.get_LIEFERBED_ALTERNATIV_cUF_NN(""))) {
				oCount.iZaehlerSachverhaltGefundenUndBereitsVorhanden++;
			} else {
				if (bUpdate) {
					recFuhreOrt.set_NEW_VALUE_LIEFERBED_ALTERNATIV(cLiefBedRueck);
					if (!bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(recFuhreOrt.get_SQL_UPDATE_STD(), true)) {
						oCount.iZaehlerSachverhaltGefundenUndUpdateFehler++;
						DEBUG.System_println("Fehler: "+recFuhreOrt.get_SQL_UPDATE_STD());
					} else {
						oCount.iZaehlerSachverhaltGefundenUndAktualisiert++;
						DEBUG.System_println("Aktualisierung: "+recFuhreOrt.get_SQL_UPDATE_STD());
						
						//protokoll schreiben 
						this.schreibe_ProtokollEintrag(recFuhreOrt);
					}
				}
			}
		}
		
		return cLiefBedRueck;
	}

	
	private boolean schreibe_ProtokollEintrag(MyRECORD  oRecord) throws myException {
		//protokoll schreiben 
		RECORDNEW_PROTOKOLLE_BATCH recNew = new RECORDNEW_PROTOKOLLE_BATCH();
		recNew.set_NEW_VALUE_TABLE_NAME_BASE(oRecord.get_TABLE_NAME().substring(3));
		recNew.set_NEW_VALUE_ID_TABLE(oRecord.get_UnFormatedValue("ID_"+oRecord.get_TABLE_NAME().substring(3)));
		recNew.set_NEW_VALUE_JOB_KENNUNG(this.get_Kennung4ProtokollTabelle());
		recNew.set_NEW_VALUE_JOB_INFO_LANG(this.get_Info4ProtokollTabelle());
		MySqlStatementBuilder  oSTB = recNew.get__StatementBuilder(true, true);
		oSTB.addSQL_Paar(_DB.PROTOKOLLE_BATCH$ZEITSTEMPEL, "SYSDATE", false);
		return bibDB.ExecSQL(oSTB.get_CompleteInsertString(_DB.PROTOKOLLE_BATCH, bibE2.cTO()), true);
	}

	
	
	public void increase_OwnCounter() {
		this.lOwnZaehler++;
	}
	
	public long get_lOwnZaehler() {
		return lOwnZaehler;
	}
	
	
}
