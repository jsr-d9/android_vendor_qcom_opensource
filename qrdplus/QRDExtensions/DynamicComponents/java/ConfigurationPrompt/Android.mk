LOCAL_PATH := $(call my-dir)
QRDExt_ConfPrompt:=no
include build/buildplus/target/QRDExt_target.min

ifeq ($(QRDExt_ConfPrompt),yes)

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional eng

LOCAL_SRC_FILES := $(call all-subdir-java-files)

LOCAL_SDK_VERSION := current

LOCAL_PACKAGE_NAME := ConfPrompt

include $(BUILD_PACKAGE)

endif
