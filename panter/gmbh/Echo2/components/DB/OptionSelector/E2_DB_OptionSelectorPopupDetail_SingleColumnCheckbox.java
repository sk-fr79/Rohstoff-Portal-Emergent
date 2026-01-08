package panter.gmbh.Echo2.components.DB.OptionSelector;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class E2_DB_OptionSelectorPopupDetail_SingleColumnCheckbox extends
		MyE2_Column implements IF_OptionSelector_PopupDetail {
	
	private HashMap<String, MyE2_CheckBox> 	m_CheckBoxes 	= new HashMap<String, MyE2_CheckBox>();
	private MyE2_Button						m_btnAn 		= new MyE2_Button(new MyString("Alle"));
	private MyE2_Button						m_btnAus 		= new MyE2_Button(new MyString("Keine"));
	private MyE2_Button						m_btnInvert		= new MyE2_Button(new MyString("Invertieren"));
	
	
	private Vector<String[]> 				m_ValueList = null;

	
	
	public E2_DB_OptionSelectorPopupDetail_SingleColumnCheckbox() {
		super();
		this.initDialog();
	}

	public E2_DB_OptionSelectorPopupDetail_SingleColumnCheckbox(
			MutableStyle oStyle) {
		super(oStyle);
		this.initDialog();
	}

	
	private void initDialog(){
		MyE2_Row oRow = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS_LEFT_CENTER());
		oRow.add(m_btnAn, E2_INSETS.I_0_10_10_10);
		oRow.add(m_btnAus, E2_INSETS.I_0_10_10_10);
		oRow.add(m_btnInvert, E2_INSETS.I_0_10_10_10);
		this.add(oRow);
		
		m_btnAn.add_oActionAgent(new XX_ActionAgent() {
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for ( MyE2_CheckBox elem : m_CheckBoxes.values() ) {
					elem.setSelected(true);
				}
			}
		});
		
		m_btnAus.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for ( MyE2_CheckBox elem : m_CheckBoxes.values() ) {
					elem.setSelected(false);
				}
			}
		});
		
		m_btnInvert.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				for ( MyE2_CheckBox elem : m_CheckBoxes.values() ) {
					elem.setSelected(!elem.isSelected());
				}
			}
		});
	}
	

	@Override
	public Vector<String> getSelectedValues() {
		Vector<String> vSelectedValues = new Vector<String>();
		for (int i=0; i < m_ValueList.size(); i++){
			if (m_CheckBoxes.get(m_ValueList.get(i)[0]).isSelected() ){
				vSelectedValues.add(m_ValueList.get(i)[0]);
			}
		}
		return vSelectedValues;
	}
	

	@Override
	public void setSelectedValues(Vector<String> vSelectedValues) {
		
		for (int i=0; i< vSelectedValues.size(); i++){
			if (m_CheckBoxes.containsKey(vSelectedValues.get(i))){
				m_CheckBoxes.get(vSelectedValues.get(i)).setSelected(true);
			}
		}
	}

	@Override
	public void setValueList(Vector<String[]> vAllValues) {
		m_ValueList = vAllValues;
		for (int i=0; i< vAllValues.size(); i++){
			MyE2_CheckBox cb = new MyE2_CheckBox(vAllValues.get(i)[2]);
			this.m_CheckBoxes.put(vAllValues.get(i)[0], cb);
			this.add(cb);
		}

	}

	

	@Override
	public void setEnabledForEdit(boolean bEnable) throws myException {
		m_btnAn.set_bEnabled_For_Edit(bEnable);
		m_btnAus.set_bEnabled_For_Edit(bEnable);
		m_btnInvert.set_bEnabled_For_Edit(bEnable);
		
		for ( MyE2_CheckBox elem : m_CheckBoxes.values() ) {
			elem.set_bEnabled_For_Edit(bEnable);
		}	
	}

}
