package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField_old;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.E2_ColorAnzeige;
import panter.gmbh.Echo2.components.E2_ColorSelectPopUp;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSKLASSE_DEF;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_compColorSelector extends MyE2_Grid implements IF_RB_Component {

	//beinhaltet: 3 textfelder, 1 refresh-button, 1 Farbanzeige-Label und 1 Farbwaehler
	
	private RB_TextField_old  	tfRed = null;
	private RB_TextField_old    tfGreen = null;
	private RB_TextField_old    tfBlue = null;
	private RB_TextField_old    tfSort = null;
	
	private MyE2_Button     btRefreshColor = new MyE2_Button(E2_ResourceIcon.get_RI("reload.png"));
	private MyE2_Grid       gridShowColor  = new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_BORDER(Color.BLACK));
	private MyE2_Button     btStartColorSelektor = new MyE2_Button(new MyE2_String("Farbe wählen"));
	
	private RB_KF           rb_Key = null;
	
	
	
	public ADK_M_compColorSelector(RB_TextField_old p_tfRed, RB_TextField_old p_tfGreen, RB_TextField_old p_tfBlue, RB_TextField_old p_tfSort) throws myException {
		super();
		
		this.tfRed = 	p_tfRed;
		this.tfGreen = 	p_tfGreen;
		this.tfBlue = 	p_tfBlue;
		this.tfSort = 	p_tfSort;
		
		this.setSize(4);
		
		int[] iBreiten = {60,60,60,20,60,100,100};
		E2_Grid4Mask gr_m = new E2_Grid4Mask()
						.set_Cols(iBreiten)
						.set_LayoutStandard4CenteredView()
						.add(new MyE2_Label(new MyE2_String("Rot")),tfRed)
						.add(new MyE2_Label(new MyE2_String("Grün")),tfGreen)
						.add(new MyE2_Label(new MyE2_String("Blau")),tfBlue)
						.add(new MyE2_Label(new MyE2_String("")),btRefreshColor)
						.add(new MyE2_Label(new MyE2_String("")),btStartColorSelektor)
						.add(new MyE2_Label(new MyE2_String("gewählte Farbe")),gridShowColor)
						.add(new MyE2_Label(new MyE2_String("Sortierung")),tfSort)
						;
		
		
		this.add(gr_m);

		this.btRefreshColor.setToolTipText(new MyE2_String("Manuell erfaßte Farbwerte einlesen und darstellen").CTrans());
		this.btRefreshColor.add_oActionAgent(new ownActionRefresh());
		this.btStartColorSelektor.add_oActionAgent(new colorButtonAction());
		
		this.gridShowColor.add(new MyE2_Label("   "));
		this.gridShowColor.setRowHeight(0, new Extent(20));
		this.gridShowColor.setColumnWidth(0, new Extent(100));

		
	}

	
	private Color readActualColor() {
		Color colRueck = new E2_ColorAnzeige(this.tfRed.getText(), this.tfGreen.getText(), this.tfBlue.getText(), 2, 2, null).get_Color();
		return colRueck;
	}
	

	private void fillReferenceColorField_clearIfFalse() {
		Color colActual = this.readActualColor();
		this.gridShowColor.setBackground(colActual);
		if (colActual == null) {
			this.tfRed.setText("");
			this.tfGreen.setText("");
			this.tfBlue.setText("");
		}
	}
	
	
	
	private class ownActionRefresh extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			ADK_M_compColorSelector.this.fillReferenceColorField_clearIfFalse();
		}
	}
	
	
	
	
	private class colorButtonAction extends XX_ActionAgent 	{
		
		private E2_ColorSelectPopUp colorSel = null;
		
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException 	{
			this.colorSel = new E2_ColorSelectPopUp(new ownActionAfterSelect(), ADK_M_compColorSelector.this.readActualColor());
		}
		
		
		private class ownActionAfterSelect extends XX_ActionAgent {

			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				ADK_M_compColorSelector oThis = ADK_M_compColorSelector.this;
				E2_ColorSelectPopUp Sel =  colorButtonAction.this.colorSel;
				
				oThis.tfRed.setText(""+Sel.get_RED());
				oThis.tfGreen.setText(""+Sel.get_GREEN());
				oThis.tfBlue.setText(""+Sel.get_BLUE());
				
				oThis.fillReferenceColorField_clearIfFalse();
			}
			
		}
		
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		this.tfRed.setText("");
		this.tfGreen.setText("");
		this.tfBlue.setText("");
		
		if (dataObject.get_RecORD()!=null) {
			RECORD_ADRESSKLASSE_DEF rec = (RECORD_ADRESSKLASSE_DEF)dataObject.get_RecORD();
			
			this.tfRed.setText(rec.get_COLOR_RED_cF_NN(""));
			this.tfGreen.setText(rec.get_COLOR_GREEN_cF_NN(""));
			this.tfBlue.setText(rec.get_COLOR_BLUE_cF_NN(""));
			
			this.fillReferenceColorField_clearIfFalse();
		}
	}

	@Override
	public void rb_set_db_value_manual(String valueFormated)	throws myException {
		
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.rb_Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.rb_Key=key;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return null;
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
		
	}

	@Override
	public void mark_Disabled() throws myException {
		
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}


	
	
}
