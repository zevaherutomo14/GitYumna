package wardiman.com.yumna.Quiz;

public class SoalEssay {

    public String pertanyaan[] = {
            "Siapa nama presiden Indonesia yang pertama ?",
            "Ideologi dasar bagi negara Indonesia adalah ...",
            "Bhinneka Tunggal Ika mempunyai arti ...",
            "Ibukota negara Indonesia saat ini adalah ...",
            "Siapa yang menjajah Indonesia selama 350 tahun ?",
            "Saat masa penjajahan, senjata yang biasa digunakan oleh pahlawan Indonesia adalah ...",
            "Monumen pada gambar sering disebut ...",
            "Teks yang dibacakan Ir. Soekarno yang menyatakan Indonesia merdeka dalah teks ...",
            "Pulau terbesar di Indonesia adalah ...",
    };

    private String image[] = {
            "foto_soekarno",
            "lambang_garuda",
            "lambang_garuda",
            "jakarta",
            "penjajahan",
            "bambu_runcing",
            "monas",
            "teks_proklamasi",
            "pulau_kalimantan",

    };

    private String jawabanBenar[] = {

            "Ir. Soekarno",
            "Pancasila",
            "Berbeda-beda tetapi tetap satu",
            "Jakarta",
            "Belanda",
            "Bambu runcing",
            "Monas",
            "Proklamasi",
            "Kalimantan",
    };

    public String getPertanyaan(int x) {
        String soal = pertanyaan[x];
        return soal;
    }

    public String getImage(int x) {
        String gambar = image[x];
        return  gambar;
    }

    public String getJawabanBenar(int x) {
        String jawaban = jawabanBenar[x];
        return jawaban;
    }
}
