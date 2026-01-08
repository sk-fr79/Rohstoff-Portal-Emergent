 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.BasicInterfaces.IF_ChangeValue;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V2;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.DB.E2_DBActiveLabelForLists;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.Echo2.components.DB.MyE2_DB_SelectFieldV2;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_artikel_bez;
  
public class DL_LIST_ComponentMap extends E2_ComponentMAP_V2  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public DL_LIST_ComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new DL_LIST_SqlFieldMAP(p_tpHashMap));
        
    	
    	this.setComponentMapMarker(new DL_LIST_ComponentMapMapMarker(this));
    	
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(DL_CONST.DL_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),S.ms("?"));
        this.add_Component(DL_CONST.DL_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),S.ms("?"));
        this.add_Component(DL_CONST.DL_NAMES.DIRECT_DEL.db_val(),    	new DL_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																S.ms("?"));
        
        this.add_Component(DL_CONST.DL_NAMES.DIRECT_EDIT.db_val(),   	new DL_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																S.ms("?"));
        this.add_Component(DL_CONST.DL_NAMES.DIRECT_VIEW.db_val(),   	new DL_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		S.ms("?"));
        this.add_Component(DL_CONST.DL_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(DL_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));

        this.add_Component(new DL_LIST_Jump())._setLongText4ColumnSelection(S.ms("Sprung zu den nicht abgerechneten DL-Positionen"));
        

        IF_ChangeValue<String, String> changer_adresse_start = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
        			Rec21 rAdresse = rec.get_up_Rec21(DLP_PROFIL.id_adresse_start, ADRESSE.id_adresse, true);
        			if (rAdresse!=null) {
        				return new Rec21_adresse(rAdresse).getVornameName1OrtInfoZuFirma();	
        			}
        		}
        		return "-";
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };

        
        IF_ChangeValue<String, String> changer_adresse_ziel = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
        			Rec21 rAdresse = rec.get_up_Rec21(DLP_PROFIL.id_adresse_ziel, ADRESSE.id_adresse, true);
        			if (rAdresse!=null) {
        				return new Rec21_adresse(rAdresse).getVornameName1OrtInfoZuFirma();	
        			}
        		}
        		return "-";
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };

        IF_ChangeValue<String, String> changer_adresse_fremdware = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
        			Rec21 rAdresse = rec.get_up_Rec21(DLP_PROFIL.id_adresse_fremdware, ADRESSE.id_adresse, true);
        			if (rAdresse!=null) {
        				return new Rec21_adresse(rAdresse).getVornameName1OrtInfoZuFirma();	
        			}
        		}
        		return "-";
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };

        IF_ChangeValue<String, String> changer_adresse_rechnung = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
        			Rec21 rAdresse = rec.get_up_Rec21(DLP_PROFIL.id_adresse_rechnung, ADRESSE.id_adresse, true);
        			if (rAdresse!=null) {
        				return new Rec21_adresse(rAdresse).getVornameName1OrtInfoZuFirma();	
        			}
        		}
        		return "-";
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };


        IF_ChangeValue<String, String> changer_adresse_buchungslager = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
        			Rec21 rAdresse = rec.get_up_Rec21(DLP_PROFIL.id_adresse_buchungslager, ADRESSE.id_adresse, true);
        			if (rAdresse!=null) {
        				return new Rec21_adresse(rAdresse).getVornameName1OrtInfoZuFirma();	
        			}
        		}
        		return "-";
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };

        
        IF_ChangeValue<String, String> changerArtikel = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
	        		Rec21 artikel = rec.get_up_Rec21(DLP_PROFIL.id_artikel, ARTIKEL.id_artikel, true);
	        		if (artikel != null) {
						return new Rec21_artikel(artikel).__get_anr1_artbez1(false);
	        		}
        		}
        		return "-";
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };

        
        IF_ChangeValue<String, String> changerArtikelBez = (orig,rec)->{
        	try {
        		if (rec!=null && rec.is_ExistingRecord()) {
        			Rec21 recAB = rec.get_up_Rec21(DLP_PROFIL.id_artikel_bez_quelle, ARTIKEL_BEZ.id_artikel_bez, true);
        			if (recAB!=null) {
						return new Rec21_artikel_bez(recAB).__get_ANR1_ANR2_ARTBEZ1();
        			}
        		} 
        		return "-";
        		
			} catch (Exception e) {
				e.printStackTrace();
				return "@@@ERROR";
			}
        };


        

        
        MyE2_DB_SelectFieldV2  selFieldAnwendenAuf = new MyE2_DB_SelectFieldV2(oFM.get_(DLP_PROFIL.anwenden_auf_typ));
        selFieldAnwendenAuf.set_ListenInhalt(DL_ENUM_ANWENDEN_AUF.ALLE.get_dd_Array(true),false);
        
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(DLP_PROFIL.aktiv),true),     			S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.aktiv)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DLP_PROFIL.gilt_ab_datum),true),   S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.gilt_ab_datum)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DLP_PROFIL.beschreibung),true),    S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.beschreibung)));
        this.add_Component(selFieldAnwendenAuf, 											    S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.anwenden_auf_typ)));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DLP_PROFIL.anteil_menge),true),     S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.anteil_menge)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(DLP_PROFIL.ref_menge_ist_lade_mge),true),   S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.ref_menge_ist_lade_mge)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DLP_PROFIL.umrech_mge_quelle_ziel),true),     S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.umrech_mge_quelle_ziel)));

        
        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_adresse_start))
        										._setChangerStringString(changer_adresse_start)
        										._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_start)));
        
        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_adresse_ziel))
												._setChangerStringString(changer_adresse_ziel)
												._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_ziel)));

        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_adresse_fremdware))
												._setChangerStringString(changer_adresse_fremdware)
												._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_fremdware)));

        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_artikel))
								        		._setChangerStringString(changerArtikel)
								        		._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel)));

        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_artikel_bez_quelle))
								        		._setChangerStringString(changerArtikelBez)
								        		._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel_bez_quelle)));

        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_artikel_bez_ziel))
								        		._setChangerStringString(changerArtikelBez)
								        		._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel_bez_ziel)));

        
        
        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_adresse_rechnung))
												._setChangerStringString(changer_adresse_rechnung)
												._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_rechnung)));
        
        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_adresse_buchungslager))
												._setChangerStringString(changer_adresse_buchungslager)
												._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_buchungslager)));
        
        this.add_Component((MyE2IF__DB_Component)new E2_DBActiveLabelForLists(oFM.get_(DLP_PROFIL.id_artikel_bez_dl))
												._setChangerStringString(changerArtikelBez)
												._lw(),     												S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel_bez_dl)));
        
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DLP_PROFIL.typ_mengen_calc),true),     S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.typ_mengen_calc)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(DLP_PROFIL.id_dlp_profil),true),       S.ms(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_dlp_profil)));

        
        VEK<IF_Field>  spalten = new VEK<>();
        spalten._a(DLP_PROFIL.aktiv)
                ._a(DLP_PROFIL.anteil_menge)
                ._a(DLP_PROFIL.gilt_ab_datum)
                ._a(DLP_PROFIL.beschreibung)
                ._a(DLP_PROFIL.anwenden_auf_typ)
                ._a(DLP_PROFIL.ref_menge_ist_lade_mge)
                ._a(DLP_PROFIL.umrech_mge_quelle_ziel)
                ._a(DLP_PROFIL.id_adresse_start)
                ._a(DLP_PROFIL.id_adresse_ziel)
                ._a(DLP_PROFIL.id_adresse_fremdware)
                ._a(DLP_PROFIL.id_adresse_rechnung)
                ._a(DLP_PROFIL.id_adresse_buchungslager)
                ._a(DLP_PROFIL.id_artikel)
                ._a(DLP_PROFIL.id_artikel_bez_quelle)
                ._a(DLP_PROFIL.id_artikel_bez_ziel)
                ._a(DLP_PROFIL.id_artikel_bez_dl)
                ._a(DLP_PROFIL.id_dlp_profil)
                ._a(DLP_PROFIL.typ_mengen_calc)
                ;
        VEK<String>  spaltenNames  = new VEK<>();
        for (IF_Field f: spalten) {
        	spaltenNames._a(f.fn());
        }
        
        this._setLineWrapListHeader(true,spaltenNames.getArray()); 

        
//        //neu ab 20171025        
//        this._setLineWrapListHeader(true 
//        						  ,DLP_PROFIL.aktiv.fn()
//                                  ,DLP_PROFIL.anteil_menge.fn()
//                                  ,DLP_PROFIL.gilt_ab_datum.fn()
//                                  ,DLP_PROFIL.anwenden_auf_typ.fn()
//                                  ,DLP_PROFIL.ref_menge_ist_lade_mge.fn()
//                                  ,DLP_PROFIL.umrech_mge_quelle_ziel.fn()
//                                  ,DLP_PROFIL.id_adresse_start.fn()
//                                  ,DLP_PROFIL.id_adresse_ziel.fn()
//                                  ,DLP_PROFIL.id_adresse_fremdware.fn()
//                                  ,DLP_PROFIL.id_adresse_rechnung.fn()
//                                  ,DLP_PROFIL.id_adresse_buchungslager.fn()
//                                  ,DLP_PROFIL.id_artikel.fn()
//                                  ,DLP_PROFIL.id_artikel_bez_quelle.fn()
//                                  ,DLP_PROFIL.id_artikel_bez_ziel.fn()
//                                  ,DLP_PROFIL.id_artikel_bez_dl.fn()
//                                  ,DLP_PROFIL.id_dlp_profil.fn()
//                                  ,DLP_PROFIL.typ_mengen_calc.fn()
//        );
        
  
        for (IF_Field f: spalten) {
            this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(f)), f.fn());
            this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(f))._ins(3,1,3,1)._col(new E2_ColorBase()), f.fn());
            this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(f))._ins(1,2,1,1)._col(new E2_ColorDark()), f.fn());
        }
        
//        
//        //hier kann das layout der einzelnen spalten definiert werden 
//        // spaltenlayout fuer:  DLP_PROFIL.anteil_menge.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.aktiv)), DLP_PROFIL.aktiv.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.aktiv))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.aktiv.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.aktiv))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.aktiv.fn());
//
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.anteil_menge)), DLP_PROFIL.anteil_menge.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.anteil_menge))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.anteil_menge.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.anteil_menge))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.anteil_menge.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.id_adresse.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.id_adresse_fremd)), DLP_PROFIL.id_adresse_fremd.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_adresse_fremd))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.id_adresse_fremd.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_adresse_fremd))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.id_adresse_fremd.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.id_adresse_dienstlager.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.id_adresse_dienstlager)), DLP_PROFIL.id_adresse_dienstlager.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_adresse_dienstlager))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.id_adresse_dienstlager.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_adresse_dienstlager))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.id_adresse_dienstlager.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.id_artikel_bez.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.id_artikel_bez)), DLP_PROFIL.id_artikel_bez.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_artikel_bez))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.id_artikel_bez.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_artikel_bez))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.id_artikel_bez.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.id_artikel_bez_dl.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.id_artikel_bez_dl)), DLP_PROFIL.id_artikel_bez_dl.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_artikel_bez_dl))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.id_artikel_bez_dl.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_artikel_bez_dl))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.id_artikel_bez_dl.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.id_DLP_PROFIL.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.id_dlp_profil)), DLP_PROFIL.id_dlp_profil.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_dlp_profil))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.id_dlp_profil.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.id_dlp_profil))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.id_dlp_profil.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.typ_mengen_calc.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.typ_mengen_calc)), DLP_PROFIL.typ_mengen_calc.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.typ_mengen_calc))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.typ_mengen_calc.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.typ_mengen_calc))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.typ_mengen_calc.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.typ_wa_we.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.typ_wa_we)), DLP_PROFIL.typ_wa_we.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.typ_wa_we))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.typ_wa_we.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.typ_wa_we))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.typ_wa_we.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.umrech_mge_quelle_ziel.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.umrech_mge_quelle_ziel)), DLP_PROFIL.umrech_mge_quelle_ziel.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.umrech_mge_quelle_ziel))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.umrech_mge_quelle_ziel.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.umrech_mge_quelle_ziel))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.umrech_mge_quelle_ziel.fn());
//        // ----
//        //
//        //
//        // spaltenlayout fuer:  DLP_PROFIL.umrech_mge_quelle_ziel.fn()
//        this._setColExtent(     new Extent(DL_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(DLP_PROFIL.gilt_ab_datum)), DLP_PROFIL.gilt_ab_datum.fn());
//        this._setLayoutElements(new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.gilt_ab_datum))._ins(3,1,3,1)._col(new E2_ColorDark()), DLP_PROFIL.gilt_ab_datum.fn());
//        this._setLayoutTitles(  new RB_gld()._al(DL_READABLE_FIELD_NAME.getAlignment(DLP_PROFIL.gilt_ab_datum))._ins(1,2,1,1)._col(new E2_ColorDark()), DLP_PROFIL.gilt_ab_datum.fn());
//        // ----
//        //
      	   	
        this.set_oSubQueryAgent(new DL_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
    }
    

    
	@Override
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		
		E2_ComponentMAP_V2 oRueck = new E2_ComponentMAP_V2(this.get_oSQLFieldMAP());
		E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);

		oRueck.setComponentMapMarker(new DL_LIST_ComponentMapMapMarker(oRueck));
		
		return oRueck;

	}

    
    
    public class DL_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
    	public DL_LIST_ComponentMapMapMarker(E2_ComponentMAP_V2 p_map) {
    		super(p_map);
    	}

    	@Override
    	protected void innerFormat(Collection<Component> v) {
    		super.innerFormat(v);
    		
    		E2_ComponentMAP_V2 map = (E2_ComponentMAP_V2)this.getMap();
    		
    		try {

    			Rec21 recDLProfil = map.getRec21();
    			if (recDLProfil.is_no_db_val(DLP_PROFIL.aktiv)) {
    				//this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
    				this.setTextColorInMap(v,Color.DARKGRAY);
    			}
    			
    		} catch (myException e) {
    			e.printStackTrace();
    		}
    		
    	}
    }

    
    
    
  
    
}
 
 
