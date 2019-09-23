package com.traydakalo.aLearn;

import com.traydakalo.security.SecurityConfig;

public class main {
    public static void main(String[] args) throws Exception {
        SecurityConfig.getMapConfig().values().forEach(x->x.forEach(System.out::println));

    }
}
