package com.yang.controller;

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
public class TravelController extends MObject {



    @Autowired
    private RestTemplate restTemplate;



    /**
     * @param
     * @return get the detail info of the traveller by useId
     * call the interface in Route Service to get the route info of the traveller
     */
    @ResponseBody
    @PostMapping("/getDetailInfo")
    @MRestApiType
    public MResponse getDetailInfo(@RequestParam("userId") String userId, @RequestHeader HttpHeaders httpHeaders){
        MResponse result = new MResponse();

        if (userId.equals("00001")){

            MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
            multiValueMap.add("userId",userId);

            HttpEntity httpEntity = new HttpEntity(multiValueMap,httpHeaders);
            ResponseEntity<MResponse> responseResponseEntity = restTemplate.exchange("http://routeservice/getRouteInfo", HttpMethod.POST,httpEntity,MResponse.class);

//            MResponse mResponse1 = restTemplate.postForObject(url,multiValueMap,MResponse.class);
            if (responseResponseEntity.getBody().getStatus().equals("Success")){
                result.setValueMap(responseResponseEntity.getBody().getValueMap());
            }
        }
        result.set("name","张三");
        result.set("type","单人游");

        return result;


    }

    /**
     * @param
     * @return search the seat info of the traveller by flight
     */
    @ResponseBody
    @PostMapping("/getSeatDistribute")
    @MRestApiType
    public MResponse getSeatDistribute(@RequestParam(value = "flight") String flight, @RequestHeader HttpHeaders httpHeaders){

        MResponse result = new MResponse();
        if (flight.equals("MU5542")){
            result.set("seat", "06B");
        }
        return  result;
    }

}