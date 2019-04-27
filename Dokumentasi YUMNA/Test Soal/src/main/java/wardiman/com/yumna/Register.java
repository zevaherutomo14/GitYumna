package wardiman.com.yumna;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import wardiman.com.yumna.app.AppController;
import wardiman.com.yumna.network.Server;

public class Register extends AppCompatActivity {

    ProgressDialog pDialog;
    Button btn_Register, btn_Login;
    EditText txt_username, txt_email, txt_password, txt_confirm;
    Intent intent;

    int success;
    ConnectivityManager conMngr;

    private String url = Server.URL + "register.php";

    private static final String TAG = Register.class.getSimpleName();

    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    String tag_json_ob = "json_obj_req";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        conMngr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        {
            if (conMngr.getActiveNetworkInfo() != null
                    && conMngr.getActiveNetworkInfo().isConnected()
                    && conMngr.getActiveNetworkInfo().isAvailable()){
            } else {
                Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
            }
        }

        btn_Login = findViewById(R.id.btnLogin);
        btn_Register = findViewById(R.id.btnRegister);
        txt_username = findViewById(R.id.username);
        txt_email = findViewById(R.id.email);
        txt_password = findViewById(R.id.password);
        txt_confirm = findViewById(R.id.confirm);

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Register.this, Login.class);
                finish();
                startActivity(intent);
            }
        });

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txt_username.getText().toString();
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                String confirm  = txt_confirm.getText().toString();
                if (conMngr.getActiveNetworkInfo() != null
                        && conMngr.getActiveNetworkInfo().isConnected()
                        && conMngr.getActiveNetworkInfo().isAvailable()){
                    checkRegister(username,email,password,confirm);
                } else {
                    Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void checkRegister(final String username, final String email, final String password, final String confirm){
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Register ....");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e(TAG, "Register Response: " + response);
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    success = jObj.getInt(TAG_SUCCESS);
                    if (success == 1) {
                        Log.e("Successfully Register", jObj.toString());

                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();

                        txt_username.setText("");
                        txt_email.setText("");
                        txt_password.setText("");
                        txt_confirm.setText("");
                    } else {
                        Toast.makeText(getApplicationContext(), jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG,"Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();

                hideDialog();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                //polimorphism
                Map<String, String> params = new HashMap<String, String>();
                params.put("username",username);
                params.put("email", email);
                params.put("password", password);
                params.put("confirm_password", confirm);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(strReq, tag_json_ob);


    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onBackPressed() {
        intent = new Intent(Register.this, Login.class);
        finish();
        startActivity(intent);
    }
}
