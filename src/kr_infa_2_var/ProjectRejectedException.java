package kr_infa_2_var;

public class ProjectRejectedException extends IllegalStateException {

    public ProjectRejectedException() {
    }

    public ProjectRejectedException(String s) {
        super(s);
    }

    public ProjectRejectedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProjectRejectedException(Throwable cause) {
        super(cause);
    }
}
