package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.LayoutData;
import panter.gmbh.Echo2.Factorys.XXX_StyleFactory;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RB_TextField_old extends MyE2_DB_TextField implements IF_RB_Component_Savable{

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

	


	public RB_TextField_old(SQLField sqlF, int iWidthInPixel, int iMaxInputSize) 	throws myException {
		super(sqlF, true, iWidthInPixel, iMaxInputSize,false);
	}





	public RB_TextField_old(SQLField sqlF, 	int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException {
		super(sqlF, true, iWidthInPixel, oLayout, oFont);
	}



	public RB_TextField_old(SQLField sqlF, 	int iWidthInPixel, LayoutData oLayout) throws myException {
		super(sqlF, true, iWidthInPixel, oLayout);
	}



	public RB_TextField_old(SQLField sqlF, int iWidthInPixel) throws myException {
		super(sqlF, true, iWidthInPixel);
	}



	public RB_TextField_old(SQLField sqlF, int iWidthInPixel,	XXX_StyleFactory oStyle) throws myException {
		super(sqlF, iWidthInPixel, oStyle);
	}





	public RB_TextField_old(SQLField sqlF) throws myException {
		super(sqlF);
	}

	public RB_TextField_old(String Tablename, String fieldName) throws myException {
		this(new RB_SQLField(Tablename, fieldName));
	}

	
	public RB_TextField_old(String Tablename, String fieldName, int iWidthInPixel, int iMaxInputSize) 	throws myException {
		super(new RB_SQLField(Tablename, fieldName), true, iWidthInPixel, iMaxInputSize,false);
	}


	public RB_TextField_old(String Tablename, String fieldName, 	int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException {
		super(new RB_SQLField(Tablename, fieldName), true, iWidthInPixel, oLayout, oFont);
	}



	public RB_TextField_old(String Tablename, String fieldName, 	int iWidthInPixel, LayoutData oLayout) throws myException {
		super(new RB_SQLField(Tablename, fieldName), true, iWidthInPixel, oLayout);
	}



	public RB_TextField_old(String Tablename, String fieldName, int iWidthInPixel) throws myException {
		super(new RB_SQLField(Tablename, fieldName), true, iWidthInPixel);
	}



	public RB_TextField_old(String Tablename, String fieldName, int iWidthInPixel,	XXX_StyleFactory oStyle) throws myException {
		super(new RB_SQLField(Tablename, fieldName), iWidthInPixel, oStyle);
	}



	public RB_TextField_old(IF_Field sqlF, 	int iWidthInPixel, LayoutData oLayout, Font oFont) throws myException {
		super(new RB_SQLField(sqlF), true, iWidthInPixel, oLayout, oFont);
	}



	public RB_TextField_old(IF_Field sqlF, 	int iWidthInPixel, LayoutData oLayout) throws myException {
		super(new RB_SQLField(sqlF), true, iWidthInPixel, oLayout);
	}



	public RB_TextField_old(IF_Field sqlF, int iWidthInPixel) throws myException {
		super(new RB_SQLField(sqlF), true, iWidthInPixel);
	}



	public RB_TextField_old(IF_Field sqlF, int iWidthInPixel,	XXX_StyleFactory oStyle) throws myException {
		super(new RB_SQLField(sqlF), iWidthInPixel, oStyle);
	}


	public RB_TextField_old(IF_Field sqlF) throws myException {
		super(new RB_SQLField(sqlF));
	}

	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.prepare_ContentForNew(false);
		} else {
			this.set_cActualMaskValue(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.rb_KF().FIELDNAME()));
		}
	}

	
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


	public RB_TextField_old set_size_and_font(int i_heigth, Font  o_font) {
		this.setHeight(new Extent(i_heigth));
		this.setFont(o_font);
		return this;
	}
	
	
	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException 	{

		boolean g_enabled = enabled && this.EXT().is_ValidEnableAlowed() && this.EXT_DB().get_bCanBeEnabled(false) && this.EXT().get_bCanBeEnabled();
		this.setEnabled(g_enabled);
		
		if (g_enabled) {
			this.setFocusTraversalParticipant(true);
		} else {
			this.setFocusTraversalParticipant(false);
		}
		
		//hintergrundfarbe funktioniert nicht immer via style ???
		if (this.isEnabled()) {
			this.mark_Neutral();
		} else {
			this.mark_Disabled();
		}
	}


	

	
	
}
