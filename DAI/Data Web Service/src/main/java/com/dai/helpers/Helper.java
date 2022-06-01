package com.dai.helpers;

import java.util.concurrent.Future;

public class Helper {

    public static synchronized <T> T await(Future<T> future) throws Exception
    {
        while (true)
        {
            if (future.isDone())
            {
                return future.get();
            }
        }
    }
}
