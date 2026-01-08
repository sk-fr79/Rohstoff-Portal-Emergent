/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author sebastien
 * @date 16.01.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_PALETTE_BOX;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibTEXT;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author sebastien
 * @date 16.01.2019
 *
 */
public class LH_P_List_bt_showHistory extends MyE2_Button {
	/**
	 * @author sebastien
	 * @date 16.01.2019
	 *
	 */
	public LH_P_List_bt_showHistory() throws myException {
		super();
		this._aaa(()->call_popUp());
		this.setIcon(E2_ResourceIcon.get_RI("inforound.png"));
		this._ttt(S.ms("Verlauf der Palettenbuchungen"));
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.components.MyE2_ButtonInLIST#get_Copy(java.lang.Object)
	 */
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			LH_P_List_bt_showHistory copy =  new LH_P_List_bt_showHistory();
			copy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(copy));
			return copy;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.getLocalizedMessage());
		}
	}

	private void call_popUp() throws myException{
		new ownBasicModuleContainer().CREATE_AND_SHOW_POPUPWINDOW(S.ms("Palette Verlauf"));
	}

	@Override
	public void setEnabled(boolean newValue) {
	}
	
	private class ownBasicModuleContainer extends E2_BasicModuleContainer{
		public ownBasicModuleContainer() throws myException {
			super();
			
			LH_P_List_bt_showHistory oThis = LH_P_List_bt_showHistory.this;
			
			try {
				String palette_id = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_UnFormatedValue(LAGER_PALETTE_BOX.id_lager_palette.fn());
				
				SEL palette_move_abfrage = new SEL().FROM(_TAB.lager_palette_box).WHERE(new vgl(LAGER_PALETTE_BOX.id_lager_palette, palette_id)).ORDERUP(LAGER_PALETTE_BOX.id_lager_palette_box);

				RecList21 recList = new RecList21(_TAB.lager_palette_box)._fill(palette_move_abfrage.s());
				
				E2_Grid grd = new E2_Grid()._s(5)._w100()._bo_dd()
				._a("Boxnr.", 			new RB_gld()._ins(1)._col_back_d())
				._a("Einbuchung am", 	new RB_gld()._ins(1)._col_back_d())
				._a("Ausbuchung am", 	new RB_gld()._ins(1)._col_back_d())
				._a("Einbuchungsfuhre", new RB_gld()._ins(1)._col_back_d())
				._a("Ausbuchungsfuhre", new RB_gld()._ins(1)._col_back_d())
				;
				
				for(Rec21 rec_lager_palette_box: recList) {
					
					String boxNr = rec_lager_palette_box.get_up_Rec21(LAGER_PALETTE_BOX.id_lager_box, LAGER_BOX.id_lager_box, false).getUfs(LAGER_BOX.boxnummer, "-");
					String einFuhreId = rec_lager_palette_box.get_up_Rec21(LAGER_PALETTE_BOX.id_lager_palette, LAGER_PALETTE.id_lager_palette, false).get_fs_dbVal(LAGER_PALETTE.id_vpos_tpa_fuhre_ein,"-");
					if(einFuhreId.equals("-")) {
						if(rec_lager_palette_box.get_up_Rec21(LAGER_PALETTE_BOX.id_lager_palette, LAGER_PALETTE.id_lager_palette, false).is_yes_db_val(LAGER_PALETTE.einbuchung_hand)) {
							einFuhreId = "Hand einbuchung";
						}
					}
					String ausFuhreId = rec_lager_palette_box.get_up_Rec21(LAGER_PALETTE_BOX.id_lager_palette, LAGER_PALETTE.id_lager_palette, false).get_fs_dbVal(LAGER_PALETTE.id_vpos_tpa_fuhre_aus,"-");
					if(ausFuhreId.equals("-")) {
						if(rec_lager_palette_box.get_up_Rec21(LAGER_PALETTE_BOX.id_lager_palette, LAGER_PALETTE.id_lager_palette, false).is_yes_db_val(LAGER_PALETTE.ausbuchung_hand)) {
							ausFuhreId = "Hand ausbuchung";
						}
					}
					grd
					._a(boxNr, new RB_gld()._ins(1)._col_back(new E2_ColorBase()))
					._a(rec_lager_palette_box.get_ufs_dbVal(LAGER_PALETTE_BOX.einbuchung_am), new RB_gld()._ins(1)._col_back(new E2_ColorBase()))
					._a(rec_lager_palette_box.get_ufs_dbVal(LAGER_PALETTE_BOX.ausbuchung_am, "-"), new RB_gld()._ins(1)._col_back(new E2_ColorBase()))
					._a(einFuhreId, new RB_gld()._ins(1)._col_back(new E2_ColorBase()))
					._a(ausFuhreId, new RB_gld()._ins(1)._col_back(new E2_ColorBase()))
					;

				}
				
				this.add(grd);
			} catch (Exception e) {
				this.add(new E2_Grid()._setSize(400)._h(300)._a(
						new RB_lab()._t("Systemfehler: \n"+bibTEXT.getExceptionToString(e))._fs(14)._b()
						, new RB_gld()._center_mid()._col_back_alarm()));
				
				
			}
		}
	}
}
