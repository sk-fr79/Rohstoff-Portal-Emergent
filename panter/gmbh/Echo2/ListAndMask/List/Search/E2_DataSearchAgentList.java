package panter.gmbh.Echo2.ListAndMask.List.Search;

import java.util.StringTokenizer;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Extent;

import org.apache.commons.collections.CollectionUtils;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorHelpBackground;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_BASIC_InfoMessageWithAddonComponent;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_TextField;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;


/**
 * suchagent fuer die standard-suche
 */
public class E2_DataSearchAgentList extends		E2_DataSearchAgentAbstract
{
	public static int ANZAHL_AB_DER_NEUSORTIERT_WIRD = 10;

	
	private E2_NavigationList 	oNavigationList = null;
	
	
	
	
	public E2_DataSearchAgentList(E2_NavigationList onavigationlist) throws myException
	{
		super();
		
		oNavigationList = onavigationlist;
		if (this.oNavigationList == null)
			throw new myException("MyE2_DataSearchListSearchAgent:Contructor:Navigation-object is null");
	}

	public void ResetSearch(E2_DataSearch oDataSearch, boolean bForceRebuild) throws myException
	{
		//ein neuaufbau via reset ist nur noetig, wenn die suche vorher schon mal stattgefunden hat
		if (this.oNavigationList.get_vIDs_From_Search().size()>0 || bForceRebuild)
		{
			this.oNavigationList.get_vIDs_From_Search().removeAllElements();
			Vector<String> vIDs = this.oNavigationList.build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components("",bForceRebuild);
			String cID_Active = this.oNavigationList.get_cID_Unformated_Of_LastActive_Row();
			this.oNavigationList.set_newContentVector(vIDs);
			this.oNavigationList.gotoSiteWithID_orFirstSite(cID_Active);
			
			//die hidden-saetze auch loeschen
			this.oNavigationList.get_vIDs_Found_ButNotInSelektion().removeAllElements();
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void StartSearch(E2_DataSearch oDatasearch, E2_SearchDefinition oSearchSpecial) throws myException
	{
		MyDBToolBox oDB = bibALL.get_myDBToolBox();

		Vector<String> vFoundWithSearch = new Vector<String>();
		
		boolean bSucheIstGueltig = false;  // wird bei dem ersten ausgefuellten suchfeld aktiv
		
		
		//auf jeden fall die hidden-eintraege in der navilist resetten
		this.oNavigationList.get_vIDs_Found_ButNotInSelektion().removeAllElements();

		
		
		Vector<E2_SearchDefinition> vSearchDefsForSearch = new Vector<E2_SearchDefinition>();
		if (oSearchSpecial!=null)
		{
			vSearchDefsForSearch.add(oSearchSpecial);
		}
		else
		{
			vSearchDefsForSearch.addAll(oDatasearch.get_SelectedSearchDefs());
		}
		
		for (int i=0;i<oDatasearch.get_vSearchFields().size();i++)
		{
			MyE2_TextField ofield = (MyE2_TextField)oDatasearch.get_vSearchFields().get(i);
			String cText = ofield.getText();
			if (!bibALL.isEmpty(cText))
			{
				bSucheIstGueltig = true;
				vFoundWithSearch = new Vector<String>(CollectionUtils.union(vFoundWithSearch,this.get_ID_VectorOfTextField(cText,vSearchDefsForSearch,oDB)));
			}
		}
		bibALL.destroy_myDBToolBox(oDB);

		if (bSucheIstGueltig)
		{
			if (vFoundWithSearch.size()>0)
			{
				
				//2011-06-17: Flexiblere Such-Methode
				int iFoundWithSearch = vFoundWithSearch.size();
				
				this.oNavigationList.get_vIDs_From_Search().removeAllElements();
				this.oNavigationList.get_vIDs_From_Search().addAll(vFoundWithSearch);
				Vector<String> vIDs_Schnittmenge = this.oNavigationList.build_ID_VECTOR_WITH_SEARCH_AND_SELECT_Components("",oDatasearch.get_bForceListQueryOnSearch());   // hier wird nur neu gemischt, nicht neu geladen,
				 																										  // da der basis-aufbau der liste sonst zu lange dauert
				
				int iAnzahlSchnittmenge = vIDs_Schnittmenge.size();
				
				String cID_Active = this.oNavigationList.get_cID_Unformated_Of_LastActive_Row();
				this.oNavigationList.set_newContentVector(vIDs_Schnittmenge);
				this.oNavigationList.gotoSiteWithID_orFirstSite(cID_Active);
				
				
				if (iAnzahlSchnittmenge==0)           //dann wurde nichts gefunden, was gleichzeitig auch in der selektion ist
				{
					
					
//					Vector<MyE2IF__Component> vZusatzComponente = new Vector<MyE2IF__Component>();
//					vZusatzComponente.add(new buttonSelektionAusgeblendeteAnzeigen(vFoundWithSearch,vFoundWithSearch));
					
					MyE2_String cMSG = new MyE2_String(""+iFoundWithSearch+" ",false,
											"Einträge wurden gefunden. Ihre momentane Selektion " +
											"verhindert die Anzeige, da die gefundenen Einträge nicht mit der Selektion übereinstimmen ",true);
					
					//bibMSG.add_MESSAGE(new MyE2_Info_Message(cMSG,vZusatzComponente));
					bibMSG.add_MESSAGE(new MyE2_BASIC_InfoMessageWithAddonComponent(	cMSG,
																						new buttonSelektionAusgeblendeteAnzeigen(vFoundWithSearch,vFoundWithSearch),
																						new Extent(600),
																						new Extent(230)));
				}
				else if (iFoundWithSearch != iAnzahlSchnittmenge)           //dann wurden mehr saetze gefunden, als in der selektion vorhanden waren
				{
					
					//verdeckte ids separat anzeigen lassen
					Vector<String> vHidden = new Vector<String>();
					vHidden.addAll(vFoundWithSearch);
					vHidden.removeAll(vIDs_Schnittmenge);
					
//					Vector<MyE2IF__Component> vZusatzComponente = new Vector<MyE2IF__Component>();
//					vZusatzComponente.add(new buttonSelektionAusgeblendeteAnzeigen(vFoundWithSearch, vHidden));


					MyE2_String cMSG = new MyE2_String(	""+iFoundWithSearch+" ",false,
														"Einträge gefunden, davon ",true, ""+(iFoundWithSearch-iAnzahlSchnittmenge),false,
														" ausgeblendet (außerhalb der Selektion). Angezeigt werden : ",true,
														""+iAnzahlSchnittmenge,false," Einträge.",true);
					
					//bibMSG.add_MESSAGE(new MyE2_Info_Message(cMSG,vZusatzComponente));
					bibMSG.add_MESSAGE(new MyE2_BASIC_InfoMessageWithAddonComponent(	cMSG,
																						new buttonSelektionAusgeblendeteAnzeigen(vFoundWithSearch, vHidden),
																						new Extent(600),
																						new Extent(230)));
				}
				else           												//dann sind alle gefundenen saetze in der selektion enthalten
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String((iFoundWithSearch==1?"Gefundener Eintrag: ":"Gefundene Einträge: ")+iFoundWithSearch)));
				}
				
			}
			else
			{
				this.ResetSearch(oDatasearch,false);
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Suche hat KEIN Ergebnis !!!"));
			}
		}
		else
		{
			this.ResetSearch(oDatasearch,false);
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Der Such-Cache wurde geleert !!!"));
		}
	}

	
	

	
	
	//2011-06-17: neue Version, auch bei teilverdeckten suchergebnissen
	private class buttonSelektionAusgeblendeteAnzeigen extends MyE2_Button
	{

		private Vector<String> 			vGefundene_incl_HIDDEN_IDs = 	new Vector<String>();
		private Vector<String> 			vHiddenIDs = 					new Vector<String>();
		
		
		public buttonSelektionAusgeblendeteAnzeigen(Vector<String> Gefundene_incl_HIDDEN_IDs ,Vector<String> HiddenIDs) 
		{
			super(new MyE2_String("Selektion AUS, ALLE ",true,""+Gefundene_incl_HIDDEN_IDs.size(),false," Einträge anzeigen",true));
			this.setStyle(MyE2_Button.StyleTextButton(	new E2_ColorHelpBackground(), 	
														Color.LIGHTGRAY, 	
														Color.BLACK,	 
														Color.DARKGRAY,
														new Alignment(Alignment.CENTER, Alignment.CENTER),
														true));
			
			this.setLayoutData(MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorHelpBackground(), 1, 1));
			
			this.vGefundene_incl_HIDDEN_IDs.addAll(Gefundene_incl_HIDDEN_IDs);
			this.vHiddenIDs.addAll(HiddenIDs);
			
			this.add_oActionAgent(new XX_ActionAgent() 
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException 
				{
					
					/*
					 * jetzt pruefen, wie verfahren wird:
					 * bei anzahl< E2_DataSearchAgentList.ANZAHL_AB_DER_NEUSORTIERT_WIRD wird einfach angezeigt 
					 */
					buttonSelektionAusgeblendeteAnzeigen oThis = buttonSelektionAusgeblendeteAnzeigen.this;

					String cID_Active = E2_DataSearchAgentList.this.oNavigationList.get_cID_Unformated_Of_LastActive_Row();
					if (oThis.vGefundene_incl_HIDDEN_IDs.size()<=E2_DataSearchAgentList.ANZAHL_AB_DER_NEUSORTIERT_WIRD)
					{
						//2011-06-17: neu, wird als contentvector und nicht als momentane seite angezeigt
						E2_DataSearchAgentList.this.oNavigationList.set_newContentVector(buttonSelektionAusgeblendeteAnzeigen.this.vGefundene_incl_HIDDEN_IDs);
					}
					else
					{
						//dann muss der Vector oThis.vGefundene_incl_HIDDEN_IDs neu sortiert werden gegen die selektion aller
						//datensaetze (ungefiltert)
						Vector<String> vIDsUngefiltertSortiert = E2_DataSearchAgentList.this.oNavigationList.build_BASE_ID_Vector("",false);
						vIDsUngefiltertSortiert.retainAll(oThis.vGefundene_incl_HIDDEN_IDs);
						
						E2_DataSearchAgentList.this.oNavigationList.set_newContentVector(vIDsUngefiltertSortiert);
					}

					E2_DataSearchAgentList.this.oNavigationList.get_vIDs_Found_ButNotInSelektion().removeAllElements();
					E2_DataSearchAgentList.this.oNavigationList.get_vIDs_Found_ButNotInSelektion().addAll(buttonSelektionAusgeblendeteAnzeigen.this.vHiddenIDs);
					
					E2_DataSearchAgentList.this.oNavigationList.gotoSiteWithID_orFirstSite(cID_Active);
					
					MyE2_Label Hinweis = new MyE2_Label(new MyE2_String("Einträge ausserhalb der Selektion sind markiert"),true);
					Hinweis.setBackground(new E2_ColorHelpBackground());
					Hinweis.setLayoutData(MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,2,2,2), new E2_ColorHelpBackground(), 1, 1));
//					Hinweis.setBackground(new E2_ColorHelpBackground());
//					Vector<MyE2IF__Component> vZusatz = new Vector<MyE2IF__Component>();
//					//vZusatz.add(Hinweis.get_InBorderGrid(new Border(2, new E2_ColorHelpBackground(), Border.STYLE_SOLID), null, E2_INSETS.I_1_1_1_1));
//					vZusatz.add(Hinweis);
					
					//bibMSG.add_MESSAGE(new MyE2_Info_Message("Zurück zur alten Selektion mit dem Suche-Löschen-Knopf !!",vZusatz));
					bibMSG.add_MESSAGE(new MyE2_BASIC_InfoMessageWithAddonComponent(
							new MyE2_String("Zurück zur alten Selektion mit dem Suche-Löschen-Knopf !!"),Hinweis,new Extent(400), new Extent(300)));
				}
			});
			
			
		}
		
	}
		
	

	
	
	/*
	 * baut den vector, der das ergebnis eines eingabefeldes repraesentiert
	 */
	@SuppressWarnings("unchecked")
	private Vector<String> get_ID_VectorOfTextField(String cSuchText,Vector<E2_SearchDefinition> vSearchDefinition ,MyDBToolBox oDB )
	{
		Vector<String> 					vIDs_Rueck = new Vector<String>();

		
		if (!cSuchText.equals(""))
		{
			Vector<String> vSuchWorte	= new Vector<String>();

			//2012-02-06: suchworte mit "" zusammenfassen
			if (cSuchText.startsWith("\"") && cSuchText.endsWith("\""))
			{
				vSuchWorte.add(cSuchText.substring(1,cSuchText.length()-1));
			}
			else
			{
				StringTokenizer stHelp = new StringTokenizer(cSuchText," ");
				
				while (stHelp.hasMoreElements())
				{
					vSuchWorte.add(stHelp.nextToken());
				}
			}
			
			for (int i=0,iZahl=vSuchWorte.size();i<iZahl;i++)
			{
				Vector<String>  vInnen = new Vector<String>();    // vereinigungsvector fuer die suchelemente
				Vector<String>  vQuerys = new Vector<String>();        //sammelt die abfrage-bloecke, die per union verbunden werden
				try
				{
					for (int k=0,iZahl2=vSearchDefinition.size();k<iZahl2;k++)
					{
						/*
					     * nur wenn der suchbegriff markiert ist
					     */
				    	vQuerys.add(vSearchDefinition.get(k).get_cSearchString());
					}
					
					vInnen = this.ErzeugeID_Liste(vQuerys, vSuchWorte.get(i), oDB);
				}
				catch (myException ex)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehlerhafte Suchfeld-Definition !!!"));
					bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
					return new Vector<String>();
				}
				
				if (i==0)   // beim ersten suchfragment wird der inhalt des ganzen vectors genommen, dann wird nur noch geschnitten
				{
					vIDs_Rueck = new Vector<String>(vInnen);
				}
				else
				{
					vIDs_Rueck = new Vector<String>(CollectionUtils.intersection(vIDs_Rueck,vInnen));
				}
		    }
		}
		return vIDs_Rueck;
	}
	
	
//	/*
//	 * methode macht eine einzelne abfrage und gibt die id-liste zurueck
//	 */
//	private Vector<String> ErzeugeID_Liste(String cQuery, String cSuchText, MyDBToolBox oDB) throws myException
//	{
//	    String[][] cRueck = null; 
//	    String cSQLQuery = bibALL.ReplaceTeilString(cQuery,"#WERT#",bibALL.MakeSqlInnerString(cSuchText));
//	    
//	    
//	    /*
//	     * jetzt erfolgt die abfrage
//	     */
//	    cRueck = oDB.EinzelAbfrageInArray(cSQLQuery,"");
//	    if (cRueck == null)
//	    {
//	    	DEBUG.System_println("Error in Searchdef ...", "");
//	    	DEBUG.System_println(cSQLQuery, "");
//	        throw new myException("Error in Query "+cSQLQuery);
//	    }
//	     
//	    Vector<String> vRueck = new Vector<String>();
//	    for (int i=0;i<cRueck.length;i++)
//	    	vRueck.add(cRueck[i][0]);
//	    
//	    return vRueck;
//	}


	
	/*
	 * methode macht eine union-abfrage aus vielen und gibt die id-liste zurueck
	 */
	private Vector<String> ErzeugeID_Liste(Vector<String> vQuery, String cSuchText, MyDBToolBox oDB) throws myException
	{
	    String[][] cRueck = null; 
	    
	    String cSQL_Union  = ""; 
	    for (int i=0;i<vQuery.size();i++)
	    {
	    	cSQL_Union = cSQL_Union+vQuery.get(i)+ " UNION ";
	    }
	    cSQL_Union = cSQL_Union.substring(0,cSQL_Union.length()-7);
	    
	    
	    String cSQLQuery = bibALL.ReplaceTeilString(cSQL_Union,"#WERT#",bibALL.MakeSqlInnerString(cSuchText));
	    
	    DEBUG.System_println(cSQLQuery, DEBUG.DEBUG_FLAGS.SQL_STATEMENT_FROM_SEARCH.name());   //2015-10-21, martin
	    
	    /*
	     * jetzt erfolgt die abfrage
	     */
	    cRueck = oDB.EinzelAbfrageInArray(cSQLQuery,"");
	    if (cRueck == null)
	    {
	    	DEBUG.System_println("Error in Searchdef ...", "");
	    	DEBUG.System_println(cSQLQuery, "");
	        throw new myException("Error in Query "+cSQLQuery);
	    }
	     
	    Vector<String> vRueck = new Vector<String>();
	    for (int i=0;i<cRueck.length;i++)
	    	vRueck.add(cRueck[i][0]);
	    
	    return vRueck;
	}

	

	
}
