LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
$(shell rm -rf $(TARGET_OUT)/qrd_theme)
$(shell mkdir -p $(TARGET_OUT)/qrd_theme)
$(shell cp -r $(LOCAL_PATH)/PreLoadTheme_res/*.zip $(TARGET_OUT)/qrd_theme/)

