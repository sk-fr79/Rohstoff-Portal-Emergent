package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class SEARCH_Sorte_ARCHIV extends MaskSearchField_WithAdditionalConditions
{
	
	
	
	public SEARCH_Sorte_ARCHIV(	) throws myException
	{
		super(		
					"JT_ARTIKEL.ID_ARTIKEL,JT_ARTIKEL.ANR1,JT_ARTIKEL.ARTBEZ1", 
					bibE2.cTO()+".JT_ARTIKEL ", 
					"ANR1",
					"  NVL(JT_ARTIKEL.AKTIV,'N')='Y'", 
					"UPPER(JT_ARTIKEL.ARTBEZ1) LIKE UPPER('%#WERT#%') OR UPPER(JT_ARTIKEL.ANR1) LIKE UPPER('%#WERT#%') OR TO_CHAR(JT_ARTIKEL.ID_ARTIKEL)='#WERT#'",                 //NEU_09
					null,
					null, 
					"SELECT trim(  NVL(ANR1,'-')) || ' - ' || trim(  NVL(JT_ARTIKEL.ARTBEZ1,'-')) "+
    									" from " + 
    									bibE2.cTO() + ".JT_ARTIKEL " +
    									"WHERE " +
    									"ID_ARTIKEL=#WERT#", 
    				E2_INSETS.I_0_0_2_0, 
    				true);
		
		
		this.set_oPosX(null);
		this.set_oPosY(null);
		
		
		// mögliche zusätzliche Parameter definieren
		SearchBlockStandard_WithAdditionalConditions oSearchBlock =  (SearchBlockStandard_WithAdditionalConditions)this.get_oSeachBlock();

		// suchen nach Sorten die einer Adresse zugeordnet sind, falls man die Adressid setzt
		String sSearchBlock = " ID_ARTIKEL IN 	( SELECT DISTINCT AB.ID_ARTIKEL FROM  " + bibE2.cTO() + ".JT_ARTIKEL_BEZ AB WHERE AB.ID_ARTIKEL_BEZ IN ( " +
							  "    							( SELECT ID_ARTIKEL_BEZ FROM " + bibE2.cTO() + ".JT_ARTIKELBEZ_LIEF ABL where ABL.ID_ADRESSE = #ID_ADR#) ) " +
							  " 				) ";
		
		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition(	UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE,
																			"ID_ADRESSE", 
																			"ID_ADR", 
																			sSearchBlock, 
																			"Zeigt nur Sorten an, die dem Kunden mit der ID #ID_ADR# in den Stammdaten zugewiesen sind. ",
																			false));
		
		
		
//		oSearchBlock.add_SearchConditionForARCHIVE(new DBSearchCondition(UP_AND_DOWNLOAD_ENUM_CONDITIONS.ID_ADRESSE, "AKTIV", "AKTIV_YN", "nvl(AKTIV,'N') = #AKTIV_YN#","Zeigt nur Sorten an die als Kennzeichen AKTIV den Wert #AKTIV_YN# gesetzt haben. ", true));
		
		
		
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			SEARCH_Sorte_ARCHIV oRueck = new SEARCH_Sorte_ARCHIV();
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			oRueck.get_oTextFieldForSearchInput().setWidth(this.get_oTextFieldForSearchInput().getWidth());
			oRueck.add_ValidatorStartSearch(this.get_ValidatorStartSearch());
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("Error Copying DB_SEARCH_Sorte: "+ex.get_ErrorMessage().get_cMessage().CTrans());
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
		return "ARTIKEL";
	}


}
