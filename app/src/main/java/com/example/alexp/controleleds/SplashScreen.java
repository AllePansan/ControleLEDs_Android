
package com.example.alexp.controleleds;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class SplashScreen extends AppCompatActivity{

    private TextView texto;
    private WifiManager wifi;
    private WifiConfiguration config;
    private String ssid = "\"".concat("ESP8266").concat("\""), passwd = "\"ESP8266Test\"";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        texto = (TextView) findViewById(R.id.Connecting);
        wifi = (WifiManager) this.getSystemService(this.WIFI_SERVICE);
        config = new WifiConfiguration();

        config.SSID = ssid;
        config.preSharedKey = passwd;

        Handler handle = new Handler();

        handle.postDelayed(new Runnable() {
            public void run() {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        System.out.println("Entrou na thread");
                        tryConnection();
                    }
                };
                thread.start();
            }
        }, 1000);

    }


    private void tryConnection() {
        List<WifiConfiguration> list = wifi.getConfiguredNetworks();

        if(wifi.isWifiEnabled() == true) {
            System.out.println("Wifi esta ligado");
            System.out.println(wifi.getConnectionInfo().getSSID()+" = "+ssid+" ?");

            if (wifi.getConnectionInfo().getSSID().equals(ssid)) {
                System.out.println("Passou a verificação do Wifi");
                runOnMainThread("Conectado");
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                System.out.println("Nao passou  a verificação do Wifi");
                runOnMainThread("Conectando...\n\nOu não...");
                wifi.addNetwork(config);

                for( WifiConfiguration i : list ) {
                    if(i.SSID != null && i.SSID.equals(ssid)) {
                        wifi.disconnect();
                        wifi.enableNetwork(i.networkId, true);
                        wifi.reconnect();

                        break;
                    }
                }
                tryConnection();
            }
        }else{
            System.out.println("Wifi nao estava ligado");
            wifi.setWifiEnabled(true);
            tryConnection();
        }
    }

    void runOnMainThread(final String message){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                texto.setText(message);
            }
        });
    }

}