package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.KREDITVERSICH;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.DB.MaskSearchField.MyE2_DB_MaskSearchField;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class DB_SearchFieldAdresse extends MyE2_DB_MaskSearchField
{

	public DB_SearchFieldAdresse(SQLField osqlfield, boolean EmptyAllowed, int iTextWidthAnzeige, int iTextWitdhSearchText) throws myException
	{
		super( 	osqlfield,
				
				" ADR.ID_ADRESSE, " +
				" " +
				" CASE  WHEN  ADR.ADRESSTYP = 1 THEN NVL(ADR.VORNAME,'-')            ELSE    " +
				" (   SELECT NVL(A2.VORNAME,'-')                    FROM  "+bibE2.cTO()+".JT_ADRESSE A2 WHERE A2.ID_ADRESSE =    " +
				"      (SELECT L2.ID_ADRESSE_BASIS FROM  "+bibE2.cTO()+".JT_LIEFERADRESSE L2 WHERE L2.ID_ADRESSE_LIEFER = ADR.ID_ADRESSE AND L2.ID_MANDANT = ADR.ID_MANDANT) " +
				" ) END as VORNAME_BASIS," +
				" CASE  WHEN  ADR.ADRESSTYP = 1 " +
				"		THEN  NVL(ADR.NAME1,'')|| NVL2(ADR.NAME2, ' ' || ADR.NAME2,'') " +
				"  		ELSE   (   SELECT  NVL(A2.NAME1,'')|| NVL2(A2.NAME2, ' ' || A2.NAME2,'')   " +
				"             FROM  "+bibE2.cTO()+".JT_ADRESSE A2 WHERE A2.ID_ADRESSE =  " +
				"				(SELECT L2.ID_ADRESSE_BASIS FROM  "+bibE2.cTO()+".JT_LIEFERADRESSE L2 WHERE L2.ID_ADRESSE_LIEFER = ADR.ID_ADRESSE AND L2.ID_MANDANT = ADR.ID_MANDANT)" +
				"  ) END as NAME_BASIS," +
				" CASE  WHEN  ADR.ADRESSTYP = 1 THEN " +
				"                NVL(ADR.PLZ,'') || NVL2(ADR.ORT,' ' || ADR.ORT,'') " +
				"           ELSE ( SELECT  NVL(A3.PLZ,'') || NVL2(A3.ORT,' ' || A3.ORT,'')" +
				"                    FROM  "+bibE2.cTO()+".JT_ADRESSE A3 WHERE A3.ID_ADRESSE =  " +
				"                    ( SELECT L3.ID_ADRESSE_BASIS FROM  "+bibE2.cTO()+".JT_LIEFERADRESSE L3" +
				"                    WHERE L3.ID_ADRESSE_LIEFER = ADR.ID_ADRESSE AND L3.ID_MANDANT = ADR.ID_MANDANT)" +
				"  ) END as ORT_BASIS," +
				" CASE  WHEN  ADR.ADRESSTYP = 1 THEN " +
				"               NVL(ADR.STRASSE,'') || NVL2(ADR.HAUSNUMMER,' ' || ADR.HAUSNUMMER,'') " +
				"            ELSE " +
				"                  ( SELECT NVL(A3.STRASSE,'') || NVL2(A3.HAUSNUMMER,' ' || A3.HAUSNUMMER,'')" +
				"                     FROM  "+bibE2.cTO()+".JT_ADRESSE A3 WHERE A3.ID_ADRESSE = " +
				"                    	( SELECT L3.ID_ADRESSE_BASIS FROM  "+bibE2.cTO()+".JT_LIEFERADRESSE L3 " +
				" 						WHERE L3.ID_ADRESSE_LIEFER = ADR.ID_ADRESSE AND L3.ID_MANDANT = ADR.ID_MANDANT)" +
				" ) END as STRASSE_BASIS," +
				" CASE  WHEN  ADR.ADRESSTYP = 5 THEN " +
				"               'Lieferadr.: ' || NVL(ADR.NAME1,'')|| NVL2(ADR.NAME2, ' ' || ADR.NAME2,'') || ', ' || NVL(ADR.PLZ,'') || NVL2(ADR.ORT,' ' || ADR.ORT,'') " +
				"				|| ' ' || NVL(ADR.STRASSE,'') || NVL2(ADR.HAUSNUMMER,' ' || ADR.HAUSNUMMER,'') " +
				"           ELSE " +
				"                to_nchar('-')" +
				" END as NAME_LIEFER" ,
				
				bibE2.cTO()+".JT_ADRESSE ADR " +
				" LEFT OUTER JOIN  "+bibE2.cTO()+".JT_FIRMENINFO  ON ADR.ID_ADRESSE = JT_FIRMENINFO.ID_ADRESSE 			AND ADR.ID_MANDANT = JT_FIRMENINFO.ID_MANDANT " +
				" LEFT OUTER JOIN "+bibE2.cTO()+".JT_LIEFERADRESSE LIEF ON ADR.ID_ADRESSE = LIEF.ID_ADRESSE_LIEFER  		AND ADR.ID_MANDANT = LIEF.ID_MANDANT " +
				" LEFT OUTER JOIN "+bibE2.cTO()+".JT_ADRESSE ADR_BASIS ON ADR_BASIS.ID_ADRESSE = LIEF.ID_ADRESSE_BASIS  	AND ADR_BASIS.ID_MANDANT = LIEF.ID_MANDANT " +
				"",
				
				" 3,ADR.ADRESSTYP ",
				
				" ( ADR.ADRESSTYP = " + myCONST.ADRESSTYP_FIRMENINFO + " OR ADR.ADRESSTYP=" + myCONST.ADRESSTYP_LIEFERADRESSE + ") " +   //NEU_09
				" AND NVL( CASE WHEN ADR.ADRESSTYP = " + myCONST.ADRESSTYP_LIEFERADRESSE + " THEN ADR_BASIS.AKTIV ELSE ADR.AKTIV END  ,'N') = 'Y' " +
				" AND NVL(ADR.AKTIV,'N') = 'Y' " ,
				
				" UPPER(ADR.NAME1) LIKE UPPER('%#WERT#%') OR " +
				" UPPER(ADR.NAME2) LIKE UPPER('%#WERT#%') " +
				" OR UPPER(ADR.ORT) LIKE UPPER('%#WERT#%') " +
				" OR ADR.LIEF_NR='#WERT#'  " +
				" OR ADR.ABN_NR='#WERT#'  " +
				" OR UPPER(ADR.STRASSE) LIKE UPPER('%#WERT#%') " +
				" OR UPPER(ADR.PLZ) LIKE '%#WERT#%' " +
				" OR TO_CHAR(ADR.ID_ADRESSE)='#WERT#'" , 
				null,
				null,
				" SELECT " +
				"	CASE  WHEN ADR.ADRESSTYP = 1 " +
				"        THEN  NVL(ADR.NAME1,'')|| NVL2(ADR.NAME2, ' ' || ADR.NAME2,'') || ', ' ||    NVL(ADR.PLZ,'') || NVL2(ADR.ORT,' ' || ADR.ORT,'') || ', ' || NVL(ADR.STRASSE,'') || NVL2(ADR.HAUSNUMMER,' ' || ADR.HAUSNUMMER,'') " +
				"        ELSE    ( " +
				"   	     SELECT  NVL(A2.NAME1,'')|| NVL2(A2.NAME2, ' ' || A2.NAME2,'') || ', ' ||  NVL(A2.PLZ,'') || NVL2(A2.ORT,' ' || A2.ORT,'') || ', ' ||   NVL(A2.STRASSE,'') || NVL2(A2.HAUSNUMMER,' ' || A2.HAUSNUMMER,'') " +
				"       	 FROM   "+bibE2.cTO()+".JT_ADRESSE A2 " +
				"        	WHERE  A2.ID_ADRESSE =  (  SELECT  L2.ID_ADRESSE_BASIS  FROM    "+bibE2.cTO()+".JT_LIEFERADRESSE L2     WHERE       L2.ID_ADRESSE_LIEFER = ADR.ID_ADRESSE       AND L2.ID_MANDANT    = ADR.ID_MANDANT        ) " +
				"		)  END ||  " +
				" CASE  WHEN ADR.ADRESSTYP = 5 " +
				"         THEN ' ( Lieferadr.: ' || NVL(ADR.NAME1,'')|| NVL2(ADR.NAME2, ' ' || ADR.NAME2,'') || ', ' || NVL(ADR.PLZ,'') || NVL2(ADR.ORT,' ' || ADR.ORT,'') || ' ' || NVL(ADR.STRASSE,'') || NVL2(ADR.HAUSNUMMER,' ' || ADR.HAUSNUMMER,'') || ' )'" +
				"        ELSE to_nchar('')    END as NAME " +
				" FROM   "+bibE2.cTO()+".JT_ADRESSE ADR  " +
				" WHERE ADR.ID_ADRESSE = #WERT# " 
				,
				
				E2_INSETS.I_0_0_0_0,
				true);
		
		

		this.get_oTextForAnzeige().setWidth(new Extent(iTextWidthAnzeige));
		this.get_oLabel4Anzeige().setWidth(new Extent(iTextWidthAnzeige));
		
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
