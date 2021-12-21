package me.ran.springbootdatajpa.reply;

import org.springframework.beans.factory.annotation.Value;

public interface ReplySummary {

    String getReply();

    int getUp();

    int getDown();

    default String getVotes2() {
        return getUp() + " " + getDown();
    }

    @Value("#{target.up + ' ' + target.down}")
    String getVotes();
}
