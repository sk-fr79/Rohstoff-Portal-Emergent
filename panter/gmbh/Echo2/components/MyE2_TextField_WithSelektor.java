package panter.gmbh.Echo2.components;


import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Row;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.indep.S;
import panter.gmbh.indep.StringSeparator;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/*
 * textfeld mit einem popup-selektor, der varianten darstellt
 */
public class MyE2_TextField_WithSelektor extends MyE2_Row implements 	MyE2IF__Component, 
																		E2_IF_Copy,
																		MyE2IF__CanGetStampInfo
{

//	public static XX_ActionAgent 	ownAction=null;
	public static MutableStyle		ownRowStyle = null;
	static
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( Row.PROPERTY_INSETS, new Insets(0)); 
		oStyle.setProperty( Row.PROPERTY_FONT, new E2_FontPlain());
		ownRowStyle = oStyle;

	};
	
	
	public static E2_ResourceIcon ICON_FOR_POPUP = E2_ResourceIcon.get_RI("textbaustein.png");

	
	
	private MyE2_TextField			oTextField        = new MyE2_TextField();
	private String[][]				cVarianten		  = null; 
	private String 					cInfoWhenEmpty		= null;
	private MyE2_PopUpMenue			oPopUp			  = null;
	
	private Component				oTopInfoComponent = null;
	
	private boolean                 bSetTextAsTooltip = false;
	
	
	public boolean get_bSetTextAsTooltip()
	{
		return bSetTextAsTooltip;
	}




	public void set_bSetTextAsTooltip(boolean bSetTextAsTooltip)
	{
		this.bSetTextAsTooltip = bSetTextAsTooltip;
	}




	/**
	 * @param Varianten
	 * @param cInfoWhenEmpty (Falls mehrzeilig, durch | getrennte Teilstrings)
	 * @throws myException
	 */
	public MyE2_TextField_WithSelektor(	String[][] 		Varianten, 
										String 			cInfo_When_Empty) throws myException
	{
		super(ownRowStyle);
		this._prepare(Varianten,cInfo_When_Empty,null);
	}  

	
	
	
	/**
	 * @param cSQLQuery  Abfrage enthaehlt 	 beschreibung,wert
	 * @throws myException
	 */
	public MyE2_TextField_WithSelektor(String cSQLQuery, int iWidth) throws myException
	{
		super(ownRowStyle);
		this._prepare(this.get_Varianten_from_Query(cSQLQuery),null,null);
		this.oTextField.set_iWidthPixel(iWidth);
	}  

	
	/**
	 * @param Varianten
	 * @param cInfoWhenEmpty (Falls mehrzeilig, durch | getrennte Teilstrings)
	 * @throws myException
	 */
	public MyE2_TextField_WithSelektor(	String[][] 		Varianten, 
										String 			cInfo_When_Empty,
										int iWidth) 	throws myException
	{
		super(ownRowStyle);
		this._prepare(Varianten,cInfo_When_Empty,null);
		this.oTextField.set_iWidthPixel(iWidth);
	}  

	
	
	
	/**
	 * @param cSQLQuery  Abfrage enthaehlt 	 beschreibung,wert
	 * @throws myException
	 */
	public MyE2_TextField_WithSelektor(String 			cSQLQuery) throws myException
	{
		super(ownRowStyle);
		this._prepare(this.get_Varianten_from_Query(cSQLQuery),null,null);
	}  

	

	public MyE2_TextField_WithSelektor() throws myException
	{
		super(ownRowStyle);
		this.oPopUp=new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("textbaustein.png"),E2_ResourceIcon.get_RI("leer.png"), false);
		this.__prepare();
	}  


	
	
	public void set_TopZusatzInfo(Component TopZusatzInfo) throws myException
	{
		this.set_Varianten(this.cVarianten, this.cInfoWhenEmpty, TopZusatzInfo);
	}

	
	private String[][] get_Varianten_from_Query(String cSQLQuery)
	{
		String[][] cRueck=bibDB.EinzelAbfrageInArray(cSQLQuery,"");
		
		String[][] 	Varianten = null;
		
		if (cRueck != null && cRueck.length>0)
		{
			if (cRueck[0].length==1)
			{
				Varianten = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
					Varianten[i][0]=Varianten[i][1]=cRueck[i][0];

			}
			else
			{
				Varianten = new String[cRueck.length][2];
				for (int i=0;i<cRueck.length;i++)
				{
					Varianten[i][0]=cRueck[i][0];
					Varianten[i][1]=cRueck[i][1];
				}
			}
		}
		
		return Varianten;
	}
	
	
	
	
	private void _prepare(String[][] Varianten, String cInfoWhenempty, Component TopInfoComponent) throws myException
	{
		this.oPopUp=new MyE2_PopUpMenue(E2_ResourceIcon.get_RI("textbaustein.png"),E2_ResourceIcon.get_RI("leer.png"), false);

		this.set_Varianten(Varianten,cInfoWhenempty,null);

		this.__prepare();
	}

	
	
	private void __prepare()
	{
		this.add(this.oTextField,new Insets(0,0,2,0));
		this.add(this.oPopUp,new Insets(2,0,2,0));
		this.oTextField.setHorizontalScroll(new Extent(1));
		this.oTextField.setVerticalScroll(new Extent(1));

	}
	
	
	public void set_Varianten(String[][] Varianten, String cInfoWhenempty, Component TopInfoComponent) throws myException
	{
		this.cInfoWhenEmpty=cInfoWhenempty;
		
		if (S.isEmpty(this.cInfoWhenEmpty))
		{
			this.cInfoWhenEmpty = new MyE2_String("*** KEINE EINTRÄGE ***").CTrans();
		}

		
		this.cVarianten = Varianten;
		this.oTopInfoComponent = TopInfoComponent;
		
		if (this.cVarianten !=null && this.cVarianten.length>0)
			if (this.cVarianten[0].length!=2)
				throw new myException("MyE2_DB_TextField_WithSelektor: set_Varianten: cVarianten musst have an array nx2");
			
		this.oPopUp.removeAllButtons();
		if (this.oTopInfoComponent != null)
			this.oPopUp.get_oColInnen().add(this.oTopInfoComponent);
		
		if (this.cVarianten != null && this.cVarianten.length>0)
		{
			for (int i=0;i<this.cVarianten.length;i++)
			{
				MyE2_Button oButHelp = new MyE2_Button(new MyE2_String(this.cVarianten[i][0],false));
				oButHelp.EXT().set_C_MERKMAL(this.cVarianten[i][1]);
				oButHelp.EXT().set_O_PLACE_FOR_EVERYTHING(this.oTextField);
				oButHelp.add_oActionAgent(new ownActionPopupButtons());
				this.oPopUp.addButton(oButHelp,true);
			}
		}
		else
		{
			if (bibALL.isEmpty(this.cInfoWhenEmpty))
			{
				this.oPopUp.get_oColInnen().add(new MyE2_Label(new MyE2_String("Text-Auswahl ist undefiniert !")));
			}
			else
			{
				StringSeparator oSep = new StringSeparator(this.cInfoWhenEmpty,"|");
				for (int i=0;i<oSep.size();i++)
				{
					this.oPopUp.get_oColInnen().add(new MyE2_Label(new MyE2_String(oSep.get_(i),false)),new Insets(2,2,2,2));
				}
			}
		}
		
	}
	
	
	public String[][] get_Varianten()
	{
		return this.cVarianten;
	}
	
	
 
	public Object get_Copy(Object ob) throws myExceptionCopy
	{
		MyE2_TextField_WithSelektor oRueck = null;
		
		try
		{
			oRueck =  new MyE2_TextField_WithSelektor(null,null);
			oRueck.set_Varianten(this.cVarianten,this.cInfoWhenEmpty,this.oTopInfoComponent);
		}
		catch (myException ex)
		{
			throw new myExceptionCopy("MyE2_DB_TextField_WithSelektor:get_Copy:copy-error!"+ex.get_ErrorMessage().get_cMessage().COrig());
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
		this.oTextField.setEnabled(bVoraussetzung);
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(bVoraussetzung,true,false));
		this.oPopUp.set_bEnabled_For_Edit(bVoraussetzung);
	}


	


	public void show_InputStatus(boolean bInputIsOK)
	{
		this.oTextField.setStyle(this.oTextField.EXT().get_STYLE_FACTORY().get_Style(this.oTextField.isEnabled(),true,!bInputIsOK));
	}


	public MyE2_TextField get_oTextField()
	{
		return this.oTextField;
	}




	public MyE2_PopUpMenue get_oPopUp()
	{
		return oPopUp;
	}



	private class ownActionPopupButtons extends XX_ActionAgent
	{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo)
		{
			/*
			 * popup-button hat den Text in  get_C_MERKMAL und das textobject in get_O_PLACE_FOR_EVERYTHING
			 */
			MyE2_Button 		oBUT = ((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource());
			MyE2_TextField 		oTextArea = (MyE2_TextField)((MyE2_Button)bibE2.get_LAST_ACTIONEVENT().getSource()).EXT().get_O_PLACE_FOR_EVERYTHING();
			
			oTextArea.setText(oBUT.EXT().get_C_MERKMAL());
			
			if (MyE2_TextField_WithSelektor.this.bSetTextAsTooltip)
			{
				oTextArea.setToolTipText(oBUT.EXT().get_C_MERKMAL());
			}
			
		}
		
	}



	@Override
	public String get_STAMP_INFO() throws myException {
		return S.NN(this.oTextField.getText());
	}

	
	
	
}
