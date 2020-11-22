package DataLayer.ArcGIS;

import android.util.Log;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Feature;
import com.konumsal.orbisozetmobil.OrtakUI.OrtakFunction;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import EntityLayer.GeoPortal.EN_KAR_AGAC;
import EntityLayer.GeoPortal.Geometry;
import EntityLayer.GeoPortal.ORNEK_ALAN;
import EntityLayer.Ortak.MobilGuzergah;
import EntityLayer.Ortak.OrtakNot;
import EntityLayer.Ortak.YolBilgi;
import ToolLayer.OrbisDefaultException;

/**
 * Created by Konumsal PC11 on 11.5.2016.
 */
public class FeatureRefactor {
    public static EN_KAR_AGAC EN_KAR_AGAC_Create(Feature satir) {
        EN_KAR_AGAC ornek_alan = null;
        try {
            Map<String, Object> satir_ornekalan = satir.getAttributes();
            ornek_alan = new EN_KAR_AGAC();

            if (TestForNullData(satir_ornekalan.get("OBJECTID"))) {
                Long val = (Long) satir_ornekalan.get("OBJECTID");
                if (val != null) {
                    String valStr = String.valueOf(val);
                    if (valStr != null) {
                        Long valLong = Long.parseLong(valStr);
                        if (valLong != null) {
                            ornek_alan.setOBJECTID(valLong);
                        }
                    }
                }
                // ornek_alan.setOBJECTID((Long)satir_ornekalan.get("OBJECTID"));
            }
            if (TestForNullData(satir_ornekalan.get("OA_KAR_ID"))) {
                ornek_alan.setOA_KAR_ID((String) satir_ornekalan.get("OA_KAR_ID"));
            }
            if (TestForNullData(satir_ornekalan.get("SIRA_NO"))) {
                ornek_alan.setSIRA_NO((Integer) satir_ornekalan.get("SIRA_NO"));
            }
            if (TestForNullData(satir_ornekalan.get("CAP"))) {
                ornek_alan.setCAP((Integer) satir_ornekalan.get("CAP"));
            }
            if (TestForNullData(satir_ornekalan.get("YAS"))) {
                ornek_alan.setYAS((Integer) satir_ornekalan.get("YAS"));
            }
            if (TestForNullData(satir_ornekalan.get("CIF_KAB_KAL"))) {
                ornek_alan.setCIF_KAB_KAL((Integer) satir_ornekalan.get("CIF_KAB_KAL"));
            }
            if (TestForNullData(satir_ornekalan.get("HAKLA_KAL"))) {
                ornek_alan.setHAKLA_KAL((Integer) satir_ornekalan.get("HAKLA_KAL"));
            }
            if (TestForNullData(satir_ornekalan.get("AGAC_BOYU"))) {
                ornek_alan.setAGAC_BOYU((Integer) satir_ornekalan.get("AGAC_BOYU"));
            }
            if (TestForNullData(satir_ornekalan.get("HAKIM_AGAC_BOY"))) {
                ornek_alan.setHAKIM_AGAC_BOY((Integer) satir_ornekalan.get("HAKIM_AGAC_BOY"));
            }
            if (TestForNullData(satir_ornekalan.get("GLOBALID"))) {
                ornek_alan.setGLOBALID((String) satir_ornekalan.get("GLOBALID"));
            }
            if (TestForNullData(satir_ornekalan.get("ORNEK_ALAN_ID"))) {
                ornek_alan.setORNEK_ALAN_ID((String) satir_ornekalan.get("ORNEK_ALAN_ID"));
            }
            if (TestForNullData(satir_ornekalan.get("MUDAHALE_DURUMU"))) {
                ornek_alan.setMUDAHALE_DURUMU((String) satir_ornekalan.get("MUDAHALE_DURUMU"));
            }
            if (TestForNullData(satir_ornekalan.get("KOKENI_OLUSUMU"))) {
                ornek_alan.setKOKENI_OLUSUMU((String) satir_ornekalan.get("KOKENI_OLUSUMU"));
            }
            if (TestForNullData(satir_ornekalan.get("BIR_CM_HALKA_SAYISI"))) {
                ornek_alan.setBIR_CM_HALKA_SAYISI((Integer) satir_ornekalan.get("BIR_CM_HALKA_SAYISI"));
            }

            if (TestForNullData(satir_ornekalan.get("ID"))) {
                Integer val = (Integer) satir_ornekalan.get("ID");
                if (val != null) {
                    String valStr = String.valueOf(val);
                    if (valStr != null) {
                        Long valLong = Long.parseLong(valStr);
                        if (valLong != null) {
                            ornek_alan.setId(valLong);
                        }
                    }
                }
            }
            if (TestForNullData(satir_ornekalan.get("KALITE_SINIFI"))) {
                ornek_alan.setKALITE_SINIFI((String) satir_ornekalan.get("KALITE_SINIFI"));
            }
            if (TestForNullData(satir_ornekalan.get("SILVIKULTUR_DURUM"))) {
                ornek_alan.setSILVIKULTUR_DURUM((Integer) satir_ornekalan.get("SILVIKULTUR_DURUM"));
            }
            if (TestForNullData(satir_ornekalan.get("SAGLIK_DURUMU"))) {
                ornek_alan.setSAGLIK_DURUMU((String) satir_ornekalan.get("SAGLIK_DURUMU"));
            }
            if (TestForNullData(satir_ornekalan.get("PLAN_ID"))) {
                ornek_alan.setPLAN_ID((String) satir_ornekalan.get("PLAN_ID"));
            }
            if (TestForNullData(satir_ornekalan.get("AGAC_TUR_KOD"))) {
                ornek_alan.setAGAC_TUR_KOD((String) satir_ornekalan.get("AGAC_TUR_KOD"));
            }
            if (TestForNullData(satir_ornekalan.get("ALAN_KARNE_ID"))) {
                ornek_alan.setALAN_KARNE_ID((Integer) satir_ornekalan.get("ALAN_KARNE_ID"));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Log.e("FetrRefctr", e.toString());
        } catch (ClassCastException e) {
            e.printStackTrace();
            Log.e("FetrRefctr", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("FetrRefctr", e.toString());
        } catch (Throwable e) {
            e.printStackTrace();
            Log.e("FetrRefctr", e.toString());
        } finally {
            return ornek_alan;
        }
    }

    public static ORNEK_ALAN OrnekAlan_Create(Feature satir) {
        ORNEK_ALAN ornek_alan = null;
        try {
            Map<String, Object> satir_ornekalan = satir.getAttributes();
            ornek_alan = new ORNEK_ALAN();

            if (TestForNullData(satir_ornekalan.get("NOKTA_NUMARASI"))) {
                ornek_alan.setNOKTA_NUMARASI((Integer) satir_ornekalan.get("NOKTA_NUMARASI"));
            }
            if (TestForNullData(satir_ornekalan.get("ENLEM_X"))) {
                ornek_alan.setENLEM_X((String) satir_ornekalan.get("ENLEM_X"));
            }
            if (TestForNullData(satir_ornekalan.get("BOYLAM_Y"))) {
                ornek_alan.setBOYLAM_Y((String) satir_ornekalan.get("BOYLAM_Y"));
            }
            if (TestForNullData(satir_ornekalan.get("RAKIM_Z"))) {
                ornek_alan.setRAKIM_Z((String) satir_ornekalan.get("RAKIM_Z"));
            }
            if (TestForNullData(satir_ornekalan.get("OBJECTID"))) {
                ornek_alan.setOBJECTID((Long) satir_ornekalan.get("OBJECTID"));
            }
            if (TestForNullData(satir_ornekalan.get("ID"))) {
                try {
                    Integer val = (Integer) satir_ornekalan.get("ID");
                    if (val != null) {
                        String valStr = String.valueOf(val);
                        if (valStr != null) {
                            Long valLong = Long.parseLong(valStr);
                            if (valLong != null) {
                                ornek_alan.setId(valLong);
                            }
                        }

                    }

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            if (TestForNullData(satir_ornekalan.get("PLAN_HAZIRLIK_ID"))) {
                ornek_alan.setPLAN_HAZIRLIK_ID((Integer) satir_ornekalan.get("PLAN_HAZIRLIK_ID"));
            }
            if (TestForNullData(satir_ornekalan.get("ALAN_BUYUKLUGU"))) {
                ornek_alan.setALAN_BUYUKLUGU((Double) satir_ornekalan.get("ALAN_BUYUKLUGU"));
            }
            if (TestForNullData(satir_ornekalan.get("GLOBALID"))) {
                ornek_alan.setGLOBALID((String) satir_ornekalan.get("GLOBALID"));
            }
            if (TestForNullData(satir_ornekalan.get("BOLMECIKID"))) {
                ornek_alan.setBOLMECIKID((String) satir_ornekalan.get("BOLMECIKID"));
            }
            if (TestForNullData(satir_ornekalan.get("PLANID"))) {
                ornek_alan.setPLANID((String) satir_ornekalan.get("PLANID"));
            }
            if (TestForNullData(satir_ornekalan.get("KOKENI"))) {
                ornek_alan.setKOKENI((String) satir_ornekalan.get("KOKENI"));
            }
            if (TestForNullData(satir_ornekalan.get("MESCERE_TIPI"))) {
                ornek_alan.setMESCERE_TIPI((String) satir_ornekalan.get("MESCERE_TIPI"));
            }
            if (TestForNullData(satir_ornekalan.get("GLOBAL_NOKTA_ID"))) {
                ornek_alan.setGLOBAL_NOKTA_ID((String) satir_ornekalan.get("GLOBAL_NOKTA_ID"));
            }
            if (TestForNullData(satir_ornekalan.get("DURUM"))) {
                ornek_alan.setDURUM((String) satir_ornekalan.get("DURUM"));
            }
            if (TestForNullData(satir_ornekalan.get("ACIKLAMA"))) {
                ornek_alan.setACIKLAMA((String) satir_ornekalan.get("ACIKLAMA"));
            }
            if (TestForNullData(satir_ornekalan.get("EKIP_ID"))) {
                ornek_alan.setEKIP_ID((Integer) satir_ornekalan.get("EKIP_ID"));
            }
        /*

    private Integer ADAY_MESCERE_TIPI_ID;
    private String PAFTA_NO;
    private Integer ORNEKLEME_ALAN_TIPI;

    private String ORMAN_FORMU;
    private String ORTUS_BOLLUK_CALI_KATI;
    private String CALI_KATI_TUR_SAYISI;
    private String ORTUS_BOLLUK_OT_KATI;
    private Integer OT_KATI_TUR_SAYISI;
    private String MUDAHALE_DURUMU;
    private Integer GECLIK_DURUMU_1;
    private Integer YASAM_GUCU_1;
    private Integer GENCLIK_DURUMU_2;
    private Integer YASAM_GUCU_2;
    private String MESCERE_TUR_KARISIMI;
    private String MESCERE_KAPALILIK;
    private String MESCERE_TABAKALILIK;
    private Integer MESCERE_OLUSUMU;
    private String BEKLENEN_ORMAN_FONKSIYONU;
    private String OZELLIKLI_YER;
    private String YUK_KORU_DEG_TAS_ORMAN;
    private Integer SILVIKULTUR_MUDAHALE_ONCELIK;
    private String SILVI_KULTUR_MUDAHALE_SEKLI;
    private String ODUN_DISI_ORMAN_URUN_ID;
    private Integer SUKSESYON_ASAMASI;
    private String  YABANI_HAYVAN_GOZLEM;
    private String YABANI_HAYVAN_TEHDIT_FAKTORU;
    private String ARAZI_KULLANIMI;
    private String MULKIYET;
    private String TUR_OTLATMA_DERECESI;
    private String DIGER_GOZLEM;
    private String TAKSATOR;
    private String OLCME_TARIH;
    private String OLU_AGAC_NEDENI_1;
    private String OLU_AGAC_NEDENI_2;
    private Integer OLU_AGAC_ADET_1;
    private Integer OLU_AGAC_ADET_2;
    private Double ORTALAMA_BOY_1;
    private Double ORTALAMA_BOY_2;
    private Integer ORTALAMA_CAP_1;
    private Integer ORTALAMA_CAP_2;
    private String  GOZLENEN_YABANI_HAYVAN;
    private Integer GOZLENEN_HAYVAN_ADET;

    private String GUNLEME_ZAMANI;
    private Integer GUNLEYEN_ID;

        */


        } catch (ClassCastException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            return ornek_alan;
        }
    }


    public static boolean TestForNullData(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return true;
        }
    }

    // public static<T extends BaseEntity> List<EntityLayer.GeoPortal.Feature> convertToFeature(List<T> datalist) throws OrbisDefaultException {
    //     List<EntityLayer.GeoPortal.Feature> featureList = new ArrayList<EntityLayer.GeoPortal.Feature>();
    //     try {
    //         if (datalist!=null && datalist.size()>0)
    //         {
    //             for (T item : datalist)
    //             {
    //                 EntityLayer.GeoPortal.Feature nfeature= new EntityLayer.GeoPortal.Feature();
    //                 Field[] fla= item.getClass().getDeclaredFields();
    //                 for (Field fd : fla)
    //                 {
    //                     FeatureAttribute fattribute= fd.getAnnotation(FeatureAttribute.class);
    //                     if (fattribute!=null)
    //                     {
    //                         if (fd!=null)
    //                         {
    //                             fd.setAccessible(true);
    //                             Object vlac = fd.get(item);
    //                             if (vlac!=null)
    //                             {
    //                                 String fdName = fd.getName();
    //                                 //nfeature.attributes.put(fdName,fd.get(item));
    //                             }
    //                         }
    //                     }
    //                 }
    //                 featureList.add(nfeature);
    //             }
    //         }
    //     } catch (IllegalAccessException e) {
    //         e.printStackTrace();
    //         throw new OrbisDefaultException("FeatureRefactor:convertToFeature->"+e.toString());
//
    //     }catch (Exception e) {
    //         e.printStackTrace();
    //         throw new OrbisDefaultException("FeatureRefactor:convertToFeature->"+e.toString());
    //     }catch (Throwable e) {
    //         e.printStackTrace();
    //         throw new OrbisDefaultException("FeatureRefactor:convertToFeature->"+e.toString());
    //     }
    //     return featureList;
    // }
    public static List<EntityLayer.GeoPortal.Mfeature> convertFeatureListFromENKARAGAC(List<EN_KAR_AGAC> datalist) {
        List<EntityLayer.GeoPortal.Mfeature> fList = new ArrayList<EntityLayer.GeoPortal.Mfeature>();
        for (EN_KAR_AGAC en_kar_agac : datalist) {
            EntityLayer.GeoPortal.Mfeature feature = new EntityLayer.GeoPortal.Mfeature();
            if (en_kar_agac.getORNEK_ALAN_ID() != null) {
                feature.attributes.put("ORNEK_ALAN_ID", en_kar_agac.getORNEK_ALAN_ID());
            }
            if (en_kar_agac.getOBJECTID() != null) {
                feature.attributes.put("OBJECTID", en_kar_agac.getOBJECTID());
            }
            if (en_kar_agac.getOA_KAR_ID() != null) {
                feature.attributes.put("OA_KAR_ID", en_kar_agac.getOA_KAR_ID());
            }
            if (en_kar_agac.getSIRA_NO() != null) {
                feature.attributes.put("SIRA_NO", en_kar_agac.getSIRA_NO());
            }
            if (en_kar_agac.getCAP() != null) {
                feature.attributes.put("CAP", en_kar_agac.getCAP());
            }
            if (en_kar_agac.getYAS() != null) {
                feature.attributes.put("YAS", en_kar_agac.getYAS());
            }
            if (en_kar_agac.getAGAC_BOYU() != null) {
                feature.attributes.put("AGAC_BOYU", en_kar_agac.getAGAC_BOYU());
            }
            if (en_kar_agac.getGLOBALID() != null) {
                feature.attributes.put("GLOBALID", en_kar_agac.getGLOBALID());
            }
            if (en_kar_agac.getORNEK_ALAN_ID() != null) {
                feature.attributes.put("ORNEK_ALAN_ID", en_kar_agac.getORNEK_ALAN_ID());
            }
            if (en_kar_agac.getMUDAHALE_DURUMU() != null) {
                feature.attributes.put("MUDAHALE_DURUMU", en_kar_agac.getMUDAHALE_DURUMU());
            }
            if (en_kar_agac.getKOKENI_OLUSUMU() != null) {
                feature.attributes.put("KOKENI_OLUSUMU", en_kar_agac.getKOKENI_OLUSUMU());
            }
            if (en_kar_agac.getKALITE_SINIFI() != null) {
                feature.attributes.put("KALITE_SINIFI", en_kar_agac.getKALITE_SINIFI());
            }
            if (en_kar_agac.getSILVIKULTUR_DURUM() != null) {
                feature.attributes.put("SILVIKULTUR_DURUM", en_kar_agac.getSILVIKULTUR_DURUM());
            }
            if (en_kar_agac.getSAGLIK_DURUMU() != null) {
                feature.attributes.put("SAGLIK_DURUMU", en_kar_agac.getSAGLIK_DURUMU());
            }
            if (en_kar_agac.getPLAN_ID() != null) {
                feature.attributes.put("PLAN_ID", en_kar_agac.getPLAN_ID());
            }
            if (en_kar_agac.getAGAC_TUR_KOD() != null) {
                feature.attributes.put("AGAC_TUR_KOD", en_kar_agac.getAGAC_TUR_KOD());
            }
            if (en_kar_agac.getALAN_KARNE_ID() != null) {
                feature.attributes.put("ALAN_KARNE_ID", en_kar_agac.getALAN_KARNE_ID());
            }
            if (en_kar_agac.getAGAC_TUR_ID() != null) {
                feature.attributes.put("AGAC_TUR_ID", en_kar_agac.getAGAC_TUR_ID());
            }
            fList.add(feature);
        }
        return fList;
    }


    public static Point utm_to_webmeter(Point p) {
        Point point = (Point) GeometryEngine.project(p, SpatialReference.create(32636), SpatialReference.create(102100));
        Log.v("WEB METER", "=>" + point.getX() + "-" + point.getY());
        return point;
    }

    public static EntityLayer.GeoPortal.Gfeature convertFeatureFromORNEKALAN(ORNEK_ALAN ornek_alan) {

        EntityLayer.GeoPortal.Gfeature feature = new EntityLayer.GeoPortal.Gfeature();
        if (ornek_alan.getACIKLAMA() != null) {
            feature.attributes.put("ACIKLAMA", ornek_alan.getACIKLAMA());
            Log.v("aciklama", "==>" + ornek_alan.getACIKLAMA());
        }
        if (ornek_alan.getPLAN_HAZIRLIK_ID() != null) {
            feature.attributes.put("PLAN_HAZIRLIK_ID", ornek_alan.getPLAN_HAZIRLIK_ID());
            Log.v("PLAN_HAZIRLIK_ID", "==>" + ornek_alan.getPLAN_HAZIRLIK_ID());
        }
        if (ornek_alan.getNOKTA_NUMARASI() != null) {
            feature.attributes.put("NOKTA_NUMARASI", ornek_alan.getNOKTA_NUMARASI());
            Log.v("NOKTA_NUMARASI", "==>" + ornek_alan.getNOKTA_NUMARASI());
        }
        if (ornek_alan.getALAN_BUYUKLUGU() != null) {
            feature.attributes.put("ALAN_BUYUKLUGU", ornek_alan.getALAN_BUYUKLUGU());
            Log.v("ALAN_BUYUKLUGU", "==>" + ornek_alan.getALAN_BUYUKLUGU());
        }
        if (ornek_alan.getENLEM_X() != null) {
            feature.attributes.put("ENLEM_X", ornek_alan.getENLEM_X());
            Log.v("ENLEM_X", "==>" + ornek_alan.getENLEM_X());
        }

        if (ornek_alan.getBOYLAM_Y() != null) {
            feature.attributes.put("BOYLAM_Y", ornek_alan.getBOYLAM_Y());
            Log.v("BOYLAM_Y", "==>" + ornek_alan.getBOYLAM_Y());
        }


        if (ornek_alan.getUTM_X() != null) {
            feature.attributes.put("UTM_X", ornek_alan.getUTM_X());
            Log.v("UTM_X", "==>" + ornek_alan.getUTM_X());
        }

        if (ornek_alan.getUTM_Y() != null) {
            feature.attributes.put("UTM_Y", ornek_alan.getUTM_Y());
            Log.v("UTM_y", "==>" + ornek_alan.getUTM_Y());
        }

        if (ornek_alan.getPLANID() != null) {
            feature.attributes.put("PLANID", ornek_alan.getPLANID());
            Log.v("PLANID", "==>" + ornek_alan.getPLANID());
        }

        if (ornek_alan.getMESCERE_TIPI() != null) {
            feature.attributes.put("MESCERE_TIPI", ornek_alan.getMESCERE_TIPI());
            Log.v("MESCERE_TIPI", "==>" + ornek_alan.getMESCERE_TIPI());
        }

        if (ornek_alan.getOLCULEN_Y() != null) {
            feature.attributes.put("OLCULEN_Y", ornek_alan.getOLCULEN_Y());
            Log.v("OLCULEN_Y", "==>" + ornek_alan.getOLCULEN_Y());
        }

        if (ornek_alan.getOLCULEN_X() != null) {
            feature.attributes.put("OLCULEN_X", ornek_alan.getOLCULEN_X());
            Log.v("OLCULEN_X", "==>" + ornek_alan.getOLCULEN_X());
        }

        if (ornek_alan.getGIRILEN_AGAC_SAYISI() != null) {
            feature.attributes.put("GIRILEN_AGAC_SAYISI", ornek_alan.getGIRILEN_AGAC_SAYISI());
            Log.v("GIRILEN_AGAC_SAYISI", "==>" + ornek_alan.getGIRILEN_AGAC_SAYISI());
        }
        if (ornek_alan.getMANUEL_MI_EKLENDI() != null) {
            feature.attributes.put("MANUEL_MI_EKLENDI", ornek_alan.getMANUEL_MI_EKLENDI());
            Log.v("MANUEL_MI_EKLENDI", "==>" + ornek_alan.getMANUEL_MI_EKLENDI());
        }
        if (ornek_alan.getBASMUHENDIS_MESTIP() != null) {
            feature.attributes.put("BASMUHENDIS_MESTIP", ornek_alan.getBASMUHENDIS_MESTIP());
            Log.v("BASMUHENDIS_MESTIP", "==>" + ornek_alan.getBASMUHENDIS_MESTIP());
        }

        if (ornek_alan.getENLEM_X() != null && ornek_alan.getBOYLAM_Y() != null) {
            Double enlem = Double.valueOf(ornek_alan.getENLEM_X());
            Double boylam = Double.valueOf(ornek_alan.getBOYLAM_Y());
            Double x = 0.0;
            Double y = 0.0;
            Log.v("enlem", "==>" + String.valueOf(enlem));
            Log.v("boylam", "==>" + String.valueOf(boylam));
            if (enlem != null && boylam != null) {
                x = enlem;
                y = boylam;
            }

            Point point_1 = new Point();
            point_1.setX(x);
            point_1.setY(y);
            Point send_point_geometry = utm_to_webmeter(point_1);


            Geometry geometry = new Geometry();
            //   geometry.x = x;
            //  geometry.y = y;
            geometry.x = send_point_geometry.getX();
            geometry.y = send_point_geometry.getY();
            feature.geometry = geometry;
        }

        return feature;
    }

    public static ORNEK_ALAN readFeatureMapORNEKALAN(final Map<String, Object> entity) throws OrbisDefaultException {
        List<EntityLayer.GeoPortal.Mfeature> fList = new ArrayList<EntityLayer.GeoPortal.Mfeature>();
        ORNEK_ALAN oa = new ORNEK_ALAN();
        for (Map.Entry<String, Object> mp : entity.entrySet()) {
            Field fld = null;
            try {
                if (mp.getKey().equals("ID")) {
                    fld = oa.getClass().getDeclaredField("id");
                } else {
                    fld = oa.getClass().getDeclaredField(mp.getKey());
                }
                fld.setAccessible(true);
                fld.set(oa, mp.getValue());
            } catch (NoSuchFieldException e) {
                throw new OrbisDefaultException(e.toString());
            } catch (Exception e) {
                throw new OrbisDefaultException(e.toString());
            } catch (Throwable e) {
                throw new OrbisDefaultException(e.toString());
            }

        }
        return oa;
    }

    //  public static List<ORNEK_ALAN> readFeaturesORNEKALAN(List<EntityLayer.GeoPortal.Feature> features) throws OrbisDefaultException
    //  {
    //      List<ORNEK_ALAN> ornek_alanList = new ArrayList<ORNEK_ALAN>();
    //      try {
    //      for (EntityLayer.GeoPortal.Feature feature : features)
    //      {
    //          ORNEK_ALAN nokta =  new ORNEK_ALAN();
    //          for (String key :feature.attributes.keySet())
    //          {
    //                  nokta.getClass().getDeclaredField(key).setAccessible(true);
    //                  nokta.getClass().getDeclaredField(key).set(nokta,feature.attributes.get(key));
    //          }
    //          if (nokta!=null)
    //          {
    //              ornek_alanList.add(nokta);
    //          }
    //      }
    //      } catch (IllegalAccessException e)
    //      {
    //         throw new OrbisDefaultException(e.toString());
    //      } catch (NoSuchFieldException e) {
    //          throw new OrbisDefaultException(e.toString());
    //      }catch (Exception e) {
    //          throw new OrbisDefaultException(e.toString());
    //      }catch (Throwable e) {
    //          throw new OrbisDefaultException(e.toString());
    //      }finally {
    //          return ornek_alanList;
    //      }
//
    //  }
    //  public static List<PLAN> readFeaturesPLAN(List<EntityLayer.GeoPortal.Feature> features) throws OrbisDefaultException
    //  {
    //      List<PLAN> plan_List = new ArrayList<PLAN>();
    //      try {
    //          for (EntityLayer.GeoPortal.Feature feature : features)
    //          {
    //              PLAN pilan= new PLAN();;
    //              for (String key :feature.attributes.keySet())
    //              {
    //                  if (feature.attributes.get(key)!=null)
    //                  {
    //                      Field fld = pilan.getClass().getDeclaredField(key);
    //                      fld.setAccessible(true);
    //                      Type typ =fld.getType();
    //                      Class<?> fldClaz = typ.getClass();
    //                      if (fldClaz.isAssignableFrom(feature.attributes.get(key).getClass()))
    //                          fld.set(pilan, fldClaz.cast(feature.attributes.get(key)));
//
    //                  }
//
    //              }
    //              if (pilan!=null)
    //              {
    //                  plan_List.add(pilan);
    //              }
    //          }
    //      } catch (IllegalAccessException e)
    //      {
    //          throw new OrbisDefaultException(e.toString());
    //      } catch (NoSuchFieldException e) {
    //          throw new OrbisDefaultException(e.toString());
    //      }catch (Exception e) {
    //          throw new OrbisDefaultException(e.toString());
    //      }catch (Throwable e) {
    //          throw new OrbisDefaultException(e.toString());
    //      }finally {
    //          return plan_List;
    //      }
//
    //  }
    public static List<EntityLayer.GeoPortal.Mfeature> convertFeatureListFromORNEKALAN(List<ORNEK_ALAN> datalist) throws OrbisDefaultException {
        List<EntityLayer.GeoPortal.Mfeature> fList = new ArrayList<EntityLayer.GeoPortal.Mfeature>();
        try {
            for (ORNEK_ALAN oa : datalist) {
                EntityLayer.GeoPortal.Mfeature feature = new EntityLayer.GeoPortal.Mfeature();
                if (oa.getOBJECTID() != null) {
                    feature.attributes.put("OBJECTID", oa.getOBJECTID());
                }

                if (oa.getId() != null) {
                    feature.attributes.put("ID", oa.getId());
                }
                if (oa.getPLAN_HAZIRLIK_ID() != null) {
                    feature.attributes.put("PLAN_HAZIRLIK_ID", oa.getPLAN_HAZIRLIK_ID());
                }
                if (oa.getNOKTA_NUMARASI() != null) {
                    feature.attributes.put("NOKTA_NUMARASI", oa.getNOKTA_NUMARASI());
                }
                if (oa.getALAN_BUYUKLUGU() != null) {
                    feature.attributes.put("ALAN_BUYUKLUGU", oa.getALAN_BUYUKLUGU());
                }
                if (oa.getADAY_MESCERE_TIPI_ID() != null) {
                    feature.attributes.put("ADAY_MESCERE_TIPI_ID", oa.getADAY_MESCERE_TIPI_ID());
                }
                if (oa.getPAFTA_NO() != null) {
                    feature.attributes.put("PAFTA_NO", oa.getPAFTA_NO());
                }
                if (oa.getORNEKLEME_ALAN_TIPI() != null) {
                    feature.attributes.put("ORNEKLEME_ALAN_TIPI", oa.getORNEKLEME_ALAN_TIPI());
                }
                if (oa.getENLEM_X() != null) {
                    feature.attributes.put("ENLEM_X", oa.getENLEM_X());
                }
                if (oa.getBOYLAM_Y() != null) {
                    feature.attributes.put("BOYLAM_Y", oa.getBOYLAM_Y());
                }
                if (oa.getRAKIM_Z() != null) {
                    feature.attributes.put("RAKIM_Z", oa.getRAKIM_Z());
                }
                if (oa.getORMAN_FORMU() != null) {
                    feature.attributes.put("ORMAN_FORMU", oa.getORMAN_FORMU());
                }
                if (oa.getORTUS_BOLLUK_CALI_KATI() != null) {
                    feature.attributes.put("ORTUS_BOLLUK_CALI_KATI", oa.getORTUS_BOLLUK_CALI_KATI());
                }
                if (oa.getCALI_KATI_TUR_SAYISI() != null) {
                    feature.attributes.put("CALI_KATI_TUR_SAYISI", oa.getCALI_KATI_TUR_SAYISI());
                }
                if (oa.getORTUS_BOLLUK_OT_KATI() != null) {
                    feature.attributes.put("ORTUS_BOLLUK_OT_KATI", oa.getORTUS_BOLLUK_OT_KATI());
                }
                if (oa.getOT_KATI_TUR_SAYISI() != null) {
                    feature.attributes.put("OT_KATI_TUR_SAYISI", oa.getOT_KATI_TUR_SAYISI());
                }
                if (oa.getMUDAHALE_DURUMU() != null) {
                    feature.attributes.put("MUDAHALE_DURUMU", oa.getMUDAHALE_DURUMU());
                }
                if (oa.getGECLIK_DURUMU_1() != null) {
                    feature.attributes.put("GECLIK_DURUMU_1", oa.getGECLIK_DURUMU_1());
                }
                if (oa.getYASAM_GUCU_1() != null) {
                    feature.attributes.put("YASAM_GUCU_1", oa.getYASAM_GUCU_1());
                }
                if (oa.getGENCLIK_DURUMU_2() != null) {
                    feature.attributes.put("GENCLIK_DURUMU_2", oa.getGENCLIK_DURUMU_2());
                }
                if (oa.getYASAM_GUCU_2() != null) {
                    feature.attributes.put("YASAM_GUCU_2", oa.getYASAM_GUCU_2());
                }
                if (oa.getMESCERE_TUR_KARISIMI() != null) {
                    feature.attributes.put("MESCERE_TUR_KARISIMI", oa.getMESCERE_TUR_KARISIMI());
                }
                if (oa.getMESCERE_KAPALILIK() != null) {
                    feature.attributes.put("MESCERE_KAPALILIK", oa.getMESCERE_KAPALILIK());
                }
                if (oa.getMESCERE_TABAKALILIK() != null) {
                    feature.attributes.put("MESCERE_TABAKALILIK", oa.getMESCERE_TABAKALILIK());
                }
                if (oa.getMESCERE_OLUSUMU() != null) {
                    feature.attributes.put("MESCERE_OLUSUMU", oa.getMESCERE_OLUSUMU());
                }
                if (oa.getBEKLENEN_ORMAN_FONKSIYONU() != null) {
                    feature.attributes.put("BEKLENEN_ORMAN_FONKSIYONU", oa.getBEKLENEN_ORMAN_FONKSIYONU());
                }
                if (oa.getOZELLIKLI_YER() != null) {
                    feature.attributes.put("OZELLIKLI_YER", oa.getOZELLIKLI_YER());
                }
                if (oa.getYUK_KORU_DEG_TAS_ORMAN() != null) {
                    feature.attributes.put("YUK_KORU_DEG_TAS_ORMAN", oa.getYUK_KORU_DEG_TAS_ORMAN());
                }
                if (oa.getSILVIKULTUR_MUDAHALE_ONCELIK() != null) {
                    feature.attributes.put("SILVIKULTUR_MUDAHALE_ONCELIK", oa.getSILVIKULTUR_MUDAHALE_ONCELIK());
                }
                if (oa.getSILVI_KULTUR_MUDAHALE_SEKLI() != null) {
                    feature.attributes.put("SILVI_KULTUR_MUDAHALE_SEKLI", oa.getSILVI_KULTUR_MUDAHALE_SEKLI());
                }
                if (oa.getODUN_DISI_ORMAN_URUN_ID() != null) {
                    feature.attributes.put("ODUN_DISI_ORMAN_URUN_ID", oa.getODUN_DISI_ORMAN_URUN_ID());
                }
                if (oa.getSUKSESYON_ASAMASI() != null) {
                    feature.attributes.put("SUKSESYON_ASAMASI", oa.getSUKSESYON_ASAMASI());
                }
                if (oa.getYABANI_HAYVAN_GOZLEM() != null) {
                    feature.attributes.put("YABANI_HAYVAN_GOZLEM", oa.getYABANI_HAYVAN_GOZLEM());
                }
                if (oa.getYABANI_HAYVAN_TEHDIT_FAKTORU() != null) {
                    feature.attributes.put("YABANI_HAYVAN_TEHDIT_FAKTORU", oa.getYABANI_HAYVAN_TEHDIT_FAKTORU());
                }
                if (oa.getARAZI_KULLANIMI() != null) {
                    feature.attributes.put("ARAZI_KULLANIMI", oa.getARAZI_KULLANIMI());
                }
                if (oa.getMULKIYET() != null) {
                    feature.attributes.put("MULKIYET", oa.getMULKIYET());
                }
                if (oa.getTUR_OTLATMA_DERECESI() != null) {
                    feature.attributes.put("TUR_OTLATMA_DERECESI", oa.getTUR_OTLATMA_DERECESI());
                }
                if (oa.getDIGER_GOZLEM() != null) {
                    feature.attributes.put("DIGER_GOZLEM", oa.getDIGER_GOZLEM());
                }
                if (oa.getTAKSATOR() != null) {
                    feature.attributes.put("TAKSATOR", oa.getTAKSATOR());
                }
                if (oa.getOLCME_TARIH() != null) {
                    feature.attributes.put("OLCME_TARIH", oa.getOLCME_TARIH());
                }
                if (oa.getOLU_AGAC_NEDENI_1() != null) {
                    feature.attributes.put("OLU_AGAC_NEDENI_1", oa.getOLU_AGAC_NEDENI_1());
                }
                if (oa.getOLU_AGAC_NEDENI_2() != null) {
                    feature.attributes.put("OLU_AGAC_NEDENI_2", oa.getOLU_AGAC_NEDENI_2());
                }
                if (oa.getOLU_AGAC_ADET_1() != null) {
                    feature.attributes.put("OLU_AGAC_ADET_1", oa.getOLU_AGAC_ADET_1());
                }
                if (oa.getOLU_AGAC_ADET_2() != null) {
                    feature.attributes.put("OLU_AGAC_ADET_2", oa.getOLU_AGAC_ADET_2());
                }
                if (oa.getORTALAMA_BOY_1() != null) {
                    feature.attributes.put("ORTALAMA_BOY_1", oa.getORTALAMA_BOY_1());
                }
                if (oa.getORTALAMA_BOY_2() != null) {
                    feature.attributes.put("ORTALAMA_BOY_2", oa.getORTALAMA_BOY_2());
                }
                if (oa.getORTALAMA_CAP_1() != null) {
                    feature.attributes.put("ORTALAMA_CAP_1", oa.getORTALAMA_CAP_1());
                }

                if (oa.getORTALAMA_CAP_2() != null) {
                    feature.attributes.put("ORTALAMA_CAP_2", oa.getORTALAMA_CAP_2());
                }
                if (oa.getGOZLENEN_YABANI_HAYVAN() != null) {
                    feature.attributes.put("GOZLENEN_YABANI_HAYVAN", oa.getGOZLENEN_YABANI_HAYVAN());
                }
                if (oa.getGOZLENEN_HAYVAN_ADET() != null) {
                    feature.attributes.put("GOZLENEN_HAYVAN_ADET", oa.getGOZLENEN_HAYVAN_ADET());
                }
                if (oa.getGLOBAL_NOKTA_ID() != null) {
                    feature.attributes.put("GLOBAL_NOKTA_ID", oa.getGLOBAL_NOKTA_ID());
                }
                if (oa.getDURUM() != null) {
                    feature.attributes.put("DURUM", oa.getDURUM());
                }
                if (oa.getACIKLAMA() != null) {
                    feature.attributes.put("ACIKLAMA", oa.getACIKLAMA());
                }
                if (oa.getEKIP_ID() != null) {
                    feature.attributes.put("EKIP_ID", oa.getEKIP_ID());
                }
                if (oa.getGUNLEME_ZAMANI() != null) {
                    feature.attributes.put("GUNLEME_ZAMANI", oa.getGUNLEME_ZAMANI());
                }
                if (oa.getGUNLEYEN_ID() != null) {
                    feature.attributes.put("GUNLEYEN_ID", oa.getGUNLEYEN_ID());
                }
                if (oa.getGLOBALID() != null) {
                    feature.attributes.put("GLOBALID", oa.getGLOBALID());
                }
                if (oa.getBOLMECIKID() != null) {
                    feature.attributes.put("BOLMECIKID", oa.getBOLMECIKID());
                }
                if (oa.getPLANID() != null) {
                    feature.attributes.put("PLANID", oa.getPLANID());
                }
                if (oa.getKOKENI() != null) {
                    feature.attributes.put("KOKENI", oa.getKOKENI());
                }
                if (oa.getMESCERE_TIPI() != null) {
                    feature.attributes.put("MESCERE_TIPI", oa.getMESCERE_TIPI());
                }
                if (oa.getOLCULEN_X() != null) {
                    feature.attributes.put("OLCULEN_X", oa.getOLCULEN_X());
                }
                if (oa.getOLCULEN_Y() != null) {
                    feature.attributes.put("OLCULEN_Y", oa.getOLCULEN_Y());
                }
                if (oa.getCREATED_USER() != null) {
                    feature.attributes.put("CREATED_USER", oa.getCREATED_USER());
                }
                if (oa.getCREATED_DATE() != null) {
                    feature.attributes.put("CREATED_DATE", oa.getCREATED_DATE());
                }
                if (oa.getLAST_EDITED_USER() != null) {
                    feature.attributes.put("LAST_EDITED_USER", oa.getLAST_EDITED_USER());
                }
                if (oa.getLAST_EDITED_DATE() != null) {
                    feature.attributes.put("LAST_EDITED_DATE", oa.getLAST_EDITED_DATE());
                }
                if (oa.getMANUEL_MI_EKLENDI() != null && oa.getMANUEL_MI_EKLENDI() == 1) {
                    feature.attributes.put("UTM_X", oa.getUTM_X());
                    feature.attributes.put("UTM_Y", oa.getUTM_Y());
                }

                if (oa.getENLEM_X() != null && oa.getBOYLAM_Y() != null) {
                    Double enlem = Double.valueOf(oa.getENLEM_X());
                    Double boylam = Double.valueOf(oa.getBOYLAM_Y());
                    Double x = 0.0;
                    Double y = 0.0;
                    Log.v("enlem", "==>" + String.valueOf(enlem));
                    Log.v("boylam", "==>" + String.valueOf(boylam));
                    if (enlem != null && boylam != null) {
                        x = enlem;
                        y = boylam;
                    }

                    Point point_1 = new Point();
                    point_1.setX(x);
                    point_1.setY(y);
                    Point send_point_geometry = utm_to_webmeter(point_1);

                    Geometry geometry = new Geometry();
                    //   geometry.x = x;
                    //  geometry.y = y;
                    geometry.x = send_point_geometry.getX();
                    geometry.y = send_point_geometry.getY();
                    Log.v("UPDATE NOKTA GEO", "NOKTA NO/X/Y =>" + oa.getNOKTA_NUMARASI() + "/" + send_point_geometry.getX() + "/" + send_point_geometry.getY());
                }

                fList.add(feature);
            }
        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } catch (Throwable e) {
            throw new OrbisDefaultException(e.toString());
        }
        return fList;
    }


    public static List<EntityLayer.GeoPortal.Gfeature> convertFeatureListFromORNEKALANupdateGeo(List<ORNEK_ALAN> datalist) throws OrbisDefaultException {
        List<EntityLayer.GeoPortal.Gfeature> fList = new ArrayList<EntityLayer.GeoPortal.Gfeature>();
        try {
            for (ORNEK_ALAN oa : datalist) {
                EntityLayer.GeoPortal.Gfeature feature = new EntityLayer.GeoPortal.Gfeature();
                if (oa.getOBJECTID() != null) {
                    feature.attributes.put("OBJECTID", oa.getOBJECTID());
                }

                if (oa.getId() != null) {
                    feature.attributes.put("ID", oa.getId());
                }
                if (oa.getPLAN_HAZIRLIK_ID() != null) {
                    feature.attributes.put("PLAN_HAZIRLIK_ID", oa.getPLAN_HAZIRLIK_ID());
                }
                if (oa.getNOKTA_NUMARASI() != null) {
                    feature.attributes.put("NOKTA_NUMARASI", oa.getNOKTA_NUMARASI());
                }
                if (oa.getALAN_BUYUKLUGU() != null) {
                    feature.attributes.put("ALAN_BUYUKLUGU", oa.getALAN_BUYUKLUGU());
                }
                if (oa.getADAY_MESCERE_TIPI_ID() != null) {
                    feature.attributes.put("ADAY_MESCERE_TIPI_ID", oa.getADAY_MESCERE_TIPI_ID());
                }
                if (oa.getPAFTA_NO() != null) {
                    feature.attributes.put("PAFTA_NO", oa.getPAFTA_NO());
                }
                if (oa.getORNEKLEME_ALAN_TIPI() != null) {
                    feature.attributes.put("ORNEKLEME_ALAN_TIPI", oa.getORNEKLEME_ALAN_TIPI());
                }
                if (oa.getENLEM_X() != null) {
                    feature.attributes.put("ENLEM_X", oa.getENLEM_X());
                }
                if (oa.getBOYLAM_Y() != null) {
                    feature.attributes.put("BOYLAM_Y", oa.getBOYLAM_Y());
                }
                if (oa.getRAKIM_Z() != null) {
                    feature.attributes.put("RAKIM_Z", oa.getRAKIM_Z());
                }
                if (oa.getORMAN_FORMU() != null) {
                    feature.attributes.put("ORMAN_FORMU", oa.getORMAN_FORMU());
                }
                if (oa.getORTUS_BOLLUK_CALI_KATI() != null) {
                    feature.attributes.put("ORTUS_BOLLUK_CALI_KATI", oa.getORTUS_BOLLUK_CALI_KATI());
                }
                if (oa.getCALI_KATI_TUR_SAYISI() != null) {
                    feature.attributes.put("CALI_KATI_TUR_SAYISI", oa.getCALI_KATI_TUR_SAYISI());
                }
                if (oa.getORTUS_BOLLUK_OT_KATI() != null) {
                    feature.attributes.put("ORTUS_BOLLUK_OT_KATI", oa.getORTUS_BOLLUK_OT_KATI());
                }
                if (oa.getOT_KATI_TUR_SAYISI() != null) {
                    feature.attributes.put("OT_KATI_TUR_SAYISI", oa.getOT_KATI_TUR_SAYISI());
                }
                if (oa.getMUDAHALE_DURUMU() != null) {
                    feature.attributes.put("MUDAHALE_DURUMU", oa.getMUDAHALE_DURUMU());
                }
                if (oa.getGECLIK_DURUMU_1() != null) {
                    feature.attributes.put("GECLIK_DURUMU_1", oa.getGECLIK_DURUMU_1());
                }
                if (oa.getYASAM_GUCU_1() != null) {
                    feature.attributes.put("YASAM_GUCU_1", oa.getYASAM_GUCU_1());
                }
                if (oa.getGENCLIK_DURUMU_2() != null) {
                    feature.attributes.put("GENCLIK_DURUMU_2", oa.getGENCLIK_DURUMU_2());
                }
                if (oa.getYASAM_GUCU_2() != null) {
                    feature.attributes.put("YASAM_GUCU_2", oa.getYASAM_GUCU_2());
                }
                if (oa.getMESCERE_TUR_KARISIMI() != null) {
                    feature.attributes.put("MESCERE_TUR_KARISIMI", oa.getMESCERE_TUR_KARISIMI());
                }
                if (oa.getMESCERE_KAPALILIK() != null) {
                    feature.attributes.put("MESCERE_KAPALILIK", oa.getMESCERE_KAPALILIK());
                }
                if (oa.getMESCERE_TABAKALILIK() != null) {
                    feature.attributes.put("MESCERE_TABAKALILIK", oa.getMESCERE_TABAKALILIK());
                }
                if (oa.getMESCERE_OLUSUMU() != null) {
                    feature.attributes.put("MESCERE_OLUSUMU", oa.getMESCERE_OLUSUMU());
                }
                if (oa.getBEKLENEN_ORMAN_FONKSIYONU() != null) {
                    feature.attributes.put("BEKLENEN_ORMAN_FONKSIYONU", oa.getBEKLENEN_ORMAN_FONKSIYONU());
                }
                if (oa.getOZELLIKLI_YER() != null) {
                    feature.attributes.put("OZELLIKLI_YER", oa.getOZELLIKLI_YER());
                }
                if (oa.getYUK_KORU_DEG_TAS_ORMAN() != null) {
                    feature.attributes.put("YUK_KORU_DEG_TAS_ORMAN", oa.getYUK_KORU_DEG_TAS_ORMAN());
                }
                if (oa.getSILVIKULTUR_MUDAHALE_ONCELIK() != null) {
                    feature.attributes.put("SILVIKULTUR_MUDAHALE_ONCELIK", oa.getSILVIKULTUR_MUDAHALE_ONCELIK());
                }
                if (oa.getSILVI_KULTUR_MUDAHALE_SEKLI() != null) {
                    feature.attributes.put("SILVI_KULTUR_MUDAHALE_SEKLI", oa.getSILVI_KULTUR_MUDAHALE_SEKLI());
                }
                if (oa.getODUN_DISI_ORMAN_URUN_ID() != null) {
                    feature.attributes.put("ODUN_DISI_ORMAN_URUN_ID", oa.getODUN_DISI_ORMAN_URUN_ID());
                }
                if (oa.getSUKSESYON_ASAMASI() != null) {
                    feature.attributes.put("SUKSESYON_ASAMASI", oa.getSUKSESYON_ASAMASI());
                }
                if (oa.getYABANI_HAYVAN_GOZLEM() != null) {
                    feature.attributes.put("YABANI_HAYVAN_GOZLEM", oa.getYABANI_HAYVAN_GOZLEM());
                }
                if (oa.getYABANI_HAYVAN_TEHDIT_FAKTORU() != null) {
                    feature.attributes.put("YABANI_HAYVAN_TEHDIT_FAKTORU", oa.getYABANI_HAYVAN_TEHDIT_FAKTORU());
                }
                if (oa.getARAZI_KULLANIMI() != null) {
                    feature.attributes.put("ARAZI_KULLANIMI", oa.getARAZI_KULLANIMI());
                }
                if (oa.getMULKIYET() != null) {
                    feature.attributes.put("MULKIYET", oa.getMULKIYET());
                }
                if (oa.getTUR_OTLATMA_DERECESI() != null) {
                    feature.attributes.put("TUR_OTLATMA_DERECESI", oa.getTUR_OTLATMA_DERECESI());
                }
                if (oa.getDIGER_GOZLEM() != null) {
                    feature.attributes.put("DIGER_GOZLEM", oa.getDIGER_GOZLEM());
                }
                if (oa.getTAKSATOR() != null) {
                    feature.attributes.put("TAKSATOR", oa.getTAKSATOR());
                }
                if (oa.getOLCME_TARIH() != null) {
                    feature.attributes.put("OLCME_TARIH", oa.getOLCME_TARIH());
                }
                if (oa.getOLU_AGAC_NEDENI_1() != null) {
                    feature.attributes.put("OLU_AGAC_NEDENI_1", oa.getOLU_AGAC_NEDENI_1());
                }
                if (oa.getOLU_AGAC_NEDENI_2() != null) {
                    feature.attributes.put("OLU_AGAC_NEDENI_2", oa.getOLU_AGAC_NEDENI_2());
                }
                if (oa.getOLU_AGAC_ADET_1() != null) {
                    feature.attributes.put("OLU_AGAC_ADET_1", oa.getOLU_AGAC_ADET_1());
                }
                if (oa.getOLU_AGAC_ADET_2() != null) {
                    feature.attributes.put("OLU_AGAC_ADET_2", oa.getOLU_AGAC_ADET_2());
                }
                if (oa.getORTALAMA_BOY_1() != null) {
                    feature.attributes.put("ORTALAMA_BOY_1", oa.getORTALAMA_BOY_1());
                }
                if (oa.getORTALAMA_BOY_2() != null) {
                    feature.attributes.put("ORTALAMA_BOY_2", oa.getORTALAMA_BOY_2());
                }
                if (oa.getORTALAMA_CAP_1() != null) {
                    feature.attributes.put("ORTALAMA_CAP_1", oa.getORTALAMA_CAP_1());
                }

                if (oa.getORTALAMA_CAP_2() != null) {
                    feature.attributes.put("ORTALAMA_CAP_2", oa.getORTALAMA_CAP_2());
                }
                if (oa.getGOZLENEN_YABANI_HAYVAN() != null) {
                    feature.attributes.put("GOZLENEN_YABANI_HAYVAN", oa.getGOZLENEN_YABANI_HAYVAN());
                }
                if (oa.getGOZLENEN_HAYVAN_ADET() != null) {
                    feature.attributes.put("GOZLENEN_HAYVAN_ADET", oa.getGOZLENEN_HAYVAN_ADET());
                }
                if (oa.getGLOBAL_NOKTA_ID() != null) {
                    feature.attributes.put("GLOBAL_NOKTA_ID", oa.getGLOBAL_NOKTA_ID());
                }
                if (oa.getDURUM() != null) {
                    feature.attributes.put("DURUM", oa.getDURUM());
                }
                if (oa.getACIKLAMA() != null) {
                    feature.attributes.put("ACIKLAMA", oa.getACIKLAMA());
                }
                if (oa.getEKIP_ID() != null) {
                    feature.attributes.put("EKIP_ID", oa.getEKIP_ID());
                }
                if (oa.getGUNLEME_ZAMANI() != null) {
                    feature.attributes.put("GUNLEME_ZAMANI", oa.getGUNLEME_ZAMANI());
                }
                if (oa.getGUNLEYEN_ID() != null) {
                    feature.attributes.put("GUNLEYEN_ID", oa.getGUNLEYEN_ID());
                }
                if (oa.getGLOBALID() != null) {
                    feature.attributes.put("GLOBALID", oa.getGLOBALID());
                }
                if (oa.getBOLMECIKID() != null) {
                    feature.attributes.put("BOLMECIKID", oa.getBOLMECIKID());
                }
                if (oa.getPLANID() != null) {
                    feature.attributes.put("PLANID", oa.getPLANID());
                }
                if (oa.getKOKENI() != null) {
                    feature.attributes.put("KOKENI", oa.getKOKENI());
                }
                if (oa.getMESCERE_TIPI() != null) {
                    feature.attributes.put("MESCERE_TIPI", oa.getMESCERE_TIPI());
                }
                if (oa.getOLCULEN_X() != null) {
                    feature.attributes.put("OLCULEN_X", oa.getOLCULEN_X());
                }
                if (oa.getOLCULEN_Y() != null) {
                    feature.attributes.put("OLCULEN_Y", oa.getOLCULEN_Y());
                }
                if (oa.getCREATED_USER() != null) {
                    feature.attributes.put("CREATED_USER", oa.getCREATED_USER());
                }
                if (oa.getCREATED_DATE() != null) {
                    feature.attributes.put("CREATED_DATE", oa.getCREATED_DATE());
                }
                if (oa.getLAST_EDITED_USER() != null) {
                    feature.attributes.put("LAST_EDITED_USER", oa.getLAST_EDITED_USER());
                }
                if (oa.getLAST_EDITED_DATE() != null) {
                    feature.attributes.put("LAST_EDITED_DATE", oa.getLAST_EDITED_DATE());
                }

                if (oa.getENLEM_X() != null && oa.getBOYLAM_Y() != null) {
                    Double enlem = Double.valueOf(oa.getENLEM_X());
                    Double boylam = Double.valueOf(oa.getBOYLAM_Y());
                    Double x = 0.0;
                    Double y = 0.0;
                    Log.v("enlem", "==>" + String.valueOf(enlem));
                    Log.v("boylam", "==>" + String.valueOf(boylam));
                    if (enlem != null && boylam != null) {
                        x = enlem;
                        y = boylam;
                    }

                    Point point_1 = new Point();
                    point_1.setX(x);
                    point_1.setY(y);
                    Point send_point_geometry = utm_to_webmeter(point_1);

                    Geometry geometry = new Geometry();
                    //   geometry.x = x;
                    //  geometry.y = y;
                    geometry.x = send_point_geometry.getX();
                    geometry.y = send_point_geometry.getY();
                    feature.geometry = geometry;
                    Log.v("UPDATE NOKTA GEO", "NOKTA NO/X/Y =>" + oa.getNOKTA_NUMARASI() + "/" + send_point_geometry.getX() + "/" + send_point_geometry.getY());
                }

                fList.add(feature);
            }
        } catch (Exception e) {
            throw new OrbisDefaultException(e.toString());
        } catch (Throwable e) {
            throw new OrbisDefaultException(e.toString());
        }
        return fList;
    }


    public static EntityLayer.GeoPortal.Gfeature convertFeatureListFromMobilGuzergahPointOnly(MobilGuzergah guzergah_point) {

        EntityLayer.GeoPortal.Gfeature feature = new EntityLayer.GeoPortal.Gfeature();

        if (guzergah_point.getOBJECTID() != null) {
            feature.attributes.put("OBJECTID", guzergah_point.getOBJECTID());
            Log.v("OBJECTID", "==>" + guzergah_point.getOBJECTID());
        }
        if (guzergah_point.getGLOBALID() != null) {
            feature.attributes.put("GLOBALID", guzergah_point.getGLOBALID());
        }

        if (OrtakFunction.vip_user_list.contains(OrtakFunction.kullanici_adi)) {
            Log.v("BIRIM_ID", "=>" + OrtakFunction.admine_ozel_birim_id);
            feature.attributes.put("BIRIM_ID", Integer.valueOf(OrtakFunction.admine_ozel_birim_id));
        } else {
            Log.v("BIRIM_ID", "=>" + OrtakFunction.birim_id);
            feature.attributes.put("BIRIM_ID", Integer.valueOf(OrtakFunction.birim_id));
        }


        if (guzergah_point.getNOT_ID() != null) {
            feature.attributes.put("NOT_ID", Integer.valueOf(String.valueOf(guzergah_point.getNOT_ID())));
            Log.v("nott id", "refactor=>" + guzergah_point.getNOT_ID());
        }


        if (guzergah_point.getX_KOOR() != null && guzergah_point.getY_KOOR() != null) {
            Double enlem = Double.valueOf(guzergah_point.getX_KOOR());
            Double boylam = Double.valueOf(guzergah_point.getY_KOOR());
            Double x = 0.0;
            Double y = 0.0;
            Log.v("enlem", "==>" + String.valueOf(enlem));
            Log.v("boylam", "==>" + String.valueOf(boylam));
            if (enlem != null && boylam != null) {
                x = enlem;
                y = boylam;
            }

            //  Point point_1 = new Point();
            //point_1.setX(x);
            // point_1.setY(y);
            // Point send_point_geometry = utm_to_webmeter(point_1);


            Geometry geometry = new Geometry();
            //   geometry.x = x;
            //  geometry.y = y;
            geometry.x = x;
            geometry.y = y;
            feature.geometry = geometry;
        }

        return feature;
    }


    public static List<EntityLayer.GeoPortal.GfeatureWithList> convertFeatureListFromMobilGuzergah(List<MobilGuzergah> datalist, OrtakNot not, List<YolBilgi> yolBilgiList) {
        List<EntityLayer.GeoPortal.GfeatureWithList> fList = new ArrayList<EntityLayer.GeoPortal.GfeatureWithList>();
        Log.v("feature refactor1", "=>datalist size=" + datalist.size());
        if (yolBilgiList.size() == 0)
            return fList;

        for(int i = 0;i<yolBilgiList.size();i++) {
            EntityLayer.GeoPortal.GfeatureWithList feature = new EntityLayer.GeoPortal.GfeatureWithList();
         /*   if (guzergah.getY_KOOR()!=null)
            {
                feature.attributes.put("Y_KOOR",guzergah.getY_KOOR());
            }
            if (guzergah.getX_KOOR()!=null)
            {
                feature.attributes.put("X_KOOR",guzergah.getX_KOOR());
            }
            */

            feature.attributes.put("MID", not.getMid());
            feature.attributes.put("YOL_ADI", not.getYolAdi());
            feature.attributes.put("YOL_KODU", not.getYolKod());
            feature.attributes.put("KISI_ID", OrtakFunction.kullanici_id);
            feature.attributes.put("LAST_EDITED_USER", OrtakFunction.kullanici_id);
            feature.attributes.put("CREATED_USER", OrtakFunction.kullanici_id);
            feature.attributes.put("TEMP1", null);
            feature.attributes.put("TEMP2", null);
            feature.attributes.put("CREATED_DATE", new Date());
            feature.attributes.put("LAST_EDITED_DATE", new Date());

            feature.attributes.put("YOL_TIPI", yolBilgiList.get(i).getYOL_TIPI());

            feature.attributes.put("YOL_BILGI_ID", Long.valueOf(yolBilgiList.get(i).getId()));
            feature.attributes.put("YOL_DURUMU", yolBilgiList.get(i).getIslemDurumu());
            feature.attributes.put("TUL", yolBilgiList.get(i).getTUL());
            feature.attributes.put("SIRA_NO", yolBilgiList.get(i).getSIRA_NO());
            feature.attributes.put("YOL_BASLANGIC_X", yolBilgiList.get(i).getYOL_BASLANGIC_X());
            feature.attributes.put("YOL_BASLANGIC_Y", yolBilgiList.get(i).getYOL_BASLANGIC_Y());
            feature.attributes.put("YOL_BITIS_X", yolBilgiList.get(i).getYOL_BITIS_X());
            feature.attributes.put("YOL_BITIS_Y", yolBilgiList.get(i).getYOL_BITIS_Y());
            feature.attributes.put("YOL_BASLANGIC_UTM_X", yolBilgiList.get(i).getYOL_BASLANGIC_UTM_X());
            feature.attributes.put("YOL_BASLANGIC_UTM_Y", yolBilgiList.get(i).getYOL_BASLANGIC_UTM_Y());
            feature.attributes.put("YOL_BITIS_UTM_X", yolBilgiList.get(i).getYOL_BITIS_UTM_X());
            feature.attributes.put("YOL_BITIS_UTM_Y", yolBilgiList.get(i).getYOL_BITIS_UTM_Y());


            Log.v("feature refactor2", "=>");
           /* if (datalist.get(0).getOBJECTID() != null) {
                feature.attributes.put("OBJECTID", datalist.get(0).getOBJECTID());
            }
            Log.v("feature refactor3", "=>");
            if (datalist.get(0).getGLOBALID() != null) {
                feature.attributes.put("GLOBALID", datalist.get(0).getGLOBALID());
            }
            */

            if (OrtakFunction.vip_user_list.contains(OrtakFunction.kullanici_adi)) {
                Log.v("BIRIM_ID", "=>" + OrtakFunction.admine_ozel_birim_id);
                feature.attributes.put("BIRIM_ID", not.getBirimId());
            } else {
                Log.v("BIRIM_ID", "=>" + OrtakFunction.birim_id);
                feature.attributes.put("BIRIM_ID", not.getBirimId());
            }

            if (not.getNotAciklama() != null) {
                feature.attributes.put("ACIKLAMA", not.getNotAciklama());
            }
            /*if (datalist.get(0).getNOT_ID() != null) {
                feature.attributes.put("NOT_ID", Integer.valueOf(String.valueOf(datalist.get(0).getNOT_ID())));
                Log.v("nott id","refactor=>"+ datalist.get(0).getNOT_ID());
            }*/
            if (not.getId() != null) {
                feature.attributes.put("YOL_ID", not.getId());
            }
            //if (datalist.get(0).getTUL() != null) {
            //    feature.attributes.put("TUL", datalist.get(0).getTUL());
           // }


            Log.v("feature refactor4", "=>");


            List<Double[]> arrayList = new ArrayList<Double[]>();
            List<List<Double[]>> ana_arrayList = new ArrayList<List<Double[]>>();
            for (MobilGuzergah guzergah_ : yolBilgiList.get(i).getGuzerhList()) {
                Double[] doubleArray = new Double[2];
                doubleArray[0] = Double.valueOf(guzergah_.getX_KOOR());
                doubleArray[1] = Double.valueOf(guzergah_.getY_KOOR());
                arrayList.add(doubleArray);
                Log.v("geo array added", "=>" + guzergah_.getX_KOOR() + "-" + guzergah_.getY_KOOR());
            }
            ana_arrayList.add(arrayList);
            feature.geometry.put("paths", ana_arrayList);


          /*  for (MobilGuzergah guzergah_ : datalist) {
                Geometry geometry = new Geometry();
                if(guzergah_.getX_KOOR() != null && guzergah_.getY_KOOR() != null) {
                    geometry.x =Double.valueOf(guzergah_.getX_KOOR());
                    geometry.y = Double.valueOf(guzergah_.getY_KOOR());
                    feature.geometry(geometry);
                }
            }
            */

            Log.v("feature refactor5", "=>");

            fList.add(feature);
            Log.v("geometry eklendi", "=>" + feature.geometry.get(0));
        }

        return fList;
    }


}