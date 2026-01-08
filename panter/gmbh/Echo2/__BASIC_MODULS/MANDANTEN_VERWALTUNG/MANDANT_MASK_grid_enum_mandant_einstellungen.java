package panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import nextapp.echo2.app.text.TextComponent;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_PopUpHelp;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.ENUM_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_MANDANT_CONFIG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_CONFIG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;


public class MANDANT_MASK_grid_enum_mandant_einstellungen extends E2_Grid implements MyE2IF__DB_Component, E2_IF_Copy {

	private MyE2EXT__DB_Component oEXT_DB = null; 

	private Vector<MandantConfig>   					mandantConfigs = new Vector<>();
	private HashMap<ENUM_MANDANT_CONFIG, String>  		old_Status = new HashMap<>(); 
	private String 										id_mandant = null;
	
	
	private E2_Grid 		gridFilter; 
	private MyE2_TextField 	tfFilter = new MyE2_TextField("", 200, 200);
	private E2_Button     	btFilter = 	(E2_Button)new E2_Button()._image("suche.png", true)._aaa(()->{ this.build_grid(); });
	private E2_Button     	btClear = 	(E2_Button)new E2_Button()._image("eraser.png", true)._aaa(()->{tfFilter.setText(""); this.build_grid();});

	


	public MANDANT_MASK_grid_enum_mandant_einstellungen(SQLField field) throws myException {
		super();

		this._setSize(300,450, 16);
		
		// Filter einbauen
		 gridFilter= new E2_Grid()._setSize(100,200,30,30)
				._a(new RB_lab()._t(new MyE2_String("Systemeinstellungen..."))._b(),new RB_gld()._ins(0, 0, 10,20)._left_mid())
				._a(this.tfFilter,new RB_gld()._ins(0, 0, 10,20)._left_mid())
				._a(this.btClear,new RB_gld()._ins(0, 0, 10,20)._left_mid())
				._a(this.btFilter,new RB_gld()._ins(0, 0, 10,20)._left_mid())
				;
		
		
		
		//jetzt alle enums durchgehen
		for (ENUM_MANDANT_CONFIG en: ENUM_MANDANT_CONFIG.values()) {
			mandantConfigs.add(new MandantConfig(en));
		}
		

		for (MandantConfig setting: this.mandantConfigs) {
			setting.set_actual_value("");
		}

		this.oEXT_DB = new MyE2EXT__DB_Component(this);
		this.oEXT_DB.set_oSQLField(field);
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this.id_mandant = oResultMAP.get_cUNFormatedROW_ID();

		this.old_Status.clear();

		for (MandantConfig tf: this.mandantConfigs) {
			tf.set_actual_value(id_mandant);   //damit werden implizit fehlende enums dem mandanten auch geschrieben
			
			this.old_Status.put(tf.enum_mandant_config, tf.get__actual_maskstring_in_view());
		}

		this.build_grid();
	}


	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.old_Status.clear();
		this.id_mandant = null;

		for (MandantConfig tf: this.mandantConfigs) {
			tf.set_actual_value(id_mandant);
		}

		this.build_grid();
	}


	private void build_grid() {
		this._clear();
		
		this._a(gridFilter,new RB_gld()._span(3));
		
		// sortierte Konfig-Einträge
		HashMap<Integer, Vector<MandantConfig>> hmSortedEntries = new HashMap<Integer, Vector<MandantConfig>>();

		List<MandantConfig> lResult;
		
		// Filtern der Settings
		String sFilter = tfFilter.getText();
		if (!bibALL.isEmpty(sFilter)) {

			// Liste filtern
			Vector<MandantConfig> vec = mandantConfigs;
			lResult = 
					(List<MandantConfig>) vec	.stream()
					 	.filter(x->x.enum_mandant_config.get_mask_text().toUpperCase().contains(sFilter.toUpperCase())) 
					 	.collect(Collectors.toList())
					;
		} else {
			// ganze Liste
			Vector<MandantConfig> vec = mandantConfigs;
			lResult =vec.stream().collect(Collectors.toList())	;
		}
		
		
		
		// NUR FÜR DIE DARSTELLUNG: füllt eine Hashmap von Vektoren, mit dem Key als Sortierung...
		// sortiert die Elemente von mandantConfigs in eine Sortierte Liste ein...
		for (MandantConfig mc: lResult) {
			if (hmSortedEntries.containsKey(mc.getEnum_mandant_config().get_configGroupSort())) {
				hmSortedEntries.get(mc.getEnum_mandant_config().get_configGroupSort()).add(mc);
			} else {
				Vector<MandantConfig> v = new Vector<MandantConfig>();
				v.add(mc);
				hmSortedEntries.put(mc.getEnum_mandant_config().get_configGroupSort(),v);
			}
		}
		//
		
		
		// nach Überschriften sortieren...
		hmSortedEntries.entrySet()
		.stream()
		.sorted(Map.Entry.comparingByKey())
		.forEach((a)->{
			Integer sort =  a.getKey();
			Vector<MandantConfig> v = a.getValue();
			
			if (v.size() > 0) {
				MandantConfig m = v.get(0);
				String sHeading = m.enum_mandant_config.get_configGroup();
				this._a(new RB_lab(sHeading)._b()._fo_s_plus(0),	new RB_gld()._ins(2, 1, 2, 5)._span(3)._left_top());
			}
			
			for (MandantConfig tf: v) {
				this
				._a(tf.label, 			new RB_gld()._ins(2, 1, 2, 1)._left_top())
				._a(tf.getTextField(), 	new RB_gld()._ins(2, 1, 2, 1)._left_mid())
				._a(tf.get_help_bt(), 	new RB_gld()._ins(2, 1, 2, 1)._left_top());
			}
			}
		);
		
		
			
//		
//		
//		if (this.mandantConfigs.size()>0) {
//			for (MandantConfig tf: this.mandantConfigs) {
//				this
//				._a(tf.label, 			new RB_gld()._ins(2, 1, 2, 1)._left_top())
//				._a(tf.getTextField(), 	new RB_gld()._ins(2, 1, 2, 1)._left_mid())
//				._a(tf.get_help_bt(), 	new RB_gld()._ins(2, 1, 2, 1)._left_top());
//			}
//		}
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

		for (MandantConfig cb: this.mandantConfigs) {

			RECORDNEW_MANDANT_CONFIG  md = new RECORDNEW_MANDANT_CONFIG();
			md.set_NEW_VALUE_WERT(cb.tf.get__actual_maskstring_in_view());
			md.set_NEW_VALUE_ENUM_KEY(cb.enum_mandant_config.get_ENUM_KEY_4_DB());
			md._add_sequencer()._add_timestamp()._add_user();
			md.add_raw_val(MANDANT_CONFIG.id_mandant, "SEQ_MANDANT.CURRVAL");
			v_sql.add(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS+md.get_RAW_SQL_4_SAVE());
		}

		return v_sql;
	}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap) throws myException {
		Vector<String> v_sql = new Vector<>();

		for (MandantConfig otf: this.mandantConfigs) {
			if (otf.is_changed(this.old_Status)) {
				MyDBToolBox  oDB = MyDBToolBox_FAB.get_myDBToolBox(false, false);

				String c_query = new SEL(MANDANT_CONFIG.id_mandant_config)
						.FROM(_TAB.mandant_config)
						.WHERE(new And(MANDANT_CONFIG.id_mandant,this.id_mandant))
						.AND(new vgl(MANDANT_CONFIG.enum_key,otf.enum_mandant_config.get_ENUM_KEY_4_DB())).s();

				String[][] arr_ids = oDB.EinzelAbfrageInArray(c_query);

				if (arr_ids !=null && arr_ids.length==1) {


					RECORD_MANDANT_CONFIG rec = new RECORD_MANDANT_CONFIG();
					rec.set_to_raw_state();
					rec.PrepareAndBuild("*", bibE2.cTO()+".JT_MANDANT_CONFIG", "ID_MANDANT_CONFIG="+arr_ids[0][0]);

					if (otf.tf != null){
						rec.set_NEW_VALUE_WERT(otf.tf.get__actual_maskstring_in_view());
					} else if (otf.ta != null){
						rec.set_NEW_VALUE_WERT(otf.ta.get__actual_maskstring_in_view());
					}
					
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


	public class MandantConfig {


		private 	ENUM_MANDANT_CONFIG 	enum_mandant_config = 	null;
		private  	RB_TextField 			tf = 		null;
		private  	RB_TextArea 			ta = 		null;
		private 	RB_lab                  label = 	null;
		private 	MyE2_PopUpHelp			help_bt = 	null;

		public MandantConfig(ENUM_MANDANT_CONFIG p_enum_dec) {
			super();
			
			this.enum_mandant_config = 	p_enum_dec;
			this.tf = null;
			this.ta = null;
			
		    Vector<String> v=	bibALL.TrenneZeile(S.NN(this.enum_mandant_config.get_helptext()), "\n");
		    VEK<MyString>  vms = new VEK<>();
		    v.stream().forEach((s)->{vms._a(S.ms(s));});
			
			this.help_bt = new MyE2_PopUpHelp(new VEK<MyString>()._a(vms));
			
			if (this.enum_mandant_config.get_max_rows()==1) {
				this.tf = new RB_TextField(this.enum_mandant_config.get_field_length(), this.enum_mandant_config.get_max_length())._small()._bord_dd();
			} else {
				this.ta = new RB_TextArea(this.enum_mandant_config.get_field_length(), this.enum_mandant_config.get_max_rows(), this.enum_mandant_config.get_max_length())._small()._bord_dd();
			}
			this.label = 		new RB_lab()._t(enum_mandant_config.get_mask_text())._fsa(-2)._lw();

		}


		public void set_actual_value(String id_mandant) throws myException {
			try {
				if(S.isFull(id_mandant)) {
					String value = this.enum_mandant_config.getUncheckedValue(id_mandant);
					if (this.tf!=null) {
						this.tf.rb_set_db_value_manual(value);
					} else {
						this.ta.rb_set_db_value_manual(value);
					}
				}
			} catch (myException e) {
				bibMSG.MV()._addWarn("Fehler auf dem Reiter:  <Einstellungen>: "+e.ErrorMessage);
				e.printStackTrace();
			}
		}

		public boolean is_changed(HashMap<ENUM_MANDANT_CONFIG, String>  old_Status_map) throws myException {

			String b_old = old_Status_map.get(this.enum_mandant_config);

			if (b_old == null) {
				throw new myException(this, "unknown enum-value added !!");
			}

			String compareVal = this.getTextField().getText();
			
			if (b_old.equals(compareVal)) {
				return false;
			} else {
				return true;
			}
		}
		
		public TextComponent getTextField() {
			if (this.ta== null) {
				return this.tf;
			}
			return this.ta;
		}
		
		public MyE2_PopUpHelp get_help_bt() {
			return help_bt;
		}
		
		public String get__actual_maskstring_in_view() throws myException {
			if (this.ta== null) {
				return this.tf.get__actual_maskstring_in_view();
			}
			return this.ta.get__actual_maskstring_in_view();
		}


		public ENUM_MANDANT_CONFIG getEnum_mandant_config() {
			return enum_mandant_config;
		}
	}




	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return null;
	}

	public Vector<MandantConfig> getMandantConfigs() {
		return mandantConfigs;
	}


}
