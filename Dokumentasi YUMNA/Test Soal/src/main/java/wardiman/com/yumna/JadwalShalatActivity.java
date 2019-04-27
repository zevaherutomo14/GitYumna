package wardiman.com.yumna;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wardiman.com.yumna.model.Item;
import wardiman.com.yumna.model.ModelJadwal;
import wardiman.com.yumna.network.ApiServices;
import wardiman.com.yumna.network.ApiUrl;

public class JadwalShalatActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    String zuhur, ashar, magrib, isya, subuh, tanggal;
    List<Item> jadwal;
    private TextView tv_lokasi_value, tv_fajr_value, tv_shurooq_value,
            tv_dhuhr_value, tv_asr_value, tv_maghrib_value, tv_isha_value, tv_jadwal;
    private ProgressDialog progressDialog;
    Location location;
    String lokasi;
    SwipeRefreshLayout swipeId;
    Calendar calander;
    SimpleDateFormat simpledateformat;
    String Date;

    private FloatingActionButton fab_refresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_shalat);

        tv_lokasi_value = (TextView) findViewById(R.id.tv_lokasi_value);
        tv_fajr_value = (TextView)findViewById(R.id.tv_fajr_value);
        tv_dhuhr_value = (TextView) findViewById(R.id.tv_dhuhr_value);
        tv_asr_value = (TextView) findViewById(R.id.tv_asr_value);
        tv_maghrib_value = (TextView) findViewById(R.id.tv_maghrib_value);
        tv_isha_value = (TextView) findViewById(R.id.tv_isha_value);
        tv_jadwal = (TextView) findViewById(R.id.tv_jadwal);
//        swipeId = (SwipeRefreshLayout) findViewById(R.id.swipe_id);

        fab_refresh = findViewById(R.id.fab_refresh);

        getJadwal();

        fab_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getJadwal();
            }
        });

    }

//    private void refresh() {
//        swipeId.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                swipeId.setRefreshing(true);
//                getJadwal();
//            }
//        });
//
//    }

    private void getJadwal() {

        //untuk get location
        String[] perms = {Manifest.permission.ACCESS_FINE_LOCATION};
        if (!EasyPermissions.hasPermissions(getApplicationContext(), perms)) {
            EasyPermissions.requestPermissions(JadwalShalatActivity.this, "Butuh Permission Location", 10, perms);
        } else{

            progressDialog = new ProgressDialog(JadwalShalatActivity.this);
            progressDialog.setMessage("Please Wait / Silahkan tunggu ...");
            progressDialog.show();

            //untuk get location
            FusedLocationProviderClient mFusedLocation = LocationServices.getFusedLocationProviderClient(this);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            //untuk get location
            mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        // Do it all with location
                        //Log.d("My Current location", "Lat : " + location.getLatitude() + " Long : " + location.getLongitude());
                        // Display in Toast
                        Geocoder gcd3 = new Geocoder(getBaseContext(), Locale.getDefault());
                        List<Address> addresses;

                        try {
                            addresses = gcd3.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            if (addresses.size() > 0)

                            {
                                lokasi = addresses.get(0).getLocality().toString();

                                if (lokasi != null) {


                                    calander = Calendar.getInstance();
                                    simpledateformat = new SimpleDateFormat("dd-MM-yyyy");
                                    Date = simpledateformat.format(calander.getTime());

                                    //melakukan get melalui API
                                    ApiServices apiInterface = ApiUrl.getClient().create(ApiServices.class);
                                    Call<ModelJadwal> call = apiInterface.getJadwalSholat(lokasi, Date);
                                    call.enqueue(new Callback<ModelJadwal>() {
                                        @Override
                                        public void onResponse(Call<ModelJadwal> call, Response<ModelJadwal> response) {

                                            jadwal = response.body().getItems();
                                            if (response.isSuccessful()) {
                                                if (jadwal != null) {
                                                    zuhur = jadwal.get(0).getDhuhr();
                                                    ashar = jadwal.get(0).getAsr();
                                                    magrib = jadwal.get(0).getMaghrib();
                                                    isya = jadwal.get(0).getIsha();
                                                    subuh = jadwal.get(0).getFajr();

                                                    Log.d("respon :", "" + zuhur);
                                                    tv_dhuhr_value.setText(zuhur);
                                                    tv_asr_value.setText(ashar);
                                                    tv_maghrib_value.setText(magrib);
                                                    tv_isha_value.setText(isya);
                                                    tv_fajr_value.setText(subuh);

//                                                        Log.d("", "lokasi" + lokasi);
                                                    tv_lokasi_value.setText(lokasi);
                                                    tv_jadwal.setText(Date);
                                                    progressDialog.dismiss();
                                                } else {
                                                    Toast.makeText(JadwalShalatActivity.this, "Error Network", Toast.LENGTH_SHORT).show();
                                                    progressDialog.dismiss();
                                                }
                                            }
                                        }
                                        @Override
                                        public void onFailure(Call<ModelJadwal> call, Throwable t) {
                                            //Log.d("Data ", "" + t.getMessage());
                                            progressDialog.dismiss();
                                        }
                                    });

                                }
                            }

                        } catch (NullPointerException e) {
                            e.printStackTrace();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

        }

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        if (requestCode == 10) {
            getJadwal();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {

    }
}

