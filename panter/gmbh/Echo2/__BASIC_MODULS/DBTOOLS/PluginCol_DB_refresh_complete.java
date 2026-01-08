package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS;

import java.util.Vector;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_TextArea;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class PluginCol_DB_refresh_complete extends Basic_PluginColumn {

	private Vector<GeneratorAction__IF> actions = new Vector<GeneratorAction__IF>();
	
	private MyE2_TextArea                tf_errors = new MyE2_TextArea("",900,-1,15);
	
	public PluginCol_DB_refresh_complete(ContainerForVerwaltungsTOOLS oMothercontainer) {
		super(oMothercontainer);
		
		this.actions.add(new GeneratorAction_BuildAllTableViews());
		this.actions.add(new GeneratorAction_BuildAllSpecialViews());
		this.actions.add(new GeneratorAction_BuildAllTableSequences());
		this.actions.add(new GeneratorAction_BuildAllTableTriggers());
		
		MyE2_Grid  grid_center = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		grid_center.setWidth(new Extent(900));
		grid_center.setColumnWidth(0, new Extent(900));
		this.add(grid_center, MyE2_Column.LAYOUT_CENTER(E2_INSETS.I(30,20,30,10)));
		
		MyE2_Button  button_db_refresh = new MyE2_Button(new MyE2_String("Datenbank-Objekte neu bauen ..."), MyE2_Button.StyleTextButton_LOOK_like_LABEL_WithBorder_Center(new E2_FontBold(10)));
		
		for (GeneratorAction__IF action: this.actions) {
			button_db_refresh.add_oActionAgent((XX_ActionAgent) action);
		}
		button_db_refresh.add_oActionAgent(new lastOwnAction());

		button_db_refresh.setToolTipText(new MyE2_String("Erstellen von folgenden DB-Elementen: \n"
										+ "Alle Tabellen-Views, alle Sonder-Views, \n"
										+ "alle Tabellen-Trigger, \n"
										+ "alle Tabellen-Sequences").CTrans());
		
		grid_center.add(button_db_refresh, MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(5,2,5,2)));
		grid_center.add(this.get_TextArea4Output(), MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(5,2,5,2)));
		grid_center.add(this.tf_errors, MyE2_Grid.LAYOUT_CENTER(E2_INSETS.I(5,2,5,2)));

	}

	private class lastOwnAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			PluginCol_DB_refresh_complete oThis = PluginCol_DB_refresh_complete.this;
			
			StringBuffer buffMsg = new StringBuffer();
			StringBuffer buffErr = new StringBuffer();
			
			for (GeneratorAction__IF action: oThis.actions) {
				
				buffMsg.append("\n");
				buffMsg.append(new MyE2_String("Meldungen - Bereich: ").CTrans()+action.get_description().CTrans()+"\n");
				buffMsg.append("==============================================================================================\n");
				buffMsg.append(S.NN(bibALL.Concatenate(action.get_v_meldungen_ok(), 		"..\n", "")));
				buffMsg.append("\n");
				buffMsg.append("==============================================================================================\n");
				buffMsg.append("\n");
				
				buffErr.append("\n");
				buffErr.append(new MyE2_String("Fehler - Bereich: ").CTrans()+action.get_description().CTrans()+"\n");
				buffErr.append("==============================================================================================\n");
				buffErr.append(S.NN(bibALL.Concatenate(action.get_v_meldungen_fehler(), 		"..\n", "")));
				buffErr.append("\n");
				buffErr.append("==============================================================================================\n");
				buffErr.append("\n");
				
			}

			oThis.get_TextArea4Output().setText(buffMsg.toString());
			oThis.tf_errors.setText(buffErr.toString());
			oThis.tf_errors.setBackground(Color.RED);
		}
	}
	
	
}
