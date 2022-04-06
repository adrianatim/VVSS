import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

public class TestAddGrade {

    public static Service service;

    @BeforeAll
    public static void setUp() {
        String filenameStudent = "src/main/resources/Studenti.xml";
        String filenameTema = "src/main/resources/Teme.xml";
        String filenameNota = "src/main/resources/Note.xml";
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

    }

    @Test
    public void test_addStudent_expectNothingToThrow() {
        Student student = new Student("1", "Ana Mere", 937, "anaAre@mere.com");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
    }

    @Test
    public void test_addAssignment_expectNothingToThrow() {
        Tema tema = new Tema("1", "Decriere tema trebuie sa aiba minim 50 de caractere", 7, 7);
        Assertions.assertDoesNotThrow(() -> service.addTema(tema));
    }

    @Test
    public void test_addGrade_expectNothingToThrow() {
        Nota nota = new Nota("1", "1", "2", 8.8, LocalDate.now());
        Assertions.assertDoesNotThrow(() -> service.addNota(nota, "Feedback"));
    }

    @Test
    public void test_addValidGrade_expectNothingToThrow() {
        test_addStudent_expectNothingToThrow();
        test_addAssignment_expectNothingToThrow();
        test_addGrade_expectNothingToThrow();
    }
}
