package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

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
import panter.gmbh.basics4project.DB_ENUMS.ENUM_VEKTOR_POS_TYP;
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
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_STATUS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

/**
 * fest-vordefinierte struktur fuer die maskenanteile eines LL-Vektors
 * Konstrukt besteht immer aus folgenden teilen
 * 1 x BEWEGUNG (passiv, nur mitgefuehrt) 
 * 1 x BEWEGUNG_VEKTOR
 * 2 x BEWEGUNG_VEKTOR_POS
 * 2 x BEWEGUNG_ATOM 
 * 4 x BEWEGUNG_STATION
 * 
 * evtl. vorhandene verdeckte umbuchungen werden nicht in der maskenstruktur behandelt, sondern ueber zusatzkomponenten mit eigener speicherlogik
 * 
 *  
 * @author martin
 *
 */
//
//
public class __LL_MASTER_KEY  extends RB_KM implements IF_MasterKey {
	
	private int i_number = 0;


	//keystruktur, die immer vorhanden ist
	private KEY_VEKT   		KV 			= null;
	
	private KEY_VEKT_POS  	KVP 		= null;
	
	private KEY_ATOM   		KA1 		= null;
	private KEY_STATION   	KS1_START 	= null;
	private KEY_STATION   	KS1_ZIEL	= null;
	
	private KEY_ATOM   		KA2 		= null;
	private KEY_STATION   	KS2_START 	= null;
	private KEY_STATION   	KS2_ZIEL 	= null;
	
	
	private MASK_STATUS    f_status = null;
	
	private Vector<IF_KeyChain>  v_son_keys = new Vector<>();
	
	private ArrayList<FieldMapperOrSetter>		v_mappingsBeforeSaveMask = new ArrayList<>();
	private ArrayList<FieldMapperOrSetter>		v_mappingsAfterSaveDataObject = new ArrayList<>();
	private ArrayList<FieldMapperOrSetter>		v_mappingsBeforeSaveDataObject = new ArrayList<>();
	private ArrayList<UmbuchungInfoMap>			v_umbuchungsInfoMap = new ArrayList<UmbuchungInfoMap>();

	
	public __LL_MASTER_KEY(MASK_STATUS p_status) throws myException {
		super(_TAB.bewegung);
	
		this.f_status = p_status;
		
		this.KV = 	new KEY_VEKT(this);

		this.KVP = 				new KEY_VEKT_POS(this.KV);
		
		//block 1: start real nach LLL
		this.KA1 = 				new KEY_ATOM(this.KVP);
		this.KS1_START=  		new KEY_STATION(this.KA1, true);
		this.KS1_ZIEL=  		new KEY_STATION(this.KA1, false);

		//block 2: start LLL nach real
		this.KA2 = 				new KEY_ATOM(this.KVP);
		this.KS2_START=  		new KEY_STATION(this.KA2, true);
		this.KS2_ZIEL=  		new KEY_STATION(this.KA2, false);
		
		
		
		
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung.seq_currval(),  this.KV,  				BEWEGUNG_VEKTOR.id_bewegung, 		true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KVP,  	BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KA1,  		BEWEGUNG_ATOM.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor_pos.seq_currval(), this.KA1, 	BEWEGUNG_ATOM.id_bewegung_vektor_pos, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS1_START, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS1_ZIEL, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));

		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor.seq_currval(), this.KA2,  		BEWEGUNG_ATOM.id_bewegung_vektor, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_vektor_pos.seq_currval(), this.KA2, 	BEWEGUNG_ATOM.id_bewegung_vektor_pos, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS2_START, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20(_TAB.bewegung_atom.seq_currval(), this.KS2_ZIEL, 	BEWEGUNG_STATION.id_bewegung_atom, true, false));

		
		//positionsnummern
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("1", this.KVP, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("1", this.KA1, BEWEGUNG_VEKTOR_POS.posnr, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldSetterRawToRec20("10000", this.KA2, BEWEGUNG_VEKTOR_POS.posnr, true, true));

		//variante des vektors
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20("1", 										this.KV, 		BEWEGUNG_VEKTOR.posnr, 					true, true));
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20(ENUM_VEKTOR_TYP.WE.dbVal4SqlTerm(),		this.KV, 		BEWEGUNG_VEKTOR.variante, 				true, true));
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20(ENUM_VEKTOR_STATUS.AKTIV.dbVal4SqlTerm(),	this.KV, 		BEWEGUNG_VEKTOR.status, 				true, false));

		//vectorpos
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20("1", 										this.KVP, 		BEWEGUNG_VEKTOR_POS.posnr, 				true, true));
		this.v_mappingsBeforeSaveDataObject.add(new FieldSetterRawToRec20(ENUM_VEKTORPOS_TYP.LL_MAIN.db_val(), 		this.KVP, 		BEWEGUNG_VEKTOR_POS.pos_typ, 			true, true));
		
		
		
		//die ID_ADRESSE_WB_START und ID_ADRESSE_WB_ZIEL
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_adresse, this.KA1, BEWEGUNG_ATOM.id_adresse_wb_start, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS2_ZIEL, BEWEGUNG_STATION.id_adresse,  this.KA1,  BEWEGUNG_ATOM.id_adresse_wb_ziel, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_adresse, this.KA2, BEWEGUNG_ATOM.id_adresse_wb_start, true, true));
		this.v_mappingsBeforeSaveMask.add(new FieldMapperRecToRec(this.KS2_ZIEL, BEWEGUNG_STATION.id_adresse,  this.KA2,  BEWEGUNG_ATOM.id_adresse_wb_ziel, true, true));


		//die ID_BEWEGUNGSTATION_LOGI_START und ID_BEWEGUNGSTATION_LOGI_ZIEL koennen erst in der offenen transaktion zugefuegt werden
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_bewegung_station, this.KA1, BEWEGUNG_ATOM.id_bewegungstation_logi_start));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS2_ZIEL,  BEWEGUNG_STATION.id_bewegung_station, this.KA1, BEWEGUNG_ATOM.id_bewegungstation_logi_ziel));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS1_START, BEWEGUNG_STATION.id_bewegung_station, this.KA2, BEWEGUNG_ATOM.id_bewegungstation_logi_start));
		this.v_mappingsAfterSaveDataObject.add(new FieldMapperRecToRec(this.KS2_ZIEL,  BEWEGUNG_STATION.id_bewegung_station, this.KA2, BEWEGUNG_ATOM.id_bewegungstation_logi_ziel));

		
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
	
	public KEY_VEKT_POS k_vektor_pos() {
		return KVP;
	}


	public KEY_ATOM k_atom_left() {
		return KA1;
	}

	public String get_atom_left_posnr(){
		return "1";
	}

	public KEY_STATION k_atom_left__lager_start() {
		return KS1_START;
	}

	public KEY_STATION k_atom_left__lager_ziel() {
		return KS1_ZIEL;
	}
	
	public KEY_ATOM k_atom_right() {
		return KA2;
	}


	public String get_atom_right_posnr(){
		return "2";
	}
	
	public KEY_STATION k_atom_right__lager_start() {
		return KS2_START;
	}

	public KEY_STATION k_atom_right__lager_ziel() {
		return KS2_ZIEL;
	}

	
	public MASK_STATUS get_mask_status() {
		return f_status;
	}

	@Override
	public __LL_MASTER_KEY set_mask_status(MASK_STATUS  status) {
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
		return ENUM_MASK_TYPE.LAGER_ZU_LAGER;
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
		return this.KA2;
	}


}
