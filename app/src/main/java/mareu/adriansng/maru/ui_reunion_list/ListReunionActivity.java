package mareu.adriansng.maru.ui_reunion_list;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.MeetingRoom;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;
import mareu.adriansng.maru.ui_reunion_list.utils.SpinnerMeetingRoomAdapter;

public class ListReunionActivity extends AppCompatActivity {

    // FOR DESIGN
    FloatingActionButton mAddButton;
    private RecyclerView recyclerView;
    private ListReunionActivity activity;

    // FOR DATA


    private ReunionApiService reunionApiService;
    private List<Reunion>  mReunions;

    //FOR FILTER
    private SpinnerMeetingRoomAdapter mAdapterSpinner;
    private ListReunionAdapter adapter;
    private ArrayList<MeetingRoom> mMeetingRoom;
    private MeetingRoom selectionRoom;

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
                AlertDialog.Builder mBuilder= new AlertDialog.Builder(ListReunionActivity.this);
                View mView= getLayoutInflater().inflate(R.layout.country_spinner_row,null);
                mBuilder.setTitle("Filter meeting room");
                Spinner mSpinner= mView.findViewById(R.id.spinner_room_reunion);
                initListSpinner();
                mAdapterSpinner= new SpinnerMeetingRoomAdapter(this,mMeetingRoom);
                mSpinner.setAdapter(mAdapterSpinner);
                mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        selectionRoom= reunionApiService.getMeetingRoom().get(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(mSpinner.getSelectedItem().toString().equalsIgnoreCase("")){
                            Toast.makeText(ListReunionActivity.this,mSpinner.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();
                return (true);
            case R.id.filter_date:
               //final PopupFilterDate popupFilterDate = new PopupFilterDate(activity);
               //DialogFragment datePicker=popupFilterDate.getDatePicker();
               //datePicker.show(getSupportFragmentManager(),"Date picker");
               //popupFilterDate.build();
               //String selectDate= popupFilterDate.getDateSelection();
               return (true);
        }
        return super.onOptionsItemSelected(item);
    }
    private void initListSpinner() {
        mMeetingRoom = new ArrayList<>();
        mMeetingRoom.addAll(reunionApiService.getMeetingRoom());
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        initList();
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        adapter.notifyDataSetChanged();
        initList();
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        adapter.notifyDataSetChanged();
        initList();
    }
    }
