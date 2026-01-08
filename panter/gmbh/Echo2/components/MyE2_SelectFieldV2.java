package panter.gmbh.Echo2.components;


import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


/**
 * erweitertes MyE2_SelectField mit merkfunktion der zuletzt eingestellten werte (um eine zuruecksetzung auf den
 * vorgaengerwert zu ermoeglichen) - MEMORY-effekt
 * @author martin
 *
 */
public class MyE2_SelectFieldV2 extends MyE2_SelectField {

	//zuletzt gesetzter wert, wird entweder ueber die ueberschriebene methode setSelectedValue oder ueber einen ActionAgent gesetzt
	private String         lastSetValue = null;

	
	public MyE2_SelectFieldV2() {
		super();
		this._init();
	}

	/**
	 * @param oWidth
	 */
	public MyE2_SelectFieldV2(Extent oWidth) {
		super(oWidth);
		this._init();
	}

	/**
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(HashMap<String, String> aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		this._init();
	}

	/**
	 * @param hm_values
	 * @param bEmptyInFront
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(LinkedHashMap<String, MyE2_String> hm_values, boolean bEmptyInFront) throws myException {
		super(hm_values, bEmptyInFront);
		this._init();
	}

	/**
	 * @param hm_values
	 * @param bEmptyInFront
	 * @param bTranslate
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(LinkedHashMap<String, String> hm_values, boolean bEmptyInFront, boolean bTranslate) throws myException {
		super(hm_values, bEmptyInFront, bTranslate);
		this._init();
	}

	/**
	 * @param cSQL_Query_For_LIST
	 * @param bThirdColumnIS_STANDARD_MARKER
	 * @param bEmtyValueInFront
	 * @param bValuesFormated
	 * @param btranslate
	 * @param Width
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront, boolean bValuesFormated, boolean btranslate, Extent Width) throws myException {
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate, Width);
		this._init();
	}

	/**
	 * @param cSQL_Query_For_LIST
	 * @param bThirdColumnIS_STANDARD_MARKER
	 * @param bEmtyValueInFront
	 * @param bValuesFormated
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER,boolean bEmtyValueInFront, boolean bValuesFormated, boolean btranslate) throws myException {
		super(cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated, btranslate);
		this._init();
	}

	/**
	 * @param cSQL_Query_For_LIST
	 * @param cSQL_Query_4_Inaktiv
	 * @param bThirdColumnIS_STANDARD_MARKER
	 * @param bEmtyValueInFront
	 * @param bValuesFormated
	 * @param btranslate
	 * @param Width
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String cSQL_Query_For_LIST, String cSQL_Query_4_Inaktiv,boolean bThirdColumnIS_STANDARD_MARKER, boolean bEmtyValueInFront, boolean bValuesFormated,boolean btranslate, Extent Width) throws myException {
		super(cSQL_Query_For_LIST, cSQL_Query_4_Inaktiv, bThirdColumnIS_STANDARD_MARKER, bEmtyValueInFront, bValuesFormated,btranslate, Width);
		this._init();
	}

	/**
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @param oWidth
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String[] aDefArray, String cdefaultValue, boolean btranslate, Extent oWidth)	throws myException {
		super(aDefArray, cdefaultValue, btranslate, oWidth);
		this._init();
	}

	/**
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String[] aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		this._init();
	}

	/**
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @param oWidth
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String[][] aDefArray, String cdefaultValue, boolean btranslate, Extent oWidth) throws myException {
		super(aDefArray, cdefaultValue, btranslate, oWidth);
		this._init();
	}

	/**
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(String[][] aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		this._init();
	}

	/**
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_SelectFieldV2(Vector<String> aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		this._init();
	}


	
	public String getLastSetValue() {
		return lastSetValue;
	}


	public void setLastSetValue(String lastSetValue) {
		this.lastSetValue = lastSetValue;
	}


    private void _init() {
    	this._aaaInFront(()-> {
    		this.lastSetValue=this.get_ActualWert();
    		},true);     //jeder click merkt sich das letzte setting
    }

   
	@Override
	public void setSelectedIndex(int index) {
		super.setSelectedIndex(index);
		try {
			this.lastSetValue=this.get_ActualWert();
		} catch (myException e) {
			this.lastSetValue=null;
			e.printStackTrace();
		}
	}


	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		if (this.get_DataToView()== null) {
			throw new myExceptionCopy("MyE2_SelectField:get_Copy: Error: SelectField not initialized !");
		}
		
		
		MyE2_SelectField oSelField = new MyE2_SelectField();
		
		oSelField.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());
		
		
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		for (int i=0;i<vAgents.size();i++) {
			oSelField.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		}
		
		
		//2013-01-04: interne actionAgents
		Vector<XX_ActionAgent> vInternalAgents = this.get_vInternalActionAgents();
		for (int i=0;i<vInternalAgents.size();i++)
			oSelField.add_oInternalActionAgent((XX_ActionAgent)vInternalAgents.get(i));

		
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++)
			oSelField.addActionListener((ActionListener)vActionListeners.get(i));
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++) {
			oSelField.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
		}
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++) {
			oSelField.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));
		}

		
		oSelField.set_bSetToolTipsToActiveListValue(this.get_bSetToolTipsToActiveListValue());
		
		oSelField.setBreak4PopupController(this.getBreak4PopupController());
		
		return oSelField;
	}

	
}
