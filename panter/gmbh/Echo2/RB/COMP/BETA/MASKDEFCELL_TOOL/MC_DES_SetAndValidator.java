package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.MASK_DEF_CELL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_MASK_DEF;

public class MC_DES_SetAndValidator extends RB_Mask_Set_And_Valid {

	private int iMaxCols 	= 0;
	private int iColWidth 	= 0;

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,
			ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		if(ActionType  == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
			Rec20_MASK_DEF recMaskDef = new Rec20_MASK_DEF(new Rec20(_TAB.mask_def)._fill_id(
					bibALL.convertID2UnformattedID(
							rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.id_mask_def).get__actual_maskstring_in_view()
							)));

			iMaxCols = 	recMaskDef.get_number_of_cols();
			iColWidth = recMaskDef.get_pixel_width();

//			mv._add(verify_span(rbMASK)			);
			mv._add(verify_field_length(rbMASK)	);
		}		
		return mv;
	}

	private MyE2_MessageVector verify_span(RB_ComponentMap rbMASK) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();

		int startpoint = new MyLong(rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.start_col_in_mask	).get__actual_maskstring_in_view()).get_iValue();
		int zelle_span = new MyLong(rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.colspan			).get__actual_maskstring_in_view()).get_iValue();

		if((startpoint + zelle_span)>iMaxCols) {
			mv._addAlarm("Die Spaltenbreite hat das Raster überschritten ");
			rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.colspan).show_InputStatus(false);
			rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.start_col_in_mask).show_InputStatus(false);
		}

		return mv;
	}

	private MyE2_MessageVector verify_field_length(RB_ComponentMap rbMASK) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();

		int field_lght 	= new MyLong(rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.field_length	).get__actual_maskstring_in_view(), 0l, 0l).get_iValue();
		int zelle_span 	= new MyLong(rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.colspan		).get__actual_maskstring_in_view()).get_iValue();
		int left_ins 	= new MyLong(rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.left_insets	).get__actual_maskstring_in_view(), 0l,0l).get_iValue();
		int right_ins 	= new MyLong(rbMASK._find_component_in_neighborhood(MASK_DEF_CELL.right_insets	).get__actual_maskstring_in_view(),0l,0l).get_iValue();
		if(field_lght>0) {
		int optimal_field_length = (zelle_span*iColWidth)-(left_ins+right_ins);

		if(field_lght>optimal_field_length) {
			mv._addWarn("Achtung die Feldlaenge ist zu gross !");
		}
		}
		return mv;
	}

}
