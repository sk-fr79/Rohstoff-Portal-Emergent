package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.GregorianCalendar;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class KFIX__BT_Set_Datum_Range extends E2_Button implements IF_RB_Component {

	private String datum_to_fill_von = "";
	private String datum_to_fill_bis = "";
	
	private IF_Field field_to_update = null;
	
	public KFIX__BT_Set_Datum_Range(int month_offset, IF_Field field_von, IF_Field field_bis) throws myException{
		super();
		this._fsa(-2)._backDDark()._width(50);
		
		String datum_for_txt_bt = "";
		GregorianCalendar calNow_Von = new myDateHelper(1,myDateHelper.getActualMonth(),myDateHelper.getActualYear()).get_oCalDate();
		GregorianCalendar calNow_Bis = new myDateHelper(myDateHelper.Find_Last_Day_OfMonth(calNow_Von).get(GregorianCalendar.DAY_OF_MONTH),myDateHelper.getActualMonth(),myDateHelper.getActualYear()).get_oCalDate();
		
		if(month_offset == 0){
			datum_to_fill_von = new myDateHelper(1,myDateHelper.getActualMonth(),myDateHelper.getActualYear()).get_cDateFormatForMask();
			datum_to_fill_bis = new myDateHelper(myDateHelper.Find_Last_Day_OfMonth(calNow_Von).get(GregorianCalendar.DAY_OF_MONTH),myDateHelper.getActualMonth(),myDateHelper.getActualYear()).get_cDateFormatForMask();
		}else{
			calNow_Von.add(GregorianCalendar.MONTH, month_offset);
			calNow_Bis.add(GregorianCalendar.MONTH, month_offset);
			datum_to_fill_von = new myDateHelper(calNow_Von).get_cDateFormatForMask();
			datum_to_fill_bis = new myDateHelper(calNow_Bis).get_cDateFormatForMask();
		}
		
		datum_for_txt_bt = datum_to_fill_von.substring(3);
		this
		._aaa(new ownActionAgent_double_field(field_von, field_bis))
		._t(datum_for_txt_bt)
		;
	}
	
	
	private class ownActionAgent_double_field extends XX_ActionAgent{
		
		private IF_Field fVon = null;
		private IF_Field fBis = null;
		
		public ownActionAgent_double_field(IF_Field field_von, IF_Field field_bis){
			this.fVon = field_von;
			this.fBis = field_bis;
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			KFIX__BT_Set_Datum_Range oThis = KFIX__BT_Set_Datum_Range.this;
			
			RB_ComponentMap oMap = oThis.rb_ComponentMap_this_belongsTo();
			Component compVon = oMap.getComp(fVon);
			Component compBis = oMap.getComp(fBis);
			
			if(compVon instanceof RB_date_selektor && compBis instanceof RB_date_selektor){
				((RB_date_selektor)compVon).rb_set_db_value_manual(oThis.datum_to_fill_von);
				((RB_date_selektor)compBis).rb_set_db_value_manual(oThis.datum_to_fill_bis);
			}else{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("<"+oThis.field_to_update.fieldName()+"> is not an instance of RB_date_selektor"));
			}
		}
	}
}
