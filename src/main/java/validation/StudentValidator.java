package validation;

import domain.Student;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentValidator implements Validator<Student> {

    public static Pattern namePattern = Pattern.compile("^([A-Z][a-z]*(-[A-Z][a-z\\-]*)? ?){2,5}$");
    public static Pattern emailPattern = Pattern.compile(".+@.+\\..+", Pattern.CASE_INSENSITIVE);

    /**
     * Valideaza un student
     *
     * @param entity - studentul pe care il valideaza
     * @throws ValidationException - daca studentul nu e valid
     */
    @Override
    public void validate(Student entity) throws ValidationException {
        if (entity.getID() == null || entity.getID().equals("")) {
            throw new ValidationException("Id incorect!");
        }

        if (entity.getNume() == null || Objects.equals(entity.getNume(), "")) {
            throw new ValidationException("Nume incorect!");
        }

        Matcher matcherName = namePattern.matcher(entity.getNume());
        if (!matcherName.find()) {
            throw new ValidationException("Nume incorect!");
        } else if (entity.getGrupa() < 0) {
            throw new ValidationException("Grupa incorecta!");
        }
        if (entity.getEmail() == null || entity.getEmail().equals("")) {
            throw new ValidationException("Email incorect!");
        }
        Matcher matcherEmail = emailPattern.matcher(entity.getEmail());
        if (!matcherEmail.find()) {
            throw new ValidationException("Email incorect!");
        }
    }
}
