package panter.gmbh.Echo2.components;

import java.util.Vector;

import echopointng.PopUp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.Style;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_ContainerEx;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;



/**
 * neue Version mit intern statt column ein E2_Grid
 * @author martin
 */
public class E2_PopUp extends PopUp  implements MyE2IF__Component, E2_IF_Copy {
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
    private Vector<MyE2IF__Component[]>  	vMenueLines = new Vector<MyE2IF__Component[]>();

 
    // grid als Rahmen
    private E2_Grid 	 		gridAussen = 		new E2_Grid();
    private E2_Grid 			gridInnen = 		new E2_Grid();
    private E2_ResourceIcon 	oIconAktiv= 		E2_ResourceIcon.get_RI("popup.png");
    private E2_ResourceIcon 	oIconInactiv = 		E2_ResourceIcon.get_RI("popup__.png");

    /*
     * falls die zahl von DropDown-buttons zu gross wird und 
     * oContExWidth / oContExHeight != null sind wird ein container-ex in den 
     * popup geschoben
     */
    private MyE2_ContainerEx	oContEx	= new MyE2_ContainerEx();
    private Extent				oContExWidth = new Extent(200);
    private Extent				oContExHeight = new Extent(200);
    private boolean 			bUseContainerEx = false;
    private int    				anzahl_button_spalten = 0;
    
    private Color               popup_col = new E2_ColorBase(20);
    private Color               col_popup_border = new E2_ColorGray(100);
    private Color    			col_rol_over_background = new E2_ColorGray(200);		//wenn null, dann kein rollover
    
    
//    //falls es eine caskadierende Struktur ist (popups in popups) muessen alle vorigen popups bei einem klick geschlossen werden
//    private Vector<E2_PopUp>    vMotherPopupsToCollapseInClick = new Vector<E2_PopUp>();
    
    private E2_PopUp            motherPopup = null;
    
    
    public E2_PopUp() {
    	super();
		
    	this.gridInnen.setInsets(new Insets(0,0,0,0));
    	this.gridAussen.setInsets(new Insets(0,0,0,0));
    	
		this.setToggleIcon(this.oIconAktiv);
		this.setTogglePressedIcon(this.oIconAktiv);
		this.setToggleRolloverIcon(this.oIconAktiv);
        this.setPopUpLeftOffset(-6);
        this.setPopUpTopOffset(6);

		this._set_inner_size(150);
	    this.setPopUpOnRollover(false);
	    this.setPopUpAlwaysOnTop(true);
	    this.setInsets(new Insets(0,0,0,0));
	    this.setOutsets(new Insets(0,0,0,0));

	    this.setPopUpInsets(new Insets(0,0,0,0));
	    this.setPopUpBorder(new Border(1, this.col_popup_border, Border.STYLE_SOLID));
	    this.gridInnen.setInsets(new Insets(0));
	    this.gridAussen.setInsets(new Insets(0));

	    this._use_container_ex(false);
	    
	    this.activate_color();
	}


    public E2_PopUp _set_icon_activ(E2_ResourceIcon icon) {
    	this.oIconAktiv = icon;
		this.setToggleIcon(this.oIconAktiv);
		this.setTogglePressedIcon(this.oIconAktiv);
		this.setToggleRolloverIcon(this.oIconAktiv);
    	return this;
    }

    
    
    public E2_PopUp _rebuild() {
    	this.rebuild();
    	return this;
    }
    
    
    
    protected void rebuild() {
        this.removeAll();
        this.gridAussen.removeAll();
        this.oContEx.removeAll();
        
        this.oContEx.setWidth(this.oContExWidth);
        this.oContEx.setHeight(this.oContExHeight);
        
        if (! this.bUseContainerEx)        {
        	this.gridAussen._a(this.gridInnen, new RB_gld()._ins(0));
            this.setPopUp(this.gridAussen);
        }  else  {
        	this.gridAussen._a(this.gridInnen, new RB_gld()._ins(0));
        	this.oContEx.add(this.gridAussen);
            this.setPopUp(this.oContEx);
        }

    	//alle ausser checkboxen loesen collapse aus
        for (MyE2IF__Component[] components: this.vMenueLines) {
	    	for (MyE2IF__Component  comp: components) {
	    		if (comp instanceof E2_IF_Handles_ActionAgents && (! (comp instanceof CheckBox))) {
	    			((E2_IF_Handles_ActionAgents) comp).add_oActionAgent(new OwnAgentCollaps(this));
	    		}
	    		
	    		if (((Component)comp).getLayoutData()==null || (!(((Component)comp).getLayoutData() instanceof GridLayoutData) )) {
	    			//dann ein standardlayout vergeben ...
	    			((Component)comp).setLayoutData(new RB_gld()._ins(2));
	    		}
	    		
	    		this.gridInnen._add_raw((Component)comp);
	    	}
        }
        
        this.activate_color();
    }
    
    
    private void activate_rollover() {
    	if (this.col_rol_over_background==null) {
    	
	        for (MyE2IF__Component[] components: this.vMenueLines) {
		    	for (MyE2IF__Component  comp: components) {
		    		if (comp instanceof AbstractButton) {
		    			((AbstractButton) comp).setRolloverEnabled(false);
		    		}
		    	}
	        }

    	} else {
    		
	        for (MyE2IF__Component[] components: this.vMenueLines) {
		    	for (MyE2IF__Component  comp: components) {
		    		if (comp instanceof AbstractButton) {
		    			((AbstractButton) comp).setRolloverEnabled(true);
		    			((AbstractButton) comp).setRolloverBackground(this.col_rol_over_background);
		    		}
		    	}
	        }
    	}
    }
    
    
    
    public E2_PopUp _set_popup_color(Color col) {
    	this.popup_col = col;
    	this.activate_color();
    	return this;
    }
    
    
    public E2_PopUp _set_roll_over_background(Color col) {
    	this.col_rol_over_background = col;
    	this.activate_color();
    	return this;
    }
    
    
    protected void activate_color() {
    	Color col = this.popup_col;
    	this.gridInnen.setBackground(col);
    	this.gridAussen.setBackground(col);
    	this.gridInnen._bo_col(col);
    	this.gridAussen._bo_col(col);
    	this.oContEx.setBackground(col);
    	this.setPopUpBackground(col);
    	this.activate_rollover();
    }
    
    public E2_PopUp _set_icon_inactiv(E2_ResourceIcon icon) {
    	this.oIconInactiv = icon;
    	return this;
    }
     
    public E2_PopUp _use_container_ex(boolean b_use_ex) {
    	this.bUseContainerEx = b_use_ex;
        this.rebuild();
    	return this;
    }
    
    public E2_PopUp _use_container_ex(boolean b_use_ex, boolean rebuild) {
    	this.bUseContainerEx = b_use_ex;
        if (rebuild) {
        	this.rebuild();
        }
    	return this;
    }

    

    public E2_PopUp _set_container_width(Extent container_width) {
    	this.oContExWidth = container_width;
    	//DEBUG._print(this.oContExWidth.toString());
    	return this;
    }
    
    public E2_PopUp _set_container_heigth(Extent container_height) {
    	this.oContExHeight = container_height;
    	return this;
    }
   
    
    public E2_PopUp _set_inner_size(int... array_spaltenbreite) {
    	this.gridInnen._setSize(array_spaltenbreite);
    	this.anzahl_button_spalten = array_spaltenbreite.length;
    	return this;
    }
    
    public E2_PopUp _set_number_cols(int number_cols) {
    	this.gridInnen.setSize(number_cols);
    	this.anzahl_button_spalten = number_cols;
    	return this;
    }
    
    
    public E2_PopUp _set_outer_border(Border b) {
    	this.gridAussen.setBorder(b);
    	return this;
    }
    
    public E2_PopUp _set_inner_border(Border b) {
    	this.gridInnen.setBorder(b);
    	return this;
    }
    
    
    public E2_Grid get_grid_aussen() {
    	return this.gridAussen;
    }
    
    public E2_Grid get_grid_innen() {
    	return this.gridInnen;
    }
    
    
    public E2_PopUp _add_line(MyE2IF__Component... components) throws myException    {
    	
    	if (components.length!=this.anzahl_button_spalten) {
    		throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
    	}
    	
    	this.vMenueLines.add(components);
    	
    	this.rebuild();
    	
    	return this;
    }

    
    public E2_PopUp _add_line(MyE2IF__Component component1, GridLayoutData gl1) throws myException    {
    	
    	if (1!=this.anzahl_button_spalten) {
    		throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
    	}
    	
    	MyE2IF__Component[] line= new MyE2IF__Component[1];
    	line[0] = component1;
    	((Component)component1).setLayoutData(gl1);
    	this.vMenueLines.add(line);
    	
    	this.rebuild();
    	
    	return this;
    } 

    
    public E2_PopUp _add_line(	MyE2IF__Component component1, GridLayoutData gl1
    							,MyE2IF__Component component2, GridLayoutData gl2
    							) throws myException    {
    	
    	if (2!=this.anzahl_button_spalten) {
    		throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
    	}
    	
    	MyE2IF__Component[] line= new MyE2IF__Component[2];
    	line[0] = component1;
    	line[1] = component2;
    	
    	((Component)component1).setLayoutData(gl1);
    	((Component)component2).setLayoutData(gl2);

    	this.vMenueLines.add(line);
    	
    	this.rebuild();
    	
    	return this;
    } 

    
    public E2_PopUp _add_line(	MyE2IF__Component component1, GridLayoutData gl1
								,MyE2IF__Component component2, GridLayoutData gl2
								,MyE2IF__Component component3, GridLayoutData gl3
								) throws myException    {

		if (3!=this.anzahl_button_spalten) {
			throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
		}
		
		MyE2IF__Component[] line= new MyE2IF__Component[3];
		line[0] = component1;
		line[1] = component2;
		line[2] = component3;
		
		((Component)component1).setLayoutData(gl1);
		((Component)component2).setLayoutData(gl2);
		((Component)component3).setLayoutData(gl3);
		
		this.vMenueLines.add(line);
		
		this.rebuild();
		
		return this;
    } 


    
   public E2_PopUp _add_line_withoutRebuild(MyE2IF__Component... components) throws myException    {
    	
    	if (components.length!=this.anzahl_button_spalten) {
    		throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
    	}
    	
    	this.vMenueLines.add(components);
    	
    	
    	return this;
    }

    
    public E2_PopUp _add_line_withoutRebuild(MyE2IF__Component component1, GridLayoutData gl1) throws myException    {
    	
    	if (1!=this.anzahl_button_spalten) {
    		throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
    	}
    	
    	MyE2IF__Component[] line= new MyE2IF__Component[1];
    	line[0] = component1;
    	((Component)component1).setLayoutData(gl1);
    	this.vMenueLines.add(line);
    	
    	
    	return this;
    } 

    
    public E2_PopUp _add_line_withoutRebuild(	MyE2IF__Component component1, GridLayoutData gl1
    							,MyE2IF__Component component2, GridLayoutData gl2
    							) throws myException    {
    	
    	if (2!=this.anzahl_button_spalten) {
    		throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
    	}
    	
    	MyE2IF__Component[] line= new MyE2IF__Component[2];
    	line[0] = component1;
    	line[1] = component2;
    	
    	((Component)component1).setLayoutData(gl1);
    	((Component)component2).setLayoutData(gl2);

    	this.vMenueLines.add(line);
    	
    	
    	return this;
    } 

    
    public E2_PopUp _add_line_withoutRebuild(	MyE2IF__Component component1, GridLayoutData gl1
								,MyE2IF__Component component2, GridLayoutData gl2
								,MyE2IF__Component component3, GridLayoutData gl3
								) throws myException    {

		if (3!=this.anzahl_button_spalten) {
			throw new myException(this, "number of colums for line must be the same as number of columns of inner grid !!");
		}
		
		MyE2IF__Component[] line= new MyE2IF__Component[3];
		line[0] = component1;
		line[1] = component2;
		line[2] = component3;
		
		((Component)component1).setLayoutData(gl1);
		((Component)component2).setLayoutData(gl2);
		((Component)component3).setLayoutData(gl3);
		
		this.vMenueLines.add(line);
		
		
		return this;
    } 

    
    
    
    public E2_PopUp clear()   {
        this.vMenueLines.clear();
        this.gridInnen.removeAll();
        return this;
    }

    

    


	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}


	
	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException {
		
		boolean bEnabled = bbEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		if (bEnabled) {
			this.setToggleIcon(this.oIconAktiv);
		} else {
			this.setToggleIcon(this.oIconInactiv);
		}
		
		this.setEnabled(bEnabled);
		this.gridAussen.setEnabled(bEnabled);
		this.gridInnen.setEnabled(bEnabled);
		
		for (MyE2IF__Component[] line:  this.vMenueLines) {
			for (MyE2IF__Component c: line) {
				c.set_bEnabled_For_Edit(bEnabled);
			}
		}
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
	
	public Vector<MyE2IF__Component[]> get_vMenueLines()							{		return this.vMenueLines;	}

	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy 	{
		throw new myExceptionCopy("not implemented yet!");
	}

	
	public class OwnAgentCollaps extends XX_ActionAgent {
		private E2_PopUp oPopUp = null;
		public OwnAgentCollaps(E2_PopUp popup)	{
			super();
			oPopUp = popup;
		}

		public void executeAgentCode(ExecINFO oExecInfo){
			this.oPopUp.closeMeAndMotherPopup();
		}
	}
	

	
	
	public E2_Grid get_gridInnen() {
		return this.gridInnen;
	}




	public MyE2_ContainerEx get_oContainerEx() {
		return oContEx;
	}


	/**
	 * liefert alle im popup befindlichen action-components
	 * @return
	 */
	public Vector<E2_IF_Handles_ActionAgents> get_action_handles() {
		Vector<E2_IF_Handles_ActionAgents> v = new Vector<>();
		
		for (MyE2IF__Component[] compLine: this.vMenueLines) {
			for (MyE2IF__Component c: compLine) {
				if (c instanceof E2_IF_Handles_ActionAgents) {
					v.add((E2_IF_Handles_ActionAgents)c);
				}
			}
		}
		return v;
	}
	

	public void add_ActionAgentToPopupButtons(XX_ActionAgent oAgent, boolean bInFront) {
		Vector<E2_IF_Handles_ActionAgents> v = this.get_action_handles();
		
		for (E2_IF_Handles_ActionAgents action: v) {
			action.add_oActionAgent(oAgent,bInFront);
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


//	public Vector<E2_PopUp> getMotherPopupsToCollapseInClick() {
//		return vMotherPopupsToCollapseInClick;
//	}


	public E2_PopUp getMotherPopup() {
		return motherPopup;
	}


	public void setMotherPopup(E2_PopUp motherPopup) {
		this.motherPopup = motherPopup;
	}

	
	public void closeMeAndMotherPopup() {
		if (this.motherPopup!=null) {
			this.motherPopup.closeMeAndMotherPopup();
		}
		this.setExpanded(false);
	}
	
	
	public E2_PopUp _setUnVisible() {
		E2_ResourceIcon minIcon = E2_ResourceIcon.get_RI("empty1x1.png");
		
		this.oIconAktiv=minIcon;
		this.oIconInactiv=minIcon;
		
		this.setToggleIcon(minIcon);
	    this.setTogglePressedIcon(minIcon);
	    this.setToggleRolloverIcon(minIcon);
	    this.setRolloverEnabled(false);
		
		return this;
	}


	public Color getPopup_col() {
		return popup_col;
	}


	public E2_PopUp _setPopup_col(Color popup_col) {
		this.popup_col = popup_col;
		return this;
	}


	public Color getCol_popup_border() {
		return col_popup_border;
	}


	public E2_PopUp _setCol_popup_border(Color col_popup_border) {
		this.col_popup_border = col_popup_border;
		return this;
	}


	public Color getCol_rol_over_background() {
		return col_rol_over_background;
	}


	public E2_PopUp _setCol_rol_over_background(Color col_rol_over_background) {
		this.col_rol_over_background = col_rol_over_background;
		return this;
	}
	

	/**
	 * This DEFAULT_STYLE is applied to the popup to give it a series of borders and background colors
	 */
	public static Style getDefaultStyle() 	{
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

}
