package com.ets.bfd.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ets.bfd.utilities.App_Config;
import com.ets.bfd.utilities.SerializedNameOnlyStrategy;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit API Client.
 * API Endpoint
 * @author Nazmul Hasan
 * @url https://square.github.io/retrofit/
 *
 */
public class RetrofitApiClient {
    private static final String BASE_URL = App_Config.BASE_URL; //address of remote server.
    private static Retrofit retrofit = null;

    private static Gson gson = new GsonBuilder()
            .setExclusionStrategies(new SerializedNameOnlyStrategy())
            .setLenient()
            .create();

    private RetrofitApiClient() {} // So that nobody can create an object with constructor

    static OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
            .connectTimeout(5, TimeUnit.MINUTES)
            .readTimeout(5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build();

    public static synchronized Retrofit getClient() {   // Threadsafe Singleton
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getUnsafeOkHttpClient())
                    .build();
        }
        return retrofit;
    }

    /**
     * For https secure connection
     *
     * @return
     */
    private static OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                                                .connectTimeout(25, TimeUnit.MINUTES)
                                                .readTimeout(25, TimeUnit.MINUTES)
                                                .writeTimeout(25, TimeUnit.MINUTES);
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });

            OkHttpClient okHttpClient = builder.build();
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
