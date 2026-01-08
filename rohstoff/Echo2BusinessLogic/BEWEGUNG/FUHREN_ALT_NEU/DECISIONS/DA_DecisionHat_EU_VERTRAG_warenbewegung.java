package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS.DA_DecisionHat_EU_VERTRAG.FEHLERSTATUS;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class DA_DecisionHat_EU_VERTRAG_warenbewegung {


	public RECORD_ADRESSE 		adress_start = null;
	public RECORD_ADRESSE 		adress_ziel = null;
	public RECORD_ARTIKEL_BEZ 	sort_at_start = null;
	public RECORD_ARTIKEL_BEZ 	sort_at_target = null;
	public MyDate         		date_execution = null;
	public FEHLERSTATUS         status_links_jur = FEHLERSTATUS.UNBEWERTET;
	public FEHLERSTATUS         status_rechts_jur = FEHLERSTATUS.UNBEWERTET;
	public FEHLERSTATUS         status_links_geo = FEHLERSTATUS.UNBEWERTET;
	public FEHLERSTATUS         status_rechts_geo = FEHLERSTATUS.UNBEWERTET;
	
	private boolean             only_transport = false;
	private boolean             left_is_relevant = false;
	private boolean             right_is_relevant = false;
	
	private boolean    			pruefung_unterdrueckt = false; 
	
	public DA_DecisionHat_EU_VERTRAG_warenbewegung(RECORD_VPOS_TPA_FUHRE recFu) throws myException {
		super();
		this.adress_start=recFu.get_UP_RECORD_ADRESSE_id_adresse_lager_start();
		this.adress_ziel=recFu.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel();
		this.sort_at_start=recFu.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
		this.sort_at_target=recFu.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
		this.date_execution=new MyDate(recFu.get_DATUM_ABLADEN_cF_NN(recFu.get_DATUM_ANLIEFERUNG_cF_NN("")));
		
		this.only_transport=recFu.is_OHNE_ABRECHNUNG_YES();
		
		this.pruefung_unterdrueckt = recFu.is_OHNE_AVV_VERTRAG_CHECK_YES();
		
	}

//	
//	public DA_DecicisonHat_EU_VERTRAG_warenbewegung(RECORD_VPOS_TPA_FUHRE_ORT recFuo) throws myException {
//		super();
//		
//		if (recFuo.get_DEF_QUELLE_ZIEL_cUF().equals("EK")) {
//			RECORD_VPOS_TPA_FUHRE  recFU = recFuo.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
//			this.adress_start=recFuo.get_UP_RECORD_ADRESSE_id_adresse_lager();
//			this.adress_ziel=recFU.get_UP_RECORD_ADRESSE_id_adresse_lager_ziel();
//			this.sort_at_start=recFuo.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
//			this.sort_at_target=recFU.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_vk();
//			this.date_execution=new MyDate(recFuo.get_DATUM_LADE_ABLADE_cF_NN(recFU.get_DATUM_ANLIEFERUNG_cF_NN("")));
//			this.only_transport=recFU.is_OHNE_ABRECHNUNG_YES();
//			this.pruefung_unterdrueckt = false;
//		} else if (recFuo.get_DEF_QUELLE_ZIEL_cUF().equals("VK")) {
//			RECORD_VPOS_TPA_FUHRE  recFU = recFuo.get_UP_RECORD_VPOS_TPA_FUHRE_id_vpos_tpa_fuhre();
//			this.adress_start=recFU.get_UP_RECORD_ADRESSE_id_adresse_lager_start();
//			this.adress_ziel=recFuo.get_UP_RECORD_ADRESSE_id_adresse_lager();
//			this.sort_at_start=recFU.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez_ek();
//			this.sort_at_target=recFuo.get_UP_RECORD_ARTIKEL_BEZ_id_artikel_bez();
//			this.date_execution=new MyDate(recFuo.get_DATUM_LADE_ABLADE_cF_NN(recFU.get_DATUM_ANLIEFERUNG_cF_NN("")));
//			this.only_transport=recFU.is_OHNE_ABRECHNUNG_YES();
//			this.pruefung_unterdrueckt = false;
//			
//		} else {
//			throw new myException("False definition FUHRE_ORT: DEF_QUELLE_ZIEL !!");
//		}
//		
//	}


	/**
	 * 
	 * @param status
	 * @param b_geo
	 */
	public void set_status_left(FEHLERSTATUS status, boolean b_geo) {
			if (b_geo) {
				this.status_links_geo=status;
			} else {
				this.status_links_jur=status;
			}
	}

	/**
	 * 
	 * @param status
	 * @param b_geo
	 */
	public void set_status_right(FEHLERSTATUS status,boolean b_geo) {
		if (b_geo) {
			this.status_rechts_geo=status;
		} else {
			this.status_rechts_jur=status;
		}
	}
	
	
	public boolean has_fehlerstatus() {
		if (  this.status_links_geo.is_relevant_4_warn()
			||this.status_links_jur.is_relevant_4_warn()
			||this.status_rechts_geo.is_relevant_4_warn()
			||this.status_rechts_jur.is_relevant_4_warn()) {
			return true;
		}
		return false;
	}

	
	public boolean has_fehlerstatus_ladeseite() {
		if (  this.status_links_geo.is_relevant_4_warn()
			||this.status_links_jur.is_relevant_4_warn()
			) {
			return true;
		}
		return false;
	}
	
	public boolean has_fehlerstatus_abladeseite() {
		if (  this.status_rechts_geo.is_relevant_4_warn()
			||this.status_rechts_jur.is_relevant_4_warn()) {
			return true;
		}
		return false;
	}

	
	//die string[]arrays enthalten 4 [id_adresse-main] [name1 name2 ort Hauptadresse] [name1 name2 ort Lieferadresse] [kein vertrag/abgelaufen]
	public Vector<String[]> get_fehlerliste() throws myException {
		Vector<String[]> v_rueck = new Vector<>();
		
		RECORD_ADRESSE_extend ra_start_geo = 	new RECORD_ADRESSE_extend(this.adress_start);
		RECORD_ADRESSE_extend ra_ziel_geo = 	new RECORD_ADRESSE_extend(this.adress_ziel);
		RECORD_ADRESSE_extend ra_start_jur = 	new RECORD_ADRESSE_extend(ra_start_geo.get_main_Adress());
		RECORD_ADRESSE_extend ra_ziel_jur = 	new RECORD_ADRESSE_extend(ra_ziel_geo.get_main_Adress());
		
		if (status_links_jur.is_relevant_4_warn()) {
			String[] ar = new String[4];
			ar[0]=ra_start_jur.get_ID_ADRESSE_cF();
			ar[1]=ra_start_jur.get__FullNameAndAdress_Typ1();
			ar[2]=new MyE2_String("Firmenadresse").CTrans();
			ar[3]=status_links_jur==FEHLERSTATUS.KEIN_VERTRAG?new MyE2_String("Kein EU-Vertrag erfaﬂt (Lieferant)").CTrans():new MyE2_String("Der EU-Vertrag ist abgelaufen (Lieferant)").CTrans();
			v_rueck.add(ar);
		}
		
		if (status_rechts_jur.is_relevant_4_warn()) {
			String[] ar = new String[4];
			ar[0]=ra_ziel_jur.get_ID_ADRESSE_cF();
			ar[1]=ra_ziel_jur.get__FullNameAndAdress_Typ1();
			ar[2]=new MyE2_String("Firmenadresse").CTrans();
			ar[3]=status_rechts_jur==FEHLERSTATUS.KEIN_VERTRAG?new MyE2_String("Kein EU-Vertrag erfaﬂt (Abnehmer)").CTrans():new MyE2_String("Der EU-Vertrag ist abgelaufen (Abnehmer)").CTrans();
			v_rueck.add(ar);
		}
		
		if (status_links_geo.is_relevant_4_warn()) {
			String[] ar = new String[4];
			ar[0]=ra_start_geo.get_main_Adress().get_ID_ADRESSE_cF();
			ar[1]=ra_start_geo.get_main_Adress().get__FullNameAndAdress_Typ1();
			ar[2]=ra_start_geo.get__FullNameAndAdress_Typ1();
			ar[3]=status_links_geo==FEHLERSTATUS.KEIN_VERTRAG?new MyE2_String("Kein EU-Vertrag erfaﬂt (Ladestation)").CTrans():new MyE2_String("Der EU-Vertrag ist abgelaufen (Ladestation)").CTrans();
			v_rueck.add(ar);
		}
		
		if (status_rechts_geo.is_relevant_4_warn()) {
			String[] ar = new String[4];
			ar[0]=ra_ziel_geo.get_main_Adress().get_ID_ADRESSE_cF();
			ar[1]=ra_ziel_geo.get_main_Adress().get__FullNameAndAdress_Typ1();
			ar[2]=ra_ziel_geo.get__FullNameAndAdress_Typ1();
			ar[3]=status_rechts_geo==FEHLERSTATUS.KEIN_VERTRAG?new MyE2_String("Kein EU-Vertrag erfaﬂt (Abladestation)").CTrans():new MyE2_String("Der EU-Vertrag ist abgelaufen (Abladestation)").CTrans();
			v_rueck.add(ar);
		}
		return v_rueck;
	}


	public boolean is_only_transport() {
		return only_transport;
	}
	public boolean is_real_business() {
		return !only_transport;
	}


	public boolean is_left_is_relevant() {
		return left_is_relevant;
	}


	public void set_left_is_relevant(boolean left_is_relevant) {
		this.left_is_relevant = left_is_relevant;
	}


	public boolean is_right_is_relevant() {
		return right_is_relevant;
	}


	public void set_right_is_relevant(boolean right_is_relevant) {
		this.right_is_relevant = right_is_relevant;
	}


	public boolean is_pruefung_unterdrueckt() {
		return pruefung_unterdrueckt;
	}
	

	
	
}
