package prog_kr;


public class BroadcastsTime implements Comparable<BroadcastsTime> {

    private byte hour;
    public byte minutes;

    public BroadcastsTime(String time) {
        String[] hoursAndMinutes = time.split(":");
        hour = Byte.parseByte(hoursAndMinutes[0]);
        minutes = Byte.parseByte(hoursAndMinutes[1]);
    }
    public byte hour() {
        return hour;
    }
    public byte minutes() {
        return minutes;
    }
    public boolean after(BroadcastsTime t) {
        return compareTo(t) > 0;
    }
    public boolean before(BroadcastsTime t) {
        return compareTo(t) < 0;
    }
    public boolean between(BroadcastsTime t1, BroadcastsTime t2) {
        return after(t1) && before(t2);
    }


    @Override
    public int compareTo(BroadcastsTime e) {
        return Integer.compare(minutesWithHours(), e.minutesWithHours());
    }

    private int minutesWithHours() {
        return minutes() + hour() * 60;
    }
}

