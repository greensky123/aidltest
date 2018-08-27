// IAddService.aidl
package server.test.aidl.com.aidltest_server;

// Declare any non-default types here with import statements

interface IAddService {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
      int addMethod(int num1 ,int num2);
}
