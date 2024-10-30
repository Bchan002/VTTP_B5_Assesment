package task01;

public class NewBikeEntry {
    
    private int season;
	private int month;
	private int holiday;
	private int weekday;
	private int weather;
	private float temperature;
	private float humidity;
	private float windspeed;
	private int casual;
	private int registered;


    
    public NewBikeEntry(int season, int month, int holiday, int weekday, int weather, float temperature,
            float humidity, float windspeed, int casual, int registered) {
        this.season = season;
        this.month = month;
        this.holiday = holiday;
        this.weekday = weekday;
        this.weather = weather;
        this.temperature = temperature;
        this.humidity = humidity;
        this.windspeed = windspeed;
        this.casual = casual;
        this.registered = registered;
    }
    public int getSeason() {
        return season;
    }
    public int getMonth() {
        return month;
    }
    public int getHoliday() {
        return holiday;
    }
    public int getWeekday() {
        return weekday;
    }
    public int getWeather() {
        return weather;
    }
    public float getTemperature() {
        return temperature;
    }
    public float getHumidity() {
        return humidity;
    }
    public float getWindspeed() {
        return windspeed;
    }
    public int getCasual() {
        return casual;
    }
    public int getRegistered() {
        return registered;
    }
    @Override
    public String toString() {
        return "NewBikeEntry [season=" + season + ", month=" + month + ", holiday=" + holiday + ", weekday=" + weekday
                + ", weather=" + weather + ", temperature=" + temperature + ", humidity=" + humidity + ", windspeed="
                + windspeed + ", casual=" + casual + ", registered=" + registered + "]";
    }

     
}
