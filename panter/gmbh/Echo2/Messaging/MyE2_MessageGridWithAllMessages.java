package panter.gmbh.Echo2.Messaging;


import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;


public class MyE2_MessageGridWithAllMessages extends MyE2_Grid
{

	private MyE2_MessageVector 				oMessageVector = 		null;
	
	
	
	public MyE2_MessageGridWithAllMessages(MyE2_MessageVector vMessageVector)
	{
		super(1,MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.setWidth(new Extent(99,Extent.PERCENT));
		this.oMessageVector = 		vMessageVector;
		
		//---sort beginn
		bibMSG.sort_Messages(this.oMessageVector);
		
		for (MyE2_Message msg: vMessageVector) {
			this.add_Row_to_grid(msg);
		}

		//nach dem anzeigen wird der messageVector geleert
		this.oMessageVector.removeAllElements();
	}

	
	

	/**
	 * restmessagezeile einfuegen (Restgrid hat eine spalte, jede message kommt in eigenes grid
	 * @param msg
	 */
	private void add_Row_to_grid(MyE2_Message msg) {
		
		Color oCol4Layout = new E2_ColorEditBackground();
		
		if (msg.get_iType()==MyE2_Message.TYP_ALARM) {
			oCol4Layout = new E2_ColorAlarm();
		} else if (msg.get_iType()==MyE2_Message.TYP_WARNING) {
			oCol4Layout = new E2_ColorHelpBackground();
		} 

		MyE2_Grid  oGridInnen = new MyE2_Grid(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		//jetzt wird fuer jede message eine eigene gridInnen gefuellt
		if (msg instanceof IF_Message_WithButtons) {

			IF_Message_WithButtons msgWB = (IF_Message_WithButtons)msg;
			oCol4Layout=msgWB.get_Color_4_MessageBackground(msg);
			Insets insets4Layout = msgWB.get_Insets_4_MessageInMessageGrid(msg);
			oGridInnen.add( msgWB.get_ComponentWithMessageAndButtons(msg) ,this.get_Layout(1,insets4Layout,oCol4Layout));
			
		} else {
			oGridInnen.add(new MyE2_Label(msg.get_cMessage(), true), this.get_Layout(3,E2_INSETS.I(2,2,2,2),oCol4Layout));
		}
		
		this.add(oGridInnen, MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(1,1,1,1), oCol4Layout, 1, 1));
		
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
