import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

public class Information {
    private String nume;
    private String prenume;
    private String email;
    private String telefon;
    private LocalDate data;
    private String sex;
    private ArrayList<String> LanguagesList;
    private ArrayList<String> LevelList;

    public Information() {
        LanguagesList = new ArrayList<>();
        LevelList = new ArrayList<>();
    }

    public void setNume(String nume) {
        this.nume = nume;
    }
    public String getNume() {
        return nume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public String getPrenume() {
        return prenume;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }
    public String getTelefon() {
        return telefon;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }
    public LocalDate getData() {
        return data;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }
    public void setLanguagesList(ArrayList<String> LanguagesList) {
        this.LanguagesList = LanguagesList;
    }
    public ArrayList<String> getLanguagesList() {
        return LanguagesList;
    }
    public void setLevelList(ArrayList<String> LevelList) {
        this.LevelList = LevelList;
    }
    public ArrayList<String> getLevelList() {
        return LevelList;
    }
    public void addLanguage(String lang) {
        LanguagesList.add(lang);
    }
    public void addLevel(String level) {
        LevelList.add(level);
    }


}
