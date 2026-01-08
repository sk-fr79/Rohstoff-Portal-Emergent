package rohstoff.businesslogic.bewegung2.list.listSelector;

import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public interface IF_CanBePopulated_NG
{
	public void populate(VEK<String> cID_ADRESSE) throws myException;
}
