package ToolLayer;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.konumsal.orbisozetmobil.OrtakUI.LogLarge;
import com.konumsal.orbisozetmobil.OrtakUI.OrtakFunction;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import EntityLayer.Ortak.Result;
import EnumsLayer.HttpRequestType;

public class RSOperator
{


    public  String downloadFileFromServer(String filename, String urlString) throws MalformedURLException, IOException
    {
        BufferedInputStream in = null;
        FileOutputStream fout = null;
        try
        {
            URL url = new URL(urlString);


            in = new BufferedInputStream(url.openStream());
            fout = new FileOutputStream(filename);

            byte data[] = new byte[1024];
            int count;
            while ((count = in.read(data, 0, 1024)) != -1)
            {
                fout.write(data, 0, count);
                //System.out.println(count);
            }
        }
        catch (Exception ex)
        {
            Log.v("indirirek hata olustu","error");
            return "0";
        }
        finally
        {
            if (in != null)
                in.close();
            if (fout != null)
                fout.close();
        }
        return "1";
    }


    public   <T> String CreateToRSUrlConnection(HttpRequestType requestType, T entity, String url ,String jsonString) throws OrbisDefaultException {
        String result =null;
        HttpURLConnection httpcon;

        String data = null;
        try {
            trustEveryone();
            httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
//           httpcon.setDoOutput(true);
//           httpcon.setDoInput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestProperty("Accept", "application/json;charset=UTF-8");
            String reqName = requestType.name();
            httpcon.setRequestMethod(requestType.name().toString());
            httpcon.setConnectTimeout(10000);
            httpcon.setReadTimeout(6000000);

            if (entity!=null || jsonString != null) {
               Log.v("2sinden biri null değil","_"+jsonString);
                if (entity != null) {
                    Log.v("json parser entity->", "entity");
                    String jsonentity = convertJsonFromEntity(entity);
                    OutputStreamWriter out = new OutputStreamWriter(httpcon.getOutputStream());
                    out.write(jsonentity);
                    out.close();
                    LogLarge.v("json entity","=>"+jsonentity);
                } else if (!jsonString.equals("")) {
                    Log.v("json parser string->", jsonString+"_");
                    OutputStream os = httpcon.getOutputStream();
                    os.write(jsonString.getBytes());
                    os.flush();
                }
            }
            Log.v("url parse","başarılı");
            httpcon.connect();
            int responseCode = httpcon.getResponseCode();
            if (responseCode == 200)
            {
                Log.v("response code","200");
                // InputStream in = new BufferedInputStream(httpcon.getInputStream());
                if (!httpcon.getInputStream().equals(null))
                {
                    Log.v("output s is not null","evet");
                    result = convertStreamString(httpcon.getInputStream());
                    Log.v("CONVERTED STR","=>"+result);
                    httpcon.getInputStream().close();
                    // in.reset();
                    // in.close();
                }

            }else if(responseCode==500)
            {
                Log.v("baglanti hatasi","server yanit vermiyor 500");
                throw new OrbisDefaultException("Baglanti Hatasi:" + httpcon.getResponseMessage() + "Server yanıt vermiyor ! kod:" + responseCode);
            }
            else
            {
                Log.v("baglanti hatasi","server yanit vermiyor "+responseCode);
                throw new OrbisDefaultException("Baglanti Hatasi:" + httpcon.getResponseMessage() + " kod:" + responseCode);
            }
            httpcon.disconnect();

        }catch (ConnectException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("Bağlantınızı kontrol edin!!!!\n"+"Hata :" + e.getMessage()+" rs1");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("Hata :" + e.getMessage()+" rs2");
        } catch (IOException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("Hata :" + e.getMessage()+" rs3");
        }catch (ClassCastException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("Hata :" + e.getMessage()+" rs4");
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException("Hata :" + e.getMessage()+" rs5");
        }catch (OrbisDefaultException e)
        {
            //senkronizasyon gerçekleştirilemedi
            Log.v("sunucu ","yanıt vermiyor");
            throw new OrbisDefaultException("Hata:" + e.getMessage()+" rs6");
        }

        return  result;
    }

    private void trustEveryone() {
        try {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier(){
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }});
            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, new X509TrustManager[]{new X509TrustManager(){
                public void checkClientTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public void checkServerTrusted(X509Certificate[] chain,
                                               String authType) throws CertificateException {}
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }}}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(
                    context.getSocketFactory());
        } catch (Exception e) { // should never happen
            e.printStackTrace();
        }
    }

    public  String CreateToRSUrlConnectionFromParams(HttpRequestType requestType, List<Object> params, String url) throws OrbisDefaultException {
        String result =null;
        HttpURLConnection httpcon;

        String data = null;
        try {
            trustEveryone();
            httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
//            httpcon.setDoOutput(true);
//            httpcon.setDoInput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestMethod("GET");
//
            httpcon.connect();
            int responseCode = httpcon.getResponseCode();
            String mess= httpcon.getResponseMessage();
            if (responseCode == 200)
            {
                InputStream in = new BufferedInputStream(httpcon.getInputStream());
                if (!in.equals(null))
                {
                    result = convertStreamToString(in);
                    Log.v("login result","=>"+result);

                    in.close();
                }
            }
            else
            {
                throw new OrbisDefaultException("Baglanti Hatasi:" + httpcon.getResponseMessage() + " kod:" + responseCode);
            }

        }catch (ConnectException e)
        {
            Log.v("rs","1");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS1:" + e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            Log.v("rs","2");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS2:" + e.getMessage());
        } catch (IOException e)
        {
            Log.v("rs","3");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS3:" + e.getMessage());
        }catch (ClassCastException e)
        {
            Log.v("rs","4");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS4:" + e.getMessage());
        }
        catch (NullPointerException e)
        {
            Log.v("rs","5");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS5:" + e.getMessage());
        }catch (OrbisDefaultException e)
        {
            Log.v("rs","6");
            throw new OrbisDefaultException("Hata RS6:" + e.getMessage());
        }

        return  result;
    }



    public  String CreateToRSUrlConnectionFromParamsPostMethod(HttpRequestType requestType, List<Object> params, String url) throws OrbisDefaultException {
        String result =null;
        HttpURLConnection httpcon;

        String data = null;
        try {
            httpcon = (HttpURLConnection) ((new URL(url).openConnection()));
//            httpcon.setDoOutput(true);
//            httpcon.setDoInput(true);
            httpcon.setRequestProperty("Content-Type", "application/json");
            httpcon.setRequestMethod("POST");
//
            httpcon.connect();
            int responseCode = httpcon.getResponseCode();
            String mess= httpcon.getResponseMessage();
            if (responseCode == 200)
            {
                InputStream in = new BufferedInputStream(httpcon.getInputStream());
                if (!in.equals(null))
                {
                    result = convertStreamToString(in);

                    in.close();
                }
            }
            else
            {
                throw new OrbisDefaultException("Baglanti Hatasi:" + httpcon.getResponseMessage() + " kod:" + responseCode);
            }

        }catch (ConnectException e)
        {
            Log.v("rs","1");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS1:" + e.getMessage());
        }
        catch (UnsupportedEncodingException e)
        {
            Log.v("rs","2");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS2:" + e.getMessage());
        } catch (IOException e)
        {
            Log.v("rs","3");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS3:" + e.getMessage());
        }catch (ClassCastException e)
        {
            Log.v("rs","4");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS4:" + e.getMessage());
        }
        catch (NullPointerException e)
        {
            Log.v("rs","5");
            e.printStackTrace();
            throw new OrbisDefaultException("Hata RS5:" + e.getMessage());
        }catch (OrbisDefaultException e)
        {
            Log.v("rs","6");
            throw new OrbisDefaultException("Hata RS6:" + e.getMessage());
        }

        return  result;
    }





    private String convertStreamToString(InputStream inputStream) throws IOException, OrbisDefaultException {

        StringBuilder stringBuilder = null;
        try {
            byte[] bytes = inputStream.toString().getBytes();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream),bytes.length);
            stringBuilder = new StringBuilder();
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }
            //inputStream.reset();
            // inputStream.close();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new OrbisDefaultException("convertStreamString"+e.getMessage()+"\nconvertStreamString");
        }

        return stringBuilder.toString();
    }
    public String convertStreamString(InputStream is) throws IOException, OrbisDefaultException
    {

        String jsonString = null;
        try {
            StringBuffer json = new StringBuffer();
            byte[] buffer = new byte[8192];
            int read = 0;
            while ((read = is.read(buffer)) > 0)
                json.append(new String(buffer, 0, read, "UTF-8"));
            is.close();
            jsonString = json.toString();
        }catch (Exception e) {
            e.printStackTrace();
            throw new OrbisDefaultException("convertStreamString"+e.getMessage()+"\nconvertStreamString");
        }
        catch (Throwable e) {
            e.printStackTrace();
            throw new OrbisDefaultException("convertStreamString"+e.getMessage()+"\nconvertStreamString");
        }
        is.close();
        return jsonString;

    }
    public <T>  T convertJSONToEntity(String jsonStr, Type type, T entity) throws OrbisDefaultException
    {

        try {
            Gson gson = null;
            JsonSerializer<Date> ser = new JsonSerializer<Date>() {
                @Override
                public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                        context) {
                    return src == null ? null : new JsonPrimitive(src.getTime());
                }
            };

            JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {


                    if(json == null)
                        return null;
                    else
                    {
                        if(json.getAsString().length() == 10)
                        {
                            Date date = new Date();
                            try {
                                date =  new DateUtils().ConvertStringToDate(json.getAsJsonPrimitive().getAsString());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return date;
                        }
                        else if(OrtakFunction.isNumeric(json.getAsString()))
                        {
                            return new Date(json.getAsJsonPrimitive().getAsLong());
                        }
                        else
                        {
                            Date date = new Date();
                            try {
                                String short_date_string = new DateUtils().ConvertLongStringToShortString(json.getAsJsonPrimitive().getAsString());
                                date =  new DateUtils().ConvertStringToDate(short_date_string);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            return date;
                        }
                    }
                    // return json == null ? null : new Date(json.getAsJsonPrimitive().getAsLong());
                }
            };






            gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, ser)
                    .registerTypeAdapter(Date.class, deser).create();
            entity = gson.fromJson(jsonStr, type);
            jsonStr=null;


        }

        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }
        catch (com.google.gson.JsonParseException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }
        finally {
            return entity;
        }
    }

    public <T>  String convertJsonFromEntity(T entity)
    {
        String jSon=null;
        try {
            GsonBuilder builder = new GsonBuilder();
            JsonSerializer<Date> ser = new JsonSerializer<Date>() {
                @Override
                public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext
                        context) {
                    return src == null ? null : new JsonPrimitive(src.getTime());
                }
            };

            JsonDeserializer<Date> deser = new JsonDeserializer<Date>() {
                @Override
                public Date deserialize(JsonElement json, Type typeOfT,
                                        JsonDeserializationContext context) throws JsonParseException {
                    return json == null ? null : new Date(json.getAsLong());
                }
            };

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(Date.class, ser)
                    .registerTypeAdapter(Date.class, deser).create();

            jSon = gson.toJson(entity);

        }

        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.toString());
        }
        catch (com.google.gson.JsonParseException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.toString());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.toString());
        }
        catch (Throwable e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.toString());
        }
        finally {
            return jSon;
        }
    }
    public <T extends Serializable> Result<T> convertResultFromJson(String jsonStr, T entitiy_) throws OrbisDefaultException
    {

        Result<T> result = new Result<T>();

        Type typeOf_val = new TypeToken<Result>(){}.getType();
        try {
            JSONObject object = new JSONObject(jsonStr);
            JSONObject resObj=  object.getJSONObject("result");
            if (resObj.toString().trim().length()>3)
            {
                Type typeOf_ent = new TypeToken<T>(){}.getType();
                entitiy_= new GsonBuilder().create().fromJson(resObj.toString(),typeOf_ent);
                String a=   entitiy_.toString();
            }
            result = new GsonBuilder().create().fromJson(jsonStr,typeOf_val);


        }

        catch (JsonSyntaxException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }
        catch (com.google.gson.JsonParseException e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }catch (Exception e)
        {
            e.printStackTrace();
            throw new OrbisDefaultException(e.getMessage());
        }
        finally {
            return result;
        }
    }

}
