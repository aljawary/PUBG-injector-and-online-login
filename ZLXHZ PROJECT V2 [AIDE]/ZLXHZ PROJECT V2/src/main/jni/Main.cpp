/* 
Credits: (Please Dont Remove This )
Join Telegram @zlxhz For More UI Injector
Thanks You For Using My Base/ UI
My Telegram Channel https://t.me/zlxhz
*/

#include <sys/types.h>
#include <pthread.h>
#include <jni.h>
#include <unistd.h>
#include "Logger.h"
#include "obfuscate.h"

bool loggedin = false;

extern "C" JNIEXPORT jstring JNICALL
Java_com_zlxhz_freeproject_LoginActivity_Check(JNIEnv *env, jobject activityObject) {
loggedin = true;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_zlxhz_freeproject_LoginActivity_loginURL(JNIEnv *env, jobject activityObject) {
    jstring str = env->NewStringUTF(OBFUSCATE_KEY("https://hzrevolution.my.id/aideproject/login.php", '~'));//Link PHP Files
    return str;
}


extern "C" JNIEXPORT jstring JNICALL
Java_com_zlxhz_freeproject_MainActivity_Tele(JNIEnv *env, jobject activityObject) {
    jstring str = env->NewStringUTF(OBFUSCATE_KEY("https://t.me/sanzdi", '~'));//Link Telegram
    return str;
}

extern "C" JNIEXPORT jstring JNICALL
Java_com_zlxhz_freeproject_MainActivity_TeleCH(JNIEnv *env, jobject activityObject) {
    jstring str = env->NewStringUTF(OBFUSCATE_KEY("https://t.me/zlxhz", '-'));//Link Telegram Channel
    return str;
}
