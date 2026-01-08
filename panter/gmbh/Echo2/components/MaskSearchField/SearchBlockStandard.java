package panter.gmbh.Echo2.components.MaskSearchField;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;



public class SearchBlockStandard extends XX_SearchBlockNonDB 
{
	protected String 					cSQL_SelectBlock = null;
	protected String 					cSQL_FromBlock = null;
	protected String 					cSQL_OrderBlock = null;
	protected String 					cSQL_WhereBlockJoiningTables = null;
	protected String 					cSQL_WhereBlockForSelecting = null;   // hier werden die platzhalter #WERT# durch die inhalte der eingabe-teilstrings ersetzt   
	/*
	 * wenn diese beiden parameter uebergeben werden, existiert eine schnelle,
	 * eindeutige suche, die in der cSQLQueryForUniqueSearch erfolgt.
	 * Dies wird immer dann ausgefuehrt, wenn der suchbegriff mit dem cCharForUniqueSearch beginnt
	 */
	protected String 					cSQLQueryForUniqueSearch= null; 
	protected String 					cCharForUniqueSearch = null;

	protected int						iMaxResults = 100;
	


	
	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLOrderBlock
	 * @param iMaxresults
	 * @throws myException
	 */
	public SearchBlockStandard(	String 		cSQLSelectBlock, 
								String 		cSQLFromBlock, 
								String 		cSQLWhereBlockJoiningTables, 
								String 		cSQLWhereBlockForSelecting, 
								String 		cSQLOrderBlock,
								String 		cSQLqueryForUniqueSearch, 
								String 		charForUniqueSearch, 
								int    		iMaxresults) throws myException
	{
		super();
		cSQL_FromBlock = cSQLFromBlock;
		cSQL_OrderBlock = cSQLOrderBlock;
		cSQL_SelectBlock = cSQLSelectBlock;
		cSQL_WhereBlockForSelecting = cSQLWhereBlockForSelecting;
		cSQL_WhereBlockJoiningTables = bibALL.null2leer(cSQLWhereBlockJoiningTables);
		this.cCharForUniqueSearch = charForUniqueSearch;
		this.cSQLQueryForUniqueSearch = cSQLqueryForUniqueSearch;
	
		this.iMaxResults = iMaxresults;

		if (this.cSQL_WhereBlockForSelecting.indexOf("#WERT#")==-1)
			throw new myException("DBSearchBlock:Constructor:Whereblock for Search MUST contain #WERT# !!!");
		
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
		
		String[][] cValues = this.get_Results(cSearchText);
		
		if (cValues.length==0)
			return vRueck;     // nix gefunden
		
		int iRowLength = cValues[0].length;
		if (iRowLength<=1)
			throw new myException("DBSearchBlock:get_vResultButtons:Query Result MUST contain at least 2 columns !!");
		
		for (int i=0;i<cValues.length;i++)
		{
			
			XX_Button4SearchResultList[] bZeile = new XX_Button4SearchResultList[iRowLength];
			for (int k=1;k<iRowLength;k++)
			{
				bZeile[k-1] = new Button4SearchResultList(new MyE2_String(cValues[i][k],false));     //zaehler startet mit 1
			}
			//am Ende einen button mit der ID anhaengen
			bZeile[iRowLength-1] = new Button4SearchResultList(new MyE2_String(cValues[i][0],false));

			for (int k=0;k<iRowLength;k++)
			{
				bZeile[k].EXT().set_C_MERKMAL(cValues[i][0]);
			}
			vRueck.add(bZeile);
		}
		
		return vRueck;
	}
	
	
	
	
	
	protected String[][] get_Results(String SearchText) throws myException
	{
		String cSearchText = SearchText;
		
		String cSQL = null;
		/*
		 * jetzt nachsehen, ob schnellsuche vorhanden ist
		 */
		if (!bibALL.isEmpty(this.cCharForUniqueSearch) &&  !bibALL.isEmpty(this.cSQLQueryForUniqueSearch) && cSearchText.startsWith(this.cCharForUniqueSearch))
		{
			cSearchText = cSearchText.substring(this.cCharForUniqueSearch.length());
			String cSQL_Help = bibALL.ReplaceTeilString(this.cSQLQueryForUniqueSearch,"#WERT#",cSearchText);
			cSQL=cSQL_Help;
		}
		else
		{
			Vector<String> vWheres = new Vector<String>();
			if (!bibALL.isEmpty(this.cSQL_WhereBlockJoiningTables))
				vWheres.add(this.cSQL_WhereBlockJoiningTables);
			
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
			
			if (bibALL.isEmpty(cWhereComplete))
				cSQL = "SELECT "+this.cSQL_SelectBlock+" FROM "+this.cSQL_FromBlock+" "+cOrder;
			else
				cSQL = "SELECT "+this.cSQL_SelectBlock+" FROM "+this.cSQL_FromBlock+" WHERE "+cWhereComplete+cOrder;
			
		}
		
		String[][] cValues = bibDB.EinzelAbfrageInArray(cSQL,"",this.iMaxResults);
		
		if (cValues == null)
			throw new myException("DBSearchBlock:get_vResultButtons:Error in Querying: "+cSQL);
		
		return cValues;
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
		
	}

	

}
