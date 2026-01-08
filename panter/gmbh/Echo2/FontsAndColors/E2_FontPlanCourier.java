package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Font;
import panter.gmbh.indep.bibALL;


public class E2_FontPlanCourier extends Font implements IF_PdFonts
{

    public E2_FontPlanCourier()
    {
        super(Font.COURIER, Font.PLAIN, new Extent(bibALL.get_FONT_SIZE(),Extent.PT));
    }

    
    public E2_FontPlanCourier(int iDiffSizeToStandard)
    {
        super(Font.COURIER, Font.PLAIN, new Extent(bibALL.get_FONT_SIZE()+iDiffSizeToStandard,Extent.PT));
    }

}
