import java.time.LocalDate;
import java.time.Period;


public class Person {
    String Name;
    LocalDate BirthDate;
    String EyeColor;
    Double Height;
    String RelationshipStatus;

    public Person(String name, String birthDay, String eyeColor, Double height, String relationshipStatus) {
        this.Name = name;
        this.BirthDate = LocalDate.parse(birthDay);
        this.EyeColor = eyeColor;
        this.Height = height;
        this.RelationshipStatus = relationshipStatus;
    }

    public static int getLeeftijd(Person person) {
        LocalDate curDate = LocalDate.now();
        if (person.BirthDate != null) {
            return Period.between(person.BirthDate, curDate).getYears();
        }
        else {
            return 0;
        }
    }
}
