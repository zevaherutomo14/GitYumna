package wardiman.com.yumna.response;

public class ArtikelItem {
    //encapsulasi
    public String id;

    public String judul;

    public String tempat;

    public String waktu;

    public String catatan;

    public String tanggal;

    public String poster;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
//        Samakan id artikel item dengan id di PHP nya
        return
                "ArtikelItem{" +
                        "tempat = '" + tempat + '\''+
                        ",waktu = '" + waktu + '\'' +
                        ",tanggal = '" + tanggal + '\'' +
                        ",catatan = '" + catatan + '\'' +
                        ",poster = '" + poster + '\'' +
                        "}";
    }
}
