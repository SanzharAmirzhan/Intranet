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
}
