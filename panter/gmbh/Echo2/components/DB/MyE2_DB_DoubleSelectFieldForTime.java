package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Factorys.StyleFactory_SelectField;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;




/*
 * datenbank-komponente fuer die darstellung von stunde/minute-Dropdowns
 */
public class MyE2_DB_DoubleSelectFieldForTime extends MyE2_Row  implements MyE2IF__Component,MyE2IF__DB_Component, E2_IF_Copy
{
	private MyE2EXT__DB_Component 	oEXTDB=new MyE2EXT__DB_Component(this);
	private MyE2_SelectField  		oSelStunden = new MyE2_SelectField();
	private MyE2_SelectField  		oSelMinuten = new MyE2_SelectField();

	private String[] cArrayStunden = {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
	private String[] cArrayMinuten = {"00","05","10","15","20","25","30","35","40","45","50","55"};

	
	public MyE2_DB_DoubleSelectFieldForTime(SQLField osqlField) throws myException
	{
		super();
		this.EXT().set_STYLE_FACTORY(new StyleFactory_SelectField());
		
		this.add(this.oSelStunden,E2_INSETS.I_1_1_1_1);
		this.add(new MyE2_Label(":"),E2_INSETS.I_1_1_1_1);
		this.add(this.oSelMinuten,E2_INSETS.I_1_1_1_1);
		
		if (osqlField == null)
			throw new myException("MyE2_DB_Label:Constructor:null-SQLField not allowed !");
	
		
		this.oEXTDB.set_bGivesBackValueToDB(true);
		this.oEXTDB.set_oSQLField(osqlField);
		
		this.oSelStunden.set_ListenInhalt(cArrayStunden,false);
		this.oSelMinuten.set_ListenInhalt(cArrayMinuten,false);

	
	}
	
	
	
	
	public void prepare_ContentForNew(boolean bSetDefault) throws myException
	{
		if (!bSetDefault)
		{
			this.oSelStunden.setSelectedIndex(0);   // 00
			this.oSelMinuten.setSelectedIndex(0);   // 00
			this.EXT_DB().set_cLASTActualDBValueFormated("00:00");
			this.EXT_DB().set_cLASTActualMaskValue("00:00");

			return;
		}

		Vector<String> vStunden  = bibALL.get_VectorAusArray(this.cArrayStunden);
		Vector<String> vMinuten  = bibALL.get_VectorAusArray(this.cArrayMinuten);
		
		
		String cText = "";
		if (this.EXT_DB().get_oSQLField().get_cDefaultValueFormated() != null)
			cText = this.EXT_DB().get_oSQLField().get_cDefaultValueFormated();

		boolean bOK = false;
		if (cText.trim().length()==5)
		{
			if (vStunden.contains(cText.substring(0,2)) && vMinuten.contains(cText.substring(3)))
			{
				this.oSelStunden.set_ActiveInhalt(cText.substring(0,2));
				this.oSelMinuten.set_ActiveInhalt(cText.substring(3));
				bOK = true;
			}
		}
		
		
		if (!bOK)
			throw new myException("MyE2_DB_DoubleSelectFieldForTime:prepare_ContentForNew:Error setting default-Value: "+cText);
		
	
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);
		this.EXT_DB().set_cLASTActualMaskValue(cText);

		
		
	}

	public String get_cActualMaskValue() throws myException
	{
		return this.oSelStunden.get_ActualView()+":"+this.oSelMinuten.get_ActualView();
	}

	public String get_cActualDBValueFormated() throws myException
	{
		return this.oSelStunden.get_ActualView()+":"+this.oSelMinuten.get_ActualView();
	}

	public void set_cActualMaskValue(String cText) throws myException
	{
		Vector<String> vStunden  = bibALL.get_VectorAusArray(this.cArrayStunden);
		Vector<String> vMinuten  = bibALL.get_VectorAusArray(this.cArrayMinuten);

		boolean bOK = false;
		if (cText.trim().length()==5)
		{
			if (vStunden.contains(cText.substring(0,2)) && vMinuten.contains(cText.substring(3)))
			{
				this.oSelStunden.set_ActiveInhalt(cText.substring(0,2));
				this.oSelMinuten.set_ActiveInhalt(cText.substring(3));
				bOK = true;
			}
		}
		
		if (!bOK)
			throw new myException("MyE2_DB_DoubleSelectFieldForTime:set_cActualMaskValue:Error setting mask-Value: "+cText);
		
		this.EXT_DB().set_cLASTActualMaskValue(cText);

		
	}


	
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException
	{
		if (!(	cMASK_STATUS.equals(E2_ComponentMAP.STATUS_EDIT) || 
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_COPY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_NEW_EMPTY) ||
				cMASK_STATUS.equals(E2_ComponentMAP.STATUS_VIEW)))
				throw new myException("MyE2_DB_DoubleSelectFieldForTime:set_cActual_Formated_DBContent_To_Mask:Status not allowed !");


		Vector<String> vStunden  = bibALL.get_VectorAusArray(this.cArrayStunden);
		Vector<String> vMinuten  = bibALL.get_VectorAusArray(this.cArrayMinuten);

		boolean bOK = false;
		if (cText.trim().length()==5)
		{
			if (vStunden.contains(cText.substring(0,2)) && vMinuten.contains(cText.substring(3)))
			{
				this.oSelStunden.set_ActiveInhalt(cText.substring(0,2));
				this.oSelMinuten.set_ActiveInhalt(cText.substring(3));
				bOK = true;
			}
		}
		
		if (!bOK)
			throw new myException("MyE2_DB_DoubleSelectFieldForTime:set_cActualMaskValue:Error setting mask-Value: "+cText);
		
		this.EXT_DB().set_cLASTActualDBValueFormated(cText);

		
	}

	public void set_bIsComplexObject(boolean bisComplex)
	{
	}

	public boolean get_bIsComplexObject()
	{
		return false;
	}

	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException
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



	
	
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();

		this.oSelStunden.setEnabled(bVoraussetzung);
		this.oSelMinuten.setEnabled(bVoraussetzung);
		
		this.oSelStunden.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
		this.oSelMinuten.setStyle(this.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),false));
	}

	
	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_DB_DoubleSelectFieldForTime oSelField = null;
		
		try
		{
			oSelField = new MyE2_DB_DoubleSelectFieldForTime(this.oEXTDB.get_oSQLField());
			oSelField.set_EXT_DB((MyE2EXT__DB_Component)this.oEXTDB.get_Copy(oSelField));
		}
		catch (myException ex)
		{
			throw new  myExceptionCopy("MyE2_DB_SelectField:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		return oSelField;
	}

	
}
