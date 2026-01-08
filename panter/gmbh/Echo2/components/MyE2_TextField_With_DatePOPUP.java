package panter.gmbh.Echo2.components;


import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.extras.app.CalendarSelect;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.maggie.TestingDate;

/*
 * textfeld mit einem popup-selektor, der varianten darstellt
 */
public class MyE2_TextField_With_DatePOPUP extends MyE2_Row implements MyE2IF__Component, E2_IF_Copy
{

	private MyE2_TextField			oTextField = 	new MyE2_TextField();
	private	int 					iWidthPixel = 	92;

	private MyE2_Button				oButtonCalendar	  = null;
	private CalendarSelect          oCalendarSelect = null;
	
	private ownContainerDatePopup   oContainer4PopUP = null;


	
	public MyE2_TextField_With_DatePOPUP() throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		this.__setBasic();
	}  

	
	public MyE2_TextField_With_DatePOPUP(String cText,int iwidthPixel) throws myException
	{
		super(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				
		this.iWidthPixel = 		iwidthPixel;
		this.oTextField.setText(cText);
		
		this.__setBasic();
		
	}  

	
	public void __setBasic()
	{
		this.oTextField.setWidth(new Extent(this.iWidthPixel));
		this.oTextField.set_iMaxInputSize(10);
		this.oTextField.setFont(new E2_FontPlain());
		this.oTextField.EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		
		this.oButtonCalendar=new MyE2_Button(E2_ResourceIcon.get_RI("calendar.png"),true);
		this.oButtonCalendar.add_oActionAgent(new XX_ActionAgent()
		{
			@Override
			public void executeAgentCode(ExecINFO execInfo) throws myException
			{
				MyE2_TextField_With_DatePOPUP.this.oContainer4PopUP = new ownContainerDatePopup();
			}
		});

		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oButtonCalendar,new Insets(2,0,2,0));
		
		this.oTextField.setHorizontalScroll(new Extent(1));
		this.oTextField.setVerticalScroll(new Extent(1));

	}

	
 
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_TextField_With_DatePOPUP oRueck = null;
		
		try
		{
			oRueck =  new MyE2_TextField_With_DatePOPUP();
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_TextField_With_DatePOPUP:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
		}

		oRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRueck));
		
		oRueck.setStyle(this.getStyle());
		oRueck.setFont(this.getFont());
		
		oRueck.get_oTextField().set_iMaxInputSize(this.oTextField.get_iMaxInputSize());
		oRueck.get_oTextField().set_iWidthPixel(this.oTextField.get_iWidthPixel());
		oRueck.get_oTextField().setText(this.oTextField.getText());
		oRueck.get_oTextField().setWidth(this.oTextField.getWidth());
		oRueck.get_oTextField().setAlignment(this.oTextField.getAlignment());
		
		return oRueck;
	}
	




	
	/*
	 * ueberschreiben, um ein enabled bei einem nicht editierbaren feld
	 * zu unterbinden
	 */
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
		boolean bVoraussetzung = enabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled() ;
		this.oTextField.set_bEnabled_For_Edit(bVoraussetzung);
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,true,false));
		this.oButtonCalendar.set_bEnabled_For_Edit(bVoraussetzung);
	}


	


	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(this.oTextField.isEnabled(),true,!bInputIsOK));
	}


	public MyE2_TextField get_oTextField()
	{
		return this.oTextField;
	}

	
	
	
	private class ownContainerDatePopup extends E2_BasicModuleContainer
	{

		public ownContainerDatePopup() throws myException 
		{
			super();
			
			//hier wird der calendar-selector eingebaut
			MyE2_TextField_With_DatePOPUP oThis = MyE2_TextField_With_DatePOPUP.this;
			
			//E2_ComponentMAP oMap = MyE2_TextField_With_DatePOPUP.this.EXT().get_oComponentMAP();
			
			MyE2_Column oColBasic = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());

			String cDatumInText = S.NN(oThis.oTextField.getText());
			if (S.isEmpty(cDatumInText))
			{
				cDatumInText = bibALL.get_cDateNOW();
			}
			TestingDate oTD = new TestingDate(cDatumInText);
			if (!oTD.testing())
			{
				cDatumInText =  bibALL.get_cDateNOW();
			}
			
			//wenn jetzt immer noch falsches datum, dann error
			oTD = new TestingDate(cDatumInText);
			if (!oTD.testing())
			{
				throw new myException(this,"Error finding system-date");
			}
			
			oThis.oCalendarSelect = new CalendarSelect(oTD.get_Calendar().getTime());

			oColBasic.add(oThis.oCalendarSelect, E2_INSETS.I_5_5_5_5);
			oColBasic.add(new E2_ComponentGroupHorizontal(0,new btSaveDate(),E2_INSETS.I_0_0_0_0), E2_INSETS.I_5_5_5_5);
			
			this.add(oColBasic, E2_INSETS.I_10_10_10_10);
			
			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(250), new Extent(300), new MyE2_String("Datum auswählen"));
			
		}
		
		private class btSaveDate extends MyE2_Button
		{

			public btSaveDate()
			{
				super(new MyE2_String("OK"));
				
				this.add_oActionAgent(new XX_ActionAgent()
				{
					@Override
					public void executeAgentCode(ExecINFO execInfo)	throws myException
					{
						String cDatumFormated = myDateHelper.FormatDateNormal(MyE2_TextField_With_DatePOPUP.this.oCalendarSelect.getDate());
						MyE2_TextField_With_DatePOPUP.this.oTextField.setText(cDatumFormated);
						MyE2_TextField_With_DatePOPUP.this.oContainer4PopUP.CLOSE_AND_DESTROY_POPUPWINDOW(true);
					}
				});
			}
		}
		
	}


	
	
	
	
	
	
}
