package panter.gmbh.Echo2.components.DB;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.event.ActionListener;
import panter.gmbh.BasicInterfaces.IF_CreateStringFromRec;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.dataTools.RECORD2.RecList22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;

/**
 * erweitertes MyE2_DB_SelectField mit merkfunktion der zuletzt eingestellten werte (um eine zuruecksetzung auf den
 * vorgaengerwert zu ermoeglichen) - MEMORY-effekt
 * @author martin
 *
 */

public class MyE2_DB_SelectFieldV2 extends MyE2_DB_SelectField {

	//zuletzt gesetzter wert, wird entweder ueber die ueberschriebene methode setSelectedValue oder ueber einen ActionAgent gesetzt
	private String         lastSetValue = null;


	
	
	/**
	 * @param osqlField
	 * @param oDataToView
	 * @param oExt
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, dataToView oDataToView, Extent oExt) throws myException {
		super(osqlField, oDataToView, oExt);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param oDataToView
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, dataToView oDataToView) throws myException {
		super(osqlField, oDataToView);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param oExt
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, Extent oExt) throws myException {
		super(osqlField, oExt);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param cSQL_Query_For_LIST
	 * @param bThirdColumnIS_STANDARD_MARKER
	 * @param btranslate
	 * @param oExt
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER,boolean btranslate, Extent oExt) throws myException {
		super(osqlField, cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, btranslate, oExt);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param cSQL_Query_For_LIST
	 * @param bThirdColumnIS_STANDARD_MARKER
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, String cSQL_Query_For_LIST, boolean bThirdColumnIS_STANDARD_MARKER,boolean btranslate) throws myException {
		super(osqlField, cSQL_Query_For_LIST, bThirdColumnIS_STANDARD_MARKER, btranslate);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param aDefArray
	 * @param btranslate
	 * @param oExt
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, String[] aDefArray, boolean btranslate, Extent oExt) throws myException {
		super(osqlField, aDefArray, btranslate, oExt);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param aDefArray
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, String[] aDefArray, boolean btranslate) throws myException {
		super(osqlField, aDefArray, btranslate);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param aDefArray
	 * @param btranslate
	 * @param oExt
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, String[][] aDefArray, boolean btranslate, Extent oExt)	throws myException {
		super(osqlField, aDefArray, btranslate, oExt);
		this._init();
	}

	/**
	 * @param osqlField
	 * @param aDefArray
	 * @param btranslate
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField, String[][] aDefArray, boolean btranslate) throws myException {
		super(osqlField, aDefArray, btranslate);
		this._init();
	}

	/**
	 * @param osqlField
	 * @throws myException
	 */
	public MyE2_DB_SelectFieldV2(SQLField osqlField) throws myException {
		super(osqlField);
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
		if (this.get_oDataToView() == null) {
			throw new myExceptionCopy("MyE2_DB_SelectFieldV2:get_Copy: Error: SelectField not initialized !");
		}
		
		MyE2_DB_SelectFieldV2 oSelField = null;
		
		try
		{
			oSelField = new MyE2_DB_SelectFieldV2(this.EXT_DB().get_oSQLField(),this.get_oDataToView());
			oSelField.set_EXT_DB((MyE2EXT__DB_Component)this.EXT_DB().get_Copy(oSelField));
		}
		catch (myException ex)
		{
			throw new  myExceptionCopy("MyE2_DB_SelectFieldV2:get_Copy: Error: "+ex.get_ErrorMessage().get_cMessage().COrig());
		}
		
		oSelField.set_EXT((MyE2EXT__Component)((MyE2IF__Component)this).EXT().get_Copy(oSelField));
		oSelField.setStyle(this.getStyle());
		oSelField.set_oDataToView(this.get_oDataToView());
		oSelField.set_odataToViewShadow(this.get_odataToViewShadow());

		oSelField.setFont(this.getFont());
		
		
		//die vorigen agents loeschen, sonst wird der innere immer wiederholt
		
		Vector<XX_ActionAgent> vAgents = this.get_vActionAgents();
		oSelField.get_vActionAgents().clear();
		for (int i=0;i<vAgents.size();i++) {
			oSelField.add_oActionAgent((XX_ActionAgent)vAgents.get(i));
		}
		
		Vector<ActionListener> vActionListeners = this.get_vExternalActionListeners();
		for (int i=0;i<vActionListeners.size();i++) {
			oSelField.addActionListener((ActionListener)vActionListeners.get(i));
		}
		
		for (int i=0;i<this.get_vGlobalValidators().size();i++) {
			oSelField.add_GlobalValidator((XX_ActionValidator)this.get_vGlobalValidators().get(i));
		}
				
		
		for (int i=0;i<this.get_vIDValidators().size();i++) {
			oSelField.add_IDValidator((XX_ActionValidator)this.get_vIDValidators().get(i));
		}

		oSelField.setFocusTraversalParticipant(this.isFocusTraversalParticipant());
		
		oSelField.setWidth(this.getWidth());

		//2013-01-04: tooltip-automatik
		oSelField.set_bSetToolTipsToActiveListValue(this.get_bSetToolTipsToActiveListValue());
		
		oSelField.setBreak4PopupController(this.getBreak4PopupController());

		return oSelField;
	}


	public MyE2_DB_SelectFieldV2 _populateWith(RecList22 list, IF_Field fieldForDbValue, IF_Field fieldForSort,IF_CreateStringFromRec stringBuilderForDropDownText ) {
		String[][] arr = new String[list.size()+1][2];
		arr[0][0] = "-";
		arr[0][1] = "";
		
		VEK<Rec22> rSort = list.get_sorted_vector((e1,e2)-> {
			try {
				return e1.get_fs_dbVal(fieldForSort,"").compareTo( e2.get_fs_dbVal(fieldForSort,""));
			} catch (myException e) {
				return 0;
			}
		});
		
		int i=1;
		for (Rec22 r: rSort) {
			arr[i][0]=stringBuilderForDropDownText.createString(r);
			arr[i][1]=r.getFs(fieldForDbValue,"","");
			i++;
		}
		
		try {
			this.set_ListenInhalt(arr, false);
			this.set_ActiveValue("");
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return this;
	}
	
	
	
}
