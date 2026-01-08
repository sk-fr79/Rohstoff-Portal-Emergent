package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_STD;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_TPA;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;


public class ES_CONST {

	public static String   HASHKEY_MASK_DAUGHTER_TARGETS = 			"HASHKEY_MASK_DAUGHTER_TARGETS";
	public static String   HASHKEY_MASK_LIST_ARCHIVMEDIEN = 		"HASHKEY_MASK_LIST_ARCHIVMEDIEN";
	public static String   HASHKEY_MASK_SHOW_INFO_REPLACETAGS = 	"HASHKEY_MASK_SHOW_INFO_REPLACETAGS";
	public static String   HASHKEY_MASK_SHOW_FIELDS_4_SEND = 		"HASHKEY_MASK_SHOW_FIELDS_4_SEND";

	
	public static String   HASHKEY_FIELDNAME_ID_EMAIL_SEND2 = "ID_EMAIL_SEND2";
	public static String   HASHKEY_FIELDNAME_ID_EMAIL_SEND3 = "ID_EMAIL_SEND3";
	public static String   HASHKEY_FIELDNAME_ID_EMAIL_SEND4 = "ID_EMAIL_SEND4";
	public static String   HASHKEY_FIELDNAME_ID_EMAIL_SEND5 = "ID_EMAIL_SEND5";
	
	
	//suchfeld in der Email-sende-tabelle zieladressen
	public static String   HASHKEY_FIELDNAME_SEARCH_RELEVANT_MAILADRESSES = "HASHKEY_FIELDNAME_SEARCH_RELEVANT_MAILADRESSES";


	
	
	public enum SEND_STATUS {
		SEND_NONE,
		SEND_PART,
		SEND_ALL,
	}
	

	/*
	 * definition, wie email versendet werden koennen
	 */
	public enum SEND_TYPE implements IF_enum_4_db {
		SINGLE(new MyE2_String("Einzelversand (Standard)"))
		,CC(new MyE2_String("CC: Gesamtversand mit CC-Mail"))
		,BCC(new MyE2_String("BCC: Gesamtversand mit BCC-Mail"))
		;
		
		private MyE2_String dropDownText = null;

		private SEND_TYPE(MyE2_String p_dropDownText) {
			this.dropDownText = p_dropDownText;
		}

		@Override
		public String db_val() {
			return this.name();
		}

		@Override
		public String user_text() {
			return this.dropDownText.CTrans();
		}

		@Override
		public String user_text_lang() {
			return this.dropDownText.CTrans();
		}

		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(SEND_TYPE.values(), emptyPairInFront);
		}
		
		public static SEND_TYPE find(String val) {
			if (S.isFull(val)) {
				for (SEND_TYPE type: SEND_TYPE.values()) {
					if (type.name().equals(val)) {
						return type;
					}
				}
			}
			return null;
		}
		
		
	}
	
	
	
	
	public enum STATUS_ORIGBELEG {
		NO_ORIG_FILE(false),           	//kein originalfile, d.h. alte belege vor automatischer archivierung
		ONE_ORIG_FILE(false),           //ein originalfile, d.h. OK
		MULTI_ORIG_FILES(true);         //mehrfache origfiles, d.h. fehler
		
		private boolean b_error = false;
		
		private STATUS_ORIGBELEG(boolean bError) { 
			this.b_error=bError;
		}
		
		public boolean is_Error() {
			return this.b_error;
		}
		
		public boolean is_OK() {
			return !this.b_error;
		}
		
	}

	/**
	 * 
			if (this.TableName.equals("JT_VKOPF_STD")) {
				return new RECORD_VKOPF_STD(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} else if (this.TableName.equals("JT_VKOPF_RG")) {
				return new RECORD_VKOPF_RG(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} else if (this.TableName.equals("JT_VKOPF_KON")) {
				return new RECORD_VKOPF_KON(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} else if (this.TableName.equals("JT_VKOPF_TPA")) {
				return new RECORD_VKOPF_TPA(cID_Vorgang).get_UP_RECORD_ADRESSE_id_adresse();
			} 

	 *
	 *
	 */
	

	
	public static Vector<ES_RECORD_ADRESSE> get_adressen(String baseTablename, String id_table) throws myException {
		Vector<ES_RECORD_ADRESSE> v_adressen = new Vector<>();

		_TAB tab = _TAB.find_TableFromBasename(baseTablename);
		
		if (tab != null) {
			MyRECORD rec = tab.get_RECORD(id_table);
			
			switch (tab) {
			case adresse: 
				v_adressen.add(new ES_RECORD_ADRESSE((RECORD_ADRESSE)rec));
				break;
			case vkopf_std: 
				v_adressen.add(new ES_RECORD_ADRESSE(((RECORD_VKOPF_STD)rec).get_UP_RECORD_ADRESSE_id_adresse()));
				break;
			case vkopf_rg: 
				v_adressen.add(new ES_RECORD_ADRESSE(((RECORD_VKOPF_RG)rec).get_UP_RECORD_ADRESSE_id_adresse()));
				break;

			case vkopf_tpa: 
				v_adressen.add(new ES_RECORD_ADRESSE(((RECORD_VKOPF_TPA)rec).get_UP_RECORD_ADRESSE_id_adresse()));
				break;

			case vkopf_kon: 
				v_adressen.add(new ES_RECORD_ADRESSE(((RECORD_VKOPF_KON)rec).get_UP_RECORD_ADRESSE_id_adresse()));
				break;

			case fibu: 
				v_adressen.add(new ES_RECORD_ADRESSE(((RECORD_FIBU)rec).get_UP_RECORD_ADRESSE_id_adresse()));
				break;

				
			case vpos_tpa_fuhre: 
				RECORD_VPOS_TPA_FUHRE fuhre = (RECORD_VPOS_TPA_FUHRE)rec;
				RECORD_ADRESSE rec_lieferant = fuhre.get_UP_RECORD_ADRESSE_id_adresse_start();
				RECORD_ADRESSE rec_abnehmer = fuhre.get_UP_RECORD_ADRESSE_id_adresse_ziel();
				RECORD_ADRESSE rec_spedition = fuhre.get_UP_RECORD_ADRESSE_id_adresse_spedition();
				if (rec_lieferant!=null) {
					ES_RECORD_ADRESSE ra = new ES_RECORD_ADRESSE(rec_lieferant);
					ra.set__titel(new MyE2_String("Lieferant:  "));
					ra.set__beschriftung(new MyE2_String().ut(ra.get__beschriftung().CTrans()));

					v_adressen.add(ra);
				}
				if (rec_abnehmer!=null) {
					ES_RECORD_ADRESSE ra = new ES_RECORD_ADRESSE(rec_abnehmer);
					ra.set__titel(new MyE2_String("Abnehmer:  "));
					ra.set__beschriftung(new MyE2_String().ut(ra.get__beschriftung().CTrans()));
					v_adressen.add(ra);
				}
				if (rec_spedition!=null) {
					ES_RECORD_ADRESSE ra = new ES_RECORD_ADRESSE(rec_spedition);
					ra.set__titel(new MyE2_String("Spediteur:  "));
					ra.set__beschriftung(new MyE2_String().ut(ra.get__beschriftung().CTrans()));

					v_adressen.add(ra);
				} else if (	fuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa()!=null && 
							fuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa()!=null &&
							fuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_UP_RECORD_ADRESSE_id_adresse()!=null) {
					
					ES_RECORD_ADRESSE ra = new ES_RECORD_ADRESSE(
							fuhre.get_UP_RECORD_VPOS_TPA_id_vpos_tpa().get_UP_RECORD_VKOPF_TPA_id_vkopf_tpa().get_UP_RECORD_ADRESSE_id_adresse());
					ra.set__titel(new MyE2_String("Spediteur (TPA):  "));
					ra.set__beschriftung(new MyE2_String().ut(ra.get__beschriftung().CTrans()));
					v_adressen.add(ra);
				}
				break;
				
				
//kann ergaenzt werden				
				
				
			default:
				break;

			}
			
		}
			
		return v_adressen;
	}
	
	
	
	
	
	
}
