package rohstoff.Echo2BusinessLogic.FIRMENSTAMM._SELECTOR;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.ZusatzFelder.E2_ListSelektor_ZUSATZFELDER;
import panter.gmbh.indep.exceptions.myException;

/**
 * 2012-05-11: neuer selektor, basierend auf den freien listenfeldern
 * @author martin
 *
 */
public class FS_ListSelektorZusatzFelder extends E2_ListSelektor_ZUSATZFELDER
{

	public FS_ListSelektorZusatzFelder(E2_ComponentMAP o_ComponentMAP_REF, String c_MODULKENNER_LIST) throws myException
	{
		super(o_ComponentMAP_REF, c_MODULKENNER_LIST);
	}

	@Override
	public E2_BasicModuleContainer get_E2_BasicContainer4Popup() throws myException
	{
		return new ownE2_BasicContainer();
	}
	
	
	private class ownE2_BasicContainer extends E2_BasicModuleContainer
	{
		public ownE2_BasicContainer()
		{
			super();
		}
	}

	
}