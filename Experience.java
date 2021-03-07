import java.time.LocalDate;
import java.time.Year;

public class Experience implements Comparable {
    private LocalDate datainceput;
    private LocalDate datasfarsit;
    private String numecompanie;
    private String pozitia;

    public Experience(LocalDate datainceput, LocalDate datasfarsit, String numecompanie, String pozitia) {
        this.datainceput = datainceput;
        this.datasfarsit = datasfarsit;
        this.numecompanie = numecompanie;
        this.pozitia = pozitia;
    }

    public LocalDate getDatainceput() {
        return datainceput;
    }

    public LocalDate getDatasfarsit() {
        return datasfarsit;
    }

    public String getNumecompanie() {
        return numecompanie;
    }

    public String getPozitia() {
        return pozitia;
    }

    public void setDatainceput(LocalDate datainceput) {
        this.datainceput = datainceput;
    }

    public void setDatasfarsit(LocalDate datasfarsit) {
        this.datasfarsit = datasfarsit;
    }

    public void setNumecompanie(String numecompanie) {
        this.numecompanie = numecompanie;
    }

    public void setPozitia(String pozitia) {
        this.pozitia = pozitia;
    }

    public int getYears() {
        if(datasfarsit == null) {
            return Year.now().getValue() - datainceput.getYear();
        }
        return datasfarsit.getYear() - datainceput.getYear();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
