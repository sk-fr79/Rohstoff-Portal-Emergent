 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
  
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.DLP_PROFIL;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
 
public class DL_MASK_MaskGrid extends E2_Grid {
 	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
	/*
	 * vector nimmt alle container auf, die reale felder enthalten
	 * und die zugehoerigen tab-texte (falls mehr als ein container noetig ist) 
	 */
    
	private VEK<E2_Grid>   fieldContainers = 	new VEK<E2_Grid>();
    private VEK<MyString>  tabText = 			new VEK<MyString>();
    
    public DL_MASK_MaskGrid(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        int iWidthComplete = DL_CONST.DL_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue()+
                                  DL_CONST.DL_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue()+20;
        this._setSize(iWidthComplete)._bo_no();
 
        
        this._setSize(800)._bo_no();
        
        this.m_tpHashMap = p_tpHashMap;
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_GRID,this);
        
        DL_MASK_ComponentMap  map1 = (DL_MASK_ComponentMap) this.m_tpHashMap.getMaskComponentMapCollector().get(this.m_tpHashMap.getLeadingMaskKey());
        
        //beginn erster tab
        E2_Grid g1 = fieldContainers._ar(new E2_Grid()._setSize( DL_CONST.DL_NUM_CONST.MASKPOPUP_DESCRIPTION_COL_SIZE.getValue(),
        															DL_CONST.DL_NUM_CONST.MASKPOPUP_FIELD_COL_SIZE.getValue())._bo_no());        
        
        int iZahl = 1;
        
        RB_gld ldTexte = new RB_gld()._ins(2,5,2,2)._al(E2_ALIGN.LEFT_TOP);
        
        tabText._a(S.ms("Tab "+(iZahl++)));
        
        
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_dlp_profil)) ,ldTexte)
        	._a(map1.getComp(DLP_PROFIL.id_dlp_profil), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.aktiv)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.aktiv), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.beschreibung)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.beschreibung), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.gilt_ab_datum)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.gilt_ab_datum), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        
        g1._addSeparator();
        g1	._a(new RB_lab(S.ms("Vergleichblock mit der auslösenden Fuhre"))._i()._fsa(-2) ,new RB_gld()._ins(2,2,2,2)._span(2));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_start)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_adresse_start), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_ziel)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_adresse_ziel), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_fremdware)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_adresse_fremdware), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_artikel), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel_bez_quelle)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_artikel_bez_quelle), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel_bez_ziel)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_artikel_bez_ziel), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.anwenden_auf_typ)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.anwenden_auf_typ), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1._addSeparator();
        g1	._a(new RB_lab(S.ms("Definition der resultierenden Fuhre (Dienstleistungsabrechnung)"))._i()._fsa(-2) ,new RB_gld()._ins(2,2,2,2)._span(2));

        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.typ_mengen_calc)) ,ldTexte)
        	._a(map1.getComp(DLP_PROFIL.typ_mengen_calc), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.anteil_menge)) ,ldTexte)
        	._a(map1.getComp(DLP_PROFIL.anteil_menge), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.umrech_mge_quelle_ziel)) ,ldTexte)
        	._a(	new E2_Grid()._s(2)._a(map1.getComp(DLP_PROFIL.umrech_mge_quelle_ziel))._a(new RB_lab()
        								._tr("Bsp: Umrechnungsfaktor 1000: 1000 kg Aluspäne -> 1 t Brikettieren von Alu")._fsa(-2)._i(), new RB_gld()._ins(10,0,0,0)._left_mid())
        															, new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));
        
        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_artikel_bez_dl)) ,ldTexte)
        	._a(map1.getComp(DLP_PROFIL.id_artikel_bez_dl), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_buchungslager)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_adresse_buchungslager), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

        g1	._a(new RB_lab(DL_READABLE_FIELD_NAME.getReadable(DLP_PROFIL.id_adresse_rechnung)) ,ldTexte)
    		._a(map1.getComp(DLP_PROFIL.id_adresse_rechnung), new RB_gld()._ins(2,2,2,2)._al(E2_ALIGN.LEFT_MID));

         
        
        
        this.renderMask();
        
    }
    
    
    private void renderMask() throws myException {
    
  		this._a(this.fieldContainers.get(0));
    }

    
    
    

}
 
 
