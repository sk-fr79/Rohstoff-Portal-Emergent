 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
  
import java.util.Collection;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_V22;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
  
public class WK_RB_LIST_ComponentMap_gen extends E2_ComponentMAP_V22  {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WK_RB_LIST_ComponentMap_gen(RB_TransportHashMap  p_tpHashMap) throws myException  {
  
    	super(new WK_RB_LIST_SqlFieldMAP(p_tpHashMap));
        
        this.m_tpHashMap = p_tpHashMap;        
        
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(WK_RB_CONST.WK_RB_NAMES.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"));
        this.add_Component(WK_RB_CONST.WK_RB_NAMES.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"));
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_DEL.db_val(),    	new WK_RB_LIST_bt_DeleteInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Loeschknopf in der Listenzeile")),	
        																new MyE2_String("?"));
        
        this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_EDIT.db_val(),   	new WK_RB_LIST_bt_ListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_VIEW.db_val(),   	new WK_RB_LIST_bt_ListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(WK_RB_CONST.WK_RB_NAMES.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(WK_RB_VALIDATORS.ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_wiegekarte),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_wiegekarte)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_wiegekarte_parent),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_wiegekarte_parent)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.adresse_lieferant),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.adresse_lieferant)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.adresse_spedition),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.adresse_spedition)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.anz_allg),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.anz_allg)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.anz_behaelter),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.anz_behaelter)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.anz_bigbags),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.anz_bigbags)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.anz_gitterboxen),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.anz_gitterboxen)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.anz_paletten),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.anz_paletten)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.befund),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.befund)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.bemerkung1),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.bemerkung1)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.bemerkung2),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.bemerkung2)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.bemerkung_intern),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.bemerkung_intern)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.bez_allg),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.bez_allg)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.container_nr),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.container_nr)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.druckzaehler),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.druckzaehler)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.druckzaehler_eingangsschein),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.druckzaehler_eingangsschein)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.es_nr),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.es_nr)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.gedruckt_am),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.gedruckt_am)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.gewicht_abzug),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.gewicht_abzug)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.gewicht_abzug_fuhre),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.gewicht_abzug_fuhre)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.gewicht_nach_abzug),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.gewicht_nach_abzug)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.gewicht_nach_abzug_fuhre),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.gewicht_nach_abzug_fuhre)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.grund_abzug),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.grund_abzug)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_adresse_abn_strecke),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_abn_strecke)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_adresse_lager),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_lager)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_adresse_lieferant),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_lieferant)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_adresse_spedition),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_adresse_spedition)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_artikel_bez),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_artikel_bez)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_artikel_sorte),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_artikel_sorte)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_vpos_tpa_fuhre),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_vpos_tpa_fuhre)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_vpos_tpa_fuhre_ort),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_vpos_tpa_fuhre_ort)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.id_waage_standort),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.id_waage_standort)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(WIEGEKARTE.ist_gesamtverwiegung),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.ist_gesamtverwiegung)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(WIEGEKARTE.ist_lieferant),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.ist_lieferant)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.kennzeichen),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.kennzeichen)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.lfd_nr),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.lfd_nr)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.nettogewicht),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.nettogewicht)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.siegel_nr),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.siegel_nr)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(WIEGEKARTE.sorte_hand),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.sorte_hand)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(WIEGEKARTE.storno),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.storno)));
        this.add_Component(new MyE2_DB_CheckBox(oFM.get_(WIEGEKARTE.strahlung_geprueft),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.strahlung_geprueft)));
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(WIEGEKARTE.typ_wiegekarte),true),     new MyE2_String(WK_RB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE.typ_wiegekarte)));
        //neu ab 20171025        
        this._setLineWrapListHeader(true 
                                  ,WIEGEKARTE.adresse_lieferant.fn()
                                  ,WIEGEKARTE.adresse_spedition.fn()
                                  ,WIEGEKARTE.anz_allg.fn()
                                  ,WIEGEKARTE.anz_behaelter.fn()
                                  ,WIEGEKARTE.anz_bigbags.fn()
                                  ,WIEGEKARTE.anz_gitterboxen.fn()
                                  ,WIEGEKARTE.anz_paletten.fn()
                                  ,WIEGEKARTE.befund.fn()
                                  ,WIEGEKARTE.bemerkung1.fn()
                                  ,WIEGEKARTE.bemerkung2.fn()
                                  ,WIEGEKARTE.bemerkung_intern.fn()
                                  ,WIEGEKARTE.bez_allg.fn()
                                  ,WIEGEKARTE.container_nr.fn()
                                  ,WIEGEKARTE.druckzaehler.fn()
                                  ,WIEGEKARTE.druckzaehler_eingangsschein.fn()
                                  ,WIEGEKARTE.es_nr.fn()
                                  ,WIEGEKARTE.gedruckt_am.fn()
                                  ,WIEGEKARTE.gewicht_abzug.fn()
                                  ,WIEGEKARTE.gewicht_abzug_fuhre.fn()
                                  ,WIEGEKARTE.gewicht_nach_abzug.fn()
                                  ,WIEGEKARTE.gewicht_nach_abzug_fuhre.fn()
                                  ,WIEGEKARTE.grund_abzug.fn()
                                  ,WIEGEKARTE.id_adresse_abn_strecke.fn()
                                  ,WIEGEKARTE.id_adresse_lager.fn()
                                  ,WIEGEKARTE.id_adresse_lieferant.fn()
                                  ,WIEGEKARTE.id_adresse_spedition.fn()
                                  ,WIEGEKARTE.id_artikel_bez.fn()
                                  ,WIEGEKARTE.id_artikel_sorte.fn()
                                  ,WIEGEKARTE.id_vpos_tpa_fuhre.fn()
                                  ,WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn()
                                  ,WIEGEKARTE.id_waage_standort.fn()
                                  ,WIEGEKARTE.id_wiegekarte.fn()
                                  ,WIEGEKARTE.id_wiegekarte_parent.fn()
                                  ,WIEGEKARTE.ist_gesamtverwiegung.fn()
                                  ,WIEGEKARTE.ist_lieferant.fn()
                                  ,WIEGEKARTE.kennzeichen.fn()
                                  ,WIEGEKARTE.lfd_nr.fn()
                                  ,WIEGEKARTE.nettogewicht.fn()
                                  ,WIEGEKARTE.siegel_nr.fn()
                                  ,WIEGEKARTE.sorte_hand.fn()
                                  ,WIEGEKARTE.storno.fn()
                                  ,WIEGEKARTE.strahlung_geprueft.fn()
                                  ,WIEGEKARTE.typ_wiegekarte.fn()
        );
        
        RB_gld gldElementCenter = 	new RB_gld()._center_top()._ins(2,4,2,2)._col(new E2_ColorBase());
       	this._setLayoutElements(gldElementCenter
                                 ,WIEGEKARTE.adresse_lieferant.fn()
                                 ,WIEGEKARTE.adresse_spedition.fn()
                                 ,WIEGEKARTE.anz_allg.fn()
                                 ,WIEGEKARTE.anz_behaelter.fn()
                                 ,WIEGEKARTE.anz_bigbags.fn()
                                 ,WIEGEKARTE.anz_gitterboxen.fn()
                                 ,WIEGEKARTE.anz_paletten.fn()
                                 ,WIEGEKARTE.befund.fn()
                                 ,WIEGEKARTE.bemerkung1.fn()
                                 ,WIEGEKARTE.bemerkung2.fn()
                                 ,WIEGEKARTE.bemerkung_intern.fn()
                                 ,WIEGEKARTE.bez_allg.fn()
                                 ,WIEGEKARTE.container_nr.fn()
                                 ,WIEGEKARTE.druckzaehler.fn()
                                 ,WIEGEKARTE.druckzaehler_eingangsschein.fn()
                                 ,WIEGEKARTE.es_nr.fn()
                                 ,WIEGEKARTE.gedruckt_am.fn()
                                 ,WIEGEKARTE.gewicht_abzug.fn()
                                 ,WIEGEKARTE.gewicht_abzug_fuhre.fn()
                                 ,WIEGEKARTE.gewicht_nach_abzug.fn()
                                 ,WIEGEKARTE.gewicht_nach_abzug_fuhre.fn()
                                 ,WIEGEKARTE.grund_abzug.fn()
                                 ,WIEGEKARTE.id_adresse_abn_strecke.fn()
                                 ,WIEGEKARTE.id_adresse_lager.fn()
                                 ,WIEGEKARTE.id_adresse_lieferant.fn()
                                 ,WIEGEKARTE.id_adresse_spedition.fn()
                                 ,WIEGEKARTE.id_artikel_bez.fn()
                                 ,WIEGEKARTE.id_artikel_sorte.fn()
                                 ,WIEGEKARTE.id_vpos_tpa_fuhre.fn()
                                 ,WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn()
                                 ,WIEGEKARTE.id_waage_standort.fn()
                                 ,WIEGEKARTE.id_wiegekarte.fn()
                                 ,WIEGEKARTE.id_wiegekarte_parent.fn()
                                 ,WIEGEKARTE.ist_gesamtverwiegung.fn()
                                 ,WIEGEKARTE.ist_lieferant.fn()
                                 ,WIEGEKARTE.kennzeichen.fn()
                                 ,WIEGEKARTE.lfd_nr.fn()
                                 ,WIEGEKARTE.nettogewicht.fn()
                                 ,WIEGEKARTE.siegel_nr.fn()
                                 ,WIEGEKARTE.sorte_hand.fn()
                                 ,WIEGEKARTE.storno.fn()
                                 ,WIEGEKARTE.strahlung_geprueft.fn()
                                 ,WIEGEKARTE.typ_wiegekarte.fn()
      	);
      	
      	RB_gld gldTitelCenter = 	new RB_gld()._center_top()._ins(1,2,1,1)._col(new E2_ColorDark());
       	this._setLayoutTitles(gldTitelCenter
                                 ,WIEGEKARTE.adresse_lieferant.fn()
                                 ,WIEGEKARTE.adresse_spedition.fn()
                                 ,WIEGEKARTE.anz_allg.fn()
                                 ,WIEGEKARTE.anz_behaelter.fn()
                                 ,WIEGEKARTE.anz_bigbags.fn()
                                 ,WIEGEKARTE.anz_gitterboxen.fn()
                                 ,WIEGEKARTE.anz_paletten.fn()
                                 ,WIEGEKARTE.befund.fn()
                                 ,WIEGEKARTE.bemerkung1.fn()
                                 ,WIEGEKARTE.bemerkung2.fn()
                                 ,WIEGEKARTE.bemerkung_intern.fn()
                                 ,WIEGEKARTE.bez_allg.fn()
                                 ,WIEGEKARTE.container_nr.fn()
                                 ,WIEGEKARTE.druckzaehler.fn()
                                 ,WIEGEKARTE.druckzaehler_eingangsschein.fn()
                                 ,WIEGEKARTE.es_nr.fn()
                                 ,WIEGEKARTE.gedruckt_am.fn()
                                 ,WIEGEKARTE.gewicht_abzug.fn()
                                 ,WIEGEKARTE.gewicht_abzug_fuhre.fn()
                                 ,WIEGEKARTE.gewicht_nach_abzug.fn()
                                 ,WIEGEKARTE.gewicht_nach_abzug_fuhre.fn()
                                 ,WIEGEKARTE.grund_abzug.fn()
                                 ,WIEGEKARTE.id_adresse_abn_strecke.fn()
                                 ,WIEGEKARTE.id_adresse_lager.fn()
                                 ,WIEGEKARTE.id_adresse_lieferant.fn()
                                 ,WIEGEKARTE.id_adresse_spedition.fn()
                                 ,WIEGEKARTE.id_artikel_bez.fn()
                                 ,WIEGEKARTE.id_artikel_sorte.fn()
                                 ,WIEGEKARTE.id_vpos_tpa_fuhre.fn()
                                 ,WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn()
                                 ,WIEGEKARTE.id_waage_standort.fn()
                                 ,WIEGEKARTE.id_wiegekarte.fn()
                                 ,WIEGEKARTE.id_wiegekarte_parent.fn()
                                 ,WIEGEKARTE.ist_gesamtverwiegung.fn()
                                 ,WIEGEKARTE.ist_lieferant.fn()
                                 ,WIEGEKARTE.kennzeichen.fn()
                                 ,WIEGEKARTE.lfd_nr.fn()
                                 ,WIEGEKARTE.nettogewicht.fn()
                                 ,WIEGEKARTE.siegel_nr.fn()
                                 ,WIEGEKARTE.sorte_hand.fn()
                                 ,WIEGEKARTE.storno.fn()
                                 ,WIEGEKARTE.strahlung_geprueft.fn()
                                 ,WIEGEKARTE.typ_wiegekarte.fn()
      	);
  
  
        //hier kann das layout der einzelnen spalten definiert werden 
        // spaltenlayout fuer:  WIEGEKARTE.adresse_lieferant.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.adresse_lieferant)), WIEGEKARTE.adresse_lieferant.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.adresse_lieferant))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.adresse_lieferant.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.adresse_lieferant))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.adresse_lieferant.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.adresse_spedition.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.adresse_spedition)), WIEGEKARTE.adresse_spedition.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.adresse_spedition))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.adresse_spedition.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.adresse_spedition))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.adresse_spedition.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.anz_allg.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.anz_allg)), WIEGEKARTE.anz_allg.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_allg))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_allg.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_allg))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_allg.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.anz_behaelter.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.anz_behaelter)), WIEGEKARTE.anz_behaelter.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_behaelter))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_behaelter.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_behaelter))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_behaelter.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.anz_bigbags.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.anz_bigbags)), WIEGEKARTE.anz_bigbags.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_bigbags))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_bigbags.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_bigbags))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_bigbags.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.anz_gitterboxen.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.anz_gitterboxen)), WIEGEKARTE.anz_gitterboxen.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_gitterboxen))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_gitterboxen.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_gitterboxen))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_gitterboxen.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.anz_paletten.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.anz_paletten)), WIEGEKARTE.anz_paletten.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_paletten))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_paletten.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.anz_paletten))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.anz_paletten.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.befund.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.befund)), WIEGEKARTE.befund.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.befund))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.befund.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.befund))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.befund.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.bemerkung1.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.bemerkung1)), WIEGEKARTE.bemerkung1.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bemerkung1))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.bemerkung1.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bemerkung1))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.bemerkung1.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.bemerkung2.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.bemerkung2)), WIEGEKARTE.bemerkung2.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bemerkung2))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.bemerkung2.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bemerkung2))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.bemerkung2.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.bemerkung_intern.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.bemerkung_intern)), WIEGEKARTE.bemerkung_intern.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bemerkung_intern))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.bemerkung_intern.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bemerkung_intern))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.bemerkung_intern.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.bez_allg.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.bez_allg)), WIEGEKARTE.bez_allg.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bez_allg))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.bez_allg.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.bez_allg))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.bez_allg.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.container_nr.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.container_nr)), WIEGEKARTE.container_nr.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.container_nr))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.container_nr.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.container_nr))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.container_nr.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.druckzaehler.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.druckzaehler)), WIEGEKARTE.druckzaehler.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.druckzaehler))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.druckzaehler.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.druckzaehler))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.druckzaehler.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.druckzaehler_eingangsschein.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.druckzaehler_eingangsschein)), WIEGEKARTE.druckzaehler_eingangsschein.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.druckzaehler_eingangsschein))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.druckzaehler_eingangsschein.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.druckzaehler_eingangsschein))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.druckzaehler_eingangsschein.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.es_nr.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.es_nr)), WIEGEKARTE.es_nr.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.es_nr))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.es_nr.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.es_nr))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.es_nr.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.gedruckt_am.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.gedruckt_am)), WIEGEKARTE.gedruckt_am.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gedruckt_am))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.gedruckt_am.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gedruckt_am))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.gedruckt_am.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.gewicht_abzug.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.gewicht_abzug)), WIEGEKARTE.gewicht_abzug.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_abzug))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_abzug.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_abzug))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_abzug.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.gewicht_abzug_fuhre.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.gewicht_abzug_fuhre)), WIEGEKARTE.gewicht_abzug_fuhre.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_abzug_fuhre))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_abzug_fuhre.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_abzug_fuhre))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_abzug_fuhre.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.gewicht_nach_abzug.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.gewicht_nach_abzug)), WIEGEKARTE.gewicht_nach_abzug.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_nach_abzug))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_nach_abzug.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_nach_abzug))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_nach_abzug.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.gewicht_nach_abzug_fuhre.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.gewicht_nach_abzug_fuhre)), WIEGEKARTE.gewicht_nach_abzug_fuhre.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_nach_abzug_fuhre))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_nach_abzug_fuhre.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.gewicht_nach_abzug_fuhre))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.gewicht_nach_abzug_fuhre.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.grund_abzug.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.grund_abzug)), WIEGEKARTE.grund_abzug.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.grund_abzug))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.grund_abzug.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.grund_abzug))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.grund_abzug.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_adresse_abn_strecke.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_adresse_abn_strecke)), WIEGEKARTE.id_adresse_abn_strecke.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_abn_strecke))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_abn_strecke.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_abn_strecke))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_abn_strecke.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_adresse_lager.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_adresse_lager)), WIEGEKARTE.id_adresse_lager.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_lager))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_lager.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_lager))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_lager.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_adresse_lieferant.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_adresse_lieferant)), WIEGEKARTE.id_adresse_lieferant.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_lieferant))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_lieferant.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_lieferant))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_lieferant.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_adresse_spedition.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_adresse_spedition)), WIEGEKARTE.id_adresse_spedition.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_spedition))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_spedition.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_adresse_spedition))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_adresse_spedition.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_artikel_bez.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_artikel_bez)), WIEGEKARTE.id_artikel_bez.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_artikel_bez))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_artikel_bez.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_artikel_bez))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_artikel_bez.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_artikel_sorte.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_artikel_sorte)), WIEGEKARTE.id_artikel_sorte.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_artikel_sorte))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_artikel_sorte.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_artikel_sorte))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_artikel_sorte.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_vpos_tpa_fuhre.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_vpos_tpa_fuhre)), WIEGEKARTE.id_vpos_tpa_fuhre.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_vpos_tpa_fuhre))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_vpos_tpa_fuhre.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_vpos_tpa_fuhre))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_vpos_tpa_fuhre.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_vpos_tpa_fuhre_ort)), WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_vpos_tpa_fuhre_ort))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_vpos_tpa_fuhre_ort))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_waage_standort.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_waage_standort)), WIEGEKARTE.id_waage_standort.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_waage_standort))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_waage_standort.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_waage_standort))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_waage_standort.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_wiegekarte.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_wiegekarte)), WIEGEKARTE.id_wiegekarte.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_wiegekarte))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_wiegekarte.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_wiegekarte))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_wiegekarte.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.id_wiegekarte_parent.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.id_wiegekarte_parent)), WIEGEKARTE.id_wiegekarte_parent.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_wiegekarte_parent))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.id_wiegekarte_parent.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.id_wiegekarte_parent))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.id_wiegekarte_parent.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.ist_gesamtverwiegung.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.ist_gesamtverwiegung)), WIEGEKARTE.ist_gesamtverwiegung.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.ist_gesamtverwiegung))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.ist_gesamtverwiegung.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.ist_gesamtverwiegung))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.ist_gesamtverwiegung.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.ist_lieferant.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.ist_lieferant)), WIEGEKARTE.ist_lieferant.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.ist_lieferant))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.ist_lieferant.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.ist_lieferant))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.ist_lieferant.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.kennzeichen.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.kennzeichen)), WIEGEKARTE.kennzeichen.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.kennzeichen))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.kennzeichen.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.kennzeichen))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.kennzeichen.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.lfd_nr.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.lfd_nr)), WIEGEKARTE.lfd_nr.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.lfd_nr))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.lfd_nr.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.lfd_nr))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.lfd_nr.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.nettogewicht.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.nettogewicht)), WIEGEKARTE.nettogewicht.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.nettogewicht))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.nettogewicht.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.nettogewicht))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.nettogewicht.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.siegel_nr.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.siegel_nr)), WIEGEKARTE.siegel_nr.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.siegel_nr))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.siegel_nr.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.siegel_nr))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.siegel_nr.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.sorte_hand.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.sorte_hand)), WIEGEKARTE.sorte_hand.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.sorte_hand))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.sorte_hand.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.sorte_hand))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.sorte_hand.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.storno.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.storno)), WIEGEKARTE.storno.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.storno))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.storno.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.storno))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.storno.fn());
        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.strahlung_geprueft.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.strahlung_geprueft)), WIEGEKARTE.strahlung_geprueft.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.strahlung_geprueft))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.strahlung_geprueft.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.strahlung_geprueft))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.strahlung_geprueft.fn());
        // ----
        //
//        // spaltenlayout fuer:  WIEGEKARTE.sys_trigger_timestamp.fn()
//        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.sys_trigger_timestamp)), WIEGEKARTE.sys_trigger_timestamp.fn());
//        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.sys_trigger_timestamp))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.sys_trigger_timestamp.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.sys_trigger_timestamp))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.sys_trigger_timestamp.fn());
//        // ----
//        //
//        // spaltenlayout fuer:  WIEGEKARTE.sys_trigger_uuid.fn()
//        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.sys_trigger_uuid)), WIEGEKARTE.sys_trigger_uuid.fn());
//        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.sys_trigger_uuid))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.sys_trigger_uuid.fn());
//        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.sys_trigger_uuid))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.sys_trigger_uuid.fn());
//        // ----
        //
        // spaltenlayout fuer:  WIEGEKARTE.typ_wiegekarte.fn()
        this._setColExtent(     new Extent(WK_RB_READABLE_FIELD_NAME.getMaxComponentOrLabelSize(WIEGEKARTE.typ_wiegekarte)), WIEGEKARTE.typ_wiegekarte.fn());
        this._setLayoutElements(new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.typ_wiegekarte))._ins(3,1,3,1)._col(new E2_ColorDark()), WIEGEKARTE.typ_wiegekarte.fn());
        this._setLayoutTitles(  new RB_gld()._al(WK_RB_READABLE_FIELD_NAME.getAlignment(WIEGEKARTE.typ_wiegekarte))._ins(1,2,1,1)._col(new E2_ColorDark()), WIEGEKARTE.typ_wiegekarte.fn());
        // ----
        //
      	
        this.set_oSubQueryAgent(new WK_RB_LIST_FORMATING_Agent(this.m_tpHashMap));
        	
//        this.set_Factory4Records(new factory4Records());
    }
    
//    private class factory4Records extends E2_ComponentMAP_Factory4Records {
//        @Override
//        public MyRECORD_IF_RECORDS get_RECORD(String cID_MAINTABLE) throws myException {
//            return new RECORD_WIEGEKARTE(cID_MAINTABLE);
//        }
//    }
    
    
    @Override
    public Object get_Copy(Object objHelp) throws myExceptionCopy {
        E2_ComponentMAP_V22 oRueck = new E2_ComponentMAP_V22(this.get_oSQLFieldMAP());
        E2_ComponentMAP.Copy_FieldsAndSettings(this, oRueck);
        oRueck.setComponentMapMarker(new WK_RB_LIST_ComponentMapMapMarker(oRueck));
	
        return oRueck;
    }
    
	/*
     * umstellung auf E2_ComponentMAP_V2 mit marker-option
     * korrespontiert mit  
     */
    public class WK_RB_LIST_ComponentMapMapMarker extends E2_ComponentMapMarker {
        public WK_RB_LIST_ComponentMapMapMarker(E2_ComponentMAP_V22 p_map) {
            super(p_map);
        }
        @Override
        protected void innerFormat(Collection<Component> v) {
            super.innerFormat(v);
    		
            E2_ComponentMAP_V22 map = (E2_ComponentMAP_V22)this.getMap();
    		
            try {
                Rec22 recWK_RB = map.getRec22();
                //hier koennen formatierungsoptionen hinterlegt werden
                
//                if (recWK_RB.is_no_db_val(WIEGEKARTE.aktiv)) {
//                    this.setLayoutBackgroundColorInMap(v,new Color(230,230,230));
//                    this.setTextColorInMap(v,Color.DARKGRAY);
//                }
    			
            } catch (myException e) {
                e.printStackTrace();
            }
        }
    }
  
    
}
 
 
