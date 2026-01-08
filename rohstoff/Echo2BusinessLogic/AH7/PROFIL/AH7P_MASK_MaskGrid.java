package rohstoff.Echo2BusinessLogic.AH7.PROFIL;


import echopointng.Separator;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.AH7_PROFIL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7_MLabelStatusFieldKey;


public class AH7P_MASK_MaskGrid extends E2_Grid {
    public AH7P_MASK_MaskGrid(AH7P_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        
        AH7P_MASK_ComponentMap  map1 = (AH7P_MASK_ComponentMap) mapColl.get(new AH7P_KEY());

        this._setSize(230,1100)._bo_no();

        RB_gld gld_mask = new RB_gld()._left_top()._ins(2,4,2,2);
        
        //mask zeile 1
        this._a(new RB_lab("ID") ,gld_mask)._a(map1.getComp(AH7_PROFIL.id_ah7_profil), gld_mask);


        //mask zeile 1
        this._a(new RB_lab("Bezeichnung") ,gld_mask)._a(map1.getComp(AH7_PROFIL.bezeichnung), gld_mask);
        this._a(new Separator(), gld_mask._c()._span(2));
        
        this._a(new RB_lab()._tr("Vergleichsblock für automatische Bewertungsläufe ...")._bi()._fsa(2), new RB_gld()._span(this.getSize())._col_back_dd()._ins(2, 10, 2, 2));
        
        
        //vergleichsblock
        E2_Grid g_vgl = new E2_Grid()._setSize(180,100,120,80,  180,100,120,80)._bo_ddd();
        
        
        
        //zeile 1
        g_vgl	._a(new RB_lab()._tr("Quelle")._fo_bold(), new RB_gld()._span(4)._ins(2)._center_top()._col(new E2_ColorDDark()))
        		._a(new RB_lab()._tr("Ziel")._fo_bold(), new RB_gld()._span(4)._ins(2)._center_top()._col(new E2_ColorDDark()));
        
        //zeile 2
        g_vgl	._a(new RB_lab()._tr("Lager von ...")._lw(), 				new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
        		._a(new RB_lab()._tr("Liegt wo ?")._lw(), 					new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
        		._a(new RB_lab()._tr("Sicherheit Quelle")._lw(), 			new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
        		._a(new RB_lab()._tr("Quelle hat Fremd-Besitzer")._lw(), 	new RB_gld()._ins(2)._center_top()._col(new E2_ColorDark()))
        		
        		._a(new RB_lab()._tr("Lager von ...")._lw(), 				new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
        		._a(new RB_lab()._tr("Liegt wo ?")._lw(), 					new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
        		._a(new RB_lab()._tr("Sicherheit Ziel")._lw(), 				new RB_gld()._ins(2)._left_top()._col(new E2_ColorDark()))
        		._a(new RB_lab()._tr("Ziel hat Fremd-Besitzer")._lw(), 		new RB_gld()._ins(2)._center_top()._col(new E2_ColorDark()))
        		;
        

        //zeile 3
        g_vgl	._a(map1.getComp(AH7_PROFIL.start_eigenes_lager), 			new RB_gld()._ins(2)._left_top())
		        ._a(map1.getComp(AH7_PROFIL.start_inland), 					new RB_gld()._ins(2)._left_top())
		        ._a(map1.getComp(AH7_PROFIL.ah7_quelle_sicher), 			new RB_gld()._ins(2)._left_top())
		        ._a(map1.getComp(AH7_PROFIL.startlager_fremdbesitz), 		new RB_gld()._ins(2)._center_top())
		        ._a(map1.getComp(AH7_PROFIL.ziel_eigenes_lager), 			new RB_gld()._ins(2)._left_top())
		        ._a(map1.getComp(AH7_PROFIL.ziel_inland), 					new RB_gld()._ins(2)._left_top())
		        ._a(map1.getComp(AH7_PROFIL.ah7_ziel_sicher), 				new RB_gld()._ins(2)._left_top())
		        ._a(map1.getComp(AH7_PROFIL.ziellager_fremdbesitz), 		new RB_gld()._ins(2)._center_top())
		        ;
        
        this	._a(new RB_lab()._tr("Spezielles Profil (Priorisiert in der Bewertung)"), gld_mask)._a(g_vgl, gld_mask);
        this._a(new RB_lab("Allgemeines Profil (untergeordnete Bewertung)") ,gld_mask._c()._ins(2,8,2,2))._a(map1.getComp(AH7_PROFIL.profile4allothers), gld_mask._c()._ins(2,8,2,2));
        
        this._a(new Separator(), gld_mask._c()._span(2));

        
        
        
        E2_Grid  gControll = new E2_Grid()._setSize(300,300)._bo_dd();
        gControll	._a(new RB_lab()._tr("Feld auf Anhang 7")._fo_bold(),	gld_mask._c()._col(new E2_ColorDDark()))
        			._a(new RB_lab()._tr("Gedruckt auf AH7")._fo_bold(),	gld_mask._c()._col(new E2_ColorDDark()));
        
        gControll	._a(new RB_lab()._tr("1. Verbringungsveranlasser"),	gld_mask)
        			._a(map1.getComp(AH7_PROFIL.verbr_veranlasser_1),	gld_mask);
        gControll	._a(new RB_lab()._tr("2. Importeur/Empfänger"),		gld_mask)
        			._a(map1.getComp(AH7_PROFIL.import_empfaenger_1),	gld_mask);
        gControll	._a(new RB_lab()._tr("6. Abfallerzeuger"),			gld_mask)
        			._a(map1.getComp(AH7_PROFIL.abfallerzeuger_1),		gld_mask);
        gControll	._a(new RB_lab()._tr("7. Verwertungsanlage"),		gld_mask)
					._a(map1.getComp(AH7_PROFIL.verwertungsanlage_1),	gld_mask);
        
        this._a(new RB_lab()._tr("Anhang7 für Kontrolle")._b(), new RB_gld()._left_top()._ins(2, 16, 2, 2))
					._a(gControll, new RB_gld()._left_top()._ins(2, 10, 2, 2));
        

        
        E2_Grid  gAbladestelle = new E2_Grid()._setSize(300,300)._bo_dd();
        gAbladestelle._a(new RB_lab()._tr("Feld auf Anhang 7")._fo_bold(),	gld_mask._c()._col(new E2_ColorDDark()))
        			._a(new RB_lab()._tr("Gedruckt auf AH7")._fo_bold(),	gld_mask._c()._col(new E2_ColorDDark()));

        gAbladestelle	._a(new RB_lab()._tr("1. Verbringungsveranlasser"),	gld_mask)
        			._a(map1.getComp(AH7_PROFIL.verbr_veranlasser_2),		gld_mask);
        gAbladestelle	._a(new RB_lab()._tr("2. Importeur/Empfänger"),		gld_mask)
        			._a(map1.getComp(AH7_PROFIL.import_empfaenger_2),		gld_mask);
        gAbladestelle	._a(new RB_lab()._tr("6. Abfallerzeuger"),			gld_mask)
        			._a(map1.getComp(AH7_PROFIL.abfallerzeuger_2),			gld_mask);
        gAbladestelle	._a(new RB_lab()._tr("7. Verwertungsanlage"),		gld_mask)
					._a(map1.getComp(AH7_PROFIL.verwertungsanlage_2),		gld_mask);
        this._a(new RB_lab()._tr("Anhang7 für Abladestelle")._b(), new RB_gld()._left_top()._ins(2, 16, 2, 2))
        			._a(gAbladestelle, new RB_gld()._left_top()._ins(2, 10, 2, 2));
        


        
        E2_Grid  gLadestelle = new E2_Grid()._setSize(300,300)._bo_dd();
        gLadestelle._a(new RB_lab()._tr("Feld auf Anhang 7")._fo_bold(),	gld_mask._c()._col(new E2_ColorDDark()))
					._a(new RB_lab()._tr("Gedruckt auf AH7")._fo_bold(),	gld_mask._c()._col(new E2_ColorDDark()));
        gLadestelle	._a(new RB_lab()._tr("1. Verbringungsveranlasser"),	gld_mask)
        			._a(map1.getComp(AH7_PROFIL.verbr_veranlasser_3),	gld_mask);
        gLadestelle	._a(new RB_lab()._tr("2. Importeur/Empfänger"),		gld_mask)
        			._a(map1.getComp(AH7_PROFIL.import_empfaenger_3),	gld_mask);
        gLadestelle	._a(new RB_lab()._tr("6. Abfallerzeuger"),			gld_mask)
        			._a(map1.getComp(AH7_PROFIL.abfallerzeuger_3),		gld_mask);
        gLadestelle	._a(new RB_lab()._tr("7. Verwertungsanlage"),		gld_mask)
					._a(map1.getComp(AH7_PROFIL.verwertungsanlage_3),	gld_mask);
        this._a(new RB_lab()._tr("Anhang7 für Ladestelle")._b(), new RB_gld()._left_top()._ins(2, 16, 2, 2))
        	._a(gLadestelle, new RB_gld()._left_top()._ins(2, 10, 2, 2));
   
        E2_Grid  g_status = new E2_Grid()._s(2)._a(map1.getComp(AH7_PROFIL.status_relation), new RB_gld()._ins(0))
        		._a(map1.getComp(new AH7_MLabelStatusFieldKey(_TAB.ah7_profil)), new RB_gld()._ins(3,0,0,0));
        
        this	._a(new RB_lab()._tr("Automatisch zu vergebener Status"), 	gld_mask._c()._ins(2,10,2,2))
        		._a(g_status, 												gld_mask._c()._ins(2,10,2,2));
    
    }
  
    
}
 
