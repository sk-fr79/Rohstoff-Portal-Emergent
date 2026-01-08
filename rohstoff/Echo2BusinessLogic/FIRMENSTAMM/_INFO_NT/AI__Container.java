package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT;

import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.UserSettings.IF_Saveable;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Grid;
import panter.gmbh.basics4project.ENUM_MANDANT_DECISION;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM._INFO_NT.AI__Const.BLOCK_TO_SHOW;

public class AI__Container extends MyE2_Grid {
	private static int[] iBreite = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};

	private MyE2_ContainerEx 						container = null;
	private TreeMap<BLOCK_TO_SHOW, IF_Saveable>  	hm_Steuerpult = new TreeMap<AI__Const.BLOCK_TO_SHOW, IF_Saveable>();

	private Vector<String>                          v_id_adressen = new Vector<String>();
	private MyE2_Grid 								gridSteuerbuttons= new MyE2_Grid(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_VERTICAL_W100());
	private AR_Grid  								infoGrid = new AR_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	public AI__Container(Vector<String> id_adresses) throws myException {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
		this.v_id_adressen.removeAllElements();
		this.v_id_adressen.addAll(id_adresses);
      	
		String[] abstaende = {"0","1","2","3","4","5","6","7","8"}; 
		String[] farbdiff = {"0","5","10","15","20","25","30"};
		
		//steuerelemente aufbauen
		for (BLOCK_TO_SHOW block: BLOCK_TO_SHOW.values()) {
			if (!block.name().startsWith("SF_")) {
				hm_Steuerpult.put(block, new AI__CheckBox(block.label4User(), block.toolTipText(), false, block));
			}
		}
		hm_Steuerpult.put(BLOCK_TO_SHOW.SF_SETZE_ABSTAND, 		new AI__SelectSettings(	BLOCK_TO_SHOW.SF_SETZE_ABSTAND.label4User(),		
																						abstaende, 
																						"1", 
																						new Extent(60),
																						BLOCK_TO_SHOW.SF_SETZE_ABSTAND.toolTipText()));
		hm_Steuerpult.put(BLOCK_TO_SHOW.SF_SETZE_FARBDIFFERENZ, new AI__SelectSettings(	BLOCK_TO_SHOW.SF_SETZE_FARBDIFFERENZ.label4User(),
																						farbdiff, 
																						"1", 
																						new Extent(60),
																						BLOCK_TO_SHOW.SF_SETZE_FARBDIFFERENZ.toolTipText()));
		//fertig mit aufbau
		
		
		
		
		//jetzt anordnen
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.MITARBEITER).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.LIEFERADRESSEN).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.MITARBEITER_MAIL).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.MITARBEITER_TELEFON).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.LIEFERADRESSEN_MAIL).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.LIEFERADRESSEN_TELEFON).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.TELEFON).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.EMAIL).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.ZUSATZINFOS).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.ZUSATZINFOS_ANLAGEN).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.ANLAGEN).get_Comp(),E2_INSETS.I(2,0,2,0));
      	
      	//2018.11.27: fur neu info button, meldung sind ein neu reiter.
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.MELDUNGEN).get_Comp(),E2_INSETS.I(2,0,2,0));
      	
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.INAKTIVE_MITARBEITER_AUSBLENDEN).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.INAKTIVE_LIEFERADRESSEN_AUSBLENDEN).get_Comp(),E2_INSETS.I(2,0,2,0));
      	
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.BESCHRIFTUNG).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(new MyE2_Label(""),E2_INSETS.I(2,0,2,0));
      	
     	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.SF_SETZE_ABSTAND).get_Comp(),E2_INSETS.I(2,0,2,0));
      	gridSteuerbuttons.add(this.hm_Steuerpult.get(BLOCK_TO_SHOW.SF_SETZE_FARBDIFFERENZ).get_Comp(),E2_INSETS.I(2,0,2,0));
    	
      	
      	
      	//settings laden
      	new AI__SaveSetting(hm_Steuerpult).RESTORE();
      	
      	
      	for (IF_Saveable cb: this.hm_Steuerpult.values()) {
      		cb.add_action(new ownActionAgentRebuild());
      		cb.add_action(new ownActionAgentSaveSettings());
      	}
      	
      	this.add(gridSteuerbuttons,E2_INSETS.I(2,2,2,10));
      	
      	this.container = new MyE2_ContainerEx(infoGrid);
      	this.add(container);
      	
      	this._build();
	}
	
	public void _build() throws myException {
		this.infoGrid.removeAll();

		AI_AdressBlock fi  = new AI_AdressBlock(this.v_id_adressen, this.hm_Steuerpult);
      	fi.fill(infoGrid);

	}
	
	public void set_height_of_ContainerEx(Extent iHeight) {
		this.container.setHeight(iHeight);
	}

	
	private class ownActionAgentRebuild extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			AI__Container.this._build();
		}
	}
	
	private class ownActionAgentSaveSettings extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new AI__SaveSetting(AI__Container.this.hm_Steuerpult).SAVE();
		}
	}

	public Vector<String> get_v_id_adressen() {
		return v_id_adressen;
	}

	public MyE2_ContainerEx get_Container_EX() {
		return this.container;
	}
	
	
}
