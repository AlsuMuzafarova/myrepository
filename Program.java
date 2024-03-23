package prog_kr;

public class Program {
    private String channel;
    private BroadcastsTime time;
    private String name;


    public Program(String channel, BroadcastsTime time, String name) {
        this.channel = channel;
        this.time = time;
        this.name = name;
    }

    public String getChannel() {

        return channel;
    }

    public void setChannel(String channel) {

        this.channel = channel;
    }

    public BroadcastsTime getTime() {

        return time;
    }

    public void setTime(BroadcastsTime time) {

        this.time = time;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }
}
