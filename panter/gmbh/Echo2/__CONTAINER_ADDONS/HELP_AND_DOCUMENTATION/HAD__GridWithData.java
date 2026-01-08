package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_CONST.TS_DEFINITION;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest;
import panter.gmbh.Echo2.RecursiveSearch.TREASURE_CHEST.TS_Treasure_Chest_Container_IF;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.activeReport_NG.AR_Grid;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT_ZU_MODUL;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VERSION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_HILFETEXT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.TermSimple;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;


/**
 * stellt eine hilfeklasse mit einer definierten auswahl basierend auf einem modulkenner dar
 * @author martin
 *
 */
public class HAD__GridWithData extends MyE2_Grid implements TS_Treasure_Chest_Container_IF {
	private static int[] iBreite = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};

	private MyE2_ContainerEx 		container = null;

	private AR_Grid  				infoGrid = new AR_Grid(iBreite,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
	
	private String  				modulKenner = null;

	
	private HAD__GridBedienPanel    panel = null;
	private RECLIST_HILFETEXT  		rl_hi = null;
	
	private Vector<HAD__CheckBox>   v_cbs = new Vector<HAD__CheckBox>();
	
	public HAD__GridWithData(String p_modulKenner) throws myException {
		super(1, MyE2_Grid.STYLE_GRID_DDARK_BORDER_W100());
	
		this.panel = new HAD__GridBedienPanel(this);
	
		
		this.modulKenner = p_modulKenner;
		
		this.panel.get_SelFieldTicketStatus().add_oActionAgent(new ownActionAgentRebuild());
		this.panel.get_SelFieldTicketTyp().add_oActionAgent(new ownActionAgentRebuild());
		this.panel.get_selFieldVersionen().add_oActionAgent(new ownActionAgentRebuild());
		this.panel.get_selFieldModule().add_oActionAgent(new ownActionAgentRebuild());
		this.panel.get_selSorter().add_oActionAgent(new ownActionAgentRebuild());
		
		this.panel.get_Bt_start().add_oActionAgent(new ownActionAgentRebuild());
		
		this.panel.get_Bt_clear().add_oActionAgent(new ownActionAgentClearText());
		this.panel.get_Bt_clear().add_oActionAgent(new ownActionAgentRebuild());
		
		//modulkenner auf dem panel einstellen
		this.panel.get_selFieldModule().set_ActiveValue_OR_FirstValue(this.modulKenner);
		
		
      	this.add(panel,E2_INSETS.I(2,2,2,10));
      	
      	this.container = new MyE2_ContainerEx(infoGrid);
      	this.add(container);
      	
      	this._build();
	}
	
	public void _build() throws myException {
		this.infoGrid.removeAll();

		this.v_cbs.clear();
		
		String modul_kenner = "";
		if (S.isFull(this.panel.get_selFieldModule().get_ActualWert())) {
			modul_kenner = this.panel.get_selFieldModule().get_ActualWert();      //kann aber geändert werden
		} 
		
		SEL  sel_hi = new SEL(	_TAB.hilfetext)
				.ADDFIELD(VERSION.version)
				.ADDFIELD(VERSION.version_code)
				.FROM(_TAB.hilfetext).ADD_Distinct()
				.LEFTOUTER(_TAB.version, 			VERSION.id_version, 			HILFETEXT.id_version)
				.LEFTOUTER(_TAB.user, 				USER.id_user, 					HILFETEXT.id_user_bearbeiter)
				.LEFTOUTER(_TAB.hilfetext_zu_modul, HILFETEXT_ZU_MODUL.id_hilfetext,HILFETEXT.id_hilfetext)
				.ORDERUP(new TermSimple(this.panel.get_selSorter().get_SortString()));

		if (S.isFull(modul_kenner)) {
			sel_hi.WHERE(new vgl(HILFETEXT.modulkenner, modul_kenner));
			sel_hi.OR(new vgl(HILFETEXT_ZU_MODUL.modulkenner, modul_kenner));
		}

		if (S.isFull(this.panel.get_SelFieldTicketStatus().get_ActualWert())) {
			sel_hi.WHERE(new vgl(HILFETEXT.status, this.panel.get_SelFieldTicketStatus().get_ActualWert()));
		}
		if (S.isFull(this.panel.get_SelFieldTicketTyp().get_ActualWert())) {
			sel_hi.WHERE(new vgl(HILFETEXT.typ, this.panel.get_SelFieldTicketTyp().get_ActualWert()));
		}
		if (S.isFull(this.panel.get_selFieldVersionen().get_ActualWert())) {
			sel_hi.WHERE(new vgl(HILFETEXT.id_version, this.panel.get_selFieldVersionen().get_ActualWert()));
		}
		
		 this.rl_hi = new RECLIST_HILFETEXT(sel_hi.s());

		HAD_HelpText_DataBlock hb  = new HAD_HelpText_DataBlock(rl_hi,this.panel.get_TfSuchtext().getText(),this.v_cbs);
      	hb.fill(infoGrid);

	}
	
	public void set_height_of_ContainerEx(Extent iHeight) {
		this.container.setHeight(iHeight);
	}

	
	private class ownActionAgentRebuild extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			HAD__GridWithData.this._build();
		}
	}

	private class ownActionAgentClearText extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			HAD__GridWithData.this.panel.get_TfSuchtext().setText("");
		}
	}
	
	
	public MyE2_ContainerEx get_Container_EX() {
		return this.container;
	}

	
	
	
	@Override
	public Vector<TS_Treasure_Chest> get_my_treasure_chests() throws myException {
		Vector<TS_Treasure_Chest> v= new Vector<TS_Treasure_Chest>();
		v.add(new ownTreasureChest());
		return v;
	}
	
	private class ownTreasureChest extends TS_Treasure_Chest {

		@Override
		public TS_DEFINITION get_TREASURE_CHEST_DEF() {
			return TS_DEFINITION.POPUP_HILFETEXTE;
		}

		@Override
		public Object get_TREASURE_CHEST() throws myException {
			return HAD__GridWithData.this;
		}
		
	}


	public String get_modulKenner() {
		return modulKenner;
	}

	public RECLIST_HILFETEXT get_actualReclistHilfetext() {
		return rl_hi;
	}

	public Vector<HAD__CheckBox> get_v_cbs() {
		return v_cbs;
	}

	public HAD__GridBedienPanel get_GridBedienPanel() {
		return panel;
	}
	
}
