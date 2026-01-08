package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import nextapp.echo2.app.Insets;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_addOnRecords;
import panter.gmbh.Echo2.ListAndMask.List.Export.EXP_popup_menue_exporter;
import panter.gmbh.Echo2.ListAndMask.List.Settings.E2_ButtonToSelectVisibleListColumns_and_other_settings;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.MODULE_REPORTLISTS.REP__POPUP_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_AH7_STEUERDATEI;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;


public class AH7_LIST_BedienPanel extends MyE2_Column {
	
	private AH7_LIST_Selector  oAH7_LIST_Selector = null;
	
	public AH7_LIST_BedienPanel(E2_NavigationList oNaviList) throws myException	{
		super(MyE2_Column.STYLE_NO_BORDER());
		
		this.oAH7_LIST_Selector = new AH7_LIST_Selector(oNaviList);
		
		Insets oInsets = new Insets(0,0,0,5);
		
		this.add(oAH7_LIST_Selector, oInsets);
		
		
		E2_Grid grid4Components = new E2_Grid();
		this.add(grid4Components, oInsets);
		
		grid4Components._a(new E2_ButtonToSelectVisibleListColumns_and_other_settings(oNaviList), new RB_gld()._left_mid()._ins(2,2,20,2));
		grid4Components._a(new AH7_LIST_bt_ListToMask(false,oNaviList), new RB_gld()._left_mid()._ins(2,2,2,2));		
		grid4Components._a(new AH7_LIST_bt_New(oNaviList), 				new RB_gld()._left_mid()._ins(2,2,2,2));
		grid4Components._a(new AH7_LIST_bt_Copy(oNaviList), 			new RB_gld()._left_mid()._ins(2,2,2,2));
		grid4Components._a(new AH7_LIST_bt_ListToMask(true,oNaviList), 	new RB_gld()._left_mid()._ins(2,2,2,2));		
		grid4Components._a(new AH7_LIST_bt_multiDelete(oNaviList), 		new RB_gld()._left_mid()._ins(2,2,2,2));		
		
		grid4Components._a(new ownExporter(oNaviList),					new RB_gld()._left_mid()._ins(10, 2, 2, 2));
		
		grid4Components._a(new REP__POPUP_Button(E2_MODULNAME_ENUM.MODUL.AH7_STEUERDATEI_LISTE.get_callKey(),oNaviList), new RB_gld()._left_mid()._ins(10, 2, 10, 2));

		
		grid4Components._a(new AH7_bt_BuildRelations(oNaviList), new RB_gld()._left_mid()._ins(2,2,10,2));
		grid4Components._a(new AH7_bt_ProfileAusListenEinordnen(oNaviList), new RB_gld()._left_mid()._ins(2,2,10,2));
		grid4Components._a(new AH7_bt_deleteEmptyRelations(oNaviList), new RB_gld()._left_mid()._ins(2,2,10,2));
		grid4Components._a(new AH7_bt_deleteInactiveRelations(oNaviList), new RB_gld()._left_mid()._ins(2,2,10,2));
		

		grid4Components._a(new AH7_LIST_DATASEARCH(oNaviList), new RB_gld()._left_mid()._ins(2,2,20,2));
		
		grid4Components._s(grid4Components.getComponentCount());
		
	}
	
	
	
	
	

	public AH7_LIST_Selector get_oAH7_LIST_Selector() 	{
		return oAH7_LIST_Selector;
	}
	
	
	
    private class ownExporter extends EXP_popup_menue_exporter {

    	/**
		 * @param p_navigationlist
		 */
		public ownExporter(E2_NavigationList p_navigationlist) {
			super(p_navigationlist);
			
			this.get_v_append_recs_4_csv_export().add(new EXP_Profil());
			this.get_v_append_recs_4_csv_export().add(new EXP_AGS());
			this.get_v_append_recs_4_csv_export().add(new EXP_AGZ());
			this.get_v_append_recs_4_csv_export().add(new EXP_AJS());
			this.get_v_append_recs_4_csv_export().add(new EXP_AJZ());
			this.get_v_append_recs_4_csv_export().add(new EXP_Kontrollblatt_veranlasser());
			this.get_v_append_recs_4_csv_export().add(new EXP_Kontrollblatt_importeur());
			this.get_v_append_recs_4_csv_export().add(new EXP_Kontrollblatt_abfallerzeuger());
			this.get_v_append_recs_4_csv_export().add(new EXP_Kontrollblatt_verwertungsanlage());
			this.get_v_append_recs_4_csv_export().add(new EXP_Abladeblatt_veranlasser());
			this.get_v_append_recs_4_csv_export().add(new EXP_Abladeblatt_importeur());
			this.get_v_append_recs_4_csv_export().add(new EXP_Abladeblatt_abfallerzeuger());
			this.get_v_append_recs_4_csv_export().add(new EXP_Abladeblatt_verwertungsanlage());
		}
    }

    
    
    private class EXP_Profil extends EXP_addOnRecords {
    	public EXP_Profil() {
    		super(_TAB.ah7_profil, new MyE2_String("AH7-Profil"),"PRF.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_AH7_PROFIL_id_ah7_profil();
    	}
    }

    
    private class EXP_AGS extends EXP_addOnRecords {
    	public EXP_AGS() {
    		super(_TAB.adresse, new MyE2_String("Adresse-Geo.Start"),"AGS.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_geo_start();
    	}
    }

    private class EXP_AGZ extends EXP_addOnRecords {
		public EXP_AGZ() {
			super(_TAB.adresse, new MyE2_String("Adresse-Geo.Ziel"),"AGZ.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_geo_ziel();
		}
	}
	
    private class EXP_AJS extends EXP_addOnRecords {
		public EXP_AJS() {
			super(_TAB.adresse, new MyE2_String("Adresse-Jur.Start"),"AJS.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_jur_start();
		}
	}
	
	private class EXP_AJZ extends EXP_addOnRecords {
		public EXP_AJZ() {
			super(_TAB.adresse, new MyE2_String("Adresse-Jur.Ziel"),"AJZ.");
		}
		@Override
		protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
			return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_adresse_jur_ziel();
		}
	}

	private class EXP_Kontrollblatt_veranlasser extends EXP_addOnRecords {
    	public EXP_Kontrollblatt_veranlasser() {
    		super(_TAB.adresse, new MyE2_String("Kontrollblatt.Veranlasser"),"KBV.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_1_verbr_veranlasser();
    	}
    }


	private class EXP_Kontrollblatt_importeur extends EXP_addOnRecords {
    	public EXP_Kontrollblatt_importeur() {
    		super(_TAB.adresse, new MyE2_String("Kontrollblatt.Importeur"),"KBI.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_1_import_empfaenger();
    	}
    }

	
	private class EXP_Kontrollblatt_abfallerzeuger extends EXP_addOnRecords {
    	public EXP_Kontrollblatt_abfallerzeuger() {
    		super(_TAB.adresse, new MyE2_String("Kontrollblatt.Erzeuger"),"KBE.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_1_abfallerzeuger();
    	}
    }

	
	private class EXP_Kontrollblatt_verwertungsanlage extends EXP_addOnRecords {
    	public EXP_Kontrollblatt_verwertungsanlage() {
    		super(_TAB.adresse, new MyE2_String("Kontrollblatt.Verwertung"),"KBVW.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_1_verwertungsanlage();
    	}
    }

	
	
	
	private class EXP_Abladeblatt_veranlasser extends EXP_addOnRecords {
    	public EXP_Abladeblatt_veranlasser() {
    		super(_TAB.adresse, new MyE2_String("Abladeblatt.Veranlasser"),"ABV.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_2_verbr_veranlasser();
    	}
    }


	private class EXP_Abladeblatt_importeur extends EXP_addOnRecords {
    	public EXP_Abladeblatt_importeur() {
    		super(_TAB.adresse, new MyE2_String("Abladeblatt.Importeur"),"ABI.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_2_import_empfaenger();
    	}
    }

	
	private class EXP_Abladeblatt_abfallerzeuger extends EXP_addOnRecords {
    	public EXP_Abladeblatt_abfallerzeuger() {
    		super(_TAB.adresse, new MyE2_String("Abladeblatt.Erzeuger"),"ABE.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_2_abfallerzeuger();
    	}
    }

	
	private class EXP_Abladeblatt_verwertungsanlage extends EXP_addOnRecords {
    	public EXP_Abladeblatt_verwertungsanlage() {
    		super(_TAB.adresse, new MyE2_String("Abladeblatt.Verwertung"),"ABVW.");
    	}
    	@Override
    	protected MyRECORD generate_instance_of_append_class(MyRECORD base_record_of_list) throws myException {
    		return ((RECORD_AH7_STEUERDATEI) base_record_of_list).get_UP_RECORD_ADRESSE_id_2_verwertungsanlage();
    	}
    }


}
