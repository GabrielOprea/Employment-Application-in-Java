import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.util.concurrent.RecursiveAction;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class Test {

    private static Consumer buildConsumer(JSONObject consumer, Consumer crtConsumer) throws InvalidDatesException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        Consumer.ResumeBuilder builder = crtConsumer.new ResumeBuilder();
        String name = ((String) consumer.get("name")).split(" ")[0];
        crtConsumer.setName(name);
        String prenume = ((String) consumer.get("name")).split(" ")[1];
        String telefon = (String) consumer.get("phone");
        String sex = (String) consumer.get("genre");
        String dataString = (String) consumer.get("date_of_birth");
        //convert String to LocalDate
        LocalDate data = LocalDate.parse(dataString, formatter);

        builder.withName(name)
                .withSurname(prenume)
                .withPhone(telefon)
                .withSex(sex)
                .withDate(data);

        JSONArray educationArr = (JSONArray) consumer.get("education");
        JSONArray experienceArr = (JSONArray) consumer.get("experience");

        JSONArray languages = (JSONArray) consumer.get("languages");
        JSONArray languagesLevel = (JSONArray) consumer.get("languages_level");

        Iterator<JSONObject> educationIterator = educationArr.iterator();
        Iterator<JSONObject> experienceIterator = experienceArr.iterator();

        Iterator<String> languagesIterator = languages.iterator();
        Iterator<String> levelIterator = languagesLevel.iterator();

        while(languagesIterator.hasNext()) {
            builder.addLanguage(languagesIterator.next());
        }

        while(levelIterator.hasNext()) {
            builder.addLevel(levelIterator.next());
        }

        while(educationIterator.hasNext()) {
            JSONObject ed = educationIterator.next();
            String level = (String) ed.get("level");
            String ed_name = (String) ed.get("name");
            String start_date = (String) ed.get("start_date");
            String end_date = (String) ed.get("end_date");
            LocalDate start_date_ed = LocalDate.parse(start_date, formatter);

            LocalDate end_date_ed;
            if(end_date != null) end_date_ed = LocalDate.parse(end_date, formatter);
            else end_date_ed = LocalDate.now();
            Number grade = (Number) ed.get("grade");
            Education crtEducation = new Education(start_date_ed, end_date_ed, ed_name, level, grade.doubleValue());
            builder.addEducation(crtEducation);
            crtConsumer.add(crtEducation);
        }

        while(experienceIterator.hasNext()) {
            JSONObject exp = experienceIterator.next();
            String company = (String) exp.get("company");
            String position = (String) exp.get("position");
            String start_date_str = (String) exp.get("start_date");
            String end_date_str = (String) exp.get("end_date");
            LocalDate start_date_exp = LocalDate.parse(start_date_str, formatter);
            LocalDate end_date_exp;
            if(end_date_str != null) end_date_exp = LocalDate.parse(end_date_str, formatter);
            else end_date_exp = LocalDate.now();
            Experience crtExperience = new Experience(start_date_exp, end_date_exp, company, position);
            builder.addExperience(crtExperience);
            crtConsumer.add(crtExperience);
        }

        builder.build();
        return crtConsumer;
    }

    public static void main(String[] args) throws IOException, InvalidDatesException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MM.yyyy");
        ArrayList<Recruiter> recruiters = new ArrayList<Recruiter>();
        ArrayList<User> users = new ArrayList<User>();
        ArrayList<Manager> managers = new ArrayList<Manager>();
        ArrayList<Employee> employees = new ArrayList<Employee>();
        ArrayList<Company> companies = new ArrayList<>();
        ArrayList<Job> jobs = new ArrayList<>();
        Application testApp = Application.getInstance();

        JSONParser parser = new JSONParser();

        //Citesc consumerii din fisierul JSON

        try (Reader reader = new FileReader("consumers.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray usersArr = (JSONArray)jsonObject.get("users");
            JSONArray recruitersArr = (JSONArray)jsonObject.get("recruiters");
            JSONArray managersArr = (JSONArray)jsonObject.get("managers");
            JSONArray employeesArr = (JSONArray)jsonObject.get("employees");

            Iterator<JSONObject> userIterator = usersArr.iterator();
            Iterator<JSONObject> recruitersIterator = recruitersArr.iterator();
            Iterator<JSONObject> managersIterator = managersArr.iterator();
            Iterator<JSONObject> employeesIterator = employeesArr.iterator();

            while(userIterator.hasNext()) {
                JSONObject user = userIterator.next();
                User crtUser = new User();
                crtUser = (User)buildConsumer(user, crtUser);
                users.add(crtUser);
            }

            while(recruitersIterator.hasNext()) {
                JSONObject recruiter = recruitersIterator.next();
                Recruiter crtRecruiter = new Recruiter();
                crtRecruiter = (Recruiter) buildConsumer(recruiter, crtRecruiter);
                crtRecruiter.calculateExp();
                recruiters.add(crtRecruiter);
            }

            while(managersIterator.hasNext()) {
                JSONObject manager = managersIterator.next();
                Manager crtManager = new Manager();
                crtManager = (Manager) buildConsumer(manager, crtManager);
                crtManager.calculateExp();
                managers.add(crtManager);
            }

            while(employeesIterator.hasNext()) {
                JSONObject employee = employeesIterator.next();
                Employee crtEmployee = new Employee();
                crtEmployee = (Employee) buildConsumer(employee, crtEmployee);
                crtEmployee.calculateExp();
                employees.add(crtEmployee);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Citim companiile din fisierul JSON

        try (Reader reader = new FileReader("companies.json")) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            JSONArray companiesArr = (JSONArray)jsonObject.get("companies");

            Iterator<JSONObject> companiesIterator = companiesArr.iterator();

            while(companiesIterator.hasNext()) {
                JSONObject company = companiesIterator.next();
                Company crtCompany = new Company();
                String name = (String) company.get("name");
                crtCompany.setName(name);

                JSONArray jobsArr = (JSONArray) company.get("jobs");
                Iterator<JSONObject> jobsIterator = jobsArr.iterator();
                while(jobsIterator.hasNext()) {
                    JSONObject crtJob = jobsIterator.next();
                    String jobName = (String) crtJob.get("name");
                    int no_positions = ((Number) crtJob.get("no_positions")).intValue();
                    int min_year = (Number) crtJob.get("min_year") == null ? -1 : ((Number) crtJob.get("min_year")).intValue();
                    int max_year = (Number) crtJob.get("max_year") == null ? -1 : ((Number) crtJob.get("max_year")).intValue();
                    int min_exp = (Number) crtJob.get("min_exp") == null ? -1 : ((Number) crtJob.get("min_exp")).intValue();
                    int max_exp = (Number) crtJob.get("max_exp") == null ? -1 : ((Number) crtJob.get("max_exp")).intValue();
                    int min_avg = (Number) crtJob.get("min_avg") == null ? -1 : ((Number) crtJob.get("min_avg")).intValue();
                    int max_avg = (Number) crtJob.get("max_avg") == null ? -1 : ((Number) crtJob.get("max_avg")).intValue();
                    Job j = new Job(jobName, name, no_positions);
                    j.setGraduationYear(new Constraint(min_year, max_year));
                    j.setExperience(new Constraint(min_exp, max_exp));
                    j.setMean(new Constraint(min_avg, max_avg));
                    jobs.add(j);
                }
                companies.add(crtCompany);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for(User u : users) {
            testApp.add(u);
        }

        for(Company c : companies) {
            testApp.add(c);
        }


    }
}