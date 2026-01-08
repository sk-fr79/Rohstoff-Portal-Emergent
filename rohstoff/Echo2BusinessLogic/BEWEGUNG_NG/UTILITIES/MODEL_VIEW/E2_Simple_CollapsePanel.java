package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.UTILITIES.MODEL_VIEW;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class E2_Simple_CollapsePanel extends MyE2_Grid {

	private Component comp;
	private boolean collapsed = true;
	
	private E2_Button collapsedBt;
	
	public E2_Simple_CollapsePanel(String collapsePanelTitle, Component oComponentToCollapse){
		super(2);
		this.setStyle(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		this.comp = oComponentToCollapse;	
		this.collapsedBt = new E2_Button()._image(E2_ResourceIcon.get_RI("expandopen.png"))._aaa(new ownCollapseButtonActionAgent());
		
		this.setColumnWidth(0, new Extent(16));
		
		this.add(collapsedBt, 1,1,E2_INSETS.I(0,5,0,5), E2_ALIGN.LEFT_TOP);
		this.add(new RB_lab(collapsePanelTitle)._b()._fsa(-1)._i(), 1,1,E2_INSETS.I(0,5,0,5),E2_ALIGN.LEFT_TOP);
		
	}
	
	public E2_Simple_CollapsePanel setCollapsed(boolean collapsed){
		this.collapsed=collapsed;
		rebuild();
		return this;
	}

	private void rebuild() {
		if(!collapsed){
			E2_Simple_CollapsePanel.this.add(E2_Simple_CollapsePanel.this.comp,2, 1, E2_INSETS.I(5,0,5,10), E2_ALIGN.LEFT_TOP);
			E2_Simple_CollapsePanel.this.collapsedBt._image(E2_ResourceIcon.get_RI("expandclose.png"));
			E2_Simple_CollapsePanel.this.collapsed=false;
		}else{
			E2_Simple_CollapsePanel.this.remove(E2_Simple_CollapsePanel.this.comp);
			E2_Simple_CollapsePanel.this.collapsedBt._image(E2_ResourceIcon.get_RI("expandopen.png"));
			E2_Simple_CollapsePanel.this.collapsed=true;
		}
	}
	
	private class ownCollapseButtonActionAgent extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			if(collapsed){
				collapsed=false;
			}else{
				collapsed=true;
			}
			rebuild();
			
		}	
	}
	
}
