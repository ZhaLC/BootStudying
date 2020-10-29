package com.zlc.studying.concurrent;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
class AsyncTestTest {

    @Autowired
    AsyncTest asyncTest;

    @Test
    void testAsync() {
        for (int i = 0; i < 5; i++) {
            asyncTest.testAsync();
        }
    }
}