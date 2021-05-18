package com.example.smartgarden.ui.conexions;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.smartgarden.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManagerFactory;

public class ConexionAPI {
    public static String TABLE_NOTIFICACION = "Notificaciones";
    public static String TABLE_REGISTRO = "Registros";

    private String url;
    private Context context;

    /**
     * NOTA: No se debe dejar espacio para los llamados del API y para las consultas ccmpuestas
     * se debe separar por %20
     * Constructor que recibe el url por del servidor API REST
     * @param url ejemplo https://192.168.4.1/ApiRest
     */
    public ConexionAPI(String url){ this.url = url; }

    /**
     * Obtener los datos de una tabla con todas las columnas
     * {
     * {"value1","value2","value3",...}, //Registro uno
     * {"value1","value2","value3",...}, //Registro dos
     * ...}
     * @param table a consultar
     * @return Retorna una matriz que representa la tabla
     */
    public String[][] getData(String table) throws InvalidKeyException, IOException, JSONException {
        String[][] strData;
        int ncolum,nrow;
        String responde = downloadData(url+"getData.php?t="+table, "GET");//Descargo el archivo JSON
        if(responde.contains("Empty Data")) return new String[0][0];
        JSONObject json= new JSONObject(responde);
        JSONArray tmp = json.getJSONArray("data");
        ncolum = tmp.getJSONObject(0).length()/2;
        nrow = tmp.length();
        strData = new String[nrow][ncolum];
        for(int n=0; n < nrow; n++){//Itero solo la cantidad de registros obtenidos correctament
            JSONObject row = tmp.getJSONObject(n);//Obtengo solo las columnas de un registro
            JSONArray results = row.names(); //Obtengo los nombres de las columnas
            for (int i=0,j=1; i < ncolum; i++,j+=2)
                strData[n][i] = row.getString(results.getString(j)); //Obtengo el valor de la columna
        }
        return  strData;
    }

    /**
     * Obtener los datos de una tabla con las columnas especificadas
     * {
     * {"value1","value2","value3",...}, //Registro uno
     * {"value1","value2","value3",...}, //Registro dos
     * ...}
     * @param table a consultar
     * @param columns separadas por , (column1,column2,column3...)
     * @return Retorna una matriz que representa la tabla
     */
    public String[][] getData(String table, String columns) throws InvalidKeyException, IOException, JSONException {
        String[][] strData;
        int ncolum,nrow;
        String responde = downloadData(url+"getData.php?t="+table+"&c="+columns, "GET");//Descargo el archivo JSON
        if(responde.contains("Empty Data")) return new String[0][0];
        JSONObject json= new JSONObject(responde);
        JSONArray tmp = json.getJSONArray("data");
        ncolum = tmp.getJSONObject(0).length()/2;
        nrow = tmp.length();
        strData = new String[nrow][ncolum];
        for(int n=0; n < nrow; n++){//Itero solo la cantidad de registros obtenidos correctament
            JSONObject row = tmp.getJSONObject(n);//Obtengo solo las columnas de un registro
            JSONArray results = row.names(); //Obtengo los nombres de las columnas
            for (int i=0,j=1; i < ncolum; i++,j+=2)
                strData[n][i] = row.getString(results.getString(j)); //Obtengo el valor de la columna
        }
        return  strData;
    }

    /**
     * Obtener los datos de una tabla con las columnas especificadas que cumplan una condición
     * {
     * {"value1","value2","value3",...}, //Registro uno
     * {"value1","value2","value3",...}, //Registro dos
     * ...}
     * @param table a consultar
     * @param columns separadas por , (column1,column2,column3...)
     * @param where condicion (ID=0, ID>0 AND ID<20)
     * @return Retorna una matriz que representa la tabla
     */
    public String[][] getData(String table, String columns, String where) throws InvalidKeyException, IOException, JSONException {
        String[][] strData;
        int ncolum,nrow;
        String responde = downloadData(url+"getData.php?t="+table+"&c="+columns+"&w="+where, "GET");//Descargo el archivo JSON
        if(responde.contains("Empty Data")) return new String[0][0];
        JSONObject json= new JSONObject(responde);
        JSONArray tmp = json.getJSONArray("data");
        ncolum = tmp.getJSONObject(0).length()/2;
        nrow = tmp.length();
        strData = new String[nrow][ncolum];
        for(int n=0; n < nrow; n++){//Itero solo la cantidad de registros obtenidos correctament
            JSONObject row = tmp.getJSONObject(n);//Obtengo solo las columnas de un registro
            JSONArray results = row.names(); //Obtengo los nombres de las columnas
            for (int i=0,j=1; i < ncolum; i++,j+=2)
                strData[n][i] = row.getString(results.getString(j)); //Obtengo el valor de la columna
        }
        return  strData;
    }

    /**
     * Metodo para inserta un registro en una tabla y retorna el resultado (el registro son su ID)
     * @param table a insertar
     * @param columns de la tabla, separados por , (column1,column2,column3)
     * @param values de las columnas separados por , (value1,value2,value3)
     * @return Retorna una matriz que representa la tabla
     */
    public String[][] setData(String table, String columns, String values) throws InvalidKeyException, IOException, JSONException {
        String[][] strData;
        int ncolum,nrow;
        JSONObject json= new JSONObject(downloadData(url+"postData.php?t="+table+"&c="+columns+"&v="+values,"POST"));//Descargo el archivo JSON
        JSONArray tmp = json.getJSONArray("data");
        ncolum = tmp.getJSONObject(0).length()/2;
        nrow = tmp.length();
        strData = new String[nrow][ncolum];
        for(int n=0; n < nrow; n++){//Itero solo la cantidad de registros obtenidos correctament
            JSONObject row = tmp.getJSONObject(n);//Obtengo solo las columnas de un registro
            JSONArray results = row.names(); //Obtengo los nombres de las columnas
            for (int i=0,j=1; i < ncolum; i++,j+=2)
                strData[n][i] = row.getString(results.getString(j)); //Obtengo el valor de la columna
        }
        return  strData;
    }

    /**
     * Metodo para inserta un registro en una tabla y retorna el resultado (el registro son su ID)
     * @param table a insertar
     * @param column de la tabla (solo una columna)
     * @param value de la columna (solo un valor)
     * @param where condicion para filtrar los registros actualizar
     * @return Retorna una matriz que representa la tabla
     */
    public String[][] updateData(String table, String column, String value, String where) throws InvalidKeyException, IOException, JSONException {
        String[][] strData;
        int ncolum,nrow;
        JSONObject json= new JSONObject(downloadData(url+"updateData.php?t="+table+"&c="+column+"&v="+value+"&w="+where,"POST"));//Descargo el archivo JSON
        JSONArray tmp = json.getJSONArray("data");
        ncolum = tmp.getJSONObject(0).length()/2;
        nrow = tmp.length();
        strData = new String[nrow][ncolum];
        for(int n=0; n < nrow; n++){//Itero solo la cantidad de registros obtenidos correctament
            JSONObject row = tmp.getJSONObject(n);//Obtengo solo las columnas de un registro
            JSONArray results = row.names(); //Obtengo los nombres de las columnas
            for (int i=0,j=1; i < ncolum; i++,j+=2)
                strData[n][i] = row.getString(results.getString(j)); //Obtengo el valor de la columna
        }
        return  strData;
    }

    /**
     * Metodo auxiliar para saber en cualquier momento si hay conexion con el API REST
     * @return
     */
    public boolean isConnected() {
        try {
            return tryConnect(setHttp(url));
        } catch (IOException e) {
            System.out.println("Error isConnected \n"+e.getMessage());
            return false;
        }
    }

    /**
     * Descargar todo el JSON
     * @param URL API LINK a descargar
     * @param med el metodo para llamar al URL (GET o POST)
     * @return devuelve el JSON en un string
     */
    private String downloadData(String URL, String med) throws InvalidKeyException, IOException {
        BufferedReader in;
        StringBuffer response;
        String inputLine;
        String params;
        HttpURLConnection con;

        if(med.equals("GET")){
            con = setHttp(URL);
            con.setRequestMethod(med);

            if(!tryConnect(con))
                throw new ConnectException("No hay conexion.");
        }
        else{
            params = URL.split("\\?")[1];

            con = setHttp(URL.split("\\?")[0]);
            con.setRequestMethod(med);
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(params);
            wr.flush();
            wr.close();
            con.disconnect();
        }

        if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStreamReader reader = new InputStreamReader(con.getInputStream());
            in = new BufferedReader(reader);
            response = new StringBuffer();
            while ((inputLine = in.readLine()) != null)
                response.append(inputLine);
            in.close();
            reader.close();
            con.disconnect();
            return response.toString();
        }
        else
            return "Empty Data";
    }

    /**
     * Metodo para probar la conexion de la pagina del API REST
     * @param url El objeto de conexion para pobrar
     * @return true si se hay conexion
     */
    private boolean tryConnect(HttpURLConnection url){
        try {

            url.setRequestProperty("User-Agent", "Test");
            //url.setRequestProperty("Connection", "close");
            url.setConnectTimeout(1500);
            url.connect();
            return (url.getResponseCode() == HttpURLConnection.HTTP_OK);
        } catch (Exception e) {
            System.out.println("Error tryConnect \n"+ e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Se encarga de crear la conexion http
     * @param urlString es la URL del servidor
     * @return la conexion establecida
     */
    private HttpURLConnection setHttp(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        return urlConnection;
    }

    /**
     * Se encarga de aceptar el certificado SSL autofirmado
     * @param urlString es la URL del servidor
     * @return la conexion establecida
     */
    private HttpsURLConnection setHttps(String urlString) throws InvalidKeyException {
        try
        {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            InputStream caInput = new BufferedInputStream(context.getAssets().open("server.cer"));
            Certificate ca = cf.generateCertificate(caInput);
            //System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
            caInput.close();

            String keyStoreType = KeyStore.getDefaultType();
            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
            keyStore.load(null, null);
            keyStore.setCertificateEntry("ca", ca);

            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(keyStore);

            SSLContext context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);

            URL url = new URL(urlString);
            HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
            urlConnection.setSSLSocketFactory(context.getSocketFactory());
            urlConnection.setHostnameVerifier( new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return urlConnection;
        }
        catch (Exception ex)
        {
            throw new InvalidKeyException("Error en el certificado");
        }
    }
}
