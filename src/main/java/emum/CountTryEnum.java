package emum;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author zyq
 * @version 1.0
 * @date 2020/4/13
 */

public enum CountTryEnum {
    SPRING(1,"春天"),
    SUMMER(2,"夏天"),
    AUTUMN(3,"秋天"),
    WINTER(4,"冬天");

    @Getter private Integer code;
    @Getter private String desc;

    CountTryEnum(Integer code,String desc) {
        this.desc = desc;
        this.code = code;
    }
    public static List<StrtusResponse> getStatusEnum() {
        List<StrtusResponse> responses = new LinkedList<>();
        for (CountTryEnum countTryEnum : CountTryEnum.values()) {
            responses.add(StrtusResponse.builder()
                    .realValue(countTryEnum.getCode())
                    .displayValue(countTryEnum.getDesc())
                    .build());
        }
        return responses;
    }
    public static CountTryEnum forEach_CountryEnum(int index){
        CountTryEnum[]myarray=CountTryEnum.values();
        for (CountTryEnum element:myarray) {
            if (index==element.getCode()){
                return element;
            }
        }
        return null;
    }
}
