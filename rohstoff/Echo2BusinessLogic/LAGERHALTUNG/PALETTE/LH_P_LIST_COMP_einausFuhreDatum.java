/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 03.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.DB.E2_DB_PlaceHolder_V3;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author sebastien
 * @date 03.09.2019
 *
 */
public class LH_P_LIST_COMP_einausFuhreDatum extends E2_DB_PlaceHolder_V3 {

	private boolean is_einbuchungsdatum = true;
	
	public LH_P_LIST_COMP_einausFuhreDatum(boolean bis_einbuchungsdatum) {
		super();
		this._bo_no()._s(1);
		this.is_einbuchungsdatum = bis_einbuchungsdatum;
	}


	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear()._bo_no()._s(1);

	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,SQLResultMAP oResultMAP) throws myException {
		
		long id_palette = oResultMAP.get_LActualDBValue(LAGER_PALETTE.id_lager_palette.fn(), true);
		String datum = "-";
		if(id_palette>0) {
			SEL einbuchungs_datum_abfrage = 
					new SEL("NVL(max(JT_LAGER_PALETTE_BOX.ID_LAGER_PALETTE_BOX),0)")
					.FROM(_TAB.lager_palette_box)
					.AND(new vgl(LAGER_PALETTE_BOX.id_lager_palette, ""+id_palette))
					.AND(new vgl(LAGER_PALETTE_BOX.id_mandant, bibALL.get_ID_MANDANT()));
			
			String oRueck = bibDB.EinzelAbfrage(einbuchungs_datum_abfrage.s());
			if(oRueck != null && S.isFull(oRueck)) {
				datum = new Rec21(_TAB.lager_palette_box)._fill_id(oRueck)
						.getFs(is_einbuchungsdatum?LAGER_PALETTE_BOX.einbuchung_am:LAGER_PALETTE_BOX.ausbuchung_am,"-");
			}
		}
		
		this._a(datum, new RB_gld()._ins(1)._center_mid());
	}
	

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		LH_P_LIST_COMP_einausFuhreDatum copy = new LH_P_LIST_COMP_einausFuhreDatum(this.is_einbuchungsdatum);
		copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
		return copy;
	}

}
