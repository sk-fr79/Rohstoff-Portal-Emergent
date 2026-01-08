/*
 * Created on 07.08.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.Echo2.FontsAndColors;

import nextapp.echo2.app.Color;
import panter.gmbh.indep.bibALL;


public class E2_ColorBase extends Color
{

    public E2_ColorBase()
    {
        super(bibALL.get_iBaseColorRED(), bibALL.get_iBaseColorGREEN(), bibALL.get_iBaseColorBLUE());
    }

    public E2_ColorBase(int iDiff)
    {
        super(bibALL.get_iBaseColorRED(iDiff), bibALL.get_iBaseColorGREEN(iDiff), bibALL.get_iBaseColorBLUE(iDiff));
    }

}
