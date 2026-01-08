package rohstoff.Echo2BusinessLogic.__SPECIALRECORDS;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_SCHABLONE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;

public class RECORD_MANDANT_ext extends RECORD_MANDANT {

	public RECORD_MANDANT_ext(RECORD_MANDANT recordOrig) {
		super(recordOrig);
	}

	public RECORD_MANDANT_ext() throws myException {
		super();
	}

	public RECORD_MANDANT_ext(long lID_Unformated, MyConnection Conn) throws myException {
		super(lID_Unformated, Conn);
	}

	public RECORD_MANDANT_ext(long lID_Unformated) throws myException {
		super(lID_Unformated);
	}

	public RECORD_MANDANT_ext(MyConnection Conn) throws myException {
		super(Conn);
	}

	public RECORD_MANDANT_ext(MyRECORD recordOrig) {
		super(recordOrig);
	}

	public RECORD_MANDANT_ext(String c_ID_or_WHEREBLOCK_OR_SQL,	MyConnection Conn) throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL, Conn);
	}

	public RECORD_MANDANT_ext(String c_ID_or_WHEREBLOCK_OR_SQL)	throws myException {
		super(c_ID_or_WHEREBLOCK_OR_SQL);
	}
	

	public RECORD_MANDANT_ext(BASIC_RECORD_MANDANT recordOrig) throws myException  {	
		this.set_oConn(recordOrig.get_oConn());
		this.putAll(recordOrig);
		this.get_hm_FieldMetaDefs().putAll(recordOrig.get_hm_FieldMetaDefs());
		this.set_cSQL_4_Build(recordOrig.get_cSQL_4_Build());
	}


	/**
	 * 
	 * @param profil
	 * @return RECORD_EMAIL_SEND_SCHABLONE witch is corresponding to profil or null
	 * @throws myException
	 */
	public RECORD_EMAIL_SEND_SCHABLONE  get_mandant_mailSchablone(MANDANT_CONST.MAILPROFILE profil) throws myException {
		RECORD_EMAIL_SEND_SCHABLONE recMP = null;
		RECLIST_EMAIL_SEND_SCHABLONE  rlSchablone = new RECLIST_EMAIL_SEND_SCHABLONE(new SELECT("*").from(_DB.EMAIL_SEND_SCHABLONE));

		DEBUG.System_println("zahl: "+rlSchablone.get_vKeyValues().size());
		
		for (RECORD_EMAIL_SEND_SCHABLONE recSchab: rlSchablone) {
			if (recSchab.get_KENNUNG_MAILVERSAND_cUF_NN("").equals(profil.get_DB_Value())) {
				recMP = recSchab;
			}
		}
		return recMP;
	}
	
	
	
	
	
	public Color  get_COLOR_BACKTEXT() throws myException {
		Color ret  = Color.BLACK;
		
		long lColorRed = this.get_COLOR_BACKTEXT_RED_lValue(-1l).longValue();
		long lColorGreen = this.get_COLOR_BACKTEXT_GREEN_lValue(-1l).longValue();
		long lColorBlue = this.get_COLOR_BACKTEXT_BLUE_lValue(-1l).longValue();
		
		
		if (lColorRed>255) {lColorRed=0;}
		if (lColorGreen>255) {lColorGreen=0;}
		if (lColorBlue>255) {lColorBlue=0;}
		
		
		if (lColorRed>-1 && lColorGreen>-1 && lColorBlue>-1) {
			ret = new Color((int)lColorRed,(int)lColorGreen, (int)lColorBlue);
		}
		
		return ret;
	}
	
	
	
	public Color  get_COLOR() throws myException {
		Color ret  = Color.BLACK;
		
		long lColorRed = this.get_COLOR_RED_lValue(-1l).longValue();
		long lColorGreen = this.get_COLOR_GREEN_lValue(-1l).longValue();
		long lColorBlue = this.get_COLOR_BLUE_lValue(-1l).longValue();
		
		
		if (lColorRed>255) {lColorRed=0;}
		if (lColorGreen>255) {lColorGreen=0;}
		if (lColorBlue>255) {lColorBlue=0;}
		
		
		if (lColorRed>-1 && lColorGreen>-1 && lColorBlue>-1) {
			ret = new Color((int)lColorRed,(int)lColorGreen, (int)lColorBlue);
		}
		
		return ret;
	}
	
	public Color  get_COLOR_MASK_HIGHLIGHT() throws myException {
		Color ret  = Color.BLACK;
		
		long lColorRed = this.get_COLOR_MASK_HIGHLIGHT_RED_lValue(-1l).longValue();
		long lColorGreen = this.get_COLOR_MASK_HIGHLIGHT_GREEN_lValue(-1l).longValue();
		long lColorBlue = this.get_COLOR_MASK_HIGHLIGHT_BLUE_lValue(-1l).longValue();
		
		
		if (lColorRed>255) {lColorRed=0;}
		if (lColorGreen>255) {lColorGreen=0;}
		if (lColorBlue>255) {lColorBlue=0;}
		
		
		if (lColorRed>-1 && lColorGreen>-1 && lColorBlue>-1) {
			ret = new Color((int)lColorRed,(int)lColorGreen, (int)lColorBlue);
		}
		
		return ret;
	}

	
	
	public Color  get_COLOR_POPUP_TITEL() throws myException {
		Color ret  = Color.BLACK;
		
		long lColorRed = this.get_COLOR_POPUP_TITEL_RED_lValue(-1l).longValue();
		long lColorGreen = this.get_COLOR_POPUP_TITEL_GREEN_lValue(-1l).longValue();
		long lColorBlue = this.get_COLOR_POPUP_TITEL_BLUE_lValue(-1l).longValue();
		
		
		if (lColorRed>255) {lColorRed=0;}
		if (lColorGreen>255) {lColorGreen=0;}
		if (lColorBlue>255) {lColorBlue=0;}
		
		
		if (lColorRed>-1 && lColorGreen>-1 && lColorBlue>-1) {
			ret = new Color((int)lColorRed,(int)lColorGreen, (int)lColorBlue);
		}
		
		return ret;
	}


}
