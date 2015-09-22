LOCAL_PATH := $(call my-dir)

QRDExt_BootLogo_gp:=no
include build/buildplus/target/QRDExt_target.min

include $(CLEAR_VARS)

LOCAL_MODULE_TAGS := optional eng

INTERNAL_BOOTLOGO_PATH := vendor/qcom/proprietary/qrdplus/InternalUseOnly/CarrierSpecific/Resource/boot_logo
ifeq ($(INTERNAL_BOOTLOGO_PATH),$(wildcard $(INTERNAL_BOOTLOGO_PATH)))
    ifeq ($(QRDExt_BootLogo_gp),cu)
        $(shell cp $(INTERNAL_BOOTLOGO_PATH)/splash_cu.h bootable/bootloader/lk/platform/msm_shared/include/splash.h)
    else
        $(shell cp $(LOCAL_PATH)/splash_cu.h bootable/bootloader/lk/platform/msm_shared/include/splash.h)
    endif
else
    $(shell cp $(LOCAL_PATH)/splash_qrd.h bootable/bootloader/lk/platform/msm_shared/include/splash.h)
endif