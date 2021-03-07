import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public abstract class Consumer {

    public class Resume {
        private Information info;
        private ArrayList<Experience> experienceList;
        private ArrayList<Education> educationList;

        private Resume(ResumeBuilder rb) {
            this.info = rb.info;
            this.experienceList = rb.experienceList;
            this.educationList = rb.educationList;
        }
    }
        public class ResumeBuilder {
            private Information info;
            private ArrayList<Experience> experienceList;
            private ArrayList<Education> educationList;

            public ResumeBuilder() {
                experienceList = new ArrayList<>();
                educationList = new ArrayList<>();
                info = new Information();
            }

            public ResumeBuilder withName(String nume) {
                info.setNume(nume);
                return this;
            }

            public ResumeBuilder withSurname(String prenume) {
                info.setNume(prenume);
                return this;
            }

            public ResumeBuilder withPhone(String telefon) {
                info.setTelefon(telefon);
                return this;
            }

            public ResumeBuilder withDate(LocalDate data) {
                info.setData(data);
                return this;
            }

            public ResumeBuilder withSex(String sex) {
                info.setSex(sex);
                return this;
            }

            public ResumeBuilder addLanguage(String language) {
                info.addLanguage(language);
                return this;
            }

            public ResumeBuilder addLevel(String level) {
                info.addLevel(level);
                return this;
            }

            public ResumeBuilder addExperience(Experience exp) {
                experienceList.add(exp);
                return this;
            }

            public ResumeBuilder addEducation(Education ed) {
                educationList.add(ed);
                return this;
            }

            public Resume build() {
                return new Resume(this);
            }
        }

    private ArrayList<Education> EducationList;
    private ArrayList<Experience> ExperienceList;
    private ArrayList<Consumer> ConsumersList;
    private boolean visited;
    private String name;


    public Consumer() {
        EducationList = new ArrayList<>();
        ExperienceList = new ArrayList<>();
        ConsumersList = new ArrayList<>();
        visited = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited() {
        this.visited = true;
    }

    public void setUnvisited() {
        this.visited = false;
    }

    public ArrayList<Consumer> getConsumersList() {
        return ConsumersList;
    }

    public ArrayList<Education> getEducationList() {
        return EducationList;
    }

    public ArrayList<Experience> getExperienceList() {
        return ExperienceList;
    }

    public void setConsumersList(ArrayList<Consumer> consumersList) {
        ConsumersList = consumersList;
    }

    public void setEducationList(ArrayList<Education> educationList) {
        EducationList = educationList;
    }

    public void setExperienceList(ArrayList<Experience> experienceList) {
        ExperienceList = experienceList;
    }

    public void add (Education education){
        EducationList.add(education);
    }

    public void add (Experience experience){
        ExperienceList.add(experience);
    }

    public void add (Consumer consumer){
        ConsumersList.add(consumer);
    }

    public void remove (Consumer consumer){
            ConsumersList.remove(consumer);
    }

    public Integer getGraduationYear() {
        LocalDate ld = getEducationList().get(getEducationList().size() - 1).getDatasfarsit();
        if(ld == null) return 0;
        return ld.getYear();
    }

    public Double meanGPA() {
        double totalMean = 0;
        double numberOfStudies = EducationList.size();
        for(Education e : EducationList) {
            totalMean += e.getMediefinal();
        }
        totalMean /= numberOfStudies;
        return totalMean;
    }

    public int getDegreeInFriendship(Consumer consumer) {
        //Using Queue for BFS Traversal
        LinkedList<Consumer> queue = new LinkedList<>();
        queue.add(this);
        queue.add(null);
        //We add additional null values to calculate the level
        int level = 0;
        while(queue.size() != 0) {
            Consumer crtConsumer = queue.poll();
            if (crtConsumer == null) {
                level++;
                queue.add(null);
                if (queue.peek() == null) return -1;
                else continue;
            }
            if(crtConsumer.equals(consumer)) {
                return level;
            }
            for (Consumer c : crtConsumer.getConsumersList()) {
                if(!c.isVisited()) {
                    queue.add(c);
                    c.setVisited();
                }
            }
        }
        return -1;
    }
}