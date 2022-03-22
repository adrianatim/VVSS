import domain.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import repository.StudentXMLRepo;

public class TestAbstractXMLRepository {

    @Test
    public void TC1() {
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");

        NullPointerException exception = Assertions.assertThrows(NullPointerException.class, () -> studentXMLRepo.save(null));
        Assertions.assertEquals("Cannot invoke \"repository.HasID.getID()\" because \"entity\" is null", exception.getMessage());
    }

    @Test
    public void TC2() {
        Student student = new Student("1", "Ana", 937, "anaAre@mere");
        StudentXMLRepo studentXMLRepo = new StudentXMLRepo("src/main/resources/Studenti.xml");
        Student studentToCompare = studentXMLRepo.save(student);

        Assertions.assertEquals(student, studentToCompare);
    }
}
