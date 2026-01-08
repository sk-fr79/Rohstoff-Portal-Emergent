package panter.gmbh.Echo2.Container;

import java.util.Vector;

import nextapp.echo2.app.Button;
import nextapp.echo2.app.CheckBox;
import nextapp.echo2.app.Column;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Grid;
import nextapp.echo2.app.Row;
import nextapp.echo2.app.SelectField;
import nextapp.echo2.app.TextField;
import nextapp.echo2.extras.app.MenuBarPane;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.Echo2.components.MyE2_WindowSplitPane;
import panter.gmbh.indep.exceptions.myException;
import echopointng.PopUp;

/*
 * klasse, die alle elemente in einem Container inaktiv macht, ausser einer
 * uebergebenen Negativ-Liste.
 * Alle elemente, die vorher enabled waren, werden gesammelt und spaeter wieder in den zustand von vorher versetzt
 */
public class E2_ComponentDisabler
{
	
	// elemente, die nicht angeruehrt werden
	private Vector<Component> vUntouchable = new Vector<Component>();
	
	
	private Vector<Component> vComponentsFound = new Vector<Component>();
	
	
	//alle elemente, die das interface MyE2IF__Component erfuellen, werden mit set_bEnabled_For_Edit(false) disable
	private Vector<MyE2IF__Component>  vComponentsStep1 = new Vector<MyE2IF__Component>();
	
	//es gibt (z.b. listenbuttons) elemente, wo die methode set_bEnabled_For_Edit nicht zum ziel fuehrt. diese 
	// elemente werden zusaetzlich noch mit Component.setEnabled(false) traktiert
	private Vector<MyE2IF__Component> vComponentsStep2 = new Vector<MyE2IF__Component>();

 
	//die window-panes werden separat betrachtet (close-button bringt einiges durcheinander, deshalb abschalten)
	private Vector<MyE2IF__Component> vComponentsWindowPanes = new Vector<MyE2IF__Component>();
	
	
	// elemente, die standard-komponenten sind
	private Vector<Component> vComponentsStandard = new Vector<Component>();

	
	
	//ein vector mit components, die genau komplementaer geschaltet werden (ein wenn alle anderen aus und umgekehrt)
	private Vector<Component> vComplement = new Vector<Component>();
	
	
	private Component  oContainerToDisable = null;
	
	/**
	 * 
	 * @param oContainerToDisable, falls null wird der ganze basiscontainer genommen
	 */
	public E2_ComponentDisabler(E2_BasicModuleContainer BasicContainer)
	{
		super();
		this.oContainerToDisable = BasicContainer;
		if (this.oContainerToDisable==null)
		{
			this.oContainerToDisable = bibE2.GET_FIRST_CONTENTPANE_IN_SESSION();
		}
	}

	
	public void INIT()
	{
		this.INIT(new Vector<Component>());
	}
	
	
	/**
	 * 
	 * @param vNotTouchable
	 */
	public void INIT(Vector<Component> vNotTouchable)
	{
		
		Vector<String> vSearchedTypes = new Vector<String>();
		vSearchedTypes.add(Button.class.getName());
		vSearchedTypes.add(PopUp.class.getName());
		vSearchedTypes.add(CheckBox.class.getName());
		vSearchedTypes.add(SelectField.class.getName());
		vSearchedTypes.add(TextField.class.getName());
		vSearchedTypes.add(MenuBarPane.class.getName());
		
		vSearchedTypes.add(MyE2_WindowPane.class.getName());
		vSearchedTypes.add(MyE2_WindowSplitPane.class.getName());

		this.vUntouchable.removeAllElements();
		this.vUntouchable.addAll(vNotTouchable);

		
		/*
		 * die uebergebenen vNotTouchable - komponenten koennen auch aus zusammengesetzten elementen bestehen,
		 * deshalb muss jedes einzelne auf container-status untersucht werden und alle inneren ebenfalls in den status notTouch gesetzt werden
		 * 
		 */
		
		Vector<Component> vNotTouchStep1 = new Vector<Component>();
		vNotTouchStep1.addAll(vNotTouchable);
		
		for (Component oComp: vNotTouchStep1)
		{
			if (oComp instanceof Column || oComp instanceof Row || oComp instanceof Grid)
			{
				E2_RecursiveSearch_Component oSearchInnerComps = new E2_RecursiveSearch_Component(oComp,vSearchedTypes,null);
				vUntouchable.addAll(oSearchInnerComps.get_vAllComponents());
			}
		}
			 

		
		//E2_RecursiveSearch_Component oSearchComponents = new E2_RecursiveSearch_Component(this.oContainer,vSearchedTypes,null);
		E2_RecursiveSearch_Component oSearchComponents = new E2_RecursiveSearch_Component(this.oContainerToDisable,vSearchedTypes,null);

		for (Component oComp: oSearchComponents.get_vAllComponents())
		{
			if (!(this.vUntouchable.contains(oComp)))
			{
				this.vComponentsFound.add(oComp);
			}
		}
	}
	

	public void setDisabled() throws myException
	{
		for (Component oComp: this.vComponentsFound)
		{
			//nur solche im status enabled betrachten
			if (oComp.isEnabled())
			{
				if (oComp instanceof MyE2IF__Component)
				{
					if (oComp instanceof MyE2_WindowPane || oComp instanceof MyE2_WindowSplitPane)
					{
						if (oComp instanceof MyE2_WindowPane)
						{
							((MyE2_WindowPane)oComp).setClosable(false);
						}
						else
						{
							((MyE2_WindowSplitPane)oComp).setClosable(false);
						}
						this.vComponentsWindowPanes.add((MyE2IF__Component)oComp);
					}
					else
					{
						((MyE2IF__Component)oComp).set_bEnabled_For_Edit(false);
						
						if (oComp.isEnabled())     // dann war es ein element, das z.b. in der liste, die standardmaessig disabled ist, immer enable sein muessen
							                       // bei diesen objekten wirkt das set_bEnabled_For_Edit nicht
						{
							oComp.setEnabled(false);
							
							this.vComponentsStep2.add((MyE2IF__Component)oComp);
						}
						else
						{
							this.vComponentsStep1.add((MyE2IF__Component)oComp);
						}
					}
				}
				else
				{
					oComp.setEnabled(false);

					this.vComponentsStandard.add(oComp);
				}
			}			
		}
		
		E2_ComponentDisabler.SetEnabled(this.vComplement);

	}
	
	
	
	public void setEnabled() throws myException
	{
		for (Component oComp: this.vComponentsStandard)
		{
			oComp.setEnabled(true);
		}
		
		for (MyE2IF__Component oComp: this.vComponentsStep1)
		{
			oComp.set_bEnabled_For_Edit(true);
		}
		
		for (MyE2IF__Component oComp: this.vComponentsStep2)
		{
			oComp.set_bEnabled_For_Edit(true);
			((Component)oComp).setEnabled(true);
		}
		
		for (MyE2IF__Component oComp: this.vComponentsWindowPanes)
		{
			if (oComp instanceof MyE2_WindowPane)
			{
				((MyE2_WindowPane)oComp).setClosable(true);
			}
			else
			{
				((MyE2_WindowSplitPane)oComp).setClosable(true);
			}
		}
		
		
		
		
		E2_ComponentDisabler.SetDisabled(this.vComplement);
		
		
		//nach enabled ist das objekt unbrauchbar !!!
		this.vUntouchable = new Vector<Component>();
		this.vComponentsFound = new Vector<Component>();
		this.vComponentsStep1 = new Vector<MyE2IF__Component>();
		this.vComponentsStep2 = new Vector<MyE2IF__Component>();
		this.vComponentsStandard = new Vector<Component>();
		this.vComplement = new Vector<Component>();
		
	}
	
	
	
	
	
	/*
	 * statische hilfsmethode, um einen Componentvector zu disablen oder enablen (REAL disabled)
	 */
	public static void SetEnabled(Vector<Component> vComps) throws myException
	{
		for (Component oComp: vComps)
		{
			//nur solche im status enabled betrachten
			if (!oComp.isEnabled())
			{
				if (oComp instanceof MyE2IF__Component)
				{
					((MyE2IF__Component)oComp).set_bEnabled_For_Edit(true);
					if (!oComp.isEnabled())
					{
						oComp.setEnabled(true);
					}
				}
				else
				{
					oComp.setEnabled(true);
				}
			}			
		}
		
	}
	

	
	/*
	 * statische hilfsmethode, um einen Componentvector zu disablen oder enablen (REAL enabled)
	 */
	public static void SetDisabled(Vector<Component> vComps) throws myException
	{
		for (Component oComp: vComps)
		{
			//nur solche im status enabled betrachten
			if (oComp.isEnabled())
			{
				if (oComp instanceof MyE2IF__Component)
				{
					((MyE2IF__Component)oComp).set_bEnabled_For_Edit(false);
					if (!oComp.isEnabled())
					{
						oComp.setEnabled(false);
					}
				}
				else
				{
					oComp.setEnabled(false);
				}
			}			
		}

	}



	public Vector<Component> get_vComplement()
	{
		return vComplement;
	}


	public Component get_ContainerToDisable()
	{
		return oContainerToDisable;
	}


	

	
}
