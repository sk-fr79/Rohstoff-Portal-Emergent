package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M_BT_Rechner extends MyE2_Button implements IF_RB_Component {

	private RB_KF key;

	private MyE2_TextField  o_rbtf_einzelPreis 		= null;
	private MyE2_TextField  o_rbtf_einzelPreis_fw 	= null;
	private MyE2_TextField  o_rbtf_waehrung 		= null;

	
	public KFIX_P_M_BT_Rechner(E2_ComponentMAP oCompMap) throws myException {
		super("Rechner");
		this._ttt("Verhältnis rechnen");
		this.add_oActionAgent(new actionCreateTaschenrechner(oCompMap));
	}


	private class actionCreateTaschenrechner extends XX_ActionAgent
	{

		private E2_BasicModuleContainer  oContainerRechner = null; 


		private E2_ComponentMAP compMap;

		private double dEinzelPreis =0.0;
		private double dEinzelPreisFw = 0.0;
		private double dWaehrungsKurs = 0.0;

		public actionCreateTaschenrechner(E2_ComponentMAP oCompMap) throws myException{
			super();

			o_rbtf_einzelPreis = 	(MyE2_TextField)new MyE2_TextField("", 100,100)._align_right();
			o_rbtf_einzelPreis_fw = (MyE2_TextField)new MyE2_TextField("", 100,100)._align_right();;
			o_rbtf_waehrung = 		(MyE2_TextField)new MyE2_TextField("", 100,100)._align_right();;

			this.compMap = oCompMap;

		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException 
		{
			this.oContainerRechner = new E2_BasicModuleContainer();

			this.oContainerRechner.set_bVisible_Row_For_Messages(true);

			if(S.isFull( ((RB_TextField)compMap.get__Comp(VPOS_KON.waehrungskurs).c()).getText())){
				MyDouble dWaehrung = new MyDouble( ((RB_TextField)compMap.get__Comp(VPOS_KON.waehrungskurs).c()).getText(),true);
				dWaehrungsKurs = dWaehrung.get_oDouble();
				o_rbtf_waehrung.setText(MyNumberFormater.formatDez(dWaehrungsKurs, 2, true));
			}

			if(S.isFull( ((RB_TextField)compMap.get__Comp(VPOS_KON.einzelpreis).c()).getText())){
				MyDouble dEpreis = new MyDouble( ((RB_TextField)compMap.get__Comp(VPOS_KON.einzelpreis).c()).getText(),true);
				dEinzelPreis = dEpreis.get_oDouble();
				o_rbtf_einzelPreis.setText(MyNumberFormater.formatDez(dEinzelPreis, 2, true));
			}

			if(S.isFull(((RB_TextField)compMap.get__Comp(VPOS_KON.einzelpreis_fw).c()).getText())){
				MyDouble dEpreisFw = new MyDouble( ((RB_TextField)compMap.get__Comp(VPOS_KON.einzelpreis_fw).c()).getText() );
				dEinzelPreisFw = dEpreisFw.get_oDouble() ;
				o_rbtf_einzelPreis_fw.setText(MyNumberFormater.formatDez(dEinzelPreisFw, 2, true));
			}

			
			MyE2_Grid oGridForElements = new MyE2_Grid(4,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11());
			MyE2_Button oButtonOK = new MyE2_Button("Berechne");
			oButtonOK
					._aaa(new ownRechnenAction(this))
					._aaa(new ownCloseAgent(this.oContainerRechner));
			
			try
			{
				oGridForElements.add(new MyE2_Label(new MyE2_String("Einzelpreis ",true,
						new RECORD_WAEHRUNG(bibALL.get_ID_BASISWAEHRUNG()).get_WAEHRUNGSSYMBOL_cUF_NN(""),false), 	MyE2_Label.STYLE_SMALL_BOLD()), MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I(5,5,5,5), 1, new Alignment(Alignment.RIGHT,Alignment.TOP)));

				oGridForElements.add(new MyE2_Label(new MyE2_String("Währungskurs "),								MyE2_Label.STYLE_SMALL_BOLD()), MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I(5,5,5,5), 1, new Alignment(Alignment.RIGHT,Alignment.TOP)));
				oGridForElements.add(new MyE2_Label(new MyE2_String("Einzelpreis FW"),								MyE2_Label.STYLE_SMALL_BOLD()), MyE2_Grid.GRID_LAYOUTDATA(E2_INSETS.I(5,5,5,5), 1, new Alignment(Alignment.RIGHT,Alignment.TOP)));
				oGridForElements.add(new MyE2_Label(new MyE2_String("")));

				oGridForElements.add(o_rbtf_einzelPreis, 	E2_INSETS.I(5,5,5,5));
				oGridForElements.add(o_rbtf_waehrung, 		E2_INSETS.I(5,5,5,5));
				oGridForElements.add(o_rbtf_einzelPreis_fw, E2_INSETS.I(5,5,5,5));
				oGridForElements.add(oButtonOK, E2_INSETS.I(5,5,5,5));
				
				this.oContainerRechner.add(oGridForElements, E2_INSETS.I_10_10_10_10);

				this.oContainerRechner.CREATE_AND_SHOW_POPUPWINDOW(new Extent(600), new Extent(200), new MyE2_String("Währungsrechner"));
				
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Unbekannter Fehler !!"));
			}

		}

	}

	@Override
	public void mark_Neutral() throws myException {}

	@Override
	public void mark_MustField() throws myException {}

	@Override
	public void mark_Disabled() throws myException {}

	@Override
	public void mark_FalseInput() throws myException {}

	@Override
	public void set_Alignment(Alignment align) throws myException {}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {}

	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {}

	@Override
	public RB_KF rb_KF() throws myException {
		return key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.key = key;

	}

	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() throws myException {
		return new Vector<RB_Validator_Component>();
	}
	
	private class ownCloseAgent extends XX_ActionAgent{
		
		private E2_BasicModuleContainer parent;
		
		public ownCloseAgent(E2_BasicModuleContainer c_parentContainer) {
			this.parent = c_parentContainer;
			
		}
		
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			parent.CLOSE_AND_DESTROY_POPUPWINDOW(false);
		}
		
	}

	private class ownRechnenAction extends XX_ActionAgent{
		
		private actionCreateTaschenrechner parent;
		
		public ownRechnenAction(actionCreateTaschenrechner actionCreateTaschenrechner) {
			super();
			parent = actionCreateTaschenrechner;
		}

		public void executeAgentCode(ExecINFO oExec_Info) throws myException 
		{
			
			MyDouble dEpreisFw = new MyDouble( o_rbtf_einzelPreis_fw.getText() );
			MyDouble dEpreis = new MyDouble( o_rbtf_einzelPreis.getText(),true);
			MyDouble dWaehrung = new MyDouble( o_rbtf_waehrung.getText(),true);
			
			if(dEpreis.get_bOK()){
				parent.dEinzelPreis = dEpreis.get_oDouble();
				}
			if(dEpreisFw.get_bOK()){
				parent.dEinzelPreisFw = dEpreisFw.get_oDouble();
				}
			if(dWaehrung.get_bOK()){
				parent.dWaehrungsKurs = dWaehrung.get_oDouble();
				}
			
		
			int iEmpty = 0;

			if (o_rbtf_einzelPreis.getText().isEmpty()) iEmpty++;
			if (o_rbtf_einzelPreis_fw.getText().isEmpty()) iEmpty++;
			if (o_rbtf_waehrung.getText().isEmpty()) iEmpty++;

			if (iEmpty != 1)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte immer 2 Felder ausfüllen, 1 Feld leerlassen!!"));
				return;
			}

			if (o_rbtf_einzelPreis.getText().isEmpty())
			{

				parent.dEinzelPreis = bibALL.Round(parent.dEinzelPreisFw/parent.dWaehrungsKurs, 2);

				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.einzelpreis)).setText(MyNumberFormater.formatDez(parent.dEinzelPreis, 2, true));
				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.einzelpreis_fw)).setText(MyNumberFormater.formatDez(parent.dEinzelPreisFw, 2, true));
				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.waehrungskurs)).setText(MyNumberFormater.formatDez(parent.dWaehrungsKurs, 4, true));
			}

			
			if (o_rbtf_einzelPreis_fw.getText().isEmpty())
			{

				parent.dEinzelPreisFw = bibALL.Round(parent.dEinzelPreis*parent.dWaehrungsKurs, 2);

				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.einzelpreis)).setText(MyNumberFormater.formatDez(parent.dEinzelPreis, 2, true));
				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.einzelpreis_fw)).setText(MyNumberFormater.formatDez(parent.dEinzelPreisFw, 2, true));
				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.waehrungskurs)).setText(MyNumberFormater.formatDez(parent.dWaehrungsKurs, 4, true));
			}


			if (o_rbtf_waehrung.getText().isEmpty())
			{

				parent.dWaehrungsKurs = bibALL.Round(parent.dEinzelPreisFw/parent.dEinzelPreis, 4);

				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.einzelpreis)).setText(MyNumberFormater.formatDez(parent.dEinzelPreis, 2, true));
				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.einzelpreis_fw)).setText(MyNumberFormater.formatDez(parent.dEinzelPreisFw, 2, true));
				((RB_TextField)parent.compMap.get__Comp(VPOS_KON.waehrungskurs)).setText(MyNumberFormater.formatDez(parent.dWaehrungsKurs, 4, true));

			}

		}
	}
}
