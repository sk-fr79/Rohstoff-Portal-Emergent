package panter.gmbh.Echo2.ListAndMask.List.Settings;

import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDDark;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.S;

public class LIST_Setting_Plugin_Show_Infos_From_List extends MyE2_Grid
{
	
	private E2_NavigationList      		oNaviList = null;
	private String   					cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER = null;

	private E2_BasicModuleContainer     oContainer_this_belongs_to = null;
	
	public LIST_Setting_Plugin_Show_Infos_From_List(E2_NavigationList NaviList, E2_BasicModuleContainer Container_this_belongs_to)
	{
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
		
		this.oNaviList = NaviList;
		this.oContainer_this_belongs_to = Container_this_belongs_to;
		
		this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER = this.oNaviList.get_MODULE_IDENTIFIER_OF_CONTAINING_MODULE();
		
		if (S.isFull(this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER)) {
			HashMap<String, String> hmSpalten = new HashMap<String, String>();
			
			E2_ComponentMAP  oMAP_List = this.oNaviList.get_oComponentMAP__REF();
			
			try {
				for (String cKEY: oMAP_List.get_hmRealComponents().keySet()) {

					//DEBUG.System_println(cKEY);
					
					if (S.isFull(cKEY) && S.isFull(((MyE2IF__Component)oMAP_List.get_hmRealComponents().get(cKEY)).EXT().get_cList_or_Mask_Titel())) {
						hmSpalten.put(cKEY, ((MyE2IF__Component)oMAP_List.get_hmRealComponents().get(cKEY)).EXT().get_cList_or_Mask_Titel().CTrans());
					}
					
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			Vector<String> vSort = new Vector<String>();
			vSort.addAll(hmSpalten.keySet());
			Collections.sort(vSort);
			
			
			
			MyE2_Grid oGridInnen = new MyE2_Grid(2, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS_W100());
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Informationen zum Listenmodul:"),MyE2_Label.STYLE_NORMAL_BOLD()),2, E2_INSETS.I(2,5,2,5));
			
			oGridInnen.add(new MyE2_Label(new MyE2_String("Modulkenner:")),1, E2_INSETS.I(2,5,2,5));
			oGridInnen.add(new MyE2_Label(new MyE2_String(this.cMODULE_IDENTIFIER_OF_CONTAINING_MODULECONTAINER),MyE2_Label.STYLE_NORMAL_BOLD()),1, E2_INSETS.I(2,5,2,5));
			
			oGridInnen.add_Separator(E2_INSETS.I(0,2,0,2));

			oGridInnen.add(new MyE2_Label(new MyE2_String("Titel",true)), MyE2_Grid.LAYOUT_LEFT_TOP( E2_INSETS.I(2,5,2,5), new E2_ColorDDDark(), 1));
			oGridInnen.add(new MyE2_Label(new MyE2_String("Kennung",true)),MyE2_Grid.LAYOUT_LEFT_TOP( E2_INSETS.I(2,5,2,5), new E2_ColorDDDark(), 1));
			
			for (String cHASHKEY: vSort) {
				oGridInnen.add(new MyE2_Label(new MyE2_String(hmSpalten.get(cHASHKEY),false)),1, E2_INSETS.I(2,1,2,1));
				oGridInnen.add(new MyE2_Label(new MyE2_String(cHASHKEY,false),MyE2_Label.STYLE_NORMAL_BOLD()),1, E2_INSETS.I(2,1,2,1));
			}
		
			this.add(oGridInnen, E2_INSETS.I(1,1,1,1));
			
		}

	}

}
