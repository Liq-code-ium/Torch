package org.torch.server;

public final class TorchIntCache {
    
    public static int[] requestArray(int size) {
        return new int[size];
    }
    
    public static void releaseCaches() {
        ;
    }
    
    public static String toReadableSize() {
        return "Integer array cache disabled.";
    }
    
}
