package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextArea;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RB_TextArea_old extends MyE2_DB_TextArea implements IF_RB_Component_Savable{

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

	public RB_TextArea_old(SQLField sqlF,  int iWidthInPixel, int iRows, boolean bDisabledFromBasic, Font oFont) throws myException {
		super(sqlF, iWidthInPixel, iRows, bDisabledFromBasic, oFont);
	}


	public RB_TextArea_old(SQLField sqlF, int iWidthInPixel, int iRows, LayoutData oLayout, Font oFont) throws myException {
		super(sqlF, iWidthInPixel, iRows, oLayout, oFont);
	}


	public RB_TextArea_old(SQLField sqlF,  int iWidthInPixel, int iRows) throws myException {
		super(sqlF, iWidthInPixel, iRows);
	}


	public RB_TextArea_old(SQLField sqlF) throws myException {
		super(sqlF);
	}

	
	public RB_TextArea_old(IF_Field sqlF,  int iWidthInPixel, int iRows, boolean bDisabledFromBasic, Font oFont) throws myException {
		super(new RB_SQLField(sqlF), iWidthInPixel, iRows, bDisabledFromBasic, oFont);
	}


	public RB_TextArea_old(IF_Field sqlF, int iWidthInPixel, int iRows, LayoutData oLayout, Font oFont) throws myException {
		super(new RB_SQLField(sqlF), iWidthInPixel, iRows, oLayout, oFont);
	}


	public RB_TextArea_old(IF_Field sqlF,  int iWidthInPixel, int iRows) throws myException {
		super(new RB_SQLField(sqlF), iWidthInPixel, iRows);
	}


	public RB_TextArea_old(IF_Field sqlF) throws myException {
		super(new RB_SQLField(sqlF));
	}
	
	
	
	public RB_TextArea_old(String Tablename, String fieldName,  int iWidthInPixel, int iRows, boolean bDisabledFromBasic, Font oFont) throws myException {
		this(new RB_SQLField(Tablename, fieldName), iWidthInPixel, iRows, bDisabledFromBasic, oFont);
	}


	public RB_TextArea_old(String Tablename, String fieldName, int iWidthInPixel, int iRows, LayoutData oLayout, Font oFont) throws myException {
		this(new RB_SQLField(Tablename, fieldName), iWidthInPixel, iRows, oLayout, oFont);
	}


	public RB_TextArea_old(String Tablename, String fieldName,  int iWidthInPixel, int iRows) throws myException {
		this(new RB_SQLField(Tablename, fieldName), iWidthInPixel, iRows);
	}


	public RB_TextArea_old(String Tablename, String fieldName) throws myException {
		this(new RB_SQLField(Tablename, fieldName));
	}

	
	
	

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.prepare_ContentForNew(false);
		} else {
			this.set_cActualMaskValue(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
		}
	}

//	//TODO: Check ob man alle Felder so behandeln kann...
//	@Override
//	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
//		super.set_bEnabled_For_Edit(enabled);
//		if (enabled){
//			mark_Neutral();
//		} else {
//			mark_Disabled();
//		}
//	}
	
	
	
	@Override
	public void mark_Neutral() throws myException {
		this.setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.setBackground(new E2_ColorEditBackground());
		this.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
	}

	@Override
	public void mark_MustField() throws myException	{
		this.setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.setBackground(new E2_ColorGray(230));
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

	public RB_TextArea_old set_size_and_font(int i_width, int i_heigth, Font  o_font) {
		this.setWidth(new Extent(i_width));
		this.setHeight(new Extent(i_heigth));
		this.setFont(o_font);
		return this;
	}


	
}
