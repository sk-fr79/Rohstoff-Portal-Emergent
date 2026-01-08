 
package rohstoff.businesslogic.bewegung2.list;
  
import java.util.Collection;

import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP_IF_Rec21;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMapMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.E2_ButtonAttachmentUseInListRow;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.validation.ENUM_VALIDATION;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibEcho;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.businesslogic.bewegung2.global.EnBgFieldList;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_ListBtDeleteInListRow;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_ListBtListToMaskInListRow;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_ListBtStornoInListRow;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_ListComponentDiverseButtons;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_ListLabelFuhrenTyp;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listCompArtikel;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listCompKontrakt;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listCompLadung;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listCompMenge;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listCompPosten;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listCompStation;
import rohstoff.businesslogic.bewegung2.list.listComponents.B2_listComp_id_bg_vektor;
  
public class B2_ListComponentMap extends E2_ComponentMAP implements E2_ComponentMAP_IF_Rec21 {
	
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public B2_ListComponentMap(RB_TransportHashMap  p_tpHashMap, boolean isCopy) throws myException  {
    	super();
    	this.m_tpHashMap = p_tpHashMap;        
    	if (!isCopy) {
    		this._init();
    	}
    	this.setComponentMapMarker(new OwnComponentMapMarker(this));
    }  
    
    public B2_ListComponentMap(RB_TransportHashMap  p_tpHashMap) throws myException  {
    	this(p_tpHashMap,false);
    }
    
    

    protected B2_ListComponentMap _init() throws myException {
    	
    	this.set_oSQLFieldMAP(new B2_ListSqlFieldMAP(this.m_tpHashMap));
    	
        SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
        
        this.add_Component(B2_ConstEnumNames.CHECKBOX_LISTE.db_val(), new E2_CheckBoxForList(),new MyE2_String("?"))._setGridLayout4List(new RB_gld()._ins(4));
        this.add_Component(B2_ConstEnumNames.MARKER_LISTE.db_val(),   new E2_ButtonListMarker(),new MyE2_String("?"))._setGridLayout4List(new RB_gld()._ins(4));
        
        this.add_Component(B2_ConstEnumNames.SHOW_TYPE.db_val(), 	  	new B2_ListLabelFuhrenTyp(),new MyE2_String("T"))
        																._setGridLayout4List(new RB_gld()._ins(4,4,4,4))
																        ._setLongText4ColumnSelection(S.ms("Anzeige des Fuhrentyps"))
																        ;
        
        //hier optionale spalten fuer direktes loeschen/edit/view
        this.add_Component(B2_ConstEnumNames.DIRECT_STORNO.db_val(),   	new B2_ListBtStornoInListRow(this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4,6,4,4))
        																			._setLongText4ColumnSelection(S.ms("Stornobutton in der Listenzeile")),	
        																new MyE2_String("S"));
        this.add_Component(B2_ConstEnumNames.DIRECT_DEL.db_val(),    	new B2_ListBtDeleteInListRow(this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4))
																					._setLongText4ColumnSelection(S.ms("Löschknopf in der Listenzeile")),	
																		new MyE2_String("D"));
        
        this.add_Component(B2_ConstEnumNames.DIRECT_EDIT.db_val(),   	new B2_ListBtListToMaskInListRow(true,this.m_tpHashMap)
        																			._setGridLayout4List(new RB_gld()._ins(4,6,4,4))
        																			._setLongText4ColumnSelection(S.ms("Bearbeitungsknopf in der Listenzeile")),
        																new MyE2_String("?"));
        this.add_Component(B2_ConstEnumNames.DIRECT_VIEW.db_val(),   	new B2_ListBtListToMaskInListRow(false,this.m_tpHashMap)
																					._setGridLayout4List(new RB_gld()._ins(4,6,4,4))
																					._setLongText4ColumnSelection(S.ms("Anzeigeknopf in der Listenzeile")),
																		new MyE2_String("?"));
        this.add_Component(B2_ConstEnumNames.DIRECT_UPLOAD.db_val(), new E2_ButtonAttachmentUseInListRow()
																					._addGlobalValidator(ENUM_VALIDATION.BG_TRANSPORT_ATTACHMENT.getValidator())
        																			._setGridLayout4List(new RB_gld()._ins(4,6,4,4))
        																			._setLongText4ColumnSelection(S.ms("Anlagen verwalten"))
        																			, S.ms("?"));
        
        //hier kommen die Felder  
        this.add_Component(new MyE2_DB_Label_INGRID(oFM.get_(BG_VEKTOR.id_bg_vektor),true),     new MyE2_String("ID"));
        this.add_Component(new B2_listComp_id_bg_vektor());
      	this.add_Component(new B2_ListComponentDiverseButtons(this.m_tpHashMap))._setLongText4ColumnSelection(S.ms("Abrechnungsstatus RE/GS"));
      	this.add_Component(new B2_listCompStation(EnBgFieldList.S1_ID_BG_STATION));
      	this.add_Component(new B2_listCompStation(EnBgFieldList.S3_ID_BG_STATION));
      	this.add_Component(new B2_listCompKontrakt(EnBgFieldList.VP1_ID_VPOS_KON));
      	this.add_Component(new B2_listCompKontrakt(EnBgFieldList.VP2_ID_VPOS_KON));
        
      	
      	this.add_Component(new B2_listCompLadung(EnBgFieldList.A1_ID_BG_ATOM));
      	this.add_Component(new B2_listCompLadung(EnBgFieldList.A2_ID_BG_ATOM));

      	this.add_Component(new B2_listCompArtikel(EnBgFieldList.AB1_ID_ARTIKEL_BEZ));
      	this.add_Component(new B2_listCompArtikel(EnBgFieldList.AB2_ID_ARTIKEL_BEZ));
      	
      	this.add_Component(new B2_listCompPosten(EnBgFieldList.A1_ID_BG_ATOM));
      	
      	this.add_Component(new B2_listCompMenge(EnBgFieldList.A1_ID_BG_ATOM));
      	this.add_Component(new B2_listCompMenge(EnBgFieldList.A2_ID_BG_ATOM));
      	
//      	this.add_Component(new B2_listCompLkwWiegekarte(EnBgFieldList.ID_BG_TRANSPORT));
//      	
//      	this.add_Component(new B2_listCompLieferbedingung(EnBgFieldList.A1_ID_BG_ATOM));
//      	
//      	this.add_Component(new B2_listCompPreis(EnBgFieldList.A1_ID_BG_ATOM));
//      	this.add_Component(new B2_listCompPreis(EnBgFieldList.A2_ID_BG_ATOM));
//      	
//      	this.add_Component(new B2_listCompBuchungsnummernBlock(EnBgFieldList.ID_BG_TRANSPORT));
//      	
//      	this.add_Component(new B2_listCompRechnungGutschrift(EnBgFieldList.A1_ID_BG_ATOM));
//      	this.add_Component(new B2_listCompRechnungGutschrift(EnBgFieldList.A2_ID_BG_ATOM));
//      	
//      	this.add_Component(new B2_listCompLieferAbholdatum(EnBgFieldList.ID_BG_TRANSPORT));
//      	
//      	this.add_Component(new B2_listCompStornoInfo(EnBgFieldList.ID_BG_TRANSPORT));
//      	
//      	this.add_Component(new B2_listCompLkwFahrer(EnBgFieldList.ID_BG_TRANSPORT));
//      	
//    	this.add_Component(new B2_listCompDatumLadeAblade(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	this.add_Component(new B2_listCompContainerSorte(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	this.add_Component(new B2_listCompTatigkeitBemerkung(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	this.add_Component(new B2_listCompFahrplan(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	
//    	this.add_Component(new B2_listCompContainerAnzahl(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	this.add_Component(new B2_listCompGruppeEANCode(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	this.add_Component(new B2_listCompVormerkzeit(EnBgFieldList.ID_BG_TRANSPORT));
//    	
////    	this.add_Component(new B2_listCompFahrplan(EnBgFieldList.ID_BG_TRANSPORT));
//    	
//    	this.add_Component(new B2_listCompAnruf(EnBgFieldList.ID_BG_TRANSPORT));
    	
    	
        this._setColExtent(new Extent(200),EnBgFieldList.S1_ID_BG_STATION.name(), EnBgFieldList.S1_ID_BG_STATION.name());
        
        
        this._clearSubQueryAgents()._addSubQueryFormatter((map,result)-> {
				try {
					if (((E2_ComponentMAP_IF_Rec21)map).getRec21().getValue(BG_VEKTOR.id_bg_del_info,null)!=null) {
						map.set_AllComponentsAsDeleted();
					}
				} catch (myException e) {
					e.printStackTrace();
				}
        		});
        
        this._addSubQueryAgent(new B2_ListFormatingAgent(this.m_tpHashMap));
        
        //stronierte grau machen
        this._addSubQueryFormatter((map,result)->{
        	try {
				if (  S.isFull(((E2_ComponentMAP_IF_Rec21)map).getRec21().getUfs("",BG_VEKTOR.id_bg_storno_info))) {
					bibEcho.setGenericForeGround(new E2_ColorGray(100), new VEK<MyE2IF__Component>()._a(map.values()));
				}
			} catch (myException e) {
				e.printStackTrace();
			}
        });
    	
    	return this;
    }
    
    
  
	public  B2_ListComponentMap get_Copy(Object objHelp)	{
		B2_ListComponentMap ret=null;;
		try {
			ret = new B2_ListComponentMap(this.m_tpHashMap,true);
			ret.set_oSQLFieldMAP(this.get_oSQLFieldMAP());
			E2_ComponentMAP.Copy_FieldsAndSettings(this, ret);
		} catch (myException e) {
			e.printStackTrace();
			ret=null;
		}
		return ret;
	}

	
	
	private class OwnComponentMapMarker extends E2_ComponentMapMarker {

		/**
		 * @author martin
		 * @date 08.03.2019
		 *
		 * @param p_map
		 */
		public OwnComponentMapMarker(E2_ComponentMAP p_map) {
			super(p_map);
		}

		@Override
		protected void innerFormat(Collection<Component> v) {
			super.innerFormat(v);
			
			if (this.getMap().isChecked()) {
				this.setLayoutBackgroundColorInMap(this.getAllActiveComponents(), Color.YELLOW);
			}
			
			
		}
		
		
		
	}
	

}
 
 
