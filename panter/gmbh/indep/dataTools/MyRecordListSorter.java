package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class MyRecordListSorter
{
	
	private HashMap<String, MyRECORD>  	reclistToSort = null;
	private Vector<String>  			FieldListToSort = null;
	private String   					FieldNameUniqueIndex = null;
	
	private Vector<String>  			vectorSortHelp = new Vector<String>();

	
	public static final String          TRENNER_TO_HASH_KEY = "#~@§";   //trennstring zwischen sort-String und hash-key-string
	
	public MyRecordListSorter(HashMap<String, MyRECORD> reclistToSort,Vector<String> fieldListToSort, String fieldNameUniqueIndex) throws myException
	{
		super();
		this.reclistToSort = reclistToSort;
		this.FieldListToSort = fieldListToSort;
		this.FieldNameUniqueIndex = fieldNameUniqueIndex;
		
		for (Entry<String, MyRECORD> oEntry: this.reclistToSort.entrySet())
		{
			String cSortString = "";
			for (int k=0;k<this.FieldListToSort.size();k++)
			{
				MyRECORD  oRec = oEntry.getValue();
				String    cField = this.FieldListToSort.get(k);
				
				if (!oRec.containsKey(cField))
				{
					throw new myException(this,"Record contains no FIELDS named:"+cField);
				}
				
				MyResultValue resValTeil1= oRec.get(cField);

				
				
				
			}
			
			
			
			
			
		}
		
		
		
	}
	

	private String get_SortString(MyResultValue resVal) throws myException
	{
		String cRueck = "";
		
		if (resVal.get_MetaFieldDef().get_bIsDateType())
		{
			String cHelp = resVal.get_cDateValueFormated();
			if (cHelp==null)
			{
				cRueck = "0000-00-00";
			}
			else
			{
				cRueck = new myDateHelper(cHelp).get_cDateFormat_ISO_FORMAT();
			}
		}
		else if (resVal.get_MetaFieldDef().get_bIsNumberTypeWithDecimals() ||resVal.get_MetaFieldDef().get_bIsNumberTypeWithOutDecimals())
		{
			//dann wird ein 40-stelliger String gebildet mit fuehrenden nullen und  5 dezimalen (sollte alle faelle abdecken
			BigDecimal  bdHelp = resVal.getBigDecimalValue();
			String cHelp = MyNumberFormater.formatDez(bdHelp, 5,false,new Character('.'),null,true);
			if (cHelp.length()<40)
			{
				cRueck = "0000000000000000000000000000000000000000000".substring(0,40-cHelp.length())+cHelp;
			}
			else
			{
				throw new myException(this, "Number ist too big to sort !");
			}
		}
		else if (resVal.get_MetaFieldDef().get_bIsTextType())
		{
			int iLen = resVal.get_MetaFieldDef().get_iNumberCharactersInMask();
			
			String cDummy = S.get_StringWithLen(' ', iLen);
			
			if (resVal.get_FieldValueUnformated()!=null)
			{				
				cRueck = cDummy.substring(0,iLen-resVal.get_FieldValueUnformated().length())+resVal.get_FieldValueUnformated();
			}
			else
			{
				cRueck = cDummy;
			}
		}
		
		return cRueck;
	}
	
}
