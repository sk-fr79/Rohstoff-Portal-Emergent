/**
 * rohstoff.businesslogic.bewegung2.lager
 * @author sebastien
 * @date 10.12.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.lager;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;


public class B2_LAG_LIST_Cmp_Selector_FirmaSelectField extends MyE2_SelectField {

	public B2_LAG_LIST_Cmp_Selector_FirmaSelectField() throws myException {
		super();
		
		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN,	new Extent(bibALL.get_FONT_SIZE() - 1, Extent.PT));
		
		this.setFont(f);

		this.set_ListenInhalt(populate_selectField(), false);
	}


	private String[][] populate_selectField() throws myException{

		String firma_adresse_abfrage = new SEL()
				.ADDFIELD("to_nchar('* HAUPTADRESSE: '), NVL(AD.NAME1,'-')||'-'||  NVL(AD.NAME2,'-')|| '-'||  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN")
				.ADDFIELD("AD.ID_ADRESSE AS ID_ADRESSE")
				.FROM(_TAB.adresse, "AD")
				.WHERE(new vgl("AD", ADRESSE.id_adresse,bibALL.get_ID_ADRESS_MANDANT()))
				.s() + " UNION "+ 
				new SEL()
				.ADDFIELD("NVL(AD.NAME1,'-')||' '||  NVL(AD.NAME2,'-') ,  NVL(AD.ORT,'-') ||' ('||TO_CHAR(  NVL(AD.ID_ADRESSE,0))||')' AS NAMEN")
				.ADDFIELD("AD.ID_ADRESSE AS  ID_ADRESSE")
				.FROM(_TAB.bg_station, "st")
				.LEFTOUTER("JT_ADRESSE AD", "ST.id_adresse_basis","AD.id_adresse")
				.WHERE(new vgl("AD", ADRESSE.id_adresse,COMP.GT.ausdruck(),"1000"))
				.s();

		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cArray ;

		cArray = oDB.EinzelAbfrageInArray(firma_adresse_abfrage, "");
		bibALL.destroy_myDBToolBox(oDB);

		// Index der darzustellenden Spalten, bei denen der Tabulator simuliert werden soll
		int [] idxColumnsToDisplay = {0,1};
		int idxSpalteID = 2;
		
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
//						+ "......................................................................................................."
						;
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

