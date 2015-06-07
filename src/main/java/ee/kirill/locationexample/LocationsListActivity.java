package ee.kirill.locationexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.List;


public class LocationsListActivity extends ListActivity {

    public static final String TAG = "LocationsListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Location> locations = new FileHandler(LocationActivity.FILENAME, this).getLocations();
        String[] from = {Location.TIME, Location.LONG, Location.LAT};

        int[] to = {R.id.txtTime, R.id.txtLong, R.id.txtLat};
        setListAdapter(new SimpleAdapter(this, locations, R.layout.list_item, from, to));
    }

}
