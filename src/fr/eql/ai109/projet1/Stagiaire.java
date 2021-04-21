package fr.eql.ai109.projet1;

public class Stagiaire {

	private String nom;
	private String prenom; 
	private String dep; 
	private String formation; 
	private int annee; 
	private long refGauche; 
	private long refDroite;
	private Stagiaire stagiaireDroite; 
	private Stagiaire stagiaireGauche; 
	
	
	public Stagiaire() {
		super();
		refGauche= 0L; 
		refDroite = 0L; 
		stagiaireDroite = null;
		stagiaireGauche = null; 
	}
	
	
	public Stagiaire(String nom, String prenom, String dep, String formation, int annee) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dep = dep;
		this.formation = formation;
		this.annee = annee;
		refGauche= 0L; 
		refDroite = 0L; 
		stagiaireDroite = null;
		stagiaireGauche = null; 
	}


	@Override
	public String toString() {
		return "Stagiaire [nom=" + nom + ", prenom=" + prenom + ", dep=" + dep + ", formation=" + formation + ", annee="
				+ annee + ", refGauche=" + refGauche + ", refDroite=" + refDroite + ", stagiaireDroite="
				+ stagiaireDroite + ", stagiaireGauche=" + stagiaireGauche + "]";
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getPrenom() {
		return prenom;
	}


	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}


	public String getDep() {
		return dep;
	}


	public void setDep(String dep) {
		this.dep = dep;
	}


	public String getFormation() {
		return formation;
	}


	public void setFormation(String formation) {
		this.formation = formation;
	}


	public int getAnnee() {
		return annee;
	}


	public void setAnnee(int annee) {
		this.annee = annee;
	}


	public long getRefGauche() {
		return refGauche;
	}


	public void setRefGauche(long refGauche) {
		this.refGauche = refGauche;
	}


	public long getRefDroite() {
		return refDroite;
	}


	public void setRefDroite(long refDroite) {
		this.refDroite = refDroite;
	}


	public Stagiaire getStagiaireDroite() {
		return stagiaireDroite;
	}


	public void setStagiaireDroite(Stagiaire stagiaireDroite) {
		this.stagiaireDroite = stagiaireDroite;
	}


	public Stagiaire getStagiaireGauche() {
		return stagiaireGauche;
	}


	public void setStagiaireGauche(Stagiaire stagiaireGauche) {
		this.stagiaireGauche = stagiaireGauche;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annee;
		result = prime * result + ((dep == null) ? 0 : dep.hashCode());
		result = prime * result + ((formation == null) ? 0 : formation.hashCode());
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		result = prime * result + (int) (refDroite ^ (refDroite >>> 32));
		result = prime * result + (int) (refGauche ^ (refGauche >>> 32));
		result = prime * result + ((stagiaireDroite == null) ? 0 : stagiaireDroite.hashCode());
		result = prime * result + ((stagiaireGauche == null) ? 0 : stagiaireGauche.hashCode());
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
		if (dep == null) {
			if (other.dep != null)
				return false;
		} else if (!dep.equals(other.dep))
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
		if (stagiaireDroite == null) {
			if (other.stagiaireDroite != null)
				return false;
		} else if (!stagiaireDroite.equals(other.stagiaireDroite))
			return false;
		if (stagiaireGauche == null) {
			if (other.stagiaireGauche != null)
				return false;
		} else if (!stagiaireGauche.equals(other.stagiaireGauche))
			return false;
		return true;
	}

	
	
	
}
