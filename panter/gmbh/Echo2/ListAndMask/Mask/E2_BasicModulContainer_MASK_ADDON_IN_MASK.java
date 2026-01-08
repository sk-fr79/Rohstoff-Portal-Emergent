package panter.gmbh.Echo2.ListAndMask.Mask;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.indep.exceptions.myException;

public interface E2_BasicModulContainer_MASK_ADDON_IN_MASK {
	
	public abstract Vector<Component> generate_MaskComponents(E2_ComponentMAP  map) throws myException;
	
}
