package com.example.alexp.controleleds;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import org.w3c.dom.Text;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.alexp.controleleds.efeitos.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String spinnerCodGetMain, spinnerCodGetBack, spinnerCodGetEfects;
    private TextView functionMsg;
    private Integer fitaExterior, fitaInterior, responseGet;
    private CheckBox fita_Exterior, fita_Interior;
    //public Cor cor = new Cor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Drawer

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        //Variaveis
        fita_Exterior = (CheckBox) findViewById(R.id.fita_Exterior);
        fita_Interior = (CheckBox) findViewById(R.id.fita_Interior);
        Spinner spinnerMain = (Spinner) findViewById(R.id.spinnerMain);
        Spinner spinnerBack = (Spinner) findViewById(R.id.spinnerBack);
        Spinner spinnerEfects = (Spinner) findViewById(R.id.spinnerEfects);
        functionMsg = (TextView) findViewById(R.id.functionMsg);


        List<Cor> cores = todosAsCores();
        List<Efeito> efeitos = todosOsEfeitos();

        //ArrayAdapter<Cor> adapter = new ArrayAdapter<Cor>(this,android.R.layout.simple_list_item_1, cores);

        AdapterColor adapter = new AdapterColor(cores, this);
        EfeitoAdapter efeitoAdapter = new EfeitoAdapter(efeitos, this);

        //configuração dos Spinners de cores...



        spinnerMain.setAdapter(adapter);
        spinnerBack.setAdapter(adapter);
        spinnerEfects.setAdapter(efeitoAdapter);

        spinnerMain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                spinnerCodGetMain = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                //Toast.makeText(getApplicationContext(), nomeSelect, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerBack.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                spinnerCodGetBack = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                //Toast.makeText(getApplicationContext(), nomeSelect, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEfects.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View v, int posicao, long id) {
                //pega nome pela posição
                spinnerCodGetEfects = parent.getItemAtPosition(posicao).toString();
                //imprime um Toast na tela com o nome que foi selecionado
                //Toast.makeText(getApplicationContext(), nomeSelect, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //OnClick functions

    public void criaFuncao (View v){

        if(fita_Exterior.isChecked()){
            fitaExterior = 1;
        }else{
            fitaExterior = 0;
        }
        if(fita_Interior.isChecked()){
            fitaInterior = 1;
        }else{
            fitaInterior = 0;
        }

        CriaURL("funcao="+spinnerCodGetEfects+","+spinnerCodGetBack+","+spinnerCodGetMain+
                ","+fitaExterior+","+fitaInterior);

    }

    void CriaURL(String url){
        RequestQueue queue = Volley.newRequestQueue(this);
        final String urlSend = "http://42.42.42.42/?"+url;

        //Configura a requisicao
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, urlSend, null, new Response.Listener<JSONObject>()
        {
            @Override
            public void onResponse(JSONObject response) {
                // mostra a resposta
                Log.d("Response", response.toString());
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                });

        // Adiciona a Fila de requisicoes
        queue.add(getRequest);

    }




    //COLOR LIST

    private List<Cor> todosAsCores() {
        return new ArrayList<>(Arrays.asList(
            new Cor("Black",0,0,0,1),
            new Cor("DimGray",105,105,105,2),
            new Cor("Gray",128,128,128,3),
            new Cor("DarkGray",169,169,169,4),
            new Cor("Silver",192,192,192,5),
            new Cor("LightGrey",211,211,211,6),
            new Cor("Gainsboro",220,220,220,7),
            new Cor("White",255,255,255,8),
            new Cor("SlateBlue",106,90,205,9),
            new Cor("DarkSlateBlue",72,61,139,10),
            new Cor("MidnightBlue",25,25,112,11),
            new Cor("Navy",0,0,128,12),
            new Cor("DarkBlue",0,0,139,13),
            new Cor("MediumBlue",0,0,205,14),
            new Cor("Blue",0,0,255,15),
            new Cor("CornflowerBlue",100,149,237,16),
            new Cor("RoyalBlue",65,105,225,17),
            new Cor("DodgerBlue",30,144,255,18),
            new Cor("DeepSkyBlue",0,191,255,19),
            new Cor("LightSkyBlue",135,206,250,20),
            new Cor("SkyBlue",135,206,235,21),
            new Cor("LightBlue",173,216,230,22),
            new Cor("SteelBlue",70,130,180,23),
            new Cor("LightSteelBlue",176,196,222,24),
            new Cor("SlateGray",112,128,144,25),
            new Cor("LightSlateGray",119,136,153,26),
            new Cor("Aqua / Cyan",0,255,255,27),
            new Cor("DarkTurquoise",0,206,209,28),
            new Cor("Turquoise",64,224,208,29),
            new Cor("MediumTurquoise",72,209,204,30),
            new Cor("LightSeaGreen",32,178,170,31),
            new Cor("DarkCyan",0,139,139,32),
            new Cor("Teal",0,128,128,33),
            new Cor("Aquamarine",127,255,212,34),
            new Cor("MediumAquamarine",102,205,170,35),
            new Cor("CadetBlue",95,158,160,36),
            new Cor("DarkSlateGray",47,79,79,37),
            new Cor("MediumSpringGreen",0,250,154,38),
            new Cor("SpringGreen",0,255,127,39),
            new Cor("PaleGreen",152,251,152,40),
            new Cor("LightGreen",144,238,144,41),
            new Cor("DarkSeaGreen",143,188,143,42),
            new Cor("MediumSeaGreen",60,179,113,43),
            new Cor("SeaGreen",46,139,87,44),
            new Cor("DarkGreen",0,100,0,45),
            new Cor("Green",0,128,0,46),
            new Cor("ForestGreen",34,139,34,47),
            new Cor("LimeGreen",50,205,50,48),
            new Cor("Lime",0,255,0,49),
            new Cor("LawnGreen",124,252,0,50),
            new Cor("Chartreuse",127,255,0,51),
            new Cor("GreenYellow",173,255,47,52),
            new Cor("YellowGreen",154,205,50,53),
            new Cor("OliveDrab",107,142,35,54),
            new Cor("DarkOliveGreen",85,107,47,55),
            new Cor("Olive",128,128,0,56),
            new Cor("DarkKhaki",189,83,107,57),
            new Cor("Goldenrod",218,165,32,58),
            new Cor("DarkGoldenrod",184,134,11,59),
            new Cor("SaddleBrown",139,69,19,60),
            new Cor("Sienna",160,82,45,61),
            new Cor("RosyBrown",188,143,143,62),
            new Cor("Peru",205,133,63,63),
            new Cor("Chocolate",210,105,30,64),
            new Cor("SandyBrown",244,164,96,65),
            new Cor("NavajoWhite",255,222,173,66),
            new Cor("Wheat",245,222,179,67),
            new Cor("BurlyWood",222,184,135,68),
            new Cor("Tan",210,180,140,69),
            new Cor("MediumSlateBlue",123,104,238,70),
            new Cor("MediumPurple",147,112,219,71),
            new Cor("BlueViolet",138,43,226,72),
            new Cor("Indigo",75,0,130,73),
            new Cor("DarkViolet",148,0,211,74),
            new Cor("DarkOrchid",153,50,204,75),
            new Cor("MediumOrchid",186,85,211,76),
            new Cor("Purple",128,0,128,77),
            new Cor("DarkMagenta",139,0,139,78),
            new Cor("Fuchsia / Magenta",255,0,255,79),
            new Cor("Violet",238,130,238,80),
            new Cor("Orchid",218,112,214,81),
            new Cor("Plum",221,160,221,82),
            new Cor("MediumVioletRed",199,21,133,83),
            new Cor("DeepPink",255,20,147,84),
            new Cor("HotPink",255,105,180,85),
            new Cor("PaleVioletRed",219,112,147,86),
            new Cor("LightPink",255,182,193,87),
            new Cor("Pink",255,192,203,88),
            new Cor("LightCoral",240,128,128,89),
            new Cor("IndianRed",205,92,92,90),
            new Cor("Crimson",220,20,60,91),
            new Cor("Maroon",128,0,0,92),
            new Cor("DarkRed",139,0,0,93),
            new Cor("FireBrick",178,34,34,94),
            new Cor("Brown",165,42,42,95),
            new Cor("Salmon",250,128,114,96),
            new Cor("DarkSalmon",233,150,122,97),
            new Cor("LightSalmon",255,160,122,98),
            new Cor("Coral",255,127,80,99),
            new Cor("Tomato",255,99,71,100),
            new Cor("Red",255,0,0,101),
            new Cor("OrangeRed",255,69,0,102),
            new Cor("DarkOrange",255,140,0,103),
            new Cor("Orange",255,165,0,104),
            new Cor("Gold",255,215,0,105),
            new Cor("Yellow",255,255,0,106),
            new Cor("Khaki",240,230,140,107),
            new Cor("AliceBlue",240,248,255,108),
            new Cor("GhostWhite",248,248,255,109),
            new Cor("Snow",255,250,250,110),
            new Cor("Seashell",255,245,238,111),
            new Cor("FloralWhite",255,250,240,112),
            new Cor("WhiteSmoke",245,245,245,113),
            new Cor("Beige",245,245,220,114),
            new Cor("OldLace",253,245,230,115),
            new Cor("Ivory",255,255,240,116),
            new Cor("Linen",250,240,230,117),
            new Cor("Cornsilk",255,248,220,118),
            new Cor("AntiqueWhite",250,235,215,119),
            new Cor("BlanchedAlmond",255,235,205,120),
            new Cor("Bisque",255,228,196,121),
            new Cor("LightYellow",255,255,224,122),
            new Cor("LemonChiffon",255,250,205,123),
            new Cor("LightGoldenrodYellow",250,250,210,124),
            new Cor("PapayaWhip",255,239,213,125),
            new Cor("PeachPuff",255,218,185,126),
            new Cor("Moccasin",255,228,181,127),
            new Cor("PaleGoldenrod",238,232,170,128),
            new Cor("MistyRose",255,228,225,129),
            new Cor("LavenderBlush",255,240,245,130),
            new Cor("Lavender",230,230,250,131),
            new Cor("Thistle",216,191,216,132),
            new Cor("Azure",240,255,255,133),
            new Cor("LightCyan",224,255,255,134),
            new Cor("PowderBlue",176,224,230,135),
            new Cor("PaleTurquoise",175,238,238,136),
            new Cor("Honeydew",240,255,240,137),
            new Cor("MintCream",245,255,250,138)
            ));
    }

    private List<Efeito> todosOsEfeitos() {
        return new ArrayList<>(Arrays.asList(
            new Efeito("AllStrip","ImgEfeito1"),
            new Efeito("Fire","ImgEfeito2"),
            new Efeito("GoBack","ImgEfeito3")
        ));
    }


    //DETECTING A sEND FROM SERVER

    void recieveData(){

    }

}
