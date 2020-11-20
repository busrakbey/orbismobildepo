package ToolLayer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.konumsal.orbisozetmobil.OrtakUI.LogLarge;

import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import EntityLayer.ArcGIS.MqueryResult;
import EntityLayer.GeoPortal.AddResult;
import EntityLayer.GeoPortal.DeleteOperationResult;
import EntityLayer.GeoPortal.DeleteResult;
import EntityLayer.GeoPortal.Error;
import EntityLayer.GeoPortal.Feature;
import EntityLayer.GeoPortal.Gfeature;
import EntityLayer.GeoPortal.GfeatureWithList;
import EntityLayer.GeoPortal.InsertOperationResult;
import EntityLayer.GeoPortal.Mfeature;
import EntityLayer.GeoPortal.OperationResult;
import EntityLayer.GeoPortal.QueryResult;
import EntityLayer.GeoPortal.UpdateOperationResult;
import EntityLayer.Ortak.BaseEntity;

/**
 * Created by Ömer YILDIRIM on 27.4.2016.
 */
public class ArcGisOperator
{
    private String AcquireArcGISToken2(String restUrl, String username, String password) throws Exception {
        String arcGISToken = "";

        if (password == null) {
           // SKullanici kullanici = SKullaniciDao.instance().findByKullanici(username);
           // password = kullanici.getSifre();
        }
        //		password = "admin";//TODO remove
        String tokensUrl = restUrl.replace("rest/services", "tokens");
        //String uri = tokensUrl + "?f=json&request=gettoken&clientid=requestip&username=" + username + "&password=" + password + "&expiration=1440";
        HttpURLConnection conn = (HttpURLConnection) new URL(tokensUrl).openConnection();
        try {
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("f", "json");
            params.put("request", "gettoken");
            params.put("clientid", "requestip");
            params.put("username", username);
            params.put("password", password);
            params.put("expiration", "1440");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            InputStream is = conn.getInputStream();
            StringBuffer json = new StringBuffer();
            byte[] buffer = new byte[8192];
            int read = 0;
            while ((read = is.read(buffer)) > 0)
                json.append(new String(buffer, 0, read, "UTF-8"));
            is.close();
            JsonObject jsonResponse = new JsonParser().parse(json.toString()).getAsJsonObject();
            if (jsonResponse.has("token"))
                arcGISToken = jsonResponse.get("token").getAsString();
        } catch (Exception ex) {
            //getLogger().error("Cant get token from " + tokensUrl, ex);
            arcGISToken = "";
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return arcGISToken;
    }

    private void AcquireArcGISToken1(String username,String password, String restUrl) throws Exception {
        //String username = user.getUsername();
        //String password = user.getPassword();
        String arcGISToken = AcquireArcGISToken(restUrl, username, password);

        //Application.getInstance().getSessionService().put(OrtakConst.ESRIPARAM_TOKEN, arcGISToken);
    }
    private String AcquireArcGISToken(String restUrl, String username, String password) throws Exception {
        String arcGISToken = "";

        if (password == null) {
           // SKullanici kullanici = SKullaniciDao.instance().findByKullanici(username);
           // password = kullanici.getSifre();
        }
        //		password = "admin";//
        String tokensUrl ="";// restUrl.replace("rest/services", "tokens");
        tokensUrl="https://orbiscbs.ogm.gov.tr/arcgis/tokens/generateToken";
        //String uri = tokensUrl + "?f=json&request=gettoken&clientid=requestip&username=" + username + "&password=" + password + "&expiration=1440";
        HttpURLConnection conn = (HttpURLConnection) new URL(tokensUrl).openConnection();
        try {
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("f", "json");
            params.put("request", "gettoken");
            params.put("clientid", "requestip");
            params.put("username", username);
            params.put("password", password);
            params.put("expiration", "1440");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            InputStream is = conn.getInputStream();
            StringBuffer json = new StringBuffer();
            byte[] buffer = new byte[8192];
            int read = 0;
            while ((read = is.read(buffer)) > 0)
                json.append(new String(buffer, 0, read, "UTF-8"));
            is.close();
            JsonObject jsonResponse = new JsonParser().parse(json.toString()).getAsJsonObject();
            if (jsonResponse.has("token"))
                arcGISToken = jsonResponse.get("token").getAsString();
        } catch (Exception ex) {
            //getLogger().error("Cant get token from " + tokensUrl, ex);
            arcGISToken = "";
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return arcGISToken;
    }

    public MqueryResult GetAGSVersionedData(String username, String password, String restUrl, Integer objectClassId, String versionName, String whereClause) throws Exception {
        String arcGISToken = AcquireArcGISToken(restUrl, username, password);
        MqueryResult queryResult = null;
        String queryUrl = restUrl;

        boolean getNext = true;
        int offsetRange = 1000;
        int offset = 0;
        while (getNext) {
            HttpURLConnection conn = (HttpURLConnection) new URL(queryUrl).openConnection();
            try {
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setReadTimeout(10000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("f", "json");
                if (arcGISToken != null && arcGISToken.isEmpty() == false)
                    params.put("token", arcGISToken);
                params.put("returnGeometry", "false");
                if (versionName != null && versionName.isEmpty() == false)
                {
                    String vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                    params.put("gdbVersion",vrsAd);
                }
                params.put("outFields", "*");
                if (whereClause == null || whereClause.isEmpty())
                    whereClause = "1=1";
                params.put("where", whereClause);
                params.put("resultOffset", Integer.toString(offset));
                params.put("maxRecordCount", Integer.toString(offsetRange));

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();

                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                String jsonString = json.toString();

                JsonObject jsonResponse = new JsonParser().parse(jsonString).getAsJsonObject();
                if (jsonResponse.has("exceededTransferLimit"))
                {
                    boolean exceeded = jsonResponse.get("exceededTransferLimit").getAsBoolean();
                    if (exceeded) {
                        offset += offsetRange;

                    }
                    else
                        getNext = false;
                }
                else if (jsonResponse.has("error"))
                {
                    getNext = false;
                } else
                {
                    getNext = false;
                }
                Gson gson = new Gson();
                MqueryResult tempResult = gson.fromJson(jsonString, MqueryResult.class);
                if (queryResult == null)
                    queryResult = tempResult;
                else
                    queryResult.features.addAll(tempResult.features);
            } catch (Exception ex) {
                //getLogger().error("Cant get token from " + queryUrl, ex);
                arcGISToken = "";
                getNext = false;
                queryResult = null;
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
        }
        return queryResult;
    }

    public int GetFeatureOID(String arcGISToken, String queryUrl, int kkyId) {
        HttpURLConnection conn = null;
        int oid = -1;
        try {
            conn = (HttpURLConnection) new URL(queryUrl).openConnection();
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("f", "json");
            if (arcGISToken != null && arcGISToken.isEmpty() == false)
                params.put("token", arcGISToken);
            params.put("returnGeometry", "false");
            params.put("outFields", "OBJECTID");
            params.put("where", "ID=" + kkyId);
            params.put("maxRecordCount", "1");

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();

            InputStream is = conn.getInputStream();
            StringBuffer json = new StringBuffer();
            byte[] buffer = new byte[8192];
            int read = 0;
            while ((read = is.read(buffer)) > 0)
                json.append(new String(buffer, 0, read, "UTF-8"));
            is.close();
            String jsonString = json.toString();

            JsonObject jsonResponse = new JsonParser().parse(jsonString).getAsJsonObject();
            Gson gson = new Gson();
            QueryResult result = gson.fromJson(jsonString, QueryResult.class);

        } catch (Exception ex) {
            //getLogger().error("Cant get token from " + queryUrl, ex);
            arcGISToken = "";
        } finally {
            if (conn != null)
                conn.disconnect();
        }
        return oid;
    }

    public OperationResult UpdateGISFeature(String username, String password, String restUrl, Integer objectClassId, int kkyId, Feature feature) throws Exception {
		/*Usage
		Feature f = new Feature();
		f.attributes.put("LAB_ADI", "DENEME1112");
		f.attributes.put("LAB_ID", "1112");
		OperationResult opRes = UpdateGISFeature("siteadmin", "orbisadmin?!", "http://cbsorbis.ogm.gov.tr/arcgis/rest/services/", 0, 41, f);
		if(opRes.success)
		{
		}
		else{
		OperationError error = opRes.error;
		}
		*/

        String arcGISToken = AcquireArcGISToken(restUrl, username, password);
        UpdateOperationResult updateOperationResult = null;
        OperationResult operationResult = new OperationResult();
        String queryUrl = restUrl + "OGM_MODULLER/MapServer/" + objectClassId + "/query";
        int oid = GetFeatureOID(arcGISToken, queryUrl, kkyId);
        if (oid > -1) {
            //feature.attributes.put("OBJECTID", oid);
            String updateUrl = restUrl + "OGM_MODULLER/FeatureServer/" + objectClassId + "/updateFeatures";

            Gson gson = new Gson();
            HttpURLConnection conn = (HttpURLConnection) new URL(updateUrl).openConnection();
            try {
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setReadTimeout(10000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("f", "json");
                if (arcGISToken != null && arcGISToken.isEmpty() == false)
                    params.put("token", arcGISToken);
                List<Feature> features = new ArrayList<Feature>();
                features.add(feature);
                String jsonFeatures = gson.toJson(features);
                params.put("features", jsonFeatures);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();

                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                String jsonString = json.toString();
                updateOperationResult = gson.fromJson(jsonString, UpdateOperationResult.class);
                if (updateOperationResult.updateResults.size() == 1)
                    operationResult = updateOperationResult.updateResults.get(0);
            } catch (Exception ex) {
               // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                updateOperationResult = new UpdateOperationResult();
                Error error = new Error();
                error.description = ex.getMessage();
                error.message =ex.getMessage();
                updateOperationResult.error =error;
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
        }
        else
            throw new Exception("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        return operationResult;
    }

    private String getQuery(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (String key : params.keySet())
        {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(params.get(key), "UTF-8"));
        }
        LogLarge.v("GET QUERY APPEND","=>"+result.toString());
        return result.toString();
    }




    public UpdateOperationResult EditFeatureForUpdateGeoOA(String username, String password, String restUrl, List<Gfeature> feature, String versionName) throws Exception {


        String arcGISToken = AcquireArcGISToken(restUrl, username, password);
        UpdateOperationResult updateOperationResult = null;

        int oid =1;// GetFeatureOID(arcGISToken, queryUrl, kkyId);
        if (oid > -1)
        {
            updateOperationResult = new UpdateOperationResult();
            Gson gson = new Gson();
            HttpURLConnection conn = (HttpURLConnection) new URL(restUrl).openConnection();
            try {
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setReadTimeout(10000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("f", "json");
                if (arcGISToken != null && arcGISToken.isEmpty() == false)
                    params.put("token", arcGISToken);
                String jsonFeatures = gson.toJson(feature);
                params.put("features", jsonFeatures);
                String vrsAd;
                if(!versionName.contains("CBSORBIS"))
                    vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                else
                    vrsAd = versionName;
                params.put("gdbVersion",vrsAd);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();

                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                String jsonString = json.toString();
                Log.v("UPDATE OA JSON","=>"+jsonString);
                updateOperationResult = gson.fromJson(jsonString, UpdateOperationResult.class);

            } catch (Exception ex) {
                // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                updateOperationResult = new UpdateOperationResult();
                Error error = new Error();
                error.message = ex.toString();
                error.description =ex.toString();
                updateOperationResult.error =error;
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
        }
        else
            throw new Exception("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        return updateOperationResult;
    }



    public UpdateOperationResult EditFeature(String username, String password, String restUrl, List<Mfeature> feature, String versionName) throws Exception {
		/*Usage
		Feature f = new Feature();
		f.attributes.put("LAB_ADI", "DENEME1112");
		f.attributes.put("LAB_ID", "1112");
		OperationResult opRes = UpdateGISFeature("siteadmin", "orbisadmin?!", "http://cbsorbis.ogm.gov.tr/arcgis/rest/services/", 0, 41, f);
		if(opRes.success)
		{
		}
		else{
		OperationError error = opRes.error;
		}
		{"error":{"code":400,"message":"Unable to complete operation.","details":["Object is Missing.","Object is Missing.","Object is Missing.","Object is Missing."]}}
		*/

        String arcGISToken = AcquireArcGISToken(restUrl, username, password);
        UpdateOperationResult updateOperationResult = null;

        int oid =1;// GetFeatureOID(arcGISToken, queryUrl, kkyId);
        if (oid > -1)
        {
            updateOperationResult = new UpdateOperationResult();
            Gson gson = new Gson();
            HttpURLConnection conn = (HttpURLConnection) new URL(restUrl).openConnection();
            try {
                conn.setUseCaches(false);
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setReadTimeout(10000);
                conn.setDoInput(true);
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("f", "json");
                if (arcGISToken != null && arcGISToken.isEmpty() == false)
                    params.put("token", arcGISToken);
                String jsonFeatures = gson.toJson(feature);
                params.put("features", jsonFeatures);
                String vrsAd;
                if(!versionName.contains("CBSORBIS"))
                    vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                else
                    vrsAd = versionName;
                params.put("gdbVersion",vrsAd);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getQuery(params));
                writer.flush();
                writer.close();
                os.close();

                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                String jsonString = json.toString();
                Log.v("UPDATE OA JSON","=>"+jsonString);
                updateOperationResult = gson.fromJson(jsonString, UpdateOperationResult.class);

            } catch (Exception ex) {
                // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                updateOperationResult = new UpdateOperationResult();
                 Error error = new Error();
                error.message = ex.toString();
                error.description =ex.toString();
                updateOperationResult.error =error;
            } finally {
                if (conn != null)
                    conn.disconnect();
            }
        }
        else
            throw new Exception("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        return updateOperationResult;
    }


    public InsertOperationResult InsertFeaturesMobilGuzergahPointOnly(List<Gfeature> features, String userName, String password, String restUrl, String versionName) throws OrbisDefaultException
    {
        /*
        * [{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"4","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}},{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"2","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}}]*/
        HttpURLConnection conn=null;
        InsertOperationResult operationResult = null;
        try {
            String arcGISToken = AcquireArcGISToken(restUrl, userName, password);
            operationResult = new InsertOperationResult();

            int oid =1;// GetFeatureOID(arcGISToken, queryUrl, kkyId);
            if (oid > -1)
            {
                try {
                    //feature.attributes.put("OBJECTID", oid);
                    // String updateUrl = restUrl + "OGM_MODULLER/FeatureServer/" + objectClassId + "/updateFeatures";

                    Gson gson = new Gson();
                    conn = (HttpURLConnection) new URL(restUrl).openConnection();
                    try {

                        conn.setUseCaches(false);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setReadTimeout(10000);
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("f", "json");
                        if (arcGISToken != null && arcGISToken.isEmpty() == false)
                            params.put("token", arcGISToken);

                        String jsonFeatures = gson.toJson(features);
                        params.put("features", jsonFeatures);
                        String vrsAd;
                        if(!versionName.contains("CBSORBIS"))
                            vrsAd ="CBSORBIS."+versionName+"_MOBIL_GUZERGAH";
                        else
                            vrsAd = versionName;
                        params.put("gdbVersion",vrsAd);
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getQuery(params));
                        writer.flush();
                        writer.close();
                        os.close();

                        InputStream is = conn.getInputStream();
                        StringBuffer json = new StringBuffer();
                        byte[] buffer = new byte[8192];
                        int read = 0;
                        while ((read = is.read(buffer)) > 0)
                            json.append(new String(buffer, 0, read, "UTF-8"));
                        is.close();
                        String jsonString = json.toString();
                        Log.v("INSERT OA JSON","=>"+jsonString);
                        operationResult = gson.fromJson(jsonString, InsertOperationResult.class);

                    } catch (Exception ex) {
                        // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                        AddResult or = new AddResult();
                        or.success = false;
                        or.error = new Error();
                        or.error.description = ex.getMessage();
                        operationResult.addResults.add(or);
                        operationResult.addResults.add(or);
                    } finally {
                        if (conn != null)
                            conn.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    AddResult or = new AddResult();
                    or.success = false;
                    or.error = new Error();
                    or.error.description = ex.getMessage();
                    operationResult.addResults.add(or);
                    operationResult.addResults.add(or);
                    throw new OrbisDefaultException("Entegrasyon hatası"+ ex.toString());
                }
            }
            else
                throw new OrbisDefaultException("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
            AddResult or = new AddResult();
            or.success = false;
            or.error = new Error();
            or.error.description = e.getMessage();
            operationResult.addResults.add(or);
            operationResult.addResults.add(or);
            throw new OrbisDefaultException("Entegrasyon hatası"+ e.toString());
        }
        return operationResult;

    }



    public InsertOperationResult InsertFeaturesMobilGuzergah(List<GfeatureWithList> features, String userName, String password, String restUrl, String versionName) throws OrbisDefaultException
    {
        /*
        * [{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"4","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}},{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"2","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}}]*/
        HttpURLConnection conn=null;
        InsertOperationResult operationResult = null;
        try {
            String arcGISToken = AcquireArcGISToken(restUrl, userName, password);
            operationResult = new InsertOperationResult();

            int oid =1;// GetFeatureOID(arcGISToken, queryUrl, kkyId);
            if (oid > -1)
            {
                try {
                    //feature.attributes.put("OBJECTID", oid);
                    // String updateUrl = restUrl + "OGM_MODULLER/FeatureServer/" + objectClassId + "/updateFeatures";

                    Gson gson = new Gson();
                    conn = (HttpURLConnection) new URL(restUrl).openConnection();
                    try {

                        conn.setUseCaches(false);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setReadTimeout(10000);
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("f", "json");
                        if (arcGISToken != null && arcGISToken.isEmpty() == false)
                            params.put("token", arcGISToken);
                        Log.v("argic token","=>"+arcGISToken);

                        String jsonFeatures = gson.toJson(features);
                        Log.v("json feature","=>"+jsonFeatures);
                        params.put("features", jsonFeatures);
                        String vrsAd ="CBSORBIS."+versionName+"_YOL_MOBIL";
                        Log.v("version ad","=>"+vrsAd);
                        params.put("gdbVersion",vrsAd);
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getQuery(params));
                        writer.flush();
                        writer.close();
                        os.close();

                        InputStream is = conn.getInputStream();
                        StringBuffer json = new StringBuffer();
                        byte[] buffer = new byte[8192];
                        int read = 0;
                        while ((read = is.read(buffer)) > 0)
                            json.append(new String(buffer, 0, read, "UTF-8"));
                        is.close();
                        String jsonString = json.toString();
                        Log.v("mobil huzergah JSON","=>"+jsonString);
                        operationResult = gson.fromJson(jsonString, InsertOperationResult.class);

                    } catch (Exception ex) {
                        // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                        AddResult or = new AddResult();
                        or.success = false;
                        or.error = new Error();
                        or.error.description = ex.getMessage();
                        operationResult.addResults.add(or);
                        operationResult.addResults.add(or);
                    } finally {
                        if (conn != null)
                            conn.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    AddResult or = new AddResult();
                    or.success = false;
                    or.error = new Error();
                    or.error.description = ex.getMessage();
                    operationResult.addResults.add(or);
                    operationResult.addResults.add(or);
                    throw new OrbisDefaultException("Entegrasyon hatası"+ ex.toString());
                }
            }
            else
                throw new OrbisDefaultException("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
            AddResult or = new AddResult();
            or.success = false;
            or.error = new Error();
            or.error.description = e.getMessage();
            operationResult.addResults.add(or);
            operationResult.addResults.add(or);
            throw new OrbisDefaultException("Entegrasyon hatası"+ e.toString());
        }
        return operationResult;

    }




    public InsertOperationResult InsertFeatures(List<Mfeature> features, String userName, String password, String restUrl, String versionName) throws OrbisDefaultException
    {
        /*
        * [{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"4","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}},{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"2","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}}]*/
        HttpURLConnection conn=null;
        InsertOperationResult operationResult = null;
        try {
            String arcGISToken = AcquireArcGISToken(restUrl, userName, password);
            operationResult = new InsertOperationResult();

            int oid =1;// GetFeatureOID(arcGISToken, queryUrl, kkyId);
            if (oid > -1)
            {
                try {
                    //feature.attributes.put("OBJECTID", oid);
                    // String updateUrl = restUrl + "OGM_MODULLER/FeatureServer/" + objectClassId + "/updateFeatures";

                    Gson gson = new Gson();
                    conn = (HttpURLConnection) new URL(restUrl).openConnection();
                    try {

                        conn.setUseCaches(false);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setReadTimeout(10000);
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("f", "json");
                        if (arcGISToken != null && arcGISToken.isEmpty() == false)
                            params.put("token", arcGISToken);

                        String jsonFeatures = gson.toJson(features);
                        params.put("features", jsonFeatures);
                        String vrsAd;
                        if(!versionName.contains("CBSORBIS"))
                            vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                        else
                            vrsAd = versionName;
                        params.put("gdbVersion",vrsAd);
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getQuery(params));
                        writer.flush();
                        writer.close();
                        os.close();

                        InputStream is = conn.getInputStream();
                        StringBuffer json = new StringBuffer();
                        byte[] buffer = new byte[8192];
                        int read = 0;
                        while ((read = is.read(buffer)) > 0)
                            json.append(new String(buffer, 0, read, "UTF-8"));
                        is.close();
                        String jsonString = json.toString();
                        operationResult = gson.fromJson(jsonString, InsertOperationResult.class);

                    } catch (Exception ex) {
                        // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                        AddResult or = new AddResult();
                        or.success = false;
                        or.error = new Error();
                        or.error.description = ex.getMessage();
                        operationResult.addResults.add(or);
                        operationResult.addResults.add(or);
                    } finally {
                        if (conn != null)
                            conn.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    AddResult or = new AddResult();
                    or.success = false;
                    or.error = new Error();
                    or.error.description = ex.getMessage();
                    operationResult.addResults.add(or);
                    operationResult.addResults.add(or);
                    throw new OrbisDefaultException("Entegrasyon hatası"+ ex.toString());
                }
            }
            else
                throw new OrbisDefaultException("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
            AddResult or = new AddResult();
            or.success = false;
            or.error = new Error();
            or.error.description = e.getMessage();
            operationResult.addResults.add(or);
            operationResult.addResults.add(or);
            throw new OrbisDefaultException("Entegrasyon hatası"+ e.toString());
        }
        return operationResult;

    }





    public InsertOperationResult InsertNOKTAFeatures(List<Gfeature> features, String userName, String password, String restUrl, String versionName) throws OrbisDefaultException
    {
        /*
        * [{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"4","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}},{"attributes":{"SILVIKULTUR_DURUM":3,"KALITE_SINIFI":"2","CAP":31,"AGAC_TUR_KOD":"Sarıçam","AGAC_TUR_ID":1,"ALAN_KARNE_ID":700}}]*/
        HttpURLConnection conn=null;
        InsertOperationResult operationResult = null;
        try {
            String arcGISToken = AcquireArcGISToken(restUrl, userName, password);
            operationResult = new InsertOperationResult();

            int oid =1;// GetFeatureOID(arcGISToken, queryUrl, kkyId);
            if (oid > -1)
            {
                try {
                    //feature.attributes.put("OBJECTID", oid);
                    // String updateUrl = restUrl + "OGM_MODULLER/FeatureServer/" + objectClassId + "/updateFeatures";

                    Gson gson = new Gson();
                    conn = (HttpURLConnection) new URL(restUrl).openConnection();
                    try {

                        conn.setUseCaches(false);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setReadTimeout(10000);
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("f", "json");
                        if (arcGISToken != null && arcGISToken.isEmpty() == false)
                            params.put("token", arcGISToken);

                        String jsonFeatures = gson.toJson(features);
                        params.put("features", jsonFeatures);
                        String vrsAd;
                        if(!versionName.contains("CBSORBIS"))
                            vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                        else
                            vrsAd = versionName;
                        params.put("gdbVersion",vrsAd);
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getQuery(params));
                        writer.flush();
                        writer.close();
                        os.close();

                        InputStream is = conn.getInputStream();
                        StringBuffer json = new StringBuffer();
                        byte[] buffer = new byte[8192];
                        int read = 0;
                        while ((read = is.read(buffer)) > 0)
                            json.append(new String(buffer, 0, read, "UTF-8"));
                        is.close();
                        String jsonString = json.toString();
                        Log.v("INSERT OA JSON","=>"+jsonString);
                        operationResult = gson.fromJson(jsonString, InsertOperationResult.class);

                    } catch (Exception ex) {
                        // getLogger().error("Update İşlemi yapılamadı : " + updateUrl, ex);
                        AddResult or = new AddResult();
                        or.success = false;
                        or.error = new Error();
                        or.error.description = ex.getMessage();
                        operationResult.addResults.add(or);
                        operationResult.addResults.add(or);
                    } finally {
                        if (conn != null)
                            conn.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    AddResult or = new AddResult();
                    or.success = false;
                    or.error = new Error();
                    or.error.description = ex.getMessage();
                    operationResult.addResults.add(or);
                    operationResult.addResults.add(or);
                    throw new OrbisDefaultException("Entegrasyon hatası"+ ex.toString());
                }
            }
            else
                throw new OrbisDefaultException("Entegrasyon IDsine Karşılık gelen CBS Verisi bulunamadı.");
        } catch (Exception e) {
            e.printStackTrace();
            AddResult or = new AddResult();
            or.success = false;
            or.error = new Error();
            or.error.description = e.getMessage();
            operationResult.addResults.add(or);
            operationResult.addResults.add(or);
            throw new OrbisDefaultException("Entegrasyon hatası"+ e.toString());
        }
        return operationResult;

    }
    public<T extends BaseEntity> DeleteOperationResult deleteFeatures(List<T> features, String userName, String password, String restUrl, String versionName) throws OrbisDefaultException
    {
        DeleteOperationResult deleteOperationResult = null;
        HttpURLConnection conn=null;
        try {
            String arcGISToken = AcquireArcGISToken(restUrl, userName, password);
             deleteOperationResult = new DeleteOperationResult();

                try {
                    //feature.attributes.put("OBJECTID", oid);
                    // String updateUrl = restUrl + "OGM_MODULLER/FeatureServer/" + objectClassId + "/updateFeatures";

                    Gson gson = new Gson();
                    conn = (HttpURLConnection) new URL(restUrl).openConnection();
                    try {

                        conn.setUseCaches(false);
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setReadTimeout(10000);
                        conn.setDoInput(true);
                        conn.setDoOutput(true);
                        conn.setRequestMethod("POST");

                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("f", "json");
                        if (arcGISToken != null && arcGISToken.isEmpty() == false)
                            params.put("token", arcGISToken);

                        String jsonFeatures = getObjectIDs(features);
                        params.put("objectIds", jsonFeatures);
                        String vrsAd;
                        if(!versionName.contains("CBSORBIS"))
                            vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                        else
                            vrsAd = versionName;
                        params.put("gdbVersion",vrsAd);
                        OutputStream os = conn.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        writer.write(getQuery(params));
                        writer.flush();
                        writer.close();
                        os.close();

                        InputStream is = conn.getInputStream();
                        StringBuffer json = new StringBuffer();
                        byte[] buffer = new byte[8192];
                        int read = 0;
                        while ((read = is.read(buffer)) > 0)
                            json.append(new String(buffer, 0, read, "UTF-8"));
                        is.close();
                        String jsonString = json.toString();
                        deleteOperationResult = gson.fromJson(jsonString, DeleteOperationResult.class);

                    } catch (Exception ex) {
                        DeleteResult or = new DeleteResult();
                        or.success = false;
                        or.error = new Error();
                        or.error.description = ex.getMessage();
                        deleteOperationResult.deleteResults.add(or);

                    } finally {
                        if (conn != null)
                            conn.disconnect();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    DeleteResult or = new DeleteResult();
                    or.success = false;
                    or.error = new Error();
                    or.error.description = ex.getMessage();
                    deleteOperationResult.deleteResults.add(or);
                    throw new OrbisDefaultException("Entegrasyon hatası"+ ex.toString());
                }
        } catch (Exception e) {
            e.printStackTrace();
            DeleteResult or = new DeleteResult();
            or.success = false;
            or.error = new Error();
            or.error.description = e.getMessage();
            deleteOperationResult.deleteResults.add(or);
            throw new OrbisDefaultException("Entegrasyon hatası"+ e.toString());
        }
        return deleteOperationResult;

    }

    public JsonObject   queryTask(String url,String wheree, String userName, String password, String versionName) throws OrbisDefaultException
    {
        JsonObject jsonResponse=null;
        HttpURLConnection conn = null;
        int oid = -1;
        try {
            String arcGISToken = AcquireArcGISToken("", userName, password);
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("f", "json");
            params.put("returnGeometry", "false");
            params.put("outFields", "*");
            params.put("resultOffset","0");
            params.put("resultRecordCount","2000");
            params.put("where", wheree);
            if (arcGISToken != null && arcGISToken.isEmpty() == false)
                params.put("token", arcGISToken);
            if (versionName != null && versionName.isEmpty() == false)
            {
                String vrsAd;
                if(!versionName.contains("CBSORBIS"))
                    vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                else
                    vrsAd = versionName;

                params.put("gdbVersion",vrsAd);
            }

            //params.put("maxRecordCount", "1");
	/*
	//Related için
			params.put("returnGeometry", "false");//Geometri dönecekse true
			params.put("outFields", "OBJECTID");//Tüm fieldları almak için *
			params.put("objectids", "680,681,682");//Örnek objectid list
			params.put("relationshipId", "1");//Relationship Tablo Id si 0,1,2,3,4 her biri için ayrı sorgu
yapılacak
			params.put("gdbVersion", "sinanbilge_alanenvanteri");//Versiyon adı yazılmazsa default dan çeker
	*/


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
            if (conn.getResponseCode()==200)
            {
                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                Log.v("ARCGIS PLAN JSON","=>"+json);
                 jsonResponse = new JsonParser().parse(json.toString()).getAsJsonObject();
            }else
            {
                throw new OrbisDefaultException("Htpp error"+conn.getResponseMessage());
            }

        }  catch (JsonSyntaxException e) {
            Log.e("arcgisOprtr",e.toString());
            throw new OrbisDefaultException(e.toString());
        }catch (Exception ex) {
            Log.e("arcgisOprtr",ex.toString());
            throw new OrbisDefaultException(ex.toString());
        } catch (Throwable ex) {
            Log.e("arcgisOprtr",ex.toString());
            throw new OrbisDefaultException(ex.toString());
        }finally {
            if (conn != null)
                conn.disconnect();
        }
       return jsonResponse;
    }




    public JsonObject   queryTaskNew(String url,String wheree, String userName, String password, String versionName , String service_name) throws OrbisDefaultException
    {
        JsonObject jsonResponse=null;
        HttpURLConnection conn = null;
        int oid = -1;
        try {
            String arcGISToken = AcquireArcGISToken("", userName, password);
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("f", "json");
            params.put("returnGeometry", "true");
            params.put("outFields", "*");
            params.put("resultOffset","0");
            params.put("resultRecordCount","2000");
            params.put("where", wheree);
            if (arcGISToken != null && arcGISToken.isEmpty() == false)
                params.put("token", arcGISToken);
            if (versionName != null && versionName.isEmpty() == false)
            {
                String vrsAd ="CBSORBIS."+versionName+"_"+service_name;
                params.put("gdbVersion",vrsAd);
            }

            //params.put("maxRecordCount", "1");
	/*
	//Related için
			params.put("returnGeometry", "false");//Geometri dönecekse true
			params.put("outFields", "OBJECTID");//Tüm fieldları almak için *
			params.put("objectids", "680,681,682");//Örnek objectid list
			params.put("relationshipId", "1");//Relationship Tablo Id si 0,1,2,3,4 her biri için ayrı sorgu
yapılacak
			params.put("gdbVersion", "sinanbilge_alanenvanteri");//Versiyon adı yazılmazsa default dan çeker
	*/


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
            if (conn.getResponseCode()==200)
            {
                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                jsonResponse = new JsonParser().parse(json.toString()).getAsJsonObject();
            }else
            {
                throw new OrbisDefaultException("Htpp error"+conn.getResponseMessage());
            }

        }  catch (JsonSyntaxException e) {
            Log.e("arcgisOprtr",e.toString());
            throw new OrbisDefaultException(e.toString());
        }catch (Exception ex) {
            Log.e("arcgisOprtr",ex.toString());
            throw new OrbisDefaultException(ex.toString());
        } catch (Throwable ex) {
            Log.e("arcgisOprtr",ex.toString());
            throw new OrbisDefaultException(ex.toString());
        }finally {
            if (conn != null)
                conn.disconnect();
        }
        return jsonResponse;
    }



    public JsonObject   queryTaskForTakeInfoFromCoordinate(String url,String input_geometry,String wheree, String userName, String password, String versionName , String service_name) throws OrbisDefaultException
    {
        JsonObject jsonResponse=null;
        HttpURLConnection conn = null;
        int oid = -1;
        try {
            String arcGISToken = AcquireArcGISToken(url, userName, password);
            conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setUseCaches(false);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setReadTimeout(10000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");

            HashMap<String, String> params = new HashMap<String, String>();
            params.put("f", "json");
            params.put("geometry", input_geometry);
            params.put("returnGeometry", "false");
            params.put("geometryType", "esriGeometryPoint");
            params.put("outFields", "*");
            params.put("resultOffset","0");
            params.put("resultRecordCount","1");
            params.put("where", wheree);
            if (arcGISToken != null && arcGISToken.isEmpty() == false)
                params.put("token", arcGISToken);
            if (versionName != null && versionName.isEmpty() == false)
            {
                String vrsAd ="CBSORBIS."+versionName+"_"+service_name;
                params.put("gdbVersion",vrsAd);
            }
            else
            {
                params.put("gdbVersion","");
            }


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getQuery(params));
            writer.flush();
            writer.close();
            os.close();
            if (conn.getResponseCode()==200)
            {
                InputStream is = conn.getInputStream();
                StringBuffer json = new StringBuffer();
                byte[] buffer = new byte[8192];
                int read = 0;
                while ((read = is.read(buffer)) > 0)
                    json.append(new String(buffer, 0, read, "UTF-8"));
                is.close();
                jsonResponse = new JsonParser().parse(json.toString()).getAsJsonObject();
            }else
            {
                throw new OrbisDefaultException("Htpp error"+conn.getResponseMessage());
            }

        }  catch (JsonSyntaxException e) {
            Log.e("arcgisOprtr",e.toString());
            throw new OrbisDefaultException(e.toString());
        }catch (Exception ex) {
            Log.e("arcgisOprtr",ex.toString());
            throw new OrbisDefaultException(ex.toString());
        } catch (Throwable ex) {
            Log.e("arcgisOprtr",ex.toString());
            throw new OrbisDefaultException(ex.toString());
        }finally {
            if (conn != null)
                conn.disconnect();
        }
        return jsonResponse;
    }

    public<T extends BaseEntity> String getObjectIDs(List<T> items)
   {
       StringBuilder result = new StringBuilder();
       boolean first = true;
       int index =0;
       int size=items.size();
       for (T item:items)
       {
           index++;
           if (item.getOBJECTID()!=null)
           {
               result.append(item.getOBJECTID());
               if (index!=size)
               result.append(",");

           }
       }

       return result.toString();
   }



    //////////////  new queryService
    public List<JsonArray> GetQueryDataObjects(String username, String password, String restUrl, Integer objectClassId, String versionName, String whereClause) throws OrbisDefaultException {
        List<JsonArray> jsonList = new ArrayList<JsonArray>();
        JsonObject jsonResponse =null;
        try {
            String queryUrl = restUrl;
            String arcGISToken = AcquireArcGISToken(restUrl, username, password);
            boolean getNext = true;
            int offsetRange = 100;
            int offset = 0;
            while (getNext) {
                HttpURLConnection conn = (HttpURLConnection) new URL(queryUrl).openConnection();
                try {
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setReadTimeout(10000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("f", "json");
                    if (arcGISToken != null && arcGISToken.isEmpty() == false)
                        params.put("token", arcGISToken);
                    params.put("returnGeometry", "false");
                    if (versionName != null && versionName.isEmpty() == false)
                    {
                        String vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                        params.put("gdbVersion",vrsAd);
                    }
                    params.put("outFields", "*");
                    if (whereClause == null || whereClause.isEmpty())
                        whereClause = "1=1";
                    params.put("where", whereClause);
                    params.put("resultOffset", Integer.toString(offset));
                    params.put("maxRecordCount", Integer.toString(offsetRange));

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    os.close();

                    InputStream is = conn.getInputStream();
                    StringBuffer json = new StringBuffer();
                    byte[] buffer = new byte[8192];
                    int read = 0;
                    while ((read = is.read(buffer)) > 0)
                        json.append(new String(buffer, 0, read, "UTF-8"));
                    is.close();
                    String jsonString = json.toString();

                   jsonResponse  = new JsonParser().parse(jsonString).getAsJsonObject();
                   if (jsonResponse.has("features"))
                   {
                       jsonList.add(jsonResponse.getAsJsonArray("features"));
                   }
                    if (jsonResponse.has("exceededTransferLimit"))
                    {
                        boolean exceeded = jsonResponse.get("exceededTransferLimit").getAsBoolean();
                        if (exceeded) {
                            offset += offsetRange;

                        }
                        else {
                            getNext = false;
                        }
                    }
                    else if (jsonResponse.has("error"))
                    {
                        getNext = false;
                    }
                    else
                    {
                        getNext = false;
                    }
                } catch (Exception ex) {
                    arcGISToken = "";
                    getNext = false;
                    jsonList=null;
                }finally {
                    if (conn != null)
                        conn.disconnect();
                }
            }
        } catch (Exception e) {
          throw new OrbisDefaultException(e.toString());
        }
        return jsonList;
    }

    public JsonObject GetPartiallyqueryObjects(String username, String password, String restUrl, Integer objectClassId, String versionName, String whereClause, String offSet,String offsetRange,String orderByField) throws OrbisDefaultException {
        JsonObject jsonResponse =null;
        HttpURLConnection conn=null;
                try {
                    String queryUrl = restUrl;
                    String arcGISToken = AcquireArcGISToken(restUrl, username, password);
                    conn= (HttpURLConnection) new URL(queryUrl).openConnection();
                    conn.setUseCaches(false);
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setReadTimeout(10000);
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    conn.setRequestMethod("POST");

                    HashMap<String, String> params = new HashMap<String, String>();
                    params.put("f", "json");
                    if (arcGISToken != null && arcGISToken.isEmpty() == false)
                        params.put("token", arcGISToken);
                    params.put("returnGeometry", "false");
                    if (versionName != null && versionName.isEmpty() == false)
                    {
                        String vrsAd;
                        if(!versionName.contains("CBSORBIS"))
                            vrsAd ="CBSORBIS."+versionName+"_ALAN_ENVANTERI";
                        else
                            vrsAd = versionName;

                        params.put("gdbVersion",vrsAd);
                    }
                    if (orderByField != null && orderByField.isEmpty() == false)
                    {
                        params.put("orderByFields",orderByField);
                    }else
                    {
                        params.put("orderByFields", "OBJECTID");
                    }
                    params.put("outFields", "*");
                    if (whereClause == null || whereClause.isEmpty())
                        whereClause = "1=1";
                    params.put("where", whereClause);
                    params.put("resultOffset", offSet);
                    params.put("resultRecordCount", offsetRange);

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                    writer.write(getQuery(params));
                    writer.flush();
                    writer.close();
                    os.close();

                    InputStream is = conn.getInputStream();
                    StringBuffer json = new StringBuffer();
                    byte[] buffer = new byte[8192];
                    int read = 0;
                    while ((read = is.read(buffer)) > 0)
                        json.append(new String(buffer, 0, read, "UTF-8"));
                    is.close();
                    String jsonString = json.toString();
                    JSONObject jsonObject =new JSONObject(jsonString);
                    jsonResponse  = new JsonParser().parse(jsonString).getAsJsonObject();

                } catch (Exception ex) {
                    throw new OrbisDefaultException(ex.toString());
                }finally {
                    if (conn != null)
                        conn.disconnect();
                }

        return jsonResponse;
    }




}

