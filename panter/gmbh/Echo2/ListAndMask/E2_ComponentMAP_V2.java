/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 17.06.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 17.06.2019
 *
 */
public class E2_ComponentMAP_V2 extends E2_ComponentMAP implements E2_ComponentMAP_IF_Rec21 {

	public E2_ComponentMAP_V2() {
		super();
	}

	public E2_ComponentMAP_V2(SQLFieldMAP sqlfieldMAP) {
		super(sqlfieldMAP);
	}

	
	//2014-04-04: neue version der copy-struktur mit statischer hilfsmethode
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
		E2_ComponentMAP_V2.Copy_FieldsAndSettings(this, oRueck);
		
		return oRueck;
	}

	@Override
	public void _DO_REFRESH_COMPONENTMAP(String cSTATUS_MASKE, boolean doMapSettingBefore, Boolean SetComponentsEnabled) throws myException {
		//hier die record-map leeren
		this.getHmRec21().clear();
		super._DO_REFRESH_COMPONENTMAP(cSTATUS_MASKE, doMapSettingBefore, SetComponentsEnabled);
	}
	
	
	
	
}
