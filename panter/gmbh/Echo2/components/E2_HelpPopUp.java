/*
 * Created on 16.10.2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.Echo2.components;

import java.util.Vector;

import echopointng.PopUp;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_IF_Copy;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.RB.VALID.RB_Validator_Component;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;




public class E2_HelpPopUp extends PopUp  implements MyE2IF__Component, E2_IF_Copy, IF_RB_Component
{
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	
 
    // grid als Rahmen
    private E2_Grid 			gridAussen = 		new E2_Grid()._bc(new E2_ColorHelpBackground())._bord_black();
    private E2_Grid 			gridInnen = 		new E2_Grid()._bc(new E2_ColorHelpBackground());
    private E2_ResourceIcon 	oIconAktiv= 		E2_ResourceIcon.get_RI("help.png");
    private E2_ResourceIcon 	oIconInactiv = 		E2_ResourceIcon.get_RI("leer.png");

     
    
    private Vector<helpContent>   content= new Vector<E2_HelpPopUp.helpContent>();      
    
	private RB_KF Key = null;


	private int pixelBreitePopup=300;

    /**
     * @param vHelpStrings = Vector aus MyString
     */
    public E2_HelpPopUp()    {
        
        this.setToggleIcon(this.oIconAktiv);
        this.setTogglePressedIcon(this.oIconAktiv);
        this.setToggleRolloverIcon(this.oIconAktiv);
        
        this.gridAussen.setInsets(new Insets(4));
        this.gridInnen.setInsets(new Insets(0));
        
        this.setPopUp(this.gridAussen);
        this.gridAussen.add(this.gridInnen);
        
        this.setPopUpAlignment(new Alignment(Alignment.LEFT,Alignment.TOP));
        this.setInsets(new Insets(0));
        this.setOutsets(new Insets(0));
        this.setPopUpInsets(new Insets(0));
        this.setPopUpOutsets(new Insets(0));
        this.setPopUpBackground(new E2_ColorHelpBackground());
        
        this.setPopUpOnRollover(false);
        this.setPopUpAlwaysOnTop(true);

        this._setWidth(this.pixelBreitePopup);
    }


    public E2_HelpPopUp _addTextLine(String text) {
    	this.content.add(new simpleTextLine(text,0));
    	this.render();
    	return this;
    }

    public E2_HelpPopUp _addTextLine(String text, int fontsizeDiff) {
    	this.content.add(new simpleTextLine(text,fontsizeDiff));
    	this.render();
    	return this;
    }
    
    
    
    /**
     * 
     * @param fontsizeDiff
     * @param titel    (wird gerendert mit der 2. zeile eingerueckt)
     * @param text
     * @return
     */
    public E2_HelpPopUp _addTextAbschnitt(int fontsizeDiff, String titel, String...text) {
    	this.content.add(new MultiTextLineAbsatz(fontsizeDiff, titel, text));
    	this.render();
    	return this;
    }
    
 
    /**
     * 
     * @param fontsizeDiff
     * @param fontsizeText
     * @param w_spalte_titel (wird gerendert mit der 2. zeile eingerueckt)
     * @param w_spalteTxt
     * @param titel
     * @param text
     * @return
     */
    public E2_HelpPopUp _addTextAbschnitt(int fontsizeDiff, int fontsizeText,  int w_spalte_titel, int w_spalteTxt, String titel, String...text) {
    	this.content.add(new MultiTextLineAbsatz2(fontsizeDiff, fontsizeText, w_spalte_titel,w_spalteTxt, titel, text));
    	if (this.pixelBreitePopup<(w_spalte_titel+w_spalteTxt) ) {
    		this._setWidth(w_spalte_titel+w_spalteTxt);
    	}
    	this.render();
    	return this;
    }

    
    
    public E2_HelpPopUp _setWidth(int pixel) {
    	this.pixelBreitePopup = pixel;
		this.gridAussen._width(pixel);
    	this.gridInnen._width(pixel);

    	return this;
    }
    
    
    private void render() {
    	this.gridInnen._clear();
    	
    	for (helpContent c: this.content) {
    		c.renderIntoHelpGrid(this.gridInnen);
    	}
    }
    
    
    

	public void set_EXT(MyE2EXT__Component oext)				{		this.oEXT = oext;	}
	public MyE2EXT__Component EXT()								{		return this.oEXT;	}


	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException
	{
		boolean bEnabled = bbEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;

		if (bEnabled)
		{
	        this.setToggleIcon(this.oIconAktiv);
	        this.setTogglePressedIcon(this.oIconAktiv);
	        this.setToggleRolloverIcon(this.oIconAktiv);
		}
		else
		{
	        this.setToggleIcon(this.oIconInactiv);
	        this.setTogglePressedIcon(this.oIconInactiv);
	        this.setToggleRolloverIcon(this.oIconInactiv);
		}
		
		this.setEnabled(bEnabled);
		this.gridAussen.setEnabled(bEnabled);
		this.gridInnen.setEnabled(bEnabled);
	}



	public E2_ResourceIcon get_oIconAktiv()						{		return oIconAktiv;	}
	//public void set_oIconAktiv(E2_ResourceIcon iconAktiv)		{		oIconAktiv = iconAktiv; }
	public E2_ResourceIcon get_oIconInactiv()					{		return oIconInactiv;	}
	public void set_oIconInactiv(E2_ResourceIcon iconInactiv)	{		oIconInactiv = iconInactiv;	}

	
	
	public E2_HelpPopUp _setIconActiv(E2_ResourceIcon icon) {
		
		this.oIconAktiv = icon;
        this.setToggleIcon(this.oIconAktiv);
        this.setTogglePressedIcon(this.oIconAktiv);
        this.setToggleRolloverIcon(this.oIconAktiv);
		
        Border border = new Border(0, null, Border.STYLE_NONE);
        this.setBorder(border);
        this.setRolloverBorder(border);
        
		return this;
	}
	
	
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		return null;
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

	
	
	
	/**
	 * interface fuer die einfuegungswerte
	 * @author martin
	 *
	 */
	public static interface helpContent {
		public void renderIntoHelpGrid(E2_Grid container);
	}
	

	
	
	
	
	/**
	 * einfache textzeile einfuegen
	 */
	public static class simpleTextLine implements helpContent {
		private String line = null;
		private int fontSizeDiff = 0;
		
		/**
		 * @param line
		 */
		public simpleTextLine(String line, int fontDiff) {
			super();
			this.line = line;
			this.fontSizeDiff=fontDiff;
		}

		@Override
		public void renderIntoHelpGrid(E2_Grid container) {
			container._a(new RB_lab()._t(S.NN(this.line))._lw()._fsa(this.fontSizeDiff), new RB_gld()._ins(2)._span(container.getSize()));
		}
	}
	
	/**
	 * einfache textzeile einfuegen
	 */
	public static class MultiTextLineAbsatz implements helpContent {
		private String titel = null;
		private String[] zeilen = null;
		private int fontSizeDiff = 0;
		
		/**
		 * @param line
		 */
		public MultiTextLineAbsatz(int fontDiff, String p_titel, String...p_zeilen ) {
			super();
			this.titel = p_titel;
			this.zeilen = p_zeilen;
			this.fontSizeDiff=fontDiff;
		}

		@Override
		public void renderIntoHelpGrid(E2_Grid container) {
			E2_Grid g = new E2_Grid()._s(2);
			RB_gld ld = new RB_gld()._left_top()._ins(2);
			g._a(new RB_lab(this.titel)._fsa(this.fontSizeDiff),ld)._a(new RB_lab(this.zeilen[0])._fsa(this.fontSizeDiff),ld);
			for (int i=1; i<this.zeilen.length;i++) {
				g._a("",ld)._a(new RB_lab(this.zeilen[i])._fsa(this.fontSizeDiff),ld);
			}
			
			container._a(g, new RB_gld()._ins(2)._span(container.getSize()));
		}
	}


	/**
	 * einfache textzeile einfuegen
	 */
	public static class MultiTextLineAbsatz2 implements helpContent {
		private String titel = null;
		private String[] zeilen = null;
		private int fontSizeDiffTitel = 0;
		private int spalteTitel;
		private int spalteTxt;
		private int fontSizeDiffTxt;
		
		/**
		 * @param line
		 */
		public MultiTextLineAbsatz2(int fontDiffTitel,int fontDiffTxt,int spalteTitel, int spalteTxt, String p_titel, String...p_zeilen ) {
			super();
			this.fontSizeDiffTxt = fontDiffTxt;
			this.spalteTitel = spalteTitel;
			this.spalteTxt = spalteTxt;
			this.titel = p_titel;
			this.zeilen = p_zeilen;
			this.fontSizeDiffTitel=fontDiffTitel;
		}

		@Override
		public void renderIntoHelpGrid(E2_Grid container) {
			E2_Grid g = new E2_Grid()._setSize(this.spalteTitel,this.spalteTxt);
			g		._a(new RB_lab(this.titel		)._fsa(this.fontSizeDiffTitel),	new RB_gld()._left_top()._ins(2,2,2,0))
					._a(new RB_lab(this.zeilen[0]	)._fsa(this.fontSizeDiffTxt),	new RB_gld()._left_top()._ins(2,2,2,0));
			for (int i=1; i<this.zeilen.length;i++) {
				g._a("",new RB_gld()._left_top()._ins(2,2,2,0))._a(new RB_lab(this.zeilen[i])._fsa(this.fontSizeDiffTxt),new RB_gld()._left_top()._ins(2,0,2,0));
			}
			
			container._a(g, new RB_gld()._ins(2)._span(container.getSize()));
		}
	}

	
	
	//2018-05-22: if_rb_component
	//implementierung des interfaces IF_RB_Component
	@Override
	public RB_KF rb_KF() throws myException {
		return Key;
	}

	
	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.Key = key;
	}

	private Vector<RB_Validator_Component> vVALIDATORS_4_INPUT = new Vector<RB_Validator_Component>();
	@Override
	public Vector<RB_Validator_Component> rb_VALIDATORS_4_INPUT() {
		return this.vVALIDATORS_4_INPUT;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


	@Override
	public void rb_set_db_value_manual(String valueFormated) throws myException {
//		this.setText();
	}

	@Override
	public void mark_Neutral() throws myException {
	}

	@Override
	public void mark_MustField() throws myException {
	}

	@Override
	public void mark_Disabled() throws myException {
	}

	@Override
	public void mark_FalseInput() throws myException {
	}

	@Override
	public void set_Alignment(Alignment align) throws myException {
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		//wird hier nicht verwendet, ist als passives steuerelement gedacht
	}
	

}
