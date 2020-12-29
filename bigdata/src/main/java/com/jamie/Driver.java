package com.jamie;

import com.jamie.friends.*;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.junit.Test;

import java.io.IOException;

import static com.jamie.Utils.getJob;

public class Driver {
    /**
     * friends 1
     *
     * 人：好友
     * A:B,C,D,F,E,O
     * B:A,C,E,K
     * C:F,A,D,I
     * D:A,E,F,L
     * E:B,C,D,M,L
     *
     * 人    关注者
     * A	I,K,C,B,G,F,H,O,D,
     * B	A,F,J,E,
     * C	A,E,B,H,F,G,K,
     * D	G,C,K,A,L,F,E,H,
     * E	G,M,L,H,A,F,B,D,
     */
    @Test
    public void t1() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(Driver.class, OneShareFriendsMapper.class, OneShareFriendsReducer.class, Text.class, Text.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/friends"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }

    /**
     * friends 2
     *
     * 人    关注者
     * A	I,K,C,B,G,F,H,O,D,
     * B	A,F,J,E,
     * C	A,E,B,H,F,G,K,
     * D	G,C,K,A,L,F,E,H,
     * E	G,M,L,H,A,F,B,D,
     *
     * A与B 的共同好友 E, C
     * A-B	E C
     */
    @Test
    public void t2() throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(Driver.class, TwoShareFriendsMapper.class, TwoShareFriendsReducer.class, Text.class, Text.class);

        FileInputFormat.setInputPaths(job, new Path("src/main/resources/friends2"));
        FileOutputFormat.setOutputPath(job, new Path("src/main/resources/out"));

        job.waitForCompletion(true);
    }
}
