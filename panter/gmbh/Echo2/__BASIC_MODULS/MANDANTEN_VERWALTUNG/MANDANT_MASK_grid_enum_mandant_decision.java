package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_DECISION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MANDANT_DECISION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_DECISION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;


public class MANDANT_MASK_grid_enum_mandant_decision extends E2_Grid implements MyE2IF__DB_Component, E2_IF_Copy {

	private MyE2EXT__DB_Component oEXT_DB = null; 
	
	private Vector<ownCheckBox>   						v_cb = new Vector<>();
	private HashMap<ENUM_MANDANT_DECISION, Boolean>  	old_Status = new HashMap<>(); 
	private String 										id_mandant = null;
	
	private MyE2_ContainerEx 							containerExForDecisions = new MyE2_ContainerEx();
	private E2_Grid 									gridWithCheckboxes = new E2_Grid()._setSize(new Extent(98,Extent.PERCENT));
	
	//suche nach string realisieren
	private RB_TextField  tf = 			new RB_TextField()._sizeWH(200, 20);
	private E2_Button     btSearch = 	(E2_Button)new E2_Button()._image("suche.png", true)._aaa(()->{this.build_grid(tf.getText());});
	private E2_Button     btEraser = 	(E2_Button)new E2_Button()._image("eraser.png", true)._aaa(()->{tf.setText(""); this.build_grid("");});
//	private E2_Button     btSearch = 	(E2_Button)new E2_Button()._image("suche.png", true)._aaa(()->{this.test();});;

	public MANDANT_MASK_grid_enum_mandant_decision(SQLField field) {
		super();
		
		//jetzt alle enums durchgehen
		for (ENUM_MANDANT_DECISION en: ENUM_MANDANT_DECISION.values()) {
			v_cb.add(new ownCheckBox(en));
		}

		for (ownCheckBox cb: this.v_cb) {
			cb.set_no_status();
		}
		
		this.oEXT_DB = new MyE2EXT__DB_Component(this);
		this.oEXT_DB.set_oSQLField(field);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this.id_mandant = oResultMAP.get_cUNFormatedROW_ID();
		
		this.old_Status.clear();
		
		for (ownCheckBox cb: this.v_cb) {
			cb.set_actual_status(id_mandant);   //damit werden implizit fehlende enums dem mandanten auch geschrieben
			this.old_Status.put(cb.enum_dec, cb.isSelected()?new Boolean(true): new Boolean(false));
		}
		
		this.build_grid("");
	}

	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.old_Status.clear();
		this.id_mandant = null;
		
		for (ownCheckBox cb: this.v_cb) {
			cb.set_no_status();
		}
		
		this.build_grid("");
	}

	


	private void build_grid(String searchKey) {
		//int spaltengrenze=20;
		this._clear();
		this.containerExForDecisions.clear();
		this.gridWithCheckboxes._clear();
		
		this._setSize(new Extent(100, Extent.PERCENT))._bo_dd();
		
		E2_Grid ueberschrift= new E2_Grid()._setSize(200,200,30,30)
										._a(new RB_lab()._t(new MyE2_String("Weitere Systemschalter ..."))._b(),new RB_gld()._ins(0, 0, 10,0)._left_mid())
										._a(this.tf,new RB_gld()._ins(0, 0, 10,0)._left_mid())
										._a(this.btEraser,new RB_gld()._ins(0, 0, 10,0)._left_mid())
										._a(this.btSearch,new RB_gld()._ins(0, 0, 10,0)._left_mid())
										;
		
		this._a(ueberschrift,new RB_gld()._ins(4, 5, 2,5));
		
		this._a(this.containerExForDecisions);
		
		
		
		this.gridWithCheckboxes.setWidth(new Extent(98,Extent.PERCENT));
		this.containerExForDecisions.add(this.gridWithCheckboxes);
		if (this.v_cb.size()>0) {
			if (S.isEmpty(searchKey)) {
				for (ownCheckBox cb: this.v_cb) {
					this.gridWithCheckboxes._a(cb, new RB_gld()._ins(2,3,2,3));
				}
			} else { 
				for (ownCheckBox cb: this.v_cb) {
					if (cb.getText().toUpperCase().contains(searchKey.toUpperCase()) ) {
						this.gridWithCheckboxes._a(cb, new RB_gld()._ins(2,3,2,3));
					}
				}
			}
		}
	}
	
	
	
	@Override
	public String get_cActualMaskValue() throws myException {
		return null;
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		return null;
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException {
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex) {
	}

	@Override
	public boolean get_bIsComplexObject() {
		return true;
	}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		
		Vector<String> v_sql = new Vector<>();
		
		for (ownCheckBox cb: this.v_cb) {
			RECORDNEW_MANDANT_DECISION  md = new RECORDNEW_MANDANT_DECISION();
			md.set_NEW_VALUE_YES_NO(cb.isSelected()?"Y":"N");
			md.set_NEW_VALUE_ENUM_KEY(cb.enum_dec.get_ENUM_KEY_4_DB());
			md._add_sequencer()._add_timestamp()._add_user();
			md.add_raw_val(MANDANT_DECISION.id_mandant, "SEQ_MANDANT.CURRVAL");
			v_sql.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+md.get_RAW_SQL_4_SAVE());
		}
		
		return v_sql;
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		Vector<String> v_sql = new Vector<>();
		
		for (ownCheckBox cb: this.v_cb) {
			if (cb.is_changed(this.old_Status)) {
				MyDBToolBox  oDB = MyDBToolBox_FAB.get_myDBToolBox(false, false);
				String c_query = "SELECT "+MANDANT_DECISION.id_mandant_decision.fn()+
									" FROM "+bibE2.cTO()+"."+_TAB.mandant_decision.n()+
									" WHERE "+new And(MANDANT_DECISION.id_mandant,this.id_mandant).and(new vgl(MANDANT_DECISION.enum_key,cb.enum_dec.get_ENUM_KEY_4_DB())).s();
				String[][] arr_ids = oDB.EinzelAbfrageInArray(c_query);
				
				if (arr_ids !=null && arr_ids.length==1) {
					
					RECORD_MANDANT_DECISION rec = new RECORD_MANDANT_DECISION();
					rec.set_to_raw_state();
					rec.PrepareAndBuild("*", bibE2.cTO()+".JT_MANDANT_DECISION", "ID_MANDANT_DECISION="+arr_ids[0][0]);

					rec.set_NEW_VALUE_YES_NO(cb.isSelected()?"Y":"N");
					rec._add_timestamp()._add_user();
					v_sql.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+rec.get_RAW_SQL_4_SAVE());
				} else {
					throw new myException(this, "Error at sql: "+c_query);
				}
			}
		}
		
		return v_sql;
	}

	@Override
	public MyE2EXT__DB_Component EXT_DB() {
		return this.oEXT_DB;
	}

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oext_db) {
		this.oEXT_DB = oext_db;
	}

	
	private class ownCheckBox extends MyE2_CheckBox {
		
		public ENUM_MANDANT_DECISION  		enum_dec = 	null;

		public ownCheckBox(ENUM_MANDANT_DECISION p_enum_dec) {
			super();
			this.enum_dec = 	p_enum_dec;
			this.setText(new MyE2_String(this.enum_dec.get_mask_text()).CTrans());
			try {
				if (bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
					this.setToolTipText(new MyE2_String(this.enum_dec.get_toolTipText(),true," ... ("+this.enum_dec.get_ENUM_KEY_4_DB()+")",false).CTrans());
				} else {
					this.setToolTipText(new MyE2_String(this.enum_dec.get_toolTipText()).CTrans());
				}
			} catch (Exception e) {
				this.setToolTipText(new MyE2_String(this.enum_dec.get_toolTipText()).CTrans());
				e.printStackTrace();
			}
		}
		
		public void set_no_status() {
			this.setSelected(false);
		}
		
		public void set_actual_status(String id_mandant) throws myException {
			this.setSelected(this.enum_dec.is_YES(id_mandant));
		}
		
		public boolean is_changed(HashMap<ENUM_MANDANT_DECISION, Boolean>  	old_Status_map) throws myException {
			
			Boolean b_old = old_Status_map.get(this.enum_dec);
			
			if (b_old == null) {
				throw new myException(this, "unknown enum-value added !!");
			}
			
			if (b_old.booleanValue()==this.isSelected()) {
				return false;
			} else {
				return true;
			}
		}
	}


	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return null;
	}

	public MyE2_ContainerEx getContainerExForDecisions() {
		return containerExForDecisions;
	}
	
	
}
