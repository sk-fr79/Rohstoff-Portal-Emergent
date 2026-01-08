package panter.gmbh.basics4project.GEO_FAHRPLAN;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoRoutingEntfernungUndKostenBean;
import panter.gmbh.BasicInterfaces.ServiceBeanInterface.PdServiceGeoRoutingEntfernungUndKosten.ENUM_ROUTING_TYP;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.BasicInterfaces.IF_Align;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class GEO_FAHR_Routing_popup extends E2_BasicModuleContainer {

	private HashMap<String, Rec20_adresse> 				adresse_pool 				= new HashMap<String, Rec20_adresse>();
	private HashMap<String, add_destination_button> 	bt_adresse_pool 			= new HashMap<String, add_destination_button>();
	private VEK<String> 								adresse_pool_sorted_by_id 	= new VEK<>();

	private VEK<BigDecimal>							vEntfernung					= new VEK<>();
	private VEK<BigDecimal>							vZeit						= new VEK<>();
	private RB_lab										lbl_gesamt_entfernung ;
	private RB_lab										lbl_gesamt_zeit ;

	private String 										kfzKennzeichenLkw 			= "";
	private String 										kfzKennzeichenAnhaenger 	= "";

	private ArrayList<String> vDestination = new ArrayList<String>();

	private E2_Grid adresse_pool_grid;

	private E2_Grid fahrplan_grid;

	private E2_Button fahrplan_loeschen;

	private MyE2_MessageVector mv = new MyE2_MessageVector();

	private String id_adresse_mandant = bibALL.get_ID_ADRESS_MANDANT();

	public GEO_FAHR_Routing_popup() throws myException {
		super();

		mv = new MyE2_MessageVector();

		E2_Grid main_grid 		= new E2_Grid()._setSize(350, 1200);
		E2_Grid command_grid 	= new E2_Grid()._s(2);

		adresse_pool_grid 		= new E2_Grid()._setSize(300)._bo_dd();

		lbl_gesamt_entfernung 	= new RB_lab();
		lbl_gesamt_zeit			= new RB_lab();

		fahrplan_grid 			= new E2_Grid();
		MyE2_ContainerEx cont_fahrplan_grid = new MyE2_ContainerEx(fahrplan_grid);
		cont_fahrplan_grid.setHeight(new Extent(480));

		fahrplan_loeschen 		= new E2_Button()._t("Fahrplan loeschen")._backDDDark()._insets(2)._width(150);
		fahrplan_loeschen._standard_text_button();
		fahrplan_loeschen._aaa(new fahrplan_loeschen_aa())._ttt("Fahrplan loeschen");

		MyE2_ContainerEx cont_addr__pool = new MyE2_ContainerEx(adresse_pool_grid);
		cont_addr__pool.setHeight(new Extent(480));

		command_grid
		._a(fahrplan_loeschen, 	new RB_gld()._ins(2,2,2,10)._left_mid());

		main_grid
		._a()
		._a(command_grid, 		new RB_gld()._left_top())
		._a(cont_addr__pool, 	new RB_gld()._left_top())
		._a(cont_fahrplan_grid, new RB_gld()._left_top())
		;

		this.add(main_grid, E2_INSETS.I_2_2_2_2);

	}

	public void render_popup()throws myException{
		if(mv.get_bIsOK()) {
			sort_adresse_pool();
			render_adresse_pool_grid();	
			render_fahrplan_grid();
		}
	}

	
	public void render_fahrplan_grid() throws myException{
		this.fahrplan_grid._clear()._setSize(30, 240, 240, 170, 170, 170)._bo_dd();
		this.vEntfernung.clear();
		this.vZeit.clear();

		E2_Grid zeit_label_grid 		= 
				new E2_Grid()._setSize(60,100)._a(new RB_lab("Zeit")._b(), 		new RB_gld()._right_mid()._ins(2,2,5,2)._span(2))
				._a("Gesamt:")._a(lbl_gesamt_zeit, 									new RB_gld()._right_mid()._ins(2,2,5,2));

		E2_Grid entfernung_label_grid 	= 
				new E2_Grid()._setSize(60,100)._a(new RB_lab("Entfernung")._b(),	new RB_gld()._right_mid()._ins(2,2,5,2)._span(2))
				._a("Gesamt:")._a(lbl_gesamt_entfernung, 							new RB_gld()._right_mid()._ins(2,2,5,2));

		this
		.fahrplan_grid
		._a()
		._a(new RB_lab("Von")._b(), 	new RB_gld()._center_top()._ins(2))
		._a(new RB_lab("Nach")._b(), 	new RB_gld()._center_top()._ins(2))
		._a(zeit_label_grid, 			new RB_gld()._left_top()._ins(10,2,0,2))
		._a(entfernung_label_grid, 		new RB_gld()._right_mid()._ins(10,2,0,2))
		._a(new RB_lab("Kosten")._b(), 	new RB_gld()._right_top()._ins(2,2,5,2))
		;

		VEK<String> v_destination_loop = new VEK<String>();	

		for(int i= 0; i<vDestination.size(); i++) {
			if(i == 0) {
				v_destination_loop._a(vDestination.get(i));
			}else {
				v_destination_loop._a(vDestination.get(i));
				v_destination_loop._a(vDestination.get(i));
			}
		}

		if(v_destination_loop.size()>1) {
			v_destination_loop.remove(v_destination_loop.size()-1);
		}

		if(v_destination_loop.size() == 1) {

			Rec20_adresse rec_start = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(v_destination_loop.get(0))); 

			render_line(rec_start, null);

		}else {
			for(int i= 0; i<v_destination_loop.size(); i+=2 ) {

				Rec20_adresse rec_start = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(v_destination_loop.get(i))); 
				Rec20_adresse rec_ziel = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(v_destination_loop.get(i+1))); 

				render_line(rec_start, rec_ziel);
			}
		}
		this.lbl_gesamt_entfernung.setText(gesamt_entfernung().get_FormatedRoundedNumber(2)+ " km");
		this.lbl_gesamt_zeit.setText(gesamt_zeit());

		if(mv.get_bHasAlarms()) {
			bibMSG.add_MESSAGE(mv);
		}
	}

	private void render_line(Rec20_adresse rec_start, Rec20_adresse rec_ziel) throws myException {
		E2_Button del_bt = 
				new E2_Button()
				._image("eraser.png");

		del_bt._aaa(new fahrplan_loeschen_aa());

		if(rec_ziel !=null) {

			HashMap<ENUM_ROUTING_TYP, BigDecimal> entf_und_zeit = new PdServiceGeoRoutingEntfernungUndKostenBean()
					.getDistanceTimeAmdCosts(
							rec_start.get_myBigDecimal_dbVal(ADRESSE.latitude).get_bdWert(), 
							rec_start.get_myBigDecimal_dbVal(ADRESSE.longitude).get_bdWert(), 
							rec_ziel.get_myBigDecimal_dbVal(ADRESSE.latitude).get_bdWert(), 
							rec_ziel.get_myBigDecimal_dbVal(ADRESSE.longitude).get_bdWert(), 
							kfzKennzeichenLkw, 
							kfzKennzeichenAnhaenger, 
							mv);

			if(! mv.get_bHasAlarms()) {
				this.vEntfernung._a(entf_und_zeit.get(ENUM_ROUTING_TYP.ENTFERNUNG));
				this.vZeit		._a(entf_und_zeit.get(ENUM_ROUTING_TYP.ZEIT_SEK));

				del_bt.remove_AllActionAgents();
				del_bt._aaa(new remove_destination(rec_ziel.get_ufs_dbVal(ADRESSE.id_adresse)));

				String entf = new MyBigDecimal(O.NN(entf_und_zeit.get(ENUM_ROUTING_TYP.ENTFERNUNG), BigDecimal.ZERO)).get_FormatedRoundedNumber(2)+" km";	
				String kost = new MyBigDecimal(O.NN(entf_und_zeit.get(ENUM_ROUTING_TYP.KOSTEN_KM), BigDecimal.ZERO)).get_FormatedRoundedNumber(2) ;

				this.fahrplan_grid
				._a(del_bt														, new RB_gld()._ins(2)._center_mid())
				._a(new destination_display_comp(rec_start)						, new RB_gld()._ins(2,5,2,5)._left_top())
				._a(new destination_display_comp(rec_ziel)						, new RB_gld()._ins(0,5,0,5)._left_top())
				._a(format_zeit(entf_und_zeit.get(ENUM_ROUTING_TYP.ZEIT_SEK))	, new RB_gld()._ins(10,2,5,2)._right_top())
				._a(entf														, new RB_gld()._ins(2,2,5,2)._right_top())
				._a(kost														, new RB_gld()._ins(2,2,5,2)._right_top());

			}else {
				bibMSG.add_MESSAGE(mv);
			}


		}else {
			this.fahrplan_grid
			._a(del_bt													, new RB_gld()._ins(2)._center_mid())
			._a(new destination_display_comp(rec_start)					, new RB_gld()._ins(2,5,2,5)._left_top())
			._a(""														, new RB_gld()._ins(0,5,0,5)._left_top())
			._a(format_zeit(BigDecimal.ZERO)						, new RB_gld()._ins(10,2,5,2)._right_top())
			._a(format_zeit(BigDecimal.ZERO)+" km"	, new RB_gld()._ins(2,2,5,2)._right_top())
			._a();
		}
	}

	private String format_zeit(BigDecimal bdzeit) throws myException{
		long lVal = bdzeit.longValue();
		int hours = (int) lVal / 3600;
		int remainder = (int) lVal - hours * 3600;
		int mins = remainder / 60;

		return String.format("%02d:%02d", hours , mins);
	}

	private MyBigDecimal gesamt_entfernung() throws myException {
		MyBigDecimal bdGesamt = new MyBigDecimal(0);
		for(BigDecimal bd_entf: vEntfernung) {
			bdGesamt.add_to_me(bd_entf);
		}
		return bdGesamt;
	}

	private String gesamt_zeit() throws myException {
		MyBigDecimal bdGesamt = new MyBigDecimal(0);
		for(BigDecimal bd_entf: vZeit) {
			bdGesamt.add_to_me(bd_entf);
		}
		return format_zeit(bdGesamt.get_bdWert());
	}

	private void sort_adresse_pool() throws myException{
		VEK<String> pool_id 		= new VEK<String>()._a(this.adresse_pool.keySet());
		VEK<String> sorted_pool_id 	= new VEK<>();

		adresse_pool_sorted_by_id.clear();

		//if mandant main adresse in adresse pool
		if(pool_id.contains(id_adresse_mandant)) {
			sorted_pool_id._a(id_adresse_mandant);
			pool_id.remove(id_adresse_mandant);
		}

		//iteration fuer lager von mandant
		for(String id_adresse: pool_id) {
			Rec20_adresse rec_addr = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(id_adresse));
			if(rec_addr.is_yes_db_val(ADRESSE.aktiv) && rec_addr.get_ufs_dbVal(ADRESSE.adresstyp).equals(""+myCONST.ADRESSTYP_LIEFERADRESSE) ){
				String basis_adress_id = rec_addr
						.get_down_reclist20(LIEFERADRESSE.id_adresse_liefer, null, null)
						.get(0)
						.get_ufs_dbVal(LIEFERADRESSE.id_adresse_basis);

				if(basis_adress_id.equals(id_adresse_mandant)){
					sorted_pool_id._a(id_adresse);
				}
			}
		}

		TreeMap<String, String> alpha_sorting = new TreeMap<>();

		//add the other adress id in vector (alphabetical order), 
		for(String id_adresse: pool_id) {
			if( ! sorted_pool_id.contains(id_adresse)) {
				Rec20_adresse rec_addr = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(id_adresse));
				alpha_sorting.put(rec_addr.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2), id_adresse);
			}
		}
		sorted_pool_id._a(alpha_sorting.values());

		this.adresse_pool_sorted_by_id._a(sorted_pool_id);
	}


	public void render_adresse_pool_grid() throws myException {
		adresse_pool_grid._clear();

		if(adresse_pool_sorted_by_id != null && adresse_pool_sorted_by_id.size()>0) {
			for(String adresse_id_sorted : this.adresse_pool_sorted_by_id) {
				this.adresse_pool_grid._a(bt_adresse_pool.get(adresse_id_sorted), new RB_gld()._ins(2)._center_mid());
			}
		}
	}

	public GEO_FAHR_Routing_popup _set_adress_pool(VEK<Rec20> v_adresse_pool) throws myException{
		this.adresse_pool.clear();
		if(v_adresse_pool.size()>0) {
			for(Rec20 adresse_rec:v_adresse_pool) {
				String id_adresse 	= adresse_rec.get_ufs_dbVal(ADRESSE.id_adresse);
				this.adresse_pool	.put(id_adresse, new Rec20_adresse(adresse_rec));

				add_destination_button bt = new add_destination_button(adresse_pool.get(id_adresse));
				this.bt_adresse_pool.put(id_adresse, bt);
			}
		}else {
			bibMSG.MV()._addWarn("Keine Adresse definiert !");
		}
		return this;
	}

	public GEO_FAHR_Routing_popup _set_destination(VEK<Rec20> destination_list) throws myException{
		vDestination.clear();
		for(Rec20 destinationRec : destination_list) {
			if(S.isAllFull(destinationRec.get_ufs_dbVal(ADRESSE.latitude), destinationRec.get_ufs_dbVal(ADRESSE.latitude))){
				vDestination.add(destinationRec.get_key_value());
				if(bt_adresse_pool.size()>0) {
					bt_adresse_pool.get(destinationRec.get_key_value()).getButton()._b();
				}
			}else {
				bibMSG.MV()._addWarn("ID: " + destinationRec.get_key_value() + " - " 
					+ destinationRec.get_ufs_dbVal(ADRESSE.name1) 
					+ "("+destinationRec.get_ufs_dbVal(ADRESSE.ort) + "): Geokoordinaten sind nicht vollständig!");
				break;
			}
		}
		return this;
	}

	public GEO_FAHR_Routing_popup _set_kfz_kennzeichen(String kfz_kennzeichen) throws myException{
		this.kfzKennzeichenLkw = kfz_kennzeichen;
		return this;
	}

	public GEO_FAHR_Routing_popup _set_kfz_anhaenger_kennzeichen(String kfz_anhaenger_kennzeichen) throws myException{
		this.kfzKennzeichenAnhaenger = kfz_anhaenger_kennzeichen;
		return this;
	}

	private class fahrplan_loeschen_aa extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			GEO_FAHR_Routing_popup othis = GEO_FAHR_Routing_popup.this;

			othis.vDestination.clear();
			othis.vEntfernung.clear();
			othis.vZeit.clear();

			othis.lbl_gesamt_entfernung.setText("");
			othis.lbl_gesamt_zeit.setText("");
			for(add_destination_button bt : bt_adresse_pool.values()) {
				bt._bc(new E2_ColorDark());
				bt.getButton()._fo_plain();
			}

			render_fahrplan_grid();
		}
	}

	private class add_destination extends XX_ActionAgent{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_Button source_bt = (E2_Button)oExecInfo.get_MyActionEvent().getSource();

			String source_id_adresse = source_bt.EXT().get_C_MERKMAL();

			vDestination.add(source_id_adresse);

			bt_adresse_pool.get(source_id_adresse).getButton()._b();

			render_fahrplan_grid();
		}
	}

	private class remove_destination extends XX_ActionAgent{

		private String id_adresse = "";

		public remove_destination(String id_adresse_to_remove_from_list) {
			super();
			this.id_adresse = id_adresse_to_remove_from_list;

		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			int start_remove_idx = vDestination.lastIndexOf(id_adresse);

			List<String> sbList = vDestination.subList(start_remove_idx, vDestination.size());			
			for(String id_to_reinit: sbList) {
				bt_adresse_pool.get(id_to_reinit).getButton()._bc(new E2_ColorDark())._fo_plain();
			}

			vDestination.subList(start_remove_idx, vDestination.size()).clear();

			for(String id: vDestination) {
				bt_adresse_pool.get(id).getButton()._b();
			}

			render_fahrplan_grid();
		}
	}

	private class add_destination_button extends E2_Grid implements IF_Align<E2_Button>{


		private RB_lab ikon = new RB_lab();
		private E2_Button button = new E2_Button();
		
		public add_destination_button(Rec20_adresse rec_addr) throws myException {
			super();
			this._setSize(25, 325);
			
			this.button.EXT().set_C_MERKMAL(rec_addr.get_key_value());
			
			this
			._a(ikon, 	new RB_gld()._ins(2)._left_mid())
			._a(button, new RB_gld()._ins(2)._left_mid());
			
			String ort = rec_addr.get_fs_dbVal(ADRESSE.ort, "");
			if(S.isFull(ort)) {
				ort = " ("+ort+")";
			}

			
			this.EXT().set_C_MERKMAL(rec_addr.get_ufs_dbVal(ADRESSE.id_adresse));

			this.button._standard_text_button()._align_left();

			if(! S.isAllFull(rec_addr.get_ufs_dbVal(ADRESSE.longitude,""), rec_addr.get_ufs_dbVal(ADRESSE.latitude,""))) {
				this.ikon._icon(E2_ResourceIcon.get_RI("inaktiv.png"));
				this.ikon._ttt("Keine geocoordinate definiert !");
				this.button.setForeground(Color.DARKGRAY);
				this.button.set_bEnabled_For_Edit(false);
			}else {
				if(rec_addr.get_ufs_dbVal(ADRESSE.id_adresse).equals(bibALL.get_ID_ADRESS_MANDANT())) {
					this.ikon._icon(E2_ResourceIcon.get_RI("firma.png"));
				}else if( is_lager_from_mandant(rec_addr)) {
					this.ikon._icon(E2_ResourceIcon.get_RI("firma.png"));
				}else{
					this.ikon._icon(E2_ResourceIcon.get_RI("empty.png"));
				}
			}


			this._insets(1);
			this.button._t(rec_addr.get_fs_dbVal(ADRESSE.name1) + ort);

			this.button._aaa(new add_destination());
		}
		
		public E2_Button getButton() {
			return button;
		}
	}

	private class destination_display_comp extends E2_Grid{

		public destination_display_comp(Rec20_adresse rec_addr) throws myException {
			super();

			this._setSize(240);

			this
			._a(new RB_lab(rec_addr.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2))		._fsa(-1), 	new RB_gld()._ins(2)._left_mid())
			._a(new RB_lab(rec_addr.get_ufs_kette(" ", ADRESSE.strasse, ADRESSE.hausnummer))._fsa(-2), 	new RB_gld()._ins(2)._left_mid())
			._a(new RB_lab(rec_addr.get_ufs_kette(" ", ADRESSE.plz, 	ADRESSE.ort))		._fsa(-2), 	new RB_gld()._ins(2)._left_mid())
			;

		}
	}

	private boolean is_lager_from_mandant(Rec20_adresse rec_addresse) throws myException{
		boolean is_mandant_lager = false;
		if(rec_addresse.__is_liefer_adresse() && rec_addresse._getMainAdresse().get_ufs_dbVal(ADRESSE.id_adresse).equals(id_adresse_mandant)) {
			is_mandant_lager = true;
		}
		return is_mandant_lager;
	}


}
