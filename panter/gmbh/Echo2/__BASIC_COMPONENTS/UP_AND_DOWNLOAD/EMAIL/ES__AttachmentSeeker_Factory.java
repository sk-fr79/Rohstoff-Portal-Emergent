package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.indep.exceptions.myException;


public class ES__AttachmentSeeker_Factory {

	public static ES__AttachementSeeker get_Seeker(String TableBaseName, String ID_Table) throws myException {
		
		if (TableBaseName.equals("VKOPF_RG")) {
			ES__AttachementSeeker_RG seeker_Rg =  new ES__AttachementSeeker_RG();
			seeker_Rg.init_with_archivInfos(TableBaseName, ID_Table);
			return seeker_Rg;
		} else {
			return null;
		}
		
		
	}
	
}
