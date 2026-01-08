package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;

public class RECORD_USER_EXT extends RECORD_USER {

	public RECORD_USER_EXT(RECORD_USER recordOrig) {
		super(recordOrig);
	}

	public RECORD_USER_EXT(MyRECORD recordOrig) {
		super(recordOrig);
	}
	
	public Color  get_COLOR_OK_INDIVID() throws myException {
		Color oColor = Color.GREEN;
		
		if ( 	this.get_COLOR_MASK_OK_RED_lValue(-1l).intValue()!=-1 && 
				this.get_COLOR_MASK_OK_GREEN_lValue(-1l).intValue()!=-1 && 
				this.get_COLOR_MASK_OK_BLUE_lValue(-1l).intValue()!=-1) {
			oColor = new Color(	this.get_COLOR_MASK_OK_RED_lValue(0l).intValue(), 
								this.get_COLOR_MASK_OK_GREEN_lValue(0l).intValue(),
								this.get_COLOR_MASK_OK_BLUE_lValue(0l).intValue());
		}
		
		return oColor;
	}
	
	
	public Color  get_COLOR_WARN_INDIVID() throws myException {
		Color oColor = new E2_ColorHelpBackground();
		
		if ( 	this.get_COLOR_MASK_WARN_RED_lValue(-1l).intValue()!=-1 && 
				this.get_COLOR_MASK_WARN_GREEN_lValue(-1l).intValue()!=-1 && 
				this.get_COLOR_MASK_WARN_BLUE_lValue(-1l).intValue()!=-1) {
			oColor = new Color(	this.get_COLOR_MASK_WARN_RED_lValue(0l).intValue(), 
								this.get_COLOR_MASK_WARN_GREEN_lValue(0l).intValue(),
								this.get_COLOR_MASK_WARN_BLUE_lValue(0l).intValue());
		}
		
		return oColor;
	}
	
	public Color  get_COLOR_ERROR_INDIVID() throws myException {
		Color oColor = new E2_ColorAlarm();
		
		if ( 	this.get_COLOR_MASK_ERROR_RED_lValue(-1l).intValue()!=-1 && 
				this.get_COLOR_MASK_ERROR_GREEN_lValue(-1l).intValue()!=-1 && 
				this.get_COLOR_MASK_ERROR_BLUE_lValue(-1l).intValue()!=-1) {
		
			oColor = new Color(	this.get_COLOR_MASK_ERROR_RED_lValue(0l).intValue(), 
								this.get_COLOR_MASK_ERROR_GREEN_lValue(0l).intValue(),
								this.get_COLOR_MASK_ERROR_BLUE_lValue(0l).intValue());
		}
		
		return oColor;
	}
	
    /** 
     * 
     * @return vorname  name1 name2
     * @throws myException
     */
    public String get__vorname_name1_name2() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(USER.vorname.fn(), USER.name1.fn(), USER.name2.fn()));
    	return cRueck;
    }
  

    /** 
     * 
     * @return vorname  name1 name2
     * @throws myException
     */
    public String get__vorname_name1() throws myException {
    	String cRueck = this.get___KETTE(bibVECTOR.get_Vector(USER.vorname.fn(), USER.name1.fn()));
    	return cRueck;
    }
  


}
