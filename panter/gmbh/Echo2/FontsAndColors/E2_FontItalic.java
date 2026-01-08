package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Font;


public class E2_FontItalic extends E2_Font implements IF_PdFonts
{

//    public E2_FontItalic()
//    {
//        super(new Font.Typeface("Arial"), Font.ITALIC, new Extent(bibALL.get_FONT_SIZE(bibE2.get_CurrSession()),Extent.PT));
//    }
//
//    
//    public E2_FontItalic(int iDiffSizeToStandard)
//    {
//        super(new Font.Typeface("Arial"), Font.ITALIC, new Extent(bibALL.get_FONT_SIZE(bibE2.get_CurrSession())+iDiffSizeToStandard,Extent.PT));
//    }

    public E2_FontItalic()
    {
        super( Font.ITALIC, 0);
    }

    
    public E2_FontItalic(int iDiffSizeToStandard)
    {
        super( Font.ITALIC, iDiffSizeToStandard);
    }
	
	
}
