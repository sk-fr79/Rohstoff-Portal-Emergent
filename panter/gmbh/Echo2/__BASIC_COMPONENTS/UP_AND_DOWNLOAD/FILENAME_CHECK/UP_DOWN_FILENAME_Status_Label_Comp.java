package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.FILENAME_CHECK;

import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.components.MyE2_Label;

public class UP_DOWN_FILENAME_Status_Label_Comp extends MyE2_Label {

	public UP_DOWN_FILENAME_Status_Label_Comp() {
		super("");
		this.setFont(new E2_FontBoldItalic(-1));
		
		this.setStatus(RENAME_STATUS.UNCORRECT);
	}

	public void setStatus(RENAME_STATUS status){
		this.setForeground(status.getTextColor());
		this.setIcon(status.getIcon());
	}
}
