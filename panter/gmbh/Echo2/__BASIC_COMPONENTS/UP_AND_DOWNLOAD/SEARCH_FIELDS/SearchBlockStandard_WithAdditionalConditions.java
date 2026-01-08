package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.SEARCH_FIELDS;

import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.Echo2.components.MaskSearchField.SearchBlockStandard;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class SearchBlockStandard_WithAdditionalConditions extends	SearchBlockStandard implements I_SearchBlock_For_UpAndDownload{

	
	/**
	 * Vektor für die zusätzlichen Bedingungen ( AND-Verknüpft )
	 */
	protected Hashtable<UP_AND_DOWNLOAD_ENUM_CONDITIONS , DBSearchCondition> m_htAdditionalSearchconditions = new Hashtable<UP_AND_DOWNLOAD_ENUM_CONDITIONS , DBSearchCondition>();
	
	/**
	 * Es werden nur die Bedingungen dynamisch zum Select dazugepackt, die auch einen Wert gesetzt haben
	 */
	protected Hashtable<UP_AND_DOWNLOAD_ENUM_CONDITIONS , String> m_htAdditionalSearchconditionValues = new Hashtable<UP_AND_DOWNLOAD_ENUM_CONDITIONS , String>();
	
	/**
	 * Es kann ein- und ausgeschaltet werden, ob man die zusätzliche Bedingung in der Suche nutzen möchte
	 */
	protected boolean  m_bUseAdditionalSearchCondition = true;
	
	
	public SearchBlockStandard_WithAdditionalConditions(	String cSQLSelectBlock, 
															String cSQLFromBlock,
															String cSQLWhereBlockJoiningTables,
															String cSQLWhereBlockForSelecting, 
															String cSQLOrderBlock,
															String cSQLqueryForUniqueSearch, 
															String charForUniqueSearch,
															int iMaxresults) throws myException {
		super(cSQLSelectBlock, cSQLFromBlock, cSQLWhereBlockJoiningTables,
				cSQLWhereBlockForSelecting, cSQLOrderBlock,
				cSQLqueryForUniqueSearch, charForUniqueSearch, iMaxresults);
		
		
		
	}

	
	
	/**
	 * fügt eine zusätzliche Bedingung zum Searchblock dazu, ohne den Wert der Bedingung festzulegen
	 * @author manfred
	 * @date   25.03.2013
	 * @param oCond
	 */
	public void add_SearchConditionForARCHIVE(DBSearchCondition oCond){
		m_htAdditionalSearchconditions.put(oCond.get_enumCondition(), oCond);
	}
	
	
	/**
	 * Setzt den Wert einer Suchbedingung 
	 * @author manfred
	 * @date   25.03.2013
	 * @param sSearchCondition
	 * @param sValue
	 */
	public void set_SearchConditionValue(UP_AND_DOWNLOAD_ENUM_CONDITIONS  enumCondition, String sValue){
		m_htAdditionalSearchconditionValues.put(enumCondition, sValue);
	}
	
	
	/**
	 * prüft, ob für die definierten Suchbedingungen auch Suchparameter gesetzt sind.
	 * Ist einer gesetzt, wird true zurückgegeben.
	 * @author manfred
	 * @date   26.03.2013
	 * @return
	 */
	public boolean get_IsAdditionalSearchConditionSet(){
		//Additional Conditions generieren
		boolean bRet = false;
		for ( Map.Entry<UP_AND_DOWNLOAD_ENUM_CONDITIONS , String> entry : m_htAdditionalSearchconditionValues.entrySet() ){
			if (m_htAdditionalSearchconditions.containsKey(entry.getKey())){
				bRet = true;
				break;
			}
		}
		return bRet;
	}

	
	/**
	 * Schaltet die zusätzlichen Suchbedingungen, die man als grundlegende Einschränkung einstellen kann, ein und aus.
	 * @author manfred
	 * @date   08.04.2013
	 * @param bUse
	 * @return
	 */
	public void set_bUseAdditionalSearchConditions(boolean bUse){
		this.m_bUseAdditionalSearchCondition = bUse;
		
		// wenn eine einschränkung gegeben ist, kann man auch mit einem leeren Suchfeld suchen.
		this.set_bAllowEmptySearchField(bUse);
	}
	
	
	/**
	 * Gibt die Beschreibung für die zusätzlichen Bedingungen zurück, die aktuell im Searchblock gesetzt sind.
	 * @author manfred
	 * @date   26.03.2013
	 * @return
	 */
	public String get_DescriptionOfAdditionalSearchConditions(){
		//Additional Conditions generieren
		String sAdditionalConditionsDescription  = "";
		for ( Map.Entry<UP_AND_DOWNLOAD_ENUM_CONDITIONS , String> entry : m_htAdditionalSearchconditionValues.entrySet() ){
			if (m_htAdditionalSearchconditions.containsKey(entry.getKey())){
				DBSearchCondition cond = m_htAdditionalSearchconditions.get(entry.getKey());
				sAdditionalConditionsDescription += cond.getDescriptionWithValue( entry.getKey(), entry.getValue());
			}
		}
		return sAdditionalConditionsDescription;
	}

	
	
	/**
	 * Überschriebene Methode
	 */
	protected String[][] get_Results(String SearchText) throws myException
	{
		
		String cSearchText = SearchText;
		
		//Additional Conditions generieren
		String sAdditionalConditions  = "";
		for ( Map.Entry<UP_AND_DOWNLOAD_ENUM_CONDITIONS , String> entry : m_htAdditionalSearchconditionValues.entrySet() ){
			if (m_htAdditionalSearchconditions.containsKey(entry.getKey())){
				DBSearchCondition cond = m_htAdditionalSearchconditions.get(entry.getKey());
				sAdditionalConditions += cond.getConditionWithValue(entry.getKey(), entry.getValue());
			}
		}
		
		
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
			if ( this.get_vZusatzWhereBedingungen() != null && this.get_vZusatzWhereBedingungen().size()>0){
				vWheres.addAll(this.get_vZusatzWhereBedingungen());
			}
			
			
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

			// die Zusätzlichen Bedingungen noch dazu, wenn sie benutzt werden sollen
			if (m_bUseAdditionalSearchCondition){
				cWhereComplete += sAdditionalConditions;
			}
			
			
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
			throw new myException("DBSearchBlockStandard:get_Results:Error in Querying: "+cSQL);
		
		return cValues;
	}


}
