package com.youxia.springcloud.eurekaapplication.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * Created by liu on 2016/11/24.
 */
@RestController
public class ClientController {

    @Autowired
    DiscoveryClient client;

    @RequestMapping("/sentence")
    public  String getSentence() {
        return
                getWord("spring_cloud_eureka_application_server_verb") + " "
                        + getWord("spring_cloud_eureka_application_server_adjective") + " "
                        + getWord("spring_cloud_eureka_application_server_article") + " "
                        + getWord("spring_cloud_eureka_application_server_subject") + " "
                        + getWord("spring_cloud_eureka_application_server_noun") + ".";//大小写不区分
    }

    public String getWord(String service) {
        List<ServiceInstance> list = client.getInstances(service);
        if (list != null && list.size() > 0 ) {
            URI uri = list.get(0).getUri();
            if (uri !=null ) {
                return (new RestTemplate()).getForObject(uri,String.class);
            }
        }
        return null;
    }
}