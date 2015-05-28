package com.example.echeverria.lmapas;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements GoogleMap.OnMapClickListener {

    private final LatLng casa = new LatLng(18.36138308979571, -100.68158861249685);
    private GoogleMap googlemap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sirve para relacionar el mapa con el fragment.
        googlemap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        //indica que tipo de mapa se mostrar al iniciar
        googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
        googlemap.moveCamera(CameraUpdateFactory.newLatLngZoom(casa, 15));

        //Mostrar / ocultar tu ubicacion
        googlemap.setMyLocationEnabled(true);
        //Mostara / ocultar los controles de Zoom
        googlemap.getUiSettings().setZoomControlsEnabled(true);
        //Mostrar / ocultar los botones de localizacion
        googlemap.getUiSettings().setMyLocationButtonEnabled(true);
        //Mostrar / ocultar icon de compas
        googlemap.getUiSettings().setCompassEnabled(true);
        //Mostrar / ocultar evento de rotar
        googlemap.getUiSettings().setRotateGesturesEnabled(true);
        //Mostrar / ocultar funcionalidad de Zoom
        googlemap.getUiSettings().setZoomGesturesEnabled(true);

        //Indicamos un marcador en nuestro mapa
        googlemap.addMarker(new MarkerOptions().position(casa).title("Casa De Euli").snippet("Col. Invisur, Atenea #1").icon(BitmapDescriptorFactory.fromResource(android.R.drawable.ic_menu_compass)).anchor(0.5f, 0.5f));
        googlemap.setOnMapClickListener(this);

    }

    //Metodo que sirve para mover la camara.
    public void moveCamera(View view) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(18.36138308979571, -100.68158861249685)).zoom(15).build();
        googlemap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    //Metodo que sirve para realizar una animacion en la camara.
    public void animateCamera(View view) {
        if (googlemap.getMyLocation()!= null)
            googlemap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(googlemap.getMyLocation().getLatitude(),googlemap.getMyLocation().getLongitude()),15));
    }

    //Metodo que sirve para añadir los marcadores utilizando un boton.
    public void addMarker(View view) {
        googlemap.addMarker(new MarkerOptions().position(new LatLng(googlemap.getCameraPosition().target.latitude, googlemap.getCameraPosition().target.longitude)));
    }

    //Metodo que sirve para añadir los marcadores pulsando sobre el mapa.
    @Override
    public void onMapClick(LatLng puntoPulsado) {
        googlemap.addMarker(new MarkerOptions().position(puntoPulsado).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //Se crea un menu con diferentes opciones.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //Dependiendo de que opcion seleccione cambiara el tipo de mapa, como se muestra a continuacion.
        if (id == R.id.uno) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_NORMAL));
            return true;
        }
        if (id == R.id.dos) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_HYBRID));
            return true;
        }
        if (id == R.id.tres) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_SATELLITE));
            return true;
        }
        if (id == R.id.cuatro) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_TERRAIN));
            return true;
        }
        if (id == R.id.quinto) {
            googlemap.setMapType((GoogleMap.MAP_TYPE_NONE));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
