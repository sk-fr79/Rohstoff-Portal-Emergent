package panter.gmbh.indep.dataTools;

import java.util.Vector;

import javax.servlet.http.HttpSession;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MyQueryBUILDER
{
	
	private Vector<MyQueryBUILDER.TableDef>   	vTableDefs = new Vector<TableDef>();
	private HttpSession 						oSES = null;
	private Vector<String>  					vZusatzWheres = new Vector<String>();
	
	
	public MyQueryBUILDER(	String 			tableWithoutOwner,
							String 			PrimaryKeyField, 
							Vector<String> 	vFieldNames, 
							Vector<String> 	vExcludeFields, 
							HttpSession 	SES) throws myException
	{
		super();
		this.oSES = SES;
		MyDBToolBox oDB = bibALL.get_myDBToolBox(); 
		
		try
		{
			this.__addTable(oDB,tableWithoutOwner,PrimaryKeyField,vFieldNames,"",vExcludeFields,"");
		}
		catch (Exception ex)
		{
			bibALL.destroy_myDBToolBox(oDB);
			throw new myException("MyQueryBUILDER:Error building Queryfields ..."+tableWithoutOwner+" .... "+ex.getLocalizedMessage());
		}

		
		bibALL.destroy_myDBToolBox(oDB);
		
	}


	
	public void add_Table(	String 			tableWithoutOwner,
							String 			PrimaryKeyField, 
							Vector<String> 	vFieldNames,
							String 			cAliasPrefix, 
							Vector<String> 	vExcludeFields,  
							String 			cWhereRelation) throws myException
	{
		MyDBToolBox oDB = bibALL.get_myDBToolBox(); 
		
		try
		{
			this.__addTable(oDB,tableWithoutOwner,PrimaryKeyField,vFieldNames,cAliasPrefix,vExcludeFields,cWhereRelation);
		}
		catch (Exception ex)
		{
			bibALL.destroy_myDBToolBox(oDB);
			throw new myException("MyQueryBUILDER:Error building Queryfields ..."+tableWithoutOwner+" .... "+ex.getLocalizedMessage());
		}

		
		bibALL.destroy_myDBToolBox(oDB);
		
	}

	
	
	public void add_Bedingung(String Bedingung) throws myException
	{
		if (Bedingung.indexOf(".") == -1)
			throw new myException("MyQueryBuilder:add_Bedingung: please add tablename to field !!");
		
		this.vZusatzWheres.add(Bedingung);
	}
	
	
	
	private void __addTable(	MyDBToolBox 	oDB,
								String 			table,
								String 			PrimaryKeyField, 
								Vector<String> 	vFieldNames,
								String 			cAliasPrefix, 
								Vector<String> 	vExcludeFields,  
								String 			cWhereRelation) throws Exception
	{
		if (vFieldNames == null || vFieldNames.size()==0)
		{
			Vector<String> vExcludeFields2 = bibALL.notNullVectorString(vExcludeFields);
			
			// dann alle felder beschaffen
			Vector<String> vHelp = new Vector<String>();
			
			String cQuery = "SELECT * FROM "+bibALL.get_TABLEOWNER()+"."+table+" WHERE "+PrimaryKeyField+"=-10000";
			
			MyDBResultSet oRS = oDB.OpenResultSet(cQuery);
			
			if (oRS.RS != null)
			{
				
                for (int i = 0; i < oRS.RS.getMetaData().getColumnCount(); i++)
                {
                	String cROWNAME	= oRS.RS.getMetaData().getColumnLabel(i+1);
                	if (!vExcludeFields2.contains(cROWNAME))
                		vHelp.add(table+"."+cROWNAME+" AS "+bibALL.null2leer(cAliasPrefix)+cROWNAME);
                	
                }
                this.vTableDefs.add(new TableDef(table,vHelp,cWhereRelation));
                oRS.Close();
			}
			else
				throw new myException("Error Quering fields of Table "+table);

		}
		else
		{
			Vector<String> vHelp = new Vector<String>();
			
            for (int i = 0; i < vFieldNames.size(); i++)
            {
            	String cROWNAME	= (String)vFieldNames.get(i);
            	vHelp.add(table+"."+cROWNAME+" AS "+bibALL.null2leer(cAliasPrefix)+cROWNAME);
            }
            this.vTableDefs.add(new TableDef(table,vHelp,cWhereRelation));
		}
	}
	
	
	
	public String get_SelectBlock()
	{
		String cRueck = "";
		
		Vector<String> vHelp = new Vector<String>();
		
		for (int i=0;i<this.vTableDefs.size();i++)
		{
			TableDef oTD = (TableDef)this.vTableDefs.get(i);
			vHelp.addAll(oTD.vFields);
		}
		cRueck = bibALL.ConcatenateWithoutException(vHelp,",","");
		
		return cRueck;
	}
	
	
	
	public String get_FromBlock()
	{
		String cRueck = "";
		
		Vector<String> vHelp = new Vector<String>();
		
		for (int i=0;i<this.vTableDefs.size();i++)
		{
			TableDef oTD = (TableDef)this.vTableDefs.get(i);
			vHelp.add(bibALL.get_TABLEOWNER()+"."+oTD.cTableName);
		}
		cRueck = bibALL.ConcatenateWithoutException(vHelp,",","");
		
		return cRueck;
	}


	public String get_WhereBlock(String cWhereFromOutside)
	{
		String cRueck = "";
		
		Vector<String> vHelp = new Vector<String>();
		
		for (int i=0;i<this.vTableDefs.size();i++)
		{
			TableDef oTD = (TableDef)this.vTableDefs.get(i);
			if (!bibALL.isEmpty(oTD.cWhereRelationString))
				vHelp.add(oTD.cWhereRelationString);
		}
		if (!bibALL.isEmpty(cWhereFromOutside))
			vHelp.add(cWhereFromOutside);
		
		vHelp.addAll(this.vZusatzWheres);
		
		cRueck = bibALL.ConcatenateWithoutException(vHelp," AND ","");
		
		return cRueck;
	}

	
	
	
	private class TableDef
	{
		public Vector<String> vFields = new Vector<String>();
		public String cTableName = null;
		public String cWhereRelationString = null;
		
		public TableDef(String tableName,Vector<String> fields, String WhereRelationString)
		{
			super();
			this.vFields = fields;
			this.cTableName = tableName;
			this.cWhereRelationString= WhereRelationString;
		}
		
	}




	public HttpSession get_oSES()
	{
		return oSES;
	}
	

	
	
	
	
	
	
}
