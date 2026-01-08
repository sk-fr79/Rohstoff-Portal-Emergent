package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.exceptions.myException;

/**
 * 
 * @author manfred
 * @date 25.01.2019
 *
 */
public class UTIL_DBSelectField_Factory_ForUser {

	MyE2_SelectField m_oSelectfield = null;
	
	
	/**
	 * @author manfred
	 * @throws myException 
	 * @date 31.01.2019
	 *
	 */
	public UTIL_DBSelectField_Factory_ForUser() {
		m_oSelectfield = new MyE2_SelectField();
	}
	
	
	/**
	 * liefert das Selectfield nur mit den realen Lagerorten zur4ueck
	 * 
	 * @author manfred
	 * @date   26.03.2012
	 * @return
	 * @throws myException
	 */
	public MyE2_SelectField getSelectField() throws myException{
		return m_oSelectfield;
	}
	
	
	
	
	
	/**
	 * aktualisiert das Selektfield mit den neuesten Listeninhalten
	 * @param bShowStrecke
	 * @throws myException 
	 */
	public UTIL_DBSelectField_Factory_ForUser refreshSelectfield(boolean bShowInactiveUsers) throws myException{
		m_oSelectfield.set_ListenInhalt(fillSelector(false,bShowInactiveUsers), false);

//		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
//		m_oSelectfield.setFont(f);
		
		return this;
		
	}
	
	
	/**
	 * 
	 * Füllt die Liste mit den gefundenen Werten 
	 * 
	 * @return
	 */
	private String[][] fillSelector(boolean bFormatted, boolean bShowInactiveUsers	){
		
		String cQueryUser  = "SELECT id_user, name1 || ' ' ||  vorname ||' ('|| name || ')'  from JD_USER where ID_MANDANT = ? AND ID_USER != ?  "	;

		if (!bShowInactiveUsers) {
			cQueryUser = cQueryUser + " AND nvl(aktiv,'N') = 'Y' ";
		}
		
		cQueryUser = cQueryUser + " ORDER BY 2";
		
		SqlStringExtended s = new SqlStringExtended(cQueryUser);
		s.getValuesList().add( new Param_Long(Long.parseLong(bibALL.get_ID_MANDANT())) );
		s.getValuesList().add( new Param_Long(Long.parseLong(bibALL.get_ID_USER()   )) );
		
		String[][] cArray ;
		cArray = bibDB.EinzelAbfrageInArray(s,"");
		
		
		//
		// Nachgebaute Tabulatoren
		int lenAnzeigeSpalten = 0;

		// Index der darzustellenden Spalten, bei denen der Tabulator simuliert werden soll
		int [] idxColumnsToDisplay = {1};
		int idxSpalteID = 0;
		
		int anzahlAnzeigeSpalten = idxColumnsToDisplay.length;

		
		// ermitteln der maximalen Länge der einzelnen Spalten
		int[] laengeSpalte = new int[idxColumnsToDisplay.length];
		String sTest = "";
		for (int i = 0; i < cArray.length; i++) {
			for (int j = 0; j < idxColumnsToDisplay.length; j++) {
				sTest = cArray[i][idxColumnsToDisplay[j] ];
				laengeSpalte[j] = sTest.length() > laengeSpalte[j] ? sTest.length() : laengeSpalte[j];
			}
		}
		// ermitteln der kompletten Anzeigenlaenge
		for (int j = 0; j < idxColumnsToDisplay.length; j++) {
			lenAnzeigeSpalten += laengeSpalte[j];
		}


		// anzahl der gefundenen Lager
		int nEntries = (cArray!=null ? cArray.length : 0);
		nEntries ++;

		
		String[][] cWerte = null;
		cWerte = new String[nEntries+1][2];
		
		// 1. Eintrag: alle Läger
		cWerte[0][0] = "-";
		cWerte[0][1] = "";

		// 2. Eintrag eingeloggter User
		cWerte[1][1] = bibALL.get_ID_USER();
		try {
			cWerte[1][0] = bibALL.get_RECORD_USER().get___KETTE(USER.name1, USER.vorname) + "(" + bibALL.get_RECORD_USER().get_NAME_cUF() + ")";
		} catch (myException e) {
			cWerte[1][0] = "ich";
		}

		
		int icount = 2;
		for (int i = 0; i < cArray.length; i++) {
			// Werte zusammengesetzt aus den Spalten
			String sRow = "";
			String sCol = "";
			String sTab = "";

			int lenCol = 0;
			for (int j = 0; j < anzahlAnzeigeSpalten; j++) {

				// aktuelle Spalte
				sCol = cArray[i][ idxColumnsToDisplay[j] ];
				lenCol = sCol.length();

				// wenn es nicht die 1. Spalte ist
				if (j > 0 && lenCol > 0) {
					sCol = sTab + sCol;
				}

				sTab = "......................................................................................................."
						+ "......................................................................................................."
						+ "......................................................................................................."
						+ ".......................................................................................................";
				int lenMax = sTab.length();
				int lenTab = laengeSpalte[j] - lenCol + 2;
				if (lenTab > lenMax) {
					lenTab = lenMax;
				}

				sTab = " " + sTab.substring(0, lenTab - 2) + " ";

				sRow += sCol;
			}

			cWerte[icount][0] = sRow;
			cWerte[icount][1] = cArray[i][idxSpalteID];
			icount++;
		}

		return cWerte;
	}

}
