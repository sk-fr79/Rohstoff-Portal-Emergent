package panter.gmbh.indep.dataTools.RECORD2;

import java.math.BigDecimal;

import panter.gmbh.indep.exceptions.myException;

public interface IF_RecListSumBigDecimal {
	public BigDecimal bd_value(Rec20 rec) throws myException;
}
