package com.ozankyncu.kullanici_giris;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Yeni_Uye_Activity extends AppCompatActivity {

    EditText edt_ykullaniciadi,edt_ysifre;
    TextView kullanici_var;
    Button btn_kaydet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yeni__uye_);

        android.support.v7.app.ActionBar actionBar;
        actionBar=getSupportActionBar();
        actionBar.setTitle("YENI UYE KAYDI");
        actionBar.setSubtitle("Lutfen Gerekli Alanları Doldurunuz.");
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.action));
        edt_ykullaniciadi=(EditText)findViewById(R.id.edt_ykullaniciadi);
        kullanici_var=(TextView)findViewById(R.id.txt_kullanicivar);
        edt_ysifre=(EditText)findViewById(R.id.edt_ysifre);
        btn_kaydet=(Button)findViewById(R.id.btn_kaydet);
       btn_kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    kullanici_var.setVisibility(View.INVISIBLE);
                    if(edt_ysifre.getText().toString().isEmpty()==true || edt_ykullaniciadi.getText().toString().isEmpty()==true){
                        throw new Exception();
                    }else{
                        Kullanici kullanici=new Kullanici();
                        kullanici.setKullanici_adi(edt_ykullaniciadi.getText().toString().trim());
                        kullanici.setSifre(edt_ysifre.getText().toString().trim());
                        Veritabani db=new Veritabani(getApplicationContext());
                        // Boyle bir kullanici var mi?
                        Boolean kullanici_var_mi=db.kullaniciadiara(kullanici,getApplicationContext());
                        if(kullanici_var_mi==false)
                        {
                            long id=db.KayitEkle(kullanici);
                            if (id == -1) {
                                Toast.makeText(getApplicationContext(), "Kayit sirasinda bir hata olustu", Toast.LENGTH_SHORT).show();
                            } else
                                Toast.makeText(getApplicationContext(), "Kayit Islemi basarili", Toast.LENGTH_SHORT).show();
                        }else{
                            kullanici_var.setVisibility(View.VISIBLE);
                            edt_ykullaniciadi.setText("");
                            edt_ysifre.setText("");

                        }

                        //-----------------------------------




                    }

                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Kullanici adi veya Şifre Boş geçilemez!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
