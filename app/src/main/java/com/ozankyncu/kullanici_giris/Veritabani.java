package com.ozankyncu.kullanici_giris;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ozankoyuncu on 11.3.2016.
 */
public class Veritabani extends SQLiteOpenHelper {
    private static final String VERITABANI_ISMI="kullanici_veritabani";
    private static final int VERITABANI_VERSIYON=1;
    private static final String TABLO_ISMI="KULLANICI";
    private static final String ID="_id";
    private static final String KULLANICI_ADI="kullanici_adi";
    private static final String SIFRE="sifre";


    public Veritabani(Context context) {
        super(context, VERITABANI_ISMI, null, VERITABANI_VERSIYON);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tablo_olustur = "CREATE TABLE " + TABLO_ISMI + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                KULLANICI_ADI + " TEXT, " +
                SIFRE + " TEXT);";
        db.execSQL(tablo_olustur);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_ISMI);
        onCreate(db);
    }

    public long KayitEkle(Kullanici kullanici) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KULLANICI_ADI,kullanici.getKullanici_adi());
        values.put(SIFRE,kullanici.getSifre());
        long id =db.insert(TABLO_ISMI,null,values);
        db.close();
        return id;
    }
    public boolean kullaniciadiara(Kullanici gkullanici,Context context)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={ID,KULLANICI_ADI,SIFRE};
        Cursor cursor=db.query(TABLO_ISMI,sutunlar,KULLANICI_ADI+"=\""+gkullanici.getKullanici_adi()+"\"",null,null,null,null);
        int adi_sira_no=cursor.getColumnIndex(KULLANICI_ADI);
        int sifre_sira_no=cursor.getColumnIndex(SIFRE);
        int id_sira_no=cursor.getColumnIndex(ID);
        Kullanici bir_kul=null;
        if(cursor!=null)
        {try{
            cursor.moveToFirst();
            bir_kul=new Kullanici();
            bir_kul.setId(cursor.getLong(id_sira_no));
            bir_kul.setKullanici_adi(cursor.getString(adi_sira_no));
            bir_kul.setSifre(cursor.getString(sifre_sira_no));}catch (Exception e)
        {
            return false;
        }
        }
        db.close();
        return true;
    }




   public Kullanici birtanekayit(Kullanici gkullanici,Context context)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={ID,KULLANICI_ADI,SIFRE};
        Cursor cursor=db.query(TABLO_ISMI,sutunlar,KULLANICI_ADI+"=\""+gkullanici.getKullanici_adi()+"\" AND "+SIFRE+"=\""+gkullanici.getSifre()+"\"",null,null,null,null);
        int adi_sira_no=cursor.getColumnIndex(KULLANICI_ADI);
        int sifre_sira_no=cursor.getColumnIndex(SIFRE);
        int id_sira_no=cursor.getColumnIndex(ID);
        Kullanici bir_kul=null;
        if(cursor!=null)
        {
            try{
            cursor.moveToFirst();
            bir_kul=new Kullanici();
            bir_kul.setId(cursor.getLong(id_sira_no));
            bir_kul.setKullanici_adi(cursor.getString(adi_sira_no));
            bir_kul.setSifre(cursor.getString(sifre_sira_no));   }catch (Exception e)
            {
                Log.e("Boyle bir kullanici Yok",e.getMessage());
                return null;
            }
        }
        db.close();
        return bir_kul;
    }
    public List<Kullanici> TumkayitlariGetir() {
       SQLiteDatabase db=this.getReadableDatabase();
        String[] sutunlar={ID,KULLANICI_ADI,SIFRE};
        Cursor cursor=db.query(TABLO_ISMI,sutunlar,null,null,null,null,null);
        int adi_sira_no=cursor.getColumnIndex(KULLANICI_ADI);
        int sifre_sira_no=cursor.getColumnIndex(SIFRE);
        int id_sira_no=cursor.getColumnIndex(ID);
        List<Kullanici> kullaniciList=new ArrayList<Kullanici>();
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
            Kullanici bir_kul=new Kullanici();
            bir_kul.setId(cursor.getLong(id_sira_no));
            bir_kul.setKullanici_adi(cursor.getString(adi_sira_no));
            bir_kul.setSifre(cursor.getString(sifre_sira_no));
            kullaniciList.add(bir_kul);
        }
        db.close();
       return kullaniciList;
    }

   /* public void KayitSil(long id) {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLO_ISMI,ID+"="+id,null);
        db.close();
    }
    public void tumunu_sil() {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLO_ISMI,null,null);
        db.close();
    }
    public void guncelle(long id,long tarih,String ders,int soru)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(DERS,ders);
        cv.put(TARIH,tarih);
        cv.put(SORU_SAYISI,soru);
       // '"+ profilename + "'"
        db.update(TABLO_ISMI,cv,ID+"="+id,null);
        db.close();
    }*/

}
