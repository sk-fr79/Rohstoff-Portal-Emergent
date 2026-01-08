package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RB_CheckBox extends MyE2_DB_CheckBox  implements IF_RB_Component_Savable {

	public RB_CheckBox(SQLField osqlField) throws myException {
		super(osqlField);
	}

	public RB_CheckBox(SQLField osqlField, boolean bDisableFromBasic,MyE2_String cTooltips) throws myException {
		super(osqlField, bDisableFromBasic, cTooltips);
	}

	public RB_CheckBox(SQLField osqlField, boolean bDisableFromBasic) throws myException {
		super(osqlField, bDisableFromBasic);
	}

	public RB_CheckBox(SQLField osqlField, MyE2_String cText,	MyE2_String cTooltips) throws myException {
		super(osqlField, cText, cTooltips);
	}

	public RB_CheckBox(SQLField osqlField, MyE2_String cTooltips) throws myException {
		super(osqlField, cTooltips);
	}

	public RB_CheckBox(IF_Field osqlField) throws myException {
		super(new RB_SQLField(osqlField));
	}

	public RB_CheckBox(IF_Field osqlField, boolean bDisableFromBasic,MyE2_String cTooltips) throws myException {
		super(new RB_SQLField(osqlField), bDisableFromBasic, cTooltips);
	}

	public RB_CheckBox(IF_Field osqlField, boolean bDisableFromBasic) throws myException {
		super(new RB_SQLField(osqlField), bDisableFromBasic);
	}

	public RB_CheckBox(IF_Field osqlField, MyE2_String cText,	MyE2_String cTooltips) throws myException {
		super(new RB_SQLField(osqlField), cText, cTooltips);
	}

	public RB_CheckBox(IF_Field osqlField, MyE2_String cTooltips) throws myException {
		super(new RB_SQLField(osqlField), cTooltips);
	}

	
	
	
	public RB_CheckBox set_W(int iWitdth) {
		this.setWidth(new Extent(iWitdth));
		return this;
	}

	
	public RB_CheckBox setNoBorder(){
		this.setBorder(null);
		return this;
	}
	

	private RB_KF Key = null;

	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}


	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.prepare_ContentForNew(false);
		} else {
			this.set_cActualMaskValue(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME(),""));
		}
	}

	
	@Override
	public void mark_Neutral() throws myException {
//		this.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.setForeground(Color.BLACK);
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException	{
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.setForeground(new E2_ColorGray(50));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.setAlignment(align);
	}

	

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.get_cActualDBValueFormated();
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
		this.set_cActualMaskValue(valueFormated);
	}


	
}
