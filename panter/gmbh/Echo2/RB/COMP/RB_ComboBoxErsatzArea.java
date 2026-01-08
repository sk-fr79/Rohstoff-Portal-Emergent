package panter.gmbh.Echo2.RB.COMP;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
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
import panter.gmbh.Echo2.components.DB.MyE2_DB_ComboBoxErsatzArea;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.exceptions.myException;

public class RB_ComboBoxErsatzArea extends MyE2_DB_ComboBoxErsatzArea implements IF_RB_Component_Savable{
 
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
	
	public RB_ComboBoxErsatzArea(SQLField sqlF,  String cSQLQuery) throws myException {
		super(sqlF, cSQLQuery);
	}

	public RB_ComboBoxErsatzArea(SQLField sqlF, String[][] aDefArray) throws myException {
		super(sqlF, aDefArray);
	}

	public RB_ComboBoxErsatzArea(String Tablename,String cFieldName, String cSQLQuery) throws myException {
		this(new RB_SQLField(Tablename, cFieldName), cSQLQuery);
	}

	public RB_ComboBoxErsatzArea(String Tablename, String fieldName, String cFieldName, String[][] aDefArray) throws myException {
		this(new RB_SQLField(Tablename, fieldName), aDefArray);
	}

	
	
	
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if (dataObject.get_RecORD()==null) {
			this.get_oTextArea().setText("");
		} else {
			this.set_cActualMaskValue(((MyRECORD)dataObject.get_RecORD()).get_FormatedValue(this.Key.FIELDNAME()));
		}
	}


	


	@Override
	public void mark_Neutral() throws myException {
		this.get_oTextArea().setBorder(new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		this.get_oTextArea().setBackground(new E2_ColorEditBackground());
		this.get_oTextArea().setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));		
	}

	@Override
	public void mark_MustField() throws myException	{
		this.get_oTextArea().setBorder(new Border(1, Color.RED, Border.STYLE_SOLID));
	}

	@Override
	public void mark_Disabled() throws myException	{
		this.get_oTextArea().setBackground(new E2_ColorGray(230));
	}

	@Override
	public void mark_FalseInput() throws myException {
		this.get_oTextArea().setBackground(new E2_ColorHelpBackground());
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
		this.get_oTextArea().setAlignment(align);
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
