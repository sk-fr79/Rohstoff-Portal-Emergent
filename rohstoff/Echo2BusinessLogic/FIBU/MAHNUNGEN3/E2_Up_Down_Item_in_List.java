package rohstoff.Echo2BusinessLogic.FIBU.MAHNUNGEN3;

import java.util.Collections;
import java.util.Vector;

import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_Up_Down_Item_in_List extends MyE2_Grid {
	
	private MyE2_Button 	up_button;
	private MyE2_Button 	down_button;
	private RB_lab 			lbl;
	
	private String 			itemToSwap = "";


	private Vector<String> 	vect;

	
	
	public E2_Up_Down_Item_in_List(String item, Vector<String> item_list) throws myException {
		super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		itemToSwap = item;
		vect = item_list;
		
		up_button = new MyE2_Button(E2_ResourceIcon.get_RI("collect.png"), 		E2_ResourceIcon.get_RI("collect_disabled.png")		,	null, new upActionAgent());
		down_button = new MyE2_Button(E2_ResourceIcon.get_RI("uncollect.png"), 	E2_ResourceIcon.get_RI("uncollect_disabled.png")	,	null, new downActionAgent());
		lbl = new RB_lab(item)._i();
		
		
		this.add(up_button);
		this.add(lbl,1,2,E2_INSETS.I(2,0,2,0),E2_ALIGN.CENTER_MID);
		this.add(down_button);
		
		vect.indexOf(item_list);
		if(vect.size() == (vect.indexOf(item)+1)){
			down_button.set_bEnabled_For_Edit(false);
		}
		if(vect.indexOf(item)==0){
			up_button.set_bEnabled_For_Edit(false);
		}
		
	}
	
	public E2_Up_Down_Item_in_List(String id,	String text,  Vector<String> item_list) throws myException {
		super(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		itemToSwap = id;
		vect = item_list;
		
		up_button = new MyE2_Button(E2_ResourceIcon.get_RI("collect.png"), 		E2_ResourceIcon.get_RI("collect_disabled.png")		,	null, new upActionAgent());
		down_button = new MyE2_Button(E2_ResourceIcon.get_RI("uncollect.png"), 	E2_ResourceIcon.get_RI("uncollect_disabled.png")	,	null, new downActionAgent());
		lbl = new RB_lab(text)._i();
		
		
		this.add(up_button);
		this.add(lbl,1,2,E2_INSETS.I(2,0,2,0),E2_ALIGN.CENTER_MID);
		this.add(down_button);
		
		vect.indexOf(item_list);
		if(vect.size() == (vect.indexOf(id)+1)){
			down_button.set_bEnabled_For_Edit(false);
		}
		if(vect.indexOf(id)==0){
			up_button.set_bEnabled_For_Edit(false);
		}
		
	}
	
	public String getId() {
		return itemToSwap;
	}
	
	public E2_Up_Down_Item_in_List  _highlight(){
		this._bc(new E2_ColorLLight());
		return this;
	}
	
	public  E2_Up_Down_Item_in_List _f(Font f){
		this.lbl._f(f);
		return this;
	}
	public abstract void refreshListComponent() throws myException;
	
	@Override
	public void show_InputStatus(boolean bInputIsOK) {
		if(!bInputIsOK){
			this._bo_col(new E2_ColorAlarm());
		}
	}
	
	private class upActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int index = vect.indexOf(itemToSwap);
			Collections.swap(vect, index,index-1);
			refreshListComponent();
	
		}
		
	}
	
	private class downActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			int index = vect.indexOf(itemToSwap);
			Collections.swap(vect, index,index+1);
			refreshListComponent();

		}
		
	}
}
