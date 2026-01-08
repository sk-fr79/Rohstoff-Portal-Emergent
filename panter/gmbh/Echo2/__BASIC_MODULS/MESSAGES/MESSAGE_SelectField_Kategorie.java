package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Hashtable;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_SelectField_Kategorie extends MyE2_SelectField {
	
	/**
	 * Hashtable der mehrere Singleton-Instanzen ablegt.
	 */
	private  Hashtable<String, Object> htSelectObjects = null;

	private MESSAGE_Editor callingContainer = null;
	
	/**
	 * Standard-Selektfield der Kategorien
	 * mit einem Leer-Eintrag zu Beginn 
	 * und einer Breite von 100 Px
	 * @throws myException
	 */
	public MESSAGE_SelectField_Kategorie(MESSAGE_Editor editor) throws myException {
		this(100,false,editor);
		callingContainer = editor;
	}
	
	/**
	 * Standard-Selektfield der Kategorien
	 * mit einem Leer-Eintrag zu Beginn 
	 * 
	 * @param Width - Breite in Px
	 * @throws myException
	 */
	public MESSAGE_SelectField_Kategorie(int Width, MESSAGE_Editor editor) throws myException {
		this(Width,false,editor);
	}
	
	
	/**
	 * Selektfield der Kategorieren
	 * mit einem Leer-Eintrag zu beginn und einem "Keine Zuordnung"-Eintrag, für die Selektion 
	 * Die ID ist -1
	 * 
	 * @param Width
	 * @param bAddSelectionForUnsetKategorie
	 * @throws myException
	 */
	public MESSAGE_SelectField_Kategorie(int Width, boolean bAddSelectionForUnsetKategorie, MESSAGE_Editor editor) throws myException {
		this.callingContainer = editor;
		
		if (editor != null){
			htSelectObjects = editor.getCacheObject();
		}
		
		
		this.set_ListenInhalt(init_werte(bAddSelectionForUnsetKategorie),false);
		this.setWidth(new Extent(Width));
	}

	
	/**
	 * Singleton-Cache zum Verhindern dass die Dropdown-Inhalte mehrmals gelesen werden müssen
	 * @param bAddSelectionForUnsetKategorie
	 * @return
	 */
	private String[][] init_werte(boolean bAddSelectionForUnsetKategorie){
		String[][] cWerte;
		
		String sKey = this.getClass().getName() + "#" + Boolean.toString(bAddSelectionForUnsetKategorie);
		
		if (htSelectObjects != null && htSelectObjects.containsKey(sKey)){
			cWerte = (String[][])htSelectObjects.get(sKey);
		} else {
			
			String cQuery = "SELECT  KATEGORIE,ID_NACHRICHT_KATEGORIE FROM "+
					bibE2.cTO()+".JT_NACHRICHT_KATEGORIE WHERE ID_MANDANT="+bibALL.get_ID_MANDANT() + 
					" ORDER BY KATEGORIE ";
			
			String[][] cArray = bibDB.EinzelAbfrageInArray(cQuery,"");
			
			// mindestens 2 Elemente ( "-" und "keine Zuordnung")
			int nArrLen = 1;
			int iCount = 0;
			
			nArrLen += ( bAddSelectionForUnsetKategorie ? 1 : 0);
			nArrLen += ( cArray != null ? cArray.length : 0);
			cWerte = new String[nArrLen][2];
			
			// Erster Eintrag als Leerfeld
			cWerte[iCount][0]="-";
			cWerte[iCount][1]="";
			iCount++;
			
			if(bAddSelectionForUnsetKategorie){
				
				cWerte[iCount][0] = new MyString("** Keiner Kategorie zugeordnet **").CTrans();
				cWerte[iCount][1]="-1";
				iCount++;
			}
			
			for (int i=0;i<cArray.length;i++)
			{
				cWerte[iCount][0] = cArray[i][0];
				cWerte[iCount][1] = cArray[i][1];
				iCount++;
			}
			
			if (htSelectObjects != null){
				htSelectObjects.put(sKey, cWerte);
			}
			
			
		}
		
		
		return cWerte;
	}

	
	
}
