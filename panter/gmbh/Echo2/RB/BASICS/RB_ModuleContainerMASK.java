package panter.gmbh.Echo2.RB.BASICS;

import java.util.LinkedHashMap;
import java.util.Vector;

import echopointng.Separator;
import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.RowLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.ADDING_FieldLoggingButtons;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.E2_BasicModuleContainer_MASK;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.IF.IF_Container4Visualisation;
import panter.gmbh.Echo2.RB.IF.IF_RB_Collector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_ERSETZUNGS_HASH_STD;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_OWN_RECLIST_MAIL_AUS_MASK;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF.MMC_StartMail_PopUP;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FIELD_RULE_MODULEKENNER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_FIELD_RULE_MODULEKENNER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.query.SELECT;
import panter.gmbh.indep.exceptions.myException;

public class RB_ModuleContainerMASK extends E2_BasicModuleContainer_MASK implements IF_RB_Collector<RB_ComponentMapCollector>, IF_Container4Visualisation{

	private E2_MODULNAME_ENUM.MODUL 				ownKENNER = null;
  										
	private LinkedHashMap<RB_K, RB_ComponentMapCollector>		hm_component_map_collector = 	new LinkedHashMap<>();

	
	public RB_ModuleContainerMASK() {
		super();
	}


	@Override
	public LinkedHashMap<RB_K, RB_ComponentMapCollector> rb_hm_component_map_collector() {
		return hm_component_map_collector;
	}


	
	
	@Override
	public RB_ComponentMapCollector rb_ComponentMapCollector(RB_K registrationKey) 	{
		return hm_component_map_collector.get(registrationKey);
	}
	
	@Override
	public RB_ComponentMapCollector rb_FirstComponentMapCollector() throws myException{
		for (RB_ComponentMapCollector col: this.hm_component_map_collector.values()) {
			return col;
		}
		throw new myException(this,"No Collector found!");
	}
	
	/**
	 * 
	 * @return the only RB_ComponentMapCollector when there is just one, otherwise exception
	 * @throws myException
	 */
	@Override
	public RB_ComponentMapCollector rb_FirstAndOnlyComponentMapCollector() throws myException{
		if (this.hm_component_map_collector.size()!=1) {
			throw new myException(this,"No Singular RB_ComponentMapCollector");
		}
		for (RB_ComponentMapCollector col: this.hm_component_map_collector.values()) {
			return col;
		}
		throw new myException(this,"No Collector found!");
	}
	
	
	
	@Override
	public RB_ComponentMapCollector registerComponent(RB_K dummyKey, RB_ComponentMapCollector component_map_collector) throws myException {
		if (this.hm_component_map_collector.containsKey(dummyKey)) {
			throw new myException(this,"Registrationkey is double !!");
		} else {
			this.hm_component_map_collector.put(dummyKey, component_map_collector);
		}
		component_map_collector.rb_set_belongs_to(this);

		//2016-07-19: einheitliche registrierung
		component_map_collector.setMyKeyInCollection(dummyKey);

		
		return component_map_collector;
	}
	

	
	
	/**
	 * registrierung ohne dummy-key, dann wird einer erzeugt (immer eindeutig)
	 * @param component_map_collector
	 * @return
	 * @throws myException
	 */
	public RB_ComponentMapCollector rb_register(RB_ComponentMapCollector component_map_collector) throws myException {
		RB_MASK_DUMMY_KEY  key = new RB_MASK_DUMMY_KEY();
		return this.registerComponent(key, component_map_collector);
	}
	


	

	/**
	 * removes key if key exists and gives back the removed RB_ComponentMapCollector (or null when not existing)
	 * @param key_2_remove
	 * @return
	 * @throws myException
	 */
	public RB_ComponentMapCollector rb_remove(RB_K key_2_remove) throws myException {
		RB_ComponentMapCollector removed_collector = null;
		
		if (this.hm_component_map_collector.containsKey(key_2_remove)) {
			removed_collector = this.hm_component_map_collector.get(key_2_remove);
			this.hm_component_map_collector.remove(key_2_remove);
		}
		
		removed_collector.rb_set_belongs_to(null);
		return removed_collector;
	}
	

	
	
	
	/**
	 * 
	 * @param maskKENNER
	 * @param componentWithMaskElements
	 * @param bShowSeparator
	 * @throws myException
	 */
	public void rb_INIT(E2_MODULNAME_ENUM.MODUL maskKENNER,  IF_BaseComponent4Mask  componentWithMaskElements, boolean bShowSeparator) throws myException {
		this.rb_set_OwnKENNER(maskKENNER);
		
		if (!this.rb_get_OwnKENNER().is_RB_MASK()) {
			throw new myException(new MyE2_String("Falscher Maskentyp ...Nur Type RB_MASK erlaubt").CTrans());
		}
		
		this.set_MODUL_IDENTIFIER(this.rb_get_OwnKENNER().get_callKey());
		
		//hier wird bei admins geprueft, ob die maske bei den modulkennern fuer die angabe von feldregeln vorhanden ist, wenn nein, dann reinschreiben
		if (bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES() || bibALL.get_RECORD_USER().is_DEVELOPER_YES()) {
			SELECT sel = new SELECT("*").from(_DB.FIELD_RULE_MODULEKENNER).
					where(_DB.FIELD_RULE_MODULEKENNER$MODULEKENNER, this.rb_get_OwnKENNER().get_callKey()).
					and(_DB.FIELD_RULE_MODULEKENNER$MODULTYP,"MASK");
			
			RECLIST_FIELD_RULE_MODULEKENNER rl = new RECLIST_FIELD_RULE_MODULEKENNER(sel);
			if (rl.size()==0) {
				RECORDNEW_FIELD_RULE_MODULEKENNER recNew = new RECORDNEW_FIELD_RULE_MODULEKENNER();
				recNew.set_NEW_VALUE_MODULEKENNER(this.rb_get_OwnKENNER().get_callKey());
				recNew.set_NEW_VALUE_MODULTYP("MASK");
				if (recNew.do_WRITE_NEW_FIELD_RULE_MODULEKENNER(null)!=null) {
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(
									"Modulkenner <",true,
									this.rb_get_OwnKENNER().get_callKey(),false,
									"> wurde geschrieben!",true)));
				}
			}
			
//			DEBUG.System_println(sel.toString());
			
		}
		
		this.set_IF_BaseComponent4Mask(componentWithMaskElements);
		
		Vector<E2_ComponentMAP> vMaps = new Vector<E2_ComponentMAP>();
		
		//wenn der modulcontainer ein einen RB_ComponentMapCollector gebunden ist, dann hier registrieren
		//hilft, die alten methoden zu nutzen
		for (RB_ComponentMapCollector coll: this.hm_component_map_collector.values()) {
			for (RB_ComponentMap mask: coll) {
				if (mask instanceof E2_ComponentMAP) {
					vMaps.add((E2_ComponentMAP)mask);
				}
			}
		}
		this.get_vCombinedComponentMAPs().addAll(vMaps);                                  
		for (E2_ComponentMAP oMAP: this.get_vCombinedComponentMAPs()) {
			oMAP.set_oModulContainerMASK_This_BelongsTo(this);
		}
		
		this.get_oRowForHeadline().setVisible(false);            // wird erst true, wenn eine headline eingeblendet wird
		
		MyE2_Row oRowButtonsUndHeadline = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
		
		oRowButtonsUndHeadline.add(this.get_oRowForButtons(), E2_INSETS.I_0_0_10_0);
		oRowButtonsUndHeadline.add(this.get_oRowForAutomaticTools(), E2_INSETS.I_0_0_10_0);
		oRowButtonsUndHeadline.add(this.get_oRowForHeadline(), E2_INSETS.I_0_0_0_0);
		
		this.add(oRowButtonsUndHeadline, new Insets(4,0,2,2));
		
		if (bShowSeparator) 		{
			this.add(new Separator(),E2_INSETS.I_0_0_0_0);
		}
		
		this.add((Component)this.get_oComponentWithMaskElements(), new Insets(4,0,2,2));

		
		/*
		 * zusatzeinblendungen in der maske (z.B. LOGGING-Info-Button) einfuegen
		 */
		if (this.get_oComponentWithMaskElements().get_Basic_Mask_Container_Components()!=null) {
			for (IF_ADDING_Allowed oContainer: this.get_oComponentWithMaskElements().get_Basic_Mask_Container_Components()) {
				new ADDING_FieldLoggingButtons(oContainer);
			}
		}
		//ende der zusatzeinblendungen
		
		
		//2014-05-09:  eMail-Versende-Makros einblenden, wenn vorhanden
		if (S.isFull(this.get_MODUL_IDENTIFIER()))
		{
			MMC_OWN_RECLIST_MAIL_AUS_MASK  oMMR = new MMC_OWN_RECLIST_MAIL_AUS_MASK(this.get_MODUL_IDENTIFIER());
			
			if (oMMR.get_vKeyValues().size()>0) {
				RowLayoutData  oRL = new RowLayoutData();
				oRL.setAlignment(new Alignment(Alignment.CENTER, Alignment.CENTER));
				this.get_oRowForAutomaticTools().add(new MMC_StartMail_PopUP(oMMR, new MMC_ERSETZUNGS_HASH_STD(this)),oRL);
			}
		}
	
	}
	
	
	
	
	
	/**
	 * @param oextWidth
	 * @param oextHeight
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(Extent				oextWidth,
											Extent				oextHeight,
											MyE2_String 		oTitle) throws myException
	{
		if (this.rb_get_OwnKENNER()==null) {
			throw new myException(this,"RB_ModuleContainerMASK: no rb_INIT-Call is done !!");
		}
		super.CREATE_AND_SHOW_POPUPWINDOW(oextWidth, oextHeight, oTitle);
	}
	
	
	/**
	 * @param oextWidth
	 * @param oextHeight
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW_SPLIT(	Extent				oextWidth,
													Extent				oextHeight,
													MyE2_String 		oTitle) throws myException
	{
		if (this.rb_get_OwnKENNER()==null) {
			throw new myException(this,"RB_ModuleContainerMASK: no rb_INIT-Call is done !!");
		}
		super.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(oextWidth, oextHeight, oTitle);
	}

	


	
	/**
	 * 
	 * @param oWidth
	 * @param oHeight
	 * @param oE2_ContentPaneCallingUnit
	 * @param bSplit
	 * @param oSplitPosition
	 * @param oTitle
	 * @throws myException
	 */
	public void CREATE_AND_SHOW_POPUPWINDOW(		Extent 					oWidth, 
													Extent 					oHeight,
													boolean 				bSplit,
													Extent    				oSplitPosition,
													MyE2_String 			oTitle) throws myException
	{
		if (this.rb_get_OwnKENNER()==null) {
			throw new myException(this,"RB_ModuleContainerMASK: no rb_INIT-Call is done !!");
		}
		super.CREATE_AND_SHOW_POPUPWINDOW(oWidth, oHeight, bSplit, oSplitPosition, oTitle);
	}

	
	
	
	
	
	
	//aufrufe der alten methoden blocken
	/**
	 * @param maskMAP
	 * @param ocomponentWithMaskElements
	 * @param oextWidth
	 * @param oextHeight
	 * !!!!!!!!!!!!!!!!!!!!!!! NOT_ALLOWD IN RB-Range
	 * @throws myException 
	 */
	public void INIT(	E2_ComponentMAP 		maskMAP, 
						IF_BaseComponent4Mask 	ocomponentWithMaskElements, 
						Extent					oextWidth,
						Extent					oextHeight) throws myException
			
	{
		throw new myException(this," NOT_ALLOWD IN RB-Range (1)");
	}


	
	/**
	 * @param maskMAP
	 * @param ocomponentWithMaskElements
	 * @param oextWidth
	 * @param oextHeight
	 * @param showSeparator
	 * !!!!!!!!!!!!!!!!!!!!!!! NOT_ALLOWD IN RB-Range
	 * @throws myException 
	 */
	public void INIT(	E2_ComponentMAP 		maskMAP, 
						IF_BaseComponent4Mask 	ocomponentWithMaskElements, 
						Extent					oextWidth,
						Extent					oextHeight,
						boolean             	showSeparator) throws myException
			
	{
		throw new myException(this," NOT_ALLOWD IN RB-Range (2)");
	}

	

	/**
	 * 	 * !!!!!!!!!!!!!!!!!!!!!!! NOT_ALLOWD IN RB-Range
	 */
	public void add_SubTableComponentMAP(E2_ComponentMAP oSubMap) throws myException
	{
		throw new myException(this," NOT_ALLOWD IN RB-Range (3)");
	}



	@Override
	public E2_MODULNAME_ENUM.MODUL rb_get_OwnKENNER() {
		return ownKENNER;
	}

	@Override
	public void rb_set_OwnKENNER(E2_MODULNAME_ENUM.MODUL kenner) {
		this.ownKENNER = kenner;
	}




	
	

}
