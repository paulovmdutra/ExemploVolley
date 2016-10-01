package br.com.paulovmdutra.exemplovolley;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final WebView webview = (WebView)findViewById(R.id.webview);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Aguarde...");
        dialog.show();

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://www.google.com.br";

        //A classe StringRequest realizar uma requisição dada uma URL e retorna uma string como resultado
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        webview.loadData(response,"text/html; charset=UTF-8", null);
                        dialog.dismiss();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String res = "<html><head></head><body><h1>Houve uma falha ao carregar a página!</h1></body><html>";
                webview.loadData(res,"text/html; charset=UTF-8", null);
                dialog.dismiss();
            }
        });

        //Adiciona a requisição para o RequestQueue
        queue.add(stringRequest);

    }
}
