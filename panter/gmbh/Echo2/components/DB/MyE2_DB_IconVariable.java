package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyE2_DB_IconVariable extends MyE2_Label implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	/**
	 * @param osqlField
	 * @param oimageEmpty (icon for status empty, new ...)
	 * @param vimages  (vector of icons corresponding to values in vdbvalues)
	 * @param vdbValues (Strings with formated DBValue)
	 * @throws myException
	 */
	public MyE2_DB_IconVariable(SQLField 				osqlField) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	
	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.setIcon(E2_ResourceIcon.get_RI("empty10.png"));
	}

	public String get_cActualMaskValue() throws myException
	{
		return null;
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
	}


	
	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return false;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oInputMAP) throws myException
	{
		return null;
	}

	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
	{
		return null;
	}

	public MyE2EXT__DB_Component EXT_DB()
	{
		return this.oEXTDB;
	}

	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB)
	{
		this.oEXTDB = oEXT_DB;
	}

	

	
}
