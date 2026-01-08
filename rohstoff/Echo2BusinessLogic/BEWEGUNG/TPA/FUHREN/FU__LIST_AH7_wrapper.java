/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.__FU_FUO_gridWithAH7_elements;

/**
 * @author martin
 * komponente, die alle helfer zum steuertabellenbasierten AH7-formular anzeigt
 */
public class FU__LIST_AH7_wrapper extends MyE2_DB_PlaceHolder_NT{

	/**
	 * @param osqlField
	 * @throws myException
	 */
	public FU__LIST_AH7_wrapper() throws myException {
		super();
	}
	
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_onlyWhenVisisble#set_cActual_Formated_DBContent_To_Mask_WhenVisisble(java.lang.String, java.lang.String, panter.gmbh.indep.dataTools.SQLResultMAP)
	 */
	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this._clear();

		this.add_raw(new __FU_FUO_gridWithAH7_elements(	oResultMAP.get_booleanActualValue(VPOS_TPA_FUHRE.print_eu_amtsblatt.fn()), 
														oResultMAP.get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_lager_start.fn(), true),
														oResultMAP.get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_lager_ziel.fn(), true), new ownActionRefreshLine()));
		
	}

	private class ownActionRefreshLine extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			FU__LIST_AH7_wrapper.this.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, false, false);
		}
		
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.DB.MyE2IF_DB_SimpleComponent#prepare_ContentForNew(boolean)
	 */
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy	{
		try {
			return new FU__LIST_AH7_wrapper();
		} catch (myException e) {
			e.printStackTrace();
			return new RB_lab()._t("ERROR");
		}
	}


	
}
