package com.zlc.zookeeper.zookeeper;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
class ZkApiTest {

    @Resource
    ZkApi zkApi;

    @Test
    void test(){
        zkApi.test();
    }

    @Test
    void exists() {
        String path = "/aa";
        zkApi.createNode(path, "aaa");
        zkApi.exists(path, true);
        zkApi.getData(path, null);
        zkApi.deleteNode(path);
    }

    @Test
    void testExists() {

    }

    @Test
    void createNode() {
        zkApi.createNode("/a", "aaa");
    }

    @Test
    void updateNode() {
    }

    @Test
    void deleteNode() {
    }

    @Test
    void getChildren() {
    }

    @Test
    void getData() {
    }

    @Test
    void init() {
    }
}