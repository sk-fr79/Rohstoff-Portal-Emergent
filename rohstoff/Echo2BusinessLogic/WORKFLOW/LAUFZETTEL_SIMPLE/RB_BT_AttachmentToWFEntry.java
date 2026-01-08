/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE
 * @author manfred
 * @date 23.04.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachments;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;

/**
 * @author manfred
 * @date 23.04.2019
 *
 */
public class RB_BT_AttachmentToWFEntry extends E2_ButtonAttachments implements IF_RB_Component
{

	private RB_KF           rb_Key = null;
	private MyLong 			l_idLaufzettelEintrag = null;
	
	private String 			sTableID = null;
	
	/**
	 * @author manfred
	 * @date 23.04.2019
	 *
	 */
	public RB_BT_AttachmentToWFEntry() {
		super();
	}

	public void initButton(String TableID) {
		sTableID = TableID;
		l_idLaufzettelEintrag = new MyLong(TableID);

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
	public void rb_set_db_value_manual(String valueFormated)	throws myException {
		
	}

	@Override
	public RB_KF rb_KF() throws myException {
		return this.rb_Key;
	}

	@Override
	public void set_rb_RB_K(RB_KF key) throws myException {
		this.rb_Key=key;
	}

	@Override
	public RB_ComponentMap rb_ComponentMap_this_belongsTo() throws myException {
		return (RB_ComponentMap)this.EXT().get_oComponentMAP();
	}


//	/* (non-Javadoc)
//	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#rb_set_belongs_to(java.lang.Object)
//	 */
//	@Override
//	public void rb_set_belongs_to(RB_ComponentMap obj) throws myException {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//	/* (non-Javadoc)
//	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#rb_get_belongs_to()
//	 */
//	@Override
//	public RB_ComponentMap rb_get_belongs_to() throws myException {
//		// TODO Auto-generated method stub
//		return null;
//	}


//	/* (non-Javadoc)
//	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#getMyKeyInCollection()
//	 */
//	@Override
//	public RB_K getMyKeyInCollection() throws myException {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	/* (non-Javadoc)
//	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#setMyKeyInCollection(panter.gmbh.Echo2.RB.BASICS.RB_K)
//	 */
//	@Override
//	public RB_K setMyKeyInCollection(RB_K key) throws myException {
//		return null;
//	}

	@Override
	public Long getIdTableForAttachment() throws myException {
		return l_idLaufzettelEintrag.get_oLong();
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachments#getTableForAttachment()
	 */
	@Override
	public _TAB getTableForAttachment() throws myException {
		return _TAB.laufzettel_eintrag;
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachments#getModule()
	 */
	@Override
	public MODUL getModule() throws myException {
		return WF_SIMPLE_CONST.TRANSLATOR.MASK.get_modul();
	}


	
}
