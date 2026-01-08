package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.ES__Select_EMAIL_SEND;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class ES_LIST_BasicModuleContainer extends Project_BasicModuleContainer 
{
	public static final String NAME_OF_CHECKBOX_IN_LIST =		"NAME_OF_CHECKBOX_IN_LIST";
	public static final String NAME_OF_LISTMARKER_IN_LIST =		"NAME_OF_LISTMARKER_IN_LIST";
	
	private String    				id_TABLE = 			null;
	private String    				basename_TABLE = 	null;
//	private ES__AttachementSeeker	attachMentSeeker =	null;   //2015-03-27: wird an die eMail-Maske uebergeben, damit dort zu bearbeitende eMail_Send-Masken
//	  															//            ermoeglicht wird, verschiedene anlagen anzuhaengen

	private boolean     			is_VisibleIn_AM_BasicContainer = false; 		
	
	
	private E2_NavigationList	naviList = 			null;
	
		
	public ES_LIST_BasicModuleContainer(String idTable, String basenameTable) throws myException
	{
		super(E2_MODULNAMES.EMAIL_SEND_LIST);
		
		this.id_TABLE = 		idTable;
		this.basename_TABLE = 	basenameTable;
		
		this.set_bVisible_Row_For_Messages(false);
		
		String cSQLWhere = null;
		if (S.isFull(this.id_TABLE) && S.isFull(this.basename_TABLE)) {
			cSQLWhere = _DB.EMAIL_SEND$ID_EMAIL_SEND+" IN ("+
					new ES__Select_EMAIL_SEND(_DB.Z_EMAIL_SEND$ID_EMAIL_SEND, this.basename_TABLE, this.id_TABLE)+")";
		}
		
		this.naviList = new E2_NavigationList();
		this.naviList.INIT_WITH_ComponentMAP(new ES_LIST_ComponentMap(cSQLWhere,this.basename_TABLE,this.id_TABLE),E2_NavigationList.STYLE__4_2_4_2,this.get_MODUL_IDENTIFIER());
		ES_LIST_BedienPanel oPanel = new ES_LIST_BedienPanel(this);
		this.add(oPanel);
		this.add(this.naviList, E2_INSETS.I_2_2_2_2);
		this.naviList._REBUILD_COMPLETE_LIST("");
	}


	public E2_NavigationList get_naviList() {
		return this.naviList;
	}

	public void refresh() throws myException {
		this.naviList._REBUILD_COMPLETE_LIST("");
	}
	

	public String get_id_TABLE() {
		return id_TABLE;
	}


	public String get_basename_TABLE() {
		return basename_TABLE;
	}
		
	
//	public ES__AttachementSeeker get_attachMentSeeker() {
//		return attachMentSeeker;
//	}


	public boolean is_Visible_in_AM_BasicContainer() {
		return is_VisibleIn_AM_BasicContainer;
	}


	public void set_Visible_in_AM_BasicContainer(boolean is_Visible_In_AM_BasicContainer) {
		this.is_VisibleIn_AM_BasicContainer = is_Visible_In_AM_BasicContainer;
	}


//	public void set_AttachMentSeeker(ES__AttachementSeeker attachmentSeeker) {
//		this.attachMentSeeker = attachmentSeeker;
//	}

	
}
