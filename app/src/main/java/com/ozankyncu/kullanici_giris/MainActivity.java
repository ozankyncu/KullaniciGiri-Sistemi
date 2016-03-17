package com.ozankyncu.kullanici_giris;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText edt_kullanici,edt_sifre;
    TextView txt_tlistele;
    Button btn_giris,btn_yeni_uye;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        edt_kullanici=(EditText)findViewById(R.id.edt_Kullaniciadi);
        edt_sifre=(EditText)findViewById(R.id.edt_Sifre);
        txt_tlistele=(TextView)findViewById(R.id.txt_tlistele);
        btn_giris=(Button)findViewById(R.id.btn_giris);
        btn_yeni_uye=(Button)findViewById(R.id.btn_yeniuye);
        btn_giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(edt_sifre.getText().toString().isEmpty()==true || edt_kullanici.getText().toString().isEmpty()==true){
                        throw new Exception();
                    }
                    Kullanici kullanici=new Kullanici();
                    kullanici.setKullanici_adi(edt_kullanici.getText().toString().trim());
                    kullanici.setSifre(edt_sifre.getText().toString().trim());

                    Veritabani db=new Veritabani(getApplicationContext());
                    Kullanici gelenk= db.birtanekayit(kullanici,getApplicationContext());
                    if(gelenk==null)
                    {
                        Toast.makeText(getApplicationContext(),"Boyle bir Kullanici Yok",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(), "Hoşgeldin " + gelenk.getKullanici_adi(), Toast.LENGTH_LONG).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Kullanici adi veya Şifre Boş geçilemez!",Toast.LENGTH_LONG).show();
                }
            }
        });

        btn_yeni_uye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                Intent ıntent=new Intent(getApplicationContext(),Yeni_Uye_Activity.class);
                startActivity(ıntent);}catch (Exception e)
                {
                    Log.e("Hata",e.getMessage());
                }
            }
        });

        txt_tlistele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(getApplicationContext(),"Tıklandi",Toast.LENGTH_SHORT).show();
                List<Kullanici> tumkullanicilar;
                Veritabani db=new Veritabani(getApplicationContext());
                tumkullanicilar=db.TumkayitlariGetir();
                for (Kullanici kullanici:tumkullanicilar
                     ) {
                    Toast.makeText(getApplicationContext(),kullanici.getKullanici_adi()+" "+kullanici.getSifre(),Toast.LENGTH_SHORT).show();

                }
            }
        });



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
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
