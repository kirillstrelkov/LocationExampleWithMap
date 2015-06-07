package ee.kirill.locationexample;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    public static final String TAG = "FileHandler";
    private String filename;
    private Context context;

    public FileHandler(String filename, Context context) {
        this.filename = filename;
        this.context = context;
    }

    private PrintWriter getWriter(int mode) {
        try {
            return new PrintWriter(context.openFileOutput(filename, mode));
        } catch (FileNotFoundException exception) {
            Log.e(TAG, exception.toString());
        }
        return null;
    }

    public void writeHeaders() {
        PrintWriter printWriter = getWriter(Context.MODE_PRIVATE);
        printWriter.println("time,longitude,latitude");
        printWriter.close();
    }

    private void appendLine(String string) {
        PrintWriter printWriter = getWriter(Context.MODE_APPEND);
        Log.v(TAG, "Writing line: " + string);
        printWriter.println(string);
        printWriter.close();
    }

    public void appendLocationToFile(Location location) {
        String formattedLocation = String.format("%d,%f,%f", location.getTime(), location.getLongitude(), location.getLatitude());
        appendLine(formattedLocation);
    }

    public List<ee.kirill.locationexample.Location> getLocations() {
        List<ee.kirill.locationexample.Location> locations = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(context.openFileInput(filename)));
            String line;
            while ((line = reader.readLine()) != null) {
                Log.v(TAG, "Reading line:" + line);
                String[] strings = line.split(",");
                if (strings[0].matches("\\d+")) {
                    locations.add(new ee.kirill.locationexample.Location(strings[0], strings[1], strings[2]));
                }
            }

        } catch (FileNotFoundException exception) {
            Log.e(TAG, "getLocations(): " + exception.toString());
        } catch (IOException exception) {
            Log.e(TAG, exception.toString());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException exception) {
                    Log.e(TAG, exception.toString());
                }
            }
        }
        return locations;
    }
}
