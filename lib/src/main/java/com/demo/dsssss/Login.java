package com.demo.dsssss;//package com.dahuatch.Login;

import com.google.gson.Gson;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

class Login {
    public static final String ACTION = "/videoService/accounts/authorize";

    private static String httpRequest(String ip, int port, String content) {
        String responJson = null;
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        InputStream inputStream = null;
        try {
            // Create HttpClient Object
            httpClient = HttpClients.createDefault();
            // Create Http Post Object
            HttpPost httpReq = new HttpPost("http://" + ip + ":" + port + ACTION);
            httpReq.addHeader("Content-type", "application/json");
            httpReq.setEntity(new StringEntity(content));
            httpResponse = httpClient.execute(httpReq);
            inputStream = httpResponse.getEntity().getContent();
            responJson = convertToString(inputStream);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != httpResponse) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != httpClient) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return responJson;
    }

    private static String convertToString(InputStream is) {
        if (is == null) {
            return null;
        }
        BufferedReader bf = null;
        try {
            StringBuilder sb = new StringBuilder();
            String temp = "";
            bf = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((temp = bf.readLine()) != null) {
                sb.append(temp);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeStream(bf);
            closeStream(is);
        }
    }

    private static void closeStream(Closeable closeable) {
        if (null != closeable) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String firstLogin(String ip, int port, String userName) {
        // Set Params
        LoginFirst loginFirst = new LoginFirst();
        loginFirst.setClientType("web");
        loginFirst.setUserName(userName);
        //loginFirst.setIpAddress("10.10.10.10");
        String rsp = httpRequest(ip, port, new Gson().toJson(loginFirst));
        return rsp;
    }

    private static String secondLogin(String ip, int port, String userName, String password, String realm, String randomKey) throws Exception {
        LoginSecond snd = new LoginSecond();
        snd.setUserName(userName);
        snd.setClientType("web");
        //snd.setIpAddress("10.10.10.10");
        snd.setRandomKey(randomKey);
        snd.setEncryptType("MD5");
        String signature = snd.calcSignature(password, realm);
        snd.setSignature(signature);

        Gson gson = new Gson();
        String ctx = gson.toJson(snd);
        String rsp = httpRequest(ip, port, ctx);

        return rsp;
    }

    @SuppressWarnings("unchecked")
    private static String login(String ip, int port, String userName, String password) throws Exception {
        String response = firstLogin(ip, port, userName);
        Map<String, String> responseMap = new Gson().fromJson(response, Map.class);
        String random = responseMap.get("randomKey");
        String realm = responseMap.get("realm");

        response = secondLogin(ip, port, userName, password, realm, random);
        return response;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        String ip = "222.174.187.242";
        int port = Integer.valueOf("8314");
        String userName = "admin";
        String password = "admin123";

        String response = login(ip, port, userName, password);
        Map<String, Object> responseMap = new Gson().fromJson(response, Map.class);
        String token = (String) responseMap.get("token");
        System.out.println("token : "+token);
    }
}



