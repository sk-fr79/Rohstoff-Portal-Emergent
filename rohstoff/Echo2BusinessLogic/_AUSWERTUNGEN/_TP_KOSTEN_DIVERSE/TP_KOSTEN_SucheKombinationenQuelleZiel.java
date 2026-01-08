package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN._TP_KOSTEN_DIVERSE;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.plugins.Prepare_TempTables_Atom;

/**
 * klasse, sucht fuer eine vorhandene Adresse die moeglichen kosten-kombinationen gibt (im falle es 
 * welche gibt, einen Vector aus recordnew-objekten zurueck
 * @author martin
 *
 */
public  class TP_KOSTEN_SucheKombinationenQuelleZiel {
	
	private String  		cID_ADRESSE_BASIS_UF = 	null;
	private Vector<Integer> vZaehler = new Vector<Integer>();
	private Vector<String>  vIDS_HaveWA = new Vector<String>();
	
	private String cAusdruckNEIN = 		bibDB.get_Constant4SQL("NEIN");
	private String cAusdruckSTRECKE = 	bibDB.get_Constant4SQL("STRECKE");

	
	/**
	 * 
	 * @param idADRESSE_BASIS_UF (wenn adresse, dann werden relationen der adresse, sonst alle aufgebaut);
	 * @throws myException
	 */
	public TP_KOSTEN_SucheKombinationenQuelleZiel(String  idADRESSE_BASIS_UF) throws myException {
		super();

		this.cID_ADRESSE_BASIS_UF = idADRESSE_BASIS_UF;
		
		this.vZaehler.add(new Integer(0));
		
		//jetzt die temporaere tabelle der WA- und WE-Atom bauen
		Prepare_TempTables_Atom oPrep = new Prepare_TempTables_Atom();
		
		if (oPrep.CHECK_IF_MUST_BE_EXECUTED()) {
			oPrep.EXECUTED_BEFORE_REPORT();
		}
		
		String[][] arrIDsWA = bibDB.EinzelAbfrageInArray("SELECT DISTINCT WA.ID_ADRESSE_BASIS FROM "+bibE2.cTO()+".ST_WA_ATOM WA");	
		for (int i=0;i<arrIDsWA.length;i++) {
			this.vIDS_HaveWA.add(arrIDsWA[i][0]);
		}
		
		if (bibDB.ExecSQL_RAW_WITHOUT_ZUSATZFIELDS_WITHOUT_DAEMONS(this.make_SQL_QUERY(this.cID_ADRESSE_BASIS_UF), true,this.vZaehler)) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(
					new MyE2_String("Zahl neu gefundener und gespeicherter Relationen: ",true,""+TP_KOSTEN_SucheKombinationenQuelleZiel.this.vZaehler.get(0).intValue(),false)));
		} else {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error: SQL: "+this.make_SQL_QUERY(this.cID_ADRESSE_BASIS_UF)));
		}
		
		
	}


	
	private String make_SQL_QUERY(String cID_ADRESSE_BASIS) {
		String cGeandertVon = bibALL.MakeSql(bibALL.get_KUERZEL());
		
		String cADDON_WA = "";
		String cADDON_LFBED = "";
		if (S.isFull(cID_ADRESSE_BASIS)) {
			cADDON_WA = " AND WA.ID_ADRESSE_BASIS="+cID_ADRESSE_BASIS;
			cADDON_LFBED = " WHERE "+_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS+"="+cID_ADRESSE_BASIS;
		}
		
		String cSQL_BAUE_FUHRE=  	
			" INSERT INTO JT_KOSTEN_LIEFERBED_ADR ( "+
					_DB.KOSTEN_LIEFERBED_ADR$ID_KOSTEN_LIEFERBED_ADR+","+
					_DB.KOSTEN_LIEFERBED_ADR$ID_MANDANT+","+
					_DB.KOSTEN_LIEFERBED_ADR$ERZEUGT_VON+","+
					_DB.KOSTEN_LIEFERBED_ADR$GEAENDERT_VON+","+
					_DB.KOSTEN_LIEFERBED_ADR$ERZEUGT_AM+","+
					_DB.KOSTEN_LIEFERBED_ADR$LETZTE_AENDERUNG+","+
					" ID_ADRESSE_BASIS,"+
					" ID_ADRESSE, "+
					" ID_ARTIKEL, "+
					" ID_ADRESSE_ZIEL) "+		
			" SELECT SEQ_KOSTEN_LIEFERBED_ADR.NEXTVAL,"+
					bibALL.get_ID_MANDANT()+","+
					cGeandertVon+","+
					cGeandertVon+", " +
					"SYSDATE,"+
					"SYSDATE,"+
					"ID_ADRESSE_BASIS," +
					"ID_ADRESSE," +
					"ID_ARTIKEL," +
					"ID_ADRESSE_ZIEL" +
					" FROM "+
			" (SELECT DISTINCT "+ 
				" WA.ID_ADRESSE_BASIS  , "+ 			//0
				" WA.ID_ADRESSE_STATION   AS ID_ADRESSE, "+   	//1
				" WA.ID_ARTIKEL, "+											//2
				" WE.ID_ADRESSE_STATION  AS ID_ADRESSE_ZIEL "+		//3
				 " FROM "+bibE2.cTO()+".ST_WA_ATOM WA " +
				 " INNER JOIN "+bibE2.cTO()+".ST_WE_ATOM WE ON (WA.ID_BEWEGUNG_VEKTOR=WE.ID_BEWEGUNG_VEKTOR) " +
				 " INNER JOIN    "+bibE2.cTO()+".JT_ADRESSE ADS ON  ( WA.ID_ADRESSE_STATION = ADS.ID_ADRESSE) "+
				 " INNER JOIN    "+bibE2.cTO()+".JT_ADRESSE ADSG ON  ( WA.ID_ADRESSE_STATION_GEGENUEBER = ADSG.ID_ADRESSE) "+
				 " INNER JOIN    "+bibE2.cTO()+".JT_ADRESSE ADZ ON  ( WE.ID_ADRESSE_STATION = ADZ.ID_ADRESSE) "+
				 " INNER JOIN    "+bibE2.cTO()+".JT_ADRESSE ADZG ON  ( WE.ID_ADRESSE_STATION_GEGENUEBER = ADZG.ID_ADRESSE) "+
			
				 " WHERE   (NVL(ADS.SONDERLAGER,"+cAusdruckNEIN+")="+cAusdruckNEIN+") "+
				 " AND   "+
				 "   (NVL(ADSG.SONDERLAGER,"+cAusdruckNEIN+")="+cAusdruckNEIN+" OR  "+
				 "    NVL(ADSG.SONDERLAGER,"+cAusdruckNEIN+")="+cAusdruckSTRECKE+") "+
				 " AND    (NVL(ADZ.SONDERLAGER,"+cAusdruckNEIN+")="+cAusdruckNEIN+") "+
				 " AND     "+
				 "   (NVL(ADZG.SONDERLAGER,"+cAusdruckNEIN+")="+cAusdruckNEIN+" OR  "+
				 "    NVL(ADZG.SONDERLAGER,"+cAusdruckNEIN+")="+cAusdruckSTRECKE+")"+
				 cADDON_WA+
				 " MINUS "+
					"SELECT "+
					_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_BASIS+","+
					_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE+","+
					_DB.KOSTEN_LIEFERBED_ADR$ID_ARTIKEL+","+
					_DB.KOSTEN_LIEFERBED_ADR$ID_ADRESSE_ZIEL+
					" FROM "+bibE2.cTO()+"."+_DB.KOSTEN_LIEFERBED_ADR+
					cADDON_LFBED+")";
					

		return cSQL_BAUE_FUHRE;
	}
	
	
}
