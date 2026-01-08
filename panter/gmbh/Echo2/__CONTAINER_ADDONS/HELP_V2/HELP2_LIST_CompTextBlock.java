package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListSorter;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.COMP.RB_ButtonImage;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.DB.E2_DB_PlaceHolder_V3;
import panter.gmbh.basics4project.DB_ENUMS.ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_Long;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.Param_String;
import panter.gmbh.indep.dataTools.TERM.vglParam;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_ArchivMedien;

public class HELP2_LIST_CompTextBlock extends E2_DB_PlaceHolder_V3 {

	public static String key = "14426ccc-aadb-11e8-a137-529269fb1459";
	
	private String title = null;
	private String text = null;
	private Vector<String> 	v_all = new Vector<>();
	private VEK<String> 	v_lines = new VEK<>();
	
    private RB_TransportHashMap   m_tpHashMap = null;

	public HELP2_LIST_CompTextBlock(RB_TransportHashMap tpHashMap) {
		super();
		
		this.EXT().set_oCompTitleInList( new E2_ButtonListSorter(	S.ms("Titel"),
											HILFETEXT.titel.tnfn()+" ASC",
											HILFETEXT.titel.tnfn()+" DESC",
											tpHashMap.getNavigationList(), 
											true));
		
		this.m_tpHashMap = tpHashMap;
	}

	
	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this._clear();
	}

	
	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask_WhenVisisble(String value_from_db, String cMASK_STATUS,	SQLResultMAP oResultMAP) throws myException {

		this.renderCells(false);
		
	}

	
	
	private void renderCells(boolean showExtended) throws myException {
		SQLResultMAP oResultMAP = this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP(); 
				
		Long id = oResultMAP.get_LActualDBValue(HILFETEXT.id_hilfetext.fn(), true);
		
		this._setSize(HELP2_READABLE_FIELD_NAME.getLabelWidth(HILFETEXT.hilfetext));
		
		title = S.NN(oResultMAP.get_UnFormatedValue(HILFETEXT.titel.fn()),"<-->");
		text = S.NN(oResultMAP.get_UnFormatedValue(HILFETEXT.hilfetext.fn()),"");
		
		v_all =   bibALL.TrenneZeile(text, "\n");
		v_lines = new VEK<>();
		
		boolean extBt = false;

		if (v_all.size()>4 && !showExtended) {
			v_lines._a(v_all.get(0),v_all.get(1),v_all.get(2),v_all.get(3));
			extBt = true;
		} else {
			v_lines._a(v_all);
		}
		

		int row = 0;
		this._clear()._a(new RB_lab()._t(this.title)._b(), new RB_gld()._ins(2, 3, 2, 3));
		for (String s: v_lines) {
			this._a(new RB_lab()._t(S.NN(s,"   "))._ttt(text), new RB_gld()._ins(2, 1, 2, 0));
			if (S.isEmpty(s)) {
				this.setRowHeight(row, new Extent(25));    //leerezeilen sichtbar
			}
			row++;
		}

		if (extBt) {
			this._a(new E2_Button()._t("...")._al_center()._aaa(new ActionExtend())._ttt(S.ms("Weiteren Text anzeigen ...")));
		}
		
		
		if ( ((HELP2__TransportHashMap) this.m_tpHashMap).getCheckBoxShowImages().isSelected()) {
		
			//jetzt die Bilder laden
			SEL sel = new SEL("*").FROM(_TAB.archivmedien).WHERE(new vglParam(ARCHIVMEDIEN.tablename)).AND(new vglParam(ARCHIVMEDIEN.id_table)).ORDERUP(ARCHIVMEDIEN.id_archivmedien);
			SqlStringExtended slqExt = new SqlStringExtended(sel.s())._addParameters(
											new VEK<ParamDataObject>()
												._a(new Param_String("", _TAB.hilfetext.baseTableName()))
												._a(new Param_Long(id))
											);
			
			RecList21 rlBilder = new RecList21(_TAB.archivmedien)._fill(slqExt);
	
			for (Rec21 r: rlBilder) {
				// this._a(new E2_Button()._image("warnschild_16.png"));
				E2_Grid g = new E2_Grid()._bo_b()._a(new RB_ButtonImage()	._setImageSize(HELP2_CONST.HELP2_NUM_CONST.WIDTH_COL_WITH_TEXT_AND_IMAGES.getValue())
						._setRecArchivmedium(new Rec21_ArchivMedien(r))
						._render(), new RB_gld()._ins(2));
						
				this._a(g, new RB_gld()._ins(2, 10, 2, 2));
				
			}
		}
	}
	
	
	
	private class ActionExtend extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			HELP2_LIST_CompTextBlock oThis = HELP2_LIST_CompTextBlock.this;
			
			oThis.renderCells(true);
			
		}
		
	}
	
	
	public HELP2_LIST_CompTextBlock get_Copy(Object objHelp) throws myExceptionCopy {
		return new HELP2_LIST_CompTextBlock(this.m_tpHashMap);
	}


	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(true);
	}
}
