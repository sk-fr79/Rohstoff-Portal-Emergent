package panter.gmbh.Echo2.ListAndMask.List.Export;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.components.LeftRightSelect.LR_CB;
import panter.gmbh.Echo2.components.LeftRightSelect.LR_Container;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.myTempFileAutoDel;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class EXP_bt_export_CSV_pre_selections_popupWindow extends E2_BasicModuleContainer {
	
	private E2_NavigationList 			navigationlist = null;
	
	//hier koennen weitere record-klassen mit einer beziehungsvorschrift hinterlegt werden
	private Vector<EXP_addOnRecords>  	v_append_recs = new Vector<>();
	
	//hier werden felder hinterlegt, die nicht exportiert werden duerfen
	private Vector<IF_Field>   			v_fields_not_to_export = new Vector<>();

	private _TAB  						base_tab = null;
	private own_container               lr_container = new  own_container();
	
	private myTempFileAutoDel           TempFile = null;
	
	public EXP_bt_export_CSV_pre_selections_popupWindow(	_TAB 						p_base_tab, 
															E2_NavigationList 			p_navigationlist, 	
															Vector<EXP_addOnRecords> 	p_append_recs,
															Vector<IF_Field>  			p_field_not_2_export
															) throws myException {
		super();
		
		this.set_bVisible_Row_For_Messages(false);
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_4_POPUP_MESSAGES());
		
		this.TempFile = new myTempFileAutoDel("export", ".txt", true);
		
		
		this.base_tab = p_base_tab;
		this.navigationlist = p_navigationlist;
		this.v_append_recs = p_append_recs;
		
		this.v_fields_not_to_export.addAll(p_field_not_2_export);
		
		IF_Field[] fields =this.base_tab.get_fields();
		
		for (IF_Field  field: fields) {
			LR_CB<EXP_Field> cb = new LR_CB<EXP_Field>(new EXP_Field(field, null),field.fn(),this.lr_container,field.fn(),false);
			if (!this.v_fields_not_to_export.contains(field)) {
				this.lr_container.get_v_cb_left().add(cb);
			}
		}
		
		for (EXP_addOnRecords add_on: this.v_append_recs) {

			IF_Field[] fields_add = add_on.get_appendedTab().get_fields();
			
			for (IF_Field field_add: fields_add) {
				LR_CB<EXP_Field> cb = new LR_CB<EXP_Field>(	new EXP_Field(field_add, add_on),
										add_on.get_infoText4FieldList().CTrans()+":"+field_add.fn(),
										this.lr_container, 
										add_on.get_add_tab_preFix4Export()+"."+field_add.fn(),
										false);
				if (!this.v_fields_not_to_export.contains(field_add)) {
					this.lr_container.get_v_cb_left().add(cb);
				}
			}
		}
		
		//jetzt auswahl aus dem speicher laden
		this.lr_container.get_v_cb_right().addAll(
				new EXP_UserSettingFieldList4Export(this).restore_vektor_4_export(this.lr_container.get_v_cb_left()));
		
		//aufbauen
		this.lr_container.refresh_left();
		this.lr_container.refresh_right();
		
		this.add(this.lr_container,MyE2_Column.LAYOUT_CENTER(E2_INSETS.I(2,2,2,2)));
		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(880), new Extent(660),new MyE2_String("Bitte wählen Sie die Exportspalten"));
	}

	private class ownActionClose extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			EXP_bt_export_CSV_pre_selections_popupWindow.this.CLOSE_AND_DESTROY_POPUPWINDOW(true);
		}
	}

	private class ownActionSaveSelected extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if  (EXP_bt_export_CSV_pre_selections_popupWindow.this.lr_container.get_cb_save_settings().isSelected()) {
				new EXP_UserSettingFieldList4Export(EXP_bt_export_CSV_pre_selections_popupWindow.this)
							.save_vektor_4_export(EXP_bt_export_CSV_pre_selections_popupWindow.this.lr_container.get_v_cb_right());
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Die Feldauswahl wurde für den nächsten Aufruf gespeichert !")));
				
			}
		
		}
	}
	
	private class ownActionStartExport  extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			EXP_bt_export_CSV_pre_selections_popupWindow oThis = EXP_bt_export_CSV_pre_selections_popupWindow.this;
			
			Vector<EXP_Field>  v_fields = new Vector<>();
			
			for (LR_CB<EXP_Field> cb: oThis.lr_container.get_v_cb_right()) {
				v_fields.add((EXP_Field)cb.get_my_object());
			}
			
			new EXP_exporter_csv_to_file( 	oThis.navigationlist, 
											oThis.TempFile, 
											oThis.base_tab, 
											v_fields).do_export();
			
		}
	}
	
	public String generate_module_key_4_save_settings() {
		String c_rueck = this.base_tab.fullTableName()+"|";
		for (EXP_addOnRecords append: this.v_append_recs) {
			c_rueck=c_rueck+(append.get_add_tab_preFix4Export()+"|"+append.get_appendedTab().fullTableName());
		}
		return c_rueck;
	}

	
	
	private class own_container extends LR_Container<EXP_Field> {

		public own_container() throws myException {
			super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100(), 400);
		}

		@Override
		public void add_content_before_left_right_panel() throws myException {
			this.add(new MyE2_Label(new MyE2_String("Bitte wählen Sie die gewünschten Spalten aus ...")),2,E2_INSETS.I(2,10,2,10));
			
		}

		@Override
		public void add_content_after_left_right_panel() throws myException {
			
			
			MyE2_Button bt_start = new MyE2_Button(new MyE2_String("Starte CSV-Export"),null,new ownActionClose());
			bt_start.add_oActionAgent(new ownActionStartExport());
			bt_start.add_oActionAgent(new ownActionSaveSelected());
			bt_start.add_oActionAgent(new ownActionClose());
			
			MyE2_Button bt_close = new MyE2_Button(new MyE2_String("Abbruch"),null,new ownActionClose());
			
			int[] i_breite = {150,150,150};
			E2_Grid4MaskSimple grid = new E2_Grid4MaskSimple()
					.def_(E2_INSETS.I(0,0,10,0))
					.add_(bt_start)
					.add_(bt_close)
					.def_(E2_INSETS.I(0,0,50,0))
					.add_(this.get_cb_save_settings())
					.setSize_(i_breite);
			
			this.add(grid,2, E2_INSETS.I(3,10,3,3));
		}
		
	}
	
}
