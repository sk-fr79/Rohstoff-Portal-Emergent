package rohstoff.Echo2BusinessLogic.__SPECIALREC20;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_VPOS_STD extends Rec20{
	
	private Rec20  recVposStdAngebot = null;
	
	public Rec20_VPOS_STD(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	public Rec20_VPOS_STD() throws myException {
		super(_TAB.vpos_kon);
	}

	
	public Rec20 get_rec_vpos_std_ang() throws myException{
		if (this.recVposStdAngebot==null) {
			this.recVposStdAngebot=this.get_down_reclist20(VPOS_STD_ANGEBOT.id_vpos_std, "","").get(0);
		}
		return this.recVposStdAngebot;
	}


	
	/**
	 * 
	 * @param date
	 * @return s true, when date is in daterange of contract
	 * @throws myException
	 */
	public boolean isDateInKontaktRange(MyDate  testDate) throws myException {
		MyDate gueltig_von = this.get_rec_vpos_std_ang().get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_von);
		MyDate gueltig_bis = this.get_rec_vpos_std_ang().get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_bis);
		
		if (gueltig_von.isOK() && gueltig_bis.isOK() &&  testDate.isOK() ) {
			return (gueltig_von.isLessEqualThan(testDate) && gueltig_bis.isGreaterEqualThan(testDate));
		} else {
			throw new myException(this, "Only myDate-Objects which are ok allowed");
		}
	}
	
	
	
	
	
}