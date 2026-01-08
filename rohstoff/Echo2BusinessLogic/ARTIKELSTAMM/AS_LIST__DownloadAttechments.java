/**
 * rohstoff.Echo2BusinessLogic.ARTIKELSTAMM
 * @author martin
 * @date 25.02.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.ARTIKELSTAMM;

import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES.RANGE_KEY;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ShowAllDownloadsInList;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;

/**
 * @author martin
 * @date 25.02.2019
 *
 */
public class AS_LIST__DownloadAttechments extends E2_ShowAllDownloadsInList {


	/**
	 * @author martin
	 * @date 25.02.2019
	 *
	 * @throws myException
	 */
	public AS_LIST__DownloadAttechments() {
		super();
	}

	@Override
	public XX_ActionValidator generateDownloadValidator(E2_Button downButton) {
		return new E2_ButtonAUTHValidator(RANGE_KEY.ATTACHMENT_ARTIKEL.db_val(), VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DOWNLOAD_ATTACHMENT.db_val());
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		return new AS_LIST__DownloadAttechments();
	}

	
	
	
}
