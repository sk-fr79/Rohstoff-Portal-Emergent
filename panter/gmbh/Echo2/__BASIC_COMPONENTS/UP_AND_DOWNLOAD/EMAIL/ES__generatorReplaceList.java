package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.Replacer.RP_GeneratorReplacelistFromRecord;
import panter.gmbh.indep.exceptions.myException;

public class ES__generatorReplaceList extends RP_GeneratorReplacelistFromRecord {
	
	private String tableBase = null;
	private String tableId = null;
	
	/**
	 * 
	 * @param p_tableBase
	 * @param p_tableId
	 * @throws myException
	 */
	public ES__generatorReplaceList(String p_tableBase, String p_tableId) throws myException {
		super();
		this.tableBase = p_tableBase;
		this.tableId = p_tableId;
		
		if (S.isFull(this.tableBase) && S.isFull(this.tableId)) {
			_TAB tab = _TAB.find_TableFromBasename(this.tableBase);
			if (tab != null) {
				this._a(tab.get_RECORD(this.tableId));
				
				
				//jetzt die speziellen faelle behandeln
				if (tab == _TAB.adresse) {
					RECORD_ADRESSE rec_adresse = new RECORD_ADRESSE(this.tableId);
					this._a(rec_adresse.get_UP_RECORD_ANREDE_id_anrede())
						._a(rec_adresse.get_UP_RECORD_SPRACHE_id_sprache())
						._a(rec_adresse.get_UP_RECORD_USER_id_user(),"HAENDLER_FIRMENSTAMM")
						._a(rec_adresse.get_UP_RECORD_USER_id_user_sachbearbeiter(),"SACHBEARBEITER_FIRMENSTAMM")
						._a(rec_adresse.get_UP_RECORD_LAND_id_land())
						._a(rec_adresse.get_UP_RECORD_USER_id_user_sachbearbeiter())
						._a(rec_adresse.get_UP_RECORD_WAEHRUNG_id_waehrung())
						._a(rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().size()>0?rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0):null);
				} else if (tab == _TAB.fibu) {
					RECORD_FIBU  	rec_fibu = new RECORD_FIBU(this.tableId);
					RECORD_ADRESSE 	rec_adresse = rec_fibu.get_UP_RECORD_ADRESSE_id_adresse();
					
					if (rec_adresse != null) {
						this._a(rec_adresse.get_UP_RECORD_ANREDE_id_anrede())
								._a(rec_adresse.get_UP_RECORD_SPRACHE_id_sprache())
								._a(rec_adresse.get_UP_RECORD_LAND_id_land())
								._a(rec_adresse.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen(),"LIEFERBED_EK")
								._a(rec_adresse.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk(),"LIEFERBED_VK")
								._a(rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().size()>0?rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0):null);
					}					

					RECORD_VKOPF_RG rec_rech_gut = rec_fibu.get_UP_RECORD_VKOPF_RG_id_vkopf_rg();
					if (rec_rech_gut != null) {
						this._a(rec_rech_gut)
							._a(rec_rech_gut.get_UP_RECORD_USER_id_user(),"ERFASSER_RECH_GUT")
							._a(rec_rech_gut.get_UP_RECORD_USER_id_user_ansprech_intern(),"HAENDLER_RECH_GUT")
							._a(rec_rech_gut.get_UP_RECORD_USER_id_user_sachbearb_intern(),"SACHBEARBEITER_RECH_GUT")
							._a(rec_rech_gut.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd())
							._a(rec_rech_gut.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen())
							;
					}
				
				} else if (tab == _TAB.vkopf_rg) {
					RECORD_VKOPF_RG  rec_rech_gut = new RECORD_VKOPF_RG(this.tableId);
					RECORD_ADRESSE 	rec_adresse = rec_rech_gut.get_UP_RECORD_ADRESSE_id_adresse();
					
					if (rec_adresse != null) {
						this._a(rec_adresse.get_UP_RECORD_ANREDE_id_anrede())
								._a(rec_adresse.get_UP_RECORD_SPRACHE_id_sprache())
								._a(rec_adresse.get_UP_RECORD_LAND_id_land())
								._a(rec_adresse.get_UP_RECORD_USER_id_user(),"HAENDLER_FIRMENSTAMM")
								._a(rec_adresse.get_UP_RECORD_USER_id_user_sachbearbeiter(),"SACHBEARBEITER_FIRMENSTAMM")
								._a(rec_adresse.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen(),"LIEFERBED_EK")
								._a(rec_adresse.get_UP_RECORD_LIEFERBEDINGUNGEN_id_lieferbedingungen_vk(),"LIEFERBED_VK")
								._a(rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().size()>0?rec_adresse.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0):null);
					}					
	
					if (rec_rech_gut != null) {
						this._a(rec_rech_gut)
							._a(rec_rech_gut.get_UP_RECORD_USER_id_user(),"ERFASSER_RECH_GUT")
							._a(rec_rech_gut.get_UP_RECORD_USER_id_user_ansprech_intern(),"HAENDLER_RECH_GUT")
							._a(rec_rech_gut.get_UP_RECORD_USER_id_user_sachbearb_intern(),"SACHBEARBEITER_RECH_GUT")
							._a(rec_rech_gut.get_UP_RECORD_WAEHRUNG_id_waehrung_fremd())
							._a(rec_rech_gut.get_UP_RECORD_ZAHLUNGSBEDINGUNGEN_id_zahlungsbedingungen())
							;
					}
					
				}
			}
		}

		
		
		
		
		
		
		
	}

	
	
	
	
	
	
}