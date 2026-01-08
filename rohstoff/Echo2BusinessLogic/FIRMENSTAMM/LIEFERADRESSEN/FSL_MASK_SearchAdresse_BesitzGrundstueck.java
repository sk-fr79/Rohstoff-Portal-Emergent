/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.LIEFERADRESSEN;

import java.math.BigDecimal;
import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.BasicInterfaces.IF_FocusTraversalIndexSetter;
import panter.gmbh.BasicInterfaces.Service.PdServiceFindAdresseNearby;
import panter.gmbh.BasicInterfaces.Service.PdServiceReadFromDBFieldMask;
import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceGeoDifferenceBetweenTwoPointsBean;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.AgentsAndValidators.XX_FieldSetter_AND_Validator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Factorys.StyleFactory_TextField;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonArrays;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButtonWithRec21;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_IF;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_ResultButton_action;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldListSortButton;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField_AbstractActionStartSearch;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HL_SearchMainAdress;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.DB.MyE2EXT__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.O;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyRECORD_IF_RECORDS;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * @author martin
 *
 */
public class FSL_MASK_SearchAdresse_BesitzGrundstueck extends RB_HL_SearchMainAdress implements MyE2IF__DB_Component, IF_FocusTraversalIndexSetter {

	
	private MyE2EXT__DB_Component 	extDb = new MyE2EXT__DB_Component(this) ;
	private MyE2EXT__Component 		ext = new MyE2EXT__Component(this) ;
	
	private ButtonSearchAdressesNearby buttonSearchAdressesNearby = new ButtonSearchAdressesNearby();
	
	private int   iLastStartSearchButton = 0;
	
	public FSL_MASK_SearchAdresse_BesitzGrundstueck(SQLField sqlField) throws myException {
		super();
		this.get_tf_search_input().EXT().set_STYLE_FACTORY(new StyleFactory_TextField());
		this.get_tf_search_input().setStyle(this.get_tf_search_input().EXT().get_STYLE_FACTORY().get_Style(true,true,false));
		this.extDb.set_bGivesBackValueToDB(true);
		this.extDb.set_oSQLField(sqlField);
		
		this._setRenderSearchResultVisibleOnMaskInEmptyManualSettings(true);

		this.get_tf_search_input()._w(100);
		
		RB_gld gl = new RB_gld()._ins(2,2,2,2)._left_top();
		this._clear()
		._a(this.get_gridContainer_to_show_searchResult()._setSize(300)._bo_ddd(), gl)
		._a(this.get_tf_search_input(), gl)
		._a(this.get_buttonErase(), gl)
		._a(this.get_buttonStartSearch(), gl)
		._a(this.get_button_to_open_mask_to_referenced_record(), gl)
		._a(buttonSearchAdressesNearby, gl)
		._setSize(450,100,20,20,20,20);

		this.buttonSearchAdressesNearby._aaaInFront(()->{iLastStartSearchButton=1;},true);
		this.get_buttonStartSearch()._aaaInFront(()->{iLastStartSearchButton=2;},true);
		
		this.set_width_popup_window(new Extent(1000));
		this.set_height_popup_window(new Extent(600));
		
		this.EXT().add_FieldSetters_AND_Validator__BeforeReadInputMAP(
				new XX_FieldSetter_AND_Validator()
				{
					@Override
					public MyE2_MessageVector isValid(String cstatus_map,MyE2EXT__Component EXT_own) throws myException {
						FSL_MASK_SearchAdresse_BesitzGrundstueck oThis =  FSL_MASK_SearchAdresse_BesitzGrundstueck.this;
						MyE2_MessageVector oMV = new MyE2_MessageVector();
						String s_vergleich = 	S.NN(oThis.get_c_vergleichswert_dbfeld()).replace(".", "");
						String s_input = 		S.NN(oThis.get_tf_search_input().getText()).replace(".", "");
						
						if (!(s_vergleich).equals(s_input)) {
							oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Feldeingabe wurde nicht über die Suchfunktion gemacht: SUCHFELD: ",true,oThis.EXT_DB().get_oSQLField().get_cFieldLabelForUser().CTrans(),false)));
						}
						return oMV;
					}
					
				});
		
		this.getAddOnComponents()._put("buttonEditInput", this.get_button_to_open_mask_to_referenced_record());
		this.getAddOnComponents()._put("buttonSearchAdressesNearby", buttonSearchAdressesNearby);
		

	}

	@Override
	public void  fill_grid_4_popup(MyE2_Grid grid_4_popup, RB_ResultButtonArrays vektor_buttons, ExecINFO execInfo) throws myException {
		
		E2_Grid gs = new E2_Grid();
		Vector<Component>  v_buttons = new Vector<Component>();
		if (iLastStartSearchButton==1) {
			v_buttons.add(new RB_SearchFieldListSortButton(this,0,  new MyE2_String("Firma"),null));
			v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("Strasse"),null));
			v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Plz/Ort"),null));
			RB_SearchFieldListSortButton bt = new RB_SearchFieldListSortButton(this,3,  new MyE2_String("Entfernung"), null);
			bt.getButton_to_sort()._al_right();
			v_buttons.add(bt);
			v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("ID"),null));
		} else {
			v_buttons.add(new RB_SearchFieldListSortButton(this,0,  new MyE2_String("Adresse"),null));
			v_buttons.add(new RB_SearchFieldListSortButton(this,1,  new MyE2_String("Strasse"),null));
			v_buttons.add(new RB_SearchFieldListSortButton(this,2,  new MyE2_String("Firma"),null));
			v_buttons.add(new MyE2_Label(new MyE2_String("Lief/Abn")));
			v_buttons.add(new RB_SearchFieldListSortButton(this,4,  new MyE2_String("ID"),null));
		}
		
		Integer     i_actual_sort_col = this.get_rb_ResultButtonArray().get_actual_sort_col();
		SORTSTATUS  status_actual =  this.get_rb_ResultButtonArray().get_actual_sort_status();
 
		//sortierstatus beruecksichtigen
		if (i_actual_sort_col!=null && i_actual_sort_col>=0 &&	i_actual_sort_col<5 && status_actual!=null) {
			Component comp = v_buttons.get(i_actual_sort_col);
			if (comp instanceof RB_SearchFieldListSortButton) {
				((RB_SearchFieldListSortButton)comp).set_sortstatus_actual(status_actual);
			}
		}
		
		gs	._setSize(250,250,350,80,40)
			._a(v_buttons.get(0), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
			._a(v_buttons.get(1), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
			._a(v_buttons.get(2), new RB_gld()._left_mid()._ins(2, 2, 2, 2))
			._a(v_buttons.get(3), (iLastStartSearchButton==1)?new RB_gld()._right_mid()._ins(2, 2, 2, 2):new RB_gld()._left_mid()._ins(2, 2, 2, 2))
			._a(v_buttons.get(4), new RB_gld()._right_mid()._ins(2, 2, 2, 2))
			;
		
		for (RB_ResultButton_IF[] arr: vektor_buttons) {
			gs
				._a((Component)arr[0], new RB_gld()._left_mid()._ins(2, 2, 2, 2))
				._a((Component)arr[1], new RB_gld()._left_mid()._ins(2, 2, 2, 2))
				._a((Component)arr[2], new RB_gld()._left_mid()._ins(2, 2, 2, 2))
				._a((Component)arr[3], (iLastStartSearchButton==1)?new RB_gld()._right_mid()._ins(2, 2, 2, 2):new RB_gld()._left_mid()._ins(2, 2, 2, 2))
				._a((Component)arr[4], new RB_gld()._right_mid()._ins(2, 2, 2, 2))
				;
			
		}
	
		grid_4_popup.removeAll();
		grid_4_popup.add(gs,E2_INSETS.I(0,0,0,0));
	}


	
	
	
	private class ButtonSearchAdressesNearby extends E2_Button {
		
		public ButtonSearchAdressesNearby() {
			super();
			
			this.__setImages(E2_ResourceIcon.get_RI("suche_gps.png"), E2_ResourceIcon.get_RI("suche_gps__.png"));
			this._style(E2_Button.StyleImageButton())
				._aaa(new ownAction(FSL_MASK_SearchAdresse_BesitzGrundstueck.this))._ttt(S.ms("Suche nach geografisch in der Nähe befindlichen Hauptadressen"));
		}
		
		private class ownAction extends RB_SearchField_AbstractActionStartSearch {

			public ownAction(RB_SearchField p_rb_searchfield_this_belongs_to) {
				super(p_rb_searchfield_this_belongs_to);
			}

			@Override
			protected MyE2_MessageVector fillResultButtonArray() throws myException {
				MyE2_MessageVector mv = new MyE2_MessageVector();
				
				FSL_MASK_SearchAdresse_BesitzGrundstueck  oThis = FSL_MASK_SearchAdresse_BesitzGrundstueck.this;
				
				Object  oLongitude = new PdServiceReadFromDBFieldMask().getActualRawMaskVal(FSL_MASK_SearchAdresse_BesitzGrundstueck.this.ext.get_oComponentMAP(), ADRESSE.longitude);
				Object  oLatitude = new PdServiceReadFromDBFieldMask().getActualRawMaskVal(FSL_MASK_SearchAdresse_BesitzGrundstueck.this.ext.get_oComponentMAP(), ADRESSE.latitude);

				if (O.isOneNull(oLongitude,oLatitude) || !(oLongitude instanceof BigDecimal)  || !(oLatitude instanceof BigDecimal)) {
					mv._addAlarm("Die Geokoordinaten können nicht korrekt gefunden werden !!");
				} else {
					VEK<Rec21>  vResult = new VEK<Rec21>();
					
					BigDecimal bdLong3Nachkomma = ((BigDecimal)oLongitude).setScale(3,BigDecimal.ROUND_FLOOR);
					BigDecimal bdLat3Nachkomma = ((BigDecimal)oLatitude).setScale(3,BigDecimal.ROUND_FLOOR);
					BigDecimal bdLong2Nachkomma = ((BigDecimal)oLongitude).setScale(2,BigDecimal.ROUND_FLOOR);
					BigDecimal bdLat2Nachkomma = ((BigDecimal)oLatitude).setScale(2,BigDecimal.ROUND_FLOOR);
							
					
					
					vResult._a(new PdServiceFindAdresseNearby().getAdressesNearby(bdLong3Nachkomma, bdLat3Nachkomma, myCONST.ADRESSTYP_FIRMENINFO,3));
					if (vResult.size()==0) {
						vResult._a(new PdServiceFindAdresseNearby().getAdressesNearby(bdLong2Nachkomma, bdLat2Nachkomma, myCONST.ADRESSTYP_FIRMENINFO,2));
					}
					
					oThis.get_rb_ResultButtonArray().clear();
					
					//jede resultbutton-zeile besteht aus 4 spalten: name1+name2, Strasse+Hnr, ort, entfernung, id
					for (Rec21 r: vResult) {
						ownResultButton[] ra= new  ownResultButton[5];
						ra[0]=new ownResultButton(r, S.NN(r.get_ufs_kette(" ", ADRESSE.name1, ADRESSE.name2),"-"));
						ra[1]=new ownResultButton(r, S.NN(r.get_ufs_kette(" ", ADRESSE.strasse, ADRESSE.hausnummer),"-"));
						ra[2]=new ownResultButton(r, S.NN(r.get_ufs_kette(" ", ADRESSE.plz, ADRESSE.ort),"-"));

						//entfernung renchnen
						String diff = "--";
						try {
							BigDecimal bdDiff = new PdServiceGeoDifferenceBetweenTwoPointsBean().differenceInMeters((BigDecimal)oLatitude, (BigDecimal)oLongitude, r.get_raw_resultValue_bigDecimal(ADRESSE.latitude), r.get_raw_resultValue_bigDecimal(ADRESSE.longitude));
							diff=MyNumberFormater.formatDez(bdDiff, 2, true, ',', '.',true)+" m";
							ra[3]=(ownResultButton)new ownResultButton(r, diff, S.makeStringLonger(diff, "0", 10,true))._al_right();
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						ra[4]=new ownResultButton(r, S.NN(r.get_ufs_kette(" ", ADRESSE.id_adresse),"-"), S.makeStringLonger(r.get_ufs_dbVal(ADRESSE.id_adresse), "0", 10,true));
						
						oThis.get_rb_ResultButtonArray().add(ra);
					}
				}
				return mv;
			}
		}
		
		
		
		
		
		
		private class ownResultButton extends RB_ResultButtonWithRec21 {

			private String sortText = null;
			
			public ownResultButton(Rec21 rec21, String cText) throws myException {
				super(FSL_MASK_SearchAdresse_BesitzGrundstueck.this, rec21, cText, E2_Button.StyleTextButton());
				this.add_oActionAgent(new RB_ResultButton_action(rec21, FSL_MASK_SearchAdresse_BesitzGrundstueck.this));
				this.sortText = cText;
			}
			public ownResultButton(Rec21 rec21, String cText, String cSort) throws myException {
				super(FSL_MASK_SearchAdresse_BesitzGrundstueck.this, rec21, cText, E2_Button.StyleTextButton());
				this.add_oActionAgent(new RB_ResultButton_action(rec21, FSL_MASK_SearchAdresse_BesitzGrundstueck.this));
				this.sortText = cSort;
			}

			@Override
			public Component me() throws myException {
				return this;
			}

			@Override
			public String get_sort_string() throws myException {
				return S.NN(this.sortText,"-");
			}
			
		}
		
	}


	@Override
	public void render_search_result_visible_on_mask(	E2_Grid gridcontainer_4_search_results, 
														String c_result_value_4_db) throws myException {
		
		gridcontainer_4_search_results.removeAll();
		MyE2_Label lab_help = null;
		if (S.isEmpty(c_result_value_4_db)) {
			lab_help=new MyE2_Label(" ");
			gridcontainer_4_search_results._setRowH(0, 18);
		} else {
			RECORD_ADRESSE_extend  recAdresse = new RECORD_ADRESSE_extend(c_result_value_4_db);
			String label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("Hauptadresse").CTrans()+")";
			if (!recAdresse.is_main_adress()) {
				RECORD_ADRESSE_extend rh = recAdresse.get_main_Adress();
				label_txt = recAdresse.get__FullNameAndAdress_Typ2()+" ("+new MyE2_String("bei ").CTrans()+rh.get__FullNameAndAdress_Typ2()+")";
			}
			
			
			lab_help=new MyE2_Label(label_txt, new E2_FontItalic(-2),true);				
		}
		gridcontainer_4_search_results._a(lab_help, new RB_gld()._ins(2));
	}

	
	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP)	throws myException {
		this.rb_set_db_value_manual(cText);
	}

	@Override
	public void prepare_ContentForNew(boolean bSetDefault) throws myException {
		this.rb_set_db_value_manual("");
	}


	@Override
	public void set_EXT(MyE2EXT__Component oEXT) {  this.ext=oEXT;	}

	@Override
	public MyE2EXT__Component EXT() { return this.ext; }

	@Override
	public void show_InputStatus(boolean bInputIsOK) {
		this.get_tf_search_input().setStyle(
				this.get_tf_search_input().EXT().get_STYLE_FACTORY().get_Style(this.get_tf_search_input().isEnabled(),this.EXT_DB().get_oSQLField().get_oFieldMetaDef().get_bFieldNullableBasicAndInteractive(),!bInputIsOK)
				);
	}


	@Override
	public String get_cActualMaskValue() throws myException {
		return this.rb_readValue_4_dataobject();
	}

	@Override
	public String get_cActualDBValueFormated() throws myException {
		return this.rb_readValue_4_dataobject();
	}

	@Override
	public void set_cActualMaskValue(String cText) throws myException {
		this.rb_set_db_value_manual(cText);
	}

	@Override
	public void set_bIsComplexObject(boolean bisComplex) {}

	@Override
	public boolean get_bIsComplexObject() {return false;}

	@Override
	public Vector<String> get_vInsertStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap)	throws myException {return null;}

	@Override
	public Vector<String> get_vUpdateStack(E2_ComponentMAP oE2_ComponentMAP, SQLMaskInputMAP oMaskInputMap)	throws myException {return null;}

	@Override
	public MyE2EXT__DB_Component EXT_DB() {  return this.extDb; }	

	@Override
	public void set_EXT_DB(MyE2EXT__DB_Component oEXT_DB) { this.extDb = oEXT_DB; }

	
	@Override
	public E2_BasicModuleContainer generate_container_4_popup_window() throws myException {
		return new ownPopupShowSearchResults();
	}

	@Override
	public String get_result_string_from_record(MyRECORD_IF_RECORDS p_result_record) throws myException {
		if (p_result_record instanceof RECORD_ADRESSE) {
			return ((RECORD_ADRESSE)p_result_record).get_ID_ADRESSE_cUF();
		} else if (p_result_record instanceof Rec21) {
			return ((Rec21)p_result_record).get_key_value();
		}
		throw new myException("Undefined recordtype!");
	}

	@Override
	public MyE2_MessageVector do_mask_settings_after_search(String unformated_MaskValue, boolean aktiv)	throws myException {
		return null;
	}
	
	private class ownPopupShowSearchResults extends E2_BasicModuleContainer {
		public ownPopupShowSearchResults() {
			super();
		}
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_FocusTraversalIndexSetter#setFocusTraversalIndexInSubComponents(int)
	 */
	@Override
	public int setFocusTraversalIndexInSubComponents(int iNext) throws myException {
		this.get_tf_search_input().setFocusTraversalParticipant(true);
		this.get_tf_search_input().setFocusTraversalIndex(iNext++);

		this.get_buttonErase().setFocusTraversalParticipant(true);
		this.get_buttonErase().setFocusTraversalIndex(iNext++);
		
		this.get_buttonStartSearch().setFocusTraversalParticipant(true);
		this.get_buttonStartSearch().setFocusTraversalIndex(iNext++);

		this.buttonSearchAdressesNearby.setFocusTraversalParticipant(true);
		this.buttonSearchAdressesNearby.setFocusTraversalIndex(iNext++);
		
		return iNext;
	}


	



}
