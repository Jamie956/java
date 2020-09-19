package basic.aenum;

public enum EnumTest {
    /**
     *
     */
    HOURS {
        @Override
        public long toMinutes(long d) {
            System.out.println(d * 60);

            return d * 60;
        }
    },

    DAYS {
        @Override
        public long toHours(long d) {
            System.out.println(d * 24);

            return d * 24;
        }
    };

    public long toMinutes(long duration) {
        throw new AbstractMethodError();
    }

    public long toHours(long duration) {
        throw new AbstractMethodError();
    }

    public static void main(String[] args) {
        EnumTest.HOURS.toMinutes(1);
    }

}
