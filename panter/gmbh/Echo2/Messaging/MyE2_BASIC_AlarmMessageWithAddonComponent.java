package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.MyString;

public class MyE2_BASIC_AlarmMessageWithAddonComponent extends MyE2_BASIC_MessageWithAddonComponent  implements IF_Message_WithButtons {

	public MyE2_BASIC_AlarmMessageWithAddonComponent(MyString cmessage, MyE2IF__Component oComponent, Extent oExtMessage, Extent oExtComponent) {
		super(MyE2_Message.TYP_ALARM, cmessage,  oComponent,  oExtMessage,  oExtComponent);
	}

	public MyE2_BASIC_AlarmMessageWithAddonComponent(MyString cmessage, MyE2IF__Component oComponent) {
		super(MyE2_Message.TYP_ALARM, cmessage, oComponent);
	}

	@Override
	public Color get_Color_4_MessageBackground(MyE2_Message oMessage) {
		return new E2_ColorAlarm();
	}


}
