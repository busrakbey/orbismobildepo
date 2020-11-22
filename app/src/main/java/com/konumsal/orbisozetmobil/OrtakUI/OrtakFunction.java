package com.konumsal.orbisozetmobil.OrtakUI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.konumsal.orbisozetmobil.R;

import java.io.File;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import EntityLayer.GeoPortal.ORNEK_ALAN;
import EntityLayer.Ortak.MobilGuzergah;
import EntityLayer.Ortak.OrtakAgacTuru;
import EntityLayer.Sistem.SModulKodDeger;

import EntityLayer.Sistem.SOrgBirim;

/**
 * Created by isahin on 31.7.2016.
 */
public class OrtakFunction {

    static final String RUNTIME_GDB_FEATURE_SERVICE_URL = "http://cbsorbis.ogm.gov.tr/arcgis/rest/services/Tools/RuntimeGdbDownload/GPServer/RuntimeGdbDownload";
    static final String LOCATION_SUMMARY_FEATURE_SERVICE_URL = "http://cbsorbis.ogm.gov.tr/arcgis/rest/services/Tools/LocationSummary/GPServer/LocationSummary";

    public static Context mContext;
    public static List<MobilGuzergah> guzergahList;
    public static boolean tpkMevcut;
    public static boolean mbTilesMevcut;
    public static boolean tifMevcut;

    public static Locale trlocale = new Locale("tr", "TR");
    public static Locale enLocale = new Locale("en","US");
    public static ORNEK_ALAN secilen_ornek_alan = null;
    public static String birim_id;
    public static Long kullanici_unvan_id;
    public static String seciliIsletme;
    public static String seciliSeflik;
    public static String seciliBolme;
    public static String seciliDamgaTarih;
    public static String seciliDamgaNo;
    public static String seciliDD_id;
    public static String seciliMescereTipi;
    public static OrtakAgacTuru seciliAgacTur;
    public static String birim_id_of_damga;
    public static Long kullanici_id;
    public static String kullanici_adi;
    public static String kullanici_gercek_adi;
    public static String kullanici_soyadi;
    public static String kullanici_unvan_adi = "";
    public static String harita_versiyon_adi;
    public static HashMap<String,Integer> modul_hashmap = new HashMap<String,Integer>();
    public static String admine_ozel_mudurluk_id = "4532";
    public static String admine_ozel_birim_id = "4533";//"4029 -kastamonu"; //4533 BUYUKDUZ 2836 DEVECİKONAK 2227 KIZILCAMAM 2228 yıldırım 4535 karabuk  2176 ankara oiş 2239 eskipazar 2692 SAMANDERE(DUZCE-ISTIF ICIN)
    public static String gecici_personel_id = "114025"; //ozm kontrol için kullanılıyor(admin per sicilde olmadığı için)
    public static HashMap<Integer,String> aylar = new HashMap<Integer,String>();
    public static HashMap<String,String> aciklama_nedenleri = new HashMap<String,String>();
    public static HashMap<Integer,String> ozm_supheli_tur = new HashMap<Integer,String>();
    public static List<String> ozm_s_modul_kod_deger_keys;
    public static HashMap<String,String> ozm_st_suc_tur_hashmap = new HashMap<String,String>();
    public static HashMap<String,String> ozm_st_suc_yeri_nitelik_hashmap = new HashMap<String,String>();
    public static String BLUETOOTH_STATE = "";

    public static HashMap<String,String> ozm_st_suc_aleti_hashmap = new HashMap<String,String>();
    public static HashMap<String,String> ozm_st_canli_vasita_hashmap = new HashMap<String,String>();
    public static HashMap<String,String> ozm_st_cansiz_vasita_hashmap = new HashMap<String,String>();

    public static HashMap<String,String> ozm_st_ihbar_intikal_sekli_hashmap = new HashMap<String,String>();
    public static HashMap<Integer,String> ozm_st_tutanak_durum_hashmap = new HashMap<Integer,String>();

    public static HashMap<String,String> ozm_st_emval_cins_hashmap = new HashMap<String,String>();
    public static HashMap<String,String> ozm_st_emval_kalite_sinifi_hashmap = new HashMap<String,String>();
    public static HashMap<String,String> ozm_st_emval_boy_tipi_hashmap = new HashMap<String,String>();
    public static List<String> vip_user_list = new ArrayList<String>();
    public static List<OrtakAgacTuru> ortak_agac_turu_list = new ArrayList<OrtakAgacTuru>();
    public static HashMap<Long,String> ortak_agac_hashmap = new HashMap<Long,String>();
    public static List<Integer> tomruk_tipi_index_list;
    public static List<Integer> yakacak_odun_tipi_index_list;
    public static List<Integer> kalas_tipi_index_list;
    public static List<Long> s_org_birim_path;
    public static List<String> ozm_kontrol_tur_string;
    public static HashMap<Integer,String> ozm_kontrol_tur_hashmap = new HashMap<Integer,String>();



    public static HashMap<String,String> yo_horizon_adi_hashmap = new HashMap<String,String>();
    public static HashMap<String,String> otsu_odunsu_hashmap = new HashMap<String,String>();
    public static HashMap<Long,String> not_konu_hashmap = new HashMap<Long,String>();
    public static HashMap<Long,String> is_kalemi_hashmap = new HashMap<Long,String>();
    public static HashMap<Long,String> bolme_hashmap = new HashMap<Long,String>();
    public static List<SOrgBirim> bolge_list = new ArrayList<SOrgBirim>();
    public static  List<String> bolge_list_string = new ArrayList<String>();
    public static List<SOrgBirim> mudurluk_list = new ArrayList<SOrgBirim>();
    public static  List<String> mudurluk_list_string = new ArrayList<String>();
    public static List<SOrgBirim> seflik_list = new ArrayList<SOrgBirim>();
    public static  List<String> seflik_list_string = new ArrayList<String>();

    public static List<Integer> ozetEkranModulList;
    public static List<String> ozetEkranModulAdlari;
    public static HashMap<Integer,List<Integer>> ozetEkranConstantHashMap = new HashMap<Integer,List<Integer>>();
    public static  HashMap<String , Integer> bolge_mudurlukleri_hashmap = new HashMap<String, Integer>();


    public static List<String> kesim_nevi_list = new ArrayList<String>();




    public static  boolean lokasyon_izin_ver;
    public final static String	MUH_OZM_TEMIN_SILAH_KART_GRUP_KODU	= "ozm_silah";
    public final static String	MUH_OZM_TEMIN_DAMGA_KART_GRUP_KODU	= "ozm_damga";

    public static String BLUETOOTH_DEVICE_TYPE = "0";

    public static HashMap<String,SModulKodDeger> fidYasList;
    public static HashMap<String,String> fidYasKod;
    public static List<String> fid_yas_list_type_string;
    public static HashMap<String,SModulKodDeger> fidHacimList;
    public static HashMap<String,String> fidHacimKod;
    public static List<String> fid_hacim_list_type_string;
    public static HashMap<String,SModulKodDeger>  fidAmbalajList;
    public static HashMap<String,String> fidAmbalajKod;
    public static List<String> fid_ambalaj_list_type_string;
    public static HashMap<String,SModulKodDeger>  fidBoyList;
    public static HashMap<String,String> fidBoyKod;
    public static List<String> fid_boy_list_type_string;
    public static HashMap<String,SModulKodDeger>  fidCevreList;
    public static HashMap<String,String> fidCevreKod;
    public static List<String> fid_cevre_list_type_string;


    /*public static HashMap<String,Integer> getBolgeMudurulukleriHashMap() {
        if(bolge_mudurlukleri_hashmap == null || bolge_mudurlukleri_hashmap.size() == 0)
        {
            bolge_mudurlukleri_hashmap = new HashMap<String , Integer>();
            bolge_mudurlukleri_hashmap.put(ADANA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(AMASYA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ANKARA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ANTALYA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ARTVİN ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(BALIKESİR ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(BOLU ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(BURSA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ÇANAKKALE ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(DENİZLİ ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ELAZIĞ ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ERZURUM ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ESKİŞEHİR ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(GİRESUN ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(ISPARTA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(İSTANBUL ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(İZMİR ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(KAHRAMANMARAŞ ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(KASTAMONU ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(KAYSERİ ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(KONYA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(KÜTAHYA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(MERSİN ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put(MUĞLA ORMAN BÖLGE MÜDÜRLÜĞÜ);
            bolge_mudurlukleri_hashmap.put();
        }
    }
    */





    public final static Integer	GENEL_OZET_MUHASEBE_NAKIT_DURUMU			= 1;
    public final static Integer	GENEL_OZET_MUHASEBE_KASA					= 2;
    public final static Integer	GENEL_OZET_MUHASEBE_BANKA					= 3;
    public final static Integer	GENEL_OZET_MUHASEBE_TOPLAM_IHALE			= 4;
    public final static Integer	GENEL_OZET_MUHASEBE_GERCEKLESEN_IHALE		= 5;
    public final static Integer	GENEL_OZET_MUHASEBE_BUTCE					= 6;
    public final static Integer	GENEL_OZET_MUHASEBE_GELIR_GERCEKLESME		= 7;
    public final static Integer	GENEL_OZET_MUHASEBE_GIDER_GERCEKLESME		= 8;

    public final static Integer	GENEL_OZET_PAZARLAMA_DIKILI_DAMGA			= 101;
    public final static Integer	GENEL_OZET_PAZARLAMA_MAKTA_STOGU			= 102;
    public final static Integer	GENEL_OZET_PAZARLAMA_RAMPA_STOGU			= 103;
    public final static Integer	GENEL_OZET_PAZARLAMA_SON_DEPO				= 104;
    public final static Integer	GENEL_OZET_PAZARLAMA_URETIM_END				= 105;
    public final static Integer	GENEL_OZET_PAZARLAMA_URETIM_YAK				= 106;
    public final static Integer	GENEL_OZET_PAZARLAMA_SATIS_DIKILI			= 107;
    public final static Integer	GENEL_OZET_PAZARLAMA_SATIS_END				= 108;
    public final static Integer	GENEL_OZET_PAZARLAMA_SATIS_YAK				= 109;
    public final static Integer	GENEL_OZET_PAZARLAMA_SATIS_DIKILI_TUTAR		= 110;
    public final static Integer	GENEL_OZET_PAZARLAMA_SATIS_END_TUTAR		= 111;
    public final static Integer	GENEL_OZET_PAZARLAMA_SATIS_YAK_TUTAR		= 112;
    public final static Integer	GENEL_OZET_PAZARLAMA_TEVZIAT				= 113;
    public final static Integer	GENEL_OZET_PAZARLAMA_ISTIHKAK				= 114;

    public final static Integer	GENEL_OZET_PERSONEL_MEMUR_SAYISI			= 201;
    public final static Integer	GENEL_OZET_PERSONEL_ISCI_SAYISI				= 202;
    public final static Integer	GENEL_OZET_PERSONEL_TOPLAM_CALISAN_SAYISI	= 203;

    public final static Integer	GENEL_OZET_OZM_MUCADELE_PROJE_SAYISI		= 301;
    public final static Integer	GENEL_OZET_OZM_SUC_TUTANAGI_SAYISI			= 302;

    public final static Integer	GENEL_OZET_ODUH_MESIRE_YERI_SAYISI			= 401;
    public final static Integer	GENEL_OZET_ODUH_BAL_ORMANI_SAYISI			= 402;
    public final static Integer	GENEL_OZET_ODUH_TOPLAM_GELIR				= 403;
    public final static Integer	GENEL_OZET_ODUH_TOPLAM_YAYILIS_ALANI		= 404;
    public final static Integer	GENEL_OZET_ODUH_TOPLAM_MIKTAR				= 405;

    public final static Integer	GENEL_OZET_IZIN_SAHASI_SAYISI				= 501;
    public final static Integer	GENEL_OZET_IZIN_SAHASI_ALANI				= 502;
    public final static Integer	GENEL_OZET_IZIN_TAHAKKUKU					= 503;
    public final static Integer	GENEL_OZET_IZIN_GELIRI						= 504;

    public final static Integer	GENEL_OZET_ORKOY_KOY_SAYISI					= 601;
    public final static Integer	GENEL_OZET_ORKOY_KOYLU_SAYISI				= 602;
    public final static Integer	GENEL_OZET_ORKOY_PROJE_SAYISI				= 603;
    public final static Integer	GENEL_OZET_ORKOY_KREDI_TUTARI				= 604;
    public final static Integer	GENEL_OZET_ORKOY_HIBE_TUTARI				= 605;

    public final static Integer	GENEL_OZET_YANGIN_DEVAM_EDEN_YANGIN_SAYISI	= 701;
    public final static Integer	GENEL_OZET_YANGIN_EKIP_SAYISI				= 702;
    public final static Integer	GENEL_OZET_YANGIN_KULE_SAYISI				= 703;
    public final static Integer	GENEL_OZET_YANGIN_TUM_YANGIN_SAYISI			= 704;
    public final static Integer	GENEL_OZET_YANGIN_BU_YIL_YANGIN_SAYISI		= 705;
    public final static Integer	GENEL_OZET_YANGIN_BU_YIL_YANGIN_ALANI		= 706;
    public final static Integer	GENEL_OZET_YANGIN_ONCEKI_YIL_YANGIN_SAYISI	= 707;
    public final static Integer	GENEL_OZET_YANGIN_ONCEKI_YIL_YANGIN_ALANI	= 708;

    public final static Integer	GENEL_OZET_HUKUK_DAVA_SAYISI				= 801;
    public final static Integer	GENEL_OZET_HUKUK_DAVA_TUTARI				= 802;
    public final static Integer	GENEL_OZET_HUKUK_ICRA_SAYISI				= 803;
    public final static Integer	GENEL_OZET_HUKUK_ICRA_TUTARI				= 804;
    public final static Integer	GENEL_OZET_HUKUK_OZEL_ASILALACAK			= 805;
    public final static Integer	GENEL_OZET_HUKUK_OZEL_YARGILAMA				= 806;
    public final static Integer	GENEL_OZET_HUKUK_DONER_ASILALACAK			= 807;
    public final static Integer	GENEL_OZET_HUKUK_DONER_YARGILAMA			= 808;

    public final static Integer	GENEL_OZET_TASINMAZ_YAPI_SAYISI				= 901;
    public final static Integer	GENEL_OZET_TASINMAZ_PARSEL_SAYISI			= 902;
    public final static Integer	GENEL_OZET_TASINMAZ_TESIS_SAYISI			= 903;
    public final static Integer	GENEL_OZET_TASINMAZ_ARAC_SAYISI				= 904;

    public final static Integer	GENEL_OZET_PLANLAMA_NORMAL_KAPALI_ALAN		= 1001;
    public final static Integer	GENEL_OZET_PLANLAMA_BOSLUKLU_KAPALI_ALAN	= 1002;
    public final static Integer	GENEL_OZET_PLANLAMA_ORMANLIK_ALAN			= 1003;

    public static Boolean	JUST_SENKRON_EDILMEYENLER = false;

    public static HashMap<Integer,List<Integer>> getOzetEkranHashMap()
    {
        if(ozetEkranConstantHashMap == null || ozetEkranConstantHashMap.size() == 0) {
            ozetEkranConstantHashMap = new HashMap<Integer, List<Integer>>();

            if(ozetEkranModulList == null)
                getOzetEkranModulList();

            for(int i = 0;i<ozetEkranModulList.size();i++) {
                List<Integer> temp_islem_list = new ArrayList<Integer>();
                if(ozetEkranModulList.get(i) == 0) {
                    temp_islem_list.add(GENEL_OZET_MUHASEBE_NAKIT_DURUMU);
                    temp_islem_list.add(GENEL_OZET_MUHASEBE_KASA);
                    temp_islem_list.add(GENEL_OZET_MUHASEBE_BANKA);
                    temp_islem_list.add(GENEL_OZET_MUHASEBE_TOPLAM_IHALE);
                    temp_islem_list.add(GENEL_OZET_MUHASEBE_GERCEKLESEN_IHALE);
                }
                else if(ozetEkranModulList.get(i) == 1) {
                    temp_islem_list.add(GENEL_OZET_PAZARLAMA_DIKILI_DAMGA);
                    temp_islem_list.add(GENEL_OZET_PAZARLAMA_MAKTA_STOGU);
                    temp_islem_list.add(GENEL_OZET_PAZARLAMA_RAMPA_STOGU);
                    temp_islem_list.add(GENEL_OZET_PAZARLAMA_SON_DEPO);
                }
                else if(ozetEkranModulList.get(i) == 2) {
                    temp_islem_list.add(GENEL_OZET_PERSONEL_MEMUR_SAYISI);
                    temp_islem_list.add(GENEL_OZET_PERSONEL_ISCI_SAYISI);
                    temp_islem_list.add(GENEL_OZET_PERSONEL_TOPLAM_CALISAN_SAYISI);
                }
                else if(ozetEkranModulList.get(i) == 3) {
                    temp_islem_list.add(GENEL_OZET_OZM_MUCADELE_PROJE_SAYISI);
                    temp_islem_list.add(GENEL_OZET_OZM_SUC_TUTANAGI_SAYISI);
                }
                else if(ozetEkranModulList.get(i) == 4) {
                    temp_islem_list.add(GENEL_OZET_ODUH_MESIRE_YERI_SAYISI);
                    temp_islem_list.add(GENEL_OZET_ODUH_BAL_ORMANI_SAYISI);
                    temp_islem_list.add(GENEL_OZET_ODUH_TOPLAM_GELIR);

                }
                else if(ozetEkranModulList.get(i) == 5) {
                    temp_islem_list.add(GENEL_OZET_IZIN_SAHASI_SAYISI);
                    temp_islem_list.add(GENEL_OZET_IZIN_SAHASI_ALANI);
                    temp_islem_list.add(GENEL_OZET_IZIN_TAHAKKUKU);
                    temp_islem_list.add(GENEL_OZET_IZIN_GELIRI);
                }
                else if(ozetEkranModulList.get(i) == 6) {
                    temp_islem_list.add(GENEL_OZET_ORKOY_KOY_SAYISI);
                    temp_islem_list.add(GENEL_OZET_ORKOY_KOYLU_SAYISI);
                    temp_islem_list.add(GENEL_OZET_ORKOY_PROJE_SAYISI);
                    temp_islem_list.add(GENEL_OZET_ORKOY_KREDI_TUTARI);
                    temp_islem_list.add(GENEL_OZET_ORKOY_HIBE_TUTARI);
                }
                else if(ozetEkranModulList.get(i) == 7) {
                    temp_islem_list.add(GENEL_OZET_YANGIN_DEVAM_EDEN_YANGIN_SAYISI);
                    temp_islem_list.add(GENEL_OZET_YANGIN_EKIP_SAYISI);
                    temp_islem_list.add(GENEL_OZET_YANGIN_KULE_SAYISI);
                    temp_islem_list.add(GENEL_OZET_YANGIN_TUM_YANGIN_SAYISI);
                }
                else if(ozetEkranModulList.get(i) == 8) {
                    temp_islem_list.add(GENEL_OZET_HUKUK_DAVA_SAYISI);
                    temp_islem_list.add(GENEL_OZET_HUKUK_DAVA_TUTARI);
                    temp_islem_list.add(GENEL_OZET_HUKUK_ICRA_SAYISI);
                    temp_islem_list.add(GENEL_OZET_HUKUK_ICRA_TUTARI);

                }
                else if(ozetEkranModulList.get(i) == 9) {
                    temp_islem_list.add(GENEL_OZET_TASINMAZ_YAPI_SAYISI);
                    temp_islem_list.add(GENEL_OZET_TASINMAZ_PARSEL_SAYISI);
                    temp_islem_list.add(GENEL_OZET_TASINMAZ_TESIS_SAYISI);
                    temp_islem_list.add(GENEL_OZET_TASINMAZ_ARAC_SAYISI);
                }
                ozetEkranConstantHashMap.put(ozetEkranModulList.get(i),temp_islem_list);
            }
        }
        return ozetEkranConstantHashMap;
    }





    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_MUHASEBE	= 0;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_URETIM		= 1;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_PERSONEL	= 2;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_OZM			= 3;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_ODUH		= 4;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_IZIN		= 5;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_ORKOY		= 6;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_YANGIN		= 7;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_HUKUK		= 8;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_TASINMAZ	= 9;
    public final static int		RAPORLAMA_GENEL_OZET_ISLEM_TURU_PLANLAMA	= 10;

    public static List<Integer> getOzetEkranModulList()
    {
        if(ozetEkranModulList == null || ozetEkranModulList.size() == 0)
        {
            ozetEkranModulList  = new ArrayList<Integer>();
            ozetEkranModulAdlari = new ArrayList<String>();

            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_MUHASEBE);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_URETIM);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_PERSONEL);
           // ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_OZM);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_ODUH);
           // ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_IZIN);
            //ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_ORKOY);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_YANGIN);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_HUKUK);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_TASINMAZ);
            ozetEkranModulList.add(RAPORLAMA_GENEL_OZET_ISLEM_TURU_PLANLAMA);

            ozetEkranModulAdlari.add("Muhasebe");
            ozetEkranModulAdlari.add("Üretim");
            ozetEkranModulAdlari.add("Personel");
            ozetEkranModulAdlari.add("Odun Dışı");
            ozetEkranModulAdlari.add("Yangın");
            ozetEkranModulAdlari.add("Hukuk");
            ozetEkranModulAdlari.add("Taşınmaz");
            ozetEkranModulAdlari.add("Planlama");
        }
        return ozetEkranModulList;
    }


    public static boolean hasMBTiles()
    {
        File demoDataFile;
        String offlineDataSDCardDirName;
        String mbtiles;
        File directory_mbtiles;
        offlineDataSDCardDirName = "/orbisdata/basemapdata/raster/";
        mbtiles = "orbis.mbtiles";
        demoDataFile = Environment.getExternalStorageDirectory();
        directory_mbtiles = new File(demoDataFile.getAbsolutePath() + offlineDataSDCardDirName + mbtiles);

        if(!directory_mbtiles.exists())
        {
            mbTilesMevcut = false;
        }
        else
        {
            mbTilesMevcut = true;
        }
        return mbTilesMevcut;
    }



    public static boolean hasTifFiles()
    {
        File demoDataFile;
        String offlineDataSDCardDirName;
        String tif;
        File directory_mbtiles;

        demoDataFile = Environment.getExternalStorageDirectory();
        offlineDataSDCardDirName = "/orbisdata/basemapdata/raster/";

        File raster_directory = new File(demoDataFile.getAbsolutePath() + "/orbisdata/basemapdata/raster");

        if(!raster_directory.exists())
        {
            tifMevcut = false;
            return  tifMevcut;
        }

        File[] raster_files = raster_directory.listFiles();

        if(raster_files!=null&& raster_files.length == 0)
        {
            tifMevcut = false;
            return  tifMevcut;
        }
        else if(raster_files!=null)
        {
            for (int i = 0; i < raster_files.length; i++)//raster ekleniyor
            {
                Log.v("raster file","=>"+raster_files[i].getName());
                String extension = "";
                int j = raster_files[i].getName().lastIndexOf('.');
                if (j > 0) {
                    extension = raster_files[i].getName().substring(j + 1);
                }
                if (!extension.equals("tif"))
                    continue;
                else
                {
                    tifMevcut = true;
                    return  tifMevcut;
                }
            }
        }
        return false;

    }



    public static boolean hasTpk(String plan_id)
    {
        File demoDataFile;
        String offlineDataSDCardDirName;
        String filename;
        File directory;
        offlineDataSDCardDirName = "/orbisdata/basemapdata/";
        filename = plan_id+".tpk";
        demoDataFile = Environment.getExternalStorageDirectory();
        directory = new File(demoDataFile.getAbsolutePath() + offlineDataSDCardDirName + filename);

        if(!directory.exists())
        {
            tpkMevcut = false;
        }
        else
        {
            tpkMevcut = true;
        }
        return tpkMevcut;
    }







    public static List<String> getKesimNeviList()
    {
        kesim_nevi_list = new ArrayList<String>();

        kesim_nevi_list.add("");
        kesim_nevi_list.add("OlaganUstu");
        kesim_nevi_list.add("Tensil");
        kesim_nevi_list.add("Bakim");
        kesim_nevi_list.add("GencMescere");
        kesim_nevi_list.add("Secme");
        kesim_nevi_list.add("DevamliOrman");
        kesim_nevi_list.add("IlkAralama");
        kesim_nevi_list.add("SiklikBakimi");
        kesim_nevi_list.add("KoruyaTahvil");
        kesim_nevi_list.add("MescereAlti");
        kesim_nevi_list.add("Rehabilitasyon");
        kesim_nevi_list.add("EndustriyelPlantasyon");
        kesim_nevi_list.add("Baltalik");
        return  kesim_nevi_list;

    }


    public static List<String> getIlacFeromonTanim()
    {
        List<String> list = new ArrayList<String>();

        list.add("");
        list.add("İlaç Tanım");
        list.add("Feromon/Tuzak Tanım");
        return  list;
    }


    public static List<String> getDurumListOzm_zd_sorgulama()
    {

        List<String> duyurma_durum_list = new ArrayList<String>();

        duyurma_durum_list.add("");
        duyurma_durum_list.add("Yeni Kayıt");
        duyurma_durum_list.add("Onaya Gönderildi");
        duyurma_durum_list.add("Onaylandı");
        duyurma_durum_list.add("Revizyona Gönderildi");
        duyurma_durum_list.add("Duyuruldu");
        duyurma_durum_list.add("İptal");

        return  duyurma_durum_list;
    }



    public static List<String> getTurListOzm_yz_sorgulama()
    {
        List<String> tur_list = new ArrayList<String>();
        tur_list.add("");
        tur_list.add("Yararlı");
        tur_list.add("Zararlı");
        tur_list.add("Diğer");

        return tur_list;
    }

    public static HashMap<Integer,String> getOzm_kontrol_tur_hashmap()
    {
        ozm_kontrol_tur_hashmap = new HashMap<Integer, String>();
        ozm_kontrol_tur_hashmap.put(1,"İşletme Şefliği Kontrolü");
        ozm_kontrol_tur_hashmap.put(2,"İşletme Müdürlüğü Kontrolü");
        ozm_kontrol_tur_hashmap.put(3,"Bölge Müdürlüğü Kontrolü");
        return ozm_kontrol_tur_hashmap;
    }
    public static List<String> getOzm_kontrol_tur_string()
    {
        List<String> ozm_kontrol_tur_string_list = new ArrayList<String>();
        ozm_kontrol_tur_string_list.add("İşletme Şefliği Kontrolü");
        ozm_kontrol_tur_string_list.add("İşletme Müdürlüğü Kontrolü");
        ozm_kontrol_tur_string_list.add("Bölge Müdürlüğü Kontrolü");

        return ozm_kontrol_tur_string_list;
    }



    public static String getOrtakAgacTuruFromHasmap(Long ortak_agac_id_)
    {
        return ortak_agac_hashmap.get(ortak_agac_id_);
    }

    public static void setOrtakAgacTuru(List<OrtakAgacTuru> ortak_agac_turu_)
    {
        ortak_agac_turu_list = ortak_agac_turu_;
        for (OrtakAgacTuru item:ortak_agac_turu_list)
            ortak_agac_hashmap.put(item.getId() , item.getAgacAdi());
    }

    public static List<String> getOzm_emval_boy_tipi_list()
    {
        List<String> emval_boy_tipi_list = new ArrayList<String>();
        emval_boy_tipi_list.add("Kısa Boy");
        emval_boy_tipi_list.add("Normal Boy");
        emval_boy_tipi_list.add("Uzun Boy");
        emval_boy_tipi_list.add("");

        return emval_boy_tipi_list;
    }

    public static List<String> getOzm_emval_sinifi_list()
    {
        List<String> emval_sinifi_list = new ArrayList<String>();
        emval_sinifi_list.add("1.Sınıf");
        emval_sinifi_list.add("2.Sınıf");
        emval_sinifi_list.add("3.Sınıf");
        emval_sinifi_list.add("");

        return emval_sinifi_list;
    }


    public static List<String> getOzm_emval_olcu_birim_string()
    {
        List<String> olcu_birim_string = new ArrayList<String>();
        olcu_birim_string.add("M3");
        olcu_birim_string.add("Ster");

        return olcu_birim_string;
    }



    public static String getOzm_emval_cins_from_hashmap(Integer kod_)
    {
        return ozm_st_emval_cins_hashmap.get(String.valueOf(kod_));
    }

    public static List<String> getOzm_emval_cins_string()
    {
        List<String> emval_cins_string = new ArrayList<String>();
        emval_cins_string.add("Tomruk");
        emval_cins_string.add("Tel Direği");
        emval_cins_string.add("Maden Direği");
        emval_cins_string.add("Yuvarlak Sanayi Odunu");
        emval_cins_string.add("Kagitlik Odun");
        emval_cins_string.add("Ince Sanayi Odunu");
        emval_cins_string.add("Yarma Sanayi Odunu");
        emval_cins_string.add("Kabuklu Kagitlik Odun");
        emval_cins_string.add("Lif Yonga Odunu");
        emval_cins_string.add("Sirik Cubuk Odunu");
        emval_cins_string.add("Yakacak Odun");
        emval_cins_string.add("Kalas");
        emval_cins_string.add("Tahta");
        emval_cins_string.add("Diger");


        ozm_st_emval_cins_hashmap = new HashMap<String, String>();
        ozm_st_emval_cins_hashmap.put("0","Tomruk");
        ozm_st_emval_cins_hashmap.put("1","Tel Direği");
        ozm_st_emval_cins_hashmap.put("2","Maden Direği");
        ozm_st_emval_cins_hashmap.put("3","Yuvarlak Sanayi Odunu");
        ozm_st_emval_cins_hashmap.put("4","Kagitlik Odun");
        ozm_st_emval_cins_hashmap.put("5","Ince Sanayi Odunu");
        ozm_st_emval_cins_hashmap.put("6","Yarma Sanayi Odunu");
        ozm_st_emval_cins_hashmap.put("7","Kabuklu Kagitlik Odun");
        ozm_st_emval_cins_hashmap.put("8","Lif Yonga Odunu");
        ozm_st_emval_cins_hashmap.put("9","Sirik Cubuk Odunu");
        ozm_st_emval_cins_hashmap.put("10","Yakacak Odun");
        ozm_st_emval_cins_hashmap.put("11","Kalas");
        ozm_st_emval_cins_hashmap.put("12","Tahta");
        ozm_st_emval_cins_hashmap.put("13","Diger");

        tomruk_tipi_index_list = new ArrayList<Integer>();
        tomruk_tipi_index_list.add(0);
        tomruk_tipi_index_list.add(1);
        tomruk_tipi_index_list.add(2);
        tomruk_tipi_index_list.add(3);
        tomruk_tipi_index_list.add(4);

        yakacak_odun_tipi_index_list = new ArrayList<Integer>();
        yakacak_odun_tipi_index_list.add(5);
        yakacak_odun_tipi_index_list.add(6);
        yakacak_odun_tipi_index_list.add(7);
        yakacak_odun_tipi_index_list.add(8);
        yakacak_odun_tipi_index_list.add(9);
        yakacak_odun_tipi_index_list.add(10);

        kalas_tipi_index_list = new ArrayList<Integer>();
        kalas_tipi_index_list.add(11);
        kalas_tipi_index_list.add(12);

        return emval_cins_string;
    }



    public static String getOzm_emval_kalite_from_hashmap(Integer kod_)
    {
        return ozm_st_emval_kalite_sinifi_hashmap.get(String.valueOf(kod_));
    }

    public static List<String> getOzm_emval_kalite_string()
    {
        List<String> emval_kalite_string = new ArrayList<String>();
        emval_kalite_string.add("BirinciSinif");
        emval_kalite_string.add("IkinciSinif");
        emval_kalite_string.add("UcuncuSinif");

        ozm_st_emval_kalite_sinifi_hashmap = new HashMap<String, String>();
        ozm_st_emval_kalite_sinifi_hashmap.put("0","BirinciSinif");
        ozm_st_emval_kalite_sinifi_hashmap.put("1","IkinciSinif");
        ozm_st_emval_kalite_sinifi_hashmap.put("2","UcuncuSinif");

        return emval_kalite_string;
    }



    public static String getOzm_emval_boy_tipi_from_hashmap(Integer kod_)
    {
        return ozm_st_emval_boy_tipi_hashmap.get(kod_);
    }

    public static List<String> getOzm_emval_boy_tipi_string()
    {
        List<String> emval_boy_tipi_string = new ArrayList<String>();
        emval_boy_tipi_string.add("KisaBoy");
        emval_boy_tipi_string.add("NormalBoy");
        emval_boy_tipi_string.add("UzunBoy");

        ozm_st_emval_boy_tipi_hashmap = new HashMap<String, String>();
        ozm_st_emval_boy_tipi_hashmap.put("0","KisaBoy");
        ozm_st_emval_boy_tipi_hashmap.put("1","NormalBoy");
        ozm_st_emval_boy_tipi_hashmap.put("2","UzunBoy");

        return emval_boy_tipi_string;
    }


    public static HashMap<Integer,String> getOzm_tutanak_durum_hashmap()
    {
        ozm_st_tutanak_durum_hashmap = new HashMap<Integer, String>();
        ozm_st_tutanak_durum_hashmap.put(0,"Yeni Kayit");
        ozm_st_tutanak_durum_hashmap.put(1,"Savciliga Gonderildi");
        ozm_st_tutanak_durum_hashmap.put(2,"Savcilik Dava Acti");
        ozm_st_tutanak_durum_hashmap.put(3,"Savcilik Reddetti");

        return ozm_st_tutanak_durum_hashmap;
    }

    public static List<String> getOzm_tutanak_durum_string()
    {
        List<String> tutanak_durum_string = new ArrayList<String>();
        tutanak_durum_string.add("Yeni Kayit");
        tutanak_durum_string.add("Savciliga Gonderildi");
        tutanak_durum_string.add("Savcilik Dava Acti");
        tutanak_durum_string.add("Savcilik Reddetti");

        return tutanak_durum_string;
    }


    public static int getModulId(String modul_adi)
    {
        return modul_hashmap.get(modul_adi);
    }

    public static void setModul_deger_keys()
    {
        modul_hashmap = new HashMap<String,Integer>();
        modul_hashmap.put("Ortak",75);
        modul_hashmap.put("Muhasebe",100);
        modul_hashmap.put("Personel",200);
        modul_hashmap.put("Tasinmaz",500);
        modul_hashmap.put("GeoPortal",750);
        modul_hashmap.put("Egitim",800);
        modul_hashmap.put("Strateji",1000);
        modul_hashmap.put("KaliteYonetimi",1100);
        modul_hashmap.put("Hukuk",1200);
        modul_hashmap.put("OrmanYanginlari",1300);
        modul_hashmap.put("IsletmePazarlama",1500);
        modul_hashmap.put("YetismeOrtami",1800);
        modul_hashmap.put("SosyoEkonomik",2000);
        modul_hashmap.put("UrunDisi",2800);
        modul_hashmap.put("OdunDisi",2900);
        modul_hashmap.put("BiyolojikCesitlilik",3000);
        modul_hashmap.put("SaglikDurumu",4000);
        modul_hashmap.put("Eizin",5000);
        modul_hashmap.put("Fidanlik",6000);
        modul_hashmap.put("Agaclandirma",7000);
        modul_hashmap.put("HizmetEnvanteri",25000);

    }


    public static List<String> getOzm_s_modul_kod_deger_keys()
    {
        ozm_s_modul_kod_deger_keys = new ArrayList<String>();
        ozm_s_modul_kod_deger_keys.add("OzmTutanakSucTur");//OZM
        ozm_s_modul_kod_deger_keys.add("OzmSucTutanagiNitelik");//
        ozm_s_modul_kod_deger_keys.add("OzmSucAletiVasitaCins");//boyle bişey yok
        ozm_s_modul_kod_deger_keys.add("OzmIhbarIntikalSekli");
        ozm_s_modul_kod_deger_keys.add("OzmSucAletiCinsi");//
        ozm_s_modul_kod_deger_keys.add("OzmCanliVasitaCinsi");//
        ozm_s_modul_kod_deger_keys.add("OzmCansizVasitaCinsi");//
        ozm_s_modul_kod_deger_keys.add("OzmZararliTurKategori");//
        ozm_s_modul_kod_deger_keys.add("OzmKartBeslenmeSekli");
        ozm_s_modul_kod_deger_keys.add("DemirbasKullanimDurumuOzm");//ozm silah
        ozm_s_modul_kod_deger_keys.add("DemirbasAmbarAltKategoriOzm");
        ozm_s_modul_kod_deger_keys.add("DemirbasIhracAltKategoriOzm");
        ozm_s_modul_kod_deger_keys.add("DemirbasAmbarAltKategori");//ozm damga
        ozm_s_modul_kod_deger_keys.add("DemirbasIhracAltKategori");



        ozm_s_modul_kod_deger_keys.add("SilProjeNeviTuru");//silvikultur kod değerleri - toprakProfili
        ozm_s_modul_kod_deger_keys.add("SahaBakisi");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilToprakTipi");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilFizyolejikDerinlikTuru");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilToprakNemi");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilHorizonAdi");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("HorizonRenkTuru");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("KisaToprakTuruAdi");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("hacimYuzdesiTuru");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("kirecTuru");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilKokDagilisi");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilAraziYuzeySekli");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilDrenaj");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilHumusTuru");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilToprakStruktur");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilMutlakDerinlikTuru");//silvikultur kod değerleri- yetismeOrtami
        ozm_s_modul_kod_deger_keys.add("SilToprakSuTuru");//silvikultur kod değerleri- yetismeOrtami

        ozm_s_modul_kod_deger_keys.add("SilOdunsuOtsu");//vejetasyon

        ozm_s_modul_kod_deger_keys.add("OymYanginTuru");//orman yangınları
        ozm_s_modul_kod_deger_keys.add("OymYangınRiskDurumu");

        ozm_s_modul_kod_deger_keys.add("fidYas");
        ozm_s_modul_kod_deger_keys.add("fidHacim");
        ozm_s_modul_kod_deger_keys.add("fidAmbalaj");
        ozm_s_modul_kod_deger_keys.add("fidBoy");
        ozm_s_modul_kod_deger_keys.add("fidCevre");


        ozm_s_modul_kod_deger_keys.add("BoySinifi");//isletme pazarlama kod değerleri
        ozm_s_modul_kod_deger_keys.add("KaliteSinifi");//isletme pazarlama kod değerleri


        return ozm_s_modul_kod_deger_keys;
    }

    public static HashMap<Integer,String> getOzm_supheli_tur()
    {
        ozm_supheli_tur = new HashMap<Integer, String>();
        ozm_supheli_tur.put(0,"Tanik");
        ozm_supheli_tur.put(1,"Supheli");
        ozm_supheli_tur.put(2,"Musteki");

        return ozm_supheli_tur;
    }

    public static List<String> getOzm_supheli_tur_string()
    {
        List<String> supheli_tur_string = new ArrayList<String>();
        supheli_tur_string.add("Tanik");
        supheli_tur_string.add("Supheli");
        supheli_tur_string.add("Musteki");

        return supheli_tur_string;
    }

    public static HashMap<Integer,String> getAylar()
    {
        aylar = new HashMap<Integer, String>();
        aylar.put(0,"Ocak");
        aylar.put(1,"Şubat");
        aylar.put(2,"Mart");
        aylar.put(3,"Nisan");
        aylar.put(4,"Mayıs");
        aylar.put(5,"Haziran");
        aylar.put(6,"Temmuz");
        aylar.put(7,"Ağustos");
        aylar.put(8,"Eylül");
        aylar.put(9,"Ekim");
        aylar.put(10,"Kasım");
        aylar.put(11,"Aralık");
        return aylar;
    }


    public static HashMap<String,String> getAciklama_nedenleri()
    {
        aciklama_nedenleri = new HashMap<String, String>();
        aciklama_nedenleri.put("0","Devrik");
        aciklama_nedenleri.put("1","Kırık");
        aciklama_nedenleri.put("2","Dikili Kuru");
        aciklama_nedenleri.put("3","Böcekli");
        aciklama_nedenleri.put("4","Azman");
        aciklama_nedenleri.put("5","Kovuk");
        aciklama_nedenleri.put("6","Dip Çatal");
        aciklama_nedenleri.put("7","Gövde Çatal");
        aciklama_nedenleri.put("8","Tepe Çatal");
        aciklama_nedenleri.put("9","Yanmış");
        aciklama_nedenleri.put("10","Mantarlı");
        aciklama_nedenleri.put("11","Gövde Bozuk");
        aciklama_nedenleri.put("12","Diğer");

        return aciklama_nedenleri;
    }

    public static String double_digit_str(double double_data , int after_dot_lenght)
    {
        NumberFormat nf = NumberFormat.getInstance(); // get instance
        nf.setMaximumFractionDigits(after_dot_lenght); // set decimal places
        String result = nf.format(double_data);
        return result;
    }

    public static String before_dot_str(double double_data)
    {
        String data = String.valueOf(double_data);
        Log.v("data = ",data);
        if(data.contains("."))
            data = data.substring(0, data.indexOf("."));
        if(data.contains(","))
            data = data.substring(0, data.indexOf(","));
        Log.v("data sonuc = >",data);
        return data;
    }


    public static String string_before_dot_str(String data)
    {

        Log.v("data = ",data);
        if(data.contains("."))
            data = data.substring(0, data.indexOf("."));
        if(data.contains(","))
            data = data.substring(0, data.indexOf(","));
        Log.v("data sonuc = >",data);
        return data;
    }




    public static Double double_digit_double(double double_data , int after_dot_lenght)
    {
        DecimalFormat df = new DecimalFormat("#.#");
        String dx=df.format(double_data);
        dx = dx.replace(',', '.');
        double_data=Double.valueOf(dx);
        return double_data;
    }


    public static Double double_digit_double_three_digit(double double_data)
    {
        DecimalFormat df = new DecimalFormat("#.###");
        String dx=df.format(double_data);
        dx = dx.replace(',', '.');
        double_data=Double.valueOf(dx);
        return double_data;
    }




    public static void show_process_result_message(Activity activity_ , String message_header_ , String message_body_)
    {
            final AlertDialog alertDialog;
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity_, R.style.Theme_IAPTheme);
        // //new AlertDialog.Builder(activity_, R.style.PauseDialog);
            View view = activity_.getLayoutInflater().inflate(R.layout.mr_dialog_messagebox, null);
            TextView message = (TextView)view.findViewById(R.id.mr_dia_messagebox_messtxt);
            TextView titleMessage = (TextView)view.findViewById(R.id.mr_dialog_title_textbox);
            titleMessage.setText(message_header_);
            titleMessage.setTextSize(30);
            message.setText(message_body_);
            builder.setView(view);
           alertDialog= builder.create();
            alertDialog.show();

            final Handler handler  = new Handler();
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    if (alertDialog.isShowing()) {
                        alertDialog.dismiss();
                    }
                }
            };
            handler.postDelayed(runnable, 1000);

    }


    public static String three_digit_after_dot(String input)
    {

        if(input.contains(".") || input.contains(",")) {
            String temp = input;
            String result = "";
            String temp_pre = input;

            if (temp_pre.contains("."))
                temp_pre = temp_pre.substring(0, temp_pre.indexOf("."));//nokta hariç noktadan öncesini alır
            if (temp_pre.contains(","))
                temp_pre = temp_pre.substring(0, temp_pre.indexOf(","));

            if (temp.contains("."))
                temp = temp.substring(temp.indexOf("."), temp.length());//nokta dahil noktadan sonrasını alır
            if (temp.contains(","))
                temp = temp.substring(temp.indexOf(","), temp.length());

            if (temp.length() == 2)//nokta dahil olduğu için lenght = 2 , 3 kontrol ediliyor.şaşırma
                temp = temp + "00";
            else if (temp.length() == 3)
                temp = temp + "0";
            else if(temp.length() > 4)
            {
                if (temp.contains("."))
                    temp = temp.substring(0, 4);//noktadan sonra çok rakam varsa kısalt
                if (temp.contains(","))
                    temp = temp.substring(0, 4);

            }

            result = temp_pre + temp;
            return result;
        }
        else
            return input;

    }








    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }





    public static List<String> get_vip_user_list()
    {
        vip_user_list = new ArrayList<String>();
        vip_user_list.add("admin");
        vip_user_list.add("sayginkurtoglu");
      //  vip_user_list.add("ibrahimsanli");
        vip_user_list.add("metingultekin");
        vip_user_list.add("resatbenli");
        vip_user_list.add("kenanakyuz");
        vip_user_list.add("selamicilan");
        vip_user_list.add("umitkorkmaz");

        return vip_user_list;
    }


}
