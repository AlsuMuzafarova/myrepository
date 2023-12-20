package kr_infa_2_var;

public class ConstructionImpl implements Construction {
    private final Stages stages;
    private String message;
    public ConstructionImpl(Stage... stages) {
        Stages s = new Stages();
        for (Stage stage : stages) {
            s.add(stage);
        }
        this.stages = s;
    }

    @Override
    public void start() {
        Stage stage;
        try {
            while ((stage = stages.next()) != null) {
                System.out.println(stage);
            }
            message = "END";
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String end() {
        return message;
    }
}
