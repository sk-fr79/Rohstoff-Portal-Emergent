package rohstoff.Echo2BusinessLogic.LAGER;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class LAG_LagerSelectField_Factory {

	public MyE2_DB_SelectField getDBSelectField(SQLField osqlField) throws myException{
		
		MyE2_DB_SelectField oSel = new MyE2_DB_SelectField(osqlField);
		
		oSel.set_ListenInhalt(fillSelector(true,false), false);
		
		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
		oSel.setFont(f);
		
		return oSel;
	}
	
	
	/**
	 * liefert das Selectfield ohne Strecke 
	 * und mit unformatierten IDs zurück
	 * 
	 * @author manfred
	 * @date   26.03.2012
	 * @return
	 * @throws myException
	 */
	public MyE2_SelectField getSelectField() throws myException{
		return getSelectField(false);
	}
	
	
	
	/**
	 * liefert das Selectfield mit unformatierten IDs zurück
	 * @author manfred
	 * @date   26.03.2012
	 * @param bShowStrecke
	 * @return
	 * @throws myException
	 */
	public MyE2_SelectField getSelectField(boolean bShowStrecke) throws myException{
		MyE2_SelectField oSel = new MyE2_SelectField();
		
		oSel.set_ListenInhalt(fillSelector(false,bShowStrecke), false);
		
		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
		oSel.setFont(f);
		
		return oSel;
	
	}
	
	
	
	/**
	 * 
	 * Füllt die Liste mit den gefundenen Werten 
	 * 
	 * @return
	 */
	private String[][] fillSelector(boolean bFormatted, boolean bShowStrecke){
		String cQuery = "SELECT NVL(JT_ADRESSE.ORT,'-')||' '||NVL('('||JT_ADRESSE.PLZ||')',''), "
				+ "				NVL(JT_ADRESSE.NAME1,'') || NVL2(JT_ADRESSE.NAME2,' ' || JT_ADRESSE.NAME2,'') ||  "
				+ "				NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') , "
				+ "	JT_ADRESSE.ID_ADRESSE "
				+ " FROM "
				+ bibE2.cTO()
				+ ".JT_ADRESSE,"
				+ bibE2.cTO()
				+ ".JT_LIEFERADRESSE "
				+ " WHERE "
				+ " JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER "
				+ " AND "
				+ " JT_LIEFERADRESSE.ID_ADRESSE_BASIS="
				+ bibALL.get_ID_ADRESS_MANDANT() + " ORDER BY JT_ADRESSE.ORT";

		// eigene adresse
		String cQuery2 = "SELECT   NVL(JT_ADRESSE.NAME1,'-')||' '|| NVL(JT_ADRESSE.STRASSE,'-')||' '|| NVL(JT_ADRESSE.ORT,'-'),"
				+ "	JT_ADRESSE.ID_ADRESSE "
				+ " FROM "
				+ bibE2.cTO()
				+ ".JT_ADRESSE"
				+ " WHERE "
				+ " ID_ADRESSE="
				+ bibALL.get_ID_ADRESS_MANDANT();

		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cArray ;
		String[][] cArray2 ;
		if (bFormatted){
			cArray = oDB.EinzelAbfrageInArrayFormatiert(cQuery, "");
			cArray2 = oDB.EinzelAbfrageInArrayFormatiert(cQuery2, "");
		} else {
			cArray = oDB.EinzelAbfrageInArray(cQuery, "");
			cArray2 = oDB.EinzelAbfrageInArray(cQuery2, "");
		}
		bibALL.destroy_myDBToolBox(oDB);
		
		
		
		//
		// Nachgebaute Tabulatoren
		
		// Anzahl der Spalten im Selektor
		int anzahlAnzeigeSpalten = 1;
		int lenAnzeigeSpalten = 0;
		
		if (cArray.length > 0) {
			anzahlAnzeigeSpalten = cArray[0].length - 1;
		}
		
		// ermitteln der maximalen Länge der einzelnen Spalten
		int[] laengeSpalte = new int[anzahlAnzeigeSpalten];
		String sTest = "";
		for (int i = 0; i < cArray.length; i++) {
			for (int j = 0; j < anzahlAnzeigeSpalten; j++) {
				sTest = cArray[i][j];
				laengeSpalte[j] = sTest.length() > laengeSpalte[j] ? sTest
						.length() : laengeSpalte[j];
			}
		}

		// ermitteln der kompletten Anzeigenlänge
		for (int j = 0; j < anzahlAnzeigeSpalten; j++) {
			lenAnzeigeSpalten += laengeSpalte[j];
		}
		lenAnzeigeSpalten += (anzahlAnzeigeSpalten - 1);

		String[][] cWerte = null;
		if ((cArray == null || cArray.length == 0)
				&& (cArray2 == null || cArray2.length == 0)) {
			cWerte = new String[1][2];
			cWerte[0][0] = "-";
			cWerte[0][1] = "";
		} else {
			int nSize = 1 + cArray.length + cArray2.length;
			if (bShowStrecke){
				nSize++;
			}
			cWerte = new String[nSize][2];
			cWerte[0][0] = "-";
			cWerte[0][1] = "";

			int icount = 1;
			for (int i = 0; i < cArray2.length; i++) {
				cWerte[icount][0] = new MyE2_String("Hauptadresse: ").CTrans()
						+ cArray2[i][0];
				cWerte[icount][1] = cArray2[i][1];
				icount++;
			}
			
			// hier die Strecke rein
			if (bShowStrecke){
				cWerte[icount][0] = new MyE2_String("Streckenlager").CTrans();
				cWerte[icount][1] = "0";
				icount++;
			}
			
			

			for (int i = 0; i < cArray.length; i++) {
				// Werte zusammengesetzt aus den Spalten
				String sRow = "";
				String sCol = "";
				String sTab = "";

				int lenCol = 0;
				for (int j = 0; j < anzahlAnzeigeSpalten; j++) {

					// aktuelle Spalte
					sCol = cArray[i][j];
					lenCol = sCol.length();

					// wenn es nicht die 1. Spalte ist
					if (j > 0 && lenCol > 0) {
						sCol = sTab + sCol;
					}

					sTab = "____________________________________________________________________________________________________"
							+ "____________________________________________________________________________________________________"
							+ "____________________________________________________________________________________________________"
							+ "____________________________________________________________________________________________________";
					int lenMax = sTab.length();
					int lenTab = laengeSpalte[j] - lenCol + 2;
					if (lenTab > lenMax) {
						lenTab = lenMax;
					}

					sTab = sTab.substring(0, lenTab);

					sRow += sCol;
				}

				// sRow = sRow.substring(0, lenAnzeigeSpalten);

				// cWerte[icount][0] = cArray[i][0];
				cWerte[icount][0] = sRow;
				cWerte[icount][1] = cArray[i][anzahlAnzeigeSpalten];
				icount++;
			}
		}
		return cWerte;
	}

}
