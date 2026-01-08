/**
 * 
 */
package panter.gmbh.Echo2.RB.COMP.MaskDaughter;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.XX_SearchAddonBedingung;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V22;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public abstract class RB_MaskDaughterBasedOnBasicModulContainer extends RB_MaskDaughterSimple {


	//listcontainer uebergeben
	private E2_BasicModuleContainer m_listContainer = null;
	private String 					m_actualIdUnformated = null;
	
	/**
	 * 
	 * @return link to mother-table (in normal it is a ID)  !! MUST be in ListSQLFieldmap 
	 */
	public abstract IF_Field        getLinkfieldToMotherTable();   
	
	
	// die suchkomponenten muss hier bekannt sein, damit die zusatzsuchbedingung erfasst werden kann, wenn die mother-id uebergeben wird
	public abstract E2_DataSearch   getSearcher();
	
	
	//die TransportHashmap
    private RB_TransportHashMap   m_tpHashMapDaughterModule = null;
	
	/**
	 * @param listContainer
	 */
	public RB_MaskDaughterBasedOnBasicModulContainer() {
		super();
	}
	
	public RB_MaskDaughterBasedOnBasicModulContainer _init(E2_BasicModuleContainer listContainer) throws myException  {
		
		this.m_listContainer = listContainer;
		
		//jetzt nachsehen, ob in der sqlfieldmap ein korrectes restrict-field vorhanden ist, wenn nein, einfuegen
		if (	this.m_listContainer!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded()!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF()!=null 
				&& this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP()!=null) {
		
			SQLFieldMAP fm = this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP();
			IF_Field f = this.getLinkfieldToMotherTable();
			SQLField sf = fm.get(f.fn());
			MyE2IF__DB_Component  comp = this.m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get__DBComp(f.fn());
			
			if (sf != null && comp != null) {
				if (!(sf instanceof SQLFieldForRestrictTableRange)) {
					//dann das feld erzeugen, in sqlfieldmap einfuegen, initialisieren und der komponente unterschieben
					SQLFieldForRestrictTableRange sfr = new SQLFieldForRestrictTableRange(f.tn(), f.fn(), f.fn(), S.ms(f.fn()), "", bibE2.get_CurrSession());
					fm.add_SQLField(sfr,true);
					fm.initFields();
					comp.EXT_DB().set_oSQLField(sfr);
				}
			}
		}
		
		this._setSize(new Extent(100, Extent.PERCENT));
		this._a(this.m_listContainer);

		//sonderbedingung in der Suche
		if (this.getSearcher() != null) {
			this.getSearcher().add_XX_SearchAddonBedingung_to_all_SearchDefinitions(new SearchRestriction());
		}
		
		
		return this;
	}
	

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		//id rauskriegen:
		//falls bestehende id und der maskstatus ist copy, auch leermachen und disablen
		Long id = null;
		if (dataObject instanceof RB_Dataobject_V21 || dataObject instanceof RB_Dataobject_V22 ) {
			if ( ((Rec20)dataObject).is_ExistingRecord() && ((RB_Dataobject)dataObject).rb_MASK_STATUS()!=MASK_STATUS.NEW_COPY) {
				id = new Long(((Rec21) dataObject).getId());
				this.rb_set_db_value_manual("" + id);
			} else {
				this.rb_set_db_value_manual("");
			}
		} else {
			if (((RB_Dataobject_V2)dataObject).get_rec20().is_ExistingRecord() && (dataObject.rb_MASK_STATUS()!=MASK_STATUS.NEW_COPY)) {
				id = new Long(((RB_Dataobject_V2)dataObject).get_rec20().getId());
				this.rb_set_db_value_manual(""+id);
			} else {
				this.rb_set_db_value_manual("");
			}
		}
	}

	@Override
	public void rb_set_db_value_manual(String id_of_mothertable) throws myException {
		MyLong l = new MyLong(id_of_mothertable);
		String idUf = "";
		if (l.isOK()) {
			idUf = l.get_cUF_LongString();
		} else {
			if (S.isFull(id_of_mothertable)) {
				throw new myException("Value for mother-ID must be a type integer or long");
			}
		}
		
		this.m_tpHashMapDaughterModule._setMotherKeyLookupField(this.getLinkfieldToMotherTable());
		
		if (S.isEmpty(id_of_mothertable)) {
			this.m_tpHashMapDaughterModule._setMotherKeyValue(null);

			
			this._clear();
			this.m_actualIdUnformated=null;
			this._a(new RB_lab(S.ms("Bitte speichern Sie zuerst den aktuellen Datensatz ..."))._fsa(4), new RB_gld()._left_mid()._ins(5));
		} else {
			this._clear();
			this._setSize(new Extent(100, Extent.PERCENT));
			this._a(this.m_listContainer);

			this.m_actualIdUnformated=idUf;
			((SQLFieldForRestrictTableRange)m_listContainer.get_oNaviListFirstAdded().get_oComponentMAP__REF().get_oSQLFieldMAP().get_(this.getLinkfieldToMotherTable().fieldName())).set_cRestrictionValue_IN_DB_FORMAT(idUf);
			m_listContainer.get_oNaviListFirstAdded()._REBUILD_COMPLETE_LIST("");
			
			
			//jetzt in der mask-componentmap eine fieldrestriction auf das motherfeld
			if (this.m_tpHashMapDaughterModule == null) {
				throw new myException("In maskdaughter is no transporthashmap set !");
			}
			
			//damit die feldbeschraenkungen innerhalb der tochterkomponente funktionieren
			this.m_tpHashMapDaughterModule._setMotherKeyValue(l.get_oLong());
			
		}
	}

	public String getActualIdUnformated() {
		return m_actualIdUnformated;
	}

	
	private class SearchRestriction extends XX_SearchAddonBedingung {
		@Override
		public String get_AddOnBedingung() throws myException {
			
			RB_MaskDaughterBasedOnBasicModulContainer oThis = RB_MaskDaughterBasedOnBasicModulContainer.this;
			
			if (S.isFull(oThis.m_actualIdUnformated)) {
				return oThis.getLinkfieldToMotherTable().tnfn()+"="+oThis.m_actualIdUnformated;
			} else {
				return "1=2";
			}
		}
	}


	public RB_TransportHashMap getTransportHashMap() {
		return m_tpHashMapDaughterModule;
	}


	public RB_MaskDaughterBasedOnBasicModulContainer _setTransportHashMap(RB_TransportHashMap p_tpHashMap) {
		this.m_tpHashMapDaughterModule = p_tpHashMap;
		return this;
	}
}
