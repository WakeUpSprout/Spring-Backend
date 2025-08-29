package wakeup.sprout.spring.common.mapper;

import org.mapstruct.Mapper;
import wakeup.sprout.spring.common.annotation.mapper.MapCreatedTime;
import wakeup.sprout.spring.common.util.mapper.CustomTimeStamp;

import java.time.LocalDateTime;

@Mapper
public class CustomTimestampMapper {
    @MapCreatedTime
    public String formatCreatedTime(LocalDateTime dateTime) {
        return new CustomTimeStamp(dateTime).toString();
    }
}
