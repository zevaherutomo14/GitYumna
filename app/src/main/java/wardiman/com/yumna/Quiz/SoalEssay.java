package wardiman.com.yumna.Quiz;

public class SoalEssay {
    public String pertanyaan[] = {
            "Seseorang menjadi mualaf adalah karena kebiasaan yang dimiliki orang tersebut untuk mempelajari tentang ajaran agama islam, di mana pada akhirnya mereka merasa mendapatkan hidayah dari Allah SWT dan kemudian memutuskan untuk masuk islam, dari pernyataan di atas salah satu alasan menjeadi mualaf karena ...",
            "Jika Seseorang yang memutuskan menjadi mualaf meskipun hal tersebut dikarenakan cinta atau pernikahan, salah satu alasan menjeadi mualaf karena ...",
            "Allah SWT akan memberikan Rizki yang cukup bagi mereka yang masuk islam, dan Allah SWT akan menjadikannya hamba Allah yang qana’ah. semua itu termasuk pada ... dan Keutamaan menjadi seorang Mualaf",
            "Kalimat pertama dalam syahadat berisi ASYHADU AN LA ILAHA ILLA ALLAH yang berarti tuhan kita hanya satu yaitu...",
            "Pada Kalimat Kedua wa ?ašhadu ?anna mu?ammadar rasulu l-Lah, siapa yang kita yakini ialah utusan Allah...",
            "Bhinneka Tunggal Ika mempunyai arti ...",
            "Ibukota negara Indonesia saat ini adalah ...",
            "Siapa yang menjajah Indonesia selama 350 tahun ?",
            "Saat masa penjajahan, senjata yang biasa digunakan oleh pahlawan Indonesia adalah ...",
            "Monumen pada gambar sering disebut ...",
            "Teks yang dibacakan Ir. Soekarno yang menyatakan Indonesia merdeka dalah teks ...",
            "Pulau terbesar di Indonesia adalah ...",
    };

    public String syahadat[] = {
            "apa yang dimaksud syahadatain",
            "sebutkanlah syahadat tauhid",
            "sebutkanlah syahadat rasul",
            "apa syarat syah mengucap syahadatain",
            "apa arti wa asyhadu anna muhammadar rasulullah",
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

    private String JwbBnrSy[] = {

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

    public String getSyahadat(int x){
        String soalSy = syahadat[x];
        return  soalSy;
    }

    public String getImage(int x) {
        String gambar = image[x];
        return  gambar;
    }

    public String getJawabanBenar(int x) {
        String jawaban = jawabanBenar[x];
        return jawaban;
    }

    public String getJwbBnrSy(int x){
        String jwbSy = JwbBnrSy[x];
        return jwbSy;
    }
}
