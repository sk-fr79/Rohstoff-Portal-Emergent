/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.Search;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;



/**
 * hilfsklasse, um die selektion der einzelnen suchmoeglicheiten zu
 * ermoeglichen, d.h. dass im popup-menue einzelne suchfelder ausgeschaltet werden
 * koennen
 */
public class E2_SearchDefinition
{
	private MyE2_CheckBox 	oCheckIsEnabled = new MyE2_CheckBox();
	/*
	 * searchstring ist komplette abfrage, die eine ID liefert und die einen parameter #WERT# im where-block enthaelt
	 */
	private String 			SearchString	= null;
	
	private MyString 		cTextForList = null;
	
	/*
	 * wenn diese vorhanden ist, dann wird als beschriftung immer die in der komponente
	 * vorhandene TextForUser - eigenschaft benutzt
	 */
	private MyE2IF__Component oComponent = null; 
	
	private SQLFieldMAP 		SQLField_MAP = null;
	
	/*
	 * statuseinstellung beim oeffnen der suchfeld-auswahl
	 */
	private boolean  			bOldStatusActiv = true;
	
	
	//2011-12-16: weitere bedingung, die waehrend der suche kontrolliert werden kann (z.B. suche in abhaengigen liste
	private XX_SearchAddonBedingung   oSearchAddon = null;
	
	
	
	//2012-02-06: fuer komplexe suchen kann die einschalt-checkbox inaktiv werden, sodass nur selektive suche moeglich ist
	private  boolean         	bOnlySingleSearch =false;
	
	
	//2015-10-12: neuer leerer konstruktor
	
	
	
	/**
	 * @param isEenabled
	 * @param searchString
	 * @param TextForList
	 * @param ocomponent
	 * @param oSQLFieldMAP wird fuer den fall eines oder mehrerer SQLFieldForRestrictTableRange uebergeben,
	 * 						Damit kann die Bedingung der Restriction hier beim bauen des sql-querys beruecksichtigt werden.
	 * 						Die Bedingung wird immer mit AND ... an den eigentlichen SQL-Query-String angehaengt 
	 * @throws myException
	 */
	public E2_SearchDefinition(		boolean 			isEenabled, 
									String 				searchString,
									MyString 			TextForList,
									MyE2IF__Component 	ocomponent, 
									SQLFieldMAP 		oSQLFieldMAP) throws myException
	{
		super();
//		this.oCheckIsEnabled.setSelected(isEenabled);
//		this.SearchString = searchString;
//		this.cTextForList = TextForList;
//		this.oComponent = ocomponent;
//		this.SQLField_MAP = oSQLFieldMAP;
//		
//		if (bibALL.isEmpty(this.SearchString))
//			throw new myException("SearchDefinition:Contructor:Emtpy Searchstring not allowed !!");
//		
//		if (this.SearchString.indexOf("#WERT#")<0)
//			throw new myException("SearchDefinition:Contructor:Phrase #WERT# MUST BE in searchstring !!");
		
		this.init(isEenabled, searchString, TextForList, ocomponent, oSQLFieldMAP);
		
		
	}
	
	
	
	public E2_SearchDefinition() {
		super();
	}



	public void init(		boolean 			isEenabled, 
							String 				searchString,
							MyString 			TextForList,
							MyE2IF__Component 	ocomponent, 
							SQLFieldMAP 		oSQLFieldMAP) throws myException {
		
		
		this.oCheckIsEnabled.setSelected(isEenabled);
		this.SearchString = searchString;
		this.cTextForList = TextForList;
		this.oComponent = ocomponent;
		this.SQLField_MAP = oSQLFieldMAP;
		
		if (bibALL.isEmpty(this.SearchString))
			throw new myException("SearchDefinition:Contructor:Emtpy Searchstring not allowed !!");
		
		if (this.SearchString.indexOf("#WERT#")<0)
			throw new myException("SearchDefinition:Contructor:Phrase #WERT# MUST BE in searchstring !!");

		
	}
	
	
	
	public MyE2_CheckBox get_oCheckIsEnabled()							{			return oCheckIsEnabled;		}
	
	
	
	
	/**
	 * @return s SQL-Query
	 * @throws myException
	 */
	public String get_cSearchString()	throws myException								
	{			
		
		String cRueck = SearchString;
		
		// jetzt nachsehen, ob eine SQLFieldMAP mit RestrictionFields uebergeben wurde
		if (this.SQLField_MAP != null)
		{
			String cWhereZusatz = this.SQLField_MAP.get_cSQL_WHERE_BLOCK_FROM_RestrictFields_FROM_MAIN_TABLE();
			
			if (! bibALL.isEmpty(cWhereZusatz))
			{
				cRueck += " AND "+cWhereZusatz;
			}
		}
		
		
		//2011-12-16: weitere, live-bedingung
		if (this.oSearchAddon!=null)
		{
			cRueck=cRueck +" AND "+this.oSearchAddon.get_AddOnBedingung();
		}
		
		return cRueck;		
	}
	
	
	
	public MyString get_cTextForList()									
	{
		MyString cText = this.cTextForList;
		if (cText ==null && this.oComponent != null)
			cText = this.oComponent.EXT().get_cList_or_Mask_Titel();
		
		if (cText == null)
			cText = new MyE2_String(" NO INFO TEXT ");
			
		return cText;		
	}
	

	
	/**
	 * @return s sourcecode-text used for checkbox-label
	 */
	public String get_cOrigTextLabel()									
	{
		MyString cText = this.cTextForList;
		if (cText ==null && this.oComponent != null)
			cText = this.oComponent.EXT().get_cList_or_Mask_Titel();
		
		if (cText == null)
			cText = new MyE2_String(" NO INFO TEXT ");
			
		return cText.COrig();		
	}
	
	public boolean get_bOldStatusActiv() 
	{
		return bOldStatusActiv;
	}
	
	public void set_bOldStatusActiv(boolean oldStatusActiv) 
	{
		bOldStatusActiv = oldStatusActiv;
	}
	
	public XX_SearchAddonBedingung get_oSearchAddon()
	{
		return oSearchAddon;
	}
	public void set_oSearchAddon(XX_SearchAddonBedingung oSearchAddon)
	{
		this.oSearchAddon = oSearchAddon;
	}
	
	public boolean get_bOnlySingleSearch()
	{
		return bOnlySingleSearch;
	}
	
	public void set_bOnlySingleSearch(boolean OnlySingleSearch) throws myException
	{
		this.bOnlySingleSearch = OnlySingleSearch;
		
		if (this.bOnlySingleSearch)
		{
			this.oCheckIsEnabled.setSelected(false);
			this.oCheckIsEnabled.set_bEnabled_For_Edit(false);
		}
	}
	
	
	public void set_SearchString(String searchString)
	{
		SearchString = searchString;
	}
	
	public SQLFieldMAP get_oSQLField_MAP()
	{
		return SQLField_MAP;
	}

	
	
}