package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS;

import java.util.ArrayList;

import panter.gmbh.indep.exceptions.myException;

public abstract class BorderCrossingInfoCollector {
	public abstract ArrayList<BorderCrossingInfo> get_al_BorderCrossingInfo() throws myException;
}
