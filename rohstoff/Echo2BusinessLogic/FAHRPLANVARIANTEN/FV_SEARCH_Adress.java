package rohstoff.Echo2BusinessLogic.FAHRPLANVARIANTEN;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class FV_SEARCH_Adress extends MyE2_DB_MaskSearchField
{
	/**
	 * @param oContentPane
	 * @param oMessageagent
	 * @param osqlField
	 * @param osqlFieldGroup
	 * @throws myException
	 * spezielles suchfeld nach adressen, koennen sowohl 
	 * Basis- als auch Lieferadressen sein
	 */
	public FV_SEARCH_Adress(	SQLField osqlField) throws myException
	{
		super(		osqlField, 
					"JT_ADRESSE.ID_ADRESSE,NAME1,NAME2,PLZ,ORT", 
					bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_FIRMENINFO ", 
					"NAME1,ORT",
					"JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE (+) AND (JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO +" OR JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE+") ", 
					"UPPER(NAME1) LIKE UPPER('%#WERT#%') OR " +
					  "UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
					 "OR LIEF_NR='#WERT#'  " +
					 "OR ABN_NR='#WERT#'  " +
					 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
					 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
					 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'", 
					null ,
					 null, 
					 "SELECT trim(  NVL(NAME1,'-'))|| ' ' || trim(  NVL(NAME2,'-'))|| ', ' || trim(  NVL(STRASSE,'-')) || ', ' || trim(  NVL(ORT,'-')) ||' " +
						" ('||TRIM(TO_CHAR(ID_ADRESSE))||')' FROM " + bibE2.cTO() + ".JT_ADRESSE WHERE ID_ADRESSE=#WERT#", null, false);
		
		this.get_oTextFieldForSearchInput().set_iWidthPixel(100);
		this.get_oTextForAnzeige().setWidth(new Extent(150));
	}

	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try
		{
			return new FV_SEARCH_Adress(this.EXT_DB().get_oSQLField());
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("Error Copying FB_SEARCH_Adress: "+ex.get_ErrorMessage().get_cMessage().CTrans());
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
