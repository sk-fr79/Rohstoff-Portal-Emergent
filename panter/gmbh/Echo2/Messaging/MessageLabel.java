package panter.gmbh.Echo2.Messaging;

import nextapp.echo2.app.layout.ColumnLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorAlarm;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorEditBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_LabelWrap;

public class MessageLabel extends MyE2_LabelWrap
{
	private MyE2_Message  SourceMessage = null;

	public MessageLabel(MyE2_Message sourceMessage)
	{
		super(sourceMessage.get_cMessage());
		
		this.SourceMessage = sourceMessage;
		
		ColumnLayoutData oCellInfo = new ColumnLayoutData();
		ColumnLayoutData oCellWarning = new ColumnLayoutData();
		ColumnLayoutData oCellAlarm = new ColumnLayoutData();

		oCellInfo.setBackground(new E2_ColorEditBackground());
		oCellWarning.setBackground(new E2_ColorHelpBackground());
		oCellAlarm.setBackground(new E2_ColorAlarm());

		oCellInfo.setBackground(new E2_ColorEditBackground());
		oCellWarning.setBackground(new E2_ColorHelpBackground());
		oCellAlarm.setBackground(new E2_ColorAlarm());
		oCellInfo.setInsets(E2_INSETS.I_2_2_2_2);
		oCellWarning.setInsets(E2_INSETS.I_2_2_2_2);
		oCellAlarm.setInsets(E2_INSETS.I_2_2_2_2);

		if (sourceMessage.get_iType() == MyE2_Message.TYP_ALARM)
		{
			this.setLayoutData(oCellAlarm);
		} 
		else if (sourceMessage.get_iType() == MyE2_Message.TYP_WARNING)
		{
			this.setLayoutData(oCellWarning);
		} 
		else if (sourceMessage.get_iType() == MyE2_Message.TYP_INFO)
		{
			this.setLayoutData(oCellInfo);
		}

		
		if (SourceMessage.get_cMessage().CTrans().length() > 60)
			this.setFont(new E2_FontPlain(-1));   // wenn zu breit, dann
													// schrift kleiner

	}

	public MyE2_Message get_SourceMessage()
	{
		return SourceMessage;
	}
	
	
}
