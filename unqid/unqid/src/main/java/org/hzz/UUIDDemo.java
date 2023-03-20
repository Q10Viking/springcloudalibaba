package org.hzz;

import java.util.UUID;

public class UUIDDemo {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            String uuid = UUID.randomUUID().toString();
            System.out.println(uuid);

            String minUuid = uuid.replaceAll("-", "");
            System.out.println(minUuid);
            System.out.println("------------------------------------");
        }
    }
}
/**
 * e3b34629-a9e7-43ad-9eee-4e5b558343c3
 * e3b34629a9e743ad9eee4e5b558343c3
 * ------------------------------------
 * 5d63b7e4-2864-4ae2-91bb-fb4241729704
 * 5d63b7e428644ae291bbfb4241729704
 * ------------------------------------
 * 98d3e5c9-326d-4e7b-ad13-b551ec6e985a
 * 98d3e5c9326d4e7bad13b551ec6e985a
 * ------------------------------------
 * 741b14bb-d4f9-45ae-84bf-8327c321baa0
 * 741b14bbd4f945ae84bf8327c321baa0
 * ------------------------------------
 * 3746e1c8-5ea3-4eaa-aed9-7c99fc44b9fa
 * 3746e1c85ea34eaaaed97c99fc44b9fa
 * ------------------------------------
 */