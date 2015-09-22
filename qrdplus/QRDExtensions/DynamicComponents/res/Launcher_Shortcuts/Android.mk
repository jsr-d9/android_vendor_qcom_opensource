LOCAL_PATH := $(call my-dir)

QRDExt_Launcher_Shortcut_gp:=no
include build/buildplus/target/QRDExt_target.min

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional eng
LAUNCHER_XML_PATH := packages/apps/Launcher2/res/xml
include build/buildplus/target/QRDExt_target.min
ifneq ($(strip $(QRDExt_Launcher_Shortcut_gp)),no)
        $(shell cp $(LOCAL_PATH)/$(QRDExt_Launcher_Shortcut_gp)/default_workspace.xml  $(LAUNCHER_XML_PATH)/default_workspace.xml)
else
    $(shell cp $(LOCAL_PATH)/default_workspace.xml  $(LAUNCHER_XML_PATH)/default_workspace.xml)
endif
