package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import fr.pizzeria.exception.CodeException;
import fr.pizzeria.exception.IndexException;
import fr.pizzeria.exception.NomException;
import fr.pizzeria.exception.PizzaException;
import fr.pizzeria.exception.PrixException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements PizzaDao {
	
	private List<Pizza> listePizza = new ArrayList<Pizza>();
	
	private FichierMetier fichier = new FichierMetier();
	
	/**
	 * Constructeur par d�fault de la classe PizzaDaoImpl qui impl�mente l'interface PizzaDao
	 * Ce constructeur permet de cr�er une liste d'objet {@link Pizza} pr�d�finie.
	 * 
	 */
	public PizzaDaoImpl() {
		
		listePizza.add(new Pizza(0,"PEP","P�p�roni",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(1,"MAR","Margherita",14.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(2,"REI","La Reine",11.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(3,"FRO","La 4 Fromages",12.00, CategoriePizza.SANS_VIANDE));
		listePizza.add(new Pizza(4,"CAN","Cannibale",12.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(5,"SAV","Savoyarde",13.00, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(6,"ORI","L'Orientale",13.50, CategoriePizza.VIANDE));
		listePizza.add(new Pizza(7,"IND","L'Indienne",14.00, CategoriePizza.VIANDE));
		
		listePizza.forEach(p -> {
			fichier.SauvegarderDansFichier(p);
	});
	}
	
	@Override
	public List<Pizza> getListePizza() {
		return this.listePizza;
	}
	
	@Override
	public int getNombrePizza() {
		
		return listePizza.size();
	}
	
	@Override
	public Pizza recupererPizza( int index ){
		
		return listePizza.get(index);
	}

	
	/**
	 * Cr�er un nouvel objet {@link Pizza} en r�cup�rant le retour de la fonction {@link #creerPizza(Integer, String, String, Double, String)} <br/>
	 * Ajoute cet objet � {@link #listePizza} <br/>
	 * Cr�er un fichier texte de sauvegarde en appellant la m�thode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * Retourne la taille de {@link #listePizza} (qui correspond � l'index de la derni�re pizza ajouter)
	 */
	@Override
	public int ajouter( String code, String nom, Double prix, String type ) throws PizzaException {
		
		Pizza pizza = creerPizza( listePizza.size(), code, nom, prix,  type);
		listePizza.add( pizza );
		fichier.SauvegarderDansFichier( pizza );
		return (listePizza.size()-1);
	}

	/**
	 * V�rifie les diff�rents param�tres d'entr�e gra�e aux m�thodes :<br/>
	 * <ul>
	 * 	<li>{@link #verifierCode(String)}</li>
	 * 	<li>{@link #verifierNom(String)}</li>
	 * 	<li>{@link #verifierPrix(Double)}</>
	 * 	<li>{@link #verifierType(String)}</li>
	 * </ul>
	 * 
	 * Ecrase le fichier de sauvegarde de cette pizza avec les nouvelles valeurs en utilisant la m�thode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * 
	 */
	@Override
	public int modifier( Integer index, String code, String nom, Double prix, String type ) throws PizzaException {
		
		listePizza.get(index).setCode(verifierCode(code));
		listePizza.get(index).setNom(verifierNom(nom));
		listePizza.get(index).setPrix(verifierPrix(prix));
		fichier.SauvegarderDansFichier(listePizza.get(index));
		return index;	
	}
	
	
	/**
	 * V�rifie les diff�rents param�tres d'entr�e gra�e aux m�thodes :<br/>
	 * <ul>
	 * 	<li>{@link #verifierNom(String)}</li>
	 * 	<li>{@link #verifierPrix(Double)}</>
	 * 	<li>{@link #verifierType(String)}</li>
	 * </ul>
	 * 
	 * Ecrase le fichier de sauvegarde de cette pizza avec les nouvelles valeurs en utilisant la m�thode {@link FichierMetier #SauvegarderDansFichier(Pizza)}<br/>
	 * <br/>
	 * 
	 */
	@Override
	public int modifier( Integer index, String nom, Double prix, String type ) throws PizzaException {
		
		listePizza.get(index).setNom(nom);
		listePizza.get(index).setPrix(prix);
		fichier.SauvegarderDansFichier(listePizza.get(index));
		return index;
	}

	/**
	 * V�rifie si l'index pass� en param�tre est valide grace � la m�thode {@link #verifierIndex(Integer)}<br/>
	 * Supprimer le fichier texte de sauvegarde de la pizza correspondante au param�tre
	 * Supprime la pizza de {@link #ListePizza}<br/>
	 * 
	 */
	@Override
	public int supprimer( int index ) throws PizzaException {
		
		verifierIndex(index);
		fichier.SupprimerDansFichier(listePizza.get(index));
		listePizza.remove(index);
		return index;	
	}
	
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
	private Pizza creerPizza( Integer index, String code, String nom, Double prix, String type) throws PizzaException {	
		
		return new Pizza( verifierIndex(index), verifierCode(code), verifierNom(nom), verifierPrix(prix), verifierType(type));
	}
	
	
	/* -------------------- METHODE DE VERIFICATION ------------------------------ */
	
	
	/**
	 * V�rifie si l'index pass� en param�tre est positif et si il est pr�sent dans la {@link #listePizza}
	 * 
	 * @param index - 
	 * 		Integer correspondant � l'index de la pizza
	 * @return
	 * 		l'index pass� en param�tre si il n'y a pas eu d'exception
	 * @throws PizzaException
	 * 		Renvoie une {@link PizzaException} de type {@link IndexException}
	 */
	private Integer verifierIndex(Integer index) throws PizzaException {
		
		if( index != null && index >= 0 && index <= listePizza.size() ) {
			return index;
		} else {
			throw new IndexException("L'index : " + index + " n'est pas valide");
		}
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
	private String verifierCode(String code) throws PizzaException {
		
		if( code != null && code.matches("[^0-9]{3}")) {
			if( listePizza.stream().map(Pizza::getCode).filter(f -> f.equals(code)).findAny().isPresent() ) {
				throw new CodeException("Le code " + code + " n'est pas valide");
			}
		}
		return code;		
	}

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
	private String verifierNom(String nom) throws PizzaException {

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
	private Double verifierPrix(Double prix) throws PizzaException{
		
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
	private CategoriePizza verifierType(String type) throws PizzaException {
		
		if( type != null && isCategorieExist(type) ) {
			return CategoriePizza.valueOf(type.toUpperCase());
		} else {
			throw new PizzaException("Le type : " + type + " n'est pas valide");
		}
	}

	@Override
	public boolean isCategorieExist(String type) {
		return Stream.of(CategoriePizza.values()).filter(c -> c.name().equalsIgnoreCase(type)).findAny().isPresent() ;
	}
}