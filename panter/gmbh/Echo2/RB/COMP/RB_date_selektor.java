package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date_Enum;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;

public class RB_date_selektor extends E2_TF_4_Date  implements IF_RB_Component_Savable {

	private RB_KF Key = null;
	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();

	public RB_date_selektor() throws myException {
		super();
		this.setPopUpInSmallMode(true);
		this.setSelectionMode(E2_TF_4_Date_Enum.DIRECT_SELECTION_MODE);
	}

	public RB_date_selektor(int i_width_in_px, boolean b_mini_icon) throws myException {
		super("",i_width_in_px,b_mini_icon);
		this.setPopUpInSmallMode(true);
		this.setSelectionMode(E2_TF_4_Date_Enum.DIRECT_SELECTION_MODE);
	}

	public RB_date_selektor(int i_width_in_px, boolean b_mini_icon, boolean add_erazor) throws myException {
		super("",i_width_in_px,b_mini_icon,add_erazor);
		this.setPopUpInSmallMode(true);
		this.setSelectionMode(E2_TF_4_Date_Enum.DIRECT_SELECTION_MODE);
	}

	
	
	
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.get_oTextField().setText("");;
		} else {
			this.get_oTextField().setText(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
		}
	}

	
	@Override
	public void mark_Neutral() throws myException {
		this.get_oTextField().setBorder(new Border(1, new E2_ColorBase(), Border.STYLE_SOLID));
		this.get_oTextField().setBackground(new E2_ColorEditBackground());
		this.get_oTextField().setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException	{
		this.get_oTextField().setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
		//this.get_oTextField().setBackground(new E2_ColorEditBackground());
		this.get_oTextField().setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.get_oTextField().setBackground(new E2_ColorGray(230));
		this.get_oTextField().setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.get_oTextField().setBackground(new E2_ColorHelpBackground());
		this.get_oTextField().setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.get_oTextField().setAlignment(align);
	}

	

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.get_oTextField().getText().trim();
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.get_oTextField().setText(valueFormated);
	}

//	
//	private String formateDate(String input) throws myException {
//		MyDate  date = new MyDate(input);
//		if (date.get_bAll_OK_With_CorrectDate()) {
//			return date.get_cUmwandlungsergebnis();
//		} 
//		return "";
//	}
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		super.set_bEnabled_For_Edit(enabled);
		if (enabled) {
			this.get_oTextField().setBackground(new E2_ColorEditBackground());
		} else {
			this.get_oTextField().setBackground(new E2_ColorGray(230));
		}
	}

	
}
