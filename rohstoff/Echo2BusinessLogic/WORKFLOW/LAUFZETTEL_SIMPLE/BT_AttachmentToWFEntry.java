package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;

public class BT_AttachmentToWFEntry extends E2_ButtonAttachmentUseInListRow {

	
	String sTableID = null;
	public BT_AttachmentToWFEntry() {
		super();
	}
	
	public void initButton(String TableID) {
		sTableID = TableID;

		try {
			RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(LAUFZETTEL_EINTRAG.baseTabName(), sTableID, null,null);
			if (rlArchivmedienNachricht.size() > 0){
				this.__setImages(E2_ResourceIcon.get_RI("attach_mini_green.png"), E2_ResourceIcon.get_RI("leer.png"));
			}
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
//		// TODO Auto-generated method stub
//		return super.get_Copy(objHelp);
		BT_AttachmentToWFEntry btCopy = new BT_AttachmentToWFEntry();
		btCopy.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(btCopy));
		return btCopy;
	}


}
