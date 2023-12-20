package kr_infa_2_var;
//абстрактрный класс с его дочерними
public abstract class Stage {

    public Status status;
    public final String stageDescription;

    protected Stage(Status status, String stageDescription) {
        this.status = status;
        this.stageDescription = stageDescription;
    }

    @Override
    public String toString() {
        return "(" + status + " (" + stageDescription + "))";
    }

    public static class Roof extends Stage {

        protected Roof(Status status, String stageDescription) {
            super(status, stageDescription);
        }
    }

    public static class Finishing extends Stage {

        protected Finishing(Status status, String stageDescription) {
            super(status, stageDescription);
        }
    }

    public static class Foundation extends Stage {

        protected Foundation(Status status, String stageDescription) {
            super(status, stageDescription);
        }
    }

    public static class Project extends Stage {

        protected Project(Status status, String stageDescription) {
            super(status, stageDescription);
        }
    }

    public static class Walls extends Stage {

        protected Walls(Status status, String stageDescription) {
            super(status, stageDescription);
        }
    }
}
