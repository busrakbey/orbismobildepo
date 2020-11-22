package EntityLayer.Ortak;

import java.util.List;

import AnnotationLayer.Column;
import AnnotationLayer.Table;

/**
 * Created by isahin on 19.1.2017.
 */
@Table(name = "GUZERGAH")
public class MobilGuzergah  extends BaseEntity {


    @Column(name = "id", nullable = false)
    private Long	id	= null;


    @Column(name = "mid")
    private Long mid;

    @Column(name = "mustid")
    private Long mustid;

    @Column(name = "not_mid")
    private Long not_mid;

    @Column(name = "NOT_ID", nullable = false)
    private Long	NOT_ID	= null;

    @Column(name = "X_KOOR", nullable = false)
    private String	X_KOOR	= null;


    @Column(name = "Y_KOOR", nullable = false)
    private String Y_KOOR	= null;



    @Column(name = "UTM_X_KOOR", nullable = false)
    private String	UTM_X_KOOR	= null;


    @Column(name = "UTM_Y_KOOR", nullable = false)
    private String UTM_Y_KOOR	= null;


    @Column(name = "YOL_DURUM_KOD", nullable = false)
    private String YOL_DURUM_KOD	= null;


    @Column(name = "SIRA_NO", nullable = false)
    private String SIRA_NO	= null;


    @Column(name = "TUL", nullable = false)
    private String TUL	= null;



    @Column(name = "OBJECTID", nullable = true)
    private Long OBJECTID;

    //@Column(name = "BIRIM_ID", nullable = true)
   // private Long BIRIM_ID;

   // @Column(name = "ACIKLAMA ", nullable = true)
  //  private String ACIKLAMA ;

    @Column(name = "GLOBALID", nullable = false)
    private String GLOBALID;


    @Column(name = "CREATED_USER", nullable = true)
    private String CREATED_USER ;

    @Column(name = "CREATED_DATE", nullable = true)
    private String CREATED_DATE ;

    @Column(name = "LAST_EDITED_USER", nullable = true)
    private String LAST_EDITED_USER ;

    @Column(name = "LAST_EDITED_DATE", nullable = true)
    private String LAST_EDITED_DATE ;

    @Column(name = "gonderildi", nullable = true)
    private Integer gonderildi ;

    @Column(name = "yolBilgiId", nullable = true)
    private String yolBilgiId ;


    @Column(name = "geotype", nullable = true)
    private Integer geotype ;

    @Column(name = "paths", nullable = true)
    private List<List<Double []>>paths ;

    public String getYolBilgiId() {
        return yolBilgiId;
    }

    public void setYolBilgiId(String yolBilgiId) {
        this.yolBilgiId = yolBilgiId;
    }

    public String getUTM_X_KOOR() {
        return UTM_X_KOOR;
    }

    public void setUTM_X_KOOR(String UTM_X_KOOR) {
        this.UTM_X_KOOR = UTM_X_KOOR;
    }

    public String getUTM_Y_KOOR() {
        return UTM_Y_KOOR;
    }

    public void setUTM_Y_KOOR(String UTM_Y_KOOR) {
        this.UTM_Y_KOOR = UTM_Y_KOOR;
    }

    public String getYOL_DURUM_KOD() {
        return YOL_DURUM_KOD;
    }

    public void setYOL_DURUM_KOD(String YOL_DURUM_KOD) {
        this.YOL_DURUM_KOD = YOL_DURUM_KOD;
    }

    public String getSIRA_NO() {
        return SIRA_NO;
    }

    public void setSIRA_NO(String SIRA_NO) {
        this.SIRA_NO = SIRA_NO;
    }

    public String getTUL() {
        return TUL;
    }

    public void setTUL(String TUL) {
        this.TUL = TUL;
    }

    public List<List<Double []>> getPaths() {
        return paths;
    }

    public void setPaths(List<List<Double []>>paths) {
        this.paths = paths;
    }

    public Integer getGeotype() {
        return geotype;
    }

    public void setGeotype(Integer geotype) {
        this.geotype = geotype;
    }

    @Override
    public Integer getGonderildi() {
        return gonderildi;
    }

    @Override
    public void setGonderildi(Integer gonderildi) {
        this.gonderildi = gonderildi;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getMid() {
        return mid;
    }

    @Override
    public void setMid(Long mid) {
        this.mid = mid;
    }

    @Override
    public Long getMustid() {
        return mustid;
    }

    @Override
    public void setMustid(Long mustid) {
        this.mustid = mustid;
    }

    public Long getNot_mid() {
        return not_mid;
    }

    public void setNot_mid(Long not_mid) {
        this.not_mid = not_mid;
    }

    public Long getNOT_ID() {
        return NOT_ID;
    }

    public void setNOT_ID(Long NOT_ID) {
        this.NOT_ID = NOT_ID;
    }

    public String getX_KOOR() {
        return X_KOOR;
    }

    public void setX_KOOR(String x_KOOR) {
        X_KOOR = x_KOOR;
    }

    public String getY_KOOR() {
        return Y_KOOR;
    }

    public void setY_KOOR(String y_KOOR) {
        Y_KOOR = y_KOOR;
    }

    @Override
    public Long getOBJECTID() {
        return OBJECTID;
    }

    public void setOBJECTID(Long OBJECTID) {
        this.OBJECTID = OBJECTID;
    }

  /*  public Long getBIRIM_ID() {
        return BIRIM_ID;
    }

    public void setBIRIM_ID(Long BIRIM_ID) {
        this.BIRIM_ID = BIRIM_ID;
    }

    public String getACIKLAMA() {
        return ACIKLAMA;
    }

    public void setACIKLAMA(String ACIKLAMA) {
        this.ACIKLAMA = ACIKLAMA;
    }*/

    public String getGLOBALID() {
        return GLOBALID;
    }

    public void setGLOBALID(String GLOBALID) {
        this.GLOBALID = GLOBALID;
    }

    public String getCREATED_USER() {
        return CREATED_USER;
    }

    public void setCREATED_USER(String CREATED_USER) {
        this.CREATED_USER = CREATED_USER;
    }

    public String getCREATED_DATE() {
        return CREATED_DATE;
    }

    public void setCREATED_DATE(String CREATED_DATE) {
        this.CREATED_DATE = CREATED_DATE;
    }

    public String getLAST_EDITED_USER() {
        return LAST_EDITED_USER;
    }

    public void setLAST_EDITED_USER(String LAST_EDITED_USER) {
        this.LAST_EDITED_USER = LAST_EDITED_USER;
    }

    public String getLAST_EDITED_DATE() {
        return LAST_EDITED_DATE;
    }

    public void setLAST_EDITED_DATE(String LAST_EDITED_DATE) {
        this.LAST_EDITED_DATE = LAST_EDITED_DATE;
    }
}
