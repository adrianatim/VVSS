import domain.Student;
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
import validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class TestService {

    public static Service service;
    public static Student student;
    public static Integer MAX_INT = 2147483647;

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
        student = new Student("1", "Ana Mere", 937, "anaAre@mere.com");
    }

    @Test
    public void test_addEmptyStudentID_expectValidationException() {
        student.setID("");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Id incorect!", exception.getMessage());
        student.setID("1");
    }

    @Test
    public void test_addNullStudentID_expectValidationException() {
        student.setID(null);
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Id incorect!", exception.getMessage());
        student.setID("1");
    }

    @Test
    public void test_addValidStudentID_expectStudentAdded() {
        student.setID("0");
        assertDoesNotThrow(() -> service.addStudent(student));
        student.setID("1");
    }

    @Test
    public void test_addEmptyStudentName_exceptValidationException() {
        student.setNume("");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addNullStudentName_exceptValidationException() {
        student.setNume(null);
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addInvalid1StudentName_exceptValidationException() {
        student.setNume("Diana");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addInvalid2StudentName_exceptValidationException() {
        student.setNume("Diana-Andreea");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addInvalid3StudentName_exceptValidationException() {
        student.setNume("Diana-andreea Tibre");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addInvalid4StudentName_exceptValidationException() {
        student.setNume("Adriana timis");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addInvalid5StudentName_exceptValidationException() {
        student.setNume("adriana timis");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Nume incorect!", exception.getMessage());
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addValid1StudentName_exceptNothingToThrow() {
        student.setNume("Adriana Timis");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addValid2StudentName_exceptNothingToThrow() {
        student.setNume("Adriana Maria Timis Ionescu");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addValid3StudentName_exceptNothingToThrow() {
        student.setNume("Diana-Andreea Tibre");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addValid4StudentName_exceptNothingToThrow() {
        student.setNume("Diana Andreea Tibre");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addValid5StudentName_exceptNothingToThrow() {
        student.setNume("Adriana Maria Timis-Ionescu");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setNume("Ana Mere");
    }

    @Test
    public void test_addInvalid1StudentEmail_exceptValidationException() {
        student.setEmail("@");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid2StudentEmail_exceptValidationException() {
        student.setEmail("ana@");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid3StudentEmail_exceptValidationException() {
        student.setEmail("@ana");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid4StudentEmail_exceptValidationException() {
        student.setEmail("@.");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid5StudentEmail_exceptValidationException() {
        student.setEmail("ana@.");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid6StudentEmail_exceptValidationException() {
        student.setEmail("@.ana");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid7StudentEmail_exceptValidationException() {
        student.setEmail("@ana.");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid8StudentEmail_exceptValidationException() {
        student.setEmail("ana@ana.");
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Email incorect!", exception.getMessage());
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addValidStudentEmail_exceptNothingToThrow() {
        student.setEmail("ana@ana.ana");
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setEmail("anaAre@mere.com");
    }

    @Test
    public void test_addInvalid1StudentGroup_exceptValidationException() {
        student.setGrupa(-1);
        ValidationException exception = Assertions.assertThrows(ValidationException.class, () -> service.addStudent(student));
        Assertions.assertEquals("Grupa incorecta!", exception.getMessage());
        student.setGrupa(937);
    }

    @Test
    public void test_addValid1StudentGroup_exceptNothingToThrow() {
        student.setGrupa(MAX_INT);
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setGrupa(937);
    }

    @Test
    public void test_addValid2StudentGroup_exceptNothingToThrow() {
        student.setGrupa(MAX_INT - 1);
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setGrupa(937);
    }

    @Test
    public void test_addValid3StudentGroup_exceptNothingToThrow() {
        student.setGrupa(0);
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setGrupa(937);
    }

    @Test
    public void test_addValid4StudentGroup_exceptNothingToThrow() {
        student.setGrupa(1);
        Assertions.assertDoesNotThrow(() -> service.addStudent(student));
        student.setGrupa(937);
    }
}
