/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF
 * @author martin
 * @date 24.09.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ARTIKELBEZ_LIEF;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.FS_MASK_ComponentMAP;
import rohstoff.Echo2BusinessLogic._4_ALL.MASK_COMPONENT_SEARCH_EAK_CODES;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
import rohstoff.utils.ECHO2.DB_SEARCH_ArtikelBez;

/**
 * @author martin
 * @date 24.09.2020
 *
 */
public class FSA_ListComponentSearchAVVCode extends MASK_COMPONENT_SEARCH_EAK_CODES {


	private Boolean 				ek;
	private SQLField 				sqlField ;
	private FS_MASK_ComponentMAP 	map;
	private DB_SEARCH_ArtikelBez 	searchArtBez;
	
	public FSA_ListComponentSearchAVVCode(SQLField osqlField, FS_MASK_ComponentMAP ocomponentMAP_ADRESS,DB_SEARCH_ArtikelBez SearchArtBez, boolean p_ek) throws myException {
		super(osqlField, ocomponentMAP_ADRESS, SearchArtBez);
		this.ek = p_ek;
		this.sqlField = osqlField;
		this.map = ocomponentMAP_ADRESS;
		this.searchArtBez = SearchArtBez;
	}

	
	
	public Object get_Copy(Object ob) throws myExceptionCopy {
		FSA_ListComponentSearchAVVCode oRueck = null;
		
		try {
			oRueck =  new FSA_ListComponentSearchAVVCode(	this.sqlField,
															this.map,
															this.searchArtBez,
															this.ek);
			
			oRueck.get_oTextForAnzeige().setWidth(this.get_oTextForAnzeige().getWidth());
			oRueck.get_oTFDatenFeldWithID().setWidth(this.get_oTFDatenFeldWithID().getWidth());
		
			oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
			
			oRueck.set_oActionAfterFound(this.get_oActionAfterFound());
			
		} catch (myException ex){
			throw new myExceptionCopy("FS_Component_LIST_SEARCH_EAK_CODES:get_Copy:copy-error!");
		}
		
		return oRueck;
	}

	
	
	// nur noch im EK die standard-kleinanlieferer vorschlagen, im VK die Standard-AVV ex mandant
	public void suche_StandardAVV_Code(DB_SEARCH_ArtikelBez    SearchArtBez) throws myException {

		try {
			String cID_ARTBEZ = bibALL.ReplaceTeilString(S.NN(SearchArtBez.get_cActualMaskValue()),".","");
			if (S.isEmpty(cID_ARTBEZ) || !bibALL.isLong(cID_ARTBEZ)) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte zuerst die Sortenbezeichung definieren !"));
				return;
			}
			Rec21_artikel_bez recABez = new Rec21_artikel_bez()._fill_id(cID_ARTBEZ);
			
			String idAVV = null;
			String meldung = null;
			if (ek) {
				idAVV = recABez._getRec21Artikel().get_fs_dbVal(ARTIKEL.id_eak_code);
				meldung = "Standard-Eingangs-AVV";
			} else {
				idAVV = recABez._getRec21Artikel().get_fs_dbVal(ARTIKEL.id_eak_code_ex_mandant);
				meldung = "Standard-Ausgangs-AVV";
			}
			
			if (S.isEmpty(idAVV)) {
				bibMSG.add_MESSAGE(new MyE2_Warning_Message("Zu dieser Sorte ist kein "+meldung+" erfasst worden !"));
			} else {
				this.set_cActual_Formated_DBContent_To_Mask(idAVV, E2_ComponentMAP.STATUS_EDIT, null);
			}
		} catch (myException e) {
			e.printStackTrace();
			throw new myException(e);
		}

	}
	
}
