package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.Mapper.FieldMapperOrSetter;
import panter.gmbh.Echo2.RB.Mapper.FieldMapperRecToRec;
import panter.gmbh.Echo2.RB.Mapper.FieldSetterRawToRec20;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.UmbuchungInfoMap;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT_POS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_MASK_TYPE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_STATUS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

/**
 * fest-vordefinierte struktur fuer die maskenanteile eines ST-Vektors
 * Konstrukt besteht immer aus folgenden teilen
 * 1 x BEWEGUNG (passiv, nur mitgefuehrt) 
 * 1 x BEWEGUNG_VEKTOR
 * 2 x BEWEGUNG_VEKTOR_POS
 * 4 x BEWEGUNG_ATOM 
 * 8 x BEWEGUNG_STATION
 * 
 * evtl. vorhandene verdeckte umbuchungen werden nicht in der maskenstruktur behandelt, sondern ueber zusatzkomponenten mit eigener speicherlogik
 * 
 *  
 * @author martin
 *
 */
//
//
public class __ST_MASTER_KEY  extends RB_KM implements IF_MasterKey {
	
	private int i_number = 0;


	//keystruktur, die immer vorhanden ist
	
	private MASK_STATUS    f_status = null;
	
	private Vector<IF_KeyChain>  v_son_keys = new Vector<>();

	private KEY_VEKT   		KV = null;
	
	private KEY_VEKT_POS  	KVP1 		= null;
	
	private KEY_ATOM   		KA1 		= null;
	private KEY_STATION 	KS1_START 	= null;
	private KEY_STATION 	KS1_ZIEL 	= null;


	private KEY_ATOM 		KA2			= null;
	private KEY_STATION 	KS2_START	= null;
	private KEY_STATION 	KS2_ZIEL	= null;

	
	private KEY_VEKT_POS  	KVP2 		= null;
	
	private KEY_ATOM 		KA3			= null;
	private KEY_STATION 	KS3_START	= null;
	private KEY_STATION 	KS3_ZIEL	= null;

	private KEY_ATOM 		KA4			= null;
	private KEY_STATION 	KS4_START	= null;
	private KEY_STATION 	KS4_ZIEL	= null;
	
	private ArrayList<FieldMapperOrSetter>		v_mappingsBeforeSaveMask = new ArrayList<>();
	private ArrayList<FieldMapperOrSetter>		v_mappingsAfterSaveDataObject = new ArrayList<>();
	private ArrayList<FieldMapperOrSetter>		v_mappingsBeforeSaveDataObject = new ArrayList<>();
	private ArrayList<UmbuchungInfoMap>			v_umbuchungsInfoMap = new ArrayList<UmbuchungInfoMap>();

	
	public __ST_MASTER_KEY(MASK_STATUS p_status) throws myException {
		super(_TAB.bewegung);
		
	
		this.f_status = p_status;
		
		this.KV = 	new KEY_VEKT(this);

		//block 1: start real nach LLL
		this.KVP1				= new KEY_VEKT_POS(this.KV);
		this.KA1 				= new KEY_ATOM(this.KVP1);
		this.KS1_START 			= new KEY_STATION(this.KA1, true);
		this.KS1_ZIEL 			= new KEY_STATION(this.KA1, false);
		this.KA2 				= new KEY_ATOM(this.KVP1);
		this.KS2_START 			= new KEY_STATION(this.KA2, true);
		this.KS2_ZIEL			= new KEY_STATION(this.KA2, false);
				
		//block 2: start LLL nach real
		this.KVP2				= new KEY_VEKT_POS(this.KV);
		this.KA3 				= new KEY_ATOM(this.KVP2);
		this.KS3_START			= new KEY_STATION(this.KA3, true);
		this.KS3_ZIEL 			= new KEY_STATION(this.KA3, false);
		this.KA4 				= new KEY_ATOM(this.KVP2);
		this.KS4_START 			= new KEY_STATION(this.KA4, true);
		this.KS4_ZIEL			= new KEY_STATION(this.KA4, false);
		
		// felder kopieren
		this.getFieldMappersAfterSaveDataObject().add(new FieldMapperRecToRec(KA1, BEWEGUNG_ATOM.e_preis, 					KA2, BEWEGUNG_ATOM.e_preis));
		this.getFieldMappersAfterSaveDataObject().add(new FieldMapperRecToRec(KA1, BEWEGUNG_ATOM.e_preis_result_netto_mge,	KA2, BEWEGUNG_ATOM.e_preis_result_netto_mge));
		this.getFieldMappersAfterSaveDataObject().add(new FieldMapperRecToRec(KA1, BEWEGUNG_ATOM.e_preis_result_brutto_mge,   KA2, BEWEGUNG_ATOM.e_preis_result_brutto_mge));
		this.getFieldMappersAfterSaveDataObject().add(new FieldMapperRecToRec(KA1, BEWEGUNG_ATOM.e_preis, 					KA3, BEWEGUNG_ATOM.e_preis));
		this.getFieldMappersAfterSaveDataObject().add(new FieldMapperRecToRec(KA1, BEWEGUNG_ATOM.e_preis_result_netto_mge,	KA3, BEWEGUNG_ATOM.e_preis_result_netto_mge));
		this.getFieldMappersAfterSaveDataObject().add(new FieldMapperRecToRec(KA1, BEWEGUNG_ATOM.e_preis_result_brutto_mge,   KA3, BEWEGUNG_ATOM.e_preis_result_brutto_mge));

		
		
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung.seq_currval(),  this.KV,  				BEWEGUNG_VEKTOR.id_bewegung, 		true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KVP1,  	BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KA1,  		BEWEGUNG_ATOM.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor_pos.seq_currval(), this.KA1, 	BEWEGUNG_ATOM.id_bewegung_vektor_pos, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS1_START, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS1_ZIEL, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KA2,  		BEWEGUNG_ATOM.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor_pos.seq_currval(), this.KA2, 	BEWEGUNG_ATOM.id_bewegung_vektor_pos, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS2_START, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS2_ZIEL, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));

		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KVP2,  	BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KA3,  		BEWEGUNG_ATOM.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor_pos.seq_currval(), this.KA3, 	BEWEGUNG_ATOM.id_bewegung_vektor_pos, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS3_START, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS3_ZIEL, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KA4,  		BEWEGUNG_ATOM.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor_pos.seq_currval(), this.KA4, 	BEWEGUNG_ATOM.id_bewegung_vektor_pos, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS4_START, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS4_ZIEL, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		
		//positionsnummern
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("1", 	this.KVP1, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("1", 	this.KA1, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("2",	this.KA2, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("10000", 	this.KVP2, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("9999", 	this.KA3, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("10000", 	this.KA4, BEWEGUNG_VEKTOR_POS.posnr, true, true));

		//variante des vektors
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20("1", 										this.KV, 		BEWEGUNG_VEKTOR.posnr, 					true, true));
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20(ENUM_VEKTOR_TYP.WE.dbVal4SqlTerm(),		this.KV, 		BEWEGUNG_VEKTOR.variante, 				true, true));
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20(ENUM_VEKTOR_STATUS.AKTIV.dbVal4SqlTerm(),	this.KV, 		BEWEGUNG_VEKTOR.status, 				true, false));

		
		//die ID_ADRESSE_WB_START und ID_ADRESSE_WB_ZIEL
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_adresse, this.KA1, BEWEGUNG_ATOM.id_adresse_wb_start, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS4_ZIEL, BEWEGUNG_STATION.id_adresse,  this.KA1,  BEWEGUNG_ATOM.id_adresse_wb_ziel, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_adresse, this.KA2, BEWEGUNG_ATOM.id_adresse_wb_start, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS4_ZIEL, BEWEGUNG_STATION.id_adresse,  this.KA2,  BEWEGUNG_ATOM.id_adresse_wb_ziel, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_adresse, this.KA3, BEWEGUNG_ATOM.id_adresse_wb_start, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS4_ZIEL, BEWEGUNG_STATION.id_adresse,  this.KA3,  BEWEGUNG_ATOM.id_adresse_wb_ziel, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_adresse, this.KA4, BEWEGUNG_ATOM.id_adresse_wb_start, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS4_ZIEL, BEWEGUNG_STATION.id_adresse,  this.KA4,  BEWEGUNG_ATOM.id_adresse_wb_ziel, true, true));
		
		
		//die ID_BEWEGUNGSTATION_LOGI_START und ID_BEWEGUNGSTATION_LOGI_ZIEL koennen erst in der offenen transaktion zugefuegt werden
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_bewegung_station, this.KA1, BEWEGUNG_ATOM.id_bewegungstation_logi_start));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS4_ZIEL,  BEWEGUNG_STATION.id_bewegung_station, this.KA1, BEWEGUNG_ATOM.id_bewegungstation_logi_ziel));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_bewegung_station, this.KA2, BEWEGUNG_ATOM.id_bewegungstation_logi_start));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS4_ZIEL,  BEWEGUNG_STATION.id_bewegung_station, this.KA2, BEWEGUNG_ATOM.id_bewegungstation_logi_ziel));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_bewegung_station, this.KA3, BEWEGUNG_ATOM.id_bewegungstation_logi_start));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS4_ZIEL,  BEWEGUNG_STATION.id_bewegung_station, this.KA3, BEWEGUNG_ATOM.id_bewegungstation_logi_ziel));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_bewegung_station, this.KA4, BEWEGUNG_ATOM.id_bewegungstation_logi_start));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS4_ZIEL,  BEWEGUNG_STATION.id_bewegung_station, this.KA4, BEWEGUNG_ATOM.id_bewegungstation_logi_ziel));

		
	}
	

	
	public Vector<KEY_VEKT>  get_v_key_vektor() {
		Vector<KEY_VEKT> v = new Vector<>();
		v.add(KV);
		return v;
	}
	
	
	@Override
	public KEY_VEKT k_vektor() {
		return KV;
	}
	
	public KEY_VEKT_POS k_vektor_pos_left() {
		return KVP1;
	}

	public KEY_VEKT_POS k_vektor_pos_right() {
		return KVP2;
	}
	
	
	public KEY_ATOM k_vektor_pos_left__atom_left(){
		return KA1;
	}
	
	public KEY_STATION k_vektor_pos_left__atom_left__station_start(){
		return KS1_START;
	}
	
	public KEY_STATION k_vektor_pos_left__atom_left__station_ziel(){
		return KS1_ZIEL;
	}
	
	public KEY_ATOM k_vektor_pos_left__atom_right(){
		return KA2;
	}
	
	public KEY_STATION k_vektor_pos_left__atom_right__station_start(){
		return KS2_START;
	}
	
	public KEY_STATION k_vektor_pos_left__atom_right__station_ziel(){
		return KS2_ZIEL;
	}
	
	public KEY_ATOM k_vektor_pos_right__atom_left(){
		return KA3;
	}
	
	public KEY_STATION k_vektor_pos_right__atom_left__station_start(){
		return KS3_START;
	}
	
	public KEY_STATION k_vektor_pos_right__atom_left__station_ziel(){
		return KS3_ZIEL;
	}
	
	public KEY_ATOM k_vektor_pos_right__atom_right(){
		return KA4;
	}

	public KEY_STATION k_vektor_pos_right__atom_right__station_start(){
		return KS4_START;
	}
	
	public KEY_STATION k_vektor_pos_right__atom_right__station_ziel(){
		return KS4_ZIEL;
	}
	
	public String get_vektor_pos_left_posnr(){
		return "1";
	}

	public String get_vektor_pos_right_posnr(){
		return "2";
	}

	public String get_vektor_pos_left__atom_left_posnr(){
		return "1";
	}

	public String get_vektor_pos_left__atom_right_posnr(){
		return "2";
	}
	
	public String get_vektor_pos_right__atom_left_posnr(){
		return "3";
	}
	
	public String get_vektor_pos_right__atom_right_posnr(){
		return "4";
	}
	
	public MASK_STATUS get_mask_status() {
		return f_status;
	}

	@Override
	public __ST_MASTER_KEY set_mask_status(MASK_STATUS  status) {
		this.f_status = status;
		return this;
	}

	@Override
	public Vector<IF_KeyChain> get_direct_child_keys() {
		return this.v_son_keys;
	}


	/**
	 * erhoeht um 1 und gibt diesen wert zurueck
	 * @return
	 */
	@Override
	public int get_next_number() throws myException {
		this.i_number++;
		return this.i_number;
	}

	@Override
	public IF_KeyChain get_father_key() {
		return null;
	}



	@Override
	public ENUM_MASK_TYPE getMaskType() {
		return ENUM_MASK_TYPE.STRECKE;
	}

	

	@Override
	public ArrayList<FieldMapperOrSetter> getFieldMappersAfterSaveDataObject() {
		return v_mappingsAfterSaveDataObject;
	}

	@Override
	public ArrayList<FieldMapperOrSetter> 	getFieldMappersBeforeSaveDataObject() {
		return v_mappingsBeforeSaveDataObject;
	}

	@Override
	public ArrayList<FieldMapperOrSetter> getFieldMappersBeforeSaveMask() {
		return this.v_mappingsBeforeSaveMask;
	}

	@Override
	public ArrayList<UmbuchungInfoMap> getUmbuchungen() {
		return this.v_umbuchungsInfoMap;
	}

	@Override
	public KEY_ATOM getKeyAtomStart() {
		return this.KA1;
	}

	@Override
	public KEY_ATOM getKeyAtomZiel() {
		return this.KA4;
	}



}
