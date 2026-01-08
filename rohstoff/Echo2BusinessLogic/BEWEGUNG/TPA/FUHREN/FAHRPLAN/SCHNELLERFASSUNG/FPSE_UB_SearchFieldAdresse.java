package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.SCHNELLERFASSUNG;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.unboundDataFields.UB_MaskSearchField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.exceptions.myException;

public class FPSE_UB_SearchFieldAdresse extends UB_MaskSearchField
{

	public FPSE_UB_SearchFieldAdresse(String fieldName, boolean EmptyAllowed, int iTextWidthAnzeige, int iTextWitdhSearchText) throws myException
	{
//		super(	fieldName, EmptyAllowed, 
//				"JT_ADRESSE.ID_ADRESSE, NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'')||' '|| NVL(TO_CHAR(JT_FIRMENINFO.ID_FIRMENINFO),' <LIEFERADRESSE>')", 
//				bibE2.cTO()+".JT_ADRESSE, "+bibE2.cTO()+".JT_FIRMENINFO ", 
//				"NAME1,ORT",
//				" JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE (+) AND (JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO +
//				" OR JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE+") AND " +   //NEU_09
//				"   NVL(JT_ADRESSE.AKTIV,'N')='Y'", 
//				" UPPER(NAME1) LIKE UPPER('%#WERT#%') OR " +
//				  "UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
//				 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
//				 "OR LIEF_NR='#WERT#'  " +
//				 "OR ABN_NR='#WERT#'  " +
//				 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
//				 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
//				 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'", 
//				 null,
//				null, 
//				"SELECT NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'') ||' '|| NVL(TO_CHAR(JT_FIRMENINFO.ID_FIRMENINFO),' <LIEFERADRESSE>') "+
//				" FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE  JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE (+) AND " +
//				" JT_ADRESSE.ID_ADRESSE=#WERT#",
//				E2_INSETS.I_0_0_2_0, false);
		
//		super(	fieldName, EmptyAllowed, 
//				"JT_ADRESSE.ID_ADRESSE, NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,''), "+
//				"CASE WHEN (JT_FIRMENINFO.ID_FIRMENINFO IS NOT NULL) THEN TO_NCHAR('<Haupadresse>') ELSE TO_NCHAR('<Lieferadresse von: ')||"+
//				"("+
//				"SELECT NVL(AD.NAME1,'')||' '||NVL(AD.ORT,'') "+
//				"FROM "+bibE2.cTO()+".JT_ADRESSE AD INNER JOIN "+bibE2.cTO()+".JT_LIEFERADRESSE LI ON (AD.ID_ADRESSE=LI.ID_ADRESSE_BASIS)"+
//				"WHERE LI.ID_ADRESSE_LIEFER=JT_ADRESSE.ID_ADRESSE "+
//				")||TO_NCHAR('>') "+
//				"END",
//				
//				"JT_ADRESSE LEFT OUTER JOIN JT_FIRMENINFO ON (JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE)",
//				
//				"JT_ADRESSE.NAME1,JT_ADRESSE.ORT",
//				" (JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO +
//				" OR JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE+") AND " +   
//				"   NVL(JT_ADRESSE.AKTIV,'N')='Y'", 
//				" UPPER(NAME1) LIKE UPPER('%#WERT#%') OR " +
//				  "UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
//				 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
//				 "OR LIEF_NR='#WERT#'  " +
//				 "OR ABN_NR='#WERT#'  " +
//				 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
//				 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
//				 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'", 
//				 null,
//				null, 
//				"SELECT NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'') ||' '|| NVL(TO_CHAR(JT_FIRMENINFO.ID_FIRMENINFO),' <LIEFERADRESSE>') "+
//				" FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE  JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE (+) AND " +
//				" JT_ADRESSE.ID_ADRESSE=#WERT#",
//				E2_INSETS.I_0_0_2_0, false);


		super(	fieldName, EmptyAllowed, 
				"JT_ADRESSE.ID_ADRESSE, NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,''), "+
				"CASE WHEN (JT_FIRMENINFO.ID_FIRMENINFO IS NOT NULL) THEN TO_NCHAR('<Haupadresse>') ELSE TO_NCHAR('<Lieferadresse von: ')||"+
				"("+
				"SELECT NVL(AD.NAME1,'')||' '||NVL(AD.ORT,'') "+
				"FROM "+bibE2.cTO()+".JT_ADRESSE AD INNER JOIN "+bibE2.cTO()+".JT_LIEFERADRESSE LI ON (AD.ID_ADRESSE=LI.ID_ADRESSE_BASIS)"+
				"WHERE LI.ID_ADRESSE_LIEFER=JT_ADRESSE.ID_ADRESSE "+
				")||TO_NCHAR('>') "+
				"END",
				
				"JT_ADRESSE LEFT OUTER JOIN JT_FIRMENINFO ON (JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE)",
				
				"JT_ADRESSE.NAME1,JT_ADRESSE.ORT",
				" (" +
				" (JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_FIRMENINFO + " AND NVL(JT_ADRESSE.AKTIV,'N')='Y')"+
				" OR " +
				" ( JT_ADRESSE.ADRESSTYP="+myCONST.ADRESSTYP_LIEFERADRESSE+" AND NVL(JT_ADRESSE.AKTIV,'N')='Y' AND " +
				"   NVL((" +
							"  SELECT NVL(A.AKTIV,'N') FROM "+bibE2.cTO()+".JT_LIEFERADRESSE LI INNER JOIN  JT_ADRESSE A " +
									" ON (A.ID_ADRESSE=LI.ID_ADRESSE_BASIS) WHERE LI.ID_ADRESSE_LIEFER=JT_ADRESSE.ID_ADRESSE" +
					"),'N') = 'Y' "+
				" )" +
				") ",    
				
				" UPPER(NAME1) LIKE UPPER('%#WERT#%') OR " +
				  "UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
				 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
				 "OR LIEF_NR='#WERT#'  " +
				 "OR ABN_NR='#WERT#'  " +
				 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
				 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
				 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'", 
				 null,
				null, 
				"SELECT NVL(JT_ADRESSE.NAME1,'')||' '||NVL(JT_ADRESSE.ORT,'') ||' '|| NVL(TO_CHAR(JT_FIRMENINFO.ID_FIRMENINFO),' <LIEFERADRESSE>') "+
				" FROM "+bibE2.cTO()+".JT_ADRESSE,"+bibE2.cTO()+".JT_FIRMENINFO WHERE  JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE (+) AND " +
				" JT_ADRESSE.ID_ADRESSE=#WERT#",
				E2_INSETS.I_0_0_2_0, false);
		
		
		
		
		this.get_oTextForAnzeige().setWidth(new Extent(iTextWidthAnzeige));
		this.get_oTextFieldForSearchInput().setWidth(new Extent(iTextWitdhSearchText));
		
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
