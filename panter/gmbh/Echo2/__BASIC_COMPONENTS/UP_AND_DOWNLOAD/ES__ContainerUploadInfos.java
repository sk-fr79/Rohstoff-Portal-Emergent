package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD;

import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES__AttachementSeeker;

public class ES__ContainerUploadInfos {
	
	private String 					ID_TABLE = null;
	private String	 				TABLE_BASENAME = null;
	private ES__AttachementSeeker 	attachmentSeeker = null;
	
	public ES__ContainerUploadInfos(String iD_TABLE, String tABLE_BASENAME, ES__AttachementSeeker attachmentSeeker) {
		super();
		this.ID_TABLE = iD_TABLE;
		this.TABLE_BASENAME = tABLE_BASENAME;
		this.attachmentSeeker = attachmentSeeker;
	}

	public String get_ID_TABLE() {
		return ID_TABLE;
	}

	public String get_TABLE_BASENAME() {
		return TABLE_BASENAME;
	}

	public ES__AttachementSeeker get_AttachmentSeeker() {
		return attachmentSeeker;
	}
			
	
	
}
