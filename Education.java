import java.time.LocalDate;

public class Education implements Comparable {
    private LocalDate datainceput;
    private LocalDate datasfarsit;
    private String numeinstitutie;
    private String niveleducatie;
    private Double mediefinal;

    public Education(LocalDate datainceput, LocalDate datasfarsit, String numeinstitutie, String niveleducatie, Double mediefinal) throws InvalidDatesException {
        this.datainceput = datainceput;
        this.datasfarsit = datasfarsit;
        if(this.datasfarsit.isBefore(this.datainceput)){
            throw new InvalidDatesException();
        }
        else {
            this.numeinstitutie = numeinstitutie;
            this.niveleducatie = niveleducatie;
            this.mediefinal = mediefinal;
        }
    }

    @Override
    public int compareTo(Object o) {
            Education ed = (Education) o;
            if (this.datasfarsit == null) {
                if (this.datainceput.isBefore(ed.datainceput) )
                    return 1;
                return -1;
            }
            if (this.datasfarsit.isEqual(ed.datasfarsit)) {
                if (this.mediefinal > ed.mediefinal) {
                    return -1;
                }
                return 1;
            }
            if (this.datasfarsit.isBefore(ed.datasfarsit)) {
                return -1;
            }
            return 1;
        }

    public void setDatainceput(LocalDate datainceput) {
        this.datainceput = datainceput;
    }
    public LocalDate getDatainceput() {
        return datainceput;
    }


    public void setDatasfarsit(LocalDate datasfarsit) {
        this.datasfarsit = datasfarsit;
    }
    public LocalDate getDatasfarsit() {
        return datasfarsit;
    }

    public void setNumeinstitutie(String numeinstitutie) {
        this.numeinstitutie = numeinstitutie;
    }
    public String getNumeinstitutie() {
        return numeinstitutie;
    }

    public void setNiveleducatie(String niveleducatie) {
        this.niveleducatie = niveleducatie;
    }
    public String getNiveleducatie() {
        return niveleducatie;
    }

    public void setMediefinal(Double mediefinal) {
        this.mediefinal = mediefinal;
    }
    public Double getMediefinal() {
        return mediefinal;
    }
}
