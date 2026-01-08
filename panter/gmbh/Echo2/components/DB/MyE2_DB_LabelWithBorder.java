package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_LabelWithBorder;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_LabelWithBorder extends MyE2_LabelWithBorder implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);

	public MyE2_DB_LabelWithBorder(SQLField osqlField, MutableStyle MutableStyleBorder,LayoutData oRowLayout) throws myException
	{
		super(MutableStyleBorder,oRowLayout);
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	
	
	public MyE2_DB_LabelWithBorder(SQLField osqlField, MutableStyle styleLabel,MutableStyle MutableStyleBorder, LayoutData rowLayout) throws myException
	{
		super("", styleLabel, MutableStyleBorder, rowLayout);
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
		
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
		
	}



	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.set_Text("");
		this.EXT_DB().set_cLASTActualDBValueFormated("");
		this.EXT_DB().set_cLASTActualMaskValue("");

	}

	public String get_cActualMaskValue() throws myException
	{
		return this.get_ownLabel().getText();
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return null;
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		this.set_Text(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Label:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		this.set_cActualMaskValue(cText);
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);

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

	
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_DB_LabelWithBorder oLabCopy = null;
		try
		{ 
			oLabCopy = new MyE2_DB_LabelWithBorder(	this.oEXTDB.get_oSQLField(),
													this.get_MutableStyleBorder(),
													this.get_ownLabel().getLayoutData());
			
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Label:get_Copy:copy-error!");
		}
		
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		oLabCopy.setFont(this.getFont());
		
		if (this.get_ownLabel().getIcon() != null)
			oLabCopy.get_ownLabel().setIcon(this.get_ownLabel().getIcon());
		
		if (this.get_ownLabel().getLayoutData() != null)
			oLabCopy.get_ownLabel().setLayoutData(this.get_ownLabel().getLayoutData());

		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.get_cErsatzFuerLeeranzeige());
		
		oLabCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oLabCopy));
		
		return oLabCopy;
	}


}
