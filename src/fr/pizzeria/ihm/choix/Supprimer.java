package fr.pizzeria.ihm.choix;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.ihm.IhmUtil;
import fr.pizzeria.model.Pizza;

public class Supprimer extends Choix {
	
	public Supprimer( IhmUtil ihm ) {
		super();
		this.ihm = ihm;
	}
	
	public Supprimer( IhmUtil ihm, int indexMenu ) {
		super();
		this.ihm = ihm;
		this.indexMenu = indexMenu;
		this.setDescription(indexMenu + " - SUPPRIMER PIZZA - " + indexMenu);
	}

	@Override
	public void executer() {
		
		Choix afficher = new AfficherCarte(ihm);
		afficher.executer();
		boolean valide = false;
		do{
			if( ihm.getPizzaDao().getNombrePizza() == 0 ){
				valide = true;
				break;
			} else {
				Integer index = ihm.getIndex(ihm.getPizzaDao().getNombrePizza(), "Selectionnez la pizza � supprimer");
				if( index.equals(Integer.valueOf(abandonner))) {
					valide = true;
					break;
				} else {
					try {
						Pizza pizzaAncienne = ihm.getPizzaDao().recupererPizza(index);
						ihm.systemOut("La pizza ci-dessous � �t� supprimer");
						ihm.afficherPizza(pizzaAncienne);
						ihm.getPizzaDao().supprimer(index);
						valide = true;
					} catch (PizzaException e) {
						ihm.systemOut(e.message());
					}
				}
			}
		}while(!valide);
	}
}