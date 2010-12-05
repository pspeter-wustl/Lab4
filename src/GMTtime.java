public class GMTtime {

  private int localTime;          //local time as represented in flight-data.txt (hhmm)
  private int localMilitaryTime;  //local time in 24 hour clock
  private int offset;             //needed to adjust local time to gmtTime
  private boolean am;             //true iff AM
  private int localHours;         //hour in local time (on a 12 hour clock)
  private int minutes;            //minutes (between 0 and 59)
  private int gmtTime;            //GMT time on a 24 hour clock in hhmm form
  private int gmtInMinutes;       //GMT time converted into minutes (hour * 60 + minutes)
                                  //   for easy computation of the difference between times


  // Constructor
  //   locateTime is in the form hhmm given by flight-data.txt
  //   city is the Airport for the time (from which the GMT conv can be found)
  //   am is true if and only if the localTime is AM (as opposed to PM)
  //
  public GMTtime(int localTime, int gmtOffset, boolean am){
    this.localTime = localTime;
    offset = gmtOffset;
    this.am = am;
    minutes = localTime % 100;                 //minutes not affected by time zone
    localHours = (localTime - minutes)/100;    //hours in local time

    localMilitaryTime = localTime;             //initialize military time to local time
    if (!am && localHours != 12)               //  to convert 1-11PM into 24 hour clock
      localMilitaryTime += 1200;               //     add 12 hours
    else if (am && localHours == 12)           //  to convert 12AM into 24 hour clock
      localMilitaryTime -= 1200;               //     subtract 12 hours

    gmtTime = localMilitaryTime - offset;      //subtract conv to convert to GMT time
    int hours = (gmtTime - minutes)/100;       //hours in GMT time
    gmtInMinutes = hours * 60 + minutes;       //GMT time in minutes 
  }

  public int minutesSince(GMTtime time){
    int duration = this.gmtInMinutes - time.gmtInMinutes;
    if (duration < 0)
      duration += 1440;
    return duration;
  }

  public String toString(){
    String out = localTime + " ";
    if (am)
      out += "AM";
    else
      out += "PM";
    return out;
  }

}

