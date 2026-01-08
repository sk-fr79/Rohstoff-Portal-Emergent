package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG_PARAM;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;

public class REP_VER_MASK_DaughterParameter extends RB_MaskDaughterSimple {

	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {}

	@Override
	public void mark_FalseInput() throws myException {

	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		int[] col_width = {240,600};
		this._clear();

		if(! (dataObject == null)) {
			RecList20 paramRecList = dataObject.rec20().get_down_reclist20(REPORT_LOG_PARAM.id_report_log, "", REPORT_LOG_PARAM.parameter_name.fn());
			if(paramRecList == null) {
				this._a("<Fehler beim Aufbau der Liste>");
			}else {
				RB_gld title_gld 	= new RB_gld()._ins(1)._left_top()._col_back_d();
				RB_gld data_gld 	= new RB_gld()._ins(1)._left_top()._col(new E2_ColorBase());

				E2_Grid in_grid1 	= new E2_Grid()._bo_dd()
						._setSize(col_width)
						._a(new RB_lab("Name")._fsa(-1), title_gld)
						._a(new RB_lab("Wert")._fsa(-1), title_gld);

				if(paramRecList.size()==0) {
					in_grid1._a(new RB_lab("<Kein Parameter>")._col_fore_lgrey()._b(), new RB_gld()._span(2)._ins(2)._center_mid());
				}else {


					for(Rec20 rec : paramRecList) {
						in_grid1
						._a(new RB_lab(rec.get_fs_dbVal(REPORT_LOG_PARAM.parameter_name, ""))._fsa(-1), data_gld)
						._a(new RB_lab(rec.get_fs_dbVal(REPORT_LOG_PARAM.parameter_wert, ""))._fsa(-1), data_gld);
					}
				}
				MyE2_ContainerEx dataContainer = new MyE2_ContainerEx(in_grid1);
				dataContainer.setHeight(new Extent(300));

				this._a(in_grid1, 		new RB_gld()._left_top()._span(2));
				this._a(dataContainer , new RB_gld()._left_top()._span(2));
			}

		}
	}

	@Override
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {

	}

}
