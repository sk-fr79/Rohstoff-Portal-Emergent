package panter.gmbh.Echo2.ListAndMask.List.calculation;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2IF__BelongsToNavigationList;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class CALC_Rule_ABSTRACT implements E2IF__BelongsToNavigationList{

	/*
	 * Beschriftung fuer diese Ergebnis-Zeile/Regel
	 */
	private MyString 			c_INFO_TEXT = null;
	private int 	  			i_DECIMALNUMBERS = 2;
	private E2_NavigationList  	o_NAVILIST = null;

	private MyE2_String         c_FEHLERINFO4USER = null;
	
	/**
	 * merkmal, ob ein ergebniss auch in einer evtl. zusatzkomponente (z.B. Textzeile in der Titelleiste) angezeigt wird
	 */
	private boolean  			b_ShowOnlyInPopup = true;
	
	public CALC_Rule_ABSTRACT() {
		super();
	}


	/**
	 * 
	 * @param vID_COL
	 * @param iRound
	 * @return Value for this Calcrule with Multi-ID-Vector
	 * 
	 * @throws myException
	 */
	public abstract BigDecimal get_bdVALUE_ERGEBNISS(Vector<String> vID_COL) throws myException;


	public MyString get_cINFOTEXT() {
		return c_INFO_TEXT;
	}


	public void set_cINFOTEXT(MyString cInfoText) {
		this.c_INFO_TEXT = cInfoText;
	}


	public int get_iDECIMALNUMBERS() {
		return i_DECIMALNUMBERS;
	}


	public void set_iDECIMALNUMBERS(int iDecimalNumbers) {
		this.i_DECIMALNUMBERS = iDecimalNumbers;
	}



	public MyE2_String get_cFehlerInfo4User() {
		return c_FEHLERINFO4USER;
	}


	public void set_cFehlerInfo4User(MyE2_String c_FehlerInfo4User) {
		this.c_FEHLERINFO4USER = c_FehlerInfo4User;
	}

	public void 	_SET_NAVILIST_THIS_BELONGS_TO(E2_NavigationList oNAVI_LIST) throws myException {
		this.o_NAVILIST = oNAVI_LIST;
	}
	
	public E2_NavigationList _GET_NAVILIST_THIS_BELONGS_TO() throws myException {
		return this.o_NAVILIST;
	}


	public boolean _GET_ShowOnlyInPopup() {
		return b_ShowOnlyInPopup;
	}


	public void _SET_ShowOnlyInPopup(boolean bShowOnlyInPopup) {
		this.b_ShowOnlyInPopup = bShowOnlyInPopup;
	}

	
}
