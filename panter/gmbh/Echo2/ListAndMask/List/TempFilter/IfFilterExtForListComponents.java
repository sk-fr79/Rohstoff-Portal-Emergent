package panter.gmbh.Echo2.ListAndMask.List.TempFilter;

import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEKSingle;




/*
 * interface, um listenkomponenten zu erweitern, die einen filter fuer die liste implementieren
 */

/**
    // implementierungsblock fuer members des interface IfFilterExtForListComponents
    private E2_NavigationList 	   navilist = null;
    private String          		   kenner = null;
    private Vector<String>           vIDs_orig = new Vector<String>();
    private Vector<FilterVariante>   vFilterVariante = new Vector<FilterVariante>();
 
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
    
    // Ende implementierungsblock  des interface IfFilterExtForListComponents

 */

public interface IfFilterExtForListComponents {

//	class EXT {
//		private static final WeakHashMap<IfFilterExtForListComponents, E2_NavigationList> 		navilistMap = 			new WeakHashMap<>();
//		private static final WeakHashMap<IfFilterExtForListComponents, String> 					kennerMap = 			new WeakHashMap<>();
//		private static final WeakHashMap<IfFilterExtForListComponents, Vector<String>> 			vIDs_origMap = 			new WeakHashMap<>();
//		private static final WeakHashMap<IfFilterExtForListComponents, Vector<FilterVariante>> 	vFilterVarianteMap = 	new WeakHashMap<>();
//	}
//	
//	
//	public default void setNavigationListeThisBelongsTo(E2_NavigationList navilist) {
//		EXT.navilistMap.put(this, navilist);
//		
//		//hier wird in der navigationlist der resetActionAgent hinterlegt
//		boolean isEvenThere = false;
//		for (XX_ActionAgent agent: navilist.getvActionAgentsAfterBuild_BASE_ID_Vector()) {
//			if (agent instanceof ActionAgentResetFilters) {
//				isEvenThere=true;
//			}
//		}
//		if (!isEvenThere) {
//			navilist.add_actionActionAgentsAfterBuild_BASE_ID_Vector(new ActionAgentResetFilters(navilist));
//		}
//	}
//
//	
//	
//	public default E2_NavigationList getNavigationListeThisBelongsTo() {
//		return EXT.navilistMap.get(this);
//	}
//	
//	public default void setKenner(String kenner) {
//		EXT.kennerMap.put(this, kenner);
//	}
//	
//	public default String getKenner() {
//		return EXT.kennerMap.get(this);
//	}
//	
//	public default Vector<String> getAndCreate_vIds() {
//		if (EXT.vIDs_origMap.get(this)==null) {
//			EXT.vIDs_origMap.put(this, new Vector<>());
//		}
//		return EXT.vIDs_origMap.get(this);
//	}
//	
//	public default Vector<FilterVariante>  	getVarianten() {
//		if (EXT.vFilterVarianteMap.get(this)==null) {
//			EXT.vFilterVarianteMap.put(this, new Vector<>());
//		}
//		return EXT.vFilterVarianteMap.get(this);
//	}

	
	//implementierung siehe oben
	public  void 					setNavigationListeThisBelongsTo(E2_NavigationList navilist);
	public  E2_NavigationList 		getNavigationListeThisBelongsTo();
	public void 					setKenner(String kenner);
    public String 					getKenner();
    public Vector<String> 			get_vIdsOrig();
    public Vector<FilterVariante> 	getVarianten();
	
	
	/**
	 * 
	 * @return first vector in memberlist
	 */
	public default Vector<String> getvCopyOfVector4Segmentation() {
		Vector<String> v_firstVector = null;

		this.storeUnfilteredNaviListIdsToFirstMember();
		
		//zuerst alle durchlaufen, damit jeder vector definiert ist, den ersten rausholen
		for (MyE2IF__Component comp: this.getNavigationListeThisBelongsTo().get_oComponentMAP__REF().values()) {
			if (comp instanceof IfFilterExtForListComponents) {
				v_firstVector = ((IfFilterExtForListComponents)comp).get_vIdsOrig();
				break;
			}
		}
		return v_firstVector;
	}
	
	

	public Component 							getAddonComponentForListTitel() throws myException;
	
	//anzeige umschalten (nur fuer das frontend)
	public void   								showAddonComponentForListTitelIsEmpty() throws myException;
	public void   								showAddonComponentForListTitelIsPrepared() throws myException;
	public void   								showAddonComponentForListTitelIsActiv() throws myException;
	public void 								buildAndFillCategories() throws myException;
	
	/**
	 * aktiviert die filter innerhalb der liste (alle vorhandene mit jeweiligem status)
	 * @throws myException
	 */
	public default void activateFilters() throws myException {

		//zuerst alle IfExtensionForListComponents-komponenten beschaffen
		Vector<IfFilterExtForListComponents>  vMembers = new Vector<>();
		for (MyE2IF__Component comp: this.getNavigationListeThisBelongsTo().get_oComponentMAP__REF().values()) {
			if (comp instanceof IfFilterExtForListComponents) {
				vMembers.add((IfFilterExtForListComponents)comp);
			}
		}

		//darf nicht sein
		if (vMembers.size()==0) {
			throw new myException(this,"Error: cannot identify component !!");
		}
		
		//jetzt alle komponenten durchsuchen und dort die aktiven filterCategories sammeln
		VEKSingle<String> vNewSegmentationVector = new VEKSingle<>();
		
		//hier komplett neu beginnen mit der filterung, zuerst alle reinhaengen
		vNewSegmentationVector.addAll(this.getvCopyOfVector4Segmentation());
		
//		DEBUG.System_println("------------------------------------------------- ",true);
//		DEBUG.System_println("Startanzahl in der filterung: "+this.getvCopyOfVector4Segmentation().size());
		
		for (IfFilterExtForListComponents comp: vMembers) {
			
			if (comp.isFilterprepared()) {
			
				//inerhalb der componente listen additiv verketten
				VEKSingle<String> subList = new VEKSingle<>();
	
				for (FilterVariante variante: comp.getVarianten()) {
					if (variante.isAktiv()) {
						subList.addAll(variante.getvIdMembers());
//						DEBUG.System_println("Variante: "+variante.getKEY().name()+": "+variante.getvIdMembers().size());
					}
				}
				
//				DEBUG.System_println(this.getKenner()+": komponente enthaelt: "+subList.size());
				
				//jetzt alle aus der vAllowed rausschmeissen, die nicht in der sublist sind
				VEKSingle<String> vCopy = new VEKSingle<>();
				for (String id: vNewSegmentationVector) {
					if (subList.contains(id)) {
						vCopy.add(id);
					}
				}
				vNewSegmentationVector.clear();
				vNewSegmentationVector.addAll(vCopy);
//				DEBUG.System_println("ueberig bleibt: "+vNewSegmentationVector.size());

				comp.showAddonComponentForListTitelIsActiv();
				
			}
		}
		
		//jetzt sind nur noch die erlaubten vorhanden
		//den segmentationvektor leeren und neu fuellen, liste neu aufbauen
		this.getNavigationListeThisBelongsTo().get_vectorSegmentation().clear();
		this.getNavigationListeThisBelongsTo().set_newContentVector(vNewSegmentationVector);
		this.getNavigationListeThisBelongsTo().gotoSiteWithID_orFirstSite(null);

	}
	
	
	
	/**
	 * sicherung der original-navilist-segmentation, falls noch nicht vorhanden
	 */
	public default void  storeUnfilteredNaviListIdsToFirstMember() {

		//zuest alle vectoren erzeugen
		for (MyE2IF__Component comp: this.getNavigationListeThisBelongsTo().get_oComponentMAP__REF().values()) {
			if (comp instanceof IfFilterExtForListComponents) {
				((IfFilterExtForListComponents)comp).get_vIdsOrig();
			}
		}

		IfFilterExtForListComponents first = null;
		for (MyE2IF__Component comp: this.getNavigationListeThisBelongsTo().get_oComponentMAP__REF().values()) {
			if (comp instanceof IfFilterExtForListComponents) {
				first = (IfFilterExtForListComponents)comp;
				break;
			}
		}
		
		if (first != null) {
			if (first.get_vIdsOrig().size()==0) {
				first.get_vIdsOrig().addAll(this.getNavigationListeThisBelongsTo().get_vectorSegmentation());
			}
		}
		
	}

	
	
	public default void clearAllFilters(boolean activeFilterAfterClean) throws myException {
		for (MyE2IF__Component comp: this.getNavigationListeThisBelongsTo().get_oComponentMAP__REF().values()) {
			if (comp instanceof IfFilterExtForListComponents) {
				((IfFilterExtForListComponents)comp).clearFilter(false);
			}
		}
		if (activeFilterAfterClean) {
			this.activateFilters();
		}
	}
	
	
	
	public default void clearFilter(boolean activeFilterAfterClean) throws myException {
		this.getVarianten().clear();
		this.showAddonComponentForListTitelIsEmpty();

		if (activeFilterAfterClean) {
			this.activateFilters();
		}
	}

	public default boolean isFilterprepared() {
		return this.getVarianten().size()>0;
	}
	

	public default void clearvIDs_origMap() {
		this.getvCopyOfVector4Segmentation().clear();
	}


}
