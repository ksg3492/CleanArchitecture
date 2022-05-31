#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_sunggil_cleanarchitecture_data_Name_a(
        JNIEnv *env,
        jobject /* this */) {
    std::string baseUrl = "BASEURL";
    return env->NewStringUTF(baseUrl.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sunggil_cleanarchitecture_data_Name_b(
        JNIEnv *env,
        jobject /* this */) {
    std::string token = "TOKEN";
    return env->NewStringUTF(token.c_str());
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_sunggil_cleanarchitecture_data_Name_c(
        JNIEnv *env,
        jobject /* this */) {
    std::string decryptKey = "DECRYPTKEY";
    return env->NewStringUTF(decryptKey.c_str());
}