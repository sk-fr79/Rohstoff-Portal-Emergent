package panter.gmbh.Echo2.components.DB;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.BasicInterfaces.IF_TranslateDbValIntoMaskVal;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_DB_TextField4HH_MM extends MyE2_DB_TextField implements MyE2IF__DB_Component,MyE2IF__Component, E2_IF_Copy, IF_TranslateDbValIntoMaskVal {

	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param iMaxInputSize
	 * @param bDisabledFromBasic
	 * @param oFont
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, int iMaxInputSize, boolean bDisabledFromBasic, Font oFont) throws myException {
		super(osqlField, bAutoAlign, iWidthInPixel, iMaxInputSize, bDisabledFromBasic, oFont);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param iMaxInputSize
	 * @param bDisabledFromBasic
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, int iMaxInputSize,
			boolean bDisabledFromBasic) throws myException {
		super(osqlField, bAutoAlign, iWidthInPixel, iMaxInputSize, bDisabledFromBasic);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param oLayout
	 * @param oFont
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, LayoutData oLayout,
			Font oFont) throws myException {
		super(osqlField, bAutoAlign, iWidthInPixel, oLayout, oFont);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @param oLayout
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel, LayoutData oLayout)
			throws myException {
		super(osqlField, bAutoAlign, iWidthInPixel, oLayout);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param bAutoAlign
	 * @param iWidthInPixel
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField, boolean bAutoAlign, int iWidthInPixel) throws myException {
		super(osqlField, bAutoAlign, iWidthInPixel);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param iWidthInPixel
	 * @param oStyle
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField, int iWidthInPixel, XXX_StyleFactory oStyle) throws myException {
		super(osqlField, iWidthInPixel, oStyle);
		this._init();
	}

	/**
	 * @param osqlField
	 * @throws myException
	 */
	public MyE2_DB_TextField4HH_MM(SQLField osqlField) throws myException {
		super(osqlField);
		this._init();
	}

	
	private void _init() {
		this.setTranslator(new ownTranslatorTime2HH_MM());
		this.EXT().add_Field_Validator_Check_Input_and_MarkFalseValues(new XX_FieldSetter_AND_Validator() {
			@Override
			public MyE2_MessageVector isValid(String cSTATUS_MAP, MyE2EXT__Component EXT_own) throws myException {
				return MyE2_DB_TextField4HH_MM.this.getTranslator().validateCorrectMaskVal(MyE2_DB_TextField4HH_MM.this.getText());
			}
		});
	}
	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		String cTranslated = this.translateDbValToMaskVal(cText);
		super.set_cActual_Formated_DBContent_To_Mask(cTranslated, cMASK_STATUS, oResultMAP);
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		return this.translateMaskValToDBVal(super.get_cActualDBValueFormated());
	}

	
	
	private class ownTranslatorTime2HH_MM implements IF_Translator {

		@Override
		public String translateDbValIntoMaskVal(String dbVal) throws myException {
			//dbval ist in minuten
			MyLong minutenAusDb = new MyLong(dbVal);
			String ret = "";
			
			if (minutenAusDb.isOK()) {
				Long stunden = minutenAusDb.get_oLong()/60;
				Long minuten = minutenAusDb.get_oLong()%60;
				
		        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
		        oSym.setDecimalSeparator(',');
		        oSym.setGroupingSeparator('.');
			    ret = new DecimalFormat("#0",oSym).format(stunden)+":"+ new DecimalFormat("00",oSym).format(minuten);
			}
			return ret;
		}

		@Override
		public String translateMaskValIntoDbVal(String maskVal) throws myException {
			String ret = "";
			if (S.isEmpty(maskVal)) {
				return "";
			}
			
			//Bsp: 34:45  = 34 std. 45 Minuten
			if (maskVal.contains(":")) {
				String cStd = maskVal.substring(0,maskVal.indexOf(":"));
				String cMin = maskVal.substring(maskVal.indexOf(":")+1);
				
				MyLong lStd = new MyLong(cStd);
				MyLong lMin = new MyLong(cMin);
				
				if (lStd.isOK() && lMin.isOK() && lMin.get_lValue()>=0 && lMin.get_lValue()<60) {
					Long l = lStd.get_lValue()*60+lMin.get_lValue();
					ret = l.toString();
				}
			}
			return ret;
		}

		@Override
		public MyE2_MessageVector validateCorrectMaskVal(String maskVal) throws myException {
			if (S.isFull(maskVal) && S.isEmpty(this.translateMaskValIntoDbVal(maskVal))) {
				return bibMSG.getNewMV()._addAlarm("Der Inhalt des Zeitfeldes ist kein Zeit-Wert: "+maskVal);
			}
			
			return bibMSG.getNewMV();
		}
		
	}
	
}
