package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Hashtable;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_SelectField_MessageTyp extends MyE2_SelectField {
	
	
	/**
	 * Standard-Selektfield des Meldungstyps (USER / SYSTEM)
	 * und einer Breite von 100 Px
	 * @throws myException
	 */
	public MESSAGE_SelectField_MessageTyp() throws myException {
		this(100);
	}
	

	/**
	 * @param Width
	 * @throws myException
	 */
	public MESSAGE_SelectField_MessageTyp(int Width ) throws myException {
		this.set_ListenInhalt(init_werte(null),false);
		this.setWidth(new Extent(Width));
	}
	
	/**
	 * Selektfield der Kategorieren
	 * mit einem Leer-Eintrag zu beginn und einem "Keine Zuordnung"-Eintrag, für die Selektion 
	 * Die ID ist -1
	 * 
	 * @param Width
	 * @param sTableAlias : falls man einen Alias braucht, der nicht "JT_NACHRICHT" ist, kann man diesen hier definieren
	 * @throws myException
	 */
	public MESSAGE_SelectField_MessageTyp(int Width, String sTableAlias) throws myException {
		
		this.set_ListenInhalt(init_werte(sTableAlias),false);
		this.setWidth(new Extent(Width));
	}


	/**
	 * füllt die Werte-Menge mit Daten
	 * 
	 * @author manfred
	 * @date   08.07.2015
	 *
	 * @param sTableAlias
	 * @return
	 */
	private String[][] init_werte(String sTableAlias){
		String[][] cWerte;
		
		String sTable = RECORD_NACHRICHT.TABLENAME;
		if (! bibALL.isEmpty(sTableAlias)  ){
			sTable = sTableAlias;
		}
		sTable = sTable + ".";
		
		// MessageTyp
		cWerte = new String[][]{
				{new MyE2_String("Benutzer").CTrans(),	sTable + "TYP_NACHRICHT = '" + MESSAGE_CONST.MESSAGE_TYP_USER  + "'" },
				{new MyE2_String("System").CTrans(),	sTable + "TYP_NACHRICHT = '" + MESSAGE_CONST.MESSAGE_TYP_SYSTEM + "'" },
				{new MyE2_String("Alle").CTrans(),""}};	
		return cWerte;
	}

	
	
}
