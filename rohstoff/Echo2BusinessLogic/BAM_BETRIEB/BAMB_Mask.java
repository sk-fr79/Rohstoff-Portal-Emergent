/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_BETRIEB;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLight;
import panter.gmbh.Echo2.ListAndMask.ComponentExtender.IF_ADDING_Allowed;
import panter.gmbh.Echo2.ListAndMask.Mask.IF_BaseComponent4Mask;
import panter.gmbh.Echo2.components.E2_ComponentGrid;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TabbedPane;
import panter.gmbh.Echo2.staticStyles.Style_Grid_Normal;
import panter.gmbh.indep.exceptions.myException;

public class BAMB_Mask extends MyE2_Column implements IF_BaseComponent4Mask	
{
	private Vector<IF_ADDING_Allowed>  vMaskGrids = new Vector<IF_ADDING_Allowed>();
	
	public BAMB_Mask(BAMB_MASK_ComponentMAP 	oE2_ComponentMAP) throws myException
	{
		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		MyE2_TabbedPane oTabbedPane = new MyE2_TabbedPane(null);
		oTabbedPane.set_bAutoHeight(true);

		
		MyE2_Grid		oGridPage1	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_Grid		oGridPage2	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_Grid		oGridPage3	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_Grid		oGridPage4	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		MyE2_Grid		oGridPage5	= new MyE2_Grid(new Style_Grid_Normal(0,new Insets(2)));
		
		this.vMaskGrids.add(oGridPage1);
		this.vMaskGrids.add(oGridPage2);
		this.vMaskGrids.add(oGridPage3);
		this.vMaskGrids.add(oGridPage4);
		this.vMaskGrids.add(oGridPage5);
		
		oTabbedPane.add_Tabb(new MyE2_String("Grundangaben"),		oGridPage1);
		oTabbedPane.add_Tabb(new MyE2_String("Weitere"),			oGridPage2);
		oTabbedPane.add_Tabb(new MyE2_String("Behebung"),			oGridPage3);
		oTabbedPane.add_Tabb(new MyE2_String("Infos"),				oGridPage4);
		oTabbedPane.add_Tabb(new MyE2_String("Protokoll"),			oGridPage5);

		this.add(oTabbedPane);

		oGridPage1.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage2.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage3.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage4.setColumnWidth(0,new Extent(25,Extent.PERCENT));
		oGridPage5.setColumnWidth(0,new Extent(10,Extent.PERCENT));
		
		
		Insets oIN = new Insets(2,8,8,2);
		
		//	---------------------
		oGridPage1.setSize(2);
		oGridPage1.add(new MyE2_Label("Beanstandung"),oIN);			oGridPage1.add(new E2_ComponentGrid(	3, 	new MyE2_Label("Grund"),
																													new MyE2_Label("Buchungsnummer"),
																													new MyE2_Label("ID"),
																														(Component)oE2_ComponentMAP.get("BAM_GRUND"),
																														(Component)oE2_ComponentMAP.get("BUCHUNGSNUMMER"),
																														(Component)oE2_ComponentMAP.get("ID_FBAM")),oIN);

		oGridPage1.add(new MyE2_Label("Fehler "),oIN);					oGridPage1.add(new E2_ComponentGrid(	2, new MyE2_Label("Datum"),
																												new MyE2_Label("Ort:"),
																												(Component)oE2_ComponentMAP.get("BAM_DATUM"),
																												(Component)oE2_ComponentMAP.get("BAM_ORT"),
																												null,null),oIN);
		
		oGridPage1.add(new MyE2_Label("Fehlerbeschreibung"),oIN);			oGridPage1.add((Component)oE2_ComponentMAP.get("FEHLERBESCHREIBUNG"),oIN);
		oGridPage1.add(new MyE2_Label("Fehlerursache"),oIN);				oGridPage1.add((Component)oE2_ComponentMAP.get("FEHLERURSACHE"),oIN);
		oGridPage1.add(new MyE2_Label("Fehler festgestellt bei"),oIN);		oGridPage1.add((Component)oE2_ComponentMAP.get("FESTSTELLUNG_BAM"),oIN);
		oGridPage1.add(new MyE2_Label("Fehler betrifft"),oIN);				oGridPage1.add((Component)oE2_ComponentMAP.get("BETREFF_BAM"),oIN);
		
		oGridPage1.add(new MyE2_Label("Ausgestellt von / am"),oIN);		oGridPage1.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get("ID_USER_AUSSTELLUNG"),
																												(Component)oE2_ComponentMAP.get("DATUM_AUSSTELLUNG"),E2_INSETS.I_2_0_2_0));
		
		
		
		//	---------------------
		
		oGridPage2.setSize(2);
		oGridPage2.add(new MyE2_Label("Auswirkungen"),oIN);				oGridPage2.add((Component)oE2_ComponentMAP.get("AUSWIRKUNGEN"),oIN);
		oGridPage2.add(new MyE2_Label("Infos an"),oIN);						oGridPage2.add(new E2_ComponentGroupHorizontal(0,
																					(Component)oE2_ComponentMAP.get(BAMB_MASK_ModulContainer.FIELDNAME_CROSSREFERENCE_BAM_USER),
																					(Component)oE2_ComponentMAP.get(BAMB_MASK_ModulContainer.FIELDNAME_FBAM_SENDE_MAILS_TO_VERTEILER),
																					E2_INSETS.I_2_0_2_0),
																					oIN);
		
		//	---------------------
		
		oGridPage3.setSize(2);
		oGridPage3.add(new MyE2_Label("Behebung Vorschlag / intern"),oIN);			oGridPage3.add((Component)oE2_ComponentMAP.get("BEHEB_VORSCHLAG"),oIN);
		
		oGridPage3.add(new MyE2_Label("Behebung Vermerk / intern"),oIN);			oGridPage3.add((Component)oE2_ComponentMAP.get("BEHEB_VERMERK"),oIN);
		oGridPage3.add(new MyE2_Label("Abschluss Behebung"),oIN);			oGridPage3.add(new E2_ComponentGroupHorizontal(0,	(Component)oE2_ComponentMAP.get("ABGESCHLOSSEN_BEHEBUNG"),
																												new MyE2_Label("von"),
																												(Component)oE2_ComponentMAP.get("ID_USER_BEHEBUNG"),
																												new MyE2_Label("am"),
																												(Component)oE2_ComponentMAP.get("DATUM_BEHEBUNG"),
																												null),oIN);
		oGridPage3.add(new MyE2_Label("Kontrolle abgeschl."),oIN);			oGridPage3.add(new E2_ComponentGroupHorizontal(0,(Component)oE2_ComponentMAP.get("ABGESCHLOSSEN_KONTROLLE"),
																												new MyE2_Label("von"),
																												(Component)oE2_ComponentMAP.get("ID_USER_KONTROLLE"),
																												new MyE2_Label("am"),
																												(Component)oE2_ComponentMAP.get("DATUM_KONTROLLE"),
																												null),oIN);

		// -----------------------
		oGridPage4.setSize(1);
		oGridPage4.add(new MyE2_Label("Infotexte zur Beanstandungsmeldung"),oIN);
		oGridPage4.add((Component)oE2_ComponentMAP.get(BAMB_MASK_ModulContainer.FIELDNAME_DAUGHTERTABLE_INFOS),oIN);
		
		// -----------------------
		oGridPage5.setSize(2);
		GridLayoutData innenLayout = new GridLayoutData();
		innenLayout.setBackground(new E2_ColorLight());
		innenLayout.setInsets(new Insets(5,2,0,2));
		/*
		 * eine tochterkomponenten fuer die druckprotokolle
		 */
		oGridPage5.add(new MyE2_Label("Druckprotokoll"),oIN);				oGridPage5.add((Component)oE2_ComponentMAP.get(BAMB_MASK_ModulContainer.FIELDNAME_DAUGHTERTABLE_PRINTPROTOKOLL),innenLayout);
		
	}

	@Override
	public Vector<IF_ADDING_Allowed> get_Basic_Mask_Container_Components() throws myException
	{
		return this.vMaskGrids;
	}
}