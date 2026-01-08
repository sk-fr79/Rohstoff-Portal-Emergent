package panter.gmbh.basics4project;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Label;

public class DummyBasicContainer extends E2_BasicModuleContainer {

	public DummyBasicContainer() {
		super();
		
		this.add(new MyE2_Label(new MyE2_String("Es ist ein Fehler passiert. Das gewünschte Modul ist nicht mehr verfügbar."),
				new E2_FontBold(20)));
	}

}
