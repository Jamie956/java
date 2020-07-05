package com.example.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NonBlockingIOServerTest {
    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.bind(new InetSocketAddress(8081));
            ssc.configureBlocking(false);

            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {
                selector.select();
                Set<SelectionKey> sk = selector.selectedKeys();
                Iterator<SelectionKey> itr = sk.iterator();

                while (itr.hasNext()) {
                    SelectionKey k = itr.next();

                    if (k.isAcceptable()) {
                       ServerSocketChannel serverChannel = (ServerSocketChannel) k.channel();
                       SocketChannel clientChannel = serverChannel.accept();
                       clientChannel.configureBlocking(false);
                       clientChannel.register(selector, SelectionKey.OP_WRITE);
                    } else if (k.isWritable()) {
                        SocketChannel clientChannel = (SocketChannel) k.channel();

                        String msg = "oops hi nio";
                        ByteBuffer bf = ByteBuffer.allocate(msg.length());
                        bf.put(msg.getBytes());
                        bf.flip();

                        clientChannel.write(bf);
                    }

                    k.cancel();
                    itr.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
