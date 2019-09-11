package Duke;

public class DateTimeParser {

    public static String parseDeadlineInfo(String by) throws DukeException{
        //expected input format: dd/mm/year time
        String[] data = by.split(" ", 2); //split by whitespace
        if(data.length != 2){
            throw new DukeException("     ☹ OOPS!!! Wrong input format for creating new Duke.Deadline. Follow this eg: deadline return book /by 2/12/2019 1800");
        }

        //trim the data
        data[0] = data[0].trim();
        data[1] = data[1].trim();

        String[] dates = data[0].split("/");
        if(dates.length != 3) throw new DukeException("     ☹ OOPS!!! Wrong input format for date when creating new Duke.Deadline. Follow this eg: /by 2/12/2019 1800");

        int day, month, year, time;
        try{
            day = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            year = Integer.parseInt(dates[2]);

            time = Integer.parseInt(data[1]);
        } catch (NumberFormatException e){
            throw new DukeException("     ☹ OOPS!!! Wrong input format for date or time when creating new Duke.Deadline. Follow this eg: /by 2/12/2019 1800");
        }

        //work with the data
        if(time < 0 || time > 2400){
            throw new DukeException("     ☹ OOPS!!! Duke.Deadline time is not a valid input. Follow this eg: /by 2/12/2019 1800");
        }

        if(isDateVaid(day, month, year)){
            return parseDate(day, month, year) + ", " + parseTime(time);
        } else {
            throw new DukeException("     ☹ OOPS!!! You have entered an invalid date");
        }

    }

    public static String parseEventInfo(String at) throws DukeException{
        //expected input format dd/mm/year time-time
        String[] data = at.split(" ", 2); //split by whitespace
        if(data.length != 2){
            throw new DukeException("     ☹ OOPS!!! Wrong input format for creating new Duke.Event. Follow this eg: event team meeting /at 2/12/2019 1600-2000");
        }

        //trim the data
        data[0] = data[0].trim();
        data[1] = data[1].trim();

        //extract out the dates
        String[] dates = data[0].split("/");
        if(dates.length != 3) throw new DukeException("     ☹ OOPS!!! Wrong input format for date when creating new Duke.Event. Follow this eg: /at 2/12/2019 1600-2000");

        int day, month, year, time1, time2;
        try{
            day = Integer.parseInt(dates[0]);
            month = Integer.parseInt(dates[1]);
            year = Integer.parseInt(dates[2]);
            String[] times = data[1].split("-");
            time1 = Integer.parseInt(times[0]);
            time2 = Integer.parseInt(times[1]);
        } catch (NumberFormatException e){
            throw new DukeException("     ☹ OOPS!!! Wrong input format for date or time when creating new Duke.Event. Follow this eg: /at 2/12/2019 1600-2000");
        } catch (IndexOutOfBoundsException e){
            throw new DukeException("     ☹ OOPS!!! Wrong input format for date or time when creating new Duke.Event. Follow this eg: /at 2/12/2019 1600-2000");
        }

        //work with the data
        if(time1 < 0 || time1 > 2400 || time2 < 0 || time2 > 2400){
            throw new DukeException("     ☹ OOPS!!! Duke.Event timing is not a valid input. Follow this eg: /at 2/12/2019 1600-2000");
        }

        if(time1 > time2){
            throw new DukeException("     ☹ OOPS!!! The event's start time cannot be after the event's end time!");
        }

        if(isDateVaid(day, month, year)){
            return parseDate(day, month, year) + ", " + parseTime(time1)+"-"+parseTime(time2);
        } else {
            throw new DukeException("     ☹ OOPS!!! You have entered an invalid date");
        }
    }

    /**
     *  checking if the user input date is a valid one
     * */
    private static boolean isDateVaid(int d, int m, int y){
        if(d < 1 || d > 31 || m < 1 || m > 12 || y < 0){
            return false;
        }

        if(m == 2){
            if(isLeapYear(y)){
                if(d > 29){
                    return false;
                }
            } else{
                if(d > 28){
                    return false;
                }
            }
        } else if(m==4 || m==6 || m==9 || m==11){
            if(d > 30){
                return false;
            }
        }
        return true;
    }


    private static boolean isLeapYear(int y){
        if((y%4 == 0 && y%100 != 0) || y %400==0){
            return true;
        }
        return false;
    }

    private static String parseDate(int day, int month, int year){
        String date = "";

        //appending day
        if(day == 1 || day == 21 || day == 31){
            date += day +"st of ";
        } else if(day == 2 || day == 22){
            date += day + "nd of ";
        } else if(day == 3 || day == 23){
            date += day + "rd of ";
        } else{
            date += day + "th of ";
        }

        //appending month and year
        if(month == 1){
            date += "January ";
        } else if(month == 2){
            date += "February ";
        } else if(month == 3){
            date += "March ";
        } else if(month == 4){
            date += "April ";
        } else if(month == 5){
            date += "May ";
        } else if(month == 6){
            date += "June ";
        } else if(month == 7){
            date += "July ";
        } else if(month == 8){
            date += "August ";
        } else if(month == 9){
            date += "September ";
        } else if(month == 10){
            date += "October ";
        } else if(month == 11){
            date += "November ";
        } else if(month == 12){
            date += "December";
        }

        date += year;

        return date;
    }

    private static String parseTime(int time) throws DukeException{
        String t;
        int hr, mins;
        if(time == 2400) time = 0000;
        hr = time/100;
        mins = time %100;

        if(hr >= 12){
            hr -= 12;
            t = "pm";
        } else {
            t = "am";
        }

        if(hr == 0){
            hr = 12;
        }

        if(mins == 0){
            return hr+""+t;
        } else if(mins <10){
            return hr+":0"+mins+t;
        } else if(mins < 60){
            return hr+":"+mins+t;
        } else {
            throw new DukeException("     ☹ OOPS!!! Invalid time entered!");
        }
    }
}
