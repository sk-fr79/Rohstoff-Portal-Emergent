package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.exceptions.myException;

public class Calculate_umbuchung_preis {
	
	private			MyInteger 		id_ladestation = 	null;
	private			MyInteger 		id_artikel = 		null;
	private			MyBigDecimal 	bd_menge = null;
	private			MyDate     		date = null;
	
	private			MyBigDecimal 	bd_preis = null;
	
	public Calculate_umbuchung_preis(MyDate p_date, MyInteger p_id_ladestation, MyInteger p_id_artikel, MyBigDecimal p_bd_menge) throws myException {
		super();
		this.date = 			p_date;
		this.id_ladestation = 	p_id_ladestation;
		this.id_artikel = 		p_id_artikel;
		this.bd_menge = 		p_bd_menge;
		
		
		if (this.date==null || this.id_ladestation==null || this.id_artikel==null || this.bd_menge==null) {
			throw new myException(this,"Null is not allowed !");
		}
		
		if (this.date.get_bOK() || this.id_ladestation.get_bOK() || this.id_artikel.get_bOK() || this.bd_menge.get_bOK()) {
			this.bd_preis = new MyBigDecimal("100");
		}
		
	}
	
	
	public MyBigDecimal get_price() throws myException {
		return this.bd_preis;
	}
	
	
}
