package wardiman.com.yumna.response;

import java.util.List;


public class ResponseBerita {

    private List<BeritaItem> berita;

    private boolean status;

    public List<BeritaItem> getBerita() {
        return berita;
    }

    public void setBerita(List<BeritaItem> berita) {
        this.berita = berita;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString(){
        return
                "ResponseBerita{" +
                        "berita = '" + berita + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
