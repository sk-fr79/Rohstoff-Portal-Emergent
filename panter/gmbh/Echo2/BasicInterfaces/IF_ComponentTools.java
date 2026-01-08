package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;

@SuppressWarnings("unchecked")
public interface IF_ComponentTools<T>  extends IF__Reflections {
	

	/**
	 * 
	 * @param someThing
	 * @return
	 */
	public default T _setSomeThing(Object someThing) {
		if (this instanceof MyE2IF__Component) {
			MyE2EXT__Component EXT = ((MyE2IF__Component)this).EXT();
			EXT.set_O_PLACE_FOR_EVERYTHING(someThing);
		}
		return (T)this;
	}


	/**
	 * 
	 * @return
	 */
	public default Object getSomeThing() {
		if (this instanceof MyE2IF__Component) {
			MyE2EXT__Component EXT = ((MyE2IF__Component)this).EXT();
			return EXT.get_O_PLACE_FOR_EVERYTHING();
		}
		return null;
	}

	
}

	
	

