package kr_infa_2_var;

public class Stages implements Staged {

    private StageNode first;
    private StageNode last;
    private StageNode current;

    public void add(Stage stage) {
        StageNode Last = last;
        StageNode newLast = new StageNode(Last, stage, null);
        last = newLast;
        if (Last == null)
            first = newLast;
        else
            Last.next = newLast;
        if (current == null) {
            current = first;
        }
    }

    @Override
    public Stage next() {
        if (current == null)
            return null;
        StageNode stageNode = current;
        current = current.next;
        if (stageNode.stage.status == Status.REJECTED) {
            if (stageNode.stage instanceof Stage.Project) {
                throw new ProjectRejectedException("Проект отклонён");
            } else {
                stageNode.stage.status = Status.COMPLETED;
            }
        }
        return stageNode.stage;
    }

    @Override
    public Stage prev() {
        if (current == null)
            return null;
        StageNode stage = current;
        current = current.prev;
        return stage.stage;
    }

    private static class StageNode {

        StageNode next;
        StageNode prev;
        Stage stage;

        StageNode(StageNode prev, Stage stage, StageNode next) {
            this.prev = prev;
            this.stage = stage;
            this.next = next;
        }
    }
}
