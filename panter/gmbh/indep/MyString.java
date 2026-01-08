package panter.gmbh.indep;

import panter.gmbh.basics4project.myTranslator;


/**
 * String-Wrapper mit zwei stringwerten, einem originalen
 * und einem der evtl. in eine andere sprache uebersetzt ist
 *
 */
public class MyString implements Cloneable
{
    private String 			cOrig = 	null;
    private String 			cTrans = 	null;
    private myTranslator 	oTrans = 	null;
    
    
    public MyString()
    {
    	this.cOrig = "";
    	this.oTrans = bibALL.get_TRANSLATOR();
    	this.cTrans = "";
    }
    
    
    public MyString(String cUntranslated)
    {
    	this.cOrig = cUntranslated;
    	this.oTrans = bibALL.get_TRANSLATOR();
    	this.cTrans = this.oTrans.translate(this.cOrig);
    }
    
 
    public MyString(String cUntranslated, boolean bTranslate)
    {
    	this.cOrig = cUntranslated;
    	
    	this.oTrans = bibALL.get_TRANSLATOR();
    	
    	
    	if (bTranslate)
    		this.cTrans = this.oTrans.translate(this.cOrig);
    	else
    		this.cTrans = cUntranslated;
    }
 
    
    public void addUnTranslated(String cString)
    {
        this.cOrig = this.cOrig+cString;
        this.cTrans = this.cTrans+cString;
    }

    
    public void addTranslated(String cString)
    {
    	this.addString(new MyString(cString));
    }

    
    public void addUnTranslatedInFront(String cString)
    {
        this.cOrig = cString+this.cOrig;
        this.cTrans = cString+this.cTrans;
    }

    
    public void addString(MyString oString)
    {
        this.cOrig += oString.COrig();
        this.cTrans += oString.CTrans();
    }
    
    
    /**
     * @return Returns the cOrig.
     */
    public String COrig()
    {
        return cOrig;
    }
    /**
     * @return Returns the cTrans.
     */
    public String CTrans()
    {
        return cTrans;
    }
    
    
    public String toString()
    {
    	return cTrans;
    }
    
    
    public Object Clone()
    {
        MyString oString = new MyString(this.cOrig);
        return oString;
    }
    
}

