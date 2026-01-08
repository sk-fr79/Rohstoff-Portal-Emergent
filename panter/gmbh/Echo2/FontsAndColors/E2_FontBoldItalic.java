package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Font;


public class E2_FontBoldItalic extends E2_Font implements IF_PdFonts
{


	   public E2_FontBoldItalic()
	   {
	        super(Font.BOLD+Font.ITALIC, 0);
	   }

	    
	   public E2_FontBoldItalic(int iDiffSizeToStandard)
	   {
	       super(Font.BOLD+Font.ITALIC,iDiffSizeToStandard);
	   }

	


}
