package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SELEKTOREN;

import panter.gmbh.indep.exceptions.myException;

public interface IF_CanBePopulated
{
	public void populate(String cID_ADRESSE) throws myException;
}
