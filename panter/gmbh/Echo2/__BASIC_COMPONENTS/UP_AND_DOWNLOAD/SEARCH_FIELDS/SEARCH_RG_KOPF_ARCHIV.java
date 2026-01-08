package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.BSRG__CONST;

public class SEARCH_RG_KOPF_ARCHIV extends MaskSearchField_WithAdditionalConditions
{
	
	
	String m_sVorgangsart = null;
	/**
	 * 
	 * @author manfred
	 * @date   18.04.2013
	 * @param sVorgangsart : RECHNUNG oder GUTSCHRIFT
	 * @throws myException
	 */
	public SEARCH_RG_KOPF_ARCHIV(String sVorgangsart) throws myException
	{
		super(		//SELECT-BLOCK der Auswahl 
					" K.ID_VKOPF_RG," +
					" nvl2(K.VORNAME, K.VORNAME,'-'), " +
					" nvl2(K.NAME1, K.NAME1,'-'), " +
					" nvl2(K.NAME2, K.NAME2,'-'),  " +
					" nvl(K.ID_ADRESSE,0), " +
					" nvl2(K.ORT, K.ORT,'-'), " +
					" to_char (nvl2(K.DRUCKDATUM, K.DRUCKDATUM, K.ERSTELLUNGSDATUM),'dd.MM.yyyy') , " +
					" nvl2(K.BUCHUNGSNUMMER,K.BUCHUNGSNUMMER,'-'), " +
					" nvl2(K.WAEHRUNG_FREMD,K.WAEHRUNG_FREMD,'-'), " + 
					" to_char(  " + getSumStatementForRG(sVorgangsart) + ",'9,999,990.99' ) " 
					, 
					// FROM
					bibE2.cTO() + ".JT_VKOPF_RG K ",
					
					// ORDER BY
					" K.NAME1, nvl2(K.DRUCKDATUM,K.DRUCKDATUM,K.ERSTELLUNGSDATUM) DESC",
					
					// WHERE JOINING TABLES
					" K.VORGANG_TYP = '" + sVorgangsart.trim() + "' " +
					" AND NVL(K.DELETED,'N') = 'N' " , 
					
					// WHERE FUER SUCHE
					
					" ( " +
					" UPPER(K.VORNAME) LIKE UPPER('%#WERT#%') OR " +
					" UPPER(K.NAME1)   LIKE UPPER('%#WERT#%') OR " +
					" UPPER(K.NAME2)   LIKE UPPER('%#WERT#%') OR " +
					" UPPER(K.NAME3)   LIKE UPPER('%#WERT#%') OR " +
					" UPPER(K.ORT)     LIKE UPPER('%#WERT#%') OR " +
					" UPPER(K.BUCHUNGSNUMMER)  LIKE UPPER('%#WERT#%') OR " +
					" to_char(K.ID_VKOPF_RG) = ('#WERT#')  " +
					" ) " ,
					
					null,
					null,
					" select  " +
					" nvl2(K.VORNAME, K.VORNAME,'') ||  " +
					" nvl2(K.NAME1, ' ' || K.NAME1,'') ||  " +
					" nvl2(K.NAME2, ' ' || K.NAME2,'') || " +
					" nvl2(K.ORT,  ' ' || K.ORT,'') || " +
					" nvl2(K.ID_ADRESSE,' (' || K.ID_ADRESSE || ')' ,'') || " +
					" ' * ' || to_char (nvl2(K.DRUCKDATUM, K.DRUCKDATUM, K.ERSTELLUNGSDATUM),'dd.MM.yyyy') || " +
					" nvl2(K.BUCHUNGSNUMMER,' * ' ||K.BUCHUNGSNUMMER,'') || " +
					" nvl2(K.WAEHRUNG_FREMD,' * ' ||K.WAEHRUNG_FREMD || ' ','') || " +
					" to_char(  " + getSumStatementForRG(sVorgangsart) + ",'9,999,990.99' ) " +
					" from  " + bibE2.cTO() + ".JT_VKOPF_RG K " +
					" WHERE K.ID_VKOPF_RG = #WERT#",
					
					E2_INSETS.I_0_0_2_0, 
					true );
	
		
		m_sVorgangsart = sVorgangsart;
 		this.set_oPosX(null);
		this.set_oPosY(null);


		/**
		 * Mögliche Einschränkung der Kontrakte, wenn eine Adresse übergeben wurde
		 */
		SearchBlockStandard_WithAdditionalConditions oSearchBlock =  (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();
		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition( UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE, 
																		  "ID_ADRESSE", 
																		  "ID_ADRESSE", 
																		  "K.ID_ADRESSE = #ID_ADRESSE#", 
																		  "Es werden die Rechnungen/Gutschriften der Adresse mit der ID #ID_ADRESSE# gesucht. ", false));
//
//		/**
//		 * Mögliche Einschränkung der Kontrakte, wenn eine Sorte übergeben wurde
//		 */
//		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition( UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, 
//																		  "ID_ARTIKEL", 
//																		  "ID_ART", 
//																		  "JT_VKOPF_KON.ID_VKOPF_KON IN (SELECT KP.ID_VKOPF_KON FROM  "+bibE2.cTO()+".JT_VPOS_KON KP WHERE KP.ID_ARTIKEL = #ID_ART# )", 
//																		  "Die Suche wird auf die Kontrakte eingeschränkt, die die Sorte mit der ID #ID_ART# betreffen. ", 
//																		  false));

	}

	/** 
	 * generiert das Subselect für die Summe der Rechnung
	 * 
	 * @author manfred
	 * @date   18.04.2013
	 * @param sVorgangsart
	 * @return
	 * @throws myException
	 */
	private static String getSumStatementForRG(String sVorgangsart) throws myException{
		String sSqlSumRG = BSRG__CONST.get_SQL_4_RECH_GUT_ENDBETRAG_BRUTTO_FW(sVorgangsart, "K.ID_VKOPF_RG", true);
		return sSqlSumRG;
	}
	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			SEARCH_RG_KOPF_ARCHIV oRueck = new SEARCH_RG_KOPF_ARCHIV(m_sVorgangsart);
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			oRueck.get_oTextFieldForSearchInput().setWidth(this.get_oTextFieldForSearchInput().getWidth());
			oRueck.add_ValidatorStartSearch(this.get_ValidatorStartSearch());
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("Error Copying SEARCH_Kontrakt_ARCHIV: "+ex.get_ErrorMessage().get_cMessage().CTrans());
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
		return "VKOPF_RG";
	}
	

	
}
