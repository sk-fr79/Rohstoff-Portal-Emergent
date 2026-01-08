package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.AH7_STEUERDATEI;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_MLabelStatusFieldKey;

public class AH7_MASK_MaskGrid extends E2_Grid {
    public AH7_MASK_MaskGrid(AH7_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(320,320,320,50)._bo_no();
        
        AH7_MASK_ComponentMap  map1 = (AH7_MASK_ComponentMap) mapColl.get(new AH7_KEY());
        
        RB_gld lt = new RB_gld()._ins(2)._left_top();
        RB_gld lt3 = new RB_gld()._ins(2)._left_top()._span(3);
        
        String warnhinweis = "ACHTUNG!! Datensätze ohne Schalter <Für Automatikläufe gesperrt> werden beim nächsten Lauf wieder überschrieben !";
        
        E2_Grid g_help = new E2_Grid()._setSize(120,480)._a(map1.getComp(AH7_STEUERDATEI.id_ah7_steuerdatei), lt._c()._span(1)._ins(0, 0, 10, 0))
        												._a(new RB_lab()._tr(warnhinweis)._b()._lw(), new RB_gld()._col_back_alarm()._span(1));
        
//        this._a(new RB_lab("ID") ,lt)												._a(map1.getComp(AH7_STEUERDATEI.id_ah7_steuerdatei), lt._c()._span(1))
//        																			._a(new RB_lab()._tr(warnhinweis)._b(), new RB_gld()._col_back_alarm()._span(1))
//        																			._a(new RB_lab())
//        		                                    								;

        this._a(new RB_lab("ID") ,lt)												._a(g_help, lt3);

        
        this._a(new RB_lab("Zugeordnetes Profil") ,lt)								._a(map1.getComp(AH7_STEUERDATEI.id_ah7_profil), lt3._c()._span(2))
        																			._a(map1.getComp(new RB_KF(AH7__CONST.AH7_B_BUTTONS.MASK_BUTTON_ZEIGE_PROFIL.db_val())), lt3._c()._span(1));

        this.addSeparator();

        this._a(new RB_lab("Quelle (geografisch)") ,lt)								._a(map1.getComp(AH7_STEUERDATEI.id_adresse_geo_start), lt3);
        this._a(new RB_lab("Quellfirma (juristisch)") ,lt)							._a(map1.getComp(AH7_STEUERDATEI.id_adresse_jur_start), lt3);

        this.addSeparator();
        
        this._a(new RB_lab("Ziel (geografisch)") ,lt)								._a(map1.getComp(AH7_STEUERDATEI.id_adresse_geo_ziel), lt3);
        this._a(new RB_lab("Zielfirma (juristisch)") ,lt)							._a(map1.getComp(AH7_STEUERDATEI.id_adresse_jur_ziel), lt3);
      
        this.addSeparator();
        
        this._a(new RB_lab("Blatt 1 für Kontrolle")._fo_bold() ,lt)						._a(new RB_lab(""), lt3);
        this._a(new RB_lab("(1) Verbringungsveranlasser (AH7 Kontrollblatt)") ,lt)		._a(map1.getComp(AH7_STEUERDATEI.id_1_verbr_veranlasser), lt3);
        this._a(new RB_lab("(2) Importeur/Empfänger (AH7 Kontrollblatt)") ,lt)			._a(map1.getComp(AH7_STEUERDATEI.id_1_import_empfaenger), lt3);
        this._a(new RB_lab("(6) Abfallerzeuger (AH7 Kontrollblatt)") ,lt)				._a(map1.getComp(AH7_STEUERDATEI.id_1_abfallerzeuger), lt3);
        this._a(new RB_lab("(7) Verwertungsanlage (AH7 Kontrollblatt)") ,lt)			._a(map1.getComp(AH7_STEUERDATEI.id_1_verwertungsanlage), lt3);

        this.addSeparator();

        this._a(new RB_lab("Blatt 2 für Abladestelle nötig")._fo_bold() ,lt)		._a(map1.getComp(AH7_STEUERDATEI.drucke_blatt2), lt3);
        this._a(new RB_lab("(1) Verbringungsveranlasser (AH7 Abladestelle)") ,lt)		._a(map1.getComp(AH7_STEUERDATEI.id_2_verbr_veranlasser), lt3);
        this._a(new RB_lab("(2) Importeur/Empfänger (AH7 Abladestelle)") ,lt)			._a(map1.getComp(AH7_STEUERDATEI.id_2_import_empfaenger), lt3);
        this._a(new RB_lab("(6) Abfallerzeuger (AH7 Abladestelle)") ,lt)				._a(map1.getComp(AH7_STEUERDATEI.id_2_abfallerzeuger), lt3);
        this._a(new RB_lab("(7) Verwertungsanlage (AH7 Abladestelle)") ,lt)				._a(map1.getComp(AH7_STEUERDATEI.id_2_verwertungsanlage), lt3);
        
        this.addSeparator();

        this._a(new RB_lab("Blatt 3 für Ladestelle nötig")._fo_bold() ,lt)			._a(map1.getComp(AH7_STEUERDATEI.drucke_blatt3), lt3);
        this._a(new RB_lab("(1) Verbringungsveranlasser (AH7 Ladestelle)") ,lt)			._a(map1.getComp(AH7_STEUERDATEI.id_3_verbr_veranlasser), lt3);
        this._a(new RB_lab("(2) Importeur/Empfänger (AH7 Ladestelle)") ,lt)				._a(map1.getComp(AH7_STEUERDATEI.id_3_import_empfaenger), lt3);
        this._a(new RB_lab("(6) Abfallerzeuger (AH7 Ladestelle)") ,lt)					._a(map1.getComp(AH7_STEUERDATEI.id_3_abfallerzeuger), lt3);
        this._a(new RB_lab("(7) Verwertungsanlage (AH7 Ladestelle)") ,lt)				._a(map1.getComp(AH7_STEUERDATEI.id_3_verwertungsanlage), lt3);

        this.addSeparator();

        this._a(new RB_lab("Für Automatikläufe gesperrt") ,lt)						._a(map1.getComp(AH7_STEUERDATEI.locked), lt3);
        
        E2_Grid  g_status = new E2_Grid()._s(2)._a(map1.getComp(AH7_STEUERDATEI.status_relation), new RB_gld()._ins(0))
												._a(map1.getComp(new AH7_MLabelStatusFieldKey(_TAB.ah7_steuerdatei)), new RB_gld()._ins(3,0,0,0));

        this._a(new RB_lab("Status dieser Relation") ,lt)							._a(g_status, lt3);
    
    }
  
    
}
 
