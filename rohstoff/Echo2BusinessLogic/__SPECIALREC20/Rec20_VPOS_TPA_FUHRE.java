package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_VPOS_TPA_FUHRE  extends Rec20{

	public Rec20_VPOS_TPA_FUHRE() throws myException{
		super (_TAB.vpos_tpa_fuhre);
	}
	
	public Rec20_VPOS_TPA_FUHRE(Rec20 baseRec) throws myException{
		super(baseRec);
	}

	public MyBigDecimal get_myBigDecimal_plan_menge(boolean isEk, int iNachKomma) throws myException{
		MyBigDecimal bd_plan_menge = null;
		if(isEk){
			bd_plan_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief, 
					this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_lief,
							this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_lief, new MyBigDecimal(0))));
		}else{
			bd_plan_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, 
					this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_abn,
							this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_planmenge_abn, new MyBigDecimal(0))));
		}
		return bd_plan_menge;
	}

	public MyBigDecimal get_myBigDecimal_real_menge(boolean isEk, int iNachKomma) throws myException{
		MyBigDecimal bd_real_menge = null;
		if(isEk){
			bd_real_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_lademenge_lief,new MyBigDecimal(0));
		}else{
			bd_real_menge = this.get_myBigDecimal_dbVal(VPOS_TPA_FUHRE.anteil_ablademenge_abn, new MyBigDecimal(0));
		}
		return bd_real_menge;
	}
	
	public RB_lab get_rbLabel_plan_menge(boolean isEk, int iNachKomma) throws myException{
		RB_lab lbl = new RB_lab();
		
		String tooltip = "";
		MyBigDecimal bd_plan_menge = get_myBigDecimal_plan_menge(isEk, iNachKomma);

		if(isEk){
			tooltip = "Lademenge oder Planmenge Ladeseite";
		}else{
			tooltip = "Abademenge oder Planmenge Abladeseite";
		}
		
		lbl._t(bd_plan_menge.get_FormatedRoundedNumber(iNachKomma) + " " + this.get_fs_dbVal(VPOS_TPA_FUHRE.einheit_mengen));
		
		lbl._ttt(tooltip);
		
		return lbl;
	}
	
	public RB_lab get_rbLabel_real_menge(boolean isEk, int iNachKomma) throws myException{
		RB_lab lbl = new RB_lab();
		
		String tooltip = "";
		MyBigDecimal bd_real_menge = get_myBigDecimal_real_menge(isEk, iNachKomma);

		if(isEk){
			tooltip = "Lademenge oder Planmenge Ladeseite";
		}else{
			tooltip = "Abademenge oder Planmenge Abladeseite";
		}
		
		lbl._t(bd_real_menge.get_FormatedRoundedNumber(iNachKomma) + " " + this.get_fs_dbVal(VPOS_TPA_FUHRE.einheit_mengen));
		
		lbl._ttt(tooltip);
		
		return lbl;
	}
}
