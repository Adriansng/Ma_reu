package mareu.adriansng.maru.ui_reunion_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;

public class ListReunionActivity extends AppCompatActivity {

    // FOR DESIGN
    FloatingActionButton mAddButton;
    private RecyclerView recyclerView;
    private ListReunionActivity activity;

    // FOR DATA
    private ListReunionAdapter adapter;
    private ReunionApiService reunionApiService;
    private List<Reunion>  mReunions;


    // OVERRIDE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        this.activity=this;
        reunionApiService= DI.getReunionApiService();
        configureRecyclerView();
        FloatingActionButton mAddButton= findViewById(R.id.add_reunion_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent= new Intent(context,AddReunionActivity.class);
                context.startActivity(intent);
            }
        });
        initList();
    }

    // Configuration
    private void configureRecyclerView() {
        recyclerView=findViewById(R.id.list_reunions);
        mReunions= reunionApiService.getReunions();
        adapter= new  ListReunionAdapter(mReunions);
        recyclerView.setAdapter((adapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initList() {
        List<Reunion> mReunions= reunionApiService.getReunions();
        ListReunionAdapter mAdapter= new ListReunionAdapter(mReunions);
        recyclerView.setAdapter(mAdapter);
    }

    // Actions
    @Subscribe
    public void onDeleteNeighbour(DeleteReunionEvent event) {
        reunionApiService.deleteReunion(event.reunion);
        initList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.filter_room:
                AlertDialog.Builder popupRoom = new AlertDialog.Builder(activity);
                popupRoom.setTitle("Filter Room");
                popupRoom.show();
                return true;
            case R.id.filter_date:
                AlertDialog.Builder popupDate = new AlertDialog.Builder(activity);
                popupDate.setTitle("Filter Date");
                popupDate.show();
                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
    }
