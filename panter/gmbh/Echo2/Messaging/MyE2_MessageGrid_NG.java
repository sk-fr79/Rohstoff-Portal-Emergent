package panter.gmbh.Echo2.Messaging;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_PopUp_OR_Down_Components;


public class MyE2_MessageGrid_NG extends MyE2_Grid
{

	private MyE2_MessageVector 				oMessageVector = 		null;
	
	//weiteres grid zur darstellung der ausgeklappten messages (falls mehr als eine message da ist)
	private MyE2_Grid  						oGridRestMessages = 	new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_GREY_BORDER_NO_INSETS_W100());
	private boolean 						bZeigeAlleAufEinmal =  	false;
	private E2_BasicModuleContainer 		o_ContainingContainer = null;
	
	
	public MyE2_MessageGrid_NG(MyE2_MessageVector vMessageVector, boolean bExtendable, E2_BasicModuleContainer oContainingContainer)
	{
		super(2,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		//in der ersten spalte steht entweder ein leerer label oder der ausklapp-button
		this.setColumnWidth(0, new Extent(10));
		//this.setColumnWidth(2, new Extent(1));
		
		this.o_ContainingContainer = oContainingContainer;
		
		if (this.o_ContainingContainer!=null &&this.o_ContainingContainer.IS_Popup()) {
			this.setWidth(new Extent(99, Extent.PERCENT));
		} else {
			this.setWidth(new Extent(1000));
		}
		
		this.oMessageVector = 		vMessageVector;
		this.bZeigeAlleAufEinmal = 	this.oMessageVector.get_bMustBeExpandedAtStart();
		
		
		if (this.oMessageVector.size()==0) {
			this.oMessageVector.add_MESSAGE(new MyE2_Warning_Message(new MyE2_String("Keine Meldungen !")));
		}
		
		
		//---sort beginn
		bibMSG.sort_Messages(this.oMessageVector);
		
		
		//ausklapp-button nur sinnvoll, wenn mehr als eine meldung vorhanden
		bExtendable = bExtendable && (this.oMessageVector.size()>1);
		
        if (bExtendable) {
        	this.add_Row_main(this.oMessageVector.get(0),  true);
        	
        	for (int i=1;i<this.oMessageVector.size();i++) {
        		MyE2_Message msg = this.oMessageVector.get(i);
        		this.add_Row_to_gridRestMessages(msg);
        	}
        } else {
        	for (MyE2_Message  msg: this.oMessageVector) {
        		this.add_Row_main(msg, false);
        	}
        }

		//nach dem anzeigen wird der messageVector geleert
		this.oMessageVector.removeAllElements();
	}

	
	
	/**
	 * hauptmessagezeile einfuegen
	 * @param msg
	 * @param bAddExtenderButton
	 */
	private void add_Row_main(MyE2_Message msg, boolean bAddExtenderButton) {
		
		Color oCol4Layout = new E2_ColorEditBackground();
		
		if (msg.get_iType()==MyE2_Message.TYP_ALARM) {
			oCol4Layout = new E2_ColorAlarm();
		} else if (msg.get_iType()==MyE2_Message.TYP_WARNING) {
			oCol4Layout = new E2_ColorHelpBackground();
		} 

		if (msg instanceof IF_Message_WithButtons) {

			IF_Message_WithButtons msgWB = (IF_Message_WithButtons)msg;
			oCol4Layout=msgWB.get_Color_4_MessageBackground(msg);
			Insets insets4Layout = msgWB.get_Insets_4_MessageInMessageGrid(msg);
			
			if (bAddExtenderButton) {
				this.add(new ownBottonPopDownWeitereMeldungen(oCol4Layout), this.get_Layout(1,E2_INSETS.I(0,1,4,1), oCol4Layout));
			} else {
				this.add(new MyE2_Label(""), this.get_Layout(1,E2_INSETS.I(0,1,0,1),oCol4Layout));
			}
			this.add( msgWB.get_ComponentWithMessageAndButtons(msg) ,this.get_Layout(1,insets4Layout,oCol4Layout));
			
		} else {
			
			if (bAddExtenderButton) {
				this.add(new ownBottonPopDownWeitereMeldungen(oCol4Layout), this.get_Layout(1,E2_INSETS.I(0,1,4,1), oCol4Layout));
			} else {
				this.add(new MyE2_Label(""), this.get_Layout(1,E2_INSETS.I(0,1,0,1),oCol4Layout));
			}
			this.add(new MyE2_Label(msg.get_cMessage(), true), this.get_Layout(1,E2_INSETS.I(0,1,2,1),oCol4Layout));

		}
		
		
	}

	/**
	 * restmessagezeile einfuegen (Restgrid hat eine spalte, jede message kommt in eigenes grid
	 * @param msg
	 */
	private void add_Row_to_gridRestMessages(MyE2_Message msg) {
		
		Color oCol4Layout = new E2_ColorEditBackground();
		
		if (msg.get_iType()==MyE2_Message.TYP_ALARM) {
			oCol4Layout = new E2_ColorAlarm();
		} else if (msg.get_iType()==MyE2_Message.TYP_WARNING) {
			oCol4Layout = new E2_ColorHelpBackground();
		} 

		MyE2_Grid  oGridInnen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		this.oGridRestMessages.add(oGridInnen, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(1,1,1,1), oCol4Layout, 1, 1));
		
		//jetzt wird fuer jede message eine eigene gridInnen gefuellt
		if (msg instanceof IF_Message_WithButtons) {

			IF_Message_WithButtons msgWB = (IF_Message_WithButtons)msg;
			oCol4Layout=msgWB.get_Color_4_MessageBackground(msg);
			Insets insets4Layout = msgWB.get_Insets_4_MessageInMessageGrid(msg);
			oGridInnen.add( msgWB.get_ComponentWithMessageAndButtons(msg) ,this.get_Layout(1,insets4Layout,oCol4Layout));
			
		} else {
			oGridInnen.add(new MyE2_Label(msg.get_cMessage(), true), this.get_Layout(1,E2_INSETS.I(2,2,2,2),oCol4Layout));
		}
		
		
	}

	
	private class ownBottonPopDownWeitereMeldungen extends MyE2_PopUp_OR_Down_Components {

		public ownBottonPopDownWeitereMeldungen(Color oCol) {
			super(	E2_ResourceIcon.get_RI("inforound.png"),
					E2_ResourceIcon.get_RI("leer.png"),
					new Alignment(Alignment.LEFT,Alignment.BOTTOM));
			
			this.setPopUpLeftOffset(20);
			this.setPopUpTopOffset(5);
			this.setBackground(oCol);
			
			this.ADD_Component(MyE2_MessageGrid_NG.this.oGridRestMessages,E2_INSETS.I_0_0_0_0);
			
			this.setToolTipText(new MyE2_String("Weitere Meldungen anzeigen ...").CTrans());
			
			if (MyE2_MessageGrid_NG.this.bZeigeAlleAufEinmal) this.setExpanded(true);
		}
		
	}
	
	
	
	private GridLayoutData get_Layout(int iColSpan, Insets oIn, Color  oCol) {
		GridLayoutData oGL = new GridLayoutData();
		if (oCol!=null) {
			oGL.setBackground(oCol);
		}
		if (oIn != null) {
			oGL.setInsets(oIn);
		}
		oGL.setColumnSpan(iColSpan);
		oGL.setAlignment(new Alignment(Alignment.LEFT, Alignment.CENTER));
		return oGL;
	}

	
	
}
