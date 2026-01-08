package _Test.Test_SEBASTIEN;

import java.util.HashMap;

import echopointng.Separator;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.ENUM_UMBUCHUNGSDEF;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_dataObjectsCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.WE_CM__Collector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE._WE_Umbuchung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_atom;

public class Test_Umbuchung_Sebastien extends MyE2_Button{

	private WE_CM__Collector cmCollector =  null;

	public Test_Umbuchung_Sebastien(WE_CM__Collector componentMap_collector) {
		super("Umbuchen (Nur fur test)");

		this.cmCollector = componentMap_collector;

		this._aaa(new ownActionAGent());
	}	

	private class ownActionAGent extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			((FZ_dataObjectsCollector)cmCollector.rb_Actual_DataobjectCollector()).get_master_key();
			make_umbuchung();
		}
	}



	private void make_umbuchung()throws myException{
		MyE2_MessageVector	mv = new MyE2_MessageVector();

		_WE_Umbuchung umbuchung_class = new _WE_Umbuchung((RB_DataobjectsCollector_V2)cmCollector.rb_Actual_DataobjectCollector(), mv);
		umbuchung_class.umbuchen(mv);
		umbuchung_class.SAVE(false, mv);
		popupcontainer popup = new popupcontainer(umbuchung_class);
		popup.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1600), new Extent(800), new MyE2_String("Umbuchung"));
	}

	private class popupcontainer extends E2_BasicModuleContainer{

		public popupcontainer(_WE_Umbuchung umb) throws myException {
			super();

			E2_Grid grd = new E2_Grid()._s(1)._gld(new RB_gld()._ins(2));
			for(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> umb_hm: umb.get_alle_umbunchung_hm()){
				grd._a(build_representation(umb_hm));
			}
			this.add(grd);
		}
	}

	private E2_Grid build_representation(HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hashMap)throws myException{

		E2_Grid umb_grid = new E2_Grid()._setSize(200,200,200,200,50,200,200,200,200)._bo_ddd();

		generate_atom_grid(umb_grid, hashMap, 
				BEWEGUNG_ATOM.id_artikel, 
				BEWEGUNG_ATOM.menge, 
				BEWEGUNG_ATOM.e_preis, 
				BEWEGUNG_ATOM.posnr);
		
		umb_grid._a(new Separator(), new RB_gld()._span(umb_grid.getSize())._ins(5));

		generate_umb_atom_grid(umb_grid, hashMap, 
				BEWEGUNG_ATOM.id_bewegung,
				BEWEGUNG_ATOM.id_bewegung_vektor,
				BEWEGUNG_ATOM.id_bewegung_vektor_pos,
				BEWEGUNG_ATOM.id_artikel, 
				BEWEGUNG_ATOM.menge, 
				BEWEGUNG_ATOM.e_preis, 
				BEWEGUNG_ATOM.posnr,
				BEWEGUNG_ATOM.id_bewegungstation_logi_start,
				BEWEGUNG_ATOM.id_bewegungstation_logi_ziel
				);
		
		umb_grid._a("", new RB_gld()._span(umb_grid.getSize())._ins(5));

		generate_station_grid(umb_grid, hashMap 
				,BEWEGUNG_STATION.id_adresse
				,BEWEGUNG_STATION.id_adresse_basis
				,BEWEGUNG_STATION.id_adresse_besitzer
				,BEWEGUNG_STATION.id_bewegung_atom);

		return umb_grid;
	}

	private void generate_atom_grid(E2_Grid grd, HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hashMap, IF_Field... fieldlist) throws myException{
		grd
		._a(new RB_lab("ATOM LINKS")._b(),	new RB_gld()._span(4)._col(new E2_ColorDDark())._ins(2)._center_mid())
		._a()
		._a(new RB_lab("ATOM RECHT")._b(),	new RB_gld()._span(4)._col(new E2_ColorDDark())._ins(2)._center_mid())
		;

		Rec20_atom atom_links = new Rec20_atom(hashMap.get(ENUM_UMBUCHUNGSDEF.atomstart));
		Rec20_atom atom_rechts = new Rec20_atom(hashMap.get(ENUM_UMBUCHUNGSDEF.atomziel));

		for(IF_Field field: fieldlist){
			grd
			._a(field.fieldName(),							new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			._a(atom_links.get_fs_lastVal(field, "-"),		new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			._a()
			._a(field.fieldName(), 							new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			._a(atom_rechts.get_fs_lastVal(field, "-"), 	new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			;

		}
	}
	
	private void generate_umb_atom_grid(E2_Grid grd, HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hashMap, IF_Field... fieldlist) throws myException{
		grd
		._a(new RB_lab("ATOM UMBUCHUNG LINKS")._b(),	new RB_gld()._span(4)._col(new E2_ColorDDark())._ins(2)._center_mid())
		._a()
		._a(new RB_lab("ATOM UMBUCHUNG RECHTS")._b(),	new RB_gld()._span(4)._col(new E2_ColorDDark())._ins(2)._center_mid())
		;

		Rec20_atom atom_umb_links = new Rec20_atom(hashMap.get(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung));
		Rec20_atom atom_umb_rechts =new Rec20_atom(hashMap.get(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel));

		for(IF_Field field: fieldlist){
			grd
			._a(field.fieldName(),							new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			._a(atom_umb_links.get_fs_lastVal(field, "-"),	new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			._a()
			._a(field.fieldName(), 							new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			._a(atom_umb_rechts.get_fs_lastVal(field, "-"), new RB_gld()._span(2)._col(new E2_ColorBase())._ins(2))
			;
		}
	}

	private void generate_station_grid(E2_Grid grd, HashMap<ENUM_UMBUCHUNGSDEF, Rec20> hashMap, IF_Field... fieldlist) throws myException{
		Rec20_atom atom_umb1 = new Rec20_atom(hashMap.get(ENUM_UMBUCHUNGSDEF.atom_start_zu_umbuchung));

		Rec20_atom atom_umb2 = new Rec20_atom(hashMap.get(ENUM_UMBUCHUNGSDEF.atom_umbuchung_zu_ziel));

		Rec20 		umb1_station_start = 	hashMap.get(ENUM_UMBUCHUNGSDEF.station_start_zu_umbuchung_links);
		Rec20 		umb1_station_ziel = 	hashMap.get(ENUM_UMBUCHUNGSDEF.station_start_zu_umbuchung_rechts);

		Rec20 		umb2_station_start = 	hashMap.get(ENUM_UMBUCHUNGSDEF.station_umbuchung_zu_ziel_links);
		Rec20 		umb2_station_ziel = 	hashMap.get(ENUM_UMBUCHUNGSDEF.station_umbuchung_zu_ziel_rechts);

		grd
		._a(new RB_lab("Station START")._b(),										new RB_gld()._col(new E2_ColorDDark())._left_mid()._ins(2))
		._a("",																		new RB_gld()._span(2)._col(new E2_ColorDDark())._ins(2))
		._a(new RB_lab("Station ZIEL")._b(),										new RB_gld()._col(new E2_ColorDDark())._right_mid()._ins(2))
		._a()
		._a(new RB_lab("Station START")._b(),										new RB_gld()._col(new E2_ColorDDark())._left_mid()._ins(2))
		._a("",																		new RB_gld()._span(2)._col(new E2_ColorDDark()))
		._a(new RB_lab("Station ZIEL")._b(),										new RB_gld()._col(new E2_ColorDDark())._right_mid()._ins(2))
		;

		for(IF_Field field: fieldlist){
			grd
			._a(umb1_station_start.get_fs_lastVal(field),	new RB_gld()._col(new E2_ColorBase())._left_mid()._ins(2))
			._a(field.fn().toLowerCase(),					new RB_gld()._col(new E2_ColorDDark())._span(2)._center_mid()._ins(2))
			._a(umb1_station_ziel.get_fs_lastVal(field), 	new RB_gld()._col(new E2_ColorBase())._right_mid()._ins(2))
			._a()
			._a(umb2_station_start.get_fs_lastVal(field),	new RB_gld()._col(new E2_ColorBase())._left_mid()._ins(2))
			._a(field.fn().toLowerCase(),					new RB_gld()._col(new E2_ColorDDark())._span(2)._center_mid()._ins(2))
			._a(umb2_station_ziel.get_fs_lastVal(field), 	new RB_gld()._col(new E2_ColorBase())._right_mid()._ins(2))
			;
		}
	}
}
