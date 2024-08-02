    /**
     * This is a class of Date and time, it includes the year, month, day, hour and minute, getter functions
     * for each of them, and a describe function to display the data and time in formatted.
     * @author Junbo Liang, junbol@student.unimelb.edu.au, 1019905.
     *
     */
    public class Date {
        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;

        /**
         * A constructor method.
         * @param year Year.
         * @param month Month.
         * @param day Day.
         * @param hour Hour.
         * @param minute Minute.
         */
        public Date(int year, int month, int day, int hour, int minute) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
        }

        /**
         * A getter to get hour.
         * @return Hour.
         */
        public int getHour() {
            return hour;
        }
        /**
         * A getter to get minute.
         * @return minute.
         */
        public int getMinute() {
            return minute;
        }
        /**
         * A getter to get year.
         * @return Year.
         */
        public int getYear() {
            return year;
        }
        /**
         * A getter to get month.
         * @return Month.
         */
        public int getMonth() {
            return month;
        }
        /**
         * A getter to get day.
         * @return Day.
         */
        public int getDay() {
            return day;
        }

        /**
         * A method to display the information of this instance.
         * @return A string in format(yyyy-mm-dd hh:mm)
         */
        public String describe(){
            return String.format("%04d-%02d-%02d %02d:%02d", year, month, day, hour, minute);
        }

    }

