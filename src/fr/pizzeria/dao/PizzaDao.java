package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public interface PizzaDao {
	
		
	/**
	 * Permet de r�cup�rer un objet Pizza depuis List<Pizza> dont l'index correspond � l'entier pass� en param�tre
	 * 
	 * @param index 
	 		Entier correspondant � un index de l'objet {@link Pizza}
	 * @return Pizza - 
	 * 		Un objet {@link Pizza}
	 */
	Pizza recupererPizza(int index);
	
	/**
	 * Permet de r�cup�rer une List d'objet Pizza
	 * 
	 * @return List<{@link Pizza}> - 
	 * 		Une {@link List} d'objet {@link Pizza}
	 */
	List<Pizza> getListePizza();
	
	/**
	 * Retourne @true une des valeurs de l'�num�ration {@link CategoriePizza} contient le String pass� en param�tre, sinon renvoie @false
	 *  
	 * @param type 
	 * 		String - correspondant � la chaine de caract�res � tester
	 * @return
	 * 		boolean
	 */
	boolean isCategorieExist(String type);
	
	/**
	 * Permet d'ajouter un objet {@link Pizza} � une {@link Link}
	 * 
	 * @param code - 
	 * 		String correspondant � un code unique � 3 lettre
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'�num�ration)
	 * @return
	 * 		Entier correspondant � l'index de la pizza dans la liste.
	 * @throws PizzaException
	 * 		Les exceptions sont g�n�r� lors de la v�rification des champs permettant la cr�ation de l'objet Pizza voir {@link PizzaDaoList}
	 */
	int ajouter( String code, String nom, Double prix, String type ) throws PizzaException;
	
	/**
	 * Permet de modifier un objet {@link Pizza} dans une {@link Link}
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'index de la pizza dans la liste
	 * @param code - 
	 * 		String correspondant � un code unique � 3 lettre
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'�num�ration)
	 * @return
	 * 		Entier correspondant � l'index de la pizza qui � �t� modifi�
	 * @throws PizzaException
	 */
	int modifier( Integer index, String code, String nom, Double prix, String type ) throws PizzaException;
	
	/**
	 * Permet de modifier un objet {@link Pizza} dans une {@link Link}
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'index de la pizza � modifier
	 * @param code - 
	 * 		String correspondant � un code unique � 3 lettre
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'�num�ration)
	 * @return
	 * 		Entier correspondant � l'index de la pizza dans la liste.
	 * @throws PizzaException
	 * 		Les exceptions sont g�n�r� lors de la v�rification des champs permettant la cr�ation de l'objet Pizza voir {@link PizzaDaoList}
	 */
	int modifier( Integer index, String nom, Double prix, String type ) throws PizzaException;
	
	/**
	 * Permet de supprimer un objet {@link Pizza} d'une {@link List}
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'objet pizza � supprimer
	 * @return
	 * 		Entier correspondant � l'index de la pizza supprimer
	 * @throws PizzaException
	 */
	int supprimer( int index ) throws PizzaException;
}
