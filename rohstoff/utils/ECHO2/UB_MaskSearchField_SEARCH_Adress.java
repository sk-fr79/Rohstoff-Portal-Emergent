package rohstoff.utils.ECHO2;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.unboundDataFields.UB_MaskSearchField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class UB_MaskSearchField_SEARCH_Adress extends UB_MaskSearchField
{
	
	public UB_MaskSearchField_SEARCH_Adress(String cDBFieldName, boolean bEmptyAllowed) throws myException
	{
		super(		cDBFieldName,
					bEmptyAllowed,
					"JT_ADRESSE.ID_ADRESSE,NAME1,NAME2,PLZ,ORT", 
					bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_FIRMENINFO ", 
					"NAME1,ORT",
					"JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE AND JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO +" AND NVL(JT_ADRESSE.AKTIV,'N')='Y' ", 
					"UPPER(NAME1) LIKE UPPER('%#WERT#%') OR " +
					  "UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
					 "OR LIEF_NR='#WERT#'  " +
					 "OR ABN_NR='#WERT#'  " +
					 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
					 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'", 
					 null,
					 null, 
					 "SELECT trim(  NVL(NAME1,'-'))|| ' ' || trim(  NVL(NAME2,'-'))|| ', ' || trim(  NVL(STRASSE,'-')) || ', ' || trim(NVL(ORT,'-')) ||' " +
						" ('||trim(TO_CHAR(ID_ADRESSE))||')' FROM " + bibE2.cTO() + ".JT_ADRESSE WHERE ID_ADRESSE=#WERT#",
					E2_INSETS.I_0_0_5_0,
					false);
		
		this.set_oPosX(null);
		this.set_oPosY(null);

		this.set_oPopupWidth(new Extent(600));
		
		
	}



	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			UB_MaskSearchField_SEARCH_Adress oRueck = new UB_MaskSearchField_SEARCH_Adress(this.get_cDBFieldName(),this.get_bEmptyAllowd());
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			
			return oRueck;
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("DB_SEARCH_Adress:get_Copy:Error building copy-object: "+ex.get_ErrorMessage().get_cMessage().CTrans());
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

	
	
	
}
