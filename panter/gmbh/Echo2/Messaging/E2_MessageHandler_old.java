package panter.gmbh.Echo2.Messaging;

import java.util.Vector;

import nextapp.echo2.app.Component;
import nextapp.echo2.app.event.ActionEvent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearchParent_BasicModuleContainer;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.indep.bibALL;

public class E2_MessageHandler_old
{

	private Vector<E2_BasicModuleContainer> 	vBasicContainerCALLING_Hierarchie = new Vector<E2_BasicModuleContainer>(); 
	private E2_BasicModuleContainer  			oBasicContainerTHIS = null;

	
	
	public E2_MessageHandler_old(ActionEvent oEvent)
	{
//		super();
//
//		if (oEvent != null && oEvent.getSource() instanceof Component)
//		{
//			Component oComp = (Component)oEvent.getSource();
//			
//			// der fall popup und drunterliegender container kann so abgedeckt werden
//			this.vBasicContainerCALLING_Hierarchie.removeAllElements();
//			
//			// sonderfall: E2_BasicModuleContainer - Popup in SlpitWindowPane, immer ein popup-Container, deshalb weitere suche unnoetig
//			if (oComp instanceof MyE2IF__Component && ((MyE2IF__Component)oComp).EXT().get_oBasicModulContainerThisBelongsTo()!=null)   
//			{
//				this.oBasicContainerTHIS = ((MyE2IF__Component)oComp).EXT().get_oBasicModulContainerThisBelongsTo();
//			}
//			else
//			{
//				this.oBasicContainerTHIS = new E2_RecursiveSearchParent_BasicModuleContainer(oComp).get_First_FoundContainer_WithMessageBlock();
//			}
//			
//			if (this.oBasicContainerTHIS != null)
//			{
//			  this.vBasicContainerCALLING_Hierarchie.addAll(oBasicContainerTHIS.get_vBasicContainerHierarchie());
//			  
//			  // der eigene wird jetzt in die reihe einsortiert
//			  this.vBasicContainerCALLING_Hierarchie.add(oBasicContainerTHIS);
//			}
//		}
	}
	
//	// sieht selbst nach, wo eine message angezeigt wird
//	public void showMessages()
//	{
//		
//		
//		
//		//test-Code fuer message-windows
////		bibE2.removeAllMessageWindowsFromBasicContentPane();           //alle vorigen messagecontainer weg
////		if (bibMSG.MV().size()>0)
////		{
////			bibE2.GET_FIRST_CONTENTPANE_IN_SESSION().add(new E2_MessageWindow());
////		}
//		
//		
//
//		
//		// zuerst den fall, dass ein button ein popupfenster aufruft (in dem die Message angezeigt wird) ...
//		if (bibE2.get_LASTPOPUP_CONTAINER()!=null && bibE2.get_LASTPOPUP_CONTAINER().get_bPopUpWasShown())
//		{
//			bibE2.get_LASTPOPUP_CONTAINER().showActualMessages();
//			bibE2.set_LAST_MESSAGE_CONTAINER(bibE2.get_LASTPOPUP_CONTAINER());
//		}
//		else
//		{
//			// jetzt alle vBasicContainerCALLING_Hierarchie - Vector von hinten nach vorne durchfraesen, bis einer passt
//			
//			boolean bMessageWasShown = false;
//			
//			for (int i=this.vBasicContainerCALLING_Hierarchie.size()-1;i>=0;i--)
//			{
//				E2_BasicModuleContainer oCont = (E2_BasicModuleContainer)this.vBasicContainerCALLING_Hierarchie.get(i);
//				if (oCont.get_bIsPopupContainer() && oCont.get_bPopUpWasShown() && oCont.get_bMessageBereichEin())
//				{
//					oCont.showActualMessages();
//					bMessageWasShown = true;
//					bibE2.set_LAST_MESSAGE_CONTAINER(oCont);
//					break;
//				}
//				else if (!oCont.get_bIsPopupContainer() && oCont.get_bMessageBereichEin())
//				{
//					oCont.showActualMessages();
//					bMessageWasShown = true;
//					bibE2.set_LAST_MESSAGE_CONTAINER(oCont);
//					break;
//				}
//			}
//			
//			//falls keiner gefunden wurde, weil alle popups bereits wieder geschlossen worden sind, dann nach dem letzten TimeStampenden-Container gucken
//			
//			if (!bMessageWasShown)
//			{
//				/*
//				 * damit das waek-reference-konstrukt funktioniert, hier einen garbade-collect
//				 * 
//				 */
//				if (!bibALL.get_DISABLE_EXPLICIT_GARBAGE_COLLECTION() ){
//					System.gc();
//				}
//				
//				E2_BasicModuleContainer  oContainerWithLastTimeStamp = bibE2.get_LAST_StartContainer_4_DBTimeStamps();
//				if (oContainerWithLastTimeStamp!=null)
//				{
//					oContainerWithLastTimeStamp.showActualMessages();
//					bMessageWasShown=true;
//				}
//				
//				
//			}
//			
//			
//			// notnagel
//			if (!bMessageWasShown)
//				bibE2.get_MessageAgent_InPOPUP().show_Messages();
//				
//		}
//	}
	
	
}
