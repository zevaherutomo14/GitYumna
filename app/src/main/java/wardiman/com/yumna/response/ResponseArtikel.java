package wardiman.com.yumna.response;

import java.util.List;

public class ResponseArtikel {

    private List<ArtikelItem> artikel;

    private boolean status;

    public List<ArtikelItem> getArtikel() {
        return artikel;
    }

    public void setArtikel(List<ArtikelItem> artikel) {
        this.artikel = artikel;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){

//        Respon yang akan muncul ketika data di get data dgn benar atau tidak
        return
                "ResponseArtikel{" +
                        "artikel = '" + artikel + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
