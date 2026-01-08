package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_HANDELSDEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class TR___CreateStatement_to_find_Fuhre {

	private RECORD_HANDELSDEF  	rec_HANDELS_DEF = null;
	private Integer   			i_AnzahlFuhren = null;
	private String   			cSQL_FAST = null;
	private String   			cSQL_SLOW = null;
	private String 				cPlaceHolderBaseTable = "#BASETABLE#";

	public TR___CreateStatement_to_find_Fuhre(RECORD_HANDELSDEF recHANDELS_DEF, Integer iAnzahlFuhren ) {
		super();
		this.rec_HANDELS_DEF = 	recHANDELS_DEF;
		this.i_AnzahlFuhren = 	iAnzahlFuhren;
	}
	
	
	public void createSqlStatementHandelsDef() throws myException {
		
		RECORD_HANDELSDEF  	recDEF = this.rec_HANDELS_DEF;
		
		String cID_HomeLand = bibALL.get_RECORD_MANDANT().get_ID_LAND_cUF();
		
		//einstellung, ob mit oder ohne unterscheuidung der Mandantenvermerke gearbeitet wird
		boolean bBeruecksichtigeMandantenVermerke = bibALL.get_RECORD_MANDANT().is_STEUERFINDUNG_OHNE_EIGENORTE_NO();

		//String cNAME_BASISTABELLE = "JT_VPOS_TPA_FUHRE";
		
		String cNAME_BASISTABELLE_FAST = "JT_VPOS_TPA_FUHRE";
		String cNAME_BASISTABELLE_SLOW = "V"+bibALL.get_ID_MANDANT()+"_FUHREN";
		
		
		String cSQL=	"SELECT FU.ID_VPOS_TPA_FUHRE  from "+cPlaceHolderBaseTable+"  FU "+
						" INNER JOIN JT_ADRESSE  AQJ  ON  (FU.ID_ADRESSE_START=AQJ.ID_ADRESSE) "+
						" INNER JOIN JT_FIRMENINFO FIQ ON (FIQ.ID_ADRESSE=AQJ.ID_ADRESSE) "+
						" INNER JOIN JD_LAND LQJ ON (AQJ.ID_LAND=LQJ.ID_LAND) "+
						" INNER JOIN JT_ADRESSE  AQG  ON  (FU.ID_ADRESSE_LAGER_START=AQG.ID_ADRESSE) "+
						" INNER JOIN JD_LAND LQG ON (AQG.ID_LAND=LQG.ID_LAND) "+
						" INNER JOIN JT_ADRESSE  AZJ  ON  (FU.ID_ADRESSE_ZIEL=AZJ.ID_ADRESSE) "+
						" INNER JOIN JT_FIRMENINFO FIZ ON (FIZ.ID_ADRESSE=AZJ.ID_ADRESSE) "+
						" INNER JOIN JD_LAND LZJ ON (AZJ.ID_LAND=LZJ.ID_LAND) "+
						" INNER JOIN JT_ADRESSE  AZG  ON  (FU.ID_ADRESSE_LAGER_ZIEL=AZG.ID_ADRESSE) "+
						" INNER JOIN JD_LAND LZG ON (AZG.ID_LAND=LZG.ID_LAND) "+
						" INNER JOIN JT_ARTIKEL_BEZ ABQ ON (FU.ID_ARTIKEL_BEZ_EK=ABQ.ID_ARTIKEL_BEZ) "+
						" INNER JOIN JT_ARTIKEL    AQ ON (ABQ.ID_ARTIKEL=AQ.ID_ARTIKEL) "+
						" INNER JOIN JT_ARTIKEL_BEZ ABZ ON (FU.ID_ARTIKEL_BEZ_VK=ABZ.ID_ARTIKEL_BEZ) "+
						" INNER JOIN JT_ARTIKEL    AZ ON (ABZ.ID_ARTIKEL=AZ.ID_ARTIKEL) "+
						" WHERE      LQJ.ID_LAND="+recDEF.get_ID_LAND_QUELLE_JUR_cUF_NN("0")+
						" AND        LZJ.ID_LAND="+recDEF.get_ID_LAND_ZIEL_JUR_cUF_NN("0")+
						" AND        LQG.ID_LAND="+recDEF.get_ID_LAND_QUELLE_GEO_cUF_NN("0")+
						" AND        LZG.ID_LAND="+recDEF.get_ID_LAND_ZIEL_GEO_cUF_NN("0");
		
		cSQL = cSQL + this.holeBedingung("AQ.DIENSTLEISTUNG", recDEF.get_SORTE_DIENSTLEIST_QUELLE_cUF_NN(""));
		cSQL = cSQL + this.holeBedingung("AZ.DIENSTLEISTUNG", recDEF.get_SORTE_DIENSTLEIST_ZIEL_cUF_NN(""));
		
		cSQL = cSQL + this.holeBedingung("AQ.IST_PRODUKT", recDEF.get_SORTE_PRODUKT_QUELLE_cUF_NN(""));
		cSQL = cSQL + this.holeBedingung("AZ.IST_PRODUKT", recDEF.get_SORTE_PRODUKT_ZIEL_cUF_NN(""));
		
		cSQL = cSQL + this.holeBedingung("AQ.END_OF_WASTE", recDEF.get_SORTE_EOW_QUELLE_cUF_NN(""));
		cSQL = cSQL + this.holeBedingung("AZ.END_OF_WASTE", recDEF.get_SORTE_EOW_ZIEL_cUF_NN(""));

		if 		(recDEF.get_UST_TEILNEHMER_QUELLE_cUF_NN("").trim().equals("1")) {
			cSQL = cSQL + (" AND NVL(FIQ.FIRMA,'N')='Y' ");
		}
		if 		(recDEF.get_UST_TEILNEHMER_QUELLE_cUF_NN("").trim().equals("-1")) {
			cSQL = cSQL + (" AND NVL(FIQ.PRIVAT,'N')='Y' ");
		}
		if 		(recDEF.get_UST_TEILNEHMER_ZIEL_cUF_NN("").trim().equals("1")) {
			cSQL = cSQL + (" AND NVL(FIZ.FIRMA,'N')='Y' ");
		}
		if 		(recDEF.get_UST_TEILNEHMER_ZIEL_cUF_NN("").trim().equals("-1")) {
			cSQL = cSQL + (" AND NVL(FIZ.PRIVAT,'N')='Y' ");
		}
		
		//2014-06-11: die mandantenvermerke beruecksichtigen
		if (bBeruecksichtigeMandantenVermerke) {
			if (recDEF.is_QUELLE_IST_MANDANT_YES()) {
				cSQL = cSQL + "AND FU.ID_ADRESSE_START="+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0");
			} else {
				cSQL = cSQL + "AND FU.ID_ADRESSE_START<>"+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0");
			}

			if (recDEF.is_ZIEL_IST_MANDANT_YES()) {
				cSQL = cSQL + "AND FU.ID_ADRESSE_ZIEL="+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0");
			} else {
				cSQL = cSQL + "AND FU.ID_ADRESSE_ZIEL<>"+bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN("0");
			}
		}
		
		// im folgenden:
		// die rc-definition ist abhaengig vom status des handelspartners: ist es eine firma, dann gilt die RC-definition der geografischen adresse,
		//                                                                 fuer private gilt immer der rc-status des mandantenlandes 
		
		if (recDEF.get_SORTE_RC_QUELLE_cUF_NN("").trim().equals("1")) {
			if (recDEF.get_UST_TEILNEHMER_QUELLE_cUF_NN("").trim().equals("1")) {
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND=LQG.ID_LAND AND RC.ID_ARTIKEL=AQ.ID_ARTIKEL)<>0 ");
			} else if (recDEF.get_UST_TEILNEHMER_QUELLE_cUF_NN("").trim().equals("-1")){
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND="+cID_HomeLand+" AND RC.ID_ARTIKEL=AQ.ID_ARTIKEL)<>0 ");
			} else {
				cSQL = cSQL + ("1=2");    //undefinierte adresse duerfen nicht gefunden werden
			}
		}
		if (recDEF.get_SORTE_RC_QUELLE_cUF_NN("").trim().equals("-1")) {
			if (recDEF.get_UST_TEILNEHMER_QUELLE_cUF_NN("").trim().equals("1")) {
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND=LQG.ID_LAND AND RC.ID_ARTIKEL=AQ.ID_ARTIKEL)=0 ");
			} else if (recDEF.get_UST_TEILNEHMER_QUELLE_cUF_NN("").trim().equals("-1")){
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND="+cID_HomeLand+" AND RC.ID_ARTIKEL=AQ.ID_ARTIKEL)=0 ");
			} else {
				cSQL = cSQL + ("1=2");    //undefinierte adresse duerfen nicht gefunden werden
			}
		}

		if (recDEF.get_SORTE_RC_ZIEL_cUF_NN("").trim().equals("1")) {
			if (recDEF.get_UST_TEILNEHMER_ZIEL_cUF_NN("").trim().equals("1")) {
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND=LZG.ID_LAND AND RC.ID_ARTIKEL=AZ.ID_ARTIKEL)<>0 ");
			} else if (recDEF.get_UST_TEILNEHMER_ZIEL_cUF_NN("").trim().equals("-1")){
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND="+cID_HomeLand+" AND RC.ID_ARTIKEL=AZ.ID_ARTIKEL)<>0 ");
			} else {
				cSQL = cSQL + ("1=2");    //undefinierte adresse duerfen nicht gefunden werden
			}
		}
		if (recDEF.get_SORTE_RC_ZIEL_cUF_NN("").trim().equals("-1")) {
			if (recDEF.get_UST_TEILNEHMER_ZIEL_cUF_NN("").trim().equals("1")) {
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND=LZG.ID_LAND AND RC.ID_ARTIKEL=AZ.ID_ARTIKEL)=0 ");
			} else if (recDEF.get_UST_TEILNEHMER_ZIEL_cUF_NN("").trim().equals("-1")){
				cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND="+cID_HomeLand+" AND RC.ID_ARTIKEL=AZ.ID_ARTIKEL)=0 ");
			} else {
				cSQL = cSQL + ("1=2");    //undefinierte adresse duerfen nicht gefunden werden
			}
		}
		
		
//		if (recDEF.get_SORTE_RC_ZIEL_cUF_NN("").trim().equals("1")) {
//			cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND=LZG.ID_LAND AND RC.ID_ARTIKEL=AZ.ID_ARTIKEL)<>0 ");
//		}
//		if (recDEF.get_SORTE_RC_ZIEL_cUF_NN("").trim().equals("-1")) {
//			cSQL = cSQL + (" AND  (SELECT COUNT(*) FROM JT_LAND_RC_SORTEN RC WHERE RC.ID_LAND=LZG.ID_LAND AND RC.ID_ARTIKEL=AZ.ID_ARTIKEL)=0 ");
//		}
		
		cSQL = cSQL +( "AND NVL(FU.TP_VERANTWORTUNG,'MANDANT')="+recDEF.get_TP_VERANTWORTUNG_VALUE_FOR_SQLSTATEMENT() );
		
		if (this.i_AnzahlFuhren!=null) {
			cSQL = cSQL +( " AND ROWNUM<= "+this.i_AnzahlFuhren.intValue());
		}
		
		this.cSQL_FAST = cSQL.replace(this.cPlaceHolderBaseTable, cNAME_BASISTABELLE_FAST);
		this.cSQL_SLOW = cSQL.replace(this.cPlaceHolderBaseTable, cNAME_BASISTABELLE_SLOW);
	}
	
	
	private String holeBedingung(String cFieldname, String cVGLWert) {
		String cRueck = "";
		if (S.NN(cVGLWert).trim().equals("0")) {
				cRueck = ""; 									//dann bleibt die bedingung leer
		} 	else if (S.NN(cVGLWert).trim().equals("1")) { 
				cRueck = " AND NVL("+cFieldname+",'N')='Y' ";	
		}	else if (S.NN(cVGLWert).trim().equals("-1")) { 
				cRueck = " AND NVL("+cFieldname+",'N')='N' ";	
		}
		return cRueck;
	}


	/**
	 * rueckgabe auf basis der Tabelle JT_VPOS_TPA_FUHRE
	 * @return
	 */
	public String get_cSQL_FAST() {
		return cSQL_FAST;
	}


	/**
	 * rueckgabe auf basis der Tabelle V<N>_FUHREN
	 * @return
	 */
	public String get_cSQL_SLOW() {
		return cSQL_SLOW;
	}


	
}
