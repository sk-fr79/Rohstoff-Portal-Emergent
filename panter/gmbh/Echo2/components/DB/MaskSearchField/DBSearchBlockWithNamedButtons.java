package panter.gmbh.Echo2.components.DB.MaskSearchField;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.Button4SearchResultList;
import panter.gmbh.Echo2.components.MaskSearchField_DB_And_Normal.XX_Button4SearchResultList;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.VectorDataBaseQuery;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionForUser;

public class DBSearchBlockWithNamedButtons extends XX_SearchBlock 
{
	private String 					cSQL_SelectBlock = null;
	private String 					cSQL_FromBlock = null;
	private String 					cSQL_OrderBlock = null;
	private String 					cSQL_WhereBlockJoiningTables = null;
	private String 					cSQL_WhereBlockForSelecting = null;   // hier werden die platzhalter #WERT# durch die inhalte der eingabe-teilstrings ersetzt   
	/*
	 * wenn diese beiden parameter uebergeben werden, existiert eine schnelle,
	 * eindeutige suche, die in der cSQLQueryForUniqueSearch erfolgt.
	 * Dies wird immer dann ausgefuehrt, wenn der suchbegriff mit dem cCharForUniqueSearch beginnt
	 */
	private String 					cSQLQueryForUniqueSearch= null; 
	private String 					cCharForUniqueSearch = null;

	

	/**
	 * @param cSQLSelectBlock
	 * @param cSQLFromBlock
	 * @param cSQLWhereBlockJoiningTables
	 * @param cSQLWhereBlockForSelecting
	 * @param cSQLOrderBlock
	 * @param iMaxresults
	 * @throws myException
	 */
	public DBSearchBlockWithNamedButtons(	String 		cSQLSelectBlock, 
											String 		cSQLFromBlock, 
											String 		cSQLWhereBlockJoiningTables, 
											String 		cSQLWhereBlockForSelecting, 
											String 		cSQLOrderBlock,
											String 		cSQLqueryForUniqueSearch, 
											String 		charForUniqueSearch) throws myException
	{
		super();
		cSQL_FromBlock = cSQLFromBlock;
		cSQL_OrderBlock = cSQLOrderBlock;
		cSQL_SelectBlock = cSQLSelectBlock;
		cSQL_WhereBlockForSelecting = cSQLWhereBlockForSelecting;
		cSQL_WhereBlockJoiningTables = bibALL.null2leer(cSQLWhereBlockJoiningTables);
		this.cCharForUniqueSearch = charForUniqueSearch;
		this.cSQLQueryForUniqueSearch = cSQLqueryForUniqueSearch;
	
//		this.iMaxResults = iMaxresults;

		if (this.cSQL_WhereBlockForSelecting.indexOf("#WERT#")==-1)
			throw new myException("DBSearchBlock:Constructor:Whereblock for Search MUST contain #WERT# !!!");
		
	}

	
	
	/**
	 * @param cSearchText
	 * @return  Vector with MyE2_Button - Components, in EXT().cMERKMAL steht der wert der gefundenen ersten spalte
	 * 			normalerweise ist das eine ID (unformatiert)
	 * @throws myException
	 */
	public Vector<XX_Button4SearchResultList[]> get_vResultButtons(String oSearchText) throws myException
	{

		String cSearchText	= ((String) oSearchText).trim();

		if (!this.get_bAllowEmptySearchField() &&  bibALL.isEmpty(cSearchText))
			throw new myExceptionForUser(new MyE2_String("Bitte geben Sie zuerst einen Suchtext ein !").CTrans());
		
		Vector<XX_Button4SearchResultList[]> 	vRueck = new Vector<XX_Button4SearchResultList[]>();
		
		VectorDataBaseQuery  vValues = this.get_Results(cSearchText);
		
		if (vValues.size()==0)
			return vRueck;     // nix gefunden
		
		Vector<String>   vNames = vValues.get_oMetaColumnsOfResultSet().get_vColumnNames();
		int iRowLength = vNames.size();
		
		if (iRowLength<=1)
			throw new myException("DBSearchBlock:get_vResultButtons:Query Result MUST contain at least 2 columns !!");
		
		for (int i=0;i<vValues.size();i++)
		{
			XX_Button4SearchResultList[] bZeile = new XX_Button4SearchResultList[iRowLength];
			for (int k=1;k<iRowLength;k++)
			{
				bZeile[k-1] = new Button4SearchResultList(  new MyE2_String(vValues.get_resultValue(vNames.get(k), i),false));     //zaehler startet mit 1
				bZeile[k-1].EXT().set_I_MERKMAL(new Integer(k));                         //aenderung 2010-11-18: dem button die spaltennummer mitgeben
				bZeile[k-1].EXT().set_C_MERKMAL(vValues.get_resultValue(vNames.get(0), i));   //in merkmal1 steht die spaltennummer der abfrage
				bZeile[k-1].EXT().set_C_MERKMAL2(vNames.get(k));                              //in merkmal2 steht der name der spalte
				
//				//debug
//				bZeile[k-1].setToolTipText("ID:"+bZeile[k-1].EXT().get_C_MERKMAL()+" -- NAME: "+bZeile[k-1].EXT().get_C_MERKMAL2());
				
			}
			//einen id-button anhaengen
			bZeile[iRowLength-1] = new Button4SearchResultList(  new MyE2_String(vValues.get_resultValue(vNames.get(0), i),false));     
			bZeile[iRowLength-1].EXT().set_I_MERKMAL(new Integer(0));                         //aenderung 2010-11-18: dem button die spaltennummer mitgeben
			bZeile[iRowLength-1].EXT().set_C_MERKMAL(vValues.get_resultValue(vNames.get(0),i));   //in merkmal1 steht die spaltennummer der abfrage
			bZeile[iRowLength-1].EXT().set_C_MERKMAL2(vNames.get(0));                              //in merkmal2 steht der name der spalte

//			//debug
//			bZeile[iRowLength-1].setToolTipText("ID:"+bZeile[iRowLength-1].EXT().get_C_MERKMAL()+" -- NAME: "+bZeile[iRowLength-1].EXT().get_C_MERKMAL2());
			
			for (int k=0;k<iRowLength;k++)
			{
				if (this.get_Manipulator() != null)
					this.get_Manipulator().Manipulate(bZeile[k]);
			}
			
			vRueck.add(bZeile);
		}
		
		return vRueck;
	}
	
	
	
	
	
	private VectorDataBaseQuery get_Results(String SearchText) throws myException
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
		
		return new VectorDataBaseQuery(cSQL);
	}



//	@Override
//	public Component get_ContainerWithFoundButtons()
//	{
//		return null;
//	}
	
	
	@Override
	public E2_BasicModuleContainer get_ContainerForShowResults()
	{
		
		return new ownBasicModuleContainer();
	}
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer
	{
		
	}

}
