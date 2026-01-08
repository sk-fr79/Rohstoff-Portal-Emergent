package panter.gmbh.Echo2.Messaging;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public abstract class MyE2_BASIC_MessageWithAddonComponent extends MyE2_Message  implements IF_Message_WithButtons {

	private MyE2IF__Component 	o_ComponentAddOn = null;
	private Extent 				o_ExtMessage = null;
	private Extent 				o_ExtComponent = null;
//	private MyE2_Grid   		oGridWithAddons = null;
	
	public MyE2_BASIC_MessageWithAddonComponent(int iTypeMsg, MyString cmessage, MyE2IF__Component oComponent, Extent oExtMessage, Extent oExtComponent) {
		super(cmessage,iTypeMsg,false);
		this.o_ComponentAddOn = oComponent;
		this.o_ExtMessage = oExtMessage;
		this.o_ExtComponent = oExtComponent;
	}

	
	public MyE2_BASIC_MessageWithAddonComponent(int iTypeMsg,MyString cmessage, MyE2IF__Component oComponent) {
		super(cmessage,iTypeMsg,false);
		this.o_ComponentAddOn = oComponent;
		this.o_ExtMessage = new Extent(400);
		this.o_ExtComponent = new Extent(100);
	}

	@Override
	public Component get_ComponentWithMessageAndButtons(MyE2_Message oMessage) {
		MyE2_Grid oGridRueck = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		MyE2_Grid  oGrid1 = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		MyE2_Label  oLabel = new MyE2_Label(oMessage.get_cMessage(),new E2_FontPlain(0),true);
		GridLayoutData oGL = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), this.get_Color_4_MessageBackground(oMessage), 1, 1);
		oLabel.setLayoutData(oGL);
		oGrid1.add(oLabel);

		
		MyE2_Grid  oGrid2 = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		if (((Component)this.o_ComponentAddOn).getLayoutData()!=null) {
			oGrid2.add_RAW_noLayoutData((Component)this.o_ComponentAddOn);
		} else {
			oGrid2.add((Component)this.o_ComponentAddOn, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), this.get_Color_4_MessageBackground(oMessage), 1, 1));
		}

		oGridRueck.add(oGrid1);
		oGridRueck.add(oGrid2);
		
		oGridRueck.setColumnWidth(0, this.o_ExtMessage);
		oGridRueck.setColumnWidth(1, this.o_ExtComponent);
		
		return oGridRueck;
		
	}

	@Override
	public Insets get_Insets_4_MessageInMessageGrid(MyE2_Message oMessage) {
		return E2_INSETS.I(2,2,2,2);
	}
	



	@Override
	public Vector<E2_IF_Handles_ActionAgents> get_vActiveComponents(MyE2_Message oMessage) {
		
		Vector<E2_IF_Handles_ActionAgents> vRueck = new Vector<E2_IF_Handles_ActionAgents>();

		if (this.o_ComponentAddOn instanceof E2_IF_Handles_ActionAgents) {
			vRueck.add((E2_IF_Handles_ActionAgents)this.o_ComponentAddOn);
		}
		
		return vRueck;
	}

	@Override
	public void disable_ActiveComponents(MyE2_Message oMessage) throws myException {
		Vector<E2_IF_Handles_ActionAgents> vComps = this.get_vActiveComponents(oMessage);
		for (E2_IF_Handles_ActionAgents oComp: vComps) {
			if (oComp instanceof MyE2IF__Component) {
				((MyE2IF__Component)oComp).set_bEnabled_For_Edit(false);
			} else if (oComp instanceof Component) {
				((Component)oComp).setEnabled(false);
			}
		}
	}

	@Override
	public void enable_ActiveComponents(MyE2_Message oMessage) throws myException {
		Vector<E2_IF_Handles_ActionAgents> vComps = this.get_vActiveComponents(oMessage);
		for (E2_IF_Handles_ActionAgents oComp: vComps) {
			if (oComp instanceof MyE2IF__Component) {
				((MyE2IF__Component)oComp).set_bEnabled_For_Edit(true);
			} else if (oComp instanceof Component) {
				((Component)oComp).setEnabled(true);
			}
		}
	}


	public MyE2IF__Component getComponentAddOn() {
		return o_ComponentAddOn;
	}
	
	public Component getAddonComponent() {
		return (Component)o_ComponentAddOn;
	}

}
