package ee.kirill.locationexample;

import java.util.HashMap;

public class Location extends HashMap<String, String> {
    public static final String TIME = "time";
    public static final String LONG = "long";
    public static final String LAT = "lat";

    public Location(String time, String longitude, String latitude) {
        put(TIME, time);
        put(LONG, longitude);
        put(LAT, latitude);
    }

    public double getLongitude() {
        return Double.parseDouble(get(LONG));
    }

    public double getLatitude() {
        return Double.parseDouble(get(LAT));
    }
}
