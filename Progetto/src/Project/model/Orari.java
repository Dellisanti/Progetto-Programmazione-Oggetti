package Project.model;

public interface Orari {
	
	public long getSunrise();
	public void setSunrise(long sunrise);
	public long getSunset();
	public void setSunset(long sunset);
	
	public abstract void CityViewToday();
}
