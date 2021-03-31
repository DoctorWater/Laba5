public class Organization implements StructureInterface {
    private Long id;
    private String name;
    private Float annualTurnover;
    private OrganizationType type;

    public void setAnnualTurnover(Float annualTurnover) {
        this.annualTurnover = annualTurnover;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void stringToEnum(String type) {
        this.type = OrganizationType.valueOf(type);
    }
}
