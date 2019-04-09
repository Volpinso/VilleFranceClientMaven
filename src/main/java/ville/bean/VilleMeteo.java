package ville.bean;

public class VilleMeteo {
	
	private String codeCommuneInsee;
	private String nomCommune;
	private String codePostal;
	private String libelleAcheminement;
	private String ligne5;
	private String lattitude;
	private String longitude;
	private String temperature;
	private String temps;
	private String idTemps;
	private String pop;

	public VilleMeteo() {
		super();
	}
	
	public String getCodeCommuneInsee() {
		return codeCommuneInsee;
	}
	
	public void setCodeCommuneInsee(String codeCommuneInsee) {
		this.codeCommuneInsee = codeCommuneInsee;
	}
	
	public String getNomCommune() {
		return nomCommune;
	}
	
	public void setNomCommune(String nomCommune) {
		this.nomCommune = nomCommune;
	}
	
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	public String getLibelleAcheminement() {
		return libelleAcheminement;
	}
	
	public void setLibelleAcheminement(String libelleAcheminement) {
		this.libelleAcheminement = libelleAcheminement;
	}
	
	public String getLigne5() {
		return ligne5;
	}
	
	public void setLigne5(String ligne5) {
		this.ligne5 = ligne5;
	}
	
	public String getLattitude() {
		return lattitude;
	}
	
	public void setLattitude(String lattitude) {
		this.lattitude = lattitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getTemps() {
		return temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}
	
	public String toString() {
        return "Ville_France [codeCommuneInsee=" + this.codeCommuneInsee + ", nomCommune=" + this.nomCommune 
        		+ ", codePostal=" + this.codePostal
                + ", libelleAcheminement=" + this.libelleAcheminement + ", ligne5=" + this.ligne5 
                + ", lattitude=" + this.lattitude + ", longitude="
                + this.longitude + ", temperature=" + this.temperature + ", temps=" + this.temps + 
                ", idTemps=" + this.idTemps + ", population=" + this.pop + "]";
    }

	public String getIdTemps() {
		return idTemps;
	}

	public void setIdTemps(String idTemps) {
		this.idTemps = idTemps;
	}

    public String getPop() {
        return pop;
    }

    public void setPop(String pop) {
        this.pop = pop;
    }

}
