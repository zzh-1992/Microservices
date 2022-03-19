/*
 *Copyright @2021 Grapefruit. All rights reserved.
 */

package com.grapefruit.a;

import com.netflix.discovery.shared.Application;
import com.netflix.eureka.registry.InstanceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 相关描述
 *
 * @author zhihuangzhang
 * @version 1.0
 * @date 2022-03-19 08:17
 */
@RestController
@RequestMapping("/")
public class InstanceRegistryController {

    @Autowired
    InstanceRegistry registry;

    @GetMapping("/getSortedApplications")
    public Object getApps(){
        List<Application> sortedApplications = registry.getSortedApplications();

        Map<String, Application> collect = sortedApplications.stream().collect(Collectors.toMap(Application::getName,
                app -> app));
        return collect;
    }
}
