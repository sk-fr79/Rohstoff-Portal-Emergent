package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.ImageReference;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

public class MyE2_DB_Icon extends MyE2_Label implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{

	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private ImageReference			oImageEmpty = null;
	private Vector<E2_ResourceIcon>	vImages		= null;
	private Vector<String>			vDBValues	= null;

	/**
	 * @param osqlField
	 * @param oimageEmpty (icon for status empty, new ...)
	 * @param vimages  (vector of icons corresponding to values in vdbvalues)
	 * @param vdbValues (Strings with formated DBValue)
	 * @throws myException
	 */
	public MyE2_DB_Icon(SQLField 				osqlField, 
						ImageReference 			oimageEmpty, 
						Vector<E2_ResourceIcon>	vimages,
						Vector<String>			vdbValues) throws myException
	{
		super();
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
		
		this.oImageEmpty = oimageEmpty;
		this.vImages = vimages;
		this.vDBValues = vdbValues;
		this.oEXTDB.set_bGivesBackValueToDB(false);
		this.oEXTDB.set_oSQLField(osqlField);
	}

	
	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		this.setIcon(this.oImageEmpty);
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
		this.setIcon(this.oImageEmpty);
		for (int i=0;i<this.vDBValues.size();i++)
		{
			if (cText.equals(this.vDBValues.get(i)))
				this.setIcon((ImageReference)this.vImages.get(i));
		}
		
		this.EXT_DB().set_cLASTActualMaskValue(cText);

	}


	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_Icon:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");

		
		this.setIcon(this.oImageEmpty);
		for (int i=0;i<this.vDBValues.size();i++)
		{
			if (cText.equals(this.vDBValues.get(i)))
				this.setIcon((ImageReference)this.vImages.get(i));
		}
		
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
		MyE2_DB_Icon oLabCopy = null;
		try
		{
			oLabCopy = new MyE2_DB_Icon(this.oEXTDB.get_oSQLField(),this.oImageEmpty,this.vImages,this.vDBValues);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_Icon:get_Copy:copy-error!");
		}
		
		oLabCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oLabCopy));
		oLabCopy.setFont(this.getFont());
		
		if (this.getIcon() != null)
			oLabCopy.setIcon(this.getIcon());
		
		if (this.get_oTextObject() != null)
			oLabCopy.set_Text(this.get_oTextObject());
		
		oLabCopy.set_cErsatzFuerLeeranzeige(this.get_cErsatzFuerLeeranzeige());
		
		oLabCopy.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oLabCopy));
		
		oLabCopy.setLayoutData(this.getLayoutData());
		
		oLabCopy.setToolTipText(this.getToolTipText());
		
		
		
		return oLabCopy;

		
	}




	public Vector<String> get_vDBValues()
	{
		return vDBValues;
	}



	public void set_vDBValues(Vector<String> values)
	{
		vDBValues = values;
	}



	public Vector<E2_ResourceIcon> get_vImages()
	{
		return vImages;
	}



	public void set_vImages(Vector<E2_ResourceIcon> images)
	{
		vImages = images;
	}


}
