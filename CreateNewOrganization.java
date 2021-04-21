
import java.util.Hashtable;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CreateNewOrganization {
    public static Organization createOrganization(Hashtable<String, Product> table) throws IllegalArgumentException, InputMismatchException {
        Scanner in = new Scanner(System.in);
        String unit;
        float annualTurnover;
        Organization organization=new Organization();
        String name;
        boolean breaker = false;
        while (!breaker) {
            try {
                System.out.println("Пожалуйста, введите название производителя");
                name = in.nextLine();
                if (name == null) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Введите значение ежегодного оборота");
                annualTurnover = in.nextFloat();
                if (annualTurnover <= 0) {
                    throw new IllegalArgumentException();
                }
                System.out.println("Введите тип компании. Варианты: \n" +
                        "    COMMERCIAL,\n" +
                        "    PUBLIC,\n" +
                        "    TRUST,\n" +
                        "    PRIVATE_LIMITED_COMPANY,\n" +
                        "    OPEN_JOINT_STOCK_COMPANY");
                in.nextLine();
                unit = in.nextLine();
                if (!unit.equals("COMMERCIAL") && !unit.equals("PUBLIC") && !unit.equals("TRUST") && !unit.equals("PRIVATE_LIMITED_COMPANY") && !unit.equals("OPEN_JOINT_STOCK_COMPANY"))
                    throw new IllegalArgumentException();
                organization.stringToEnum(unit);
                organization.setId(Organization.checkId(table));
                organization.setName(name);
                organization.setAnnualTurnover(annualTurnover);
                breaker=true;
            }
            catch (IllegalArgumentException | InputMismatchException e) {
            System.out.println("Введенное значение неверно! Повторите ввод");

        }
    }
        return organization;
    }
}
