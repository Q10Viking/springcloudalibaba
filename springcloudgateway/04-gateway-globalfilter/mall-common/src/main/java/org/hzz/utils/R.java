package org.hzz.utils;

import java.util.HashMap;

public class R extends HashMap<String, Object> {
    public R(){
        put("code",0);
        put("msg","success");
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }
}
