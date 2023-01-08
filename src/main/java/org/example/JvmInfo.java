package org.example;

public class JvmInfo {
    private static volatile JvmInfo JVM_INFO;

    private JvmInfo() {
    }

    public static JvmInfo getJvmInfo() {
        if (JVM_INFO == null) {
            synchronized (JvmInfo.class) {
                if (JVM_INFO == null) JVM_INFO = new JvmInfo();
            }
        }
        return JVM_INFO;
    }
    public Runtime getRuntime(){
        return Runtime.getRuntime();
    }

    public Integer getCPU_CORES() {
        return getRuntime().availableProcessors();
    }

    public Long getFreeMemory(){
        return getRuntime().freeMemory();
    }
}
