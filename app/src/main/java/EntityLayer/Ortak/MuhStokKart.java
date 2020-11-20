package EntityLayer.Ortak;

import java.math.BigDecimal;

/**
 * Created by isahin on 30.3.2017.
 */
public class MuhStokKart {


    @Override
    public String toString()
    {
        return adi;
    }

    private Long id	= null;
    private String tur	= null;
    private String adi	= null;
    private String latinceAdiTur	= null;
    private Boolean aktif	= null;
    private String cins	= null;
    private String cinsDigerAciklama	= null;
    private String turDigerAciklama	= null;
    private String kategori1	= null;
    private String kategori2	= null;
    private String kategori3	= null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getLatinceAdiTur() {
        return latinceAdiTur;
    }

    public void setLatinceAdiTur(String latinceAdiTur) {
        this.latinceAdiTur = latinceAdiTur;
    }

    public Boolean getAktif() {
        return aktif;
    }

    public void setAktif(Boolean aktif) {
        this.aktif = aktif;
    }

    public String getCins() {
        return cins;
    }

    public void setCins(String cins) {
        this.cins = cins;
    }

    public String getCinsDigerAciklama() {
        return cinsDigerAciklama;
    }

    public void setCinsDigerAciklama(String cinsDigerAciklama) {
        this.cinsDigerAciklama = cinsDigerAciklama;
    }

    public String getTurDigerAciklama() {
        return turDigerAciklama;
    }

    public void setTurDigerAciklama(String turDigerAciklama) {
        this.turDigerAciklama = turDigerAciklama;
    }

    public String getKategori1() {
        return kategori1;
    }

    public void setKategori1(String kategori1) {
        this.kategori1 = kategori1;
    }

    public String getKategori2() {
        return kategori2;
    }

    public void setKategori2(String kategori2) {
        this.kategori2 = kategori2;
    }

    public String getKategori3() {
        return kategori3;
    }

    public void setKategori3(String kategori3) {
        this.kategori3 = kategori3;
    }

    private Long	superId	= null;

    /**
     * OrtakMalzemeKart nesnesine referansÄ± dÃ¶ndÃ¼rÃ¼r
     **/
    @Deprecated
    public Long getSuperId() {
        return superId;
    }

    /**
     * OrtakMalzemeKart nesnesine referansÄ± deÄiÅtirir
     **/
    @Deprecated
    public void setSuperId(Long superId) {
        this.superId = superId;
    }



    private Long	kategoriId	= null;

    public Long getKategoriId() {
        return this.kategoriId;
    }

    public void setKategoriId(final Long kategoriId) {
        this.kategoriId = kategoriId;
    }


    private String	stokNo	= null;

    public String getStokNo() {
        return this.stokNo;
    }

    public void setStokNo(final String stokNo) {
        this.stokNo = stokNo;
    }


    private String	barkod	= null;

    public String getBarkod() {
        return this.barkod;
    }

    public void setBarkod(final String barkod) {
        this.barkod = barkod;
    }


    private Integer	stokTipi	= null;

    public Integer getStokTipi() {
        return this.stokTipi;
    }

    public void setStokTipi(final Integer stokTipi) {
        this.stokTipi = stokTipi;
    }


    private Integer	kdvOrani	= null;

    public Integer getKdvOrani() {
        return this.kdvOrani;
    }

    public void setKdvOrani(final Integer kdvOrani) {
        this.kdvOrani = kdvOrani;
    }


    private Integer	stoktaKalmaSuresi	= null;

    public Integer getStoktaKalmaSuresi() {
        return this.stoktaKalmaSuresi;
    }

    public void setStoktaKalmaSuresi(final Integer stoktaKalmaSuresi) {
        this.stoktaKalmaSuresi = stoktaKalmaSuresi;
    }


    private Integer	rafOmru	= null;

    public Integer getRafOmru() {
        return this.rafOmru;
    }

    public void setRafOmru(final Integer rafOmru) {
        this.rafOmru = rafOmru;
    }


    private Integer	girisCikisYontemi	= null;

    public Integer getGirisCikisYontemi() {
        return this.girisCikisYontemi;
    }

    public void setGirisCikisYontemi(final Integer girisCikisYontemi) {
        this.girisCikisYontemi = girisCikisYontemi;
    }


    private Boolean	demirbas	= false;

    public Boolean getDemirbas() {
        return this.demirbas;
    }

    public void setDemirbas(final Boolean demirbas) {
        this.demirbas = demirbas;
    }


    private Long	amortismanGelirHesapId	= null;

    public Long getAmortismanGelirHesapId() {
        return this.amortismanGelirHesapId;
    }

    public void setAmortismanGelirHesapId(final Long amortismanGelirHesapId) {
        this.amortismanGelirHesapId = amortismanGelirHesapId;
    }


    private Long	amortismanGiderHesapId	= null;

    public Long getAmortismanGiderHesapId() {
        return this.amortismanGiderHesapId;
    }

    public void setAmortismanGiderHesapId(final Long amortismanGiderHesapId) {
        this.amortismanGiderHesapId = amortismanGiderHesapId;
    }


    private Integer	amortismanOrani	= null;

    public Integer getAmortismanOrani() {
        return this.amortismanOrani;
    }

    public void setAmortismanOrani(final Integer amortismanOrani) {
        this.amortismanOrani = amortismanOrani;
    }


    private Long	degerlemeHesapId	= null;

    public Long getDegerlemeHesapId() {
        return this.degerlemeHesapId;
    }

    public void setDegerlemeHesapId(final Long degerlemeHesapId) {
        this.degerlemeHesapId = degerlemeHesapId;
    }


    private Long	kiymetHesapId	= null;

    public Long getKiymetHesapId() {
        return this.kiymetHesapId;
    }

    public void setKiymetHesapId(final Long kiymetHesapId) {
        this.kiymetHesapId = kiymetHesapId;
    }


    private Long	girisHesapId	= null;

    public Long getGirisHesapId() {
        return this.girisHesapId;
    }

    public void setGirisHesapId(final Long girisHesapId) {
        this.girisHesapId = girisHesapId;
    }


    private Long	cikisHesapId	= null;

    public Long getCikisHesapId() {
        return this.cikisHesapId;
    }

    public void setCikisHesapId(final Long cikisHesapId) {
        this.cikisHesapId = cikisHesapId;
    }


    private Long	girisKdv	= null;

    public Long getGirisKdv() {
        return this.girisKdv;
    }

    public void setGirisKdv(final Long girisKdv) {
        this.girisKdv = girisKdv;
    }


    private Long	cikis_kdv_id	= null;

    public Long getCikis_kdv_id() {
        return this.cikis_kdv_id;
    }

    public void setCikis_kdv_id(final Long cikis_kdv_id) {
        this.cikis_kdv_id = cikis_kdv_id;
    }


    private Long	girisOtvId	= null;

    public Long getGirisOtvId() {
        return this.girisOtvId;
    }

    public void setGirisOtvId(final Long girisOtvId) {
        this.girisOtvId = girisOtvId;
    }


    private Long	cikisOtv	= null;

    public Long getCikisOtv() {
        return this.cikisOtv;
    }

    public void setCikisOtv(final Long cikisOtv) {
        this.cikisOtv = cikisOtv;
    }


    private Long	girisIskonto	= null;

    public Long getGirisIskonto() {
        return this.girisIskonto;
    }

    public void setGirisIskonto(final Long girisIskonto) {
        this.girisIskonto = girisIskonto;
    }


    private Long	cikisIskonto	= null;

    public Long getCikisIskonto() {
        return this.cikisIskonto;
    }

    public void setCikisIskonto(final Long cikisIskonto) {
        this.cikisIskonto = cikisIskonto;
    }


    private String	isoNumarasi	= null;

    public String getIsoNumarasi() {
        return this.isoNumarasi;
    }

    public void setIsoNumarasi(final String isoNumarasi) {
        this.isoNumarasi = isoNumarasi;
    }


    private String	marka	= null;

    public String getMarka() {
        return this.marka;
    }

    public void setMarka(final String marka) {
        this.marka = marka;
    }


    private String	aciklama	= null;

    public String getAciklama() {
        return this.aciklama;
    }

    public void setAciklama(final String aciklama) {
        this.aciklama = aciklama;
    }


    private Long	miktarOlcuBirimId	= null;

    public Long getMiktarOlcuBirimId() {
        return this.miktarOlcuBirimId;
    }

    public void setMiktarOlcuBirimId(final Long miktarOlcuBirimId) {
        this.miktarOlcuBirimId = miktarOlcuBirimId;
    }



    private Long	uzunlukOlcuBirimId	= null;

    public Long getUzunlukOlcuBirimId() {
        return this.uzunlukOlcuBirimId;
    }

    public void setUzunlukOlcuBirimId(final Long uzunlukOlcuBirimId) {
        this.uzunlukOlcuBirimId = uzunlukOlcuBirimId;
    }


    private Long	odunTuruId	= null;

    public Long getOdunTuruId() {
        return this.odunTuruId;
    }

    public void setOdunTuruId(final Long odunTuruId) {
        this.odunTuruId = odunTuruId;
    }




    private Long	agacTuruId	= null;

    public Long getAgacTuruId() {
        return this.agacTuruId;
    }

    public void setAgacTuruId(final Long agacTuruId) {
        this.agacTuruId = agacTuruId;
    }




    private Integer	sinifKodu	= null;

    public Integer getSinifKodu() {
        return this.sinifKodu;
    }

    public void setSinifKodu(final Integer sinifKodu) {
        this.sinifKodu = sinifKodu;
    }

    private Integer	boyKodu	= null;

    public Integer getBoyKodu() {
        return this.boyKodu;
    }

    public void setBoyKodu(final Integer boyKodu) {
        this.boyKodu = boyKodu;
    }

    private Boolean	tevziMasrafinaTabi	= null;

    public Boolean getTevziMasrafinaTabi() {
        return this.tevziMasrafinaTabi;
    }

    public void setTevziMasrafinaTabi(final Boolean tevziMasrafinaTabi) {
        this.tevziMasrafinaTabi = tevziMasrafinaTabi;
    }


    private Integer	capKodu	= null;

    /**
     * 0-Kl. 1-İn. 2-Paç
     */
    public Integer getCapKodu() {
        return this.capKodu;
    }

    /**
     * 0-Kl. 1-İn. 2-Paç
     */
    public void setCapKodu(final Integer capKodu) {
        this.capKodu = capKodu;
    }


    private Integer	ebatKodu	= null;

    /**
     * kerestelik,soyma kaplamalık vb..
     */
    public Integer getEbatKodu() {
        return this.ebatKodu;
    }

    /**
     * kerestelik,soyma kaplamalık vb..
     */
    public void setEbatKodu(final Integer ebatKodu) {
        this.ebatKodu = ebatKodu;
    }


    private Integer	sayimOlcuTuru	= null;

    public Integer getSayimOlcuTuru() {
        return this.sayimOlcuTuru;
    }

    public void setSayimOlcuTuru(final Integer sayimOlcuTuru) {
        this.sayimOlcuTuru = sayimOlcuTuru;
    }


    private BigDecimal kesmeTarifeBedeli	= null;

    public BigDecimal getKesmeTarifeBedeli() {
        return this.kesmeTarifeBedeli;
    }

    public void setKesmeTarifeBedeli(final BigDecimal kesmeTarifeBedeli) {
        this.kesmeTarifeBedeli = kesmeTarifeBedeli;
    }


    private String	maktaHesapNo	= null;

    public String getMaktaHesapNo() {
        return this.maktaHesapNo;
    }

    public void setMaktaHesapNo(final String maktaHesapNo) {
        this.maktaHesapNo = maktaHesapNo;
    }


    private String	ormanIciHesapNo	= null;

    public String getOrmanIciHesapNo() {
        return this.ormanIciHesapNo;
    }

    public void setOrmanIciHesapNo(final String ormanIciHesapNo) {
        this.ormanIciHesapNo = ormanIciHesapNo;
    }


    private String	ormanDisiHesapNo	= null;

    public String getOrmanDisiHesapNo() {
        return this.ormanDisiHesapNo;
    }

    public void setOrmanDisiHesapNo(final String ormanDisiHesapNo) {
        this.ormanDisiHesapNo = ormanDisiHesapNo;
    }


    private Boolean	ormanEmvali	= false;

    public Boolean getOrmanEmvali() {
        return this.ormanEmvali;
    }

    public void setOrmanEmvali(final Boolean ormanEmvali) {
        this.ormanEmvali = ormanEmvali;
    }


    private Boolean	birebirTakipEdilir	= false;

    /**
     * ürün stok girişeri ve çıkışlarında birebir takip edilir. ürün seçimi ile birebir takip edilir.
     */
    public Boolean getBirebirTakipEdilir() {
        return this.birebirTakipEdilir;
    }

    /**
     * ürün stok girişeri ve çıkışlarında birebir takip edilir. ürün seçimi ile birebir takip edilir.
     */
    public void setBirebirTakipEdilir(final Boolean birebirTakipEdilir) {
        this.birebirTakipEdilir = birebirTakipEdilir;
    }


    private Boolean	hamOrmanEmvali	= false;

    public Boolean getHamOrmanEmvali() {
        return this.hamOrmanEmvali;
    }

    public void setHamOrmanEmvali(final Boolean hamOrmanEmvali) {
        this.hamOrmanEmvali = hamOrmanEmvali;
    }


    private Boolean	tasinir	= false;

    public Boolean getTasinir() {
        return this.tasinir;
    }

    public void setTasinir(final Boolean tasinir) {
        this.tasinir = tasinir;
    }


    private Boolean	tasinmaz	= false;

    public Boolean getTasinmaz() {
        return this.tasinmaz;
    }

    public void setTasinmaz(final Boolean tasinmaz) {
        this.tasinmaz = tasinmaz;
    }


    private Boolean	sarfMalzeme	= false;

    public Boolean getSarfMalzeme() {
        return this.sarfMalzeme;
    }

    public void setSarfMalzeme(final Boolean sarfMalzeme) {
        this.sarfMalzeme = sarfMalzeme;
    }


    private Boolean	ozmTemini	= false;

    public Boolean getOzmTemini() {
        return this.ozmTemini;
    }

    public void setOzmTemini(final Boolean ozmTemini) {
        this.ozmTemini = ozmTemini;
    }


    private Boolean	insaatIkmalEmvali	= false;

    public Boolean getInsaatIkmalEmvali() {
        return this.insaatIkmalEmvali;
    }

    public void setInsaatIkmalEmvali(final Boolean insaatIkmalEmvali) {
        this.insaatIkmalEmvali = insaatIkmalEmvali;
    }


    private Boolean	yanginEmvali	= false;

    public Boolean getYanginEmvali() {
        return this.yanginEmvali;
    }

    public void setYanginEmvali(final Boolean yanginEmvali) {
        this.yanginEmvali = yanginEmvali;
    }


    private Boolean	odunDisiEmvali	= false;

    public Boolean getOdunDisiEmvali() {
        return this.odunDisiEmvali;
    }

    public void setOdunDisiEmvali(final Boolean odunDisiEmvali) {
        this.odunDisiEmvali = odunDisiEmvali;
    }


    private Boolean	bilisimEmvali	= false;

    public Boolean getBilisimEmvali() {
        return this.bilisimEmvali;
    }

    public void setBilisimEmvali(final Boolean bilisimEmvali) {
        this.bilisimEmvali = bilisimEmvali;
    }


    private Boolean	haritaEmvali	= false;

    public Boolean getHaritaEmvali() {
        return this.haritaEmvali;
    }

    public void setHaritaEmvali(final Boolean haritaEmvali) {
        this.haritaEmvali = haritaEmvali;
    }


    private Boolean	hizmet	= false;

    public Boolean getHizmet() {
        return this.hizmet;
    }

    public void setHizmet(final Boolean hizmet) {
        this.hizmet = hizmet;
    }


    private Long	turKayitId	= null;

    public Long getTurKayitId() {
        return this.turKayitId;
    }

    public void setTurKayitId(final Long turKayitId) {
        this.turKayitId = turKayitId;
    }


    private Integer	madenDirekBoyKod	= null;

    public Integer getMadenDirekBoyKod() {
        return this.madenDirekBoyKod;
    }

    public void setMadenDirekBoyKod(final Integer madenDirekBoyKod) {
        this.madenDirekBoyKod = madenDirekBoyKod;
    }


    private Integer	minimumStokMiktari	= null;

    public Integer getMinimumStokMiktari() {
        return this.minimumStokMiktari;
    }

    public void setMinimumStokMiktari(final Integer minimumStokMiktari) {
        this.minimumStokMiktari = minimumStokMiktari;
    }


    private String	stokKodu	= null;

    public String getStokKodu() {
        return this.stokKodu;
    }

    public void setStokKodu(final String stokKodu) {
        this.stokKodu = stokKodu;
    }

    private String	faydalanilanKisim	= null;

    /**
     * Odun dışı ürünler için eklendi
     */
    public String getFaydalanilanKisim() {
        return this.faydalanilanKisim;
    }

    /**
     * Odun dışı ürünler için eklendi
     */
    public void setFaydalanilanKisim(final String faydalanilanKisim) {
        this.faydalanilanKisim = faydalanilanKisim;
    }


    private Boolean	fidanlikEmvali	= null;

    public Boolean getFidanlikEmvali() {
        return this.fidanlikEmvali;
    }

    public void setFidanlikEmvali(final Boolean fidanlikEmvali) {
        this.fidanlikEmvali = fidanlikEmvali;
    }


    private String	fidanlikBoyu	= null;

    public String getFidanlikBoyu() {
        return this.fidanlikBoyu;
    }

    public void setFidanlikBoyu(final String fidanlikBoyu) {
        this.fidanlikBoyu = fidanlikBoyu;
    }


    private String	fidanlikYasi	= null;

    public String getFidanlikYasi() {
        return this.fidanlikYasi;
    }

    public void setFidanlikYasi(final String fidanlikYasi) {
        this.fidanlikYasi = fidanlikYasi;
    }


    private String	fidanlikHacmi	= null;

    public String getFidanlikHacmi() {
        return this.fidanlikHacmi;
    }

    public void setFidanlikHacmi(final String fidanlikHacmi) {
        this.fidanlikHacmi = fidanlikHacmi;
    }


    private String	fidanlikAmbalaj	= null;

    public String getFidanlikAmbalaj() {
        return this.fidanlikAmbalaj;
    }

    public void setFidanlikAmbalaj(final String fidanlikAmbalaj) {
        this.fidanlikAmbalaj = fidanlikAmbalaj;
    }


    private String	fidanlikCevre	= null;

    public String getFidanlikCevre() {
        return this.fidanlikCevre;
    }

    public void setFidanlikCevre(final String fidanlikCevre) {
        this.fidanlikCevre = fidanlikCevre;
    }


    private Boolean	musadereEmvali	= false;

    public Boolean getMusadereEmvali() {
        return this.musadereEmvali;
    }

    public void setMusadereEmvali(final Boolean musadereEmvali) {
        this.musadereEmvali = musadereEmvali;
    }


    private String	elbiseTakimCinsi	= null;

    public String getElbiseTakimCinsi() {
        return this.elbiseTakimCinsi;
    }

    public void setElbiseTakimCinsi(final String elbiseTakimCinsi) {
        this.elbiseTakimCinsi = elbiseTakimCinsi;
    }


    private String	elbiseCesidi	= null;

    public String getElbiseCesidi() {
        return this.elbiseCesidi;
    }

    public void setElbiseCesidi(final String elbiseCesidi) {
        this.elbiseCesidi = elbiseCesidi;
    }

    private String	mevcutGomlekBeden	= null;

    public String getMevcutGomlekBeden() {
        return this.mevcutGomlekBeden;
    }

    public void setMevcutGomlekBeden(final String mevcutGomlekBeden) {
        this.mevcutGomlekBeden = mevcutGomlekBeden;
    }

    private String	mevcutParkaBeden	= null;

    public String getMevcutParkaBeden() {
        return this.mevcutParkaBeden;
    }

    public void setMevcutParkaBeden(final String mevcutParkaBeden) {
        this.mevcutParkaBeden = mevcutParkaBeden;
    }

    private String	mevcutPantolonBeden	= null;

    public String getMevcutPantolonBeden() {
        return this.mevcutPantolonBeden;
    }

    public void setMevcutPantolonBeden(final String mevcutPantolonBeden) {
        this.mevcutPantolonBeden = mevcutPantolonBeden;
    }

    private Integer	ayakkabiNo	= null;

    public Integer getAyakkabiNo() {
        return this.ayakkabiNo;
    }

    public void setAyakkabiNo(final Integer ayakkabiNo) {
        this.ayakkabiNo = ayakkabiNo;
    }

    private String	kaskBeden	= null;

    public String getKaskBeden() {
        return this.kaskBeden;
    }

    public void setKaskBeden(final String kaskBeden) {
        this.kaskBeden = kaskBeden;
    }

    private String	eldivenKepBeden	= null;

    public String getEldivenKepBeden() {
        return this.eldivenKepBeden;
    }

    public void setEldivenKepBeden(final String eldivenKepBeden) {
        this.eldivenKepBeden = eldivenKepBeden;
    }

    private Long	digerCinsId	= null;

    public Long getDigerCinsId() {
        return this.digerCinsId;
    }

    public void setDigerCinsId(final Long digerCinsId) {
        this.digerCinsId = digerCinsId;
    }



    private Boolean	hamFidanEmvali	= false;

    public Boolean getHamFidanEmvali() {
        return this.hamFidanEmvali;
    }

    public void setHamFidanEmvali(final Boolean hamFidanEmvali) {
        this.hamFidanEmvali = hamFidanEmvali;
    }
}
