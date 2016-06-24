import models.*;

import java.util.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class DatabaseManager {
    private static final SessionFactory ourSessionFactory;
    private static final ServiceRegistry serviceRegistry;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            ourSessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public DatabaseManager() {
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public Auth checkAuth(){
        Session session = getSession();
        try{
            session.beginTransaction();

            Auth auth = (Auth) session.get(Auth.class, 1);

            session.getTransaction().commit();

            return auth;
        }
        catch (HibernateException e){
            System.out.println("---ERROR: auth");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return null;
    }

    public void saveAuth(String type, int who_id){
        Session session = getSession();
        try{
            Auth auth = new Auth(type, who_id);
            auth.setId(1);
            session.beginTransaction();
            session.save(auth);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: saveAuth");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public void deleteAuth(){
        Session session = getSession();
        try{
            Auth auth = null;
            session.beginTransaction();
            auth = (Auth) session.get(Auth.class, 1);
            session.delete(auth);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: deleteAuth");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    //------- Create Objects -------
    public int createStudent(String firstName, String lastName, String password){
        Session session = getSession();
        int student_id = -1;
        try {
            Student student = new Student(firstName, lastName, password);

            session.beginTransaction();
            student_id = (int) session.save(student);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: insert student");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return student_id;
    }

    public int createTeacher(String firstName, String lastName, String password){
        Session session = getSession();
        int teacher_id = -1;
        try {
            Teacher teacher = new Teacher(firstName, lastName, password);

            session.beginTransaction();
            teacher_id = (int) session.save(teacher);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: insert teacher");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return teacher_id;
    }

    public int createAdmin(String name, String password){
        Session session = getSession();
        int admin_id = -1;
        try {
            Admin admin = new Admin(name, password);

            session.beginTransaction();
            admin_id = (int) session.save(admin);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: insert admin");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return admin_id;
    }

    public int createSubject(String title, String description){
        Session session = getSession();
        int subject_id = -1;
        try {
            Subject subject = new Subject(title, description);

            session.beginTransaction();
            subject_id = (int) session.save(subject);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: insert student");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return subject_id;
    }

    //------- Student Object -------
    public Student getStudent(String firstName, String lastName, String password){
        Session session = getSession();
        Student student = null;
        try{
            session.beginTransaction();

            List studentsList = session.createCriteria(models.Student.class).add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName)).add(Restrictions.eq("password", password)).list();
            if(studentsList.size() != 0){
                student = (Student) studentsList.get(0);
            }

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getStudent");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return student;
    }

    public Student getStudentById(int id){
        Session session = getSession();
        Student student = null;
        try{
            session.beginTransaction();

            student = (Student) session.get(models.Student.class, id);

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getStudentById");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return student;
    }

    public Set<Transcript> getTranscriptForStudent(Student student){
        Session session = getSession();
        Set<Transcript> transcriptSet =  null;
        try{
            session.beginTransaction();
            transcriptSet = student.getTranscriptSet();
            session.getTransaction().commit();

        }
        catch (HibernateException e){
            System.out.println("---ERROR: getTranscriptForStudent");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return transcriptSet;
    }

    public Set<Attendance> getAttendanceForStudent(Student student){
        Session session = getSession();
        Set<Attendance> attendanceSet =  null;
        try{
            session.beginTransaction();
            attendanceSet = student.getAttendanceSet();
            session.getTransaction().commit();

        }
        catch (HibernateException e){
            System.out.println("---ERROR: getAttendanceForStudent");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return attendanceSet;
    }

    public void addSubjectForStudent(Student student, Subject subject){
        Session session = getSession();
        try{
            student.getSubjectSet().add(subject);

            session.beginTransaction();
            session.update(student);
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: addSubjectForStudent");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    public Set<Subject> getStudentSubjects(Student student){
        Session session = getSession();
        Set<Subject> subjectSet = null;
        try{
            session.beginTransaction();
            subjectSet = student.getSubjectSet();
            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getStudentSubjects");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return subjectSet;
    }


    //------- Teacher Object -------
    public Teacher getTeacher(String firstName, String lastName, String password){
        Session session = getSession();
        Teacher teacher = null;
        try{
            session.beginTransaction();

            List teacherList = session.createCriteria(models.Teacher.class).add(Restrictions.eq("firstName", firstName))
                    .add(Restrictions.eq("lastName", lastName)).add(Restrictions.eq("password", password)).list();
            if(teacherList.size() != 0){
                teacher = (Teacher) teacherList.get(0);
            }

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getTeacher");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return teacher;
    }

    public Teacher getTeacherById(int id){
        Session session = getSession();
        Teacher teacher = null;
        try{
            session.beginTransaction();

            teacher = (Teacher) session.get(models.Teacher.class, id);

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getTeacherById");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return teacher;
    }


    //------- Admin Object -------
    public Admin getAdmin(String name, String password){
        Session session = getSession();
        Admin admin = null;
        try{
            session.beginTransaction();

            List adminList = session.createCriteria(models.Admin.class).add(Restrictions.eq("name", name))
                    .add(Restrictions.eq("password", password)).list();
            if(adminList.size() != 0){
                admin = (Admin) adminList.get(0);
            }

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getAdmin");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return admin;
    }

    public Admin getAdminById(int id){
        Session session = getSession();
        Admin admin = null;
        try{
            session.beginTransaction();

            admin = (Admin) session.get(models.Admin.class, id);

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getAdminById");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return admin;
    }

    //------- For All --------
    public List<Subject> getAllSubjects(){
        Session session = getSession();
        List<Subject> subjectSet = null;
        try{
            session.beginTransaction();

            subjectSet = session.createCriteria(models.Subject.class).list();

            session.getTransaction().commit();
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getAllSubjects");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return subjectSet;
    }

    public Subject getSubjectWithName(String title){
        Session session = getSession();
        Subject subject = null;
        try{
            List<Subject> subjectList = null;
            session.beginTransaction();
            subjectList = session.createCriteria(models.Subject.class).add(Restrictions.eq("title", title)).list();
            session.getTransaction().commit();

            if(!subjectList.isEmpty()){
                subject = subjectList.get(0);
            }
        }
        catch (HibernateException e){
            System.out.println("---ERROR: getSubjectWithName");
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return subject;
    }
}
