package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SCHNELLERFASSUNG.COMPS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MaskSearchField.XX_SearchBlockNonDB;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class FUS_SearchAdresse__SearchBlock extends XX_SearchBlockNonDB
{

	private String 					cSQL_SelectBlock = "SELECT JT_ADRESSE.* FROM " +
													   " JT_ADRESSE LEFT OUTER JOIN JT_FIRMENINFO ON (JT_ADRESSE.ID_ADRESSE=JT_FIRMENINFO.ID_ADRESSE) "+
													   " WHERE ADRESSTYP IN (1,5) AND NVL("+RECORD_ADRESSE.FIELD__AKTIV+",'N')='Y'";
	
	
	
	private String 					cSQL_OrderBlock = "NAME1";
	
	
	private String     cSQL_WhereBlockForSelecting = "    UPPER(VORNAME) LIKE UPPER('%#WERT#%') " +
														" OR UPPER(NAME1) LIKE UPPER('%#WERT#%') " +
														" OR UPPER(NAME2) LIKE UPPER('%#WERT#%') " +
														 "OR UPPER(ORT) LIKE UPPER('%#WERT#%') " +
														 "OR LIEF_NR='#WERT#'  " +
														 "OR ABN_NR='#WERT#'  " +
														 "OR UPPER(STRASSE) LIKE UPPER('%#WERT#%') " +
														 "OR UPPER(PLZ) LIKE '%#WERT#%' " +
														 "OR TO_CHAR(JT_ADRESSE.ID_ADRESSE)='#WERT#'"; 

	
	
	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLOrderBlock
	 * @param iMaxresults
	 * @throws myException
	 */
	public FUS_SearchAdresse__SearchBlock() throws myException
	{
		super();
	}

	
	
	/**
	 * @param cSearchText
	 * @return  Vector with MyE2_Button - Components, in EXT().cMERKMAL steht der wert der gefundenen ersten spalte
	 * 			normalerweise ist das eine ID (unformatiert)
	 * @throws myException
	 */
	public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String SearchText) throws myException
	{

		String cSearchText	= SearchText.trim();

		if (!this.get_bAllowEmptySearchField() &&  bibALL.isEmpty(cSearchText))
			throw new myExceptionForUser(new MyE2_String("Bitte geben Sie zuerst einen Suchtext ein !").CTrans());
		
		Vector<XX_Button4SearchResultList[]> 	vRueck = new Vector<XX_Button4SearchResultList[]>();
		
		RECLIST_ADRESSE recListAdressen = this.get_Results(cSearchText);
		
		
		if (recListAdressen.get_vKeyValues().size()==0)
			return vRueck;     // nix gefunden
		
		
		for (int i=0;i<recListAdressen.get_vKeyValues().size();i++)
		{
			RECORD_ADRESSE recAdresse = recListAdressen.get(i);
			
			XX_Button4SearchResultList[] bZeile = new XX_Button4SearchResultList[5];
			

			Font oFontNormal = 	new E2_FontPlain();
			Font oFontBold = 	new E2_FontBold();
			Font oFontSmall = 	new E2_FontPlain(-2);
			
			
			if (recAdresse.get_ADRESSTYP_lValue(new Long(-1))==myCONST.ADRESSTYP_FIRMENINFO)
			{
				bZeile[0] = new ownButton(recAdresse.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1", "NAME2")), 	recAdresse.get_ID_ADRESSE_cUF(),oFontBold);
				bZeile[1] = new ownButton(recAdresse.get___KETTE(bibALL.get_Vector("STRASSE", "HAUSNUMMER")),		recAdresse.get_ID_ADRESSE_cUF(),oFontNormal);
				bZeile[2] = new ownButton(recAdresse.get___KETTE(bibALL.get_Vector("PLZ", "ORT")), 					recAdresse.get_ID_ADRESSE_cUF(),oFontNormal);
				bZeile[3] = new ownButton("<Firmensitz>", recAdresse.get_ID_ADRESSE_cUF(),oFontSmall);
				bZeile[4] = new ownButton(recAdresse.get_ID_ADRESSE_cF(), recAdresse.get_ID_ADRESSE_cUF(),oFontSmall);

			}
			else
			{
				RECORD_ADRESSE recBasis = recAdresse.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0).get_UP_RECORD_ADRESSE_id_adresse_basis();
				
				bZeile[0] = new ownButton(recAdresse.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1", "NAME2")), 	recAdresse.get_ID_ADRESSE_cUF(),oFontNormal);
				bZeile[1] = new ownButton(recAdresse.get___KETTE(bibALL.get_Vector("STRASSE", "HAUSNUMMER")),		recAdresse.get_ID_ADRESSE_cUF(),oFontNormal);
				bZeile[2] = new ownButton(recAdresse.get___KETTE(bibALL.get_Vector("PLZ", "ORT")), 					recAdresse.get_ID_ADRESSE_cUF(),oFontNormal);
				bZeile[3] = new ownButton("<Lieferadresse von "+recBasis.get___KETTE(bibALL.get_Vector("NAME1", "NAME2","ORT"))+">", recAdresse.get_ID_ADRESSE_cUF(),oFontSmall);
				bZeile[4] = new ownButton(recAdresse.get_ID_ADRESSE_cF(), recAdresse.get_ID_ADRESSE_cUF(),oFontSmall);

			}
			
			
			vRueck.add(bZeile);
		}
		
		return vRueck;
	}
	
	
	private class ownButton extends Button4SearchResultList
	{

		public ownButton(String cText, String cID_ADRESSE, Font oFont)
		{
			super(cText);
			this.EXT().set_C_MERKMAL(cID_ADRESSE);
			if (oFont!=null)
			{
				this.setFont(oFont);
			}
			this.setLineWrap(true);
		}
		
	}
	
	
	private RECLIST_ADRESSE get_Results(String SearchText) throws myException
	{
		String cSearchText = SearchText;
		
		String cSQL = null;

		Vector<String> vWheres = new Vector<String>();
		
		// jetzt nachsehen, ob aktuell ein weiterer where-statement-vector vorhanden ist
		if (this.get_vZusatzWhereBedingungen() != null && this.get_vZusatzWhereBedingungen().size()>0)
			vWheres.addAll(this.get_vZusatzWhereBedingungen());
		
		
		if (!bibALL.isEmpty(cSearchText))
		{
			StringSeparator 	oSeparator = new StringSeparator(cSearchText," ");

			for (int i=0;i<oSeparator.size();i++)
			{
				if (oSeparator.get(i) != null)
				{
					vWheres.add( "("+bibALL.ReplaceTeilString(this.cSQL_WhereBlockForSelecting,"#WERT#",(String)oSeparator.get(i))+")");
				}
			}
		}
		
		String cWhereComplete = bibALL.Concatenate(vWheres," AND ", null);
		String cOrder = "";
		if (!bibALL.isEmpty(this.cSQL_OrderBlock))
			cOrder = " ORDER BY "+this.cSQL_OrderBlock;
		
		cSQL = this.cSQL_SelectBlock+" AND "+cWhereComplete+cOrder;

		return new RECLIST_ADRESSE(cSQL);
	}
	
	
//	@Override
//	public Component get_ContainerWithFoundButtons()
//	{
//		return null;
//	}
//	

	
	@Override
	public E2_BasicModuleContainer get_ContainerForShowResults()
	{
		
		return new ownBasicModuleContainer();
	}
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{

		public ownBasicModuleContainer()
		{
			super();
			
			this.set_oExtWidth(new Extent(600));
			this.set_oExtHeight(new Extent(600));

		}
		
	}

}
