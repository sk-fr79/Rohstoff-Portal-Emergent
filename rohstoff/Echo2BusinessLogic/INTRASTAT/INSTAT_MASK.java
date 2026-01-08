package rohstoff.Echo2BusinessLogic.INTRASTAT;


import panter.gmbh.Echo2.ListAndMask.Mask.E2_MaskFiller;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.exceptions.myException;

public class INSTAT_MASK extends MyE2_Grid 
{

	
	public INSTAT_MASK(INSTAT_MASK_ComponentMAP oMap) throws myException
	{
		super(6, MyE2_Grid.STYLE_GRID_NO_BORDER());
	
		E2_MaskFiller  oFiller = new E2_MaskFiller(oMap,null,null);

		//hier kommen die Felder	
		oFiller.add_Line(this, oMap.get__Comp("ID_INTRASTAT_MELDUNG").EXT().get_cList_or_Mask_Titel() , 1, "ID_INTRASTAT_MELDUNG|#  |",1,
				oMap.get__Comp("PAGINIERNUMMER").EXT().get_cList_or_Mask_Titel(), 1, "PAGINIERNUMMER|#  |",1,
				oMap.get__Comp("EXPORTIERT_AM").EXT().get_cList_or_Mask_Titel(), 1, "EXPORTIERT_AM|#  |",1);

		oFiller.add_Line(this,
				oMap.get__Comp("ID_VPOS_TPA_FUHRE").EXT().get_cList_or_Mask_Titel(), 1, "ID_VPOS_TPA_FUHRE|#  |",1,
				oMap.get__Comp("ID_VPOS_TPA_FUHRE_ORT").EXT().get_cList_or_Mask_Titel(), 1, "ID_VPOS_TPA_FUHRE_ORT|#  |",3);
		
		oFiller.add_Line(this, 
				oMap.get__Comp("MELDEART").EXT().get_cList_or_Mask_Titel(), 1, "MELDEART|#  |",1,
				oMap.get__Comp("ANMELDEFORM").EXT().get_cList_or_Mask_Titel(), 1, "ANMELDEFORM|#  |",1,
				oMap.get__Comp("ANMELDEMONAT").EXT().get_cList_or_Mask_Titel(), 1, "ANMELDEMONAT|#  |",1);

		oFiller.add_Line(this, oMap.get__Comp("BEZUGSJAHR").EXT().get_cList_or_Mask_Titel(), 1, "BEZUGSJAHR|#  |",1,
				oMap.get__Comp("BEZUGSMONAT").EXT().get_cList_or_Mask_Titel(), 1, "BEZUGSMONAT|#  |",3);


		oFiller.add_Line(this, oMap.get__Comp("BUNDESLAND_FA").EXT().get_cList_or_Mask_Titel(), 1, "BUNDESLAND_FA|#  |",1,
				oMap.get__Comp("STEUERNR").EXT().get_cList_or_Mask_Titel(), 1, "STEUERNR|#  |",1,
				oMap.get__Comp("UNTERSCHEIDUNGSNR").EXT().get_cList_or_Mask_Titel(), 1, "UNTERSCHEIDUNGSNR|#  |",1);


		oFiller.add_Line(this, oMap.get__Comp("BESTIMM_LAND").EXT().get_cList_or_Mask_Titel(), 1, "BESTIMM_LAND|#  |",1,
				oMap.get__Comp("BESTIMM_REGION").EXT().get_cList_or_Mask_Titel(), 1, "BESTIMM_REGION|#  |",3);

				
		oFiller.add_Line(this, oMap.get__Comp("WAEHRUNGSKENNZIFFER").EXT().get_cList_or_Mask_Titel(), 1, "WAEHRUNGSKENNZIFFER|#  |",5);
				
		oFiller.add_Line(this, oMap.get__Comp("WARENNR").EXT().get_cList_or_Mask_Titel(), 1, "WARENNR|#  |",5);
		
		oFiller.add_Line(this, oMap.get__Comp("GESCHAEFTSART").EXT().get_cList_or_Mask_Titel(), 1, "GESCHAEFTSART|#  |",1,
				oMap.get__Comp("VERKEHRSZWEIG").EXT().get_cList_or_Mask_Titel(), 1, "VERKEHRSZWEIG|#  |",1,
				oMap.get__Comp("LAND_URSPRUNG").EXT().get_cList_or_Mask_Titel(), 1, "LAND_URSPRUNG|#  |",1);

		oFiller.add_Line(this, oMap.get__Comp("EIGENMASSE").EXT().get_cList_or_Mask_Titel(), 1, "EIGENMASSE|#  |",1,
				oMap.get__Comp("MASSEINHEIT").EXT().get_cList_or_Mask_Titel(), 1, "MASSEINHEIT|#  |",3);

		oFiller.add_Line(this, oMap.get__Comp("RECHBETRAG").EXT().get_cList_or_Mask_Titel(), 1, "RECHBETRAG|#  |",1,
				oMap.get__Comp("STATISTISCHER_WERT").EXT().get_cList_or_Mask_Titel(), 1, "STATISTISCHER_WERT|#  |",1,
				oMap.get__Comp("EXPORT_NO_PRICE").EXT().get_cList_or_Mask_Titel(),1,"EXPORT_NO_PRICE|#",1 );

		oFiller.add_Line(this, oMap.get__Comp("FRACHTKOSTEN").EXT().get_cList_or_Mask_Titel(), 1, "FRACHTKOSTEN|#  |",1,
				oMap.get__Comp("NICHT_MELDEN").EXT().get_cList_or_Mask_Titel(), 1, "NICHT_MELDEN|#  |",3);

//		oFiller.add_Line(this, new MyE2_String("EXPORTFREIGABE:"), 1, "EXPORTFREIGABE|#  |",3);

		/*		
		 * beispiele fuer maskendefinition
		 *
		oFiller.add_Line(this, new MyE2_String("Besitzer:"), 1, "ID_USER|#  |#ID|ID_INSTAT",3);
		oFiller.add_Line(this, new MyE2_String("Dringlichkeit:"), 1, "ID_INSTAT_WICHTIGKEIT|# ",3);   
		oFiller.add_Line(this, new MyE2_String("Erstellt am:"), 1, "GENERIERUNGSDATUM|#   |#Zu erledigen bis|ABLAUFDATUM|#  |#Abgeschlossen |ERLEDIGT|#  am |ABSCHLUSSDATUM",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Aufgabe:"), 1, "AUFGABEKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "AUFGABENTEXT",3);
		this.add(new Separator(), 4, E2_INSETS.I_2_2_2_2);
		oFiller.add_Line(this, new MyE2_String("Ergebnis:"), 1, "ANTWORTKURZ",3);
		oFiller.add_Line(this, new MyE2_String("Details:"), 1, "ANTWORTTEXT",3);
		*/

	}

	
	
}
