/*
 * Created on 16.10.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.RB.COMP.BETA;

import java.util.Vector;

import echopointng.PopUp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.event.ActionEvent;
import nextapp.echo2.app.event.ActionListener;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.staticStyles.Style_Column_Normal;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/**
 * @author martin
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class E2_PopUpMenue extends PopUp  implements MyE2IF__Component, E2_IF_Copy
{
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
    private Vector<E2_Button>  	vMenueButtons = new Vector<E2_Button>();

 
    // grid als Rahmen
    private MyE2_Column 		oColAussen = 		new MyE2_Column(new Style_Column_Normal(1,new Insets(2)));
    private MyE2_Column 		oColInnen = 		new MyE2_Column(new Style_Column_Normal(1,new Insets(1)));
    private int 				iButtonCounter = 	0;
    private E2_ResourceIcon 	oIconAktiv= 		E2_ResourceIcon.get_RI("popup.png");
    private E2_ResourceIcon 	oIconInactiv = 		E2_ResourceIcon.get_RI("popup__.png");

    private E2_PopUpMenue.ownAgent oAgentCollapse = null;
    
    /*
     * falls die zahl von DropDown-buttons zu gross wird und 
     * oContExWidth / oContExHeight != null sind wird ein container-ex in den 
     * popup geschoben
     */
    private MyE2_ContainerEx	oContEx	= new MyE2_ContainerEx();
    private Extent				oContExWidth = new Extent(200);
    private Extent				oContExHeight = new Extent(200);
    
    
    private boolean 			bUseContainerEx = false;
    
    /**
     * @param oiconAktiv
     * @param oiconinaktiv
     * @param useContainerEx
     * Beide null heist: Standardbutton
     */
    public E2_PopUpMenue(E2_ResourceIcon oiconAktiv, E2_ResourceIcon oiconinaktiv, boolean useContainerEx)
    {
        if (oiconAktiv != null) this.oIconAktiv = oiconAktiv;
        if (oiconinaktiv != null) this.oIconInactiv=oiconinaktiv;
        
        
        this.setToggleIcon(this.oIconAktiv);
        this.setTogglePressedIcon(this.oIconAktiv);
        this.setToggleRolloverIcon(this.oIconAktiv);
        
        this.setPopUpLeftOffset(-6);
        this.setPopUpTopOffset(6);
        
        this.oColAussen.setStyle(E2_PopUpMenue.DEFAULT_STYLE_OUTER_COLUMN());
        this.oColInnen.setStyle(E2_PopUpMenue.DEFAULT_STYLE_INNER_COLUMN());
        
        this.setStyle(E2_PopUpMenue.DEFAULT_STYLE_POPUP());

        this.oAgentCollapse =	new E2_PopUpMenue.ownAgent(this);
        
        this.oContEx.setBackground(new E2_ColorBase(20));
        this.oContEx.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
        
        this.oContEx.setWidth(this.oContExWidth);
        this.oContEx.setHeight(this.oContExHeight);

 
        this.set_ContainerExActive(useContainerEx);
        
        this.setPopUpOnRollover(false);
        this.setPopUpAlwaysOnTop(true);
        
    }

    
 
    /**
     * 
     * @param oiconAktiv
     * @param oiconinaktiv
     * @param useContainerEx
     * @param ContainerExWidth
     * @param ContainerExHeight
     * @param iLeftOffest
     * @param iTopOffest
     */
    public E2_PopUpMenue(		E2_ResourceIcon oiconAktiv, 
    							E2_ResourceIcon oiconinaktiv, 
    							boolean useContainerEx, 
    							Extent ContainerExWidth, 
    							Extent ContainerExHeight, 
    							int iLeftOffest, 
    							int iTopOffest)
    {
        if (oiconAktiv != null) this.oIconAktiv = oiconAktiv;
        if (oiconinaktiv != null) this.oIconInactiv=oiconinaktiv;
        
        
        this.setToggleIcon(this.oIconAktiv);
        this.setTogglePressedIcon(this.oIconAktiv);
        this.setToggleRolloverIcon(this.oIconAktiv);
        
        this.setPopUpLeftOffset(iLeftOffest);
        this.setPopUpTopOffset(iTopOffest);
        
        this.oColAussen.setStyle(E2_PopUpMenue.DEFAULT_STYLE_OUTER_COLUMN());
        this.oColInnen.setStyle(E2_PopUpMenue.DEFAULT_STYLE_INNER_COLUMN());
        
        this.oContExHeight=ContainerExHeight;
        this.oContExWidth = ContainerExWidth;
        
        
        this.setStyle(E2_PopUpMenue.DEFAULT_STYLE_POPUP());

        this.oAgentCollapse =	new E2_PopUpMenue.ownAgent(this);
        
        this.oContEx.setBackground(new E2_ColorBase(20));
        this.oContEx.setBorder(new Border(1, Color.BLACK, Border.STYLE_SOLID));
        
        this.oContEx.setWidth(this.oContExWidth);
        this.oContEx.setHeight(this.oContExHeight);

 
        this.set_ContainerExActive(useContainerEx);
        
        this.setPopUpOnRollover(false);
        this.setPopUpAlwaysOnTop(true);
        
    }

    
     
    
    
    public void set_ContainerExActive(boolean useContainerEx)
    {
        this.bUseContainerEx = useContainerEx;
        
        this.removeAll();
        this.oColAussen.removeAll();
        this.oContEx.removeAll();
        
        if (! this.bUseContainerEx)
        {
        	this.oColAussen.add(this.oColInnen);
            this.setPopUp(this.oColAussen);
        }
        else
        {
        	this.oContEx.add(this.oColInnen);
            this.setPopUp(this.oContEx);
        }
    	
    }
    
    
    
    public void addButton(E2_Button oNewButton, boolean bSetDefaultStyle)
    {
        this.vMenueButtons.add(oNewButton);
        this.oColInnen.add(oNewButton);
        oNewButton.add_oActionAgent(this.oAgentCollapse);
        if (bSetDefaultStyle)
        	oNewButton.setStyle(E2_PopUpMenue.DEFAULT_STYLE_BUTTON());
        this.iButtonCounter++;
    }

    

    
    /**
     * Baut eine impliziten button aus dem uebergebenen string
     * @param cButtonText
     */
    public void addTextButton(String cButtonText, String cEVENTKENNUNG)
    {
    	E2_Button oButtonNew = new E2_Button()._t(cButtonText);
    	oButtonNew.setStyle(E2_PopUpMenue.DEFAULT_STYLE_BUTTON());
    	if (bibALL.isEmpty(cEVENTKENNUNG))
    		oButtonNew.EXT().set_C_EVENT_KENNUNG(cEVENTKENNUNG);

    	oButtonNew.add_oActionAgent(this.oAgentCollapse);
        this.addButton(oButtonNew, false);
    }
    

	// damit kann ein button an einer stelle an/ ausgeschaltet werden
	public void set_ButtonEnabled(int iButtonNr, boolean bEnabled)
	{
		((E2_Button)this.vMenueButtons.get(iButtonNr)).setEnabled(bEnabled);
	}


    public void removeAllButtons()
    {
        this.vMenueButtons.removeAllElements();
        this.oColInnen.removeAll();
    }

    
    public E2_Button get_Button(int iPos)
    {
    	E2_Button oRueck = null;

        if (iPos < this.vMenueButtons.size())
            oRueck = (E2_Button) this.vMenueButtons.get(iPos);

        return oRueck;
    }

    /*
     * @ermittelt bei vorhandenem Actionevent, welcher button es war, ansonsten
     * wird -1 zurückgegeben
     */
    public int get_ID_Click(ActionEvent e)
    {
        int iRueck = -1;

        for (int i = 0; i < this.vMenueButtons.size(); i++)
        {
            if (e.getSource() == (E2_Button) this.vMenueButtons.get(i))
            {
                iRueck = i;
                break;
            }
        }

        return iRueck;
    }

    /*
     * @ermittelt bei vorhandenem Actionevent, welcher button es war, ansonsten
     * wird -1 zurückgegeben
     */
    public E2_Button get_buttonClick(ActionEvent e)
    {
    	E2_Button buttonRueck = null;

        for (int i = 0; i < this.vMenueButtons.size(); i++)
        {
            if (e.getSource() == (E2_Button) this.vMenueButtons.get(i))
            {
                buttonRueck = (E2_Button) this.vMenueButtons.get(i);

                break;
            }
        }

        return buttonRueck;
    }

    public int get_ButtonCount()
    {
        return this.vMenueButtons.size();
    }



    public void addActionListenerToAllButtons(ActionListener myListener)
    {
    	E2_Button buttonHelp = null;
        if (myListener != null)
        {
	        for (int i = 0; i < this.vMenueButtons.size(); i++)
	        {
	            buttonHelp = (E2_Button) this.vMenueButtons.get(i);
	            buttonHelp.removeActionListener(myListener);
	            buttonHelp.addActionListener(myListener);
	        }
        }
    }
    

	/*
	 * pruefen, ob ein event einer der buttons im menue ist, und rückgabe der cEVENT_KENNUNG_STRING
	 */
	public String find_EventKennung(ActionEvent e)
	{
	    String cRueck = "";
	    
	    if (e.getSource() instanceof MyE2_Button)
	    {
		    for (int i=0;i<this.vMenueButtons.size();i++)
		    {
		        if (e.getSource()==(E2_Button)this.vMenueButtons.get(i))
		        {
		            cRueck = ((E2_Button)this.vMenueButtons.get(i)).EXT().get_C_EVENT_KENNUNG();
		            break;
		        }
		    }
	    }
	    
	    return cRueck;
	}
	
	
	

	
	/**
	 * gibt einen button zurueck mit einer bestimmten event-kennung 
	 */
	public E2_Button find_Button(String cEVENT_KENNUNG)
	{
	    for (int i=0;i<this.vMenueButtons.size();i++)
	    {
	        if (((E2_Button)this.vMenueButtons.get(i)).EXT().get_C_EVENT_KENNUNG().equals(cEVENT_KENNUNG))
	        {
	            return (E2_Button)this.vMenueButtons.get(i);
	        }
	    }
	    return null;
	}
	

//	/**
//	 * @param oWidth
//	 * @param oHeight
//	 * @param iNumberToStartContEx
//	 */
//	public void define_ContainerEX(Extent oWidth, Extent oHeight, int iNumberToStartContEx,Integer iLeftOffset)
//	{
//		this.oContExWidth = oWidth;
//		this.oContExHeight = oHeight;
//		this.iNumberButtonsToStartContainerEx= iNumberToStartContEx;
//		if (iLeftOffset != null)
//			this.setPopUpLeftOffset(iLeftOffset.intValue());
//		
//		this.check_ContainerX();
//	}
//	
	

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}


	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException
	{
		boolean bEnabled = bbEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		if (bEnabled)
			this.setToggleIcon(this.oIconAktiv);
		else
			this.setToggleIcon(this.oIconInactiv);
		
		this.setEnabled(bEnabled);
		this.oColAussen.setEnabled(bEnabled);
		this.oColInnen.setEnabled(bEnabled);
		for (int i=0;i<this.vMenueButtons.size();i++)
			this.set_ButtonEnabled(i,bEnabled);
	}



	public E2_ResourceIcon get_oIconAktiv()						{		return oIconAktiv;	}
	
	public void set_oIconAktiv(E2_ResourceIcon iconAktiv)		
	{
		if (this.getToggleIcon()!=this.oIconInactiv)
		{
			this.setToggleIcon(iconAktiv);
		    this.setTogglePressedIcon(iconAktiv);
		    this.setToggleRolloverIcon(iconAktiv);
		}
		this.oIconAktiv = iconAktiv;

	}
	
	public E2_ResourceIcon get_oIconInactiv()					{		return oIconInactiv;	}
	
	public void set_oIconInactiv(E2_ResourceIcon iconInactiv)	
	{		
		if (this.getToggleIcon()==this.oIconInactiv)
		{
			this.setToggleIcon(iconInactiv);
		}
		this.oIconInactiv = iconInactiv;
		oIconInactiv = iconInactiv;	
	}
	
	public Vector<E2_Button> get_vMenueButtons()							{		return vMenueButtons;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		E2_PopUpMenue oPopRueck = new E2_PopUpMenue(this.oIconAktiv,this.oIconInactiv, this.bUseContainerEx);
		for (int i=0;i<this.vMenueButtons.size();i++)
		{
			oPopRueck.addButton((E2_Button)((E2_Button)this.vMenueButtons.get(i)).get_Copy(null), false);
		}
		
		oPopRueck.get_oContainerEx().setWidth(this.get_oContainerEx().getWidth());
		oPopRueck.get_oContainerEx().setHeight(this.get_oContainerEx().getHeight());
		
		return oPopRueck;
	}

	
	public static class ownAgent extends XX_ActionAgent
	{
		private E2_PopUpMenue oPopUp = null;
		public ownAgent(E2_PopUpMenue popup)
		{
			super();
			oPopUp = popup;
		}

		public void executeAgentCode(ExecINFO oExecInfo)
		{
			this.oPopUp.setExpanded(false);
		}
		
	}
	
	
	
	
	/**
	 * This DEFAULT_STYLE is applied to the popup to give it a series of borders and background colors
	 */
	public static Style DEFAULT_STYLE_POPUP()
	{
		MutableStyle style = new MutableStyle();
		
		style.setProperty(PROPERTY_POPUP_INSETS,new Insets(0));
		style.setProperty(PROPERTY_POPUP_OUTSETS,new Insets(0));
		style.setProperty(PopUp.PROPERTY_POPUP_ON_ROLLOVER, new Boolean(false));

		style.setProperty(PROPERTY_INSETS,new Insets(0));
		
		//2011-11-18: keine farbgebung, damit transparenz wirken kann
//		style.setProperty(PROPERTY_BACKGROUND,new E2_ColorBase());
		style.setProperty(PROPERTY_BORDER,new Border(0, new E2_ColorBase(), Border.STYLE_RIDGE));
		style.setProperty(PROPERTY_ROLLOVER_BORDER,new Border(0, new E2_ColorBase(), Border.STYLE_SOLID));

		return style;
	}	

	
	public static  Style DEFAULT_STYLE_OUTER_COLUMN()
	{
		MutableStyle style = new MutableStyle();
		
		style.setProperty(Column.PROPERTY_BORDER,new Border(1, Color.BLACK, Border.STYLE_SOLID));
		style.setProperty( Column.PROPERTY_BACKGROUND, new E2_ColorBase(20)); 
		style.setProperty( Column.PROPERTY_INSETS, new Insets(0)); 
		return style;
	}	

	
	public static  Style DEFAULT_STYLE_INNER_COLUMN()
	{
		MutableStyle style = new MutableStyle();
	
		style.setProperty(Column.PROPERTY_BORDER,new Border(0, Color.BLACK, Border.STYLE_NONE));
		style.setProperty( Column.PROPERTY_BACKGROUND, new E2_ColorBase(20)); 
		style.setProperty( Column.PROPERTY_INSETS, new Insets(2)); 
		
		return style;
	}	


	public static  Style DEFAULT_STYLE_BUTTON()
	{
		MutableStyle style = new MutableStyle();
	
		style.setProperty( AbstractButton.PROPERTY_BACKGROUND, new E2_ColorBase(20)); 
		style.setProperty( AbstractButton.PROPERTY_BORDER, new Border(0, new E2_ColorBase(-20), Border.STYLE_NONE)); 
		style.setProperty( AbstractButton.PROPERTY_ROLLOVER_ENABLED, Boolean.TRUE); 
		style.setProperty( AbstractButton.PROPERTY_ROLLOVER_BACKGROUND, new E2_ColorGray(220)); 
		style.setProperty( AbstractButton.PROPERTY_INSETS, new Insets(2,0,2,0)); 
		style.setProperty( AbstractButton.PROPERTY_FONT, new E2_FontPlain());
		style.setProperty( AbstractButton.PROPERTY_LINE_WRAP, new Boolean(false));
		
		return style;
	}
	
	public MyE2_Column get_oColInnen()
	{
		return oColInnen;
	}




	public MyE2_ContainerEx get_oContainerEx()
	{
		return oContEx;
	}


	public void add_ActionAgentToPopupButtons(XX_ActionAgent oAgent, boolean bInFront)
	{
		for (int i=0;i<this.vMenueButtons.size();i++)
		{
			this.vMenueButtons.get(i).add_oActionAgent(oAgent,bInFront);
		}
	}
	
	
	
	
	//2014-09-08: methoden um das objekt in ein grid einzuwickeln
	private Vector<MyE2IF__Component> 	vADDONS_IN_WRAP = new Vector<MyE2IF__Component>();
	private boolean 					b_OnComponentInFront = true;
	private int   						i_SpaceInPixel = 2;
	@Override
	public MyE2IF__Component ME() throws myException {
		MyE2_Grid oGridRueck = new MyE2_Grid(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		oGridRueck.setSize(1+this.vADDONS_IN_WRAP.size());
		//die alte layoutdata sichern und an das aussengrid uebergeben
		LayoutData oLayoutThis = this.getLayoutData();
		GridLayoutData 	oLayoutInnen1 = new GridLayoutData();
		GridLayoutData 	oLayoutInnen2 = new GridLayoutData();
		oLayoutInnen1.setInsets(E2_INSETS.I(0,0,this.i_SpaceInPixel,0));
		oLayoutInnen1.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		oLayoutInnen2.setInsets(E2_INSETS.I(0,0,0,0));
		oLayoutInnen2.setAlignment(new Alignment(Alignment.LEFT, Alignment.TOP));
		if (this.b_OnComponentInFront) {
			this.setLayoutData(oLayoutInnen1);
			oGridRueck.add_raw(this);
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			((Component)this.vADDONS_IN_WRAP.get(this.vADDONS_IN_WRAP.size()-1)).setLayoutData(oLayoutInnen2);
		} else {
			for (MyE2IF__Component oComp: this.vADDONS_IN_WRAP) {
				((Component)oComp).setLayoutData(oLayoutInnen1);
				oGridRueck.add_raw((Component)oComp);
			}
			this.setLayoutData(oLayoutInnen2);
			oGridRueck.add_raw(this);
		}
		
		oGridRueck.setLayoutData(oLayoutThis);
		return oGridRueck;
	}

	@Override
	public Component C_ME() throws myException {
		return (Component) this.ME();
	}

	@Override
	public Vector<MyE2IF__Component> get_vADDONS_IN_WRAP() throws myException {
		return vADDONS_IN_WRAP;
	}

	@Override
	public void set_ME_First(boolean ME_InFront) {
		this.b_OnComponentInFront = ME_InFront;
	}

	@Override
	public void set_SpaceInPX(int iSpace) {
		this.i_SpaceInPixel=iSpace;
	}



	public int get_button_counter() {
		return iButtonCounter;
	}

	
}
