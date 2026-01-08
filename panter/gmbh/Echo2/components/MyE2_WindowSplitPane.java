package panter.gmbh.Echo2.components;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.LayoutData;
import nextapp.echo2.app.SplitPane;
import nextapp.echo2.app.WindowPane;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.Container.E2_ContentPane;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;


public class MyE2_WindowSplitPane extends WindowPane  implements MyE2IF__Component
{
	
	
	private MyE2EXT__Component 		oEXT = new MyE2EXT__Component(this);
	private MyString 				oTitle = null;
	
	private SplitPane				oSplitPane = new SplitPane(SplitPane.ORIENTATION_VERTICAL);
	
	private E2_ContentPane 			oPaneContent = new E2_ContentPane(true);
	private E2_ContentPane 			oPaneButtons = new E2_ContentPane(true);
	
	private MyE2_Row				oRowForButtons = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER());

	
	public MyE2_WindowSplitPane(boolean bModal)
	{
		super();
		this.setDefaultCloseOperation(WindowPane.DISPOSE_ON_CLOSE);
		this.setStyle(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE());
		this.setModal(bModal);
		this.__setPane(null);
		
	}



	private void __setPane(Extent Height)
	{
		E2_ContentPane oPaneBase = new E2_ContentPane(true);
		this.add(oPaneBase);

		oPaneBase.add(this.oSplitPane);

		
		if (Height != null)
			oSplitPane.setSeparatorPosition(new Extent(Height.getValue()-70,Height.getUnits()));    // platz für die buttons

		this.oSplitPane.add(this.oPaneContent);
		this.oSplitPane.add(this.oPaneButtons);
		this.oPaneButtons.add(this.oRowForButtons);
		
	}
	
	
	public void setHeight(Extent oHeight)
	{
		super.setHeight(oHeight);
		if (oHeight!=null && oSplitPane !=null)
			oSplitPane.setSeparatorPosition(new Extent(oHeight.getValue()-70,oHeight.getUnits()));
	}
	

	
	
	public void set_EXT(MyE2EXT__Component oeXT)
	{
		this.oEXT = oeXT;
	}

	public MyE2EXT__Component EXT()
	{
		return this.oEXT;
	}

	public void set_bEnabled_For_Edit(boolean bEnabled) throws myException
	{
		this.setEnabled(bEnabled && this.EXT().is_ValidEnableAlowed()&& this.EXT().get_bCanBeEnabled());
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
		int iRueck = 120 + (iGap + bibALL.get_FONT_SIZE())* iNumberOfRows;
		return iRueck;
	}
	public void show_InputStatus(boolean bInputIsOK)
	{
	}

	public MyE2_Row get_oRowForButtons() {
		return this.oRowForButtons;
	}

	public E2_ContentPane get_oPaneContent() {
		return oPaneContent;
	}

	public SplitPane get_oSplitPane() {
		return oSplitPane;
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

}
