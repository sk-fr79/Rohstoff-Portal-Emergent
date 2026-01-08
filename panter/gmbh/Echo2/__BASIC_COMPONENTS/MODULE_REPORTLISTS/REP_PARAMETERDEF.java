package panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Label;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.Selector_Report_Params.ENUM_Selector_Report_Params;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUpHelp;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.MyE2_TextField_DatePOPUP_OWN;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;
import panter.gmbh.indep.maggie.TestingDate;

public class REP_PARAMETERDEF extends HashMap<String,String>
{
	public static String			FIELD_LIST = "ID_REPORT_PARAMETER|TEXTUSERINTERFACE|DROPDOWN_DEF|BESCHREIBUNGSTEXT|PARAMETERNAME_IN_REPORT|" +
													"DEFAULTVALUE|PARAMETERTYP|PARAMETER_MUST_BE_SET|ID_REPORT|" +
													"ERSATZTEXT_FUER_LEER|ERSATZTEXT_FUER_WERT";
	
	public static StringSeparator	SEP_FIELDLIST = null;
	
	
	private MyE2_TextField					oTF_ForUserInput = null;    // bei einfachen eingaben ist es ein Textfeld, 
	
	private MyE2_TextField_DatePOPUP_OWN 	oDateInput = null;  		// datums-popup wenn ein typ date
	
	private MyE2_CheckBox				    oCheckBox = null;          // wenn im definitionsfeld der String #CKECKBOX# steht  				
	private MyE2_SelectField				oSelectUserInput = null;	// bei definierten ein dropdown 
	private MyE2_Label 						oLabel_UserInfo = null;
	private Component						oComponentHELP = null;
	
	
	// MP: 2016-06-01 zusätzliche Defaultwerte die ersetzt werden können ausser die groovy und Systemvariablen
	private Hashtable<ENUM_Selector_Report_Params,Object> 		htAdditionalDefaultValues = null;
	
	
	public REP_PARAMETERDEF(String[] ValueList) throws myException
	{
		this(ValueList,null);
	}
	
	
	public REP_PARAMETERDEF(String[] ValueList, Hashtable<ENUM_Selector_Report_Params,Object> AdditionalDefaultValues) throws myException
	{
		super();
		
		this.htAdditionalDefaultValues = AdditionalDefaultValues;
		
		if (REP_PARAMETERDEF.SEP_FIELDLIST==null)
			REP_PARAMETERDEF.SEP_FIELDLIST = new StringSeparator(REP_PARAMETERDEF.FIELD_LIST,"|");
		
		for (int k=0;k<ValueList.length;k++)
		{
			this.put(REP_PARAMETERDEF.SEP_FIELDLIST.get_(k),ValueList[k]);
		}
		
		//2013-10-11: checkbox als weiteres auswahlelement
		// 2016-06-03: manfred: additional Selektor-Parameter in den definitionen tauschen, bevor die Systemvariablen getauscht werden.
//		this.oCheckBox = REP_CreateDropDownForParameters.buildCheckBox(this.get_DROPDOWN_DEF(),this.get_QuailfiedDEFAULTVALUE());
		this.oCheckBox = REP_CreateDropDownForParameters.buildCheckBox(  this.replaceAdditionalDefaultValues(this.get_DROPDOWN_DEF())
																		,this.get_QuailfiedDEFAULTVALUE());
		
		
//		this.oSelectUserInput = REP_CreateDropDownForParameters.build_oSelectFieldUserInput(this.get_DROPDOWN_DEF(),this.get_QuailfiedDEFAULTVALUE());
		this.oSelectUserInput = REP_CreateDropDownForParameters.build_oSelectFieldUserInput( this.replaceAdditionalDefaultValues(this.get_DROPDOWN_DEF())
																							,this.get_QuailfiedDEFAULTVALUE());
		
		this.oDateInput = this.build_oDateField();           //wird nur != nll, wenn es ein date-feld ist
		this.oTF_ForUserInput = this.build_oTF_Parameter();
		this.oLabel_UserInfo = this.build_oLabelDescription();
		this.oComponentHELP = this.build_ComponentInfo();
	}

	
	
	public String get_ID_REPORT_PARAMETER() 			{			return (String)this.get("ID_REPORT_PARAMETER");				}
	public MyString get_TEXTUSERINTERFACE() 			{			return new MyE2_String(bibALL.null2leer((String)this.get("TEXTUSERINTERFACE")));	}
	public String get_PARAMETERNAME_IN_REPORT() 		{			return (String)this.get("PARAMETERNAME_IN_REPORT");			}
	public String get_DEFAULTVALUE() 					{			return (String)this.get("DEFAULTVALUE");					}
	public String get_BESCHREIBUNGSTEXT() 				{			return (String)this.get("BESCHREIBUNGSTEXT");				}
	public String get_PARAMETERTYP() 					{			return (String)this.get("PARAMETERTYP");					}
	public String get_ID_REPORT() 						{			return (String)this.get("ID_REPORT");						}
	public String get_DROPDOWN_DEF() 					{			return (String)this.get("DROPDOWN_DEF");					}
	
	public boolean get_bPARAMETER_MUST_BE_SET()			{			return (((String)this.get("PARAMETER_MUST_BE_SET")).equals("Y"));		}
	
	
	public String get_QuailfiedDEFAULTVALUE() throws myException 			
	{		
		// Standard-Defaultwerte ersetzen
		String cDefVal = (String)this.get("DEFAULTVALUE");
		
		// zuerst die zusätzlichen Defaultwerte ersetzen, damit diese dann in den Groovy-Statements genutzt werden können
		cDefVal = this.replaceAdditionalDefaultValues(cDefVal);
		
		// dann die Systemvariablen und groovy-Scripts
		cDefVal = bibReplacer.ReplaceSysvariablesInStrings(cDefVal);
		
		return cDefVal;
	}
	
	
	// baut ein Textfeld nach den vorgaben der parameter-Definition 
	private MyE2_TextField	build_oTF_Parameter() throws myException 
	{
		MyE2_TextField oTFRueck = new MyE2_TextField();
		if (this.get_bPARAMETER_MUST_BE_SET())
			oTFRueck.setStyle(oTFRueck.EXT().get_STYLE_FACTORY().get_Style(true,false,false));   // roter rand wenn MUSS-FELD
		
		
		if (this.get_PARAMETERTYP().equals(myCONST.REPORT_TYPE_VALUE_INTEGER))
			oTFRueck.set_iWidthPixel(100);
		
		if (this.get_PARAMETERTYP().equals(myCONST.REPORT_TYPE_VALUE_DATE))
			oTFRueck.set_iWidthPixel(100);
		
		if (this.get_PARAMETERTYP().equals(myCONST.REPORT_TYPE_VALUE_TEXT))
			oTFRueck.set_iWidthPixel(150);
		
		
		if (! bibALL.isEmpty(this.get_DEFAULTVALUE()))
			oTFRueck.setText(this.get_QuailfiedDEFAULTVALUE());
			
		return oTFRueck;
		
	}
	

	// baut MyE2_TextField_DatePOPUP_OWN (wenn es ein date-feld ist)
	private MyE2_TextField_DatePOPUP_OWN	build_oDateField() throws myException 
	{
		MyE2_TextField_DatePOPUP_OWN oTFRueck = null;
		
		if (this.get_PARAMETERTYP().equals(myCONST.REPORT_TYPE_VALUE_DATE))
		{
			oTFRueck = new MyE2_TextField_DatePOPUP_OWN();
			if (this.get_bPARAMETER_MUST_BE_SET())
				oTFRueck.get_oTextField().setStyle(oTFRueck.get_oTextField().EXT().get_STYLE_FACTORY().get_Style(true,false,false));   // roter rand wenn MUSS-FELD
		
			if (! bibALL.isEmpty(this.get_DEFAULTVALUE()))
				oTFRueck.get_oTextField().setText(this.get_QuailfiedDEFAULTVALUE());
		}
		
		
		return oTFRueck;
		
	}

	
	
	
	
	
	
	
	
	private MyE2_Label build_oLabelDescription()
	{
		MyE2_Label oLabelRueck = new MyE2_Label(this.get_TEXTUSERINTERFACE());
		return oLabelRueck;
	}
	
	

	private Component build_ComponentInfo()
	{
		String cHelpText = this.get_BESCHREIBUNGSTEXT();
		if (bibALL.isEmpty(cHelpText))
		{
			return new Label("");
		}
		
		String cHelp = new MyE2_String(cHelpText).CTrans();
		
		Vector<String> vSep = bibALL.TrenneZeile(cHelp,"\n");    // zeilentrenner
		
		Vector<MyString> vvSep = new Vector<MyString>();
		for (int i=0;i<vSep.size();i++)
			vvSep.add(new MyE2_String(vSep.get(i)));
		
		MyE2_PopUpHelp oPopHelp = new MyE2_PopUpHelp(vvSep);
		return oPopHelp;
	}
	

	
	
	
	
	/**
	 * @throws myException 
	 * @returns Strings for Report-Query without ' - Signs
	 * When Error it returns "@@@"
	 */
	public String  get_validatedInput() throws myException
	{
		String cTest = "";
		
		try
		{
			if (this.oCheckBox != null) {
				cTest = this.oCheckBox.isSelected()?"Y":"N";
			} else if (this.oSelectUserInput != null)
			{
				cTest = this.oSelectUserInput.get_ActualWert();
			}
			else if (this.oDateInput != null)
			{
				cTest = S.NN(this.oDateInput.get_oTextField().getText());
			}
			else
			{
				cTest = this.oTF_ForUserInput.getText();
				// vorbesetzen als waere kein fehler vorhanden
				this.oTF_ForUserInput.setStyle(this.oTF_ForUserInput.EXT().get_STYLE_FACTORY().get_Style(true,!this.get_bPARAMETER_MUST_BE_SET(),false));
			}
		}
		catch (myException ex)
		{
			return "@@@";
		}

		
		// hier muss die uebersetzung rein (wenn vorhanden)
		// zuerst nachsehen, ob der wert leer ist
		if (S.isEmpty(cTest) && S.isFull(this.get("ERSATZTEXT_FUER_LEER")))
		{
			cTest = this.get("ERSATZTEXT_FUER_LEER");
			
			//jetzt noch evtl. system-platzhalter ersetzen
			cTest = bibReplacer.ReplaceSysvariablesInStrings(cTest);
		}
		else if (S.isFull(cTest) && S.isFull(this.get("ERSATZTEXT_FUER_WERT")))
		{
			if (this.get("ERSATZTEXT_FUER_WERT").indexOf("#WERT#")<0)
			{
				throw new myException("Error in Report-Design! Field <ERSATZTEXT_FUER_WERT> must contain #WERT# !!!");
			}
			cTest = bibALL.ReplaceTeilString(this.get("ERSATZTEXT_FUER_WERT"), "#WERT#", cTest);
			cTest = bibReplacer.ReplaceSysvariablesInStrings(cTest);
		}
		
		
		
		if (this.get_bPARAMETER_MUST_BE_SET() && bibALL.isEmpty(cTest))
		{
			this.oTF_ForUserInput.setStyle(this.oTF_ForUserInput.EXT().get_STYLE_FACTORY().get_Style(true,false,true));
			return "@@@";
		}

		
		if (!this.get_bPARAMETER_MUST_BE_SET() && bibALL.isEmpty(cTest))
		{
			return "";
		}

		
		
		
		if 		(this.get_PARAMETERTYP().equals(myCONST.REPORT_TYPE_VALUE_INTEGER))
		{
			DotFormatterGermanFixed oDF = new DotFormatterGermanFixed(cTest);
			if (oDF.doFormat())
			{
				String cHelp = oDF.getStringUnFormated();
				return cHelp;
			}
			else
			{
				this.oTF_ForUserInput.setStyle(this.oTF_ForUserInput.EXT().get_STYLE_FACTORY().get_Style(true,false,true));
				return "@@@";
			}
			
		}
		else if (this.get_PARAMETERTYP().equals(myCONST.REPORT_TYPE_VALUE_DATE))
		{
			TestingDate	oTD = new TestingDate(cTest);
			if (oTD.testing())
			{
				String cHelp = oTD.get_FormatedDateString("dd.mm.yyyy");
				String cRueck = cHelp.substring(6,10)+"-"+cHelp.substring(3, 5)+"-"+cHelp.substring(0, 2);
					
				return cRueck;
			}
			else
			{
				this.oTF_ForUserInput.setStyle(this.oTF_ForUserInput.EXT().get_STYLE_FACTORY().get_Style(true,false,true));
				return "@@@";
			}
		}
		else
		{
			return cTest;
		}
	}



	public MyE2_Label get_oLabel_UserInfo()
	{
		return oLabel_UserInfo;
	}



	public Component get_oComponent_ForUserInput()
	{
		if (this.oCheckBox != null) {
			return this.oCheckBox;
		} else if (this.oSelectUserInput != null)
		{
			return this.oSelectUserInput;
		}
		else if (this.oDateInput != null)
		{
			return this.oDateInput;
		}
		else
		{
			return oTF_ForUserInput;
		}
	}
	
	public Component		get_oComponentHELP()
	{
		return this.oComponentHELP;			
	}
	
	
	

	/** 
	 * ersetzt Platzhalter mit dem Wert, wenn er gefunden wird
	 * 
	 * @author manfred
	 * @date 01.06.2016
	 *
	 * @param sDefaultDef
	 * @return
	 */
	private String replaceAdditionalDefaultValues(String sDefaultDef){
		
		if (!bibALL.isEmpty(sDefaultDef) && htAdditionalDefaultValues != null && htAdditionalDefaultValues.size() > 0 ){
			
			Set<ENUM_Selector_Report_Params> keys = htAdditionalDefaultValues.keySet();
			
			Iterator<ENUM_Selector_Report_Params>  it = keys.iterator();
			
			while (it.hasNext()){
				ENUM_Selector_Report_Params key = it.next();
				String value = htAdditionalDefaultValues.get(key).toString();
				sDefaultDef = bibALL.ReplaceTeilString(sDefaultDef, key.get_Name_For_Param(), value );		
			}
		}

		return sDefaultDef;
	}


}
