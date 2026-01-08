/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder_NT;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung;

/**
 * @author martin
 *
 */
public class FU_LIST_WaehrungsAnzeigeWrapper extends MyE2_DB_PlaceHolder_NT {

	/**
	 * @throws myException
	 */
	public FU_LIST_WaehrungsAnzeigeWrapper() throws myException {
		super();
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {
		this._clear();
		this._setSize(70);
		this._add_r(new OwnWaehrungsAnzeiger(oResultMAP));
	}

	
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy	{
		try {
			return new FU_LIST_WaehrungsAnzeigeWrapper();
		} catch (myException e) {
			e.printStackTrace();
			return new RB_lab()._t("ERROR");
		}
	}

	
	private class OwnWaehrungsAnzeiger extends Warenbewegung_InfoBlockFremdWaehrung {
		private SQLResultMAP m_resultMap;
	
		/**
		 * @param resultMAP
		 */
		public OwnWaehrungsAnzeiger(SQLResultMAP resultMAP) {
			super();
			this.m_resultMap = resultMAP;
			this.fill();
		}

		@Override
		protected VEK<Triple<Long>> getIdAdressAndIdVposKonAndIdVposAngebot() {

			Long id_adresse_start = null;
			Long id_vpos_kon_ek = 	null;
			Long id_vpos_std_ek = 	null;
			
			Long id_adresse_ziel  =	null;
			Long id_vpos_kon_vk = 	null;
			Long id_vpos_std_vk = 	null;
			
			
			
			try {
				id_adresse_start =  this.m_resultMap.getLongDBValue(VPOS_TPA_FUHRE.id_adresse_start);
				id_vpos_kon_ek = 	this.m_resultMap.getLongDBValue(VPOS_TPA_FUHRE.id_vpos_kon_ek);
				id_vpos_std_ek = 	this.m_resultMap.getLongDBValue(VPOS_TPA_FUHRE.id_vpos_std_ek);
				
				id_adresse_ziel  =	this.m_resultMap.getLongDBValue(VPOS_TPA_FUHRE.id_adresse_ziel);
				id_vpos_kon_vk = 	this.m_resultMap.getLongDBValue(VPOS_TPA_FUHRE.id_vpos_kon_vk);
				id_vpos_std_vk = 	this.m_resultMap.getLongDBValue(VPOS_TPA_FUHRE.id_vpos_std_vk);
				
			} catch (myException e) {
				id_adresse_start = null;
				id_vpos_kon_ek = 	null;
				id_vpos_std_ek = 	null;
				
				id_adresse_ziel  =	null;
				id_vpos_kon_vk = 	null;
				id_vpos_std_vk = 	null;
				
				e.printStackTrace();
			}
			
			VEK<Triple<Long>> v = new VEK<Triple<Long>>()	._a(new Triple<Long>(id_adresse_start, id_vpos_kon_ek, id_vpos_std_ek))
															._a(new Triple<Long>(id_adresse_ziel, id_vpos_kon_vk, id_vpos_std_vk))
														;
			return v;
		}

		@Override
		protected int getSpaltenBreite() {
			return 35;
		}

		@Override
		protected int getBlockHeigth() {
			return 40;
		}

		@Override
		protected Color getBlockBackColorHighlight() {
			return new E2_ColorAlarm();
		}

		@Override
		protected Font getBlockTextFont() {
			return new E2_FontBold();
		}

		@Override
		protected Color getBlockBackColorNormal() {
			return new E2_ColorLight();
		}
		
	}
	
	
}
