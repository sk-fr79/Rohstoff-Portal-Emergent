package panter.gmbh.indep.dataTools;

import java.util.Collections;
import java.util.Vector;

public class ORA_TABLE_QUERY
{
	private Vector<String>		vTabNames = new Vector<String>();
	
	public ORA_TABLE_QUERY()
	{
		super();
		
		String cQuery = "SELECT TABLE_NAME FROM SYS.USER_TABLES WHERE UPPER(TABLE_NAME) LIKE 'JT%' OR  UPPER(TABLE_NAME) LIKE 'JD%' ";
		String[][] cArrTabTemp = bibDB.EinzelAbfrageInArray(cQuery, false);
		
		
		for (int i=0;i<cArrTabTemp.length;i++)
		{
			this.vTabNames.add(cArrTabTemp[i][0].toUpperCase());
		}
		
	}

	
	public String[] get_ArrayTables(boolean bWithEmptyFrontTag)
	{
		String[] cRueck = null;
		
		Collections.sort(this.vTabNames);
		
		if (bWithEmptyFrontTag)
		{
			cRueck = new String[this.vTabNames.size()+1];
			
			int i=1;
			cRueck[0]="";
			for (String cTabName: this.vTabNames)
			{
				cRueck[i++]=cTabName;
			}
		}
		else
		{
			cRueck = new String[this.vTabNames.size()];
			int i=0;
			for (String cTabName: this.vTabNames)
			{
				cRueck[i++]=cTabName;
			}
		}
		
		return cRueck;
	}
	
	
}
