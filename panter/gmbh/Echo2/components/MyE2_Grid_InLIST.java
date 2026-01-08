package panter.gmbh.Echo2.components;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import nextapp.echo2.app.MutableStyle;

public class MyE2_Grid_InLIST extends MyE2_Grid 
{

	public MyE2_Grid_InLIST() {
		super();
	}

	public MyE2_Grid_InLIST(int iNumCols, int iBorderSize) {
		super(iNumCols, iBorderSize);
	}

	public MyE2_Grid_InLIST(int iNumCols, MutableStyle oStyle) {
		super(iNumCols, oStyle);
	}

	public MyE2_Grid_InLIST(int iNumCols) {
		super(iNumCols);
	}

	public MyE2_Grid_InLIST(int[] iSpalten, int iBorderSize) {
		super(iSpalten, iBorderSize);
	}

	public MyE2_Grid_InLIST(int[] iSpalten, MutableStyle oStyle, boolean bScaleRowsTo100Percent) {
		super(iSpalten, oStyle, bScaleRowsTo100Percent);
	}

	public MyE2_Grid_InLIST(int[] iSpalten, MutableStyle oStyle) {
		super(iSpalten, oStyle);
	}

	public MyE2_Grid_InLIST(MutableStyle oStyle) {
		super(oStyle);
	}

	
	//hier ohne funktion!!
	public void set_bEnabled_For_Edit(boolean enabled) throws myException
	{
	
	}

	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		MyE2_Grid_InLIST  oGridCopy = new MyE2_Grid_InLIST(this.getSize());
		
		oGridCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oGridCopy));

		oGridCopy.setStyle(this.getStyle());
		
		return oGridCopy;
	}

	
}
