package fr.pizzeria.dao;

import java.util.stream.Stream;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.NomException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.PrixException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class MetierDaoPizza {

	/**
	 * Permet de cr�er un Objet {@link Pizza}<br/>
	 * 
	 * V�rifie les diff�rents param�tres d'entr�e gra�e aux m�thodes :<br/>
	 * <ul>
	 * 	<li>{@link #verifierIndex(Integer)}</li>
	 * 	<li>{@link #verifierCode(String)}</li>
	 * 	<li>{@link #verifierNom(String)}</li>
	 * 	<li>{@link #verifierPrix(Double)}</>
	 * 	<li>{@link #verifierType(String)}</li>
	 * </ul>
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'index de la pizza
	 * @param code - 
	 * 		String correspondant au code de la pizza
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @param type - 
	 * 		{@link CategoriePizza} correspondant au type de pizza (contenu dans l'�num�ration)
	 * @return
	 * 		{@link Pizza} l'objet cr��
	 * @throws PizzaException
	 */
	public Pizza creerPizza(String code, String nom, Double prix, String type) throws PizzaException {
		return new Pizza( verifierCode(code), verifierNom(nom), verifierPrix(prix), verifierType(type));
	}

	/**
	 * V�rifie si le code pass� en param�tre est bien compos� de 3 Lettre et si il n'est pas d�j� pr�sent dans la {@link #listePizza}
	 * 
	 * @param code - 
	 * 		String correspondant aux code de la pizza
	 * @return
	 * 		le code pass� en param�tre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link CodeException}
	 */
	public String verifierCode(String code) throws PizzaException {
		
		if( code != null && code.matches("[^0-9]{3}")) {
			return code;
		} else {
			throw new CodeException("Le code : " + code + " n'est pas valide");
		}
	}
	
	/* private String isCodeExist( String code ) {
		if( listePizza.stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
			throw new CodeException("Le code " + code + " existe d�j�");
		}
	} */

	/**
	 * V�rifie si le nom pass� en param�tre n'est pas null et ne d�passe pas les 13 caract�re
	 * 
	 * @param nom - 
	 * 		String correspondant au libell� de la pizza
	 * @return
	 * 		Le String pass� en param�tre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link NomException}
	 */
	public String verifierNom(String nom) throws PizzaException {

		if( nom != null && nom.length() <= 13 ) {
			return nom;
		} else {
			throw new NomException("Le nom : " + nom + " n'est pas valide");
		}
	}
	
	/**
	 * V�rifie si le prix entr� en param�tre n'est pas null
	 * 
	 * @param prix - 
	 * 		Double correspondant au prix de la pizza
	 * @return
	 * 		Le Double pass� en param�tre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link PrixException}
	 */
	public Double verifierPrix(Double prix) throws PizzaException{
		
		if( prix != null ) {
			return prix;
		} else {
			throw new PrixException("Le prix : " + prix + " n'est pas valide");
		}
	}
	
	/**
	 * V�rifie si le String pass� en param�tre n'est pas null et si ce String correspond � une des valeurs de l'�num�ration {@link CategoriePizza}
	 * 
	 * @param type - 
	 * 		String correspondant � une cat�gorie de pizza
	 * @return
	 * 		Une des valeurs de l'�num�ration {@link CategoriePizza} si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException}
	 */
	public CategoriePizza verifierType(String type) throws PizzaException {
		
		if( type != null && isCategorieExist(type) ) {
			return CategoriePizza.valueOf(type.toUpperCase());
		} else {
			throw new PizzaException("Le type : " + type + " n'est pas valide");
		}
	}
	
	public boolean isCategorieExist(String type) {
		return Stream.of(CategoriePizza.values()).filter(c -> c.name().equalsIgnoreCase(type)).findAny().isPresent() ;
	}

	public Pizza creerPizzaDepuisFichier(String p, String[] valeurs) throws Exception {
		return creerPizza( p, valeurs[0], Double.valueOf(valeurs[1]), valeurs[2]);
	}
}
