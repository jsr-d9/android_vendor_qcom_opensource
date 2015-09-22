LOCAL_PATH:= $(call my-dir)

include $(CLEAR_VARS)

LOCAL_SRC_FILES := $(call all-subdir-java-files)

LOCAL_MODULE:= com.qrd.plugin.feature_query

LOCAL_MODULE_TAGS := optional eng
LOCAL_NO_STANDARD_LIBRARIES := true
LOCAL_JAVA_LIBRARIES := core core-junit ext
include $(BUILD_JAVA_LIBRARY)

#MAKE_XML
include $(CLEAR_VARS)
LOCAL_MODULE := com.qrd.plugin.feature_query.xml
LOCAL_MODULE_CLASS := ETC
LOCAL_MODULE_PATH := $(TARGET_OUT_ETC)/permissions
LOCAL_SRC_FILES := $(LOCAL_MODULE)
LOCAL_MODULE_TAGS := optional eng
include $(BUILD_PREBUILT)


