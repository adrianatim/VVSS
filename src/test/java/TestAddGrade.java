import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import utils.FileClearer;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TestAddGrade {

    public static Service service;

    @BeforeEach
    void setUp() {
        String filenameStudent = "src/test/resources/Studenti.xml";
        String filenameTema = "src/test/resources/Teme.xml";
        String filenameNota = "src/test/resources/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);

        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);

        FileClearer.clearFiles();
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @AfterEach
    void tearDown() {
        FileClearer.clearFiles();
    }

    @Test
    public void test_addStudent_expectNothingToThrow() {
        Student student = new Student("123", "Ana Mere", 100, "test@test.com");
        assertNull(service.addStudent(student));
    }

    @Test
    public void test_addAssignment_expectNothingToThrow() {
        assertNull(service.addTema(new Tema("123", "Decriere tema trebuie sa aiba minim 50 de caractere", 1, 1)));
    }

    @Test
    public void test_addGrade_expectValidationException() {
        assertThrows(ValidationException.class, () -> service.addNota(
                new Nota("1", "", "", 10.0, LocalDate.now()), "test")
        );
    }

    @Test
    public void test_addValidGrade_expectNothingToThrow() {
        Student student = new Student("1", "Ana Mere", 937, "anaAre@mere.com");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        Tema tema = new Tema("3", "Decriere tema trebuie sa aiba minim 50 de caractere", 7, 7);
        Assertions.assertDoesNotThrow(() -> service.addTema(tema));
        Nota nota = new Nota("1", "1", "3", 8.8, LocalDate.now());
        Assertions.assertDoesNotThrow(() -> service.addNota(nota, "Feedback"));
    }

    @Test
    public void addStudent_integration() {
        addStudent();
    }

    @Test
    public void addAssignment_integration() {
        addStudent();
        addAssignment();
    }

    @Test
    public void addGrade_integration() {
        addStudent();
        addAssignment();
        addGrade();
    }

    public void addStudent() {
        Student student = new Student("123", "Ana Mere", 100, "some@email.com");
        assertNull(service.addStudent(student));
    }

    public void addAssignment() {
        assertNull(service.addTema(new Tema("123", "Decriere tema trebuie sa aiba minim 50 de caractere", 1, 1)));
    }

    // 2022,3,30 (in file)
    public void addGrade() {
        service.addNota(new Nota("1", "123", "123", 10.0, LocalDate.of(2022, 4, 5)), "asd");
        assertEquals(10.0, service.addNota(new Nota("1", "123", "123", 10.0, LocalDate.of(2022, 4, 5)), "asd"));
    }
}