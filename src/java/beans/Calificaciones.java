package beans;
public class Calificaciones {
    private String matricula;
    private String nombreCompleto;
    private String claveMat;
    private String nombreMat;
    private double mat1;
    private double mat2;
    private double mat3;
    private double mat4;
    private double p1;
    private double p2;
    private double p3;
    private double prom;
    private double extra;

    public Calificaciones() {
    }

    public Calificaciones(String matricula, String nombreMat, double mat1, double mat2, double mat3, double mat4, double prom) {
        this.matricula = matricula;
        this.nombreMat = nombreMat;
        this.mat1 = mat1;
        this.mat2 = mat2;
        this.mat3 = mat3;
        this.mat4 = mat4;
        this.prom = prom;
    }
    
    public Calificaciones(String matricula, String nombreCompleto, String nombreMat, double prom, double extra) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.nombreMat = nombreMat;
        this.prom = prom;
        this.extra = extra;
    }

    public Calificaciones(String matricula, String nombreCompleto, String nombreMat, double p1, double p2, double p3, double prom, double extra) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.nombreMat = nombreMat;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.prom = prom;
        this.extra = extra;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getClaveMat() {
        return claveMat;
    }

    public void setClaveMat(String claveMat) {
        this.claveMat = claveMat;
    }

    public String getNombreMat() {
        return nombreMat;
    }

    public void setNombreMat(String nombreMat) {
        this.nombreMat = nombreMat;
    }

    public double getP1() {
        return p1;
    }

    public void setP1(double p1) {
        this.p1 = p1;
    }

    public double getP2() {
        return p2;
    }

    public void setP2(double p2) {
        this.p2 = p2;
    }

    public double getP3() {
        return p3;
    }

    public void setP3(double p3) {
        this.p3 = p3;
    }

    public double getProm() {
        return prom;
    }

    public void setProm(double prom) {
        this.prom = prom;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public double getMat1() {
        return mat1;
    }

    public void setMat1(double mat1) {
        this.mat1 = mat1;
    }

    public double getMat2() {
        return mat2;
    }

    public void setMat2(double mat2) {
        this.mat2 = mat2;
    }

    public double getMat3() {
        return mat3;
    }

    public void setMat3(double mat3) {
        this.mat3 = mat3;
    }

    public double getMat4() {
        return mat4;
    }

    public void setMat4(double mat4) {
        this.mat4 = mat4;
    }

    
   
    
}

