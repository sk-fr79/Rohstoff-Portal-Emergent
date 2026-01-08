/*
 * Created on 10.12.2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panterdt.common;

import java.io.File;
import java.io.FilenameFilter;

public class pdFileHelper
{

    
    /**
     * @author martin
     * Filterclass for defining patterns at names end 
     */
    public static class Filter implements FilenameFilter 
    {
        protected String pattern;
        public Filter (String str) 
        {
          pattern = str;
        }
	    public boolean accept (File dir, String name) 
	    {
	        if (this.pattern.equals("*.*"))
	            return true;                 // jede datei im pfad
	        
	        if (this.pattern.startsWith("*"))
	            this.pattern = this.pattern.substring(1);
	        
	        return name.toLowerCase().endsWith(pattern.toLowerCase());
        }
    }

    
    
    
}
