
package rohstoff.businesslogic.bewegung2.lager.selector;

import panter.gmbh.indep.exceptions.myException;

public interface IF_CanBeDeactivate {
	public void setEnabled(boolean isEnabled) throws myException;
}
