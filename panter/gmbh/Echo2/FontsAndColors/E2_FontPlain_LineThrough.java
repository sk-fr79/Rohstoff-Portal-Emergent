package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Font;


public class E2_FontPlain_LineThrough extends E2_Font implements IF_PdFonts
{

    public E2_FontPlain_LineThrough()
    {
        //super(new Font.Typeface("Verdana"), Font.PLAIN, new Extent(bibALL.get_FONT_SIZE(bibE2.get_CurrSession()),Extent.PT));
        super(Font.LINE_THROUGH,0);
    }

    
    public E2_FontPlain_LineThrough(int iDiffSizeToStandard)
    {
        //super(new Font.Typeface("Verdana"), Font.PLAIN, new Extent(bibALL.get_FONT_SIZE(bibE2.get_CurrSession())+iDiffSizeToStandard,Extent.PT));
        super(Font.LINE_THROUGH, iDiffSizeToStandard);
    }

}
