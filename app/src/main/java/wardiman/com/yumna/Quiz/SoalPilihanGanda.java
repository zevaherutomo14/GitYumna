package wardiman.com.yumna.Quiz;

public class SoalPilihanGanda {

    public String pertanyaan[] = {
            "Siapa nama presiden Indonesia yang pertama ?",
            "Ideologi dasar bagi negara Indonesia adalah ...",
            "Bhinneka Tunggal Ika mempunyai arti ...",
            "Ibukota negara Indonesia saat ini adalah ...",
            "Siapa yang menjajah Indonesia selama 350 tahun ?",
            "Saat masa penjajahan, senjata yang biasa digunakan oleh pahlawan Indonesia adalah ...",
            "Monumen untuk mengenang perlawanan dan perjuanagan rakyat Indonesia untuk merebut kemerdekaan dari pemerintahan kolonial Hindia Belanda adalah ...",
            "Teks yang dibacakan Ir. Soekarno yang menyatakan Indonesia merdeka dalah teks ...",
            "Pulau terbesar di Indonesia adalah ...",

    };

    private String PilihanJawaban[][] = {
            {"Ir. Soekarno","Joko Widodo","Susilo Bambang Yudhoyono"},
            {"UUD 1945","Pancasila","Bhinneka Tunggal Ika"},
            {"Berbeda-beda tetapi tetap satu","Bersama selamanya","Bersatu teguh bercerai runtuh"},
            {"Semarang","Surabaya","Jakarta"},
            {"Jepang","Belanda","Malaysia"},
            {"Bambu runcing","Ketapel","Shotgun"},
            {"Tugu Muda","Patung Pancoran","Monas"},
            {"Proklamasi","Pancasila","Pembukaan UUD 1945"},
            {"Jawa","Sumatera","Kalimantan"},
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
        String soal =  pertanyaan[x];
        return soal;
    }

    public String getJawabanBenar(int x) {
        String jawaban = jawabanBenar[x];
        return jawaban;
    }

    public String getPilihanJawaban1(int x) {
        String jawaban1 = PilihanJawaban[x][0];
        return jawaban1;
    }

    public String getPilihanJawaban2(int x) {
        String jawaban2 = PilihanJawaban[x][1];
        return jawaban2;
    }

    public String getPilihanJawaban3(int x) {
        String jawaban3 = PilihanJawaban[x][2];
        return jawaban3;
    }

}
