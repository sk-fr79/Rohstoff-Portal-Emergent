package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Font;


public class E2_FontPlain extends E2_Font implements IF_PdFonts
{

    public E2_FontPlain()
    {
        //super(new Font.Typeface("Verdana"), Font.PLAIN, new Extent(bibALL.get_FONT_SIZE(bibE2.get_CurrSession()),Extent.PT));
        super( Font.PLAIN,0);
    }

    
    public E2_FontPlain(int iDiffSizeToStandard)
    {
        //super(new Font.Typeface("Verdana"), Font.PLAIN, new Extent(bibALL.get_FONT_SIZE(bibE2.get_CurrSession())+iDiffSizeToStandard,Extent.PT));
        super(Font.PLAIN, iDiffSizeToStandard);
    }

}
