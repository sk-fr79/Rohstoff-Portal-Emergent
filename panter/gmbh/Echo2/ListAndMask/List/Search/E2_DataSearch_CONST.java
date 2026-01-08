package panter.gmbh.Echo2.ListAndMask.List.Search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class E2_DataSearch_CONST
{
	
	
	
	/**
	 * Baut ein SearchQuery auf einer HashMap<String, MyRECORD.DATATYPES> auf
	 * @param hmFields
	 * @param cCompleteTableName
	 * @param oSQLFieldMAP              //kann null sein, wenn nicht, werden die statischen bedingungen miteingebaut 
	 * @return
	 * @throws myException
	 */
	public static String get_SearchTextBlockFulltext(HashMap<String, MyRECORD.DATATYPES> hmFields, String cCompleteTableName, SQLFieldMAP oSQLFieldMAP) throws myException
	{
		
		HashMap<String, MyRECORD.DATATYPES>  hmReduziert = bibDB.get_HM_RelevantFields(hmFields);
		
		
		String cQueryString = "SELECT "+cCompleteTableName+".ID_"+cCompleteTableName.substring(3)+" FROM "+bibE2.cTO()+"."+cCompleteTableName+" WHERE ";
		
		
		Vector<String>  vWhereBlocks = new Vector<String>();
		
		Iterator<String>  oIter = hmReduziert.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cFeldname = oIter.next();
			MyRECORD.DATATYPES  oType = hmReduziert.get(cFeldname);

			switch(oType)
			{
				case TEXT:
				{
					vWhereBlocks.add("UPPER(NVL("+cCompleteTableName+"."+cFeldname+",'-')) LIKE  UPPER('%#WERT#%')");
					break;
				}
				case NUMBER:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9g999g999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");   //schreibweise mit punkt
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9999999999990','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");       //und ohne
					break;
				}
				case DECIMALNUMBER:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9g999g999g999g990d000','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");   //schreibweise mit punkt
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9999999999990d000','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");       //und ohne
					break;
				}
				case DATE:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DD.MM.YYYY') LIKE '%#WERT#%'");
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DDMMYYYY')='#WERT#'");
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DDMMYY')='#WERT#'");
					break;
				}
			}
			
		}
		
		
		//jetzt aus den vWhereBlocks einen einzigen where-block zusammenbauen mit OR dazwischen
		
		String cWhere = "("+bibALL.Concatenate(vWhereBlocks, " OR ", "1=1")+")";
		
		
		// jetzt nachsehen, ob eine SQLFieldMAP mit RestrictionFields uebergeben wurde
		if (oSQLFieldMAP != null)
		{
			String cWhereZusatz = oSQLFieldMAP.get_cSQL_WHERE_BLOCK_FROM_RestrictFields_FROM_MAIN_TABLE();
			
			if (! bibALL.isEmpty(cWhereZusatz))
			{
				cWhere += " AND "+cWhereZusatz;
			}
		}
	
		
		
		return cQueryString+" "+cWhere;
		
		
	}
	
	
	/**
	 * Baut ein SearchQuery auf einer HashMap<String, MyRECORD.DATATYPES> auf
	 * @param hmFields
	 * @param cCompleteTableName
	 * @param oSQLFieldMAP              //kann null sein, wenn nicht, werden die statischen bedingungen miteingebaut 
	 * @return
	 * @throws myException
	 */
	public static String get_SearchTextBlockFulltextOnlyWhere(HashMap<String, MyRECORD.DATATYPES> hmFields, String cCompleteTableName, SQLFieldMAP oSQLFieldMAP) throws myException
	{
		
		HashMap<String, MyRECORD.DATATYPES>  hmReduziert = bibDB.get_HM_RelevantFields(hmFields);
		
		
		String cQueryString = "";
		
		
		Vector<String>  vWhereBlocks = new Vector<String>();
		
		Iterator<String>  oIter = hmReduziert.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cFeldname = oIter.next();
			MyRECORD.DATATYPES  oType = hmReduziert.get(cFeldname);

			switch(oType)
			{
				case TEXT:
				{
					vWhereBlocks.add("UPPER(NVL("+cCompleteTableName+"."+cFeldname+",'-')) LIKE  UPPER('%#WERT#%')");
					break;
				}
				case NUMBER:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9g999g999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");   //schreibweise mit punkt
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9999999999990','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");       //und ohne
					break;
				}
				case DECIMALNUMBER:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9g999g999g999g990d000','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");   //schreibweise mit punkt
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",0),'fm9999999999990d000','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");       //und ohne
					break;
				}
				case DATE:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DD.MM.YYYY') LIKE '%#WERT#%'");
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DDMMYYYY')='#WERT#'");
					vWhereBlocks.add("TO_CHAR(NVL("+cCompleteTableName+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DDMMYY')='#WERT#'");
					break;
				}
			}
			
		}
		
		
		//jetzt aus den vWhereBlocks einen einzigen where-block zusammenbauen mit OR dazwischen
		
		String cWhere = "("+bibALL.Concatenate(vWhereBlocks, " OR ", "1=1")+")";
		
		
		// jetzt nachsehen, ob eine SQLFieldMAP mit RestrictionFields uebergeben wurde
		if (oSQLFieldMAP != null)
		{
			String cWhereZusatz = oSQLFieldMAP.get_cSQL_WHERE_BLOCK_FROM_RestrictFields_FROM_MAIN_TABLE();
			
			if (! bibALL.isEmpty(cWhereZusatz))
			{
				cWhere += " AND "+cWhereZusatz;
			}
		}
	
		
		
		return cQueryString+" "+cWhere;
		
		
	}
	
	
	
	
	
	/**
	 * Baut ein SearchQuery auf einer HashMap<String, MyRECORD.DATATYPES> auf
	 * @param hmFields
	 * @param TableAlias
	 * @return
	 * @throws myException
	 */
	public static Vector<String> get_vWhereStatementes4FullText(HashMap<String, MyRECORD.DATATYPES> hmFields, String TableAlias) throws myException
	{
		
		HashMap<String, MyRECORD.DATATYPES>  hmReduziert = bibDB.get_HM_RelevantFields(hmFields);
		
		Vector<String>  vWhereBlocks = new Vector<String>();
		
		Iterator<String>  oIter = hmReduziert.keySet().iterator();
		
		while (oIter.hasNext())
		{
			String cFeldname = oIter.next();
			MyRECORD.DATATYPES  oType = hmReduziert.get(cFeldname);

			switch(oType)
			{
				case TEXT:
				{
					vWhereBlocks.add("UPPER(NVL("+TableAlias+"."+cFeldname+",'-')) LIKE  UPPER('%#WERT#%')");
					break;
				}
				case NUMBER:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",0),'fm9g999g999g999g990','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");   //schreibweise mit punkt
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",0),'fm9999999999990','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");       //und ohne
					break;
				}
				case DECIMALNUMBER:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",0),'fm9g999g999g999g990d000','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");   //schreibweise mit punkt
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",0),'fm9999999999990d000','NLS_NUMERIC_CHARACTERS = '',.''')='%#WERT#%'");       //und ohne
					break;
				}
				case DATE:
				{
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DD.MM.YYYY') LIKE '%#WERT#%'");
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DDMMYYYY')='#WERT#'");
					vWhereBlocks.add("TO_CHAR(NVL("+TableAlias+"."+cFeldname+",TO_DATE('01.01.1492','DD.MM.YYYY')),'DDMMYY')='#WERT#'");
					break;
				}
			}
			
		}
		
		return vWhereBlocks;
		
		
	}

	
}
