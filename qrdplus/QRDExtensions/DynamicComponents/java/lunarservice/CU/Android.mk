LOCAL_PATH := $(call my-dir)

QRDExt_LunarService:=no
   
include build/buildplus/target/QRDExt_target.min

ifeq ($(QRDExt_LunarService),yes)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional eng

LOCAL_SRC_FILES := $(call all-java-files-under, src) \
        src/com/android/lunar/ILunarService.aidl  
 
LOCAL_PACKAGE_NAME := LunarService

LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

endif

# when make default build, we'd like also build but install into the update zip, to avoid conflict, we need change the module name and install path
ifeq ($(strip $(QRDExt_LunarService)),no)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional eng

LOCAL_SRC_FILES := $(call all-java-files-under, src) \
        src/com/android/lunar/ILunarService.aidl

LOCAL_PACKAGE_NAME := LunarService_cu

LOCAL_CERTIFICATE := platform

include $(BUILD_PACKAGE)

endif
