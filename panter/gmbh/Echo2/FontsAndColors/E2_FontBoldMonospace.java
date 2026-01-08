package panter.gmbh.Echo2.FontsAndColors;

import panter.gmbh.indep.bibALL;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;

public class E2_FontBoldMonospace extends Font  implements IF_PdFonts
{
    public E2_FontBoldMonospace(int iDiffSizeToStandard)
    {
        super(	Font.MONOSPACE, 
        		Font.BOLD, 
        		new Extent(bibALL.get_FONT_SIZE()+iDiffSizeToStandard,Extent.PT));
    }
}
