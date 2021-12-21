package me.ran.springbootdatajpa.reply;


public class ReplySummaryClass {
    private String reply;

    private int up;

    private int down;

    public ReplySummaryClass(String reply, int up, int down) {
        this.reply = reply;
        this.up = up;
        this.down = down;
    }

    public String getReply() {
        return reply;
    }

    public int getUp() {
        return up;
    }

    public int getDown() {
        return down;
    }

    public String getVotes() {
        return getUp() + " " + getDown();
    }
}
