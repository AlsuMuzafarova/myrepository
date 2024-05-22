package controlWork.v15;

class FilePart {

    private final int size;
    private final String string;
    private final int controlNumber;
    private final int number;

    public FilePart(int size, String string, int controlNumber, int number) {
        this.size = size;
        this.string = string;
        this.controlNumber = controlNumber;
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public String getString() {
        return string;
    }

    public int getControlNumber() {
        return controlNumber;
    }

    public int getNumber() {
        return number;
    }
}
