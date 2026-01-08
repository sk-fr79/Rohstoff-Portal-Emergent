package rohstoff.businesslogic.bewegung2.list.listSelector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.ListAndMask.List.Selektion.E2_SelektorDateFromTo_NG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class B2_listSelector_PlanDatumVonBis extends E2_SelektorDateFromTo_NG {

	public B2_listSelector_PlanDatumVonBis() throws myException {
		super(
				null, 
				BG_VEKTOR.datum_planung_von.tnfn(), 
				BG_VEKTOR.datum_planung_bis.tnfn(), 
				new Extent(120)
				);

		this.get_oTFDatumVon().get_oTextField().set_iWidthPixel(100);
		this.get_oTFDatumVon().get_oTextField().setFont(new E2_FontPlain(-2));
		
		this.get_oTFDatumBis().get_oTextField().set_iWidthPixel(100);
		this.get_oTFDatumBis().get_oTextField().setFont(new E2_FontPlain(-2));
		
		this.set_ToolTips(S.ms(
				"Selektiert Bewegung aus, deren Zeitbereich Ladedatum-Abladedatum sich mit dem Eingabe-Zeitbereich überschneiden!"));
	}
	
	@Override
	public Component get_oComponentForSelection() {
		E2_Grid  grd = new E2_Grid()._setSize(90,185,185);
		grd
			._a(this.get_oTFDatumVon()	, new RB_gld()._left_mid())
			._a(this.get_oTFDatumBis()	, new RB_gld()._ins(15,1,5,1)._left_mid())
			;
		return grd;
	}
}