package panter.gmbh.BasicInterfaces.Service;

import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.ENUM_REGISTRY;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.SANKTION_PRUEFUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.SANKTION.ENUM_SANKTION_Ergebnis_typ;
import panter.gmbh.basics4project.SANKTION.SANKTION_Ergebnisse;
import panter.gmbh.basics4project.SANKTION.SANKTION_MessageService;
import panter.gmbh.basics4project.SANKTION.SANKTION_PdToolbox;
import panter.gmbh.basics4project.SANKTION.SANKTION_Treffer;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;

public class PdServiceHandleSanktionsChecks {

	private boolean is_send_meldung_aktiv = false;

	private HashMap<String, SANKTION_Ergebnisse> sanktion_Ergebnisse =  new HashMap<>();

	private VEK<String> vIdAdresses = new VEK<>();

	private VEK<String> vHeaderLeft = new VEK<String>()._a("Hauptadresse")._a("Mitarbeiteradresse")._a("Lageradresse");

	public PdServiceHandleSanktionsChecks _send_meldung(boolean bSendMeldung) throws myException{
		this.is_send_meldung_aktiv = bSendMeldung;
		return this;
	}

	public PdServiceHandleSanktionsChecks initWithAdresses(VEK<String> vIdAdresse) throws myException{
		this.vIdAdresses = vIdAdresse;
		if(check_connexion_info()) {
			if(!(vIdAdresse==null) && vIdAdresse.size()>0) {
				if(test_sanktion_db()) {
					for(int i=0;i<vIdAdresse.size();i++) {
						String id_adresse_2_check = vIdAdresse.get(i);


						SANKTION_Ergebnisse pruefung_ergebnis = 
								new SANKTION_Ergebnisse()
								._init_with_mainadress(new Rec21_adresse(new Rec21(_TAB.adresse)._fill_id(id_adresse_2_check)));

						pruefung_ergebnis.writeSanktionToDatabase();

						this.sanktion_Ergebnisse.put(id_adresse_2_check, pruefung_ergebnis);

						//send message only if only 1 adresse, has no use autorization and appeared in sanktion database
						if(this.is_send_meldung_aktiv && this.sanktion_Ergebnisse.size()==1) {

							if(has_freigabe(vIdAdresse.get(0)) == false) {
								if(this.sanktion_Ergebnisse.get(id_adresse_2_check).containsSanktion()) {
									new SANKTION_MessageService().generate_message(id_adresse_2_check);

								}
							}

						}
					}
				}

			}
		}else {
			for(String id_adresse_2_check: vIdAdresse) {
				this.sanktion_Ergebnisse.put(id_adresse_2_check, new SANKTION_Ergebnisse()._add_dummy_eintraege(vIdAdresses));
			}
		}
		return this;
	}

	public HashMap<String, SANKTION_Ergebnisse> get_check_result() throws myException{
		return this.sanktion_Ergebnisse;
	}

	public boolean has_freigabe(String id_adresse_to_check) throws myException{
		
		//hier  muss zuerst die hauptadresse geholt werden
		Rec21_adresse ra = new Rec21_adresse()._fill_id(id_adresse_to_check);
		if (ra.is_ExistingRecord()) {
			
			String id_adresse = ""+ra._getMainAdresse().getId();
			
			String str_query = new SEL(SANKTION_PRUEFUNG.freigabe).FROM(_TAB.sanktion_pruefung)
					.WHERE(new vgl_YN(SANKTION_PRUEFUNG.aktiv, true))
					.AND(new vgl(SANKTION_PRUEFUNG.id_adresse, id_adresse))
					.AND(new vgl(SANKTION_PRUEFUNG.id_mandant, bibALL.get_ID_MANDANT())).s();
	
			String query_response = bibDB.EinzelAbfrage(str_query);
			if(query_response.equals("@@@@ERROR")) {
				return true;
			}else {
				return (query_response.equals("Y")?true:false);
			}
		} else {
			return false;
		}
	}

	private boolean check_connexion_info() throws myException{

		String login = ENUM_REGISTRY.SANKTION_JDBC_LOGIN.getStringValue();
		String pwd = ENUM_REGISTRY.SANKTION_JDBC_PWD.getStringValue();
		String jdbc = ENUM_REGISTRY.SANKTION_JDBC_STRING.getStringValue();

		return S.isAllFull(login, pwd, jdbc);
	}

	//2018-10-10: fehlerkorrektur fuer Sebastien
	private boolean test_sanktion_db(){
		try {
			//new SANKTION_PdToolbox().EinzelAbfrage(new SEL().FROM("LISTP").WHERE("ROWNUM", "=", "1").s());
			//fehler kann auch sein: rueckgabe is leer oder "@@@@ERROR"
			String ret = new SANKTION_PdToolbox().EinzelAbfrage(new SEL().FROM("LISTP").WHERE("ROWNUM", "=", "1").s());
			
			if (S.isEmpty(ret)) {
				bibMSG.MV()._addWarn("Sanktions-Datenbank: UEBERPRÜFEN!  Tabelle LISTP ist nicht vorhanden !");
				return false;
			}
			
			if (ret.equals("@@@@ERROR")) {
				bibMSG.MV()._addWarn("Sanktions-Datenbank: UEBERPRÜFEN! Tabelle LISTP ist leer!");
				return false;
			}
			
			return true;
		}catch(myException e){
			if(e.ErrorMessage.contains("ORA-01017")) {
				bibMSG.MV()._addWarn("Sanktions-Datenbank: UEBERPRÜFEN!  der Benutzername oder das Passwort sind nicht bekannt.");
			}else if(e.ErrorMessage.contains("I/O-Fehler")) {
				bibMSG.MV()._addWarn("Sanktions-Datenbank: UEBERPRÜFEN!  der Netzwerkadapter konnte die Verbindung nicht herstellen.");
			}else {
				bibMSG.MV()._addWarn("Sanktions-Datenbank: UEBERPRÜFEN! "+e.getLocalizedMessage());
			}
		}
		return false;
	}


	//Ergebnis grid

	public E2_Grid render_ergebnis() throws myException{

		HashMap<String, own_counter_map> analyse_result = prepare_ergebnisse();


		RB_gld gld_ddark_mid= new RB_gld()._ins(2)._center_mid()._col_back_dd();
		RB_gld gld_ddark_lef= new RB_gld()._ins(2)._left_mid()._col_back_dd();

		E2_Grid  grd = new E2_Grid()._setSize(120,80,80,80,80,80)._bo_ddd();

		grd._a()._a("Name",gld_ddark_mid)._a("Anschrift",gld_ddark_mid)._a("Ausweis",gld_ddark_mid)._a("Fehler allg.",gld_ddark_mid)._a("OK",gld_ddark_mid);

		for(String header_left:vHeaderLeft) {
			grd._a(header_left,gld_ddark_lef);
			own_counter_map res = analyse_result.get(header_left);
			int name_count 		= res.get(ENUM_SANKTION_Ergebnis_typ.TREFFER_NAME).get();
			int anschrift_count = res.get(ENUM_SANKTION_Ergebnis_typ.TREFFER_ADRESSE).get();
			int ausweis_count 	=res.get(ENUM_SANKTION_Ergebnis_typ.TREFFER_AUSWEIS).get();
			int fehler_count 	= res.get(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG).get();
			int ok_count 		= res.get(ENUM_SANKTION_Ergebnis_typ.OK).get();


			add_row(grd, name_count, 		false);
			add_row(grd, anschrift_count, 	false);
			add_row(grd, ausweis_count, 	false);
			add_row(grd, fehler_count, 		false);
			add_row(grd, ok_count, 			true);

		}

		return grd;
	}

	private void add_row(E2_Grid grd, int counter, boolean true_if_green) {
		RB_gld gld_neutral 	= new RB_gld()._center_mid();

		RB_gld inner_grd = new RB_gld()._ins(1)._center_mid();

		RB_lab lbl_counter = new RB_lab(""+counter);

		E2_Grid grid_4_rahmen =  new E2_Grid()._setSize(70);

		if(counter == 0) {
			grd._a(grid_4_rahmen._a(lbl_counter, inner_grd)._bo_no(), 				gld_neutral);
		}else {
			if(true_if_green) {
				grd._a(grid_4_rahmen._a(lbl_counter._b(), inner_grd)._bo_green(), 	gld_neutral);
			}else {
				grd._a(grid_4_rahmen._a(lbl_counter._b(), inner_grd)._bo_red(), 	gld_neutral);
			}
		}
	}

	private HashMap<String, own_counter_map> prepare_ergebnisse() throws myException {

		HashMap<String, own_counter_map> counter_per_adresstyp_MAP = new HashMap<String, own_counter_map>();
		counter_per_adresstyp_MAP.put(vHeaderLeft.get(0), new own_counter_map());
		counter_per_adresstyp_MAP.put(vHeaderLeft.get(1), new own_counter_map());
		counter_per_adresstyp_MAP.put(vHeaderLeft.get(2), new own_counter_map());

		for(String id_adresse : this.sanktion_Ergebnisse.keySet()) {

			SANKTION_Ergebnisse erg = this.sanktion_Ergebnisse.get(id_adresse);
			
			for(String id_sanktion_ergebnis :erg.keySet()) {

				int adresse_typ = new Rec21(_TAB.adresse)._fill_id(id_sanktion_ergebnis).get_myLong_dbVal(ADRESSE.adresstyp).get_iValue();

				String hauptkey = "";

				if(adresse_typ == myCONST.ADRESSTYP_FIRMENINFO) {
					hauptkey = vHeaderLeft.get(0);
				}else if(adresse_typ == myCONST.ADRESSTYP_MITARBEITER){
					hauptkey = vHeaderLeft.get(1);
				}else if(adresse_typ == myCONST.ADRESSTYP_LIEFERADRESSE){
					hauptkey = vHeaderLeft.get(2);
				}

				VEK<SANKTION_Treffer> all_treffer = erg.get(id_sanktion_ergebnis);

				for(SANKTION_Treffer treffer:all_treffer) {
					MutableInt count = counter_per_adresstyp_MAP.get(hauptkey).get(treffer.getTreffer_typ());
					if(count == null) {
						counter_per_adresstyp_MAP.get(hauptkey).put(treffer.getTreffer_typ(), new MutableInt());
					}else {
						count.increment();
					}
				}
			}

		}

		return counter_per_adresstyp_MAP;
	}

	private class own_counter_map extends HashMap<ENUM_SANKTION_Ergebnis_typ, MutableInt>{
		public own_counter_map() {
			super();
			this.put(ENUM_SANKTION_Ergebnis_typ.OK, 					new MutableInt());
			this.put(ENUM_SANKTION_Ergebnis_typ.FEHLER_BEI_PRUEFUNG, 	new MutableInt());
			this.put(ENUM_SANKTION_Ergebnis_typ.TREFFER_ADRESSE, 		new MutableInt());
			this.put(ENUM_SANKTION_Ergebnis_typ.TREFFER_AUSWEIS,		new MutableInt());
			this.put(ENUM_SANKTION_Ergebnis_typ.TREFFER_NAME, 			new MutableInt());

		}
	}

	class MutableInt {
		int value = 0; // note that we start at 1 since we're counting
		public void increment () { ++value;      }
		public int  get ()       { return value; }
	}


}
