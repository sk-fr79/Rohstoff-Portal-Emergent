package rohstoff.Echo2BusinessLogic._TAX.RULES.MAP_SETTERS;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.IF.IF_WrappedSimple;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.HANDELSDEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;

public class __TR_MapSetting_Steuerinfos extends XX_MAP_Set_And_Valid {

	
	
	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.show_steuerText_Konto(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.show_steuerText_Konto(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.show_steuerText_Konto(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(	E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.show_steuerText_Konto(oMAP, ActionType);
	}

	@Override
	public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo,SQLMaskInputMAP oInputMap) throws myException {
		return this.show_steuerText_Konto(oMAP, ActionType);
	}

	
	public MyE2_MessageVector show_steuerText_Konto(E2_ComponentMAP oMAP, int ActionType) throws myException {
		
		this.zeige_TaxZusatz(HANDELSDEF.id_tax_quelle, oMAP, true, ActionType);
		this.zeige_TaxZusatz(HANDELSDEF.id_tax_ziel, oMAP,false, ActionType);
		this.zeige_TaxZusatz(HANDELSDEF.id_tax_negativ_quelle, oMAP,true, ActionType);
		this.zeige_TaxZusatz(HANDELSDEF.id_tax_negativ_ziel, oMAP,false, ActionType);
		
		
		return new MyE2_MessageVector();
	}
	
	private RECORD_TAX _pruefe_tax(IF_Field field, E2_ComponentMAP oMAP) throws myException {
		RECORD_TAX  recTax = null;
		MyE2IF__DB_Component steuer_dd = (MyE2IF__DB_Component)oMAP.get__Comp(field.fn());
		
		MyLong l_steuer = new MyLong(steuer_dd.get_cActualDBValueFormated());
		
		if (l_steuer.get_bOK()) {
			recTax = new RECORD_TAX(l_steuer.get_lValue());
		}
		return recTax;
	}
	
	
	private void zeige_TaxZusatz(IF_Field field, E2_ComponentMAP oMAP, boolean bEK_seite, int ActionType) throws myException {
		IF_WrappedSimple steuer_dd = (IF_WrappedSimple)oMAP.get__Comp(field.fn());
		MyE2_Grid  grid = (MyE2_Grid) ((Component)steuer_dd.box((MyE2IF__Component)steuer_dd));
		
//		steuer_dd.get_GridContainer().removeAll();
//		steuer_dd.get_GridContainer().add(steuer_dd.box(steuer_dd),E2_INSETS.I(0,0,0,1));
//		steuer_dd.get_GridContainer().setBorder(new Border(0,new E2_ColorBase(),Border.STYLE_SOLID));
		
//		RECORD_TAX rt =this._pruefe_tax(field, oMAP);
//		if (rt!=null) {
//			
//			if (S.isFull(rt.get_STEUERVERMERK_cF_NN(""))) {
//				steuer_dd.get_GridContainer().add(new MyE2_Label(rt.get_STEUERVERMERK_cF_NN(""), new E2_FontItalic(-2),true),E2_INSETS.I(0,2,0,1));
//			} else {
//				steuer_dd.get_GridContainer().add(new MyE2_Label(new MyE2_String("<kein Steuervermerk>"), new E2_FontItalic(-2),true),E2_INSETS.I(0,0,0,1));
//			}
//	
//			String c_konto_info = "<kein Konto>";
//			if (bEK_seite) {
//				if (rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs()!=null) {
//					c_konto_info = rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs().get_BESCHREIBUNG_cUF_NN("")+" ("+rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs().get_KONTO_cUF_NN("")+")";
//				}
//			} else {
//				if (rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re()!=null) {
//					c_konto_info = rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re().get_BESCHREIBUNG_cUF_NN("")+" ("+rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re().get_KONTO_cUF_NN("")+")";
//				}
//			}
//			steuer_dd.get_GridContainer().add(new MyE2_Label(c_konto_info, new E2_FontItalic(-2),true),E2_INSETS.I(0,2,0,1));
//			steuer_dd.get_GridContainer().setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));
//
//		}

		RECORD_TAX rt =this._pruefe_tax(field, oMAP);
		if (rt!=null) {
			
			if (S.isFull(rt.get_STEUERVERMERK_cF_NN(""))) {
				grid.add(new MyE2_Label(rt.get_STEUERVERMERK_cF_NN(""), new E2_FontItalic(-2),true),E2_INSETS.I(0,2,0,1));
			} else {
				grid.add(new MyE2_Label(new MyE2_String("<kein Steuervermerk>"), new E2_FontItalic(-2),true),E2_INSETS.I(0,0,0,1));
			}
	
			String c_konto_info = "<kein Konto>";
			if (bEK_seite) {
				if (rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs()!=null) {
					c_konto_info = rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs().get_BESCHREIBUNG_cUF_NN("")+" ("+rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_gs().get_KONTO_cUF_NN("")+")";
				}
			} else {
				if (rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re()!=null) {
					c_konto_info = rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re().get_BESCHREIBUNG_cUF_NN("")+" ("+rt.get_UP_RECORD_FIBU_KONTO_id_fibu_konto_re().get_KONTO_cUF_NN("")+")";
				}
			}
			grid.add(new MyE2_Label(c_konto_info, new E2_FontItalic(-2),true),E2_INSETS.I(0,2,0,1));
			grid.setBorder(new Border(1,new E2_ColorDDDark(),Border.STYLE_SOLID));

		}

		
		
	}
	
}
