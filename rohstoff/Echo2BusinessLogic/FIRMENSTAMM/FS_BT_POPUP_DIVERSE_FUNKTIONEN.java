package rohstoff.Echo2BusinessLogic.FIRMENSTAMM;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgentWhenCloseWindow;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.__BASIC_MODULS.ADMINTOOLS.Tool_ChangeAdressIDs;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS.FS_ListBtGeoCodingAdresses;
import rohstoff.Echo2BusinessLogic.FIRMENSTAMM.SANKTION.FS_ListBtAdressesSanktionPruefung;



/**
 * @author martin 
 * allgemeiner platz fuer alle moeglichen listenbasierten methoden und funktionen
 */
public class FS_BT_POPUP_DIVERSE_FUNKTIONEN extends MyE2_PopUpMenue {

	private FS_ListSelector  listSelektor = null;

	public FS_BT_POPUP_DIVERSE_FUNKTIONEN(E2_NavigationList  oNaviList, FS_ListSelector  oListSelektor) 
	{
		super(E2_ResourceIcon.get_RI("popup.png"),E2_ResourceIcon.get_RI("leer.png"), false);
		this.listSelektor = oListSelektor;

		this.addButton(new buttonAdresseanhaengeUmdefinieren(), true);

		//2011-07-29: kopier-funktion fuer adressen
		this.addButton(new FS_BT_KOPIERE_FIRMA_UND_ANHAENGE(oNaviList), true);

		//2014-04-09: neue buttons fuer den aufbau und das fuellen der kostenrelationen
		this.addButton(new FS_BT_Ermittle_Kosten_Relationen_In_AdressListModul(), true);
		this.addButton(new FS_BT_Ermittle_Kosten_In_AdressListModul(oNaviList), true);
		this.addButton(new FS_BT_Ermittle_Kosten_betraege_in_warenbewegungen(oNaviList), true);

		//2015-04-15: New button for checkin the tax Id validity via web service (nils)
		this.addButton(new FS_BT_CheckTaxIdValidity(oNaviList), true);

		//2014-11-05: alle belege aus dem archiv ziehen
		this.addButton(new FS_BT_HOLE_BELEGE_AUS_ARCHIV(oNaviList), true);

		//2016-03-10: suche nach unvollstaendigen EU-Vertragseintraegen
		this.addButton(new FS_BT_SucheAdresseMit_EU_VERTRAGS_PROBLEM(this.listSelektor.get_selector_Flexible()), true);

		//2018-02-01: geocodierung
		this.addButton(new FS_ListBtGeoCodingAdresses(oNaviList), true);

		//2018-04-24:Adresse Gefahr
		this.addButton(new FS_ListBtAdressesSanktionPruefung(oNaviList), true);
	}


	// button, um die umdefinition der anhaenge zweier Adresse umzuhaengen
	private class buttonAdresseanhaengeUmdefinieren extends MyE2_Button
	{
		public buttonAdresseanhaengeUmdefinieren() 
		{
			super(new MyE2_String("Adressanhänge zusammenfassen"));
			this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST,"ADRESSENREFERENZEN_ZUSAMMENFASSEN"));
			this.add_oActionAgent(new ActionAgentAdresseanhaengeUmdefinieren());
		}
	}


	private class ActionAgentAdresseanhaengeUmdefinieren extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			E2_BasicModuleContainer oListContainer = new E2_RecursiveSearchParent_BasicModuleContainer().get_First_FoundContainer();

			if (oListContainer != null)
			{
				Vector<Component> vComponents = new E2_RecursiveSearch_Component(oListContainer,bibALL.get_Vector(E2_NavigationList.class.getName()),null).get_vAllComponents();

				// duerfte genau einen eintrag finden
				if (vComponents.size()==1)
				{
					E2_NavigationList oNaviListAdressen = (E2_NavigationList)vComponents.get(0);

					Vector<String> vSelectedAdresses = oNaviListAdressen.get_vSelectedIDs_Unformated();

					if (vSelectedAdresses.size() == 2)
					{
						try
						{
							new E2_ContainerToChange(vSelectedAdresses,oNaviListAdressen);
						}
						catch (myException ex)
						{
							bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
						}
					}
					else
					{
						bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte genau 2 Adressen markieren !!!"));
					}
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! Der Listencontainer wurde nicht gefunden (2) !!!"));
				}
			}
			else
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Achtung! Der Listencontainer wurde nicht gefunden (1) !!!"));	
			}


		}
	}



	private class E2_ContainerToChange extends E2_BasicModuleContainer
	{
		private Vector<String> vSelectedIDs = null;
		private E2_NavigationList oNaviListAdressen = null;

		public E2_ContainerToChange(Vector<String> selectedIDs, E2_NavigationList NaviListAdressen) throws myException
		{
			super();
			this.vSelectedIDs = selectedIDs;
			this.oNaviListAdressen = NaviListAdressen;

			Tool_ChangeAdressIDs oTool = new Tool_ChangeAdressIDs(E2_MODULNAMES.NAME_MODUL_FIRMENSTAMM_LIST);
			this.add(oTool, E2_INSETS.I_5_5_5_5);

			oTool.get_oTFAdresseQuelle().setText((String)vSelectedIDs.get(0));
			oTool.get_oTFAdresseZiel().setText((String)vSelectedIDs.get(1));


			this.add_CloseActions(new XX_ActionAgentWhenCloseWindow(this)
			{
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					E2_ContainerToChange.this.oNaviListAdressen.Refresh_ComponentMAP((String)E2_ContainerToChange.this.vSelectedIDs.get(0), E2_ComponentMAP.STATUS_VIEW);
					E2_ContainerToChange.this.oNaviListAdressen.Refresh_ComponentMAP((String)E2_ContainerToChange.this.vSelectedIDs.get(1), E2_ComponentMAP.STATUS_VIEW);
				}
			});

			this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(800), new Extent(900),new MyE2_String("Adressanhänge zusammenfassen ..."));
		}
	}

}
