package admin.ats.com.focaloidproject.Activities;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import admin.ats.com.focaloidproject.R;

public class Signup extends AppCompatActivity {
    private static final String TAG = "MyDB";
    EditText ename,eemail,eusername,epassword,ecpassword;
    Button save;
    String name,email,uname,pwd,cpwd;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ename=(EditText)findViewById(R.id.ename);

        eemail=(EditText)findViewById(R.id.eemail);
        eusername =(EditText)findViewById(R.id.eusername);
        epassword=(EditText)findViewById(R.id.epassword);
        ecpassword=(EditText)findViewById(R.id.ecpassword);
        save=(Button)findViewById(R.id.save);

        db=this.openOrCreateDatabase("pjt_db",MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS RegistrationData(name text,email text,username text,password text)");
        Log.d(TAG,"after create query");
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=ename.getText().toString();
                email=eemail.getText().toString();
                uname=eusername.getText().toString();
                pwd=epassword.getText().toString();
                cpwd=ecpassword.getText().toString();

                Log.d(TAG,"inside save click");
                if (pwd.equals(cpwd)) {
                    Log.d(TAG,"inside if loop");
                    db.execSQL("INSERT INTO RegistrationData values('" + name + "','" +
                            email + "','" + uname + "','"+pwd+"');");
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                    Intent i=new Intent(Signup.this,MainActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(), "Please check ur password", Toast.LENGTH_LONG).show();
            }

        });


    }
}
