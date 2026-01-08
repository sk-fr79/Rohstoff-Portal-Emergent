/*
 * Created on 14.06.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep.maggie;

import java.util.Locale;


public class DotFormatterGermanFixed extends DotFormatter
{

    /**
     * @param sOriginalString
     */
    public DotFormatterGermanFixed(String sOriginalString)
    {
        super(sOriginalString, 0, Locale.GERMAN, true, 3,false);
    }

    public DotFormatterGermanFixed(String sOriginalString,boolean bStartEvalInConstructor)
    {
        super(sOriginalString, 0, Locale.GERMAN, true, 3,bStartEvalInConstructor);
    }

}
