/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.ORTE;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Font;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.Triple;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung;

/**
 * @author martin
 *
 */
public class FUO_MASK_InfoBlockWaehrungen extends Warenbewegung_InfoBlockFremdWaehrung {


	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung#getIdAdressAndIdVposKon()
	 */
	@Override
	protected VEK<Triple<Long>> getIdAdressAndIdVposKonAndIdVposAngebot() {
		VEK<Triple<Long>> v = new VEK<Triple<Long>>();
		
		try {
			Long idAdress = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE_ORT.id_adresse.fn(), null, null);
			Long idVposKon = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE_ORT.id_vpos_kon.fn(), null, null);
			Long idVposStd = this.EXT().get_oComponentMAP().get_LActualDBValue(VPOS_TPA_FUHRE_ORT.id_vpos_std.fn(), null, null);
			
			v.add(new Triple<Long>(idAdress, idVposKon, idVposStd));
			
			
		} catch (myException e) {
			v.clear();
			v.add(new Triple<Long>(null, null, null));
			v.add(new Triple<Long>(null, null, null));
			e.printStackTrace();
		}
		
		return v;
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung#getSpaltenBreite()
	 */
	@Override
	protected int getSpaltenBreite() {
		return 35;
	}

	@Override
	protected int getBlockHeigth() {
		return 50;
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung#getBlockBackColorHighlight()
	 */
	@Override
	protected Color getBlockBackColorHighlight() {
		return new E2_ColorAlarm();
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.Warenbewegung_InfoBlockFremdWaehrung#getBlockTextFont()
	 */
	@Override
	protected Font getBlockTextFont() {
		return new E2_FontBold();
	}
	
	@Override
	protected Color getBlockBackColorNormal() {
		return new E2_ColorBase();
	}

}
