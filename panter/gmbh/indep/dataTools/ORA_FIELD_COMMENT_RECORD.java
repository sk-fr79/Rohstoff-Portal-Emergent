package panter.gmbh.indep.dataTools;

import java.util.Collections;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class ORA_FIELD_COMMENT_RECORD extends MyDataRecordHashList
{
	private    	String						cTableName	= "";

	
	private     static String     			FIELD__COLUMN_NAME= 	"COLUMN_NAME";
	private     static String     			FIELD__TABLE_NAME= 		"TABLE_NAME";
	private     static String     			FIELD__COMMENTS= 		"COMMENTS";

	
	public ORA_FIELD_COMMENT_RECORD(String TableName) throws myException
	{
		super(bibVECTOR.get_Vector(ORA_FIELD_COMMENT_RECORD.get_IndexField_4_Query(FIELD__COLUMN_NAME, FIELD__TABLE_NAME),FIELD__COLUMN_NAME,FIELD__TABLE_NAME,FIELD__COMMENTS)," USER_COL_COMMENTS ",FIELD__TABLE_NAME+"="+bibALL.MakeSql(TableName),"",false,false);
				
		this.cTableName = TableName;
	}

	/*
	 * indexfeld fuer den zugriff via hashtable
	 */
	private static String get_IndexField_4_Hash(String tAble,String fIeld)
	{
		return tAble+"@"+fIeld;   //erzeugt datenbankausdruck fuer einen verketteten String aus feldname und tabellenname
	}
	
	/*
	 * indexfeld fuer das sql-statement
	 */
	private static String get_IndexField_4_Query(String FeldnameTable,String FeldNameField)
	{
		return FeldnameTable+"||'@'||"+FeldNameField;   //erzeugt datenbankausdruck fuer einen verketteten String aus feldname und tabellenname
	}

	
	public String get_CommentText(String cFieldName) throws myException
	{
		if (!this.keySet().contains(cFieldName))
		{
			throw new myException(this,": Field "+cFieldName+" is not in this record!");
		}
		return this.get(cFieldName).get_FormatedValue_LeerWennNull(FIELD__COMMENTS);
	}

	
	public String get_cTableName()
	{
		return cTableName;
	}


	public String[] get_ArrayFields(Vector<String> vFieldsExclude) throws myException
	{
		String[] cRueck = null;
		
		Vector<String> vFieldHashes = new Vector<String>();
		vFieldHashes.addAll(this.keySet());
		
		Vector<String> vFieldNames = new Vector<String>();
		for (String cHash: vFieldHashes)
		{
			vFieldNames.add(this.get_FormatedValue_LeerWennNull(FIELD__COLUMN_NAME, cHash));
		}
		
		Vector<String> vHelp = new Vector<String>();
		Vector<String> vHelpExclude = bibVECTOR.MakeUpperStrings(vFieldsExclude);
		
		
		if (vHelpExclude==null || vHelpExclude.size()==0)
		{
			vHelp.addAll(vFieldNames);
		}
		else
		{
			for (String cFieldName: vFieldNames)
			{
				if (!vHelpExclude.contains(cFieldName.toUpperCase()))
				{
					vHelp.add(cFieldName);
				}
			}
		}
			
		Collections.sort(vHelp);
		
		cRueck = new String[vHelp.size()];
		int i=0;
		for (String cTabName: vHelp)
		{
			cRueck[i++]=cTabName;
		}
		
		return cRueck;
	}

	
}
