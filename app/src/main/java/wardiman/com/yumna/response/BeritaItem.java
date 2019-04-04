package wardiman.com.yumna.response;

import com.google.gson.annotations.SerializedName;

public class BeritaItem {


    @SerializedName("penulis")
    private String penulis;

    @SerializedName("images")
    private String foto;

    @SerializedName("id")
    private String id;

    @SerializedName("judul_berita")
    private String judulBerita;

    @SerializedName("tanggal_posting")
    private String tanggalPosting;

    @SerializedName("isi_berita")
    private String isiBerita;

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJudulBerita() {
        return judulBerita;
    }

    public void setJudulBerita(String judulBerita) {
        this.judulBerita = judulBerita;
    }

    public String getTanggalPosting() {
        return tanggalPosting;
    }

    public void setTanggalPosting(String tanggalPosting) {
        this.tanggalPosting = tanggalPosting;
    }

    public String getIsiBerita() {
        return isiBerita;
    }

    public void setIsiBerita(String isiBerita) {
        this.isiBerita = isiBerita;
    }

    @Override
    public String toString(){
        return
                "BeritaItem{" +
                "penulis = '" + penulis + '\''+
                ",foto = '" + foto + '\'' +
                ",id = '" + id + '\'' +
                ",judul_berita = '" + judulBerita + '\'' +
                ",tanggal_posting = '" + tanggalPosting + '\'' +
                ",isi_berita = '" + isiBerita + '\'' +
                        "}";
    }
}

