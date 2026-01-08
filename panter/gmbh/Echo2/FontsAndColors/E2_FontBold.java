package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Font;


public class E2_FontBold extends E2_Font implements IF_PdFonts
{


	   public E2_FontBold()
	   {
	        super(Font.BOLD, 0);
	   }

	    
	   public E2_FontBold(int iDiffSizeToStandard)
	   {
	       super(Font.BOLD,iDiffSizeToStandard);
	   }

}
