package panter.gmbh.indep.dataTools;

import java.util.Collections;
import java.util.Vector;

import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class ORA_FIELD_QUERY
{
	//
	private Vector<String>		vFieldNames = new Vector<String>();
	
	public ORA_FIELD_QUERY(String cTableName) throws myException
	{
		super();
		
		if (S.isEmpty(cTableName))
		{
			throw new myException("Empty table is not allowed ...");
		}
		
		String cQuery = "SELECT COLUMN_NAME  FROM SYS.USER_TAB_COLUMNS WHERE   UPPER(TABLE_NAME) = "+bibALL.MakeSql(cTableName.toUpperCase());
		String[][] cArrTabTemp = bibDB.EinzelAbfrageInArray(cQuery, false);
		
		
		for (int i=0;i<cArrTabTemp.length;i++)
		{
			this.vFieldNames.add(cArrTabTemp[i][0].toUpperCase());
		}
		
	}

	
	public String[] get_ArrayFields(boolean bWithEmptyFrontTag, Vector<String> vFieldsExclude)
	{
		String[] cRueck = null;
		
		Vector<String> vHelp = new Vector<String>();
		
		Vector<String> vHelpExclude = bibVECTOR.MakeUpperStrings(vFieldsExclude);
		
		
		if (vHelpExclude==null || vHelpExclude.size()==0)
		{
			vHelp.addAll(this.vFieldNames);
		}
		else
		{
			for (String cFieldName: this.vFieldNames)
			{
				if (!vHelpExclude.contains(cFieldName))
				{
					vHelp.add(cFieldName);
				}
			}
		}
			
		Collections.sort(vHelp);
		
		if (bWithEmptyFrontTag)
		{
			cRueck = new String[vHelp.size()+1];
			
			int i=1;
			cRueck[0]="";
			for (String cTabName: vHelp)
			{
				cRueck[i++]=cTabName;
			}
		}
		else
		{
			cRueck = new String[vHelp.size()];
			int i=0;
			for (String cTabName: vHelp)
			{
				cRueck[i++]=cTabName;
			}
		}
		
		return cRueck;
	}

}
