package mareu.adriansng.maru.ui_reunion_list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.ButterKnife;
import mareu.adriansng.maru.R;
import mareu.adriansng.maru.di.DI;
import mareu.adriansng.maru.event.DeleteReunionEvent;
import mareu.adriansng.maru.model.Reunion;
import mareu.adriansng.maru.service_api.ReunionApiService;


public class ListReunionActivity extends AppCompatActivity {

    //FOR FILTER TOOLBAR
    private static final int FILTER_ROOM= 0;
    private static final int FILTER_DATE= 1;
    public int currentFilter= FILTER_DATE;

    // FOR DESIGN
    FloatingActionButton mAddButton;
    private RecyclerView recyclerView;

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
        reunionApiService= DI.getReunionApiService();
        configureRecyclerView();
        FloatingActionButton mAddButton= findViewById(R.id.add_reunion_button);
        Toolbar mToolBar= findViewById(R.id.my_toolbar);
        mToolBar.setOnMenuItemClickListener(item -> {
           if(item.getItemId()==R.id.action_filter){
               onSortClicked();
           }
           return false;
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context=v.getContext();
                Intent intent= new Intent(context,AddReunionActivity.class);
                context.startActivity(intent);
            }
        });
 }

    private void onSortClicked() {
        String[] items={"Room","Date"};
        new MaterialAlertDialogBuilder(this)
                .setTitle("Sort order")
                .setSingleChoiceItems(items,currentFilter,(dialog, which) ->{
                   dialog.dismiss();
                   currentFilter=which;
                   sortData();
                }).show();
    }

    private void sortData() {
        if(currentFilter== FILTER_ROOM){
            adapter.sortByRoom();
        }else if(currentFilter==FILTER_DATE){
            adapter.sortByDate();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    // Configuration

    private void configureRecyclerView() {
        recyclerView=findViewById(R.id.list_reunions);
        mReunions= reunionApiService.getReunions();
        adapter= new  ListReunionAdapter(mReunions);
        recyclerView.setAdapter((adapter));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Actions
    @Subscribe
    public void onDeleteNeighbour(DeleteReunionEvent event) {
        reunionApiService.deleteReunion(event.reunion);
        configureRecyclerView();
    }

    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
      //  return true;
    //}

   // @Override
    //public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        //int id = item.getItemId();

       // if (id == R.id.action_filter) {
          //}

      //  return super.onOptionsItemSelected(item);
    //}
    }
