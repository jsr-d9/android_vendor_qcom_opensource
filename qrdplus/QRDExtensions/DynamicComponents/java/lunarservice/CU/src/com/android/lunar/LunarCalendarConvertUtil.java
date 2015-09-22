
/*
*
* Copyright (c) 2012, Code Aurora Forum. All rights reserved.
*
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above
      copyright notice, this list of conditions and the following
      disclaimer in the documentation and/or other materials provided
      with the distribution.
    * Neither the name of Code Aurora Forum, Inc. nor the names of its
      contributors may be used to endorse or promote products derived
      from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESS OR IMPLIED
WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT
ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR
BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN
IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/ 

package com.android.lunar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LunarCalendarConvertUtil {
    private final static short[] lunarCalendarBaseInfo = new short[] { 0x4bd,
            0x4ae, 0xa57, 0x54d, 0xd26, 0xd95, 0x655, 0x56a, 0x9ad, 0x55d,
            0x4ae, 0xa5b, 0xa4d, 0xd25, 0xd25, 0xb54, 0xd6a, 0xada, 0x95b,
            0x497, 0x497, 0xa4b, 0xb4b, 0x6a5, 0x6d4, 0xab5, 0x2b6, 0x957,
            0x52f, 0x497, 0x656, 0xd4a, 0xea5, 0x6e9, 0x5ad, 0x2b6, 0x86e,
            0x92e, 0xc8d, 0xc95, 0xd4a, 0xd8a, 0xb55, 0x56a, 0xa5b, 0x25d,
            0x92d, 0xd2b, 0xa95, 0xb55, 0x6ca, 0xb55, 0x535, 0x4da, 0xa5d,
            0x457, 0x52d, 0xa9a, 0xe95, 0x6aa, 0xaea, 0xab5, 0x4b6, 0xaae,
            0xa57, 0x526, 0xf26, 0xd95, 0x5b5, 0x56a, 0x96d, 0x4dd, 0x4ad,
            0xa4d, 0xd4d, 0xd25, 0xd55, 0xb54, 0xb5a, 0x95a, 0x95b, 0x49b,
            0xa97, 0xa4b, 0xb27, 0x6a5, 0x6d4, 0xaf4, 0xab6, 0x957, 0x4af,
            0x497, 0x64b, 0x74a, 0xea5, 0x6b5, 0x55c, 0xab6, 0x96d, 0x92e,
            0xc96, 0xd95, 0xd4a, 0xda5, 0x755, 0x56a, 0xabb, 0x25d, 0x92d,
            0xcab, 0xa95, 0xb4a, 0xbaa, 0xad5, 0x55d, 0x4ba, 0xa5b, 0x517,
            0x52b, 0xa93, 0x795, 0x6aa, 0xad5, 0x5b5, 0x4b6, 0xa6e, 0xa4e,
            0xd26, 0xea6, 0xd53, 0x5aa, 0x76a, 0x96d, 0x4bd, 0x4ad, 0xa4d,
            0xd0b, 0xd25, 0xd52, 0xdd4, 0xb5a, 0x56d, 0x55b, 0x49b, 0xa57,
            0xa4b, 0xaa5, 0xb25, 0x6d2, 0xada };
    private final static byte[] lunarCalendarSpecialInfo = new byte[] { 0x08,
            0x00, 0x00, 0x05, 0x00, 0x00, 0x14, 0x00, 0x00, 0x02, 0x00, 0x06,
            0x00, 0x00, 0x15, 0x00, 0x00, 0x02, 0x00, 0x17, 0x00, 0x00, 0x05,
            0x00, 0x00, 0x14, 0x00, 0x00, 0x02, 0x00, 0x06, 0x00, 0x00, 0x05,
            0x00, 0x00, 0x13, 0x00, 0x17, 0x00, 0x00, 0x16, 0x00, 0x00, 0x14,
            0x00, 0x00, 0x02, 0x00, 0x07, 0x00, 0x00, 0x15, 0x00, 0x00, 0x13,
            0x00, 0x08, 0x00, 0x00, 0x06, 0x00, 0x00, 0x04, 0x00, 0x00, 0x03,
            0x00, 0x07, 0x00, 0x00, 0x05, 0x00, 0x00, 0x04, 0x00, 0x08, 0x00,
            0x00, 0x16, 0x00, 0x00, 0x04, 0x00, 0x0a, 0x00, 0x00, 0x06, 0x00,
            0x00, 0x05, 0x00, 0x00, 0x03, 0x00, 0x08, 0x00, 0x00, 0x05, 0x00,
            0x00, 0x04, 0x00, 0x00, 0x02, 0x00, 0x07, 0x00, 0x00, 0x05, 0x00,
            0x00, 0x04, 0x00, 0x09, 0x00, 0x00, 0x16, 0x00, 0x00, 0x04, 0x00,
            0x00, 0x02, 0x00, 0x06, 0x00, 0x00, 0x05, 0x00, 0x00, 0x03, 0x00,
            0x07, 0x00, 0x00, 0x16, 0x00, 0x00, 0x05, 0x00, 0x00, 0x02, 0x00,
            0x07, 0x00, 0x00, 0x15, 0x00, 0x00 };

    private final static int baseYear = 1900;
    private final static int outBoundYear = 2050;
    private final static String baseDay = "1900-1-31";
    private final static int bigMonthDays = 30;
    private final static int smallMonthDays = 29;

    private static long baseDayTime = 0;

    private static SimpleDateFormat simpleLunarCalendarDateFormat = new SimpleDateFormat(
            "yyyy-MM-dd");

    static {
        try {
            baseDayTime = simpleLunarCalendarDateFormat.parse(baseDay)
                    .getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static int getLunarMonthDays(int lunarYear, int lunarMonth) {
        if (isLunarBigMonth(lunarYear, lunarMonth)) return bigMonthDays;
        else return smallMonthDays;
    }

    public static boolean isLunarBigMonth(int lunarYear, int lunarMonth) {
        short lunarYearBaseInfo = lunarCalendarBaseInfo[lunarYear - baseYear];
        if ((lunarYearBaseInfo & (0x01000 >>> lunarMonth)) != 0) return true;
        else return false;
    }

    final public static int getYearDays(int lunarYear) {
        int retSum = 0;
        for (int iLunarMonth = 1; iLunarMonth <= 12; iLunarMonth++) {
            retSum += getLunarMonthDays(lunarYear, iLunarMonth);
        }
        return (retSum + getLeapMonthDays(lunarYear));
    }

    final public static int getLeapMonth(int lunarYear) {
        return lunarCalendarSpecialInfo[lunarYear - baseYear] & 0xf;
    }

    final public static int getLeapMonthDays(int lunarYear) {
        if (getLeapMonth(lunarYear) == 0) return 0;
        else if ((lunarCalendarSpecialInfo[lunarYear - baseYear] & 0x10) != 0) return bigMonthDays;
        else return smallMonthDays;
    }

    public static void parseLunarCalendar(int year, int month, int day,
            LunarService lunarService) {
        if (lunarService == null) return;

        if (lunarService.lunarYear > 0
                && lunarService.lunarMonth > 0
                && lunarService.lunarDay > 0
                && lunarService.solarYear > 0
                && lunarService.solarMonth > 0
                && lunarService.solarDay > 0) {
            // have parse before
            if (year == lunarService.solarYear && month == lunarService.solarMonth) {
                if (day - lunarService.solarDay == 1 && lunarService.lunarDay < 29) {
                    lunarService.solarDay = day;
                    lunarService.lunarDay = lunarService.lunarDay + 1;
                    return;
                }
            }
        }

        // need parse again
        int leapLunarMonth = 0;
        Date presentDate = null;
        boolean isLeapMonth = false;

        try {
            presentDate = simpleLunarCalendarDateFormat.parse(year + "-"
                    + (month + 1) + "-" + day);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        int offsetDayNum = (int) ((presentDate.getTime() - baseDayTime) / 86400000L);;

        int lunarYear = 0;
        int lunarMonth = 0;
        int lunarDay = 0;

        for (lunarYear = baseYear; lunarYear < outBoundYear; lunarYear++) {
            int daysOfLunarYear = getYearDays(lunarYear);
            if (offsetDayNum < daysOfLunarYear) break;
            offsetDayNum -= daysOfLunarYear;
        }
        if (offsetDayNum < 0 || lunarYear == outBoundYear) return;

        leapLunarMonth = getLeapMonth(lunarYear);

        for (lunarMonth = 1; lunarMonth <= 12; lunarMonth++) {
            int daysOfLunarMonth = 0;
            if (isLeapMonth) daysOfLunarMonth = getLeapMonthDays(lunarYear);
            else daysOfLunarMonth = getLunarMonthDays(lunarYear, lunarMonth);

            if (offsetDayNum < daysOfLunarMonth) break;
            else {
                offsetDayNum -= daysOfLunarMonth;
                if (lunarMonth == leapLunarMonth) {
                    if (!isLeapMonth) {
                        lunarMonth--;
                        isLeapMonth = true;
                    } else {
                        isLeapMonth = false;
                    }
                }
            }
        }

        lunarDay = offsetDayNum + 1;

        lunarService.lunarYear = lunarYear;
        lunarService.lunarMonth = lunarMonth;
        lunarService.lunarDay = lunarDay;
        lunarService.isLeapMonth = isLeapMonth;

        lunarService.solarYear = year;
        lunarService.solarMonth = month;
        lunarService.solarDay = day;
    }

}
