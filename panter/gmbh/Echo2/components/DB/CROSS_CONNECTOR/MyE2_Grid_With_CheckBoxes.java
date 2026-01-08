package panter.gmbh.Echo2.components.DB.CROSS_CONNECTOR;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_Grid_With_CheckBoxes extends MyE2_Grid
{
	private Vector<MyE2_CheckBox> 			vCheckBoxes = null;
		
	private Vector<MyE2IF__Component>		vAddOnComponentsInFront = new Vector<MyE2IF__Component>();  
	private Vector<MyE2IF__Component>		vAddOnComponentsBehind = new Vector<MyE2IF__Component>();;  
	
	private int 							iNumOfCols = 1;
	/*
     * specialagent, um festzulegen, welche button-auswahl gerade gezeigt wird
     * (z.B. um aktive und inaktive zu unterscheiden)
     */
    private XX_SelectAgentForCheckboxesVisible	oAgentToSelektAnzeige = null;
	
    private XX_FormatCheckBox       oFormatCheckBoxes = null;

    
    public MyE2_Grid_With_CheckBoxes()
	{
		super();
		this.setOrientation(ORIENTATION_VERTICAL);
	}

	public MyE2_Grid_With_CheckBoxes(int numCols, int borderSize)
	{
		super(numCols, borderSize);
		this.iNumOfCols = numCols;
		this.setOrientation(ORIENTATION_VERTICAL);
	}

	public MyE2_Grid_With_CheckBoxes(int numCols, MutableStyle style)
	{
		super(numCols, style);
		this.iNumOfCols = numCols;
		this.setOrientation(ORIENTATION_VERTICAL);
	}

	public MyE2_Grid_With_CheckBoxes(int numCols)
	{
		super(numCols);
		this.iNumOfCols = numCols;
		this.setOrientation(ORIENTATION_VERTICAL);
	}

	public MyE2_Grid_With_CheckBoxes(MutableStyle style)
	{
		super(style);
		this.setOrientation(ORIENTATION_VERTICAL);
	}

	public Vector<MyE2_CheckBox> get_vCheckBoxes()
	{
		return vCheckBoxes;
	}

	public void set_vCheckBoxes(Vector<MyE2_CheckBox> checkBoxes)
	{
		vCheckBoxes = checkBoxes;
	}

	public XX_SelectAgentForCheckboxesVisible get_oAgentToSelektAnzeige()
	{
		return oAgentToSelektAnzeige;
	}

	public void set_oAgentToSelektAnzeige(XX_SelectAgentForCheckboxesVisible agentToSelektAnzeige)
	{
		oAgentToSelektAnzeige = agentToSelektAnzeige;
	}

	public void build_selectorGrid() throws myException
    {
		
		int iNumOfElements = 0;
		if (vAddOnComponentsInFront != null) iNumOfElements += vAddOnComponentsInFront.size();
		if (vAddOnComponentsBehind != null) iNumOfElements += vAddOnComponentsBehind.size();
		
		// Zählen der Checkboxen
	    if (this.vCheckBoxes != null){
	        for (int i = 0; i < this.vCheckBoxes.size(); i++) {   
	        	boolean bVisible = true;
	        	if (this.oAgentToSelektAnzeige != null)	{
        			bVisible = this.oAgentToSelektAnzeige.get_Visible(this.vCheckBoxes.get(i));
	        	}
	        	if (bVisible) {
	        		iNumOfElements ++;
	        	}
	        }
        }
		
		
		
		// wenn die Orientierung Vertikal gelegt ist, dann 
		// muss die Zeilenzahl berechnet werden.
		if( this.getOrientation() == Grid.ORIENTATION_VERTICAL) {
			this.setSize( (iNumOfElements / iNumOfCols) + 1);
		} else {
			this.setSize(iNumOfCols);
		}
		
    	this.removeAll();
    	
        if (this.vAddOnComponentsInFront != null)
        {
        	for (int i=0;i<this.vAddOnComponentsInFront.size();i++)
        	{
        		this.add((Component)this.vAddOnComponentsInFront.get(i));
        	}
        }
        
        if (this.vCheckBoxes != null)
        {
	        for (int i = 0; i < this.vCheckBoxes.size(); i++)
	        {   
	        	boolean bVisible = true;
	        	if (this.oAgentToSelektAnzeige != null)
	        	{
        			bVisible = this.oAgentToSelektAnzeige.get_Visible(this.vCheckBoxes.get(i));
	        	}
	        	if (bVisible)
	        	{
	        		this.add(this.vCheckBoxes.get(i));
	        	}
	        	
	        	//2011-04-12: formatieren
	        	if (this.oFormatCheckBoxes!=null)
	        	{
	        		this.oFormatCheckBoxes.doFormat(this.vCheckBoxes.get(i));
	        	}
	        	
	        	
	        }
        }
        
        if (this.vAddOnComponentsBehind != null)
        {
        	for (int i=0;i<this.vAddOnComponentsBehind.size();i++)
        	{
        		this.add((Component)this.vAddOnComponentsBehind.get(i));
        	}
        }
    }

	public Vector<MyE2IF__Component> get_vAddOnComponentsInFront()
	{
		return vAddOnComponentsInFront;
	}

	public void set_vAddOnComponentsInFront(Vector<MyE2IF__Component> addOnComponentsInFront)
	{
		vAddOnComponentsInFront = addOnComponentsInFront;
	}

	public Vector<MyE2IF__Component> get_vAddOnComponentsBehind()
	{
		return vAddOnComponentsBehind;
	}

	public void set_vAddOnComponentsBehind(Vector<MyE2IF__Component> addOnComponentsBehind)
	{
		vAddOnComponentsBehind = addOnComponentsBehind;
	}
	
	public void add_AddOnComponentsInFront(MyE2IF__Component addOnComponentsInFront) throws myException
	{
		this.vAddOnComponentsInFront.add(addOnComponentsInFront);
		/*
		 * hier muss nochmals das grid gebaut werden
		 */
		this.build_selectorGrid();
	}

	public void add_AddOnComponentsBehind(MyE2IF__Component addOnComponentsBehind) throws myException
	{
		this.vAddOnComponentsBehind.add(addOnComponentsBehind);
		/*
		 * hier muss nochmals das grid gebaut werden
		 */
		this.build_selectorGrid();

	}
	
	
    public XX_FormatCheckBox get_oFormatCheckBoxes()
    {
    	return this.oFormatCheckBoxes;
    }
    
    public void set_oFormatCheckBoxes(XX_FormatCheckBox o_FormatCheckBoxes)
    {
    	this.oFormatCheckBoxes = o_FormatCheckBoxes;
    }

	/**
	 * @param iNumOfCols the iNumOfCols to set
	 */
	public void set_NumOfColsHorizontalOrRowsVertical(int iNumOfCols) {
		this.iNumOfCols = iNumOfCols;
	}

	/**
	 * @return the iNumOfCols
	 */
	public int get_NumOfCols() {
		return iNumOfCols;
	}

	
	/**
	 * 2013-04-09: neue methode, die die formatierung nach einer aktion ausloest
	 * @throws myException 
	 */
	public void MakeFormatting() throws myException
	{
		for (MyE2_CheckBox oCB: this.vCheckBoxes)
		{
			if (this.oFormatCheckBoxes!=null)
			{
				this.oFormatCheckBoxes.doFormat(oCB);
			}
		}
	}
	
	
}
