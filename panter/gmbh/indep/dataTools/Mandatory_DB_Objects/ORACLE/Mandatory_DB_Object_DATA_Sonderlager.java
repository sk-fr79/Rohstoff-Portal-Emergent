package panter.gmbh.indep.dataTools.Mandatory_DB_Objects.ORACLE;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;


public class Mandatory_DB_Object_DATA_Sonderlager extends Mandatory_DB_Object_ORACLE {
 
	private String m_Beschreibung = "";
	
	public Mandatory_DB_Object_DATA_Sonderlager(String SONDERLAGER, String Beschreibung) {
		super(SONDERLAGER);
		this.m_Beschreibung = Beschreibung;
	}

	@Override
	public boolean CreateObject() {
		boolean bRet = false;
		Vector<String> vSql = new Vector<String>();
		
		// erste mögliche ID unter 1000 und ab 10:
		String sSql = " SELECT NVL(MAX(id_adresse),1) FROM  " + bibE2.cTO() +".JT_ADRESSE WHERE ID_ADRESSE < 1000 AND ID_ADRESSE > 9 " ;
		String sValue 	= bibDB.EinzelAbfrage(sSql,false,false);
		String sValueLieferadresse = sValue;
		
		int nValue = Integer.parseInt(sValue);
		if (nValue == 1){
			nValue = 10;
			sValue = Integer.toString(nValue);
			sValueLieferadresse = sValue;
		}  else if ( nValue == 999 ){
			// falls die ersten 1000 IDS schon ausgefüllt sind, oder im Sonderfall die 999 schon belegt ist, dann einfach in die Sequenzen einhaengen
			nValue = 0;
			sValue = "SEQ_ADRESSE.NEXTVAL";
			sValueLieferadresse = "SEQ_ADRESSE.CURRVAL";
		} else {
			nValue = nValue + 1;
			sValue = Integer.toString(nValue);
			sValueLieferadresse = sValue;
		}
		
		
		vSql.add(  	" INSERT INTO  " + bibE2.cTO() +".JT_ADRESSE ( ID_ADRESSE,SONDERLAGER, NAME1, ADRESSTYP ) " +
				 	" VALUES (" + sValue + ", '" + getObjectName().toUpperCase() + "', '" + m_Beschreibung + "', 5) " );
		

		vSql.add(  	" INSERT INTO  " + bibE2.cTO() +".JT_LIEFERADRESSE ( ID_LIEFERADRESSE, IST_STANDARD, ID_ADRESSE_BASIS, ID_ADRESSE_LIEFER ) " +
					" VALUES (SEQ_LIEFERADRESSE.NEXTVAL, 'N', " + bibALL.get_ID_ADRESS_MANDANT() +  ", " + sValueLieferadresse + ") " );

		bRet = bibDB.ExecSQL(vSql, true);

		
		return bRet;
	}

	
	
	@Override
	public boolean ObjectExists() {
		boolean bRet = false;
		
		// prüfen ob alle Sonderläger genau einmal vorhanden sind
		String sSql 	= "SELECT COUNT(*) FROM " + bibE2.cTO() +".JT_ADRESSE WHERE ID_MANDANT = " + bibALL.get_ID_MANDANT() + " AND trim(SONDERLAGER) = trim('" + getObjectName().toUpperCase().trim() + "') " ; 
		String sValue 	= bibDB.EinzelAbfrage(sSql);
		
		// es muss genau ein Wert vorhanden sein, wenn der Trigger existiert
		if (!bibALL.isEmpty(sValue)){
			try {
				int n = Integer.parseInt(sValue);
				if (n > 0){
					bRet = true;
				}
			} catch (Exception e) {
				// parse-Fehler
			}
		}
		
		return bRet;
	}

	@Override
	public boolean ObjectIsValid() {
		return true;
	}


}
