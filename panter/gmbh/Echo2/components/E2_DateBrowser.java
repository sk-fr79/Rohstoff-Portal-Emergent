package panter.gmbh.Echo2.components;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class E2_DateBrowser extends MyE2_Row implements MyE2IF__Component
{
	public static MutableStyle StyleCol = null;
	static
	{
		MutableStyle ost = new MutableStyle();
		ost.setProperty( Column.PROPERTY_BORDER, new Border(1, new E2_ColorBase(-20), Border.STYLE_SOLID));
		ost.setProperty( Column.PROPERTY_INSETS, new Insets(1)); 
		StyleCol = ost;
	}
	
	private MyE2_Button 				oButtonLeft = new MyE2_Button(E2_ResourceIcon.get_RI("smallarrowleft.png"));
	private MyE2_Button 				oButtonRight = new MyE2_Button(E2_ResourceIcon.get_RI("smallarrowright.png"));

	private MyE2_TextField  			oDatumsFeld = new MyE2_TextField();
	private MyE2_Label					oLabelDay = new MyE2_Label("");
	
	public E2_DateBrowser()
	{
		super(E2_DateBrowser.StyleCol);
		this.add(this.oButtonLeft,new ownLayout(new Insets(0,0,0,0),null));
		this.add(oLabelDay,new ownLayout(new Insets(0,0,3,0),new Extent(25)));
		this.add(this.oDatumsFeld,new ownLayout(new Insets(0,0,0,0),null));
		this.add(this.oButtonRight,new ownLayout(new Insets(0,0,0,0),null));
		
		this.oButtonLeft.setStyle(MyE2_Button.StyleImageButtonNoBorders());
		this.oButtonRight.setStyle(MyE2_Button.StyleImageButtonNoBorders());
		
		this.oDatumsFeld.set_iWidthPixel(100);
		this.oDatumsFeld.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		
		this.oButtonLeft.add_oActionAgent(new ownActionAgent("L"));
		this.oButtonRight.add_oActionAgent(new ownActionAgent("R"));
		
	}

	

	
	
	public MyE2_TextField get_oDatumsFeld()
	{
		return oDatumsFeld;
	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		this.oButtonLeft.set_bEnabled_For_Edit(bEnabled);
		this.oButtonRight.set_bEnabled_For_Edit(bEnabled);
		this.oDatumsFeld.set_bEnabled_For_Edit(bEnabled);
	}

	
	public void DO_EvaluateActualText()
	{
		
		
		TestingDate oDate = new TestingDate(this.oDatumsFeld.getText());
		
		if (!oDate.testing())
			return ;
		
		GregorianCalendar oCal = oDate.get_Calendar();
		
		// zuerst die tages-buchstaben finden und anzeigen
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		String cHelp = df.format(oCal.getTime()).substring(0,2);
		this.oLabelDay.set_Text(new MyE2_String(cHelp));
		
		// dann (falls noetig) das textfeld in form bringen
		SimpleDateFormat oSDF = new  SimpleDateFormat("dd.MM.yyyy");
		this.oDatumsFeld.setText(oSDF.format(oCal.getTime()));
	}
	

	public myDateHelper get_oDateHelper() throws myException
	{
		myDateHelper  oDate = null;
		
		TestingDate oTD = new TestingDate(S.NN(this.oDatumsFeld.getText()));
		
		if (oTD.testing())
		{
			
			oDate = new myDateHelper(oTD.get_Calendar());
		}
		
		return oDate;
	}
	
	

	public String DO_EvaluateActualTextAndGiveBackFormatedText(String cFormat)
	{
		
		
		TestingDate oDate = new TestingDate(this.oDatumsFeld.getText());
		
		if (!oDate.testing())
			return null;
		
		GregorianCalendar oCal = oDate.get_Calendar();
		
		// zuerst die tages-buchstaben finden und anzeigen
		DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
		String cHelp = df.format(oCal.getTime()).substring(0,2);
		this.oLabelDay.set_Text(new MyE2_String(cHelp));
		
		// dann (falls noetig) das textfeld in form bringen
		SimpleDateFormat oSDF = new  SimpleDateFormat(cFormat);
		return oSDF.format(oCal.getTime());
	}
	

	
	
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		private String cTYPE = "";
		
		public ownActionAgent(String cType)
		{
			super();
			this.cTYPE=cType;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			MyE2_TextField oField = E2_DateBrowser.this.oDatumsFeld;
			
			String cInhalt = oField.getText();
			if (cInhalt==null || cInhalt.trim().equals(""))
			{
				cInhalt = bibALL.get_cDateNOW();
				oField.setText(cInhalt);
				DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
				String cHelp = df.format(new GregorianCalendar().getTime()).substring(0,2);
				E2_DateBrowser.this.oLabelDay.set_Text(new MyE2_String(cHelp));
				return;
			}
			
			TestingDate oDate = new TestingDate(cInhalt);
			if (oDate.testing())
			{
				int iAdd = 1;
				if (this.cTYPE.equals("L")) iAdd=-1;
					
				GregorianCalendar oCal = oDate.get_Calendar();
				oCal.add(Calendar.DAY_OF_MONTH,iAdd);
				SimpleDateFormat oSDF = new  SimpleDateFormat("dd.MM.yyyy");
				cInhalt = oSDF.format(oCal.getTime());
				oField.setText(cInhalt);
				DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
				String cHelp = df.format(oCal.getTime()).substring(0,2);
				E2_DateBrowser.this.oLabelDay.set_Text(new MyE2_String(cHelp));
				return;

				
			}
			else
			{
				cInhalt = bibALL.get_cDateNOW();
				oField.setText(cInhalt);
				DateFormat df = DateFormat.getDateInstance(DateFormat.FULL);
				String cHelp = df.format(new GregorianCalendar().getTime()).substring(0,2);
				E2_DateBrowser.this.oLabelDay.set_Text(new MyE2_String(cHelp));
				return;
			}
			
			
			
		}
		
	}

	
	
	private class ownLayout extends RowLayoutData
	{
		public ownLayout(Insets oInsets, Extent oWidth)
		{
			super();
			this.setInsets(oInsets);
			this.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
			if (oWidth !=null) this.setWidth(oWidth);
		}
		
	}



	public MyE2_Button get_oButtonLeft()
	{
		return oButtonLeft;
	}





	public MyE2_Button get_oButtonRight()
	{
		return oButtonRight;
	}




	
}
