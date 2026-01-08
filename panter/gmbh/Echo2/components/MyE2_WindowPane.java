package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.FillImage;
import nextapp.echo2.app.FillImageBorder;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Container.E2_FillImageBorder;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneContent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorWindowPaneTitel;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MyE2_WindowPane extends WindowPane  implements MyE2IF__Component
{
	
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	private MyString 				oTitle = null;

	private E2_BasicModuleContainer  oContainerThisBelongsTo = null;

	
//	/**
//	 * 20180118: Vector mit MyE2_WindowPane.userCloseHandler - objecten
//	 * Damit kann Einfluss genommen werden auf den Schliess-Vorgang des popup-fensters
//	 */
//	public Vector<MyE2_WindowPane.userCloseHandler> vCloseHandlers = new Vector<MyE2_WindowPane.userCloseHandler>();
//	
//	public Vector<MyE2_WindowPane.userCloseHandler> getvCloseHandlers() {
//		return vCloseHandlers;
//	}



	public MyE2_WindowPane(boolean bModal)
	{
		super();
		this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		this.setStyle(MyE2_WindowPane.STYLE_WINDOW_STANDARD());
		this.setModal(bModal);

	}

	

	
	public MyE2_WindowPane(MyString ctitle, Extent owidth, Extent oheight,boolean bModal)
	{
		super("", owidth, oheight);
		this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		this.oTitle = ctitle;
		this.setTitle(this.oTitle.CTrans());
		this.setStyle(MyE2_WindowPane.STYLE_WINDOW_STANDARD());
		this.setTitleForeground(Color.WHITE);
		this.setModal(bModal);
	}

	
	
	public void set_EXT(MyE2EXT__Component oeXT)
	{
		this.oEXT = oeXT;
	}

	public MyE2EXT__Component EXT()
	{
		return this.oEXT;
	}

	public void set_bEnabled_For_Edit(boolean bbEnabled) throws myException
	{
		boolean bEnabled = bbEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled();;
		this.setEnabled(bEnabled);
	}

	public MyString get_oTitle()
	{
		return this.oTitle;
	}

	public void set_oTitle(MyString title)
	{
		this.oTitle = title;
		this.setTitle(this.oTitle.CTrans());
	}


	/**
	 * @param iNumberOfRows (anzahl zeilen)
	 * @param iGap (zusatz pro zeile)
	 * @return
	 */
	public static int CalculateWindowHight(int iNumberOfRows, int iGap)
	{
		int iRueck = 100 + (iGap + bibALL.get_FONT_SIZE())* iNumberOfRows;
		return iRueck;
	}
	
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	
	
	public static MutableStyle STYLE_WINDOW_STANDARD()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent());
		try
		{
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose.png"));
		oStyle.setProperty( WindowPane.PROPERTY_ICON_INSETS, new Insets(0,0,0,0));

		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));
		
		try
		{
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())
			{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(3+2*bibALL.get_FONT_SIZE()));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBold());
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		return oStyle;
		
	}

	
	//2016-01-26: standardfenster mit schaltbarem closebutton in der rechten ecke
	public static MutableStyle STYLE_WINDOW_STANDARD(boolean b_is_closeable) {
		MutableStyle oStyle = MyE2_WindowPane.STYLE_WINDOW_STANDARD();
		oStyle.setProperty( WindowPane.PROPERTY_CLOSABLE, new Boolean(b_is_closeable));
		return oStyle;
	}

	
	
	
	public static MutableStyle STYLE_WINDOW_SMALL_TITLE()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent());
		try
		{
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose_small.png"));
		
		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));

		
		try
		{
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())
			{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(3+2*(bibALL.get_FONT_SIZE()-2)));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBoldItalic(-2));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		return oStyle;
		
	}

	
	
	
	
	public static MutableStyle STYLE_WINDOW_SMALL_TITLE_NO_CLOSE()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent());
		try
		{
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, null);
		oStyle.setProperty( WindowPane.PROPERTY_CLOSABLE, new Boolean(false));
		
		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));

		
		try
		{
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())
			{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(3+2*(bibALL.get_FONT_SIZE()-2)));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBoldItalic(-2));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		return oStyle;
		
	}


	
	
	public static MutableStyle STYLE_WINDOW_4_HELP(Color HelpBackground)
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, HelpBackground);
		try
		{
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose_small.png"));
		
		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));

		
		try
		{
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())
			{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(3+2*(bibALL.get_FONT_SIZE()-2)));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBoldItalic(-2));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		return oStyle;
		
	}


	
	
	
	
	
	
	
	
	
	
	
	
	public E2_BasicModuleContainer get_oContainerThisBelongsTo()
	{
		return oContainerThisBelongsTo;
	}




	public void set_oContainerThisBelongsTo(E2_BasicModuleContainer oContainerThisBelongsTo)
	{
		this.oContainerThisBelongsTo = oContainerThisBelongsTo;
	}





	

	//2011-12-13: meldungs-window, non-modal
	public static MutableStyle STYLE_WINDOW_4_MESSAGES()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent());
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorDDDark());

		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT, new Extent(6));
		oStyle.setProperty( WindowPane.PROPERTY_MINIMUM_HEIGHT , new Extent(30));
		
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, null);
		oStyle.setProperty( WindowPane.PROPERTY_CLOSABLE, new Boolean(false));
		
		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));

		
		try
		{
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())
			{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(3+2*(bibALL.get_FONT_SIZE()-2)));
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBoldItalic(-2));
//		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		return oStyle;
		
	}



	
	
	public static MutableStyle STYLE_WINDOW_4_POPUP_MESSAGES()
	{
		MutableStyle oStyle = new MutableStyle();
		oStyle.setProperty( WindowPane.PROPERTY_BACKGROUND, new E2_ColorWindowPaneContent());
		try
		{
			oStyle.setProperty( WindowPane.PROPERTY_TITLE_BACKGROUND, new E2_ColorWindowPaneTitel());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		oStyle.setProperty( WindowPane.PROPERTY_CLOSE_ICON, E2_ResourceIcon.get_RI("windowpaneclose.png"));
		oStyle.setProperty( WindowPane.PROPERTY_ICON_INSETS, new Insets(0,0,0,0));

		oStyle.setProperty( WindowPane.PROPERTY_BORDER, new E2_FillImageBorder(Color.BLACK,new Insets(1),new Insets(1)));
		
		try
		{
			if (bibALL.get_RECORD_USER().is_FENSTER_MIT_SCHATTEN_YES())
			{
				FillImageBorder border = new FillImageBorder();
				
				border.setBorderInsets(new Insets(20));
				border.setContentInsets(new Insets(4,4,10,10));
				border.setFillImage(FillImageBorder.TOP_LEFT, new FillImage(E2_ResourceIcon.get_RI("bordertopleft.png")));
				border.setFillImage(FillImageBorder.TOP, new FillImage(E2_ResourceIcon.get_RI("bordertop.png")));
				border.setFillImage(FillImageBorder.TOP_RIGHT, new FillImage(E2_ResourceIcon.get_RI("bordertopright.png")));
				border.setFillImage(FillImageBorder.LEFT, new FillImage(E2_ResourceIcon.get_RI("borderleft.png")));
				border.setFillImage(FillImageBorder.RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderright.png")));
				border.setFillImage(FillImageBorder.BOTTOM_LEFT, new FillImage(E2_ResourceIcon.get_RI("borderbottomleft.png")));
				border.setFillImage(FillImageBorder.BOTTOM, new FillImage(E2_ResourceIcon.get_RI("borderbottom.png")));
				border.setFillImage(FillImageBorder.BOTTOM_RIGHT, new FillImage(E2_ResourceIcon.get_RI("borderbottomright.png")));
				
				oStyle.setProperty( WindowPane.PROPERTY_BORDER,border);
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_HEIGHT,new Extent(3+2*bibALL.get_FONT_SIZE()));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_INSETS,new Insets(7,3,0,0));
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FONT, new E2_FontBold());
		oStyle.setProperty( WindowPane.PROPERTY_TITLE_FOREGROUND, Color.WHITE);
		return oStyle;
		
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


	public void set_Size(MyE2_WindowPaneExtender oExt) {
		this.setPositionX(oExt.get_extLeft());
		this.setPositionY(oExt.get_extTop());
		this.setWidth(oExt.get_extWidth());
		this.setHeight(oExt.get_extHeight());
	}

	
//	/**
//	 * 2018-01-18: ueberschreiben, um eine validierung durchfuehren zu koennen
//	 */
//	public void userClose() {
//		Vector<MyE2_WindowPane.userCloseHandler> v = new Vector<MyE2_WindowPane.userCloseHandler>();
//		v.addAll(this.vCloseHandlers);                   //zuerst die eigenen
//		if (this.oContainerThisBelongsTo!=null) {
//			v.addAll(this.oContainerThisBelongsTo.getvCloseHandlers());
//		}
//		
//		MyE2_MessageVector mv = new MyE2_MessageVector();
//		MyE2_MessageVector mvAllowForce = new MyE2_MessageVector();
//		
//		for (userCloseHandler h: v) {
//			try {
//				if (h.allowForceClose()) {
//					mvAllowForce.add_MESSAGE(h.checkClose());
//				} else {
//					mv.add_MESSAGE(h.checkClose());
//				}
//			} catch (myException e) {
//				mv.add(e.get_ErrorMessage());
//				e.printStackTrace();
//			}
//		}
//		
//		if (mv.get_bHasAlarms()) {
//			mv.add_MESSAGE(mvAllowForce);         //dann gibt es keine moeglichkeit trotzdem zu schliessen 
//			
//			if (this.oContainerThisBelongsTo != null) {
//				this.oContainerThisBelongsTo.showMessages(mv);
//			} else {
//				new E2_WindowPane_4_PopupMessages().showMessages(mv);
//			}
//		} else if (mvAllowForce.size()>0) {
//			try {
//				new ownMessageBoxYesNo(mvAllowForce);
//			} catch (myException e) {
//				e.printStackTrace();
//			}
//		} else {
//			super.userClose();
//		}
//	}
//	
//	public void forceClose() {
//		super.userClose();
//	}
//	
//	
//	
//	public static abstract class userCloseHandler {
//		public abstract MyE2_MessageVector checkClose()  throws myException;
//		public abstract boolean allowForceClose();
//	}
//
//	
//	
//	private class ownMessageBoxYesNo extends E2_MessageBox {
//		public ownMessageBoxYesNo(MyE2_MessageVector mv) throws myException {
//			super();
//			
//			this._setTitleOfPopup(S.ms("Bitte bestätigen ..."));
//			
//			for (MyE2_Message m: mv) {
//				this.getVInfos().add(new MyE2_String(m.get_cMessage().CTrans(), false));
//			}
//
//			this.getBtYes()._tr("Trotzdem schliessen");
//			this.getBtNo()._tr("Abbruch der Aktion");
//
//			this.getBtYes()._aaa(new XX_ActionAgent() {
//				@Override
//				public void executeAgentCode(ExecINFO oExecInfo) throws myException {
//					//fenster trotzdem schliessen
//					MyE2_WindowPane.this.forceClose();
//				}
//			});
//			
//			this._show(400, 400);
//		}
//	}
	 
	
	
	/*
	 * 2018-05-16: neue methode, um den close-button zu kontrollieren:
	 * Es kann dieser klasse ein externer button zugeordnet werden, der die close-operation ausfuehrt.
	 * Dies kann ein eigener button oder ein button aus der maske sein.
	 * Wenn ein solcher button existert, dann wird die userClose() - Methode ueberbrueckt und durch die ausfuehrung des buttons ersetzt.
	 * Einzige Anderung im sonstigen Code: im CLOSE_AND_DESTROY - aufruf wird nicht die userclose-Methode, sondern die methode superUserClose() aufgerufen
	 */
	private Button buttonForClosingWindow = null;
	
	public Button getButtonForClosingWindow() {
		return buttonForClosingWindow;
	}


	public void setButtonForClosingWindow(Button buttonForClosingWindow) {
		this.buttonForClosingWindow = buttonForClosingWindow;
	}

	
	public void superUserClose() {
		super.userClose();
	}
	
    private void handleOwnClose() {
   		this.buttonForClosingWindow.doAction();
    }
    
    
    
    @Override
    public void userClose() {
    	if (this.buttonForClosingWindow==null) {
    		this.superUserClose();
    	} else {
    		this.handleOwnClose();
    	}
    }



	
	
}
