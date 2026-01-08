package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.ORDER_POPUP.OP_BasicSortContainer;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.ORDER_POPUP.OP_IF_SortObject;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class AW2_SORT_object implements OP_IF_SortObject{

	private AW2_RECORD_REPORT  			recReport = 	null;
	private AW2_SORT_Container          sort_container = null; 
	private MyE2_Label                  label_in_list = null;
	
	
	public AW2_SORT_object(AW2_RECORD_REPORT p_recReport) throws myException {
		super();
		this.recReport = p_recReport;
		this.label_in_list = new MyE2_Label(this.recReport.get_TITEL_cUF_NN("--"));
	}

	@Override
	public Component get_SortRepresenationInList() throws myException {
		return this.label_in_list;
	}

	@Override
	public MyE2_Button generate_SortUpButton() throws myException {
		return new MyE2_Button(E2_ResourceIcon.get_RI("sort_up.png"),null, new ownActionSortUp());
	}

	@Override
	public MyE2_Button generate_SortDownButton() throws myException {
		return new MyE2_Button(E2_ResourceIcon.get_RI("sort_down.png"),null, new ownActionSortDown());
	}

	@Override
	public MyE2_MessageVector store_Sort_position() throws myException {

		MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
		
		int i_pos = this.find_pos_of_me_in_vector();
		
		if (i_pos>=0) {
			String c_pos = bibALL.makeDez(i_pos, false);
			c_pos = "0000000000".substring(0,10-c_pos.length())+c_pos;
			
			this.recReport.get_m2n().set_NEW_VALUE_SORT1(c_pos);
			mv_rueck.add_MESSAGE(this.recReport.get_m2n().UPDATE(true));
		}
		return mv_rueck;
	}

	@Override
	public void connect_with_sort_container(OP_BasicSortContainer  op_basic) throws myException {
		this.sort_container = (AW2_SORT_Container)op_basic;
	}
	
	
	private class ownActionSortUp extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AW2_SORT_object oThis = AW2_SORT_object.this;
			
			int i_actual_pos = oThis.find_pos_of_me_in_vector();
			
			Vector<OP_IF_SortObject>  v_sort = oThis.sort_container.get_v_sort_objects();
			AW2_SORT_object object_raus = null;
			if (i_actual_pos!=0) {
				object_raus = (AW2_SORT_object)v_sort.remove(i_actual_pos);
				v_sort.add(i_actual_pos-1,object_raus);
			}
			
			oThis.sort_container.fill_grid_4_sorting();
			oThis.sort_container.unmark_all();
			if (object_raus != null) {
				object_raus.mark_object();
			}
			
		}
	}
	

	private class ownActionSortDown extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AW2_SORT_object oThis = AW2_SORT_object.this;
			
			int i_actual_pos = oThis.find_pos_of_me_in_vector();
			
			Vector<OP_IF_SortObject>  v_sort = oThis.sort_container.get_v_sort_objects();
			AW2_SORT_object object_raus = null;
	
			if (i_actual_pos!=(v_sort.size()-1)) {
				object_raus = (AW2_SORT_object)v_sort.remove(i_actual_pos);
				v_sort.add(i_actual_pos+1,object_raus);
			}
			
			oThis.sort_container.fill_grid_4_sorting();
			oThis.sort_container.unmark_all();
			if (object_raus != null) {
				object_raus.mark_object();
			}

		}
	}

	
	
	
	private int find_pos_of_me_in_vector() throws myException {
		int i_pos =-1;
		
		Vector<OP_IF_SortObject>  v_sort = this.sort_container.get_v_sort_objects();

		for (int i=0;i<v_sort.size();i++) {
			if (v_sort.get(i)==this) {
				i_pos = i;
				break;
			}
		}
		
		return i_pos;
	}

	@Override
	public void mark_object() throws myException {
		this.label_in_list.setFont(new E2_FontBold());
		this.label_in_list.setLayoutData(MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),new E2_ColorLLight(),1,1));
	}

	@Override
	public void unmark_object() throws myException {
		this.label_in_list.setFont(new E2_FontPlain());
		this.label_in_list.setLayoutData(MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2),null,1,1));
	}
	

	
}
