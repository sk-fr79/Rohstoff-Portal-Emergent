package panter.gmbh.indep.dataTools;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

/**
 * speichert die eingegebenen maskenwerte unter dem schluessel der jeweils zu
 * grunde liegenden SQLField - HashValues/FieldAlias
 */
public class SQLMaskInputMAP extends HashMap<String,String>
{
	
	// ein vector, der Fehlerhafte eingaben mitschreibt
	private Vector<String> vFieldsWithFalseInput = new Vector<String>(); 
	
	private E2_ComponentMAP    oMap = null;
	
	

	
	public SQLMaskInputMAP() throws myException
	{
		super();
	}
	

	
	/**
	 * 
	 * @param Map
	 * @throws myException
	 * Generiert eine SQLMaskInputMAP aus der componentmap und markiert fehlerhafte felder
	 */
	public SQLMaskInputMAP(E2_ComponentMAP    Map) throws myException
	{
		super();
		this.oMap = Map;
		
		this.Read_ActualInputValues(true);
	}
	


	/**
	 * 
	 * @param Map
	 * @param bMarkFalseFields
	 * @throws myException
	 * Generiert eine SQLMaskInputMAP aus der componentmap und markiert fehlerhafte felder wenn bMarkFalseFields=true
	 */
	public SQLMaskInputMAP(E2_ComponentMAP    Map, boolean bMarkFalseFields) throws myException
	{
		super();
		this.oMap = Map;
		
		this.Read_ActualInputValues(true);
	}
	

	
	public void Read_ActualInputValues(boolean bMarkFalseFields) throws myException
	{
		if (this.oMap==null)
			throw new myException(this,"SQLInputMAP was created without a E2_ComponentMAP !!");
		
		
		this.clear();

		Vector<MyE2IF__Component> vRealComponents = this.oMap.get_REAL_ComponentVector();

		
		for (int i=0;i<vRealComponents.size();i++)
		{
			Component oComp = (Component)vRealComponents.get(i);
			if (oComp instanceof MyE2IF__DB_Component)
			{
				MyE2IF__DB_Component oComp2 = (MyE2IF__DB_Component) oComp;
				String cHashKEY = oComp2.EXT_DB().get_oSQLField().get_cFieldLabel();
				if (oComp2.EXT_DB().get_bGivesBackValueToDB() && !oComp2.get_bIsComplexObject())
				{
					String cValue = oComp2.get_cActualDBValueFormated();
					MyE2_MessageVector oMV_Help = oComp2.EXT_DB().get_oSQLField().get_vCheckNewValue(cValue);
					
					//2011-05-06: weitere validierung, die bei der maskenpruefung auch markiert
					if (oComp2 instanceof MyE2IF__Component)
					{
						oMV_Help.add_MESSAGE(   ((MyE2IF__Component)oComp2).EXT().get_Field_Validation_Check_Input_and_MarkFalseValues());
					}
					
					
					if (oMV_Help.get_bHasAlarms())
					{
						this.add_FieldWithFalseInput(cHashKEY);
					}
					
					if (bMarkFalseFields)
					{
						((MyE2IF__Component)oComp2).show_InputStatus(oMV_Help.get_bIsOK());
					}
					
					this.add_MaskInputValue(cHashKEY,cValue);
				}
			}
		}

		
	}
	
	
	
	public void add_MaskInputValue(String cSQLFieldAlias,String cNewValueFromInput)
	{
		this.put(cSQLFieldAlias,cNewValueFromInput);
	}

	public String get_InputString(String cFIELDNAME) throws myException
	{
		String cRueck = "";
		
		if (this.containsKey(cFIELDNAME))
		{
			if (this.get(cFIELDNAME) != null)
			{
				cRueck = (String)this.get(cFIELDNAME);
			}
			
		}
		else
		{
			throw new myException(this,":"+cFIELDNAME+": was not found in Resultmap!");
		}
		
		return cRueck;
		
	}


	/**
	 * 
	 * @param cFIELDNAME
	 * @param cValue_when_Emtpy_or_Null
	 * @return
	 * @throws myException
	 */
	public String get_InputString(String cFIELDNAME, String cValue_when_Emtpy_or_Null) throws myException
	{
		String cRueck = this.get_InputString(cFIELDNAME);
		
		if (S.isEmpty(cRueck))
		{
			return cValue_when_Emtpy_or_Null;
		}
		else
		{
			return cRueck;
		}
		
	}

	
	
	public void add_FieldWithFalseInput(String cFIELD_HASH)
	{
		this.vFieldsWithFalseInput.add(cFIELD_HASH);
	}
	
	
	public Vector<String> get_vFieldsWithFalseInput()
	{
		return this.vFieldsWithFalseInput;
	}
	
	
	
	
	
	
	
	
	
	public TestingDate get_DateActualValue(String cFIELDNAME, boolean bNullWhenEmpty, boolean bNullWhenFalseInput) throws myException
	{
		String cTestValue = this.get_InputString(cFIELDNAME);
		
		if (bibALL.isEmpty(cTestValue))
		{
			if (bNullWhenEmpty)
			{
				return null;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_DActualMaskValue:"+cTestValue+" is empty !");
			}
		}
		
		TestingDate oDateRueck = new TestingDate(cTestValue);
		
		if (oDateRueck.testing())
		{
			return oDateRueck;
		}
		else
		{
			if (bNullWhenFalseInput)
			{
				return null;
			}
			else
			{
				throw new myException(this,"get_DateActualMaskValue:NoDate-Field !!");
			}
		}
		
	}

	

	public Double get_DActualValue(String cFIELDNAME, boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Double nullValue) throws myException
	{
		String cTestValue = this.get_InputString(cFIELDNAME);

		if (bibALL.isEmpty(cTestValue))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException(this,"get_DActualDBValue:"+cTestValue+" is empty !");
			}
		}

		
		
		MyDouble oDOU = new MyDouble(cTestValue);
		
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else
		{
			if (bNullValueWhenFalseInput)
			{
				return nullValue;
			}
			else
			{
				throw new myException(this,"get_DActualDBValue:"+oDOU.get_cErrorCODE()+" --> "+cTestValue);
			}
		}
	}

	
	
	public Long get_LActualValue(String cFIELDNAME, boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Long nullValue) throws myException
	{
		String cTestValue = this.get_InputString(cFIELDNAME);
		
		if (bibALL.isEmpty(cTestValue))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException(this,"get_IActualMaskValue:"+cTestValue+" is empty !");
			}
		}
		
		MyLong oINT = new MyLong(cTestValue);
		
		if (oINT.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oINT.get_oLong();
		}
		else
		{
			if (bNullValueWhenFalseInput)
			{
				return nullValue;
			}
			else
			{
				throw new myException(this,"get_IActualMaskValue:"+oINT.get_cErrorCODE()+" --> "+cTestValue);
			}
		}
	}
	
	
	
	
	public String get_Unformated_Long_String(String cFIELDNAME) throws myException
	{
		return new MyLong(this.get_InputString(cFIELDNAME)).get_cUF_LongString();
	}
	
}
