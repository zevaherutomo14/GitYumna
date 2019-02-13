package wardiman.com.yumna.network;

import retrofit2.Call;
import retrofit2.http.GET;
import wardiman.com.yumna.response.ResponseArtikel;
import wardiman.com.yumna.response.ResponseBerita;

public interface ApiServices {
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();

    @GET("tampil_artikel.php")
    Call<ResponseArtikel> request_show_all_artikel();
}
