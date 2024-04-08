package medfx;

public class CheckIn {
	private double height;
	private double weight;
	private String bloodPressure;	//	"120/80" The larger number is the pressure in the arteries as the heart pumps out blood during each beat. The lower number is the pressure as the heart relaxes before the next beat.
	private double temperature;
	private boolean isOver12;
	private String allergies;
	private String alcoholDrugHistory;
	private String visitReason;

	//	Constructor
	public CheckIn(double height, double weight, String bloodPressure, double temperature, boolean isOver12, String allergies, String alcoholDrugHistory, String visitReason) {
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.temperature = temperature;
		this.isOver12 = isOver12;
		this.allergies = allergies;
		this.alcoholDrugHistory = alcoholDrugHistory;
		this.visitReason = visitReason;
	}
	
	//	Getters
	public double getHeight() {
		return height;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public String getBloodPressure() {
		return bloodPressure;
	}
	
	public double getTemperature() {
		return temperature;
	}
	
	public boolean getIsOver12() {
		return isOver12;
	}
	
	public String getAllergies() {
		return allergies;
	}
	
	public String getAlcoholDrugHistory() {
		return alcoholDrugHistory;
	}
	
	public String getVisitReason() {
		return visitReason;
	}
}
