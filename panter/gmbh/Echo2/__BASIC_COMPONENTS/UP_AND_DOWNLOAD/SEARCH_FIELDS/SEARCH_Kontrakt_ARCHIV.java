package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SEARCH_Kontrakt_ARCHIV extends MaskSearchField_WithAdditionalConditions
{

	public SEARCH_Kontrakt_ARCHIV() throws myException
	{
		super(		"JT_VPOS_KON.ID_VPOS_KON,JT_VKOPF_KON.NAME1,JT_VKOPF_KON.NAME2,JT_VKOPF_KON.PLZ,JT_VKOPF_KON.ORT,JT_VPOS_KON.ANR1,JT_VPOS_KON.ARTBEZ1", 
					bibE2.cTO()+".JT_VKOPF_KON,"+bibE2.cTO()+".JT_VPOS_KON,"+bibE2.cTO()+".JT_VPOS_KON_TRAKT", 
					"NAME1",
					"JT_VKOPF_KON.ID_VKOPF_KON = JT_VPOS_KON.ID_VKOPF_KON AND " +
					"JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND " +
					"NVL(JT_VPOS_KON_TRAKT.ABGESCHLOSSEN,'N')='N' AND " +
					"NVL(JT_VPOS_KON.DELETED,'N')='N' AND NVL(JT_VKOPF_KON.DELETED,'N')='N'", 
					"UPPER(JT_VKOPF_KON.NAME1) LIKE '%#WERT#%' OR " +
					"UPPER(JT_VKOPF_KON.NAME2) LIKE '%#WERT#%'  OR " +
					"UPPER(JT_VKOPF_KON.ORT) LIKE '%#WERT#%' OR " +
					"UPPER(JT_VPOS_KON.ANR1) LIKE '%#WERT#%' OR " +
					"UPPER(JT_VPOS_KON.ARTBEZ1) LIKE '%#WERT#%' OR " +
					"TO_CHAR(JT_VKOPF_KON.ID_ADRESSE)='#WERT#' OR "+ 
					"TO_CHAR(JT_VPOS_KON.ID_VPOS_KON)='#WERT#'", 
					null,
					null,
					"SELECT NVL(JT_VKOPF_KON.NAME1,'-')||' - '|| NVL(JT_VKOPF_KON.ORT,'-')||' - '||trim(to_char(floor( NVL(JT_VPOS_KON.ANZAHL,0))))||' - '|| NVL(JT_VPOS_KON.EINHEITKURZ,'-')||' - '|| NVL(JT_VPOS_KON.ANR1,'-')||' - '|| NVL(JT_VPOS_KON.ARTBEZ1,'-') " +
					" FROM "+bibE2.cTO()+".JT_VKOPF_KON, 	"+bibE2.cTO()+".JT_VPOS_KON, 	"+bibE2.cTO()+".JT_VPOS_KON_TRAKT " +
					" WHERE  " +
					" JT_VKOPF_KON.ID_VKOPF_KON = JT_VPOS_KON.ID_VKOPF_KON AND JT_VPOS_KON.ID_VPOS_KON = JT_VPOS_KON_TRAKT.ID_VPOS_KON AND JT_VPOS_KON_TRAKT.ID_VPOS_KON=#WERT#", 
					E2_INSETS.I_0_0_2_0, 
					true);
		
 		this.set_oPosX(null);
		this.set_oPosY(null);


		/**
		 * Mögliche Einschränkung der Kontrakte, wenn eine Adresse übergeben wurde
		 */
		SearchBlockStandard_WithAdditionalConditions oSearchBlock =  (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();
		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition( UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE, 
																		  "ID_ADRESSE", 
																		  "ID_ADRESSE", 
																		  "JT_VKOPF_KON.ID_ADRESSE = #ID_ADRESSE#", 
																		  "Es werden die Kontrakte der Adresse mit der ID #ID_ADRESSE# gesucht. ", false));

		/**
		 * Mögliche Einschränkung der Kontrakte, wenn eine Sorte übergeben wurde
		 */
		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition( UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ARTIKEL, 
																		  "ID_ARTIKEL", 
																		  "ID_ART", 
																		  "JT_VKOPF_KON.ID_VKOPF_KON IN (SELECT KP.ID_VKOPF_KON FROM  "+bibE2.cTO()+".JT_VPOS_KON KP WHERE KP.ID_ARTIKEL = #ID_ART# )", 
																		  "Die Suche wird auf die Kontrakte eingeschränkt, die die Sorte mit der ID #ID_ART# betreffen. ", 
																		  false));

	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			SEARCH_Kontrakt_ARCHIV oRueck = new SEARCH_Kontrakt_ARCHIV();
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
		return "VKOPF_KON";
	}



	

	
}
