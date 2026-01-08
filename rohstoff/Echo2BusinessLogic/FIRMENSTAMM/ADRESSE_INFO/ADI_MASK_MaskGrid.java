
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import panter.gmbh.Echo2.E2_INSETS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.TOOLS.RB_MaskGrid;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE_INFO;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;




public class ADI_MASK_MaskGrid extends RB_MaskGrid {

	public ADI_MASK_MaskGrid(ADI_MASK_ComponentMapCollector mapColl) throws myException {
		super(3, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());

		ADI_MASK_ComponentMap map1 = (ADI_MASK_ComponentMap) mapColl.get(new RB_KM(_TAB.adresse_info));

		this.add(new MyE2_Label(new MyE2_String("Erfassung von")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.kuerzel)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Erfassung am")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.datumeintrag)), 2, E2_INSETS.I(2, 2, 2, 2));
		
		this.add(new MyE2_Label(new MyE2_String("Datum Ereignis")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.datumereignis)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Wiedervorlage")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.folgedatum)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Wiedervorlage Monatlich")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.wiederholungmonatlich)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Wiedervorlage Jährlich")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.wiederholungjaehrlich)), 2, E2_INSETS.I(2, 2, 2, 2));
		
		this.add(new MyE2_Label(new MyE2_String("Anlass der Info")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_aktionsanlass)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Betreuer")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_user)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Betreuer (2)")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_user_ersatz)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Sachbearbeiter")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_user_sachbearbeiter)), 2, E2_INSETS.I(2, 2, 2, 2));
		
		this.add(new MyE2_Label(new MyE2_String("Ergebnis 1")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis1)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Ergebnis 2")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis2)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Ergebnis 3")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.id_besuchsergebnis3)), 2, E2_INSETS.I(2, 2, 2, 2));
		this.add(new MyE2_Label(new MyE2_String("Wortlaut der Information")), 1, E2_INSETS.I(2, 2, 2, 2));
		this.add(map1.getRbComponent(new RB_KF(ADRESSE_INFO.text)), 2, E2_INSETS.I(2, 2, 2, 2));
		
//		this.add(new MyE2_Label(new MyE2_String("id_adresse")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(ADRESSE_INFO.id_adresse_info)), 2, E2_INSETS.I(2, 2, 2, 2));
//		this.add(new MyE2_Label(new MyE2_String("ist_message")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(ADRESSE_INFO.ist_message)), 2, E2_INSETS.I(2, 2, 2, 2));
//		this.add(new MyE2_Label(new MyE2_String("message_sofort")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(ADRESSE_INFO.message_sofort)), 2, E2_INSETS.I(2, 2, 2, 2));
//		this.add(new MyE2_Label(new MyE2_String("message_typ")), 1, E2_INSETS.I(2, 2, 2, 2));
//		this.add(map1.rb_Component(new RB_KF(ADRESSE_INFO.message_typ)), 2, E2_INSETS.I(2, 2, 2, 2));

	}

}
