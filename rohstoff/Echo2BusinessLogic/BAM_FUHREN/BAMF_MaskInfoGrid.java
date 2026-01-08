/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FBAM;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE_ORT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_TPA_FUHRE_FromView;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

/**
 * @author martin
 *
 */
public class BAMF_MaskInfoGrid extends E2_Grid {


	public BAMF_MaskInfoGrid() throws myException {
		super();
	}
	
	public BAMF_MaskInfoGrid _render(BAMF_MASK_ComponentMAP map) throws myException {
		//unterscheiden, ob neueingabe, dann kommen die infos ueber die felder des maskcontainer
		//oder bearbeiten, dann ueber die sqlresultmap der maske
	
		SQLResultMAP    resultMap = map.get_oInternalSQLResultMAP();
		
		Rec20_VPOS_TPA_FUHRE_FromView  	recFU = null; 
		Rec20    						recFO = null;

		if (resultMap==null) {
			//bei neuerfassung einer bam aus der fuhrenmaske
			recFU = new Rec20_VPOS_TPA_FUHRE_FromView(map.getIdVPOS_TPA_FUHRE(), map.getIdVPOS_TPA_FUHRE_ORT());
		} else {
			//bei bearbeitung einer bam innerhalb des bam-modul
			recFU = new Rec20_VPOS_TPA_FUHRE_FromView(resultMap.get_UnFormatedValue(FBAM.id_vpos_tpa_fuhre.fn()), resultMap.get_UnFormatedValue(FBAM.id_vpos_tpa_fuhre_ort.fn()));
		}
		recFO = recFU.getRec20FuhrenOrt();

		this._clear()._setSize(60,70,80,70,80,150,30,200,40,30,200)._bo_ddd();
		
		//jetzt die info, ob die BAM gemailt werden soll
		Rec20_adresse recAdStart = 	new Rec20_adresse()._fill_id(recFU.getUfs(VPOS_TPA_FUHRE.id_adresse_lager_start));
		Rec20_adresse recAdZiel = 	new Rec20_adresse()._fill_id(recFU.getUfs(VPOS_TPA_FUHRE.id_adresse_lager_ziel));
		
		Rec20_adresse recLieferant = 	recAdStart.__get_main_adresse();
		Rec20_adresse recAbnehmer =  	recAdZiel.__get_main_adresse();
		
		boolean bStartBekommtMail = 	recLieferant.getFirmeninfo().is_no_db_val(FIRMENINFO.fbam_nur_intern);
		boolean bZielBekommtMail = 		recAbnehmer.getFirmeninfo().is_no_db_val(FIRMENINFO.fbam_nur_intern);

		RB_lab  iconStart = new RB_lab()._icon(E2_ResourceIcon.get_RI(bStartBekommtMail?"emailwhitebig.png":"emailwhiteforbidden.png"));
		RB_lab  iconZiel =  new RB_lab()._icon(E2_ResourceIcon.get_RI(bZielBekommtMail?"emailwhitebig.png":"emailwhiteforbidden.png"));
		
		String sMailInfoLieferant = 	recLieferant.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.name2,ADRESSE.ort);
		String sMailInfoAbnehmer = 		recAbnehmer.get_ufs_kette(" ", ADRESSE.name1,ADRESSE.name2,ADRESSE.ort);

		
		if (!bStartBekommtMail) {
			iconStart._ttt(S.ms("Die Adresse <").ut(sMailInfoLieferant).t("> trägt den Vermerk: BAM nur Intern!"));
		} else {
			iconStart._ttt(S.ms("Die Adresse <").ut(sMailInfoLieferant).t("> kann BAMS per eMail bekommen!"));
		}
		
		if (!bZielBekommtMail) {
			iconZiel._ttt(S.ms("Die Adresse <").ut(sMailInfoAbnehmer).t("> trägt den Vermerk: BAM nur Intern!"));
		}else {
			iconZiel._ttt(S.ms("Die Adresse <").ut(sMailInfoAbnehmer).t("> kann BAMS per eMail bekommen!"));
		}
		
		RB_gld ld = new RB_gld()._left_top()._ins(2, 2, 2, 2);
		
		
		String title = null;
		String labFuhre = null;
		String labID_fu = null;
		String labFO = null;
		String labID_FO = null;
		String labANR = null;
		String labARTBEZ = null;
		String labAdrStart = null;
		String labSepStartZiel = null;
		String labAdrZiel = null;
		
		//jetzt fuellen
		if (recFO != null) {

			title = 		new MyE2_String("Beanstandungsmeldung zu Zusatzort "+(recFO.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"").equals("EK")?" Einkauf":" Verkauf")).CTrans();
			labFuhre=		new MyE2_String("Fuhre-ID: ").CTrans();
			labID_fu=		recFO.getFs(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre);
			labFO=			new MyE2_String("FuhreOrt-ID: ").CTrans();
			labID_FO=		recFO.getFs(VPOS_TPA_FUHRE_ORT.id_vpos_tpa_fuhre_ort);
			labANR=			recFO.getFs(VPOS_TPA_FUHRE_ORT.anr1,"")+" - "+recFO.getFs(VPOS_TPA_FUHRE_ORT.anr2,"");
			labARTBEZ=		recFO.getFs(VPOS_TPA_FUHRE_ORT.artbez1,"");
			labAdrStart = 	recFO.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"").equals("EK")?
							recFO.get_ufs_kette(" ", VPOS_TPA_FUHRE_ORT.name1,VPOS_TPA_FUHRE_ORT.name2,VPOS_TPA_FUHRE_ORT.ort) :
							recFU.get_ufs_kette(" ", VPOS_TPA_FUHRE.l_name1,VPOS_TPA_FUHRE.l_name2,VPOS_TPA_FUHRE.l_ort);
			labSepStartZiel= new MyE2_String(" nach ").CTrans();
			labAdrZiel = 	recFO.getUfs(VPOS_TPA_FUHRE_ORT.def_quelle_ziel,"").equals("EK")?
							recFU.get_ufs_kette(" ", VPOS_TPA_FUHRE.a_name1,VPOS_TPA_FUHRE.a_name2,VPOS_TPA_FUHRE.a_ort) :
							recFO.get_ufs_kette(" ", VPOS_TPA_FUHRE_ORT.name1,VPOS_TPA_FUHRE_ORT.name2,VPOS_TPA_FUHRE_ORT.ort);
		} else {

			title = 		new MyE2_String("Beanstandungsmeldung zu Fuhre ").CTrans();
			labFuhre=		new MyE2_String("Fuhre-ID: ").CTrans();
			labID_fu=		recFU.getFs(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre);
			labFO=			"";
			labID_FO=		"";
			labANR=			recFU.getFs(VPOS_TPA_FUHRE.anr1_ek,"")+" - "+recFU.getFs(VPOS_TPA_FUHRE.anr2_ek,"");
			labARTBEZ=		recFU.getFs(VPOS_TPA_FUHRE.artbez1_ek,"");
			labAdrStart = 	recFU.get_ufs_kette(" ", VPOS_TPA_FUHRE.l_name1,VPOS_TPA_FUHRE.l_name2,VPOS_TPA_FUHRE.l_ort);
			labSepStartZiel= new MyE2_String(" nach ").CTrans();
			labAdrZiel = 	recFU.get_ufs_kette(" ", VPOS_TPA_FUHRE.a_name1,VPOS_TPA_FUHRE.a_name2,VPOS_TPA_FUHRE.a_ort);
		}
		
		this._a(new RB_lab(title), new RB_gld()._left_mid()._span(this.getSize())._ins(2, 5, 2, 5)._col(new E2_ColorDDark()));
		this._a(new RB_lab(labFuhre)._fsa(-2),			ld._c()._ins(2, 4, 2, 2));
		this._a(new RB_lab(labID_fu),					ld._c()._center_top());
		this._a(new RB_lab(labFO)._fsa(-2),				ld._c()._ins(2, 4, 2, 2));
		this._a(new RB_lab(labID_FO),					ld._c()._center_top());
		this._a(new RB_lab(labANR),						ld);
		this._a(new RB_lab(labARTBEZ),					ld);
		this._a(iconStart,								ld);
		this._a(new RB_lab(labAdrStart)._fsa(-2),		ld);
		this._a(new RB_lab(labSepStartZiel)._fsa(-2),	ld);
		this._a(iconZiel,								ld);
		this._a(new RB_lab(labAdrZiel)._fsa(-2),		ld);
		

		
		return this;
	}
	
	
	
	
	
}
