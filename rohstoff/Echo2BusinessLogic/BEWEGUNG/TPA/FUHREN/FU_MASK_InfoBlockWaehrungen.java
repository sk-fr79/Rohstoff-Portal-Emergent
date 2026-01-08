/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung;

/**
 * @author martin
 *
 */
public class FU_MASK_InfoBlockWaehrungen extends Warenbewegung_InfoBlockFremdWaehrung {

	@Override
	protected int getBlockHeigth() {
		return 50;
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung#getIdAdressAndIdVposKon()
	 */
	@Override
	protected VEK<Triple<Long>> getIdAdressAndIdVposKonAndIdVposAngebot() {
		VEK<Triple<Long>> v = new VEK<Triple<Long>>();
		
		try {
			Long idAdressStart = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_start.fn(), null, null);
			Long idVposKonStart = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE.id_vpos_kon_ek.fn(), null, null);
			Long idVposStdStart = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE.id_vpos_std_ek.fn(), null, null);

			Long idAdressZiel = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE.id_adresse_ziel.fn(), null, null);
			Long idVposKonZiel = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE.id_vpos_kon_vk.fn(), null, null);
			Long idVposStdZiel = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE.id_vpos_std_vk.fn(), null, null);
			
			v.add(new Triple<Long>(idAdressStart, idVposKonStart,idVposStdStart));
			v.add(new Triple<Long>(idAdressZiel, idVposKonZiel,idVposStdZiel));
			
			
		} catch (myException e) {
			v.clear();
			v.add(new Triple<Long>(null, null, null));
			v.add(new Triple<Long>(null, null, null));
			e.printStackTrace();
		}
		
		return v;
	}

	@Override
	protected int getSpaltenBreite() {
		return 35;
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
		return new E2_ColorBase();
	}

}
