/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL
 * @author manfred
 * @date 12.06.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_ENUM_DB_MESSAGES_FILTER;
import panter.gmbh.Echo2.RB.BASICS.RB_K;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_ModuleContainerMASK;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen;
import panter.gmbh.Echo2.RB.CONTROLLERS.RB_actionStandardSaveAndReopen_Rec20;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.ES_bt_StartEmailSendung;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.ES_RB_DataobjectsCollector;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_LIST_bt_ListToMask;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.FULL_DAUGHTER.XX_FULL_DAUGHTER;
import panter.gmbh.basics4project.DB_ENUMS.BORDERCROSSING_ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForPrimaryKey;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.BOR_MASK_DataObjectCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;

/**
 * @author manfred
 * @date 12.06.2018
 *
 */
public class BOR_ART_FULLDAUGHTER extends RB_MaskDaughterSimple {

	private	E2_BasicModuleContainer			   	_parent = null;
	private RB_ComponentMap 					_compParent = null;
	private BOR_ART_LIST_BasicModuleContainer 	_artikel_inlay 	 = null;
	
	private String 								_actualIdUnformated = null;
	public RB__CONST.MASK_STATUS				_status_on_build = null; 
	
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;

	/**
	 * @author manfred
	 * @date 12.06.2018
	 *
	 * @param osqlField
	 * @throws myException 
	 */
	public BOR_ART_FULLDAUGHTER( E2_BasicModuleContainer _parentContainer,RB_ComponentMap componentMapParent ) throws myException {
		super();
		this._parent = _parentContainer;
		this._compParent = componentMapParent;
		
	    //zentrale hashmap fuer transport von infos
	    params = new PARAMHASH();

	    // Child-Grid aufbauen
	    this.initInlay();

	}

	/**
	 * baut den eingebetteten Container auf
	 * @author manfred
	 * @date 19.06.2018
	 *
	 * @throws myException
	 */
	private void initInlay() throws myException{
		_artikel_inlay = new BOR_ART_LIST_BasicModuleContainer(params);
		
		// grauer Rahmen 
        this.setBorder(new Border(0,new E2_ColorDDDark(),Border.STYLE_SOLID));
		this._a(_artikel_inlay);
	}



	

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#rb_set_belongs_to(java.lang.Object)
	 */
	@Override
	public void rb_set_belongs_to(RB_ComponentMap obj) throws myException {
		
		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#rb_get_belongs_to()
	 */
	@Override
	public RB_ComponentMap rb_get_belongs_to() throws myException {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#getMyKeyInCollection()
	 */
	@Override
	public RB_K getMyKeyInCollection() throws myException {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.IF.IF_RB_Part#setMyKeyInCollection(panter.gmbh.Echo2.RB.BASICS.RB_K)
	 */
	@Override
	public RB_K setMyKeyInCollection(RB_K key) throws myException {
		// TODO Auto-generated method stub
		return null;
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#set_bEnabled_For_Edit(boolean)
	 */
	@Override
	public void set_bEnabled_For_Edit(boolean enabled) throws myException {
		// TODO Auto-generated method stub
		if (_artikel_inlay != null){
			_artikel_inlay.set_bEnabled_For_Edit(enabled);
		}
	}
	



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_Neutral()
	 */
	@Override
	public void mark_Neutral() throws myException {
		// TODO Auto-generated method stub
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_MustField()
	 */
	@Override
	public void mark_MustField() throws myException {
		// TODO Auto-generated method stub
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_Disabled()
	 */
	@Override
	public void mark_Disabled() throws myException {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#mark_FalseInput()
	 */
	@Override
	public void mark_FalseInput() throws myException {
		// TODO Auto-generated method stub
		
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		//id rauskriegen:
		Long id = null;
		
		
		if (!dataObject.isStatusNew()){
			
			// falls der erste Aufruf ein StatusNEW hatte, muss man hier das innere Grid neu aufbauen
			if (_status_on_build != null && _status_on_build.equals(RB__CONST.MASK_STATUS.NEW)){
				this.removeAll();
				initInlay();
			}
			
			if ( dataObject instanceof RB_Dataobject_V1){
					  id = ((RB_Dataobject_V1)dataObject).get_RecORD().get_PRIMARY_KEY_VALUE();
					  this.rb_set_db_value_manual(""+id);
			
			} else if (dataObject instanceof RB_Dataobject_V2){
				if (((RB_Dataobject_V2)dataObject).get_rec20().is_ExistingRecord()) {
					id = new Long(((RB_Dataobject_V2)dataObject).get_rec20().getId());
					this.rb_set_db_value_manual(""+id);
				}
			}
		} else {
			
			_status_on_build = RB__CONST.MASK_STATUS.NEW;
			
			this.removeAll();
			
			// grauer Rahmen 
	        this.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
	        this._s(1);
			this._a( new MyE2_Label("Es muss zuerst der Eintrag für den Grenzübertritt gespeichert werden!"));
			
			MyE2_Button btnSave = new MyE2_Button("Speichern und weitermachen...");
			btnSave.add_oActionAgent(new oActionSaveAndResumeEdit());
			this._a(btnSave );
			
		}
	}



	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.MaskDaughter.RB_MaskDaughterSimple#rb_set_db_value_manual(java.lang.String)
	 */
	@Override
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {
		MyLong l = new MyLong(id_of_mothertable);
		String idUf = "";
		if (l.isOK()) {
			idUf = l.get_cUF_LongString();
		}
		
		// ID des parent-Datensatzes in die Paramterliste ablegen..
		this.params.put(BOR_ART_PARAMS.BOR_ART_ID_BORDERCROSSING, idUf);
		
		
		if (S.isEmpty(id_of_mothertable)) {
			this._clear();
		} else {
			this._actualIdUnformated=idUf;
			((SQLFieldForRestrictTableRange)_artikel_inlay.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().get_(BORDERCROSSING_ARTIKEL.id_bordercrossing.fn())).set_cRestrictionValue_IN_DB_FORMAT(idUf);
			_artikel_inlay.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
		}
	}

	
	
	
	
	/**
	 * Action Agent, der die Haupt-Maske speichert und damit Einträge in die Child-Table ermöglicht, ohne die Maske zu schliessen und wieder zu öffnen 
	 * @author manfred
	 * @date 03.07.2018
	 *
	 */
	private class oActionSaveAndResumeEdit extends RB_actionStandardSaveAndReopen_Rec20 {
		
		public oActionSaveAndResumeEdit() throws myException {
			super();
			this._set_leading_table_on_mask(_TAB.bordercrossing)._set_mask_key_main(new RB_KM(_TAB.bordercrossing));
		}


		@Override
		public RB_DataobjectsCollector generate_dataObjectsCollector_4_edit(
				String id_record,
				RB_ComponentMapCollector componentmapCollectorActual) throws myException {
			return new BOR_MASK_DataObjectCollector(id_record, RB__CONST.MASK_STATUS.EDIT);		
		}

		@Override
		public RB_ComponentMapCollector get_componentMapCollector() throws myException {
			BOR_ART_FULLDAUGHTER oThis = BOR_ART_FULLDAUGHTER.this;
 			return oThis._compParent.rb_get_belongs_to(); 
		}

		
	}
	
	
}
