/**
 * rohstoff.businesslogic.bewegung2.detail
 * @author sebastien
 * @date 27.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.detail;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 27.03.2019
 *
 */
public class BG_STATION_DefinitionVektor extends B2_Abstract_DefinitionVektor {

	
	public BG_STATION_DefinitionVektor() throws myException {
		super();
		this
		._a(new E2_FieldInfo_Component("ID", BG_STATION.id_bg_station))

		._a(new E2_FieldInfo_Component(
				"ID Land", 						
				BG_STATION.id_land, 				 
				(r)->{return new RB_lab()._t(r.get_up_Rec21(LAND.id_land).get_fs_dbVal(LAND.beschreibung,"")).c();})
				) 
		
		._a(new E2_FieldInfo_Component("ID Adresse", 					BG_STATION.id_adresse, 				new B2_fieldInfoComp_Adresse(BG_STATION.id_adresse, true)))
		._a(new E2_FieldInfo_Component("ID Adresse Besitzer", 			BG_STATION.id_adresse_basis, 		new B2_fieldInfoComp_Adresse(BG_STATION.id_adresse_basis, true)))
		._a(new E2_FieldInfo_Component("ID Adresse Ladung Besitzer", 	BG_STATION.id_adresse_besitz_ldg, 	new B2_fieldInfoComp_Adresse(BG_STATION.id_adresse_besitz_ldg, true)))
		
		._a(new E2_FieldInfo_Component("Wiegekarte", 					BG_STATION.id_wiegekarte, 			null)) 
		._a(new E2_FieldInfo_Component("ID Storno info",				BG_STATION.id_bg_storno_info, 		null))
		._a(new E2_FieldInfo_Component("ID Del info",					BG_STATION.id_bg_del_info, 			null))
		._a(new E2_FieldInfo_Component("Wiegekartenkenner",				BG_STATION.wiegekartenkenner, 		null))
		._a(new E2_FieldInfo_Component("Kontrollmenge",					BG_STATION.kontrollmenge, 			null))
		._a(new E2_FieldInfo_Component("Name 1",						BG_STATION.name1, 					null))
		._a(new E2_FieldInfo_Component("Name 2",						BG_STATION.name2, 					null))
		._a(new E2_FieldInfo_Component("Name 3",						BG_STATION.name3, 					null))
		._a(new E2_FieldInfo_Component("Strasse",						BG_STATION.strasse, 				null))
		._a(new E2_FieldInfo_Component("Hausnummer",					BG_STATION.hausnummer, 				null))
		._a(new E2_FieldInfo_Component("PLZ",							BG_STATION.plz, 					null))
		._a(new E2_FieldInfo_Component("Ort",							BG_STATION.ort, 					null))
		._a(new E2_FieldInfo_Component("Ortzusatz",						BG_STATION.ortzusatz, 				null))
		._a(new E2_FieldInfo_Component("Oeffnungszeiten",				BG_STATION.oeffnungszeiten, 		null))
		._a(new E2_FieldInfo_Component("Telefon",						BG_STATION.telefon, 				null))
		._a(new E2_FieldInfo_Component("Fax",							BG_STATION.fax, 					null))
		._a(new E2_FieldInfo_Component("Timestamp in Mask",				BG_STATION.timestamp_in_mask, 		null))
		._a(new E2_FieldInfo_Component("Pos in Mask",					BG_STATION.pos_in_mask, 			null))
		;
	}

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung2.detail.B2_Abstract_DefinitionVektor#inner_grid_size()
	 */
	@Override
	public int[] inner_grid_size() {
		 return new int[]{200,210,350};
	}

	


}
