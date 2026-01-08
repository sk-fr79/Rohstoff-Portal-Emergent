package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SEARCH_Maschinen_ARCHIV extends MaskSearchField_WithAdditionalConditions
{
	
	private static String cSQLDisplay = " SELECT  T.MASCHINENTYP || nvl2( M.KFZKENNZEICHEN ,' * ' || M.KFZKENNZEICHEN,'') ||  " +
			"  	nvl2(M.BESCHREIBUNG,' * ' || M.BESCHREIBUNG,'' ) || " +
			"  	nvl2(M.BRIEFNUMMER ,' * ' ||  M.BRIEFNUMMER, '') || " +
			"   nvl2(M.FAHRGESTELLNUMMER ,' * ' || M.FAHRGESTELLNUMMER , '') ||" +
			"   nvl2(M.HERSTELLER,' * ' || M.HERSTELLER , '') ||" +
			"   nvl2(M.TYPENBEZ,' * ' || M.TYPENBEZ , '') ||" +
			"   ' * ' || A.STRASSE || ' ' || A.ORT || ' (' || M.ID_MASCHINEN || ')'" +
			"   from  " + bibE2.cTO() + ".JT_MASCHINEN M" +
			"   INNER JOIN  " + bibE2.cTO() + ".JT_MASCHINENTYP T ON M.ID_MASCHINENTYP = T.ID_MASCHINENTYP" +
			"   LEFT OUTER JOIN  " + bibE2.cTO() + ".JT_ADRESSE A ON M.ID_ADRESSE_STANDORT = A.ID_ADRESSE" +
			"   WHERE M.ID_MASCHINEN = #WERT#" ;
	
	
	 
	/**
	 * 
	 * @author manfred
	 * @date   08.04.2013
	 * @throws myException
	 */
	public SEARCH_Maschinen_ARCHIV(	) throws myException
	{
		this(E2_INSETS.I_0_0_2_0);
	}

	
	/**
	 * aenderung 2010-11-18: weiterer konstructor, der definiert, ob lieferanten oder abnehmer hervorgehoben werden sollen 	
	 * @author 
	 * @date   
	 * @param MarkLieferanten
	 * @param MarkAbnehmer
	 * @param oInsets4Components
	 * @throws myException
	 */
	public SEARCH_Maschinen_ARCHIV(	 Insets oInsets4Components) throws myException
	{
		
		super(		//SELECT-BLOCK der Auswahl 
					" M.ID_MASCHINEN, " +
					" T.MASCHINENTYP, " + 
					" nvl2( M.KFZKENNZEICHEN , M.KFZKENNZEICHEN,'-'), " +
					" nvl2(M.BRIEFNUMMER ,  M.BRIEFNUMMER, '-') ," +
					" nvl2(M.FAHRGESTELLNUMMER ,M.FAHRGESTELLNUMMER , '-')," +
					" nvl2(M.HERSTELLER, M.HERSTELLER , '-') ," +
					" nvl2(M.TYPENBEZ, M.TYPENBEZ , '-') ," +
					" nvl(A.STRASSE,'-') , " +
					" nvl(A.ORT,'-') ", 
					
					// FROM
					" "+bibE2.cTO()+".JT_MASCHINEN M " +
					" INNER JOIN  "+bibE2.cTO()+".JT_MASCHINENTYP T ON M.ID_MASCHINENTYP = T.ID_MASCHINENTYP" +
					" LEFT OUTER JOIN  "+bibE2.cTO()+".JT_ADRESSE A ON M.ID_ADRESSE_STANDORT = A.ID_ADRESSE",
					
					// ORDER BY
					" T.MASCHINENTYP,M.KFZKENNZEICHEN,M.BESCHREIBUNG",

					// WHERE JOINING TABLES
					" nvl(M.AKTIV,'N') = 'Y'  ",
					
					// WHERE FUER SUCHE
					" (UPPER(T.MASCHINENTYP) LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(M.KFZKENNZEICHEN)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(M.BESCHREIBUNG)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(M.BRIEFNUMMER)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(M.FAHRGESTELLNUMMER)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(M.HERSTELLER)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(M.TYPENBEZ)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(A.STRASSE)  LIKE UPPER ('%#WERT#%')  " +
					" OR  UPPER(A.ORT)  LIKE UPPER ('%#WERT#%') " +
					" OR TO_CHAR(M.ID_MASCHINEN)='#WERT#'" +
					" )" 
					,
					 
					 null,
					 null,
					 cSQLDisplay,
					 oInsets4Components, 
					 true);
			
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		
		
		SearchBlockStandard_WithAdditionalConditions oSearchBlock = (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();

		// Suche nach Adressen denen ein bestimmter Artikel zugeordnet wurde
//		String sSearchBlock = "  JT_ADRESSE.ID_ADRESSE IN (SELECT DISTINCT ABL.ID_ADRESSE FROM "+bibE2.cTO()+".JT_ARTIKELBEZ_LIEF ABL WHERE ABL.ID_ARTIKEL_BEZ IN (SELECT AB.ID_ARTIKEL_BEZ FROM "+bibE2.cTO()+".JT_ARTIKEL_BEZ AB WHERE AB.ID_ARTIKEL = #ID_ART#) )" ;
//		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, 
//																		"ID_ARTIKEL", 
//																		"ID_ART", 
//																		sSearchBlock,
//																		"Die Suche wird eingeschränkt auf Adressen, die die Sorte mit der ID #ID_ART# zugewiesen haben.", false));
//		

			
	}
	
	
	

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			SEARCH_Maschinen_ARCHIV oRueck = new SEARCH_Maschinen_ARCHIV();
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("SEARCH_Maschinen_ARCHIV:get_Copy:Error building copy-object: "+ex.get_ErrorMessage().get_cMessage().CTrans());
		}
		
	}



	@Override
	public E2_BasicModuleContainer get_ownContainer() throws myException
	{
		return new ownE2_Container();
	}
	
	

	private class ownE2_Container extends E2_BasicModuleContainer
	{
		public ownE2_Container()
		{
			super();
		}
	}


	
		

	@Override
	public String get_DBTableName() {
		return "MASCHINEN";
	}
	
}
