package panter.gmbh.indep.dataTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


/**
 * HashMap nimmt elemente vom Typ MyMetaColumnDefinition auf (fuer ein ganzes resultset, als key fungiert die nummer der Spalte, beginnt ab 0)
 */
public class MyMetaFieldDEF_HashMap extends HashMap<String,MyMetaFieldDEF> 
{

	private int iNumberOfColumns = -1;
	
	private Vector<String>  vColumnNames = new Vector<String>();
	
	public MyMetaFieldDEF_HashMap(ResultSet oRS)  throws SQLException
	{
		super();
		this.__create(oRS);
	}



	/**
	 * 
	 * @param cTableName
	 * @throws SQLException
	 * @throws myException
	 */
	public MyMetaFieldDEF_HashMap(String cTableName)  throws SQLException, myException
	{
		super();
    	MyDBToolBox oDB = bibALL.get_myDBToolBox();
    	MyDBResultSet oRS = oDB.OpenResultSet(DB_META.get_EmptyResultQuery(bibALL.get_DBKENNUNG(), bibALL.get_TABLEOWNER(), cTableName));
       	if (oRS.RS != null)
       	{
       		this.__create(oRS.RS);
       	}
       	oRS.Close();
		bibALL.destroy_myDBToolBox(oDB);
	}

	
	
	private void __create(ResultSet oRS)  throws SQLException
	{
		this.iNumberOfColumns = oRS.getMetaData().getColumnCount();
   		for (int i=0;i<this.iNumberOfColumns;i++)
		{
   			MyMetaFieldDEF oHelp = new MyMetaFieldDEF(oRS,i, null);
   			this.put((""+i),oHelp);
   				
   			this.vColumnNames.add(oHelp.get_FieldName());
		}
		
	}
	
	
	
	/*
	 * vergleicht die namen der spalten und reihenfolge der spalten
	 */
	public boolean equalsInNames(MyMetaFieldDEF_HashMap oCompareSet) throws myException
	{
		boolean bRueck = true;
		if (this.iNumberOfColumns != oCompareSet.get_iNumberOfColumns())
			return false;
		
		for (int i=0;i<this.iNumberOfColumns;i++)
		{
			if (!this.get_FieldName(i).toUpperCase().equals(oCompareSet.get_FieldName(i).toUpperCase()))
			{
				bRueck = false;
				break;
			}
		}
		
		return bRueck;
	}
	
	
	
	public String get_FieldName(int iCol) throws myException
	{
		MyMetaFieldDEF oHelp = (MyMetaFieldDEF)this.get(""+iCol);
		if (oHelp == null)
			throw new myException(this.getClass().getName()+"get_FieldName: Error: No Member found !");
		
		return (String)oHelp.get(MyMetaFieldDEF.KEY_FIELDNAME);
	}
	
	public String get_FieldType(int iCol) throws myException
	{
		MyMetaFieldDEF oHelp = (MyMetaFieldDEF)this.get(""+iCol);
		if (oHelp == null)
			throw new myException(this.getClass().getName()+"get_FieldType: Error: No Member found !");

		return (String)oHelp.get(MyMetaFieldDEF.KEY_FIELDTYPE);
	}

	public int get_FieldNumberLENGTH(int iCol) throws myException
	{
		MyMetaFieldDEF oHelp = this.get(""+iCol);
		if (oHelp == null)
			throw new myException(this.getClass().getName()+"get_FieldLEGTH: Error: No Member found !");
	
		return oHelp.get_FieldNumberLENGTH();
	}

	public int get_FieldDecimals(int iCol) throws myException
	{
		MyMetaFieldDEF oHelp = this.get(""+iCol);
		if (oHelp == null)
			throw new myException(this.getClass().getName()+"get_FieldDecimals: Error: No Member found !");
	
		return oHelp.get_FieldDecimals();
	}

	public boolean get_FieldNullable(int iCol) throws myException
	{
		MyMetaFieldDEF oHelp = (MyMetaFieldDEF)this.get(""+iCol);
		if (oHelp == null)
			throw new myException(this.getClass().getName()+"get_FieldNullable: Error: No Member found !");
	
		//return ((Integer)oHelp.get(MyMetaFieldDEF.KEY_FIELD_IS_NULLABLE_BASIC)).intValue();
		return oHelp.get_bFieldNullableBasic();
	}
	

	public int get_iNumberOfColumn(String cColumnName) throws myException
	{
		Iterator<Map.Entry<String, MyMetaFieldDEF>> it = this.entrySet().iterator(); 
		int iReturn = -1;
		int iFound  =  0;
		
		while (it.hasNext()) 
		{
			
		    Map.Entry<String, MyMetaFieldDEF> entry = (Map.Entry<String, MyMetaFieldDEF>)it.next();
		    MyMetaFieldDEF oHelp= (MyMetaFieldDEF)entry.getValue();

		    if (oHelp.get_FieldName().toUpperCase().trim().equals(cColumnName.toUpperCase().trim()))
		    {
		    	iReturn = new Integer((String)entry.getKey()).intValue();
		    	iFound++;
		    }
		}
		
		if (iReturn==-1)
			throw new myException(this," : "+cColumnName+" : Field was not found !!");
			
		if (iFound != 1)
			throw new myException(this," : "+cColumnName+" : Field is more than one time present!!");
		
		
		return iReturn;
	}


	public int get_iNumberOfColumns() 
	{
		return iNumberOfColumns;
	}
	
	public MyMetaFieldDEF get_oMetaColumnDefinition(int iColumn) throws myException
	{
		if (iColumn>=this.iNumberOfColumns)
			throw new myException(this.getClass().getName()+" : "+iColumn+" : Number extends HashMap-Size !!");
		

		return (MyMetaFieldDEF)this.get(""+iColumn);
		
		
	}

	public Vector<String> get_vColumnNames() 
	{
		return this.vColumnNames;
	}

	
	public HashMap<String,String> get_hmFields()
	{
		HashMap<String,String> hmRueck = new HashMap<String,String>();
		for (int i=0;i<this.vColumnNames.size();i++)
			hmRueck.put(this.vColumnNames.get(i),this.vColumnNames.get(i));
		
		return hmRueck;
	}

	
}
