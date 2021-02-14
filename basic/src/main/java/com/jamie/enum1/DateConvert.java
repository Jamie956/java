package com.jamie.enum1;

public enum DateConvert {
    /**
     *
     */
    HOURS {
        @Override
        public long toMinutes(long hour) {
            return hour * 60;
        }

        @Override
        public long toSeconds(long hour) {
            return hour * 60 * 60;
        }

    },

    DAYS {
        @Override
        public long toHours(long day) {
            return day * 24;
        }

        @Override
        public long toMinutes(long day) {
            return day * 24 * 60;
        }

        @Override
        public long toSeconds(long day) {
            return day * 24 * 60 * 60;
        }
    };

    public long toMinutes(long duration) {
        throw new AbstractMethodError();
    }

    public long toSeconds(long duration) {
        throw new AbstractMethodError();
    }

    public long toHours(long duration) {
        throw new AbstractMethodError();
    }

    public static void main(String[] args) {
        long a = DateConvert.HOURS.toMinutes(1);
        long b = DateConvert.DAYS.toHours(1);
        long c = DateConvert.HOURS.toSeconds(1);
    }

}
