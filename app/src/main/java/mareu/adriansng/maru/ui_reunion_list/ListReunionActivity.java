package mareu.adriansng.maru.ui_reunion_list;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.event.AddReunionEvent;
import mareu.adriansng.maru.model.Reunion;


public class ListReunionActivity extends AppCompatActivity  {

    // UI Components
    @BindView(R.id.add_reunion_button)
    FloatingActionButton mAddButton;

    // OVERRIDE

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mAddButton.setOnClickListener(v ->
                EventBus.getDefault().post(new AddReunionEvent(Reunion.addReunion())));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        int id = item.getItemId();

        if (id == R.id.action_filter) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
