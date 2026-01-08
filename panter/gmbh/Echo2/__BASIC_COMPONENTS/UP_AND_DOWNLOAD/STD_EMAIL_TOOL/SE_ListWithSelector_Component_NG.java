package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.STD_EMAIL_TOOL;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class SE_ListWithSelector_Component_NG extends MyE2_Grid  {

	private HashMap<String, MyE2_CheckBox> chkBoxList;

	private boolean isChecked = false;

	private boolean multipleSelectionAllowed = true;

	private Vector<String> itemList;
	
	private int maxSelection = 5;
	
	public SE_ListWithSelector_Component_NG() {
		super(1);
		this.__setBasic();
		this.chkBoxList.clear();
		

		fillList();
	}

	public SE_ListWithSelector_Component_NG(boolean bMultipleSelection) {
		super(1);
		this.__setBasic();
		this.chkBoxList.clear();
		this.multipleSelectionAllowed = bMultipleSelection;
		

		fillList();
	}
	
	public SE_ListWithSelector_Component_NG(int iMaxItemSelection) {
		super(1);
		this.__setBasic();
		this.chkBoxList.clear();
		
		fillList();
	}
	
	public Vector<String> getItemList() {
		return itemList;
	}

	public void setItemList(Vector<String> itemList) {
		this.itemList = itemList;
	}

	public int getMaxSelection() {
		return maxSelection;
	}

	public void setMaxSelection(int maxSelection) {
		this.maxSelection = maxSelection;
	}

	public SE_ListWithSelector_Component_NG( Vector<String> vItemList) throws myException {
		super(1);
		this.__setBasic();
		this.itemList = vItemList;
		
		fillList();
	}
	
	public SE_ListWithSelector_Component_NG( Vector<String> vItemList, int iMaxItemSelection) throws myException {
		super(1);
		this.__setBasic();
		this.itemList = vItemList;
		
		fillList();
	}
	
	public SE_ListWithSelector_Component_NG( Vector<String> vItemList, boolean bMultipleSelection) throws myException {
		super(1);
		this.__setBasic();
		this.multipleSelectionAllowed = bMultipleSelection;
		this.itemList = vItemList;
		
		fillList();
	}
	
	public SE_ListWithSelector_Component_NG( Vector<String> vItemList, boolean bMultipleSelection, int iMaxItemSelection) throws myException {
		super(1);
		this.__setBasic();
		this.multipleSelectionAllowed = bMultipleSelection;
		this.itemList = vItemList;
		
		fillList();
	}
	
	private void __setBasic(){		
		chkBoxList = new HashMap<>();
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.setLayoutData(MyE2_Grid.LAYOUT_LEFT_TOP(E2_INSETS.I_0_0_0_0));
	}

	public SE_ListWithSelector_Component_NG fillList(){
		chkBoxList.clear();
		MyE2_Grid subGrid=new MyE2_Grid(1);
		subGrid.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		for(String item: itemList){
			MyE2_CheckBox chkBox = new MyE2_CheckBox();
			chkBox.setText(item);
			chkBox.setSelected(false);
			if(isMultipleSelectionAllowed()){
				chkBox.add_oActionAgent(new ownCheckboxActionAgent_multiple());
			}else{
				chkBox.add_oActionAgent(new ownCheckboxActionAgent_single());
			}
			chkBoxList.put(item, chkBox);
			subGrid.add(chkBox);
		}

		MyE2_ContainerEx  container = new MyE2_ContainerEx();
		container.add(subGrid);
		this.add(container);
		return this;
	}





	public boolean updateCheckedContact(List<String> ckeckedContactList){
		boolean bCheckedItem =false;
		for(String checkedContact :  ckeckedContactList){
			if(! (S.isEmpty(checkedContact) ) ){
				MyE2_CheckBox chk = chkBoxList.get(checkedContact);
				if(!(chk== null)){
					chk.setSelected(true);
					bCheckedItem = true;
				}

			}
		}
		return bCheckedItem;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	public boolean isMultipleSelectionAllowed() {
		return multipleSelectionAllowed;
	}

	public void setMultipleSelectionAllowed(boolean multipleSelectionAllowed) {
		this.multipleSelectionAllowed = multipleSelectionAllowed;
	}

	private class ownCheckboxActionAgent_multiple extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			isChecked = false;

			for(String key : chkBoxList.keySet()){

				if(chkBoxList.get(key).isSelected()){
					setChecked(true);
					break;
				}
			}
		}
	}

	private class ownCheckboxActionAgent_single extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_CheckBox oCB = (MyE2_CheckBox)bibE2.get_LAST_ACTIONEVENT().getSource();
			isChecked = false;

			for (String key : chkBoxList.keySet())
			{
				MyE2_CheckBox oCB1 = (MyE2_CheckBox)chkBoxList.get(key);

				if (oCB!=oCB1)
				{
					oCB1.setSelected(false);

				}
				else
				{
					setChecked(oCB.isSelected());

				}
			}


		}
	}
}

