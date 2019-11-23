package sop.kawisara.validate.service;

import org.springframework.stereotype.Service;
import sop.kawisara.validate.model.Time;

@Service
public class ValidateTimeService {
    public String validate(Time time){
            if (time.getTime() >= 8 && time.getBossTime() == 0){
                return "false";
            }
            else if(time.getTime() >= 8 && time.getBossTime() <= time.getTime()){
                return "true";
            }
            else if(time.getTime() <= 8 && time.getBossTime() <= time.getTime() ){
                return "false";
            }
            return "true";
        }
}
