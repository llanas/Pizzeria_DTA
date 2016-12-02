package fr.pizzeria.dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import fr.pizzeria.model.Pizza;

public class FichierMetier {

	private static final String CHEMIN 		= "src/main/resources/data";
	
	/**
	 * Permet de cr�er un fichier texte nomm� <CODE_PIZZA>.txt contenant les informations d'un objet {@link Pizza} tel que :
	 * "<NOM_PIZZA>;<PRIX_PIZZA>;<TYPE_PIZZA>
	 * 
	 * @param pizza
	 * 		Correspond � l'objet {@link Pizza} � enregistrer
	 */
	public void SauvegarderDansFichier( Pizza pizza ) {
		
		try {
			File fichier = new File(CHEMIN,  pizza.getCode() + ".txt");
			FileWriter ecrireDansFichier = new FileWriter(fichier);
			ecrireDansFichier.write(pizza.getNom() + ";" + pizza.getPrix() + ";" + pizza.getType().name());
			ecrireDansFichier.flush();
			ecrireDansFichier.close();
			} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Permet de supprimer le fichier texte d'un objet {@link Pizza} pass� en param�tre
	 * 
	 * @param pizza
	 * 		Correspond � l'objet {@link Pizza} � supprimer
	 */
	public void SupprimerDansFichier( String code ) {
		
		File fichier = new File(CHEMIN,  code + ".txt");
		fichier.delete();
	}
	
	/**
	 * Permet de supprimer tous les fichiers et dossier contenu dans le chemin pass� en param�tre.
	 * Dans le cas pr�sent, cette fonction est appel� � la fermeture de l'application.
	 * 
	 * 
	 * @param chemin
	 * 		Correspond au chemin du dossier dont le contenu est � supprimer
	 */
	public void SupprimerDossier( String chemin )
	{
	  File path = new File( CHEMIN );
	  if( path.exists() )
	  {
	    File[] files = path.listFiles();
	    for( int i = 0 ; i < files.length ; i++ )
	    {
	      if( files[ i ].isDirectory() )
	      {
	        SupprimerDossier( path+"\\"+files[ i ] );
	      }
	      files[ i ].delete();
	    }
	  }
	}
	
	public String[] listerFichier() {
		File data = new File(CHEMIN);
		return data.list();
	}

	public String[] recupererPizza(String codePizza) throws IOException {
		File pizzaFile = new File(codePizza + ".txt");
		FileReader f = new FileReader(pizzaFile);
		BufferedReader reader = new BufferedReader(f);
		String linePizza = reader.readLine();
		reader.close();
		String[] pizzaAttributes = linePizza.split(";");
		return pizzaAttributes;
		
	}
}
