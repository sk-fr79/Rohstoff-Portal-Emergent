package panter.gmbh.Echo2.__BASIC_MODULS.CODEGENERATOR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class CG__Column_RBRegistierung_assistant_tool extends MyE2_Column {

	private E2_Grid mainGrid 				= null;
	private E2_Grid fieldGrid 				= null;
	private E2_Grid subGrid 				= null;

	private RB_TextArea codeTextArea 		= null;
	private MyE2_SelectField table_selector = null;

	private E2_Button selectAll_bt  	= null;
	private E2_Button unSelectAll_bt  	= null;
	private E2_Button invertSelect_bt		= null;

	private HashMap<String, field_object> field_list = new HashMap<>();
	private HashMap<String, field_object> selected_field_list = new HashMap<>();
	private MyE2_CheckBox cb_standardfield;

	private _TAB  table = null;
	
	
	public CG__Column_RBRegistierung_assistant_tool() throws myException {
		super();

		this.mainGrid = new E2_Grid()	._s(4);
		this.subGrid = new E2_Grid()	._setSize(560,560);
		this.fieldGrid = new E2_Grid()	._setSize(20,300,225)._bo_ddd();

		this.cb_standardfield = new MyE2_CheckBox("Standard Felder anzeigen?", true, false);
		this.cb_standardfield.setStyle(MyE2_CheckBox.STYLE_NORMAL_NO_BORDER());
		this.cb_standardfield._aaa(new own_tableSelector_aa());

		this.codeTextArea = new RB_TextArea(550, 15);
		this.selectAll_bt = new E2_Button()._image(E2_ResourceIcon.get_RI("checkbox_quartett_check.png"))._aaa(new own_select_all_fields("ALL"));		
		this.unSelectAll_bt = new E2_Button()._image(E2_ResourceIcon.get_RI("checkbox_quartett_uncheck.png"))._aaa(new own_select_all_fields("NONE"));
		this.invertSelect_bt = new E2_Button()._image(E2_ResourceIcon.get_RI("checkbox_quartett_invert.png"))._aaa(new own_select_all_fields("INVERT"));

		_init_field_grid();

		E2_Button bt_generate_code	= 
				new E2_Button()
				._t("Generate code")
				._aaa(new own_generate_code_aa())
				._s_BorderText();

		E2_Button bt_generate_code_4_enum	= 
				new E2_Button()
				._t("Generate BG enum")
				._s_BorderText();
		bt_generate_code_4_enum._aaa(()->generate_v2_code());
		
		String cAbfrageTable = DB_META.get_TablesQuerySort_A_to_Z_NUR_JD_JT_TABLES(bibE2.cTO(),true);
		String[][] 	cTabellen = bibDB.EinzelAbfrageInArray(cAbfrageTable,false);

		this.table_selector = new MyE2_SelectField();
		this.table_selector._aaa(new own_tableSelector_aa());
		this.table_selector.set_ListenInhalt(cTabellen, false);

		this.mainGrid
		._a(this.table_selector, new RB_gld()._ins(2,2,10,2))
		._a(bt_generate_code, new RB_gld()._ins(10,2,10,2))
		._a(bt_generate_code_4_enum, new RB_gld()._ins(10,2,10,2))
		._a(this.cb_standardfield, new RB_gld()._ins(10,2,10,2));

		MyE2_ContainerEx fieldGridContainer =new MyE2_ContainerEx(fieldGrid);
		fieldGridContainer.setHeight(new Extent(480));

		this.subGrid._a(fieldGridContainer, new RB_gld()._ins(2)._left_top())._a(codeTextArea, new RB_gld()._ins(2)._left_top());

		this.add(mainGrid);
		this.add(subGrid);
	}

	/**
	 * @author sebastien
	 * @date 22.03.2019
	 *
	 * @return
	 * @throws myException 
	 */
	private void generate_v2_code() throws myException {
		StringBuffer generatedCode = new StringBuffer();
		String startLine = "";
		this.codeTextArea.setText("");

		ArrayList<String> keyList = new ArrayList<String>(selected_field_list.keySet());
		Collections.sort(keyList);
		if(selected_field_list.size()>0){
			for(String key: keyList){

				field_object attr = selected_field_list.get(key);

				generatedCode
				.append(attr.getField().fieldName().toUpperCase()+"(")
				.append("\""+attr.getField().fieldName().toLowerCase()+"\",")	
				.append(attr.getField()._t().baseTableName())
				.append(".")
				.append(attr.getField().fieldName().toLowerCase())	
				.append(",")
				.append(" ()->("+attr.getComponent_code().get_ActualView()+")")
				.append(",null),")
				.append("\n");

			}
		}
		this.codeTextArea.setText(generatedCode.toString());
	}

	public void _fill_grid() throws myException{
		this._init_field_grid();

		this.field_list.clear();
		this.selected_field_list.clear();

		String selected_table = table_selector.get_ActualWert();

		if(S.isFull(selected_table)){
			_TAB oSelectedTable = _TAB.find_Table(selected_table);

			ArrayList<IF_Field> field_list = new ArrayList<>(new Rec20(oSelectedTable).keySet());

			ArrayList<String> field_name_list = new ArrayList<>();
			for(IF_Field field: field_list){
				if(! this.cb_standardfield.isSelected()){
					if(isStandardFeldern(field)){
						continue;
					}	
				}
				field_name_list.add(field.fieldName());
			}

			Collections.sort(field_name_list);

			for(IF_Field field: field_list){
				component_select_field comp_sf = new component_select_field();
				this.field_list.put(field.fieldName(), new field_object(field, comp_sf));
			}

			for(String field_key: field_name_list){

				field_object field_obj = this.field_list.get(field_key);
				IF_Field field = field_obj.getField();
				component_select_field comp_sf = field_obj.getComponent_code();

				MyE2_CheckBox chkBox = new MyE2_CheckBox();
				chkBox._aaa(new own_select_field_aa());
				chkBox.EXT().set_O_PLACE_FOR_EVERYTHING(field);



				if(field.generate_MetaFieldDef().is_boolean_single_char()){
					comp_sf.set_ActiveValue(COMPONENT_ENUM.CHECKBOX.name());
				}else if(field.generate_MetaFieldDef().get_bIsDateType()){
					comp_sf.set_ActiveValue(COMPONENT_ENUM.DATEFIELD.name());
				}else if( isStandardFeldern(field) || field==this.table.key() ){
					comp_sf.set_ActiveValue(COMPONENT_ENUM.TEXT_ANZEIGE.name());
				}else if(field.fieldTextLength()>200){
					comp_sf.set_ActiveValue(COMPONENT_ENUM.TEXTAREA.name());
				}else{
					comp_sf.set_ActiveValue(COMPONENT_ENUM.TEXTFIELD.name());
				}

				RB_lab lbl_field = new RB_lab()._t(field.fieldName())._fsa(-2);

				this.fieldGrid
				._a(chkBox, new RB_gld()._ins(2)._al(E2_ALIGN.CENTER_MID))
				._a(lbl_field, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID))
				._a(comp_sf, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID));
			}


		}
	}

	public void _generate_code() throws myException{
		StringBuffer generatedCode = new StringBuffer();
		String startLine = "this.rb_register(";
		this.codeTextArea.setText("");

		ArrayList<String> keyList = new ArrayList<String>(selected_field_list.keySet());
		Collections.sort(keyList);
		if(selected_field_list.size()>0){
			for(String key: keyList){

				field_object attr = selected_field_list.get(key);

				generatedCode
				.append(startLine)
				.append(attr.getField()._t().baseTableName())
				.append(".")
				.append(attr.getField().fieldName().toLowerCase())	
				.append(",")
				.append(" "+attr.getComponent_code().get_ActualView()+");")
				.append("\n");

			}
		}
		this.codeTextArea.setText(generatedCode.toString());
	}

	private void _init_field_grid(){
		E2_Grid selection_bt_grid = new E2_Grid()._s(2)
				._a(this.unSelectAll_bt, new RB_gld()._ins(1)._al(E2_ALIGN.CENTER_MID))
				._a(this.selectAll_bt,new RB_gld()._ins(1)._al(E2_ALIGN.CENTER_MID))
				._a(this.invertSelect_bt,new RB_gld()._ins(1)._al(E2_ALIGN.CENTER_MID));

		this.fieldGrid._clear()
		._a(selection_bt_grid, new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._col(new E2_ColorDark()))
		._a("Field", new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._col(new E2_ColorDark()))
		._a("RB Component", new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_MID)._col(new E2_ColorDark()));
	}

	private void update_selected_field_list(MyE2_CheckBox oChkBox){

		IF_Field field_ = (IF_Field) oChkBox.EXT().get_O_PLACE_FOR_EVERYTHING(); 


		field_object fo = new field_object(field_, this.field_list.get(field_.fieldName()).getComponent_code());

		if(oChkBox.isSelected()){		
			this.selected_field_list.put(field_.fieldName(), fo);
		}else{
			this.selected_field_list.remove(field_.fieldName());
		}
	}

	private class own_generate_code_aa extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			_generate_code();
		}

	}

	private class own_select_all_fields extends XX_ActionAgent{
		private String status = "";

		public own_select_all_fields(String oAction){
			this.status = oAction;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CG__Column_RBRegistierung_assistant_tool oThis = CG__Column_RBRegistierung_assistant_tool.this;
			Component[] compList = oThis.fieldGrid.getComponents();
			for(Component comp : compList){
				if(comp instanceof MyE2_CheckBox){
					if(status.equals("ALL")){
						((MyE2_CheckBox) comp).setSelected(true);
					}else if(status.equals("NONE")){
						((MyE2_CheckBox) comp).setSelected(false);
					}else if(status.equals("INVERT")){
						((MyE2_CheckBox) comp).setSelected(!((MyE2_CheckBox) comp).isSelected());
					}

					update_selected_field_list((MyE2_CheckBox)comp);
				}
			}
		}

	}

	private class own_select_field_aa extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CG__Column_RBRegistierung_assistant_tool oThis = CG__Column_RBRegistierung_assistant_tool.this;
			MyE2_CheckBox oChkBox = (MyE2_CheckBox)oExecInfo.get_MyActionEvent().getSource();
			update_selected_field_list(oChkBox);
		}

	}

	private class own_tableSelector_aa extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			CG__Column_RBRegistierung_assistant_tool oThis = CG__Column_RBRegistierung_assistant_tool.this;

			String tableName =oThis.table_selector.get_ActualWert();
			oThis.table = _TAB.find_Table(tableName);
			
			oThis.field_list.clear();
			oThis.selected_field_list.clear();
			oThis.codeTextArea.setText("");
			oThis._fill_grid();
		}

	}


	private class component_select_field extends MyE2_SelectField{
		public component_select_field() throws myException {
			super();
			this.setWidth(new Extent(200));
			this.setFont(new E2_FontPlain(-2));
			String[][] component = {
					{COMPONENT_ENUM.TEXTFIELD.get_text_code(),COMPONENT_ENUM.TEXTFIELD.name()},
					{COMPONENT_ENUM.TEXTAREA.get_text_code(),COMPONENT_ENUM.TEXTAREA.name()},
					{COMPONENT_ENUM.CHECKBOX.get_text_code(),COMPONENT_ENUM.CHECKBOX.name()},
					{COMPONENT_ENUM.DATEFIELD.get_text_code(),COMPONENT_ENUM.DATEFIELD.name()},
					{COMPONENT_ENUM.TEXT_ANZEIGE.get_text_code(),COMPONENT_ENUM.TEXT_ANZEIGE.name()}
					
			};

			//bibALL.get_Array("RB_TextField(100)", "RB_CheckBox()", "RB_TextArea(400,5)")
			this.set_ListenInhalt(component, true);
			//	this.set_ActiveValue_OR_FirstValue("RB_TextField(100)");
		}
	}

	private static boolean isStandardFeldern(IF_Field field){
		boolean std_feld = field.fieldName().equals("ERZEUGT_AM") ||
		field.fieldName().equals("ERZEUGT_VON") ||
		field.fieldName().equals("GEAENDERT_VON") ||
		field.fieldName().equals("LETZTE_AENDERUNG") ||
		field.fieldName().equals("ID_MANDANT");
		
		return std_feld;
	}

	private class field_object{
		private IF_Field field;
		private component_select_field component_code;

		public field_object(IF_Field oField, component_select_field oComp) {
			this.field = oField;
			this.component_code = oComp;
		}

		public IF_Field getField() {
			return field;
		}

		public component_select_field getComponent_code() {
			return component_code;
		}
	}

	private enum COMPONENT_ENUM{

		TEXTFIELD("RB_TextField(100)"),
		TEXTAREA("RB_TextArea(400,5)"),
		CHECKBOX("RB_cb()"),
		DATEFIELD("RB_date_selektor(80, true)"),
		TEXT_ANZEIGE("RB_TextAnzeige()")
		;

		String text_code ="";

		private COMPONENT_ENUM(String text_code) {
			this.text_code=	text_code;
		}
		public String get_text_code() {
			return "new " + text_code;
		}
	}
}
