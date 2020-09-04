package com.teamokcodex.roomdatabasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.ManufacturerUtils;

import java.util.List;

import static com.teamokcodex.roomdatabasedemo.R.id.deleteid;

public class MainActivity extends AppCompatActivity {

    public static final int add=1;
    public static final int editadd=2;

    private static final String ID ="ID" ;
    private static final String SEMISTERNAME =" " ;
    private static final String SEMISTERCREADIT =" " ;
    private static final String SEMISTERCGPA =" " ;
    private  SeemisterViewModel seemisterViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView result=findViewById(R.id.resultid);

        FloatingActionButton buttonadd=findViewById(R.id.floatingActionButton);


        buttonadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,SemisterAddActivity.class);
                startActivityForResult(intent,add);

            }
        });

        RecyclerView recyclerView=findViewById(R.id.semistercontainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final SemisterAdapter semisterAdapter=new SemisterAdapter();
        recyclerView.setAdapter(semisterAdapter);

        seemisterViewModel= ViewModelProviders.of(this).get(SeemisterViewModel.class);

        seemisterViewModel.getAllsemisters().observe(this, new Observer<List<Semister>>()
        {
            @Override
            public void onChanged(List<Semister> semisters)
            {

                semisterAdapter.setSemisters(semisters);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT)
        {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
            {


                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction)
            {
                seemisterViewModel.delete(semisterAdapter.getSemisterAt(viewHolder.getAdapterPosition()));

                Toast.makeText(MainActivity.this, "Note is delete", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        semisterAdapter.setOnItemClickListner(new SemisterAdapter.OnItemClickListner() {
            @Override
            public void OnItemClick(Semister semister) {
                Intent intent=new Intent(MainActivity.this,SemisterAddActivity.class);
                intent.putExtra(SemisterAddActivity.ID,semister.id);
                intent.putExtra(SemisterAddActivity.Item1,semister.semister_name);
                intent.putExtra(SemisterAddActivity.Item2,semister.semister_credit);
                intent.putExtra(SemisterAddActivity.Item3,semister.semister_cgpa);
                startActivityForResult(intent,editadd);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==add &&resultCode==RESULT_OK)
        {
            String SEMISTERNAME=data.getStringExtra(SemisterAddActivity.Item1);
            String SEMISTERCREADIT=data.getStringExtra(SemisterAddActivity.Item2);
            String SEMISTERCGPA=data.getStringExtra(SemisterAddActivity.Item3);



            Semister semister=new Semister(SEMISTERNAME,SEMISTERCREADIT,SEMISTERCGPA);
            seemisterViewModel.Insert(semister);
            Toast.makeText(this, "SEEMISTER DATE IS SAVED...", Toast.LENGTH_SHORT).show();

        }
         else if(requestCode==editadd &&resultCode==RESULT_OK)
        {

            int id=data.getIntExtra(SemisterAddActivity.ID,1);

            if(id==-1)

            {
                Toast.makeText(this, "Note cant be  saved....", Toast.LENGTH_SHORT).show();
                return;

            }
            String SEMISTERNAME=data.getStringExtra(SemisterAddActivity.Item1);
            String SEMISTERCREADIT=data.getStringExtra(SemisterAddActivity.Item2);
            String SEMISTERCGPA=data.getStringExtra(SemisterAddActivity.Item3);

            Semister semister=new Semister(SEMISTERNAME,SEMISTERCREADIT,SEMISTERCGPA);
            semister.setId(id);
            seemisterViewModel.Update(semister);
            Toast.makeText(this, "UPDATA DATA is  saved....", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Note is not saved....", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId())
        {
            case deleteid:
                Semister semister=new Semister(SEMISTERNAME,SEMISTERCREADIT,SEMISTERCGPA);

                seemisterViewModel.deleteall(semister);
                Toast.makeText(this, "BAL AT", Toast.LENGTH_SHORT).show();
        }


        return super.onOptionsItemSelected(item);



    }
}
