package respfunction;

import respfunction.Data.*;
import lombok.Data;

import java.util.List;

@Data
public class orderGetResp {
    private List<Orders> orders;
    private PageInfo pageInfo;
    private List<Metro> availableStations;
}
