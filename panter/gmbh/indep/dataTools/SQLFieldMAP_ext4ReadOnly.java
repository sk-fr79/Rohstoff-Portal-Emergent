package panter.gmbh.indep.dataTools;

import java.sql.SQLException;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/*
 * 2012-11-12: weitere sqlfieldmap fuer abfragen, deren innerer kern eine query, keine einfache tabelle ist
 *             Dabei werden alle methoden, die nicht auf die situation passen ueberschrieben (bez. schmeissen eine exception)
 */
public class SQLFieldMAP_ext4ReadOnly extends SQLFieldMAP
{

	private String InnerQueryString = null;
	private String InnerQueryAlias = null;
	
	
	public SQLFieldMAP_ext4ReadOnly(String innerQueryString, String innerQueryAlias)
	{
		super(innerQueryAlias);
		InnerQueryString = innerQueryString;
		InnerQueryAlias = innerQueryAlias;
	}
	
	
	@Override
	public String get_cSQL_FROM_BLOCK() throws myException
	{
		//wenn es eine Abfrage-basierte basistabelle ist, dann wird hier dir vTableNames nicht gebraucht
		String cRueck = "("+this.InnerQueryString+") "+this.InnerQueryAlias;
		
		//jetzt noch die join-tables dranhaengen
		for (int i=0;i<this.get_vJoinTables().size();i++)
		{
			cRueck += " "+this.get_vJoinTables().get(i).get_JoinBlockForSQLQuery()+" ";
		}
		
		return cRueck; 
	}

	
	/**
	 * die komplette inner-view-tabelle anhaengen
	 * @param ListOfFields
	 * @param bIncludeList
	 * @throws myException
	 */
	public void addCompleteBase_Table_FIELDLIST(String ListOfFields,boolean bIncludeList) throws myException
	{
		String cListOfFields = ListOfFields;
		
		MyDBToolBox  	oDB = null;
		MyDBResultSet 	oRS = null;
		
		cListOfFields = cListOfFields.trim();
		if (!cListOfFields.endsWith(":")) cListOfFields+=":";
		if (!cListOfFields.startsWith(":")) cListOfFields= ":"+cListOfFields;
		
		try 
		{
			/*
			 * dann baut er eine standard-tabelle auf
			 */
			oDB = bibALL.get_myDBToolBox();
			oRS = oDB.OpenResultSet(this.InnerQueryString);
			
			MyMetaFieldDEF_HashMap oFields = new MyMetaFieldDEF_HashMap(oRS.RS);
			
			for (int i=0;i<oFields.get_iNumberOfColumns();i++)
			{
				boolean bFieldInAuswahl = cListOfFields.toUpperCase().indexOf(":"+oFields.get_FieldName(i).toUpperCase()+":")>=0;

				if ((!bFieldInAuswahl && !bIncludeList) || (bFieldInAuswahl && bIncludeList))
				{
					SQLField oField = new SQLField(	this.InnerQueryAlias.toUpperCase(),
													oFields.get_FieldName(i),
													oFields.get_FieldName(i),
													new MyString(oFields.get_FieldName(i),false),
													oFields.get_FieldNullable(i),
													bibE2.get_CurrSession());
					oField.set_bWriteable(false);
					oField.set_bFieldCanBeWrittenInMask(false);
					this.add_SQLField(oField, false);
				}
			}
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
	       	oRS.Close();
			bibALL.destroy_myDBToolBox(oDB);
			throw new myException(this,":SQLException:"+e.getLocalizedMessage());
		}
       	oRS.Close();
		bibALL.destroy_myDBToolBox(oDB);
	}
	
	
	@Override
	public void addCompleteTable_FIELDLIST(String cTable,String ListOfFields,boolean bIncludeList, boolean Writeable, String cFieldAliasPrefix) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}
	
	@Override
	public Vector<String> get_SQL_INSERTSTACK(SQLMaskInputMAP oInputMAP) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}
	
	@Override
	public Vector<String> get_SQL_INSERTSTACK(SQLMaskInputMAP oInputMAP, String Formated_ValueOfConnectedFieldToOutside) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}

	
	@Override
	public MyE2_MessageVector get_vCheckNewValues(SQLResultMAP oActualResult, SQLMaskInputMAP oMaskInputValues) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}
	
	@Override
	public Vector<String>  get_SQL_UPDATESTACK(SQLResultMAP oActualResult, SQLMaskInputMAP oMaskInputValues) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}

	
	@Override
	public Vector<String> get_SQL_DELETESTACK_From_FORMATED_KEY(String cFormated_Primary_KEY_Value) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}

	
	@Override
	public Vector<String> get_SQL_DELETESTACK_From_UNFORMATED_KEY(String cUNFormated_Primary_KEY_Value) throws myException
	{
		throw new myException("Not allowed in SQLFieldMAP_ext4ReadOnly");
	}


	public String get_cInnerQueryString()
	{
		return InnerQueryString;
	}


	public String get_cInnerQueryAlias()
	{
		return InnerQueryAlias;
	}


	public void set_cInnerQueryString(String innerQueryString)
	{
		InnerQueryString = innerQueryString;
	}


	public void set_cInnerQueryAlias(String innerQueryAlias)
	{
		InnerQueryAlias = innerQueryAlias;
	}
	
	
	
}
