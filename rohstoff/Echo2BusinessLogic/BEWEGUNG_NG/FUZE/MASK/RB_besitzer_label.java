package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class RB_besitzer_label extends RB_LabelFilledWithResult implements IF_RB_Component_Savable{
	
	private String id_adresse = "";
	
	public RB_besitzer_label(boolean is_small) throws myException {
		super("...");
		this._ttt("Besitzer");
		this.setLineWrap(true);
		if(is_small){
			this.setFont(new E2_FontPlain(-2));
		}
	}

	@Override
	public String transferDbValueToVisibleText(String id_adresse_besitzer) throws myException {
		MyLong  l_adresse = new MyLong(id_adresse_besitzer);
		if (l_adresse.get_bOK()) {
			Rec20_adresse r_adresse = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(l_adresse.get_lValue()));
			id_adresse = l_adresse.get_cUF_LongString();
			return r_adresse.__get_name1_ort_info_zu_firma();
		}
		return "...";
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return id_adresse;
	}
	
	
}
