package yang.controller;

import com.mbs.common.base.MResponse;
import com.mbs.mclient.annotation.MRestApiType;
import com.mbs.mclient.base.MObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class RouteController extends MObject {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * @param
     * @return get the info the travel route
     *
     * invoked by the interface in Travel Service and it also invoke the function in the travel service
     */
    @ResponseBody
    @PostMapping("/getRouteInfo")
//    @MRestApiType
    public MResponse getRouteInfo(@RequestParam(value = "userId") String userId,
                                  @RequestHeader HttpHeaders httpHeaders){
        System.out.println("===================");
        MResponse result = new MResponse();
        result.set("from","威海");
        result.set("to","上饶");
        result.set("flight", "MU5542");
        String url = "http://travelservice/getSeatDistribute";
        MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("flight","MU5542");

        HttpEntity httpEntity = new HttpEntity(multiValueMap,httpHeaders);
        ResponseEntity<MResponse> responseResponseEntity =
                restTemplate.exchange(url, HttpMethod.POST,httpEntity,MResponse.class);

        if (responseResponseEntity.getBody().get("seat")!=null){
            result.set("seat",responseResponseEntity.getBody().get("seat"));
        }
        return result;
    }
}
