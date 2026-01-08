/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import java.util.HashMap;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class AH7__ActualizeDropDownsInMask {

	public AH7__ActualizeDropDownsInMask(RB_ComponentMap rbMASK, MyE2_MessageVector mv, boolean refill) throws myException {
		super();
		
		RB_MaskController  	con = 	new RB_MaskController(rbMASK);

		VEK<IF_Field>    vAdressFields = new VEK<IF_Field>();
		vAdressFields._a(AH7_STEUERDATEI.id_1_abfallerzeuger);
		vAdressFields._a(AH7_STEUERDATEI.id_1_import_empfaenger);
		vAdressFields._a(AH7_STEUERDATEI.id_1_verbr_veranlasser);
		vAdressFields._a(AH7_STEUERDATEI.id_1_verwertungsanlage);
		vAdressFields._a(AH7_STEUERDATEI.id_2_abfallerzeuger);
		vAdressFields._a(AH7_STEUERDATEI.id_2_import_empfaenger);
		vAdressFields._a(AH7_STEUERDATEI.id_2_verbr_veranlasser);
		vAdressFields._a(AH7_STEUERDATEI.id_2_verwertungsanlage);
		vAdressFields._a(AH7_STEUERDATEI.id_3_abfallerzeuger);
		vAdressFields._a(AH7_STEUERDATEI.id_3_import_empfaenger);
		vAdressFields._a(AH7_STEUERDATEI.id_3_verbr_veranlasser);
		vAdressFields._a(AH7_STEUERDATEI.id_3_verwertungsanlage);
		
		HashMap<IF_Field, String>  hmLoadedValues = new HashMap<>();

		// hier die geladenen daten wegspeichern
		for (IF_Field f: vAdressFields) {
			//DEBUG._print("maskenwerte: "+f.fn()+": "+S.NN(con.get_liveVal(f)));
			hmLoadedValues.put(f, con.get_liveVal(f));
			((AH7_Mask_selField_adress)con.get_comp(f, mv))._clear();
			((AH7_Mask_selField_adress)con.get_comp(f, mv))._putEmptyValInFront();
			((AH7_Mask_selField_adress)con.get_comp(f, mv))._setActiveDBVal("");
		}
		
		//zuerst die id_adressen_geo beschaffen
		MyLong l_start_geo = con.get_MyLong_liveVal(AH7_STEUERDATEI.id_adresse_geo_start);
		MyLong l_ziel_geo = con.get_MyLong_liveVal(AH7_STEUERDATEI.id_adresse_geo_ziel);
		
			
		if (l_start_geo!=null && l_ziel_geo!=null && l_start_geo.isOK() && l_ziel_geo.isOK()) {
			//alle leermachen und neu fuellen
			for (IF_Field f: vAdressFields) {
				((AH7_Mask_selField_adress)con.get_comp(f, mv))._populateWith6Adresses(l_start_geo.get_cUF_LongString(), l_ziel_geo.get_cUF_LongString());
				((AH7_Mask_selField_adress)con.get_comp(f, mv))._setActiveDBVal("");   //zuerst immer den leeren wert einstellen
			}
		}
		
		if (refill) {
			//falls die maske bereits werte enthalten hat, dann den status wiederherstellen (wenn moeglich)
			if (rbMASK.rb_get_belongs_to().rb_Actual_DataobjectCollector().get(_TAB.ah7_steuerdatei.rb_km()).rb_MASK_STATUS()!=MASK_STATUS.NEW) {
				for (IF_Field f: vAdressFields) {
					if (S.isFull(hmLoadedValues.get(f))) {
						if (((AH7_Mask_selField_adress)con.get_comp(f, mv)).getVCompleteDBVals().contains(hmLoadedValues.get(f))) {
							((AH7_Mask_selField_adress)con.get_comp(f, mv)).rb_set_db_value_manual(hmLoadedValues.get(f));
						}
					}
				}
			}
		}
			
		
	}

}
