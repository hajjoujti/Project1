package fr.eql.ai109.projet1;

public class Stagiaire {

	private String nom;
	private String prenom; 
	private int dep; 
	private String formation; 
	private int annee; 
	private long refGauche; 
	private long refDroite;
	
	
	public Stagiaire() {
		super();
		refGauche= 0L; 
		refDroite = 0L; 
	}
	
	
	public Stagiaire(String nom, String prenom, int dep, String formation, int annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dep = dep;
		this.formation = formation;
		this.annee = annee;
		refGauche= 0L; 
		refDroite = 0L; 
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annee;
		result = prime * result + dep;
		result = prime * result + ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + (int) (refDroite ^ (refDroite >>> 32));
		result = prime * result + (int) (refGauche ^ (refGauche >>> 32));
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stagiaire other = (Stagiaire) obj;
		if (annee != other.annee)
			return false;
		if (dep != other.dep)
			return false;
		if (formation == null) {
			if (other.formation != null)
				return false;
		} else if (!formation.equals(other.formation))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		if (refDroite != other.refDroite)
			return false;
		if (refGauche != other.refGauche)
			return false;
		return true;
	}







	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", dep=" + dep + ", formation=" + formation + ", annee="
				+ annee + ", refGauche=" + refGauche + ", refDroite=" + refDroite + "]";
	}


	public String getNom() {
		return nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public int getDep() {
		return dep;
	}


	public String getFormation() {
		return formation;
	}


	public int getAnnee() {
		return annee;
	}


	public long getRefGauche() {
		return refGauche;
	}


	public long getRefDroite() {
		return refDroite;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public void setDep(int dep) {
		this.dep = dep;
	}


	public void setFormation(String formation) {
		this.formation = formation;
	}


	public void setAnnee(int annee) {
		this.annee = annee;
	}


	public void setRefGauche(long refGauche) {
		this.refGauche = refGauche;
	}


	public void setRefDroite(long refDroite) {
		this.refDroite = refDroite;
	} 
	
	
}
