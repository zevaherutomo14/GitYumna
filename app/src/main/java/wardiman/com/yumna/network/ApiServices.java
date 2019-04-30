package wardiman.com.yumna.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import wardiman.com.yumna.model.ModelJadwal;
import wardiman.com.yumna.response.BeritaItem;
import wardiman.com.yumna.response.ResponseArtikel;
import wardiman.com.yumna.response.ResponseBerita;

public interface ApiServices {
    @GET("tampil_berita.php")
    Call<ResponseBerita> request_show_all_berita();

    @GET("tampil_artikel.php")
    Call<ResponseArtikel> request_show_all_artikel();

    @GET("{periode}/{date}.json")
    Call<ModelJadwal> getJadwalSholat(@Path("periode") String periode, @Path("date") String date);

    @GET("getcontacts.php")
    Call<List<BeritaItem>> getContact(
            @Query("item_type") String item_type,
            @Query("key") String keyword);
}
