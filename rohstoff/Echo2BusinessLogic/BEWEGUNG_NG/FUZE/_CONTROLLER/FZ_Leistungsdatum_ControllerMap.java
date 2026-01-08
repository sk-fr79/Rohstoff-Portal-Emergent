package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;

public abstract class FZ_Leistungsdatum_ControllerMap extends RB_MaskControllerMap {

	private RB_ComponentMapCollector comp_map_collector;


	public FZ_Leistungsdatum_ControllerMap(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);

		this.comp_map_collector 	= p_componentMapCollector;

	}


	public abstract RB_KM set_key(RB_ComponentMapCollector p_componentMapCollector);

	public void fill_datum(MyE2_MessageVector mv) throws myException{

		mv.clear();

		String leistung_datum_start = this.get_maskVal(get_quelle_atom(), BEWEGUNG_ATOM.leistungsdatum);

		String leistung_datum_ziel 	= this.get_maskVal(get_ziel_atom(), BEWEGUNG_ATOM.leistungsdatum);

		String lade_datum_v = this.get_maskVal(get_vektor(), BEWEGUNG_VEKTOR.l_datum_von);
		String lade_datum_b = this.get_maskVal(get_vektor(), BEWEGUNG_VEKTOR.l_datum_bis);

		String ablade_datum_v = this.get_maskVal(get_vektor(), BEWEGUNG_VEKTOR.a_datum_von);
		String ablade_datum_b = this.get_maskVal(get_vektor(), BEWEGUNG_VEKTOR.a_datum_bis);

		if(S.isAllFull(leistung_datum_ziel, lade_datum_v,lade_datum_b ,ablade_datum_v, ablade_datum_b)){

			if(S.isEmpty(leistung_datum_ziel)){
				this.set_maskVal(get_ziel_atom(), BEWEGUNG_ATOM.leistungsdatum, leistung_datum_start, mv);
			}else{
				if(myDateHelper.get_Date1_Greater_Date2(leistung_datum_start,leistung_datum_ziel)){
					mv._addAlarm("Die Abladedatum Datum ist groesser als die Ladedatum");
				}
			}


			if(S.isEmpty(lade_datum_v) || S.isEmpty(lade_datum_b)){
				this.set_maskVal(get_vektor(), BEWEGUNG_VEKTOR.l_datum_von, leistung_datum_start, mv);
				this.set_maskVal(get_vektor(), BEWEGUNG_VEKTOR.l_datum_bis, leistung_datum_start, mv);	
			}else if(S.isFull(lade_datum_v) && S.isFull(lade_datum_b)){

				if(myDateHelper.get_Date1_Greater_Date2(lade_datum_v,lade_datum_b)){
					mv._addAlarm("#--#PLANZEITRAUM:ladedatum von ist groesser als die ladedatum bis");
					this.get_comp(this.get_vektor(),  BEWEGUNG_VEKTOR.l_datum_von, mv).show_InputStatus(false);
					this.get_comp(this.get_vektor(),  BEWEGUNG_VEKTOR.l_datum_bis, mv).show_InputStatus(false);
				}
				if(myDateHelper.get_Date1_Less_Date2(lade_datum_v,leistung_datum_start)){
					mv._addAlarm("#--#PLANZEITRAUM: ladedatum von ist vor die leistungsdatum quelle");
					
					this.get_comp(this.get_quelle_atom(), BEWEGUNG_ATOM.leistungsdatum, mv).show_InputStatus(false);
					this.get_comp(this.get_vektor(),  	BEWEGUNG_VEKTOR.a_datum_bis, mv).show_InputStatus(false);
				}

				if(myDateHelper.get_Date1_Greater_Date2(lade_datum_b,leistung_datum_ziel)){
					mv._addAlarm("#--#PLANZEITRAUM: ladedatum ist ist groesser als die ziel leitsungsdatum");
					
					this.get_comp(this.get_ziel_atom(),BEWEGUNG_ATOM.leistungsdatum, mv).show_InputStatus(false);
					this.get_comp(this.get_vektor(),   BEWEGUNG_VEKTOR.l_datum_bis, mv).show_InputStatus(false);
				}

			}else{

			}

			if(S.isEmpty(ablade_datum_v) || S.isEmpty(ablade_datum_b)){
				this.set_maskVal(get_vektor(), BEWEGUNG_VEKTOR.a_datum_von, leistung_datum_ziel, mv);
				this.set_maskVal(get_vektor(), BEWEGUNG_VEKTOR.a_datum_bis, leistung_datum_ziel, mv);
			}else if(S.isFull(ablade_datum_v) && S.isFull(ablade_datum_b)){

				if(myDateHelper.get_Date1_Greater_Date2(ablade_datum_v,ablade_datum_b)){
					mv._addAlarm("#--#PLANZEITRAUM: abladedatum von ist groesser als die abladedatum bis");
					
					this.get_comp(this.get_vektor(),  BEWEGUNG_VEKTOR.a_datum_von, mv).show_InputStatus(false);
					this.get_comp(this.get_vektor(),  BEWEGUNG_VEKTOR.a_datum_bis, mv).show_InputStatus(false);
				}
				if(myDateHelper.get_Date1_Less_Date2(ablade_datum_v,leistung_datum_start)){
					mv._addAlarm("#--#PLANZEITRAUM: abladedatum von ist vor die leistungsdatum start");
					
					this.get_comp(this.get_quelle_atom(), BEWEGUNG_ATOM.leistungsdatum, mv).show_InputStatus(false);
					this.get_comp(this.get_vektor(),  BEWEGUNG_VEKTOR.a_datum_bis, mv).show_InputStatus(false);
				}

				if(myDateHelper.get_Date1_Greater_Date2(ablade_datum_b,leistung_datum_ziel)){
					mv._addAlarm("#--#PLANZEITRAUM: abladedatum ist ist groesser als die ziel leitsungsdatum");
					
					this.get_comp(this.get_ziel_atom(),BEWEGUNG_ATOM.leistungsdatum, mv).show_InputStatus(false);
					this.get_comp(this.get_vektor(),  BEWEGUNG_VEKTOR.a_datum_bis, mv).show_InputStatus(false);
				}

			}
		}else{
			if(S.isEmpty(leistung_datum_ziel)){
				this.set_maskVal(get_ziel_atom(), 	BEWEGUNG_ATOM.leistungsdatum, leistung_datum_start, mv);			
			}
			
			if(S.isEmpty(lade_datum_v)){
				this.set_maskVal(get_vektor(), 		BEWEGUNG_VEKTOR.l_datum_von, leistung_datum_start, mv);
			}
			
			if(S.isEmpty(lade_datum_b)){
				this.set_maskVal(get_vektor(), 		BEWEGUNG_VEKTOR.l_datum_bis, leistung_datum_start, mv);
			}
			
			if(S.isEmpty(ablade_datum_v)){
				leistung_datum_ziel 				= this.get_liveVal(get_ziel_atom(), BEWEGUNG_ATOM.leistungsdatum);
				
				this.set_maskVal(get_vektor(), 		BEWEGUNG_VEKTOR.a_datum_von, leistung_datum_ziel, mv);
			}
			
			if(S.isEmpty(ablade_datum_b)){
				leistung_datum_ziel 				= this.get_liveVal(get_ziel_atom(), BEWEGUNG_ATOM.leistungsdatum);
				
				this.set_maskVal(get_vektor(), 		BEWEGUNG_VEKTOR.a_datum_bis, leistung_datum_ziel, mv);
			}
		}
	}

	public abstract KEY_ATOM get_quelle_atom();

	public abstract KEY_ATOM get_ziel_atom();

	public abstract KEY_VEKT get_vektor();


	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		return null;
	}

	public RB_ComponentMapCollector get_component_map_collector() {
		return comp_map_collector;
	}

}
