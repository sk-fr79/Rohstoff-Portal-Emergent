package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_INVENTUR;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

@Deprecated
public class ATOM_LAG_DBSelectFieldOwnLAGER extends MyE2_DB_SelectField{

	public ATOM_LAG_DBSelectFieldOwnLAGER(SQLField osqlField) throws myException
	{
		super(osqlField);
		
				String cQuery = "SELECT NVL(JT_ADRESSE.ORT,'-')||' '||NVL('('||JT_ADRESSE.PLZ||')',''), " +
				"				NVL(JT_ADRESSE.NAME1,'') || NVL2(JT_ADRESSE.NAME2,' ' || JT_ADRESSE.NAME2,'') ||  " +
				"				NVL2(JT_LIEFERADRESSE.BESCHREIBUNG, ' - ' || JT_LIEFERADRESSE.BESCHREIBUNG,'') , " +
							"	JT_ADRESSE.ID_ADRESSE " +
						" FROM "+
							bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_LIEFERADRESSE "+
					    " WHERE " +
					    	" JT_ADRESSE.ID_ADRESSE=JT_LIEFERADRESSE.ID_ADRESSE_LIEFER " +
					    " AND "+
					    	" JT_LIEFERADRESSE.ID_ADRESSE_BASIS="+bibALL.get_ID_ADRESS_MANDANT()+
					    " ORDER BY JT_ADRESSE.ORT";
		
		
		// eigene adresse
		String cQuery2 = "SELECT   NVL(JT_ADRESSE.NAME1,'-')||' '|| NVL(JT_ADRESSE.STRASSE,'-')||' '|| NVL(JT_ADRESSE.ORT,'-')," +
							"	JT_ADRESSE.ID_ADRESSE " +
						" FROM "+
							bibE2.cTO()+".JT_ADRESSE"+
					    " WHERE " +
					    	" ID_ADRESSE="+bibALL.get_ID_ADRESS_MANDANT();
		
		
		MyDBToolBox oDB = bibALL.get_myDBToolBox();
		String[][] cArray = oDB.EinzelAbfrageInArray(cQuery,"");
		String[][] cArray2 = oDB.EinzelAbfrageInArray(cQuery2,"");
		bibALL.destroy_myDBToolBox(oDB);
		
		Font f = new nextapp.echo2.app.Font(Font.MONOSPACE, Font.PLAIN, new Extent(bibALL.get_FONT_SIZE()-1 ,Extent.PT));
		this.setFont(f);
		
		
		//
		// Nachgebaute Tabulatoren
		
		// Anzahl der Spalten im Selektor
		int anzahlAnzeigeSpalten = 1;
		int lenAnzeigeSpalten = 0;
		
		if (cArray.length  > 0){
			anzahlAnzeigeSpalten = cArray[0].length - 1;
		}
		
		// ermitteln der maximalen Länge der einzelnen Spalten
		int[] laengeSpalte = new int[anzahlAnzeigeSpalten];
		String sTest = "";
		for (int i=0; i<cArray.length; i++){
			for (int j=0; j < anzahlAnzeigeSpalten; j++){
				sTest = cArray[i][j];
				laengeSpalte[j] = sTest.length() > laengeSpalte[j] ? sTest.length() : laengeSpalte[j];
			}
		}
		
		// ermitteln der kompletten Anzeigenlänge
		for (int j = 0; j < anzahlAnzeigeSpalten; j++){
			lenAnzeigeSpalten += laengeSpalte[j] ;
		}
		lenAnzeigeSpalten += (anzahlAnzeigeSpalten - 1);
		
		String[][] cWerte = null; 
		if ((cArray == null || cArray.length==0) && (cArray2 == null || cArray2.length==0) )
		{
			cWerte = new String[1][2];
			cWerte[0][0]="-";
			cWerte[0][1]="";
		}
		else
		{
			cWerte = new String[1+cArray.length+cArray2.length][2];
			cWerte[0][0]="-";
			cWerte[0][1]="";
		
			int icount = 1;
			for (int i=0;i<cArray2.length;i++)
			{
				cWerte[icount][0] = new MyE2_String("Hauptadresse: ").CTrans()+cArray2[i][0];
				cWerte[icount][1] = cArray2[i][1];
				icount++;
			}
			
			for (int i=0;i<cArray.length;i++)
			{
				// Werte zusammengesetzt aus den  Spalten
				String sRow = "";
				String sCol = "";
				String sTab = "";
				
				int lenVorher = 0;
				int posAktuell = 0;
				int lenCol = 0;
				for (int j=0; j < anzahlAnzeigeSpalten; j++){
					
					// aktuelle Spalte
					sCol = cArray[i][j];
					lenCol = sCol.length();
		
		
					// wenn es nicht die 1. Spalte ist
					if ( j > 0 && lenCol > 0){
						sCol = sTab + sCol;
					}
					
					
					sTab = "________________________________________________________________________________".substring(0, laengeSpalte[j] - lenCol + 2);
					
					lenVorher = lenCol;
					posAktuell = posAktuell + lenVorher;
					sRow += sCol;
				}
				
				//sRow = sRow.substring(0, lenAnzeigeSpalten);
				
				//cWerte[icount][0] = cArray[i][0];
				cWerte[icount][0] = sRow;
				cWerte[icount][1] = cArray[i][anzahlAnzeigeSpalten];
				icount++;
			}
		}
		
		this.set_ListenInhalt(cWerte,false);

		
	}

}
