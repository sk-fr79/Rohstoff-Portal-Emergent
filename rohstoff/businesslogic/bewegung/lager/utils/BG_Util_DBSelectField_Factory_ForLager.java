package rohstoff.businesslogic.bewegung.lager.utils;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;

public class BG_Util_DBSelectField_Factory_ForLager {

	MyE2_SelectField m_oSelectfield = null;
	
	
//	/**
//	 * DBSelectfield mit den Lagern ohne Streckenlager
//	 * @param osqlField
//	 * @return
//	 * @throws myException
//	 */
////	public MyE2_DB_SelectField getDBSelectField(SQLField osqlField) throws myException{
//		
//		MyE2_DB_SelectField m_oDB_Selectfield = new MyE2_DB_SelectField(osqlField);
//		m_oDB_Selectfield.set_ListenInhalt(fillSelector(true,false), false);
//		
//		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
//		m_oDB_Selectfield.setFont(f);
//		
//		return m_oDB_Selectfield;
//	}
	
	
	
	
	/**
	 * liefert das Selectfield nur mit den realen Lagerorten zur4ueck
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
	 * liefert das Selectfield mit realen Lagerorten und ggf. dem Streckenlager zurueck
	 * @author manfred
	 * @date   26.03.2012
	 * @param bShowStrecke
	 * @return
	 * @throws myException
	 */
	public MyE2_SelectField getSelectField(boolean bShowStrecke) throws myException{
		m_oSelectfield = new MyE2_SelectField();
		
		m_oSelectfield.set_ListenInhalt(fillSelector(false,bShowStrecke), false);
		
		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
		m_oSelectfield.setFont(f);
		
		return m_oSelectfield;
	}
	
	
	
	
	
	
	
	
	/**
	 * aktualisiert das Selektfield mit den neuesten Listeninhalten
	 * @param bShowStrecke
	 * @throws myException 
	 */
	public void refreshSelectfield(boolean bShowStrecke) throws myException{
		m_oSelectfield.set_ListenInhalt(fillSelector(false,bShowStrecke), false);
	}
	
	
	
	private String[][] fillSelector(boolean bFormatted,boolean bShowStrecke	){
			return fillSelector(bFormatted,bShowStrecke,false,false,false,false,false,false);
	}
	
	
	
	/**
	 * 
	 * Fï¿½llt die Liste mit den gefundenen Werten 
	 * 
	 * @return
	 */
	private String[][] fillSelector(boolean bFormatted, 
			boolean bShowStrecke, 
			boolean bShowUmbuchungHand,
			boolean bShowKorrekturHand,
			boolean bShowSortenwechsel,
			boolean bShowLagerLager,
			boolean bShowMengenkorrektur,
			boolean bShowMixed
			){
		
		
		String sSonderlager = "''";
		if (bShowStrecke) sSonderlager += ",'STRECKE'";
//		if (bShowUmbuchungHand) sSonderlager += ",'UH'";
//		if (bShowKorrekturHand) sSonderlager += ",'KH'";
//		if (bShowSortenwechsel) sSonderlager += ",'SW'";
//		if (bShowLagerLager) 	sSonderlager += ",'LL'";
//		if (bShowMengenkorrektur) sSonderlager += ",'MK'";
//		if (bShowMixed) sSonderlager += ",'MI'";
		
		String cQueryLager = "SELECT " 
				+ "				2, " 
				+ "				NVL(JT_ADRESSE.ORT,'-')||' '||NVL('('||JT_ADRESSE.PLZ||')',''), "
				+ "				NVL(JT_ADRESSE.NAME1,'') || NVL2(JT_ADRESSE.NAME2,' ' || JT_ADRESSE.NAME2,'') ||  "
				+ "				NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') , "
				+ "				JT_ADRESSE.ID_ADRESSE "
				+ " FROM "
				+ bibE2.cTO()+ ".JT_ADRESSE,"	+ bibE2.cTO()+ ".JT_LIEFERADRESSE "
				+ " WHERE "
				+ " JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER "
				+ " AND JT_ADRESSE.SONDERLAGER is null "
				+ " AND JT_LIEFERADRESSE.ID_ADRESSE_BASIS= "+ bibALL.get_ID_ADRESS_MANDANT()
				;

		String cQuerySL = "SELECT 3,	NVL(JT_ADRESSE.NAME1,'SONDERLAGER'), "
				+ "					NVL(JT_ADRESSE.BEMERKUNGEN,'') , "
				+ "					JT_ADRESSE.ID_ADRESSE "
				+ " FROM "
				+ bibE2.cTO() + ".JT_ADRESSE,"
				+ bibE2.cTO() + ".JT_LIEFERADRESSE "
				+ " WHERE "
				+ " JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER "
				+ " AND JT_ADRESSE.SONDERLAGER IS NOT null "
				+ " AND JT_ADRESSE.SONDERLAGER IN (" + sSonderlager + ") "
				+ " AND JT_LIEFERADRESSE.ID_ADRESSE_BASIS= "+ bibALL.get_ID_ADRESS_MANDANT()
				;


		
		
		// eigene adresse
		String cQueryHaupt = "SELECT 1, to_nchar('HAUPTADRESSE')  , NVL(JT_ADRESSE.NAME1,'-')||' '|| NVL(JT_ADRESSE.STRASSE,'-')||' '|| NVL(JT_ADRESSE.ORT,'-'), "
				+ "					JT_ADRESSE.ID_ADRESSE "
				+ " FROM "
				+ bibE2.cTO() + ".JT_ADRESSE"
				+ " WHERE "
				+ " ID_ADRESSE=" + bibALL.get_ID_ADRESS_MANDANT();

		String cQueryALL = cQueryLager + " UNION " + cQueryHaupt + " UNION " + cQuerySL + " ORDER BY 1,2,3 ";

		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cArray ;
		if (bFormatted){
			cArray = oDB.EinzelAbfrageInArrayFormatiert(cQueryALL  , "");
		} else {
			cArray = oDB.EinzelAbfrageInArray(cQueryALL, "");
		}
		bibALL.destroy_myDBToolBox(oDB);
		
		
		
		//
		// Nachgebaute Tabulatoren
		int lenAnzeigeSpalten = 0;

		// Index der darzustellenden Spalten, bei denen der Tabulator simuliert werden soll
		int [] idxColumnsToDisplay = {1,2};
		int idxSpalteID = 3;
		
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
		cWerte = new String[nEntries][2];
		
		// 1. Eintrag: alle Läger
		cWerte[0][0] = "-";
		cWerte[0][1] = "";

		int icount = 1;
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
