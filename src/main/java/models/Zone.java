package models;

import lombok.Builder;
import lombok.Getter;

@Getter @Builder
public class Zone {

    private Integer number;
    private Integer totalRows;
    private Integer totalColumns;
}
