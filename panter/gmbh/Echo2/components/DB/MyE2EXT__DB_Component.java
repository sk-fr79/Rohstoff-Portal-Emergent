package panter.gmbh.Echo2.components.DB;

import java.math.BigDecimal;
import java.util.Locale;

import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.TestingDate;

/**
 * @author martin
 *
 */
public class MyE2EXT__DB_Component implements E2_IF_Copy
{
	private SQLField 				oSQLField = null;

	private boolean 				bIsSortable = true;						// falls die selektion nach dieser spalte sortierbar sein soll
	
	private boolean				bGivesBackValueToDB	= false;			// wird benutzt, um in der maske die felder zu erkennen, 
																			// bei denen ein benutzer etwas eingeben kann
	
	private MyE2IF__DB_Component  	ownE2IF__DB_Component = null;         // hier wird eine referenz auf die eigene DB_Komponente gehalten
	
	
	/*
	 * beim laden von maskenwerte werden diese werte hier abgelegt und koennen fuer validierungen o.ae. verwendet werden
	 */
	private String      			cLASTActualMaskValue = null;
	private String      			cLASTActualDBValueFormated = null;
	
	//falls ein Titelbutton einen besonderen sort-ausdruck braucht
	private String              	cSortAusdruckFuerSortbuttonUP = 	null;
	private String              	cSortAusdruckFuerSortbuttonDOWN = 	null;
	
	
//	//erweiterung fuer neue DB_RB-Struktur
//	private String  				cTABLENAME = null;
//	private String  				cFIELDNAME = null;
	
	
	public MyE2EXT__DB_Component(MyE2IF__DB_Component E2IF__DB_Component)
	{
		super();
		this.ownE2IF__DB_Component = E2IF__DB_Component;
	}


	public void 			set_oSQLField(SQLField osqlField)						{		this.oSQLField = osqlField;	}
	public SQLField 		get_oSQLField()											{		return this.oSQLField;	}
	public SQLFieldMAP 		get_oSQLFieldMAP()										{		return this.oSQLField.get_oFieldMapThisBelongsTo();	}
	public boolean 		get_bIsSortable()										{		return bIsSortable;		}
	public void 			set_bIsSortable(boolean bSortable)						{		this.bIsSortable = bSortable; }
	

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (!(objHelp instanceof MyE2IF__DB_Component))
			throw new myExceptionCopy("MyE2EXT__DB_Component:get_Copy(): Error: Copy must have an MyE2IF__DB_Component-Object as parameter !");
		
		MyE2EXT__DB_Component oRueck = new MyE2EXT__DB_Component((MyE2IF__DB_Component)objHelp);
		oRueck.set_oSQLField(this.get_oSQLField());
		oRueck.set_bIsSortable(this.bIsSortable);
		oRueck.set_bGivesBackValueToDB(this.get_bGivesBackValueToDB());
		oRueck.set_cSortAusdruckFuerSortbuttonUP(this.cSortAusdruckFuerSortbuttonUP);
		oRueck.set_cSortAusdruckFuerSortbuttonDOWN(this.cSortAusdruckFuerSortbuttonDOWN);
		return oRueck;
	}

	public boolean get_bGivesBackValueToDB()
	{
		return this.bGivesBackValueToDB;
	}

	public void set_bGivesBackValueToDB(boolean givesBackValueToDB)
	{
		this.bGivesBackValueToDB = givesBackValueToDB;
	}

	
	/**
	 * prueft anhand der schalter disabled ... und dem feld (writeable or not) ob ein
	 * feld, das diese extension hat, enabled sein kann
	 * Bei komplexen feldern werden nur die schalter disabled gecheckt, da diese nicht direkt in die datenbanktabelle schreiben
	 * 
	 * @param bDBFieldIsComplex
	 * @return
	 */
	public boolean get_bCanBeEnabled(boolean bDBFieldIsComplex)
	{
		if (!bDBFieldIsComplex)
			return this.oSQLField.get_bWriteable() && this.oSQLField.get_bFieldCanBeWrittenInMask();
		else
			return true;
	}
	
	
	
	
	
	public TestingDate get_DateActualMaskValue(boolean bNullWhenEmpty, boolean bNullWhenFalseInput) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()))
		{
			if (bNullWhenEmpty)
			{
				return null;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_DActualMaskValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}
		
		TestingDate oDateRueck = new TestingDate(this.ownE2IF__DB_Component.get_cActualMaskValue());
		
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

	
	
	public Double get_DActualMaskValue(boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Double nullValue) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_DActualMaskValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}
		
		
		MyDouble oDOU = new MyDouble(this.ownE2IF__DB_Component.get_cActualMaskValue());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
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
				throw new myException("MyE2EXT__DB_Component:get_DActualMaskValue:"+oDOU.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
			}
		}
		
	}

	

	public Double get_DActualDBValue(boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Double nullValue) throws myException
	{
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualDBValueFormated()))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_DActualDBValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}

		
		
		MyDouble oDOU = new MyDouble(this.ownE2IF__DB_Component.get_cActualDBValueFormated());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
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
				throw new myException("MyE2EXT__DB_Component:get_DActualDBValue:"+oDOU.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
			}
		}
	}


	
	
	
	
	public MyDate get_MyDateActualDBValue(boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, MyDate nullValue) throws myException
	{
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualDBValueFormated()))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_MyDateActualDBValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}

		MyDate oDate = new MyDate(this.ownE2IF__DB_Component.get_cActualDBValueFormated());
		
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			return oDate;
		}
		else
		{
			if (bNullValueWhenFalseInput)
			{
				return nullValue;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_MyDateActualDBValue:"+oDate.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
			}
		}
	}

	
	
	
	
	
	
	
	public Long get_LActualMaskValue(boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Long nullValue) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_IActualMaskValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}
		
		MyLong oINT = new MyLong(this.ownE2IF__DB_Component.get_cActualMaskValue());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
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
				throw new myException("MyE2EXT__DB_Component:get_IActualMaskValue:"+oINT.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
			}
		}
	}
	

	public Long get_LActualDBValue(boolean bNullValueWhenEmpty, boolean bNullValueWhenFalseInput, Long nullValue) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualDBValueFormated()))
		{
			if (bNullValueWhenEmpty)
			{
				return nullValue;
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_IActualDBValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}

		
		MyLong oINT = new MyLong(this.ownE2IF__DB_Component.get_cActualDBValueFormated());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oINT.get_cErrorCODE().equals(MyLong.ALL_OK))
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
				throw new myException("MyE2EXT__DB_Component:get_IActualDBValue:"+oINT.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
			}
		}		
	}



	
	
	// variante 2 der getter
	public Double get_DActualMaskValue(Double dValueWhenEmpty, Double dValueWhenFalseInput) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()))
		{
			return dValueWhenEmpty;
		}
		
		
		MyDouble oDOU = new MyDouble(this.ownE2IF__DB_Component.get_cActualMaskValue());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else
		{
			return dValueWhenFalseInput;
		}
		
	}

	

	public Double get_DActualDBValue(Double dValueWhenEmpty, Double dValueWhenFalseInput) throws myException
	{
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualDBValueFormated()))
		{
			return dValueWhenEmpty;
		}

		
		
		MyDouble oDOU = new MyDouble(this.ownE2IF__DB_Component.get_cActualDBValueFormated());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else
		{
			return dValueWhenFalseInput;
		}
	}

	

	
	public BigDecimal get_bdActualDBValue(BigDecimal bdValueWhenEmpty, BigDecimal bdValueWhenFalseInput) throws myException
	{
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualDBValueFormated()))
		{
			return bdValueWhenEmpty;
		}
		
		MyBigDecimal bdWert = new MyBigDecimal(this.ownE2IF__DB_Component.get_cActualDBValueFormated(),bdValueWhenEmpty,bdValueWhenFalseInput);
		
		return bdWert.get_bdWert();
	}


	
	
	
	
	public Long get_LActualMaskValue(Long lValueWhenEmpty, Long lValueWhenFalseInput) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()))
		{
			return lValueWhenEmpty;
		}
		
		MyLong oINT = new MyLong(this.ownE2IF__DB_Component.get_cActualMaskValue());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oINT.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oINT.get_oLong();
		}
		else
		{
			return lValueWhenFalseInput;
		}
	}
	

	public Long get_LActualDBValue(Long lValueWhenEmpty, Long lValueWhenFalseInput) throws myException
	{
		
		if (bibALL.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()))
		{
			return lValueWhenEmpty;
		}

		
		MyLong oINT = new MyLong(this.ownE2IF__DB_Component.get_cActualDBValueFormated());
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oINT.get_cErrorCODE().equals(MyLong.ALL_OK))
		{
			return oINT.get_oLong();
		}
		else
		{
			return lValueWhenFalseInput;
		}		
	}

	
	
	
	// ---------------------
	
	
	
	public String get_cLASTActualDBValueFormated() 
	{
		return cLASTActualDBValueFormated;
	}


	public String get_cLASTActualMaskValue() 
	{
		return cLASTActualMaskValue;
	}

	
	
	
	public Double get_D_LAST_ActualMaskValue(boolean b_0_WhenEmpty) throws myException
	{
		
		if (bibALL.isEmpty(this.cLASTActualMaskValue))
		{
			if (b_0_WhenEmpty)
			{
				return new Double(0);
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_DActualMaskValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}
		
		
		MyDouble oDOU = new MyDouble(this.cLASTActualMaskValue);
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else
			throw new myException("MyE2EXT__DB_Component:get_DActualMaskValue:"+oDOU.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
		
		
	}
	

	public Double get_D_LAST_ActualDBValue(boolean b_0_WhenEmpty) throws myException
	{
		if (bibALL.isEmpty(this.cLASTActualDBValueFormated))
		{
			if (b_0_WhenEmpty)
			{
				return new Double(0);
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_DActualDBValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}

		
		
		MyDouble oDOU = new MyDouble(this.cLASTActualDBValueFormated);
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else
			throw new myException("MyE2EXT__DB_Component:get_DActualDBValue:"+oDOU.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
		
		
	}


	
	public Double get_D_LAST_ActualDBValue(Double dValueWhenEmpty,Double dValueWhenFalse) throws myException
	{
		return __get_lastDoubleValue(this.cLASTActualDBValueFormated, dValueWhenEmpty, dValueWhenFalse);
	}
	
	public Double get_D_LAST_ActualMaskValue(Double dValueWhenEmpty,Double dValueWhenFalse) throws myException
	{
		return __get_lastDoubleValue(this.cLASTActualMaskValue, dValueWhenEmpty, dValueWhenFalse);
	}


	private Double __get_lastDoubleValue(String cPruefWert, Double dValueWhenEmpty,Double dValueWhenFalse) throws myException
	{
		if (bibALL.isEmpty(cPruefWert))
		{
			return dValueWhenEmpty;
		}
		
		MyDouble oDOU = new MyDouble(cPruefWert);
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oDOU.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oDOU.get_oDouble();
		}
		else
		{
			return dValueWhenFalse;
		}
	}


	public Long get_L_LAST_ActualDBValue(Long dValueWhenEmpty,Long dValueWhenFalse) throws myException
	{
		return __get_lastLongValue(this.cLASTActualDBValueFormated, dValueWhenEmpty, dValueWhenFalse);
	}
	
	public Long get_L_LAST_ActualMaskValue(Long dValueWhenEmpty,Long dValueWhenFalse) throws myException
	{
		return __get_lastLongValue(this.cLASTActualMaskValue, dValueWhenEmpty, dValueWhenFalse);
	}

	private Long __get_lastLongValue(String cPruefWert, Long lValueWhenEmpty, Long lValueWhenFalse) throws myException
	{
		if (bibALL.isEmpty(cPruefWert))
		{
			return lValueWhenEmpty;
		}
		
		MyLong oLonh = new MyLong(cPruefWert);
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oLonh.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oLonh.get_oLong();
		}
		else
		{
			return lValueWhenFalse;
		}
	}



	
	
	public Long get_L_LAST_ActualMaskValue(boolean b_0_WhenEmpty) throws myException
	{
		
		if (bibALL.isEmpty(this.cLASTActualMaskValue))
		{
			if (b_0_WhenEmpty)
			{
				return new Long(0);
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_IActualMaskValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}
		
		MyLong oINT = new MyLong(this.cLASTActualMaskValue);
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oINT.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oINT.get_oLong();
		}
		else
			throw new myException("MyE2EXT__DB_Component:get_IActualMaskValue:"+oINT.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
		
		
	}
	

	public Long get_L_LAST_ActualDBValue(boolean b_0_WhenEmpty) throws myException
	{
		
		if (bibALL.isEmpty(this.cLASTActualDBValueFormated))
		{
			if (b_0_WhenEmpty)
			{
				return new Long(0);
			}
			else
			{
				throw new myException("MyE2EXT__DB_Component:get_IActualDBValue:"+this.oSQLField.get_cFieldName()+" is empty !");
			}
		}

		
		MyLong oINT = new MyLong(this.cLASTActualDBValueFormated);
		String cHelp = "FieldName: ";

		if (this.oSQLField != null)
			cHelp += this.oSQLField.get_cFieldName();
		else
			cHelp += " ????? ";
		
		
		if (oINT.get_cErrorCODE().equals(MyDouble.ALL_OK))
		{
			return oINT.get_oLong();
		}
		else
			throw new myException("MyE2EXT__DB_Component:get_IActualDBValue:"+oINT.get_cErrorCODE()+" --> "+this.oSQLField.get_cFieldName());
		
		
	}


	public void set_cLASTActualDBValueFormated(String actualDBValueFormated) 
	{
		cLASTActualDBValueFormated = actualDBValueFormated;
	}


	public void set_cLASTActualMaskValue(String actualMaskValue) 
	{
		cLASTActualMaskValue = actualMaskValue;
	}

	
	public boolean get_bActualMaskValueHasChanged() throws myException 
	{
		
		if 		(S.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()) && S.isEmpty(this.cLASTActualMaskValue))
		{
			return false;
		}
		else if (S.isEmpty(this.ownE2IF__DB_Component.get_cActualMaskValue()) && S.isFull(this.cLASTActualMaskValue))
		{
			return true;
		}
		else if (S.isFull(this.ownE2IF__DB_Component.get_cActualMaskValue()) && S.isEmpty(this.cLASTActualMaskValue))
		{
			return true;
		}
		else if (this.ownE2IF__DB_Component.get_cActualMaskValue().trim().equals(this.cLASTActualMaskValue.trim()))
		{
			return false;
		}

		//falls beide voll sind, aber nicht gleich, dann die datentypen unterscheiden
		if 			(this.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT))
		{
			return true;
		}
		else if 	(this.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
		{
			TestingDate dateOLD = new TestingDate(this.cLASTActualMaskValue);
			TestingDate dateNEW = new TestingDate(this.ownE2IF__DB_Component.get_cActualMaskValue());
			
			if (dateOLD.testing() & dateNEW.testing())
			{
				if (dateOLD.get_FormatedDateString("dd.mm.yyyy").equals(dateNEW.get_FormatedDateString("dd.mm.yyyy")))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		else if 	(this.get_oSQLField().get_oFieldMetaDef().get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
		{
			DotFormatter numberOLD = new DotFormatter(this.cLASTActualMaskValue,5,Locale.GERMAN,true,3);
			DotFormatter numberNEW = new DotFormatter(this.ownE2IF__DB_Component.get_cActualMaskValue(),5,Locale.GERMAN,true,3);
			
			if (numberOLD.doFormat() & numberNEW.doFormat())
			{
				if (numberOLD.getStringUnFormated().equals(numberNEW.getStringUnFormated()))
				{
					return false;
				}
				else
				{
					return true;
				}
			}
		}
		
		
		throw new myException(this,"Error in checking changes !");
	}


	public String get_cSortAusdruckFuerSortbuttonUP()
	{
		return cSortAusdruckFuerSortbuttonUP;
	}


	public void set_cSortAusdruckFuerSortbuttonUP(String sortAusdruckFuerSortbuttonUP)
	{
		cSortAusdruckFuerSortbuttonUP = sortAusdruckFuerSortbuttonUP;
	}
	
	public String get_cSortAusdruckFuerSortbuttonDOWN()
	{
		return cSortAusdruckFuerSortbuttonDOWN;
	}


	public void set_cSortAusdruckFuerSortbuttonDOWN(String sortAusdruckFuerSortbuttonDOWN)
	{
		cSortAusdruckFuerSortbuttonDOWN = sortAusdruckFuerSortbuttonDOWN;
	}
	
	
	
	
//	public String get_cTABLENAME() {
//		return cTABLENAME;
//	}
//
//
//	public void set_cTABLENAME(String tablename) {
//		this.cTABLENAME = tablename;
//	}
//
//
//	public String get_cFIELDNAME() {
//		return cFIELDNAME;
//	}
//
//
//	public void set_cFIELDNAME(String fieldname) {
//		this.cFIELDNAME = fieldname;
//	}
//


	
	
}
