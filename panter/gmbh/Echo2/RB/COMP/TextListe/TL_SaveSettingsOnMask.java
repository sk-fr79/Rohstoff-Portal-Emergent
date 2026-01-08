/**
 * panter.gmbh.Echo2.RB.COMP.TextListe
 * @author martin
 * @date 19.02.2020
 * 
 */
package panter.gmbh.Echo2.RB.COMP.TextListe;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.UserSettings.E2_Usersetting_Vector_of_Strings;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 * @date 19.02.2020
 *
 */
public class TL_SaveSettingsOnMask extends E2_Usersetting_Vector_of_Strings {

	private static String key = "USERSETTINGS_TEXTLISTE_ERFASSUNGSMASK";
	

	
	
	/**
	 * @author martin
	 * @date 19.02.2020
	 *
	 * @param sessionHash
	 * @param stringSeparator
	 * @param identifier
	 */
	public TL_SaveSettingsOnMask(EnumTableTranslator tableTrans) {
		super(TL_SaveSettingsOnMask.key+"_"+tableTrans.db_val(), "@", TL_SaveSettingsOnMask.key+"_"+tableTrans.db_val());
	}
	
	
	public void save(RB_ComponentMap map, _TAB motherTable) throws myException {
		VEK<String>  toSave = new VEK<>();
		
		RB_MaskController mc = new RB_MaskController(map);
		
		for (TL_enumFieldWithSavedStatus sf: TL_enumFieldWithSavedStatus.values()) {
			toSave._a(S.NN(mc.get_liveVal(sf.getF()),sf.getDefaultVal()));
		}
		
		int count = this.STORE_Vector(toSave);
		if (count != 1) {
			bibMSG.MV()._addWarn(S.ms("Probleme beim Speichern der Einstellungen ...").ut("<853d5d22-5333-11ea-8d77-2e728ce88125>"));
		}

	}

	public HMAP<IF_Field, String> read() throws myException {
		
		Vector<String> vStored = this.READ_Vector();
		HMAP<IF_Field, String> hmRet = null;
		
		if (vStored==null || vStored.size()!=TL_enumFieldWithSavedStatus.values().length) {
			return hmRet;   //falls die anzahl werte nicht stimmt, wird null zurueckgegeben
		}
		hmRet = new HMAP<>();
		for (int i=0; i<TL_enumFieldWithSavedStatus.values().length;i++) {
			hmRet._put(TL_enumFieldWithSavedStatus.values()[i].getF(), vStored.get(i));
		}
		return hmRet;
	}
	
	
	
}
