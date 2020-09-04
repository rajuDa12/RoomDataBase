package com.teamokcodex.roomdatabasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SemisterAddActivity extends AppCompatActivity {

    public static final String ID="ID";

    public static final String Item1="SEMISTERNAME";
    public static final String Item2="SEMISTERCREADIT";
    public static final String Item3="SEMISTERCGPA";


    String Cur[];
    String Cur2[];
    private EditText semistername;
    private Button savebutton;
    private TextView textView,Creadit,cgpa,dot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_semister_add);

        semistername=findViewById(R.id.edit1);
        cgpa=findViewById(R.id.edit3);
        savebutton=findViewById(R.id.savebutton);


        textView=findViewById(R.id.spiid);

        dot=findViewById(R.id.spiid2);

        Creadit=findViewById(R.id.edit2);


        final Spinner spinner=findViewById(R.id.spinnerid);
        final Spinner spinner2=findViewById(R.id.spiner2);




        Cur=getResources().getStringArray(R.array.NUMBER);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spi,R.id.spiid,Cur);

        spinner.setAdapter(adapter);



        Creadit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val=spinner.getSelectedItem().toString();
                Creadit.setText(val);

            }
        });





        Cur2=getResources().getStringArray(R.array.CGPA);
        ArrayAdapter<String> adapter2=new ArrayAdapter<String>(this,R.layout.sp2,R.id.spiid2,Cur2);

        spinner2.setAdapter(adapter2);



        cgpa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val2=spinner2.getSelectedItem().toString();
                cgpa.setText(val2);

            }
        });






        Intent intent=getIntent();

        if(intent.hasExtra(ID)){
            setTitle("Edit Note");
            semistername.setText(intent.getStringExtra(Item1));
            Creadit.setText(intent.getStringExtra(Item2));
            cgpa.setText(intent.getStringExtra(Item3));

        }
        else
        {
            setTitle("Note");
        }



        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SAVEDATA();
            }
        });


    }

    private void SAVEDATA()
    {

        String SEMISTERNAME=semistername.getText().toString().trim();
        String SEMISTERCREADIT=Creadit.getText().toString().trim();
        String SEMISTERCGPA=cgpa.getText().toString().trim();

        if(SEMISTERNAME.isEmpty()||SEMISTERCREADIT.isEmpty()||SEMISTERCGPA.isEmpty())
        {
            Toast.makeText(this, "ENTER A VALUE.....", Toast.LENGTH_SHORT).show();
        }


        Intent data=new Intent();
        data.putExtra(Item1,SEMISTERNAME);
        data.putExtra(Item2,SEMISTERCREADIT);
        data.putExtra(Item3,SEMISTERCGPA);

        int id=getIntent().getIntExtra(ID,-1);
        if(id==-1)
        {
            data.putExtra(ID,id);
        }
        setResult(RESULT_OK,data);
        finish();



    }
}
