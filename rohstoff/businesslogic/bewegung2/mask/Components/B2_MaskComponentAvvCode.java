/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 18.03.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevelSearchAVVCode;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.basics4project.DB_ENUMS.BG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.global.EnPositionStation;
import rohstoff.businesslogic.bewegung2.mask.Setters.B2_MaskController;
import rohstoff.businesslogic.bewegung2.recs.RecA1;
import rohstoff.businesslogic.bewegung2.recs.RecA2;

/**
 * @author martin
 * @date 18.03.2019
 *
 */
public class B2_MaskComponentAvvCode extends RB_HighLevelSearchAVVCode {

	private EnPositionStation  m_stationLeftRight = null; 

	
	
	
	public B2_MaskComponentAvvCode(EnPositionStation stationLeftRight) throws myException {
		super();
		this.m_stationLeftRight = stationLeftRight;
		
		this._bo(null);

		this._setSize(40, 300, 20, 20);
		this.setBackground(null);
		
		this.get_tf_4_id()._f(new E2_FontItalic(-2));
		this.get_tf_4_anzeige()._f(new E2_FontItalic(-2));

		this._a(this.get_tf_4_id(), 					new RB_gld()._ins(0,0,3,0)._col_back_base());
		this._a(this.get_tf_4_anzeige()._width(295), 	new RB_gld()._ins(0,0,3,0)._col_back_base());
		this._a(this.get_bt_Erase(), 					new RB_gld()._ins(0,0,3,0)._col_back_base());
		this._a(this.get_bt_Select(), 					new RB_gld()._ins(0,0,0,0)._col_back_base());
	}



	@Override
	public Rec21 get_actual_artikel() throws myException {
		
		B2_MaskController con = new B2_MaskController(this) ;
		Long idArtikel = con.getLongLiveVal(this.m_stationLeftRight==EnPositionStation.LEFT?RecA1.key:RecA2.key, BG_ATOM.id_eak_code);
		if (idArtikel!=null) {
			return new Rec21(_TAB.adresse)._fill_id(idArtikel);
		}
		return null;
	}

	


	
}
