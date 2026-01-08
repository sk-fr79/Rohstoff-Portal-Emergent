package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING;


import java.util.HashMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.Label;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.TempFilter.ActionAgentResetFilters;
import panter.gmbh.Echo2.ListAndMask.List.TempFilter.FilterVariante;
import panter.gmbh.Echo2.ListAndMask.List.TempFilter.IfFilterExtForListComponents;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2EXT__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.VEKSingle;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.KONTRAKTE.CLEARING.BSKC__CONST.filterType;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_VPOS_KON_EXT.CLEARINGTAGS;


 
public class BSK_GridWithNumber extends MyE2_Grid implements IfFilterExtForListComponents {
	

	
	public static GridLayoutData LAYOUT_RIGHT = null;
	public static GridLayoutData LAYOUT_PERCENT = null;
	static
	{
		GridLayoutData layout = new GridLayoutData();
		layout.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		layout.setInsets(new Insets(2,0,2,0));
		LAYOUT_RIGHT = layout;
		
		GridLayoutData layoutp = new GridLayoutData();
		layoutp.setAlignment(new Alignment(Alignment.LEFT,Alignment.CENTER));
		layoutp.setInsets(new Insets(2,2,10,0));
		LAYOUT_PERCENT = layoutp;

	}

	
	private BSK_BtFilterExtender             btExtenderComponent = null;
	
	private E2_NavigationList   		naviList = null;
	private BSKC__CONST.FILTERCOLUMN 	column = null;
	
	public BSK_GridWithNumber(E2_NavigationList p_naviList, BSKC__CONST.FILTERCOLUMN p_column, String EK_VK) throws myException {
		super(MyE2_Grid.STYLE_GRID_NO_BORDER());

		this.setInsets(E2_INSETS.I_0_0_0_0);
		this.setWidth(new Extent(99,Extent.PERCENT));
		this.naviList = p_naviList;
		this.column = p_column;
		
		if (p_column!=null) {
			this.setNavigationListeThisBelongsTo(p_naviList);
			this.setKenner(p_column.name());
			this.btExtenderComponent = new BSK_BtFilterExtender(this, p_naviList, p_column);
			this.EXT().set_oCompTitleInList(new E2_Grid()._a(new RB_lab(this.column.getTitleText(EK_VK))._ttt(p_column.getTooltips4headerComponents()), new RB_gld()._col(new E2_ColorDark())._ins(0,0,5,0))
														 ._a(this.btExtenderComponent, new RB_gld()._col(new E2_ColorDark())));
		}
		
	}
	
	
	public void set_Number(double dWertFuerDarstellung, int Nachkomma, Double GesamtWert, boolean bVerhaeltnisFarbe_umdrehen)
	{
		this.removeAll();
		
		MyE2_String cHelpToolTip = new MyE2_String("Anteil (auch prozentual) von der Gesamtmenge: ",true,MyNumberFormater.formatDez(GesamtWert, 1, true),false);
		
		
		double ddWert = bibALL.Round(dWertFuerDarstellung,Nachkomma);
		
		String cText = bibALL.makeDez(ddWert,Nachkomma,true);
		MyE2_Label oLabel = new MyE2_Label(cText);
		
		// jetzt nachsehen, ob mit formatierung - FARBEN und prozentualer wert eintragen
		if (GesamtWert != null)
		{
			double dGesamtwert = GesamtWert.doubleValue();
			
			BSKC__CONST.filterType typ = BSKC__CONST.pruefeEinstufung(dWertFuerDarstellung, dGesamtwert, bVerhaeltnisFarbe_umdrehen);
			this.setBackground(typ.getColor());
			
			if (dGesamtwert != 0)
			{
				double verhaeltnis = 	ddWert/dGesamtwert;
				double Prozent = 		bibALL.Round(verhaeltnis*100,0);

				MyE2_Label oLabelProzent = new MyE2_Label(""+bibALL.makeDez(Prozent,0,false)+" % =",MyE2_Label.STYLE_SMALL_ITALIC());
				this.add(oLabelProzent,BSK_GridWithNumber.LAYOUT_PERCENT);
				this.add(oLabel,BSK_GridWithNumber.LAYOUT_RIGHT);
				
				oLabelProzent.setToolTipText(cHelpToolTip.CTrans());
				oLabel.setToolTipText(cHelpToolTip.CTrans());
			}
			else
			{
				this.add(new Label(""),BSK_GridWithNumber.LAYOUT_PERCENT);
				this.add(oLabel,BSK_GridWithNumber.LAYOUT_RIGHT);
			}
		}
		else
		{
			this.add(new Label(""),BSK_GridWithNumber.LAYOUT_PERCENT);
			this.add(oLabel,BSK_GridWithNumber.LAYOUT_RIGHT);
		}
	}
	
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy
	{
		try {
			BSK_GridWithNumber oRowRueck = new BSK_GridWithNumber(this.naviList, null, null);
			oRowRueck.set_EXT((MyE2EXT__Component)this.EXT().get_Copy(oRowRueck));
			return oRowRueck;
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}



	@Override
	public Component getAddonComponentForListTitel() throws myException {
		return this.btExtenderComponent;
	}


	@Override
	public void buildAndFillCategories() throws myException {
		this.showAddonComponentForListTitelIsEmpty();
		
		//die kathegorien aufbauen
		VEK<String>  vIds = new VEK<String>()._a(this.getvCopyOfVector4Segmentation());
		
		HashMap<filterType, VEKSingle<String>> hmSammle = new HashMap<>();
		
		for (filterType f: filterType.values()) {
			hmSammle.put(f, new VEKSingle<>());
		}
		
		for (String id: vIds) {
			RECORD_VPOS_KON_EXT re = new RECORD_VPOS_KON_EXT(id);
			HashMap<CLEARINGTAGS, Double> hmWerte = re.getClearingInfos();
			hmSammle.get(this.column.getFilterType(hmWerte)).add(id);
		}
		
		this.getVarianten().clear();
		for (filterType f: filterType.values()) {
			this.getVarianten().add(new FilterVariante()._setKEY(f)._setTextTr(f.getText4cb())._addIDs(hmSammle.get(f)));
		}
		
		this.showAddonComponentForListTitelIsPrepared();
	}


//	public BSKC__CONST.FILTERCOLUMN getColumnKey() {
//		return column;
//	}




	@Override
	public void showAddonComponentForListTitelIsEmpty() throws myException {
		this.btExtenderComponent._image(E2_ResourceIcon.get_RI("filter.png"));
	}


	@Override
	public void showAddonComponentForListTitelIsPrepared() throws myException {
		this.btExtenderComponent._image(E2_ResourceIcon.get_RI("filter_prepared.png"));
	}


	@Override
	public void showAddonComponentForListTitelIsActiv() throws myException {
		this.btExtenderComponent._image(E2_ResourceIcon.get_RI("filter_active.png"));
	}




    // implementierungsblock fuer members des interface IfFilterExtForListComponents
    private E2_NavigationList 	   		navilist = null;
    private String          			kenner = null;
    private Vector<String>           	vIDs_orig = new Vector<String>();
    private Vector<FilterVariante>   	vFilterVariante = new Vector<FilterVariante> ();
 
    @Override
    public E2_NavigationList getNavigationListeThisBelongsTo() {
        return this.navilist;
    }

    @Override
    public void setNavigationListeThisBelongsTo(E2_NavigationList p_navilist) {
        this.navilist = p_navilist;
        //hier wird in der navigationlist der resetActionAgent hinterlegt
        boolean isEvenThere = false;
        for (XX_ActionAgent agent: navilist.getvActionAgentsAfterBuild_BASE_ID_Vector()) {
            if (agent instanceof ActionAgentResetFilters) {
                isEvenThere=true;
            }
        }
        if (!isEvenThere) {
		    navilist.add_actionActionAgentsAfterBuild_BASE_ID_Vector(new ActionAgentResetFilters(navilist));
	    }
    }


    @Override
    public void setKenner(String p_kenner) {
        this.kenner=p_kenner;
    }
	
    @Override
    public String getKenner() {
        return this.kenner;
    }

    @Override
    public Vector<String> get_vIdsOrig() {
        return this.vIDs_orig;
    }
    
    @Override
    public Vector<FilterVariante>  	getVarianten() {
        return this.vFilterVariante;
    }
    
    // Ende implementierungsblock 


}
