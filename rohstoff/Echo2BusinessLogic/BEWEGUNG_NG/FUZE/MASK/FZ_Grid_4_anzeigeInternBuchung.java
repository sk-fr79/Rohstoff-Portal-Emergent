package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_mapCollector_4_FZ_MaskModulContainer;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_atom;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_station;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class FZ_Grid_4_anzeigeInternBuchung extends E2_Grid{

	private LinkedHashMap<String, String> fields_map ;

	private int[] grid_size = {0};

	private RB_ComponentMapCollector 	compMap_collector = null;

	private IF_MasterKey 				master_key = null;



	public FZ_Grid_4_anzeigeInternBuchung(IF_mapCollector_4_FZ_MaskModulContainer pCompMapCollector) throws myException {

		super();

		this._setSize(60,20,250,60,400,400,200,200);
		
		this.fields_map = new LinkedHashMap<String, String>();

		this.compMap_collector = (RB_ComponentMapCollector)pCompMapCollector;

		this.master_key = pCompMapCollector.get_master_key();

		this.init_field_list();

		this.fill_field_list();
	}


	public E2_Grid _render_grid(){
		return this;
	}

	private void fill_field_list() throws myException{

		Rec20 rec_vektor = this.compMap_collector.rb_Actual_DataobjectCollector().get(master_key.k_vektor()).rec20();

		RecList20 list_vektor_pos = rec_vektor.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, null, BEWEGUNG_VEKTOR_POS.posnr.fn());

		if(list_vektor_pos.size()>0){
			RB_gld ldt = new RB_gld()._left_top()._ins(1, 0, 8, 0)._col(new E2_ColorLight());
			this._a(new RB_lab()._tr("Menge")._fs(8)._i(),ldt._c()._right_top());
			this._a(new RB_lab()._tr("EH")._fs(8)._i(),ldt);
			this._a(new RB_lab()._tr("Sorte")._fs(8)._i(),ldt);
			this._a(new RB_lab()._tr("Preis")._fs(8)._i(),ldt._c()._right_top());
			this._a(new RB_lab()._tr("von")._fs(8)._i(),ldt);
			this._a(new RB_lab()._tr("nach")._fs(8)._i(),ldt);
			this._a(new RB_lab()._tr("Besitz vorher")._fs(8)._i(),ldt);
			this._a(new RB_lab()._tr("Besitz nachher")._fs(8)._i(),ldt);

			for(Rec20 rec_pos: list_vektor_pos){

				RecList20  reclist_atome = rec_pos.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, BEWEGUNG_ATOM.posnr.fn());

				for (int i=0; i<reclist_atome.size();i++) {

					Rec20_atom 	atom_e = new Rec20_atom(reclist_atome.get(i));
					Rec20_station  	station_start = atom_e.__station_start();
					Rec20_station  	station_ziel  = atom_e.__station_ziel();

					RB_lab info_besitzerwechselvon 	= new RB_lab();
					RB_lab info_besitzerwechselziel = new RB_lab();

					if (i==0 || i==(reclist_atome.size()-1)) {
						//besitzerwechsel feststellen
						MyLong id_besitzer_start = station_start.get_myLong_dbVal(BEWEGUNG_STATION.id_adresse_besitzer);
						MyLong id_besitzer_ziel = station_ziel.get_myLong_dbVal(BEWEGUNG_STATION.id_adresse_besitzer);

						if (id_besitzer_start !=null && id_besitzer_ziel!=null && id_besitzer_start.isOK() && id_besitzer_ziel.isOK()) {

							Rec20_adresse ra_links = 	new Rec20_adresse(station_start.get_up_Rec20(BEWEGUNG_STATION.id_adresse_besitzer, ADRESSE.id_adresse,true));
							Rec20_adresse ra_rechts = 	new Rec20_adresse(station_ziel.get_up_Rec20(BEWEGUNG_STATION.id_adresse_besitzer, ADRESSE.id_adresse,true));

							if (id_besitzer_start.get_lValue()== id_besitzer_ziel.get_lValue()) {
								info_besitzerwechselvon._t(ra_links.__get_name1_ort())._fs(8)._col_fore_dgrey();
								info_besitzerwechselziel._t(ra_rechts.__get_name1_ort())._fs(8)._col_fore_dgrey();
							} else {
								info_besitzerwechselvon._t(ra_links.__get_name1_ort())._fs(8);
								info_besitzerwechselziel._t(ra_rechts.__get_name1_ort())._fs(8);
							}

						}

						RB_gld ld = new RB_gld()._left_top()._ins(1, 0, 8, 0);

						this._a(new RB_lab()._t(atom_e.get_fs_dbVal(BEWEGUNG_ATOM.menge))._fs(8),ld._c()._right_top());
						this._a(new RB_lab()._t(atom_e._get_einheitkurz())._fs(8),ld);
						this._a(new RB_lab()._t(atom_e._get_anr1_anr2_artbez1())._fs(8),ld);
						this._a(new RB_lab()._t(atom_e.get_fs_dbVal(BEWEGUNG_ATOM.e_preis))._fs(8),ld._c()._right_top());

						if (station_start.is_reale_adresse()) {
							this._a(new RB_lab()._t(station_start.info_string_station())._fs(8),ld);
						} else {
							this._a(new RB_lab()._t(station_start.info_string_station())._fs(8)._col_fore_dgrey(),ld);
						}

						if (station_ziel.is_reale_adresse()) {
							this._a(new RB_lab()._t(station_ziel.info_string_station())._fs(8),ld);
						} else {
							this._a(new RB_lab()._t(station_ziel.info_string_station())._fs(8)._col_fore_dgrey(),ld);
						}

						this._a(info_besitzerwechselvon, ld);
						this._a(info_besitzerwechselziel, ld);
					}
				}
			}
		}
	}

	private void init_field_list(){
		this.fields_map.put("Menge"			, 	"");
		this.fields_map.put("EH"			, 	"");
		this.fields_map.put("Sorte"			, 	"");
		this.fields_map.put("Preis"			, 	"");
		this.fields_map.put("von"			, 	"");
		this.fields_map.put("nach"			,	"");
		this.fields_map.put("Besitz vorher"	, 	"");
		this.fields_map.put("Besitz nachher", 	"");
	}



}
